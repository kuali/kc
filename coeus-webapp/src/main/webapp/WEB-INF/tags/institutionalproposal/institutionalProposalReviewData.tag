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

<!-- Member of IntitutionalProposalIntellectualPropertyReview.jsp -->

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="intellectualPropertyReviewAttributes" value="${DataDictionary.IntellectualPropertyReview.attributes}" />

<kul:tabTop tabTitle="Review Data" defaultOpen="false" tabErrorKey="">

<div class="tab-container" align="center">

<h3>
    <span class="subhead-left">Review Data</span>
    <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview" altText="help"/></span>
</h3>

<table cellpadding="0" cellspacing="0">
    <tr>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.reviewSubmissionDate}" /></th>
        <td><kul:htmlControlAttribute property="document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.reviewSubmissionDate" attributeEntry="${intellectualPropertyReviewAttributes.reviewSubmissionDate}" readOnly="true" /></td>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.ipReviewRequirementTypeCode}" /></th>
        <td><c:out value="${KualiForm.document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.ipReviewRequirementType.description}" />&nbsp;</td>
    </tr>
    <tr>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.reviewReceiveDate}" /></th>
        <td><kul:htmlControlAttribute property="document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.reviewReceiveDate" attributeEntry="${intellectualPropertyReviewAttributes.reviewReceiveDate}" readOnly="true" /></td>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.reviewResultCode}" /></th>
        <td><c:out value="${KualiForm.document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.reviewResult.description}" />&nbsp;</td>
    </tr>
    <tr>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.ipReviewer}" /></th>
        <td>
            <kul:inquiry boClassName="org.kuali.coeus.common.framework.person.KcPerson" keyValues="personId=${KualiForm.document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.ipReviewer}" render="true">
                <c:out value="${KualiForm.document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.person.userName}" />
            </kul:inquiry>&nbsp;
        </td>
        <th align="right">&nbsp;</th>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.generalComments}" /></th>
        <td>
            <table style="border:none; width:100%;" cellpadding=0 cellspacing=0>
                <tr>
                    <td style="border:none;"><c:out value="${KualiForm.document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.generalComments}" /></td>
                    <td style="border:none; width:20px; vertical-align:bottom;">
                        <kul:expandedTextArea textAreaFieldName="document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.generalComments" action="institutionalProposalHome" textAreaLabel="General Comments" readOnly="true" />
                    </td>
                </tr>
            </table>
        </td>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.reviewerComments}" /></th>
        <td>
            <table style="border:none; width:100%;" cellpadding=0 cellspacing=0>
                <tr>
                    <td style="border:none;"><c:out value="${KualiForm.document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.reviewerComments}" /></td>
                    <td style="border:none; width:20px; vertical-align:bottom;">
                        <kul:expandedTextArea textAreaFieldName="document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.reviewerComments" action="institutionalProposalHome" textAreaLabel="Reviewer Comments" readOnly="true" />
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
    
</div>

</kul:tabTop>
