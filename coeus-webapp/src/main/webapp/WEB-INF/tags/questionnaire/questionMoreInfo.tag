<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
<div class="Qmoreinfodiv">
    <span class="Qmoreinfo">
        ${question.questionId} :  ${question.question} </br>
        <c:if test="${fn:length(question.questionExplanations) > 0}" >
            <c:forEach items="${question.questionExplanations}" var="questionExplanation" varStatus="status">
                <c:choose>
                    <c:when test="${questionExplanation.explanationType eq 'E'}">
                    Explanation : ${questionExplanation.explanation} </br>
                    </c:when>
                    <c:when test="${questionExplanation.explanationType eq 'P'}">
                    Policy : ${questionExplanation.explanation} </br>
                    </c:when>
                    <c:when test="${questionExplanation.explanationType eq 'R'}">
                    Regulation : ${questionExplanation.explanation} </br>
                    </c:when>
                </c:choose>
            </c:forEach>
        </c:if>
    </span>
</div>
