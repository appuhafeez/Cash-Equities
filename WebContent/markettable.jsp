<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="datatable1" class="table table-striped table-hover">
                                          <thead>
                                             <tr>
                                                <th class="tableSmallPad">Name</th>
                                                <th class="tableSmallPad">Price</th>
                                                <th class="tableSmallPad">Vol</th>
                                                <th class="tableSmallPad">Change</th>
                                                <th class="tableSmallPad">Code</th>
                                             </tr>
                                          </thead>
                                          <tbody>
                                          <c:forEach items="${securityList}" var="security" varStatus="status">
                                             <!-- BCH Starts Here -->
                                             <tr class="clickable-row" data-href="market.jsp" onclick="window.location='company?id=${security.securityCode}&stock=${security.securityName}'">
                                                <td class="tableSmallPad">
                                                   <!--RADIO 1-->
                                                   <input type="radio" class="radio_item" value=${ security.securityName} name=${ security.securityName} id="radio1">
                                                   <label class="label_item" for="radio1"> <i class="fa fa-star text-c-blue"></i> </label> ${ security.securityName}
                                                </td>
                                                <td class="tableSmallPad">${security.priceOfSecurity}</td>
                                                <td class="tableSmallPad">${security.quantityAvailable}</td>
                                                <td class="text-c-blue tableSmallPad">${security.changedPrice}</td>
                                                <td class="tableSmallPad">${security.symbol}</td>
                                             </tr>
                                             <!-- BCH Ends Here -->
                                             </c:forEach>
                                          </tbody>
                             </table>

