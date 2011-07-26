<?xml version="1.0" encoding="UTF-8"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:t="http://tiles.apache.org/tags-tiles"
          xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">

	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

<![CDATA[
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
]]> 
  <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Feit Map Service</title>
    </head>
		<body>
			<div id="wrapper_home">
				<div class="container_home">
					<div id="header">
						<t:insertAttribute name="header" />
					</div>
					<div id="content">
						<t:insertAttribute name="content" />
					</div>
				</div>
			</div>
		</body>  
	</html>

</jsp:root>
