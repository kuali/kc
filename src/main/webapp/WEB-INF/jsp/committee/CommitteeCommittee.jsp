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

<c:set var="committeeAttributes" value="${DataDictionary.CommitteeDocument.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="committeeCommittee"
	documentTypeName="CommitteeDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="committee">
  	
<div align="right"><kul:help documentTypeName="CommitteeDocument" pageName="Committee" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-committee:committee />
<kra-committee:committeeResearchAreas />

<kul:panelFooter />
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="false"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>



<script src="scripts/jquery/jquery.js"></script>
<script type="text/javascript">
   var $j = jQuery.noConflict();
   $j(document).ready(function () {
       $j('#document\\.committeeList\\[0\\]\\.committeeTypeCode').change(function() 
          {
             changeCommitteeType($j(this).val());
        });
        // fire change event onload
        $j('#coireview').hide();
        $j('#irbreview').show();
        changeCommitteeType($j('#document\\.committeeList\\[0\\]\\.committeeTypeCode').val());
   }); // end document.ready
   
   function changeCommitteeType(typeCode) {
              if (typeCode == '2') {
                 // COI type
                    $j('#document\\.committeeList\\[0\\]\\.maxProtocols').attr("value",0);
                    $j('#document\\.committeeList\\[0\\]\\.maxProtocols').hide();
                    $j('#maxProtocolsLabel').hide();
                    $j('#tab-AreaofResearch-div').prev().hide();
                    if ($j("input[name^='tabStates(AreaofResearch)']").attr("value") == 'OPEN') {
                         $j('#tab-AreaofResearch-div').hide();
                    }
                    $j('#coireview').show();
                    $j('#irbreview').hide();
                    $j('#document\\.committeeList\\[0\\]\\.reviewTypeCode').attr("value",'');                    
              }
              if (typeCode == '1') {
                  // IRB type
                    $j('#document\\.committeeList\\[0\\]\\.maxProtocols').show();
                    $j('#maxProtocolsLabel').show();
                    if ($j("input[name^='tabStates(AreaofResearch)']").attr("value") == 'OPEN') {
                        $j('#tab-AreaofResearch-div').show();
                    }
                    $j('#tab-AreaofResearch-div').prev().show();
                    $j('#coireview').hide();
                    $j('#irbreview').show();
                    $j('#document\\.committeeList\\[0\\]\\.coiReviewTypeCode').attr("value",'');                    
              }   
              
   }
   
</script>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>

<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/UnitService.js"></script>
<script>
loadUnitNameTo('document.committeeList[0].homeUnitNumber','document.committee.homeUnitName');
</script>
</kul:documentPage>
