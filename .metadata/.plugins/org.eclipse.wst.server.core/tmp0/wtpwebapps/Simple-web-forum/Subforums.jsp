<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIMPLE WEB FORUM</title>
<link rel="stylesheet" type="text/css" href="WEB-SRC/StyleSettingsMainPage.css">
</head>
<body>
<ul>
  <li><a href="MainPageStuff">Subforums</a></li>
  <c:if test ="${user != null}">
  <li><a href="UserPageServlet">User Page</a></li>
  </c:if>
  <li><a href="RegisterServlet">Register</a>
  <c:if test="${user.accountType eq 'ADMIN' && user.accountType != null}">
  <li><a href="AddForumServlet">Add Subforum</a></li>
  </c:if>
  <li><form class="form-wrapper" method="post" action="MainPageStuff">
    <input type="text" id="search" name="search" placeholder="Search for a subfourm  ..." required>
    <input type="submit" value="go" name="searchSubmit" id="submit">
</form></li>
<c:choose>
	<c:when test="${user.accountType == null}">
  		<li><a href="LoginServlet">Login</a></li>
  	</c:when>
  	<c:otherwise>
  		<li><a href="LoginServlet">${user.username} Logout</a></li>
  	</c:otherwise>
</c:choose>
</ul>

<table>
	<tr>
	<th colspan="4">Forum Name</th>
    <th colspan="2">Quick Desctription</th>
    <th>Creator</th>
    <th>Rules</th>
    <th>Moderators</th>
  </tr>
 <c:forEach items="${allSubforums}" var="subforum">
 <c:if test="${allSubforums != null}">
  <tr>
  	<td><img src="${subforum.iconURL}"/></td>
    <td colspan="3"><a href="SpecificSubforumServlet?subforum=${subforum.name}">${subforum.name}</a></td>
    <td colspan="2">${subforum.description}</td>
    <td>${subforum.moderatorInChief}</td>
    <td><c:forEach items="${subforum.rules}" var="rule">${rule}<br></c:forEach></td>
    <td><c:forEach items="${subforum.plebModerators}" var="plebMod">${plebMod}, </c:forEach></td>
  </tr>
 </c:if>
</c:forEach>
</table>


</body>
</html>