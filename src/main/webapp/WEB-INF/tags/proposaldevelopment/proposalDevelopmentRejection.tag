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