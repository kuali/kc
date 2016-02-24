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
<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
                            <tr>
                                <td style="background-color: rgb(195, 195, 195); font-weight: bold;">
                                               Questionnaire:
                                               
                                </td>
                                <td align="left" valign="middle" width="85%">
                                    <div align="left">
							        <html:image property="methodToCall.questionnaire.actionType116.anchor${currentTabIndex}"
								        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
									onclick="questionnairePop('${KualiForm.document.protocolList[0].protocolNumber}','${KualiForm.actionHelper.currentSequenceNumber}','${KualiForm.formKey}',' ${KualiForm.document.sessionDocument}', true); return false;"
								        alt="View Questionnaire" />
                                     </div>
                                  </td>
                            </tr>
           	</tbody>
           	</table>	

