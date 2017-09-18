<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Subforum</title>
<link rel="stylesheet" type="text/css" href="WEB-SRC/StyleSettings.css">
</head>
<body>
<h1>CREATE A NEW SUBFORUM</h1>
		<c:set var="allMods" value="" scope="page"/>
		<c:set var="allRules" value="" scope="page"/>
		<c:forEach items="${CurrentForum.plebModerators }" var="moderator"> 
		<c:if test="${allMods ne ''}">
			<c:set var="allMods" value="${allMods};" scope="page"/>
		</c:if>
				<c:set var="allMods" value="${allMods}${moderator}" scope="page"/>
		</c:forEach>
		<c:forEach items="${CurrentForum.rules }" var="rule"> 
		<c:if test="${allRules ne ''}">
			<c:set var="allRules" value="${allRules};" scope="page"/>
		</c:if>
				<c:set var="allRules" value="${allRules}${rule}" scope="page"/>
		</c:forEach>

	<form style="padding-left: 260px" name="forumForm" method="post" action="AddForumServlet">
		<table>
			<tr>
				<td>Forum Name:</td>
				<c:choose>
					<c:when test="${action eq 'Add Subforum'}">
						<td><input type="text" name="forumName"></td>
				   </c:when>
				   <c:when test="${action eq 'Edit'}">
						<td><input type="text" readonly="readonly" value="${CurrentForum.name}" name="forumName"></td>
				   </c:when>
				</c:choose>
			</tr>
			<tr>
				<td>Forum Description:</td>
				<td><input height="30px" value="${CurrentForum.description }" type="text" name="forumDescription"></td>
			</tr>
			<tr>
				<td>IconUrl:</td>
				<td><input type="text" value="${CurrentForum.iconURL }" name="icoUrl"></td>
			</tr>
			<tr>
				<td>Rules:</td>
				<td><input type="text" value="${allRules}" placeholder="separate rules with ;" name="ruleList"></td>
			</tr>
			<tr>
				<td>Additional Moderators:</td>
				<td><input type="text" placeholder="Separate mods with ; " value="${allMods }" name="plebMods"></td>
			</tr>
			<tr>
				<td colspan='2'><input type="submit" name="AddSubforum" value="${action}">
				<input type="submit" name="Cancel" value="Cancel">
			</tr>
		</table>
</form>

</body>
</html>