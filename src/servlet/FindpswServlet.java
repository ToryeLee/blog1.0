package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ChkLogin;
import bean.DataOp;

public class FindpswServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public FindpswServlet() {
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
		String value=(String) request.getParameter("submition");
//		System.out.println(value);

		
		DataOp find=new DataOp();
		if(value.equals("Ã·Ωª")){
			String userid=request.getParameter("userid");
			if(userid.equals("")){
				request.setAttribute("flag3", 1);
				request.setAttribute("f", 0);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			int f=new ChkLogin().check(userid);
			if(f==2){ 
				request.setAttribute("flag3", 1);
				request.setAttribute("f", 1);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			else{
				String problem=find.getProblem(userid);
		
//			 System.out.println(problem);
			 
				request.setAttribute("problem", problem);
				request.setAttribute("flag3", 2);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		
		else if(value.equals("’“ªÿ")){
			String userid=request.getParameter("userid");
			String problem=request.getParameter("problem");
			String answer=request.getParameter("answer");
//			System.out.println("utype="+utype);
//			System.out.println("logname="+logname);
			System.out.println("problem is "+problem);
			int flag=find.chkAnswer(userid,answer);
			if(flag==1){
//				System.out.println("f=1 here is go");
				request.setAttribute("flag3", 3);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}
			else if(flag==2){
				
				request.setAttribute("problem", problem);
				request.setAttribute("flag3", 2);
				request.setAttribute("f", 2);
//				System.out.println("f=2 here is go");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
//			System.out.println("f!=1&&2 here is go");
			
		}
		else {System.out.println("error");}
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
