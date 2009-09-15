<%--
 Copyright 2007 The Kuali Foundation.
 
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
<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp"%>

<channel:portalChannelTop channelTitle="Sample Travel Application" />
<div class="body">
  
  <ul class="chan">
	 <li><portal:portalLink displayTitle="true" title="Create New Sample Application Travel Request (KualiDocumentActionBase)" url="travelDocument2.do?methodToCall=docHandler&command=initiate&docTypeName=TravelRequest" /></li>
  	 <li><portal:portalLink displayTitle="true" title="Travel Account Lookup" url="kr/lookup.do?methodToCall=start&businessObjectClassName=edu.sampleu.travel.bo.TravelAccount&returnLocation=http://localhost:8080/kr-dev/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  	 <li><portal:portalLink displayTitle="true" title="Travel Fiscal Officer Lookup" url="kr/lookup.do?methodToCall=start&businessObjectClassName=edu.sampleu.travel.bo.FiscalOfficer&returnLocation=http://localhost:8080/kr-dev/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  	 <li><portal:portalLink displayTitle="true" title="Travel Account Type Lookup" url="kr/lookup.do?methodToCall=start&businessObjectClassName=edu.sampleu.travel.bo.TravelAccountType&returnLocation=http://localhost:8080/kr-dev/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  </ul>

  
</div>
<channel:portalChannelBottom />
