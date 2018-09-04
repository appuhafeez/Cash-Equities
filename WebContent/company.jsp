<%@page import="com.trade.ShareDetail"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
  <link rel="icon" type="image/png" sizes="16x16" href="images/favicon-16x16.png">

  <title>DBS Stock Trader</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/plugins/animate%2banimo.css">
  <link rel="stylesheet" href="css/plugins/csspinner.min.css">
  <link rel="stylesheet" href="css/app.css">
  <link rel="stylesheet" href="css/phpwallet1.css">
  <script src="js/modernizr.js"></script>
  <script src="js/fastclick.js"></script>
  <link rel="stylesheet" href="css/tab.css">
  <script src="js/chart.js"></script>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

   <style>
.table5 td{ padding:5px;}
.table5 td.hed{ background:#E8EBEF; font:19px arial;}
.table5 td.det{ font:19px arial; }
   </style>

<script>
  function sync() {
    var a = document.getElementById('su').value;
    var b = document.getElementById('sa').value;
   
    
    document.getElementById('st').value = a*b;
  }
  function sync1() {
    var a = document.getElementById('bu').value;
    var b = document.getElementById('bb').value;
   
    
    document.getElementById('bt').value = a*b;
  }
  </script>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 47%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>

</head>

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
          <li><a href="logout">Logout</a>
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
           
        </ul>
      </nav>
    </aside>



    <section>

      <!--Scroll ticker -->

      <iframe allowtransparency="true" id="macroaxis_stock_ticker" name="macroaxis_stock_ticker" marginheight="0" marginwidth="0" scrolling="NO" height="31px" width="100%" frameborder="0" src="ticker.html"></iframe>

      <!-- ticker close-->
      <br><br>

      <section class="main-content">
             <button style="font-size:15px" type="button" class="btn btn-labeled btn-info pull-right" >
                       <span class="btn-label"><i class="fa fa-bullhorn"></i>
  
                       </span>Available Stocks: 20000</button>
 <div class="row">
<div class="col-md-9">
 <div class="row margin-bottom-10" style="margin-right:-16px;margin-left:-17px">
  <div class="wallets-container card white-background">


   <div> &nbsp;&nbsp;<label><h3>COMPANY NAME :</h3></label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><h3><%=request.getParameter("stock") %></h3></label><br/>
   &nbsp;&nbsp;<label><h4>SYMBOL:</h4></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><%=request.getParameter("id") %></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><h4>ISIN NO:</h4></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>${ISBN}</label></div>
  </div>
</div>

<!-- saasaas -->
<!-- <div class="row">
<div class="col-md-9"> -->
  <div class="row margin-bottom-10" style="margin-right:-17px;margin-left:-17px">
  <div class="wallets-container card white-background">
  <div>
       <center> <b style="font-size:35px"> QUOTE </b></center>
        
  <table style="float:left">
    <tr> 
<th> QUANTITY </th>
<th> PRICE</th>

    </tr>
      <c:forEach items="${buylist}" var="buyval" varStatus="status">

                                             <tr>
                                                <td>${buyval.quantity}</td>
                                                <td>${buyval.priceOfSecurity}</td>
                                             </tr>
                                             </c:forEach>


</table>
<table style="float:right">
    <tr> 
<th> PRICE </th>
<th>QUANTITY</th>

    </tr>
<c:forEach items="${selllist}" var="sellval" varStatus="status">

                                             <tr class="clickable-row " data-href="index.html">
                                                <td class="tableSmallPad">${sellval.priceOfSecurity}</td>
                                                <td class="tableSmallPad">${sellval.quantity}</td>
                                             </tr>

                                             </c:forEach>

</table>
  </div>
</div>
</div> <br>
<div class="row margin-0">
<!-- <div class="col-lg-12"> -->
<div class="row margin-bottom-10" style="margin-right:-5px;margin-left:-5px">
<div class="wallets-container card white-background">
<!-- <div class="row"> -->
<!-- <div class="col-lg-12 col-md-12 col-sm-12 header"> -->

<table style="margin-left:195px">
<tbody>

  <tr>

<td>
  <div class="tab" style="width:100%">
<button class="tablinks" onclick="openCity(event, 'Tokyo')" id="defaultOpen">SELL</button>
<button class="tablinks" onclick="openCity(event, 'Paris')">BUY</button>

</div>
</td>
</tr>
</tbody></table>
<!-- </div> -->
<!-- </div> -->
<div class="row">
  <div class="col-md-12">

   <!-- table start -->

      <div id="Paris" class="tabcontent">
       
<!-- bid price -->
                  <div class="col-lg-6" style="margin-left:180px">
                     <div class="panel panel-default">
                        <div class="panel-heading">Bid Price
                           <a href="#" data-perform="panel-collapse" data-toggle="tooltip" title="Collapse Panel" class="pull-right">
                           <em class="fa fa-minus"></em>
                           </a>
                        </div>
                        <form action="PlaceOrder" method="post">
                        <input type="hidden" name="securityCode" value="<%=request.getParameter("id")%>">
                         <div class="pannel panel-body">
                           <label > UNITS</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" value="" name="quantity" onkeyup="sync1()"  id="bu" >
                                                      <div class="m-t-9">
                              <label >BID</label>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" value="" name="priceOfSecurity" onkeyup="sync1()" id="bb">
                           </div>
                           <div class="row">
                              <div class="btn-group m-t-9 col-md-6 col-sm-12 col-xs-12">
                               <select id="condition">
                                <option name="limit">LIMIT</option>
                                <option name="market">MARKET</option>

                               </select>
                              </div>
                              <div class="btn-group m-t-9 col-md-6 col-sm-12 col-xs-12">
                                  <select id="good_cancel">
                                <option name="till">Good Till Cancelled</option>
                                <option name="Imediate">Imediate</option>

                               </select>                               </div>
                           </div>
                           <div class="m-t-9">
                              <label>Total</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <input type="text" value="" name="atotal" onkeyup="sync1()" id="bt">
                           </div>
                           <div class="m-t-9">
                              <button name="direction" value="buy" type="submit" class="btn btn-primary btn-block"><i class="fa fa-plus"></i> <b style="font-size: 15px">BUY STOCKS</b></button>
                           </div>
                        </div></form>
                     </div>
                  </div>
                  <!-- bid price close-->


      </div>

      <div id="Tokyo" class="tabcontent">
             <!-- ask price -->
                  <div class="col-lg-6" style="margin-left:180px">
                     <div class="panel panel-default">
                        <div class="panel-heading">Ask Price
                           <a href="#" data-perform="panel-collapse" data-toggle="tooltip" title="Collapse Panel" class="pull-right">
                           <em class="fa fa-minus"></em>
                           </a>
                        </div>
                      <form action="PlaceOrder" method="post">
                       <input type="hidden" name="securityCode" value="<%=request.getParameter("id")%>">
                        <div class="pannel panel-body">
                           <label> UNITS</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="number" value="" name="quantity" onkeyup="sync()"  id="su">
                                                      <div class="m-t-9">
                              <label >ASK</label>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="number" value="" name="priceOfSecurity"  id="sa" onkeyup="sync()" >
                           </div>
                           <div class="row">
                              <div class="btn-group m-t-9 col-md-6 col-sm-12 col-xs-12">
                               <select id="condition">
                                <option name="limit">LIMIT</option>
                                <option name="market">MARKET</option>

                               </select>
                              </div>
                              <div class="btn-group m-t-9 col-md-6 col-sm-12 col-xs-12">
                                  <select id="good_cancel">
                                <option name="till">Good Till Cancelled</option>
                                <option name="Imediate">Imediate</option>

                               </select>                               </div>
                           </div>
                           <div class="m-t-9">
                              <label>Total</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <input type="number" value="" name="stotal" onkeyup="sync()" id="st">
                           </div>
                           <div class="m-t-9">
                              <button name="direction" value="sell"  type="submit" class="btn btn-primary btn-block"><i class="fa fa-minus"></i> <b style="font-size: 15px">SELL STOCKS</b></button>
                           </div>
                        </div></form>
                     </div>
                  </div>
                  <!-- ask price close -->
        
      </div>

      <div id="buy" class="tabcontent">
        
<div class="row">

<div class="col-lg-7" style="margin-left:140px">
    <div class="panel panel-default">
      
    </div>
  </div>



<div class="col-lg-7" style="margin-left:140px">
    <div class="panel panel-default">
      
    </div>
  </div>

<!--end ask price-->
</div>

      </div>

      <!-- table close>  -->
</div>

</div>
</div>
</div>
<!-- </div> -->
<!-- sasasa close-->
</div>
</div>


   <!-- Top 5 companies -->
   <div class="col-md-3">
        <div class="panel panel-default">
           <div class="panel-heading">
              <div class="panel-title">Stock Status</div>
           </div>
           <%
           ShareDetail shareDetail=(ShareDetail)request.getAttribute("shareDetails");
           System.out.print(shareDetail.getId());
                    %>
                    
           <div class="list-group">
              <div class="list-group-item">
                 <div class="media">
                    <div class="pull-left">
                       <span class="fa-stack fa-lg">
                       <em class="fa fa-circle fa-stack-2x text-warning"></em>
                       <em class="fa fa-money fa-stack-1x fa-inverse text-white"></em>
                       </span>
                    </div>
                    <div class="media-body clearfix">
                       <strong>Current Price</strong>
                       <p class="m0">
                          <small class="text-green"><%=shareDetail.getOpen() %> <i class="fa fa-money"></i></small>
                       </p>
                       <p><i class="fa fa-inr"></i> <%=shareDetail.getOpen() %></p>
                    </div>
                 </div>
              </div>
              <div class="list-group-item">
                 <div class="media">
                    <div class="pull-left">
                       <span class="fa-stack fa-lg">
                       <em class="fa fa-circle fa-stack-2x text-blue"></em>
                       <em class="fa fa-history fa-stack-1x fa-inverse text-white"></em>
                       </span>
                    </div>
                    <div class="media-body clearfix">
                       <strong>Open Price</strong>
                       <p class="m0">
                         <small class="text-green">${shareDetails.open } <i class="fa fa-money"></i></small>
                       </p>
                       <p><i class="fa fa-inr"></i> <%=shareDetail.getOpen() %></p>
                    </div>
                 </div>
              </div>
              <div class="list-group-item">
                 <div class="media">
                    <div class="pull-left">
                       <span class="fa-stack fa-lg">
                       <em class="fa fa-circle fa-stack-2x text-danger"></em>
                       <em class="fa fa-level-down fa-stack-1x fa-inverse text-white"></em>
                       </span>
                    </div>
                    <div class="media-body clearfix">
                       <strong>Low price</strong>
                       <p class="m0">
                         <small class="text-green">${shareDetails.low } <i class="fa fa-money"></i></small>
                       </p>
                       <p><i class="fa fa-inr"></i> <%=shareDetail.getLow()%> <i class="fa fa-level-down text-danger"></i></p>
                    </div>
                 </div>
              </div>
              <div class="list-group-item">
                 <div class="media">
                    <div class="pull-left">
                       <span class="fa-stack fa-lg">
                       <em class="fa fa-circle fa-stack-2x text-green"></em>
                       <em class="fa fa-level-up fa-stack-1x fa-inverse text-white"></em>
                       </span>
                    </div>
                    <div class="media-body clearfix">
                       <strong>High Price</strong>
                       <p class="m0">
                         <small class="text-green"><%=shareDetail.getHigh() %><i class="fa fa-money"></i></small>
                       </p>
                       <p><i class="fa fa-inr"></i> 0.00 <i class="fa fa-level-up text-green"></i></p>
                    </div>
                 </div>
              </div>
           </div>
        </div>
                    <!-- Coin Status Ends Here -->
                    <div class="panel panel-default">
                       <div class="panel-heading">
                          <div class="pull-right"><i class="fa fa-bullhorn"></i></div>
                          <div class="panel-title">Upcoming News</div>
                       </div>
                       <div class="list-group">
                          <a href="javascript:void(0);" class="list-group-item">
                             <div class="media">
                                <div class="media-body">
                                   <small class="pull-right">2h</small>
                                   <strong class="media-heading text-primary">Titan Ltd</strong>
                                   <p><small>Posted by Times of India</small></p>
                                   <p class="mb-sm">
                                      <small>We are introducing new customizable designs for customers</small>
                                   </p>
                                </div>
                             </div>
                          </a>
                          <a href="javascript:void(0);" class="list-group-item">
                             <div class="media">
                                <div class="media-body">
                                   <small class="pull-right">2h</small>
                                   <strong class="media-heading text-primary">Bharti Airtel</strong>
                                   <p><small>Posted by Hindu</small></p>
                                   <p class="mb-sm">
                                      <small>Exciting offers for prepaid and postpaid customers</small>
                                   </p>
                                </div>
                             </div>
                          </a>
                       </div>
                       <div class="panel-footer clearfix">
                          <a href="javascript:void(0);" class="pull-left">
                          <small>Read All</small>
                          </a>
                       </div>
                    </div>
                 </div>
   <!-- top 5 companies close-->
      </div>





      </section>
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
  <script src="js/tab.js"></script>
  <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

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
