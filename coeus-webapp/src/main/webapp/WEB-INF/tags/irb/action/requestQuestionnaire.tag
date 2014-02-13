<%--
 Copyright 2005-2013 The Kuali Foundation

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
<%@ attribute name="actionTypeCode" required="true" %>
<%@ attribute name="altLabel" required="true" %>

    <c:if test = "${KualiForm.actionHelper.toAnswerSubmissionQuestionnaire}" >
                <table cellpadding="0" cellspacing="0" summary="">
                    <tr>
                        <td class="subhead" >Questionnaire</td>
                    </tr>
                    <tr>
					        <td align="left" valign="middle">
						        <div align="left">
							        <html:image property="methodToCall.submissionQuestionnaire.actionType${actionTypeCode}.anchor${currentTabIndex}"
								        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-questionnaire.gif' styleClass="tinybutton"
									    onclick="questionnairePop('${KualiForm.document.protocolList[0].protocolNumber}T', '${actionTypeCode}','${KualiForm.formKey}',' ${KualiForm.document.sessionDocument}'); return false;"
								        alt="${altLabel}" />
						        </div>
					        </td>
	         	        </tr>
                    
                </table>
                                                
    </c:if>