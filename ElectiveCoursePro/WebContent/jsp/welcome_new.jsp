<!DOCTYPE html>
<head lang="en-IE">
	<meta charset="utf-8">	
	<title>HTML5 Video Voice Control with the Web Speech API</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<%=request.getContextPath()%>/css/video.css" rel="stylesheet" media="all">
	<script>
		var contextPath='<%=request.getContextPath()%>';
	</script>
</head>
<body>
	<main role="main">
		<h1><abbr title="HyperText Markup Language 5">HTML5</abbr> Video Voice Control with the Web Speech <abbr title="Application Programming Interface">API</abbr></h1>
		<p>Read the related <a href="http://www.iandevlin.com/blog/2014/01/javascript/using-the-web-speech-api-to-control-a-html5-video/">article</a>.</p>
		<div class="msg" data-state="hidden">This browser doesn't support the WebKit Web Speech <abbr title="Application Programming Interface">API</abbr>. You need the latest version of Chrome. Sorry!</div>
		
		<div class="rec-status">Recogniser status:<span id="recStatus">not recognising</span></div>
		<button id="startRecBtn">Start Recognition</button>
		<button id="stopRecBtn">Stop Recognition</button>
		<p>Click "Start Recognition", give microphone access permission and start controlling the video's playback, mute and volume control via speech.<br>Available commands are:</p>
		<ul>
			<li id="send">"Send" Use this Keyword for Payment Transfer</li>
			<li id="Stop">"Stop" Use this Stop Payment Instruction</li>
		</ul>
	</main>
	<script src="<%=request.getContextPath()%>/js/video-speech.js" defer></script>
	<script>
		var _gaq=[['_setAccount','UA-2046650-1'],['_trackPageview']];(function(a,b){var c=a.createElement(b),d=a.getElementsByTagName(b)[0];c.src=("https:"==location.protocol?"//ssl":"//www")+".google-analytics.com/ga.js";d.parentNode.insertBefore(c,d)})(document,"script");
	</script>
	
	<div id="confirmationMessage"></div>
	<div id="errorMessage"></div>

	
	
</body>
</html>