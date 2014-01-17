<%--
 Copyright 2005-2014 The Kuali Foundation

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

<c:set var="budgetedGoalsAttributes" value="${DataDictionary.AwardSubcontractingBudgetedGoals.attributes}" />

<html:hidden property="containingUnsavedChanges" styleId="containingUnsavedChanges" />

<div class="tab-container" align="center" id="awardNumberLookupPanel">
	<h3>
		<span class="subhead-left">
			Award Number Lookup
		</span> 
		<span class="subhead-right">
			<kul:help businessObjectClassName="org.kuali.kra.award.subcontracting.AwardSubcontractingBudgetedGoals" altText="help" />
		</span>
	</h3>
	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="infoline">
				<div align="center">
					<kul:htmlAttributeLabel	attributeEntry="${budgetedGoalsAttributes.awardNumber}" />
					<input type="hidden" name="subPlanFlag" value="Y">
					<kul:htmlControlAttribute property="awardNumber" attributeEntry="${budgetedGoalsAttributes.awardNumber}"  />
					<span id="awardLookupButton">
						<kul:lookup boClassName="org.kuali.kra.award.home.Award" fieldConversions="awardNumber:awardNumber,awardId:awardId" lookupParameters="subPlanFlag:subPlanFlag" anchor="${tabKey}" />
					</span>
					<html:hidden property="awardId" />
					<kul:directInquiry boClassName="org.kuali.kra.award.home.Award" inquiryParameters="awardId:awardId" anchor="${tabKey}" />
				</div>
			</td>
		</tr>
	</table>
</div>


<script type="text/javascript">
   
    // this flag indicates form data has changed, and is set either when the user makes changes to the text fields, or 
    // based on the yes/no value of a form field used to persist the unsaved changes indicator across 
    // multiple unsuccessful saves (due to validation errors).
    var changed = false;
    // this flag is used by save and recalculate to submit even if 
    // there are changes (since the changes wont be lost in these cases)
    var override = false;
    // this flag is used to decide if we need to evaluate if the 'recalculate' button needs to appear
    var recalculateTotal = false;
    
    var $j = jQuery.noConflict();

	var timeout;
    
    // this is bound to onbeforeunload 
    function warnAboutUnsavedChanges() {
        if(changed && !(override)) {
            timeout = setTimeout(function() {
                createLoading(false);
            }, 100);
           return "You have unsaved changes!";
        }
    }
    
    function noTimeout() {
        clearTimeout(timeout);
    }

    // this will set the changed flag and also disable the award number lookup
    // if it has not already been done
    function setChangedAndSuppressAwardNumberLookup() {
    	if(!changed) {
	    	changed = true;
	   		$j("#awardNumber").prop("readOnly", true);
	   		$j("#awardLookupButton").hide();
	   		$j("#awardNumber").focus(function() {
	   			this.blur();
	   			alert("Please finalize modified data using save, clear or reload in order to re-enable lookup");
	   		});
    	}
    }
    
 	// shows the 'recalculate' button if the users change any of the fields involved in computing the large/small business totals  
    function checkIfRecalculateTotalNeeded(changedInputId) {    
		if(    (changedInputId == "awardSubcontractingBudgetedGoals.smallBusinessGoalAmount")
			|| (changedInputId == "awardSubcontractingBudgetedGoals.largeBusinessGoalAmount")
		   ) {
			$j("#recalculateBusinessTotals").show();
			recalculateTotal = true;
		}
    }
    
    // on ready
   	$j(function(){
	   	window.onbeforeunload = warnAboutUnsavedChanges;
	   	window.unload = noTimeout;

	   	// check for the hidden form field used to persist unsaved data indicator across unsuccessful save submissions
	   	if($j("#containingUnsavedChanges").prop("value") == "Yes") {
	   		setChangedAndSuppressAwardNumberLookup();
	   	}

	   	// hide the 'recalculate' button on initial load
	   	$j("#recalculateBusinessTotals").hide();
	   	
	   	// capture any change events from the form's text boxes
	    $j("input, textarea").change(function() {
	    	var changedInputId = $j(this).prop("id");	    	
	    	if(changedInputId != "awardNumber") {
	    		setChangedAndSuppressAwardNumberLookup();
	    		// set the hidden form field to persist unsaved data indicator across submissions
	    		$j("#containingUnsavedChanges").val("Yes");	    		
	    		if(!recalculateTotal) {
	    			checkIfRecalculateTotalNeeded(changedInputId);
	    		}
			}
	    	else {
	    		// the change is in the lookup award number, so just submit the form to 'refresh'
	    		submitFormToMethod('kualiForm', 'refresh');
	    	}
		});
	    
	    
		$j("#recalculateBusinessTotals").click(function() {
			override = true;			   
		});		
		
		
		$j("#save").click(function() {
			override = true;			   
		});
	  
   });

</script> 
