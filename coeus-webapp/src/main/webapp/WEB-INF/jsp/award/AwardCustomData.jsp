<%--
  ~ Copyright (c) 2014. Boston University
  ~
  ~ Licensed under the Educational Community License, Version 2.0 (the "License"); you may not use this
  ~ file except in compliance with the License. You may obtain a copy of the License at
  ~
  ~ http://www.opensource.org/licenses/ecl1.php
  ~
  ~ Unless required by applicable law or agreed to in writing, software distributed under the License is
  ~ distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND either express or
  ~ implied.
  ~
  ~ See the License for the specific language governing permissions and limitations under the License.
  --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp" %>

<%--<c:set var="readOnly" value="${!KualiForm.customDataHelper.modifyCustomData}" scope="request" />--%>
<c:set var="readOnly" value="false"
       scope="request"/><%--temp fix..Permissions have not been established for Award.  Shared tag files.--%>

<kul:documentPage
        showDocumentInfo="true"
        htmlFormAction="${KualiForm.actionName}CustomData"
        documentTypeName="${KualiForm.documentTypeName}"
        renderMultipart="false"
        showTabButtons="true"
        auditCount="0"
        headerDispatch="${KualiForm.headerDispatch}"
        headerTabActive="customData"
        extraTopButtons="${KualiForm.extraTopButtons}">

    <c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request"/>

    <div align="right">
    <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
    <kul:help documentTypeName="${KualiForm.documentTypeName}" pageName="BU Custom Data"/>
    </div>

    <div id="workarea">

        <kra-customdata:customDataTab/>
        <kra-a:awardBUCustom/>
        <kul:panelFooter/>

        <kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true"/>
    </div>

</kul:documentPage>
