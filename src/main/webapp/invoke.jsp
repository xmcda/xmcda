<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>XMCDA - Web Services</title>

<%@ include file="/include/css.jsp"%>
</head>
<body>

	<div id="wrapper">

		<!--  <%@ include file="/include/navbar.jsp"  %> -->
		<c:if test="${sessionScope['user'] != null}">

			<%@ include file="/include/navbarbis.jsp"%>
		</c:if>

		<c:if test="${sessionScope['user'] == null}">

			<%@ include file="/include/navbar.jsp"%>
		</c:if>
		
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">Reponse</h3>
						</div>
						<div class="panel-body" style="padding-bottom: 5px;">
							<textarea name="reponse" id="reponse" spellcheck="false"
								style="display: none;"><?xml version="1.0" encoding="ISO-8859-1"?>
								       <c:out value="${requestScope['result']}" />
							</textarea>
						</div>
					</div>
				</div>
			</div>
			<!-- /.row -->
		</div>
					<form action="index.jsp">
					<div class="col-lg-12 text-center">
						<br>
						
						<button class="btn btn-primary" type="submit">Enregistrer</button>
						
					</div>
				</form>
		<!-- /#page-wrapper -->
	</div>
</body>
<%@ include file="/include/js.jsp"%>