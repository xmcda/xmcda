<%@page import="io.github.oliviercailloux.y2016.xmcda.beans.*"%>
<jsp:useBean id="loginBean" class="io.github.oliviercailloux.y2016.xmcda.beans.LoginBean" scope="session"/>
<jsp:setProperty name="loginBean" property="*"/>
 
<%
String result=LoginVerification.loginCheck(loginBean);
 
if(result.equals("true")){
    session.setAttribute("email",loginBean.getEmail());
    response.sendRedirect("home.jsp");
}
 
if(result.equals("false")){
    response.sendRedirect("index.jsp?status=false");
}
 
if(result.equals("error")){
    response.sendRedirect("index.jsp?status=error");
}
 
%>