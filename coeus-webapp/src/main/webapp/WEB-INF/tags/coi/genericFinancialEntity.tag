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
<%@ attribute name="idx" required="true" description="Coi disl project list index" %>
<%@ attribute name="disclProject" required="true" type="org.kuali.kra.coi.CoiDisclProject" %>
<%@ attribute name="boLocation" required="true" description="Location of the BO on the form such that it can be referenced by htmlControlAttribute tags" %>

<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:set var="financialEntityAttributes" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="readOnly" value="${!KualiForm.disclosureHelper.canEditDisclosureFinancialEntity}" />

		<kra-coi:projectHeader disclProject="${disclProject}" boLocation="${boLocation}"/>
<div id="div_FinancialEntity${idx}" class="div_FinancialEntity" style="${KualiForm.document.coiDisclosureList[0].coiDisclosureEventType.excludeFinancialEntities ? 'display:none' : ''}">
	<h3>
		<span class="subhead-left"> 
   			<a href="#" id ="financialEntityControl${idx}" class="financialEntitySubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> 
    		<c:if test="${not disclProject.complete}">
      			<img src="${ConfigProperties.kra.externalizable.images.url}exclamation.png" style="border:none; width:16px; height:16px; vertical-align:middle;" label="Incomplete Project">
    		</c:if>
 			Financial Entities (${disclProject.completeMessage})
 		</span>
		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
  	</h3>
    <div  id="financialEntityContent${idx}" class="financialEntitySubpanelContent">
		<table id="protocol-table" cellpadding="0" cellspacing="0" summary="">
			<thead>
			<tr><kul:htmlAttributeHeaderCell literalLabel="Review" scope="col" /> 
				<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.personFinIntDisclosureId}" scope="col" />
				<th rowspan="1" colspan="1" scope="col"><c:out value="${KualiForm.disclosureHelper.conflictHeaderLabel}"/></th>
				<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.comments}" scope="col" />
			</tr>
			</thead>
			<tbody> 
			<c:if test="${!readOnly}">
         		<tr>
					<th class="infoline">&nbsp;</th>
					<td class="infoline" style="text-align:center;">
						<html:image property="methodToCall.newFinancialEntity.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-newfinancialentity.gif' styleClass="tinybutton"/>
           			</td>
           			<td style="text-align:center;" valign="middle"  class="infoline">
           				<c:if test="${not empty disclProject.coiDiscDetails}">
						<select onchange="jQuery(this).parents('table').first().find('select.related').val(jQuery(this).val());">
							<c:forEach items="${krafn:getOptionList('org.kuali.kra.coi.disclosure.CoiDispositionStatusValuesFinder', null)}" var="option">
		                        <option value="${option.key}">${option.value}</option>
				            </c:forEach>
						</select>
						</c:if>
					</td>
            		<td align="left" valign="middle" class="infoline">&nbsp;</td>
        		</tr>
			</c:if>
			<c:forEach var="disclosureDetail" items="${disclProject.coiDiscDetails}" varStatus="festatus">
        		<tr>
 					<td style="text-align: center;">&nbsp;
						<c:if test="${KualiForm.disclosureHelper.canViewDisclosureFeHistory}">		
                  			<a class="disclosureFeView" id="viewEntitySummary${festatus.index}" title="${disclosureDetail.personFinIntDisclosure.entityName} Summary" href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=viewFinancialEntity&status=activecoi&index=${disclosureDetail.personFinIntDisclosure.personFinIntDisclosureId}" scrolling="no" noresize>
  								<html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton" title="View Entity"/>
      	        			</a>   
      	    			</c:if>         
						<c:if test="${KualiForm.disclosureHelper.canEditDisclosureFinancialEntity}">		
							<html:image property="methodToCall.editFinancialEntity.line${festatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton"/>
      	     			</c:if>
						<c:if test="${KualiForm.disclosureHelper.canViewDisclosureFeHistory}">		
							<a class="disclosureFeHistory" id="history${festatus.index}" title="${disclosureDetail.personFinIntDisclosure.entityName} History" href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=showFinancialEntityHistory&status=activecoi&index=${disclosureDetail.personFinIntDisclosure.personFinIntDisclosureId}">
 								<html:image property="methodToCall.historyFinancialEntity.line${festatus.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-history.gif' styleClass="tinybutton"/>
      	         			</a>
      	     			</c:if>
           			</td>
             		<td style="text-align: left;" valign="middle">
           				<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${idx}].coiDiscDetails[${festatus.index}].personFinIntDisclosure.entityName" readOnly="true" attributeEntry="${financialEntityAttributes.entityName}" /> 
           			</td>
             		<td style="text-align: left;" valign="middle">
           				<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${idx}].coiDiscDetails[${festatus.index}].entityDispositionCode" 
  							readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.entityDispositionCode}" styleClass="related" />
					</td>
             		<td style="text-align: left;" valign="middle">
           				<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${idx}].coiDiscDetails[${festatus.index}].comments" 
  							readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.comments}" />
 					</td>
        		</tr>
        	</c:forEach>
        	</tbody>
		</table> <%-- fe table --%>
	</div>
</div>
