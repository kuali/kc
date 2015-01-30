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

<script src="scripts/jquery/jquery.js"></script>
<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>
 
<kul:documentPage showDocumentInfo="true"
                  htmlFormAction="personMassChangeHome"
                  documentTypeName="PersonMassChangeDocument"
                  renderMultipart="false"
                  showTabButtons="true"
                  auditCount="0"
                  headerDispatch="${KualiForm.headerDispatch}"
                  headerTabActive="home">

<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-personmasschange:personMassChangePersonType />
<kra-personmasschange:personMassChangeReplace />

<kul:panelFooter />

<kul:documentControls transactionalDocument="true"
                      suppressRoutingControls="true"
                      extraButtonSource="${extraButtonSource}"
                      extraButtonProperty="${extraButtonProperty}"
                      extraButtonAlt="${extraButtonAlt}"
                      viewOnly="${KualiForm.editingMode['viewOnly']}" />

</kul:documentPage>
