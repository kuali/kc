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

<c:if test="${!proposalBudgetFlag}">
<script language="javascript">
  //well IE7 is super helpful and doesn't support Array.indexOf(used below) so here we implement
  //our own if there isn't one.
  if(!Array.indexOf){
    Array.prototype.indexOf = function(obj){
        for(var i=0; i<this.length; i++){
            if(this[i]==obj){
                return i;
            }
        }
        return -1;
    }
  }

  function displayOfInactiveStatuses(checkbox) {
	  var statuses = [
	        <c:forEach items="${KualiForm.awardBudgetInactiveStatuses}" var="statusCode" varStatus="idx">
	           '${statusCode}'
	           <c:if test="${idx.index < fn:length(KualiForm.awardBudgetInactiveStatuses)-1}">
	              ,
	           </c:if>
		   </c:forEach>
		   ];
	  jQuery('#budget-versions-table').find('select[name$="awardBudgetStatusCode"]').each(function() {
		  if (statuses.indexOf(jQuery(this).val()) != -1) {
			  if (jQuery(checkbox).is(':checked')) {
			  	jQuery(this).parent().parent().parent().parent().show();
			  	if (jQuery(this).parent().parent().parent().parent().find('input[name*="tabStates"]').val() == 'OPEN') {
			  		jQuery(this).parent().parent().parent().parent().next().show();
			  	}
			  } else {
				jQuery(this).parent().parent().parent().parent().hide();
				jQuery(this).parent().parent().parent().parent().next().hide();
			  }
		  }
	  });
  }

  jQuery(document).ready(function() {
	  jQuery('tr.showAllRow').show();
	  displayOfInactiveStatuses(jQuery('input[name=showAllBudgetVersions]'));
	  jQuery('input[name=showAllBudgetVersions]').click(function () {
		  displayOfInactiveStatuses(this);
	  });
  });
</script>
</c:if>

<%@ attribute name="budgetDocumentVersions" required="true" type="java.util.List"%>
<%@ attribute name="pathToVersions" required="true"%>
<%@ attribute name="requestedStartDateInitial" required="true" %>
<%@ attribute name="requestedEndDateInitial" required="true" %>
<%@ attribute name="errorKey" required="false"%>
<%@ attribute name="hierarchyParentBudgetIsComplete" required="true"%>

<c:set var="budgetAttributes" value="${DataDictionary.Budget.attributes}" />
<c:set var="awardBudgetAttributes" value="${DataDictionary.AwardBudgetExt.attributes}" />
<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="budgetVersionOverviewAttributes" value="${DataDictionary.BudgetVersionOverview.attributes}" />
<c:set var="javascriptEnabled" value="true" />
<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" scope="request" />
<c:set var="readonly" value="true"/>
<kra:section permission="modifyProposalBudget">
	<c:set var="readonly" value="false"/>
</kra:section> 
<c:if test="${hierarchyParentBudgetIsComplete}">
	<c:set var="readonly" value="true"/> 	
</c:if>
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>
<c:set var="projectDatesString" value=""/>
 <c:set var="useRiceAuditMode" value="true" scope="request" />

	<c:set var="transParent" value="false"/>
<c:choose>	
<c:when test="${proposalBudgetFlag}">
	<c:set var="projectDatesString" value="(${KualiForm.formattedStartDate} - ${KualiForm.formattedEndDate})"/>
	<%--instead of using kul:tabTop tag just define the workarea div - this gets around an unbalanced tag problem when using conditional tags --%>
	<div id="workarea">
	<c:set var="transParent" value="true"/>
</c:when>
<c:otherwise>
   <c:choose>
    <c:when test="${not empty awardBudgetPage}">
	  <c:set var="projectDatesString" value ="(${KualiForm.document.award.awardIdAccount})" />
    </c:when>
    <c:otherwise> 
	  <div id="workarea">
	  <c:set var="transParent" value="true"/>
	  <c:set var="projectDatesString" value ="(${KualiForm.document.parentDocument.award.awardIdAccount})" />
    </c:otherwise>
   </c:choose>
