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

<%@ attribute name="property" required="true" %>
<%@ attribute name="size" required="true" %>
<%@ attribute name="maxLength" required="true" %>

<%@ attribute name="accessibilityHint" required="false"
        description="Use this to attach further information to the title attribute of a field
        if present"%>

<kul:checkErrors keyMatch="${property}"/>

<c:choose>
	<c:when test="${!empty accessibilityHint}">
		<html:text property="${property}" styleId="${property}" size="${size}" maxlength="${maxLength}" style="${textStyle}" alt="${accessibilityHint}" title="${accessibilityHint}" />
	</c:when>
	<c:otherwise>
		<html:text property="${property}" styleId="${property}" size="${size}" maxlength="${maxLength}" style="${textStyle}"/>
	</c:otherwise>
</c:choose>
<c:if test="${hasErrors==true}">
  <kul:fieldShowErrorIcon />
</c:if>
<img src="${ConfigProperties.kr.externalizable.images.url}cal.gif" id="${property}_datepicker" style="cursor: pointer;" alt="Date selector" title="Date selector" onmouseover="this.style.background='#F00';" onmouseout="this.style.background='#FFF';" />    
<script type="text/javascript">
	Calendar.setup(
	{
    	inputField : "${property}", // ID of the input field
    	ifFormat : "%m/%d/%Y", // the date format
    	button : "${property}_datepicker" // ID of the button
    }
    );
</script>

