<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="questionIndex" required="true" %>
<%--<div id="panelcontent03"> --%>
<%--    <table class="content_table">  
        <tr>
            <td class="content_questionnaire"> --%>
                <c:set var="fieldName" value="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" />
			    <c:set var="answerValidationError" value="false" scope="request"/>
            	<c:forEach items="${ErrorPropertyList}" var="key">
				    <c:if test="${key eq fieldName}">
					  <c:set var="answerValidationError" value="true" scope="request"/>
				    </c:if>
			     </c:forEach>
            
                <c:choose>
                    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 1}" >
                        <kra-questionnaire:yesNoQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
                            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
                    </c:when>
                    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 2}" >
                        <kra-questionnaire:yesNoNaQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
                            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
                    </c:when>
                    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 3}" >
                        <kra-questionnaire:numberQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
                            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
                    </c:when>
                    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 4}" >
                        <kra-questionnaire:dateQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
                            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
                    </c:when>
                    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 5}" >
                        <kra-questionnaire:textQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
                            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
                    </c:when>
                    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 6}" >
                        <kra-questionnaire:lookupQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
                            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
<%--            </td>
        </tr>
    </table>   --%> 
<%-- </div>	 --%>
