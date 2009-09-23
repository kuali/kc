<%--
 Copyright 2008-2009 The Kuali Foundation
 
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>
<c:set var="maxRoleChildrenToDisplay" value="5" />
<c:choose>
  <c:when test="${level == 0}">
    <c:set var="fontStyle" value="color:black"/>
    <c:set var="headerClass" value="headercell4"/>
    <c:set var="datacell" value="headercell4"/>
  </c:when>
  <c:when test="${level == 1}">
    <c:set var="fontStyle" value="color:white"/>
    <c:set var="headerClass" value="headercell3-b-l"/>
    <c:set var="datacell" value="headercell4"/>
  </c:when>
  <c:otherwise>
    <c:set var="fontStyle" value=""/>
    <c:set var="headerClass" value="thnormal"/>
    <c:set var="datacell" value="datacell"/>
  </c:otherwise>
</c:choose>
<tr>
    <td align="center" align="right" class="<c:out value="${headerClass}"/>">
        <c:if test="${level == 0 || !empty actionRequest.childrenRequests }">
            <a id="A<c:out value="${index}" />" onclick="rend(this, false)"><img src="images/tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F<c:out value="${index}" />"></a>
        </c:if>
        &nbsp;
    </td>
    <td align="center" class="<c:out value="${headerClass}"/>" nowrap="nowrap">
        <b><c:out value="${actionRequest.displayStatus}" />
        <c:if test="${actionRequest.displayStatus != null}">
            <br>
        </c:if>
        <c:out value="${actionRequest.actionRequestedLabel}" />
    </td>
    
    <td align="left" class="<c:out value="${headerClass}"/>" nowrap="nowrap">
        <c:choose>
            <c:when test="${actionRequest.userRequest}">
                <kul:inquiry boClassName="org.kuali.rice.kim.bo.Person" keyValues="principalId=${actionRequest.principalId}" render="true"><c:out value="${actionRequest.displayName}" /></kul:inquiry>
                &nbsp;
                <c:choose>
                  <c:when test="${actionRequest.delegationType == KEWConstants.DELEGATION_SECONDARY}">
                    <bean-el:message key="routeLog.ActionRequests.actionRequest.label.secondaryDelegate"/>
                  </c:when>
                  <c:when test="${actionRequest.delegationType == KEWConstants.DELEGATION_PRIMARY}">
                    <bean-el:message key="routeLog.ActionRequests.actionRequest.label.primaryDelegate"/>
                  </c:when>
                </c:choose>
            </c:when>
            <c:when test="${actionRequest.groupRequest}">
                <kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.GroupImpl" keyValues="groupId=${actionRequest.groupId}" render="true"><c:out value="${actionRequest.groupName}" /></kul:inquiry>
                <c:choose>
                  <c:when test="${actionRequest.delegationType == KEWConstants.DELEGATION_SECONDARY}">
                    <bean-el:message key="routeLog.ActionRequests.actionRequest.label.secondaryDelegate"/>
                  </c:when>
                  <c:when test="${actionRequest.delegationType == KEWConstants.DELEGATION_PRIMARY}">
                    <bean-el:message key="routeLog.ActionRequests.actionRequest.label.primaryDelegate"/>
                  </c:when>
                </c:choose>
            </c:when>
            <c:otherwise>
             <c:choose>
              <c:when test="${fn:length(actionRequest.childrenRequests) <= maxRoleChildrenToDisplay}">
               <c:forEach var="roleRequest" items="${actionRequest.childrenRequests}" varStatus="arStatus">
                   <c:choose>
                      <c:when test="${roleRequest.primaryDelegator}">
                          <c:forEach var="primDelegateRequest" items="${roleRequest.primaryDelegateRequests}" varStatus="pDelegateArStatus">
                          <c:set var="primDelegateDisplayName" value="${primDelegateRequest.displayName}"/>
                          <c:if test="${primDelegateRequest.userRequest}">
                              <kul:inquiry boClassName="org.kuali.rice.kim.bo.Person"
                                  keyValues="principalId=${primDelegateRequest.principalId}"
                                  render="true">
                                  <c:out value="${primDelegateDisplayName}" />
                              </kul:inquiry>
                           </c:if>
                          <c:if test="${primDelegateRequest.groupRequest}">
                              <kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.GroupImpl" keyValues="groupId=${primDelegateRequest.groupId}" render="true"><c:out value="${primDelegateRequest.groupName}" /></kul:inquiry>
                          </c:if>
                          <c:if test="${!empty primDelegateRequest.qualifiedRoleNameLabel}">
                            &nbsp;(<c:out value="${primDelegateRequest.qualifiedRoleNameLabel}" />)
                          </c:if>
                          <c:if test="${!pDelegateArStatus.last}"><br /></c:if>
                          </c:forEach>
                       </c:when>
                       <c:when test="${roleRequest.groupRequest}">
                          <kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.GroupImpl" keyValues="groupId=${roleRequest.groupId}" render="true"><c:out value="${roleRequest.groupName}" /></kul:inquiry>
                           <c:if test="${!empty actionRequest.qualifiedRoleNameLabel}">
                               &nbsp;(<c:out value="${actionRequest.qualifiedRoleNameLabel}" />)
                           </c:if>
                           <c:if test="${!arStatus.last}"><br /></c:if>
                       </c:when>
                       <c:otherwise>
                            <kul:inquiry boClassName="org.kuali.rice.kim.bo.Person" keyValues="principalId=${roleRequest.principalId}" render="true"><c:out value="${roleRequest.displayName}" /></kul:inquiry>
                            &nbsp;
                            <c:choose>
                              <c:when test="${roleRequest.delegationType == KEWConstants.DELEGATION_SECONDARY}">
                                <bean-el:message key="routeLog.ActionRequests.actionRequest.label.secondaryDelegate"/>
                              </c:when>
                              <c:when test="${roleRequest.delegationType == KEWConstants.DELEGATION_PRIMARY}">
                                <bean-el:message key="routeLog.ActionRequests.actionRequest.label.primaryDelegate"/>
                              </c:when>
                            </c:choose>
                            <c:if test="${!empty actionRequest.qualifiedRoleNameLabel}">
                            &nbsp;(<c:out value="${actionRequest.qualifiedRoleNameLabel}" />)
                            </c:if>
                            <c:if test="${!arStatus.last}"><br></c:if>
                     </c:otherwise>
                   </c:choose>
               </c:forEach>
              </c:when>
              <c:otherwise>
                (Multiple - expand to see details)
              </c:otherwise>
             </c:choose>
            </c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="<c:out value="${headerClass}"/>" nowrap="nowrap">
       <b>&nbsp;<fmt:formatDate value="${actionRequest.createDate}" pattern="${RiceConstants.DEFAULT_DATE_FORMAT_PATTERN}" /></b>
    </td>
    <td align="left" class="<c:out value="${headerClass}"/>">
        &nbsp;<c:out value="${actionRequest.annotation}" />
        <%-- If we are dealing with a primary delegation, display the annotations for the primary delegations at top level --%>
        <c:if test="${actionRequest.roleRequest}">
          <c:forEach var="roleRequest" items="${actionRequest.childrenRequests}" varStatus="arStatus">
              <c:if test="${roleRequest.primaryDelegator}">
                  <c:forEach var="primDelegateRoleRequest" items="${roleRequest.childrenRequests}">
                      <c:if test="${!empty primDelegateRoleRequest.annotation}">
                          <br /><c:out value="${primDelegateRoleRequest.annotation}"/>
                      </c:if>
                  </c:forEach>
              </c:if>
          </c:forEach>
        </c:if>
        <%-- ${actionRequest} --%>
    </td>   
</tr>
<c:if test="${level == 0 || !empty actionRequest.childrenRequests }">
<tr id="G<c:out value="${index}" />" style="display: none;">
    <td align="right" class="thnormal">
       <img src="images/pixel_clear.gif" width="60" height="20">
    </td>
    <td colspan="4" style="padding: 0; border: 0;">
       <jsp:include page="ActionRequests.jsp" flush="true" />
    </td>
</tr>
</c:if>
