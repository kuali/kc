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
<%-- member of AwardContacts.jsp --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<script type='text/javascript' src='dwr/interface/RolodexService.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<c:set var="awardSponsorContactAttributes" value="${DataDictionary.AwardSponsorContact.attributes}" />
<c:set var="awardContactAttributes" value="${DataDictionary.AwardContact.attributes}" />

<%-- kra:section permission="modifyAward" --%>
<kul:tab defaultOpen="false" tabItemCount="${KualiForm.sponsorContactsBean.sponsorContactsCount}" useRiceAuditMode="true"
				tabTitle="Sponsor Contacts" tabErrorKey="sponsorContactsBean.newAwardContact*,document.awardList[0].sponsorContact*,document.award.awardTemplate*" 
				auditCluster="contactsAuditErrors" tabAuditKey="document.awardList[0].sponsorContact*">
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Sponsor Contacts</span>
         	<span class="subhead-right"><kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardSponsorContactsHelpUrl" altText="help"/></span>
		</h3>
	    <table id="sponsor-contacts-table" cellpadding="0" cellspacing="0" summary="Sponsor Contacts">
			<tr>
				<th scope="row" width="5%">&nbsp;</th>
				<th width="15%">* Person or Organization</th>
				<th width="20%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardSponsorContactAttributes.contactRoleCode}" /></div></th>
				<th width="15%">Office Phone</th>
				<th width="15%">Email</th>
				<th width="15%"><div align="center">Actions</div></th>
			</tr>
			
			<c:if test="${!readOnly}">
			<tbody class="addline">
			<tr>
				<th class="infoline" scope="row">Add</th>
				<td nowrap class="grid" class="infoline">
                    Non-employee ID:
					<kul:htmlControlAttribute property="sponsorContactsBean.newAwardContact.rolodex.fullName" 
      								attributeEntry="${awardContactAttributes.fullName}" 
      							    onblur="loadRolodexInfo('sponsorContactsBean.newAwardContact.rolodex.fullName',
	                               							'org.fullName.div',
	                	        				  			'org.phoneNumber',
           	        							  			'org.emailAddress',
           	        							  			'rolodexId');"
           	        							  			readOnly="${readOnly}"/>
  					<c:if test="${!readOnly}">
  						<kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.Rolodex" fieldConversions="rolodexId:sponsorContactsBean.rolodexId" 
  									anchor="${tabKey}" lookupParameters="sponsorContactsBean.rolodexId:rolodexId"/>
  					</c:if>
  			   </c:if> 
  			   <c:if test="${readOnly}">
					<html:hidden styleId ="org.fullName" property="sponsorContactsBean.newAwardContact.rolodex.fullName" />
				</c:if>
				
				${kfunc:registerEditableProperty(KualiForm, "sponsorContactsBean.rolodexId")}
				<html:hidden styleId ="rolodexId" property="sponsorContactsBean.rolodexId" />


				<div id="org.fullName.div">&nbsp; <c:if
					test="${!empty KualiForm.sponsorContactsBean.newAwardContact.contact}">
					<c:choose>
						<c:when
							test="${empty KualiForm.sponsorContactsBean.newAwardContact.contact}">
							<span style='color: red;'>not found</span>
						</c:when>
						<c:otherwise>
							<c:out
								value="${KualiForm.sponsorContactsBean.newAwardContact.contact.contactOrganizationName}" />
						</c:otherwise>
					</c:choose>
				</c:if></div>
				</td>  

	        	<td class="infoline" style="font-size: 80%">
	        		<div align="center">
		        		<kul:htmlControlAttribute property="sponsorContactsBean.contactRoleCode" 
	                									attributeEntry="${awardSponsorContactAttributes.contactRoleCode}" />
					</div>
	        	</td>
	        	<td id="org.phoneNumber" class="infoline">
	        		<c:out value="${KualiForm.sponsorContactsBean.newAwardContact.contact.phoneNumber}" />&nbsp;
	        	</td>
	        	<td id="org.emailAddress" class="infoline">
	        		<c:out value="${KualiForm.sponsorContactsBean.newAwardContact.contact.emailAddress}" />&nbsp;
	        	</td>
	        	<td class="infoline">
	        		<div align="center">	        			
			        	<html:image property="methodToCall.addSponsorContact" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Contact" alt="Add Contact" styleClass="tinybutton addButton" />
			        </div>
	        	</td>
			</tr>
			</tbody>
				
			<c:forEach var="awardContact" items="${KualiForm.sponsorContactsBean.sponsorContacts}" varStatus="awardContactRowStatus">
				<tr>
					<th class="infoline" scope="row">
						<c:out value="${awardContactRowStatus.index + 1}" />
					</th>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="sponsor_contact.identifier_${awardContactRowStatus.index}" value="${awardContact.contact.identifier}" />
	                		<c:choose>
	                		    <c:when test="${empty awardContact.fullName}">
	                		       ${awardContact.contactOrganizationName}&nbsp;
	                		    </c:when>
	                		    <c:otherwise>
	                	           ${awardContact.fullName}&nbsp;
	                		    </c:otherwise>
	                		</c:choose>
	                		
	                		<kul:directInquiry boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex" inquiryParameters="sponsor_contact.identifier_${awardContactRowStatus.index}:rolodexId" anchor="${tabKey}" />
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<kul:htmlControlAttribute property="sponsorContactsBean.sponsorContacts[${awardContactRowStatus.index}].contactRoleCode" 
	                									attributeEntry="${awardSponsorContactAttributes.contactRoleCode}" 
	                									readOnlyAlternateDisplay ="${awardContact.contactRole.description}"/>
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
						  <c:if test="${!readOnly}">
							<html:image property="methodToCall.deleteSponsorContact.line${awardContactRowStatus.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton" />
  							<c:if test="${KualiForm.syncMode}">
    							<html:image property="methodToCall.syncSponsorContact.line${awardContactRowStatus.index}.anchor${currentTabIndex}"
		    								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-sync.gif' alt="sync" styleClass="tinybutton" disabled="${readOnly}"/>
    						</c:if>
						  </c:if>
						  <c:if test="${readOnly}">&nbsp;</c:if>
						</div>
	                </td>
	            </tr>
    		</c:forEach>	    	
    	</table>
    
    	<div align="center">
    		<c:set var="syncPropertyName" value="sponsorContacts" />
    		<c:if test="${!readOnly}">
				<kra-a:awardSyncButton scopeNames="SPONSOR_CONTACTS_TAB" tabKey="${tabKey}"/>
		    </c:if>
		</div>
	</div>
</kul:tab>
