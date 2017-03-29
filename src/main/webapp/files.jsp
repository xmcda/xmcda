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
                <div class="col-lg-12 v-center">
                   
					<form class="form-inline">
					
					  <div class="col-lg-6">
						  <h2>Les fichiers obligatoires</h2>
						  <label for="obj1">Criteria : </label>
						  <div class="form-group">
							<input type="file" id="obj1" class="btn btn-primary btn-xs">
						  </div>
						  <br>
						  <br>
						  
						  <label for="obj2">Alternatives : </label>
						  <div class="form-group">
							<input type="file" id="obj2" class="btn btn-primary btn-xs">
						  </div>
					  </div>
					  
						   <div class="col-lg-6">
						  <h2>Les fichiers optionnels</h2>
						  <label for="obj1">PerformanceTable : </label>
						  <div class="form-group">
							<input type="file" id="obj1" class="btn btn-primary btn-xs">
						  </div>
						  
						  </div>
						 
						   <div class="col-lg-12 text-center">
							<br>
						 
						  <button class="btn btn-primary" type="button">Invoquer</button>
						  </div>
					</form>
                </div>
				</div>
				
           
            
            <!-- /.row -->

        </div>
        <!-- /#page-wrapper -->
    </div>    
</body>
<%@ include file="/include/js.jsp"  %> 
</html>