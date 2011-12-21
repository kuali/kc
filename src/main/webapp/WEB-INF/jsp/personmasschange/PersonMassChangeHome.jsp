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
                  
<kra-personmasschange:personMassChangePersonType />
<kra-personmasschange:personMassChangeReplace />

<kul:panelFooter />

<kul:documentControls transactionalDocument="true"
                      suppressRoutingControls="false"
                      extraButtonSource="${extraButtonSource}"
                      extraButtonProperty="${extraButtonProperty}"
                      extraButtonAlt="${extraButtonAlt}"
                      viewOnly="${KualiForm.editingMode['viewOnly']}" />

</kul:documentPage>