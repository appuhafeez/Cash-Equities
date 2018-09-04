
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.crud.UserDetailDAO"%>
<%@page import="com.trade.UserDetail"%>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
  <title>DBS Stock Trader</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/font-awesome.css">
  <link rel="stylesheet" href="plugins/animate%2banimo.css">
  <link rel="stylesheet" href="plugins/csspinner.min.css">
  <link rel="stylesheet" href="css/app.css">
  <link rel="stylesheet" href="css/phpwallet1.css">
  <script src="js/modernizr.js"></script>
  <script src="js/fastclick.js"></script>
<link rel="stylesheet" src="css/table.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

<body>
  <div id="overlayLoader">
    <div id="preloader">
      <span></span>/
      <span></span>
    </div>
  </div>
<%
UserDetailDAO userDetailDAO = new UserDetailDAO();
HttpSession session2=request.getSession();
UserDetail userDetail = userDetailDAO.getWalletInfo(session2.getAttribute("userid").toString()).get(0);

%>

  <section class="wrapper">
    <nav class="navbar navbar-default navbar-top navbar-fixed-top">
      <div class="navbar-header">
        <a href="market" class="navbar-brand">
          <div class="brand-logo"><i> <img src="css/dbslogo.png" height="35px" width="35px" style="border-radius:50%"> </i> <b style="font-weight:700; color:#DD1414">STOCK</b>TRADER.com</div>
          <div class="brand-logo-collapsed"><i><img src="css/dbslogo.png" height="40px" width="40px" style="border-radius:50%; top:25px"></i></div>
        </a>
      </div>
      <div class="nav-wrapper">
        <ul class="nav navbar-nav mt0">
          <li>
            <a href="#" data-toggle="aside">
                     <em class="fa fa-align-left"></em>
                     </a>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right mt0">
          <li class="dropdown dropdown-list">
            <a href="walletlist" data-toggle="dropdown" data-play="fadeIn" class="dropdown-toggle">
                     <strong><i class="fa fa-inr"></i> Wallet</strong>
                     </a>

      </li>
      <li class="dropdown dropdown-list">
        <a href="" data-toggle="dropdown" data-play="fadeIn" class="dropdown-toggle">
                        <em class="fa fa-bell"></em>
                        <div class="label label-danger">3</div>
                     </a>
        <ul class="dropdown-menu col-md-4 col-sm-6 col-xs-12">
          <li>
            <div class="table-responsive">
              <table class="table table-striped table-bordered table-hover">
                <thead>
                  <tr>
                    <th>Buy/Sell</th>
                    <th>Progress</th>
                    <th>Date</th>
                    <th>Status</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Buy order SC</td>
                    <td>
                      <div class="progress progress-striped progress-xs">
                        <div role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" class="progress-bar progress-bar-success w-100-p">
                          <span class="sr-only">100% Complete</span>
                        </div>
                      </div>
                    </td>
                    <td>
                      <em class="fa fa-calendar fa-fw text-muted"></em>02/19/2018
                    </td>
                    <td class="text-center">
                      Complete
                    </td>
                  </tr>
                  <tr>
                    <td>Sell order SC</td>
                    <td>
                      <div class="progress progress-striped progress-xs">
                        <div role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" class="progress-bar progress-bar-danger w-50-p">
                          <span class="sr-only">50% Complete</span>
                        </div>
                      </div>
                    </td>
                    <td>
                      <em class="fa fa-calendar fa-fw text-muted"></em>02/18/2018
                    </td>
                    <td class="text-center">
                      50% Filled
                    </td>
                  </tr>
                  <tr>
                    <td>Buy order IOTA</td>
                    <td>
                      <div class="progress progress-striped progress-xs">
                        <div role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" class="progress-bar progress-bar-success w-50-p">
                          <span class="sr-only">50% Complete</span>
                        </div>
                      </div>
                    </td>
                    <td>
                      <em class="fa fa-calendar fa-fw text-muted"></em>02/17/2018
                    </td>
                    <td class="text-center">
                      50% Filled
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" data-toggle="dropdown" data-play="fadeIn" class="dropdown-toggle">
                     <em class="fa fa-user"></em>
                     </a>
        <ul class="dropdown-menu">
          <li><a href="#">Profile</a>
          </li>
          <li class="divider"></li>
          <li><a href="login">Logout</a>
          </li>
        </ul>
      </li>
      </ul>
      </div>
    </nav>
    <aside class="aside">
      <nav class="sidebar">
        <ul class="nav">
          <li>
            <div data-toggle="collapse-next" class="item user-block has-submenu">
              <div class="user-block-picture">
                <img src="02.jpg" alt="Avatar" width="60" height="60" class="img-thumbnail img-circle account-img-mb">
              </div>
              <div class="user-block-info">
                <span class="user-block-name item-text">${username}</span>
                <span class="user-block-role"><i class="fa fa-check text-green"></i> Verified</span>
                <div class="label label-primary"><i class="fa fa-chevron-down"></i> Account Info</div>
              </div>
            </div>
            <ul class="nav collapse">
              <li><a href="market#open_orders">Open Orders</a>
              </li>
              <li><a href="market#history">Transactions History</a>
              </li>
              <li><a href="market#market">Market History</a>
              </li>
              <li>
                <a href="javascript:void(0);">
                              Verification
                              <div class="label label-success pull-right"><i class="fa fa-check"></i> Verified</div>
                           </a>
              </li>
            </ul>
          </li>
          <li class="active">
            <a href="market" onclick="location.href='market'" title="index" data-toggle="collapse-next">
                        <em class="fa fa-home"></em>
                        <div class="label pull-right"><i class="fa fa-line-chart"></i></div>
                        <span class="item-text">Markets</span>
                     </a>
            </li>
            <li>
              <a href="Portfolio" title="Dashboard" class="">
                        <em class="fa fa-btc"></em>
                        <span class="item-text">Portfolio</span>
                     </a>
            </li>
            <li class="">
              <a href="market#order_book" title="Dashboard" class="">
                        <em class="fa fa-gear"></em>
                        <span class="item-text">Order Book</span>
                     </a>
            </li>
            <li>
              <a title="Pages" href="market#history">
                     <em class="fa fa-file-text"></em>
                     <span class="item-text">My Order History</span>
                     </a>

            </li>
            <li>
              <a title="Pages" href="market#history">
                     <em class="fa fa-bell"></em>
                     <span class="item-text">Transaction status</span>
                     </a>

            </li>
        </ul>
      </nav>
    </aside>

	<style>
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
	font-size:25 px;

}

