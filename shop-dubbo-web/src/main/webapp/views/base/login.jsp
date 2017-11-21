<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib uri="/WEB-INF/tld/el.tld" prefix="el"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<title>用户登录</title>
<script type="text/javascript"
	src="${el:getWebRootPath()}/resource/js/sea.js"></script>
</head>
<body>8888
	<div style="background: #f1f1f1;">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-md-8">
					<span class="glyphicon glyphicon-earphone"></span>
					<span class="glyphicon glyphicon-envelope"></span>
				</div>
				<div class="col-xs-6 col-md-4"></div>
			</div>
		</div>
	</div>
</body>
<script>
	seajs.use("views/base/js/login.js", function(A) {
		//A.trans();
	});
</script>
</html>