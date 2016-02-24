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

<kul:page lookup="false" 
          docTitle="New Projects For Disclosure" 
          transactionalDocument="false"
          renderMultipart="false"
          showTabButtons="true"
          htmlFormAction="projectEventDisclosure">
          
    <script language="javascript" src="scripts/kuali_application.js"></script>
    
    <div id="workarea">

<kra-coi:proposalEventListing topOfStack="true" />
<kra-coi:institutionalProposalEventListing />
<kra-coi:awardEventListing />
<kra-coi:protocolEventListing />
<kra-coi:iacucEventListing />

<kul:panelFooter />

<div id="globalbuttons" class="globalbuttons">
    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" styleClass="globalbuttons" property="methodToCall.close" title="close" alt="close"/>
</div>

<script type="text/javascript">
   var $j = jQuery.noConflict();
   $j(document).ready(function () {
        $j('#horz-links').hide();
   }); // end document.ready
</script>

</kul:page>
