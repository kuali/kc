		$(document).ready(function(){
			
			// Opened Panel
/*				$(".openedpanelcontrol").toggle(
					function()
					{
						$(this).parent().parent().parent().parent().next().slideUp(400);
						$(this).attr("src","../images/tinybutton-show.gif");
					},function(){
						$(this).parent().parent().parent().parent().next().slideDown(400);
						$(this).attr("src","../images/tinybutton-hide.gif");
					}
				);
*/			
			// Closed Panel
/*				$(".closedpaneldiv").attr("style","display:none;");
				$(".closedpanelcontrol").toggle(
					function()
					{
						$(this).parent().parent().parent().parent().next().slideDown(400);
						$(this).attr("src","../images/tinybutton-hide.gif");
					},function(){
						$(this).parent().parent().parent().parent().next().slideUp(400);
						$(this).attr("src","../images/tinybutton-show.gif");
					}
				);
*/				
			// Opened Section
/*				$(".openedsectioncontrol").toggle(
					function()
					{
						$(this).parent().parent().parent().parent().next().slideUp(400);
						$(this).attr("src","../images/tinybutton-show.gif");
					},function(){
						$(this).parent().parent().parent().parent().next().slideDown(400);
						$(this).attr("src","../images/tinybutton-hide.gif");
					}
				);
*/			
			// Closed Section
/*				$(".closedsectiondiv").attr("style","display:none;");
				$(".closedsectioncontrol").toggle(
					function()
					{
						$(this).parent().parent().parent().parent().next().slideDown(400);
						$(this).attr("src","../images/tinybutton-hide.gif");
					},function(){
						$(this).parent().parent().parent().parent().next().slideUp(400);
						$(this).attr("src","../images/tinybutton-show.gif");
					}
				);
*/			
			// More/Less Information...
				$(".Qmoreinfocontrol").parent().next().hide(0);
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
				




					
		});

		// set up Questions show/hide	
        var ansHeaderSize = $("#numberOfQuestionaires").attr("value");
    for ( var i = 0; i < ansHeaderSize; i++) {
        /*  this is not working for toggle, always got the last one, so has to implement a little diferent.
		var panelcontentid = "questionpanelcontent"+i;
		var panelcontrolid = "questionpanelcontrol"+i;
        alert("size "+ansHeaderSize+" - "+i+panelcontentid+panelcontrolid);
        var control = $("#"+panelcontrolid);
        var content = $("#"+panelcontentid);
        control.toggle(
            function()
            {
                alert("control id = "+$(this).attr("id"));
                content.slideDown(500);
                control.html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
            },function(){
                alert("control id = "+panelcontrolid);
                content.slideUp(500);
                control.html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
            }
        );
        $("#"+panelcontrolid).click();
        $("#"+panelcontrolid).click();
  */
        $("#questionpanelcontent"+i).hide();
    }

    $(".questionpanel").toggle(
            function()
            {
                //alert("control id = "+$(this).attr("id"));
                var panelcontentid = "questionpanelcontent"+$(this).attr("id").substring(20);
                //alert("control id = "+$(this).attr("id")+panelcontentid);
                $("#"+panelcontentid).slideDown(500);
                $(this).html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
            },function(){
                var panelcontentid = "questionpanelcontent"+$(this).attr("id").substring(20);
                $("#"+panelcontentid).slideUp(500);
                $(this).html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
            }
        );


    /*
     * go thru each question. if display flag is 'N', then hide this question/
     */
    $('table[@id^=table-parent-]').each(
			function() {
				//alert ("hide child "+$(this).attr("id"));
			//	$(this).hide();
			//}
			//	alert("child value "+$(this).children().children().children().children().children('input[id^=childDisplay]').attr("value"));
			if ($(this).children().children().children().children().children('input[id^=childDisplay]').attr("value") == 'N') {
			//	alert ("hide child "+$(this).attr("id"));
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
     * function that handles answer change.   It will check whether to hide to show the affected descendant answers.
     */
	function answerChanged(answer) {
		//alert ("changed "+$(answer).attr("value")+"-"+$(answer).parents('div[class^=Qresponsediv]').siblings('div[class^=Qresponsediv]').size());
		var qn= $(answer).parents('div[class^=Qresponsediv]').siblings('input[id^=parent]');
 		//alert ("answerChanged "+$(answer).attr("id"));
       //alert("qn "+$(qn).attr("id")+"-"+$(qn).attr("id").indexOf("-")+"-"+$(qn).attr("id").substring(7,$(qn).attr("id").indexOf(7,"-")));
        if (qn) {
            var answerValue = $(answer).attr("value");
            var idx = $(qn).attr("id").substring(7,$(qn).attr("id").indexOf("-",7));
            var headerIdx = $(qn).attr("id").substring($(qn).attr("id").indexOf("-",7)+1);
            var responseDiv = $(answer).parents('div[class^=Qresponsediv]');
            var prefix = "table-parent-"+headerIdx+"-"+idx;
          // alert("table condition size "+$("table[@id^="+prefix+"]").children().size()+"-"+prefix);
           $("table[@id^="+prefix+"]").each(                           
        			function() {
                        var conditionDiv = $("#"+$(this).attr("id")+" .condition:nth(0)");
            			//alert("table "+$(this).attr("id")+"-"+conditionDiv.size());
                        var qidx = $(this).attr("id").substring(prefix.length+1);
                        // not sure why conditionDiv is not 'undefine' if the node is not set
                        //if (conditionDiv && conditionDiv.size() > 1) {
                        //	conditionDiv = $(conditionDiv(':eq(0)'));
                        //}	
            			//alert("table "+$(this).attr("id")+"-"+conditionDiv.size());
                        if (conditionDiv && conditionDiv.children() && conditionDiv.size() == 1 && conditionDiv.children().size() == 3) {
                        //    alert(typeof(eval(conditionDiv)) + " "+conditionDiv.children('input:eq(0)').attr("value") +"-"+conditionDiv.children('input:eq(1)').attr("value") +"-"+conditionDiv.children('input:eq(2)').attr("value") +"-")
        				//  alert ("show child "+conditionDiv.children('input:eq(1)').attr("id").substring(9));
                           if (isConditionMatchAnswers(responseDiv, conditionDiv.children('input:eq(1)').attr("value"), conditionDiv.children('input:eq(2)').attr("value"), answerValue)) {
                           		$(this).show();                                   		
                           		$("#childDisplay"+conditionDiv.children('input:eq(1)').attr("id").substring(9)).attr("value","Y");
                           		$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.matchedChild").attr("value","Y");
                          		showChildren(conditionDiv.children('input:eq(1)').attr("id").substring(10));
                           } else {
                         		//alert ("hide "+$(this + ' .Qanswer').size());
                          		hideChildren(conditionDiv.children('input:eq(1)').attr("id").substring(10));
                          		$(this).hide();
                           		$("#childDisplay"+conditionDiv.children('input:eq(1)').attr("id").substring(9)).attr("value","N");
                           		$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.matchedChild").attr("value","N");
                           		//$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.answer").attr("value","");
                           		emptyAnswerForHiddenQuestion(this);
/*                           		
                           		$(this).find('.Qanswer').each(
                           		function() {		
                           		  var radioChecked = $(this).attr('checked');
                                  alert("radio "+radioChecked+"-"+qidx)
                           		  if (radioChecked) {
                           			  $(this).attr('checked', false);
                                  } else {
                                	  if ($(this).attr("type") != "radio") {
                                		  $(this).attr("value","");
                                	  }
                                  }	  
                           		}); 
 */                          		
                           }    
        				//$(this).show();
                        }  else {
                     		//alert ("hide "+conditionDiv.children('input:eq(1)').attr("id").substring(10));
                      		hideChildren(conditionDiv.children('input:eq(1)').attr("id").substring(10));
                      		$(this).hide();
                       		$("#childDisplay"+conditionDiv.children('input:eq(1)').attr("id").substring(9)).attr("value","N");
                       		$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.matchedChild").attr("value","N");
                       		//$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.answer").attr("value","");
                       		emptyAnswerForHiddenQuestion(this);

                        }
        			});
        }  
	}

	/*
	 * If a child answer matched condition, then this will be called to see if any other descendats
	 * under this question hierarchy need to be 'shown' too.  This is in general related to descendant
	 * without condition set up.
	 */
    function showChildren(parentIndicator) {
		//alert("show child table "+parentIndicator);
    	var prefix = "table-parent-"+parentIndicator;
    	var headerIdx = parentIndicator.substring(0, parentIndicator.indexOf("-"));
    	var idx = parentIndicator.substring(parentIndicator.indexOf("-")+1);
        $("table[@id^="+prefix+"]").each(                           
    			function() {
                    var conditionDiv = $("#"+$(this).attr("id")+" .condition:nth(0)");
    				//alert("show child table "+$(this).attr("id"));
                    if (isNaN(conditionDiv.children('input:eq(1)').attr("value"))) {
                   		$(this).show();                   		
                   		$("#childDisplay"+conditionDiv.children('input:eq(1)').attr("id").substring(9)).attr("value","Y");
                   		$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.matchedChild").attr("value","Y");
                  		showChildren(conditionDiv.children('input:eq(1)').attr("id").substring(10));
                    } 
    			});

    }   

    /*
     * If q child questin's condition becomes unmatched, then need to navigate thru the
     * descendants and hide all of then undeer this hierarchy.
     */
    function hideChildren(parentIndicator) {
		//alert("hide child table "+parentIndicator);
    	var prefix = "table-parent-"+parentIndicator;
    	var headerIdx = parentIndicator.substring(0, parentIndicator.indexOf("-"));
    	var idx = parentIndicator.substring(parentIndicator.indexOf("-")+1);
        $("table[@id^="+prefix+"]").each(                           
    			function() {
    				//alert("hide child table "+$(this).attr("id"));
              		$(this).hide();
                    var conditionDiv = $("#"+$(this).attr("id")+" .condition:nth(0)");
        			//alert("table "+$(this).attr("id")+"-"+conditionDiv.size());
                    
                    if (conditionDiv && conditionDiv.children() && conditionDiv.size() == 1 && conditionDiv.children().size() == 3) {
                      		hideChildren(conditionDiv.children('input:eq(1)').attr("id").substring(10));                           
                       		$("#childDisplay"+conditionDiv.children('input:eq(1)').attr("id").substring(9)).attr("value","N");
                            var qidx = $(this).attr("id").substring(prefix.length+1);
                       		$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.matchedChild").attr("value","N");
                       		//$("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.answers\\["+qidx+"\\]\\.answer").attr("value","");
                       		emptyAnswerForHiddenQuestion(this);
                    } 
    			});

    }   

    function emptyAnswerForHiddenQuestion(questionTable) {
   		$(questionTable).find('[class^=Qanswer]').each(
           		function() {		
           		  var radioChecked = $(this).attr('checked');
                  //alert("radio "+radioChecked+"-"+qidx)
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
	function isConditionMatchAnswers(responseDiv, condition, conditionValue, parentAnswer) {
		// if condition is not set, then it is a required question if its parents is displayed
		//alert ("ismatch "+isNaN(condition) +"-"+condition);
		var isMatched = isNaN(condition) || isConditionMatched(condition, conditionValue, parentAnswer);
		if (!isMatched && responseDiv.siblings('div[class^=Qresponsediv]').size() > 0) {
			responseDiv.siblings('div[class^=Qresponsediv]').each (
				function() {
					if (!isMatched) {
                        var answer = $(this).children().children().children().children().children().children('input').attr("value");
                       // alert("other answer ");
                        isMatched = isConditionMatched(condition, conditionValue, answer);
					}
                    
				}
			)
		}
		//alert ("ismatch "+isNaN(condition) +"-"+condition+"-"+isMatched);
		
		return isMatched;
		
	}
	
    /*
     * condition check for all the conditions implemented in this release 2.1.
     */
    function isConditionMatched(condition, conditionValue, parentAnswer) {

      /*
       * var responseArray = [ 'select', 'Contains text value', 'Matches text',
                 		'Less than number', 'Less than or equals number', 'Equals number',
                		'Greater than or equals number', 'Greater than number', 'Before date',
                		'After date' ];                 
       */
       
		var isMatched = false;
        if (condition == 1) {
          // contains text value
            //alert ("condition 1 "+parentAnswer+"-"+conditionValue+"-"+parentAnswer.indexOf(conditionValue));
            isMatched = ((parentAnswer.toUpperCase()).indexOf(conditionValue.toUpperCase()) >= 0);
        } else if (condition == 2) {
          // match text   
            isMatched = (conditionValue.toUpperCase() == parentAnswer.toUpperCase());
        } else if (condition >= 3 && condition <= 7) {
            if (isNaN(parentAnswer)) {
    		   alert("Value must be a number");
            } else {
            	isMatched = (condition == 3 && (Number(parentAnswer) < Number(conditionValue))) ||
            	            (condition == 4 && (Number(parentAnswer) <= Number(conditionValue))) ||
            	            (condition == 5 && (Number(parentAnswer) == Number(conditionValue))) ||
            	            (condition == 6 && (Number(parentAnswer) >= Number(conditionValue))) ||
            	            (condition == 7 && (Number(parentAnswer) > Number(conditionValue)));
            }    
    	} else if (condition > 7) {
    		//alert(condition)
        	if (!isDate(parentAnswer, 'MM/dd/yyyy')) {
    		    alert("Not a Valid Date (mm/dd/yyyy)");
        	} else {
        		isMatched = isDateMatched(parentAnswer, conditionValue, condition)
        	}
    	}	  

        return isMatched;	
	}


	/*
	 * check if date is either 'before date' or 'after date'
	 */
	function isDateMatched(parentAnswer, conditionValue, condition)
	 {
	     var dt1  = parentAnswer.substring(0,2);
	     var mon1 = parentAnswer.substring(3,5)-1;
	     var yr1  = parentAnswer.substring(6,10);
	     var dt2  = conditionValue.substring(0,2);
	     var mon2 = conditionValue.substring(3,5)-1;
	     var yr2  = conditionValue.substring(6,10);
	     var date1 = new Date(yr1, mon1, dt1);
	     var date2 = new Date(yr2, mon2, dt2);

		 return (condition == 8 && (date1 < date2)) ||
		            (condition == 9 && (date1 > date2));
	 }
		
