/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
var questionnaireDateFormat = "%m/%e/%Y";    
var ruleReferenced;
    $j(document).ready(function(){
			
			// More/Less Information...
				$j(".Qmoreinfocontrol").parent().next().hide();
				$j(".Qmoreinfocontrol").toggle(
					function()
					{
						$j(this).parent().next().slideDown(400);
						$j(this).html("Less Information...");
					},function(){
						$j(this).parent().next().slideUp(400);
						$j(this).html("More Information...");
					}
				);
				
				// set up Questions show/hide
				$j(".numberOfQuestionnaires").each(
					function(index) {
					var idSplit = $j(this).attr("id").split(":");
					var formProperty = idSplit[1];
					for( var i = 0; i < $j(this).attr("value");i++) {
						//$j("#questionpanelcontent\\:"+formProperty+"\\:"+i).hide();
						if ($j("#"+formProperty+"\\.answerHeaders\\["+i+"\\]\\.showQuestions").attr("value") == 'Y')  {
							$j("#questionpanelcontrol\\:"+formProperty+"\\:"+i).click();
						}
					}
				  });
				
				ruleReferenced = $j("#ruleReferenced");	
				
				$j('.questionnaireAnswer').change(function() { answerChanged(this); });
		});

    /*
     * questionnaire panel to toggle hide/show of panel
     */
    $j(".questionpanel").toggle(
		function()
		{
			$j(this).parent().parent().next().slideUp(500);
			$j(this).html("<img src='kr/images/tinybutton-show.gif' alt='Show panel' width='45' height='15' border='0' align='absmiddle'>");
			$j(this).parent().parent().parent().prev().attr("value","N");
		},
		function()
		{
			$j(this).parent().parent().next().slideDown(500);
			$j(this).html("<img src='kr/images/tinybutton-hide.gif' alt='Hide panel' width='45' height='15' border='0' align='absmiddle'>");
			$j(this).parent().parent().parent().prev().attr("value","Y");
		}
	);

    /*
     * set up 'change' event trigger for answer fields.
     * This is not working for date field from date picker, and lookup field return from search page.
     */
    $j('.Qanswer, textarea').change(
			function() {
				var headerDetails = $j(this).attr("name").split(".");
            	var formProperty = headerDetails[0];
				answerChanged(this,formProperty);
			});

    /*
     * function that handles answer change.   It will check whether to hide or show the affected descendant answers.
     * Notes : 1. check the siblings (of "input") of 'div[class^=Qresponsediv]', this input id contains answer header and question answer index
     *         2. The child questions have table id starts with 'table-parent-{answerheaderindex}-{questionanswerindex}-{childquestionanswerindex}"
     *         3. All the matched children questions should be checked whether answer match condition or not.
     */     
	function answerChanged(answer) {
		var questionnairePanel = $j(answer).parents('div.questionnaireContent');
		var questionTable = $j(answer).parents('table.question');
		var parentQuestionId = $j(questionTable).data('kc-questionid');
		var ruleId = $j(questionTable).data('kc-question-ruleid');
		
        $j(questionnairePanel).find("table[data-kc-question-parentid='"+parentQuestionId+"']").each(function() {
    		var condition = eval($j(this).data('kc-question-condition'));
    		var questionId = $j(this).data('kc-questionid');
    		if ($j(questionTable).is(':visible') && isConditionMatchAnswers(answer, condition, ruleId)) {
    			$j(this).show();
    		} else {
    			$j(this).hide();
    			emptyAnswerForHiddenQuestion(this);
    		}
    		answerChanged($j(this).find('div.answer input[name$=".answer"]'));
        });
	}

	/*
     * uncheck radio button if it is checked and empty answer fields if it is not a 'radio' type.
     */
    function emptyAnswerForHiddenQuestion(questionTable) {
   		$j(questionTable).find('input[name$=".answer"]').each(function() {		
   			var radioChecked = $j(this).attr('checked');
   			if (radioChecked) {
   				$j(this).attr('checked', false);
   			} else {
   				if ($j(this).attr("type") != "radio") {
   					$j(this).attr("value","");
   				}
   			}	  
   		});
    }
    
    /*
     * check if the answer matched the condition set up for the child question.
     */
	function isConditionMatchAnswers(answer, conditionObj, ruleId) {
		// if condition is not set (ie, condition is empty and isNaN) , then it is a required question if its parents is displayed
		var isMatched = (isRuleValid(ruleId) && isConditionMatched(answer, conditionObj));
		if (!isMatched && $j(answer).parent().siblings('div.answer').size() > 0) {
			$j(answer).parent().siblings('div.answer').each (function() {
					if (!isMatched) {
                        isMatched = isConditionMatched($j(this).find('input.answer:first'), conditionObj);
					}
			});
		}
		return isMatched;
	}
	
	/*
	 * if the branching condition is "rule evaluation" and the rule is evaluated to "true" or "false"
	 */
	function isRuleValid(ruleId) {
		  if(ruleId.length==0){
			  return true;
		  }else{
			  return ruleReferenced.length > 0 && (ruleReferenced.val().indexOf(ruleId+":Y") == 0 
		    		|| ruleReferenced.val().indexOf(","+ruleId+":Y") > 0);
		  }
		  
	  }
    /*
     * condition check for all the conditions implemented in this release 2.1.
     * Coeus seems only to allow positive integer if condition is related to number
     */
    function isConditionMatched(answer, conditionObj) {

      /* The following conditions is set up in questionnaire maintenance document maintenance
       * var responseArray = [ 'select', 'Contains text value', 'Matches text',
       *          		'Less than number', 'Less than or equals number', 'Equals number',
       *         		'Greater than or equals number', 'Greater than number', 'Before date',
       *         		'After date' ];                 
       */
       var condition = conditionObj.condition;
       var conditionValue = conditionObj.conditionValue;
       var answerValue = $j(answer).val();
       if ($j(answer).is(':radio')) {
    	   answerValue = $j(answer).parent().find(':checked').val();
    	   if (answerValue === undefined) {
    		   answerValue = "";
    	   }
       }
		var isMatched = false;
        if (condition == 1) {
          // contains text value
            isMatched = ((answerValue.toUpperCase()).indexOf(conditionValue.toUpperCase()) >= 0);
        } else if (condition == 2) {
            // begins with text   
              isMatched = (answerValue.toUpperCase().startsWith(conditionValue.toUpperCase()));
        } else if (condition == 3) {
            // ends text   
            isMatched = (answerValue.toUpperCase().endsWith(conditionValue.toUpperCase()));
        } else if (condition == 4) {
            // match text   
              isMatched = (conditionValue.toUpperCase() == answerValue.toUpperCase());
        } else if (condition >= 5 && condition <= 10) {
            if (isNaN(answerValue)) {
    		   alert("Value must be a number");
            } else if (!_isInteger(answerValue)){
     		   alert("Value must be a positive integer");
            } else {
            	isMatched = (condition == 5 && (Number(answerValue) < Number(conditionValue))) ||
            	            (condition == 6 && (Number(answerValue) <= Number(conditionValue))) ||
            	            (condition == 7 && (Number(answerValue) == Number(conditionValue))) ||
            	            (condition == 8 && (answerValue.length > 0) && (Number(answerValue) != Number(conditionValue))) ||
            	            (condition == 9 && (Number(answerValue) >= Number(conditionValue))) ||
            	            (condition == 10 && (Number(answerValue) > Number(conditionValue)));
            }    
    	} else if (condition > 10 && condition <= 12) {
        	if (answerValue != null) {
        		if (!Date.parseDate(answerValue, questionnaireDateFormat)) {
        			alert(answerValue + " is Not a Valid Date ");
        		} else {
        			isMatched = isDateMatched($j(answer).val(), conditionValue, condition)
        		}
        	}
    	}	  

        return isMatched;	
	}


	/*
	 * check if date is either 'before date' or 'after date'
	 */
	function isDateMatched(parentAnswer, conditionValue, condition) {
	     var date1 = Date.parseDate(parentAnswer, questionnaireDateFormat);
	     var date2 = Date.parseDate(conditionValue, questionnaireDateFormat);

		 return (condition == 11 && (date1 < date2)) ||
		            (condition == 12 && (date1 > date2));
	 }
		

	String.prototype.startsWith = function(str)
	{return (this.match("^"+str)==str)}
	
	String.prototype.endsWith = function(str)
	{return (this.match(str+"$")==str)}
