<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

    <c:set var="transparent" value="false" />

    <c:if test="${answerHeaderIndex == 0}">
      <c:set var="transparent" value="true" />
    </c:if> 
    <c:choose>
    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].completed}">
     	<c:set var="tabTitle" value="${KualiForm.questionnaireHelper.headerLabels[answerHeaderIndex]} (Complete)" />
    </c:when>
    <c:otherwise>
     	<c:set var="tabTitle" value="${KualiForm.questionnaireHelper.headerLabels[answerHeaderIndex]} (Incomplete)" />
    </c:otherwise> 
    </c:choose>

<kul:tab tabTitle="${tabTitle}"
					 tabErrorKey="questionnaireHelper.answerHeaders[${answerHeaderIndex}]*"
					 auditCluster="requiredFieldsAuditErrors" 
					 tabAuditKey="" 
					 useRiceAuditMode="true"
			         tabDescription=""
			         defaultOpen="${hasErrors}" 
					 useCurrentTabIndexAsKey="true"
			         transparentBackground="${transparent}">
			         
	<div class="tab-container" align="center">
        <h3>
        <span class="subhead-left"><a href="#" id ="questionpanelcontrol${answerHeaderIndex}" class="questionpanelcontrol${answerHeaderIndex}"><img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a>
          Questions </span>
 	        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.questionnaire.answer.AnswerHeader" altText="help"/></span>
        </h3>
<div id="questionpanelcontent${answerHeaderIndex}">
       <c:set var="questionid" value="" />
        <c:forEach items="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers}" var="answer" varStatus="status">   

<c:if test="${questionid ne answer.question.questionId}" >

<c:if test="${!empty questionid}" >

</div>
</td></tr></table>
</c:if>


<c:set var="qname" value="HD${answerHeaderIndex}-QN${questionIndex}"/>            
       <c:set var="questionid" value="${answer.question.questionId}" />
    <table class="content_table">  
        <tr>
            <td class="content_questionnaire">
<div class="Qdiv" id="${qname}div">
    <div class="Qquestiondiv">
        <span class="Qmoreinfocontrol">More Information...</span>
        <!--<span class="Qnumber">1.0.0</span>-->
        <span class="Qquestion">${answer.question.question}</span>
        
    </div>
    <kra-questionnaire:questionMoreInfo question="${answer.question}" />
</c:if>

            <kra-questionnaire:questionnaireAnswer questionIndex="${status.index}" />        
        </c:forEach>

       <c:set var="questionid" value="${answer.question.questionId}" />

</div>
</td></tr></table>


        </div>
    </div>
</kul:tab>
