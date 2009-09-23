<%--
 Copyright 2005-2007 The Kuali Foundation

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


    <c:if test="${KualiForm.documentActions[Constants.KUALI_ACTION_CAN_ADD_ADHOC_REQUESTS] and not KualiForm.suppressAllButtons}">
        <kul:tab tabTitle="Ad Hoc Recipients" defaultOpen="false" tabErrorKey="${Constants.AD_HOC_ROUTE_ERRORS}">

        <div class="tab-container" align=center>
    <h3>Ad Hoc Recipients</h3>
            <table cellpadding="0" cellspacing="0" class="datatable" summary="view/edit ad hoc recipients">
        <%-- first do the persons --%>
              <kul:displayIfErrors keyMatch="${Constants.AD_HOC_ROUTE_PERSON_ERRORS}">
          <tr>
              <th colspan=4>
                <kul:errors keyMatch="${Constants.AD_HOC_ROUTE_PERSON_ERRORS}" />
              </th>
            </tr>
        </kul:displayIfErrors>
              <tr>
                <td colspan=4 class="tab-subhead">Person Requests:</td>
              </tr>
            <tr>
                  <kul:htmlAttributeHeaderCell
                      attributeEntry="${DataDictionary.AdHocRoutePerson.attributes.actionRequested}"
                      scope="col"
                      />
                  <kul:htmlAttributeHeaderCell
                      attributeEntry="${DataDictionary.AdHocRoutePerson.attributes.id}"
                      scope="col"
                      colspan="2"
                      />
                <kul:htmlAttributeHeaderCell
                    literalLabel="Actions"
                    scope="col"
                    />
              </tr>

                <tr>
                    <td class="infoline" ><div align=center>
                        <html:hidden property="newAdHocRoutePerson.type"/>
                        <%-- Define variable that will hold the Title of the html control --%>
                        <c:set var="accessibleTitle" value="${DataDictionary.AdHocRoutePerson.attributes.actionRequested.label}"/>
                                                <c:set var="accessibleTitle2" value="${DataDictionary.AdHocRouteWorkgroup.attributes.actionRequested.label}"/>
                        <c:if test="${(DataDictionary.AdHocRoutePerson.attributes.actionRequested.required == true) && readOnly != true}">
                          <c:set var="accessibleTitle" value="${Constants.REQUIRED_FIELD_SYMBOL} ${accessibleTitle}"/>
                        </c:if>
                        <c:if test="${(DataDictionary.AdHocRouteWorkgroup.attributes.actionRequested.required == true) && readOnly != true}">
                          <c:set var="accessibleTitle2" value="${Constants.REQUIRED_FIELD_SYMBOL} ${accessibleTitle2}"/>
                        </c:if>
                        <html:select title="${accessibleTitle}" property="newAdHocRoutePerson.actionRequested">
                          <c:set var="actionRequestCodes" value="${KualiForm.adHocActionRequestCodes}"/>
                          <html:options collection="actionRequestCodes" property="key" labelProperty="value"/>
                        </html:select></div>
                    </td>
					
					<kul:checkErrors keyMatch="newAdHocRoutePerson.id" />
				
                    <td class="infoline" colspan="2" ><div align=center>
                        <kul:user userIdFieldName="newAdHocRoutePerson.id"
                              userId="${KualiForm.newAdHocRoutePerson.id}"
                              universalIdFieldName=""
                              universalId=""
                              userNameFieldName="newAdHocRoutePerson.name"
                              userName="${KualiForm.newAdHocRoutePerson.name}"
                              readOnly="${displayReadOnly}"
                              renderOtherFields="true"
                              fieldConversions="principalName:newAdHocRoutePerson.id,name:newAdHocRoutePerson.name"
                              lookupParameters="newAdHocRoutePerson.id:principalName"
                              hasErrors="${hasErrors}" />
                    </td>
                    <td class="infoline" ><div align=center>
                        <html:image property="methodToCall.insertAdHocRoutePerson" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Insert Additional Ad Hoc Person" alt="Insert Additional Ad Hoc Person" styleClass="tinybutton"/></div>
                    </td>
                </tr>
              <logic:iterate name="KualiForm" id="person" property="adHocRoutePersons" indexId="ctr">
                  <tr>
                      <td class="datacell center" ><div align=center>
                          <html:hidden property="adHocRoutePerson[${ctr}].type"/>
                          <html:hidden property="adHocRoutePerson[${ctr}].versionNumber"/>
                          <html:select title="${accessibleTitle}" property="adHocRoutePerson[${ctr}].actionRequested">
                            <c:set var="actionRequestCodes" value="${KualiForm.adHocActionRequestCodes}"/>
                        <html:options collection="actionRequestCodes" property="key" labelProperty="value"/>
                    </html:select></div>
                      </td>
                      <td class="datacell center" colspan="2"><div align=center>
						<kul:checkErrors keyMatch="adHocRoutePerson[${ctr}].id" />
                        <kul:user userIdFieldName="adHocRoutePerson[${ctr}].id"
                              userId="${KualiForm.document.adHocRoutePersons[ctr].id}"
                              universalIdFieldName=""
                              universalId=""
                              userNameFieldName="adHocRoutePerson[${ctr}].name"
                              userName="${KualiForm.document.adHocRoutePersons[ctr].name}"
                              readOnly="${displayReadOnly}"
                              renderOtherFields="true"
                              fieldConversions="principalName:adHocRoutePerson[${ctr}].id,name:adHocRoutePerson[${ctr}].name"
                              lookupParameters="adHocRoutePerson[${ctr}].id:principalName"
							  hasErrors="${hasErrors}" />
                      </td>
                          <td class="datacell center" ><div align=center>
                            <html:image property="methodToCall.deleteAdHocRoutePerson.line${ctr}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" title="Delete Additional Ad Hoc Person" alt="Delete Additional Ad Hoc Person" styleClass="tinybutton"/></div>
                      </td>
                  </tr>
              </logic:iterate>
        <%-- next do the workgroups --%>
        <kul:displayIfErrors keyMatch="${Constants.AD_HOC_ROUTE_WORKGROUP_ERRORS}">
          <tr>
              <th colspan=4>
                <kul:errors keyMatch="${Constants.AD_HOC_ROUTE_WORKGROUP_ERRORS}" />
              </th>
            </tr>
        </kul:displayIfErrors>
          <tr>
                <td colspan=4 class="tab-subhead">Ad Hoc Group Requests:</td>
              </tr>
            <tr>
                  <kul:htmlAttributeHeaderCell
                      attributeEntry="${DataDictionary.AdHocRouteWorkgroup.attributes.actionRequested}"
                      scope="col"
                      />
                  <kul:htmlAttributeHeaderCell
                      attributeEntry="${DataDictionary.PersonDocumentGroup.attributes.namespaceCode}"
                      scope="col"
                      />
                  <kul:htmlAttributeHeaderCell
                      attributeEntry="${DataDictionary.PersonDocumentGroup.attributes.groupName}"
                      scope="col"
                      />
                  <kul:htmlAttributeHeaderCell
                      literalLabel="Actions"
                      scope="col"
                      />
              </tr>
                <tr>
                    <td class="infoline" ><div align=center>
                        <html:hidden property="newAdHocRouteWorkgroup.type"/>
                        <html:select title="${accessibleTitle2}" property="newAdHocRouteWorkgroup.actionRequested">
                          <c:set var="actionRequestCodes" value="${KualiForm.adHocActionRequestCodes}"/>
                        <html:options collection="actionRequestCodes" property="key" labelProperty="value"/>
                    </html:select></div>
                    </td>
                    <td class="infoline" ><div align=center>
                        <kul:htmlControlAttribute property="newAdHocRouteWorkgroup.recipientNamespaceCode" attributeEntry="${DataDictionary.PersonDocumentGroup.attributes.namespaceCode}" readOnly="${displayReadOnly}" />
                    </td>
                    <td class="infoline" ><div align=center>
                        <kul:htmlControlAttribute property="newAdHocRouteWorkgroup.recipientName" attributeEntry="${DataDictionary.PersonDocumentGroup.attributes.groupName}" readOnly="${displayReadOnly}" />
                        <kul:workflowWorkgroupLookup fieldConversions="namespaceCode:newAdHocRouteWorkgroup.recipientNamespaceCode,groupName:newAdHocRouteWorkgroup.recipientName"
                          lookupParameters="newAdHocRouteWorkgroup.recipientNamespaceCode:namespaceCode,newAdHocRouteWorkgroup.recipientName:groupName" /></div>
                    </td>
                    <td class="infoline" ><div align=center>
                        <html:image property="methodToCall.insertAdHocRouteWorkgroup" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Insert Additional Ad Hoc Workgroup" alt="Insert Additional Ad Hoc Workgroup" styleClass="tinybutton"/></div>
                    </td>
                </tr>
                <logic:iterate name="KualiForm" id="workgroup" property="adHocRouteWorkgroups" indexId="ctr">
                    <tr>
                        <td class="datacell center" ><div align=center>
                            <html:hidden property="adHocRouteWorkgroup[${ctr}].type"/>
                            <html:hidden property="adHocRouteWorkgroup[${ctr}].versionNumber"/>
                            <html:select title="${accessibleTitle2}" property="adHocRouteWorkgroup[${ctr}].actionRequested">
                              <c:set var="actionRequestCodes" value="${KualiForm.adHocActionRequestCodes}"/>
                            <html:options collection="actionRequestCodes" property="key" labelProperty="value"/>
                        </html:select></div>
                        </td>
                        <td class="datacell center"><div align=center>
                            <kul:htmlControlAttribute property="adHocRouteWorkgroup[${ctr}].recipientNamespaceCode" attributeEntry="${DataDictionary.PersonDocumentGroup.attributes.namespaceCode}" readOnly="displayReadOnly" />
                        </td>
                        <td class="datacell center"><div align=center>
                            <kul:htmlControlAttribute property="adHocRouteWorkgroup[${ctr}].recipientName" attributeEntry="${DataDictionary.PersonDocumentGroup.attributes.groupName}" readOnly="displayReadOnly" />
                            <kul:workflowWorkgroupLookup fieldConversions="namespaceCode:adHocRouteWorkgroup[${ctr}].recipientNamespaceCode,groupName:adHocRouteWorkgroup[${ctr}].recipientName"
                                 lookupParameters="adHocRouteWorkgroup[${ctr}].recipientNamespaceCode:namespaceCode,adHocRouteWorkgroup[${ctr}].recipientName:groupName" /></div>
                       </td>
                        <td class="datacell center" ><div align=center>
                            <html:image property="methodToCall.deleteAdHocRouteWorkgroup.line${ctr}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" title="Delete Additional Ad Hoc Workgroup" alt="Delete Additional Ad Hoc Workgroup" styleClass="tinybutton"/></div>
                        </td>
                    </tr>
                </logic:iterate>
          </table>
          </div>
      </kul:tab>
    </c:if>
