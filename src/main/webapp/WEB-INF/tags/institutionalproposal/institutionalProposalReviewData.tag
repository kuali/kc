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

<!-- Member of IntitutionalProposalIntellectualPropertyReview.jsp -->

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="intellectualPropertyReviewAttributes" value="${DataDictionary.IntellectualPropertyReview.attributes}" />

<kul:tabTop tabTitle="Review Data" defaultOpen="false" tabErrorKey="">

<div class="tab-container" align="center">

<h3>
    <span class="subhead-left">Review Data</span>
    <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.home.Award" altText="help"/></span>
</h3>

<table cellpadding="0" cellspacing="0">
    <tr>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.reviewSubmissionDate}" /></th>
        <td><kul:htmlControlAttribute property="document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.reviewSubmissionDate" attributeEntry="${intellectualPropertyReviewAttributes.reviewSubmissionDate}" readOnly="true" /></td>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.ipReviewRequirementTypeCode}" /></th>
        <td><c:out value="${KualiForm.document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.ipReviewRequirementType.description}" /></td>
    </tr>
    <tr>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.reviewReceiveDate}" /></th>
        <td><kul:htmlControlAttribute property="document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.reviewReceiveDate" attributeEntry="${intellectualPropertyReviewAttributes.reviewReceiveDate}" readOnly="true" /></td>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.reviewResultCode}" /></th>
        <td><c:out value="${KualiForm.document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.reviewResult.description}" /></td>
    </tr>
    <tr>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.ipReviewer}" /></th>
        <td><c:out value="${KualiForm.document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.person.fullName}" /></td>
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
                        <kra:expandedTextArea textAreaFieldName="document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.generalComments" action="institutionalProposalHome" textAreaLabel="General Comments" viewOnly="true" />
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
                        <kra:expandedTextArea textAreaFieldName="document.institutionalProposal.proposalIpReviewJoin.intellectualPropertyReview.reviewerComments" action="institutionalProposalHome" textAreaLabel="Reviewer Comments" viewOnly="true" />
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
    
</div>

</kul:tabTop>
