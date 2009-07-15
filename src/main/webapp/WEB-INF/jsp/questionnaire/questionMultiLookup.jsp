<%--
 Copyright 2006-2008 The Kuali Foundation

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


 		<!--  <label>Sponsor Code Search</label> -->
   		<label>
   		
   			<input type="image" tabindex="1000000" name="methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(:;questionnaireQuestions;:).((%true%)).anchor" id = "lookupBtn" 
	   src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Multiple Value Search on " title="Multiple Value Search on " />

         	</label><br>
         	
         		<input type="hidden" id="selectedQuestions" name="selectedQuestions" value="${QuestionLookupForm.selectedQuestions}" />
         	
         	<p><a href="javascript:returnQuestion();window.close();"><b>return data</b></a> <a href="javascript:window.close()">Close</a></p> 
         	
          	<script type="text/javascript">
       function hasFormAlreadyBeenSubmitted() {
           //return false;
       }
          	
          	  
          	     function returnQuestion() {
          	            var questions = document.getElementById("selectedQuestions").value
          	         	window.opener.returnQuestionList(questions);
          	     
          	     }
                 var lookupBtn=document.getElementById("lookupBtn");
                 //alert("methodtocall "+document.getElementById("methodToCall").value);
                 if (document.getElementById("methodToCall").value != "refresh") {
                 	//lookupBtn.click();
                 } else {
                 	returnQuestion();
                 	window.close();
                 }
            </script>
 <%--           
</kul:page>
  --%>          
</html:form>
