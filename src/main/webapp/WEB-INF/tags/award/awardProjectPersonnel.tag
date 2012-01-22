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
<script type='text/javascript' src='dwr/interface/KraPersonService.js'></script>
<script type='text/javascript 'src='dwr/interface/PersonService.js'></script>
<script type='text/javascript' src='dwr/interface/RolodexService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script>
 $jq = jQuery.noConflict();
</script>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardPersonAttributes" value="${DataDictionary.AwardPerson.attributes}" />
<c:set var="keyPersonRoleConstant" value="<%=org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE%>" />
<c:set var="coiRoleConstant" value="<%=org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE%>" />

<%-- kra:section permission="modifyAward" --%>
				
<kul:tab tabTitle="Key Personnel and Credit Split" tabItemCount="${KualiForm.projectPersonnelBean.projectPersonnelCount}" defaultOpen="false" 
			 transparentBackground="true" useRiceAuditMode="true" innerTabErrorKey="document.awardList[0].projectPersons[*">
	<div class="tab-container" align="center">
	   <h3>
            <span class="subhead-left">Key Personnel and Credit Split</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.contacts.AwardPersonCreditSplit" altText="help"/></span>
        </h3>
        <kul:innerTab tabTitle="Key Personnel" parentTab="Key Personnel and Credit Split" defaultOpen="true" 
            tabErrorKey="projectPersonnelBean.contactRoleCode*,projectPersonnelBean.newAwardContact*,document.awardList[0].projectPersons,projectPersonnelBean.projectPersonnel[*"
            auditCluster="contactsAuditErrors,contactsAuditWarnings" tabAuditKey="document.awardList[0].projectPerson*">
		
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
       										onblur="loadContactPersonName('projectPersonnelBean.newProjectPerson.person.fullName',
	                               				 			'per.fullName.div',
	                	        				     		'key.unitNumber',
	                	        				  			'key.phoneNumber',
           	        							  			'key.emailAddress',
           	        							  			'key.personId');"
											readOnly="${readOnly}" /> <c:if test="${!readOnly}">
              					<kul:lookup boClassName="org.kuali.kra.bo.KcPerson"
                                            fieldConversions="personId:projectPersonnelBean.personId" anchor="${tabKey}"
      	 									lookupParameters="projectPersonnelBean.newProjectPerson.person.fullName:lastName"/>				

				</c:if> <c:if test="${readOnly}">
					<html:hidden styleId ="per.fullName.div" property="projectPersonnelBean.newProjectPerson.person.fullName" />
				</c:if>
 
 				<html:hidden styleId ="key.personId" property="projectPersonnelBean.personId" />
				${kfunc:registerEditableProperty(KualiForm, "projectPersonnelBean.personId")}				
 
				<div id="per.fullName.div">&nbsp; <c:if
					test="${!empty KualiForm.projectPersonnelBean.newProjectPerson.person}">
					<c:choose>
						<c:when
							test="${empty KualiForm.projectPersonnelBean.newProjectPerson.person}">
							<span style='color: red;'>not found</span>
						</c:when>
						<c:otherwise>
							<c:out
								value="${KualiForm.projectPersonnelBean.newProjectPerson.person.fullName}" />
						</c:otherwise>
					</c:choose>
				</c:if></div>
      	 					</td>
                        </tr><tr>
                            <td style="white-space:nowrap; border: none;">
                  				<label><span style="margin-right: 3;">Non-employee ID:
                            </td>
                            <td style="white-space:nowrap; border: none;">
                                <kul:htmlControlAttribute property="projectPersonnelBean.newProjectPerson.rolodex.fullName" 
                                                attributeEntry="${awardPersonAttributes.fullName}"
                                                onblur="loadRolodexInfo2('projectPersonnelBean.newProjectPerson.rolodex.fullName',
	                               							'rol.fullName.div',
	                	        				     		'key.unitNumber',	                               							
                      	        				  			'key.phoneNumber',
           	        							  			'key.emailAddress',
           	        							  			'key.rolodexId');"
           	        							 readOnly="${readOnly}"/>
 					<c:if test="${!readOnly}">
  					<kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" fieldConversions="rolodexId:projectPersonnelBean.rolodexId" 
          			anchor="${tabKey}"
          			lookupParameters="projectPersonnelBean.rolodexId:rolodexId,projectPersonnelBean.newProjectPerson.rolodex.fullName:lastName"/>																	
  					</c:if>
  			   <c:if test="${readOnly}">
					<html:hidden styleId ="rol.fullName.div" property="projectPersonnelBean.newProjectPerson.rolodex.fullName" />
				</c:if>

				${kfunc:registerEditableProperty(KualiForm, "projectPersonnelBean.rolodexId")}
				<html:hidden styleId ="key.rolodexId" property="projectPersonnelBean.rolodexId" />
  
				<div id="rol.fullName.div">&nbsp; <c:if
					test="${!empty KualiForm.projectPersonnelBean.newAwardContact}">
					<c:choose>
						<c:when
							test="${empty KualiForm.projectPersonnelBean.newAwardContact}">
							<span style='color: red;'>not found</span>
						</c:when>
						<c:otherwise>
							<c:out
								value="${KualiForm.projectPersonnelBean.newAwardContact.contactOrganizationName}" />
						</c:otherwise>
					</c:choose>
				</c:if></div>
				</td>
                        </tr></table>
    	        	</td>
    	        	<td id="key.unitNumber" class="infoline">
    	        		<div align="center">
    	        			<c:out value="${KualiForm.projectPersonnelBean.newAwardContact.contactOrganizationName}" />&nbsp;
    	        		</div>
    	        	</td>

    	        	<td class="infoline">
                        ${KualiForm.valueFinderResultDoNotCache}
    	        		<div align="center">
    		        		<kul:htmlControlAttribute property="projectPersonnelBean.contactRoleCode" 
    	                									attributeEntry="${awardPersonAttributes.contactRoleCode}" onchange="proposalRoleChange(this, 400);"/><br/>
    	                	<span class="keypersononly">
    					    *<kul:htmlAttributeLabel attributeEntry="${awardPersonAttributes.keyPersonRole}" useShortLabel="true" noColon="false" />
    					    <span class="noscriptonly">(Required for Key Persons only)</span> 
    				         <kul:htmlControlAttribute property="projectPersonnelBean.newAwardContact.keyPersonRole" 
    										           attributeEntry="${awardPersonAttributes.keyPersonRole}"/>
    					    </span>
    					    <c:if test="${KualiForm.document.awardList[0].sponsorNihMultiplePi}">
    					    <span class="coionly">
    					    <kul:htmlAttributeLabel attributeEntry="${awardPersonAttributes.multiplePi}" noColon="false" />
    				         <kul:htmlControlAttribute property="projectPersonnelBean.newAwardContact.multiplePi" 
    										           attributeEntry="${awardPersonAttributes.multiplePi}"/>    					    
    					    </span>
    					    </c:if>
                        <script type="text/javascript">
                          function proposalRoleChange(formItem, speed) {
                              if ($jq(formItem).val() == '${keyPersonRoleConstant}') {
                                  $jq(formItem).siblings('.keypersononly').slideDown(speed);
                              } else {
                            	  $jq(formItem).siblings('.keypersononly').slideUp(speed);
                              }
                              if ($jq(formItem).val() == '${coiRoleConstant}') {
                            	  $jq(formItem).siblings('.coionly').slideDown(speed);
                              } else {
                            	  $jq(formItem).siblings('.coionly').slideUp(speed);
                              }
                          }
                          $jq(document).ready(function() {
                        	  $jq('.noscriptonly').hide();
                              $jq('.keypersononly').hide();
                              $jq('.coionly').hide();
                              $jq(document).find("[id$='contactRoleCode']").each(function() {
                              	proposalRoleChange(this, 0);
                              });
                          });
                        </script>
                        ${KualiForm.valueFinderResultCache}
                        </div>
    	        	</td>
    	        	<td id="key.phoneNumber" class="infoline">
    	        		<div  align="center">
    	        			<c:out value="${KualiForm.projectPersonnelBean.newAwardContact.contact.phoneNumber}" />&nbsp;
    	        		</div>
    	        	</td>
    	        	<td id="key.emailAddress" class="infoline">
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
    	                	${KualiForm.valueFinderResultDoNotCache}
    	                	<div align="center">
    	                	 	<kul:htmlControlAttribute property="projectPersonnelBean.projectPersonnel[${awardContactRowStatus.index}].contactRoleCode" 
    	                			attributeEntry="${awardPersonAttributes.contactRoleCode}" onchange="proposalRoleChange(this, 400);"/><br/>
	
   	                			<span class="keypersononly">
	    					    	*<kul:htmlAttributeLabel attributeEntry="${awardPersonAttributes.keyPersonRole}" useShortLabel="true" noColon="false" />
		    					    <span class="noscriptonly">(Required for Key Persons only)</span> 
		    				        <kul:htmlControlAttribute property="projectPersonnelBean.projectPersonnel[${awardContactRowStatus.index}].keyPersonRole" 
		    										           attributeEntry="${awardPersonAttributes.keyPersonRole}"/>
	    					    </span>
	    					    <c:if test="${KualiForm.document.awardList[0].sponsorNihMultiplePi}">
	    					    <span class="coionly">
	    					    	<kul:htmlAttributeLabel attributeEntry="${awardPersonAttributes.multiplePi}" noColon="false" />
		    				        <kul:htmlControlAttribute property="projectPersonnelBean.projectPersonnel[${awardContactRowStatus.index}].multiplePi" 
		    										           attributeEntry="${awardPersonAttributes.multiplePi}"/>	    					    
	    					    </span>
	    					    </c:if>
    	                	</div>
    	                	${KualiForm.valueFinderResultCache}
    	                	 
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
    							<c:if test="${KualiForm.syncMode}">
	    							<html:image property="methodToCall.syncProjectPerson.line${awardContactRowStatus.index}.anchor${currentTabIndex}"
	    								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-sync.gif' alt="sync" styleClass="tinybutton" disabled="${readOnly}"/>
    							</c:if>
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
    	</kul:innerTab>
    	    
	    <c:if test="${KualiForm.awardCreditSplitBean.awardCreditsLimitApplicable && KualiForm.document.awardList[0].totalUnitCount > 0}" > 
	    	<kra-a:creditSplit/>
	    </c:if>	    
    </div>    
</kul:tab>
