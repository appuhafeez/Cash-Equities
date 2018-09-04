<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-stariped table-bordered table-hover">
	<thead>
		<tr>
			<th>Security</th>
			<th>Date</th>
			<th>Type</th>
			<th>Price</th>
			<th>Quantity</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ordersList}" var="order" varStatus="status">
			<!-- BCH Starts Here -->
			<tr class="clickable-row" data-href="index.html">
				<td class="tableSmallPad">${order.securityName}</td>
				<td class="tableSmallPad">${order.timeStamp}</td>
				<td class="tableSmallPad">${order.tradeType}</td>
				<td class="tableSmallPad">${order.priceOfSecurity}</td>
				<td class="tableSmallPad">${order.quantity}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>