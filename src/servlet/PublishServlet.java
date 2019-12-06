package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ArticleOp;
import user.User;

public class PublishServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public PublishServlet() {
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
		HttpSession session=request.getSession();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		String value=request.getParameter("submition");
		System.out.println("2222value is"+value);
		if(value.equals("发表")){
			User user=(User)session.getAttribute("user");
			String title=request.getParameter("title");
			String atype=request.getParameter("atype");
			String intro=request.getParameter("intro");
			String content=request.getParameter("content");
			request.setAttribute("title", title);
			request.setAttribute("atype",atype);
			request.setAttribute("intro",intro);
			request.setAttribute("content", content);
			if(title.equals("")||atype.equals("")||intro.equals("")||content.equals("")){
				int flag=0;
				request.setAttribute("flag", flag);
				getServletContext().getRequestDispatcher("/publish.jsp").forward(request, response);
			}
			else {
				ArticleOp aop=new ArticleOp();
				int flag=aop.insertArticle(user,title,atype,content,intro);
//				System.out.println("333falg is"+flag);
				if (flag==1){
					request.setAttribute("flag", 2);
					getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
				}
				else{
					getServletContext().getRequestDispatcher("/publish.jsp").forward(request, response);
				}
			}
			
		}
		else if(value.equals("取消")){
			response.sendRedirect("index.jsp");
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
