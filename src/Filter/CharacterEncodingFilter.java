package Filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {

	private FilterConfig config;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
		String encoding = config.getInitParameter("encoding");
		
		if(encoding==null)
			encoding = "utf-8";
		
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html; charset=" + encoding);
		
		EncodingHttpServletRequest ehsr = new EncodingHttpServletRequest(request);
		
		chain.doFilter(ehsr, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.config = arg0;
	}
}

class EncodingHttpServletRequest extends HttpServletRequestWrapper{

	    public EncodingHttpServletRequest(HttpServletRequest request){  
	        super(request);  
	    }  
	    //只对get请求方式进行改写  
	    public String getParameter(String name) {  
	        String value = super.getParameter(name);  
	        if(value==null)  
	            return value;  
	        //得到请求方式  
	        String method = super.getMethod();  
	        if("post".equalsIgnoreCase(method)){  
	            try {  
	                value = new String(value.getBytes("utf-8"),super.getCharacterEncoding());  
	            } catch (UnsupportedEncodingException e) {  
	                e.printStackTrace();  
	            }  
	        }
	        if("get".equalsIgnoreCase(method)){  
	            try {  
	                value = new String(value.getBytes("utf-8"),super.getCharacterEncoding());  
	            } catch (UnsupportedEncodingException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return value;  
	    }     
	} 
	
