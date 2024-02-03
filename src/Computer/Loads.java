package Computer;

import java.util.ArrayList;

public class Loads {
	private int numberOfCells;
	private Object[][] table;// busy 
	private int emptyCells;
	
	
	public Loads(int numberOfCells) {
		this.numberOfCells =numberOfCells;
		this.emptyCells = numberOfCells;
		this.table = new Object[numberOfCells][3];
		for(int i = 0;i<numberOfCells;i++) {
			table[i][0] = "L"+ (1+i) ;
		}
	}
	public String issue(int address ) {
		emptyCells--;
		for(Object[] instruction:table) {
			if(instruction[1]==null || (int)instruction[1]==0) {
				String tag = (String)instruction[0]; //tag
				instruction[1] =1;//busy
				instruction[2] =address;//address
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
	
	public double puplishResult(String tag,double[] memory) {
		emptyCells++;
		int cell =Integer.parseInt(tag.substring(1)) -1;
		double result = memory[(int)table[cell][2]];
		table[cell][1] = 0;
		return result;
	}
	public Object[][] getTable() {
		return table;
	}
	
}
