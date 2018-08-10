<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">
 function preventBack(){window.history.forward();}
  setTimeout("preventBack()", 0);
  window.onunload=function(){null};
</script>
</head>
<body>
<div id='cssmenu'>
<ul>
	<li class='active '><a
		href="<%=request.getContextPath()%>/jsp/admin.jsp"><span>Home</span></a></li>
	<li class='active '><a
		href="<%=request.getContextPath()%>/jsp/addconstants.jsp"><span>Update Constants</span></a></li>
	<li class='active '><a
		href="<%=request.getContextPath()%>/jsp/addfilename.jsp"><span>Add File Name</span></a></li>
	<li class='active '><a
		href="<%=request.getContextPath()%>/jsp/removefilename.jsp"><span>Remove File Name</span></a></li>
	<li class='active '><a
		href="<%=request.getContextPath()%>/jsp/viewfilenames.jsp"><span>View File Names</span></a></li>
	<li class='active '><a
		href="<%=request.getContextPath()%>/jsp/viewdatasets.jsp"><span>View Data Sets</span></a></li>
		
	<li class='has-sub '><a href='<%=request.getContextPath()%>/elective/logout.do'>Logout<span></span></a>
	</li>
	

	
</ul>
</div>
</body>
</html>