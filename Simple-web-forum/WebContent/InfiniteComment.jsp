<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Infinite Comment</title>
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
<li><form  method="post" action="InfiniteCommentServlet">
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
	    <th>Edited?</th>
	</tr>
		<tr>
			<td colspan="1"><a href="InfiniteCommentServlet?comment=${pinnedComment.id}">View Replies</a></td>
					<td colspan="3">${pinnedComment.commentContent}</td>
			<td>${pinnedComment.author}</td>
			<td>${pinnedComment.dateCreated }</td>
			<td>${pinnedCommentLikes}</td>
			<td>${pinnedCommentDislikes}</td>
			<c:choose>
				<c:when test="${pinnedComment.commentEdited == true}">
					<td>YES</td>
				</c:when>
				
				<c:otherwise>
					<td>NO</td>
				</c:otherwise>
			</c:choose>
			
			
		</tr>
	
</table>


<table style="padding-top: 20px; margin-top: 20px;">
	<tr>
		<th colspan="4">Comment</th>
	    <th>Author</th>
	    <th>Date Commented</th>
	    <th>Likes</th>
	    <th>Dislikes</th>
	    
	</tr>
	
		<c:forEach items="${commentsToDisplayINF}" var="comment" varStatus="status">
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
<form style="padding-top: 20px;" name="msgForm"  method="post" action="InfiniteCommentServlet">
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