<%@page import="fr.eni.easyauction.bo.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>

               <%
                    ArticleVendu article = (ArticleVendu) request.getAttribute("articleCourant");
               %>   
               
         	<div class="container">
		      <div class="row g-3">
		        <div class="col-12">
		          <div class="card" >
			          <div class="col-4">
	      		         <img src="img/htmlcss2reduce.jpg" class="card-img-top" alt="...">
			          </div>

		            <div class="col-8 card-body">
		              <h5 class="card-title"><%=article.getNomArticle()%></h5>
		              <p class="card-text"><%=article.getDescription()%>.</p>
		              <a href="" class="btn btn-primary">Go somewhere</a>
		            </div>
		          </div>
		        </div>
			  </div>
			</div>
			 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>