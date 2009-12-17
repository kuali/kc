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
<%@ attribute name="summaryIndex" required="true" %>
<%@ attribute name="parentTabTitle" required="true" %>

<c:set var="budgetAttributes" value="${DataDictionary.Budget.attributes}" />
<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="budgetVersionOverviewAttributes" value="${DataDictionary.BudgetVersionOverview.attributes}" />

<c:set var="summary" value="${KualiForm.hierarchyProposalSummaries[summaryIndex]}"/>
<div class="tab-container" align="center">
	<h3><span class="subhead-left">Proposal Summary</span><span class="subhead-right"><!-- "Open Proposal" button here? --></span></h3>
	<kra:innerTab parentTab="${parentTabTitle}" tabTitle="Overview" tabDescription="Overview" defaultOpen="false">
		<div class="innerTab-container" align="left">
			<table cellpadding=0 cellspacing=0 summary="">
	          	<tr><th width="20%" align="right" valign="middle">Proposal Number:</th><td width="30%" align="left" valign="middle">${summary.proposalNumber}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Status:</th><td width="30%" align="left" valign="middle">${summary.proposalStateName}&nbsp;</td></tr>
	          	<tr><th width="20%" align="right" valign="middle">Project Start Date:</th><td width="30%" align="left" valign="middle">${summary.requestedStartDateInitial}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Proposal Type:</th><td width="30%" align="left" valign="middle">${summary.proposalTypeName}&nbsp;</td></tr>	
				<tr><th width="20%" align="right" valign="middle">Project End Date:</th><td width="30%" align="left" valign="middle">${summary.requestedEndDateInitial}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Narrative:</th><td width="30%" align="left" valign="middle">${summary.narrative}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Lead Unit:</th><td width="30%" align="left" valign="middle">${summary.ownedByUnitName}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Budget:</th><td width="30%" align="left" valign="middle">${summary.budget}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Activity Type:</th><td width="30%" align="left" valign="middle">${summary.activityTypeName}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Project Title:</th><td width="30%" align="left" valign="middle">${summary.title}&nbsp;</td></tr>
			</table>
		</div>
	</kra:innerTab>
	<kra:innerTab parentTab="${parentTabTitle}" tabTitle="Sponsor &amp; Program Information" tabDescription="Sponsor &amp; Program Information" defaultOpen="false">
		<div class="innerTab-container" align="left">
			<table cellpadding=0 cellspacing=0 summary="">
	          	<tr><th width="20%" align="right" valign="middle">Sponsor Deadline Date:</th><td width="30%" align="left" valign="middle">${summary.deadlineDate}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Notice of Opportunity:</th><td width="30%" align="left" valign="middle">${summary.noticeOfOpportunityName}&nbsp;</td></tr>
	          	<tr><th width="20%" align="right" valign="middle">Sponsor Deadline Type:</th><td width="30%" align="left" valign="middle">${summary.deadlineType}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">CDFA Number:</th><td width="30%" align="left" valign="middle">${summary.cfdaNumber}&nbsp;</td></tr>	
				<tr><th width="20%" align="right" valign="middle">Sponsor Name:</th><td width="30%" align="left" valign="middle">${summary.sponsorName}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Opportunity ID:</th><td width="30%" align="left" valign="middle">${summary.programAnnouncementNumber}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Prime Sponsor ID:</th><td width="30%" align="left" valign="middle">${summary.primeSponsorCode}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Sponsor Proposal ID:</th><td width="30%" align="left" valign="middle">${summary.sponsorProposalNumber}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">NSF Science Code:</th><td width="30%" align="left" valign="middle">${summary.nsfCode}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Sub-Award(s) Included:</th><td width="30%" align="left" valign="middle">${summary.subcontracts}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Sponsor Div Code:</th><td width="30%" align="left" valign="middle">${summary.agencyDivisionCode}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Sponsor Program Code:</th><td width="30%" align="left" valign="middle">${summary.agencyProgramCode}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Opportunity Title:</th><td width="80%" align="left" valign="middle" colspan="3">${summary.programAnnouncementTitle}&nbsp;</td></tr>
			</table>
		</div>
	</kra:innerTab>
	<kra:innerTab parentTab="${parentTabTitle}" tabTitle="Investigator/Units" tabDescription="Investigator/Units" defaultOpen="false">
		<div class="innerTab-container" align="left">
			<table cellpadding=0 cellspacing=0 summary="">
				<tr><th width="20%" align="right" valign="middle">Principle Investigator:</th><td width="30%" align="left" valign="middle">${summary.principleInvestigator.fullName}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Unit(s):</th>
	          		<td width="30%" align="left" valign="middle">${summary.ownedByUnitName} (Lead Unit)<br />
	          			<c:forEach var="unit" items="${summary.principleInvestigator.units}" varStatus="unitStatus">
	          				<c:if test="${not unit.leadUnit}">${unit.unitNumber} : ${unit.unit.unitName}<br /></c:if>
	          			</c:forEach>
	          		</td></tr>
				<c:forEach var="coInvestigator" items="${summary.coInvestigators}" varStatus="status">
	          	<tr><th width="20%" align="right" valign="middle">Investigator:</th><td width="30%" align="left" valign="middle">${coInvestigator.fullName}</td>
	          		<th width="20%" align="right" valign="middle">Unit(s):</th>
	          		<td width="30%" align="left" valign="middle">
	          			<c:forEach var="unit" items="${coInvestigator.units}" varStatus="unitStatus">${unit.unitNumber} : ${unit.unit.unitName}<br /></c:forEach>
	          			<c:if test="${empty coInvestigator.units}">&nbsp;</c:if>
					</td></tr>	
				</c:forEach>
				<c:forEach var="keyPerson" items="${summary.keyPersons}" varStatus="status">
	          	<tr><th width="20%" align="right" valign="middle">Key Person:</th><td width="30%" align="left" valign="middle">${keyPerson.fullName}</td>
	          		<th width="20%" align="right" valign="middle">Unit(s):</th>
	          		<td width="30%" align="left" valign="middle">
	          			<c:forEach var="unit" items="${keyPerson.units}" varStatus="unitStatus">${unit.unitNumber} : ${unit.unit.unitName}<br /></c:forEach>
	          			<c:if test="${empty keyPerson.units}">&nbsp;</c:if>
					</td></tr>	
				</c:forEach>
			</table>
		</div>
	</kra:innerTab>
	<br />
	<h3><span class="subhead-left">Budget Summary</span><span class="subhead-right"><!-- "Open Budget" button here? --></span></h3>
	
