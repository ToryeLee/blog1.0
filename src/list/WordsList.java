package list;

import java.util.ArrayList;
import java.util.List;

public class WordsList {
	
	private static List<String> list = new ArrayList<String>();
	
	static{
		list.add("²İÄãÂè");
		list.add("ÄãÂèB");
		list.add("Éµ±Æ");
		list.add("ÉµB");
		list.add("B»õ");
		list.add("µğÄã");
		list.add("ËÀÈ«¼Ò");
		
	}

	public static List<String> getList() {
		return list;
	}

	public static void setList(List<String> list) {
		WordsList.list = list;
	}
}
