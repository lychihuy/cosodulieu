package cosodulieunc;

import java.util.ArrayList;
import java.util.HashSet;


public class giaithuat {
	// Kiểm tra 1 tập có phải tập con 
	public boolean isSubSet(String a, HashSet<String> b) {
		for (String i : a.split(""))
		{
			if (!b.contains(i))
				return false;
		}
		return true;
	}
	
	
	// Thuật toán 1: Tìm bao đóng
	// F: tập các phụ thuộc hàm
	// X: Tập các thuộc tính
	// Trả lại: X+
	public HashSet<String> getBaodong(TapPTH F, String X){
		HashSet<String> closureX = new HashSet<>();
		// Bước 1: X+ = X
		 for (String i: X.split("")) {
			 closureX.add(i);
		 }
		 int m = F.getTapPTH().size(); // m là số lượng phụ thuộc hàm
		 boolean done = false;
		 while (!done) {
			 done = true;
			 for (int i = 0; i < m; i++)
			 {
				if (isSubSet(F.getTapPTH().get(i).X(), closureX) && !isSubSet(F.getTapPTH().get(i).Y(), closureX)) {
//					closureX.add(F.getDfs().get(i).Y());
					for (String k:F.getTapPTH().get(i).Y().split(""))
						closureX.add(k);
					done = false;
				}
			 }
		 }
		 return closureX;
	}
	
	public boolean isDependencyInClosure(TapPTH F, PTH df)
	{
		return laTapHopCon(df.Y(), hToS(getBaodong(F, df.X())));
	}
	
	// Những thuộc tính bên X mà bỏ đi vẫn tạo được PTH dư thừa
	public boolean isAttributeRedundant(TapPTH F, PTH df, String a)
	{
		String X = remove(df.X(), a);
		String Y = df.Y();
		df.setPTH(X + " -> " + Y);

		
//		df.printDf();
//		F.printDfs();
//		System.out.println("Xét " + a);
		return isDependencyInClosure(F, df);
	}
	
	public String remove(String goc, String xoa)
	{
		int pos = goc.indexOf(xoa);
		return goc.substring(0, pos) + goc.substring(pos+xoa.length());				
	}
	
	
	// Xóa những thuộc tính dư thừa vế trái
	public String reduceAtributes(TapPTH F, PTH df)
	{
		String X_new = df.X();
		String a1[] = df.X().split("");
		for (String a:a1)
		{
			PTH df1 = new PTH(X_new + " -> " + df.Y());
			//System.out.println("Lấy ra " + a);
			if (isAttributeRedundant(F, df1, a)) 
//			{
				X_new = remove(X_new, a);	
//				System.out.println("==> Bỏ  " + a);
//			}else {
//				System.out.println("==> Không bỏ " + a );
//			}
		}
		return X_new;
	}
	
	public String reduceAtributesDetails(TapPTH F, PTH df)
	{
		String X_new = df.X();
		String a1[] = df.X().split("");
		for (String a:a1)
		{
			PTH df1 = new PTH(X_new + " -> " + df.Y());
			System.out.println("Lấy ra " + a);
			if (isAttributeRedundant(F, df1, a)) 
			{
				X_new = remove(X_new, a);	
				System.out.println("==> Bỏ  " + a);
			}else {
				System.out.println("==> Không bỏ " + a );
			}
		}
		return X_new;
	}
	
	// Thuật toán 2: Tìm khóa đâu tiên
	public String getFirstKey(TapPTH F, String A) // A: tập các thuộc tính
	{
		PTH df = new PTH(A + " -> " + A);
		return reduceAtributes(F, df); // Tập thuộc tính khóa
	}
	public String getFirstKeyDetails(TapPTH F, String A) // A: tập các thuộc tính
	{
		System.out.println("Xét tập thuộc tính: " + A);
		PTH df = new PTH(A + " -> " + A);
		return reduceAtributesDetails(F, df); // Tập thuộc tính khóa
	}
	
	public boolean isIntersect(String x, String y)
	{
		for (int i = 0; i < x.length(); i++)
			for (int j = 0; j < y.length(); j++)
				if (x.charAt(i) == ' ' || y.charAt(j) ==  ' ')
					continue;
				else if (x.charAt(i) == y.charAt(j))
					return true;
		return false;
	}
	
