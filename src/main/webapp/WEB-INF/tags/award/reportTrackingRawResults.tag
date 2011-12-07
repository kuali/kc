<%--
 Copyright 2005-2010 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

			<c:if test="${reqSearchResultsActualSize>0}">
				<c:out value="${reqSearchResultsActualSize}" /> items found.  Please refine your search criteria to narrow down your search.
            </c:if>
			<c:if test="${!empty reqSearchResultsActualSize }">
			    <c:if test="${KualiForm.searchUsingOnlyPrimaryKeyValues}">
			    	<bean-el:message key="lookup.using.primary.keys" arg0="${KualiForm.primaryKeyFieldLabels}"/>
			    	<br/><br/>
			    </c:if>
			    <c:if test="${!empty reqSearchResults && !KualiForm.hasReturnableRow && KualiForm.formKey!='' && KualiForm.hideReturnLink!=true && !KualiForm.multipleValues}">
    				<bean-el:message key="lookup.no.returnable.rows" />
    				<br/><br/>
    			</c:if>
				<display:table class="datatable-100" cellspacing="0"
				requestURIcontext="false" cellpadding="0" name="${reqSearchResults}"
				id="row" export="true" pagesize="100" varTotals="totals" 
				excludedParams="methodToCall reqSearchResultsActualSize searchResultKey searchUsingOnlyPrimaryKeyValues actionUrlsExist" 
				requestURI="reportTrackingLookup.do?methodToCall=viewResults&reqSearchResultsActualSize=${reqSearchResultsActualSize}&searchResultKey=${searchResultKey}&searchUsingOnlyPrimaryKeyValues=${KualiForm.searchUsingOnlyPrimaryKeyValues}&actionUrlsExist=${KualiForm.actionUrlsExist}">

				<%-- the param['d-16544-e'] parameter below is NOT null when we are in exporting mode, so this check disables rendering of return/action URLs when we are exporting to CSV, excel, xml, etc. --%>
				<c:if test="${param['d-16544-e'] == null}">
					<logic:present name="KualiForm" property="formKey">
						<c:if test="${KualiForm.formKey!='' && KualiForm.hideReturnLink!=true && !KualiForm.multipleValues && !empty KualiForm.backLocation}">
							<c:choose>
								<c:when test="${row.rowReturnable}">    
									<display:column class="infocell" media="html" title="Return Value">${row.returnUrl}</display:column>
								</c:when>
								<c:otherwise>
									<display:column class="infocell" media="html" title="Blank">&nbsp;</display:column>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${KualiForm.actionUrlsExist==true && KualiForm.suppressActions!=true && !KualiForm.multipleValues && KualiForm.showMaintenanceLinks}">
							<c:choose>
								<c:when test="${row.actionUrls!=''}">
									<display:column class="infocell" property="actionUrls"
										title="Actions" media="html" />
								</c:when>
								<c:otherwise>
									<display:column class="infocell"
										title="Actions" media="html">&nbsp;</display:column>
								</c:otherwise>
							</c:choose>
						</c:if>
					</logic:present>
				</c:if>

                <%-- needed for total line display --%>
                <c:set var="columnNums" value=""/>
                <c:set var="totalColumnNums" value=""/>
                
				<c:forEach items="${row.columns}" var="column" varStatus="loopStatus">
                    <c:set var="colClass" value="${ fn:startsWith(column.formatter, 'org.kuali.rice.kns.web.format.CurrencyFormatter') ? 'numbercell' : 'infocell' }" />
              
                    <c:if test="${!empty columnNums}" >
                      <c:set var="columnNums" value="${columnNums},"/>
                    </c:if>
                    <c:set var="columnNums" value="${columnNums}column${loopStatus.count}"/>
                    
                    <c:set var="staticColumnValue" value="${column.propertyValue}" />
                    <c:if test="${column.total}" >
                      <c:set var="staticColumnValue" value="${column.unformattedPropertyValue}" />
                      
                      <c:if test="${!empty totalColumnNums}" >
                        <c:set var="totalColumnNums" value="${totalColumnNums},"/>
                      </c:if>
                      <c:set var="totalColumnNums" value="${totalColumnNums}column${loopStatus.count}"/>
                    </c:if>
                    
					<c:choose>
						<%--NOTE: Check if exporting first, as this should be outputted without extra HTML formatting --%>
						<c:when	test="${param['d-16544-e'] != null}">
								<display:column class="${colClass}" sortable="${column.sortable}"
									title="${column.columnTitle}" comparator="${column.comparator}" total="${column.total}" value="${staticColumnValue}" 
									maxLength="${column.maxLength}"><c:out value="${column.propertyValue}" escapeXml="false" default="" /></display:column>
						</c:when>
						<c:when	test="${!empty column.columnAnchor.href || column.multipleAnchors}">
							<display:column class="${colClass}" sortable="${column.sortable}" title="${column.columnTitle}" comparator="${column.comparator}"  >
