<%--
 Copyright 2005-2010 The Kuali Foundation

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

<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
<%@ attribute name="answer" required="true" type="org.kuali.kra.questionnaire.answer.Answer" %>
<%@ attribute name="questionIndex" required="true" %>

<%@ attribute name="answerHeaderIndex" required="true" %>
<%@ attribute name="bean" required="true" type="org.kuali.kra.questionnaire.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="responseDivClass" required="true" %>
<%@ attribute name="answerValidationError" required = "true" %>

<c:set var="questionFieldName" value="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" />
${kfunc:registerEditableProperty(KualiForm, questionFieldName)}
                      
<div id="${responseDivClass}" class="Qresponsediv">
    <span class="Qresponse">
        <table style="border:none; width:100%;" cellpadding="0" cellspacing="0">
            <tr>
                <td style="border:none;">
                <c:set var="answerLength" value="${question.answerMaxLength}" />
                    <c:choose>
                        <c:when test="${answerLength > 300}">
                            <html:textarea property="${questionFieldName}" style="" styleId="${questionFieldName}" title="Question Answer" tabindex="${tabindex}"
                                rows="3" cols="80"
                                styleClass="Qanswer"
                                onkeyup="textLimit(this, ${answerLength});" />
                    
                            <kul:expandedTextArea textAreaFieldName="${questionFieldName}" 
                                action="questionnaire" textAreaLabel="Question Answer" maxLength="${question.answerMaxLength}" />

                        </c:when>
                        <c:otherwise>
                            <c:set var="fieldSize" value="${answerLength}" />
                            <c:if test="${answerLength > 80}">
                                <c:set var="fieldSize" value="80" />
                            </c:if>
                            <input type="text" class="Qanswer" id="${questionFieldName}" name="${questionFieldName}" maxlength="${question.answerMaxLength}" size="${fieldSize}" 
                                value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}" />
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${answerValidationError}">
	 		            <kul:fieldShowErrorIcon />
                    </c:if>
                </td>
            </tr>
        </table>
    </span>
</div>
