<%--
 Copyright 2005-2014 The Kuali Foundation

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
<c:set var="disclosureAttributes" value="${DataDictionary.CoiDisclosure.attributes}" />
<c:set var="readOnly" value="${KualiForm.document.viewOnly}" />

	<div class="tab-container" align="center">
		<h3> 
			<span class="subhead-left">Review Status</span>
            <span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiAdministratorActionHelp" altText="help"/></span>
 		</h3>
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                <tr>               
					<th style="width: 150px">
                		<div align="right">
                			<kul:htmlAttributeLabel attributeEntry="${disclosureAttributes.reviewStatusCode}" /></div>       
                		</div>
                	</th>
                	<td style="width: 150px">
                        <kul:htmlControlAttribute property="disclosureActionHelper.coiDisclosure.reviewStatusCode" 
                                                  attributeEntry="${disclosureAttributes.reviewStatusCode}" readOnly="${readOnly}"/>
                	</td>
	            </tr>               
                <tr>
					<td align="center" colspan="4">
						<div align="center">
							<c:if test="${!readOnly}">
							<html:image property="methodToCall.updateDisclosureReviewStatus.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
							</c:if>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
   </div>
