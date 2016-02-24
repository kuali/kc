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
	                		<kul:directInquiry boClassName="org.kuali.coeus.common.framework.person.KcPerson" inquiryParameters="admin_contact.identifier_${institutionalProposalContactRowStatus.index}:personId" anchor="${tabKey}" />		                	
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="admin_contact.orgNumber_${institutionalProposalContactRowStatus.index}" value="${institutionalProposalContact.organizationIdentifier}" />
							${institutionalProposalContact.unitAdministratorUnitNumberByPersonId}&nbsp;
							<kul:directInquiry boClassName="org.kuali.coeus.common.framework.unit.Unit" inquiryParameters="admin_contact.orgNumber_${institutionalProposalContactRowStatus.index}:unitNumber" anchor="${tabKey}" />
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
