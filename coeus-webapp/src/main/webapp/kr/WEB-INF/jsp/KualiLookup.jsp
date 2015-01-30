<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="tldHeader.jsp"%>
<%@ page import="org.kuali.rice.krad.util.KRADConstants"%>

<%--NOTE: DO NOT FORMAT THIS FILE, DISPLAY:COLUMN WILL NOT WORK CORRECTLY IF IT CONTAINS LINE BREAKS --%>
<c:set var="headerMenu" value="" />
<c:set var="boClassName" value="${KualiForm.lookupable.businessObjectClass.name}"/>

<!-- KCIU Customization Starts 
Suppresses the Create New button on the top right corner-->
<c:if test="${KualiForm.suppressActions!=true and KualiForm.supplementalActionsEnabled!=true}">
    <c:if test="${not fn:contains(boClassName,'org.kuali.kra.bo.Rolodex')}">
    <c:set var="headerMenu" value="${KualiForm.lookupable.createNewUrl}   ${KualiForm.lookupable.htmlMenuBar}" />
    </c:if>
</c:if>
<!-- KCIU Customization Ends -->
<c:if test="${!empty KualiForm.backLocation}">
    <c:choose>
     <c:when test="${fn:contains(KualiForm.backLocation,'?')}">
      <c:set var="backLocation" value="${KualiForm.backLocation}&" />
     </c:when>
     <c:otherwise>
      <c:set var="backLocation" value="${KualiForm.backLocation}?" />
     </c:otherwise>
    </c:choose>
    <c:if test="${!fn:contains(backLocation,'methodToCall')}">
      <c:set var="backLocation" value="${backLocation}methodToCall=refresh&" />
    </c:if>
</c:if>

<c:set var="numberOfColumns" value="${KualiForm.numColumns}" />
<c:set var="headerColspan" value="${numberOfColumns * 2}" />


