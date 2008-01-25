<%--
 Copyright 2005-2006 The Kuali Foundation.
 
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

<%@ attribute name="channelTitle" required="true" %>
<%@ attribute name="channelUrl" required="true" %>

<!--  temp fix for htmlunit bug 
<iframe src="${channelUrl}" onload='setFocusedIframeDimensions("iframeportlet", 500, true); setIframeAnchor("iframeportlet")' name="iframeportlet" id="iframeportlet" hspace="0" vspace="0" style="height: 500px;" title="E-Doc" frameborder="0" height="500" scrolling="auto" width="100%"></iframe>
-->
<iframe src="${channelUrl}" onload='setFocusedIframeDimensions("iframeportlet", 500, true)' name="iframeportlet" id="iframeportlet" hspace="0" vspace="0" style="height: 500px;" title="E-Doc" frameborder="0" height="500" scrolling="auto" width="100%"></iframe>
                     
