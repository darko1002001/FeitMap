<?xml version="1.0" encoding="UTF-8"?>

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">

	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-latest.js">
    <!-- JS -->
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/css/formly/formly.js">
    <!-- JS -->
    </script>
  
  
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.js">
    <!-- JS -->
    </script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.14.custom.min.js">
    <!-- JS -->
    </script>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/formly/formly.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.14.custom.css"/>
      <style type="text/css" title="currentStyle"> 
      
			@import "${pageContext.request.contextPath}/css/style.css";
			@import "${pageContext.request.contextPath}/css/jquery-ui-1.8.14.custom.css"
		</style> 
    <form style="min-width: 620px;" id="headerForm">
    <table>
		<tr>
		<td>
	       	<span>FEIT Direction Service: </span><a href="${pageContext.request.contextPath}/public/homepage">Home Page</a> | 
		</td>
		<td>
	       	<a href="${pageContext.request.contextPath}/public/places/index">Places</a> | 
		</td>		
		<td>
            <a href="${pageContext.request.contextPath}/public/edges/index">Edges</a> | 
		</td>
		<td>
            <a href="${pageContext.request.contextPath}/public/uploadfile">Upload File</a> | 
		</td>
		</tr>
	</table>
    </form>
    
    <script type="text/javascript">
        $('#headerForm').formly();
    </script>
        
</jsp:root>
