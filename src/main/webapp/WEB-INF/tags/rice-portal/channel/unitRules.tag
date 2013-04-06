<%--
 Copyright 2005-2013 The Kuali Foundation
 
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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<channel:portalChannelTop channelTitle="Business Rules" />
<div class="body">
  <ul class="chan">
    <li><portal:portalLink displayTitle="true" title="Agenda" url="${ConfigProperties.krad.url}/lookup?methodToCall=start&dataObjectClassName=org.kuali.rice.krms.impl.repository.AgendaBo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Context" url="${ConfigProperties.krad.url}/lookup?methodToCall=start&dataObjectClassName=org.kuali.rice.krms.impl.repository.ContextBo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Attribute Definition" url="${ConfigProperties.krad.url}/lookup?methodToCall=start&dataObjectClassName=org.kuali.rice.krms.impl.repository.KrmsAttributeDefinitionBo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Term" url="${ConfigProperties.krad.url}/lookup?methodToCall=start&dataObjectClassName=org.kuali.rice.krms.impl.repository.TermBo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Term Specification" url="${ConfigProperties.krad.url}/lookup?methodToCall=start&dataObjectClassName=org.kuali.rice.krms.impl.repository.TermSpecificationBo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Category" url="${ConfigProperties.krad.url}/lookup?methodToCall=start&dataObjectClassName=org.kuali.rice.krms.impl.repository.CategoryBo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
  </ul>
</div>
<channel:portalChannelBottom />
