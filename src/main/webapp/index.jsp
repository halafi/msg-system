<%@include file='header.jsp'%> 
<div class="container">
    <div class="jumbotron">
        <c:if test="${not empty error}">
            <p class="error">
                <c:out escapeXml="false" value="${error}"/>
            </p>
        </c:if>
        <h2>Messages</h2>
        <table class="table">
            <tr><th>Id</th><th>Author</th><th>Header</th><th>Body</th><th/></tr>
        <c:forEach items="${messages}" var="message" varStatus="loopStatus">
            <tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
                <td><c:out value="${message.id}"/></td>
                <td><c:out value="${message.author}"/></td>
                <td><c:out value="${message.header}"/></td>
                <td><c:out value="${message.body}"/></td>
                <td>
                    <form class="button" action="<c:url value="/RemoveMessage"/>" method="post">
                            <input type="hidden" name="id" value="<c:out value="${message.id}"/>"/>
                            <input type="Submit" value="Remove" name="submit"/>
                    </form>
                </tr>
        </c:forEach>
        </table>
        <br>
        <h2>Post Message</h2>
    </div>
</div>
