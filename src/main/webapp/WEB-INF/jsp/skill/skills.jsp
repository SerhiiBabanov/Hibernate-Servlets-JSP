<%--
  Created by IntelliJ IDEA.
  User: Serhii Babanov
  Date: 20.10.2022
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:readPageGenerator entityName="skill">
    <jsp:body>
        <t:skillsTable></t:skillsTable>
    </jsp:body>
</t:readPageGenerator>
