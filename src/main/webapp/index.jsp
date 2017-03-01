<html>
    <head>
        <title>Login System</title>
    </head>
 
    <body>
        <%
        String email=(String)session.getAttribute("email");
        
        
        if(email!=null){
            response.sendRedirect("home.jsp");
        }
 
        String status=request.getParameter("status");
        
        if(status!=null){ // is status is null so it was the first time we enter 
            if(status.equals("false")){
                   out.print("Votre Login n est pa correct!");//                       
            }
            else{
                out.print("erreurs!");
            }
        }
        %>
    
        <form action="loginRequestHandler.jsp">
            <table cellpadding="5">
                <tr>
                    <td><b>Email:</b></td>
                    <td><input type="text" name="email" required/></td>
                </tr>
 
                <tr>
                    <td><b>Password:</b></td>
                    <td><input type="password" name="password" required/></td>
                </tr>
 
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Login"/></td>
                </tr>
 
            </table>
        </form>
    
    </body>
</html>