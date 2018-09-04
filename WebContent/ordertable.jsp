<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered  table-striped table-hover">
                                 <thead>
                                    <tr>
                                    	<th>Security</th>
                                       <th>Date</th>
                                       <th>Buy/Sell</th>
                                       <th>Price</th>
                                       <th>Quantity</th>
                                       <th>Total Cost</th>
                                       <th>Status</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                          <c:forEach items="${ordersList}" var="order" varStatus="status">
                                             <!-- BCH Starts Here -->
                                             <tr class="clickable-row" data-href="index.html">                               
                                                <td class="tableSmallPad">${order.securityName}</td>
                                                <td class="tableSmallPad">${order.timeStamp}</td>
                                                <td class="tableSmallPad">${order.direction}</td>
                                                <td class="tableSmallPad">${order.priceOfSecurity}</td>
                                                <td class="tableSmallPad">${order.quantity}</td>
                                                <td class="tableSmallPad">${order.totalPrice}</td>
                                                <td class="tableSmallPad">${order.status}</td>
                                             </tr>
                                             <!-- BCH Ends Here -->
                                             </c:forEach>
                                          </tbody>
                              </table>