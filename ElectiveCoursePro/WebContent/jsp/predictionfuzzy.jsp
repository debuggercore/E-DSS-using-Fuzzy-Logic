<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.model.PredictedValue"%>
<%@page
	import="com.model.AJAXResponse,com.constants.ElectiveCourseConstantsIF"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/errormsg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">
var contextPath='<%=request.getContextPath()%>';
var updatable=false;
</script>

<script type="text/javascript">
 function preventBack(){window.history.forward();}
  setTimeout("preventBack()", 0);
  window.onunload=function(){null};
</script>

</head>
<body>
	<jsp:include page="/jsp/usermenu.jsp"></jsp:include>

	<%
									AJAXResponse ajax=(AJAXResponse)request.getAttribute(ElectiveCourseConstantsIF.Keys.OBJ);
									if(null==ajax){
										
									}else{	
											String msg=ajax.getMessage();
											if(null==msg){
											}
											else{
								%>
	<div class="isa_success">
		<%=msg%></div>
	<%
										}
											
											PredictedValue predictedValue =(PredictedValue)ajax.getModel();
											
											if(null==predictedValue){
												
											}else{
								%>
	<div class="isa_success">
	VERYHIGH=>{S,A,B} HIGH=>{A,B,C} MEDIUM=>{B,C,D} LOW=>{C,D,E} VERYLOW=>{D,E,F}</br>
	S=10 A=9 B=8 C=7 D=6 E=5 F=4
	</div>
	<table class="isa_success">
	<tr><td><label>The Predicted Value is:</label> <span class="value"><%=predictedValue.getPredictedCapable()%></span></td></tr>
	<tr><td><label>The Center of Gravity for the Elective <%=predictedValue.getElectiveCourse()%> is:	</label><span class="value"><%=predictedValue.getFirstFuzzyOutputCog()%></span></td></tr>
	
	</table>

	<%}}%>


</body>
</html>