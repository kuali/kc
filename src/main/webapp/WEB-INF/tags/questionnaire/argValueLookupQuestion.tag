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

<%@ attribute name="answerHeaderIndex" required="true" %>
<%@ attribute name="bean" required="true" type="org.kuali.kra.questionnaire.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="responseDivClass" required="true" %>
<%@ attribute name="answerValidationError" required = "true" %>

<%@ attribute name="questionIndex" required="true" %>
<div id="${responseDivClass}" class="Qresponsediv">
    <span class="Qresponse">
        
        <c:set var="prop" value="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer"/>
        ${kfunc:registerEditableProperty(KualiForm, prop)}
                        <jsp:useBean id="paramMap" class="java.util.HashMap"/>
		                <c:set target="${paramMap}" property="argName" value="${question.lookupReturn}" />

	                        <html:select property="${prop}" tabindex="0"  styleClass="Qanswer">
		                        <c:forEach items="${krafn:getOptionList('org.kuali.kra.lookup.keyvalue.ArgValueLookupValuesFinder', paramMap)}" var="option">
		                            <c:choose>                    	
		                    	        <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].answer == option.key}">
		                                    <option value="${option.key}" selected>${option.value}</option>
 		                                </c:when>
		                                <c:otherwise>
		                                    <option value="${option.key}">${option.value}</option>
		                                </c:otherwise>
		                            </c:choose>                    
		                        </c:forEach>
		                    </html:select>
    </span>
</div>
		                    