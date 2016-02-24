<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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
		         hasAdministratorRole="${awardAdmin}" action="awardNotesAndAttachments" showUpdate="true" isAddLine="${false}" />
	    	</c:forEach> 
        </table>
   </div>
</kul:tab>
