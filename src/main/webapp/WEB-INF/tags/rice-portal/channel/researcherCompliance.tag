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
        <script type="text/javascript" src="scripts/jquery/jquery.fancybox-1.3.4.pack.js"></script>
        <link rel="stylesheet" type="text/css" href="scripts/jquery/fancybox/jquery.fancybox-1.3.4.css"" media="screen"/>
        <script type="text/javascript">
            var $j = jQuery.noConflict();
        	$j(document).ready(function() {
        		$j("#FB_manual_coi_help_control").fancybox();
        	})
        </script>

<channel:portalChannelTop channelTitle="Compliance" />
<div class="body">
  <ul class="chan">
    <li><portal:portalLink displayTitle="false" title="Create Disclosure" url="coiDisclosure.do?methodToCall=docHandler&command=initiate&docTypeName=CoiDisclosureDocument">Create Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="Financial Entity" url="financialEntityManagement.do?methodToCall=management">Financial Entity</portal:portalLink></li>
    <li>Review Final Entities</li>
    <li>Pending Disclosures</li>
    <li>All my Disclosures</li>
    <li><portal:portalLink displayTitle="false" title="Create Manual Disclosure - temporary link" url="coiDisclosure.do?methodToCall=docHandler&command=initiate_11&docTypeName=CoiDisclosureDocument">Create Manual Disclosure - temp link<a id="FB_manual_coi_help_control" href="#FB_manual_coi_help_div" title="Coi Manual Disclosure"><img src="${ConfigProperties.kr.externalizable.images.url}my_cp_inf.gif" alt="[Help]${altText}" hspace=5 border=0  align="middle"></a></portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="New Protocol Disclosure - temporary link" url="coiDisclosure.do?methodToCall=docHandler&command=initiate_12&docTypeName=CoiDisclosureDocument">New Protocol Disclosure - temp link</portal:portalLink></li>

  </ul>
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
