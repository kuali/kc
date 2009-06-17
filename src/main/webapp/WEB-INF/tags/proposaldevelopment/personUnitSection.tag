  <%--
 Copyright 2006-2009 The Kuali Foundation

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
<%@ include file="/WEB-INF/jsp/proposaldevelopment/proposalPerson.jsp"%>

<c:set var="personUnitAttributes" value="${DataDictionary.ProposalPersonUnit.attributes}" />
<c:set var="unitAttributes" value="${DataDictionary.Unit.attributes}" />
<c:set var="piRole" value="<%= org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE %>" />
<bean:define id="currentPerson" name="KualiForm" property="${proposalPerson}" />

                <tbody id="G3">
                  <tr>
                    <th width="10%">&nbsp;</th>
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.Unit.attributes.unitName" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.Unit.attributes.unitNumber" />
             
                    <th><div align="center">Actions</div></th>
                  </tr>
                  
                  <kra:section permission="modifyProposal">
                  <tr>
                    <th scope="row" align="center">Add:</th>
                    <td class="infoline">
                     <html:hidden property="newProposalPersonUnit[${personIndex}].unitName" />
   					<div id="newProposalPersonUnit[${personIndex}].unitName.div" class="same-line">
                     <c:choose>
                      <c:when test="${empty KualiForm.newProposalPersonUnit[personIndex].unitName}" >
                      (select)
                      </c:when>
                      <c:otherwise>
                         ${KualiForm.newProposalPersonUnit[personIndex].unitName}
                      </c:otherwise>
                     </c:choose> 
                      </div>
                     &nbsp; <kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:newProposalPersonUnit[${personIndex}].unitNumber,unitName:newProposalPersonUnit[${personIndex}].unitName" />
                        <span class="fineprint"></span> </td>

                    <td class="infoline"><div align=left>
                      <kul:htmlControlAttribute attributeEntry="${unitAttributes.unitNumber}" property="newProposalPersonUnit[${personIndex}].unitNumber" onblur="loadUnitName('newProposalPersonUnit[${personIndex}].unitNumber');" />
                      </div>
                        <span class="fineprint"></span> </td>
                    <td class="infoline"><div align=center><html:image property="methodToCall.insertUnit.${proposalPerson}.line${status.index}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Unit" alt="Add Unit" styleClass="tinybutton"/></div></td>
                  </tr>
                  </kra:section>
                  
  				<c:forEach var="aUnit" items="${currentPerson.units}" varStatus="status">
	                  <tr>
	                    <th scope="row"  align="center"><c:out value="${status.index + 1}" /></th>
	
	                    <td><kul:htmlControlAttribute attributeEntry="${unitAttributes.unitName}" property="${proposalPerson}.unit[${status.index}].unit.unitName" readOnly="true" /></td>
	                    
	                    <td><kul:htmlControlAttribute attributeEntry="${unitAttributes.unitNumber}" property="${proposalPerson}.unit[${status.index}].unitNumber" readOnly="true"/></td>
	                    <td>
	                    	<div align=center>
		                    	<kra:section permission="modifyProposal">
			                    	<c:choose>
			                    		<c:when test="${(currentPerson.proposalPersonRoleId == piRole && aUnit.unitNumber != KualiForm.document.ownedByUnitNumber) || (currentPerson.proposalPersonRoleId != piRole)}">
			                    			<html:image property="methodToCall.deleteUnit.${proposalPerson}.line${status.index}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" title="Remove Unit" alt="Remove Unit" styleClass="tinybutton" />
			                    		</c:when>
			                    		<c:otherwise>
			                    			&nbsp;
			                    		</c:otherwise>
			                    	</c:choose>
		                    	</kra:section>
	                    	</div>
	                    </td>
	                  </tr>
  				</c:forEach>
  				
                </tbody>
