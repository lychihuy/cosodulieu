package cosodulieunc;

// Class định nghĩa một phụ thuộc hàm
public class PTH {
	String df;
	
	public PTH () {}
	public PTH (String s) {
		df = s;
	}
	// Vế trái
	public String X()
	{
		return df.split(" -> ")[0].trim();
	}
	// Vế phải
	public String Y()
	{
		return df.split(" -> ")[1].trim();
	}
	
	public void setPTH(String s)
	{
		df = s;
	}
	
	public void printPTH()
	{
		System.out.println(df);
	}
	
}
