<%--
 Copyright 2007-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp"%>

<channel:portalChannelTop channelTitle="KNS Maintenance Documents" />
<div class="body">
  
  <ul class="chan">
     <li><portal:portalLink displayTitle="true" title="Parameter Lookup" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.Parameter&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
     <li><portal:portalLink displayTitle="true" title="Parameter Component Lookup" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.ParameterDetailType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
     <li><portal:portalLink displayTitle="true" title="Parameter Type Lookup" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.ParameterType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
     <li><portal:portalLink displayTitle="true" title="System Namespace Lookup" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.Namespace&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
  	 <li><portal:portalLink displayTitle="true" title="Document Lookup" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kew.docsearch.DocSearchCriteriaDTO&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
  </ul>

  
</div>
<channel:portalChannelBottom />
