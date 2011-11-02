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

<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<kul:tab defaultOpen="false" tabTitle="Manual Event and Financial Entities" auditCluster="" tabAuditKey="" useRiceAuditMode="true"
    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
	<div class="tab-container" align="center">
      <div class="div_Proposal">
                <h3>
    		        <span class="subhead-left">New Event Project</span>
    		        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
                </h3>
        <table id="newpEvent-table" cellpadding="0" cellspacing="0" summary="">
            <tr>
                <th align="right" valign="middle">
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.disclosureEventType}" />
                </th>
                <td align="left" valign="middle" colspan="5" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.disclosureEventType" 
                            attributeEntry="${coiDisclProjectAttributes.disclosureEventType}" onchange="updateTable(this)" /> 
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectTitle}" />
                </th>
                <td align="left" valign="middle" colspan="2">
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectTitle" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectTitle}" /> 
                </td>
                 <th align="right" valign="middle">
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectId}" />
                </th>
                <td align="left" valign="middle" colspan="2" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectId" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectId}" /> 
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle" >
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectRole}" />
                </th>
               <td align="left" valign="middle" colspan="2" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectRole" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectRole}" />
          
                 </td>
                <th align="right" valign="middle" >
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectSponsor}" />
                </th>
               <td align="left" valign="middle" colspan="2" >
                       <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectSponsor" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectSponsor}" />
          
                 </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectType}" />
                </th>
                <td align="left" valign="middle" colspan="2" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectType" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectType}" /> 
                      <kul:htmlControlAttribute property="disclosureHelper.protocolType" attributeEntry="${protocolAttributes.protocolTypeCode}" />
                </td>
                <th align="right" valign="middle" >
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectFundingAmount}" />
                </th>
               <td align="left" valign="middle" colspan="2" >
                       <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectFundingAmount" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectFundingAmount}" />
          
                 </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectStartDate}" />
                </th>
                <td align="left" valign="middle" colspan="2" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectStartDate" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectStartDate}" /> 
                </td>
                <th align="right" valign="middle" >
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectEndDate}" />
                </th>
               <td align="left" valign="middle" colspan="2" >
                       <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectEndDate" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectEndDate}" />
          
                 </td>
            </tr>
                <tr>
                    <td align="center" colspan="6">
                        <div align="center">
                            <html:image property="methodToCall.addProposal.anchor${tabKey}"
                                        src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
              </table>
           </div> <%-- proposal div --%>              
 
       <div class="div_ProposalList">
                <h3>
    		        <span class="subhead-left">Proposals</span>
    		        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
                </h3>
              
              
                                  
            <%-- New data --%>
            
            <%-- Existing data --%>

        	<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclProjects}" varStatus="status">
                 <kra-coi:proposalFinancialEntity disclProject="${disclProject}"  idx="${status.index}"/>	            
        	</c:forEach> 
            <%-- Existing data --%>
     </div> <%-- proposal list div --%>
    </div>
</kul:tab>

