<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
				<table class="content_table">
					<tbody>
						<tr>
							<td
								style="background: none repeat scroll 0% 0% rgb(228, 227, 228); border-width: 0px 1px 1px; border-style: solid; border-color: rgb(147, 147, 147); padding: 3px; font-weight: bold; width: 110px; text-align: center;">Type</td>
							<td
								style="background: none repeat scroll 0% 0% rgb(228, 227, 228); border-width: 0px 1px 1px; border-style: solid; border-color: rgb(147, 147, 147); padding: 3px; font-weight: bold; width: 110px; text-align: center;">Values</td>
						</tr>
						<tr>
							<td
								style="background: none repeat scroll 0% 0% rgb(255, 255, 255); border-width: 0px 1px 1px; border-style: solid; border-color: rgb(147, 147, 147); padding: 3px; vertical-align: top; text-align: center;">${question.questionType.questionTypeName}</td>
							<td
								style="background: none repeat scroll 0% 0% rgb(255, 255, 255); border-width: 0px 1px 1px; border-style: solid; border-color: rgb(147, 147, 147); padding: 3px; text-align: left;">
							<div class="responsetypediv1b" id="responsetypeText1b">
							<c:if test="${question.questionType.questionTypeId == '1'}" >
                                <p>The user will be presented with the following radio buttons: Yes, No.<br />Only one selection is possible.<br />A selection is required.</p>
                            </c:if>
							<c:if test="${question.questionType.questionTypeId == '2'}" >
                                <p>The user will be presented with the following pulldown: Yes, No, Not Applicable.<br />Only one selection is possible.<br />A selection is required.</p>							</c:if>
							<c:if test="${question.questionType.questionTypeId == '3'}" >
                                <p>The user will be presented with ${question.displayedAnswers} text box.<br />The entered value will be validated requiring a number only.<br />The maximum length of the number in characters is ${question.answerMaxLength}.<br />The number of possible answers is ${question.maxAnswers}. </p>							</c:if>
							<c:if test="${question.questionType.questionTypeId == '4'}" >
						        <p>The user will be presented with ${question.displayedAnswers} text boxes.<br />The entered value will be validated for a date in MM/DD/YYYY format.<br />A response is required for each text box.</p>
							</c:if>
							<c:if test="${question.questionType.questionTypeId == '5'}" >
							<p>The user will be presented with ${question.displayedAnswers} text areas.<br>
							The number of possible answers is ${question.maxAnswers}.<br>
							Maximum length of each response in characters: ${question.answerMaxLength}.</p>
							</c:if>
							<c:if test="${question.questionType.questionTypeId == '6'}" >
						        <p>The user will be presented with the ability to search for: ${question.lookupClass}.<br />The field to return is: ${question.lookupReturn}.<br />The number of possible returns is ${question.maxAnswers}.</p>
							</c:if>
							</div>
							</td>
						</tr>
					</tbody>
				</table>
