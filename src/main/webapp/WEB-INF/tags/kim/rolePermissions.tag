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

<c:set var="permissionAttributes" value="${DataDictionary.PermissionImpl.attributes}" />

<kul:tab tabTitle="Permissions" defaultOpen="true" tabErrorKey="document.perm*">
	<div class="tab-container" align="center">    
    <table cellpadding="0" cellspacing="0" summary="">
          <c:if test="${!readOnly}">	
          	
             <tr>
				<td align="center">
	                <div align="center">
	                	<br/>
						<b>Add Permission ID:</b>
						<kul:htmlControlAttribute property="permission.permissionId" attributeEntry="${permissionAttributes.permissionId}"/>
	                	<kul:lookup boClassName="org.kuali.rice.kim.bo.impl.PermissionImpl" fieldConversions=
	                	"template.name:permission.kimPermission.template.name,permissionId:permission.permissionId,name:permission.kimPermission.name,namespaceCode:permission.kimPermission.namespaceCode" anchor="${tabKey}" />
						<html:hidden property="permission.kimPermission.name" />
						<html:hidden property="permission.kimPermission.namespaceCode" />
						${KualiForm.permission.kimPermission.namespaceCode}&nbsp;&nbsp;${KualiForm.permission.kimPermission.name}&nbsp;
	                	<br/>
	                	<br/>
		            </div>
				</td>
			</tr>
			<tr>                                
                <td class="infoline">
					<div align="center">
						<html:image property="methodToCall.addPermission.anchor${tabKey}"
							src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
					</div>
                </td>
	       </tr>         
     </c:if>       
	</table>
	<table>
        	<tr>
        		<th>&nbsp;</th> 
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${permissionAttributes.namespaceCode}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${permissionAttributes.permissionId}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${permissionAttributes.name}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${permissionAttributes.detailObjectsToDisplay}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${permissionAttributes.active}" noColon="true" /></div></th>
				<c:if test="${!readOnly}">	
            		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
				</c:if>	
        	</tr>     
      	<c:forEach var="permission" items="${KualiForm.document.permissions}" varStatus="status">
            <tr>
				<th class="infoline">
					<c:out value="${status.index+1}" />
				</th>
	            <td align="left" valign="middle">
	               	<div align="left"> <kul:htmlControlAttribute property="document.permissions[${status.index}].kimPermission.namespaceCode"  attributeEntry="${permissionAttributes.namespaceCode}" readOnly="true"  />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="left"> <kul:htmlControlAttribute property="document.permissions[${status.index}].permissionId"  attributeEntry="${permissionAttributes.permissionId}" readOnly="true"  />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="left"> <kul:htmlControlAttribute property="document.permissions[${status.index}].kimPermission.nameToDisplay"  attributeEntry="${permissionAttributes.name}" readOnly="true"  />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="left"> <kul:htmlControlAttribute property="document.permissions[${status.index}].kimPermission.detailObjectsToDisplay"  attributeEntry="${permissionAttributes.detailObjectsToDisplay}" readOnly="true"  />
					</div>
				</td>
				<c:choose>
					<c:when test="${permission.edit && !readOnly}">
			            <td align="center" valign="middle">
			               	<div align="center"> <kul:htmlControlAttribute property="document.permissions[${status.index}].active"  attributeEntry="${permissionAttributes.active}" />
							</div>
						</td>
					</c:when>
       	       		<c:otherwise>
			            <td align="center" valign="middle">
			               	<div align="center"> <kul:htmlControlAttribute property="document.permissions[${status.index}].active"  attributeEntry="${permissionAttributes.active}" readOnly="true"  />
							</div>
						</td>
        	       	</c:otherwise>
       	     	</c:choose>  
			<c:if test="${!readOnly}">	
				<td>
					<div align="center">&nbsp;
						<c:choose>
							<c:when test="${permission.edit}">
	        	          		<img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
							</c:when>
	        	       		<c:otherwise>
	        	        		<html:image property='methodToCall.deletePermission.line${status.index}.anchor${currentTabIndex}'
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
