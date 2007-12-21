<%--
 Copyright 2005-2007 The Kuali Foundation.
 
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
<%@ attribute name="textAreaFieldName" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="textAreaLabel" required="true" %>
<%@ attribute name="disabled" required="false" %>

  <c:choose>
    <c:when test="${disabled}">
      <img class="nobord" src="${ConfigProperties.kra.externalizable.images.url}pencil_add1.png" alt="expand textarea">
    </c:when>
    <c:otherwise>
       <html:image property="methodToCall.updateTextArea.((#${textAreaFieldName}:${action}:${textAreaLabel}#))" src='${ConfigProperties.kra.externalizable.images.url}pencil_add.png' onclick="javascript: textAreaPop('${textAreaFieldName}','${action}','${textAreaLabel}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}');return false" styleClass="tinybutton"  title="Expanded Text Area" alt="Expanded Text Area"/>
    </c:otherwise>
  </c:choose>