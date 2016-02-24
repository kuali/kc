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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ page import="java.util.HashMap" %>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="coiDisclosure"
	documentTypeName="CoiDisclosureDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="disclosure">
  	
<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>
	<link rel="stylesheet" href="css/jquery/questionnaire.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
    <script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
    <script type="text/javascript" src="scripts/jquery/CalendarPopup.js"></script>



        <script type="text/javascript">
            var $j = jQuery.noConflict();
        	var proposalType;
        	var protocolType;
        	$j(document).ready(function() {
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
        			'autoscale' : 'false'
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
		   if ($j(".disclosedProjectsSubpanel").length > 0) {
		       $j(".disclosedProjectsSubpanel").toggle(
                        function()
                        {
                       	 var controlId = $j(this).attr("id");
                            var contentId = controlId.replace("Control","Content");
                            $j("#"+contentId).hide();
                            $j(this).html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                        },function(){
                            var controlId = $j(this).attr("id");
                            var contentId = controlId.replace("Control","Content");
                            $j("#"+contentId).slideDown(500);
                            $j(this).html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                        }
		       );
		       $j(".disclosedProjectsSubpanel").click();
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
        		                 //Project Id and Project Title are always shown and are required
        		                 $j("#newpEvent-table tr:eq(1)").show();
                                 $j("#newpEvent-table tr:eq(1) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.projectIdLabel + ":");
                                 $j("#newpEvent-table tr:eq(2)").show();
                                 $j("#newpEvent-table tr:eq(2) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.projectTitleLabel + ":");
               
        		                 if (data.disclosureEventType.useLongTextField1) {
                                     $j("#newpEvent-table tr:eq(3)").show();
                                     if (data.disclosureEventType.requireLongTextField1) {
                                         $j("#newpEvent-table tr:eq(3) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.longTextField1Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(3) th:eq(0) span").html(data.disclosureEventType.longTextField1Label + ":");
                                     }
        		                 } else {
                                     $j("#newpEvent-table tr:eq(3)").hide();
        		                 }
        		                 
                                 if (data.disclosureEventType.useShortTextField1) {
                                     $j("#newpEvent-table tr:eq(4)").show();
                                     if (data.disclosureEventType.requireShortTextField1) {
                                         $j("#newpEvent-table tr:eq(4) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.shortTextField1Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(4) th:eq(0) span").html(data.disclosureEventType.shortTextField1Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(4)").hide();
                                 }       
		                 
                                 if (data.disclosureEventType.useShortTextField3) {
                                     $j("#newpEvent-table tr:eq(5)").show();
                                     if (data.disclosureEventType.requireShortTextField3) {
                                         $j("#newpEvent-table tr:eq(5) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.shortTextField3Label + ":"); 
                                     } else {
                                        $j("#newpEvent-table tr:eq(5) th:eq(0) span").html(data.disclosureEventType.shortTextField3Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(5)").hide();
                                 }
                                 
                                 if (data.disclosureEventType.useLongTextField2) {
                                     $j("#newpEvent-table tr:eq(6)").show();
                                     if (data.disclosureEventType.requireLongTextField2) {
                                         $j("#newpEvent-table tr:eq(6) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.longTextField2Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(6) th:eq(0) span").html(data.disclosureEventType.longTextField2Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(6)").hide();
                                 } 
                                 
                                 if (data.disclosureEventType.useShortTextField2) {
                                     $j("#newpEvent-table tr:eq(7)").show();
                                     if (data.disclosureEventType.requireShortTextField2) {
                                         $j("#newpEvent-table tr:eq(7) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.shortTextField2Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(7) th:eq(0) span").html(data.disclosureEventType.shortTextField2Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(7)").hide();
                                 }
                                 
                                 if (data.disclosureEventType.useNumberField1) {
                                     $j("#newpEvent-table tr:eq(8)").show();
                                     if (data.disclosureEventType.requireNumberField1) {
                                         $j("#newpEvent-table tr:eq(8) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.numberField1Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(8) th:eq(0) span").html(data.disclosureEventType.numberField1Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(8)").hide();                              	 
                                 }
                                 
                                 if (data.disclosureEventType.useLongTextField3) {
                                     $j("#newpEvent-table tr:eq(9)").show();
                                     if (data.disclosureEventType.requireLongTextField3) {
                                         $j("#newpEvent-table tr:eq(9) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.longTextField3Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(9) th:eq(0) span").html(data.disclosureEventType.longTextField3Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(9)").hide();                                 
                                 }
                                 
                                 if (data.disclosureEventType.useNumberField2) {
                                     $j("#newpEvent-table tr:eq(10)").show();
                                     if (data.disclosureEventType.requireNumberField2) {
                                         $j("#newpEvent-table tr:eq(10) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.numberField2Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(10) th:eq(0) span").html(data.disclosureEventType.numberField2Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(10)").hide();                           
                                 }
                                 
                                 if (data.disclosureEventType.useDateField1) {
                                     $j("#newpEvent-table tr:eq(11)").show();
                                     if (data.disclosureEventType.requireDateField1) {
                                         $j("#newpEvent-table tr:eq(11) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.dateField1Label + ":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(11) th:eq(0) span").html(data.disclosureEventType.dateField1Label + ":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(11)").hide();
                                 }
                                 
                                 if (data.disclosureEventType.useDateField2) {
                                     $j("#newpEvent-table tr:eq(12)").show();
                                     if (data.disclosureEventType.requireDateField2) {
                                         $j("#newpEvent-table tr:eq(12) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.dateField2Label +":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(12) th:eq(0) span").html(data.disclosureEventType.dateField2Label +":");
                                     }
                                 } else {
                                     $j("#newpEvent-table tr:eq(12)").hide();                   
                                 }
                                 
                                 if (data.disclosureEventType.useSelectBox1) {
                                     $j("#newpEvent-table tr:eq(13)").show();
                                     if (data.disclosureEventType.requireSelectBox1) {
                                         $j("#newpEvent-table tr:eq(13) th:eq(0) span").html("*&nbsp;" + data.disclosureEventType.selectBox1Label +":");
                                     } else {
                                         $j("#newpEvent-table tr:eq(13) th:eq(0) span").html(data.disclosureEventType.selectBox1Label +":");
                                     }
                                     
                                     var mySelect = document.getElementById("selectBox1-placeholder");
                                     var mySelectValue = document.getElementById("disclosureHelper.newCoiDisclProject.selectBox1");
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
                                     $j("#newpEvent-table tr:eq(13)").hide();                                	 
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
                $j("#newpEvent-table tr:eq(12)").hide();                   
                $j("#newpEvent-table tr:eq(13)").hide();                   
            }
            
            function setSelectBox1Value(selectBox) {
            	var hiddenEl = document.getElementById("disclosureHelper.newCoiDisclProject.selectBox1");
            	hiddenEl.value = selectBox.value;
            }
        </script>
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />

<%-- --%>
<div align="right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiDisclosure1Help" altText="help"/></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />

<kra-coi:disclosureReporter />


<c:if test="${not( (KualiForm.document.coiDisclosureList[0].manualEvent) and (empty KualiForm.document.coiDisclosureList[0].coiDisclProjects) and (not KualiForm.document.coiDisclosureList[0].updateEvent))}" >
<kra-coi:disclosureQuestionnaire />
<script type="text/javascript" src="scripts/questionnaireAnswer.js"></script>
</c:if>
 
<c:if test="${KualiForm.document.coiDisclosureList[0].awardEvent || KualiForm.document.coiDisclosureList[0].proposalEvent || KualiForm.document.coiDisclosureList[0].institutionalProposalEvent || KualiForm.document.coiDisclosureList[0].protocolEvent || KualiForm.document.coiDisclosureList[0].iacucProtocolEvent}" >
	<kul:tab defaultOpen="false" tabTitle="Project & Financial Entity Relationships" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="document.coiDisclosureList[0].coiDisclEventProjects*" useRiceAuditMode="true"
	       tabErrorKey="document.coiDisclosureList[0].coiDisclEventProjects*" >
		<div class="tab-container" align="center">
    		<kra-coi:genericFinancialEntity idx="0" disclProject="${KualiForm.document.coiDisclosureList[0].coiDisclProjects[0]}" 
    			boLocation="document.coiDisclosureList[0].coiDisclProjects[0]"/>
    	</div>
    </kul:tab>
</c:if>

<c:if test="${KualiForm.document.coiDisclosureList[0].annualEvent and not KualiForm.document.coiDisclosureList[0].annualUpdate}" >
<kra-coi:allUnDisclosedProjects />
</c:if>


<%-- <c:if test="${KualiForm.document.coiDisclosureList[0].eventTypeCode=='11' or KualiForm.document.coiDisclosureList[0].eventTypeCode=='12' or KualiForm.document.coiDisclosureList[0].eventTypeCode=='13'}" > --%>
<c:if test="${KualiForm.document.coiDisclosureList[0].manualEvent}" >
	<kra-coi:manualProjects />
</c:if>

<c:if test="${KualiForm.document.coiDisclosureList[0].updateEvent or (KualiForm.document.coiDisclosureList[0].annualEvent and KualiForm.document.coiDisclosureList[0].annualUpdate)}" >
	<kra-coi:allDisclosedProjects/>
</c:if>

<kra-coi:coiNotifications/>

<kra-coi:coiNoteAndAttachment/>

<kra-coi:coiCertification topTab="false" />

<c:if test="${KualiForm.document.coiDisclosure.disclosureSaved}">
	<kra:dataValidation auditActivated="${KualiForm.auditActivated}" topTab="false"/>
</c:if>

<kul:panelFooter />
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		/>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;

</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
</kul:documentPage>
