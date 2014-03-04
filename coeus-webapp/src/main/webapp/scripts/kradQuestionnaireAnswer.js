String.prototype.startsWith = function(str)
{return (this.match("^"+str)==str)}

String.prototype.endsWith = function(str)
{return (this.match(str+"$")==str)}
	
QuestionnaireAnswer = (function($j) { return {
	questionnaireDateFormat : "%m/%e/%Y",   

    /*
     * function that handles answer change.   It will check whether to hide or show the affected descendant answers.
     * Notes : 1. check the siblings (of "input") of 'div[class^=Qresponsediv]', this input id contains answer header and question answer index
     *         2. The child questions have table id starts with 'table-parent-{answerheaderindex}-{questionanswerindex}-{childquestionanswerindex}"
     *         3. All the matched children questions should be checked whether answer match condition or not.
     */

	answerChanged : function(answerWrapper) {
		var questionWrapper = $j(answerWrapper).parents('div.question[data-kc-questionid]');
		var questionnairePanel = $j(questionWrapper).parents('div.questionnaireContent');
		var answer = $j(questionWrapper).find('input.answer:first');
		var parentQuestionId = $j(questionWrapper).data('kc-questionid');
		
        $j(questionnairePanel).find("div[data-kc-question-parentid='"+parentQuestionId+"']").each(function() {
    		var condition = eval($j(this).data('kc-question-condition'));
    		var questionId = $j(this).data('kc-questionid');
    		if ($j(questionWrapper).is(':visible') && QuestionnaireAnswer.isConditionMatchAnswers(answer, condition)) {
    			$j(this).slideDown(500);
    		} else {
    			$j(this).slideUp(500);
    			QuestionnaireAnswer.emptyAnswerForHiddenQuestion(this);
    		}
    		QuestionnaireAnswer.answerChanged($j(this).find('input.answer'));
        });
	},

    /*
     * uncheck radio button if it is checked and empty answer fields if it is not a 'radio' type.
     */
    emptyAnswerForHiddenQuestion : function(questionTable) {
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
    },
    
    /*
     * check if the answer matched the condition set up for the child question.
     */
	isConditionMatchAnswers : function(answer, conditionObj) {
		// if condition is not set (ie, condition is empty and isNaN) , then it is a required question if its parents is displayed
		var isMatched = (conditionObj.conditionFlag == 'false') || QuestionnaireAnswer.isRuleValid(conditionObj.condition, conditionObj.conditionValue) || QuestionnaireAnswer.isConditionMatched(answer, conditionObj);
		if (!isMatched && $j(answer).parent().siblings('input.answer').size() > 0) {
			$j(answer).parent().siblings('input.answer').each (function() {
					if (!isMatched) {
                        isMatched = QuestionnaireAnswer.isConditionMatched($j(this).find('input.answer:first'), conditionObj);
					}
                    
			});
		}
		return isMatched;
	},
	
	/*
	 * if the branching condition is "rule evaluation" and the rule is evaluated to "true" or "false"
	 */
	isRuleValid : function(condition, conditionValue) {
	
	  return condition == 13 && ruleReferenced.length > 0 && (ruleReferenced.val().indexOf(conditionValue+":Y") == 0 
	    		|| ruleReferenced.val().indexOf(","+conditionValue+":Y") > 0);
		  
	  },
    /*
     * condition check for all the conditions implemented in this release 2.1.
     * Coeus seems only to allow positive integer if condition is related to number
     */
    isConditionMatched : function(answer, conditionObj) {

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
            	            (condition == 8 && (Number(answerValue) != Number(conditionValue))) ||
            	            (condition == 9 && (Number(answerValue) >= Number(conditionValue))) ||
            	            (condition == 10 && (Number(answerValue) > Number(conditionValue)));
            }    
    	} else if (condition > 10 && condition <= 12) {
        	if (answerValue != null) {
        		if (!Date.parseDate(answerValue, questionnaireDateFormat)) {
        			alert(answerValue + " is Not a Valid Date ");
        		} else {
        			isMatched = QuestionnaireAnswer.isDateMatched($j(answer).val(), conditionValue, condition)
        		}
        	}
    	}	  

        return isMatched;	
	},


	/*
	 * check if date is either 'before date' or 'after date'
	 */
	isDateMatched : function(parentAnswer, conditionValue, condition) {
	     var date1 = Date.parseDate(parentAnswer, questionnaireDateFormat);
	     var date2 = Date.parseDate(conditionValue, questionnaireDateFormat);

		 return (condition == 11 && (date1 < date2)) ||
		            (condition == 12 && (date1 > date2));
	 }
};

})(jQuery);