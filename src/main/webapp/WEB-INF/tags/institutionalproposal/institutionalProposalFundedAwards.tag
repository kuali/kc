<%--
 Copyright 2005-2010 The Kuali Foundation
 
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

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="awardFundingProposalAtrributes" value="${DataDictionary.AwardFundingProposal.attributes}" />

<kul:tab tabTitle="Funded Awards" defaultOpen="false" tabErrorKey="selectedAwardFunding*">

<div class="tab-container" align="center">

<h3>
    <span class="subhead-left">Funded Awards</span>
  		<span class="subhead-right"><kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="fundedAwardsHelpUrl" altText="help"/></span>
</h3>

<table cellpadding="0" cellspacing="0" summary="">
    <tbody>
        <tr>
            <th><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardNumber}" noColon="true" /></th>
            <th>Award Version</th>
            <th>Proposal Version</th>
            <th><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountNumber}" noColon="true" /></th>
            <c:if test="${!readOnly}">
                <th>Actions</th>
            </c:if>
        </tr>
        <c:forEach var="awardFundingProposal" items="${KualiForm.document.institutionalProposal.awardFundingProposals}" varStatus="status">
            <tr>
                <td><div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.awardFundingProposal[${status.index}].award.awardNumber" attributeEntry="${awardAttributes.awardNumber}" readOnly="true"/></div></td>
                <td><div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.awardFundingProposal[${status.index}].award.sequenceNumber" attributeEntry="${awardAttributes.sequenceNumber}" readOnly="true"/></div></td>
                <td><div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.awardFundingProposal[${status.index}].proposal.sequenceNumber" attributeEntry="${institutionalProposalAttributes.proposalNumber}" readOnly="true"/></div></td>
                <td><div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.awardFundingProposal[${status.index}].award.accountNumber" attributeEntry="${awardAttributes.accountNumber}" readOnly="true"/></div></td>
                <c:if test="${!readOnly}">
                    <td>
                        <div align="center">
                            <html:multibox property="selectedAwardFundingProposals" value="${status.index}" />
                        </div>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        <c:if test="${!readOnly && KualiForm.document.institutionalProposal.awardFundingProposalsExist}">
            <tr>
                <td colspan="4" class="infoline">
                    <div align="center">
                        <html:image property="methodToCall.unlockSelected.anchor${tabKey}"
                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-unlockselected.gif' styleClass="tinybutton"/>
                    </div>
                </td>
                <td nowrap class="infoline">
                    <div align="center">
                        <html:image property="methodToCall.selectAllFundedAwards.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="javascript:selectAllFundedAwards(document);return false" />
                        <html:image property="methodToCall.deselectAllFundedAwards.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-deselectall.gif" title="Deselect All" alt="Deselect All" styleClass="tinybutton" onclick="javascript:unselectAllFundedAwards(document);return false" />
                    </div>
                </td>
            </tr>
        </c:if>
    </tbody>
</table>

</div>
</kul:tab>
