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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="answerHeaderIndex" value="0" /> 
<c:set var="property" value="proposalPersonQuestionnaireHelpers[${personIndex}]" />
<c:set var="bean" value="${KualiForm.proposalPersonQuestionnaireHelpers[personIndex]}" />

<kra-questionnaire:questionnaireAnswers bean="${bean}"
	property="${property}" answerHeaderIndex="${answerHeaderIndex}" readOnly="true" defaultOpen="true"/>










