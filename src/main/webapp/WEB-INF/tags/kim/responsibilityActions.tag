<%--
 Copyright 2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="mbrIdx" required="true" %>
<c:set var="roleMember" value="${KualiForm.document.members[mbrIdx]}"/>
<c:set var="docRoleRspActionAttributes" value="${DataDictionary.KimDocumentRoleResponsibilityAction.attributes}" />

<kul:subtab lookedUpCollectionName="roleRspActions" noShowHideButton="true" width="${tableWidth}" subTabTitle="Responsibility Actions">      
    <table cellpadding="0" cellspacing="0" summary="">
      	<tr>
            <th width="5%" rowspan="20" style="border-style:none">&nbsp;</th>
			<kul:htmlAttributeHeaderCell literalLabel="Name"  align="center"/>
			<kul:htmlAttributeHeaderCell attributeEntry="${docRoleRspActionAttributes.actionTypeCode}"  align="center"/>
         	<kul:htmlAttributeHeaderCell attributeEntry="${docRoleRspActionAttributes.priorityNumber}"  align="center" />
         	<kul:htmlAttributeHeaderCell attributeEntry="${docRoleRspActionAttributes.actionPolicyCode}"  align="center" />
         	<kul:htmlAttributeHeaderCell attributeEntry="${docRoleRspActionAttributes.forceAction}"  align="center" />
       	</tr>
		<c:forEach var="roleRspAction" items="${roleMember.roleRspActions}" varStatus="actionStatus">
           	<tr>	
				<td>
					All
        		</td>
				<td>
					<div align="center">
		            	<kul:htmlControlAttribute property="document.members[${mbrIdx}].roleRspActions[${actionStatus.index}].actionTypeCode"  attributeEntry="${docRoleRspActionAttributes.actionTypeCode}" readOnlyAlternateDisplay="${fn:escapeXml(roleRspAction.actionTypeDescription)}" readOnly="${readOnly}" />
		            </div>
        		</td>
        		<td>
	        		<div align="center">
		            	<kul:htmlControlAttribute property="document.members[${mbrIdx}].roleRspActions[${actionStatus.index}].priorityNumber"  attributeEntry="${docRoleRspActionAttributes.priorityNumber}" readOnly="${readOnly}" />
	        		</div>
        		</td>
        		<td>
	        		<div align="center">
		            	<kul:htmlControlAttribute property="document.members[${mbrIdx}].roleRspActions[${actionStatus.index}].actionPolicyCode"  attributeEntry="${docRoleRspActionAttributes.actionPolicyCode}" readOnlyAlternateDisplay="${fn:escapeXml(roleRspAction.actionPolicyDescription)}" readOnly="${readOnly}" />
	        		</div>
        		</td>
	       		<td>
	        		<div align="center">
		            	<kul:htmlControlAttribute property="document.members[${mbrIdx}].roleRspActions[${actionStatus.index}].forceAction"  attributeEntry="${docRoleRspActionAttributes.forceAction}" readOnly="${readOnly}" />
	        		</div>
	       		</td>
		    </tr>
		</c:forEach>
		<tr>
            <td colspan="7" style="padding:0px; border-style:none; height:22px; background-color:#F6F6F6">&nbsp;</td>
        </tr>		        	
	</table>       
</kul:subtab>
