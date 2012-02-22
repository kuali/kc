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

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<script type='text/javascript' src='dwr/interface/KraPersonService.js'></script>
<script type='text/javascript' src='dwr/interface/PersonService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<c:set var="awardUnitContactAttributes"
	value="${DataDictionary.AwardUnitContact.attributes}" />
<c:set var="awardContactAttributes" value="${DataDictionary.AwardContact.attributes}" />
<c:set var="award" value="${KualiForm.document.award}" />

<%-- kra:section permission="modifyAward" --%>
<kul:tab defaultOpen="false"
	tabItemCount="${KualiForm.unitContactsBean.unitContactsCount}"
	tabTitle="Unit Contacts"
	tabErrorKey="unitContactsBean.newAwardContact.*,unitContactsBean.unitContact.unitAdministratorTypeCode,document.awardList[0].awardUnitContacts*">
	<div class="tab-container" align="center">
	<h3><span class="subhead-left">Unit Contacts</span>  		
	<span class="subhead-right"><kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardUnitContactsHelpUrl" altText="help"/></span>
	</h3>
	<table id="contacts-table" cellpadding="0" cellspacing="0" summary="Unit Contacts">
		<tr>
			<th scope="row" width="5%">&nbsp;</th>
			<th width="15%">*Person</th>
			<th width="15%">Unit</th>
			<th width="20%">*Project Role</th>
			<th width="15%">Office Phone</th>
			<th width="15%">Email</th>
			<th width="15%">
			<div align="center">Actions</div>
			</th>
		</tr>

		<c:if test="${!readOnly}">
			<tr>
				<th class="infoline" scope="row">Add</th>
				<td nowrap class="grid" class="infoline">
				Employee User Name: 
				<kul:htmlControlAttribute
					property="unitContactsBean.newAwardContact.fullName"
					attributeEntry="${awardContactAttributes.fullName}"
					onblur="loadContactPersonName('unitContactsBean.newAwardContact.fullName',
	                               				 			'fullName.div',
	                	        				     		'unitNumber',
	                	        				  			'phoneNumber',
           	        							  			'emailAddress',
           	        							  			'personId');"
					readOnly="${readOnly}" /> <c:if test="${!readOnly}">
					<kul:lookup boClassName="org.kuali.kra.bo.KcPerson"
						fieldConversions="personId:unitContactsBean.personId"
						lookupParameters="unitContactsBean.personId:personId"
						anchor="${tabKey}" />
						
				</c:if> <c:if test="${readOnly}">
					<html:hidden styleId ="fullName" property="unitContactsBean.newAwardContact.fullName" />
				</c:if>

				${kfunc:registerEditableProperty(KualiForm, "unitContactsBean.personId")}
				<html:hidden styleId ="personId" property="unitContactsBean.personId" />

				<div id="fullName.div">&nbsp; <c:if
					test="${!empty KualiForm.unitContactsBean.newAwardContact.contact}">
					<c:choose>
						<c:when
							test="${empty KualiForm.unitContactsBean.newAwardContact.contact}">
							<span style='color: red;'>not found</span>
						</c:when>
						<c:otherwise>
							<c:out
								value="${KualiForm.unitContactsBean.newAwardContact.contact.fullName}" />
						</c:otherwise>
					</c:choose>
				</c:if></div>

				</td>
				<td id="unitNumber" class="infoline"><%--<div align="center">
	        			<c:out value="${KualiForm.unitContactsBean.newAwardContact.unitAdministratorUnitNumberByPersonId}" />&nbsp;
	        		</div>--%> &nbsp;</td>
				<td class="infoline" style="font-size: 80%">
				<div align="center"><kul:htmlControlAttribute
					property="unitContactsBean.unitContact.unitAdministratorTypeCode"
					attributeEntry="${awardUnitContactAttributes.unitAdministratorTypeCode}" />
				</div>
				</td>
				<td id="phoneNumber" class="infoline"><c:out
					value="${KualiForm.unitContactsBean.newAwardContact.contact.phoneNumber}" />&nbsp;
				</td>
				<td id="emailAddress" class="infoline"><c:out
					value="${KualiForm.unitContactsBean.newAwardContact.contact.emailAddress}" />&nbsp;
				</td>

				<td class="infoline">
				<div align="center"><html:image
					property="methodToCall.addUnitContact"
					src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif"
					title="Add Contact" alt="Add Contact" styleClass="tinybutton" /></div>
				</td>
			</tr>
		</c:if>
		

		<c:forEach var="awardContact"
			items="${KualiForm.unitContactsBean.unitContacts}"
			varStatus="awardContactRowStatus">
			<c:set var="isDefaultUnitContact"
				value="${awardContact.unitAdministratorUnitNumber != null}" />
			<tr>
				<th class="infoline" scope="row"><c:out
					value="${awardContactRowStatus.index + 1}" /></th>
				<td valign="middle">
				<div align="center"><input type="hidden"
					name="unit_contact.identifier_${awardContactRowStatus.index}"
					value="${awardContact.contact.identifier}" />
				${awardContact.fullName}&nbsp; <kul:directInquiry
					boClassName="org.kuali.kra.bo.KcPerson"
					inquiryParameters="unit_contact.identifier_${awardContactRowStatus.index}:personId"
					anchor="${tabKey}" /></div>
				</td>
				<td valign="middle">
				<div align="center"><input type="hidden"
					name="unit_contact.orgNumber_${awardContactRowStatus.index}"
					value="${awardContact.organizationIdentifier}" />
				${awardContact.unitNumberForDisplay}&nbsp; <kul:directInquiry
					boClassName="org.kuali.kra.bo.Unit"
					inquiryParameters="unit_contact.orgNumber_${awardContactRowStatus.index}:unitNumber"
					anchor="${tabKey}" /></div>
				</td>
				<td valign="middle"><c:choose>
					<c:when test="${not isDefaultUnitContact}">
						<div align="center"><kul:htmlControlAttribute
							property="unitContactsBean.unitContacts[${awardContactRowStatus.index}].unitAdministratorTypeCode"
							attributeEntry="${awardUnitContactAttributes.unitAdministratorTypeCode}"
							readOnlyAlternateDisplay="${awardContact.unitAdministratorType.description}" />
						</div>
					</c:when>
					<c:otherwise>
						<c:out
							value="${KualiForm.unitContactsBean.unitContacts[awardContactRowStatus.index].unitAdministratorType.description}" />
					</c:otherwise>
				</c:choose></td>
				<td valign="middle">
				<div align="center">${awardContact.phoneNumber}&nbsp;</div>
				</td>
				<td valign="middle">
				<div align="center">${awardContact.emailAddress}&nbsp;</div>
				</td>

				<td><c:if test="${not isDefaultUnitContact}">
					<c:set var="deleteButton" value="tinybutton-delete1.gif" />
				</c:if> <c:if test="${isLeadUnit}">
					<c:set var="deleteButton" value="tinybutton-delete2.gif" />
				</c:if>
				<div align="center"><c:if test="${!readOnly}">
					<c:if test="${!isLeadUnit}">
						<html:image
							property="methodToCall.deleteUnitContact.line${awardContactRowStatus.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif'
							styleClass="tinybutton" disabled="${isLeadUnit}" />
					</c:if>
				</c:if> <c:if test="${readOnly}">&nbsp;</c:if></div>
				</td>
			</tr>
		</c:forEach>

		<tr>
			<th colspan="7" align="center" scope="row">
			<div align="center"><c:if test="${!readOnly}">
				<html:image
					property="methodToCall.syncDefaultUnitContactsToLeadUnit.anchor${tabKey}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-sync_lead_unit.jpg'
					styleClass="tinybutton" />
			</c:if> <c:if test="${readOnly}">&nbsp;</c:if></div>
			</th>
		</tr>
	</table>
	</div>
</kul:tab>
