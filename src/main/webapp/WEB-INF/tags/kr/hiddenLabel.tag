<%--
 Copyright 2005-2008 The Kuali Foundation
 
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
<%@ tag body-content="empty" %>

<%@ attribute name="property" required="true" description="A control which renders a property as both a hidden field and as a property." %>

<%-- This tag is from textAttribute.tag -r 1.3's isLabel attribute.
When I refactored testAttribute.tag and textareaAttribute.tag into htmlControlAttribute.tag
for KULNRVSYS-872, this part of the tag didn't fit because it wasn't using the DataDictionary's
attribute entry, so I refactored this part into this separate tag.
I'm not sure what it's doing or what the following comment means. --%>

<%-- would be better to use the bean:write but had problems to get the KualiForm into property with JSTL
${KualiForm.document.budget.fringeRates[ctr].appointmentType.fringeRateAmount} --%>
<html:hidden property="${property}"/><bean:write name="KualiForm" property="${property}"/>

