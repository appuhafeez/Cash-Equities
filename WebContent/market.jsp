<%@page import="com.trade.ShareInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.crud.MarketCRUD"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<link rel="icon" type="image/png" sizes="16x16"
	href="images/favicon-16x16.png">

<title>DBS Stock Trader</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!-- <link rel="stylesheet" href="css/font-awesome.css"> -->
<link rel="stylesheet" href="css/plugins/animate%2banimo.css">
<link rel="stylesheet" href="css/plugins/csspinner.min.css">
<link rel="stylesheet" href="css/app.css">
<link rel="stylesheet" href="css/phpwallet1.css">
<script src="js/modernizr.js"></script>
<script src="js/fastclick.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

<body>
	<div id="overlayLoader">
		<div id="preloader">
			<span></span> <span></span>
		</div>
	</div>


	<section class="wrapper"> <nav
		class="navbar navbar-default navbar-top navbar-fixed-top">
	<div class="navbar-header">
		<a href="market" class="navbar-brand">
			<div class="brand-logo">
				<i> <img src="css/dbslogo.png" height="35px" width="35px"
					style="border-radius: 50%">
				</i> <b style="font-weight: 700; color: #DD1414">STOCK</b>TRADER.com
			</div>
			<div class="brand-logo-collapsed">
				<i><img src="css/dbslogo.png" height="40px" width="40px"
					style="border-radius: 50%; top: 25px"></i>
			</div>
		</a>
	</div>
	<div class="nav-wrapper">
		<ul class="nav navbar-nav mt0">
			<li><a href="#" data-toggle="aside"> <em
					class="fa fa-align-left"></em>
			</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right mt0">
			<li class="dropdown dropdown-list"><a href="walletlist"
				data-toggle="dropdown" data-play="fadeIn" class="dropdown-toggle">
					<strong><i class="fa fa-inr"></i> Wallet</strong>
			</a></li>
			<li class="dropdown dropdown-list"><a href=""
				data-toggle="dropdown" data-play="fadeIn" class="dropdown-toggle">
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
												<div role="progressbar" aria-valuenow="100"
													aria-valuemin="0" aria-valuemax="100"
													class="progress-bar progress-bar-success w-100-p">
													<span class="sr-only">100% Complete</span>
												</div>
											</div>
										</td>
										<td><em class="fa fa-calendar fa-fw text-muted"></em>02/19/2018
										</td>
										<td class="text-center">Complete</td>
									</tr>
									<tr>
										<td>Sell order SC</td>
										<td>
											<div class="progress progress-striped progress-xs">
												<div role="progressbar" aria-valuenow="50" aria-valuemin="0"
													aria-valuemax="100"
													class="progress-bar progress-bar-danger w-50-p">
													<span class="sr-only">50% Complete</span>
												</div>
											</div>
										</td>
										<td><em class="fa fa-calendar fa-fw text-muted"></em>02/18/2018
										</td>
										<td class="text-center">50% Filled</td>
									</tr>
									<tr>
										<td>Buy order IOTA</td>
										<td>
											<div class="progress progress-striped progress-xs">
												<div role="progressbar" aria-valuenow="50" aria-valuemin="0"
													aria-valuemax="100"
													class="progress-bar progress-bar-success w-50-p">
													<span class="sr-only">50% Complete</span>
												</div>
											</div>
										</td>
										<td><em class="fa fa-calendar fa-fw text-muted"></em>02/17/2018
										</td>
										<td class="text-center">50% Filled</td>
									</tr>
								</tbody>
							</table>
						</div>
					</li>
				</ul></li>
			<li class="dropdown"><a href="#" data-toggle="dropdown"
				data-play="fadeIn" class="dropdown-toggle"> <em
					class="fa fa-user"></em>
			</a>
				<ul class="dropdown-menu">
					<li><a href="profile.jsp">Profile</a></li>
					<li class="divider"></li>
					<li><a href="logout">Logout</a></li>
				</ul></li>
		</ul>
	</div>
	</nav> <aside class="aside"> <nav class="sidebar">
	<ul class="nav">
		<li>
			<div data-toggle="collapse-next" class="item user-block has-submenu">
				<div class="user-block-picture">
					<img src="02.jpg" alt="Avatar" width="60" height="60"
						class="img-thumbnail img-circle account-img-mb">
				</div>
				<div class="user-block-info">
					<span class="user-block-name item-text">${username}</span> <span
						class="user-block-role"><i class="fa fa-check text-green"></i>
						Verified</span>
					<div class="label label-primary">
						<i class="fa fa-chevron-down"></i> Account Info
					</div>
				</div>
			</div>
			<ul class="nav collapse">
				<li><a href="#open_orders">Open Orders</a></li>
				<li><a href="#history">Transactions History</a></li>
				<li><a href="#market">Market History</a></li>
				<li><a href="javascript:void(0);"> Verification
						<div class="label label-success pull-right">
							<i class="fa fa-check"></i> Verified
						</div>
				</a></li>
			</ul>
		</li>
		<li class="active"><a href="market"
			onclick="location.href='market'" title="index"
			data-toggle="collapse-next"> <em class="fa fa-home"></em>
				<div class="label pull-right">
					<i class="fa fa-line-chart"></i>
				</div> <span class="item-text">Markets</span>
		</a></li>
		<li><a href="Portfolio" title="Dashboard" class=""> <em
				class="fa fa-btc"></em> <span class="item-text">Portfolio</span>
		</a></li>
		<li class=""><a href="#order_book" title="Dashboard" class="">
				<em class="fa fa-gear"></em> <span class="item-text">Order
					Book</span>
		</a></li>
		<li><a title="Pages" href="#history"> <em
				class="fa fa-file-text"></em> <span class="item-text">My
					Order History</span>
		</a></li>
		
	</ul>
	</nav> </aside> <section> <!--Scroll ticker --> <iframe
		allowtransparency="true" id="macroaxis_stock_ticker"
		name="macroaxis_stock_ticker" marginheight="0" marginwidth="0"
		scrolling="NO" height="31px" width="100%" frameborder="0"
		src="ticker.html"></iframe> <!--<jsp:include page="/ticker" />--> <!-- ticker close-->

	<section class="main-content">

	<h3>Dashboard</h3>
	<div class="row">
		<div class="col-md-12">
			<!-- First Row Starts Here -->
			<div class="row">
				<div class="col-lg-3 col-sm-6">
					<div data-toggle="play-animation" data-play="fadeInDown"
						data-offset="0" data-delay="100" class="panel widget">
						<div class="panel-body bg-primary">
							<div class="row row-table row-flush">
								<div class="col-xs-12">
									<p class="mb0">
										<span class="fa fa-inr">759,781</span> <em class="fa fa-level-up"> </em>
									</p>
									<h4 class="m0">Market Cap</h4>
									<span class="m-t-10"><i class="fa fa-inr"></i> Total
										Market Capital </span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div data-toggle="play-animation" data-play="fadeInDown"
						data-offset="0" data-delay="500" class="panel widget">
						<div class="panel-body bg-warning">
							<div class="row row-table row-flush">
								<div class="col-xs-12">
									<p class="mb0">
										<span class="fa fa-inr">1,947</span> <em class="fa fa-level-down"></em>
									</p>
									<h4 class="m0">Trade Volume</h4>
									<span class="f-left m-t-10"> <i class="fa fa-inr"></i>
										24h Trade Volume
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div data-toggle="play-animation" data-play="fadeInDown"
						data-offset="0" data-delay="1000" class="panel widget">
						<div class="panel-body bg-danger">
							<div class="row row-table row-flush">
								<div class="col-xs-12">
									<p class="mb0">
										324 <em class="fa fa-refresh"></em>
									</p>
									<h4 class="m0">Total Bid Orders</h4>
									<span class="m-t-10"> <i
										class="text-c-green f-16 fa fa-refresh"></i> Total Bids
										
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div data-toggle="play-animation" data-play="fadeInDown"
						data-offset="0" data-delay="1500" class="panel widget">
						<div class="panel-body bg-success">
							<div class="row row-table row-flush">
								<div class="col-xs-12">
									<p class="mb0">
										40 <em class="fa fa-money"></em>
									</p>
									<h4 class="m0">Total Sell Orders</h4>
									<span class="f-left m-t-10"> <i class="fa fa-money"></i>
										Total Sell Orders
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- First Row Ends Here -->
			<div class="row">
				<div class="col-lg-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							All Markets <a href="#" data-perform="panel-collapse"
								data-toggle="tooltip" title="Collapse Panel" class="pull-right">
								<em class="fa fa-minus"></em>
							</a>
						</div>
						<div class="panel-wrapper collapse in">
							<div class="panel panel-default">
								<div class="panel-body" id="securitytable">
									<jsp:include page="/markettable" />

								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Top 5 companies -->
				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">Gainers / Losers</div>
						</div>
						<jsp:include page="/gainloss" />
					</div>
					<!-- Coin Status Ends Here -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="pull-right">
								<i class="fa fa-bullhorn"></i>
							</div>
							<div class="panel-title">Upcoming News</div>
						</div>
						<div class="list-group">
							<a href="javascript:void(0);" class="list-group-item">
								<div class="media">
									<div class="media-body">
										<small class="pull-right">2h</small> <strong
											class="media-heading text-primary">Titan Ltd</strong>
										<p>
											<small>Posted by Times of India</small>
										</p>
										<p class="mb-sm">
											<small>We are introducing new customizable designs
												for customers</small>
										</p>
									</div>
								</div>
							</a> <a href="javascript:void(0);" class="list-group-item">
								<div class="media">
									<div class="media-body">
										<small class="pull-right">2h</small> <strong
											class="media-heading text-primary">Bharti Airtel</strong>
										<p>
											<small>Posted by Hindu</small>
										</p>
										<p class="mb-sm">
											<small>Exciting offers for prepaid and postpaid
												customers</small>
										</p>
									</div>
								</div>
							</a>
						</div>
						<div class="panel-footer clearfix">
							<a href="javascript:void(0);" class="pull-left"> <small>Read
									All</small>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Orders Book -->
	<div class="row" id="order_book">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					Order Blotter<a href="javascript:void(0);"
						data-perform="panel-collapse" data-toggle="tooltip" title=""
						class="pull-right" data-original-title="Collapse Panel"> <em
						class="fa fa-minus"></em>
					</a>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-6 col-sm-12">
							<div class="table-responsive ">
								<jsp:include page="/buyorders"/>
							</div>
						</div>
						<div class="col-md-6 col-sm-12">
							<div class="table-responsive">
								<jsp:include page="/sellorders"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Open Orders -->
		<div class="col-md-12" id="open_orders">
			<div class="panel panel-default">
				<div class="panel-heading">
					Open Orders <a href="#open_orders" data-perform="panel-collapse"
						data-toggle="tooltip" title="Collapse Panel" class="pull-right">
						<em class="fa fa-minus"></em>
					</a>
				</div>
				<div class="table-responsive">
					<jsp:include page="/individualorder"></jsp:include>
				</div>
			</div>
		</div>
		<!-- Market History -->
		<div class="col-md-12" id="history">
			<div class="panel panel-default">
				<div class="panel-heading">
					Executed Market History <a href="#" data-perform="panel-collapse"
						data-toggle="tooltip" title="Collapse Panel" class="pull-right">
						<em class="fa fa-minus"></em>
					</a>
				</div>
				<!-- pranav addition-->
				<div class="panel-wrapper collapse in">
					<div class="table-responsive">
						<jsp:include page="/ordertable" />
					</div>
				</div>

			</div>
		</div>
		
	</div>
	</section> </section> </section>
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
	<script src="js/bootstable.js"></script>

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
    			//$('#macroaxis_stock_ticker').fadeIn("slow");
    			$('#securitytable').load("http://localhost:8080/Cash-Equities-Master/markettable").fadeIn("slow");
    	    }, 5000);
    
    
    
    
  </script>
</body>

</html>

