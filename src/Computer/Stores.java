package Computer;

import java.util.ArrayList;

public class Stores {
	private int numberOfCells;
	private Object[][] table;// busy 
	private int emptyCells;
	
	
	public Stores(int numberOfCells) {
		this.numberOfCells =numberOfCells;
		this.emptyCells = numberOfCells;
		this.table = new Object[numberOfCells][5];
		for(int i = 0;i<numberOfCells;i++) {
			table[i][0] = "S"+ (1+i) ;
		}
	}
	public String issue(Object operand,int address ) {
		emptyCells--;
		for(Object[] instruction:table) {
			if(instruction[1]==null || (int)instruction[1]==0) {
				String tag = (String)instruction[0]; //tag
				instruction[1] =1;//busy
				instruction[2] =address;//address
				if(operand instanceof Double) {
					instruction[3] =(Double)operand;//vj
				}
				else {
					instruction[4] = operand;//qj
				}	
				return tag;
			}
			else {
				continue;
			}	
		}
		return "";
	}
	
	public boolean isFull() {
		return emptyCells==0;
	}
	
	public void puplishResult(String tag,double[] memory) {
		emptyCells++;
		int cell =Integer.parseInt(tag.substring(1)) -1;
		memory[(int)table[cell][2]] = (double)table[cell][3];
		table[cell][1] = 0;
	}
	
	public ArrayList<String> listen(String tag ,Double value) {
		ArrayList<String> ready = new ArrayList<String>(); 
		for(Object[] instruction: table) {
			if(instruction[1]!=null && instruction[1].equals(0)) {
				continue;
			}
			if(instruction[4]!=null && instruction[4].equals(tag)) {
				instruction[3]=value;
				instruction[4] = null;
				ready.add((String)instruction[0]);
			}
	}
		return ready;
	}
	
	public Object[][] getTable() {
		return table;
	}
	
}
