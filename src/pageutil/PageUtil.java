package pageutil;

import bean.*;

public class PageUtil{
	
	public static Page createPage(int pageCount, int totalCount, int currentPage){
		pageCount = getPageCount(pageCount);
		currentPage = getCurrentPage(currentPage);
		int totalPage = getTotalPage(pageCount, totalCount);
		int pageBegin = getPageBegin(pageCount, currentPage);
		boolean hasNext = getHasNextPage(totalPage, currentPage);
		boolean hasPre = getHasPrePage(currentPage);
		return new Page(totalCount, pageCount, currentPage, totalPage, pageBegin, hasNext, hasPre);
	}
	
	public static int getPageCount(int pageCount){
		return pageCount == 0 ? 5 : pageCount;  	//默认值为每页5条记录
	}
	
	public static int getCurrentPage(int currentPage) { //获得当前页,默认给1
        return currentPage == 0 ? 1 : currentPage;
    }
	
	public static int getTotalPage(int pageCount,int totalCount) {//获得总页数
		return totalCount % pageCount == 0 ? totalCount / pageCount : totalCount / pageCount + 1;
	}
	
	public static int getPageBegin(int pageCount,int currentPage) {//获得起始位置
        return ( currentPage - 1 ) * pageCount;
    }
	
	public static boolean getHasPrePage(int currentPage) {//是否有上一页
        return currentPage == 1 ? false : true;
    }
    public static boolean getHasNextPage(int totalPage, int currentPage) {  //是否有下一页
        return currentPage == totalPage || totalPage == 0 ? false : true;
    }
}
