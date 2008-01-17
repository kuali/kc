<%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
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

<%@ attribute name="budgetVersionOverviews" required="true" type="java.util.List"%>

<c:set var="budgetAttributes" value="${DataDictionary.BudgetDocument.attributes}" />

<kul:tabTop tabTitle="Budget Versions (${KualiForm.document.requestedStartDateInitial} - ${KualiForm.document.requestedEndDateInitial})" defaultOpen="true" tabErrorKey="${Constants.DOCUMENT_ERRORS}">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Budget Versions</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        <table cellpadding="0" cellspacing="0" summary="Budget Versions">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><div align="left">*Name:</div></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.versionNumber}" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalDirectCost}" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalIndirectCost}" /></th>
				<th>Total:</th>
				<th>Status:</th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.finalVersionFlag}" /></th>
				<th><div align="center">Action</div></th>
			</tr>
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
            			<html:image property="methodToCall.addBudgetVersion" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
				</td>
          	</tr>
          	<c:forEach var="budgetVersion" items="${budgetVersionOverviews}" varStatus="status">
          		<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>
          		<c:set var="parentTab" value="Budget Versions" scope="request"/>
          		<c:set var="tabTitle" value="${status.index}" scope="request"/>
          		<c:set var="tabKey" value="${kfunc:generateTabKey(parentTab)}:${kfunc:generateTabKey(tabTitle)}" scope="request"/>
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
           			<td align="right" class="tab-subhead1" scope="row">
           				<div align="center">
           					<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
                 				<html:image property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="close ${tabTitle}" alt="close ${tabTitle}" styleClass="tinybutton"  styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
               				</c:if>
               				<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
                 				<html:image  property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="open ${tabTitle}" alt="open ${tabTitle}" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
               				</c:if>
           				</div>
           			</td>
           			<td class="tab-subhead1">${budgetVersion.documentDescription}</td>
	            	<td class="tab-subhead1"><div align="center">${budgetVersion.budgetVersionNumber}</div></td>
		            <td class="tab-subhead1"><div align="right">${budgetVersion.totalDirectCost}</div></td>
		            <td class="tab-subhead1"><div align="right">${budgetVersion.totalIndirectCost}</div></td>
		            <td class="tab-subhead1"><div align="right">${budgetVersion.totalCost}</div></td>
		            <td class="tab-subhead1">
		            	<div align="center">
			              <select onchange="dataChanged()" name="activityType">
			                <option>select</option>
			                <option selected>incomplete</option>
			                <option>complete</option>
			              </select>
		            	</div>
            		</td>
	            	<td class="tab-subhead1">
	            		<div align="center">
	            			<html:radio name="KualiForm" property="finalBudgetVersion" value="${budgetVersion.budgetVersionNumber}"/>
	            		</div>
	            	</td>
           			<td nowrap class="tab-subhead1">
           				<div align=center>
           					<html:image property="methodToCall.openBudgetVersion.line${status.index}.x" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-open.gif' alt="open budget" />
           					<img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-copy2.gif" alt="copy budget" width=40 height=15 hspace=3 vspace=0 border=0>
           				</div>
           			</td>
         		</tr>
         		<tbody style="${displayStyle}">
         		<tr>
            		<th align="right" scope="row">&nbsp;</th>
            		<td colspan="8" style="padding:0px; border-left:none">
            			<table cellpadding="0" cellspacing="0" summary="" style="width:100%;">
                			<tr>
	                    		<th width="1%" nowrap><div align="right">Residual Funds:</div></th>
	                    		<td align="left" width="12%">&nbsp;${budgetVersion.residualFunds}</td>
	                    		<th width="40%" nowrap><div align="right">OH Rate Type:</div></th>
	                    		<td align="left" width="99%">${budgetVersion.ohRateTypeCode}</td>
                  			</tr>
	                  		<tr>
	                    		<th nowrap><div align="right">Cost Sharing:</div></th>
	                    		<td align="left">${budgetVersion.costSharingAmount}</td>
	                    		<th nowrap><div align="right">Last Updated:</div></th>
	                    		<td align="left">${budgetVersion.updateTimestamp}</td>
	                  		</tr>
				            <tr>
				                <th nowrap><div align="right">Unrecovered F&amp;A:</div></th>
				                <td align="left">${budgetVersion.underrecoveryAmount}</td>
				                <th nowrap><div align="right">Last Updated By:</div></th>
				                <td align="left">${budgetVersion.updateUser}</td>
				            </tr>
                 			<tr>
                   				<th nowrap><div align="right">Comments:</div></th>
                   				<td colspan="3" align="left">${budgetVersion.comments}</td>
                 			</tr>
           				</table>
           			</td>
         		</tr>
         		</tbody>
          	</c:forEach>
        </table>
	</div>
</kul:tabTop>
<kul:panelFooter />