<%--
	
	<c:forEach var="budgetOverview" items="${summary.budgetVersionOverviews}"  varStatus="status">
		<kra:innerTab parentTab="${parentTabTitle}" tabTitle="${budgetOverview.documentDescription}" tabDescription="${budgetOverview.documentDescription}" defaultOpen="false">
			<div class="innerTab-container" align="left">
				<table cellpadding=0 cellspacing=0 summary="">
		          	<tr><th width="20%" align="right" valign="middle">Residual Funds:</th><td width="30%" align="left" valign="middle">${budgetOverview.residualFunds}&nbsp;</td>
		          		<th width="20%" align="right" valign="middle">F&amp;A Rate Type:</th><td width="30%" align="left" valign="middle">${budgetOverview.rateClass.description}&nbsp;</td></tr>
		          	<tr><th width="20%" align="right" valign="middle">Cost Sharing:</th><td width="30%" align="left" valign="middle">${budgetOverview.costSharingAmount}&nbsp;</td>
		          		<th width="20%" align="right" valign="middle">Last Updated:</th><td width="30%" align="left" valign="middle">${budgetOverview.updateTimestamp}&nbsp;</td></tr>	
					<tr><th width="20%" align="right" valign="middle">Unrecovered F&amp;A:</th><td width="30%" align="left" valign="middle">${budgetOverview.underrecoveryAmount}&nbsp;</td>
						<th width="20%" align="right" valign="middle">Last Updated By:</th><td width="30%" align="left" valign="middle">${budgetOverview.updateUser}&nbsp;</td></tr>
					<tr><th width="20%" align="right" valign="middle">Comments:</th><td width="80%" align="left" valign="middle" colspan="3">${budgetOverview.comments}&nbsp;</td></tr>
				</table>
			</div>
		</kra:innerTab>
	</c:forEach>

