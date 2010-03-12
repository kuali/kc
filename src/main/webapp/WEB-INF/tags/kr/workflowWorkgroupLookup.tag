<%--
 Copyright 2005-2007 The Kuali Foundation

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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="fieldConversions" required="false" description="Pre-set values to populate within the lookup form." %>
<%@ attribute name="lookupParameters" required="false" description="On return from lookup, these parameters describe which attributes of the business object to populate in the lookup parent." %>
<%@ attribute name="tabKey" required="false" description="The tab key to use as the HTML named anchor to scroll to on return from the lookup." %>

<%--<input type="image" tabindex="-1" name="methodToCall.performWorkgroupLookup.(((${fieldConversions})))" title="Search Workgroup" alt="Search Workgroup" src="${ConfigProperties.kr.externalizable.images.url}searchicon.gif" border="0" class="tinybutton" />--%>
<kul:lookup boClassName="org.kuali.rice.kim.bo.impl.GroupImpl"
    fieldConversions="${fieldConversions}"
    lookupParameters="${lookupParameters}"
    anchor="${tabKey}"
/>
