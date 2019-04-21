

	public class Register {

		private String name;
		private int size;
		private String value="";
		
		public Register(String name,int size)
		{
			this.name=name;
			this.size = size;
			for(int i=0;i<size;i++)
				value="0"+value;
		}
		
		public Register(int size)
		{
			this.size = size;
			for(int i=0;i<size;i++)
				value="0"+value;
		}
		
		
		
		public String getValue()
		{
			return value;
		}
		
		
		public void setValue(String value)
		{
			this.value = value;
		}
		
		
		public int getSize()
		{
			return size;
		}
		
		public String getName()
		{
			return name;
		}
		

		
		public void clear()
		{
			for(int i=0;i<size;i++)
				value="0"+value;
		}
		
		public String toString()
		{
//			String r = Integer.toBinaryString(value);
			String r =value;
			while(r.length() < size)
				r = "0" + r;
			if(name != null)
				r = name + ": " + r;
			return r + " (DEC = "+Integer.parseInt(value,2)+")";
		}
	}
