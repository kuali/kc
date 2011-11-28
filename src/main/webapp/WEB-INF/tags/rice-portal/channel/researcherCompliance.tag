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
        <script type="text/javascript" src="scripts/jquery/jquery.js"></script> 
         <script type="text/javascript" src="scripts/jquery/jquery.fancybox-1.3.4jh.js"></script>
        <link rel="stylesheet" type="text/css" href="scripts/jquery/fancybox/jquery.fancybox-1.3.4.css"" media="screen"/>    
        <script type="text/javascript">
            var $j = jQuery.noConflict();
        	$j(document).ready(function() {
        		$j("#FB_manual_coi_help_control").fancybox();
        	})
        </script>

<channel:portalChannelTop channelTitle="Conflict of Interest" />
<div class="body">
  <strong>My Financial Entities </strong>
  <ul class="chan">
    <li><portal:portalLink displayTitle="false" title="Create New Financial Entity" url="financialEntityManagement.do?methodToCall=management&financialEntityHelper.reporterId=&coiDocId=">Financial Entity</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="View/Edit Financial Entities" url="financialEntityManagement.do?methodToCall=editList&financialEntityHelper.reporterId=&coiDocId=">View/Edit Financial Entities</portal:portalLink></li>
  </ul>
  <strong>My Disclosures </strong>
  <ul class="chan">
    <li><portal:portalLink displayTitle="false" title="Create Annual Disclosure" url="coiDisclosure.do?methodToCall=docHandler&command=initiate&docTypeName=CoiDisclosureDocument">Create Annual Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="Create Manual Disclosure" url="coiDisclosure.do?methodToCall=docHandler&command=initiate_14&docTypeName=CoiDisclosureDocument">Create Manual Disclosure<a id="FB_manual_coi_help_control" href="#FB_manual_coi_help_div" title="Coi Manual Disclosure">
      <img src="${ConfigProperties.kra.externalizable.images.url}questionmark.jpg" alt="[Help]${altText}" style="border:none; width:16px; height:16px; vertical-align:middle;"></a></portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="New Award Disclosure" url="awardEventDisclosure.do?methodToCall=getNewAwardsForDisclosure">New Award Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="New Proposal Disclosure " url="proposalEventDisclosure.do?methodToCall=getNewProposalsForDisclosure">New Proposal Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="New Protocol Disclosure" url="protocolEventDisclosure.do?methodToCall=getNewProtocolsForDisclosure">New Protocol Disclosure</portal:portalLink></li>
    <li>View/Edit Disclosure</li>
  </ul>
  <!--
  <strong>Reporter Disclosure/FE </strong>
  <ul class="chan">
    <li>Financial Entity</li>
    <li>Disclosure</li>
    <li>View/Edit Disclosure</li>
  </ul>
  -->
</div>
    <div style="display: none;">
          <div id="FB_manual_coi_help_div" style="overflow:auto;">
          <p>
          To be used only for non-system generated project disclosures. e.g.,  </br>
          Proposal or Award is not in the system.
          </P
          </div>
    </div>      
<channel:portalChannelBottom />
