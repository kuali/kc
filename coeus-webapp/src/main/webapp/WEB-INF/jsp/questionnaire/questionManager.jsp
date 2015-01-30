<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

