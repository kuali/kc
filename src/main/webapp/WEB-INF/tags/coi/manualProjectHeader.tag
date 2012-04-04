<%--
 Copyright 2005-2010 The Kuali Foundation

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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="idx" required="true" description="Coi disl project list index" %>
<%@ attribute name="style" required="false" description="style for current project" %>
<%@ attribute name="disclProject" required="true" type="org.kuali.kra.coi.CoiDisclProject" %>

<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
                <div>
                <h3>
                    <span class="subhead-left" style="${style}">generic - ${disclProject.coiDisclosureEventType.projectIdLabel}: ${disclProject.coiProjectId}</span>
                    <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
                </h3>
              
                    <table class=tab cellpadding="0" cellspacing="0" summary="">
                        <tbody>
                        <%-- Header --%>
                    <c:forEach var="labelValue" items="${disclProject.headerItems}" varStatus="status">
                        <c:if test="${(status.index mod 2) == 0}">
                        <tr>
                        </c:if>
                           <th><div align="right">${labelValue.label}:</div></th> 
                           <td align="left" valign="middle">
                               <div align="left">
                                  ${labelValue.value}
                               </div>
                         <c:if test="${(status.index mod 2) != 0}">
                        </tr>
                        </c:if>
                    </c:forEach>
                        <c:if test="${fn:length(disclProject.headerItems) mod 2 != 0}">
                        <td/>
                        <td/>
                        </tr>
                        </c:if>


               </table>
               </div>