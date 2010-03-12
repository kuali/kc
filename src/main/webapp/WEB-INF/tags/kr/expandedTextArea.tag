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
<%@ attribute name="textAreaFieldName" required="true" description="The name of the field populated by the text area window." %>
<%@ attribute name="action" required="true" description="The name of the action method to be filled in by the text area window." %>
<%@ attribute name="textAreaLabel" required="true" description="The label to render as part of the text area." %>
<%@ attribute name="disabled" required="false" description="Whether the text area control will be rendered as disabled." %>
<%@ attribute name="formKey" required="false" description="The unique key of the form which holds the document holding the field being populated by the text area." %>
<%@ attribute name="sessionDocument" required="false" description="An unused attribute." %>
<%@ attribute name="title" required="false" description="The title of the text area window." %>
<%@ attribute name="readOnly" required="false" description="Whether this field should be rendered as read only." %>
<%@ attribute name="maxLength" required="false" description="The maximum length of the text that can be entered into the text area." %>

<c:if test="${empty formKey}">
  <c:set var="formKey" value="88888888" />
</c:if>


  <c:choose>
    <c:when test="${disabled}">
      <img class="nobord" 
           src="${ConfigProperties.kr.externalizable.images.url}pencil_add1.png"
           alt="Expand Text Area">
    </c:when>
    <c:otherwise>
	    <c:choose>
	    	<c:when test="${readOnly}">
	           <c:set var="srcImage" value="${ConfigProperties.kr.externalizable.images.url}openreadonly_greenarrow01.png"/>
	           <c:set var="altMsg" value="View Text"/>
	    	</c:when>
	    	<c:otherwise>
	           <c:set var="readOnly" value="false" />
	           <c:set var="srcImage" value="${ConfigProperties.kr.externalizable.images.url}pencil_add.png"/>
	           <c:set var="altMsg" value="Expand Text Area"/>
	   		</c:otherwise>
	  	</c:choose>
    
       <html:image property="methodToCall.updateTextArea.((#${textAreaFieldName}:${action}:${textAreaLabel}:${readOnly}:${maxLength}#))"
                   src="${srcImage}"
                   onclick="javascript: textAreaPop('${textAreaFieldName}','${action}','${textAreaLabel}','${formKey}','${readOnly}','${maxLength}');return false"
                   styleClass="tinybutton"
                   title="${title}"
                   alt="${altMsg}"/>
    </c:otherwise>
  </c:choose>
