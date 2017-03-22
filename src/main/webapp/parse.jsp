<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.awt.List"%>
<%@page import="io.github.oliviercailloux.y2017.xmcda.XMscheme.InputStruct"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>XMCDA - Web Services</title>
    
<%@ include file="/include/css.jsp"  %>    
</head>
<body>

    <div id="wrapper">
        
		<%@ include file="/include/navbar.jsp"  %> 
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
			<div class="row">
                <div class="col-lg-12 v-center">
                   
					<form class="form-inline" action="/xmcda/invoke" method="Post" >
					
					  <div class="col-lg-6">
						  <h2>Les fichiers obligatoires  </h2>
						  
						  
						  
						  
						    <c:forEach var="name"  items="${requestScope['obligatoires']}" >
         						 <h1>  </h1>
         						 <label for="<c:out value="${name}"/>"><c:out value="${name}"/> : </label>
						  <div class="form-group">
							<input type="file" id="<c:out value="${name}"/>" class="btn btn-primary btn-xs" required>
						  </div>
						  <br>
						  <br>
							</c:forEach>
						  
						  
					  </div>
					  
						   <div class="col-lg-6">
						  <h2>Les fichiers optionnels</h2>
						
						  <c:forEach var="name"  items="${requestScope['facultatifs']}" >
         						 <h1>  </h1>
         						 <label for="<c:out value="${name}"/>"><c:out value="${name}"/> : </label>
						  <div class="form-group">
							<input type="file" id="<c:out value="${name}"/>" class="btn btn-primary btn-xs">
						  </div>
						  <br>
						  <br>
							</c:forEach>
						  
						  </div>
						 
						   <div class="col-lg-12 text-center">
							<br>
						 
						  <button class="btn btn-primary" type="submit">Invoquer</button>
						  </div>
					</form>
                </div>
				</div>
				 <br>
						 <br>
				
           
            
            <!-- /.row -->

        </div>
        <!-- /#page-wrapper -->
    </div>    
</body>
<%@ include file="/include/js.jsp"  %> 
