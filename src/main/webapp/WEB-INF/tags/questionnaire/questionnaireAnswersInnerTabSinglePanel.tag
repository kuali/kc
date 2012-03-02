 <%--
 Copyright 2005-2010 The Kuali Foundation

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
<%@ attribute name="parentTab" required="true" %>

        <%-- reset readonly, in case there is deactivated qn displayed before this one --%>
        <c:set var="readOnly" value="${!bean.answerQuestionnaire}" scope = "request"/>
    	<c:if test="${not bean.answerHeaders[answerHeaderIndex].activeQuestionnaire}">
            <c:set var="inactivate" value="- This Questionnaire has been deactivated." />
            <c:set var="readOnly" value="true" scope="request"/>
        </c:if>
    
    <c:choose>
    <c:when test="${bean.answerHeaders[answerHeaderIndex].completed}">
     	<c:set var="tabTitle" value="${bean.headerLabels[answerHeaderIndex]} (Complete) ${inactivate}" />
    </c:when>
    <c:otherwise>
     	<c:set var="tabTitle" value="${bean.headerLabels[answerHeaderIndex]} (Incomplete) ${inactivate}" />
    </c:otherwise> 
    </c:choose>
    <c:set var="showQuestions" value="false" />
    <c:if test="${!empty bean.answerHeaders[answerHeaderIndex].showQuestions and bean.answerHeaders[answerHeaderIndex].showQuestions == 'Y'}">
      <c:set var="showQuestions" value="true" />
    </c:if> 



<kul:innerTab tabTitle="${tabTitle}"
					 tabErrorKey="${property}.answerHeaders[${answerHeaderIndex}]*"
					 auditCluster="${property}${bean.headerLabels[answerHeaderIndex]}${answerHeaderIndex}" 
					 tabAuditKey="${property}.answerHeaders[${answerHeaderIndex}]*" 
			         tabDescription=""
			         defaultOpen="${showQuestions}" 
					 useCurrentTabIndexAsKey="true"
			         parentTab="${parentTabName}">
			         
	<div class="innerTab-container" align="center">
	    <c:if test="${bean.answerHeaders[answerHeaderIndex].newerVersionPublished and not readOnly}">
            <kra-questionnaire:updateQuestionnaireAnswer  answerHeaderIndex="${answerHeaderIndex}" bean = "${bean}" property = "${property}"/>        
        </c:if>
	
        <h3>
            <span class="subhead-left">
                <a href="#" id ="questionpanelcontrol:${property}:${answerHeaderIndex}" class="questionpanel">
                    <img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' title="show/hide panel" width='45' height='15' border='0' align='absmiddle'>
                </a>
                Questions 
            </span>
 	        <span class="subhead-right">
 	            <html:image property="methodToCall.printQuestionnaireAnswer.${property}.line${answerHeaderIndex}.anchor"
	                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printdark.gif' styleClass="tinybutton"
                            alt="Print Questionnaire Answer" title="Print Questionnaire Answer" onclick="excludeSubmitRestriction = true;"/> 
