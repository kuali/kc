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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentRejectionBeanAttributes" value="${DataDictionary.ProposalDevelopmentRejectionBean.attributes}" />

<kul:tabTop tabTitle="Proposal Developement Rejection Confirmation" defaultOpen="true" tabErrorKey="ProposalDevelopmentRejectionBean*">
	<div class="tab-container" align="center">
	<table cellpadding=0 cellspacing=0 summary="">
		<tr>
			<th colspan="2">Are you sure you want to reject this document?</th>
		</tr>
		<tr>
			<th>
				<kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentRejectionBeanAttributes.rejectReason}"/>
			</th>
			<td>
				<kul:htmlControlAttribute property="proposalDevelopmentRejectionBean.rejectReason" attributeEntry="${proposalDevelopmentRejectionBeanAttributes.rejectReason}" readOnly="${false}" />
			</td>
		</tr>
		<tr>
			<th>
				Attachment:
			</th>
			<td>
				<html:file property="proposalDevelopmentRejectionBean.rejectFile" />
			</td>
		</tr>
		<tr>
            <td style="text-align: center;" colspan="2">
            	<html:image property="methodToCall.rejectYes" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_Yes.gif" title="Do Reject" alt="Do Reject" styleClass="tinybutton"/>
            	<html:image property="methodToCall.rejectNo" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_No.gif" title="Dont Reject" alt="Don't Reject" styleClass="tinybutton"/>	                
            </td>
        </tr>
	</table>
	</div>
</kul:tabTop>
<kul:panelFooter />
