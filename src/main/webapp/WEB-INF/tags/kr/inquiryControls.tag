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

<div id="globalbuttons" class="globalbuttons">
        <c:if test="${KualiForm.canExport}">
		  <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_export.gif" styleClass="globalbuttons" property="methodToCall.export" title="Perform Export" alt="Perform Export" />
	    </c:if>
		<SCRIPT>
	        document.write('<input type="image" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" class="globalbuttons" onclick=\"window.close()\" title="close this window" alt="close this window">');
        </SCRIPT>
        <NOSCRIPT>
            &nbsp;
        </NOSCRIPT>
</div>
