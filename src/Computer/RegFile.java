package Computer;

import java.util.ArrayList;

public class RegFile {
	private Object[][] register;

	public RegFile() {
		register = new Object[32][3];
		for (int i = 0; i < register.length; i++) {
			register[i][0] = "F" + (i);
		}
	}

	public void listen(String tag, Double value) {
		for (Object[] instruction : register) {
			if (instruction[2]!=null && instruction[2].equals(tag)) {
				instruction[1] = value;
				instruction[2] = null;
			}
		}
	}
	
	public void update( String reg,String tag) {
			int index = Integer.parseInt(reg.substring(1));
			register[index][2] = tag;
	}
	
	public Object get(String find) {
		int reg = Integer.parseInt(find.substring(1));
		if(register[reg][2]==null) {
			return register[reg][1];
		}
		return register[reg][2];

	}
	public Object[][] getRegister() {
		return register;
	}
}
