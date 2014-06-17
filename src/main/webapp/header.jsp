<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messaging Service</title>                       
        <link rel="stylesheet" href="<c:url value="bootstrap/css/bootstrap.min.css"/>" type="text/css">
        <link rel="stylesheet" href="<c:url value="bootstrap/css/jumbotron.css"/>" type="text/css">
    </head>
    <!-- header / navbar -->
    <body>
        <!--<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="./List">Home</a>
	        </div>
                <div class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" role="form">
                        <div class="form-group">
                            <input type="text" placeholder="Username" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" class="form-control"/>
                        </div>
                        <button type="submit" class="btn btn-success">Sign in</button>
                        <a role="button" href="#">
                            <button class="btn btn-primary">Sign up</button>
                        </a>
                    </form>
                </div>
            </div>-->
            
            <!-- error messages -->
            
                <c:if test="${not empty error}">
                <div class="container">
                    <div class="alert alert-danger">
                        <c:out escapeXml="false" value="${error}"/>
                    </div>
                </div>
                </c:if>
            
	</div>
