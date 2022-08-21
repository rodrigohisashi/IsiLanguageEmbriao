package br.com.professorisidro.isilanguage.datastructures;

public class IsiVariable extends IsiSymbol {
	
	public static final int INTEGER = 0;
	public static final int TEXT    = 1;
	public static final int DOUBLE  = 2;
	public static final int BOOL    = 3;
	public static final int CHAR    = 4;

	
	private int type;
	private String value;
	
	public IsiVariable(String name, int type, String value) {
		super(name);
		this.type = type;
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "IsiVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
	}
	
	public String generateJavaCode() {
       String str;
       if (type == INTEGER) {
		   str = "int ";
	   }
       else if (type == DOUBLE) {
    	   str = "double ";
       }
       else if (type == BOOL) {
       	   str = "boolean ";
	   }
       else if (type == CHAR) {
       	   str = "char ";
	   }
       else {
    	   str = "String ";
       }
       return str + " "+super.name+";";
	}

	public String generateCppCode() {
		String str;
		if (type == INTEGER) {
			str = "int ";
		}
		else if (type == DOUBLE) {
			str = "double ";
		}
		else if (type == BOOL) {
			str = "bool ";
		}
		else if (type == CHAR) {
			str = "char ";
		}
		else {
			str = "string ";
		}
		return str + " "+super.name+";";
	}


}
