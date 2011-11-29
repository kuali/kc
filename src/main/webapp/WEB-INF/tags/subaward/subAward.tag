<%--
 Copyright 2005-2010 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="subAwardAttributes" value="${DataDictionary.SubAward.attributes}" />
<c:set var="action" value="subAward" />

<kul:tab tabTitle="SubAward" defaultOpen="true" tabErrorKey="" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> SubAward</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SubAward" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.subAwardId}" /></div></th>
                <td>
                    ${KualiForm.subAwardDocument.subAward.subAwardCode}&nbsp; 
                      
                     <%--  <kul:htmlControlAttribute property="document.subAwardList[0].subAwardId" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.subAwardId}" /> --%>
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.siteInvestigator}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].rolodex.firstName" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.siteInvestigator}" />
                      <kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
								fieldConversions="firstName:document.subAwardList[0].rolodex.firstName,rolodexId:document.subAwardList[0].siteInvestigator" 			
          						anchor="${tabKey}"/> 
          			   <kul:directInquiry boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" inquiryParameters="document.subAwardList[0].siteInvestigator:siteInvestigator" anchor="${tabKey}" />			
                </td>
            </tr>
        	<tr>
				<th>
					<div align="right">Version:</div></th>
				
					<td>${KualiForm.subAwardDocument.subAward.sequenceNumber}&nbsp; </td>              
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.organizationId}" /></div></th>
                <td>
                <kul:htmlControlAttribute property="document.subAwardList[0].organization.organizationName" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.organizationId}" />
                  <kul:lookup boClassName="org.kuali.kra.bo.Organization" fieldConversions="organizationId:document.subAwardList[0].organizationId,organizationName:document.subAwardList[0].organization.organizationName" anchor="${tabKey}" />
            	  	    <kul:directInquiry boClassName="org.kuali.kra.bo.Organization" inquiryParameters="document.subAwardList[0].organizationId:organizationId" anchor="${tabKey}" /> 
                      <%-- <kul:htmlControlAttribute property="document.subAwardList[0].subAwardorId" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.subAwardorId}" /> --%>
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.startDate}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].startDate" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.startDate}" datePicker="true" />
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.endDate}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].endDate" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.endDate}" datePicker="true" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.subAwardTypeCode}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTypeCode" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.subAwardTypeCode}" />
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.purchaseOrderNum}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].purchaseOrderNum" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.purchaseOrderNum}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.title}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].title" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.title}" />
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.statusCode}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].statusCode" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.statusCode}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.accountNumber}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].accountNumber" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.accountNumber}" />
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.vendorNumber}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].vendorNumber" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.vendorNumber}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.requisitionerId}" /></div></th>
              
                      
             <td>
                <kul:htmlControlAttribute property="document.subAwardList[0].requisitionerName" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.requisitionerId}" />
                  <kul:lookup boClassName="org.kuali.kra.bo.KcPerson" fieldConversions="personId:document.subAwardList[0].requisitionerId,fullName:document.subAwardList[0].requisitionerName,unit.unitNumber:document.subAwardList[0].requisitionerUnit,unit.unitName:document.subAwardList[0].unit.unitName" anchor="${tabKey}" />
            	  	    <kul:directInquiry boClassName="org.kuali.kra.bo.KcPerson" inquiryParameters="document.subAwardList[0].subAwardorId:subAwardorId" anchor="${tabKey}" /> 
                      <%-- <kul:htmlControlAttribute property="document.subAwardList[0].subAwardorId" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.subAwardorId}" /> --%>
                </td> 
				<%-- <td>           
                <kul:htmlControlAttribute property="document.subAwardList[0].requisitionerId" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.requisitionerId}" />
                </td>  --%>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.requisitionerUnit}" /></div></th>
                <td>                       
                    
                      <kul:htmlControlAttribute property="document.subAwardList[0].requisitionerUnit" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.requisitionerUnit}" />
                      <kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:document.subAwardList[0].requisitionerUnit,unitName:document.subAwardList[0].unit.unitName" anchor="${tabKey}" />
            	  	    <kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="document.subAwardList[0].requisitionerUnit:requisitionerUnit" anchor="${tabKey}" /> 
            	  	   
            	  	   <div>${KualiForm.document.subAwardList[0].unit.unitName}&nbsp;</div>  
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.archiveLocation}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].archiveLocation" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.archiveLocation}" />
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.closeoutDate}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].closeoutDate" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.closeoutDate}" datePicker="true" />
                </td>
            </tr>        	
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.comments}" /></div></th>
                <td colspan="3">
                      <kul:htmlControlAttribute property="document.subAwardList[0].comments" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.comments}" />
                </td>
				
            </tr>
            
            
            <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalObligatedAmount}" /></div></th>
                <td>
                       ${KualiForm.subAwardDocument.subAward.totalObligatedAmount}&nbsp; 
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAnticipatedAmount}" /></div></th>
                <td>
                      ${KualiForm.subAwardDocument.subAward.totalAnticipatedAmount}&nbsp; 
                </td>
            </tr>    
            
            <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAmountReleased}" /></div></th>
                <td>
                       ${KualiForm.subAwardDocument.subAward.totalAmountReleased}&nbsp; 
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAvailableAmount}" /></div></th>
                <td>
                      ${KualiForm.subAwardDocument.subAward.totalAvailableAmount}&nbsp; 
                </td>
            </tr>  
        </table>
    </div>
</kul:tab>
