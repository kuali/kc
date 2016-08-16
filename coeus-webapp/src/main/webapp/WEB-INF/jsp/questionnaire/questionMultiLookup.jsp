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


<html:form styleId="kualiForm" method="post"
	action="/questionLookup.do" enctype=""
	onsubmit="return hasFormAlreadyBeenSubmitted();"> 
<%--	
	<kul:page lookup="true" 
          docTitle="Question Lookup" 
          transactionalDocument="false" 
          htmlFormAction="questionLookup">
	--%>
	
	<input type="hidden" id="methodToCall"
		name="methodToCall" value="${QuestionLookupForm.methodToCall}"/>
	<input type="hidden" id="nodeIndex"
		name="nodeIndex" value="${QuestionLookupForm.nodeIndex}"/>
	<input type="hidden" id="newQuestion"
		name="newQuestion" value="${QuestionLookupForm.newQuestion}"/>
	<input type="hidden" id="newQuestionTypeId"
		name="newQuestionTypeId" value="${QuestionLookupForm.newQuestionTypeId}"/>
	<input type="hidden" id="newQuestionId"
		name="newQuestionId" value="${QuestionLookupForm.newQuestionId}"/>
	<input type="hidden" id="anchor"
		name="newQuestionId" value="${QuestionLookupForm.anchor}"/>
	<kul:csrf />

   		<label>
   		
   			<input type="image" tabindex="1000000" name="methodToCall.performLookup.(!!org.kuali.coeus.common.questionnaire.framework.question.Question!!).(:;questionnaireQuestions;:).((%true%)).anchor" id = "lookupBtn" 
	   src="kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Multiple Value Search on " title="Multiple Value Search on " />

         	</label><br>
         	
         		<input type="hidden" id="selectedQuestions" name="selectedQuestions" value="${QuestionLookupForm.selectedQuestions}" />
         	
         	<p><a href="javascript:returnQuestion();window.close();"><b>return data</b></a> <a href="javascript:window.close()">Close</a></p> 
         	
          	<script type="text/javascript">
       function hasFormAlreadyBeenSubmitted() {
           //return false;
       }
          	
          	  
          	     function returnQuestion() {
          	            var questions = document.getElementById("selectedQuestions").value;
          	            if (questions != '') {
          	         	window.opener.returnQuestionList(questions);
          	            }
          	     
          	     }
                 var lookupBtn=document.getElementById("lookupBtn");
                 //alert("methodtocall "+document.getElementById("methodToCall").value);
                 if (document.getElementById("methodToCall").value != "refresh") {
                 	lookupBtn.click();
                 } else {
                 	returnQuestion();
                 	window.close();
                 }
            </script>
 <%--           
</kul:page>
  --%>
</html:form>
