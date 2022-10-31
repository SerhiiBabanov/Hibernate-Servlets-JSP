<%--
  Created by IntelliJ IDEA.
  User: Serhii Babanov
  Date: 19.10.2022
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:readPageGenerator entityName="company">
    <jsp:body>
        <t:companiesTable></t:companiesTable>
    </jsp:body>
</t:readPageGenerator>
