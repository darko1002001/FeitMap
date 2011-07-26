<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feit MAP places Visual</title>
<!-- CSS Files -->
<link type="text/css" href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" />
<link type="text/css" href="${pageContext.request.contextPath}/css/RGraph.css" rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jit.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/graphsettings.js">
</script>
</head>
<body>

<div id="container">



<div id="center-container">
    <div id="infovis"></div>    
</div>

<div id="right-container">

<div id="inner-details"></div>

</div>
<div id="log">

</div>
</div>
</body>
 <script type="text/javascript">
  $(document).ready(function(){
	 var json = jQuery.parseJSON('${json}');
	  init(json);
  });
  </script>
</html>