<%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.KraProposalPrototype.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentAbstract"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  headerDispatch="save"
  headerTabActive="abstract">
  
  <html:hidden property="document.documentNumber" />
   <html:hidden property="document.documentHeader.versionNumber" />
   <html:hidden property="document.objectId" />
   <html:hidden property="document.documentHeader.documentNumber" />
   <html:hidden property="document.documentHeader.financialDocumentStatusCode" />
   <html:hidden property="document.documentHeader.objectId" />
   <html:hidden property="document.documentHeader.financialDocumentDescription" />

 	<kul:hiddenDocumentFields isTransactionalDocument="false" isFinancialDocument="true" excludePostingYear="true"/>

<center>Under Construction</center>

</kul:documentPage>