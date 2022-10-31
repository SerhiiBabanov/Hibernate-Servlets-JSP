<%@tag description="Developer Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Username</th>
        <th scope="col">Salary</th>
        <th scope="col">Projects</th>
        <th scope="col" colspan="2">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${developers}" var="developer">
        <tr>
            <th scope="row">${developer.id}</th>
            <td>${developer.name}</td>
            <td>${developer.username}</td>
            <td>${developer.salary}</td>
            <td><a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/projects?developerId=${developer.id}">Get list of
                project</a>
            </td>
            <td><a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/developerEdit?id=${developer.id}">Edit</a>
            </td>
            <td>
                <button class="btn btn-primary"
                        onclick="makeDELETErequest('${pageContext.request.contextPath}/developers?id=${developer.id}')">
                    Delete
                </button>
            </td>
        </tr>
    </c:forEach>

    </tbody>

</table>