<%--                <a title="[Help]help" target="helpWindow" href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getBusinessObjectHelpText&amp;businessObjectClassName=org.kuali.kra.questionnaire.question.Question">
                    <img styleClass="tinybutton" alt="[Help]help" src="${ConfigProperties.kr.externalizable.images.url}my_cp_inf.gif"></a>
                    <%--  when using this tag, the 'print' and '?' is not aligning well.
                    <kul:help businessObjectClassName="org.kuali.kra.questionnaire.question.Question" altText="help"/>
                    --%> 
            </span>
        </h3>
        <div id="questionpanelcontent:${property}:${answerHeaderIndex}">
            <c:set var="questionid" value="" />
            <c:set var="addFooter" value="false" />
            <c:forEach items="${bean.answerHeaders[answerHeaderIndex].answers}" var="answer" varStatus="status">   

                <c:if test="${questionid ne answer.questionNumber}" >
                <%-- This 'if' block displays tab header for each question. if question has multiple answers
                     This is only displayed once when the 1st answer of this question is displayed --%>
                    <c:if test="${addFooter}" >
                    <%-- close tags for each question --%>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </c:if>

					<c:set var="addFooter" value="true"/>
                    <c:set var="tableId" value="table-${answerHeaderIndex}-${status.index}" />   
                    <c:if test="${answer.questionnaireQuestion.parentQuestionNumber != 0}" >
                        <c:set var="tableId" value="table-parent-${answerHeaderIndex}-${answer.questionnaireQuestion.parentQuestionNumber}-${status.index}"/>   
                    </c:if>                 

                    <c:set var="qname" value="HD${answerHeaderIndex}-QN${status.index}"/>            
                    <c:set var="questionid" value="${answer.questionNumber}" />
                    <table class="content_table" id = "${tableId}">  
                        <tr>
                            <td class="content_questionnaire">
                                <div class="Qdiv" id="${qname}div">
                                    <div class="Qquestiondiv">
                                        <span class="Qmoreinfocontrol" onclick="var ele = document.getElementById('${qname}AddInfo'); if(ele.style.display == 'none'){ ele.style.display = 'block';} else {ele.style.display = 'none';}">More Information...</span>
                                        <!--<span class="Qnumber">1.0.0</span>-->
                                        <span class="Qquestion">${answer.question.question}</span>
        
                                    </div>
                                    <div id="${qname}AddInfo" style="display: none;">
                                    	<kra-questionnaire:questionMoreInfo question="${answer.question}" />
                                    </div>
                </c:if>
				
                <c:choose>
                    <%-- decide whether it is readonly mode --%>
                    <c:when test = "${readOnly}" >
                        
                        <c:set var="prop" value="childDisplay-${answerHeaderIndex}-${answer.questionNumber}"/>
                        ${kfunc:registerEditableProperty(KualiForm, prop)}
                        <input type="hidden" id="childDisplay-${answerHeaderIndex}-${answer.questionNumber}" name="childDisplay-${answerHeaderIndex}-${answer.questionNumber}" value="${answer.matchedChild}" />                
                        <c:choose>
                            <c:when test = "${answer.question.questionTypeId == 1 or answer.question.questionTypeId == 2}" >
                                <c:choose>
                                    <c:when test = "${answer.answer == 'Y'}" >
                                      Yes
                                    </c:when>
                                    <c:when test = "${answer.answer == 'N'}" >
                                      No
                                    </c:when>
                                    <c:when test = "${answer.answer == 'X'}" >
                                      N/A
                                    </c:when>
                                    <c:otherwise>
                                    
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:when test = "${answer.question.questionTypeId == 6 and answer.question.lookupClass == 'org.kuali.kra.bo.ArgValueLookup'}" >
                                <jsp:useBean id="paramMap" class="java.util.HashMap"/>
		                        <c:set target="${paramMap}" property="argName" value="${answer.question.lookupReturn}" />
		                        <c:forEach items="${krafn:getOptionList('org.kuali.kra.lookup.keyvalue.ArgValueLookupValuesFinder', paramMap)}" var="option">
		        	                <c:if test="${answer.answer == option.key}">
		        	                    ${option.value}
		        	                </c:if>    
		                        </c:forEach>
                            </c:when>
                            <c:otherwise>
                                  ${answer.answer} </br>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <kra-questionnaire:questionnaireAnswer questionIndex="${status.index}" bean = "${bean}" property = "${property}" answerHeaderIndex = "${answerHeaderIndex}" />        
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:set var="questionid" value="${answer.questionNumber}" />

           <%-- following 4 tags is to close the last question's display tag --%>
             <c:if test="${addFooter}" >
                                </div>
                            </td>
                        </tr>
                    </table>
			 </c:if>                    


        </div>
    </div>
</kul:innerTab>
