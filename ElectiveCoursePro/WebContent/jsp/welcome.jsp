
<!DOCTYPE html>
<!--[if lt IE 7]>      <html lang="en" class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html lang="en" class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html lang="en" class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
    <head>
    	<!-- meta character set -->
        <meta charset="utf-8">
		<!-- Always force latest IE rendering engine or request Chrome Frame -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Elective Course</title>		
		<!-- Meta Description -->
        <meta name="description" content="Blue One Page Creative Template">
        <meta name="keywords" content="one page, single page, onepage, responsive, parallax, creative, business, html5, css3, css3 animation">
        		
		<!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS
		================================================== -->
		
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
		
		<!-- Fontawesome Icon font -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
		<!-- bootstrap.min -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.fancybox.css">
		<!-- bootstrap.min -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<!-- bootstrap.min -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.carousel.css">
		<!-- bootstrap.min -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slit-slider.css">
		<!-- bootstrap.min -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/animate.css">
		<!-- Main Stylesheet -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

		<!-- Modernizer Script for old Browsers -->
        <script src="<%=request.getContextPath()%>/js/modernizr-2.6.2.min.js"></script>

    </head>
	
    <body id="body">

		<!-- preloader -->
		<div id="preloader">
            <div class="loder-box">
            	<div class="battery"></div>
            </div>
		</div>
		<!-- end preloader -->

        <!--
        Fixed Navigation
        ==================================== -->
        <header id="navigation" class="navbar-inverse navbar-fixed-top animated-header">
            <div class="container">
                <div class="navbar-header">
                    <!-- responsive nav button -->
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
                    </button>
					<!-- /responsive nav button -->	
                </div>

				<!-- main nav -->
                <nav class="collapse navbar-collapse navbar-right" role="navigation">
                    <ul id="nav" class="nav navbar-nav">
                        <li><a href="#body">Home</a></li>
                        <li><a href="#service">Service</a></li>
                        <li><a href="#portfolio">portfolio</a></li>
                        <li><a href="#contact">Contact</a></li>
                    </ul>
                </nav>
				<!-- /main nav -->
				
            </div>
        </header>
        <!--
        End Fixed Navigation
        ==================================== -->
		
		<main class="site-content" role="main">
		
        <!--
        Home Slider
        ==================================== -->
		
		<section id="home-slider">
            <div id="slider" class="sl-slider-wrapper">

				<div class="sl-slider">
				
					<div class="sl-slide" data-orientation="horizontal" data-slice1-rotation="-25" data-slice2-rotation="-25" data-slice1-scale="2" data-slice2-scale="2">

						<div class="bg-img bg-img-1"></div>

						<div class="slide-caption">
                            <div class="caption-content">
                                <h2 class="animated fadeInDown">Elective Course</h2>
                                <span class="animated fadeInDown">Elective Course,Data Mining Approach</span>
                                <a href="<%=request.getContextPath()%>/jsp/registerview.jsp" class="btn btn-blue btn-effect">Sign Up</a>
							 	<a href="<%=request.getContextPath()%>/jsp/login.jsp" class="btn btn-blue btn-effect">Login</a>
								 <span>This is for  knowing your Subjects Score and is used for academic purpose. The data will be confidential</span>	
                            </div>
                        </div>
						
					</div>
					
					<div class="sl-slide" data-orientation="vertical" data-slice1-rotation="10" data-slice2-rotation="-15" data-slice1-scale="1.5" data-slice2-scale="1.5">
					
						<div class="bg-img bg-img-2"></div>
						<div class="slide-caption">
                            <div class="caption-content">
                                <h2>Elective Course</h2>
                                <span>Elective Course,Data Mining Approach</span>
                                <a href="<%=request.getContextPath()%>/jsp/registerview.jsp" class="btn btn-blue btn-effect">Sign Up</a>
							 	<a href="<%=request.getContextPath()%>/jsp/login.jsp" class="btn btn-blue btn-effect">Login</a>
								 <span>This is for  knowing your Subjects Score and is used for academic purpose. The data will be confidential</span>	
                            </div>
                        </div>
						
					</div>
					
					<div class="sl-slide" data-orientation="horizontal" data-slice1-rotation="3" data-slice2-rotation="3" data-slice1-scale="2" data-slice2-scale="1">
						
						<div class="bg-img bg-img-3"></div>
						<div class="slide-caption">
                            <div class="caption-content">
                                <h2>Fuzzy Logic</h2>
                                <span>Elective Course,Data Mining Approach</span>
                                <a href="<%=request.getContextPath()%>/jsp/welcome.jsp" class="btn btn-blue btn-effect">Home</a>
                            </div>
                        </div>

					</div>

				</div><!-- /sl-slider -->

                <!-- 
                <nav id="nav-arrows" class="nav-arrows">
                    <span class="nav-arrow-prev">Previous</span>
                    <span class="nav-arrow-next">Next</span>
                </nav>
                -->
                
                <nav id="nav-arrows" class="nav-arrows hidden-xs hidden-sm visible-md visible-lg">
                    <a href="javascript:;" class="sl-prev">
                        <i class="fa fa-angle-left fa-3x"></i>
                    </a>
                    <a href="javascript:;" class="sl-next">
                        <i class="fa fa-angle-right fa-3x"></i>
                    </a>
                </nav>
                

				<nav id="nav-dots" class="nav-dots visible-xs visible-sm hidden-md hidden-lg">
					<span class="nav-dot-current"></span>
					<span></span>
					<span></span>
				</nav>

			</div><!-- /slider-wrapper -->
		</section>
		
        <!--
        End Home SliderEnd
        ==================================== -->
			
			
		</main>
		
		
		
		<!-- Essential jQuery Plugins
		================================================== -->
		<!-- Main jQuery -->
        <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.min.js"></script>
		<!-- Twitter Bootstrap -->
        <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
		<!-- Single Page Nav -->
        <script src="<%=request.getContextPath()%>/js/jquery.singlePageNav.min.js"></script>
		<!-- jquery.fancybox.pack -->
        <script src="<%=request.getContextPath()%>/js/jquery.fancybox.pack.js"></script>
		<!-- Owl Carousel -->
        <script src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
        <!-- jquery easing -->
        <script src="<%=request.getContextPath()%>/js/jquery.easing.min.js"></script>
        <!-- Fullscreen slider -->
        <script src="<%=request.getContextPath()%>/js/jquery.slitslider.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.ba-cond.min.js"></script>
		<!-- onscroll animation -->
        <script src="<%=request.getContextPath()%>/js/wow.min.js"></script>
		<!-- Custom Functions -->
        <script src="<%=request.getContextPath()%>/js/main.js"></script>
    </body>
</html>