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

<c:set var="awardPersonAttributes" value="${DataDictionary.AwardPerson.attributes}" />

<%-- kra:section permission="modifyAward" --%>
<kul:tabTop tabTitle="Project Personnel (${KualiForm.projectPersonnelBean.projectPersonnelCount})" defaultOpen="false" 
			 tabErrorKey="document.awardList[0].projectPersons*,projectPersonnelBean.newAwardContact*">
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Project Personnel</span>
			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.AwardPerson" altText="help"/></span>
		</h3>
		
		<table id="contacts-table" cellpadding="0" cellspacing="0" summary="Project Personnel">
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
					<div>
						<label><span style="margin-right: 30;">Add Employee: </span></label>
    					<kul:htmlControlAttribute property="projectPersonnelBean.newProjectPerson.person.fullName" 
          							attributeEntry="${awardPersonAttributes.fullName}" readOnly="false"/>
          				<label>
          					<kul:lookup boClassName="org.kuali.kra.bo.Person" fieldConversions="personId:projectPersonnelBean.personId" anchor="${tabKey}" 
  	 									lookupParameters="projectPersonnelBean.personId:personId"/>
  	 					</label>
  	 				</div>
  	 				<div>
          				<label><span style="margin-right: 3;">Add Non-employee:</span></label>
      					<kul:htmlControlAttribute property="projectPersonnelBean.newProjectPerson.rolodex.fullName" 
          								attributeEntry="${awardPersonAttributes.fullName}" readOnly="false"/>
      					<label>
      						<kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" fieldConversions="rolodexId:projectPersonnelBean.rolodexId" 
      									anchor="${tabKey}" lookupParameters="projectPersonnelBean.rolodexId:rolodexId"/>
      					</label>
      				</div>
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.projectPersonnelBean.newAwardContact.contactOrganizationName}" />&nbsp;
	        		</div>
	        	</td>
	        	<td class="infoline" style="font-size: 80%">
	        		<div align="center">
		        		<kul:htmlControlAttribute property="projectPersonnelBean.contactRoleCode" 
	                									attributeEntry="${awardPersonAttributes.contactRoleCode}" />
					</div>
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.projectPersonnelBean.newAwardContact.contact.phoneNumber}" />&nbsp;
	        		</div>
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.projectPersonnelBean.newAwardContact.contact.emailAddress}" />&nbsp;
	        		</div>
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<html:image property="methodToCall.addProjectPerson" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Contact" alt="Add Contact" styleClass="tinybutton" />
	        		</div>
	        	</td>
			</tr>
			
			<c:forEach var="awardContact" items="${KualiForm.document.awardList[0].projectPersons}" varStatus="awardContactRowStatus">
				<tr>
					<th class="infoline" scope="row" rowspan="4">
						<c:out value="${awardContactRowStatus.index + 1}" />
					</th>
	                <td valign="middle">
	                	<input type="hidden" name="award_person.identifier_${awardContactRowStatus.index}" value="${awardContact.contact.identifier}" />
	                	<div align="center">
	                		${awardContact.fullName}&nbsp;
	                		<c:choose>
		                		<c:when test="${awardContact.employee}">
		                			<kul:directInquiry boClassName="org.kuali.kra.bo.Person" inquiryParameters="award_person.identifier_${awardContactRowStatus.index}:personId" anchor="${tabKey}" />
		                		</c:when>
		                		<c:otherwise>
		                			<kul:directInquiry boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" inquiryParameters="award_person.identifier_${awardContactRowStatus.index}:rolodexId" anchor="${tabKey}" />
		                		</c:otherwise>
		                	</c:choose>
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="award_person.orgNumber_${awardContactRowStatus.index}" value="${awardContact.organizationIdentifier}" />
	                		<c:out value="${awardContact.contactOrganizationName}" />&nbsp;
	                		<c:choose>
		                		<c:when test="${awardContact.employee}">
		                			<kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="award_person.orgNumber_${awardContactRowStatus.index}:unitNumber" anchor="${tabKey}" />
		                		</c:when>
		                		<c:otherwise>
		                			<kul:directInquiry boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" inquiryParameters="award_person.identifier_${awardContactRowStatus.index}:rolodexId" anchor="${tabKey}" />
		                		</c:otherwise>
		                	</c:choose>		                	
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		${awardContact.contactRole.description}&nbsp;
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
							<html:image property="methodToCall.deleteProjectPerson.line${awardContactRowStatus.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	            </tr>
	            
	            <tr>
	            	<td colspan="6">
	            		<kra-a:awardProjectPersonnelPersonDetails awardContact="${awardContact}" awardContactRowStatusIndex="${awardContactRowStatus.index}" />
	            	</td>
	            </tr>
	            <tr>
	            	<td colspan="6">
	            		<kra-a:awardProjectPersonnelUnits awardContact="${awardContact}" awardContactRowStatusIndex="${awardContactRowStatus.index}" />
	            	</td>
	            </tr>
	            <tr>
					<td colspan="6">&nbsp;</td>
				</tr>	                     
	    	</c:forEach>	    	
	    </table>	    
    </div>
</kul:tabTop>