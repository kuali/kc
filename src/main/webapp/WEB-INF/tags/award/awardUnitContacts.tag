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
<%-- member of AwardContacts.jsp --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardUnitContactAttributes" value="${DataDictionary.AwardUnitContact.attributes}" />
<c:set var="award" value="${KualiForm.document.award}" />
 
<%-- kra:section permission="modifyAward" --%>
<kul:tab defaultOpen="false" tabItemCount="${KualiForm.unitContactsBean.unitContactsCount}" 
				tabTitle="Unit Contacts" tabErrorKey="unitContactsBean.newAwardContact,document.awardList[0].awardUnitContacts*" >
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Unit Contacts</span>
			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.AwardUnitContact" altText="help"/></span>
		</h3>
	    <table id="contacts-table" cellpadding="0" cellspacing="0" summary="Unit Contacts">
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
					<c:choose>                  
						<c:when test="${empty KualiForm.unitContactsBean.newAwardContact.contact.identifier}">
							<div align="center">
	        					<input type="text" size="20" value="" readonly="true"/>
	              				<label>
	              					<kul:lookup boClassName="org.kuali.kra.bo.Person" fieldConversions="personId:unitContactsBean.personId" anchor="${tabKey}" 
		  	 									lookupParameters="unitContactsBean.personId:personId"/>
		  	 					</label>
		  	 				</div>
						</c:when>
						<c:otherwise>
							<div align="center">
	              				<label><kul:htmlControlAttribute property="unitContactsBean.newAwardContact.fullName" 
	              							attributeEntry="${awardUnitContactAttributes.fullName}" readOnly="true"/></label>
																				            			
							</div>
						</c:otherwise>
					</c:choose>
        		</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.unitContactsBean.newAwardContact.contactOrganizationName}" />&nbsp;
	        		</div>
				</td>
	        	<td class="infoline" style="font-size: 80%">
	        		<div align="center">
		        		<kul:htmlControlAttribute property="unitContactsBean.contactRoleCode" 
	                									attributeEntry="${awardUnitContactAttributes.contactRoleCode}" />
					</div>
	        	</td>
	        	<td class="infoline">
	        		<c:out value="${KualiForm.unitContactsBean.newAwardContact.contact.phoneNumber}" />&nbsp;
	        	</td>
	        	<td class="infoline">
	        		<c:out value="${KualiForm.unitContactsBean.newAwardContact.contact.emailAddress}" />&nbsp;
	        	</td>
	        	<td class="infoline">
	        		<c:choose>
		        		<c:when test="${not empty KualiForm.unitContactsBean.newAwardContact.contact.identifier}">
			        		<div align="center">	        			
			        			<html:image property="methodToCall.addUnitContact" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Contact" alt="Add Contact" styleClass="tinybutton" />
			        			<html:image property="methodToCall.clearNewUnitContact" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Fields" alt="Clear Fields" styleClass="tinybutton" />
			        		</div>
			        	</c:when>
			        	<c:otherwise>&nbsp;</c:otherwise>
			        </c:choose>
	        	</td>
			</tr>
				
			<c:forEach var="awardContact" items="${KualiForm.unitContactsBean.unitContacts}" varStatus="awardContactRowStatus">
				<tr>
					<th class="infoline" scope="row">
						<c:out value="${awardContactRowStatus.index + 1}" />
					</th>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="unit_contact.identifier_${awardContactRowStatus.index}" value="${awardContact.contact.identifier}" />
	                		${awardContact.fullName}&nbsp;
	                		<kul:directInquiry boClassName="org.kuali.kra.bo.Person" inquiryParameters="unit_contact.identifier_${awardContactRowStatus.index}:personId" anchor="${tabKey}" />		                	
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="unit_contact.orgNumber_${awardContactRowStatus.index}" value="${awardContact.organizationIdentifier}" />
							${awardContact.contactOrganizationName}&nbsp;
							<kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="unit_contact.orgNumber_${awardContactRowStatus.index}:unitNumber" anchor="${tabKey}" />
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<kul:htmlControlAttribute property="unitContactsBean.unitContacts[${awardContactRowStatus.index}].contactRoleCode" 
	                									attributeEntry="${awardUnitContactAttributes.contactRoleCode}" />
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
						<c:set var="isLeadUnit" value="${award.leadUnit != null && awardContact.person.homeUnit == award.leadUnit.unitNumber}" />
						<c:if test="${not isLeadUnit}">
							<c:set var="deleteButton" value="tinybutton-delete1.gif" />
						</c:if>
						<c:if test="${isLeadUnit}">
							<c:set var="deleteButton" value="tinybutton-delete2.gif" />
						</c:if>
						<div align="center">
							<html:image property="methodToCall.deleteUnitContact.line${awardContactRowStatus.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}${deleteButton}' styleClass="tinybutton" disabled="${isLeadUnit}"/>
						</div>
	                </td>
	            </tr>
    		</c:forEach>	    	
    	</table>
	</div>
</kul:tab>