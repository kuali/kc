<%--
 Copyright 2005-2014 The Kuali Foundation

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
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="protocolOnlineReviewInactive"
	documentTypeName="ProtocolOnlineReviewDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="onlineReview">
<script type="text/javascript">
    var $j = jQuery.noConflict();
</script>




<c:set var="protocolAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="onlineReviewAttributes" value = "${DataDictionary.ProtocolOnlineReview.attributes}"/>
<c:set var="protocolReviewerAttributes" value="${DataDictionary.ProtocolReviewerBean.attributes}" />

<c:set var="readOnly" value = "true"/>

<style type="text/css">
   .compare { color: #666666 }
   .compare td, .compare th { color:#666666; }
</style>




	<kul:documentOverview editingMode="${KualiForm.editingMode}" />
	
	<kul:tab tabTitle="Protocol Online Review " defaultOpen="true" tabErrorKey="" >
	
	
	 <table cellpadding="0" cellspacing="0" class="datatable" title="view/edit document overview information" summary="view/edit document overview information">
				<tr>
                	<th>
	                    <div align="center">
	                    <div align="left">
	                     
	                        Online Review can be edited/viewed only if the following conditions are met :
	                        <font color = "red"><p> 
	                        <li> The protocol submission status is 'Submitted to committee' or 'In Agenda'. </li>	                        
	                        <li>You must be an Irb Admin or assigned reviewer.</li>
	                        </p></font>
	                        
	                        </div>
	                    </div>
                	</th>
              </tr>
         	</table>
	
	</kul:tab>

	<kul:routeLog />

<kul:panelFooter />
<%-- <kul:panelFooter /> --%>
	<kul:documentControls 
		transactionalDocument="true"
		suppressRoutingControls="false"
		suppressCancelButton="false"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="true"
		/>


<script language="javascript">
//enableJavaScript()

$j(document).ready(function() {

	$j("#globalbuttons").find('input').each(function() {
             // alert($j(this).attr("name"));
              if ($j(this).attr("name") != 'methodToCall.close') {
            	  $j(this).hide();
              } 
          });

    });

</script>

</kul:documentPage>
