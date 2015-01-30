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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />

<kul:documentPage
    showDocumentInfo="true"
    htmlFormAction="resubmissionPrompt"
    documentTypeName="ProposalDevelopmentDocument"
    renderMultipart="false"
    showTabButtons="false"
    auditCount="0"
    headerTabActive="actions">

<kul:tabTop tabTitle="Resubmission Options" defaultOpen="true" tabErrorKey="resubmissionOption">

    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Resubmission Options</span>
            <span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
            <c:if test="${not empty KualiForm.document.developmentProposal.continuedFrom}">
            <tr>
                <th align="right" valign="middle"><html:radio property="resubmissionOption" value="O"/></th>
                <td align="left" valign="middle">Generate a new version of Original Institutional Proposal (<c:out value="${KualiForm.document.developmentProposal.continuedFrom}" />)</td>
            </tr>
            </c:if>
            <tr>
                <th align="right" valign="middle"><html:radio property="resubmissionOption" value="A" /></th>
                <td align="left" valign="middle">
                    Generate a new version of Institutional Proposal:
                    <kul:htmlControlAttribute property="institutionalProposalToVersion" attributeEntry="${proposalDevelopmentAttributes.continuedFrom}" />
                    <kul:lookup boClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposal" fieldConversions="proposalNumber:institutionalProposalToVersion" anchor="${tabKey}" />
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle"><html:radio property="resubmissionOption" value="N" /></th>
                <td align="left" valign="middle">Generate a new Institutional Proposal</td>
            </tr>
            <tr>
                <th align="right" valign="middle"><html:radio property="resubmissionOption" value="X" /></th>
                <td align="left" valign="middle">Do not generate a new Institutional Proposal or Institutional Proposal version</td>
            </tr>
        </table>
    </div>

</kul:tabTop>
<kul:panelFooter />

<div id="globalbuttons" class="globalbuttons">
<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_continue.gif" styleClass="globalbuttons" property="methodToCall.proceed" title="continue" alt="continue"/>
<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" property="methodToCall.cancel" title="cancel" alt="cancel"/>
</div>

</kul:documentPage>
