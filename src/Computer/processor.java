package Computer;

import java.io.*;
import java.util.*;

public class processor {
	private RegFile registerFile;
	private ArrayList<Object[]> instructions;
	private FpMultiblication fpMultiblication;
	private FpAddition fpAddition;
	private Loads loads;
	private Stores stores;
	private int cycle;
	private ArrayList<String> rawInstructions;
	private boolean stalled;
	public double[] memory;
	private int addexcutecycles;
	private int mullexcutecycles;
	private int storeexcutecycles;
	private int loadexcutecycles;
	private Queue<String> writeQueue;
	private int instCount = 0;
	String logs ="";

	public processor(int fpAddNum , int fpMult,int loadNum, int storeNum , int memSize , int addcycles,int mullcycles,int storecycles,int loadcycles,String fileName) throws IOException {
		this.instructions = new ArrayList<Object[]>();
		this.fpMultiblication = new FpMultiblication(fpMult);
		this.fpAddition = new FpAddition(fpAddNum);
		this.loads = new Loads(loadNum);
		this.stores = new Stores(storeNum);
		this.cycle = 1;
		this.addexcutecycles=addcycles;
		this.mullexcutecycles=mullcycles;
		this.storeexcutecycles=storecycles;
		this.loadexcutecycles=loadcycles;
		this.rawInstructions = new ArrayList<String>();
		this.stalled = false;
		this.registerFile = new RegFile();
		this.memory=new double[memSize];
		this.writeQueue = new ArrayDeque<String>();
		this.parseFile(fileName);
		this.instCount=this.rawInstructions.size();
		System.out.println(rawInstructions.toString());
		
	}
	private void addInstruction(String inst,String tag,boolean status) {
		Object[] instruction = new Object[7];
		instruction[0] = inst;//type of instruction
		instruction[1] = this.cycle;//issued at
		instruction[2] = null;//start of execution
		instruction[3] = null;//end of execution
		instruction[4] = null;//write at
		instruction[5] = tag;//tag of the instruction
		instruction[6] = status;//waiting
		
		instructions.add(instruction);
	}
	public void parseFile(String fileName) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while (br.ready()) this.rawInstructions.add(br.readLine());
		}
	}
	
	private void issue() {
		if(rawInstructions.isEmpty()) return;
		String instruction= rawInstructions.get(0);
		String[] splitedInstruction = instruction.split(" ");
		if(splitedInstruction[0].equals("add") || splitedInstruction[0].equals("sub")) {
			if(this.fpAddition.isFull()) {
				stalled = true;
			}
			else {
				rawInstructions.remove(0);
				stalled = false;
				Object val1 = this.registerFile.get(splitedInstruction[2]);
				Object val2 = this.registerFile.get(splitedInstruction[3]);	
				String tag = this.fpAddition.issue(splitedInstruction[0],val1, val2);
				this.registerFile.update(splitedInstruction[1], tag);
				this.addInstruction(instruction,tag,!(val1 instanceof Double && val2 instanceof Double));
				logs += "instruction (" + instruction + " ) issued with tag "+tag+". ";
			}
			
		}
		if(splitedInstruction[0].equals("mul") || splitedInstruction[0].equals("div")) {
			if(this.fpMultiblication.isFull()) {
				stalled = true;
			}
			else {
				rawInstructions.remove(0);
				stalled = false;
				Object val1 = this.registerFile.get(splitedInstruction[2]);
				Object val2 = this.registerFile.get(splitedInstruction[3]);				
				String tag = this.fpMultiblication.issue(splitedInstruction[0],val1, val2);
				this.registerFile.update(splitedInstruction[1], tag);
				this.addInstruction(instruction,tag,!(val1 instanceof Double && val2 instanceof Double));
				logs += "instruction (" + instruction + " ) issued with tag "+tag+". ";

			}
			
		}
		if(splitedInstruction[0].equals("ld")) {
			if(this.loads.isFull()) {
				stalled = true;
			}
			else {
				rawInstructions.remove(0);
				stalled = false;
				String tag = this.loads.issue(Integer.parseInt(splitedInstruction[2]));
				this.registerFile.update(splitedInstruction[1], tag);
				this.addInstruction(instruction,tag,false);
				logs += "instruction (" + instruction + " ) issued with tag "+tag+". ";
			}
			
		}
		if(splitedInstruction[0].equals("sd")) {
			if(this.stores.isFull()) {
				stalled = true;
			}
			else {
				rawInstructions.remove(0);
				stalled = false;
				Object val = this.registerFile.get(splitedInstruction[1]);
				String tag = this.stores.issue(val, Integer.parseInt(splitedInstruction[2]));
				this.addInstruction(instruction,tag, !(val instanceof Double));
				logs += "instruction (" + instruction + " ) issued with tag "+tag+". ";
			}
			
		}
	}
	public void execute() {
		String finished ="";
		logs+="Instructions: [ ";
		for(Object[] instruction:instructions ) {
			String tag = (String)instruction[5];
			if(instruction[6].equals(false) && instruction[2]==null) {// not waiting and hasn't started execution
				instruction[2]=cycle;
				if(tag.charAt(0)=='A') {
					instruction[3]=cycle+addexcutecycles-1;
				}
				else if(tag.charAt(0)=='M') {
					instruction[3]=cycle+mullexcutecycles-1;
				}
				else if(tag.charAt(0)=='L') {
					instruction[3]=cycle+loadexcutecycles-1;
				}
				else if(tag.charAt(0)=='S') {
					instruction[3]=cycle+storeexcutecycles-1;
				}
			}
			int endAt = instruction[3]==null? 0:(int)instruction[3];
			if(cycle<endAt && endAt!=0) logs+=tag+" ";
			if(cycle==endAt) finished += tag+" finished executing. ";
			if(instruction[4]==null && endAt==cycle-1) {
				if(!writeQueue.contains(tag)) {
					writeQueue.add(tag);
				}
			}
		}
		logs+="] executed. "+finished;
		
	}
	public void write() {
		if(!writeQueue.isEmpty()) {
			instCount--;
			String tag = writeQueue.poll();
			if(tag.charAt(0)=='S') logs+="Insrtuction "+tag+" wrote to memory. ";
			else logs+="Insrtuction "+tag+" wrote to register file. ";
			double result = Double.MAX_VALUE;
			boolean isStore = false;
			if(tag.charAt(0)=='A') {
				result= this.fpAddition.puplishResult(tag);
			}
			else if(tag.charAt(0)=='M') {
				result= this.fpMultiblication.puplishResult(tag);
			}
			else if(tag.charAt(0)=='L') {
				result= this.loads.puplishResult(tag,memory);
			}
			else if(tag.charAt(0)=='S') {
				this.stores.puplishResult(tag, memory);
				isStore = true;
			}
			ArrayList<String> can = new ArrayList<>();
			if(!isStore) {
			can.addAll( this.fpAddition.listen(tag, result));
			can.addAll( this.fpMultiblication.listen(tag, result));
			can.addAll( this.stores.listen(tag, result));
			this.registerFile.listen(tag, result);
			}
			
			for(Object[] instruction:instructions) {
				if(instruction[5].equals(tag)) {
					instruction[4] = cycle;
				}
				if(!isStore && can.contains(instruction[5])) {
					instruction[6]=false;
					can.remove(instruction[5]);
				}
			}

			
		}
	}
	
	
	
	public void run() {
		while(this.instCount>0) {
			logs = "";
			execute();
			write();
			issue();
			this.cycle++;
			System.out.println("cycle" + (cycle-1));
			for(Object[] instruction:instructions) {
				System.out.print("tag "+instruction[5] + " start "+ ((instruction[2]==null)? "?":(int)instruction[2]) );
				System.out.println(" end " + ((instruction[3]==null)? "?":(int)instruction[3])+" Write "+ (instruction[4]==null? "?":(int)instruction[4]));
			}
			System.out.println(logs);
		}
		
	}
	// 
	public int executeCycle() {
		if(this.instCount>0) {
			logs = "";
			execute();
			write();
			issue();
			this.cycle++;

			System.out.println("cycle" + (cycle-1));
			for(Object[] instruction:instructions) {
				System.out.print("tag "+instruction[5] + " start "+ ((instruction[2]==null)? "?":(int)instruction[2]) );
				System.out.println(" end " + ((instruction[3]==null)? "?":(int)instruction[3])+" Write "+ (instruction[4]==null? "?":(int)instruction[4]));
			}
			System.out.println(instCount);
		}
		System.out.println(instCount);
		return this.cycle;
	}
	
	
	public ArrayList<Object[]> getInstructions() {
		return instructions;
		
	}
	public Object[][] getRegFile() {
		return registerFile.getRegister();
	}
	public Object[][] getAddResevation() {
		return fpAddition.getTable();
	}
	public Object[][] getMultResevation() {
		return fpMultiblication.getTable();
	}
	public Object[][] getLoadResevation() {
		return loads.getTable();
	}
	public Object[][] getStoreResevation() {
		return stores.getTable();
	}
	
	public static void main(String[] args) throws IOException {
		processor processor = new processor(10, 10, 10, 10, 10,2,2,2,2,"Program_1.txt");
		//processor.parseFile("Program_1.txt");
		//processor.instCount=processor.rawInstructions.size();
		//System.out.println("jajaj"+processor.instCount);
		processor.memory[0]=2;
		processor.memory[1]=9;
		processor.run();
	}
	
}
