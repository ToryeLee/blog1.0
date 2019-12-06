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
		//���˹�����ǿrequest������getParameter()�����Ĺ���
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
	 * ��ǿrequest������getParameter()�����Ĺ���	 
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
		
		//�����ݲ�Ϊ��ʱ�������дʿ��еĴ��滻Ϊ***
		List<String> list = WordsList.getList();
		
		for(String word : list){
			str = str.replaceAll(word, "***");
		}
		
		return str;
	}
}








