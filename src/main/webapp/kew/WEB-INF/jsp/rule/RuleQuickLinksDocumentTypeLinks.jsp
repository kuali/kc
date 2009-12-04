<%--
 Copyright 2007-2009 The Kuali Foundation
 
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

<c:if test="${ documentTypeStruct.shouldDisplay }" >
    <c:set var="documentType" value="${documentTypeStruct.documentType}" />
    <c:if test="${documentType.currentInd == true && documentType.active == true}">
      <c:choose>
        <c:when test="${excludeDocId != documentType.documentTypeId}">
          <table class="datatable" cellspacing="0" cellpadding="0" align="center" style="text-align: left; margin-left: auto; margin-right: auto;">
            <tbody>
              <tr>
                <td class="tab-subhead" width="50%">
                  <h2>
                    <kul:inquiry keyValues="documentTypeId=${documentType.documentTypeId}" boClassName="org.kuali.rice.kew.doctype.bo.DocumentType" render="true">
                      <c:choose>
	                      <c:when test="${documentType.label != documentType.name}">
		                      <c:out value="${documentType.label} (${documentType.name})" />
		                  </c:when>
		                  <c:otherwise>
		                      <c:out value="${documentType.label}" />
		                  </c:otherwise>
	                  </c:choose>
                    </kul:inquiry>
                  </h2>
                </td>
                <td class="tab-subhead" width="50%">
                    <c:if test="${KualiForm.canInitiateDocumentTypeDocument}">
                    <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.MAINTENANCE_ACTION}">
                        <c:param name="methodToCall" value="edit" />
                        <c:param name="businessObjectClassName" value="org.kuali.rice.kew.doctype.bo.DocumentType"/>
                        <c:param name="documentTypeId" value="${documentType.documentTypeId}"/>
                        <c:param name="name" value="${documentType.name}"/>
                      </c:url>" target="_blank">Edit Document Type</a>
                    &nbsp;&nbsp;&nbsp;
                    </c:if>
                    <a href="<c:url value="DocumentConfigurationView.do">
                        <c:param name="methodToCall" value="start" />
                        <c:param name="documentTypeName" value="${documentType.name}"/>
                      </c:url>" target="_blank">View Document Configuration</a>
                    <br />
                </td>
              </tr>
            </tbody>
          </table>
          <table cellspacing="0" cellpadding="0" width="100%">
            <c:forEach items="${documentTypeStruct.flattenedNodes}" var="routeLevel">

              <c:if test="${routeLevel.routeMethodName != Constants.EXCEPTION_ROUTE_MODULE_NAME &&
                  routeLevel.routeMethodName != Constants.ADHOC_ROUTE_MODULE_NAME}">
                <c:if test="${routeLevel.flexRM}">
	                <tr>
	                  <kul:htmlAttributeHeaderCell width="50%"  scope="col" align="left" nowrap="nowrap">
	                    <c:out value="${routeLevel.routeNodeName}" />&nbsp;
	                  </kul:htmlAttributeHeaderCell>
	                  <td class="datacell" width="50%" nowrap="nowrap">
	                    <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.MAINTENANCE_ACTION}">
	                        <c:param name="methodToCall" value="start" />
	                        <c:param name="businessObjectClassName" value="org.kuali.rice.kew.rule.RuleBaseValues"/>
	                        <c:param name="ruleCreationValues.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
	                        <c:param name="ruleCreationValues.ruleTemplateName" value="${routeLevel.ruleTemplate.name}"/>
	                        <c:param name="ruleCreationValues.docTypeName" value="${documentType.name}"/>
	                      </c:url>" target="_blank">Add Rule</a>&nbsp;
	                    <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.LOOKUP_ACTION}">
	                        <c:param name="businessObjectClassName" value="org.kuali.rice.kew.rule.RuleBaseValues"/>
	                        <c:param name="returnLocation" value="${ConfigProperties.application.url}/portal.do"/>
	                        <c:param name="hideReturnLink" value="true"/>
	                        <c:param name="methodToCall" value="search"/>
	                        <c:param name="docFormKey" value="88888888"/>
	                        <c:param name="documentType.name" value="${documentType.name}"/>
	                        <c:param name="ruleTemplate.name" value="${routeLevel.ruleTemplate.name}"/>
	                      </c:url>" target="_blank">Search</a>
	                    <c:if test="${routeLevel.ruleTemplate.delegationTemplate != null}">
	                      &nbsp;
	                      <a href="<c:url value="RuleQuickLinks.do">
	                          <c:param name="businessObjectClassName" value="org.kuali.rice.kew.rule.RuleDelegation"/>
	                          <c:param name="returnLocation" value="DelegateRule.do"/>
	                          <c:param name="hideReturnLink" value="false"/>
	                          <c:param name="methodToCall" value="addDelegationRule"/>
	                          <c:param name="docFormKey" value="88888888"/>
	                          <c:param name="delegationRuleBaseValues.documentType.name" value="${documentType.name}"/>
	                          <c:param name="delegationRuleBaseValues.ruleTemplate.name" value="${routeLevel.ruleTemplate.name}"/>
	                          <c:param name="delegationRuleBaseValues.ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
	                        </c:url>" target="_blank">Add Delegation</a>&nbsp;
	                      <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.LOOKUP_ACTION}">
	                          <c:param name="businessObjectClassName" value="org.kuali.rice.kew.rule.RuleDelegation"/>
	                          <c:param name="returnLocation" value="${ConfigProperties.application.url}/portal.do"/>
	                          <c:param name="hideReturnLink" value="true"/>
	                          <c:param name="methodToCall" value="search"/>
	                          <c:param name="docFormKey" value="88888888"/>
	                          <c:param name="delegationRuleBaseValues.documentType.name" value="${documentType.name}"/>
	                          <c:param name="delegationRuleBaseValues.ruleTemplate.name" value="${routeLevel.ruleTemplate.name}"/>
	                        </c:url>" target="_blank">Search Delegations</a>
	                    </c:if>
	                  </td>
	                </tr>
	              </c:if>
	              <c:if test="${!routeLevel.flexRM && routeLevel.roleNode}">
	              	<tr> 
	                  <kul:htmlAttributeHeaderCell nowrap="nowrap" width="50%"  scope="col" align="left" colspan="2">
	                    <c:out value="${routeLevel.routeNodeName}" />&nbsp;
	                  </kul:htmlAttributeHeaderCell>
					</tr>  
	              </c:if>
              </c:if>
            </c:forEach>
          </table>
          <c:if test="${! empty documentTypeStruct.childrenDocumentTypes}">
            <div class="tab-container" style="border-left:1px solid #999999;border-bottom:1px solid #999999; padding-left: 3em;">
              <c:forEach items="${documentTypeStruct.childrenDocumentTypes}" var="childDocumentTypeStruct">
                <c:set var="documentTypeStruct" value="${childDocumentTypeStruct}" scope="request"/>
                <c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
              </c:forEach>
            </div>
          </c:if>
        </c:when>
        <%-- have for the "excluded" document type - really the header one --%>
        <c:otherwise>
          <table cellspacing="0" cellpadding="0" width="100%">
          <c:forEach items="${documentTypeStruct.flattenedNodes}" var="routeLevel">
            <c:if test="${routeLevel.routeMethodName != Constants.EXCEPTION_ROUTE_MODULE_NAME &&
                routeLevel.routeMethodName != Constants.ADHOC_ROUTE_MODULE_NAME}">
              <c:if test="${routeLevel.flexRM}">
                <tr>
                  <kul:htmlAttributeHeaderCell width="50%"  scope="col" align="left">
                    <c:out value="${routeLevel.routeNodeName}" />&nbsp;
                  </kul:htmlAttributeHeaderCell>
                  <td class="datacell" width="50%" nowrap>
                    <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.MAINTENANCE_ACTION}">
                        <c:param name="methodToCall" value="start" />
                        <c:param name="businessObjectClassName" value="org.kuali.rice.kew.rule.RuleBaseValues"/>
                        <c:param name="ruleCreationValues.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
                        <c:param name="ruleCreationValues.ruleTemplateName" value="${routeLevel.ruleTemplate.name}"/>
                        <c:param name="ruleCreationValues.docTypeName" value="${documentType.name}"/>
                      </c:url>" target="_blank">Add Rule</a>&nbsp;
                    <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.LOOKUP_ACTION}">
                        <c:param name="businessObjectClassName" value="org.kuali.rice.kew.rule.RuleBaseValues"/>
                        <c:param name="returnLocation" value="${ConfigProperties.application.url}/portal.do"/>
                        <c:param name="hideReturnLink" value="true"/>
                        <c:param name="methodToCall" value="search"/>
                        <c:param name="docFormKey" value="88888888"/>
                        <c:param name="documentType.name" value="${documentType.name}"/>
                        <c:param name="ruleTemplate.name" value="${routeLevel.ruleTemplate.name}"/>
                      </c:url>" target="_blank">Search</a>
                    <c:if test="${routeLevel.ruleTemplate.delegationTemplate != null}">
                      &nbsp;
                      <a href="<c:url value="RuleQuickLinks.do">
                          <c:param name="businessObjectClassName" value="org.kuali.rice.kew.rule.RuleDelegation"/>
                          <c:param name="returnLocation" value="DelegateRule.do"/>
                          <c:param name="hideReturnLink" value="false"/>
                          <c:param name="methodToCall" value="addDelegationRule"/>
                          <c:param name="docFormKey" value="88888888"/>
                          <c:param name="delegationRuleBaseValues.documentType.name" value="${documentType.name}"/>
                          <c:param name="delegationRuleBaseValues.ruleTemplate.name" value="${routeLevel.ruleTemplate.name}"/>
                          <c:param name="delegationRuleBaseValues.ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
                        </c:url>" target="_blank">Add Delegation</a>&nbsp;
                      <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.LOOKUP_ACTION}">
                          <c:param name="businessObjectClassName" value="org.kuali.rice.kew.rule.RuleDelegation"/>
                          <c:param name="returnLocation" value="${ConfigProperties.application.url}/portal.do"/>
                          <c:param name="hideReturnLink" value="true"/>
                          <c:param name="methodToCall" value="search"/>
                          <c:param name="docFormKey" value="88888888"/>
                          <c:param name="delegationRuleBaseValues.documentType.name" value="${documentType.name}"/>
                          <c:param name="delegationRuleBaseValues.ruleTemplate.name" value="${routeLevel.ruleTemplate.name}"/>
                        </c:url>" target="_blank">Search Delegations</a>
                    </c:if>
                  </td>
                </tr>
              </c:if>
              <c:if test="${!routeLevel.flexRM && routeLevel.roleNode}">
              	<tr> 
                  <kul:htmlAttributeHeaderCell width="50%"  scope="col" align="left" colspan="2" nowrap="nowrap">	              	>
                    <c:out value="${routeLevel.routeNodeName}" />&nbsp;
                  </kul:htmlAttributeHeaderCell>
				</tr>  
              </c:if>
              <c:if test="${! empty documentTypeStruct.childrenDocumentTypes}">
                <c:forEach items="${documentTypeStruct.childrenDocumentTypes}" var="childDocumentTypeStruct">
                  <c:set var="documentTypeStruct" value="${childDocumentTypeStruct}" scope="request"/>
                  <c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
                </c:forEach>
              </c:if>
            </c:if>
          </c:forEach>
          </table>
        </c:otherwise>
      </c:choose>
      <c:if test="${! empty documentTypeStruct.childrenDocumentTypes}">
        <c:forEach items="${documentTypeStruct.childrenDocumentTypes}" var="childDocumentTypeStruct">
          <c:set var="documentTypeStruct" value="${childDocumentTypeStruct}" scope="request"/>
          <c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
        </c:forEach>
      </c:if>
    </c:if>
  </c:if>
