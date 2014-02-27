<%--
 Copyright 2005-2014 The Kuali Foundation
 
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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardNotesAttributes" value="${DataDictionary.AwardNotepad.attributes}" />
<c:set var="awardAdmin" value="true" />
<c:set var="tabItemCount" value="0" />
<c:forEach var="awardNotepad" items="${KualiForm.document.award.awardNotepads}" varStatus="status">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
</c:forEach>


<kul:tab tabTitle="Notes" tabItemCount="${tabItemCount}" defaultOpen="false" tabErrorKey="awardNotepadBean.*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Notes</span>
    		<span class="subhead-right">
    			<kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardNotesHelp" altText="help"/>
			</span>
        </h3>
        <table id="cost-share-table" cellpadding="0" cellspacing="0" summary="Cost Share">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardNotesAttributes.createUser}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardNotesAttributes.updateUser}" useShortLabel="true" noColon="true" /></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${awardNotesAttributes.noteTopic}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${awardNotesAttributes.comments}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardNotesAttributes.restrictedView}" useShortLabel="true" noColon="true"/></th>
				<th><div align="center">Actions</div></th>
			</tr>
			
			<c:if test="${!readOnly}">
			<tbody class="addline">
			<tr>
            	<th width="40" align="center" scope="row"><div align="center">Add:</div></th>
            	<td width="80" class="infoline">
            		&nbsp;           	
            	</td>
	            <td width="50" class="infoline">
	              	&nbsp;
	            </td>
	            <td width="150" class="infoline">
	            	<div align="center">
            	    	<kul:htmlControlAttribute property="awardNotepadBean.newAwardNotepad.noteTopic" attributeEntry="${awardNotesAttributes.noteTopic}"/>
            	  	</div>
	            </td>
	            <td width="1000" class="infoline">
	            	<div align="left">
            	    	<kul:htmlControlAttribute property="awardNotepadBean.newAwardNotepad.comments" attributeEntry="${awardNotesAttributes.comments}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center" class="ignoreMeFromWarningOnAddRow">
            	   	 	<kul:htmlControlAttribute property="awardNotepadBean.newAwardNotepad.restrictedView" attributeEntry="${awardNotesAttributes.restrictedView}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align=center>
						<html:image property="methodToCall.addNote.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
					</div>
	            </td>
          	</tr>
          	</tbody>
          	</c:if>
          	
         <c:forEach var="awardNotepad" items="${KualiForm.document.award.awardNotepads}" varStatus="status">
		     <kra:noteLineItem statusIndex="${status.index}" noteParmeterString="document.award.awardNotepads[${status.index}]" 
		         viewRestrictedNotes="true" noteObject="${awardNotepad}" modifyPermission="${modifyPermission}" 
		         hasAdministratorRole="${awardAdmin}" action="AwardNotesAndAttachmentsAction" showUpdate="true" isAddLine="${false}" />
	    	</c:forEach> 
        </table>
   </div>
</kul:tab>
