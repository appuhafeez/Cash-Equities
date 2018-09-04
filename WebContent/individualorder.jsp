<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-bordered table-hover" id="open_orders_table">
	<thead>
		<tr>
			<th><i class="fa fa-plus"></i></th>
			<th>SecurityCode</th>
			<th>Security</th>
			<th>Date</th>
			<th>Type</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Total Cost</th>
			<th>Status</th>
			<th><i class="fa fa-times"></i></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ordersList}" var="order" varStatus="status">
			<!-- BCH Starts Here -->
			<tr class="clickable-row" data-href="index.html">
				<td><i class="fa fa-minus"></i></td>
				<td class="tableSmallPad">${order.securityCode}</td>
				<td class="tableSmallPad">${order.securityName}</td>
				<td class="tableSmallPad">${order.timeStamp}</td>
				<td class="tableSmallPad">${order.tradeType}<br>${order.direction}</td>
				<td class="tableSmallPad">${order.priceOfSecurity}</td>
				<td class="tableSmallPad" id="change">${order.quantity}</td>
				<td class="tableSmallPad">${order.totalPrice}</td>
				<td class="tableSmallPad">${order.status}</td>
				<td><a href="Cancel?id=${order.orderId}"><i class="fa fa-times btn btn-danger"></i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script src="js/bootstable.js"></script>