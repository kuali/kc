<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<c:set var="institutionalProposalUnitAdministratorAttributes" value="${DataDictionary.InstitutionalProposalUnitAdministrator.attributes}" />
<c:set var="personAttributes" value="${DataDictionary.Person.attributes}" />
<c:set var="unitAdministratorTypeAttributes" value="${DataDictionary.UnitAdministratorType.attributes}" />

<c:set var="tabItemCount" value="0" />
<c:forEach var="institutionalProposalUnitAdministrator" items="${KualiForm.document.institutionalProposal.institutionalProposalUnitAdministrators}" varStatus="status">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
</c:forEach>
<kul:tab tabTitle="Unit Administrators" tabItemCount="${tabItemCount}" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Unit Administrators</span>
			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnitAdministrator" altText="help"/></span>
		</h3>
	    <table id="contacts-table" cellpadding="0" cellspacing="0" summary="Unit Administrators">
			<tr>
				<th scope="row" width="5%">&nbsp;</th>
				<th width="25%">Name</th>
				<th width="20%">Admin Type</th>
				<th width="10%">Unit</th>
				<th width="15%">Phone</th>
				<th width="25%">Email</th>
			</tr>
				
			<c:forEach var="institutionalProposalUnitAdministrator" items="${KualiForm.document.institutionalProposal.institutionalProposalUnitAdministrators}" varStatus="propRowStatus">
				<tr>
					<th class="infoline" scope="row">
						<c:out value="${propRowStatus.index + 1}" />
					</th>
	                <td valign="left">	      
	                		${KualiForm.document.institutionalProposal.institutionalProposalUnitAdministrators[propRowStatus.index].person.fullName}&nbsp;						
					</td>
	                <td valign="middle">
	                <div align="center">
	                	<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalUnitAdministrators[${propRowStatus.index}].unitAdministratorTypeCode" attributeEntry="${institutionalProposalUnitAdministratorAttributes.unitAdministratorTypeCode}" />
	                </div>
					</td>
					<td valign="middle">	                	               	
							${KualiForm.document.institutionalProposal.institutionalProposalUnitAdministrators[propRowStatus.index].person.homeUnitRef.unitName}&nbsp;						 
					</td>
	                <td valign="middle">	                	
	                		${KualiForm.document.institutionalProposal.institutionalProposalUnitAdministrators[propRowStatus.index].person.officePhone}&nbsp;	                	
					</td>
					<td valign="middle">						
	                		${KualiForm.document.institutionalProposal.institutionalProposalUnitAdministrators[propRowStatus.index].person.emailAddress}&nbsp;	                	 
					</td>
	            </tr>
    		</c:forEach>	    	
    	</table>
	</div>
</kul:tab>
