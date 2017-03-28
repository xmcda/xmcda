
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="">XMCDA</a>
            </div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    
                    <li class="selected"><a href="index.jsp"><i class="fa fa-cogs"></i> Invoquer web service</a></li>
					 
                    <li><a href="./createobject.jsp"><i class="fa fa-file-code-o"></i> Créer des objets</a></li>          
                </ul>
                <ul class="nav navbar-nav navbar-right navbar-user">
                     <li ><a href="./login.jsp" id="modifetat"><i class="fa fa-sign-in"></i> Connexion</a></li>
                    <!--  <li ><a id="modifetat" onclick="modification(this)"class="fa fa-sign-in">Connexion</a></li>-->
                    <button id="like-'+this.id+'" class=" fa fa-thumbs-o-up" title="like" onclick="modifier_choix_like(\''+this.id+'\')"></button>
                 
                    <li><a href="./signup.jsp"><i class="fa fa-user	"></i> Inscription</a></li> 
                </ul>
            </div>
</nav>