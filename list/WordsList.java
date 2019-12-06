package com.list;

import java.util.ArrayList;
import java.util.List;

public class WordsList {
	
	private static List<String> list = new ArrayList<String>();
	
	static{
		list.add("²âÊÔ");
		list.add("TEST");
	}

	public static List<String> getList() {
		return list;
	}

	public static void setList(List<String> list) {
		WordsList.list = list;
	}
}
