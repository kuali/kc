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
<%@ attribute name="idx" required="true" description="Coi disl project list index" %>
<%@ attribute name="disclProject" required="true" type="org.kuali.kra.coi.CoiDisclProject" %>

<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
                <div class="innerTab-container" align="left">
                    <table class=tab cellpadding="0" cellspacing="0" summary="">
                        <tbody>
                        <%-- Header --%>
                                 <tr>
                                    <th><div align="center">Award Number</div></th> 
                                    <th><div align="center">Award Title</div></th> 
                                    <th><div align="center">Award Date</div></th> 
                                    <th><div align="center">PI</div></th> 
                                </tr>
                        <%-- Header --%>
                        
                         <%-- New data --%>
                        <%-- kra:permission value="${KualiForm.disclosureHelper.modifyPersonnel}" --%>
                <tr>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${idx}].disclosureFlag" attributeEntry="${coiDisclProjectAttributes.disclosureFlag}" readOnly="${readOnly}" styleClass="selectDisclClass${idx}M" /> 
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${idx}].coiProjectId" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectId}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${idx}].coiProjectTitle" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectTitle}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${idx}].coiProjectStartDate" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectStartDate}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
				         ${KualiForm.document.coiDisclosureList[0].disclosurePersons[0].reporter.fullName}
					</div>
				  </td>
	            </tr>

                <tr>
                    <td colspan="4">
