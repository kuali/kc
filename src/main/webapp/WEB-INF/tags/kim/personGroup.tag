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

<c:set var="docGroupAttributes" value="${DataDictionary.PersonDocumentGroup.attributes}" />

<kul:subtab lookedUpCollectionName="group" width="${tableWidth}" subTabTitle="Groups" noShowHideButton="false">      
   <table cellpadding="0" cellspacing="0" summary="">
     	<tr>
    		<th><div align="left">&nbsp;</div></th> 
    		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docGroupAttributes.groupId}" noColon="true" /></div></th>
    		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docGroupAttributes.namespaceCode}" noColon="true" /></div></th>
    		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docGroupAttributes.groupName}" noColon="true" /></div></th>
    		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docGroupAttributes.kimTypeId}" noColon="true" /></div></th>
    		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docGroupAttributes.activeFromDate}" noColon="true" /></div></th>
    		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docGroupAttributes.activeToDate}" noColon="true" /></div></th>
           	<c:if test="${not inquiry}">	
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</c:if>
       	</tr>     
        <c:if test="${not inquiry and not readOnly}">	          	
          	<tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>
                <td align="left" valign="middle" class="infoline" >
                	<div align="center">
	                	<kul:htmlControlAttribute property="newGroup.groupId" attributeEntry="${docGroupAttributes.groupId}" readOnly="${readOnly}"/>
	                	<kul:lookup boClassName="org.kuali.rice.kim.bo.impl.GroupImpl" fieldConversions="groupId:newGroup.groupId,kimTypeId:newGroup.groupType,groupName:newGroup.groupName,namespaceCode:newGroup.namespaceCode,kimTypeInfo.name:newGroup.kimGroupType.name" anchor="${tabKey}" />
						<html:hidden property="newGroup.groupName" />
						<html:hidden property="newGroup.groupType" />
						<html:hidden property="newGroup.kimGroupType.name" />
						<html:hidden property="newGroup.namespaceCode" />				
					</div>
				</td>
                <td align="left" valign="middle" class="infoline" >
                	<div align="center">
	                	<kul:htmlControlAttribute property="newGroup.namespaceCode" attributeEntry="${docGroupAttributes.namespaceCode}" readOnly="true"/>
					</div>
				</td>
                <td align="left" valign="middle" class="infoline" >
                	<div align="center">
	                	<kul:htmlControlAttribute property="newGroup.groupName" attributeEntry="${docGroupAttributes.groupName}" readOnly="true"/>
					</div>
				</td>
                <td align="left" valign="middle" class="infoline" >
                	<div align="center">
	                	<kul:htmlControlAttribute property="newGroup.kimGroupType.name" attributeEntry="${docGroupAttributes.kimGroupType.name}" readOnly="${readOnly}"/>
					</div>
				</td>
	            <td align="left" valign="middle">
	                <div align="center"> <kul:htmlControlAttribute property="newGroup.activeFromDate"  attributeEntry="${docGroupAttributes.activeFromDate}"  datePicker="true" readOnly="${readOnly}"/>
					</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="newGroup.activeToDate"  attributeEntry="${docGroupAttributes.activeToDate}"  datePicker="true" readOnly="${readOnly}"/>
					</div>
				</td>				                                
                <td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addGroup.anchor${tabKey}"
							src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
       		</tr>         
    	</c:if>        
        <c:forEach var="group" items="${KualiForm.document.groups}" varStatus="status">
	       	<tr>
				<th class="infoline">
					<c:out value="${status.index+1}" />
				</th>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.groups[${status.index}].groupId"  attributeEntry="${docGroupAttributes.groupId}"  readOnly="true" />
					</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.groups[${status.index}].namespaceCode"  attributeEntry="${docGroupAttributes.namespaceCode}" readOnly="true"  />
					</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.groups[${status.index}].groupName"  attributeEntry="${docGroupAttributes.groupName}" readOnly="true"  />
					</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.groups[${status.index}].kimGroupType.name"  attributeEntry="${docGroupAttributes.kimGroupType.name}" readOnly="true"  />
					</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.groups[${status.index}].activeFromDate"  attributeEntry="${docGroupAttributes.activeFromDate}" datePicker="true" readOnly="${readOnly}"/>
					</div>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.groups[${status.index}].activeToDate"  attributeEntry="${docGroupAttributes.activeToDate}" datePicker="true" readOnly="${readOnly}"/>
					</div>
				</td>
           		<c:if test="${not inquiry}">						
					<td>
						<div align=center>&nbsp;
		        	     <c:choose>
		        	       <c:when test="${group.edit  or readOnly}">
		        	          <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
		        	       </c:when>
		        	       <c:otherwise>
		        	          <html:image property='methodToCall.deleteGroup.line${status.index}.anchor${currentTabIndex}'
									src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass='tinybutton'/>
		        	       </c:otherwise>
		        	     </c:choose>  
						</div>
	                </td>
	            </c:if>    
	      	</tr>
        </c:forEach>                    
 	</table>
</kul:subtab>
