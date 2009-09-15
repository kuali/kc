<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="roleIdx" required="true" %>
<%@ attribute name="mbrIdx" required="true" %>
<c:set var="rolePrncpl" value="${KualiForm.document.roles[roleIdx].rolePrncpls[mbrIdx]}"/>
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
		<c:forEach var="roleRspAction" items="${rolePrncpl.roleRspActions}" varStatus="actionStatus">
           	<tr>	
				<td>
					<div align="center">
					${roleRspAction.roleResponsibility.kimResponsibility.template.name}
					${roleRspAction.roleResponsibility.kimResponsibility.name}
		            </div>
        		</td>
				<td>
					<div align="center">
		            	<kul:htmlControlAttribute property="document.roles[${roleIdx}].rolePrncpls[${mbrIdx}].roleRspActions[${actionStatus.index}].actionTypeCode"  attributeEntry="${docRoleRspActionAttributes.actionTypeCode}" readOnlyAlternateDisplay="${roleRspAction.actionTypeDescription}"/>
		            </div>
        		</td>
        		<td>
	        		<div align="center">
		            	<kul:htmlControlAttribute property="document.roles[${roleIdx}].rolePrncpls[${mbrIdx}].roleRspActions[${actionStatus.index}].priorityNumber"  attributeEntry="${docRoleRspActionAttributes.priorityNumber}" />
	        		</div>
        		</td>
        		<td>
	        		<div align="center">
		            	<kul:htmlControlAttribute property="document.roles[${roleIdx}].rolePrncpls[${mbrIdx}].roleRspActions[${actionStatus.index}].actionPolicyCode"  attributeEntry="${docRoleRspActionAttributes.actionPolicyCode}" readOnlyAlternateDisplay="${roleRspAction.actionPolicyDescription}"/>
	        		</div>
        		</td>
	       		<td>
	        		<div align="center">
		            	<kul:htmlControlAttribute property="document.roles[${roleIdx}].rolePrncpls[${mbrIdx}].roleRspActions[${actionStatus.index}].forceAction"  attributeEntry="${docRoleRspActionAttributes.forceAction}" />
	        		</div>
	       		</td>
		    </tr>
		</c:forEach>
		<tr>
            <td colspan="7" style="padding:0px; border-style:none; height:22px; background-color:#F6F6F6">&nbsp;</td>
        </tr>		        	
	</table>       
</kul:subtab>
	