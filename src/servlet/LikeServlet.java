package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LikesOp;

public class LikeServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public LikeServlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		doPost(request,response);
		out.flush();
		out.close();
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		String value=request.getParameter("submition");
		String articleid=request.getParameter("articleid");
		String i=request.getParameter("i");
		int flag2=0;
		System.out.println("3333333333 value is " +value);
		if(i.equals("1")){
			LikesOp lop=new LikesOp();
			flag2=lop.Likesadd(articleid);
			if (flag2==1){
				request.setAttribute("flag2", flag2);
				String url="/showArticle.jsp?articleid="+articleid;
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
		}
		if(i.equals("2")){
			LikesOp lop=new LikesOp();
			flag2=lop.Likesreduce(articleid);
			if (flag2==1){
				request.setAttribute("flag2", null);
				String url="/showArticle.jsp?articleid="+articleid;
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
