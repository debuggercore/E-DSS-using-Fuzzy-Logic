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
		href="<%=request.getContextPath()%>/jsp/user.jsp"><span><font size="4">Home</font></span></a></li>
	<!-- li class='active '><a
		href="<%=request.getContextPath()%>/jsp/c45entermarks.jsp"><span>C 4.5 Prediction</span></a></li-->
	<li class='has-sub '><a href='#'><span>C 4.5 Prediction</span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/jsp/c45entermarksdatamining.jsp'><span>
								DataMining</span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/c45entermarkscns.jsp'><span>
								Computer & Network Security </span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/c45entermarksnlp.jsp'><span>
								NaturalLanguageProcessing </span></a></li>

				</ul></li>		
	<li class='has-sub '><a href='#'><span>Fuzzy Prediction</span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/jsp/entermarksdatamining.jsp'><span>
								DataMining</span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/entermarkscns.jsp'><span>
								Computer & Network Security </span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/entermarksnlp.jsp'><span>
								NaturalLanguageProcessing </span></a></li>

				</ul></li>
		
	<li class='active '><a
		href="<%=request.getContextPath()%>/jsp/recommendations.jsp"><span>Recommendations-So Far</span></a></li>	

	<li class='active '><a
		href="<%=request.getContextPath()%>/jsp/recommendationsgraph.jsp"><span>Recommendations Graph</font></a></li>
		
		
	<li class='has-sub '><a href='<%=request.getContextPath()%>/elective/logout.do'><span>Logout</span></a>
	</li>
	

	
</ul>
</div>
</body>
</html>