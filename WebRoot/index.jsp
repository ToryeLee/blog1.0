<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page import="pageutil.*"%>
<%@ page import="user.*"%>
<%@ page import="bean.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style.css">

  </head>
  
  <body>
  <c:if test="${requestScope.flag==2 }"><script>alert("发表成功！")</script></c:if>
  <c:if test="${requestScope.flag==0 }"><script>alert("抱歉，暂时没有找到有关该文章的信息！")</script></c:if>
    <c:if test="${requestScope.flag==4 }"><script>alert("删除成功！")</script></c:if>
  <header>
  <nav id="nav">

    <ul>
    
      <li><a href="index.jsp">首页</a></li>
      	<li><a href="webtype.jsp">web类</a></li>
      	<li><a href="javatype.jsp">java类</a></li>
      	<li><a href="databasetype.jsp">数据库类</a></li>
      	<li><a href="diary.jsp">日记类</a></li>
      	<c:if test="${sessionScope.user.userid=='1375011167'}">
      		<li><a href="publish.jsp">发表博客</a></li>
      	</c:if>
      	<li><a href="aboutMe.jsp">关于我</a></li>
      	<li><a href="message.jsp">留言版</a></li>
      	<c:if test="${sessionScope.user==null}">
      		<li><a href="login.jsp">登录</a></li>
      		<li><a href="register.jsp">注册</a></li>
      	</c:if>
      	<c:if test="${sessionScope.user!=null}">
      		<li>
      			<form action="LoginServlet" method="post">
      				<input type="submit" name="submition" value="退出登录">
      			</form>
      		</li>
      	</c:if>
    </ul>
  </nav>   
  <script>
window.onload = function ()
{
var obj=null;
var As=document.getElementById('nav').getElementsByTagName('a');
obj = As[0];
for(i=1;i<As.length;i++){if(window.location.href.indexOf(As[i].href)>=0)
obj=As[i];}
obj.id='selected'
}
</script> 
</header>
	<article>
  	<div class="l_box">
    	<div class="about_me">
      	<h2>关于我</h2>
      	<ul>
        	<i><img src="imge/head.jpg" width=100px height=100px></i>
        	<p><b>李朝伟</b>个人简介</p>
      	</ul>
    	</div>
    	<div class="wdxc">
      	<h2>我的相册</h2>
      	<ul>
        	<li><img src="imge/1.jpg"></li>
        	<li><img src="imge/2.jpg"></li>
        	<li><img src="imge/3.jpg"></li>
        	<li><img src="imge/4.jpg"></li>
        	<li><img src="imge/5.jpg"></li>
        	<li><img src="imge/6.jpg"></li>
      	</ul>
    	</div>
    	<div class="search">

      	<form action="SearchServlet?n=0" method="post" >
        	<input type="text" name="search" style="color: rgb(153, 153, 153);" class="input_text" value="请输入关键字词 " onfocus="if(value=='请输入关键字词'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字词'}" />
        	<input type="submit" name="submition" class="input_submit" value="搜索">
      	</form>
    	</div>
    	<%Show show=new Show(); %>
    	
    	<div class="fenlei">
    	
      	<h2>文章分类</h2>
      	<ul>
        	<li><a href="webtype.jsp">web(<%=show.getTCount("web类") %>)</a></li>
        	<li><a href="javatype.jsp">java(<%=show.getTCount("java类") %>)</a></li>
        	<li><a href="databasetype.jsp">数据库(<%=show.getTCount("数据库类") %>)</a></li>
        	<li><a href="diary.jsp">日记(<%=show.getTCount("日记类") %>)</a></li>
        	<li><a href="message.jsp">留言(<%=show.getTCount("留言") %>)</a></li>
      	</ul>
    	</div>
    	<div class="links">
      	<h2>友情链接</h2>
      	<ul>
        	<a href="index.jsp">我的个人博客</a> <a href="index.jsp">我的微博</a>
      	</ul>
    	</div>
    	<div class="guanzhu">
      	<h2>关注我 么么哒</h2>
      	<ul>
        	<img src="imge/wx.jpg" width="200px" height="200px">
    	  </ul>
  	  </div>
  	</div>
  	<div class="r_box">
  	<% 
    	
    	
    	int pageCount = 5	;//显示每页记录数
    	
       	int totalCount=show.getCount();//页数获取总
       	
       	
       	int currentPage=1;//当前页
       	
       	String p=null;//
       	
       	if((p=request.getParameter("ppage"))!=null){
       	 	currentPage = Integer.parseInt(p);
       	}
       	
       	Page ppage = PageUtil.createPage(pageCount, totalCount, currentPage);
       	
       	ArrayList<Article> Alist=show.getArticle(currentPage,pageCount);
       	
       	request.setAttribute("ppage",ppage);
       	session.setAttribute("Alist",Alist);
       	
         %>
         
       <table width="500" border="0">  
			<c:forEach items="${sessionScope.Alist}" var="article">
				<li>
				<h3><a href="showArticle.jsp?articleid=${article.articleid }">${article.title }</a></h3>
				<p>${article.intro }</p>
				<p>点赞量（${article.likes}） 阅读量 （${article.readcount}） ${article.times }</p>
				</li>
			</c:forEach>
  	   </table>
   
  
       <br>
		<c:if test="${requestScope.ppage.totalPage!=0 }">
			<div class="pagelist">
				<c:if test="${requestScope.ppage.hasPre }">
					<a href="index.jsp?ppage=${requestScope.ppage.currentPage-1 }">上一页</a>
				</c:if>
				<c:forEach begin="1" end="${requestScope.ppage.totalPage }"  var="p">
					<a href="index.jsp?ppage=${p }">${p }</a> 
				</c:forEach>
				<c:if test="${requestScope.ppage.hasNext }">
					<a href="index.jsp?ppage=${requestScope.ppage.currentPage+1 }">下一页</a>
				</c:if>
			</div>
       </c:if>
  	</div>
  </article>
  </body>
</html>
