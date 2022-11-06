package cosodulieunc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
public class Main {
	
	public static void menu(TapPTH TapPTH, String setOfAttrs)
	{
		Scanner sc = new Scanner(System.in);
		giaithuat algo = new giaithuat();
		boolean f = true;
		while(f)
		{
			System.out.println("DANH SÁCH CÁC LỰA CHỌN");
			System.out.println("1. Tìm bao đóng");
			System.out.println("2. Tìm một khóa");
			System.out.println("3. Tìm tất cả các khóa");
			System.out.println("4. Thoát");
			System.out.print("Nhập sự lựa chọn: ");
			int i = sc.nextInt();
			sc.nextLine();
			switch(i)
			{
			case 1:
				System.out.print("Nhập tập thuộc tính: ");
				String x = sc.nextLine();
				HashSet<String> b = algo.getBaodong(TapPTH, x);
				System.out.println("Bao đóng " + x +"+ = " + b);
				break;
			case 2:
				System.out.println("TÌM MỘT THUỘC TÍNH KHÓA");
				System.out.println("----------------KẾT QUẢ----------------");
				System.out.println("Một thuộc tính khóa của bảng là: " + algo.getFirstKeyDetails(TapPTH, setOfAttrs));
				System.out.println("------------------------------------------------");
				break;
			case 3:
				System.out.println("----------------KẾT QUẢ----------------");
				System.out.println("Tất cả các khóa của bảng là: " + algo.getAllKeysDetails(TapPTH, setOfAttrs));
				System.out.println("Số lượng khóa của bảng là: " + algo.getAllKeys(TapPTH, setOfAttrs).size());
				System.out.println("------------------------------------------------");
                                break;
			case 4:
				f = false;
				break;
			default:
				System.out.println("Lựa chọn không hợp lệ! Nhập lại");
			}
			
		}
	}
	
	public static void main(String[] args) {
		TapPTH TapPTH = new TapPTH();
		TapPTH.readFile();
		String setOfAttrs = TapPTH.getSetOfAttributes();
		menu(TapPTH, setOfAttrs);
	} 

}
