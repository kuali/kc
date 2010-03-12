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
<%@ tag body-content="empty" %>
<%@ attribute name="delegationMemberIdx" required="true" description="The index of the delegation member information on the IdentityManagementPersonDocument to display qualifiers for." %>

<c:set var="delegationMember" value="${KualiForm.document.delegationMembers[delegationMemberIdx]}"/>
<c:set var="delegationMemberAttributes" value="${DataDictionary.RoleDocumentDelegationMember.attributes}" />
<c:set var="roleDocumentDelegationMemberQualifier" value="${DataDictionary.RoleDocumentDelegationMemberQualifier.attributes}" />

<kul:subtab lookedUpCollectionName="delegationMemberQualifier" width="${tableWidth}" subTabTitle="Delegation Member Qualifier" useCurrentTabIndexAsKey="true">      
    <table cellpadding="0" cellspacing="0" summary="">
        <tr>
        	<th width="5%" rowspan="20" style="border-style:none">&nbsp;</th>
			<th>&nbsp;</th> 
		    <c:choose>
	            <c:when test="${!empty delegationMember.attributesHelper.definitions and fn:length(delegationMember.attributesHelper.definitions) > 0}" >
					<c:forEach var="attrDefn" items="${delegationMember.attributesHelper.definitions}" varStatus="status1">
						<c:set var="attr" value="${attrDefn.value}" />
						<c:set var="fieldName" value="${attr.name}" />
						<c:set var="attrEntry" value="${delegationMember.attributesHelper.attributeEntry[fieldName]}" />
	         		    <kul:htmlAttributeHeaderCell attributeEntry="${attrEntry}" useShortLabel="false" />
					</c:forEach>	
 		        </c:when>
		     </c:choose>
      		</tr>         
        <tr>
         		<th>&nbsp;</th> 
		    <c:choose>
	            <c:when test="${!empty delegationMember.attributesHelper.definitions and fn:length(delegationMember.attributesHelper.definitions) > 0}" >
					<c:forEach var="attrDefn" items="${delegationMember.attributesHelper.definitions}" varStatus="status1">
						<c:set var="attr" value="${attrDefn.value}" />
						<c:set var="fieldName" value="${attr.name}" />
						<c:set var="attrEntry" value="${delegationMember.attributesHelper.attributeEntry[fieldName]}" />
				       	<td align="left" valign="middle">
				       		<div align="center"> 
				      		   <kul:htmlControlAttribute kimTypeId="${delegationMember.roleImpl.kimTypeId}" property="document.delegationMembers[${delegationMemberIdx}].qualifiers[${status1.index}].attrVal"  attributeEntry="${attrEntry}" readOnly="${readOnly}" />
				       		   <c:if test="${attrDefinition.hasLookupBoDefinition}"> 
                                   <c:if test="${!empty attr.lookupBoClass and not readOnly}">
    				       		       <kim:attributeLookup attributeDefinitions="${delegationMember.attributesHelper.definitions}" pathPrefix="document.delegationMembers[${delegationMemberIdx}]" attr="${attr}" />
    				          	   </c:if>
                               </c:if>
				  			</div>
						</td>
					</c:forEach>	
 		        </c:when>
		     </c:choose>
      	</tr>
    </table>
</kul:subtab>
