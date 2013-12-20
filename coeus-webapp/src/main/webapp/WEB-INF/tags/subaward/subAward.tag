<%--
 Copyright 2005-2013 The Kuali Foundation
 
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
<script type='text/javascript' src='dwr/interface/KraPersonService.js'></script>
<script language="javascript" src="dwr/interface/OrganizationService.js"></script>
<script type='text/javascript' src='dwr/interface/RolodexService.js'></script>

<kul:tab tabTitle="Subaward" defaultOpen="${KualiForm.document.subAwardList[0].defaultOpen}" tabErrorKey="document.subAwardList[0].statusCode*,document.subAwardList[0].requisitionerUserName*,document.subAwardList[0].siteInvestigatorId*,document.subAwardList[0].purchaseOrderNum*,document.subAwardList[0].organizationId*,document.subAwardList[0].subAwardTypeCode*,document.subAwardList[0].title*,document.subAwardList[0].startDate*,document.subAwardList[0].endDate*,document.subAwardList[0].accountNumber*,document.subAwardList[0].vendorNumber*,document.subAwardList[0].requisitionerUnit*,document.subAwardList[0].archiveLocation*,document.subAwardList[0].closeoutDate*,document.subAwardList[0].comments*,document.subAwardList[0].totalAnticipatedAmount*,document.subAwardList[0].totalObligatedAmount*"
 auditCluster="subawardFinancialdAuditErrors" tabAuditKey="document.subAwardList[0].totalAnticipatedAmount*,document.subAwardList[0].totalObligatedAmount*" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Subaward</span>
    			<div align="right"><kul:help parameterNamespace="KC-SUBAWARD" parameterDetailType="Document" parameterName="subAwardHomeHelpUrl" altText="help"/></div>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.subAwardId}" /></div></th>
                <td>
                    ${KualiForm.subAwardDocument.subAward.subAwardCode}&nbsp; 
               </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.siteInvestigator}" /></div></th>
                <td>
                     <kul:htmlControlAttribute property="document.subAwardList[0].siteInvestigatorId" 
                                                attributeEntry="${subAwardAttributes.siteInvestigator}"
                                                onblur="loadRolodexPersonName('document.subAwardList[0].siteInvestigatorId',
	                               							'sub.fullName.div',
	                               							'sub.siteInvestigatorId.div');"
           	        							  			readOnly="${readOnly}"/>           	         						  			  
                      <c:if test="${!readOnly}">
                      	<kul:lookup boClassName="org.kuali.kra.bo.Rolodex" 
								fieldConversions="rolodexId:document.subAwardList[0].siteInvestigator" 			
          						anchor="${tabKey}"/> 
          			  </c:if>			
          			  	<kul:directInquiry boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" inquiryParameters="document.subAwardList[0].siteInvestigator:rolodexId" anchor="${tabKey}" />
          			  
          			  <div id="sub.fullName.div">
          			      &nbsp; 
          			      <c:if test="${!empty KualiForm.document.subAwardList[0].siteInvestigatorId}">
					          <c:if test="${!empty KualiForm.document.subAwardList[0].rolodex}">
						          <c:choose>
						              <c:when test="${empty KualiForm.document.subAwardList[0].rolodex.fullName}">
						                  <c:out value="${KualiForm.document.subAwardList[0].rolodex.organization}"/>
                                      </c:when>
                                      <c:otherwise>						                      
							              <c:out value="${KualiForm.document.subAwardList[0].rolodex.fullName}" />
							          </c:otherwise>
							      </c:choose>
				              </c:if>
				          </c:if>
				      </div>
          			  <html:hidden styleId ="sub.siteInvestigatorId.div" property="document.subAwardList[0].siteInvestigator" />  
          			   ${kfunc:registerEditableProperty(KualiForm, "document.subAwardList[0].siteInvestigator")} 
                </td>
            </tr>
        	<tr>
				<th>
					<div align="right">Version:</div></th>
				
					<td>${KualiForm.subAwardDocument.subAward.sequenceNumber}&nbsp; </td>              
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.organizationId}" /></div></th>
                <td>
                  <kul:htmlControlAttribute property="document.subAwardList[0].organizationId" readOnly="${readOnly}" 
                  				onblur="loadOrganizationName('document.subAwardList[0].organizationId', 'sub.organizationName');" 
                  				attributeEntry="${subAwardAttributes.organizationId}" />
                  <c:if test="${!readOnly}">
                  	<kul:lookup boClassName="org.kuali.kra.bo.Organization" fieldConversions="organizationId:document.subAwardList[0].organizationId,organizationName:document.subAwardList[0].organization.organizationName" anchor="${tabKey}" />
            	  </c:if>	
            	  	<kul:directInquiry boClassName="org.kuali.kra.bo.Organization" inquiryParameters="document.subAwardList[0].organizationId:organizationId" anchor="${tabKey}" /> 
                 
                <div id="sub.organizationName.div">${KualiForm.document.subAwardList[0].organization.organizationName}&nbsp;</div>
                 <c:if test="${readOnly}"><html:hidden styleId ="sub.organizationName" property="document.subAwardList[0].organizationId" /></c:if>            		
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
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.requisitionerUserName}" forceRequired="true" /></div></th>
                <td>
			<c:if test="${!readOnly}">
	                    <kul:htmlControlAttribute property="document.subAwardList[0].requisitionerUserName" readOnly="${readOnly}" 
							onblur="loadContactPersonName('document.subAwardList[0].requisitionerUserName',
										'requistioner.fullName',
										'na',
										'na',
										'na',
										'sub.requisitionerId.div');"
	                    	 attributeEntry="${subAwardAttributes.requisitionerId}"/>						
					</c:if> 
					<c:if test="${readOnly}">${KualiForm.document.subAwardList[0].requisitionerUserName}</c:if>
				  
				  <c:if test="${!readOnly}">
                  	<kul:lookup boClassName="org.kuali.kra.bo.KcPerson" fieldConversions="personId:document.subAwardList[0].requisitionerId,fullName:document.subAwardList[0].requisitionerName,unit.unitNumber:document.subAwardList[0].requisitionerUnit,unit.unitName:document.subAwardList[0].unit.unitName" anchor="${tabKey}" />
            	  </c:if> 	 
            	  	  <kul:directInquiry boClassName="org.kuali.kra.bo.KcPerson" inquiryParameters="document.subAwardList[0].requisitionerId:personId" anchor="${tabKey}" /> 
                     
                      <br/><span id="requistioner.fullName"><c:out value="${KualiForm.document.subAwardList[0].requisitionerName}"/>&nbsp;</span>                
                	  <html:hidden styleId ="sub.requisitionerId.div" property="document.subAwardList[0].requisitionerId" />
          			  ${kfunc:registerEditableProperty(KualiForm, "document.subAwardList[0].requisitionerId")}
                </td> 
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.requisitionerUnit}" /></div></th>
                <td>                       
                    
                      <kul:htmlControlAttribute property="document.subAwardList[0].requisitionerUnit"                      
                      readOnly="${readOnly}" attributeEntry="${subAwardAttributes.requisitionerUnit}" 
                      onblur="ajaxLoad('getUnitName','document.subAwardList[0].requisitionerUnit', 'sub.unitName');"/>
                     
                     <c:if test="${!readOnly}"> 	
                      	<kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:document.subAwardList[0].requisitionerUnit,unitName:document.subAwardList[0].unit.unitName" anchor="${tabKey}" />
            	  	 </c:if>     
            	  	    <kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="document.subAwardList[0].requisitionerUnit:unitNumber" anchor="${tabKey}" /> 
            	  	 
            	  	    <div id="sub.unitName.div">${KualiForm.document.subAwardList[0].unit.unitName}&nbsp;</div>
            	  	    <c:if test="${readOnly}"> <html:hidden styleId ="sub.unitName" property="document.subAwardList[0].requisitionerUnit" /></c:if>
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
                       <kul:htmlControlAttribute property="document.subAwardList[0].totalObligatedAmount" disabled="true" attributeEntry="${subAwardAttributes.totalObligatedAmount}" />     
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAnticipatedAmount}" /></div></th>
                <td>
                     <kul:htmlControlAttribute property="document.subAwardList[0].totalAnticipatedAmount" disabled="true" attributeEntry="${subAwardAttributes.totalAnticipatedAmount}" />  
                </td>
            </tr>    
            
            <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAmountReleased}" /></div></th>
                <td>
                       <kul:htmlControlAttribute property="document.subAwardList[0].totalAmountReleased" disabled="true" attributeEntry="${subAwardAttributes.totalAmountReleased}" />  
                </td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAvailableAmount}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].totalAvailableAmount" disabled="true" attributeEntry="${subAwardAttributes.totalAvailableAmount}" /> 
                </td>
            </tr>  
        </table>
    </div>
</kul:tab>
