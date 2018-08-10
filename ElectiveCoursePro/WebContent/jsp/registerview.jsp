<!DOCTYPE html> 
<html>
	<head>
	<%@page import="com.model.RegisterVerifyMsgs"%>
	<%@page import="com.constants.ElectiveCourseConstantsIF"%>
		<title>Register</title>
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sky-forms.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/errormsg.css">
		<!--[if lt IE 9]>
			<link rel="stylesheet" href="css/sky-forms-ie8.css">
		<![endif]-->
		
		<!--[if lt IE 10]>
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
			<script src="js/jquery.placeholder.min.js"></script>
		<![endif]-->		
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/sky-forms-ie8.js"></script>
		<![endif]-->
		<script>
			function preventBack(){window.history.forward();}
			setTimeout("preventBack()", 0);
			window.onunload=function(){null};
		</script>
	</head>
	<body class="bg-cyan">
	
	<div class="isa_success">
     <i class="fa fa-check">
     If you are interested to track your history please register (These are only for academic purpose. The information will be kept confidential).
     </i>
     	
     </div>
	
	<%
RegisterVerifyMsgs loginVerify=(RegisterVerifyMsgs) request.getAttribute(ElectiveCourseConstantsIF.Keys.OBJ);
if(null==loginVerify)
{
	
}
else
{
	String  errMsg=loginVerify.getSeverMessage();
	if(null==errMsg)
	{
		
	}
	else
	{
%>

<div class="isa_error" name="userNameErr">
<i class="fa fa-times-circle"></i>
<%= errMsg%>
</div>
<%	
	}
}
%>

<%
if(null==loginVerify)
{
	
}
else
{
	String  sucessMsg=loginVerify.getSucessMsg();
	if(null==sucessMsg)
	{
		
	}
	else
	{
%>

<div class="isa_success">
     <i class="fa fa-check"></i>
<%= sucessMsg%>
</div>
<%	
	}
}
%>




		<%
		if(loginVerify!=null)
		{
			String u=loginVerify.getUserNameErrMsg();
			if(null==u)
			{
				
			}
			else
			{
				%> 
				<div class="isa_error" name="userNameErr">
				<i class="fa fa-times-circle"></i>
				<%=u%>
				</div>
		<%
			
			
			}
			}%>
		<%
		if(loginVerify!=null)
		{
			String p=loginVerify.getPasswordErrMsg();
			if(null==p)
			{
				
			}
			else
			{
				%> 
				<div class="isa_error">
				<i class="fa fa-times-circle"></i>
				<%=p%>
				</div>
		<%
			
			
			}
			}%>

			<%
			if(loginVerify!=null)
			{
			String firstNameErr=loginVerify.getFirstNameErrMsg();
			if(null==firstNameErr || firstNameErr.isEmpty())
			{
				
			}
			else
			{
				%> 
				<div class="isa_error">
				<i class="fa fa-times-circle"></i>
				<%=firstNameErr%>
				</div>
			<%
			
			}
			}%>


			<%
			if(loginVerify!=null)
			{
			String lastNameErr=loginVerify.getLastNameErrMsg();
			if(null==lastNameErr || lastNameErr.isEmpty())
			{
				
			}
			else
			{
				%> 
				
				<div class="isa_error">
				<i class="fa fa-times-circle"></i>
				<%=lastNameErr%>
				</div>
			<%
			
			}
			}%>


				<%
				if(loginVerify!=null)
				{
			String emailErr=loginVerify.getEmailErrMsg();
			if(null==emailErr)
			{
				
			}
			else
			{
				%>
				<div class="isa_error">
				<i class="fa fa-times-circle"></i>
				<%=emailErr%>
				</div>
				<%
			
			}
			}%>

				<%
				if(loginVerify!=null)
				{
			String  sexErr=loginVerify.getSexErrMsg();
			if(null==sexErr)
			{
				
			}
			else
			{
				%> 
				<div class="isa_error">
				<i class="fa fa-times-circle"></i>
				<%=sexErr%>
				</div>
				<%
			
			}
			}%>

				<%
				if(loginVerify!=null)
				{
			String  dobErr=loginVerify.getDobErrMsg();
			if(null==dobErr)
			{
				
			}
			else
			{
				%> <div class="isa_error">
					<i class="fa fa-times-circle"></i>
					<%=dobErr%></div>
				<%
			
			}
			}%>


		<div class="body body-s">
		
			<form action="<%=request.getContextPath()%>/elective/registerUser.do" method="post" class="sky-form">
				<header>Registration form <a href="<%=request.getContextPath()%>/jsp/welcome.jsp" class="button">Home</a></header>
				<fieldset>					
					<section>
						<label class="input">
							<i class="icon-append icon-user"></i>
							<input type="text" name="userId" placeholder="Username">
							<b class="tooltip tooltip-bottom-right">Enter Username</b>
						</label>
					</section>
					
					<section>
						<label class="input">
							<i class="icon-append icon-envelope-alt"></i>
							<input type="text" name="emailId" placeholder="Email address">
							<b class="tooltip tooltip-bottom-right">Email</b>
						</label>
					</section>
					
					<section>
						<label class="input">
							<i class="icon-append icon-lock"></i>
							<input type="password" name="userPassword" placeholder="Password">
							<b class="tooltip tooltip-bottom-right">Only latin characters and numbers</b>
						</label>
					</section>
					
					<section>
						<label class="input">
							<i class="icon-append icon-lock"></i>
							<input type="date" name="dob" placeholder="Date of Birth">
							<b class="tooltip tooltip-bottom-right">Only latin characters and numbers</b>
						</label>
					</section>
				</fieldset>
					
				<fieldset>
					<div class="row">
						<section class="col col-6">
							<label class="input">
								<input type="text" name="firstName" placeholder="First name">
							</label>
						</section>
						<section class="col col-6">
							<label class="input">
								<input type="text" name="lastName" placeholder="Last name">
							</label>
						</section>
					</div>
					
					
					
					<section>
						<label class="select">
							<select name="sex">
								<option value="0" selected disabled>Sex</option>
								<option value="1">Male</option>
								<option value="2">Female</option>
							</select>
							<i></i>
						</label>
					</section>
					</fieldset>
									<footer>
					<button type="submit" class="button">Submit</button>
				</footer>
			</form>
			
		</div>
	</body>
</html>