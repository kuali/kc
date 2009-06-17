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

<channel:portalChannelTop channelTitle="Workflow" />
<div class="body">
  <ul class="chan">
  <!--  TODO : not sure what it is in rice 1.1 
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/ApplicationConstants.do'>Application Constants</portal:portalLink></li> -->
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='kew/DocumentOperation.do'>Document Operation</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='kr/lookup.do?start&businessObjectClassName=org.kuali.rice.kew.doctype.bo.DocumentType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true'>Document Type</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.bo.RuleAttribute&docFormKey=88888888&returnLocation=Administration.do&hideReturnLink=true'>Rule Attribute</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.bo.RuleTemplate&docFormKey=88888888&returnLocation=Administration.do&hideReturnLink=true'>Rule Template</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/Stats.do'>Statistics Report</portal:portalLink></li>
  <!--  TODO : not sure what it is in rice 1.1 
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='kr/lookup.do?businessObjectClassName=org.kuali.rice.kim.bo.group.impl.KimGroupImpl&docFormKey=88888888&returnLocation=Administration.do&hideReturnLink=true'>Workgroup Type</portal:portalLink></li> -->
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/Ingester.do'>XML Ingester</portal:portalLink></li>
  </ul>
</div>
<channel:portalChannelBottom />
