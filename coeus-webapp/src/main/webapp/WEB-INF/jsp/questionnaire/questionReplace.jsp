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

<html:form styleId="kualiForm" method="post"
	action="/questionLookup.do" enctype=""
	onsubmit="return hasFormAlreadyBeenSubmitted();"> 
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
    <input type="hidden" id="newLookupClass"
        name="newLookupClass" value="${QuestionLookupForm.newLookupClass}"/>
    <input type="hidden" id="newLookupReturn"
        name="newLookupReturn" value="${QuestionLookupForm.newLookupReturn}"/>
    <input type="hidden" id="newDisplayedAnswers"
        name="newDisplayedAnswers" value="${QuestionLookupForm.newDisplayedAnswers}"/>
    <input type="hidden" id="newMaxAnswers"
        name="newMaxAnswers" value="${QuestionLookupForm.newMaxAnswers}"/>
    <input type="hidden" id="newAnswerMaxLength"
        name="newAnswerMaxLength" value="${QuestionLookupForm.newAnswerMaxLength}"/>
    <input type="hidden" id="newQuestionSequence"
        name="newQuestionSequence" value="${QuestionLookupForm.newQuestionSequence}"/>
	<input type="hidden" id="anchor"
		name="anchor" value="${QuestionLookupForm.anchor}"/>


 		<!--  <label>Sponsor Code Search</label> -->
   		<label>
   		<%--
   			<input type="image" tabindex="1000000" name="methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionRefId:newQuestionId,question:newQuestion))).((%true%)).anchor" id = "lookupBtn" 
	   src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Multiple Value Search on " title="Multiple Value Search on " />
	    --%>
    			<input type="image" tabindex="1000000" name="methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionRefId:newQuestionId,questionTypeId:newQuestionTypeId,question:newQuestion,answerMaxLength:newAnswerMaxLength,maxAnswers:newMaxAnswers,displayedAnswers:newDisplayedAnswers,lookupReturn:newLookupReturn,lookupClass:newLookupClass,sequenceNumber:newQuestionSequence))).((%false%)).anchor" id = "lookupBtn" 
	   src="kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Multiple Value Search on " title="Multiple Value Search on " />
   		
         	</label><br>
         	
         	
         	<p><a href="javascript:returnQuestion();window.close();"><b>return data</b></a> <a href="javascript:window.close()">Close</a></p> 
         	
          	<script type="text/javascript">
       function hasFormAlreadyBeenSubmitted() {
       //    return false;
       }
          	
          	  
          	     function returnQuestion() {
          	            var newQuestionId = document.getElementById("newQuestionId").value
          	            if (newQuestionId != '') {
          	            var newQuestionTypeId = document.getElementById("newQuestionTypeId").value
          	            var newQuestion = document.getElementById("newQuestion").value
          	            var nodeIndex = document.getElementById("nodeIndex").value
                        var newQuestionSequence = document.getElementById("newQuestionSequence").value;
          	            // retString = question.getDisplayedAnswers()+"#f#"+question.getMaxAnswers()+"#f#"+question.getAnswerMaxLength();
          	            var  displayedAnswers ;
                        var maxAnswers = document.getElementById("newMaxAnswers").value;
                        var answerMaxLength ;    
                        if (newQuestionTypeId == '6') {
                        	displayedAnswers = document.getElementById("newLookupClass").value;
                            if (displayedAnswers.indexOf(".") > 0) {
                                displayedAnswers = displayedAnswers.substring(displayedAnswers.lastIndexOf(".")+1);
                            }
                            //alert(displayedAnswers)
                            answerMaxLength = document.getElementById("newLookupReturn").value;
                        } else {
                            displayedAnswers = document.getElementById("newDisplayedAnswers").value;
                            answerMaxLength = document.getElementById("newAnswerMaxLength").value;
                        }             
          	            //var mapKey = document.getElementById("mapKey").value
          	            //alert(newQuestionId+"-"+newQuestionTypeId+"-"+newQuestion+"-")
          	         	window.opener.replaceQuestion(newQuestionId, newQuestion,newQuestionTypeId,newQuestionSequence,displayedAnswers,maxAnswers,answerMaxLength,nodeIndex);
          	            }
          	     
          	     }
                 var lookupBtn=document.getElementById("lookupBtn");
                 //alert("methodtocall "+document.getElementById("methodToCall").value);
                 if (document.getElementById("methodToCall").value != "refresh") {
                 	lookupBtn.click();
                 } else {
                  // alert("else "+document.getElementById("methodToCall").value);
                    if (document.getElementById("newQuestionId").value) {
                 	    returnQuestion();
                    }
                 	window.close();
                 }
            </script>
</html:form>
