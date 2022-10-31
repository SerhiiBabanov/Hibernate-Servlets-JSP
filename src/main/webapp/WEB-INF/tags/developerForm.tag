<%@tag description="Company Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="methodName" required="true" type="java.lang.String" %>
<div class="col-md-3 col-lg-3">
    <h4 class="lg-3">${methodName} developer</h4>
    <form class="needs-validation" action="${pageContext.request.contextPath}/developers"
          method="post">
        <div class="row g-3">

            <div class="col-3">
                <label class="form-label" for="id">id</label>
                <input class="form-control" disabled id="id" name="id" value="${developer.id}"
                       placeholder="${developer.id}" type="number">
            </div>

            <div class="col-3">
                <label class="form-label" for="name">Name </label>
                <input class="form-control" id="name" name="name" value="${developer.name}"
                       placeholder="${developer.name}" required type="text">
            </div>

            <div class="col-3">
                <label class="form-label" for="username">Username. Only [a-z] and symbol '_'. </label>
                <div class="input-group has-validation">

                    <input class="form-control" id="username" pattern="[a-z_]+" name="username"
                           value="${developer.username}" placeholder="${developer.username}"
                           required=""
                           type="text">
                    <div class="invalid-feedback">
                        Username is required.
                    </div>
                </div>
            </div>

            <div class="col-3">
                <label class="form-label" for="salary">Salary </label>
                <input class="form-control" id="salary" name="salary" value="${developer.salary}"
                       placeholder="${developer.salary}" type="number">
            </div>
            <hr class="my-4">


            <button class="w-100 btn btn-primary btn-lg" type="submit"
                    <c:if test="${methodName eq 'Update'}"> name="update_data" resource="${pageContext.request.contextPath}/developers" </c:if>>
                ${methodName} developer
            </button>
            <hr class="my-4">
            <a class="w-100 btn btn-primary btn-lg"
               href="${pageContext.request.contextPath}/developers">Go to developers page</a>
        </div>
    </form>
</div>