tr:hover {background-color:#e6e6e6;}


label {
color: #B4886B;
font-weight: bold;

width: 150px;

font-size:20px;
}

</style>


    <section>


      <!--Scroll ticker -->

     
       <!--<jsp:include page="/ticker" />-->
      <!-- ticker close-->

	   <section>
	   <div>
	   <pre>  <label>ACCOUNT STATUS:- </label>    <label><b style="color:green;"> Active</b></label>                                 <label>Account Credited on:</label></pre>
	   </div>


<div >
  <div >
   <center> <font size="5px"> PERSONAL INFORMATION </font></center>
<div class="wallets-container card white-background">
    <table >
  <tr>
    <th>NAME:</th>
    <td><%=userDetail.getFullName().split("\\s")[0] %></td>

  </tr>
  <tr>
    <th>ACCOUNT NUMBER</th>
    <td><%=userDetail.getAccountNumber() %> </td>

  </tr>
  <tr>
    <th>STOCK TRADER ID</th>
    <td><%=userDetail.getUserId() %></td>

  </tr>




</table>

</div>
  </div>

  <div  >
   <center> <font size="5px"> KYC DETAILS </font></center>

<div class="wallets-container card white-background">
<table >
  <tr>
    <th>AADHAR NUMBER:</th>
    <td><%=userDetail.getAadharNumber() %></td>

  </tr>
  <tr>
    <th>PAN NUMBER</th>
    <td><%=userDetail.getPan() %></td>

  </tr>


  <tr>
    <th>FULL NAME:</th>
    <td><%=userDetail.getFullName() %></td>

  </tr>

</table>
</div>
  </div>
</div>

</section>

  <script src="js/plugins/jquery.js"></script>
  <script src="js/plugins/velocity.js"></script>
  <script src="js/plugins/velocity.ui.js"></script>
  <script src="js/plugins/bootstrap.js"></script>
  <script src="js/plugins/chosen.jquery.js"></script>
  <script src="js/plugins/bootstrap-slider.js"></script>
  <script src="js/plugins/bootstrap-filestyle.js"></script>
  <script src="js/plugins/animo.js"></script>
  <script src="js/plugins/jquery.sparkline.js"></script>
  <script src="js/plugins/jquery.slimscroll.js"></script>
  <script src="js/plugins/jquery.dataTables.js"></script>
  <script src="js/plugins/dataTables.bootstrap.js"></script>
  <script src="js/plugins/dataTables.bootstrapPagination.js"></script>
  <script src="js/highcharts.js"></script>
  <script src="js/exporting.js"></script>
  <script src="js/plugins/dataTables.colVis.js"></script>

  <!--[if lt IE 8]><script src="js/excanvas.min.js"></script><![endif]-->
  <script src="js/tradify.js"></script>
  <script>
    $(document).ready(function() {
      // Candlestick
      $.getJSON('tradify/data.json', function(data) {
        // create the chart
        Highcharts.stockChart('candlestickChart', {
          chart: {},
          rangeSelector: {
            selected: 1
          },
          series: [{
            type: 'candlestick',
            name: 'SC-BTC',
            data: data,
            dataGrouping: {
              units: [
                [
                  'week', // unit name
                  [1] // allowed multiples
                ],
                [
                  'month', [1, 2, 3, 4, 6]
                ]
              ]
            }
          }]
        });
      });
    });

    var auto_refresh = setInterval(
    	    function()
    	    {
    			$('#macroaxis_stock_ticker').fadeIn("slow");
    			$('#securitytable').load("http://localhost:8080/Cash-Equities-Master/markettable").fadeIn("slow");
    	    }, 5000);




  </script>
</body>

</html>
