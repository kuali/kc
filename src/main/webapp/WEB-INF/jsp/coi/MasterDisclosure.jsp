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
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="coiDisclosure"
	documentTypeName="CoiDisclosureDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="disclosure"
  	>
	<link type="text/css" rel="stylesheet" href="krad/plugins/fancybox/jquery.fancybox-1.3.4.css"></link>
    <script type="text/javascript" src="krad/plugins/fancybox/jquery.fancybox-1.3.4.pack.js"></script>     

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
//        		 updateTable($j("#disclosureHelper\\.newCoiDisclProject\\.disclosureEventType"));
        		 
        		 
                 
        	}) // end document ready
        	

        </script>
 

  	
<%-- --%>
<c:set var="readOnly" value="true" scope="request"/>
<div align="right"><kul:help documentTypeName="CoiDisclosureDocument" pageName="CoiDisclosure" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-coi:disclosureReporter />
<kra-coi:masterAnnualQuestionnaires />                    
<c:set var="masterDisclosure" value="${KualiForm.disclosureHelper.masterDisclosureBean}" />
<c:if test="${fn:length(masterDisclosure.manualAwardProjects) > 0 or fn:length(masterDisclosure.manualProposalProjects) > 0 or  fn:length(masterDisclosure.manualProtocolProjects) > 0 or  fn:length(masterDisclosure.manualTravelProjects) > 0}" >
<kul:tab defaultOpen="false" tabTitle="Manual Projects" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="${auditErrorKey}" useRiceAuditMode="true"
    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
    <div class="tab-container" align="center">
              

    <c:if test="${fn:length(masterDisclosure.manualAwardProjects) > 0}" >
        <kra-coi:masterManualProject masterDisclosureProjects="${masterDisclosure.manualAwardProjects}" projectDivNamePrefix="masterManualAwardFE" projectListName="manualAwardProjects"/>
    </c:if>
    <c:if test="${fn:length(masterDisclosure.manualProposalProjects) > 0}" >
        <kra-coi:masterManualProject masterDisclosureProjects="${masterDisclosure.manualProposalProjects}" projectDivNamePrefix="masterManualProposalFE" projectListName="manualProposalProjects"/>
    </c:if>
    <c:if test="${fn:length(masterDisclosure.manualProtocolProjects) > 0}" >
        <kra-coi:masterManualProject masterDisclosureProjects="${masterDisclosure.manualProtocolProjects}" projectDivNamePrefix="masterManualProtocolFE" projectListName="manualProtocolProjects"/>
    </c:if>
    <c:if test="${fn:length(masterDisclosure.manualTravelProjects) > 0}" >
        <kra-coi:masterManualProject masterDisclosureProjects="${masterDisclosure.manualTravelProjects}" projectDivNamePrefix="masterManualTravelFE" projectListName="manualTravelProjects"/>
    </c:if>
       </div>
</kul:tab>
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

<kra-coi:coiNoteAndAttachment/>

<c:if test="${fn:length(masterDisclosure.allProjects) > 0}" >
    <%-- <kra-coi:proposalProjects /> --%>
    <kra-coi:masterDisclosures />
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
