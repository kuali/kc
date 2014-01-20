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
<tr>
<td colspan="2">
<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
                            <tr>
                                <th class="tab-subhead" align="left" >
                                <div align="right">
                                               Questionnaire:
                                               </div>
                                </th>
                                <td align="left" valign="middle" width="85%">
                                    <div align="left">
							        <html:image property="methodToCall.questionnaire.actionType116.anchor${currentTabIndex}"
								        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-questionnaire.gif' styleClass="tinybutton"
									onclick="questionnairePop('${KualiForm.document.protocolList[0].protocolNumber}','${KualiForm.actionHelper.selectedSubmission.submissionNumber}','${KualiForm.formKey}',' ${KualiForm.document.sessionDocument}', false); return false;"
								        alt="View Questionnaire" />
                                     </div>
                                  </td>
                            </tr>
           	</tbody>
           	</table>	
        </td>
</tr>
           	