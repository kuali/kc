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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="textAreaFieldName" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="textAreaLabel" required="true" %>
<%@ attribute name="disabled" required="false" %>
<%@ attribute name="viewOnly" required="false" %>

  <c:choose>
    <c:when test="${viewOnly}">
           <c:set var="srcImage" value="${ConfigProperties.kra.externalizable.images.url}openreadonly_greenarrow01.png"/>
           <c:set var="altMsg" value="View Text"/>
    </c:when>
    <c:otherwise>
           <c:set var="viewOnly" value="false" />
           <c:set var="srcImage" value="${ConfigProperties.kr.externalizable.images.url}pencil_add.png"/>
           <c:set var="altMsg" value="Expand Text Area"/>
    </c:otherwise>
  </c:choose>

<c:if test="${empty readOnly or readOnly != true}" > 
  <c:choose>
    <c:when test="${disabled}">
      <img class="nobord" src="${ConfigProperties.kra.externalizable.images.url}pencil_add1.png" alt="expand textarea">
    </c:when>
    <c:otherwise>
       <c:set var="formKey" value="${KualiForm.formKey}" />
       <c:if test="${empty KualiForm.formKey}">
           <c:set var="formKey" value="0" />
       </c:if>
       <html:image property="methodToCall.kraUpdateTextArea.((#${textAreaFieldName}:${action}:${textAreaLabel}:${viewOnly}#))" src='${srcImage}' onclick="javascript: kraTextAreaPop('${textAreaFieldName}','${action}','${textAreaLabel}',${formKey},'${KualiForm.document.sessionDocument}','${viewOnly}');return false" styleClass="tinybutton"  title="${altMsg }" alt="${altMsg }"/>
    </c:otherwise>
  </c:choose>
</c:if>
