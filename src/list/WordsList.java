package list;

import java.util.ArrayList;
import java.util.List;

public class WordsList {
	
	private static List<String> list = new ArrayList<String>();
	
	static{
		list.add("������");
		list.add("����B");
		list.add("ɵ��");
		list.add("ɵB");
		list.add("B��");
		list.add("����");
		list.add("��ȫ��");
		
	}

	public static List<String> getList() {
		return list;
	}

	public static void setList(List<String> list) {
		WordsList.list = list;
	}
}
