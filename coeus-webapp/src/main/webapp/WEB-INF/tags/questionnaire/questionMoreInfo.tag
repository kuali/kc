<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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

<%@ attribute name="question" required="true" type="org.kuali.coeus.common.questionnaire.framework.question.Question" %>
<div class="Qmoreinfodiv">
    <span class="Qmoreinfo">
        ${question.questionSeqId} :  ${question.question} </br>
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
