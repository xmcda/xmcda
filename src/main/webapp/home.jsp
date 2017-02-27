<html>
    <head>
        <title>Login System</title>
    </head>
 
    <body>
        <%
        String email=(String)session.getAttribute("email");
        
        //redirect user to login page if not logged in
        if(email==null){
            response.sendRedirect("index.jsp");
        } // after that, we will redirect the user to the page of xmcda modular
        %>
        <p>Welcome <%=email%></p>    
        <a href="logout.jsp">Logout</a>
    </body>
</html>