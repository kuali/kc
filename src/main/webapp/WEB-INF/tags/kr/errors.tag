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

<%@ attribute name="keyMatch" required="false" description="A concatenated String, splittable by a comma, of the properties which should have their errors displayed by this tag."%>
<%@ attribute name="displayRemaining" required="false"
              description="If the keyMatch attribute is not given,
              display any errors that have not been displayed already.
              This is a failsafe for incorrect error paths, and helps with maintenance.
              It can only be used with a KualiForm." %>
<%@ attribute name="errorTitle" required="false" description="The text to display above the rendered errors." %>
<%@ attribute name="warningTitle" required="false" description="The text to display above the rendered warnings." %>
<%@ attribute name="infoTitle" required="false" description="The text to display above the rendered information messages." %>

<%-- set generic error title if one was not given --%>
<c:if test="${empty errorTitle}">
  <c:set var="errorTitle" value="Errors found in this Section:"/>
</c:if>
<c:if test="${empty warningTitle}">
  <c:set var="warningTitle" value="Warnings for this Section:"/>
</c:if>
<c:if test="${empty infoTitle}">
  <c:set var="infoTitle" value="Informational messages in this Section:"/>
</c:if>

<c:if test="${!empty ErrorPropertyList}">
    <div>
      <c:set var="errorTitleRendered" value="false"/>  
      
        <c:choose>
          <%-- if match string given displayed only matched keys --%>
          <c:when test="${keyMatch!=null}">
            <%-- iterate through all keys in the error map --%>
            <c:forEach items="${ErrorPropertyList}" var="key">
              <c:set var="errorDisplayed" value="false"/>
              
              <%-- for each error, try to match with one of the match strings given, either by exact 
                   match or like match if wildcard is given --%>
              <c:forEach items="${fn:split(keyMatch,',')}" var="prefix">
                <c:if test="${(fn:endsWith(prefix,'*') && fn:startsWith(key,fn:replace(prefix,'*',''))) || (key == prefix)}">
                 
                  <%-- render title if this is the first error --%>
                  <c:if test="${!errorTitleRendered}">
                    <img src="${ConfigProperties.kr.externalizable.images.url}errormark.gif" alt="error" />
					<strong>${errorTitle}</strong>
                    <c:set var="errorTitleRendered" value="true"/>
                  </c:if>
                  
                  <%-- check so same message is not displayed again --%>
                  <c:if test="${errorDisplayed==false}">
                    <!-- error key = '${key}' -->
                    <html:errors property="${key}"/>
                    <%-- This is in case a single error matches more than one pattern in the given keyMatch. --%>
                    <c:set var="errorDisplayed" value="true"/>
                    <%-- If in a KualiForm, globally remember which errors have already been displayed. --%>
                    <c:if test="${KualiForm != null}">
                      <c:set target="${KualiForm.displayedErrors}" property="${key}" value="true"/>
                    </c:if>
                  </c:if>
                </c:if>
              </c:forEach>
            </c:forEach>  
          </c:when>

            <%-- else, if displayRemaining attribute is true, display any errors that have not already been displayed --%>
            <c:when test="${displayRemaining}">
                <c:forEach items="${ErrorPropertyList}" var="key">
                    <c:if test="${not KualiForm.displayedErrors[key]}">
                        <%-- render title if this is the first error --%>
                        <c:if test="${!errorTitleRendered}">
                            <strong>${errorTitle}</strong>
                            <c:set var="errorTitleRendered" value="true"/>
                        </c:if>
                        <%-- include error path in a comment so a developer can fix it --%>
                        <!-- remaining error path = "${key}" -->
                        <html:errors property="${key}"/>
                        <c:if test="${KualiForm != null}">
                      		<c:set target="${KualiForm.displayedErrors}" property="${key}" value="true"/>
                    	</c:if> 
                    </c:if>
                </c:forEach>
            </c:when>

          <%-- no key to match on given, display all errors --%>
          <c:otherwise>
            <logic:messagesPresent>
              <strong>${errorTitle}</strong>
            </logic:messagesPresent>
            
            <html:errors/>
          </c:otherwise>
      </c:choose>
    </div>
</c:if>

