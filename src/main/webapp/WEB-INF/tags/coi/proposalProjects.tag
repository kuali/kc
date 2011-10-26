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
<kul:tab defaultOpen="false" tabTitle="Add Proposals" auditCluster="" tabAuditKey="" useRiceAuditMode="true"
    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Financial Entity</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
        </h3>
        
        <table id="location-table" cellpadding="0" cellspacing="0" summary="">
            <tr>
                <th align="right" valign="middle">
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectTitle}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectTitle" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectTitle}" /> 
                </td>
                <th align="right" valign="middle" >
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectRole}" />
                </th>
               <td align="left" valign="middle" colspan="3" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectRole" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectRole}" />
          
                 </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectId}" />
                </th>
                <td align="left" valign="middle" colspan="3" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectId" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectId}" /> 
                </td>
                <th align="right" valign="middle" >
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectSponsor}" />
                </th>
               <td align="left" valign="middle" colspan="3" >
                       <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectSponsor" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectSponsor}" />
          
                 </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectType}" />
                </th>
                <td align="left" valign="middle" colspan="3" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectType" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectType}" /> 
                </td>
                <th align="right" valign="middle" >
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectFundingAmount}" />
                </th>
               <td align="left" valign="middle" colspan="3" >
                       <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectFundingAmount" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectFundingAmount}" />
          
                 </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectStartDate}" />
                </th>
                <td align="left" valign="middle" colspan="3" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectStartDate" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectStartDate}" /> 
                </td>
                <th align="right" valign="middle" >
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectEndDate}" />
                </th>
               <td align="left" valign="middle" colspan="3" >
                       <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectEndDate" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectEndDate}" />
          
                 </td>
            </tr>
                <tr>
                    <td align="center" colspan="8">
                        <div align="center">
                            <html:image property="methodToCall.addProposal.anchor${tabKey}"
                                        src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
                                  
            <%-- New data --%>
            
            <%-- Existing data --%>
            <tr>
                <td colspan="8">
    	<h3>
    		<span class="subhead-left">Proposal List</span>
        </h3>
        </td>
        </tr>
                                 <tr>
                                    <th/>
                                    <th><div align="center">Proposal Number</div></th> 
                                    <th><div align="center"></div>Proposal Name</th> 
                                    <th><div align="center"></div>Sponsor</th> 
                                    <th><div align="center">start Date</div></th> 
                                    <th><div align="center">End Date</div></th> 
                                    <th><div align="center">PI</div></th> 
                                </tr>
            
        	<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclProjects}" varStatus="status">
 
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].disclosureFlag" attributeEntry="${coiDisclProjectAttributes.disclosureFlag}" readOnly="${readOnly}"/> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].coiProjectId" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectId}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].coiProjectTitle" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectTitle}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].coiProjectSponsor" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectSponsor}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].coiProjectStartDate" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectStartDate}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].coiProjectEndDate" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectEndDate}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
				         ${KualiForm.document.coiDisclosureList[0].disclosurePersons[0].reporter.fullName}
					</div>
				  </td>
	            </tr>
	            
        	</c:forEach> 
            <%-- Existing data --%>
        </table>

    </div>
</kul:tab>

