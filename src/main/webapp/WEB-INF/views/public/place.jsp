<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js">
</script>
  
    <title>Places Manager</title>

</head>
<body>
 
<h2>Places Manager</h2>
 
<form:form id="placesForm" method="post" action="add" commandName="place">
  <fieldset>
    <table>
    <tr>
        <td><form:label path="name"><spring:message code="label.name" /></form:label></td>
        <td><form:input id="name" path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="description"><spring:message code="label.description"/></form:label></td>
        <td><form:input id="description" path="description" /></td>
    </tr>
    <tr>
        <td><form:label path="imageUrl"><spring:message code="label.imageurl"/></form:label></td>
        <td><form:input path="imageUrl" /></td>
    </tr>
    <tr>
        <td><form:label path="validDestination"><spring:message code="label.isvaliddestination"/></form:label></td>
        <td><form:checkbox path="validDestination"/></td>
    </tr>
    <tr>
        <td >
            <input type="submit" class="submit" value="<spring:message code="label.addplace"/>"/>
        </td>
    </tr>
</table>
</fieldset>
</form:form>
 
<h3>Places</h3>

<c:if  test="${!empty placesList}">
<table id="placesTable" class="data">
<thead> 
<tr>
    <th>Name</th>
    <th>Description</th>
    <th>imageURL</th>
    <th>Is Valid Destination</th>
    <th>&nbsp;</th>
</tr>
</thead> 
<tbody>
<c:forEach items="${placesList}" var="place">
    <tr>
        <td>${place.name}</td>
        <td>${place.description}</td>
        <td>${place.imageUrl}</td>
        <td>${place.validDestination}</td>
        <td><a href="delete/${place.id}">delete</a></td>
    </tr>
</c:forEach>
</tbody>
</table>
</c:if>
 
</body>
 <script type="text/javascript">
  $(document).ready(function(){
	  $('#placesTable').dataTable({
			"bJQueryUI": true,
			"bAutoWidth": false
		});
		$('#placesForm').formly();
    $('#placesForm').validate({
    	rules:{
    		name:{
    			required: true
    		},
    		description: {
    			required: true
    		},
    		imageUrl:{
    			required: true
    		}
    	}
    });
  });
  </script>
</html>