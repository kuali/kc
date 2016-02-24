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
	   if (element.value == 'Y') {
		   $j("#alternate-search-div").show();
	   } else {
		   $j("#alternate-search-div").hide();
	   }
   }

   function addOtherDatabase() {
	   var inputField = $j("#otherAltSearchDatabase");
	   var inputVal = inputField.val().trim();
	   
	   if (inputVal != '') {
		   var newDatabaseField = document.getElementsByName('iacucAlternateSearchHelper.newDatabases')[0];
		   var opt = new Option(inputVal, inputVal);
		   opt.selected = true;
		   newDatabaseField.options[newDatabaseField.length] = opt;
		   inputField.val('');
	   } else {
		   window.alert('Please enter a valid other database name.');
	   }
   }
   
   $j(document).ready(function() {
		var selectEn = document.getElementsByName("document.protocolList[0].iacucPrinciples[0].searchRequired");
	    alternateSearchRequired(selectEn[0]);
	    
	    var options = {
	        button_select: "#move_right",
	        button_deselect: "#move_left"
        };
        $j("#iacucAlternateSearchHelper\\.newAlternateSearch\\.databases").multiSelect("#new-databases-select", options);	    
   });   
</script>

	<div align="right">
        <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
        <kul:help documentTypeName="IacucProtocolDocument" pageName="The Three R's" />
    </div>

<kra-iacuc:iacucProtocolPrinciples 
    protocolPrinciples="${protocolPrinciples}" />
<kra-iacuc:iacucProtocolAlternateSearch
    protocolAlternateSearch="${protocolAlternateSearch}"
    modifyPermissions="${KualiForm.iacucAlternateSearchHelper.modifyPermissions}" />   

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
