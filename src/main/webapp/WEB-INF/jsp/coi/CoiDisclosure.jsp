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
        <link rel="stylesheet" type="text/css" href="scripts/jquery/fancybox/jquery.fancybox-1.3.4.css"" media="screen"/>    
        <script type="text/javascript">
            var $j = jQuery.noConflict();
            // Fancybox calculates the div sizes wrong in Chrome and some other browsers for some reason 
            //and the grey background image ends up being a tiny bit bigger
            // this makes Rice JS expand the page, which makes Fancybox try to expand the grey image to cover the entire page and this happens in a loop 
            // and causes the dropbox to move downward. To prevent this, remove the background grey image completely in Chrome. This also required a modification
            // of Fancybox code in order to disable dropshadow.
        	$j.fancybox.setup({ dropshadow : false, overlayShow : false });  
        	$j(document).ready(function() {
        		$j("a.disclosureFeHistory").fancybox({ 
        			'width':400,
        			'height':200,
        			'type':'iframe',
        			'autoScale':'false'
        			            		
        		});
        		
	             $j("input[class^=selectDisclClass]").click(function() {
		            var idx= $j(this).attr("class").substring(16);
		            if ($j(this).is(":checked"))
		            {
			            //show the hidden div
			            $j("#div_FinancialEntity"+idx).show("fast");
		            }
		            else
		            {
			            //otherwise, hide it
			            $j("#div_FinancialEntity"+idx).hide("fast");
		            }
				 
                 });
	             $j("input[class^=selectDisclClass]").each(function() {
		            var idx= $j(this).attr("class").substring(16);
		            if ($j(this).is(":checked"))
		            {
			            //show the hidden div
			            $j("#div_FinancialEntity"+idx).show("fast");
		            }
		            else
		            {
			            //otherwise, hide it
			            $j("#div_FinancialEntity"+idx).hide("fast");
		            }
				 
                 });
                 
        		
        	})
        </script>
 
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="coiDisclosure"
	documentTypeName="CoiDisclosureDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="disclosure">
  	
<%-- --%>
<div align="right"><kul:help documentTypeName="CoiDisclosureDocument" pageName="CoiDisclosure" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-coi:disclosureReporter />
<c:if test="${KualiForm.document.coiDisclosureList[0].moduleCode=='1'}" >
    <kra-coi:awardProjects />
</c:if>
<c:if test="${KualiForm.document.coiDisclosureList[0].moduleCode=='11'}" >
    <kra-coi:proposalProjects />
</c:if>
<c:if test="${KualiForm.document.coiDisclosureList[0].moduleCode=='12'}" >
    <kra-coi:protocolProjects />
</c:if>
<c:if test="${KualiForm.document.coiDisclosureList[0].moduleCode=='13'}" >
<kra-coi:disclosureFinancialEntities />
</c:if>
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
