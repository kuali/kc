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

<channel:portalChannelTop channelTitle="Proposals" />
<div class="body">
<ul class="chan">  
  <li><portal:portalLink displayTitle="true" title="Activity Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.ActivityType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Reg Type</li>
  <li>Result Type</li>
  <li><portal:portalLink displayTitle="true" title="Abstract Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.AbstractType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Budget Category" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.BudgetCategory&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Budget Category Mapping" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.BudgetCategoryMapping&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Budget Category Maps" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.BudgetCategoryMap&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Budget Category Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.BudgetCategoryType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Budget Status" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.BudgetStatus&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li></li>  
  <li><portal:portalLink displayTitle="true" title="Cost Element" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.CostElement&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li></li>  
  <li><portal:portalLink displayTitle="true" title="Degree Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.DegreeType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Deadline Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.DeadlineType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>  
  <li><portal:portalLink displayTitle="true" title="Institute La Rate" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.InstituteLaRate&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Institute Rate" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.InstituteRate&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Mail By" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.MailBy&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Mail Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.MailType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Narrative Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.NarrativeType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Narrative Status" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.NarrativeStatus&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Person Table Editable Columns" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.PersonEditableField&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Person Document Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.PropPerDocType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Rate Class" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.RateClass&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li></li>    
  <li><portal:portalLink displayTitle="true" title="Rate Class Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.RateClassType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li></li>    
  <li><portal:portalLink displayTitle="true" title="Rate Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.RateType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="S2S Submission Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.s2s.bo.S2sSubmissionType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="S2S Revision Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.S2sRevisionType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Proposal Dev Editable Columns</li>
  <li>Proposal Hierarchy Child Type</li>
  <li>Development Status</li>
  <li><portal:portalLink displayTitle="true" title="Keyword" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.ScienceKeyword&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="TBN Person" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.TbnPerson&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Valid Calculation Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.ValidCalcType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li></li>  	
  <li><portal:portalLink displayTitle="true" title="Valid Cost Element Rate Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.budget.bo.ValidCeRateType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li></li>  
</ul>

</div>
<channel:portalChannelBottom />
