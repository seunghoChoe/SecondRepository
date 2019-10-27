<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap&subset=korean" rel="stylesheet">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<table width="100%">
	<tr>
		<td style="width:*;"> </td>
		<td style="width:1000px">
			<div style="height:200px">
				<tiles:insertAttribute name="header"/>
			</div>
			<div style=";height:100px">
				<tiles:insertAttribute name="menu" />
			</div>
			<table width="100%" >
				<tr>
					<td style="vertical-align:top;width:200px;">
						<tiles:insertAttribute name="sidebar" />
					</td>
					<td style=width:785px;>
						<tiles:insertAttribute name="body" />
					</td>
				</tr>
			</table>
		</td>
		<td style="width:*"></td>
	</tr>
	<tr>
		<td colspan = "3">
			<tiles:insertAttribute name="footer" />
		</td>
	</tr>
</table>
</body>

</html>