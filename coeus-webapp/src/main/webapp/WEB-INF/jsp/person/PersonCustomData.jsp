<%--
 Copyright 2005-2013 The Kuali Foundation

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

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
<script type="text/javascript" src="scripts/jquery/CalendarPopup.js"></script>
<script>var jsContextPath = "${pageContext.request.contextPath}";</script>

<c:set var="readOnly" value="${KualiForm.document.documentHeader.workflowDocument.status.code eq 'F'}" scope="request" />

<kra-customdata:customDataTabSpecific
	customAttributeGroups="${KualiForm.document.newMaintainableObject.customDataHelper.customAttributeGroups}" 
	customDataList="${KualiForm.document.newMaintainableObject.businessObject.personCustomDataList}" 
	customDataListPrefix="document.newMaintainableObject.businessObject.personCustomDataList"
	headerAndFooter="false"/>