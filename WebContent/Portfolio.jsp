<!DOCTYPE html>
<%@page import="com.trade.UserStockInfo"%>
<%@page import="com.trade.UserHistory"%>
<%@page import="java.util.List"%>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">

  <link rel="icon" type="image/png" sizes="16x16" href="images/favicon-16x16.png">
  <title>DBS Stock Trader</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="plugins/animate%2banimo.css">
  <link rel="stylesheet" href="plugins/csspinner.min.css">
  <link rel="stylesheet" href="css/app.css">
  <script src="js/modernizr.js"></script>
  <script src="js/fastclick.js"></script>

  <link rel="stylesheet" href="css/wallet_history.css">
  <link rel="stylesheet" href="css/wallet_history2.css">
  <link rel="stylesheet" href="css/phpwallet1.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
  <link rel="stylesheet" href="css/payment.css">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

  <style>
  .circularIcons {
    width: 45px;
    height: 45px;
    padding: 12px;
    background: #0c6eb5;
    border-radius: 100%;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    justify-content: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    color: white;
    font-size: 20px;
    margin: 6px 11px 6px 6px
  }
  </style>

  <script>
    $(document).ready(function() {
      $("#deposit").click(function() {
        $("#add_money").fadeIn(10, function() {
            $("#wallet_history").fadeOut(10, function() {
              $("#add").click(function() {
                $("#add_money4").fadeIn(10,function(){
                  $("#add_money").fadeOut(10, function() {
                     $("#submit1").click(function() {
                        $("#success").fadeIn(10,function(){
                            $("#add_money4").fadeOut(10,function(){
                                setTimeout(function(){location.href="wallet.html";}, 3000);
                              });
                            });
                        });
                    });
                  });
                });
            });
          });
        });
      $("#withdraw").click(function() {
         $("#add_money").fadeIn(10, function() {
           $("#wallet_history").fadeOut(10, function() {
               $("#add").click(function() {
                   $("#add_money").fadeOut(10, function() {
                      $("#success1").fadeIn(10,function(){
                          setTimeout(function(){location.href="wallet.html";}, 3000);
                   });
                 });
               });
            });
         });
      });
    });
  </script>
  <script>
  function sync() {
    var a = document.getElementById('enter_amount').value;
    document.getElementById('net_amount').value = a - (a * (0.02));
  }
  </script>
</head>DBS WALLET 

<body>

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
              <li><a href="profile">Profile</a>
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
                <span class="user-block-name item-text">James Franky</span>
                <span class="user-block-role"><i class="fa fa-check text-green"></i> Verified</span>
                <div class="label label-primary"><i class="fa fa-chevron-down"></i> Account Info</div>
              </div>
            </div>
            <ul class="nav collapse">
              <li><a onclick="location.href='market#open_orders'">Open Orders</a>
              </li>
              <li><a onclick="location.href='market#history'">Transactions History</a>
              </li>
              <li><a onclick="location.href='market#market'">Market History</a>
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
              <a onclick="location.href='market#order_book'" title="Dashboard" class="">
                        <em class="fa fa-gear"></em>
                        <span class="item-text">Order Book</span>
                     </a>
            </li>
            <li>
              <a title="Pages" onclick="location.href='market#history'" href="#history">
                     <em class="fa fa-file-text"></em>
                     <span class="item-text">My Order History</span>
                     </a>

            </li>
           
        </ul>
      </nav>
    </aside>

    <section style="padding-top:30px">
      <section id="whole_page" class="main-content">

        <div class="row margin-left-right-0">
          <div class="col-lg-12 col-md-12 col-sm-12 margin-bottom-10">
            <div class="row wallets-container white-background card">
              <div class="col-lg-6 col-md-6 col-sm-6 header">
                <table>
                  <tbody>
                    <tr>
                      <td>
                        <img src="images/moneybag.png" style="width: 80px;padding: 12px;">
                      </td>
                      <td>
                        <b style="font-size:15px;color:black">DBS WALLET</b>
                        <br>
                        <span>PORTFOLIO VALUE :</span>
                        <span ng-class="{'highIndicator_without_background':vm.portfolio.portfolio_flag == 1,'lowIndicator_without_background':vm.portfolio.portfolio_flag != 1}" style="font-size:16px;" class="ng-binding lowIndicator_without_background">
                        <span class="inrLabel">INR</span> 0.00
                        <!-- ngIf: vm.portfolio.portfolio_flag == 1 -->
                        <!-- ngIf: vm.portfolio.portfolio_flag == -1 -->
                        </span>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div class="col-md-6">
                <table class="float-right">
                  <tbody>

                  </tbody>
                </table>
              </div>
              <div class="col-lg-12">
                <table class="table">
                  <thead>
                    <tr>
                      <th>
                        <span ng-click="vm.sortmethod('currency_type')" style="cursor:pointer;color:black;margin-right:10px">
                            COMPANY&nbsp;
                        </span>
                      </th>