	public String Uninon(String a, String b)
	{
		HashSet<String> s = new HashSet<>();
		a.trim();
		b.trim();
		for (int i = 0; i < a.length(); i++) {
			s.add(a.substring(i, i+1));
		}
		for (int i = 0; i < b.length(); i++) {
			s.add(b.substring(i, i+1));
		}
		String res = "";
		for (String j:s)
			res += j;
		return res;
	}
	
	public String HieuHaiTapHop(String a, String b)
	{
		HashSet <String> res1 = new HashSet<>();
		for (int i = 0; i < a.length(); i++)
		{
			String x = a.substring(i, i+1);
			if (!b.contains(x))
				res1.add(x);
		}
		String res = "";
		for (String j:res1)
			res += j;
		return res;
	}
	// a là con của b
	public boolean laTapHopCon(String a, String b)
	{
		for (int i = 0; i < a.length(); i++)
		{
			String x = a.substring(i, i+1);
			if (!b.contains(x))
				return false;
		}
		return true;
	}
	
	public HashSet<String> sToH(String s)
	{
		HashSet<String> h = new HashSet<>();
		for (int i = 0; i < s.length(); i++)
			h.add(s.substring(i, i+1));
		return h;
	}
	
	public String hToS(HashSet<String> h)
	{
		String s = "";
		for (String i:h)
			s += i;
		return s;
	}
	
	public boolean dk2(ArrayList<String>keys, PTH fd, String K)
	{
		for (String K_new : keys) {
			if (laTapHopCon(K_new, Uninon(fd.X(), HieuHaiTapHop(K, fd.Y()))))
				return true;
		}
		return false;
	}
	
	public ArrayList<String> getAllKeys(TapPTH F, String A)
	{
		ArrayList<String> keys = new ArrayList<>();
		keys.add(getFirstKey(F, A));
		for (int i = 0; i < keys.size(); i++)
		{
			String K = keys.get(i);
			for (PTH fd: F.getTapPTH())
			{
				if (isIntersect(fd.Y(), K) && !dk2(keys, fd, K))
				{
//					
//					if (!HieuHaiTapHop(Uninon(K, fd.X()), fd.Y()).equals(""))
					if (!Uninon(fd.X(), HieuHaiTapHop(K, fd.Y())).equals(""))
					{
//						PTH df_new = new PTH(HieuHaiTapHop(Uninon(K, fd.X()), fd.Y()) + " -> " + A);
						PTH df_new = new PTH(Uninon(fd.X(), HieuHaiTapHop(K, fd.Y())) + " -> " + A);
//						df_new.printDf();
						String N = reduceAtributes(F, df_new);
						keys.add(N);
					}
				}
			}
//			System.out.println("***************************");
		}
		return keys;
	}
	
	public ArrayList<String> getAllKeysDetails(TapPTH F, String A)
	{
		ArrayList<String> keys = new ArrayList<>();
		System.out.println("Bước 1: Tìm khóa đầu tiên: " + getFirstKey(F, A));
		int step = 1;
		keys.add(getFirstKey(F, A));
		for (int i = 0; i < keys.size(); i++)
		{
			String K = keys.get(i);
			for (PTH fd: F.getTapPTH())
			{
				if (isIntersect(fd.Y(), K) && !dk2(keys, fd, K))
				{
//					
//					if (!HieuHaiTapHop(Uninon(K, fd.X()), fd.Y()).equals(""))
					if (!Uninon(fd.X(), HieuHaiTapHop(K, fd.Y())).equals(""))
					{
//						PTH df_new = new PTH(HieuHaiTapHop(Uninon(K, fd.X()), fd.Y()) + " -> " + A);
						PTH df_new = new PTH(Uninon(fd.X(), HieuHaiTapHop(K, fd.Y())) + " -> " + A);
						step++;
						System.out.print("Bước " + step +": Xét phụ thuộc hàm ");
						df_new.printPTH();
						String N = reduceAtributesDetails(F, df_new);
						keys.add(N);
					}
				}
			}
//			System.out.println("***************************");
		}
		return keys;
	}
}
