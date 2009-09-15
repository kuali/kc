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

<channel:portalChannelTop channelTitle="KNS Maintenance Documents" />
<div class="body">
  
  <ul class="chan">
  	 <li><portal:portalLink displayTitle="true" title="Parameter Lookup" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.Parameter&docFormKey=88888888&returnLocation=http://localhost:8080/kr-dev/portal.do&hideReturnLink=true" /></li>
     <li><portal:portalLink displayTitle="true" title="Parameter Component Lookup" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.ParameterDetailType&docFormKey=88888888&returnLocation=http://localhost:8080/kr-dev/portal.do&hideReturnLink=true" /></li>
     <li><portal:portalLink displayTitle="true" title="Parameter Type Lookup" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.ParameterType&docFormKey=88888888&returnLocation=http://localhost:8080/kr-dev/portal.do&hideReturnLink=true" /></li>
     <li><portal:portalLink displayTitle="true" title="System Namespace Lookup" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.Namespace&docFormKey=88888888&returnLocation=http://localhost:8080/kr-dev/portal.do&hideReturnLink=true" /></li>
  	 <li><portal:portalLink displayTitle="true" title="Pessimistic Lock Lookup" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.document.authorization.PessimisticLock&returnLocation=http://localhost:8080/kr-dev/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  	 <li><portal:portalLink displayTitle="true" title="New KEW Document Type Lookup" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kew.doctype.bo.DocumentType&returnLocation=http://localhost:8080/kr-dev/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  	 <li><portal:portalLink displayTitle="true" title="Document Lookup" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kew.docsearch.DocSearchCriteriaDTO&docFormKey=88888888&returnLocation=http://localhost:8080/kr-dev/portal.do&hideReturnLink=true" /></li>
  </ul>

  
</div>
<channel:portalChannelBottom />
