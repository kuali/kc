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

<c:set var="currentQuestionType" value="${KualiForm.document.newMaintainableObject.businessObject.questionType.name}" />

<c:choose>
    <c:when test="${currentQuestionType == 'Number'}">
        <c:set var="preMessage" value="The number of possible answers is " />
        <c:set var="postMessage" value=", not exceeding the number of text boxes presented." />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:when>
    <c:when test="${currentQuestionType == 'Date'}">
        <c:set var="preMessage" value="The number of possible answers is " />
        <c:set var="postMessage" value=", not exceeding the number of text boxes presented." />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:when>
    <c:when test="${currentQuestionType == 'Text'}">
        <c:set var="preMessage" value="The number of possible answers is " />
        <c:set var="postMessage" value=", not exceeding the number of text areas presented." />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:when>
    <c:when test="${currentQuestionType == 'Lookup'}">
        <c:set var="preMessage" value="The number of possible returns is " />
        <c:set var="postMessage" value="." />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:when>
    <c:otherwise>
        <c:set var="preMessage" value="" />
        <c:set var="postMessage" value="" />
        <c:set var="htmlControlDivStyle" value="display: none" />
    </c:otherwise>
</c:choose>

<div id="max_answers_pre_message" style="display: inline">
    ${preMessage}
</div>
<div id="max_answers_html_control" style="${htmlControlDivStyle}">
    <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.maxAnswers" 
                              attributeEntry="${DataDictionary.Question.attributes.maxAnswers}" />
</div>
<div id="max_answers_post_message" style="display: inline">
    ${postMessage}
</div>
<div id="max_answers_br" style="${htmlControlDivStyle}">
    <br />
</div>
