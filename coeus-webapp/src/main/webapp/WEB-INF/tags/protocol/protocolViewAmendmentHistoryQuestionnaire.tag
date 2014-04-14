<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="questionnaireTabTitle" required="true" %>
<%@ attribute name="amendRenewIndex" required="true" %>

<kul:innerTab tabTitle="${questionnaireTabTitle}" parentTab="" defaultOpen="false">
	<div class="innerTab-container">
	<c:forEach items="${KualiForm.actionHelper.amendmentSummaries[amendRenewIndex].answerHeaders}" var="answerHeader" varStatus="ansHeaderStatus">
		<c:set var="tabTitle" value="${KualiForm.questionnaireHelper.headerLabels[ansHeaderStatus.index]}" />
		<kul:innerTab tabTitle="${tabTitle}" parentTab="${questionnaireTabTitle}" defaultOpen="false">
  			<div class="questionnaireContent">
      			<c:set var="questionid" value="" />
      			<c:forEach items="${answerHeader.answers}" var="answer" varStatus="ansStatus">   
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
              				data-kc-questionindex="${ansStatus.index}"
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
                        	</c:choose>
                    	</c:when>
                    	<c:when test = "${answer.question.questionTypeId == 6 and answer.question.lookupClass == 'org.kuali.kra.bo.ArgValueLookup'}" >
                        	<jsp:useBean id="paramMap" class="java.util.HashMap"/>
                  			<c:set target="${paramMap}" property="argName" value="${answer.question.lookupReturn}" />
                  			<c:forEach items="${krafn:getOptionList('org.kuali.kra.lookup.keyvalue.ArgValueLookupValuesFinder', paramMap)}" var="option">
  	                			<c:if test="${answer.answer == option.key}">
  	                    			${option.value}
  	                			</c:if>    
                  			</c:forEach>
                    	</c:when>
                    	<c:otherwise>
                        	${answer.answer} </br>
                   		 </c:otherwise>
                	</c:choose>
      			</c:forEach>
      			<c:set var="questionid" value="${answer.questionNumber}" />
     				<%-- following 4 tags is to close the last question's display tag --%>
                          </div>
                      </td>
                  </tr>
              </table>
  		</div>
		</kul:innerTab>
		</c:forEach>						
	</div>
</kul:innerTab>
