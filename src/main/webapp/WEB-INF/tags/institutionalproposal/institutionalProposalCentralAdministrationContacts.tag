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
<%-- member of InstitutionalProposalContacts.jsp --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="institutionalProposalCentralAdminAttributes" value="${DataDictionary.InstitutionalProposalCentralAdminContact.attributes}" />
<c:set var="institutionalProposalContactAttributes" value="${DataDictionary.InstitutionalProposalContact.attributes}" />
<c:set var="institutionalProposal" value="${KualiForm.document.institutionalProposal}" />
 
<%-- kra:section permission="modifyInstitutionalProposal" --%>
<kul:tab defaultOpen="false" tabItemCount="${KualiForm.centralAdminContactsBean.centralAdminContactsCount}" 
				tabTitle="Central Administration Contacts" tabErrorKey="centralAdminContactsBean.newInstitutionalProposalContact,document.institutionalProposalList[0].institutionalProposalCentralAdminContacts*" >
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Central Administration Contacts</span>
  		<span class="subhead-right"><kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="centralAdministrationContactsHelpUrl" altText="help"/></span>
		</h3>
	    <table id="central-admin-table" cellpadding="0" cellspacing="0" summary="Central Admin Contacts">
			<tr>
				<th scope="row" width="5%">&nbsp;</th>
				<th width="20%">Person</th>
				<th width="15%">Unit</th>
				<th width="20%">Project Role</th>
				<th width="20%">Office Phone</th>
				<th width="20%">Email</th>
			</tr>
				
			<c:forEach var="institutionalProposalContact" items="${KualiForm.centralAdminContactsBean.centralAdminContacts}" varStatus="institutionalProposalContactRowStatus">
				<tr>
					<th class="infoline" scope="row">
						<c:out value="${institutionalProposalContactRowStatus.index + 1}" />
					</th>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="admin_contact.identifier_${institutionalProposalContactRowStatus.index}" value="${institutionalProposalContact.contact.identifier}" />
	                		${institutionalProposalContact.fullName}&nbsp;
	                		<kul:directInquiry boClassName="org.kuali.kra.bo.KcPerson" inquiryParameters="admin_contact.identifier_${institutionalProposalContactRowStatus.index}:personId" anchor="${tabKey}" />		                	
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="admin_contact.orgNumber_${institutionalProposalContactRowStatus.index}" value="${institutionalProposalContact.organizationIdentifier}" />
							${institutionalProposalContact.unitAdministratorUnitNumberByPersonId}&nbsp;
							<kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="admin_contact.orgNumber_${institutionalProposalContactRowStatus.index}:unitNumber" anchor="${tabKey}" />
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		${institutionalProposalContact.unitAdministratorType.description}&nbsp;
	                	</div>
					</td>
					<td valign="middle">
						<div align="center">
	                		${institutionalProposalContact.phoneNumber}&nbsp;
	                	</div> 
					</td>
	                <td valign="middle">
	                	<div align="center">                	
							${institutionalProposalContact.emailAddress}&nbsp;
						</div> 
					</td>
	            </tr>
    		</c:forEach>	    	
    	</table>
	</div>
</kul:tab>
