<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

 <%@ attribute name="proposalPerson" description="The ProposalPerson which this is for." required="true" %>
 <%@ attribute name="index" description="Index of the property for a ProposalPerson" required="false" %>
 <%@ attribute name="personIndex" description="Index of a ProposalPerson" required="true" %>

 <c:set var="proposalPersonAttributes" value="${DataDictionary.ProposalPerson.attributes}" />
 <c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" />
 <c:set var="isParent" value="${KualiForm.document.developmentProposalList[0].parent}" />

 <c:set var="personDegreeAttributes" value="${DataDictionary.ProposalPersonDegree.attributes}" />
<c:set var="degree" value="newProposalPersonDegree[${personIndex}]" />
<c:set var="readOnly" value="false" />
<c:set var="actionImage" value="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif"/>
<c:set var="actionMethod" value="insertDegree.${proposalPerson}.line${status.index}" />
<c:set var="actionTitle" value="Add a Degree" />
<c:set var="actionStyle" value="addButton"/>
<c:set var="rowStyle" value="addline"/>
<c:set var="degreeIndexHeader" value="Add:" />
<c:set var="degreeCodeProperty" value="${degree}.degreeCode" />
<c:set var="degreeCodeAttribute" value="${DataDictionary.ProposalPersonDegree.attributes.degreeCode}" />
<c:set var="tdClass" value="infoline" />
<c:if test="${!empty index}">
  <c:set var="degreeIndexHeader" value="${index + 1}" />
  <c:set var="degree" value="${proposalPerson}.proposalPersonDegrees[${index}]" /> 
  <c:set var="readOnly" value="true" />
  <c:set var="actionTitle" value="Remove a Degree" />
  <c:set var="actionMethod" value="deleteDegree.${proposalPerson}.line${index}" />
  <c:set var="actionImage" value="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif"/>
  <c:set var="actionStyle" value=""/>
  <c:set var="rowStyle" value=""/>
  <c:set var="degreeCodeProperty" value="${degree}.degreeType.description" />
  <c:set var="degreeCodeAttribute" value="${DataDictionary.DegreeType.attributes.description}" />
<c:set var="tdClass" value="" />
</c:if>
                  <tr class="${rowStyle}">
                    <th scope="row" align="center">${degreeIndexHeader}</th>

                    <td class="${tdClass}"><div align=left><span class="copy">
                    <kul:htmlControlAttribute property="${degreeCodeProperty}" attributeEntry="${degreeCodeAttribute}" readOnly="${readOnly}" /> 
                      </span></div>
                        <span class="fineprint"></span> </td>
                    <td class="${tdClass}"><kul:htmlControlAttribute property="${degree}.degree" attributeEntry="${personDegreeAttributes.degree}" readOnly="${readOnly}" /></td>
                    <td class="${tdClass}"><kul:htmlControlAttribute property="${degree}.graduationYear" attributeEntry="${personDegreeAttributes.graduationYear}" readOnly="${readOnly}" />

                    </td>
                    <td class="${tdClass}"><div align=left>
                        <kul:htmlControlAttribute property="${degree}.school" attributeEntry="${personDegreeAttributes.school}" readOnly="${readOnly}" />
                      </div>

                        <span class="fineprint"></span> </td>
                    <td class="${tdClass}">
                    <div align=center>&nbsp;
                    <kra:section permission="modifyProposal">
                    <c:if test="${ not isParent }">
                    	<html:image property="methodToCall.${actionMethod}" src="${actionImage}" title="${actionTitle}" alt="${actionTitle}" styleClass="tinybutton ${actionStyle}"/>
                    </c:if>
                    </kra:section>
                    </div>
                    </td>
                  </tr>
