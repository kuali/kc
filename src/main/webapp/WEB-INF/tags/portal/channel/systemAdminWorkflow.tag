<%--
 Copyright 2006 The Kuali Foundation.
 
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

<channel:portalChannelTop channelTitle="Workflow" />
<div class="body">
  <ul class="chan">
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/ApplicationConstants.do'>Application Constants</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/DocumentOperation.do'>Document Operation</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/Lookup.do?lookupableImplServiceName=DocumentTypeLookupableImplService'>Document Type</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/Lookup.do?lookupableImplServiceName=RuleAttributeLookupableImplService'>Rule Attribute</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/Lookup.do?lookupableImplServiceName=RuleTemplateLookupableImplService'>Rule Template</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/Stats.do'>Statistics Report</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/Lookup.do?lookupableImplServiceName=WorkgroupTypeLookup'>Workgroup Type</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='Workflow Services' url='${ConfigProperties.workflow.url}/Ingester.do'>XML Ingester</portal:portalLink></li>
  </ul>
</div>
<channel:portalChannelBottom />