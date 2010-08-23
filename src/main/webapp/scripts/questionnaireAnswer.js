		$(document).ready(function(){
			
			// More/Less Information...
				$(".Qmoreinfocontrol").parent().next().hide();
				$(".Qmoreinfocontrol").toggle(
					function()
					{
						$(this).parent().next().slideDown(400);
						$(this).html("Less Information...");
					},function(){
						$(this).parent().next().slideUp(400);
						$(this).html("More Information...");
					}
				);
				
				// set up Questions show/hide	
			    for ( var i = 0; i < $("#numberOfQuestionaires").attr("value"); i++) {
		    		$("#questionpanelcontent"+i).hide();
			    	if ($("#questionnaireHelper\\.answerHeaders\\["+i+"\\]\\.showQuestions").attr("value") == 'Y') {
			    		$("#questionpanelcontrol"+i).click();
			    	}
			    }

					
		});

    /*
     * questionnaire panel to toggle hide/show of panel
     */
    $(".questionpanel").toggle(
            function()
            {
            	var headerIdx = $(this).attr("id").substring(20);
                var panelcontentid = "questionpanelcontent"+headerIdx;
                $("#"+panelcontentid).slideDown(500);
                $(this).html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                $("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.showQuestions").attr("value","Y")
            },function(){
            	var headerIdx = $(this).attr("id").substring(20);
                var panelcontentid = "questionpanelcontent"+headerIdx;
                $("#"+panelcontentid).slideUp(500);
                $(this).html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                $("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.showQuestions").attr("value","N")
            }
        );


    /*
     * go thru each question. if display flag is 'N', then hide this question/
     */
    $('table[@id^=table-parent-]').each(
			function() {
			    if ($(this).children().children().children().children().children('input[id^=childDisplay]').attr("value") == 'N') {
				    $(this).hide();
			    }
			});

    /*
     * set up 'change' event trigger for answer fields.
     * This is not working for date field from date picker, and lookup field return from search page.
     */
    $('.Qanswer, textarea').change(
			function() {
                answerChanged(this);
			});

    /*
     * function that handles answer change.   It will check whether to hide or show the affected descendant answers.
     * Notes : 1. check the siblings (of "input") of 'div[class^=Qresponsediv]', this input id contains answer header and question answer index
     *         2. The child questions have table id starts with 'table-parent-{answerheaderindex}-{questionanswerindex}-{childquestionanswerindex}"
     *         3. All the matched children questions should be checked whether answer match condition or not.
     */     
	function answerChanged(answer) {
		var qn= $(answer).parents('div[class^=Qresponsediv]').siblings('input[id^=parent]');
        if (qn) {
            var answerValue = $(answer).attr("value");
            var idx = $(qn).attr("id").substring(7,$(qn).attr("id").indexOf("-",7));
            var headerIdx = $(qn).attr("id").substring($(qn).attr("id").indexOf("-",7)+1);
            var responseDiv = $(answer).parents('div[class^=Qresponsediv]');
            var prefix = "table-parent-"+headerIdx+"-"+idx;
           $("table[@id^="+prefix+"-]").each(                           
        			function() {
                        var conditionDiv = $("#"+$(this).attr("id")+" .condition:nth(0)");
                        var qidx = $(this).attr("id").substring(prefix.length+1);
                        // not sure why conditionDiv is not 'undefine' if the node is not set
                        if (conditionDiv && conditionDiv.children() && conditionDiv.size() == 1 && conditionDiv.children().size() == 3) {
                           if (isConditionMatchAnswers(answer, responseDiv, conditionDiv.children('input:eq(1)').attr("value"), conditionDiv.children('input:eq(2)').attr("value"), answerValue)) {
                           		$(this).show();                                   		
                           		$("#childDisplay"+conditionDiv.children('input:eq(1)').attr("id").substring(9)).attr("value","Y");
                           		$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.matchedChild").attr("value","Y");
                          		showChildren(conditionDiv.children('input:eq(1)').attr("id").substring(10));
                           } else {
                          		hideChildren(conditionDiv.children('input:eq(1)').attr("id").substring(10));
                          		$(this).hide();
                           		$("#childDisplay"+conditionDiv.children('input:eq(1)').attr("id").substring(9)).attr("value","N");
                           		$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.matchedChild").attr("value","N");
                           		emptyAnswerForHiddenQuestion(this);
                           }    
                        }  else {
                      		hideChildren(conditionDiv.children('input:eq(1)').attr("id").substring(10));
                      		$(this).hide();
                       		$("#childDisplay"+conditionDiv.children('input:eq(1)').attr("id").substring(9)).attr("value","N");
                       		$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.matchedChild").attr("value","N");
                       		emptyAnswerForHiddenQuestion(this);

                        }
        			});
        }  
	}

	/*
	 * If a answer matched child condition, then this will be called to see if any further descendants
	 * under this question hierarchy need to be 'shown' too.  This is in general related to descendant
	 * without condition set up.
	 */
    function showChildren(parentIndicator) {
    	var prefix = "table-parent-"+parentIndicator;
    	var headerIdx = parentIndicator.substring(0, parentIndicator.indexOf("-"));
    	var idx = parentIndicator.substring(parentIndicator.indexOf("-")+1);
        $("table[@id^="+prefix+"-]").each(                           
    			function() {
                    var conditionDiv = $("#"+$(this).attr("id")+" .condition:nth(0)");
                    if (isNaN(conditionDiv.children('input:eq(1)').attr("value"))) {
                   		$(this).show();                   		
                   		$("#childDisplay"+conditionDiv.children('input:eq(1)').attr("id").substring(9)).attr("value","Y");
                   		$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.matchedChild").attr("value","Y");
                  		showChildren(conditionDiv.children('input:eq(1)').attr("id").substring(10));
                    } 
    			});

    }   

    /*
     * If a child questin's condition becomes unmatched, then need to navigate thru the
     * descendants and hide all of them under this hierarchy.
     */
    function hideChildren(parentIndicator) {
    	var prefix = "table-parent-"+parentIndicator;
    	var headerIdx = parentIndicator.substring(0, parentIndicator.indexOf("-"));
    	var idx = parentIndicator.substring(parentIndicator.indexOf("-")+1);
        $("table[@id^="+prefix+"-]").each(                           
    			function() {
              		$(this).hide();
                    var conditionDiv = $("#"+$(this).attr("id")+" .condition:nth(0)");
                    
                    if (conditionDiv && conditionDiv.children() && conditionDiv.size() == 1 && conditionDiv.children().size() == 3) {
                      		hideChildren(conditionDiv.children('input:eq(1)').attr("id").substring(10));                           
                       		$("#childDisplay"+conditionDiv.children('input:eq(1)').attr("id").substring(9)).attr("value","N");
                            var qidx = $(this).attr("id").substring(prefix.length+1);
                       		$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.matchedChild").attr("value","N");
                       		emptyAnswerForHiddenQuestion(this);
                    } 
    			});

    }   

    /*
     * uncheck radio button if it is checked and empty answer fields if it is not a 'radio' type.
     */
    function emptyAnswerForHiddenQuestion(questionTable) {
   		$(questionTable).find('[class^=Qanswer]').each(
           		function() {		
           		  var radioChecked = $(this).attr('checked');
           		  if (radioChecked) {
           			  $(this).attr('checked', false);
                  } else {
                	  if ($(this).attr("type") != "radio") {
                		  $(this).attr("value","");
                	  }
                  }	  
           		});

    }
    
    /*
     * check if the answer matched the condition set up for the child question.
     */
	function isConditionMatchAnswers(parentAnswerField, responseDiv, condition, conditionValue, parentAnswer) {
		// if condition is not set (ie, condition is empty and isNaN) , then it is a required question if its parents is displayed
		var isMatched = (condition == "") || isNaN(condition) || isConditionMatched(parentAnswerField, condition, conditionValue, parentAnswer);
		if (!isMatched && responseDiv.siblings('div[class^=Qresponsediv]').size() > 0) {
			responseDiv.siblings('div[class^=Qresponsediv]').each (
				function() {
					if (!isMatched) {
                        var answer = $(this).children().children().children().children().children().children('input').attr("value");
                        isMatched = isConditionMatched(parentAnswerField, condition, conditionValue, answer);
					}
                    
				}
			)
		}
		
		return isMatched;
		
	}
	
    /*
     * condition check for all the conditions implemented in this release 2.1.
     * Coeus seems only to allow positive integer if condition is related to number
     */
    function isConditionMatched(parentAnswerField, condition, conditionValue, parentAnswer) {

      /* The following conditions is set up in questionnaire maintenance document maintenance
       * var responseArray = [ 'select', 'Contains text value', 'Matches text',
       *          		'Less than number', 'Less than or equals number', 'Equals number',
       *         		'Greater than or equals number', 'Greater than number', 'Before date',
       *         		'After date' ];                 
       */
       
		var isMatched = false;
        if (condition == 1) {
          // contains text value
            isMatched = ((parentAnswer.toUpperCase()).indexOf(conditionValue.toUpperCase()) >= 0);
        } else if (condition == 2) {
            // begins with text   
              isMatched = (parentAnswer.toUpperCase().startsWith(conditionValue.toUpperCase()));
        } else if (condition == 3) {
            // ends text   
            isMatched = (parentAnswer.toUpperCase().endsWith(conditionValue.toUpperCase()));
        } else if (condition == 4) {
            // match text   
              isMatched = (conditionValue.toUpperCase() == parentAnswer.toUpperCase());
        } else if (condition >= 5 && condition <= 10) {
            if (isNaN(parentAnswer)) {
    		   alert("Value must be a number");
            } else if (!_isInteger(parentAnswer)){
     		   alert("Value must be a positive integer");
            } else {
            	isMatched = (condition == 5 && (Number(parentAnswer) < Number(conditionValue))) ||
            	            (condition == 6 && (Number(parentAnswer) <= Number(conditionValue))) ||
            	            (condition == 7 && (Number(parentAnswer) == Number(conditionValue))) ||
            	            (condition == 8 && (Number(parentAnswer) != Number(conditionValue))) ||
            	            (condition == 9 && (Number(parentAnswer) >= Number(conditionValue))) ||
            	            (condition == 10 && (Number(parentAnswer) > Number(conditionValue)));
            }    
    	} else if (condition > 10) {
        	if (!parseDate(parentAnswer)) {
    		    alert(parentAnswer + " is Not a Valid Date ");
        	} else {
        		setDate(parentAnswerField, parentAnswer);
        		//$(parentAnswerField).attr("value",formatDate(parseDate(parentAnswer), "MM/dd/yyyy"));
        		isMatched = isDateMatched($(parentAnswerField).attr("value"), conditionValue, condition)
        	}
    	}	  

        return isMatched;	
	}

    /*
     * This method is to convert some special formatted to rice supported date format.
     * These special formats have no "year" specified, so it parse to year like 110. or -1790
     * the current year will be added to these format, so it can behave like rice's 'MMM d'
     */
    function setDate(parentAnswerField, parentAnswer) {
    	if (isDate(parentAnswer, "MMM d") || isDate(parentAnswer, "M/d") || isDate(parentAnswer, "M-d")) {
    		// "MMM d" will be converted to MMM d, 110, and cause issue.  so, make it "MMM d, yyyy"
    		var d = new Date();
    		if (isDate(parentAnswer, "MMM d")) {
    			parentAnswer = parentAnswer + ", " +d.getFullYear();
    		} else if (isDate(parentAnswer, "M/d")) {
    			parentAnswer = parentAnswer + "/" +d.getFullYear();
    		} else if (isDate(parentAnswer, "M-d")) {
    			parentAnswer = parentAnswer + "-" +d.getFullYear();
    		} 
    		
    	}
    		$(parentAnswerField).attr("value",formatDate(parseDate(parentAnswer), "MM/dd/yyyy"));
    	
    }

	/*
	 * check if date is either 'before date' or 'after date'
	 */
	function isDateMatched(parentAnswer, conditionValue, condition)
	 {
	 //    var mon1  = parentAnswer.substring(0,2)-1;
	 //    var dt1 = parentAnswer.substring(3,5);
	 //    var yr1  = parentAnswer.substring(6,10);
	 //    var mon2  = conditionValue.substring(0,2)-1;
	 //    var dt2 = conditionValue.substring(3,5);
	 //    var yr2  = conditionValue.substring(6,10);
	     var date1 = parseDate(parentAnswer);
	     var date2 = parseDate(conditionValue);

		 return (condition == 11 && (date1 < date2)) ||
		            (condition == 12 && (date1 > date2));
	 }
		

	String.prototype.startsWith = function(str)
	{return (this.match("^"+str)==str)}
	
	String.prototype.endsWith = function(str)
	{return (this.match(str+"$")==str)}