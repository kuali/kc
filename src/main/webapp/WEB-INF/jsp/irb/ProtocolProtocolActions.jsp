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
<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>
    <link rel="stylesheet" href="css/jquery/questionnaire.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
    <script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
    <script type="text/javascript" src="scripts/jquery/CalendarPopup.js"></script> 
    <script type="text/javascript" src="scripts/jquery/jquery.tablesorter.js"></script>     

<c:set var="protocolAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="showActions" value="${empty DocumentPessimisticLockMessages}" scope="request"/>
<c:set var="suppressRoutingControls" value="${KualiForm.actionHelper.canApproveFull || !KualiForm.actionHelper.canApproveOther}" scope="request"/>
<c:if test="${suppressRoutingControls && !empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS]}">
	<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request"/>
</c:if>	

<style type="text/css">
   .compare { color: #666666 }
   .compare td, .compare th { color:#666666; }
</style>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="protocolProtocolActions"
	documentTypeName="ProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="protocolActions">
  	
<div align="right"><kul:help documentTypeName="ProtocolDocument" pageName="Protocol Actions" /></div>
<kra-irb:protocolRequestAction />
<c:if test="${showActions}" >
<kra:dataValidation auditActivated="${KualiForm.auditActivated}" topTab="false" helpParameterNamespace="KC-PROTOCOL" helpParameterName="protocolDataValidationHelp" helpParameterDetailType="Document"/>
</c:if>
<kra-irb:protocolSummaryPrint/>
<kra-irb:protocolSummaryViewPrint/>
<kra-irb:protocolCopyProtocol />
<kul:routeLog /> 
<c:if test="${showActions}" >
<kul:adHocRecipients />
</c:if>
<kul:panelFooter />
	            
	<kul:documentControls 
		transactionalDocument="true"
		suppressRoutingControls="${suppressRoutingControls}"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		extraButtons="${extraButtons}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>

<input id="javaScriptFlag" type="hidden" name="javaScriptEnabled" value="0" />
<script language="javascript" src="dwr/interface/ProtocolActionAjaxService.js"></script>

<script language="javascript">enableJavaScript()</script>
<script type="text/javascript" src="scripts/questionnaireAnswer.js"></script>
    
<script language="javascript">
    		$j(document).ready(function(){

    		    $j(".printSubpanel").toggle(
    		            function()
    		            {
    		            	var controlId = $j(this).attr("id");
    		            	var contentId = controlId.replace("Control","Content");
    		                $j("#"+contentId).slideDown(500);
    		                $j(this).html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
    		            },function(){
    		            	var controlId = $j(this).attr("id");
    		            	var contentId = controlId.replace("Control","Content");
    		                $j("#"+contentId).slideUp(500);
    		                $j(this).html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
    		            }
    		        );
    		    $j(".printSubpanelContent").hide();       		


    		    $j(".printQnSubpanel").toggle(
    		            function()
    		            {
        		            var idx = $j(this).attr("id").substring(9);
        		           // alert(idx)
        		           // click to ajax load questionnaire for the first time
        		            $j("input[name=viewQnhistory"+idx+"]").click();
    		                $j("#"+$j(this).attr("id")+"Content").slideDown(500);
    		                $j(this).html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
    		            },function(){
    		                $j("#"+$j(this).attr("id")+"Content").slideUp(500);
    		                $j(this).html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
    		            }
    		        );
    		    $j(".printQnSubpanelContent").hide();  
    		    
    		    $j("#protocolActionPrint-protocolAttachment-table").tablesorter({         
                    // pass the headers argument and assign an object         
                       headers: {               // assign the first column (we start counting zero)             
                           5: {                 // disable it by setting the property sorter to false                 
                              sorter: false  },             
                          }
                      });   
    		    
                $j("#protocolActionPrint-personnelAttachment-table").tablesorter({         
                    // pass the headers argument and assing a object         
                       headers: {             // assign the first column (we start counting zero)             
                           5: {                 // disable it by setting the property sorter to false                 
                              sorter: false  },             
                          }
                      });   
                
                $j("#protocolActionSummary-protocolAttachment-table").tablesorter({         
                    // pass the headers argument and assing a object         
                       headers: {             // assign the first column (we start counting zero)             
                           0: {                 // disable it by setting the property sorter to false                 
                              sorter: false  },   
                           6: {
                        	  sorter: false  },
                          }
                      });    	
                      
               if ($j("#Csequence01").length >0)     {   
                  //$j("#Csequence01").click();
                  $j("#Dsequence01").hide()
                 // $j("#Csequence01").click();
                }      	    
		});

 </script>
 
</kul:documentPage>
