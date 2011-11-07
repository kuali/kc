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


<kul:page lookup="false" 
          docTitle="New Proposal For Disclosure" 
          transactionalDocument="false"
          renderMultipart="true" 
          htmlFormAction="proposalEventDisclosure">
          
    <script language="javascript" src="scripts/kuali_application.js"></script>
    
    <div id="workarea">


<kul:tab tabTitle="New Proposals for disclosure"
         defaultOpen="true"
         alwaysOpen="true"
         transparentBackground="true" 
         useCurrentTabIndexAsKey="true"
         tabErrorKey="notificationTemplates[*">
         
    <div class="tab-container" align="center" id="G100">
        <h3>
            <span class="subhead-left">Proposal List</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDisclosure" altText="help" /></span>
        </h3>
        
            <table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpAdding="0" cellspacing="0" width="50%" align="center" >
                                 <tr>
                                    <th><div align="center">Proposal Number</div></th> 
                                    <th><div align="center">Proposal Name</div></th> 
                                    <th><div align="center">Sponsor</div></th> 
                                    <th><div align="center">start Date</div></th> 
                                    <th><div align="center">End Date</div></th> 
                                    <th><div align="center">Action</div></th> 
                                </tr>
        <c:forEach items="${KualiForm.disclosureHelper.newProposals}" var="proposal" varStatus="status">
               <tr>
                  <td align="left" valign="middle">
					<div align="left">
                		${proposal.proposalNumber}
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		${proposal.title}
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		${proposal.sponsor.sponsorName} 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                 	    ${proposal.requestedStartDateInitial}
					</div>
					</td>
                  <td align="left" valign="middle">
					<div align="left">
                 	    ${proposal.requestedEndDateInitial}
					</div>
					</td>
                    <td>
                      <div align="center">
                        <a title="New Proposal Disclosure" href="coiDisclosure.do?methodToCall=newProjectDisclosure&disclosureHelper.moduleCode=11&disclosureHelper.newProjectId=${proposal.proposalNumber}&docTypeName=CoiDisclosureDocument">Report Coi</a>
                       </div>
                    </td>
               </tr>
        </c:forEach>
        <c:forEach items="${KualiForm.disclosureHelper.newInstitutionalProposals}" var="proposal" varStatus="status">
               <tr>
                  <td align="left" valign="middle">
					<div align="left">
                		${proposal.proposalNumber}
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		${proposal.title}
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		${proposal.sponsor.sponsorName} 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                 	    ${proposal.requestedStartDateInitial}
					</div>
					</td>
                  <td align="left" valign="middle">
					<div align="left">
                 	    ${proposal.requestedEndDateInitial}
					</div>
					</td>
                    <td>
                      <div align="center">
                        <a title="New Proposal Disclosure" href="coiDisclosure.do?methodToCall=newProjectDisclosure&disclosureHelper.moduleCode=15&disclosureHelper.proposalType=ip&disclosureHelper.newProjectId=${proposal.proposalNumber}&docTypeName=CoiDisclosureDocument">Report Coi</a>
                       </div>
                    </td>
               </tr>
        </c:forEach>
            </table>   
    </div> 
</kul:tab>

<kul:panelFooter />

<div id="globalbuttons" class="globalbuttons">
    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" styleClass="globalbuttons" property="methodToCall.close" title="close" alt="close"/>
</div>
<hr>

</SCRIPT>

</kul:page>
