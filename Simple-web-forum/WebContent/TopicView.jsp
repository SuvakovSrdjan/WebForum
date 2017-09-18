<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Best Topic EVER</title>
<link rel="stylesheet" type="text/css" href="WEB-SRC/StyleSettingsMainPage.css">
</head>
<body>
<ul>

  <li><a href="MainPageStuff">Subforums</a></li>
  <c:if test ="${user != null}">
  <li><a href="UserPageServlet">User Page</a></li>
  </c:if>
  <li><a href="RegisterServlet">Register</a>
  <c:if test="${user != null}">
<li><form  method="post" action="TopicServlet">
	<input type="submit" value="Like" name="Like" id="submit">
	<input type="submit" value="Dislike" name="disLike" id="submit">
	<input type="submit" value="Follow" name="follow" id="submit">
	<input type="submit" value="EDIT" name="edit" id="submit">
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

<table style="padding-top: 20px;">
	<tr>
		<th colspan="4">Topic</th>
	    <th>Author</th>
	    <th>Date Created</th>
	    <th>Likes</th>
	    <th>Dislikes</th>
	</tr>
		<tr>
			<c:choose>
				<c:when test="${topic.topicType eq 'TEXT'}">
					<td colspan="4">${topic.imageLink}</td>
				</c:when>
				<c:when test="${topic.topicType eq 'IMAGE'}">
				<td colspan="4"><img style="height: 410px; width: 628px;" alt="${topic.name}" src="${topic.imageLink}"></td>
				</c:when>
				<c:when test="${topic.topicType eq 'LINK'}">
					<td colspan="3"><a href="${topic.imageLink}">${topic.name}</a></td>
				</c:when>
			</c:choose>
			<td>${topic.author}</td>
			<td>${topic.dateCreated }</td>
			<td>${topicLikes}</td>
			<td>${topicDislikes}</td>
			
</tr>
	<tr><td colspan ="7">${topic.content}</tr>
</table>


<table style="padding-top: 20px; margin-top: 20px;">
	<tr>
		<th colspan="4">Comment</th>
	    <th>Author</th>
	    <th>Date Commented</th>
	    <th>Likes</th>
	    <th>Dislikes</th>
	</tr>
	
		<c:forEach items="${commentsToDisplay}" var="comment" varStatus="i">
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
<c:if test = "${user != null}">
<form style="padding-top: 20px;" name="msgForm"  method="post" action="TopicServlet">
	<table>
		<tr>
			<td colspan = "2"><textarea style="resize: none;" rows="3" cols="117" name="commentText"></textarea></td>
			<td>Leave a reply:           <input type="submit" name="addComment" value="Add Comment"></td>
		</tr>
	</table>
</form>
</c:if>
</body>
</html>