<c:choose><c:when test="${column.multipleAnchors}"><c:set var="numberOfColumnAnchors" value="${column.numberOfColumnAnchors}" /><c:choose>
<c:when test="${empty columnAnchor.target}"><c:set var="anchorTarget" value="_blank" /></c:when><c:otherwise><c:set var="anchorTarget" value="${columnAnchor.target}" /></c:otherwise></c:choose>
<!-- Please don't change formatting of this logic:iterate block -->
<logic:iterate id="columnAnchor" name="column" property="columnAnchors" indexId="ctr"><a href="<c:out value="${columnAnchor.href}"/>" target='<c:out value="${columnAnchor.target}"/>' title="${columnAnchor.title}"><c:out value="${fn:substring(columnAnchor.displayText, 0, column.maxLength)}" escapeXml="${column.escapeXMLValue}"/><c:if test="${column.maxLength gt 0 && fn:length(columnAnchor.displayText) gt column.maxLength}">...</c:if></a><c:if test="${ctr lt numberOfColumnAnchors-1}">,&nbsp;</c:if></logic:iterate>
</c:when><c:otherwise><c:choose><c:when test="${empty column.columnAnchor.target}"><c:set var="anchorTarget" value="_blank" /></c:when><c:otherwise><c:set var="anchorTarget" value="${column.columnAnchor.target}" />
</c:otherwise></c:choose><a href="<c:out value="${column.columnAnchor.href}"/>" target='<c:out value="${anchorTarget}"/>' title="${column.columnAnchor.title}"><c:out value="${fn:substring(column.propertyValue, 0, column.maxLength)}" escapeXml="${column.escapeXMLValue}"/><c:if test="${column.maxLength gt 0 && fn:length(column.propertyValue) gt column.maxLength}">...</c:if></a>
</c:otherwise></c:choose></display:column>
						</c:when>
<%--NOTE: DO NOT FORMAT THIS FILE, DISPLAY:COLUMN WILL NOT WORK CORRECTLY IF IT CONTAINS LINE BREAKS --%>
						<c:otherwise>
							<display:column class="${colClass}" sortable="${column.sortable}"
								title="${column.columnTitle}" comparator="${column.comparator}" total="${column.total}" value="${staticColumnValue}" 
								maxLength="${column.maxLength}" decorator="org.kuali.rice.kns.web.ui.FormatAwareDecorator"><c:out value="${column.propertyValue}"/>&nbsp;</display:column>
                        </c:otherwise>
					</c:choose>
				</c:forEach>

               <%-- block for displaying the total line --%>
               <c:if test="${!empty totalColumnNums}" >
                 <display:footer>
  	               <tr>
  	                 <c:forTokens var="colNum" items="${columnNums}" delims="," varStatus="loopStatus">
  	                   <c:set var="isTotalColumn" value="false"/>
  	                   
  	                   <c:forTokens var="totalNum" items="${totalColumnNums}" delims="," >
  	                     <c:if test="${totalNum eq colNum}">
  	                       <c:set var="isTotalColumn" value="true"/>
  	                     </c:if>
  	                   </c:forTokens>
  	                   
  	                   <c:choose>
  	                    <c:when test="${isTotalColumn}" >
  		                  <td><b><fmt:formatNumber type="currency" value="${totals[colNum]}"/></b></td>
  		                </c:when>
  		                <c:otherwise>
  		                  <td>
  		                    <c:if test="${loopStatus.first}">
  		                      <b><bean-el:message key="lookup.total.row.label" /></b>
  		                    </c:if>
  		                  </td>
  		                </c:otherwise>
  		               </c:choose> 
  		              
  		             </c:forTokens>
                   </tr>
                </display:footer>
              </c:if>
              
			</display:table>
		 </c:if></td>
			<td width="1%"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20"
				height="20"></td>
		</tr>
	</table>
