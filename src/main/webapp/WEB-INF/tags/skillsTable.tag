<%@tag description="Skill Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Skill language</th>
        <th scope="col">Skill level</th>
        <th scope="col" colspan="2">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${skills}" var="skill">
        <tr>
            <th scope="row">${skill.id}</th>
            <td>${skill.language}</td>
            <td>${skill.level}</td>
            <td><a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/skillEdit?id=${skill.id}">Edit</a>
            </td>
            <td>
                <button class="btn btn-primary"
                        onclick="makeDELETErequest('${pageContext.request.contextPath}/skills?id=${skill.id}')">
                    Delete
                </button>
            </td>
        </tr>
    </c:forEach>

    </tbody>

</table>
