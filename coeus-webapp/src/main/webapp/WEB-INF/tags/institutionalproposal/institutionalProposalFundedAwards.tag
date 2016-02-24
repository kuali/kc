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
            <c:if test="${!readOnly && KualiForm.document.institutionalProposal.awardFundingProposalsExist}">
                <th>Actions</th>
            </c:if>
        </tr>
        <c:forEach var="awardFundingProposal" items="${KualiForm.document.institutionalProposal.allFundingProposals}" varStatus="status">
            <tr>
                <td><div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.allFundingProposals[${status.index}].award.awardNumber" attributeEntry="${awardAttributes.awardNumber}" readOnly="true"/></div></td>
                <td><div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.allFundingProposals[${status.index}].award.sequenceNumber" attributeEntry="${awardAttributes.sequenceNumber}" readOnly="true"/></div></td>
                <td><div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.allFundingProposals[${status.index}].proposal.sequenceNumber" attributeEntry="${institutionalProposalAttributes.proposalNumber}" readOnly="true"/></div></td>
                <td><div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.allFundingProposals[${status.index}].award.accountNumber" attributeEntry="${awardAttributes.accountNumber}" readOnly="true"/></div></td>
                <c:if test="${!readOnly && KualiForm.document.institutionalProposal.awardFundingProposalsExist}">
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
