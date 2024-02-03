package Computer;

import java.util.ArrayList;

public class FpAddition {
	private int numberOfCells;
	private Object[][] table;// busy 
	private int emptyCells;
	
	
	public FpAddition(int numberOfCells) {
		this.numberOfCells =numberOfCells;
		this.emptyCells = numberOfCells;
		this.table = new Object[numberOfCells][7];
		for(int i = 0;i<numberOfCells;i++) {
			table[i][0] = "A"+ (1+i) ;
		}
	}
	public String issue(String inst,Object firstOperand,Object secondOperand ) {
		emptyCells--;
		for(Object[] instruction:table) {
			if(instruction[1]==null || (int)instruction[1]==0) {
				String tag = (String)instruction[0]; 
				instruction[1] =1;//busy
				instruction[2] =inst;
				if(firstOperand instanceof Double) {
					instruction[3] =(Double)firstOperand;//vj
				}
				else {
					instruction[5] = firstOperand;//qj
				}
				if(secondOperand instanceof Double) {
					instruction[4] =(Double)secondOperand;//vk
				}
				else {
					instruction[6] = secondOperand;//qk
				}
				instruction[4] =1;
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
	
	public double puplishResult(String tag) {
		emptyCells++;
		int cell =Integer.parseInt(tag.substring(1)) -1;
		double result = 0;
		if(table[cell][2].equals("add")) {
			result = (Double)table[cell][3] + (Double)table[cell][4];
		}
		else if(table[cell][2].equals("sub")){
			result = (Double)table[cell][3] - (Double)table[cell][4];
		}
		table[cell][1] =0;
		table[cell][2] =null;
		table[cell][3] =null;
		table[cell][4] =null;
		table[cell][5] =null;
		table[cell][6] =null;		
		return result;
	}
	
	public ArrayList<String> listen(String tag ,Double value) {
		System.out.println("ops"+tag+" "+ value);
		ArrayList<String> ready = new ArrayList<String>(); 
		for(Object[] instruction: table) {
			if(instruction[1]!=null && (int)instruction[1]==0) {
				continue;
			}
			if(instruction[5]!=null && instruction[5].equals(tag)) {
				instruction[3]=value;
				instruction[5] = null;
				if(instruction[6]==null) {
					ready.add((String)instruction[0]);
				}
			}
			if(instruction[6]!=null && instruction[6].equals(tag)) {
				instruction[4]=value;
				instruction[6] = null;
				if(instruction[5]==null) {
					ready.add((String)instruction[0]);
				}

			}
	}
		return ready;
	}
	public Object[][] getTable() {
		return table;
	}
}
