<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="bean" required="true" type="org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="answerHeaderIndex" required="true" %>
<%@ attribute name="readOnly" required="true" %>
<%@ attribute name="printLineIndex" required="true" %>
<%@ attribute name="answerable" required="false" %>

	    <c:if test="${bean.answerHeaders[answerHeaderIndex].newerVersionPublished and not readOnly}">
            <kra-questionnaire:updateQuestionnaireAnswer  answerHeaderIndex="${answerHeaderIndex}" bean = "${bean}" property = "${property}"/>        
        </c:if>
	
		<c:if test="${!bean.answerHeaders[answerHeaderIndex].hasVisibleQuestion}">
			<h2>No conditions were met for questions in this questionnaire.</h2>
		</c:if>
        <h3>
            <span class="subhead-left">
                <a href="#" id ="questionpanelcontrol:${property}:${answerHeaderIndex}" class="questionpanel">
                    <img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' title="show/hide panel" width='45' height='15' border='0' align='absmiddle'>
                </a>
                Questions 
            </span>
 	        <span class="subhead-right">
 	        <c:choose>
				<c:when test="${personIndex != null}" >
					<html:image property="methodToCall.printQuestionnaireAnswer.${property}.line${personIndex}.anchor"
	                   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printdark.gif' styleClass="tinybutton"
                       alt="Print Questionnaire Answer" title="Print Questionnaire Answer" onclick="excludeSubmitRestriction = true;"/>
				</c:when>
				<c:otherwise>
					<html:image property="methodToCall.printQuestionnaireAnswer.${property}.line${printLineIndex}.anchor"
	                   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printdark.gif' styleClass="tinybutton"
                       alt="Print Questionnaire Answer" title="Print Questionnaire Answer" onclick="excludeSubmitRestriction = true;"/>
				</c:otherwise>
			</c:choose>
            </span>
        </h3>
        <div class="questionnaireContent">
        <%-- hidden rule results --%>
            <input type="hidden" name="ruleReferenced" id ="ruleReferenced" value = "${bean.ruleReferenced}" />
        
            <c:set var="questionid" value="" />
            <c:forEach items="${bean.answerHeaders[answerHeaderIndex].answers}" var="answer" varStatus="status">   

                <c:if test="${questionid ne answer.questionNumber}" >
                <%-- This 'if' block displays tab header for each question. if question has multiple answers
                     This is only displayed once when the 1st answer of this question is displayed --%>
                    <c:if test="${!empty questionid}" >
                    <%-- close tags for each question --%>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </c:if>
            
                    <c:set var="questionid" value="${answer.questionNumber}" />
                   	<c:set var="displayCondition" value="({ 'conditionFlag' : '${answer.questionnaireQuestion.conditionFlag}', 'condition': '${answer.questionnaireQuestion.condition}', 'conditionValue' : '${answer.questionnaireQuestion.conditionValue}'})"/>
                    <c:set var="ruleId" value="${answer.questionnaireQuestion.ruleId}"/>
                    <table class="content_table question" style="display: ${answer.matchedChild == 'Y' ? 'table' : 'none'}"
                    		data-kc-questionindex="${status.index}"
                    		data-kc-questionid="${questionid}" 
                    		data-kc-question-matched="${answer.matchedChild}"
                    		data-kc-question-parentid="${answer.questionnaireQuestion.parentQuestionNumber}"
                    		data-kc-question-condition="${displayCondition}"
                    		data-kc-question-ruleid="${ruleId}">  
                        <tr>
                            <td class="content_questionnaire">
                                <div class="Qdiv" >
                                    <div class="Qquestiondiv">
                                        <span class="Qmoreinfocontrol">More Information...</span>
                                        <span class="Qquestion">${answer.question.question}</span>
        
                                    </div>
                                    <kra-questionnaire:questionMoreInfo question="${answer.question}" />
                </c:if>
				
                <c:choose>
                    <%-- decide whether it is readonly mode --%>
                    <c:when test = "${readOnly and not answerable}" >
                       <c:choose>
                            <c:when test = "${answer.question.questionTypeId == 1 or answer.question.questionTypeId == 2}" >
                                <c:choose>
                                    <c:when test = "${answer.answer == 'Y'}" >
                                      Yes
                                    </c:when>
                                    <c:when test = "${answer.answer == 'N'}" >
                                      No
                                    </c:when>
                                    <c:when test = "${answer.answer == 'X'}" >
                                      N/A
                                    </c:when>
                                    <c:otherwise>
                                    
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:when test = "${answer.question.questionTypeId == 6 and answer.question.lookupClass == 'org.kuali.coeus.common.framework.custom.arg.ArgValueLookup'}" >
                                <jsp:useBean id="paramMap" class="java.util.HashMap"/>
		                        <c:set target="${paramMap}" property="argName" value="${answer.question.lookupReturn}" />
		                        <c:forEach items="${krafn:getOptionList('org.kuali.coeus.common.impl.custom.arg.ArgValueLookupValuesFinder', paramMap)}" var="option">
		        	                <c:if test="${answer.answer == option.key}">
		        	                    ${option.value}
		        	                </c:if>    
		                        </c:forEach>
                            </c:when>
                            <c:when test="${answer.question.questionTypeId == 5}">
                                <html:textarea name="KualiForm" property="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${status.index}].answer" disabled="true" />
                            </c:when>
                            <c:when test="${answer.question.questionTypeId == 100}" >
                               <c:choose>
                                   <c:when test="${answer.question.maxAnswers == 1 && answer.question.maxAnswers != answer.question.displayedAnswers}">
                                       <c:forEach items="${answer.question.questionMultiChoices}" var="option" varStatus="opStatus">
                                           <c:choose>
                                               <c:when test="${fn:startsWith(answer.answer, option.prompt)}">
                                                   &#10004;&nbsp;${answer.answer}<br>
                                               </c:when>
                                               <c:otherwise>
                                                   &#9744;&nbsp;${option.prompt}<br>
                                               </c:otherwise>
                                           </c:choose>
                                       </c:forEach>
                                   </c:when>
                                   <c:otherwise>
                                       <c:set var="prompt" value="${answer.question.questionMultiChoices[answer.answerNumber - 1].prompt}" />
                                       <c:choose>
                                           <c:when test="${not empty answer.answer}">
                                               <c:choose>
                                                   <c:when test="${fn:endsWith(prompt,':')}" >
                                                       &#10004;&nbsp;${answer.answer}<br>
                                                   </c:when>
                                                   <c:otherwise>
                                                       &#10004;&nbsp;${prompt}<br>
                                                   </c:otherwise>
                                               </c:choose>
                                           </c:when>
                                           <c:otherwise>
                                               &#9744;&nbsp;${prompt}<br>
                                           </c:otherwise>
                                       </c:choose>
                                   </c:otherwise>
                               </c:choose>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${answer.answer}" /> </br>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <c:set var="showError" value="${!answer.answered and KualiForm.auditActivated and answer.questionNumber != shownError && answer.answerHeader.questionnaireMandatory}" />
                        <c:set var="shownError" value="${answer.questionNumber}" />
                        <kra-questionnaire:questionnaireAnswer questionIndex="${status.index}" bean = "${bean}" property = "${property}" answerHeaderIndex = "${answerHeaderIndex}" showError="${showError}" />
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:set var="questionid" value="${answer.questionNumber}" />

           <%-- following 4 tags is to close the last question's display tag --%>
                                </div>
                            </td>
                        </tr>
                        <c:if test="${answerable}">
                        <tr>
                        	<td>
                        		<div float="left">
                        			<html:image property="methodToCall.completeQuestionnaireAfterRouting.${property}.line${printLineIndex}.anchor"
	                   				src='${ConfigProperties.kew.externalizable.images.url}tinybutton-save.gif' styleClass="tinybutton"
                       				alt="Complete Questionnaire" title="Complete Questionnaire" onclick="excludeSubmitRestriction = true;"/>
                       			</div>
                        	</td>
                        </tr>
                        </c:if>
                    </table>


        </div>
