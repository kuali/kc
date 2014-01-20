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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="coiMasterDisclosure"
	documentTypeName="CoiDisclosureDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="disclosure">
  	

    <link rel="stylesheet" href="css/jquery/questionnaire.css" type="text/css" />
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
        		$j("a.viewNotification").fancybox({ 
        			'width':700,
        			'height':150,
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
        	
                 // hide protocol type list
                 protocolType = $j("#disclosureHelper\\.protocolType").html();
                 $j("#disclosureHelper\\.protocolType").hide();
                 proposalType=$j("#disclosureHelper\\.newCoiDisclProject\\.coiProjectType").html();
        	}) // end document ready
        	

        </script>
 

  	
<%-- --%>
<c:set var="readOnly" value="true" scope="request"/>
<div align="right"><kul:help documentTypeName="CoiDisclosureDocument" pageName="CoiDisclosure" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-coi:disclosureReporter />
<kra-coi:masterAnnualQuestionnaires />                    
<c:set var="masterDisclosure" value="${KualiForm.disclosureHelper.masterDisclosureBean}" />
<kra-coi:allDisclosedProjects/>
<kra-coi:coiNoteAndAttachment/>

<c:if test="${fn:length(masterDisclosure.allProjects) > 0}" >
    <kra-coi:masterDisclosures />
</c:if>
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
		viewOnly="true"
		/>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;

</SCRIPT>
<script type="text/javascript" src="scripts/questionnaireAnswer.js"></script>

</kul:documentPage>
