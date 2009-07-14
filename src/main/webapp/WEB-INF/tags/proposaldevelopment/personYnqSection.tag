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
<%@ include file="/WEB-INF/jsp/proposaldevelopment/proposalPerson.jsp"%>

              <!-- <table cellpadding=0 cellspacing="0" summary=""> -->
                <tbody id="G4">
                  <tr>
                    <th width="10%">&nbsp;</th>
                    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.Ynq.attributes.description" />
                    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonYnq.attributes.answer" />
	<bean:define id="proposalPersonYnqs" name="KualiForm" property="${proposalPerson}.proposalPersonYnqs" />
    <c:set var="questionIdAttribute" value="${DataDictionary.ProposalPersonYnq.attributes.questionId}" />
    <c:set var="questionAttribute" value="${DataDictionary.Ynq.attributes.description}" />
    
    <c:set var="noOfAnswersAttibute" value="${DataDictionary.Ynq.attributes.noOfAnswers}" />
    <c:set var="answerAttribute" value="${DataDictionary.ProposalPersonYnq.attributes.answer}" />
    <c:set var="dummyAnswerAttribute" value="${DataDictionary.ProposalPersonYnq.attributes.dummyAnswer}" />
	<c:set var="answerYesNo" value="${KualiForm.answerYesNo}" /> 
	<c:set var="answerYesNoNa" value="${KualiForm.answerYesNoNA}" /> 
    <c:forEach items="${proposalPersonYnqs}"  var="ynqs" varStatus="status">
  		<!--  <c:set var="ynqs" value="${proposalPerson}.proposalPersonYnqs[${status.index}]" /> --> 
                  <tr>
                    <th scope="row" align="center">${status.index+1}</th>

                    <td width="70%" class="${tdClass}"><div align=left><span class="copy">
                    <bean:write name="KualiForm" property="${proposalPerson}.proposalPersonYnqs[${status.index}].ynq.description"/>
                    </span></div>
                    <span class="fineprint"></span> </td>
					<bean:define id="noOfAnswers" name="KualiForm" property="${proposalPerson}.proposalPersonYnqs[${status.index}].ynq.noOfAnswers" />
                    <td width="20%" class="${tdClass}"><div align=left><span class="copy">
					
					<c:choose>
					    <c:when test="${KualiForm.editingMode['certify']}">
							<c:choose>
								<c:when test="${noOfAnswers == answerYesNo}">
				                    <kul:htmlControlAttribute property="${proposalPerson}.proposalPersonYnqs[${status.index}].answer" attributeEntry="${answerAttribute}" /> 
								</c:when>
								<c:when test="${noOfAnswers == answerYesNoNa}">
				                    <kul:htmlControlAttribute property="${proposalPerson}.proposalPersonYnqs[${status.index}].answer" attributeEntry="${dummyAnswerAttribute}" /> 
								</c:when>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
	                            <c:when test="${noOfAnswers == answerYesNo}">
	                                <kul:htmlControlAttribute readOnly="true" property="${proposalPerson}.proposalPersonYnqs[${status.index}].answer" attributeEntry="${answerAttribute}" /> 
	                            </c:when>
	                            <c:when test="${noOfAnswers == answerYesNoNa}">
	                                <kul:htmlControlAttribute readOnly="true" property="${proposalPerson}.proposalPersonYnqs[${status.index}].answer" attributeEntry="${dummyAnswerAttribute}" /> 
	                            </c:when>
	                        </c:choose>
						</c:otherwise>
					</c:choose>
					
                    </span></div>
                    <span class="fineprint"></span> </td>
                  </tr>
    </c:forEach>

                </tbody>
