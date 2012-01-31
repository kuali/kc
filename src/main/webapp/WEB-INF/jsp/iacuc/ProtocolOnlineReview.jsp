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

<script src="scripts/jquery/jquery.js"></script>
<script type="text/javascript">
    var $j = jQuery.noConflict();
    
    function checkForUnprocessedComments(id) {
    	var commentValue = document.getElementById(id).value;
    	if (commentValue && commentValue.length > 0) {
    		return confirm("You have unsaved changes in your comments section, do you wish to proceed anyway?");
    	} else {
    		return true;
    	}
    }
</script>




<style type="text/css">
   .compare { color: #666666 }
   .compare td, .compare th { color:#666666; }
</style>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucProtocolOnlineReview"
	documentTypeName="IacucProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="iacucProtocolOnlineReview">

Iacuc online review to be implemented

</kul:documentPage>
