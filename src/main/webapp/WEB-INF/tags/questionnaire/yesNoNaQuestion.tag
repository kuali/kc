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
<%@ attribute name="answerValidationError" %>
                        
<div class="Qresponsediv">
    <span class="Qresponse">
    	
    	<c:set var="prop" value="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer"/>
        ${kfunc:registerEditableProperty(KualiForm, prop)} 
        <input type="hidden" name="checkboxToReset" value="${prop}"/>
    
        <c:choose>
            <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].answer eq 'Y'}" >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this,'${property}')" style="border:none;" id="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" checked="checked" value="Y" />Yes
            </c:when>
            <c:otherwise>
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this,'${property}')" style="border:none;" id="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" value="Y" />Yes
            </c:otherwise>
        </c:choose>  
        <c:choose>
            <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].answer eq 'N'}" >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this,'${property}')" style="border:none;" id="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" checked="checked" value="N" />No
            </c:when>
            <c:otherwise >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this,'${property}')" style="border:none;" id="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer"  value="N" />No
            </c:otherwise>
        </c:choose>  
        <c:choose>
            <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].answer eq 'X'}" >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this,'${property}')" style="border:none;" id="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" checked="checked" value="X" />NA
            </c:when>
            <c:otherwise >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this,'${property}')" style="border:none;" id="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" value="X" />NA
            </c:otherwise>
        </c:choose>  
    </span>
</div>
