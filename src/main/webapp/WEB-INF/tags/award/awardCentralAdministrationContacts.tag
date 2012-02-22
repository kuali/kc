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
<%-- member of AwardContacts.jsp --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardCentralAdminAttributes" value="${DataDictionary.AwardCentralAdminContact.attributes}" />
<c:set var="awardContactAttributes" value="${DataDictionary.AwardContact.attributes}" />
<c:set var="award" value="${KualiForm.document.award}" />
 
<%-- kra:section permission="modifyAward" --%>
<kul:tab defaultOpen="false" tabItemCount="${fn:length(KualiForm.awardDocument.award.centralAdminContacts)}" 
				tabTitle="Central Administration Contacts" tabErrorKey="centralAdminContactsBean.newAwardContact,document.awardList[0].awardCentralAdminContacts*" >
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Central Administration Contacts</span>
	      	<span class="subhead-right"><kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardCentralAdminContactsHelpUrl" altText="help"/></span>
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
				
			<c:forEach var="awardContact" items="${KualiForm.awardDocument.award.centralAdminContacts}" varStatus="awardContactRowStatus">
				<tr>
					<th class="infoline" scope="row">
						<c:out value="${awardContactRowStatus.index + 1}" />
					</th>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="admin_contact.identifier_${awardContactRowStatus.index}" value="${awardContact.contact.identifier}" />
	                		${awardContact.fullName}&nbsp;
	                		<kul:directInquiry boClassName="org.kuali.kra.bo.KcPerson" inquiryParameters="admin_contact.identifier_${awardContactRowStatus.index}:personId" anchor="${tabKey}" />		                	
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="admin_contact.orgNumber_${awardContactRowStatus.index}" value="${awardContact.organizationIdentifier}" />
							${awardContact.unitAdministratorUnitNumberByPersonId}&nbsp;
							<kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="admin_contact.orgNumber_${awardContactRowStatus.index}:unitNumber" anchor="${tabKey}" />
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		${awardContact.unitAdministratorType.description}&nbsp;
	                	</div>
					</td>
					<td valign="middle">
						<div align="center">
	                		${awardContact.phoneNumber}&nbsp;
	                	</div> 
					</td>
	                <td valign="middle">
	                	<div align="center">                	
							${awardContact.emailAddress}&nbsp;
						</div> 
					</td>
	            </tr>
    		</c:forEach>	    	
    	</table>
	</div>
</kul:tab>
