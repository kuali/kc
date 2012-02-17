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

<channel:portalChannelTop channelTitle="Awards" />
<c:set var="financialIntegration" value='<%=org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator.getParameterService().getParameterValueAsBoolean("KC-AWARD", "Document", "FIN_SYSTEM_INTEGRATION_ON")%>' />
<div class="body">
  <ul class="chan">
    <li><portal:portalLink displayTitle="true" title="Account Type" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.AccountType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li><portal:portalLink displayTitle="true" title="Attachment Type" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.notesandattachments.attachments.AwardAttachmentType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
	<li><portal:portalLink displayTitle="true" title="Award Status" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardStatus&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li><portal:portalLink displayTitle="true" title="Award Type" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li><portal:portalLink displayTitle="true" title="Basis of Payment" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardBasisOfPayment&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li><portal:portalLink displayTitle="true" title="Valid Award Basis of Payment" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.ValidAwardBasisPayment&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li><portal:portalLink displayTitle="true" title="Valid Class Report Frequency" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.ValidClassReportFrequency&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li><portal:portalLink displayTitle="true" title="Valid Frequency Base" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.ValidFrequencyBase&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li><portal:portalLink displayTitle="true" title="Valid Basis and Method of Payment" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.ValidBasisMethodPayment&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <c:if test="${financialIntegration == 'ON'}">
        <li><portal:portalLink displayTitle="true" title="CFDA" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.CFDA&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    </c:if>
    <li><portal:portalLink displayTitle="true" title="Contact Type" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.ContactType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <c:if test="${financialIntegration == 'ON'}">
    <li><portal:portalLink displayTitle="true" title="Financial Indirect Cost Recovery Type Code" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.external.award.FinancialIndirectCostRecoveryTypeCode&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Financial Object Code Mapping" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.external.budget.FinancialObjectCodeMapping&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    </c:if>
    <li><portal:portalLink displayTitle="true" title="Frequency" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.Frequency&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Frequency Base" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.FrequencyBase&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Method of Payment" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardMethodOfPayment&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="OSP File Copy" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.Distribution&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Report" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.Report&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>    
    <li><portal:portalLink displayTitle="true" title="Report Class" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.ReportClass&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Report Status" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.ReportStatus&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
  	<li><portal:portalLink displayTitle="true" title="Sponsor Template" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardTemplate&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  	<li><portal:portalLink displayTitle="true" title="Sponsor Terms" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SponsorTerm&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  	<!--<li>--><!--<portal:portalLink displayTitle="true" title="Award Template Contact" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardTemplateContact&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" />--><!--</li>-->
  	<!--<li>--><!--<portal:portalLink displayTitle="true" title="Award Template Comment" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardTemplateComment&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" />--><!--</li>-->
  	<li><portal:portalLink displayTitle="true" title="Award Transaction Type" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.timeandmoney.transactions.AwardTransactionType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  </ul>
</div>
<channel:portalChannelBottom />
