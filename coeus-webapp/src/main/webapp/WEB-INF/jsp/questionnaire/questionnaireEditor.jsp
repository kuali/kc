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

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
<script type="text/javascript" src="scripts/jquery/CalendarPopup.js"></script>
<script>var jsContextPath = "${pageContext.request.contextPath}";</script>


<kul:tab defaultOpen="true" tabTitle="Questionnaire Details, Content & Use "
    tabErrorKey="document.newMaintainableObject.businessObject*">
    <c:choose>
      <c:when test = "${KualiForm.document.newMaintainableObject.maintenanceAction eq 'Copy' and !(KualiForm.document.documentHeader.workflowDocument.status.code eq 'F')}">
        <kra-questionnaire:questionnaireMaintCopy />      
      </c:when>
      <c:otherwise>
    <kra-questionnaire:questionnaireMaintCore />
    <kra-questionnaire:questionnaireQuestion />
    <kra-questionnaire:questionnaireMaintUsage />
      </c:otherwise>
     </c:choose>
</kul:tab>
    <input type="hidden" id="maintAction" name="maintAction" value = "${KualiForm.document.newMaintainableObject.maintenanceAction}"/>
     <input type="hidden" id="docStatus" name="docStatus" value="${KualiForm.document.documentHeader.workflowDocument.status.code }"  />   
     <input type="hidden" id="readOnly" name="readOnly" value="${KualiForm.readOnly}"  />   

<script type="text/javascript" src="scripts/questionnaireMaint.js"></script>





</script>
