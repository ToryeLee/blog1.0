package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ChkLogin;
import user.User;

public class LoginServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public LoginServlet() {
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
		HttpSession session=request.getSession();
		String value=request.getParameter("submition");
		System.out.println("111111111111111111 value is "+value);
		int flag=0;
		if(value.equals("µÇÂ¼")){
		  String userid = request.getParameter("userid");
		  String userpsw = request.getParameter("userpsw");
		  session.setAttribute("userid", userid);
		  if(!userid.equals("")&&!userpsw.equals("")){
		 	ChkLogin ckl = new ChkLogin();
		 	
		 	flag =ckl.Login(userid,userpsw);
		 	request.setAttribute("flag", flag);

			if( flag==1 ){
				User user=new User();
				user = ckl.getUser(userid);
				System.out.print("userid is "+user.getUserid());
				session.setAttribute("user", user);
//				System.out.println(user.getUtype()+""+user.getUtype().length());
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		 	 }
		 	else{
		 		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		 	}
			}
		  else{		 	
			  request.setAttribute("flag", flag);
			  getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		  }
			}
		else if(value.equals("×¢²á")){
			response.sendRedirect("register.jsp");
		}
		else if(value.equals("ÍË³öµÇÂ¼")){
			session.setAttribute("user", null);
			response.sendRedirect("index.jsp");
		}
		else if(value.equals("ÕÒ»ØÃÜÂë")){
			int flag3=1;
			request.setAttribute("flag3", flag3);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
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
