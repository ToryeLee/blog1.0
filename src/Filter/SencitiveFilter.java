package Filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import list.WordsList;

public class SencitiveFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//过滤规则，增强request对象中getParameter()方法的功能
		SencitiveRequest req = new SencitiveRequest((HttpServletRequest)request);
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

class SencitiveRequest extends HttpServletRequestWrapper{

	/**
	 * 增强request对象中getParameter()方法的功能	 
	 */
	HttpServletRequest request;
	
	
	public SencitiveRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
		// TODO Auto-generated constructor stub
	}
	
	public String getParameter(String content){
		String str = this.request.getParameter(content);
		
		if( str == null )
			return null;
		
		//当内容不为空时，将敏感词库中的词替换为***
		List<String> list = WordsList.getList();
		
		for(String word : list){
			str = str.replaceAll(word, "***");
		}
		
		return str;
	}
}