<kul:page lookup="true" showDocumentInfo="false"
	headerMenuBar="${headerMenu}"
	headerTitle="Lookup" docTitle="" transactionalDocument="false"
	htmlFormAction="lookup" >

	<SCRIPT type="text/javascript">
      var kualiForm = document.forms['KualiForm'];
      var kualiElements = kualiForm.elements;
    </SCRIPT>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/DocumentTypeService.js"></script>
	
	<c:if test="${KualiForm.headerBarEnabled}">
	<div class="headerarea-small" id="headerarea-small">
		<h1><c:out value="${KualiForm.lookupable.title}" /> <c:choose>
			<c:when test="${KualiForm.fields.documentTypeName != null}">
				<%-- this is a custom doc search --%>
				<kul:help searchDocumentTypeName="${KualiForm.fields.documentTypeName}" altText="lookup help" />
			</c:when>
			<c:otherwise>
				<%-- KNS looup --%>
				<kul:help lookupBusinessObjectClassName="${KualiForm.lookupable.businessObjectClass.name}" altText="lookup help" />
			</c:otherwise>
		</c:choose></h1>
	</div>
	</c:if>
	
	<c:if test="${KualiForm.renderSearchButtons}">
	  <kul:enterKey methodToCall="search" />
	</c:if>  

	<html-el:hidden name="KualiForm" property="backLocation" />
	<html-el:hidden name="KualiForm" property="formKey" />
	<html-el:hidden name="KualiForm" property="lookupableImplServiceName" />
	<html-el:hidden name="KualiForm" property="businessObjectClassName" />
	<html-el:hidden name="KualiForm" property="conversionFields" />
	<html-el:hidden name="KualiForm" property="hideReturnLink" />
	<html-el:hidden name="KualiForm" property="suppressActions" />
	<html-el:hidden name="KualiForm" property="multipleValues" />
	<html-el:hidden name="KualiForm" property="lookupAnchor" />
	<html-el:hidden name="KualiForm" property="readOnlyFields" />
	<html-el:hidden name="KualiForm" property="referencesToRefresh" />
	<html-el:hidden name="KualiForm" property="hasReturnableRow" />
	<html-el:hidden name="KualiForm" property="docNum" />
	<html-el:hidden name="KualiForm" property="showMaintenanceLinks" />
	<html-el:hidden name="KualiForm" property="headerBarEnabled" />
    <html-el:hidden name="KualiForm" property="fieldNameToFocusOnAfterSubmit"/>


	<c:if test="${KualiForm.headerBarEnabled}">
	<c:forEach items="${KualiForm.extraButtons}" varStatus="status">
		<html-el:hidden name="KualiForm" property="extraButtons[${status.index}].extraButtonSource" />
		<html-el:hidden name="KualiForm" property="extraButtons[${status.index}].extraButtonParams" />
	</c:forEach>
		<c:if test="${KualiForm.supplementalActionsEnabled==true}" >
		<div class="lookupcreatenew" title="Supplemental Search Actions" style="padding: 3px 30px 3px 300px;">
			${KualiForm.lookupable.supplementalMenuBar} &nbsp;
			<c:set var="extraField" value="${KualiForm.lookupable.extraField}"/>
			<c:if test="${not empty extraField}">
				<%--has to be a dropdown script for now--%>
				<c:if test="${extraField.fieldType eq extraField.DROPDOWN_SCRIPT}">

                            	${kfunc:registerEditableProperty(KualiForm, extraField.propertyName)}
                                <select id='${extraField.propertyName}' name='${extraField.propertyName}'
                                        onchange="${extraField.script}" style="">
                                    <kul:fieldSelectValues field="${extraField}"/>
                                </select>

						&nbsp;

							<kul:fieldShowIcons isReadOnly="${true}" field="${extraField}" addHighlighting="${true}" />

				</c:if>
			</c:if>
		</div>
	</c:if>
	
	<div class="right">
		<div class="excol">
		* required field
		</div>
	</div>
    <div class="msg-excol">
      <div class="left-errmsg">
        <kul:errors errorTitle="Errors found in Search Criteria:" />
        <kul:messages/>
	  </div>
    </div>
    <br/>
    </c:if>

	<table width="100%">
	  <c:if test="${KualiForm.lookupCriteriaEnabled}">
		<tr>
			<td width="1%"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20"
				height="20"></td>
			<td>

			<div id="lookup" align="center"><br />
			<br />
			<table align="center" cellpadding="0" cellspacing="0" class="datatable-100">
				<c:set var="FormName" value="KualiForm" scope="request" />
				<c:set var="FieldRows" value="${KualiForm.lookupable.rows}" scope="request" />
				<c:set var="ActionName" value="Lookup.do" scope="request" />
				<c:set var="IsLookupDisplay" value="true" scope="request" />
				<c:set var="cellWidth" value="50%" scope="request" />

                <kul:rowDisplay rows="${FieldRows}" skipTheOldNewBar="true" numberOfColumns="${numberOfColumns}" />

				<tr align="center">
					<td height="30" colspan="${headerColspan}"  class="infoline">
					
					<c:if test="${KualiForm.renderSearchButtons}">
					  <html:image
						property="methodToCall.search" value="search"
						src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_search.gif" styleClass="tinybutton"
						alt="search" title="search" border="0" /> 
					  <html:image
						property="methodToCall.clearValues" value="clearValues"
						src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_clear.gif" styleClass="tinybutton"
						alt="clear" title="clear" border="0" /> 
					</c:if>	
					
					<c:if test="${KualiForm.formKey!=''}">
						<c:if test="${!empty KualiForm.backLocation}"><a
							href='<c:out value="${backLocation}docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" />'  title="cancel"><img
							src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" class="tinybutton" alt="cancel" title="cancel"
							border="0" /></a></c:if>
					</c:if>
					
					<!-- Optional extra buttons -->
					<c:forEach items="${KualiForm.extraButtons}" var="extraButton" varStatus="status">
						<c:if test="${!empty extraButton.extraButtonSource && !empty extraButton.extraButtonParams}">
							<c:if test="${not KualiForm.ddExtraButton && !empty KualiForm.backLocation}">
								<a href='<c:out value="${backLocation}refreshCaller=kualiLookupable&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" /><c:out value="${extraButton.extraButtonParams}" />'><img
							    	src='<c:out value="${extraButton.extraButtonSource}" />'
									class="tinybutton" border="0" /></a>
							</c:if>
							<c:if test="${KualiForm.ddExtraButton}">
								<html:image src="${extraButton.extraButtonSource}" styleClass="tinybutton" property="methodToCall.customLookupableMethodCall" alt="${extraButton.extraButtonAltText}" onclick="${extraButton.extraButtonOnclick}"/> &nbsp;&nbsp;
							</c:if>
						</c:if>

					</c:forEach>
					<c:if test="${KualiForm.multipleValues && !empty KualiForm.backLocation}">
						<a
							href='<c:out value="${backLocation}docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" />'>
						<img src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_retnovalue.gif" class="tinybutton"
							border="0" /></a>
						<a
							href='<c:out value="${backLocation}docFormKey=${KualiForm.formKey}&refreshCaller=multipleValues&searchResultKey=${searchResultKey}&searchResultDataKey=${searchResultDataKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}"/>'>
						<img src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_returnthese.gif" class="tinybutton"
							border="0" /></a>
					</c:if>
					</td>
				</tr>
			  </c:if>
			</table>
			</div>

			<br>
			
			<!-- KCIU Customization Starts -->
			<c:set var="adminEmail" value='<%=org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator.getParameterService().getParameterValueAsString("KC-GEN", "All", "LOOKUP_CONTACT_EMAIL")%>' />
			<c:if test="${!empty reqSearchResultsActualSize}" >			
    			<c:choose>
						<c:when test="${fn:contains(boClassName,'org.kuali.kra.bo.Rolodex') or fn:contains(boClassName,'org.kuali.kra.bo.NonOrganizationalRolodex')}">
    			    			<!--  Cannot create this by parsing the createNewUrl because NonOrganizationalRolodex is not a maint doc and does not have one-->
    				  			Can't find what you are looking for? Click <a href="${KRADConstants.MAINTENANCE_ACTION}?businessObjectClassName=org.kuali.kra.bo.Rolodex&methodToCall=start" target="_blank">here</a> to add a new Address Book entry
    				  			or contact the <a href="mailto:${adminEmail}">System Administrator</a> to add one.
    				  			<br>
    				  			<br>
						</c:when>
				</c:choose>
			</c:if>
				
			<!-- KCIU Customization Ends -->
			
			<br>

			<c:if test="${reqSearchResultsActualSize > reqSearchResultsLimitedSize && reqSearchResultsLimitedSize>0}">
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
				requestURI="lookup.do?methodToCall=viewResults&reqSearchResultsActualSize=${reqSearchResultsActualSize}&searchResultKey=${searchResultKey}&searchUsingOnlyPrimaryKeyValues=${KualiForm.searchUsingOnlyPrimaryKeyValues}&actionUrlsExist=${KualiForm.actionUrlsExist}">

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
                    <c:set var="colClass" value="${ fn:startsWith(column.formatter, 'org.kuali.rice.krad.web.format.CurrencyFormatter') ? 'numbercell' : 'infocell' }" />
              
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
</kul:page>
