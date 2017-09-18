<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Page</title>
<link rel="stylesheet" type="text/css" href="WEB-SRC/StyleSettingsMainPage.css">
</head>
<body>
	<ul>
	  <li><a href="MainPageStuff">Subforums</a></li>
	  <li><a href="UserPageServlet">User Page</a></li>
	  <li><a href="RegisterServlet">Register</a>
	  <c:if test="${user.accountType eq 'ADMIN' && user.accountType != null}">
	  <li><a href="AddForumServlet">Add Subforum</a></li>
	  </c:if>
	  <li><form class="form-wrapper" method="post">
	    <input type="text" id="search" name="search" placeholder="Search for..." required>
	    <input type="submit" value="go" name="submit" id="submit">
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
	<form style="padding-bottom: 10px" name="msgForm"  method="post" action="UserPageServlet">
			<table>
				<tr>
					<td>Send message to:<input type="text" name="UsernameForMsg"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea  cols="175" rows="4" type="text" name="MsgText"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" name="sendMsg" value="SendMsg">
				</tr>
			</table>
	</form>
<table >
		<tr>
			<th colspan="1">Message Sender</th>
	    	<th colspan="4">Message</th>
	    
	  </tr>
	  <c:forEach items="${dispMsg}" var="msg">
 <c:if test="${dispMsg != null}">
  <tr>
    <td colspan="1">${msg.sender}</td>
    <td colspan="4">${msg.messageContent}</td>
  </tr>
 </c:if>
</c:forEach>
	</table>
	
<table style="margin-top: 20px;">
	<tr>
	<th colspan="4">Followed Forum</th>
    <th colspan="2">Quick Desctription</th>
    <th>Creator</th>
    <th>Rules</th>
    <th>Moderators</th>
  </tr>
 <c:forEach items="${dispForum}" var="subforum">
 <c:if test="${dispForum != null}">
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

<table style="padding-top: 20px; margin-top: 20px;">
	<tr>
		<th colspan="4">Topic</th>
	    <th>Author</th>
	    <th>Date Created</th>
	    <th>Likes</th>
	    <th>Dislikes</th>
	</tr>
	
<c:forEach items="${dispTopic}" var="topic" varStatus="status">
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
			<c:set var="topiclikesCount" value="0" scope="page" />
			<c:set var="topicDisLikesCount" value="0" scope="page" />
			<c:forEach items="${topic.likesMap}" var="topicLike">
			<c:if test="${topicLike.value == true}">
				<c:set var="topiclikesCount" value="${topiclikesCount + 1}" scope="page"/>
			</c:if>
			<c:if test="${topicLike.value == false}">
				<c:set var="topicDisLikesCount" value="${topicDisLikesCount + 1}" scope="page"/>
			</c:if>
			</c:forEach>
			<td>${topiclikesCount}</td>
			<td>${topicDisLikesCount}</td>
			<c:set var="topiclikesCount" value="0" scope="page" />
			<c:set var="topicDisLikesCount" value="0" scope="page" />
			
		</tr>
		</c:forEach>
	
</table>

<table style="padding-top: 20px; margin-top: 20px;">
	<tr>
		<th colspan="4">Comment</th>
	    <th>Author</th>
	    <th>Date Commented</th>
	    <th>Likes</th>
	    <th>Dislikes</th>
	    
	</tr>
	
		<c:forEach items="${dispComment}" var="comment" varStatus="status">
		<tr>
			<td colspan="1"><a href="InfiniteCommentServlet?comment=${comment.id}">View Replies</a></td>
					<td colspan="3">${comment.commentContent}</td>
			<td>${comment.author}</td>
			<td>${comment.dateCreated }</td>
			<c:set var="likesCount" value="0" scope="page" />
			<c:set var="DisLikesCount" value="0" scope="page" />
			<c:forEach items="${comment.commentLikesMap}" var="commentLike">
			<c:if test="${commentLike.value == true}">
				<c:set var="likesCount" value="${likesCount + 1}" scope="page"/>
			</c:if>
			<c:if test="${commentLike.value == false}">
				<c:set var="DisLikesCount" value="${DisLikesCount + 1}" scope="page"/>
			</c:if>
			</c:forEach>
			<td>${likesCount}</td>
			<td>${DisLikesCount}</td>
			<c:set var="likesCount" value="0" scope="page" />
			<c:set var="DisLikesCount" value="0" scope="page" />
		</tr>
		</c:forEach>
	
</table>

</body>
</html>