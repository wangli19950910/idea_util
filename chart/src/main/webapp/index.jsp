<%@ page import="org.jfree.chart.servlet.ServletUtilities" %>
<%@ page import="com.chart.ChartUtil" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/6/25
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String fileName = ServletUtilities.saveChartAsJPEG(ChartUtil.createChart(),450,300,session);
    String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
%>
<img src="<%=graphURL %>" border="1"/>
</body>
</html>
