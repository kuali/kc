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
    <kra-questionnaire:questionManagerQuestionMultiChoice />
</kul:tab>

<script type="text/javascript">
    $j(document).ready(function() {
        showQuestionType();
    });
</script>

<script type="text/javascript" src="scripts/questionMaint.js"/>

     <input type="hidden" id="docStatus" name="docStatus" value="${KualiForm.document.documentHeader.workflowDocument.status.code }"  />   
     <input type="hidden" id="readOnly" name="readOnly" value="${KualiForm.readOnly}"  />   

<script language="javascript">
        $j(document).ready(function(){
           if ($j("#readOnly").attr("value") == 'true' && $j("#docStatus").attr("value") == 'I') {
               $j("#tab-RouteLog-div").hide();
               $j("#tab-RouteLog-div").prev().hide();
           }

        });

 </script>

