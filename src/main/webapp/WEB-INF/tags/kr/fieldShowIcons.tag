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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="isReadOnly" required="true"
              description="Is the view for this field readOnly?" %>
<%@ attribute name="field" required="true" type="org.kuali.core.web.ui.Field"
              description="The field for which to show the lookup icon." %>
<%@ attribute name="addHighlighting" required="false"
              description="boolean indicating if this field should be highlighted (to indicate old/new change)" %>            

<kul:fieldShowLookupIcon isReadOnly="${isReadOnly}" field="${field}" anchor="${currentTabIndex}"/>
<kul:fieldShowHelpIcon isReadOnly="${isReadOnly}" field="${field}" />

<%-- don't render the field changed icon if readonly since the fieldShowReadOnly tag will render it when the field is readonly --%>
<c:if test="${addHighlighting && field.highlightField && !isReadOnly}">
  <kul:fieldShowChangedIcon />
</c:if>