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

<%@ attribute name="budgetDocumentVersions" required="true" type="java.util.List"%>
<%@ attribute name="pathToVersions" required="true"%>
<%@ attribute name="requestedStartDateInitial" required="true" %>
<%@ attribute name="requestedEndDateInitial" required="true" %>
<%@ attribute name="errorKey" required="false"%>

<c:set var="budgetAttributes" value="${DataDictionary.Budget.attributes}" />
<c:set var="awardBudgetAttributes" value="${DataDictionary.AwardBudgetExt.attributes}" />
<c:set var="javascriptEnabled" value="true" />
<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" scope="request" />
<c:set var="readonly" value="true"/>
<kra:section permission="modifyProposalBudget">
	<c:set var="readonly" value="false"/>
</kra:section>
<c:set var="projectDatesString" value=""/>
 <c:set var="useRiceAuditMode" value="true" scope="request" />

	<c:set var="transParent" value="false"/>

   <c:choose>
    <c:when test="${not empty awardBudgetPage}">
	  <c:set var="projectDatesString" value ="(${KualiForm.document.award.awardIdAccount})" />
    </c:when>
    <c:otherwise>
	  <div id="workarea">
	  <c:set var="transParent" value="true"/>
	  <c:set var="projectDatesString" value ="(${KualiForm.document.budget.award.awardIdAccount})" />
    </c:otherwise>
   </c:choose>

<kul:tab tabTitle="Budget Versions ${projectDatesString}"  transparentBackground="${transParent}" defaultOpen="true" tabErrorKey="document.budget.budgetParent.budgetVersion*,${Constants.DOCUMENT_ERRORS},${errorKey},document.budgetDocumentVersion[*" auditCluster="awardBudgetTotalCostAuditErrors" tabAuditKey="document.budget.totalCost">

	<div class="tab-container" align="center">

		<c:forEach var="warning" items="${budgetModularWarnings}">
			<ul class="warnings">
				<li class="warnings"><c:out value="${warning}"/></li>
			</ul>
		</c:forEach>

    	<h3>
            <span class="subhead-left">Budget Versions</span>

            <span class="subhead-right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetVersionsHelpUrl" altText="help"/></span>
         </h3>
        <table id="budget-versions-table" cellpadding="0" cellspacing="0" summary="Budget Versions">
            <thead>
			<tr>
				<th scope="row">&nbsp;</th>
				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.name}" useShortLabel="true" noColon="true" /></div></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.budgetVersionNumber}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalDirectCost}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalIndirectCost}" useShortLabel="true" noColon="true"/></th>
				<th>Total</th>
				<th>Budget Status</th>
					<th><kul:htmlAttributeLabel attributeEntry="${awardBudgetAttributes.awardBudgetTypeCode}" useShortLabel="true" noColon="true"/></th>
				<kra:section permission="openBudgets">
				    <th><div align="center">Actions</div></th>
				</kra:section>
			</tr>

			<kra:section permission="addBudget">
			<tr class="addline">
            	<th width="50" align="right" scope="row"><div align="right">Add:</div></th>
            	<td class="infoline"><label><html:text name="KualiForm" property="newBudgetVersionName" size="16" maxlength="40"/></label></td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
            	<td class="infoline">&nbsp;</td>
	            <td class="infoline">
            		<div align=center>
   				    	<html:image property="methodToCall.addBudgetVersion" styleClass="addButton" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-new38.gif' />
					</div>
				</td>
          	</tr>
          	</kra:section>
          	</thead>

          	<c:forEach var="budgetVersion" items="${budgetDocumentVersions}" varStatus="status">
          		<c:set var="version" value="${pathToVersions}[${status.index}]" />
          		<c:set var="descriptionUpdatable" value="${budgetVersion.nameUpdatable}" />
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
           			<td class="tab-subhead"><kul:htmlControlAttribute property="${version}.name" attributeEntry="${budgetAttributes.name}" readOnly="${!descriptionUpdatable}"/></td>
	            	<td class="tab-subhead"><div align="center">${budgetVersion.budgetVersionNumber}</div></td>
		            <td class="tab-subhead"><div align="right">&nbsp;<kul:htmlControlAttribute property="${version}.totalDirectCost" attributeEntry="${budgetAttributes.totalDirectCost}" styleClass="amount" readOnly="true"/></div></td>
		            <td class="tab-subhead"><div align="right">&nbsp;<kul:htmlControlAttribute property="${version}.totalIndirectCost" attributeEntry="${budgetAttributes.totalIndirectCost}" styleClass="amount" readOnly="true"/></div></td>
		            <td class="tab-subhead"><div align="right">&nbsp;<kul:htmlControlAttribute property="${version}.totalCost" attributeEntry="${budgetAttributes.totalCost}" styleClass="amount" readOnly="true"/></div></td>
		            <td class="tab-subhead">
		            	<div align="center">
		            		<kul:htmlControlAttribute property="${version}.awardBudgetStatusCode" attributeEntry="${awardBudgetAttributes.awardBudgetStatusCode}" disabled="true"/>
		            	</div>
            		</td>
		            	<td class="tab-subhead">
		            		<div align="center">
	            				<kul:htmlControlAttribute property="${version}.awardBudgetTypeCode" attributeEntry="${awardBudgetAttributes.awardBudgetTypeCode}" disabled="true"/>
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
									<c:when test="${not empty awardBudgetPage}">
										<th width="1%" nowrap>Award Version</th>
                    					<td align="left" width="12%">${budgetVersion.award.sequenceNumber}</td>
									</c:when>
									<c:otherwise>
										<th width="1%" nowrap>&nbsp;</th>
                    					<td align="left" width="12%">&nbsp;</td>
									</c:otherwise>
								</c:choose>
	                    		<th width="40%" nowrap><div align="right">F&A Rate Type:</div></th>
	                    		<td align="left" width="99%">${budgetVersion.rateClass.description}&nbsp;</td>
                  			</tr>
	                  		<tr>
	                    		<th nowrap><div align="right">Cost Sharing:</div></th>
	                    		<td align="left">${budgetVersion.costSharingAmount}&nbsp;</td>
	                    		<th nowrap><div align="right">Last Updated:</div></th>
	                    		<td align="left"><fmt:formatDate value="${budgetVersion.updateTimestamp}" type="both" />&nbsp;</td>
	                  		</tr>
				            <tr>
				                <th nowrap><div align="right">Unrecovered F&amp;A:</div></th>
				                <td align="left">${budgetVersion.underrecoveryAmount}&nbsp;</td>
				                <th nowrap><div align="right">Last Updated By:</div></th>
				                <td align="left">${budgetVersion.updateUser}&nbsp;</td>
				            </tr>
                 			<tr>
                   				<th nowrap><div align="right">Comments:</div></th>
                   				<td colspan="3" align="left">${budgetVersion.comments}&nbsp;</td>
                 			</tr>
           				</table>
           			</td>
         		</tr>
         		</tbody>
          	</c:forEach>
          	<tr class="showAllRow" style="display:none;">
          	  <td class="infoline" style="text-align:right;" colspan="9"><html:checkbox name="KualiForm" property="showAllBudgetVersions">Show All Budgets</html:checkbox></td>
          	</tr>
        </table>
	</div>
</kul:tab>
<c:if test="${empty awardBudgetPage}">
  <kul:panelFooter />
</c:if>