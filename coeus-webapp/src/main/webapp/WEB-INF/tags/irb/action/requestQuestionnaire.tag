<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
