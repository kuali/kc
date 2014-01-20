<%--
 Copyright 2005-2014 The Kuali Foundation
 
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

<c:set var="institutionalProposalUnitContactAttributes" value="${DataDictionary.InstitutionalProposalUnitContact.attributes}" />
<c:set var="institutionalProposalContactAttributes" value="${DataDictionary.InstitutionalProposalContact.attributes}" />
<c:set var="institutionalProposal" value="${KualiForm.document.institutionalProposal}" />
 
<%-- kra:section permission="modifyInstitutionalProposal" --%>
<kul:tab defaultOpen="false" tabItemCount="${KualiForm.unitContactsBean.unitContactsCount}" 
				tabTitle="Unit Contacts" tabErrorKey="unitContactsBean.newInstitutionalProposalContact.*,unitContactsBean.unitContact.unitAdministratorTypeCode,document.institutionalProposalList[0].institutionalProposalUnitContacts*" >
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Unit Contacts</span>
			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalUnitContact" altText="help"/></span>
		</h3>
	    <table id="contacts-table" cellpadding="0" cellspacing="0" summary="Unit Contacts">
			<tr>
				<th scope="row" width="5%">&nbsp;</th>
				<th width="15%">*Person</th>
				<th width="15%">Unit</th>
				<th width="20%">*Project Role</th>
				<th width="15%">Office Phone</th>
				<th width="15%">Email</th>
				<th width="15%"><div align="center">Actions</div></th>
			</tr>
			
			<c:if test="${!readOnly}">
			<tr>
				<th class="infoline" scope="row">Add</th>
				<td nowrap class="grid" class="infoline">
					<kul:htmlControlAttribute property="unitContactsBean.newInstitutionalProposalContact.fullName" 
      							attributeEntry="${institutionalProposalContactAttributes.fullName}" readOnly="true"/>
      				<label>
      					<kul:lookup boClassName="org.kuali.kra.bo.KcPerson" fieldConversions="personId:unitContactsBean.personId" anchor="${tabKey}" 
 									lookupParameters="unitContactsBean.personId:personId"/>
 					</label>
        		</td>
	        	<td class="infoline">
	        		<%--<div align="center">
	        			<c:out value="${KualiForm.unitContactsBean.newInstitutionalProposalContact.unitAdministratorUnitNumberByPersonId}" />&nbsp;
	        		</div>--%>
	        		&nbsp;
				</td>
	        	<td class="infoline" style="font-size: 80%">
	        		<div align="center">
		        		<kul:htmlControlAttribute property="unitContactsBean.unitContact.unitAdministratorTypeCode" 
	                									attributeEntry="${institutionalProposalUnitContactAttributes.unitAdministratorTypeCode}" />
					</div>
	        	</td>
	        	<td class="infoline">
	        		<c:out value="${KualiForm.unitContactsBean.newInstitutionalProposalContact.contact.phoneNumber}" />&nbsp;
	        	</td>
	        	<td class="infoline">
	        		<c:out value="${KualiForm.unitContactsBean.newInstitutionalProposalContact.contact.emailAddress}" />&nbsp;
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<html:image property="methodToCall.addUnitContact" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Contact" alt="Add Contact" styleClass="tinybutton" />
	        		</div>
	        	</td>
			</tr>
			</c:if>
				
			<c:forEach var="institutionalProposalContact" items="${KualiForm.unitContactsBean.unitContacts}" varStatus="institutionalProposalContactRowStatus">
				<tr>
					<th class="infoline" scope="row">
						<c:out value="${institutionalProposalContactRowStatus.index + 1}" />
					</th>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="unit_contact.identifier_${institutionalProposalContactRowStatus.index}" value="${institutionalProposalContact.contact.identifier}" />
	                		${institutionalProposalContact.fullName}&nbsp;
	                		<kul:directInquiry boClassName="org.kuali.kra.bo.KcPerson" inquiryParameters="unit_contact.identifier_${institutionalProposalContactRowStatus.index}:personId" anchor="${tabKey}" />		                	
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="unit_contact.orgNumber_${institutionalProposalContactRowStatus.index}" value="${institutionalProposalContact.organizationIdentifier}" />
							${institutionalProposalContact.unitAdministratorUnitNumberByPersonId}&nbsp;
							<kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="unit_contact.orgNumber_${institutionalProposalContactRowStatus.index}:unitNumber" anchor="${tabKey}" />
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<kul:htmlControlAttribute property="unitContactsBean.unitContacts[${institutionalProposalContactRowStatus.index}].unitAdministratorTypeCode" 
	                                                  attributeEntry="${institutionalProposalUnitContactAttributes.unitAdministratorTypeCode}" 
                                                      readOnlyAlternateDisplay="${institutionalProposalContact.unitAdministratorType.description}" />
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
	                
					<td>
						<div align="center">
						  <c:if test="${!readOnly}">
							 <html:image property="methodToCall.deleteUnitContact.line${institutionalProposalContactRowStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton" />
						  </c:if>
						  <c:if test="${readOnly}">&nbsp;</c:if>
						</div>
	                </td>
	            </tr>
    		</c:forEach>
    		<tr>
            	<th colspan="7" align="center" scope="row">
            		<div align="center">
            		  <c:if test="${!readOnly}">
	         			<html:image property="methodToCall.syncDefaultUnitContactsToLeadUnit.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-syncall.gif' styleClass="tinybutton"/>
					   </c:if>
					   <c:if test="${readOnly}">&nbsp;</c:if>
					</div>
	         	</th>
			</tr>	    	
    	</table>
	</div>
</kul:tab>
