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
<script src="scripts/jquery/jquery.js"></script>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardPersonAttributes" value="${DataDictionary.AwardPerson.attributes}" />

<%-- kra:section permission="modifyAward" --%>
<kul:tab tabTitle="Key Personnel and Credit Split" tabItemCount="${KualiForm.projectPersonnelBean.projectPersonnelCount}" defaultOpen="false" 
			 transparentBackground="true" useRiceAuditMode="true" innerTabErrorKey="document.awardList[0].projectPersons[*">
	<div class="tab-container" align="center">
	   <h3>
            <span class="subhead-left">Key Personnel and Credit Split</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.contacts.AwardPersonCreditSplit" altText="help"/></span>
        </h3>
        <kra:innerTab tabTitle="Key Personnel" parentTab="Key Personnel and Credit Split" defaultOpen="true" 
            tabErrorKey="projectPersonnelBean.contactRoleCode*,projectPersonnelBean.newAwardContact*,document.awardList[0].projectPersons"
            auditCluster="contactsAuditErrors" tabAuditKey="document.awardList[0].projectPerson*">
		
    		<table id="contacts-table" cellpadding="0" cellspacing="0" summary="Key Personnel">
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
    				    <table style="border: none;"><tr>
    				        <td style="white-space:nowrap; border: none;">
        						Employee User Name:
                            </td>
                            <td style="white-space:nowrap; border: none;">
                                <kul:htmlControlAttribute property="projectPersonnelBean.newProjectPerson.person.fullName" 
                                            attributeEntry="${awardPersonAttributes.fullName}"
                                            readOnly="true" />
              					<kul:lookup boClassName="org.kuali.kra.bo.KcPerson"
                                            fieldConversions="personId:projectPersonnelBean.personId" anchor="${tabKey}"
      	 									lookupParameters="projectPersonnelBean.newProjectPerson.person.fullName:lastName"/>
      	 					</td>
                        </tr><tr>
                            <td style="white-space:nowrap; border: none;">
                  				<label><span style="margin-right: 3;">Non-employee ID:
                            </td>
                            <td style="white-space:nowrap; border: none;">
                                <kul:htmlControlAttribute property="projectPersonnelBean.newProjectPerson.rolodex.fullName" 
                                                attributeEntry="${awardPersonAttributes.fullName}"
                                                readOnly="true" />
          						<kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" fieldConversions="rolodexId:projectPersonnelBean.rolodexId" 
          									anchor="${tabKey}"
          									lookupParameters="projectPersonnelBean.rolodexId:rolodexId,projectPersonnelBean.newProjectPerson.rolodex.fullName:lastName"/>
                            </td>
                        </tr></table>
    	        	</td>
    	        	<td class="infoline">
    	        		<div align="center">
    	        			<c:out value="${KualiForm.projectPersonnelBean.newAwardContact.contactOrganizationName}" />&nbsp;
    	        		</div>
    	        	</td>
    	        	<td class="infoline">
                        ${KualiForm.valueFinderResultDoNotCache}
    	        		<div align="center">
    		        		<kul:htmlControlAttribute property="projectPersonnelBean.contactRoleCode" 
    	                									attributeEntry="${awardPersonAttributes.contactRoleCode}" onchange="proposalRoleChange(this, 'normal');"/><br/>
    	                	<span class="keypersononly">
    					    *<kul:htmlAttributeLabel attributeEntry="${awardPersonAttributes.keyPersonRole}" useShortLabel="true" noColon="false" /><span class="noscriptonly">(Required for Key Persons only)</span> 
    				         <kul:htmlControlAttribute property="projectPersonnelBean.newAwardContact.keyPersonRole" 
    										           attributeEntry="${awardPersonAttributes.keyPersonRole}"/>
    					    </span>
                        <script type="text/javascript">
                          function proposalRoleChange(formItem, speed) {
                              if ( $(formItem).val() == 'KP' ) {
                                  $('.keypersononly').slideDown(speed);
                              } else {
                                  $('.keypersononly').slideUp(speed);
                              }
                          }
                          $(document).ready(function() {
                              proposalRoleChange('#projectPersonnelBean\\.contactRoleCode', 'now');
                              $('.noscriptonly').hide();
                          });
                        </script>
                        ${KualiForm.valueFinderResultCache}
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
    			</c:if>
    			
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
    		                			<kul:directInquiry boClassName="org.kuali.kra.bo.KcPerson" inquiryParameters="award_person.identifier_${awardContactRowStatus.index}:personId" anchor="${tabKey}" />
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
                                <c:set var="isNih" value="${KualiForm.document.awardList[0].nih}" />
                                <c:if test="${isNih}">
                                    <c:set var="roleDescription" value="${KualiForm.document.awardList[0].nihDescription[awardContact.contactRole.roleCode]}" />
                                </c:if>
                                <c:if test="${!isNih}">
                                    <c:set var="roleDescription" value="${awardContact.contactRole.description}" />
                                </c:if>
    	                		${roleDescription}&nbsp;
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
    							<html:image property="methodToCall.deleteProjectPerson.line${awardContactRowStatus.index}.anchor${currentTabIndex}"
    							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
    						  </c:if>
    						  <c:if test="${readOnly}">&nbsp;</c:if>
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
    	            		<kra-a:awardProjectPersonnelUnits awardContact="${awardContact}" awardPersonIndex="${awardContactRowStatus.index}" />
    	            	</td>
    	            </tr>
    	            <tr>
    					<td colspan="6">&nbsp;</td>
    				</tr>	                     
    	    	</c:forEach>	    	
    	    </table>
    	</kra:innerTab>
    	    
	    <c:if test="${KualiForm.awardCreditSplitBean.awardCreditsLimitApplicable && KualiForm.document.awardList[0].totalUnitCount > 0}" > 
	    	<kra-a:creditSplit/>
	    </c:if>	    
    </div>    
</kul:tab>
