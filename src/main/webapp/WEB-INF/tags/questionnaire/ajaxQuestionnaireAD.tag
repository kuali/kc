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
<%@ attribute name="viewOnly" required="true" %>

<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />
<%--  <c:set var="readOnly" value="${!KualiForm.questionnaireHelper.answerQuestionnaire}" scope = "request"/> --%>
<c:set var="readOnly" value="${viewOnly}" scope = "request"/>
<div id="workarea">
<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<tr>
		<td class="tabtable1-left">
		    <img height="29" width="12" align="middle" alt="" src="${ConfigProperties.kr.externalizable.images.url}tab-topleft.gif">
              
              
              
                <h2>Questionnaire</h2>
              
            
		</td>
		<td class="tabtable1-mid">
            
			    <%-- <input type="image" alt="close Questionnaire" title="close Questionnaire" class="tinybutton" id="tab-Questionnaire-imageToggle" onclick="javascript: return toggleTab(document, 'Questionnaire'); " src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" name="methodToCall.toggleTab.tabRoles">
		    --%>
		    
		</td>
		<td class="tabtable1-right">
		    <img height="29" width="12" align="middle" alt="" src="${ConfigProperties.kr.externalizable.images.url}tab-topright.gif">
		</td>
	</tr>
  </tbody>
</table>
<h5>
<c:forEach items="${KualiForm.questionnaireHelper.answerHeaders}" var="answerHeader" varStatus="status">
	<c:set var="answerHeaderIndex" value="${status.index}"/>
	
	<%--
	<c:set var="prop" value="questionnaireHelper.answerHeaders[${answerHeaderIndex}].showQuestions"/>
	${kfunc:registerEditableProperty(KualiForm, prop)}
	<input type="hidden" name="${prop}" id ="${prop}" 
           value = "${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].showQuestions}" readonly = "${readOnly}"/>
		 --%>
		 
     <kra-questionnaire:submissionQuestionnaireAnswers property = "questionnaireHelper" bean = "${KualiForm.questionnaireHelper}" answerHeaderIndex = "${answerHeaderIndex}"/>
				
				 
</c:forEach>
</h5>
<c:if test="${fn:length(KualiForm.questionnaireHelper.answerHeaders) > 0}">

	${kfunc:registerEditableProperty(KualiForm, "numberOfQuestionaires")}
    <input type="hidden" name="numberOfQuestionaires" id ="numberOfQuestionaires" 
       value = "${fn:length(KualiForm.questionnaireHelper.answerHeaders)}" />
    <kul:panelFooter />
</c:if>

</div>