</c:otherwise>
</c:choose>

<kul:tab tabTitle="Budget Versions ${projectDatesString}"  transparentBackground="${transParent}" defaultOpen="true" tabErrorKey="document.budget.parentDocument.budgetParent.budgetVersion*,${Constants.DOCUMENT_ERRORS},${errorKey},document.budgetDocumentVersion[*" auditCluster="awardBudgetTotalCostAuditErrors" tabAuditKey="document.budget.totalCost">

	<div class="tab-container" align="center">

		<c:forEach var="warning" items="${budgetModularWarnings}">
			<ul class="warnings">
				<li class="warnings"><c:out value="${warning}"/></li>
			</ul>
		</c:forEach>

    	<h3>
            <span class="subhead-left">Budget Versions</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.versions.BudgetVersionOverview" altText="help"/></span>
        </h3>
        <table id="budget-versions-table" cellpadding="0" cellspacing="0" summary="Budget Versions">
            <thead>
			<tr>
				<th scope="row">&nbsp;</th>
				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${budgetVersionOverviewAttributes.documentDescription}" useShortLabel="true" noColon="true" /></div></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.budgetVersionNumber}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalDirectCost}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalIndirectCost}" useShortLabel="true" noColon="true"/></th>
				<th>Total</th>
				<th>Budget Status</th>
          		<c:choose>
    				<c:when test="${proposalBudgetFlag}">
						<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.finalVersionFlag}" useShortLabel="true" noColon="true"/></th>
					</c:when>
					<c:otherwise>
						<th><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.awardBudgetTypeCode}" useShortLabel="true" noColon="true"/></th>
					</c:otherwise>
				</c:choose>
				<kra:section permission="openBudgets">
				    <th><div align="center">Actions</div></th>
				</kra:section>
			</tr>
			
			<kra:section permission="addBudget">
			<tr>
            	<th width="50" align="right" scope="row"><div align="right">Add:</div></th>
            	<td class="infoline"><label><html:text name="KualiForm" property="newBudgetVersionName" size="16"/></label></td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
            	<td class="infoline">&nbsp;</td>
	            <td class="infoline">
            		<div align=center>
            		<c:choose>
    					<c:when test="${proposalBudgetFlag}">
            				<html:image property="methodToCall.addBudgetVersion" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
            			</c:when>
						<c:otherwise>
    				    	<html:image property="methodToCall.addBudgetVersion" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-new38.gif' />
    				    	<%-- <html:image property="methodToCall.rebudget" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-rebudget.gif' /> --%>
    					</c:otherwise>
					</c:choose>
					</div>
				</td>
          	</tr>
          	</kra:section>
          	</thead>
          	
          	<c:forEach var="budgetVersion" items="${budgetDocumentVersions}" varStatus="status">
          		<c:set var="version" value="${pathToVersions}.budgetDocumentVersion[${status.index}].budgetVersionOverview" />
          		<bean:define id="descriptionUpdatable" name="KualiForm" property="${pathToVersions}.budgetDocumentVersion[${status.index}].budgetVersionOverview.descriptionUpdatable" />
          		<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>
          		<c:set var="parentTab" value="Budget Versions" scope="request"/>
          		<c:set var="tabTitle" value="${status.index}" scope="request"/>
          		<c:set var="tabKey" value="${kfunc:generateTabKey(parentTab)}:${kfunc:generateTabKey(tabTitle)}" scope="request"/>
				<c:set var="versionTab" value="${tabKey}${status.index}"/>
          		<c:set var="currentTab" value="${kfunc:getTabState(KualiForm, versionTab)}"/>
          		<c:choose>
    				<c:when test="${empty currentTab}">
        				<c:set var="isOpen" value="true" />
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
					
				<tbody>
          		<tr class="budgetline">
           			<td align="right" class="tab-subhead" scope="row">
           				<div align="center">
           				    <html:hidden property="tabStates(${versionTab})" value="${(isOpen ? 'OPEN' : 'CLOSE')}" />
           					<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
                 				<html:image property="methodToCall.toggleTab.tab${versionTab}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="close ${tabTitle}" alt="close ${tabTitle}" styleClass="tinybutton"  styleId="tab-${versionTab}-imageToggle" onclick="javascript: return toggleTab(document, '${versionTab}'); " />
               				</c:if>
               				<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
                 				<html:image  property="methodToCall.toggleTab.tab${versionTab}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="open ${tabTitle}" alt="open ${tabTitle}" styleClass="tinybutton" styleId="tab-${versionTab}-imageToggle" onclick="javascript: return toggleTab(document, '${versionTab}'); " />
               				</c:if>
           				</div>
           			</td>
           			<td class="tab-subhead"><kul:htmlControlAttribute property="${version}.documentDescription" attributeEntry="${budgetVersionOverviewAttributes.documentDescription}" readOnly="${descriptionUpdatable != 'Yes'}"/></td>
	            	<td class="tab-subhead"><div align="center">${budgetVersion.budgetVersionOverview.budgetVersionNumber}</div></td>
		            <td class="tab-subhead"><div align="right">&nbsp;<kul:htmlControlAttribute property="${version}.totalDirectCost" attributeEntry="${budgetAttributes.totalDirectCost}" styleClass="amount" readOnly="true"/></div></td>
		            <td class="tab-subhead"><div align="right">&nbsp;<kul:htmlControlAttribute property="${version}.totalIndirectCost" attributeEntry="${budgetAttributes.totalIndirectCost}" styleClass="amount" readOnly="true"/></div></td>
		            <td class="tab-subhead"><div align="right">&nbsp;<kul:htmlControlAttribute property="${version}.totalCost" attributeEntry="${budgetAttributes.totalCost}" styleClass="amount" readOnly="true"/></div></td>
		            <td class="tab-subhead">
		            	<div align="center">
		            		<!--  This field is to hold select status if it's disabled by javascript -->
			          		<c:choose>
			    				<c:when test="${proposalBudgetFlag}">
		            		        <html:hidden name="KualiForm" property="${version}.budgetStatus" disabled="true" />
				            		<kul:htmlControlAttribute property="${version}.budgetStatus" attributeEntry="${proposalDevelopmentAttributes.budgetStatus}" onchange="javascript: toggleFinalCheckboxes(document)" disabled="${not (KualiForm.editingMode['modifyCompletedBudgets'] || KualiForm.editingMode['modifyProposalBudget'])}"/>
				            	<%--	<c:if test="${not (KualiForm.editingMode['modifyCompletedBudgets'] || KualiForm.editingMode['modifyProposalBudget'])}">
				            			<html:hidden name="KualiForm" property="${version}.budgetStatus" disabled="true" />
				            		</c:if> --%>
			    				</c:when>
			    				<c:otherwise>
				            		<kul:htmlControlAttribute property="${version}.awardBudgetStatusCode" attributeEntry="${awardBudgetAttributes.awardBudgetStatusCode}" onchange="javascript: toggleFinalCheckboxes(document)" disabled="${readonly}"/>
			    				</c:otherwise>
							</c:choose>
		            	</div>
            		</td>
		            	<td class="tab-subhead">
		            		<div align="center">
				          		<c:choose>
				    				<c:when test="${proposalBudgetFlag}">
				            			<kul:htmlControlAttribute property="${version}.finalVersionFlag" attributeEntry="${budgetAttributes.finalVersionFlag}" onclick="javascript: enableBudgetStatus(document, '${status.index}')" disabled="${readonly}"/>
				            			<!--  This field is to hold checkbox status if it's disabled by javascript -->
					           			<html:hidden name="KualiForm" property="${version}.finalVersionFlag" disabled="true" />
				            		</c:when>
				            		<c:otherwise>
				            			<kul:htmlControlAttribute property="${version}.awardBudgetTypeCode" attributeEntry="${awardBudgetAttributes.awardBudgetTypeCode}" disabled="${readonly}"/>
				            		</c:otherwise>
				            	</c:choose>
		            		</div>
		            	</td>
	            	<kra:section permission="openBudgets">
	           			<td nowrap class="tab-subhead">
	           				<div align=center>
	           					<html:image property="methodToCall.openBudgetVersion.line${status.index}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-open.gif' alt="open budget" />
	           					<kra:section permission="addBudget">
	           					    <html:image property="methodToCall.copyBudgetVersion.line${status.index}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-copy2.gif' alt="copy budget" />
	           				    </kra:section>
	           				</div>
	           			</td>
	           		</kra:section>
         		</tr>
         		</tbody>
         		<tbody style="${displayStyle}" id = "tab-${versionTab}-div">
	         		<tr>
            		<th align="right" scope="row">&nbsp;</th>
            		<td colspan="8" style="padding:0px; border-left:none">
            			<table cellpadding="0" cellspacing="0" summary="" style="width:100%;">
	                		<tr>
							   	<c:choose>
									<c:when test="${proposalBudgetFlag}">
		                    			<th width="1%" nowrap><div align="right">Residual Funds:</div></th>
		                    			<td align="left" width="12%">${budgetVersion.budgetVersionOverview.residualFunds}&nbsp;</td>
									</c:when>
									<c:otherwise>
		                    			<c:choose>
											<c:when test="${not empty awardBudgetPage}">
												<th width="1%" nowrap>Award Version</th>
		                    					<td align="left" width="12%">${budgetVersion.parentDocument.award.sequenceNumber}</td>
											</c:when>
											<c:otherwise>
												<th width="1%" nowrap>&nbsp;</th>
		                    					<td align="left" width="12%">&nbsp;</td>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
	                    		<th width="40%" nowrap><div align="right">F&A Rate Type:</div></th>
	                    		<td align="left" width="99%">${budgetVersion.budgetVersionOverview.rateClass.description}&nbsp;</td>
                  			</tr>
	                  		<tr>
	                    		<th nowrap><div align="right">Cost Sharing:</div></th>
	                    		<td align="left">${budgetVersion.budgetVersionOverview.costSharingAmount}&nbsp;</td>
	                    		<th nowrap><div align="right">Last Updated:</div></th>
	                    		<td align="left"><fmt:formatDate value="${budgetVersion.budgetVersionOverview.updateTimestamp}" type="both" />&nbsp;</td>
	                  		</tr>
				            <tr>
				                <th nowrap><div align="right">Unrecovered F&amp;A:</div></th>
				                <td align="left">${budgetVersion.budgetVersionOverview.underrecoveryAmount}&nbsp;</td>
				                <th nowrap><div align="right">Last Updated By:</div></th>
				                <td align="left">${budgetVersion.budgetVersionOverview.updateUser}&nbsp;</td>
				            </tr>
                 			<tr>
                   				<th nowrap><div align="right">Comments:</div></th>
                   				<td colspan="3" align="left">${budgetVersion.budgetVersionOverview.comments}&nbsp;</td>
                 			</tr>
           				</table>
           			</td>
         		</tr>
         		</tbody>
          	</c:forEach>
            <c:if test="${!proposalBudgetFlag}">
          	<tr class="showAllRow" style="display:none;">
          	  <td class="infoline" style="text-align:right;" colspan="9"><html:checkbox name="KualiForm" property="showAllBudgetVersions">Show All Budgets</html:checkbox></td>
          	</tr>
          	</c:if>
        </table>
	</div> 
</kul:tab>
<c:if test="${proposalBudgetFlag or empty awardBudgetPage}">
  <kul:panelFooter />
</c:if>
<c:if test="${proposalBudgetFlag}">
<c:choose>                    	
	<c:when test="${readonly}">
		<img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" onLoad="toggleFinalCheckboxesAndDisable(document);" />
 	</c:when>
	<c:otherwise>
		<img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" onLoad="toggleFinalCheckboxes(document); setupBudgetStatuses(document);" />
	</c:otherwise>
</c:choose>
</c:if>