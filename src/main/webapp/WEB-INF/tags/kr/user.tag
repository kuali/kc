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

<%@ attribute name="userIdFieldName" required="true" description="The name of the property which will be populated with the principal name." %>
<%@ attribute name="universalIdFieldName" required="true" description="The name of the field which will be populated with the principal id." %>
<%@ attribute name="userNameFieldName" required="true" description="The field which will be populated with the principal name, for displaying." %>
<%@ attribute name="userId" required="true" description="The passed in principal name." %>
<%@ attribute name="universalId" required="true" description="The passed in principal id." %>
<%@ attribute name="userName" required="true" description="The passed in principal name, for display." %>

<%@ attribute name="label" required="false" description="The label to be shown on the linked lookup screen." %>
<%@ attribute name="fieldConversions" required="false" description="Pre-set values to populate within the lookup form." %>
<%@ attribute name="lookupParameters" required="false" description="On return from lookup, these parameters describe which attributes of the business object to populate in the lookup parent." %>
<%@ attribute name="referencesToRefresh" required="false" description="On return from the lookup, the references on the parent business object which will be refreshed." %>

<%@ attribute name="renderOtherFields" required="false" description="If true, renders the principal id and the principal name as hidden fields." %>

<%@ attribute name="hasErrors" required="false" description="Whether an error icon should be displayed by the field or not." %>
<%@ attribute name="readOnly" required="false" description="Whether this field should be displayed as read only or not." %>
<%@ attribute name="onblur" required="false" description="Javascript code which will be executed with the input field's onblur event is triggered." %>

<%@ attribute name="helpLink" required="false" description="HTML - not just a URL - to be displayed next to the input field, presumably linking to a help resource." %>

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
