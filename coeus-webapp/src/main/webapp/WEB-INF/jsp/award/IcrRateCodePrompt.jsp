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

<c:set var="validRatesAttributes" value="${DataDictionary.ValidRates.attributes}" />

<kul:documentPage
    showDocumentInfo="true"
    htmlFormAction="icrRateCodePrompt"
    documentTypeName="${KualiForm.documentTypeName}"
    renderMultipart="false"
    showTabButtons="false"
    auditCount="0"
    headerTabActive="actions">
    
    <div align="center">
    <b>Selected F&A Rate maps to more than one possible ICR Rate Code. Please select an ICR Rate Code for the Account, or select 'Create Account with no ICR Rate Code' to fill it in later.</b>
    </div>
    <br />
    
    <kul:tabTop tabTitle="Select ICR Rate Code" defaultOpen="true" tabErrorKey="accountCreationHelper.selectedIcrRateCode">

    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Select ICR Rate Code</span>
            <span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0">
        <tr>
            <th align="center">select</th>
            <th align="center"><kul:htmlAttributeLabel attributeEntry="${validRatesAttributes.icrRateCode}" noColon="true" /></th>
            <th align="center"><kul:htmlAttributeLabel attributeEntry="${validRatesAttributes.validRatesId}" noColon="true" /></th>
            <th align="center"><kul:htmlAttributeLabel attributeEntry="${validRatesAttributes.onCampusRate}" noColon="true" /></th>
            <th align="center"><kul:htmlAttributeLabel attributeEntry="${validRatesAttributes.offCampusRate}" noColon="true" /></th>
            <th align="center"><kul:htmlAttributeLabel attributeEntry="${validRatesAttributes.adjustmentKey}" noColon="true" /></th>
        </tr>
        <c:forEach var="validRate" items="${KualiForm.accountCreationHelper.validRateCandidates}" varStatus="status">
            <tr>
                <th align="center" valign="middle"><html:radio property="accountCreationHelper.selectedIcrRateCode" value="${validRate.icrRateCode}"/></th>
                <td align="center" valign="middle"><c:out value="${validRate.icrRateCode}" /></td>
                <td align="center" valign="middle"><c:out value="${validRate.validRatesId}" /></td>
                <td align="center" valign="middle"><c:out value="${validRate.onCampusRate}" /></td>
                <td align="center" valign="middle"><c:out value="${validRate.offCampusRate}" /></td>
                <td align="center" valign="middle"><c:out value="${validRate.adjustmentKey}" /></td>
            </tr>
        </c:forEach>
        <tr>
            <th align="center" valign="middle"><html:radio property="accountCreationHelper.selectedIcrRateCode" value="ICRNONE"/></th>
            <td colspan="5">Create Account with no ICR Rate Code</td>
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
