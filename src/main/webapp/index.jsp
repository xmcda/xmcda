<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>XMCDA - Web Services</title>
  <script src="js/jquery-1.10.2.js"></script>
 <script TYPE="text/javascript" src="js/login.js"></script>

    
<%@ include file="/include/css.jsp"  %>    
<%@ include file="/include/js.jsp"  %>   
</head>
<body>

    <div id="wrapper">
      
      
		<!--  <%@ include file="/include/navbar.jsp"  %> -->
<c:if test="${sessionScope['user'] != null}">

<%@ include file="/include/navbarbis.jsp"  %>
</c:if>

<c:if test="${sessionScope['user'] == null}">

<%@ include file="/include/navbar.jsp"  %>
</c:if>


     
      
 <div id="page-wrapper">

            <div class="row">

                <div class="col-lg-12 text-center v-center">

                    
                    <p class="lead">Entrez l'URL de votre web service pour le parser.</p>

                    <br>
                    

                   <form class="col-lg-8 col-lg-offset-2" action="/xmcda/Parsing" method="Post">
                        <div class="input-group" style=" text-align: center; margin: 0 auto;">
                            <input class="form-control input-lg" title="Confidential signup."
                                placeholder="Entrez L'URL du web service" type="text" name="URL">
                            <span class="input-group-btn">
                                <button class="btn btn-lg btn-primary" type="submit" name="Pars" value="Parser">PARSER</button></span>
                        </div>
                    </form>
                </div>
            </div>
           <br>
		   <br>
		   <br>
			
				 <br>
						 <br>
				
           
            
            <!-- /.row -->

        </div>
        <!-- /#page-wrapper -->
    </div>    
</body>
<%@ include file="/include/js.jsp"  %> 
