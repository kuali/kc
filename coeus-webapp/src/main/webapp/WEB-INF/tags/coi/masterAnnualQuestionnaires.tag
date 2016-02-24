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

<kul:tab defaultOpen="true" tabTitle="Questionnaire" transparentBackground="false"
    tabErrorKey="" >
    <c:set var="answerHeaderIndex" value="0" />
    <c:set var="property" value="disclosureQuestionnaireHelper" />
    <c:set var="bean" value="${KualiForm.disclosureQuestionnaireHelper}" />
            
            
    <c:forEach items="${bean.answerHeaders}" var="answerHeader" varStatus="ahstatus">
        <div class="tab-container" align="center">
            <c:set var="prop" value="${property}.answerHeaders[${ahstatus.index}].showQuestions"/>
            ${kfunc:registerEditableProperty(KualiForm, prop)}
            <input type="hidden" name="${prop}" id ="${prop}" 
                   value = "${bean.answerHeaders[ahstatus.index].showQuestions}" />
           <kra-questionnaire:questionnaireAnswersInnerTab bean = "${bean}" property = "${property}" answerHeaderIndex = "${ahstatus.index}" parentTab="Master Disclosure"/>
       </div>
    </c:forEach>
</kul:tab>
