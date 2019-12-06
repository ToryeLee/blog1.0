package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Chkregister;

public class registerServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public registerServlet() {
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
		if(value.equals("注册")){
			String userid=request.getParameter("userid");
			String userpsw1=request.getParameter("userpsw1");
			String userpsw2=request.getParameter("userpsw2");
			String logname=request.getParameter("logname");
			String gender=request.getParameter("gender");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			String education=request.getParameter("education");
			String problem=request.getParameter("problem");
			String answer=request.getParameter("answer");
			String selfintro=request.getParameter("selfintro");
			String[] info={userid,userpsw1,logname,gender,phone,email,education,problem,answer,selfintro};
			
				request.setAttribute("userid", info[0]);
				request.setAttribute("logname",info[2]);
				request.setAttribute("phone",info[4]);
				request.setAttribute("email",info[5]);
				request.setAttribute("education",info[6]);
				request.setAttribute("problem",info[7]);
				request.setAttribute("answer",info[8]);
				request.setAttribute("selfintro",info[9]);
				int flag=0;
				
				System.out.println(userid+userpsw1+logname+gender);
				if(userid.equals("")||userpsw1.equals("")||userpsw2.equals("")||logname.equals("")||gender==null){
					System.out.println(userid+userpsw1+logname+gender);
					flag=3;request.setAttribute("flag", flag);getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
				}
				else{
				if(userpsw1.equals(userpsw2)){
					Chkregister ckr=new Chkregister();
					flag=ckr.Register(info);
					request.setAttribute("flag", flag);
					if(flag==1){
							out.println("<script>alert('注册成功！')</script>");
							getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
					
						}
					else {
						getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
					}
				}
				else{
					request.setAttribute("flag", flag);
					getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
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
