package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Show;
import pageutil.Page;
import pageutil.PageUtil;
import user.Article;

public class SearchServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public SearchServlet() {
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
		String n=request.getParameter("n");
		if(value.equals("ËÑË÷")){
			String search=request.getParameter("search");
			Show show=new Show();
			
			ArrayList<Article> Alist=new ArrayList<Article>();
			Alist=show.searchArticle(search);
			if(Alist.size()==0){
				request.setAttribute("flag", 0);
				String url="index.jsp";
				if(n.equals("0"))url="/index.jsp";
				if(n.equals("1"))url="/databasetype.jsp";
				if(n.equals("2"))url="/javatype.jsp";
				if(n.equals("3"))url="/diary.jsp";
				if(n.equals("4"))url="/webtype.jsp";
				if(n.equals("5"))url="/searchResult.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			else {
				String size=""+Alist.size();
				System.out.println("55555 size is "+size);

				request.setAttribute("size", size);
				request.setAttribute("Alist", Alist);
				getServletContext().getRequestDispatcher("/searchResult.jsp").forward(request, response);
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
