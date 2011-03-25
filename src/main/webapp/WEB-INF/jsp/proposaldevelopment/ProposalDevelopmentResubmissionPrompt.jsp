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