<c:if test="${!empty WarningPropertyList}">
    <div>
      <c:set var="warningTitleRendered" value="false"/>  
      
        <c:choose>
          <%-- if match string given displayed only matched keys --%>
          <c:when test="${keyMatch!=null}">
            <%-- iterate through all keys in the error map --%>
            <c:forEach items="${WarningPropertyList}" var="key">
              <c:set var="warningDisplayed" value="false"/>
              
              <%-- for each warning, try to match with one of the match strings given, either by exact 
                   match or like match if wildcard is given --%>
              <c:forEach items="${fn:split(keyMatch,',')}" var="prefix">
                <c:if test="${(fn:endsWith(prefix,'*') && fn:startsWith(key,fn:replace(prefix,'*',''))) || (key == prefix)}">
                 
                  <%-- render title if this is the first warning --%>
                  <c:if test="${!warningTitleRendered}">
					 <img src="${ConfigProperties.kr.externalizable.images.url}warning.png" alt="warning" />
                    <strong>${warningTitle}</strong>
                    <c:set var="warningTitleRendered" value="true"/>
                  </c:if>
                  
                  <%-- check so same message is not displayed again --%>
                  <c:if test="${warningDisplayed==false}">
                    <!-- warning key = '${key}' -->
                    <html:errors property="${key}" name="WarningActionMessages"/>
                    <%-- This is in case a single warning matches more than one pattern in the given keyMatch. --%>
                    <c:set var="warningDisplayed" value="true"/>
                    <%-- If in a KualiForm, globally remember which warnings have already been displayed. --%>
                    <c:if test="${KualiForm != null}">
                      <c:set target="${KualiForm.displayedWarnings}" property="${key}" value="true"/>
                    </c:if>
                  </c:if>
                </c:if>
              </c:forEach>
            </c:forEach>  
          </c:when>

            <%-- else, if displayRemaining attribute is true, display any warnings that have not already been displayed --%>
            <c:when test="${displayRemaining}">
                <c:forEach items="${WarningPropertyList}" var="key">
                    <c:if test="${not KualiForm.displayedWarnings[key]}">
                        <%-- render title if this is the first warning --%>
                        <c:if test="${!warningTitleRendered}">
                            <strong>${warningTitle}</strong>
                            <c:set var="warningTitleRendered" value="true"/>
                        </c:if>
                        <%-- include error path in a comment so a developer can fix it --%>
                        <!-- remaining error path = "${key}" -->
                        <html:errors property="${key}" name="WarningActionMessages"/>
                        <c:set target="${KualiForm.displayedWarnings}" property="${key}" value="true"/>
                    </c:if>
                </c:forEach>
            </c:when>

          <%-- no key to match on given, display all warnings --%>
          <c:otherwise>
            <logic:messagesPresent name="WarningActionMessages">
              <strong>${warningTitle}</strong>
            </logic:messagesPresent>
            
            <html:errors name="WarningActionMessages"/>
          </c:otherwise>
      </c:choose>
    </div>
</c:if>

<c:if test="${!empty InfoPropertyList}">
    <div>
      <c:set var="infoTitleRendered" value="false"/>  
      
        <c:choose>
          <%-- if match string given displayed only matched keys --%>
          <c:when test="${keyMatch!=null}">
            <%-- iterate through all keys in the error map --%>
            <c:forEach items="${InfoPropertyList}" var="key">
              <c:set var="infoDisplayed" value="false"/>
              
              <%-- for each info message, try to match with one of the match strings given, either by exact 
                   match or like match if wildcard is given --%>
              <c:forEach items="${fn:split(keyMatch,',')}" var="prefix">
                <c:if test="${(fn:endsWith(prefix,'*') && fn:startsWith(key,fn:replace(prefix,'*',''))) || (key == prefix)}">
                 
                  <%-- render title if this is the first info --%>
                  <c:if test="${!infoTitleRendered}">
                     <img src="${ConfigProperties.kr.externalizable.images.url}info.png" alt="info" /><strong>${infoTitle}</strong>
                    <c:set var="infoTitleRendered" value="true"/>
                  </c:if>
                  
                  <%-- check so same message is not displayed again --%>
                  <c:if test="${infoDisplayed==false}">
                    <!-- info key = '${key}' -->
                    <html:errors property="${key}" name="InfoActionMessages"/>
                    <%-- This is in case a single info matches more than one pattern in the given keyMatch. --%>
                    <c:set var="infoDisplayed" value="true"/>
                    <%-- If in a KualiForm, globally remember which infos have already been displayed. --%>
                    <c:if test="${KualiForm != null}">
                      <c:set target="${KualiForm.displayedInfo}" property="${key}" value="true"/>
                    </c:if>
                  </c:if>
                </c:if>
              </c:forEach>
            </c:forEach>  
          </c:when>

            <%-- else, if displayRemaining attribute is true, display any infos that have not already been displayed --%>
            <c:when test="${displayRemaining}">
                <c:forEach items="${InfoPropertyList}" var="key">
                    <c:if test="${not KualiForm.displayedInfo[key]}">
                        <%-- render title if this is the first info --%>
                        <c:if test="${!infoTitleRendered}">
                            <strong>${infoTitle}</strong>
                            <c:set var="infoTitleRendered" value="true"/>
                        </c:if>
                        <%-- include error path in a comment so a developer can fix it --%>
                        <!-- remaining error path = "${key}" -->
                        <html:errors property="${key}" name="InfoActionMessages"/>
                        <c:set target="${KualiForm.displayedInfo}" property="${key}" value="true"/>
                    </c:if>
                </c:forEach>
            </c:when>

          <%-- no key to match on given, display all info --%>
          <c:otherwise>
            <logic:messagesPresent name="InfoActionMessages">
              <strong>${infoTitle}</strong>
            </logic:messagesPresent>
            
            <html:errors name="InfoActionMessages"/>
          </c:otherwise>
      </c:choose>
    </div>
</c:if>
