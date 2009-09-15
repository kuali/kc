<%--
 Copyright 2006-2009 The Kuali Foundation
 
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
<div class="body">
  <ul class="chan">
    <li><portal:portalLink displayTitle="true" title="Account Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.AccountType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
	<li><portal:portalLink displayTitle="true" title="Award Status" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardStatus&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li><portal:portalLink displayTitle="true" title="Award Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li>Basis of Payment</li>
    <li>Calculated Cost Elements</li>
    <li><portal:portalLink displayTitle="true" title="Contact Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.ContactType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Distribution" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.Distribution&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Equipment Approval</li>
    <li><portal:portalLink displayTitle="true" title="Frequency" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.Frequency&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Frequency Base" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.FrequencyBase&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="IP Review Activity Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReviewActivityType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="IP Review Requirement Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReviewRequirementType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="IP Review Result Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReviewResultType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Invention</li>
    <li>Method of Payment</li>
    <li>Prior Approval</li>
    <li>Property</li>
    <li>Publication</li>
    <li>Referenced Document</li>
    <li><portal:portalLink displayTitle="true" title="Report" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.Report&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>    
    <li><portal:portalLink displayTitle="true" title="Report Class" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.ReportClass&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Report Status</li>
    <li>Rights to Data</li>
    <li>Sub-Contract Approval</li>
    <li>Travel Restriction</li>
  	<li><portal:portalLink displayTitle="true" title="Award Template" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardTemplate&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  	<!--<li>--><!--<portal:portalLink displayTitle="true" title="Award Template Contact" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardTemplateContact&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" />--><!--</li>-->
  	<!--<li>--><!--<portal:portalLink displayTitle="true" title="Award Template Comment" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.AwardTemplateComment&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" />--><!--</li>-->
  </ul>
</div>
<channel:portalChannelBottom />
