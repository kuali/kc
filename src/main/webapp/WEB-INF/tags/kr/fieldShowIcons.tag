<%--
 Copyright 2007-2008 The Kuali Foundation
 
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

<%@ attribute name="isReadOnly" required="true"
              description="Is the view for this field readOnly?" %>
<%@ attribute name="field" required="true" type="org.kuali.rice.kns.web.ui.Field"
              description="The field for which to show the lookup icon." %>
<%@ attribute name="addHighlighting" required="false"
              description="boolean indicating if this field should be highlighted (to indicate old/new change)" %>            
<%--
				#######################################################
				# If the field has errors, display error icon.
				####################################################### --%>
<kul:checkErrors keyMatch="${field.propertyName}" />
<c:if test="${hasErrors}">
	 <kul:fieldShowErrorIcon />
</c:if>
<kul:fieldShowLookupIcon isReadOnly="${isReadOnly}" field="${field}" anchor="${currentTabIndex}"/>
<kul:fieldShowDirectInquiryIcon isReadOnly="${isReadOnly}" field="${field}" anchor="${currentTabIndex}"/>
<kul:fieldShowExpandedTextareaIcon isReadOnly="${isReadOnly}" field="${field}" anchor="${currentTabIndex}"/>
<c:if test="${field.fieldLevelHelpEnabled || (!field.fieldLevelHelpDisabled && KualiForm.fieldLevelHelpEnabled)}">
<kul:fieldShowHelpIcon isReadOnly="${isReadOnly}" field="${field}" />
</c:if>

<%-- don't render the field changed icon if readonly since the fieldShowReadOnly tag will render it when the field is readonly --%>
<c:if test="${addHighlighting && field.highlightField && !isReadOnly}">
  <kul:fieldShowChangedIcon />
</c:if>

