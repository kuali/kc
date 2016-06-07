<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- member of InstitutionalProposalContacts.jsp --%>
<script type="text/javascript">
  $jq = jQuery.noConflict();
</script>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="keyPersonRoleConstant" value="<%=org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE%>" />
<c:set var="coiRoleConstant" value="<%=org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE%>" />

<c:set var="institutionalProposalPersonAttributes" value="${DataDictionary.InstitutionalProposalPerson.attributes}" />
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />

<%-- kra:section permission="modifyInstitutionalProposal" --%>
<div id="workarea">
<kul:tab tabTitle="Project Personnel" defaultOpen="false" alwaysOpen="false" tabItemCount="${KualiForm.projectPersonnelBean.projectPersonnelCount}" 
			 tabErrorKey="document.institutionalProposalList[0].projectPerson*,projectPersonnelBean.contactRoleCode*,projectPersonnelBean.personId*"
			 auditCluster="contactsAuditErrors" tabAuditKey="document.institutionalProposalList[0].projectPerson*"
			 transparentBackground="true" useRiceAuditMode="true">
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Project Personnel</span>
    		<span class="subhead-right"><kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="projectPersonnel1HelpUrl" altText="help"/></span>
		</h3>
		
		<table id="contacts-table" cellpadding="0" cellspacing="0" summary="Project Personnel">
			<tr>
				<th scope="row" width="5%">&nbsp;</th>
				<th width="15%">Person</th>
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
					<div>
						<label><span style="margin-right: 30;">Add Employee: </span></label>
    					<kul:htmlControlAttribute property="projectPersonnelBean.newProjectPerson.person.fullName" 
          							attributeEntry="${institutionalProposalPersonAttributes.fullName}" readOnly="true"/>
          				<label>
          					<kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson"
                                        fieldConversions="personId:projectPersonnelBean.personId" anchor="${tabKey}"
  	 									lookupParameters="projectPersonnelBean.personId:personId"/>
  	 					</label>
  	 				</div>
  	 				<div>
          				<label><span style="margin-right: 3;">Add Non-employee:</span></label>
      					<kul:htmlControlAttribute property="projectPersonnelBean.newProjectPerson.rolodex.fullName" 
          								attributeEntry="${institutionalProposalPersonAttributes.fullName}" readOnly="true"/>
      					<label>
      						<kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex" fieldConversions="rolodexId:projectPersonnelBean.rolodexId"
      									anchor="${tabKey}" lookupParameters="projectPersonnelBean.rolodexId:rolodexId"/>
      					</label>
      				</div>
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.projectPersonnelBean.newInstitutionalProposalContact.contactOrganizationName}" />&nbsp;
	        		</div>
	        	</td>
	        	<td class="infoline">
                    ${KualiForm.valueFinderResultDoNotCache}
	        		<div align="center">
		        		<kul:htmlControlAttribute property="projectPersonnelBean.contactRoleCode" 
	                									attributeEntry="${institutionalProposalPersonAttributes.contactRoleCode}" onchange="proposalRoleChange(this, 400);"/><br/>
	                	<span class="keypersononly">
					    *<kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonAttributes.keyPersonRole}" useShortLabel="true" noColon="false" /><span class="noscriptonly">(Required for Key Persons only)</span> 
				         <kul:htmlControlAttribute property="projectPersonnelBean.newInstitutionalProposalContact.keyPersonRole" 
										           attributeEntry="${institutionalProposalPersonAttributes.keyPersonRole}"/>
					    </span>
                    <script type="text/javascript">
                    function proposalRoleChange(formItem, speed) {
                        if ($jq(formItem).val() == '${keyPersonRoleConstant}') {
                            $jq(formItem).siblings('.keypersononly').slideDown(speed);
                        } else {
                      	  $jq(formItem).siblings('.keypersononly').slideUp(speed);
                        }
                    }
                    $jq(document).ready(function() {
                  	  $jq('.noscriptonly').hide();
                        $jq('.keypersononly').hide();
                        $jq(document).find("[id$='contactRoleCode']").each(function() {
                        	proposalRoleChange(this, 0);
                        });
                    });
                    </script>
                    ${KualiForm.valueFinderResultCache}
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.projectPersonnelBean.newInstitutionalProposalContact.contact.phoneNumber}" />&nbsp;
	        		</div>
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<c:out value="${KualiForm.projectPersonnelBean.newInstitutionalProposalContact.contact.emailAddress}" />&nbsp;
	        		</div>
	        	</td>
	        	<td class="infoline">
	        		<div align="center">
	        			<html:image property="methodToCall.addProjectPerson" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Contact" alt="Add Contact" styleClass="tinybutton" />
	        		</div>
	        	</td>
			</tr>
			</c:if>

            <c:set var="displayCoiDisclosureStatus" value="${KualiForm.displayCoiDisclosureStatus}" />
            <c:set var="coiDisclosureStatuses" value="${KualiForm.disclosureProjectStatuses}" />
            <c:set var="coiDispositionViewEnabled" value="${KualiForm.coiDispositionViewEnabled}" />

			<c:forEach var="institutionalProposalContact" items="${krafn:copy(KualiForm.document.institutionalProposalList[0].projectPersons)}" varStatus="institutionalProposalContactRowStatus">
				<tr>
					<th class="infoline" scope="row" rowspan="4">
						<c:out value="${institutionalProposalContactRowStatus.index + 1}" />
					</th>
	                <td valign="middle">
	                	<input type="hidden" name="institutionalproposal_person.identifier_${institutionalProposalContactRowStatus.index}" value="${institutionalProposalContact.contact.identifier}" />
	                	<div align="center">
	                		${institutionalProposalContact.fullName}&nbsp;
                                <c:choose>
		                		<c:when test="${institutionalProposalContact.employee}">
		                			<kul:directInquiry boClassName="org.kuali.coeus.common.framework.person.KcPerson" inquiryParameters="institutionalproposal_person.identifier_${institutionalProposalContactRowStatus.index}:personId" anchor="${tabKey}" />
		                		</c:when>
		                		<c:otherwise>
		                			<kul:directInquiry boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex" inquiryParameters="institutionalproposal_person.identifier_${institutionalProposalContactRowStatus.index}:rolodexId" anchor="${tabKey}" />
		                		</c:otherwise>
		                	</c:choose>
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
	                		<input type="hidden" name="institutionalproposal_person.orgNumber_${institutionalProposalContactRowStatus.index}" value="${institutionalProposalContact.organizationIdentifier}" />
	                		<c:out value="${institutionalProposalContact.contactOrganizationName}" />&nbsp;
	                		<c:choose>
		                		<c:when test="${institutionalProposalContact.employee}">
		                			<kul:directInquiry boClassName="org.kuali.coeus.common.framework.unit.Unit" inquiryParameters="institutionalproposal_person.orgNumber_${institutionalProposalContactRowStatus.index}:unitNumber" anchor="${tabKey}" />
		                		</c:when>
		                		<c:otherwise>
		                			<kul:directInquiry boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex" inquiryParameters="institutionalproposal_person.identifier_${institutionalProposalContactRowStatus.index}:rolodexId" anchor="${tabKey}" />
		                		</c:otherwise>
		                	</c:choose>		                	
						</div>
					</td>
	                <td valign="middle">
	                	<div align="center">
							${institutionalProposalContact.investigatorRoleDescription}
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
							<html:image property="methodToCall.deleteProjectPerson.line${institutionalProposalContactRowStatus.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						  </c:if>
						</div>
	                </td>
	            </tr>

                <c:choose>
                    <c:when test="${displayCoiDisclosureStatus}">
                        <c:forEach items="${coiDisclosureStatuses}" var="projectStatus">
                            <c:choose>
                                <c:when test="${institutionalProposalContact.genericId eq projectStatus.userId}">
                                    <tr>
                                        <td colspan="1" nowrap class="tab-subhead">
                                            Coi Disclosure Status:
                                        </td>
                                        <td colspan="5" nowrap class="tab-subhead">
                                                ${projectStatus.status}
                                        </td>
                                    </tr>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${coiDispositionViewEnabled && institutionalProposalContact.canViewDisclosureDisposition}">
                        <c:forEach items="${coiDisclosureStatuses}" var="projectStatus">
                            <c:choose>
                               <c:when test="${institutionalProposalContact.genericId eq projectStatus.userId}">
                                    <tr>
                                        <td colspan="1" nowrap class="tab-subhead">
                                            Coi Disposition Status:
                                        </td>
                                        <td colspan="5" nowrap class="tab-subhead">
                                                ${projectStatus.disposition}
                                        </td>
                                    </tr>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                </c:choose>

	            <tr>
	            	<td colspan="6">
	            		<kra-ip:institutionalProposalProjectPersonnelPersonDetails institutionalProposalContact="${institutionalProposalContact}" institutionalProposalContactRowStatusIndex="${institutionalProposalContactRowStatus.index}" />
	            	</td>
	            </tr>
	            <tr>
	            	<td colspan="6">
	            		<kra-ip:institutionalProposalProjectPersonnelUnits institutionalProposalContact="${institutionalProposalContact}" institutionalProposalPersonIndex="${institutionalProposalContactRowStatus.index}" />
	            	</td>
	            </tr>
	            <tr>
					<td colspan="6">&nbsp;</td>
				</tr>	                     
	    	</c:forEach>	    	
	    </table>
	    <c:if test="${KualiForm.institutionalProposalCreditSplitBean.institutionalProposalCreditsLimitApplicable && KualiForm.document.institutionalProposalList[0].totalUnitCount > 0}" >
	    	<kra-ip:creditSplit/>
	    </c:if>  
    </div>    
</kul:tab>

