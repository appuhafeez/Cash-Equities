<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 strict//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<HTML>

<HEAD>

  <script type="text/javascript" src="https://cdn-macroaxis.netdna-ssl.com/scripts/minimum.js"></script>
  <link href="https://cdn-macroaxis.netdna-ssl.com//material/css/app.css" rel="stylesheet">
  <link href="https://cdn-macroaxis.netdna-ssl.com/static/vendors/noUiSlider/jquery.nouislider.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn-macroaxis.netdna-ssl.com/skins/minimum.css" type="text/css" />

  <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-87DrmpqHRiY8hPLIr7ByqhPIywuSsjuQAfMXAE0sMUpY3BM7nXjf+mLIUSvhDArs" crossorigin="anonymous">

  <link href="https://cdn-macroaxis.netdna-ssl.com/static/css/override.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    .defaultCombo {
      height: 32px;
    }
  </style>


  <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

  <script async src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>

  <link href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.min.css" rel="stylesheet">

  <script src="https://cdn-macroaxis.netdna-ssl.com/scripts/jquery.modern-ticker.min.js" type="text/javascript"></script>
  <link href="https://cdn-macroaxis.netdna-ssl.com/skins/modern-ticker.css" type="text/css" rel="stylesheet">
  <link href="https://cdn-macroaxis.netdna-ssl.com/skins/modern-ticker-theme10.css" type="text/css" rel="stylesheet">

  <style type="text/css">
    .noindent {
      list-style-type: none;
      list-style: none;
      padding: 0;
      border: 0px solid #ddd;
      margin: 0px;
    }

    li {
      list-style-type: none;
      list-style: none;
      padding: 0;
      border: 0px solid #ddd;
      margin: 0px;
    }

    .mt-controls {
      margin: 0px;
      padding: 0px;
      right: 0px;
      border: 0px solid red;
    }

    .mt-label {
      margin: 0px;
      width: 95px;
      padding: 0;
      padding-left: 2px;
      padding-top: 1px;
      background: #eeeddd;
      border: 0px solid red;
    }

    .mt-scroll {
      margin: 0px;
      padding: 0px;

      border: 0px solid red;
    }

    .modern-ticker {
      width: 100%;

      margin: 0px;
      padding: 0px;
    }

    .mt-news {

      margin: 0px;
      padding: 0px;
      border: 0px solid red;
    }

    .mt-prev {

      margin: 0px;
      padding: 0px;
      border: 0px solid red;
    }

    .mt-play {

      margin: 0px;
      padding: 0px;
      border: 0px solid red;
    }

    .mt-next {
      margin: 0px;
      padding: 0px;
      border: 0px solid red;
    }
  </style>

  <title>Stock Ticker - Macroaxis</title>
</HEAD>

<body>







  <script type="text/javascript">
    $(function() {
      // Start ticker initialization code
      $(".modern-ticker").modernTicker({
        effect: "scroll",
        scrollInterval: 20,
        transitionTime: 500,
        autoplay: true
      });

    });
  </script>


  <div id="wrapper" style="padding:0px; margin:0px; border: 0px solid black;background: green;">


    <div id="scroll"class="modern-ticker">


      <jsp:include page="/ticker"/>
      <!-- <div class="mt-controls">
        <div class="mt-prev"></div>
        <div class="mt-play"></div>
        <div class="mt-next"></div>
      </div> -->
    </div>
  </div>


  <!-- Global site tag (gtag.js) - Google Analytics -->
  <script async src="https://www.googletagmanager.com/gtag/js?id=UA-649901-12"></script>
  <script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
      dataLayer.push(arguments);
    }
    gtag('js', new Date());

    gtag('config', 'UA-649901-12');
    
    var auto_refresh = setInterval(
    	    function()
    	    {
    			$('#scroll').load("http://localhost:8080/Cash-Equities-Master/ticker").fadeIn("slow");
    	    }, 5000);
    
    
  </script>




</body>

</html>