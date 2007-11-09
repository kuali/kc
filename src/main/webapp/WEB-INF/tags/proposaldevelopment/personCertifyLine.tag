 <%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/proposalPerson.jsp"%>
<c:set var="personQuestionAttributes" value="${DataDictionary.ProposalPersonYesNoQuestion.attributes}" />
<c:set var="questionAttributes" value="${DataDictionary.Ynq.attributes}" />
<c:set var="question" value="${proposalPerson}.questions[${index}]" /> 

                  <tr>
                    <th scope="row" align="center">
                      <kul:htmlControlAttribute property="${question}.questionId" 
                                          attributeEntry="${questionAttributes.questionId}" readOnly="true" /> 
                    </th>

                    <td><div align=left><span class="copy">
                    <kul:htmlControlAttribute property="${question}.question.description" attributeEntry="${questionAttributes.description}" readOnly="true" /> 
                      </span></div>
                        <span class="fineprint"></span> </td>
                    <td><kul:htmlControlAttribute property="${question}.answer" attributeEntry="${personQuestionAttributes.answer}" /></td>

                    </td>
                  </tr>
