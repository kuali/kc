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

<kul:tab tabTitle="Print" defaultOpen="false" tabErrorKey="print*">
    
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Print</span>
    	    <span class="subhead-right"><kul:help parameterNamespace="KC-PD" parameterDetailType="Document" parameterName="proposalDevelopmentPrintFormsHelpUrl" altText="help"/></span>
        </h3>
        <kra-pd:proposalDevelopmentActionsPrintForms />
        <kra-pd:printProposalSponsorForms />
	    <kra:printReports requestUri="/proposalDevelopmentActions.do?docNum=${KualiForm.docId}&documentWebScope=session&formKey=${KualiForm.formKey}&docFormKey=${KualiForm.formKey}"/>
    </div> 
</kul:tab>
