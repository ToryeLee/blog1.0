package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CommentOp;
import bean.MessageOp;
import user.User;

public class LeaveMsgServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public LeaveMsgServlet() {
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
		int flag=0;
		if(value.equals("¡Ù—‘")){
			User user=(User)session.getAttribute("user");
			String url="/message.jsp";
			
			if(user!=null){
			
			String msg=request.getParameter("msg");
			
			if(!msg.equals("")){
					System.out.println("msg is"+msg);
					
					flag=new MessageOp().insertMsg(msg, user.getLogname());
					request.setAttribute("flag", flag);
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}
			else{
				flag=3;
				request.setAttribute("flag", flag);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			}
			else{
				flag=2;
				request.setAttribute("flag", flag);
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
