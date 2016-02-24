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

<%@ attribute name="bean" required="true" type="org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="answerHeaderIndex" required="true" %>
<%@ attribute name="forceNonTransparent" required="false" %>
<%@ attribute name="readOnly" required="false" %>
<%@ attribute name="printLineIndex" required="false" %>
<%@ attribute name="defaultOpen" required="false" %>

<c:if test = "${empty forceNonTransparent}">
	<c:set var = "forceNonTransparent" value = "false"/>
</c:if> 

    <c:set var="transparent" value="false" />

    <c:if test="${answerHeaderIndex == 0 && !forceNonTransparent}">
      <c:set var="transparent" value="true" />
    </c:if> 
    <c:set var="questReadOnly" value="${!bean.answerQuestionnaire || readOnly}" scope = "request"/>
   	<c:if test="${not bean.answerHeaders[answerHeaderIndex].activeQuestionnaire}">
           <c:set var="inactivate" value="- This Questionnaire has been deactivated." />
           <c:set var="questReadOnly" value="true" scope="request"/>
    </c:if>
    
    
    <c:choose>
    <c:when test="${bean.answerHeaders[answerHeaderIndex].completed && bean.answerHeaders[answerHeaderIndex].hasVisibleQuestion}">
     	<c:set var="tabTitle" value="${bean.headerLabels[answerHeaderIndex]} (Complete) ${inactivate}" />
    </c:when>
    <c:when test="${bean.answerHeaders[answerHeaderIndex].hasVisibleQuestion}">
     	<c:set var="tabTitle" value="${bean.headerLabels[answerHeaderIndex]} (Incomplete) ${inactivate}" />
    </c:when>
    <c:otherwise>
			<c:set var="tabTitle" value="${bean.headerLabels[answerHeaderIndex]} ${inactivate}" />    
    </c:otherwise> 
    </c:choose>
    <c:set var="showQuestions" value="false" />
    <c:if test="${!empty bean.answerHeaders[answerHeaderIndex].showQuestions and bean.answerHeaders[answerHeaderIndex].showQuestions == 'Y'}">
      <c:set var="showQuestions" value="true" />
    </c:if> 
    <c:if test="${empty printLineIndex}">
    	<c:set var="printLineIndex" value="${answerHeaderIndex}"/>
    </c:if>

	<kul:tab tabTitle="${tabTitle}"
						 tabErrorKey="${property}.answerHeaders[${answerHeaderIndex}]*"
						 auditCluster="${property}${bean.headerLabels[answerHeaderIndex]}${answerHeaderIndex}" 
						 tabAuditKey="${property}.answerHeaders[${answerHeaderIndex}]*" 
						 useRiceAuditMode="true"
				         tabDescription=""
				         defaultOpen="${defaultOpen || showQuestions || !bean.answerHeaders[answerHeaderIndex].hasVisibleQuestion}" 
						 useCurrentTabIndexAsKey="true"
				         transparentBackground="${transparent}">
			         
	<div class="tab-container" align="center">
		<kra-questionnaire:questionnaireAnswersBody  answerHeaderIndex="${answerHeaderIndex}" bean="${bean}" 
			property="${property}" readOnly="${questReadOnly}" printLineIndex="${answerHeaderIndex}"/>	
    </div>
</kul:tab>
