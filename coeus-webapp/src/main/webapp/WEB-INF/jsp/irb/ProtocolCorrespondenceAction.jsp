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


<c:set var="protocolAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="showActions" value="${empty DocumentPessimisticLockMessages}" scope="request"/>
<c:set var="suppressRoutingControls" value="${KualiForm.actionHelper.canApproveFull || !KualiForm.actionHelper.canApproveOther}" scope="request"/>
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request"/>


<kul:documentPage
    showDocumentInfo="true"
    htmlFormAction="protocolProtocolActions"
    documentTypeName="ProtocolDocument"
    renderMultipart="false"
    showTabButtons="true"
    auditCount="0"
    headerDispatch="${KualiForm.headerDispatch}"
    headerTabActive="protocolActions">
     
<div align="right"><kul:help documentTypeName="ProtocolDocument" pageName="Protocol Actions" /></div>
<kra-irb:manageCorrespondence />
<kul:panelFooter />
                <br/>
                <div class="globalbuttons" align="center">
                    <input type="image"  style="border: none;" name="methodToCall.saveCorrespondence" src="kr/static/images/buttonsmall_save.gif" tabindex="0" onclick="resetScrollPosition();" title="save" alt="save">
                    <input type="image"  style="border: none;" name="methodToCall.closeCorrespondence" src="kr/static/images/buttonsmall_close.gif" tabindex="0" title="close" alt="close">
                </div>
 <%--   <kul:documentControls 
        transactionalDocument="true"
        suppressRoutingControls="${suppressRoutingControls}"
        extraButtonSource="${extraButtonSource}"
        extraButtonProperty="${extraButtonProperty}"
        extraButtons="${extraButtons}"
        extraButtonAlt="${extraButtonAlt}"
        viewOnly="${KualiForm.editingMode['viewOnly']}"
        />
  --%>
<input id="javaScriptFlag" type="hidden" name="javaScriptEnabled" value="0" />
<script language="javascript" src="dwr/interface/ProtocolActionAjaxService.js"></script>

<script language="javascript">enableJavaScript()</script>

<script language="javascript">
   var $j = jQuery.noConflict();
     $j(document).ready(function(){
        $j("#globalbuttons").find('input').each(function() {
              //alert($j(this).attr("name"));
//              if ($j(this).attr("name") == 'methodToCall.reload') {
                  $j(this).hide();
/*
              } else if ($j(this).attr("name") == 'methodToCall.save') {
                  $j(this).attr("name", "methodToCall.saveCorrespondence");
              } else if ($j(this).attr("name") == 'methodToCall.sendNotification') {
                  $j(this).hide();
              } else if ($j(this).attr("name") == 'methodToCall.close') {
                  $j(this).hide();
              }
*/
          });

         $j(".tabul input[type^=submit]").each(function() {
             $j(this).attr("disabled","disabled");
         });


        }); // end document.ready

 </script>
    
 
</kul:documentPage>
