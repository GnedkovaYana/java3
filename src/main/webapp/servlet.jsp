<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getParameter("path");
    if (path == null) {
        path = "C:\\";
    }
    File currentDirectory = new File(path);
    String parentDirectoryPath = currentDirectory.getParent();

    if (parentDirectoryPath == null) {
        parentDirectoryPath = "C:\\";
    }
%>
<html>
<head>
    <title>Files</title>
    <link rel="stylesheet" href="files.css">
</head>
<body>

    <jsp:useBean id="now" class="java.util.Date"/>
    <fmt:formatDate value="${now}" pattern="dd.MM.yyyy HH:mm:ss" var="formattedDate"/>
    <p>${formattedDate}</p>
    <h1><%=path%></h1>
    <p>
        <img src="icons8-папка-симлинк-100.png" alt="">
        <a href=<%="?path=" + parentDirectoryPath%>>Вверх</a>
    </p>
    <table>
        <tr>
            <td><b>Файл</b></td>
            <td><b>Размер</b></td>
            <td><b>Дата</b></td>
        </tr>
        <%
            File[] directories = (File[]) request.getAttribute("directories");
            for (File directory : directories) {
        %>
        <tr>
            <th>
                <img src="icons8-папка-48.png" alt="">
                <a href="<%="files?path=" + directory.getAbsolutePath()%>"><%=directory.getName() + "/"%></a>
            </th>
            <th></th>
            <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy, HH:mm:ss"); %>
            <th><%=df.format(new Date(directory.lastModified()))%></th>
        </tr>
        <%}%>
        <%
            File[] files = (File[]) request.getAttribute("files");
            for (File file : files) {
        %>
        <tr>
            <th>
                <img src="icons8-файл-128.png" alt="">
                <a href=<%="download?path=" + file.getAbsolutePath()%>><%=file.getName()%></a>
            </th>
            <th><%=file.length() + " B"%></th>
            <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy, HH:mm:ss"); %>
            <th><%=df.format(new Date(file.lastModified()))%></th>
        </tr>
        <%}%>
    </table>
</body>
</html>