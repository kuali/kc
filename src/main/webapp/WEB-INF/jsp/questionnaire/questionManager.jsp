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
<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>

<c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/>

<kul:tab tabTitle="Question Manager" 
         defaultOpen="true" 
         useCurrentTabIndexAsKey="true"
         tabErrorKey="document.newMaintainableObject.*">
    <kra-questionnaire:questionManagerQuestion />
    <kra-questionnaire:questionManagerResponse />
</kul:tab>

     <input type="hidden" id="docStatus" name="docStatus" value="${KualiForm.document.documentHeader.workflowDocument.status.code }"  />   
     <input type="hidden" id="readOnly" name="readOnly" value="${KualiForm.readOnly}"  />   

<script language="javascript">
        $j(document).ready(function(){
           if ($j("#readOnly").attr("value") == 'true' && $j("#docStatus").attr("value") == 'I') {    
       // option 1 : simply hide it
               $j("#tab-RouteLog-div").hide();
               $j("#tab-RouteLog-div").prev().hide();
       
       // option 2 : display message on route log tab
       /*
                var routemsg = $j('<div class="tab-container">').attr("id", "noroutelog");
        
                var tbltmp = $j('<table width="100%" width="100%" cellpadding="0" cellspacing="0" class="datatable" />');
 
                var tbodytmp = $j('<tbody/>');
                var tr1 = $j('<tr></tr>');
                var td1 = $j('<td class="subelementcontent"></td>');
                td1.html("Route log is not available for Bootstrap data");
                tr1.html(td1);
                tbodytmp.html(tr1);
                tbltmp.html(tbodytmp);
                routemsg.html(tbltmp);
                routemsg.insertAfter($j("#tab-RouteLog-div").children('div:eq(0)'));
        
                $j("#tab-RouteLog-div").children('div:eq(0)').hide();
            */
           }

        });

 </script>

