 <%--
 Copyright 2005-2013 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="bean" required="true" type="org.kuali.kra.questionnaire.QuestionnaireHelperBase" %>
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
