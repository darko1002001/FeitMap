<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title><spring:message code="label.edges.title"/></title>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.validate.js">
</script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/chosen.jquery.js">
</script>

   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/chosen/chosen.css"/>
</head>
<body>
 
<h2><spring:message code="label.edges.title"/></h2>
 
<form:form id="edgesForm" method="post" action="add" commandName="edge">
 <fieldset>
    <table>
    
    <tr>
      <td><form:label path="fromPlace.id">
						<spring:message code="label.edges.fromplace" />
					</form:label>
				</td>
				<td><form:select path="fromPlace.id" class="chzn-select" style="width:350px;" name="fromPlace.id" id="fromPlace">
				<option value="-1">Select a Place</option>
						<c:if test="${!empty placesList}">
								
									<c:forEach items="${placesList}" var="place">
									<option value="${place.id}">${place.name}</option>
									</c:forEach>
						</c:if>
					</form:select>
				</td>
				<td><form:errors path="fromPlace"></form:errors></td>
    </tr>
    <tr>
        <td><form:label path="toPlace.id"><spring:message code="label.edges.toplace"/></form:label></td>
				<td><form:select path="toPlace.id" class="chzn-select" style="width:350px;" id="toPlace" name="toPlace.id">
				<option value="-1">Select a Place</option>
						<c:if test="${!empty placesList}">
								
									<c:forEach items="${placesList}" var="place">
									<option value="${place.id}">${place.name}</option>
									</c:forEach>
						</c:if>
					</form:select>
				</td>
				<td><form:errors path="toPlace"></form:errors></td>
    </tr>
    <tr>
        <td><form:label path="cost"><spring:message code="label.edges.cost"/></form:label></td>
        <td><form:input path="cost" id="cost"/></td>
         <td><form:errors path="cost"></form:errors></td>
        
    </tr>
    <tr>
        <td >
            <input type="submit" class="submit" value="<spring:message code="label.edges.addedge"/>"/>
        </td>
        <td>&nbsp;</td>
        <td><form:errors ></form:errors></td>
    </tr>
    
</table>
</fieldset>
</form:form>
 
<h3>Edges</h3>

<c:if  test="${!empty edgesList}">
<table id="edgesListTable" class="data">
<thead> 
<tr>
    <th>To Place</th>
    <th>From Place</th>
    <th>Cost</th>
    <th>&nbsp;</th>
</tr>
</thead> 
<tbody>
<c:forEach items="${edgesList}" var="edge">
    <tr>
        <td>${edge.toPlace.name}</td>
        <td>${edge.fromPlace.name}</td>
        <td>${edge.cost}</td>
        <td><a href="delete/${edge.id}">delete</a></td>
    </tr>
</c:forEach>
</tbody>
</table>
</c:if>
 
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#edgesListTable').dataTable({
			"bJQueryUI": true,
			"bAutoWidth": false
		});
		$('#edgesForm').formly();
		 $("#edgesForm").validate({
			rules:{
				"fromPlace.id":{
	    			required: true,
	    			min:0
	    		},
	    		"toPlace.id":{
	    			required: true,
	    			min:0
	    		},
	    		cost:{
	    			required: true,
	    			min:10
	    		}
	    	},
	    	messages:{
	    		"fromPlace.id":{
	    			required:"Select a place",
	    			min:"Select a place"
	    		},
	    		"toPlace.id": {
	    			required:"Select a place",	
	    				min:"Select a place"
	    		},
	    		cost:{
	    			required: "Enter a cost",
	    			min:jQuery.format("must be at least {0}")
	    		}
	    	}
		});
		$('#fromPlace').chosen();
		$('#toPlace').chosen();
	});
</script>

</html>