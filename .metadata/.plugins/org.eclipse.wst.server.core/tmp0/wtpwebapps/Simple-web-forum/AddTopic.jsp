<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Topic</title>
<link rel="stylesheet" type="text/css" href="WEB-SRC/StyleSettings.css">
</head>
<body>
<h1>CREATE A NEW TOPIC</h1>

	<form style="padding-left: 260px" name="forumForm" method="post" action="AddTopicServlet">
		<table>
			<tr>
				<td>Topic Name:</td>
				<c:if test="${action eq 'Edit' }">
					<td><input type="text" readonly="readonly" value="${CurrentTopic.name}" name="topicName"></td>
				</c:if>
				<c:if test="${action eq 'Add Topic' }">
					<td><input type="text" name="topicName"></td>
				</c:if>
			</tr>
			<tr>
				<td>Topic Content:</td>
				<td><input height="30px" type="text" value="${CurrentTopic.content}" name="topicContent"></td>
			</tr>
			<tr>
				<td>Topic Type:</td>
				<td><select name="topicType" value="${CurrentTopic.topicType}">
						<option value="${topicTypeSelected}" selected='selected'>${CurrentTopic.topicType}</option>
						<option value="0">LINK</option>
						<option value="1">IMAGE</option>
						<option value="2">TEXT</option>
				</select></td>
			</tr>
			<tr>
				<td>Topic:</td>
				<td><input type="text" value="${CurrentTopic.imageLink}" placeholder="type in url if the topic is an image or a site" name="topicUrl"></td>
			</tr>
			<tr>
				<td colspan='2'><input type="submit" name="addTopic" value="${action }">
				<input type="submit" name="cancel" value="Cancel">
			</tr>
		</table>
</form>

</body>
</html>