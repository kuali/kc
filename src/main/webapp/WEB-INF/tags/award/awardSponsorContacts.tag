<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="awardSponsorContactAttributes" value="${DataDictionary.AwardSponsorContact.attributes}" />
<c:set var="awardContactAttributes" value="${DataDictionary.AwardContact.attributes}" />

<%-- kra:section permission="modifyAward" --%>
<kul:tab defaultOpen="false" tabItemCount="${KualiForm.sponsorContactsBean.sponsorContactsCount}" 
				tabTitle="Sponsor Contacts" tabErrorKey="sponsorContactsBean.newAwardContact*,document.awardList[0].sponsorContacts*" >
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Sponsor Contacts</span>
			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.AwardSponsorContact" altText="help"/></span>
		</h3>
	    <table id="sponsor-contacts-table" cellpadding="0" cellspacing="0" summary="Sponsor Contacts">
			<tr>
				<th scope="row" width="5%">&nbsp;</th>
				<th width="15%">Person</th>
				<th width="15%">Unit</th>
				<th width="20%">Project Role</th>
				<th width="15%">Office Phone</th>
				<th width="15%">Email</th>
				<th width="15%"><div align="center">Actions</div></th>
			</tr>
			
			<tr>
				<th class="infoline" scope="row">Add</th>
				<td nowrap class="grid" class="infoline">
					<kul:htmlControlAttribute property="sponsorContactsBean.newAwardContact.rolodex.fullName" 
      								attributeEntry="${awardContactAttributes.fullName}" readOnly="false"/>
  					<label>
  						<kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" fieldConversions="rolodexId:sponsorContactsBean.rolodexId" 
  									anchor="${tabKey}" lookupParameters="sponsorContactsBean.rolodexId:rolodexId"/>
  					</label>					
        		</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.sponsorContactsBean.newAwardContact.contactOrganizationName}" />&nbsp;
	        		</div>
				</td>
	        	<td class="infoline" style="font-size: 80%">
	        		<div align="center">
		        		<kul:htmlControlAttribute property="sponsorContactsBean.contactRoleCode" 
	                									attributeEntry="${awardSponsorContactAttributes.contactRoleCode}" />
					</div>
	        	</td>
	        	<td class="infoline">
	        		<c:out value="${KualiForm.sponsorContactsBean.newAwardContact.contact.phoneNumber}" />&nbsp;
	        	</td>
	        	<td class="infoline">
	        		<c:out value="${KualiForm.sponsorContactsBean.newAwardContact.contact.emailAddress}" />&nbsp;
	        	</td>
	        	<td class="infoline">
	        		<div align="center">	        			
			        	<html:image property="methodToCall.addSponsorContact" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Contact" alt="Add Contact" styleClass="tinybutton" />
			        </div>
	        	</td>
			</tr>
				
			<c:forEach var="awardContact" items="${KualiForm.sponsorContactsBean.sponsorContacts}" varStatus="awardContactRowStatus">
				<tr>
					<th class="infoline" scope="row">
						<c:out value="${awardContactRowStatus.index + 1}" />
					</th>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="sponsor_contact.identifier_${awardContactRowStatus.index}" value="${awardContact.contact.identifier}" />
	                		${awardContact.fullName}&nbsp;
	                		<kul:directInquiry boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" inquiryParameters="sponsor_contact.identifier_${awardContactRowStatus.index}:rolodexId" anchor="${tabKey}" />		                	
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
							${awardContact.contactOrganizationName}&nbsp;
							<kul:directInquiry boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" inquiryParameters="sponsor_contact.identifier_${awardContactRowStatus.index}:rolodexId" anchor="${tabKey}" />
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<kul:htmlControlAttribute property="sponsorContactsBean.sponsorContacts[${awardContactRowStatus.index}].contactRoleCode" 
	                									attributeEntry="${awardSponsorContactAttributes.contactRoleCode}" />
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
	                
					<td>
						<div align="center">
							<html:image property="methodToCall.deleteSponsorContact.line${awardContactRowStatus.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton" />
						</div>
	                </td>
	            </tr>
    		</c:forEach>	    	
    	</table>
	</div>
</kul:tab>