<!--                       <th> -->
<!--                         <span ng-click="vm.sortmethod('total_amount')" style="cursor:pointer;color:black;margin-right:60px"> -->
<!--                                BUY/SELL&nbsp; -->
<!--                         </span> -->
<!--                       </th> -->
                      <th>
                        <span ng-click="vm.sortmethod('reserved_amount')" style="cursor:pointer;color:black;margin-right:40px">
                             PRICE&nbsp;
                        </span>
                      </th>
                      <th>
                        <span ng-click="vm.sortmethod('amount')" style="cursor:pointer;color:black">
                              QUANTITY&nbsp;
                        </span>
                      </th>
                      <!-- <th class="center" style="color:black">TRANSACT</th> -->
                    </tr>
                  </thead>
                  <tbody>
                  <%
                  HttpSession session2 = request.getSession();
                  List<UserStockInfo> histories = (List<UserStockInfo>) request.getAttribute("history");
                  for(UserStockInfo temp:histories){
                  %>
                  <tr>
                  
<td>
<div style="font-size:16px;" class="ng-binding">
<%=temp.getSecurityName() %>
</div>
</td>
<!-- <td> -->
<!-- <!-- <div style="font-size:16px;" class="ng-binding"> --> -->
<%-- <%-- <% --%>
	
	
<%-- <%-- %> --%>
<!-- <!-- </div> --> 
<!-- </td> -->
<td>
<div style="font-size:16px;margin-right:20px" class="ng-binding">
	<%=temp.getTotalPrice() %>
</div>
</td>
<td>
<div style="font-size:16px;margin-right:20px" class="ng-binding">
	<%=temp.getTotalQuantity() %>
</div>
</td>
<td>
<div class="col-md-6 text-align-center customtooltip">
<a class="link newtooltip"  style="position:relative;width:80px;text-align-left;" href="company?id=<%=temp.getSecurityCode()%>&stock=<%=temp.getSecurityName()%>">
<span class="fa tooltipinput buyColor fa-check-circle-o"></span>

<b>BUY</b>
</a>
</div>
<div class="col-md-6 text-align-center customtooltip">
<a class="link newtooltip" style="position:relative;width:110px;text-align-left;" href="company?id=<%=temp.getSecurityCode()%>&stock=<%=temp.getSecurityName()%>">
<span class="fa tooltipinput buyColor fa-check-circle-o"></span>

<b ng-class="{'secondSellColor':wallet.withdrawal_freezed}">SELL</b>
</a>
</div>
</td>
</tr>
<%
}
%>

                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>


      </section>
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
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.payment/1.2.3/jquery.payment.min.js"></script>
    <script type="text/javascript" src="https://js.stripe.com/v2/"></script>
    <script type="text/javascript" src="js/payment.js"></script>
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
    </script>
</body>

</html>
