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
        		
                 // hide protocol type list
                 protocolType = $j("#disclosureHelper\\.protocolType").html();
                 $j("#disclosureHelper\\.protocolType").hide();
                 proposalType=$j("#disclosureHelper\\.newCoiDisclProject\\.coiProjectType").html();
        		 updateTable($j("#disclosureHelper\\.newCoiDisclProject\\.disclosureEventType"));
        	}) // end document ready
        	

        </script>
 
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="coiDisclosure"
	documentTypeName="CoiDisclosureDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="disclosure"
  	>
  	
<%-- --%>
<div align="right"><kul:help documentTypeName="CoiDisclosureDocument" pageName="CoiDisclosure" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-coi:disclosureReporter />
<c:set var="masterDisclosure" value="${KualiForm.disclosureHelper.masterDisclosureBean}" />
<c:if test="${fn:length(masterDisclosure.manualAwardProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterManualAward masterDisclosureProjects="${masterDisclosure.manualAwardProjects}"/>
</c:if>
<c:if test="${fn:length(masterDisclosure.manualProposalProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterManualProposal masterDisclosureProjects="${masterDisclosure.manualProposalProjects}"/>
</c:if>
<c:if test="${fn:length(masterDisclosure.manualProtocolProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterManualProtocol masterDisclosureProjects="${masterDisclosure.manualProtocolProjects}"/>
</c:if>
<c:if test="${fn:length(masterDisclosure.awardProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterAward masterDisclosureProjects="${masterDisclosure.awardProjects}"/>
</c:if>
<c:if test="${fn:length(masterDisclosure.proposalProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterProposal masterDisclosureProjects="${masterDisclosure.proposalProjects}"/>
</c:if>
<c:if test="${fn:length(masterDisclosure.protocolProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterProtocol masterDisclosureProjects="${masterDisclosure.protocolProjects}"/>
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

</kul:documentPage>
