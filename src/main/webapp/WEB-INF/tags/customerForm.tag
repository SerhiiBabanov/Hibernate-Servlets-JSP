<%@tag description="Company Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="methodName" required="true" type="java.lang.String" %>
<div class="col-md-3 col-lg-3">
    <h4 class="lg-3">${methodName} customer</h4>
    <form class="needs-validation" action="${pageContext.request.contextPath}/customers" method="post" novalidate="">
        <div class="row g-3">

            <div class="col-3">
                <label class="form-label" for="id">id</label>
                <input class="form-control" disabled id="id" name="id" value="${customer.id}" placeholder="${customer.id}" type="number">
            </div>


            <div class="col-3">
                <label class="form-label" for="name">Name</label>
                <div class="input-group has-validation">

                    <input class="form-control" id="name" name="name" value="${customer.name}" placeholder="${customer.name}" required="" type="text">
                    <div class="invalid-feedback">
                        Your name is required.
                    </div>
                </div>
            </div>

            <div class="col-3">
                <label class="form-label" for="email">Email </label>
                <input class="form-control" id="email" name="email" value="${customer.email}" placeholder="${customer.email}"  type="email">
            </div>

            <hr class="my-4">


            <button class="w-100 btn btn-primary btn-lg" type="submit"
                    <c:if test="${methodName eq 'Update'}"> name="update_data" resource="${pageContext.request.contextPath}/customers" </c:if>>
                ${methodName} customer
            </button>
            <hr class="my-4">
            <a class="w-100 btn btn-primary btn-lg"
               href="${pageContext.request.contextPath}/customers">Go to customers page</a>
        </div>
    </form>
</div>

