<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Best Forum EVER</title>
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
  <c:if test="${user != null}">
<li><form  method="post" action="SpecificSubforumServlet">
	<input type="submit" value="follow" name="follow" id="submit">
	<input type="submit" value="Edit" name="edit" id="submit">
	<input type="submit" value="Delete" name="delete" id="submit">
</form></li>
</c:if>
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
 <c:if test="${CurrentForum != null}">
  <tr>
  	<td><img src="${CurrentForum.iconURL}"/></td>
    <td colspan="3">${CurrentForum.name}</td>
    <td colspan="2">${CurrentForum.description}</td>
    <td>${CurrentForum.moderatorInChief}</td>
    <td><c:forEach items="${CurrentForum.rules}" var="rule">${rule}<br></c:forEach></td>
    <td><c:forEach items="${CurrentForum.plebModerators}" var="plebMod">${plebMod}, </c:forEach></td>
  </tr>
 </c:if>
</table>


<table style="padding-top: 20px; margin-top: 20px;">
	<tr>
		<th colspan="4">Topic</th>
	    <th>Author</th>
	    <th>Date Created</th>
	    <th>Likes</th>
	    <th>Dislikes</th>
	</tr>
	
		<c:forEach items="${displayTopics}" var="topic" varStatus="status">
		<tr>
			<td colspan="1"><a href="TopicServlet?topic=${topic.id}">Visit</a></td>
			<c:choose>
				<c:when test="${topic.topicType eq 'TEXT'}">
					<td colspan="3">${topic.imageLink}</td>
				</c:when>
				<c:when test="${topic.topicType eq 'IMAGE'}">
				<td colspan="3"><img style="height: 250px; width: 450px;" alt="${topic.name}" src="${topic.imageLink}"></td>
				</c:when>
				<c:when test="${topic.topicType eq 'LINK'}">
					<td colspan="3"><a href="${topic.imageLink}">${topic.name}</a></td>
				</c:when>
			</c:choose>
			<td>${topic.author}</td>
			<td>${topic.dateCreated }</td>
			<td>${likes[status.index]}</td>
			<td>${dislikes[status.index]}</td>
			
		</tr>
		</c:forEach>
	
</table>
<c:if test = "${user != null}">
<form style="padding-top: 20px;" name="msgForm"  method="get" action="AddTopicServlet">
	<table>
		<tr>
			<td>Create new topic           <input type="submit" name="AddTopic" value="Add Topic"></td>
		</tr>
	</table>
</form>
</c:if>
</body>
</html>