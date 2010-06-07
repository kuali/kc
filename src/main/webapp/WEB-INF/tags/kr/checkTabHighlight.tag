<%--
 Copyright 2005-2010 The Kuali Foundation
 
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


<%@ attribute name="rows" required="true" type="java.util.List"
        description="rows containing fields to iterate through and check for a highlighted field" %>
<%@ attribute name="addHighlighting" required="false"
              description="boolean to add HTML tags if this field should be highlighted (to indicate old/new change)" %>       

<%-- iterates through the list of rows, and each row's fields, if the field is marked as highlighted, meaning has changed
on maintenance document, then sets a var to indicate the tab should be highlighted as well --%>
<c:set var="tabHighlight" value="false" scope="request"/>

<c:if test="${addHighlighting}">
  <c:forEach items="${rows}" var="row">
    <c:forEach items="${row.fields}" var="field"> 
        <c:if test="${(field.fieldType eq field.CONTAINER) && !tabHighlight}" >
           <%-- cannot refer to recursive tag (checkTabHighlight) using kul alias or Jetty 7 will have jsp compilation errors on Linux --%>
           </WEB-INF/tags/kr:checkTabHighlight rows="${field.containerRows}" addHighlighting="${addHighlighting}" />  
        </c:if>
      
  	    <c:if test="${field.highlightField}">
          <c:set var="tabHighlight" value="true" scope="request"/>
  	    </c:if>
  	</c:forEach>
  </c:forEach>
</c:if>
