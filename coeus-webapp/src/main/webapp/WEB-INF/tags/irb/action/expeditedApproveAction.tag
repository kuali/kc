<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<%@ attribute name="tabTitle" required="true" %>
<%@ attribute name="bean" required="true" type="org.kuali.kra.irb.actions.approve.ProtocolApproveBean" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="taskName" required="true" %>
<%@ attribute name="methodToCall" required="true" %>
<%@ attribute name="canPerformAction" required="true" %>
<%@ attribute name="defaultOpen" required="false" %>

<script type="text/javascript">
$j(document).ready(
	function(){
		// initial state
		enableDisableCheckbox();
	}
);
// function
function enableDisableCheckbox() {
	var choice = document.getElementById("${property}.assignToAgenda");
	var sched = document.getElementById("${property}.scheduleId");
	var schedChoice = true;
	if (choice != null && choice.checked) {
		//sched.disabled = false;
		schedChoice = false;
	//} else {
	//	sched.disabled = true;
	}
	if(sched != null) {
		sched.disabled = schedChoice;
	}
}
</script>

<c:set var="attributes" value="${DataDictionary.ProtocolApproveBean.attributes}" />
<c:set var="scheduleAttr" value="${DataDictionary.ProtocolAssignCmtSchedBean.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="datesReadOnly" value="${(KualiForm.actionHelper.protocol.FYI or KualiForm.actionHelper.protocol.amendment) and not KualiForm.actionHelper.protocol.renewal}" />

<c:if test="${empty defaultOpen}">
    <c:set var="defaultOpen" value="false" />
</c:if>

<kra:permission value="${canPerformAction}">

<kul:innerTab tabTitle="${tabTitle}" parentTab="" defaultOpen="${defaultOpen}" tabErrorKey="${property}*">
   
   <kra-irb-action:padLeft>
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
            
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                                <kul:htmlAttributeLabel attributeEntry="${attributes.approvalDate}" />
                            </nobr>
                        </div>
                    </th>
                    <td colspan="5">
                    	<html:hidden styleId="defaultExpirationDateDifference" property="${property}.defaultExpirationDateDifference" />
                        <nobr>
                            <kul:htmlControlAttribute property="${property}.approvalDate" 
                                                      attributeEntry="${attributes.approvalDate}" 
                                                      readOnly="${datesReadOnly}"                                                      
                                                      onchange="loadExpeditedDates('${property}.approvalDate', '${property}.expirationDate', 'defaultExpirationDateDifference');" />
                                                      
                        </nobr>
                    </td>
                </tr>
                
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                                <kul:htmlAttributeLabel attributeEntry="${attributes.expirationDate}" />
                            </nobr>
                        </div>
                    </th>
                    <td colspan="5">
                        <nobr>
                            <kul:htmlControlAttribute property="${property}.expirationDate" 
                                                      attributeEntry="${attributes.expirationDate}" 
                                                      readOnly="${datesReadOnly}" />
                        </nobr>
                    </td>
                </tr>
                
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                                <kul:htmlAttributeLabel attributeEntry="${attributes.comments}" />
                            </nobr>
                        </div>
                    </th>
                    <td colspan="5">
                        <nobr>
                            <kul:htmlControlAttribute property="${property}.comments" 
                                                      attributeEntry="${attributes.comments}" />
                        </nobr>
                    </td>
                </tr>
                
                <tr>

                	<th>
               			<div align="right">
                           	<nobr>
                           		Include in an agenda?
                           	</nobr>
                    	</div>
               		</th>
               		<td width="5%">
               			<kul:htmlControlAttribute property="${property}.assignToAgenda" attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" onchange="javascript: enableDisableCheckbox();" />
               		</td>

	            	<th width="10%"> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${scheduleAttr.committeeId}" />
	                    </div>
	                </th>
	                <td width="10%">
		               <kul:htmlControlAttribute property="${property}.committeeName" attributeEntry="${kraAttributeReferenceDummyAttributes.description}" readOnly="${true}" />
	                </td>
	            	<th width="10%"> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${scheduleAttr.scheduleId}" />
	                    </div>
	                </th>
	                <td>
		               <nobr>
				           <kul:htmlControlAttribute property="${property}.scheduleId" attributeEntry="${scheduleAttr.scheduleId}" />
				           <noscript>
                               <html:image property="methodToCall.refreshPage.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif' styleClass="tinybutton"/>
                           </noscript>
		               </nobr>
	                </td>
	            </tr>
                
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                                <kul:htmlAttributeLabel attributeEntry="${attributes.actionDate}" />
                            </nobr>
                        </div>
                    </th>
                    <td colspan="5">
                        <nobr>
                            <kul:htmlControlAttribute property="${property}.actionDate" 
                                                      attributeEntry="${attributes.actionDate}"  />
                        </nobr>
                    </td>
                </tr>
                
                <tr>
                    <td colspan="6">
                        <kra-irb-action:riskLevel bean="${bean.protocolRiskLevelBean}"
                                                  property="${property}.protocolRiskLevelBean"
                                                  action="${action}" 
                                                  taskName="${taskName}" />
                    </td>
                </tr>
                
                <tr>
                    <td colspan="6">
                        <kra-irb-action:reviewComments bean="${bean.reviewCommentsBean}"
                                                       property="${property}.reviewCommentsBean"
                                                       action="${action}"
                                                       taskName="${taskName}" />
                   </td>
                </tr>
                
                <tr>
                    <td align="center" colspan="6">
                        <div align="center">
                            <html:image property="methodToCall.${methodToCall}.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' 
                                        styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>       
   </kra-irb-action:padLeft>
    
</kul:innerTab>

</kra:permission>
