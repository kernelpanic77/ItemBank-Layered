<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
        	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
            <title>ItemBankModule</title>
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
                    
                    
                     <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/login" class="nav-link">Logout</a></li>
                    </ul>
                    
                    <ul class="navbar-nav">
                        <li>You have logged in as an Admin.</li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Items</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New Item</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Domain</th>
                                <th>itemType</th>
                                <th>itemText</th>
                                <th>answerKey</th>
                                <th>author</th>
                                <th>toBeUpdated</th>
                                <th>toBeDeleted</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="user" items="${listUser}">

                                <tr>
                                    <td>
                                        <c:out value="${user.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.domain}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.itemType}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.itemText}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.answerKey}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.author}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.toBeUpdated}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.toBeDeleted}" />
                                    </td>
                                    <td><a href="toUpdate?id=<c:out value='${user.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="toDelete?id=<c:out value='${user.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>