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

<%@ attribute name="readOnly" required="false" %>

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="intellectualPropertyReviewAttributes" value="${DataDictionary.IntellectualPropertyReview.attributes}" />

<kul:tabTop tabTitle="Review Data" defaultOpen="false" tabErrorKey="">

<div class="tab-container" align="center">

<h3>
    <span class="subhead-left">Review Data</span>
    <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.Award" altText="help"/></span>
</h3>

<table cellpadding="0" cellspacing="0">
    <tr>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.reviewSubmissionDate}" /></th>
        <td><kul:htmlControlAttribute property="document.institutionalProposal.intellectualPropertyReview.reviewSubmissionDate" attributeEntry="${intellectualPropertyReviewAttributes.reviewSubmissionDate}" readOnly="${readOnly}" /></td>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.ipReviewRequirementTypeCode}" /></th>
        <td><kul:htmlControlAttribute property="document.institutionalProposal.intellectualPropertyReview.ipReviewRequirementTypeCode" attributeEntry="${intellectualPropertyReviewAttributes.ipReviewRequirementTypeCode}" readOnly="${readOnly}" /></td>
    </tr>
    <tr>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.reviewReceiveDate}" /></th>
        <td><kul:htmlControlAttribute property="document.institutionalProposal.intellectualPropertyReview.reviewReceiveDate" attributeEntry="${intellectualPropertyReviewAttributes.reviewReceiveDate}" readOnly="${readOnly}" /></td>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.reviewResultCode}" /></th>
        <td><kul:htmlControlAttribute property="document.institutionalProposal.intellectualPropertyReview.reviewResultCode" attributeEntry="${intellectualPropertyReviewAttributes.reviewResultCode}" readOnly="${readOnly}" /></td>
    </tr>
    <tr>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${intellectualPropertyReviewAttributes.ipReviewer}" /></th>
        <td><kul:htmlControlAttribute property="document.institutionalProposal.intellectualPropertyReview.ipReviewer" attributeEntry="${intellectualPropertyReviewAttributes.ipReviewer}" readOnly="${readOnly}" /></td>
        <th align="right">&nbsp;</th>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <th align="right">General Comments</th>
        <td>
            <table style="border:none; width:100%;" cellpadding=0 cellspacing=0>
                <tr>
                    <td style="border:none;">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse purus. Nullam et justo. In volutpat odio sit amet pede. Pellentesque ipsum dui, convallis in, mollis a, lacinia vel, diam. Phasellus molestie neque at sapien condimentum massa nunc...</td>
                    <td style="border:none; width:20px; vertical-align:bottom;"><input name="x3" src="${ConfigProperties.kra.externalizable.images.url}/openreadonly_greenarrow01.png" onclick="javascript: alert('If this were not a mock, this icon would open a read only view of the full text.'); return false" class="tinybutton" alt="Expanded Read Only Text Area" type=image /></td>
                </tr>
            </table>
        </td>
        <th align="right">Reviewer Comments</th>
        <td>
            <table style="border:none; width:100%;" cellpadding=0 cellspacing=0>
                <tr>
                    <td style="border:none;">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse purus. Nullam et justo. In volutpat odio sit amet pede. Pellentesque ipsum dui, convallis in, mollis a, lacinia vel, diam. Phasellus molestie neque at sapien condimentum massa nunc...</td>
                    <td style="border:none; width:20px; vertical-align:bottom;"><input name="x" src="${ConfigProperties.kra.externalizable.images.url}/openreadonly_greenarrow01.png" onclick="javascript: alert('If this were not a mock, this icon would open a read only view of the full text.'); return false" class="tinybutton" alt="Expanded Read Only Text Area" type=image /></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
    
</div>

</kul:tabTop>
