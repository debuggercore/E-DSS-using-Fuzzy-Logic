<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page import="com.model.AJAXResponse"%>
<%@page import="com.model.Message"%>

<%@page import="com.constants.ElectiveCourseConstantsIF"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Constant</title>
</head>
<body>
<body background="<%=request.getContextPath()%>/images/background.jpg">
<jsp:include page="/jsp/adminmenu.jsp"></jsp:include>

<%
AJAXResponse ajaxResponse=(AJAXResponse) request.getAttribute(ElectiveCourseConstantsIF.Keys.OBJ);
if(null==ajaxResponse)
{
	
}
else
{
	boolean status=ajaxResponse.isStatus();
%>
<%
	if(!status)
	{
		List<Message> msg=ajaxResponse.getEbErrors();
%>
<%
		for(int i=0;i<msg.size();i++)
		{
			Message tempMsg=msg.get(i);
	%>
	
	<div class="errMsg"><%= tempMsg.getErrMessage()%></div>
	
<%		}
	
	}
}
%>


<form action="<%=request.getContextPath()%>/elective/addFileName.do" method="post"  >


	<table>
		<tr>
			<td><label>Enter File Name:</label> </td>
			<td><input name="fileName" id="fileName" type="text"  size="50" maxlength="50" /></td>
		</tr>
		<tr>
			<td><label>Enter Elective Name:</label> </td>
			<td><input name="electiveName" id="electiveName" type="text"  size="50" maxlength="50" /></td>
		</tr>
			<tr>
			<td><input  type="submit" value="Add Info" onclick="validatestopword()"/></td>
		</tr>
			
	</table>
</form>
</body>
</html>