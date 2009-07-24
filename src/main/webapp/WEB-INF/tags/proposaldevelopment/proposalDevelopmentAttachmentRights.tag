<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="narrativeUserRightsAttributes" value="${DataDictionary.NarrativeUserRights.attributes}" />
<c:set var="selectedProposalAttachment" value="${KualiForm.document.developmentProposalList[0].narratives[line]}"/>
<%-- <kra:uncollapsable tabTitle="Rights"  > --%>
<kul:tabTop defaultOpen="true" tabTitle="Rights"
            tabErrorKey="newNarrativeUserRight*">
    <input type="hidden" name="line" value="${line}" />
	<div class="tab-container" align="center">
		<h3>
    		<span class="subhead-left">Proposal Attachment Rights for ${line+1}. ${selectedProposalAttachment.narrativeType.description }</span>
        </h3>	
        <table id="narrative-rights-table" cellpadding=0 cellspacing=0 summary="">
	       <c:forEach var="narrUserRight" items="${selectedProposalAttachment.narrativeUserRights}" varStatus="status">
          	<tr>
            	<th><div align="right">
            		<c:out value="${narrUserRight.personName}"/>
				</div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newNarrativeUserRight[${status.index}].accessType" attributeEntry="${narrativeUserRightsAttributes.accessType}" />
				</td>
        	</tr>
	       </c:forEach>
	       
	    <c:set var="modifyRightsKey" value="proposalAttachment.${selectedProposalAttachment.moduleNumber}.modifyRights" />
        <c:set var="modifyRights" value="${KualiForm.editingMode[modifyRightsKey]}" />
        
	    <c:if test="${modifyRights}">
		<tr>
			<td colspan="2">
			<div id="globalbuttons" class="globalbuttons"><input
				type="image" styleId="saveNarativeRights" name="methodToCall.addProposalAttachmentRights"
				src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="tinybutton"
				class="globalbuttons" title="save" alt="save"></div>
			</td>
		</tr>
		</c:if>
	       
        </table>
    </div>
</kul:tabTop>    
<%--</kra:uncollapsable>--%>
