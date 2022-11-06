package cosodulieunc;
import java.util.*;
import java.io.*;
// Class định nghĩa một danh sách các phụ thuộc hàm
public class TapPTH {
	Vector<PTH> TapPTH;
	
	public void readFile()
	{
		TapPTH = new Vector<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Admin\\Documents\\Cosodulieunc\\src\\cosodulieunc\\input.txt"));
			String line  = br.readLine();
			while(line != null)
			{
				TapPTH.add(new PTH(line));
				line  = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Vector<PTH> getTapPTH()
	{
		return TapPTH;
	}
	
	public void printTapPTH()
	{
		for (PTH df:TapPTH)
			df.printPTH();
	}
	
	public String getSetOfAttributes()
	{
		HashSet<String> s = new HashSet<>();
		for (PTH df:TapPTH)
		{
			for (int i = 0; i < df.X().length(); i++)
				s.add(df.X().substring(i, i+1));
			for (int i = 0; i < df.Y().length(); i++)
				s.add(df.Y().substring(i, i+1));
		}
		String res = "";
		for (String k:s)
			res += k;
		return res;
	}
}
