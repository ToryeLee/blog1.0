package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ArticleOp;
import bean.DataOp;
import user.Article;
import user.User;

public class UpdateServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public UpdateServlet() {
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
		System.out.println("value is "+value);
		HttpSession session=request.getSession(); 

		if(value.equals("重置")){
			String userid=request.getParameter("userid");
			String newpsw1=request.getParameter("newpsw1");
			String newpsw2=request.getParameter("newpsw2");
			int f=0;
			if(newpsw1.equals(newpsw2)){
				DataOp update=new DataOp();
				f=update.resetPsw(userid,newpsw1);
				if(f==1){
					request.setAttribute("f", 4);
					request.setAttribute("flag3", null);
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);}
				else{
					System.out.println("reset faild");
					}
				}
			else{
				request.setAttribute("f", 3);
				request.setAttribute("flag3", 3);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			}
		if(value.equals("提交修改")){
			User user=new User();
			user=(User)session.getAttribute("user");
			DataOp update=new DataOp();
			String logname=request.getParameter("logname");
			String userid=request.getParameter("userid");
			String email=request.getParameter("email");
			String gender=request.getParameter("gender");
			String phone=request.getParameter("phone");
			String problem=request.getParameter("problem");
			String answer=request.getParameter("answer");
			String education=request.getParameter("education");
			String selfintro=request.getParameter("selfintro");
			User user2=new User();
			user2.setUserid(userid);
			user2.setLogname(logname);
			user2.setEmail(email);
			user2.setUserpsw(user.getUserpsw());
			if(gender==null)
				user2.setGender(user.getGender());
			else
				user2.setGender(gender);
			user2.setPhone(phone);
			user2.setProblem(problem);
			if(answer==null)
				user2.setAnswer(user.getAnswer());
			else user2.setAnswer(answer);
			user2.setEducation(education);
			user2.setSelfintro(selfintro);
			int flag=update.updateData(user,user2);
			if(flag==1){
				session.setAttribute("user", user2);
				request.setAttribute("flag", 1);
				getServletContext().getRequestDispatcher("/aboutMe.jsp").forward(request, response);
			}
		}
		else if(value.equals("修改")){

			String title=request.getParameter("title");
			String atype=request.getParameter("atype");
			String intro=request.getParameter("intro");
			String content=request.getParameter("content");
			if(title.equals("")||atype.equals("")||intro.equals("")||content.equals("")){
				int flag=0;
				
				request.setAttribute("flag", flag);
				getServletContext().getRequestDispatcher("/updateArticle.jsp").forward(request, response);
			}
			else{			
				Article article1=new Article();
				Article article2=new Article();
				article1=(Article)session.getAttribute("article");
				article2.setUserid(article1.getUserid());
				article2.setArticleid(article1.getArticleid());
				article2.setTitle(title);
				article2.setAtype(atype);
				article2.setIntro(intro);
				article2.setContent(content);
				article2.setLikes(article1.getLikes());
				int flag=new ArticleOp().updateArticle(article2);
				if(flag==1){
					String url="/showArticle.jsp?articleid="+article1.getArticleid();
					request.setAttribute("flag",4);
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}
			}
		}
		if(value.equals("删除")){
			Article article=new Article();
			article=(Article)session.getAttribute("article");
			int flag=new ArticleOp().deleteArticle(article.getArticleid());
			if(flag==1){
				String url="/index.jsp";
				request.setAttribute("flag",4);
				getServletContext().getRequestDispatcher(url).forward(request, response);
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
