<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="narrativeUserRightsAttributes" value="${DataDictionary.NarrativeUserRights.attributes}" />
<c:set var="selectedProposalAttachment" value="${KualiForm.document.narratives[line]}"/>
<%-- <kul:uncollapsable tabTitle="Rights"  > --%>
<kul:tabTop defaultOpen="true" tabTitle="Rights"
            tabErrorKey="newNarrativeUserRight*">
	<div class="tab-container" align="center">
		<div class="h2-container">
    		<span class="subhead-left"><h2>Proposal Attachment Rights for ${line+1}. ${selectedProposalAttachment.narrativeType.description }</h2></span>
        </div>	
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
		<tr>
			<td colspan="2">
			<div id="globalbuttons" class="globalbuttons"><input
				type="image" styleId="saveNarativeRights" name="methodToCall.addProposalAttachmentRights"
				src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif"
				class="globalbuttons" title="save" alt="save"></div>
			</td>
		</tr>
	       
        </table>
    </div>
</kul:tabTop>    
<%--</kul:uncollapsable>--%>
