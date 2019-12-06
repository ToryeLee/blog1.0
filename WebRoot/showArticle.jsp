<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="user.*"%>
<%@ page import="bean.*"%>
<%@ page import="pageutil.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showArticle.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="style.css">


  </head>
  
  <body>

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
<c:if test="${flag==0 }"><script>alert("评论失败")</script></c:if>
<c:if test="${flag==1 }"><script>alert("评论成功！")</script></c:if>
<c:if test="${flag==2 }"><script>alert("请先登录，否则无法留言")</script></c:if>
<c:if test="${flag==3 }"><script>alert("评论内容不能为空！")</script></c:if>
<c:if test="${flag==4 }"><script>alert("修改成功！")</script></c:if>

<div class="article">
	<% 
		String articleid=request.getParameter("articleid");
		Article article=new Article();
		article=new ArticleOp().getArticle(articleid);
		session.setAttribute("article",article);
		 %>
	<div class="title" align="center"><h3><%=article.getTitle() %></h3></div>
	<div class="atype" align="center"><p>类别：<%=article.getAtype() %></a></p></div>
	<div class="intro" align="center"><h4>简介：<%=article.getIntro() %></h4></div>
	<div class="content2"><xmp> <%=article.getContent() %> </xmp></div>
	<br/>
	<c:if test="${sessionScope.user.userid=='1375011167' }">
		<a href="updateArticle.jsp">修改</a>
	</c:if>
	<div class="author">--天痕--<%=article.getTimes() %></div>
</div>
<br/>

  <%
  	bean.Counter counter=new bean.Counter();//创建对象
  	int count=0;
  	count=counter.readCount(articleid);
  	%>

<div class="like">
<c:if test="${sessionScope.user.userid!='1375011167' }">
<%
  	 ++count;
  	counter.writeCount(articleid,count);//更新文件记录
  	%>
	你好！第<%=count %>个访客！
	<c:if test="${requestScope.flag2==null }">
		<a href="LikeServlet?articleid=<%=articleid %>&&i=1"><img src="imge/likead.png"  width=30px height=30px/></a>
	</c:if>
	<c:if test="${requestScope.flag2==1 }">
	<script>alert("点赞成功！谢谢你的赞！")</script>
		<a href="LikeServlet?articleid=<%=articleid %>&&i=2"><img src="imge/likere.png" width=30px height=30px /></a>
	</c:if>
</c:if>

<c:if test="${sessionScope.user.userid=='1375011167' }">阅读量<%=count %><br/>点赞数量:</c:if>
<%=article.getLikes()  %><br/>

</div>

<div class="message">
	<form action="commentServlet?articleid=<%=articleid %>" method="post">
	<textarea name="msg"  rows="8" cols="100" onKeyUp="if(this.value.length>400)this.value=this.value.substr(0,400)" ></textarea><br/>
	<input type="submit" name="submition" value="评论">
	</form>
</div>


<div class="comment">
<%
	ArrayList<Comment> Clist=new CommentOp().getComment(articleid);
	request.setAttribute("Clist",Clist); %>
	
       <table width="500" border="0">  
			<c:forEach items="${requestScope.Clist}" var="comment">
			<div class="comment">
				<li>
				<p>${comment.logname }</p>
				<h6>${comment.msg }</h6>
				<p>${comment.times}</p>
				</li>
				</div>
			</c:forEach>
  	   </table>

</div>
  </body>
</html>
