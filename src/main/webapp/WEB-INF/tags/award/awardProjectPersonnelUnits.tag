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

<%-- Member of awardProjectPersonnel.tag --%>

<%@ attribute name="awardContact" required="true" type="org.kuali.kra.award.contacts.AwardPerson" %>
<%@ attribute name="awardContactRowStatusIndex" required="true" %>

<c:set var="awardPersonUnitAttributes" value="${DataDictionary.AwardPersonUnit.attributes}" />
<c:set var="isPrincipalInvestigator" value="${awardContact.principalInvestigator}" />

<kul:innerTab tabTitle="Unit Details" parentTab="${awardContact.fullName}" defaultOpen="false" 
				tabErrorKey="document.award[${awardPersonUnitRowStatus.index}].awardContact*,projectPersonnelBean.newAwardPersonUnit">
	<table>
		<tr>
			<th class="infoline">
				<div align="center">&nbsp;</div>
			</th>
			<c:if test="${isPrincipalInvestigator}" >
				<th class="infoline">
					<div align="center">*Lead Unit</div>
				</th>
			</c:if>
			<th class="infoline">
				<div align="center">Unit Name</div>
			</th>
			<th class="infoline">
				<div align="center">Unit Number</div>
			</th>
			<th class="infoline">
				<div align="center">Actions</div>
			</th>
		</tr>
		<tr>
			<th class="infoline" scope="row">Add:</th>
			<c:if test="${isPrincipalInvestigator}" >
				<th class="infoline">
					<div align="center">
					<kul:htmlControlAttribute property="projectPersonnelBean.newAwardPersonUnit.leadUnit" 
												attributeEntry="${awardPersonUnitAttributes.leadUnit}" />
					</div>
				</th>
			</c:if>
			<th class="infoline">
				<div align="center">
					<c:choose>                  
						<c:when test="${empty KualiForm.projectPersonnelBean.newAwardPersonUnit.unit}">
							<div>
								<label><span style="margin-right: 3;">(select)</span></label>
								<kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:projectPersonnelBean.newUnitNumber" 
  											anchor="${tabKey}" lookupParameters="projectPersonnelBean.newUnitNumber:unitNumber"/>
		  	 				</div>
						</c:when>
						<c:otherwise>
							<div align="center">
	              				<label>
	              				<c:out value="${KualiForm.projectPersonnelBean.newAwardPersonUnit.unit.unitName}" />
	              				</label>										            			
							</div>
						</c:otherwise>
					</c:choose>					
				</div>
			</th>
			<th class="infoline">
				<div align="center">
					<c:choose>                  
						<c:when test="${empty KualiForm.projectPersonnelBean.newAwardPersonUnit.unit}">
							&nbsp;
						</c:when>
						<c:otherwise>
							<div align="center">
	              				<label>
	              				<c:out value="${KualiForm.projectPersonnelBean.newAwardPersonUnit.unit.unitNumber}" />
	              				</label>
																				            			
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</th>			
			<th class="infoline">
				<c:choose>
					<c:when test="${not empty KualiForm.projectPersonnelBean.newAwardPersonUnit.unit}">
		        		<div align="center">	        			
		        			<html:image property="methodToCall.addNewProjectPersonUnit.line${awardContactRowStatusIndex}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Contact" alt="Add Contact" styleClass="tinybutton" />
		        			<html:image property="methodToCall.clearNewProjectPersonUnit" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Fields" alt="Clear Fields" styleClass="tinybutton" />
		        		</div>
		        	</c:when>
		        	<c:otherwise>&nbsp;</c:otherwise>
		        </c:choose>
			</th>
		</tr>
		
		<c:forEach var="awardPersonUnit" items="${awardContact.units}" varStatus="awardPersonUnitRowStatus">
			<tr>
				<th class="infoline" scope="row">
					<c:out value="${awardPersonUnitRowStatus.index + 1}" />
				</th>
				<c:if test="${isPrincipalInvestigator}">
	                <td valign="middle">
	                	<div align="center">
	                		<html:radio property="selectedLeadUnit" value="${awardPersonUnit.unit.unitName}"/>	                		
						</div>
					</td>
				</c:if>
                <td valign="middle">
                	<div align="center">
                		${awardPersonUnit.unit.unitName}&nbsp;
                		<kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="'${awardPersonUnit.unit.unitNumber}':unitNumber" anchor="${tabKey}" />
                	</div> 
				</td>
				<td valign="middle">
                	<div align="center">
						${awardPersonUnit.unit.unitNumber}&nbsp;						
					</div>
				</td>
				<td class="infoline">
					<div align="center">
						<html:image property="methodToCall.deleteProjectPersonUnit.${awardContactRowStatusIndex}.line${awardPersonUnitRowStatus.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
		</c:forEach>
	</table>
</kul:innerTab>