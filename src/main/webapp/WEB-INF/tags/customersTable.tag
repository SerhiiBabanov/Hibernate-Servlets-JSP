<%@tag description="Customer Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">email</th>
        <th scope="col" colspan="2">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <th scope="row">${customer.id}</th>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td><a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/customerEdit?id=${customer.id}">Edit</a>
            </td>
            <td>
                <button class="btn btn-primary"
                        onclick="makeDELETErequest('${pageContext.request.contextPath}/customers?id=${customer.id}')">
                    Delete
                </button>
            </td>
        </tr>
    </c:forEach>

    </tbody>

</table>