--%>



        <table id="budget-versions-table" cellpadding="0" cellspacing="0" summary="Budget Versions">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${budgetVersionOverviewAttributes.documentDescription}" useShortLabel="true" noColon="true" /></div></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.budgetVersionNumber}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalDirectCost}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalIndirectCost}" useShortLabel="true" noColon="true"/></th>
				<th>Total</th>
				<th>Budget Status</th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.finalVersionFlag}" useShortLabel="true" noColon="true"/></th>
			</tr>
			

			<c:forEach var="budgetOverview" items="${summary.budgetVersionOverviews}"  varStatus="status">
          	<%--<c:forEach var="budgetVersion" items="${budgetDocumentVersions}" varStatus="status">--%>
          		<c:set var="tabTitle" value="${status.index}" scope="request"/>
          		<c:set var="tabKey" value="${kfunc:generateTabKey(parentTabTitle)}:${kfunc:generateTabKey(tabTitle)}" scope="request"/>
          		<c:set var="currentTab" value="${kfunc:getTabState(KualiForm, tabKey)}"/>
          		<c:choose>
    				<c:when test="${empty currentTab}">
        				<c:set var="isOpen" value="false" />
    				</c:when>
    				<c:when test="${!empty currentTab}" >
        				<c:set var="isOpen" value="${currentTab == 'OPEN'}" />
    				</c:when>
				</c:choose>
				<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
					<c:set var="displayStyle" value="display: table-row-group;"/>
				</c:if>
				<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
					<c:set var="displayStyle" value="display: none;"/>
				</c:if>
          		<tr>
           			<td align="right" class="tab-subhead" scope="row">
           				<div align="center">
           					<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
                 				<html:image property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="close ${tabTitle}" alt="close ${tabTitle}" styleClass="tinybutton"  styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
               				</c:if>
               				<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
                 				<html:image  property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="open ${tabTitle}" alt="open ${tabTitle}" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
               				</c:if>
           				</div>
           			</td>
           			<td class="tab-subhead">${budgetOverview.documentDescription}</td>
	            	<td class="tab-subhead"><div align="center">${budgetOverview.budgetVersionNumber}</div></td>
		            <td class="tab-subhead"><div align="right">${budgetOverview.totalDirectCost}&nbsp;</div></td>
		            <td class="tab-subhead"><div align="right">${budgetOverview.totalIndirectCost}&nbsp;</div></td>
		            <td class="tab-subhead"><div align="right">${budgetOverview.totalCost}&nbsp;</div></td>
		            <td class="tab-subhead">
		            	<div align="center">
		            		<c:if test="${budgetOverview.finalVersionFlag}">${summary.budget}&nbsp;</c:if>
		            		<c:if test="${not budgetOverview.finalVersionFlag}">${summary.incompleteBudgetLabel}&nbsp;</c:if>
		            	</div>
            		</td>
	            	<td class="tab-subhead">
	            		<div align="center">${budgetOverview.finalVersionFlag}&nbsp;</div>
	            	</td>
         		</tr>
         		<tbody style="${displayStyle}">
	         		<tr>
            		<th align="right" scope="row">&nbsp;</th>
            		<td colspan="7" style="padding:0px; border-left:none">
            			<table cellpadding="0" cellspacing="0" summary="" style="width:100%;">
                			<tr>
	                    		<th width="1%" nowrap><div align="right">Residual Funds:</div></th>
	                    		<td align="left" width="12%">${budgetOverview.residualFunds}&nbsp;</td>
	                    		<th width="40%" nowrap><div align="right">F&A Rate Type:</div></th>
	                    		<td align="left" width="99%">${budgetOverview.rateClass.description}&nbsp;</td>
                  			</tr>
	                  		<tr>
	                    		<th nowrap><div align="right">Cost Sharing:</div></th>
	                    		<td align="left">${budgetOverview.costSharingAmount}&nbsp;</td>
	                    		<th nowrap><div align="right">Last Updated:</div></th>
	                    		<td align="left"><fmt:formatDate value="${budgetOverview.updateTimestamp}" type="both" />&nbsp;</td>
	                  		</tr>
				            <tr>
				                <th nowrap><div align="right">Unrecovered F&amp;A:</div></th>
				                <td align="left">${budgetOverview.underrecoveryAmount}&nbsp;</td>
				                <th nowrap><div align="right">Last Updated By:</div></th>
				                <td align="left">${budgetOverview.updateUser}&nbsp;</td>
				            </tr>
                 			<tr>
                   				<th nowrap><div align="right">Comments:</div></th>
                   				<td colspan="3" align="left">${budgetOverview.comments}&nbsp;</td>
                 			</tr>
           				</table>
           			</td>
         		</tr>
         		</tbody>
          	</c:forEach>
        </table>



</div>
