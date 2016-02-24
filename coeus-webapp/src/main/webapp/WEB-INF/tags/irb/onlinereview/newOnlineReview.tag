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
<c:set var="onlineReviewAttributes" value="${DataDictionary.ProtocolOnlineReview.attributes}" />
<c:set var="protocolReviewerAttributes" value="${DataDictionary.ProtocolReviewer.attributes}" />
<c:set var="newOnlineReviewAttributes" value="${DataDictionary.NewProtocolOnlineReviewBean.attributes}" />

<c:set var="readOnly" value = "false"/>
<c:set var="docHeaderAttributes" value="${DataDictionary.DocumentHeader.attributes}" /><c:set var="documentTypeName" value="${KualiForm.docTypeName}" />
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />

<c:set var = "availableCommitteeMembers" value = "${KualiForm.onlineReviewsActionHelper.availableCommitteeMembersForCurrentSubmission}"/>
<c:set var = "canAssignNewReviewer" value = "${fn:length(availableCommitteeMembers) gt 0}"/>

		  <!-- DOC OVERVIEW TABLE -->
  	<c:choose>
	  	<c:when test = "${canAssignNewReviewer}">
	  	
		 		<h3>Create New Online Review</h3>
		    	<table cellpadding="0" cellspacing="0" class="datatable" title="view/edit document overview information" summary="view/edit document overview information">
			
	             	<tr>
	               	<th class="grid"><div align="right"><kul:htmlAttributeLabel attributeEntry="${newOnlineReviewAttributes.newProtocolReviewCommitteeMembershipId}" noColon="false" /></div></th>
	               	<td nowrap class="grid">
	               		<c:if test = "${canAssignNewReviewer && !readOnly}">
						<html:select property="onlineReviewsActionHelper.newProtocolReviewCommitteeMembershipId" tabindex="0">
							<option value = "">Select...</option>
							<c:forEach items = "${availableCommitteeMembers}" var = "member">
								<option value = "${member.committeeMembershipId}" <c:if test = "${KualiForm.onlineReviewsActionHelper.newProtocolReviewCommitteeMembershipId eq member.committeeMembershipId}">selected="true"</c:if>>
									${member.personName}
								</option>
							</c:forEach>
						</html:select> 
						<kul:checkErrors keyMatch="onlineReviewsActionHelper.newProtocolReviewCommitteeMembershipId"/>
							<c:if test="${hasErrors}">
	 							<kul:fieldShowErrorIcon />
  							</c:if>
						</c:if>
					</td>
	               	<th class="grid"><div align="right"><kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.dateRequested}" noColon="false" /></div></th>
		               	<td class="grid" >
							<kul:htmlControlAttribute property="onlineReviewsActionHelper.newReviewDateRequested" attributeEntry="${onlineReviewAttributes.dateRequested}" datePicker="true" readOnly="${!canAssignCommitteeMember || readOnly}" />
	    	           	</td>
	             	</tr>
	             	<tr>
	             		<th class="grid"><div align="right">
	             			<kul:htmlAttributeLabel attributeEntry="${newOnlineReviewAttributes.newReviewerTypeCode}" noColon="false" /></div>
	             		</th>
	        	     	<td class = "grid"><kul:htmlControlAttribute property="onlineReviewsActionHelper.newReviewerTypeCode" attributeEntry="${newOnlineReviewAttributes.newReviewerTypeCode}" readOnly="false"/>
	             			<th class="grid">
	             				<div align="right">
	             					<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.dateDue}" noColon="false" />
	             				</div>
	             			</th>
				            <td class="grid" >
								<kul:htmlControlAttribute property="onlineReviewsActionHelper.newReviewDateDue" attributeEntry="${onlineReviewAttributes.dateDue}" datePicker="true" readOnly="${!canAssignNewReviewer || readOnly}" />
	               			</td>
	             		</td>
	             	</tr>
	        
	          		<tr>
		      		<kul:htmlAttributeHeaderCell
		          	labelFor="document.documentHeader.documentDescription"
		          	attributeEntry="${docHeaderAttributes.documentDescription}"
		          	horizontal="true"
		          	/>
		      		<td align="left" valign="middle">
		      			<kul:htmlControlAttribute property="onlineReviewsActionHelper.newReviewDocumentDescription" attributeEntry="${docHeaderAttributes.documentDescription}" readOnly="${!canAssignNewReviewer || readOnly}"/>
		     		 </td>
		      		<kul:htmlAttributeHeaderCell
	                 	labelFor="document.documentHeader.explanation"
	                 	attributeEntry="${docHeaderAttributes.explanation}"
	                 	horizontal="true"
		          	rowspan="2"
	                 	/>
		      		<td align="left" valign="middle" rowspan="2">
	                 		<kul:htmlControlAttribute
	                     		property="onlineReviewsActionHelper.newReviewExplanation"
	                     		attributeEntry="${docHeaderAttributes.explanation}"
	                     		readOnly="${!canAssignNewReviewer || readOnly}"
	                     		readOnlyAlternateDisplay="${fn:replace(fn:escapeXml(onlineReviewsActionHelper.newReviewExplanation), Constants.NEWLINE, '<br/>')}"
	                     		/>
	             		</td>
		    	</tr>
		    	<tr>
			  		<kul:htmlAttributeHeaderCell
		        		labelFor="document.documentHeader.organizationDocumentNumber"
		        		attributeEntry="${docHeaderAttributes.organizationDocumentNumber}"
		        		horizontal="true"
		      		/>			  
	             		<td align="left" valign="middle">
	             			<kul:htmlControlAttribute property="onlineReviewsActionHelper.newReviewOrganizationDocumentNumber" attributeEntry="${docHeaderAttributes.organizationDocumentNumber}" readOnly="${!canAssignNewReviewer || readOnly}"/>
	             		</td>
	           	</tr>
			</table>   
	        
			<br/>
			
			<c:if test = "${canAssignNewReviewer && !readOnly}">
				<html:image property="methodToCall.createOnlineReview.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-create.gif' styleClass="tinybutton"/>
			</c:if>
  		</c:when>
  		
  		<c:otherwise>
			<p>There are no committee members available for review assignment.</p>
   		</c:otherwise>

	</c:choose>


