<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="narrativeUserRightsAttributes" value="${DataDictionary.NarrativeUserRights.attributes}" />
<c:set var="selectedInstituteAttachment" value="${KualiForm.document.developmentProposalList[0].instituteAttachments[line]}"/>
<%-- <kul:uncollapsable tabTitle="Rights"  > --%>
<kul:tabTop defaultOpen="true" tabTitle="Rights" tabErrorKey="newNarrativeUserRight*">
	<div class="tab-container" align="center">
	    <input type="hidden" name="line" value="${line}" />
		<h3>
    		<span class="subhead-left">Internal Attachment Rights for ${line+1}. ${selectedInstituteAttachment.narrativeType.description }</span>
        </h3>	
        <table id="narrative-rights-table" cellpadding=0 cellspacing=0 summary="">
	       <c:forEach var="narrUserRight" items="${selectedInstituteAttachment.narrativeUserRights}" varStatus="status">
          	<tr>
            	<th><div align="right">
            		<c:out value="${narrUserRight.personName}"/>
				</div></th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="newNarrativeUserRight[${status.index}].accessType" attributeEntry="${narrativeUserRightsAttributes.accessType}" />
				</td>
        	</tr>
	       </c:forEach>
	      
	    <c:set var="modifyRightsKey" value="instituteAttachment.${selectedInstituteAttachment.moduleNumber}.modifyRights" />
        <c:set var="modifyRights" value="${KualiForm.editingMode[modifyRightsKey]}" />
        
        <c:if test="${modifyRights}">
		<tr>
			<td colspan="2">
			<div id="globalbuttons" class="globalbuttons">
			    <html:image property="methodToCall.addInstituteAttachmentRights"
				    src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif"
				    styleClass="globalbuttons" title="save" alt="save"/>
			</div>
			</td>
		</tr>
		</c:if>
	       
        </table>
    </div>
</kul:tabTop>    
<%--</kul:uncollapsable>--%>
