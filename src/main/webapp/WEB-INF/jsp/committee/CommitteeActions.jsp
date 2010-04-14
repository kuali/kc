<%--
 Copyright 2006-2010 The Kuali Foundation

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

<c:set var="readOnly" value="${!KualiForm.committeeHelper.modifyCommittee}"  scope="request" />

<kul:documentPage 
    showDocumentInfo="true"
    htmlFormAction="committeeActions" 
    documentTypeName="CommitteeDocument"
    renderMultipart="false" 
    showTabButtons="true" 
    auditCount="0"
    headerDispatch="${KualiForm.headerDispatch}"
    headerTabActive="committeeActions">

    <kra-committee:committeeActionBatchCorrespondence />
    <kra-committee:committeeActionPrint />

    <kul:documentControls 
        transactionalDocument="false"
        suppressRoutingControls="false"
        extraButtonSource="${extraButtonSource}"
        extraButtonProperty="${extraButtonProperty}"
        extraButtonAlt="${extraButtonAlt}"
        viewOnly="${KualiForm.editingMode['viewOnly']}" />

    <script type="text/javascript">
        var kualiForm = document.forms['KualiForm'];
        var kualiElements = kualiForm.elements;
    </script>

    <script language="javascript" src="scripts/kuali_application.js"></script>
    <script language="javascript" src="dwr/interface/UnitService.js"></script>
</kul:documentPage>