<%--
 Copyright 2007 The Kuali Foundation
 
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

<%@ attribute name="userIdFieldName" required="true" %>
<%@ attribute name="universalIdFieldName" required="true" %>
<%@ attribute name="userNameFieldName" required="true" %>
<%@ attribute name="userId" required="true" %>
<%@ attribute name="universalId" required="true" %>
<%@ attribute name="userName" required="true" %>

<%@ attribute name="label" required="false" %>
<%@ attribute name="fieldConversions" required="false" %>
<%@ attribute name="lookupParameters" required="false" %>
<%@ attribute name="referencesToRefresh" required="false" %>

<%@ attribute name="renderOtherFields" required="false" %>

<%@ attribute name="hasErrors" required="false" %>
<%@ attribute name="readOnly" required="false" %>
<%@ attribute name="onblur" required="false" %>

<%@ attribute name="helpLink" required="false" %>

<%@ attribute name="highlight" required="false"
              description="boolean indicating if this field is rendered as highlighted (to indicate old/new value change)" %> 


<%-- if the universal user ID field is a key field on this document, lock-down the user ID field --%>
<c:choose>
  <c:when test="${readOnly}">
    <input type="hidden" id='<c:out value="${userIdFieldName}"/>' name='<c:out value="${userIdFieldName}"/>' value='<c:out value="${userId}"/>' />
    <kul:inquiry boClassName="org.kuali.rice.kim.bo.Person" keyValues="principalId=${universalId}&principalName=${userId}" render="true"><c:out value="${userId}" /></kul:inquiry>&nbsp;
  </c:when>
  <c:otherwise>
    ${kfunc:registerEditableProperty(KualiForm, userIdFieldName)}
    <input type="text" id='<c:out value="${userIdFieldName}"/>' name='<c:out value="${userIdFieldName}"/>' value='<c:out value="${userId}"/>'
    title='${DataDictionary.PersonImpl.attributes.principalName.label}'
    size='${DataDictionary.PersonImpl.attributes.principalName.control.size}' 
    maxlength='${DataDictionary.PersonImpl.attributes.principalName.maxLength}' style="${textStyle}"
    onBlur="loadUserInfo( '${userIdFieldName}', '${universalIdFieldName}', '${userNameFieldName}' );${onblur}" />
    <c:if test="${hasErrors}">
     <kul:fieldShowErrorIcon />
    </c:if>
    <kul:lookup boClassName="org.kuali.rice.kim.bo.Person" 
          fieldConversions="${fieldConversions}" 
          lookupParameters="${lookupParameters}" 
          fieldLabel="${label}" 
          referencesToRefresh="${referencesToRefresh}"
          anchor="${currentTabIndex}" />
  </c:otherwise>
</c:choose>
<c:choose>
  <c:when test="${readOnly}">
    -
  </c:when>
  <c:otherwise>
    ${helpLink}
    <br />
  </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${!empty userNameFieldName}">
        <span id="${userNameFieldName}.div">${userName}&nbsp;</span>
    </c:when>
    <c:otherwise><%-- guess at the name if the name field is not being rendered --%>
        <span id="${fn:replace( userIdFieldName, ".principalName", ".name" )}.div">${userName}&nbsp;</span>
        <%-- When the user name field is not set, most likely, the name is not passed through
             (It is also not available to be passed in, since only the Field objects are present
             for use by rowDisplay.tag.  So, we fire off the needed JS to update the name. --%>
        <c:if test="${empty userName && !(empty userId)}">
            <script type="text/javascript">loadUserInfo( "${userIdFieldName}", "", "" );</script>
        </c:if>
    </c:otherwise>
</c:choose>
  
<c:if test="${renderOtherFields}">
  <c:if test="${!empty universalIdFieldName}">
    ${kfunc:registerEditableProperty(KualiForm, universalIdFieldName)}
    <input type="hidden" name="${universalIdFieldName}" id="${universalIdFieldName}" value="${universalId}" />
  </c:if>
  <c:if test="${!empty userNameFieldName}">
    ${kfunc:registerEditableProperty(KualiForm, userNameFieldName)}
    <input type="hidden" name="${userNameFieldName}" id="${userNameFieldName}" value="${userName}" />
  </c:if>
</c:if>

<c:if test="${highlight}">
<kul:fieldShowChangedIcon/>
</c:if>
