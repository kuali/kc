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

<c:set var="subAwardAttributes" value="${DataDictionary.SubAward.attributes}" />
<c:set var="action" value="subAward" />
<script type='text/javascript' src='dwr/interface/KraPersonService.js'></script>
<script language="javascript" src="dwr/interface/OrganizationService.js"></script>
<script type='text/javascript' src='dwr/interface/RolodexService.js'></script>

<kul:tab tabTitle="Subaward" defaultOpen="${KualiForm.document.subAwardList[0].defaultOpen}" tabErrorKey="document.subAwardList[0].statusCode*,document.subAwardList[0].requisitionerUserName*,document.subAwardList[0].siteInvestigatorId*,document.subAwardList[0].purchaseOrderNum*,document.subAwardList[0].organizationId*,document.subAwardList[0].subAwardTypeCode*,document.subAwardList[0].title*,document.subAwardList[0].startDate*,document.subAwardList[0].endDate*,document.subAwardList[0].accountNumber*,document.subAwardList[0].vendorNumber*,document.subAwardList[0].requisitionerUnit*,document.subAwardList[0].archiveLocation*,document.subAwardList[0].closeoutDate*,document.subAwardList[0].comments*,document.subAwardList[0].totalAnticipatedAmount*,document.subAwardList[0].totalObligatedAmount*,document.subAwardList[0].fedAwardProjDesc*,document.subAwardList[0].fAndARate*,document.subAwardList[0].deMinimus*,document.subAwardList[0].ffataRequired*,document.subAward.ffataRequired*"
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
                <th>&nbsp;</th>
                <td>
                    &nbsp;
                </td>
            </tr>
        	<tr>
				<th><div align="right">Version:</div></th>
                <td>${KualiForm.subAwardDocument.subAward.sequenceNumber}&nbsp; </td>

                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.executionDate}" /></div></th>
                <td>
                    <kul:htmlControlAttribute property="document.subAwardList[0].executionDate" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.executionDate}" datePicker="true" />
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
                  	<kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" fieldConversions="personId:document.subAwardList[0].requisitionerId,fullName:document.subAwardList[0].requisitionerName,unit.unitNumber:document.subAwardList[0].requisitionerUnit,unit.unitName:document.subAwardList[0].unit.unitName" anchor="${tabKey}" />
            	  </c:if> 	 
            	  	  <kul:directInquiry boClassName="org.kuali.coeus.common.framework.person.KcPerson" inquiryParameters="document.subAwardList[0].requisitionerId:personId" anchor="${tabKey}" /> 
                     
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
                      	<kul:lookup boClassName="org.kuali.coeus.common.framework.unit.Unit" fieldConversions="unitNumber:document.subAwardList[0].requisitionerUnit,unitName:document.subAwardList[0].unit.unitName" anchor="${tabKey}" />
            	  	 </c:if>     
            	  	    <kul:directInquiry boClassName="org.kuali.coeus.common.framework.unit.Unit" inquiryParameters="document.subAwardList[0].requisitionerUnit:unitNumber" anchor="${tabKey}" /> 
            	  	 
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
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.fedAwardProjDesc}" /></div></th>
                <td colspan="3">
                    <kul:htmlControlAttribute property="document.subAwardList[0].fedAwardProjDesc" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.fedAwardProjDesc}" />
                </td>

            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.fAndARate}" /></div></th>
                <td>
                    <kul:htmlControlAttribute property="document.subAwardList[0].fAndARate" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.fAndARate}" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.deMinimus}" /></div></th>
                <td>
                    <kul:htmlControlAttribute property="document.subAwardList[0].deMinimus" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.deMinimus}" />
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
            
            <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.costType}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].costType" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.costType}" />
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
                        <kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.Rolodex"
                                    fieldConversions="rolodexId:document.subAwardList[0].siteInvestigator"
                                    anchor="${tabKey}"/>
                    </c:if>
                    <kul:directInquiry boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex" inquiryParameters="document.subAwardList[0].siteInvestigator:rolodexId" anchor="${tabKey}" />

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
            <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.requisitionId}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.subAwardList[0].requisitionId" readOnly="${readOnly}" attributeEntry="${subAwardAttributes.requisitionId}" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.organizationId}" /></div></th>
                <td>
                    <kul:htmlControlAttribute property="document.subAwardList[0].organizationId" readOnly="${readOnly}"
                                              onblur="loadOrganizationName('document.subAwardList[0].organizationId', 'sub.organizationName'); loadOrganizationDuns('document.subAwardList[0].organizationId', 'sub.organizationDuns');"
                                              attributeEntry="${subAwardAttributes.organizationId}" />
                    <c:if test="${!readOnly}">
                        <kul:lookup boClassName="org.kuali.coeus.common.framework.org.Organization" fieldConversions="organizationId:document.subAwardList[0].organizationId,organizationName:document.subAwardList[0].organization.organizationName,dunsNumber:document.subAwardList[0].organization.dunsNumber" anchor="${tabKey}" />
                    </c:if>
                    <kul:directInquiry boClassName="org.kuali.coeus.common.framework.org.Organization" inquiryParameters="document.subAwardList[0].organizationId:organizationId" anchor="${tabKey}" />

                    <div id="sub.organizationName.div">${KualiForm.document.subAwardList[0].organization.organizationName}&nbsp;</div>
                    <div id="sub.organizationDuns.div">${KualiForm.document.subAwardList[0].organization.dunsNumber}&nbsp;</div>
                    <c:if test="${readOnly}"><html:hidden styleId ="sub.organizationName" property="document.subAwardList[0].organizationId" /></c:if>
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.ffataRequired}" /></div></th>
                <td><kul:htmlControlAttribute property="document.subAwardList[0].ffataRequired" attributeEntry="${subAwardAttributes.ffataRequired}" /></td>
            </tr>
        </table>
    </div>
</kul:tab>
