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
var Kc = Kc || {};
Kc.Questionnaire = Kc.Questionnaire || {};
Kc.Questionnaire.Answer = Kc.Questionnaire.Answer || {};
(function(namespace, $) {
    namespace.questionnaireDateFormat = "%m/%e/%Y";
	namespace.answerIdRegex = /(.*)(\.answers\[)(\d+)(\])/;

    namespace.initQuestions = function(){
		$(".uif-documentPage").find(".question").each(function(){
			$(this).on('change',function(e){
			   namespace.answerChanged(this);
			});

			var displayedAnswers = ($(this).data('kc-question-displayed-answers'));
			if (displayedAnswers && displayedAnswers !== 0) {
				var hasValue = false;
				var hidAnswers = false;
				$($(this).find(".answer").get().reverse()).each(function() {
					if ($(this).val()) {
						hasValue = true;
					}

					if (!hasValue && !namespace.isVisibleIndex($(this).attr('name'), displayedAnswers)) {
						$(this).parents("li").first().hide();
						hidAnswers = true;
					}
				});

				if (hidAnswers) {
					namespace.addDisplayMore($(this));
				}
			}

        });
    };

	namespace.isVisibleIndex = function(id, displayedAnswers) {
		var match = namespace.answerIdRegex.exec(id);
		if (match && match[3]) {
			return match[3] < displayedAnswers;
		}

		return true;
	};

	namespace.addDisplayMore = function(answerWrapper) {
		var answerList = answerWrapper.find('ul');
		var addmore = answerList.children('.addmore');

		if (!addmore.length) {
			answerList.append(
				$('<li>').attr('class', 'addmore').append(
					$('<div>').attr('class', 'uif-boxSection clearfix').attr('style', 'padding-bottom: 20px').append(
						$('<a>').on('click', function(e){
							answerList.children('li:hidden:lt(10)').each(function() {
								$(this).show();
							});
						}).append(
							$('<span>').append('More answers...')
						))));
		}
	};

    /*
     * function that handles answer change.   It will check whether to hide or show the affected descendant answers.
     * Notes : 1. check the siblings (of "input") of 'div[class^=Qresponsediv]', this input id contains answer header and question answer index
     *         2. The child questions have table id starts with 'table-parent-{answerheaderindex}-{questionanswerindex}-{childquestionanswerindex}"
     *         3. All the matched children questions should be checked whether answer match condition or not.
     */
	namespace.answerChanged = function(answerWrapper) {
		var questionWrapper = $(answerWrapper).parents('div.question[data-kc-questionid]');
		var questionnairePanel = $(questionWrapper).parents('section.questionnaireContent');
		var answer = $(questionWrapper).find('input.answer:first');
		var parentQuestionId = $(questionWrapper).data('kc-questionid');

        if (answer.size() == 0) {
            answer = questionWrapper.find("select");
        }

        $(questionnairePanel).find("div[data-kc-question-parentid='"+parentQuestionId+"']").each(function() {
    		var condition = eval($(this).data('kc-question-condition'));
    		if ($(questionWrapper).is(':visible') && namespace.isConditionMatchAnswers(answer, condition)) {
    			$(this).slideDown(500);
    		} else {
    			$(this).slideUp(500);
    			namespace.emptyAnswerForHiddenQuestion(this);
    		}
    		namespace.answerChanged($(this).find('input.answer'));
        });
	};

    /*
     * uncheck radio button if it is checked and empty answer fields if it is not a 'radio' type.
     */
    namespace.emptyAnswerForHiddenQuestion = function(questionTable) {
   		$(questionTable).find('input[name$=".answer"]').each(function() {		
   			var radioChecked = $(this).attr('checked');
   			if (radioChecked) {
   				$(this).attr('checked', false);
   			} else {
   				if ($(this).attr("type") != "radio") {
   					$(this).attr("value","");
   				}
   			}	  
   		});
    };
    
    /*
     * check if the answer matched the condition set up for the child question.
     */
	namespace.isConditionMatchAnswers = function(answer, conditionObj) {
		// if condition is not set (ie, condition is empty and isNaN) , then it is a required question if its parents is displayed
		var isMatched = (conditionObj.conditionFlag == 'false') || namespace.isRuleValid(conditionObj.condition, conditionObj.conditionValue) || namespace.isConditionMatched(answer, conditionObj);
		if (!isMatched && $(answer).parent().siblings('input.answer').size() > 0) {
			$(answer).parent().siblings('input.answer').each (function() {
					if (!isMatched) {
                        isMatched = namespace.isConditionMatched($(this).find('input.answer:first'), conditionObj);
					}
                    
			});
		}
		return isMatched;
	};
	
	/*
	 * if the branching condition is "rule evaluation" and the rule is evaluated to "true" or "false"
	 */
	namespace.isRuleValid = function(condition, conditionValue) {
	
	  return condition == 13 && ruleReferenced.length > 0 && (ruleReferenced.val().indexOf(conditionValue+":Y") == 0 
	    		|| ruleReferenced.val().indexOf(","+conditionValue+":Y") > 0);
		  
	  };
    /*
     * condition check for all the conditions implemented in this release 2.1.
     * Coeus seems only to allow positive integer if condition is related to number
     */
    namespace.isConditionMatched = function(answer, conditionObj) {

      /* The following conditions is set up in questionnaire maintenance document maintenance
       * var responseArray = [ 'select', 'Contains text value', 'Matches text',
       *          		'Less than number', 'Less than or equals number', 'Equals number',
       *         		'Greater than or equals number', 'Greater than number', 'Before date',
       *         		'After date' ];                 
       */
       var condition = conditionObj.condition;
       var conditionValue = conditionObj.conditionValue;
       var answerValue = $(answer).val();
       if ($(answer).is(':radio')) {
    	   answerValue = $(answer).parent().find(':checked').val();
       }
        if ($(answer).is('select')) {
            answerValue = $(answer).find(':selected').val();
        }
        if (answerValue === undefined) {
            answerValue = "";
        }
		var isMatched = false;
        if (condition == 1) {
          // contains text value
            isMatched = ((answerValue.toUpperCase()).indexOf(conditionValue.toUpperCase()) >= 0);
        } else if (condition == 2) {
            // begins with text   
              isMatched = (answerValue.toUpperCase().match("^" + conditionValue.toUpperCase()));
        } else if (condition == 3) {
            // ends text   
            isMatched = (answerValue.toUpperCase().match(conditionValue.toUpperCase() + "$"));
        } else if (condition == 4) {
            // match text
                if (answerValue == ""){
                    answerValue = "N"
                }
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
        			isMatched = namespace.isDateMatched($(answer).val(), conditionValue, condition);
        		}
        	}
    	}	  

        return isMatched;	
	};


	/*
	 * check if date is either 'before date' or 'after date'
	 */
	namespace.isDateMatched = function(parentAnswer, conditionValue, condition) {
	     var date1 = Date.parseDate(parentAnswer, questionnaireDateFormat);
	     var date2 = Date.parseDate(conditionValue, questionnaireDateFormat);

		 return (condition == 11 && (date1 < date2)) ||
		            (condition == 12 && (date1 > date2));
	 };
    namespace.setWidgetInputOnly = function() {
        $('.questionnaire-widgetInputOnly').each(function() {$(this).attr('readOnly',true)});
    }
})(Kc.Questionnaire.Answer, jQuery);
