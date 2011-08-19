<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js">
</script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jcarousel.js">
</script>
    <title>Places Add Or Update</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tango/skin.css"/>

</head>
<body>
 
<h2>Places Manager</h2>
 
<form:form id="placesForm" method="post" action="persist" commandName="place">
  <fieldset>
    <table>
    <form:input type="hidden" path="id" name="id"></form:input>
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
        <td><form:input id="imageUrl" path="imageUrl" style="width:500px;" /></td>
    </tr>
    <tr>
        <td><form:label path="validDestination"><spring:message code="label.isvaliddestination"/></form:label></td>
        <td><form:checkbox path="validDestination"/></td>
    </tr>
    <tr>
        <td >
            <input id="submit" type="submit" class="submit" />
        </td>
    </tr>
</table>
</fieldset>
</form:form>
  <ul id="mycarousel" class="jcarousel-skin-tango">
  
 </ul>
</body>
 <script type="text/javascript">
  $(document).ready(function(){
	  
		$('#placesForm').formly();
		fetchImages();
		 jQuery('#mycarousel').jcarousel({
		        itemLoadCallback: mycarousel_itemLoadCallback
		    });
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
    setLabelText();
  });
  </script>
   <script type="text/javascript">
   function setLabelText(){
	   if('${place.id}'===''){
		   placesForm.submit.value='<spring:message code="label.addplace"/>';
	   }else{
		   placesForm.submit.value='<spring:message code="label.updateplace"/>';
	   }
   };
    function fetchImages() {
    	jQuery('#mycarousel').jcarousel({
            itemLoadCallback: mycarousel_itemLoadCallback,
            visible: 6,
            scroll: 3
        });
    }
  </script>
  
  <script type="text/javascript">

function mycarousel_itemLoadCallback(carousel, state)
{
    // Since we get all URLs in one file, we simply add all items
    // at once and set the size accordingly.
    if (state != 'init')
        return;
    jQuery.getJSON("images", function (data) {
    	 mycarousel_itemAddCallback(carousel, carousel.first, carousel.last, data);
      });
};
var imageArray;
function mycarousel_itemAddCallback(carousel, first, last, data)
{
    // Simply add all items at once and set the size accordingly.
	imageArray=data;
    for (i = 0; i < data.length; i++) {
        carousel.add(i+1, mycarousel_getItemHTML(data[i].url,i));
    }

    carousel.size(data.length);
};

/**
 * Item html creation helper.
 */
function mycarousel_getItemHTML(url,i)
{
    return '<img onclick="javascript:onImageClick('+ i + ');" src="' + url + '" width="75" height="75" alt="" />';
};
function onImageClick(position){
	placesForm.imageUrl.value=imageArray[position].id;
};
</script>
</html>