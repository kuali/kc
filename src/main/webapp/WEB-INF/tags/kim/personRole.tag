<%--
 Copyright 2008-2009 The Kuali Foundation
 
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

<c:set var="docRoleAttributes" value="${DataDictionary.PersonDocumentRole.attributes}" />
<c:set var="docRolePrncplAttributes" value="${DataDictionary.KimDocumentRoleMember.attributes}" />

<kul:subtab lookedUpCollectionName="role" width="${tableWidth}" subTabTitle="Roles" noShowHideButton="false">      
    <table cellpadding="0" cellspacing="0" summary="">
        <c:if test="${!readOnly}">	          	
          	<tr>
          		<th>&nbsp;</th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRoleAttributes.roleId}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRoleAttributes.namespaceCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRoleAttributes.roleName}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRoleAttributes.kimTypeId}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRolePrncplAttributes.activeFromDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRolePrncplAttributes.activeToDate}" noColon="true" /></div></th>
           	<c:if test="${!readOnly}">	
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</c:if>	
          	</tr>     
          	
            <tr>
				<th class="infoline">Add:</th>

                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newRole.roleId" attributeEntry="${docRoleAttributes.roleId}" readOnly="${readOnly}" />
                	<kul:lookup boClassName="org.kuali.rice.kim.bo.impl.RoleImpl" fieldConversions="roleId:newRole.newRolePrncpl.memberId,roleId:newRole.roleId,kimTypeId:newRole.kimTypeId,roleName:newRole.roleName,namespaceCode:newRole.namespaceCode,kimRoleType.name:newRole.kimRoleType.name,kimRoleType.kimTypeServiceName:newRole.kimRoleType.kimTypeServiceName" anchor="${tabKey}" />
					
					<html:hidden property="newRole.roleName" />
					<html:hidden property="newRole.roleId" />
					<html:hidden property="newRole.newRolePrncpl.memberId" />
					<html:hidden property="newRole.namespaceCode" />
					<html:hidden property="newRole.kimTypeId" />
					<html:hidden property="newRole.kimRoleType.name" />
					<html:hidden property="newRole.kimRoleType.kimTypeServiceName" />
	            </div>
				</td>
				<td>${KualiForm.newRole.namespaceCode}&nbsp;</td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newRole.roleName" attributeEntry="${docRoleAttributes.roleName}" disabled="true"/>
	            </div>
				</td>
				<td>${KualiForm.newRole.kimRoleType.kimTypeServiceName}</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="newRole.newRolePrncpl.activeFromDate"  attributeEntry="${docRolePrncplAttributes.activeFromDate}"  datePicker="true" readOnly="${readOnly}" />
				</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="newRole.newRolePrncpl.activeToDate"  attributeEntry="${docRolePrncplAttributes.activeToDate}"  datePicker="true" readOnly="${readOnly}" />
				</div>
				</td>

                <td class="infoline">
					<div align="center">
						<html:image property="methodToCall.addRole.anchor${tabKey}"
							src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
					</div>
                </td>
       		</tr>         
     	</c:if>       
        <c:forEach var="role" items="${KualiForm.document.roles}" varStatus="status">
			<c:set var="readOnlyRole" scope="request" value="${!role.editable || readOnly}" />
        	<%-- add header label for each 'role' to see if it is less confusion for user --%>
          	<tr>
          		<th>&nbsp;</th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRoleAttributes.roleId}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRoleAttributes.namespaceCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRoleAttributes.roleName}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRoleAttributes.kimTypeId}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRolePrncplAttributes.activeFromDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docRolePrncplAttributes.activeToDate}" noColon="true" /></div></th>
	           	<c:if test="${!readOnly}">	
	              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
	          	</c:if>	
          	</tr>             	
       	    <c:set var="rows" value="2"/>
       		<c:if test="${empty role.definitions and (empty role.rolePrncpls or fn:length(role.rolePrncpls) < 1 or fn:length(role.rolePrncpls[0].roleRspActions) < 1)}">	
        	       <c:set var="rows" value="1"/>       		
       		</c:if>        	
	        <tr>
                <%-- TODO : try 'valign' to see if it helps user--%>
				<th rowspan="${rows}" class="infoline" valign="top">
					<c:out value="${status.index+1}" />
				</th>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.roles[${status.index}].roleId"  attributeEntry="${docRoleAttributes.roleId}" readOnly="true"  />
				</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.roles[${status.index}].namespaceCode"  attributeEntry="${docRoleAttributes.namespaceCode}" readOnly="true" />
				</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.roles[${status.index}].roleName"  attributeEntry="${docRoleAttributes.roleName}" readOnly="true"  />
				</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.roles[${status.index}].kimRoleType.name"  attributeEntry="${docRoleAttributes.kimGroupType.name}" readOnly="true"  />
				</div>
				</td>
				<c:set var="roleMemberActiveDatesReadOnly" value="${(!empty role.definitions and fn:length(role.definitions) > 0) || readOnlyRole}" />
                <td align="left" valign="middle">
                	<c:if test="${fn:length(role.rolePrncpls) > 0}">
                		<div align="center"> <kul:htmlControlAttribute property="document.roles[${status.index}].rolePrncpls[0].activeFromDate"  attributeEntry="${docRolePrncplAttributes.activeFromDate}"  datePicker="true" readOnly="${roleMemberActiveDatesReadOnly}" />
						</div>
					</c:if>
				</td>
                <td align="left" valign="middle">
                	<c:if test="${fn:length(role.rolePrncpls) > 0}">
	               		<div align="center"> <kul:htmlControlAttribute property="document.roles[${status.index}].rolePrncpls[0].activeToDate"  attributeEntry="${docRolePrncplAttributes.activeToDate}"  datePicker="true" readOnly="${roleMemberActiveDatesReadOnly}" />
						</div>
					</c:if>
				</td>
           		<c:if test="${!readOnlyRole}">
					<td>
						<div align=center>&nbsp;
			        	     <c:choose>
				        	       <c:when test="${role.edit or readOnly}">
				        	          <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
				        	       </c:when>
				        	       <c:otherwise>
				        	          <html:image property='methodToCall.deleteRole.line${status.index}.anchor${currentTabIndex}'
											src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass='tinybutton'/>
				        	       </c:otherwise>
			        	     </c:choose>  
						</div>
	                </td>
	           </c:if>     
	      	</tr>
		    <c:choose>
	            <c:when test="${!empty role.definitions  and fn:length(role.definitions) > 0}" >
	            	<tr>
		              <td colspan="7" style="padding:0px;">
		              	<kim:personRoleQualifier roleIdx="${status.index}" role="${role}" />
			          </td>
			        </tr>
 		        </c:when>
		        <c:otherwise>
			         <c:if test="${fn:length(role.rolePrncpls[0].roleRspActions) > 0}">	
	     			    <tr>
			              <td colspan="7" style="padding:0px;">
			              	<kim:roleResponsibilityAction roleIdx="${status.index}" mbrIdx="0" />
				          </td>
				        </tr>
					</c:if>	      			        			        
 		        </c:otherwise>
		     </c:choose>
       	</c:forEach>                   
    </table>
</kul:subtab>
