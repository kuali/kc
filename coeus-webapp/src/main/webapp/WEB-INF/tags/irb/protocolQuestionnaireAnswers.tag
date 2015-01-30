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

<%@ attribute name="bean" required="true" type="org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>

<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />
<c:set var="readOnly" value="${!bean.answerQuestionnaire}" scope = "request"/>
<div id="workarea">
<c:forEach items="${bean.answerHeaders}" var="answerHeader" varStatus="status">
	
	<c:set var="prop" value="${property}.answerHeaders[${status.index}].showQuestions"/>
	${kfunc:registerEditableProperty(KualiForm, prop)}
	<input type="hidden" name="${prop}" id ="${prop}" 
           value = "${bean.answerHeaders[status.index].showQuestions}" />
     <kra-questionnaire:questionnaireAnswers bean = "${bean}" property = "${property}" answerHeaderIndex = "${status.index}"/>
				
				 
</c:forEach>

<c:if test="${fn:length(bean.answerHeaders) > 0}">

	${kfunc:registerEditableProperty(KualiForm, "numberOfQuestionaires")}
    <input type="hidden" name="numberOfQuestionaires" id ="numberOfQuestionaires" 
       value = "${fn:length(bean.answerHeaders)}" />
    <kul:panelFooter />
</c:if>

</div>
