<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

    <c:set var="transparent" value="false" />

    <c:if test="${answerHeaderIndex == 0}">
      <c:set var="transparent" value="true" />
    </c:if> 
    <c:choose>
    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].completed}">
     	<c:set var="tabTitle" value="${KualiForm.questionnaireHelper.headerLabels[answerHeaderIndex]} (Complete)" />
    </c:when>
    <c:otherwise>
     	<c:set var="tabTitle" value="${KualiForm.questionnaireHelper.headerLabels[answerHeaderIndex]} (Incomplete)" />
    </c:otherwise> 
    </c:choose>
    <c:set var="showQuestions" value="false" />
    <c:if test="${!empty KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].showQuestions and KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].showQuestions == 'Y'}">
      <c:set var="showQuestions" value="true" />
    </c:if> 

<kul:tab tabTitle="${tabTitle}"
					 tabErrorKey="questionnaireHelper.answerHeaders[${answerHeaderIndex}]*"
					 auditCluster="requiredFieldsAuditErrors" 
					 tabAuditKey="" 
					 useRiceAuditMode="true"
			         tabDescription=""
			         defaultOpen="${showQuestions}" 
					 useCurrentTabIndexAsKey="true"
			         transparentBackground="${transparent}">
			         
	<div class="tab-container" align="center">
	    <c:if test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].newerVersionPublished}">
                <kra-questionnaire:updateQuestionnaireAnswer  answerHeaderIndex="${answerHeaderIndex}"/>        
        </c:if>
	
        <h3>
            <span class="subhead-left"><a href="#" id ="questionpanelcontrol${answerHeaderIndex}" class="questionpanel"><img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a>
                Questions </span>
 	        <span class="subhead-right">
 	        <html:image property="methodToCall.printQuestionnaireAnswer.line${answerHeaderIndex}.anchor"
	src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printdark.gif' styleClass="tinybutton"
   alt="Print Questionnaire Answer" onclick="excludeSubmitRestriction = true;"/> 
   <a title="[Help]help" target="helpWindow" href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getBusinessObjectHelpText&amp;businessObjectClassName=org.kuali.kra.questionnaire.answer.AnswerHeader">
   <img styleClass="tinybutton"
  alt="[Help]help" src="${ConfigProperties.kr.externalizable.images.url}my_cp_inf.gif"></a>
   <%--  when using this tag, the 'print' and '?' is not aligning well.
   <kul:help businessObjectClassName="org.kuali.kra.questionnaire.answer.AnswerHeader" altText="help"/>
   --%> 
   </span>
        </h3>
        <div id="questionpanelcontent${answerHeaderIndex}">
            <c:set var="questionid" value="" />
            <c:forEach items="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers}" var="answer" varStatus="status">   

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

                    <c:set var="tableId" value="table-${answerHeaderIndex}-${status.index}" />   
                    <c:if test="${answer.questionnaireQuestion.parentQuestionNumber != 0}" >
                        <c:set var="tableId" value="table-parent-${answerHeaderIndex}-${answer.questionnaireQuestion.parentQuestionNumber}-${status.index}"/>   
                    </c:if>                 

                    <c:set var="qname" value="HD${answerHeaderIndex}-QN${status.index}"/>            
                    <c:set var="questionid" value="${answer.questionNumber}" />
                    <table class="content_table" id = "${tableId}">  
                        <tr>
                            <td class="content_questionnaire">
                                <div class="Qdiv" id="${qname}div">
                                    <div class="Qquestiondiv">
                                        <span class="Qmoreinfocontrol">More Information...</span>
                                        <!--<span class="Qnumber">1.0.0</span>-->
                                        <span class="Qquestion">${answer.question.question}</span>
        
                                    </div>
                                    <kra-questionnaire:questionMoreInfo question="${answer.question}" />
                </c:if>

                <c:choose>
                    <%-- decide whether it is readonly mode --%>
                    <c:when test = "${readOnly}" >
                        
                        <c:set var="prop" value="childDisplay-${answerHeaderIndex}-${answer.questionNumber}"/>
                        ${kfunc:registerEditableProperty(KualiForm, prop)}
                        <input type="hidden" id="childDisplay-${answerHeaderIndex}-${answer.questionNumber}" name="childDisplay-${answerHeaderIndex}-${answer.questionNumber}" value="${answer.matchedChild}" />                
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
                            <c:otherwise>
                                  ${answer.answer} </br>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <kra-questionnaire:questionnaireAnswer questionIndex="${status.index}" />        
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
    </div>
</kul:tab>
