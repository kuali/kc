<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="htmlFormAction" required="false" %>
<%@ attribute name="renderMultipart" required="false" %>
<c:set var="subAwardAmountReleasedAttributes" value="${DataDictionary.SubAwardAmountReleased.attributes}" />
<c:set var="action" value="subAwardFinancial" />
<c:set var="newSubAwardAmountReleased" value="${KualiForm.newSubAwardAmountReleased}" />
<c:set var="subAwardAttributes" value="${DataDictionary.SubAward.attributes}" />
<kul:tab tabTitle="Invoices" defaultOpen="true" transparentBackground="false" tabErrorKey="newSubAwardAmountReleased.invoiceNumber*,newSubAwardAmountReleased.startDate*,newSubAwardAmountReleased.endDate*,newSubAwardAmountReleased.effectiveDate*,newSubAwardAmountReleased.amountReleased*,newSubAwardAmountReleased.comments*,document.subAwardList[0].subAwardAmountReleasedList*" auditCluster="" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
	<script>
		jq(document).ready(function() {
			jq('input[name*="downloadInvoiceAttachment"]').hide();
			jq('input[name*="downloadInvoiceAttachment"]').click(function() {excludeSubmitRestriction = true});
			jq('a.attachmentLink').click(function() { jq(this).siblings('input').click(); });
		});
	</script>
	<style>
	  tbody.docStatusD {
	  	text-decoration: line-through;
	  }
	  tbody.docStatusX {
	    display:none;
	  }
	  tbody.docStatusL {
	    display:none;
	  }
	</style>
	<h3>
    		<span class="subhead-left"></span>
    		<span class="subhead-right"></span>
        </h3>
	<table cellpadding=0 cellspacing=0 summary="">
   				<tr>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalObligatedAmount}" /></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].totalObligatedAmount" disabled="true" attributeEntry="${subAwardAttributes.totalObligatedAmount}" />           
                </td>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAvailableAmount}" /></th>
                <td>
                     <kul:htmlControlAttribute property="document.subAwardList[0].totalAvailableAmount" disabled="true" attributeEntry="${subAwardAttributes.totalAvailableAmount}" />            
                </td>
            </tr>    
            
            <tr>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAmountReleased}" /></th>
                <td colspan="3">
                       <kul:htmlControlAttribute property="document.subAwardList[0].totalAmountReleased" disabled="true" attributeEntry="${subAwardAttributes.totalAmountReleased}" />           
                </td>
				
            </tr>
            </table>
    	<h3>
    		<span class="subhead-left">Invoices</span>
    	    <div align="right"><kul:help parameterNamespace="KC-SUBAWARD" parameterDetailType="Document" parameterName="subAwardInvoicesHelpUrl" altText="help"/></div>
         </h3>
        
      <table cellpadding=0 cellspacing=0 summary="">
            <tr>
              <th><div align="left">&nbsp;</div></th> 
              <th>&nbsp;</th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.invoiceNumber}" noColon="true" readOnly="true"/></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.startDate}" noColon="true" readOnly="true"/></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.endDate}" noColon="true" readOnly="true"/></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.effectiveDate}" noColon="true" readOnly="true"/></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.amountReleased}" noColon="true" readOnly="true"/></div></th>
			   <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.invoiceStatus}" noColon="true" readOnly="true"/></div></th>               
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.newFile}" noColon="true" readOnly="true"/></div></th>
            </tr>

		  	<c:set var="useInvoiceInquiry" value="${KualiForm.useInvoiceInquiry}" />

   			<c:forEach var="subAwardInvoice" items="${KualiForm.document.subAwardList[0].subAwardAmountReleasedList}" varStatus="status">
   					<tbody class="docStatus${subAwardInvoice.invoiceStatus}">
		             <tr>
						<th class="infoline" rowspan="2">
							<c:out value="${status.index+1}" />
						</th>
						<td style="text-align: center;" rowspan="2">
							<c:if test="${not useInvoiceInquiry}">
							    <a href="#" onClick="openNewWindow('subAwardFinancial','openAmountReleased','${status.index}','${KualiForm.formKey}','${KualiForm.document.sessionDocument}');return false;">open</a>
							</c:if>

							<c:if test="${useInvoiceInquiry}">
								<input type="hidden" name="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].subAwardAmtReleasedId" value="${subAwardInvoice.subAwardAmtReleasedId}" />
								<c:set var="boClassName" value="org.kuali.kra.subaward.bo.SubAwardAmountReleased"/>
								<c:set var="inquiryParameters" value="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].subAwardAmtReleasedId:subAwardAmtReleasedId"/>
								<c:set var="epMethodToCallAttribute" value="methodToCall.performInquiry.(!!${boClassName}!!).((`${inquiryParameters}`)).anchor${status.index}"/>
									${kfunc:registerEditableProperty(KualiForm, epMethodToCallAttribute)}
								<a href="#" onclick="javascript:inquiryPop('${boClassName}','${inquiryParameters}'); return false">view</a>
							</c:if>
						</td>	                
		                <td>
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].invoiceNumber"  readOnly="true" attributeEntry="${subAwardAmountReleasedAttributes.invoiceNumber}" />
						</div>
						</td>
		                <td>
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].startDate" readOnly="true" attributeEntry="${subAwardAmountReleasedAttributes.startDate}" datePicker="true" />                		
						</div>
						</td>
		                <td>
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].endDate" readOnly="true" attributeEntry="${subAwardAmountReleasedAttributes.endDate}" datePicker="true" />
						</div>
					
							
						<td>
						<div align="center">
						
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].effectiveDate" readOnly="true" attributeEntry="${subAwardAmountReleasedAttributes.effectiveDate}" datePicker="true" />
						</div>
						</td>	
						
						<td>
						<div align="center">
						
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].amountReleased" readOnly="true" attributeEntry="${subAwardAmountReleasedAttributes.amountReleased}" />
						</div>
						</td>
						<td>
							<c:out value="${subAwardInvoice.invoiceStatusLabel}"/>
						</td>							
                		<td>
							<c:if test="${subAwardInvoice.fileName!=null}">
								<a href="#" class="attachmentLink">
									<kra:fileicon attachment="${subAwardInvoice}" />
									<kul:htmlControlAttribute
										property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].fileName"
										readOnly="true"
										attributeEntry="${subAwardAmountReleasedAttributes.fileName}" />
								</a>
								<html:image property="methodToCall.downloadInvoiceAttachment.invoiceIndex${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton" />
							</c:if>								
						</td>
           		   </tr>
           		   <tr>
           		     <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountReleasedAttributes.comments}" noColon="true" readOnly="true"/></div></th>
           		     <td colspan="7">
                       <kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountReleasedList[${status.index}].comments" readOnly="true" attributeEntry="${subAwardAmountReleasedAttributes.comments}" />
                	 </td>
           		   </tr>
				  </tbody>           		   
	        	</c:forEach>
        </table>
    </div>
</kul:tab>
