<%--
 Copyright 2005-2010 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="noteObject" required="true" type="org.kuali.kra.infrastructure.KraNotepadInterface" %>
<%@ attribute name="noteParmeterString" required="true"%>
<%@ attribute name="viewRestrictedNotes" required="true"%>
<%@ attribute name="statusIndex" required="true"%>
<%@ attribute name="modifyPermission" required="true"%>
<%@ attribute name="hasAdministratorRole" required="true"%>
<%@ attribute name="action" required="true"%>

<c:set var="protocolNotesAttributes" value="${DataDictionary.ProtocolNotepad.attributes}" />


<%--<c:if test="${viewRestrictedNotes || !protocolNotepad.restrictedView}"> --%>
<c:if test="${viewRestrictedNotes}">
	<tr>
		<th class="infoline">
			<c:out value="${statusIndex + 1}" />
		</th>
		<td valign="middle">
			<c:out value="${noteObject.createUserFullName }"/>
			<Br /> 
			<kul:htmlControlAttribute property="${noteParmeterString }.createTimestamp" attributeEntry="${protocolNotesAttributes.updateTimestamp}" readOnly="true" />
			
			
		</td>
		<td valign="middle">
			<c:out value="${noteObject.updateUserFullName }"/>
			<Br /> 
			<kul:htmlControlAttribute property="${noteParmeterString }.updateTimestamp" attributeEntry="${protocolNotesAttributes.updateTimestamp}" readOnly="true" />
		</td>
		<td valign="middle">
			<div align="center">
				<kul:htmlControlAttribute property="${noteParmeterString }.noteTopic" attributeEntry="${protocolNotesAttributes.noteTopic}" 
					readOnly="${!modifyPermission || !noteObject.editable}" />
			</div>
		</td>
		<td valign="middle">
			<div align="left">
				<Br/>
				<c:choose> 
					<c:when test="${!modifyPermission || !noteObject.editable}">
						<c:set var="displaySize" value="120"/>
						<c:choose>
				            <c:when test="${fn:length(noteObject.comments) > displaySize}">
			                	  ${fn:substring(noteObject.comments,0,displaySize - 1)}...
			                	  
			                	  <html:hidden property="${noteParmeterString }.comments" write="false" styleId="${noteParmeterString }.comments" />
			                	  <kul:expandedTextArea textAreaFieldName="${noteParmeterString }.comments" action="${action }" 
			                	  	textAreaLabel="${protocolNotesAttributes.comments.label}"  readOnly="true" />
			                	  
			                </c:when>
				            <c:otherwise>
			                	  ${noteObject.comments}
			                </c:otherwise>
			            </c:choose>
					</c:when>
					<c:otherwise>
						<kul:htmlControlAttribute property="${noteParmeterString }.comments" attributeEntry="${protocolNotesAttributes.comments}" />
					</c:otherwise>
				</c:choose>
			</div>
		</td>
		<td valign="middle">
			<div align="center">
				<kul:htmlControlAttribute property="${noteParmeterString }.restrictedView" attributeEntry="${protocolNotesAttributes.restrictedView}" 
					readOnly="${!modifyPermission || ((!viewRestrictedNotes || !noteObject.editable) && !hasAdministratorRole)}" />
					
			</div>
		</td>
		<td>
			<div align=center>
				<nobr>
					<c:choose>
						<c:when test="${modifyPermission}">
							<c:if test="${!noteObject.editable}">
								<html:image property="methodToCall.editNote.line${statusIndex}.anchor${tabKey}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif'
									styleClass='tinybutton' />
								&nbsp;	
  	        				</c:if>
  	        				<html:image property="methodToCall.deleteNote.line${statusIndex}.anchor${tabKey}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' 
  	        					styleClass="tinybutton" />
  	        			</c:when>
						<c:otherwise>
				   			&nbsp;
    					</c:otherwise>
					</c:choose>
				</nobr>
			</div>
		</td>
	</tr>
</c:if>