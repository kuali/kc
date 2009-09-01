<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<c:set var="tabItemCount" value="0" />
<c:forEach var="awardNotepad" items="${KualiForm.document.award.awardNotepads}" varStatus="status">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
</c:forEach>


<kul:tab tabTitle="Notes" tabItemCount="${tabItemCount}" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Notes</span>
        </h3>
        <table id="cost-share-table" cellpadding="0" cellspacing="0" summary="Cost Share">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardNotesAttributes.createTimestamp}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardNotesAttributes.updateUser}" useShortLabel="true" noColon="true" /></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${awardNotesAttributes.noteTopic}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${awardNotesAttributes.comments}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardNotesAttributes.restrictedView}" useShortLabel="true" noColon="true"/></th>
				<th><div align="center">Actions</div></th>
			</tr>
			
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
            	    	<kra:expandedTextArea textAreaFieldName="awardNotepadBean.newAwardNotepad.comments" action="awardHome" textAreaLabel="${awardNotesAttributes.comments.label}" />
            	    	
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	   	 	<kul:htmlControlAttribute property="awardNotepadBean.newAwardNotepad.restrictedView" attributeEntry="${awardNotesAttributes.restrictedView}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align=center>
						<html:image property="methodToCall.addNote.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	            </td>
          	</tr>
         <c:forEach var="awardNotepad" items="${KualiForm.document.award.awardNotepads}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td valign="middle">
						${KualiForm.document.award.awardNotepads[status.index].createTimestamp}&nbsp;
					</td>
	                <td valign="middle">
						${KualiForm.document.award.awardNotepads[status.index].updateUser}&nbsp;
	                </td>
	                <td valign="middle">                	
					<div align="center">
						${KualiForm.document.award.awardNotepads[status.index].noteTopic}&nbsp;  
					</div>
					</td>
	                <td valign="middle">                	
					<div align="left">
						${KualiForm.document.award.awardNotepads[status.index].comments}&nbsp; 
					</div>
					</td>
	                <td valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.award.awardNotepads[${status.index}].restrictedView" attributeEntry="${awardNotesAttributes.restrictedView}"/>
					</div>
	                </td>
					<td>
					<div align="center">&nbsp;
						<html:image property="methodToCall.updateNotes.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif' styleClass="tinybutton"/>
					</div>
	                </td>
	            </tr>
        	</c:forEach> 
        </table>
   </div>
</kul:tab>
