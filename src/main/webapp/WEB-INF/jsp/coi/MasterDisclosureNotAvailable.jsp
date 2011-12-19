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
        	$j(document).ready(function() {
        //	alert($j("#workarea table:first").length);
        	   $j("#workarea table:first").hide();
        	   $j("#tab-DocumentOverview-div").hide();
        	
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
  	

<div align="right"><kul:help documentTypeName="CoiDisclosureDocument" pageName="CoiDisclosure" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<%-- <kra-coi:disclosureReporter /> --%>

	<kul:tab tabTitle="Master Disclosure " defaultOpen="true" transparentBackground="true" tabErrorKey="" >
	
	<div>
	 <table cellpadding="0" cellspacing="0" class="datatable" title="view/edit document overview information" summary="view/edit document overview information">
				<tr>
                	<th align="center">
	                     
	                        No Approved disclosure yet.
	                        
                	</th>
              </tr>
         	</table>
	  </div>
	</kul:tab>

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

</kul:documentPage>
