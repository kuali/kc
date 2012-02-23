<%--
 Copyright 2005-2010 The Kuali Foundation

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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>



        <script type="text/javascript" src="scripts/jquery/jquery.js"></script> 
         <script type="text/javascript" src="scripts/jquery/jquery.fancybox-1.3.4jh.js"></script>
        <link rel="stylesheet" type="text/css" href="scripts/jquery/fancybox/jquery.fancybox-1.3.4.css" media="screen"/>    
       	<style type="text/css">div#fancybox-wrap {top:100px !important;}</style>
        <script type="text/javascript">
            var $j = jQuery.noConflict();
            // Fancybox calculates the div sizes wrong in Chrome and some other browsers for some reason 
            //and the grey background image ends up being a tiny bit bigger
            // this makes Rice JS expand the page, which makes Fancybox try to expand the grey image to cover the entire page and this happens in a loop 
            // and causes the dropbox to move downward. To prevent this, remove the background grey image completely in Chrome. This also required a modification
            // of Fancybox code in order to disable dropshadow.
        	$j.fancybox.setup({ dropshadow : true, overlayShow : true });  
        	var proposalType;
        	var protocolType;
        	$j(document).ready(function() {
        	   // $j("#fancybox-wrap").css('top', '100px !important');
        		$j("a.disclosureFeHistory").fancybox({ 
        			'width':400,
        			'height':200,
        			'type':'iframe',
        			'autoScale':'false'
        			            		
        		});
        		$j("a.disclosureFeView").fancybox({
        			'width' : 553,
        			'height': 500,
        			'type' : 'iframe',
        			'autoscale' : 'false',
        		});
        		
        		           if ($j(".financialEntitySubpanel").length > 0) {
               $j(".financialEntitySubpanel").toggle(
                        function()
                        {
                            var controlId = $j(this).attr("id");
                            var contentId = controlId.replace("Control","Content");
                            $j("#"+contentId).slideDown(500);
                            $j(this).html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                        },function(){
                            var controlId = $j(this).attr("id");
                            var contentId = controlId.replace("Control","Content");
                            $j("#"+contentId).slideUp(500);
                            $j(this).html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                        }
               );
               $j(".financialEntitySubpanel").click();
               
           }
        	                
    
                 //populate form
        		 handleEventType($j("#disclosureHelper\\.newCoiDisclProject\\.disclosureEventType"));
        	}) // end document ready

           function handleEventType(eventType) {
        	   var eventTypeValue = $j(eventType).attr("value");
               if (eventTypeValue == "") {
            	   hideProjectFields();
               } else {
        	       $j.getJSON("coiDisclosure.do?methodToCall=getDisclosureEventTypeInfo&eventType=" + eventTypeValue,
        			         function(data) {
        		                 //alert("JSON data: " + data.useShortTextField1);
               
        		                 if (data.disclosureEventType.useLongTextField1) {
                                     $j("#newpEvent-table tr:eq(1)").show();
                                     if (data.disclosureEventType.requireLongTextField1) {
                                         $j("#newpEvent-table tr:eq(1) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.longTextField1Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(1) th:eq(0) span").html(data.disclosureEventType.longTextField1Label + ":");
                                     }
        		                 } else {
                                     $j("#newpEvent-table tr:eq(1)").hide();
        		                 }
        		                 
                                 if (data.disclosureEventType.useShortTextField1) {
                                     $j("#newpEvent-table tr:eq(2)").show();
                                     if (data.disclosureEventType.requireShortTextField1) {
                                         $j("#newpEvent-table tr:eq(2) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.shortTextField1Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(2) th:eq(0) span").html(data.disclosureEventType.shortTextField1Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(2)").hide();
                                 }       
		                 
                                 if (data.disclosureEventType.useShortTextField3) {
                                     $j("#newpEvent-table tr:eq(3)").show();
                                     if (data.disclosureEventType.requireShortTextField3) {
                                         $j("#newpEvent-table tr:eq(3) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.shortTextField3Label + ":"); 
                                     } else {
                                        $j("#newpEvent-table tr:eq(3) th:eq(0) span").html(data.disclosureEventType.shortTextField3Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(3)").hide();
                                 }
                                 
                                 if (data.disclosureEventType.useLongTextField2) {
                                     $j("#newpEvent-table tr:eq(4)").show();
                                     if (data.disclosureEventType.requireLongTextField2) {
                                         $j("#newpEvent-table tr:eq(4) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.longTextField2Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(4) th:eq(0) span").html(data.disclosureEventType.longTextField2Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(4)").hide();
                                 } 
                                 
                                 if (data.disclosureEventType.useShortTextField2) {
                                     $j("#newpEvent-table tr:eq(5)").show();
                                     if (data.disclosureEventType.requireShortTextField2) {
                                         $j("#newpEvent-table tr:eq(5) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.shortTextField2Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(5) th:eq(0) span").html(data.disclosureEventType.shortTextField2Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(5)").hide();
                                 }
                                 
                                 if (data.disclosureEventType.useNumberField1) {
                                     $j("#newpEvent-table tr:eq(6)").show();
                                     if (data.disclosureEventType.requireNumberField1) {
                                         $j("#newpEvent-table tr:eq(6) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.numberField1Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(6) th:eq(0) span").html(data.disclosureEventType.numberField1Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(6)").hide();                              	 
                                 }
                                 
                                 if (data.disclosureEventType.longTextField3) {
                                     $j("#newpEvent-table tr:eq(7)").show();
                                     if (data.disclosureEventType.requireLongTextField3) {
                                         $j("#newpEvent-table tr:eq(7) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.longTextField3Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(7) th:eq(0) span").html(data.disclosureEventType.longTextField3Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(7)").hide();                                 
                                 }
                                 
                                 if (data.disclosureEventType.useNumberField2) {
                                     $j("#newpEvent-table tr:eq(8)").show();
                                     if (data.disclosureEventType.requireNumberField2) {
                                         $j("#newpEvent-table tr:eq(8) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.numberField2Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(8) th:eq(0) span").html(data.disclosureEventType.numberField2Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(8)").hide();                           
                                 }
                                 
                                 if (data.disclosureEventType.longTextField3) {
                                     $j("#newpEvent-table tr:eq(9)").show();
                                     if (data.disclosureEventType.requireLongTextField3) {
                                         $j("#newpEvent-table tr:eq(9) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.dateField1Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(9) th:eq(0) span").html(data.disclosureEventType.dateField1Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(9)").hide();
                                 }
                                 
                                 if (data.disclosureEventType.useNumberField2) {
                                     $j("#newpEvent-table tr:eq(10)").show();
                                     if (data.disclosureEventType.requireNumberField2) {
                                         $j("#newpEvent-table tr:eq(10) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.dateField2Label +":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(10) th:eq(0) span").html(data.disclosureEventType.dateField2Label +":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(10)").hide();                   
                                 }
                                 
                                 if (data.disclosureEventType.useSelectBox1) {
                                     $j("#newpEvent-table tr:eq(11)").show();
                                     if (data.disclosureEventType.requireSelectBox1) {
                                         $j("#newpEvent-table tr:eq(11) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.selectBox1Label +":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(11) th:eq(0) span").html(data.disclosureEventType.selectBox1Label +":");
                                     }
                                     
                                     var mySelectValue = document.getElementById("selectBox1-placeholder");
                                     var mySelect = document.getElementById("disclosureHelper.newCoiDisclProject.selectBox1");
                                     mySelect.options.length = 0;
                                     
                                     mySelect.options[mySelect.length]=new Option("select", "", true, false);
                                     for (var i = 0; i < data.keyValues.length; i++) {
                                       if (mySelectValue == data.keyValues[i].key) {
                                           mySelect.options[mySelect.length]=new Option(data.keyValues[i].value, data.keyValues[i].key, false, true);
                                       } else {
                                           mySelect.options[mySelect.length]=new Option(data.keyValues[i].value, data.keyValues[i].key);
                                       }
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(11)").hide();                                	 
                                 }
        	                 }
        	             );
               }
           }
        	
            function hideProjectFields() {
                $j("#newpEvent-table tr:eq(1)").hide();                   
                $j("#newpEvent-table tr:eq(2)").hide();                   
                $j("#newpEvent-table tr:eq(3)").hide();                   
                $j("#newpEvent-table tr:eq(4)").hide();                   
                $j("#newpEvent-table tr:eq(5)").hide();                   
                $j("#newpEvent-table tr:eq(6)").hide();                   
                $j("#newpEvent-table tr:eq(7)").hide();                   
                $j("#newpEvent-table tr:eq(8)").hide();                   
                $j("#newpEvent-table tr:eq(9)").hide();                   
                $j("#newpEvent-table tr:eq(10)").hide();
                $j("#newpEvent-table tr:eq(11)").hide();                   
            }
        </script>
 
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="coiDisclosure"
	documentTypeName="CoiDisclosureDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="disclosure">
  	
<%-- --%>
<div align="right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiDisclosureHelp" altText="help"/></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-coi:disclosureReporter />
<c:if test="${KualiForm.document.coiDisclosureList[0].eventTypeCode=='1'}" >
    <%-- <kra-coi:awardProjects /> --%>
    <kra-coi:newAwardFinancialEntities />
</c:if>
<c:if test="${KualiForm.document.coiDisclosureList[0].eventTypeCode=='2'}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:newProposalFinancialEntities />
</c:if>
<c:if test="${KualiForm.document.coiDisclosureList[0].eventTypeCode=='10'}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:newInstitutionalProposalFinancialEntities />
</c:if>
<c:if test="${KualiForm.document.coiDisclosureList[0].eventTypeCode=='3'}" >
   <%-- <kra-coi:protocolProjects /> --%>
    <kra-coi:newProtocolFinancialEntities />
</c:if>
<c:if test="${KualiForm.document.coiDisclosureList[0].eventTypeCode=='14'}" >
<kra-coi:disclosureFinancialEntities />
</c:if>
<c:if test="${KualiForm.document.coiDisclosureList[0].eventTypeCode=='11' or KualiForm.document.coiDisclosureList[0].eventTypeCode=='12' or KualiForm.document.coiDisclosureList[0].eventTypeCode=='13'}" >
<kra-coi:manualProjects />
</c:if>

<kra-coi:coiNoteAndAttachment/>

<kra-coi:coiCertification topTab="false" />

<kul:panelFooter />
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;

</SCRIPT>

</kul:documentPage>
