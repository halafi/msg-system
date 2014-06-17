<%@include file='header.jsp'%> 
<div class="container">
    <div class="jumbotron">
        <h2>Messages - table view</h2>
        <div class="well">
        <table class="table">
            <thead>
                <tr>
                    <!--<th>Id</th>-->
                    <th>Author</th>
                    <th>Header</th>
                    <th>Body</th>
                    <th/>
                    <th/>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${messages}" var="message" varStatus="loopStatus">
                <tr>
                    <!--<td><c:out value="${message.id}"/></td>-->
                    <td><c:out value="${message.author}"/></td>
                    <td><c:out value="${message.header}"/></td>
                    <td><c:out value="${message.body}"/></td>
                    <td>
                        <form class="button" action="<c:url value="/FindMessage"/>" method="post">
                                <input type="hidden" name="id" value="<c:out value="${message.id}"/>"/>
                                <input type="Submit" value="Edit" name="submit" class="btn btn-default"/>
                        </form>
                    </td>
                    <td>
                        <form class="button" action="<c:url value="/RemoveMessage"/>" method="post">
                                <input type="hidden" name="id" value="<c:out value="${message.id}"/>"/>
                                <input type="Submit" value="Delete" name="submit" class="btn btn-default" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
        <br>
        <div class="well">
        <h2>Post Message</h2>
            <form role="form" action="<c:url value="/PostMessage"/>" method="post">
                <div class="form-group">
                    <label for="author">Author</label>
                    <input type="text" class="form-control" id="author" name="author" value="${messageForm.author}" placeholder="Enter your name">
                </div>
                <div class="form-group">
                    <label for="header">Title</label>
                    <input type="text" class="form-control" id="header" name="header" value="${messageForm.header}" placeholder="Enter message title">
                </div>
                <div class="form-group">
                    <label for="body">Body</label>
                    <input type="text" class="form-control" id="body" name="body" value="${messageForm.body}" placeholder="Enter message">
                </div>
                <button type="submit" class="btn btn-default">Publish</button>
            </form>
        </div>
        <c:if test="${show}">  
        <div class="well">
            <h2>Edit Message</h2>
            <form role="form" action="<c:url value="/EditMessage"/>" method="post">
                <input type="hidden" name="id" value="${preparedMessage.id}"/></td>
                <div class="form-group">
                    <label for="author">Author</label>
                    <input type="text" class="form-control" id="author" name="author" value="${preparedMessage.author}">
                </div>
                <div class="form-group">
                    <label for="header">Title</label>
                    <input type="text" class="form-control" id="header" name="header" value="${preparedMessage.header}">
                </div>
                <div class="form-group">
                    <label for="body">Body</label>
                    <input type="text" class="form-control" id="body" name="body" value="${preparedMessage.body}">
                </div>
                <button type="submit" class="btn btn-default">Update</button>
            </form>
        </div>
        </c:if>
    </div>
</div>
