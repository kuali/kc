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
<%-- member of awardPaymentAndInvoices.tag --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<jsp:useBean id="paramMap1" class="java.util.HashMap"/>
<jsp:useBean id="paramMap2" class="java.util.HashMap"/>


<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="document" value = "${KualiForm.document}"/>
<c:set target="${paramMap2}" property = "basisOfPaymentCode" value = "${document.awardList[0].basisOfPaymentCode}"/>


<h3>
	<span class="subhead-left">Payment &amp; Invoice</span>
	<span class="subhead-right">
	  	<span class="subhead-right"><kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardPaymentsandInvoicesHelpUrl" altText="help"/></span>
  	</span>	
</h3>

<table border="0" cellpadding="0" cellspacing="0" summary="">
	<tr>
		<th width="20%">
			<div align="right">
				<kul:htmlAttributeLabel attributeEntry="${awardAttributes.basisOfPaymentCode}"/> 
			</div>
		</th>
		<td width="30%" valign="middle">
			<div align="left">
                
                <html:select property="document.awardList[0].basisOfPaymentCode" tabindex="0" onchange="javascript: loadAwardMethodOfPaymentCodes('document.awardList[0].basisOfPaymentCode','document.awardList[0].methodOfPaymentCode'); return false;" disabled="${readOnly}" >                                              
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.ValidAwardBasisPaymentValueFinder', paramMap1)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${document.awardList[0].basisOfPaymentCode == option.key}">
	                        <option value="${option.key}" selected>${option.value}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.value}"/>
	                        <option value="${option.key}">${option.value}</option>
	                    </c:otherwise>
	                </c:choose>         
	            </c:forEach>
	            </html:select>
                <kul:checkErrors keyMatch="document.awardList[0].paymentsAndInvoices.awardBasisOfPaymentCode" auditMatch="document.awardList[0].paymentsAndInvoices.awardBasisOfPaymentCode"/>
                <c:if test="${hasErrors}">
                    <kul:fieldShowErrorIcon />
                </c:if>
	            <html:image property="methodToCall.refreshPulldownOptions" styleClass="tinybutton" 
	            	src='${ConfigProperties.kra.externalizable.images.url}arrow_refresh.png'/>
                <%-- <kul:htmlControlAttribute property="document.awardList[0].basisOfPaymentCode" attributeEntry="${awardAttributes.basisOfPaymentCode}" /> --%>
			</div>
		</td>
		<th width="20%">
			<div align="right">
				<kul:htmlAttributeLabel attributeEntry="${awardAttributes.methodOfPaymentCode}"  />
			</div>
		</th>
		<td width="30%" valign="middle">
			<div align="left" onLoad = "javascript: loadAwardBasisOfPaymentCodes('${document.awardList[0].awardTypeCode}', 'document.awardList[0].basisOfPaymentCode');return false;">
				<html:select property="document.awardList[0].methodOfPaymentCode" tabindex="0" disabled="${readOnly}">                                              
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.ValidMethodOfPaymentValuesFinder', paramMap2)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${document.awardList[0].methodOfPaymentCode == option.key}">
	                        <option value="${option.key}" selected>${option.value}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.value}"/>
	                        <option value="${option.key}">${option.value}</option>
	                    </c:otherwise>
	                </c:choose>         
	            </c:forEach>
	            </html:select>
                <kul:checkErrors keyMatch="document.awardList[0].paymentsAndInvoices.awardMethodOfPaymentCode" auditMatch="document.awardList[0].paymentsAndInvoices.methodOfPaymentCode"/>
                <c:if test="${hasErrors}">
                    <kul:fieldShowErrorIcon />
                </c:if>
	            <%-- <html:image property="methodToCall.refreshPulldownOptions" styleClass="tinybutton" 
	            	src='${ConfigProperties.kra.externalizable.images.url}arrow_refresh.png'/> --%>
                <%-- <kul:htmlControlAttribute property="document.awardList[0].methodOfPaymentCode" attributeEntry="${awardAttributes.methodOfPaymentCode}" /> --%>
			</div>
		</td>
	</tr>
	<tr>
		<th width="25%">
			<div align="right">
				<kul:htmlAttributeLabel attributeEntry="${awardAttributes.documentFundingId}"  />
			</div>
		</th>
		<td width="25%" valign="middle">
			<div align="left">
                <kul:htmlControlAttribute property="document.awardList[0].documentFundingId" attributeEntry="${awardAttributes.documentFundingId}" />
			</div>
		</td>
				<th width="25%">
			<div align="right">
			</div>
		</th>
		<td width="25%" valign="middle">
			<div align="left">
			</div>
		</td>
		
	</tr>
</table>
