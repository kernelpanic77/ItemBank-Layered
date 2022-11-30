<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
        	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
            <title>Create Item</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        ItemBankModule
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Items</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${user != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${user == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${user != null}">
                                    Update Item
                                </c:if>
                                <c:if test="${user == null}">
                                    Add New Item
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${user != null}">
                            <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Domain</label> <input type="text" value="<c:out value='${user.domain}' />" class="form-control" name="domain" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>itemType</label> <input type="text" value="<c:out value='${user.itemType}' />" class="form-control" name="itemType">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>itemText</label> <input type="text" value="<c:out value='${user.itemText}' />" class="form-control" name="itemText">
                        </fieldset>
                        
                        
                        <fieldset class="form-group">
                            <label>answerKey</label> <input type="text" value="<c:out value='${user.answerKey}' />" class="form-control" name="answerKey" required="required">
                        </fieldset>
                        
                        
                        <fieldset class="form-group">
                            <label>author</label> <input type="text" value="<c:out value='${user.author}' />" class="form-control" name="author" required="required">
                        </fieldset>
                        
                 
                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>