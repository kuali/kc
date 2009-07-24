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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>


    <c:if test="${KualiForm.documentActions[Constants.KUALI_ACTION_CAN_AD_HOC_ROUTE] and not KualiForm.suppressAllButtons}">
        <kul:tab tabTitle="Ad Hoc Recipients" defaultOpen="false" tabErrorKey="${Constants.AD_HOC_ROUTE_ERRORS}">
        
        <div class="tab-container" align=center>     
		    <h3>Ad Hoc Recipients</h3>
            <table cellpadding="0" cellspacing="0" class="datatable" summary="view/edit ad hoc recipients">
			  <%-- first do the persons --%>
              <kul:displayIfErrors keyMatch="${Constants.AD_HOC_ROUTE_PERSON_ERRORS}">
				  <tr>
	        		<th colspan=3>
	            	<kul:errors keyMatch="${Constants.AD_HOC_ROUTE_PERSON_ERRORS}" />
	        		</th>
	    		  </tr>    
			  </kul:displayIfErrors>
              <tr>
                <td colspan=3 class="tab-subhead">Person Requests:</td>
              </tr>
	          <tr>
                  <kul:htmlAttributeHeaderCell
                      attributeEntry="${DataDictionary.AdHocRoutePerson.attributes.actionRequested}"
                      scope="col"
                      />
                  <kul:htmlAttributeHeaderCell
                      attributeEntry="${DataDictionary.AdHocRoutePerson.attributes.id}"
                      scope="col"
                      />
                <kul:htmlAttributeHeaderCell
                    literalLabel="Actions"
                    scope="col"
                    />
              </tr>
                
                <tr>
                    <td class="infoline" ><div align=center>
                        <html:hidden property="newAdHocRoutePerson.type"/>
                        <html:select property="newAdHocRoutePerson.actionRequested">
  		                    <c:set var="actionRequestCodes" value="${KualiForm.adHocActionRequestCodes}"/>
	    		            <html:options collection="actionRequestCodes" property="key" labelProperty="value"/>
	  			        </html:select></div>
                    </td>
                    <td class="infoline" ><div align=center>
	                    	<kra-pd:user userIdFieldName="newAdHocRoutePerson.id" 
	                    			  userId="${KualiForm.newAdHocRoutePerson.id}" 
	                    			  universalIdFieldName=""
	                    			  universalId=""
	                    			  userNameFieldName="newAdHocRoutePerson.name"
	                    			  userName="${KualiForm.newAdHocRoutePerson.name}"
	                    			  readOnly="${displayReadOnly}" 
	                    			  renderOtherFields="true"
	                    			  fieldConversions="userName:newAdHocRoutePerson.id,fullName:newAdHocRoutePerson.name" 
	                    			  lookupParameters="newAdHocRoutePerson.id:userName" />
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
	                        <html:select property="adHocRoutePerson[${ctr}].actionRequested">
	  		                    <c:set var="actionRequestCodes" value="${KualiForm.adHocActionRequestCodes}"/>
		    		            <html:options collection="actionRequestCodes" property="key" labelProperty="value"/>
		  			        </html:select></div>
	                    </td>
	                    <td class="datacell center" ><div align=center>
	                    	<kul:user userIdFieldName="adHocRoutePerson[${ctr}].id" 
	                    			  userId="${KualiForm.document.developmentProposalList[0].adHocRoutePersons[ctr].id}" 
	                    			  universalIdFieldName=""
	                    			  universalId=""
	                    			  userNameFieldName="adHocRoutePerson[${ctr}].name"
	                    			  userName="${KualiForm.document.developmentProposalList[0].adHocRoutePersons[ctr].name}"
	                    			  readOnly="${displayReadOnly}" 
	                    			  renderOtherFields="true"
	                    			  fieldConversions="personId:adHocRoutePerson[${ctr}].id,fullName:adHocRoutePerson[${ctr}].name" 
	                    			  lookupParameters="adHocRoutePerson[${ctr}].id:personId" />
	                    </td>
	                        <td class="datacell center" ><div align=center>
                            <html:image property="methodToCall.deleteAdHocRoutePerson.line${ctr}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" title="Delete Additional Ad Hoc Person" alt="Delete Additional Ad Hoc Person" styleClass="tinybutton"/></div>
	                    </td>
	                </tr>
	            </logic:iterate>
			  <%-- next do the workgroups --%>
			  <kul:displayIfErrors keyMatch="${Constants.AD_HOC_ROUTE_WORKGROUP_ERRORS}">
				  <tr>
	        		<th colspan=3>
	            	<kul:errors keyMatch="${Constants.AD_HOC_ROUTE_WORKGROUP_ERRORS}" />
	        		</th>
	    		  </tr>    
			  </kul:displayIfErrors>
		      <tr>
                <td colspan=3 class="tab-subhead">Ad Hoc Workgroup Requests:</td>
              </tr>
	          <tr>
                  <kul:htmlAttributeHeaderCell
                      attributeEntry="${DataDictionary.AdHocRouteWorkgroup.attributes.actionRequested}"
                      scope="col"
                      />
                  <kul:htmlAttributeHeaderCell
                      attributeEntry="${DataDictionary.AdHocRouteWorkgroup.attributes.id}"
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
                        <html:select property="newAdHocRouteWorkgroup.actionRequested">
  		                    <c:set var="actionRequestCodes" value="${KualiForm.adHocActionRequestCodes}"/>
    		                <html:options collection="actionRequestCodes" property="key" labelProperty="value"/>
  			            </html:select></div>
                    </td>
                    <td class="infoline" ><div align=center>
                        <kul:htmlControlAttribute property="newAdHocRouteWorkgroup.id" attributeEntry="${DataDictionary.AdHocRouteWorkgroup.attributes.id}" readOnly="${displayReadOnly}" />
                        <kul:workflowWorkgroupLookup fieldConversions="groupId:newAdHocRouteWorkgroup.id" /></div>
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
                            <html:select property="adHocRouteWorkgroup[${ctr}].actionRequested">
  		                        <c:set var="actionRequestCodes" value="${KualiForm.adHocActionRequestCodes}"/>
    		                    <html:options collection="actionRequestCodes" property="key" labelProperty="value"/>
  			                </html:select></div>
                        </td>
                        <td class="datacell center" ><div align=center>
                            <kul:htmlControlAttribute property="adHocRouteWorkgroup[${ctr}].id" attributeEntry="${DataDictionary.AdHocRouteWorkgroup.attributes.id}" readOnly="${displayReadOnly}" />
                            <kul:workflowWorkgroupLookup fieldConversions="groupId:adHocRouteWorkgroup[${ctr}].id" /></div>
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
