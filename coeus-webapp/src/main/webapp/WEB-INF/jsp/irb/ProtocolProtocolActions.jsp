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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="protocolProtocolActions"
	documentTypeName="ProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="protocolActions">
  	
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
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request"/>

<style type="text/css">
   .compare { color: #666666 }
   .compare td, .compare th { color:#666666; }
</style>
  	
<div align="right">
    <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
    <kul:help documentTypeName="ProtocolDocument" pageName="Protocol Actions" />
</div>
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
<kul:superUserActions showTab="false"/>	
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
                              sorter: false  }             
                          }
                      });   
    		    
                $j("#protocolActionPrint-personnelAttachment-table").tablesorter({         
                    // pass the headers argument and assing a object         
                       headers: {             // assign the first column (we start counting zero)             
                           5: {                 // disable it by setting the property sorter to false                 
                              sorter: false  }             
                          }
                      });   
                
                $j("#protocolActionSummary-protocolAttachment-table").tablesorter({         
                    // pass the headers argument and assing a object         
                       headers: {             // assign the first column (we start counting zero)             
                           0: {                 // disable it by setting the property sorter to false                 
                              sorter: false  },   
                           6: {
                        	  sorter: false  }
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
