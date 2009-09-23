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

<c:set var="roleMemberAttributes" value="${DataDictionary.KimDocumentRoleMember.attributes}" />
<c:set var="delegationMemberAttributes" value="${DataDictionary.RoleDocumentDelegationMember.attributes}" />
<c:set var="roleDocumentDelegationMemberQualifier" value="${DataDictionary.RoleDocumentDelegationMemberQualifier.attributes}" />
<c:set var="kimAttributes" value="${DataDictionary.KimAttributeImpl.attributes}" />

<c:if test="${KualiForm.anchor ne '' && KualiForm.anchor ne 'topOfForm'}">
	<script type="text/javascript">
		jumpToAnchorName(<c:out value="${KualiForm.anchor}"/>);
	</script>
</c:if>	
<c:if test="${!(empty KualiForm.document.delegationMembers) || canModifyAssignees}">
<kul:tab tabTitle="Delegations" defaultOpen="true" tabErrorKey="document.delegationMember*,delegationMember.*">
	<div class="tab-container" align="center">    
    <table cellpadding="0" cellspacing="0" summary="">
        	<tr>
        		<th>&nbsp;</th> 
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${delegationMemberAttributes.roleMemberId}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${delegationMemberAttributes.memberTypeCode}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${delegationMemberAttributes.memberId}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${delegationMemberAttributes.memberNamespaceCode}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${delegationMemberAttributes.memberName}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${delegationMemberAttributes.activeFromDate}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${delegationMemberAttributes.activeToDate}" noColon="true" /></div></th>
				<c:forEach var="attrDefn" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="status">
        			<c:set var="fieldName" value="${attrDefn.attributeName}" />
        			<c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
         		    <kul:htmlAttributeHeaderCell attributeEntry="${attrEntry}" useShortLabel="false" />
		        </c:forEach>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${delegationMemberAttributes.delegationTypeCode}" noColon="true" /></div></th>
				<c:if test="${!readOnlyAssignees}">	
            		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
				</c:if>	
        	</tr>     
          <c:if test="${!readOnlyAssignees}">	
             <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>
                <td class="infoline">   
                <div align="center">
					<!-- Combo of role members -->
					<c:if test="${KualiForm.delegationMemberRoleMemberId ne ''}" >
						<c:set var="jumpToRoleMemberAnchorName" value="methodToCall.jumpToRoleMember.dmrmi${KualiForm.delegationMemberRoleMemberId}.x" />
			  	   		${kfunc:registerEditableProperty(KualiForm, jumpToRoleMemberAnchorName)}
						<input type="submit" tabindex="${tabindex}" name="${jumpToRoleMemberAnchorName}" value="${KualiForm.delegationMember.roleMemberNamespaceCode}&nbsp;${KualiForm.delegationMember.roleMemberName}"/>
					</c:if>
	               	<kul:lookup boClassName="org.kuali.rice.kim.bo.ui.KimDocumentRoleMember" fieldConversions="roleMemberId:delegationMemberRoleMemberId" anchor="${tabKey}" />
				</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="delegationMember.memberTypeCode" 
                	attributeEntry="${delegationMemberAttributes.memberTypeCode}" 
                	onchange="changeDelegationMemberTypeCode(this.form)" disabled="${!canModifyAssignees}" />
					<NOSCRIPT>
                        <input type="image" tabindex="32768" name="methodToCall.changeDelegationMemberTypeCode" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-refresh.gif" class="tinybutton" title="Click to refresh the page after changing the member type." alt="Click to refresh the page after changing the member type." />
					</NOSCRIPT>
	            </div>
            	<c:set var="bo" value="${KualiForm.delegationMemberBusinessObjectName}"/>
            	<c:set var="fc" value="${KualiForm.delegationMemberFieldConversions}"/>
				</td>
                <td class="infoline">   
                <div align="center">
					<kul:htmlControlAttribute property="delegationMember.memberId" attributeEntry="${delegationMemberAttributes.memberId}" readOnly="${!canModifyAssignees}" />
					<c:if test="${!readOnlyAssignees}" >
		               	<kul:lookup boClassName="${bo}" fieldConversions="${fc}" anchor="${tabKey}" />
		            </c:if>
				</div>
				</td>
				<td class="infoline">   
                <div align="center">
					<kul:htmlControlAttribute property="delegationMember.memberNamespaceCode" attributeEntry="${delegationMemberAttributes.memberNamespaceCode}" readOnly="true" />
				</div>
				</td>
				<td class="infoline">   
                <div align="center">
					<kul:htmlControlAttribute property="delegationMember.memberName" attributeEntry="${delegationMemberAttributes.memberName}" readOnly="true" />
				</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="delegationMember.activeFromDate" attributeEntry="${delegationMemberAttributes.activeFromDate}" datePicker="true" readOnly="${!canModifyAssignees}" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="delegationMember.activeToDate" attributeEntry="${delegationMemberAttributes.activeToDate}" datePicker="true" readOnly="${!canModifyAssignees}" />
                </div>
                </td>
				<c:forEach var="qualifier" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="statusQualifier">
					<c:set var="fieldName" value="${qualifier.attributeName}" />
        			<c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
        			<c:set var="attrDefinition" value="${KualiForm.document.definitionsKeyedByAttributeName[fieldName]}"/>
		            <td align="left" valign="middle">
		               	<div align="center"> <kul:htmlControlAttribute kimTypeId="${KualiForm.document.kimType.kimTypeId}" property="delegationMember.qualifier(${qualifier.kimAttributeId}).attrVal"  attributeEntry="${attrEntry}" readOnly="${!canModifyAssignees}" />
		               	<c:if test="${attrDefinition.hasLookupBoDefinition}"> 
                            <c:if test="${!empty attrDefinition.lookupBoClass and canModifyAssignees}">
    			       		  <kim:attributeLookup attributeDefinitions="${KualiForm.document.definitions}" pathPrefix="delegationMember" attr="${attrDefinition}" />
    			          	</c:if>
                        </c:if>
						</div>
					</td>
		        </c:forEach>
                <td align="left" valign="middle" class="infoline">
	                <div align="center">
	                	<kul:htmlControlAttribute property="delegationMember.delegationTypeCode" 
	                	attributeEntry="${delegationMemberAttributes.delegationTypeCode}" disabled="${!canModifyAssignees}" />
		            </div>
				</td>
                <td class="infoline">
					<div align=center>
						<c:choose>
				        <c:when test="${!readOnlyAssignees}">
							<html:image property="methodToCall.addDelegationMember.anchor${tabKey}"
							src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</c:when>
						<c:otherwise>
							<html:image property="methodToCall.addDelegationMember.anchor${tabKey}"
							src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton" disabled="true" />
						</c:otherwise>
						</c:choose>
					</div>
                </td>
    	   </tr>         
		</c:if>

      	<c:forEach var="member" items="${KualiForm.document.delegationMembers}" varStatus="statusMember">
            <tr>
				<th class="infoline" valign="top">
					<c:out value="${statusMember.index+1}" />
				</th>
	            <td align="left" valign="middle">
	               	<div align="center">
						<c:set var="jumpToRoleMemberAnchorName" value="methodToCall.jumpToRoleMember.dmrmi${KualiForm.document.delegationMembers[statusMember.index].roleMemberId}.x" />
			  	   		${kfunc:registerEditableProperty(KualiForm, jumpToRoleMemberAnchorName)}
						<input type="submit" tabindex="${tabindex}" name="${jumpToRoleMemberAnchorName}" value="${KualiForm.document.delegationMembers[statusMember.index].roleMemberNamespaceCode}&nbsp;${KualiForm.document.delegationMembers[statusMember.index].roleMemberName}"/>
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.delegationMembers[${statusMember.index}].memberTypeCode"  attributeEntry="${delegationMemberAttributes.memberTypeCode}" disabled="true" readOnly="false" />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.delegationMembers[${statusMember.index}].memberId"  attributeEntry="${delegationMemberAttributes.memberId}" readOnly="true" />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.delegationMembers[${statusMember.index}].memberNamespaceCode"  attributeEntry="${delegationMemberAttributes.memberNamespaceCode}" readOnly="true" />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.delegationMembers[${statusMember.index}].memberName"  attributeEntry="${delegationMemberAttributes.memberName}" readOnly="true" />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.delegationMembers[${statusMember.index}].activeFromDate"  attributeEntry="${delegationMemberAttributes.activeFromDate}" readOnly="${!canModifyAssignees}" datePicker="true" />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.delegationMembers[${statusMember.index}].activeToDate"  attributeEntry="${delegationMemberAttributes.activeToDate}" readOnly="${!canModifyAssignees}"  datePicker="true" />
					</div>
				</td>
				<c:set var="numberOfColumns" value="${KualiForm.member.numberOfQualifiers+6}"/>
				<c:forEach var="qualifier" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="statusQualifier">
					<c:set var="fieldName" value="${qualifier.attributeName}" />
        			<c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
        			<c:set var="attrDefinition" value="${KualiForm.document.definitionsKeyedByAttributeName[fieldName]}"/>
        			<c:set var="attrReadOnly" value="${(!canModifyAssignees || attrDefinition.unique)}"/>
		            <td align="left" valign="middle">
		               	<div align="center"> <kul:htmlControlAttribute kimTypeId="${KualiForm.document.kimType.kimTypeId}" property="document.delegationMembers[${statusMember.index}].qualifier(${qualifier.kimAttributeId}).attrVal"  attributeEntry="${attrEntry}" readOnly="${attrReadOnly}"  />
		               	<c:if test="${attrDefinition.hasLookupBoDefinition}"> 
                            <c:if test="${!empty attrDefinition.lookupBoClass and not attrReadOnly}">
    			       		  <kim:attributeLookup attributeDefinitions="${KualiForm.document.definitions}" pathPrefix="document.delegationMembers[${statusMember.index}]" attr="${attrDefinition}" />
    			          	</c:if>
                        </c:if>
						</div>
					</td>
		        </c:forEach>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.delegationMembers[${statusMember.index}].delegationTypeCode"  attributeEntry="${delegationMemberAttributes.delegationTypeCode}" disabled="${!canModifyAssignees}" readOnly="false" />
					</div>
				</td>
			<c:if test="${!readOnlyAssignees}">	
				<td>
					<div align="center">&nbsp;
						<c:choose>
							<c:when test="${member.edit or readOnlyAssignees}">
	        	          		<img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
							</c:when>
	        	       		<c:otherwise>
	        	        		<html:image property='methodToCall.deleteDelegationMember.line${statusMember.index}.anchor${currentTabIndex}'
								src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass='tinybutton'/>
		        	       	</c:otherwise>
	        	     	</c:choose>  
					</div>
				</td>
			</c:if>    
			</tr>
		</c:forEach>        
	</table>
	</div>
</kul:tab>
</c:if>
