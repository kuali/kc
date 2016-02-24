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


   		<label>
   		<%--
   			<input type="image" tabindex="1000000" name="methodToCall.performLookup.(!!org.kuali.coeus.common.questionnaire.framework.question.Question!!).(((id:newQuestionId,question:newQuestion))).((%true%)).anchor" id = "lookupBtn"
	   src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Multiple Value Search on " title="Multiple Value Search on " />
	    --%>
    			<input type="image" tabindex="1000000" name="methodToCall.performLookup.(!!org.kuali.coeus.common.questionnaire.framework.question.Question!!).(((id:newQuestionId,questionTypeId:newQuestionTypeId,question:newQuestion,answerMaxLength:newAnswerMaxLength,maxAnswers:newMaxAnswers,displayedAnswers:newDisplayedAnswers,lookupReturn:newLookupReturn,lookupClass:newLookupClass,sequenceNumber:newQuestionSequence))).((%false%)).anchor" id = "lookupBtn"
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
          	         	window.opener.returnQuestion(newQuestionId, newQuestion,newQuestionTypeId,newQuestionSequence,displayedAnswers,maxAnswers,answerMaxLength,nodeIndex);
          	            }
          	     
          	     }
                 var lookupBtn=document.getElementById("lookupBtn");
                 //alert("methodtocall "+document.getElementById("methodToCall").value);
                 if (document.getElementById("methodToCall").value != "refresh") {
                 	lookupBtn.click();
                 } else {
                  // alert("else "+document.getElementById("methodToCall").value);
                 	returnQuestion();
                 	window.close();
                 }
            </script>
</html:form>
