<%--
 Copyright 2006-2007 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

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
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.ActivityType&returnLocation=portal.do&hideReturnLink=true&docFormKey=88888888">Activity Type</a></li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.CarrierType&returnLocation=portal.do&hideReturnLink=true&docFormKey=88888888">Carrier Type</a></li>
  <li>Reg Type</li>
  <li>Result Type</li>
  <li>Proposal Status</li>
  <li>Abstract Type</li>
  <li>Budget Category</li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.DegreeType&returnLocation=portal.do&hideReturnLink=true&docFormKey=88888888">Degree Type</a></li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.MailBy&returnLocation=portal.do&hideReturnLink=true&docFormKey=88888888">Mail By</a></li>
  <li>Narrative Types</li>
  <li>Person Table Editable Columns</li>
  <li>Person Document Type</li>
  <li>Proposal Dev Editable Columns</li>
  <li>Proposal Hierarchy Child Type</li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.ProposalResponse&returnLocation=portal.do&hideReturnLink=true&docFormKey=88888888">Proposal in Response</a></li>
  <li>Development Status</li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.ScienceKeyword&returnLocation=portal.do&hideReturnLink=true&docFormKey=88888888">Science Keyword</a></li>
</ul>

</div>
<channel:portalChannelBottom />