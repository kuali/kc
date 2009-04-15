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

<c:set var="awardContactAttributes" value="${DataDictionary.AwardContact.attributes}" />

<%-- kra:section permission="modifyAward" --%>
<kul:tab defaultOpen="false" tabItemCount="${KualiForm.sponsorContactsBean.sponsorContactsCount}" 
				tabTitle="Sponsor Contacts" tabErrorKey="newAwardContact*,document.awardList[0].awardContactsCount*" >
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Sponsor Contacts</span>
			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.AwardContact" altText="help"/></span>
		</h3>
	    <table id="contacts-table" cellpadding="0" cellspacing="0" summary="Unit Contacts">
			<tr>
				<th scope="row" width="5%">&nbsp;</th>
				<th width="15%">Person</th>
				<th width="15%">Organization</th>
				<th width="20%">Project Role</th>
				<th width="15%">Office Phone</th>
				<th width="15%">Email</th>
				<th width="15%"><div align="center">Actions</div></th>
			</tr>
			
			<tr>
				<th class="infoline" scope="row">Add</th>
				<td nowrap class="grid" class="infoline">
					<c:choose>                  
						<c:when test="${empty KualiForm.sponsorContactsBean.newAwardContact.contact.identifier}">
							<div>
								<input type="text" size="20" value="" readonly="true"/>
	          					<label>
	          						<kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" fieldConversions="rolodexId:sponsorContactsBean.rolodexId" 
	          									anchor="${tabKey}" lookupParameters="sponsorContactsBean.rolodexId:rolodexId"/>
	          					</label>
	          				</div>                 	
						</c:when>
						<c:otherwise>
							<div align="center">
	              				<label><kul:htmlControlAttribute property="sponsorContactsBean.newAwardContact.fullName" 
	              							attributeEntry="${awardPersonAttributes.fullName}" readOnly="true"/></label>
																				            			
							</div>
						</c:otherwise>
					</c:choose>
        		</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.sponsorContactsBean.newAwardContact.contactOrganizationName}" />&nbsp;
	        		</div>
	        	</td>
	        			
	        	<td class="infoline" style="font-size: 80%">
	        		<div align="center">
		        		<html:select property="sponsorContactsBean.contactRoleCode" styleClass="fixed-size-300-select">
		        			<c:forEach var="role" items="${KualiForm.sponsorContactsBean.contactRoles}">
		        				<option value="${role.roleCode}"><c:out value="${role.roleDescription}" /></option>
		        			</c:forEach>                		
						</html:select>
					</div>
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.sponsorContactsBean.newAwardContact.contact.phoneNumber}" />&nbsp;
	        		</div>
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.sponsorContactsBean.newAwardContact.contact.emailAddress}" />&nbsp;
	        		</div>
	        	</td>
	        	<td class="infoline">
	        		<c:choose>
		        		<c:when test="${not empty KualiForm.sponsorContactsBean.newAwardContact.contact.identifier}">
			        		<div align="center">	        			
			        			<html:image property="methodToCall.addSponsorContact" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Contact" alt="Add Contact" styleClass="tinybutton" />
			        			<html:image property="methodToCall.clearNewSponsorContact" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Fields" alt="Clear Fields" styleClass="tinybutton" />
			        		</div>
			        	</c:when>
			        	<c:otherwise>&nbsp;</c:otherwise>
			        </c:choose>
	        	</td>
			</tr>
				
			<c:forEach var="awardContact" items="${KualiForm.sponsorContactsBean.sponsorContacts}" varStatus="awardContactRowStatus">
				<tr>
					<th class="infoline" scope="row">
						<c:out value="${awardContactRowStatus.index + 1}" />
					</th>
	                <td valign="middle">
	                	<div align="center">
	                		${awardContact.fullName}&nbsp;
	                		<kul:directInquiry boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" inquiryParameters="'${awardContact.contact.identifier}':rolodexId" anchor="${tabKey}" />
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
							${awardContact.contactOrganizationName}&nbsp;							
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                	<html:select name="awardContact" property="contactRoleCode" styleClass="fixed-size-300-select">
	                		<c:forEach var="role" items="${KualiForm.sponsorContactsBean.contactRoles}">
		        				<c:if test="${awardContact.contactRoleCode != role.roleCode}">
		        					<option value="${role.roleCode}"><c:out value="${role.roleDescription}" /></option>
		        				</c:if>
		        				<c:if test="${awardContact.contactRoleCode == role.roleCode}">
		        					<option value="${role.roleCode}" selected="true"><c:out value="${role.roleDescription}" /></option>
		        				</c:if>
		        			</c:forEach>                		
						</html:select>
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
	                
					<td class="infoline">
						<div align="center">
							<html:image property="methodToCall.deleteSponsorContact.line${awardContactRowStatus.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	            </tr>
    		</c:forEach>	    	
    	</table>
	</div>
</kul:tab>