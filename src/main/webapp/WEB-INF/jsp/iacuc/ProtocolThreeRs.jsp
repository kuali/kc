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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolPrinciples" value="${DataDictionary.IacucPrinciples.attributes}" />
<c:set var="protocolAlternateSearch" value="${DataDictionary.IacucAlternateSearch.attributes}" />


<kul:documentPage
    showDocumentInfo="true"
    htmlFormAction="iacucProtocolThreeRs"
    documentTypeName="IacucProtocolDocument"
    renderMultipart="false"
    showTabButtons="true"
    auditCount="0"
    headerDispatch="${KualiForm.headerDispatch}"
    headerTabActive="threeRs">

<script type="text/javascript" src="scripts/jquery/jquery.multiselects.js" /></script>    
<script type="text/javascript">
   var $j = jQuery.noConflict();
   
   function alternateSearchRequired(element) {
	   if (element.value == 'No') {
		   $j("#alternate-search-div").hide();
	   } else {
		   $j("#alternate-search-div").show();
	   }
   }

   
   $j(document).ready(function() {
	    var selectEl = document.getElementById("iacucAlternateSearchHelper.newAlternateSearch.searchRequired");
	    alternateSearchRequired(selectEl);
	    
	    var options = {
	        button_select: "#move_right",
	        button_deselect: "#move_left"
        };
        $j("#iacucAlternateSearchHelper.newAlternateSearch.databases").multiSelect("#new-databases-select", options);	    
   });   
</script>

<kra-iacuc:iacucProtocolPrinciples 
    protocolPrinciples="${protocolPrinciples}" />
<kra-iacuc:iacucProtocolAlternateSearch
    protocolAlternateSearch="${protocolAlternateSearch}" />   

<kul:panelFooter />

    <kul:documentControls 
        transactionalDocument="false"
        suppressRoutingControls="true"
        extraButtonSource="${extraButtonSource}"
        extraButtonProperty="${extraButtonProperty}"
        extraButtonAlt="${extraButtonAlt}"
        viewOnly="${KualiForm.editingMode['viewOnly']}"
        />

</kul:documentPage>    