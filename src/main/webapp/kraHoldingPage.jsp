<%--
 Copyright 2005-2013 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<script language="JavaScript" type="text/javascript" src="../../../krad/plugins/blockUI/jquery.blockUI.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery.js"></script> 
<script type="text/javascript">
    var jq = jQuery.noConflict();
    jq(document).ready(function(){
    	top.jQuery.unblockUI();
    }
	);
</script>

<meta http-equiv="refresh" content="5">
        
<kul:page showDocumentInfo="false"
          headerTitle="Kuali Transactional Document Postprocessing Holding Page"
          docTitle="Kuali Transactional Document Postprocessing Holding Page"
          transactionalDocument="false"
          htmlFormAction="kraHoldingPage"
          defaultMethodToCall="returnToPortal">
          
    <div class="topblurb">
        <div align="center">
	        <table cellpadding="10" cellspacing="0" border="0">
	            <tr>
	                <td>
	                    <div align="center">
	                        The document is being processed. You will be returned to the document once processing is complete. 
	                        You can also return to the main menu by clicking below.
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td>
	                    <div align="center">
	                        <input type="image" name="methodToCall.returnToPortal" title="Return to Portal" alt="Return to Portal" 
	                               src="${ConfigProperties.kra.externalizable.images.url}buttonsmall_returnToPortal.gif" class="tinybutton" />
	                    </div>
	                </td>
	            </tr>
	        </table>
	    </div>
    </div>
    
</kul:page>