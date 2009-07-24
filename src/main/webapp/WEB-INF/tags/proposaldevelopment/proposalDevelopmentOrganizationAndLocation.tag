<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="organizationAttributes" value="${DataDictionary.Organization.attributes}" />
<c:set var="propLocationAttributes" value="${DataDictionary.ProposalLocation.attributes}" />
<c:set var="rolodexAttributes" value="${DataDictionary.Rolodex.attributes}" />

<kul:tab tabTitle="Organization/Location" defaultOpen="false" tabErrorKey="document.developmentProposalList[0].organizationId*,document.developmentProposalList[0].performingOrganizationId*,document.developmentProposalList[0].proposalLocation*,newPropLocation*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Organization</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.Organization" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
             <tr>
				<th width="40%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.organizationId}" /></div></th>
                <td align="left" valign="middle"  class="infoline" width="60%">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].organizationId" attributeEntry="${proposalDevelopmentAttributes.organizationId}" />
                    <c:out value="${KualiForm.document.developmentProposalList[0].organization.organizationName}"/>
                    <kul:lookup boClassName="org.kuali.kra.bo.Organization" 
                    fieldConversions="organizationId:document.developmentProposalList[0].organizationId,congressionalDistrict:document.developmentProposalList[0].organization.congressionalDistrict,organizationName:document.developmentProposalList[0].organization.organizationName,rolodex.firstName:document.developmentProposalList[0].organization.rolodex.firstName,rolodex.lastName:document.developmentProposalList[0].organization.rolodex.lastName,rolodex.addressLine1:document.developmentProposalList[0].organization.rolodex.addressLine1,rolodex.addressLine2:document.developmentProposalList[0].organization.rolodex.addressLine2,rolodex.addressLine3:document.developmentProposalList[0].organization.rolodex.addressLine3,rolodex.city:document.developmentProposalList[0].organization.rolodex.city,rolodex.state:document.developmentProposalList[0].organization.rolodex.state" anchor="${currentTabIndex}"/> 
                    <kul:directInquiry boClassName="org.kuali.kra.bo.Organization" inquiryParameters="document.developmentProposalList[0].organizationId:organizationId" anchor="${currentTabIndex}"/>
                  </td>
             </tr>  
             <tr>
				<th><div align="right">Authorized Representative Name & Address:</div></th>
                <td align="left" valign="middle"  class="infoline">                  
                    <c:out value="${KualiForm.document.developmentProposalList[0].organization.rolodex.firstName}"/>&nbsp                    
                    <c:out value="${KualiForm.document.developmentProposalList[0].organization.rolodex.lastName}"/><br>
                    <c:out value="${KualiForm.document.developmentProposalList[0].organization.rolodex.addressLine1}"/><br>
                    <c:out value="${KualiForm.document.developmentProposalList[0].organization.rolodex.addressLine2}"/><br>
                    <c:out value="${KualiForm.document.developmentProposalList[0].organization.rolodex.addressLine3}"/><br>
                    <c:out value="${KualiForm.document.developmentProposalList[0].organization.rolodex.city}"/><br>
                    <c:out value="${KualiForm.document.developmentProposalList[0].organization.rolodex.state}"/><br>
				</td>
				</tr>
				<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${organizationAttributes.congressionalDistrict}" /></div></th>
                <td class="infoline">
                	<c:out value="${KualiForm.document.developmentProposalList[0].organization.congressionalDistrict}"/>
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.performingOrganizationId}"  /></div></th>
                <td class="infoline">
                   	<kul:htmlControlAttribute property="document.developmentProposalList[0].performingOrganizationId" attributeEntry="${proposalDevelopmentAttributes.performingOrganizationId}" />               
                   	<c:out value="${KualiForm.document.developmentProposalList[0].performingOrganization.organizationName}"/>	                
                    <kul:lookup boClassName="org.kuali.kra.bo.Organization" 
                    fieldConversions="organizationId:document.developmentProposalList[0].performingOrganizationId,organizationName:document.developmentProposalList[0].performingOrganization.organizationName"  anchor="${currentTabIndex}"/>
                    <kul:directInquiry boClassName="org.kuali.kra.bo.Organization" inquiryParameters="document.developmentProposalList[0].performingOrganizationId:organizationId" anchor="${currentTabIndex}"/>
                </td>
            </tr>
        </table>
        <h3>
    		<span class="subhead-left">Performance Site Locations</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.ProposalLocation" altText="help"/></span>
        </h3>
        <table cellpadding=0 cellspacing="0"  summary="">
             <tr>
              	<th><div align="left">&nbsp</div></th>  
				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${propLocationAttributes.location}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Address" scope="col" align="left"/> 
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
             </tr>
             
            <kra:section permission="modifyProposal">
            <kra-pd:propLocationDisplay index="-1" locationIter="${KualiForm.newPropLocation}" docLocation="newPropLocation"/> 
            </kra:section> 
              
        	<c:forEach var="location" items="${KualiForm.document.developmentProposalList[0].proposalLocations}" varStatus="status">
          		<kra-pd:propLocationDisplay index="${status.index}" locationIter="${location}" docLocation="document.developmentProposalList[0].proposalLocation[${status.index}]"/> 
        	</c:forEach>       
        </table>
      
    </div>
</kul:tab>
