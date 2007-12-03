 <%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/proposaldevelopment/proposalPerson.jsp"%>

<c:set var="personDegreeAttributes" value="${DataDictionary.ProposalPersonDegree.attributes}" />
<c:set var="degree" value="newProposalPersonDegree[${personIndex}]" />
<c:set var="readOnly" value="false" />
<c:set var="actionImage" value="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" />
<c:set var="actionMethod" value="insertDegree.personIndex${personIndex}" />
<c:set var="actionTitle" value="Add a Degree" />
<c:set var="degreeIndexHeader" value="add:" />
<c:set var="degreeCodeProperty" value="${degree}.degreeCode" />
<c:set var="degreeCodeAttribute" value="${DataDictionary.ProposalPersonDegree.attributes.degreeCode}" />
<c:set var="tdClass" value="infoline" />
<c:if test="${!empty index}">
  <c:set var="degreeIndexHeader" value="${index + 1}" />
  <c:set var="degree" value="${proposalPerson}.proposalPersonDegree[${index}]" /> 
  <c:set var="readOnly" value="true" />
  <c:set var="actionTitle" value="Remove a Degree" />
  <c:set var="actionMethod" value="deleteDegree.${proposalPerson}.line${index}" />
  <c:set var="actionImage" value="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" />
  <c:set var="degreeCodeProperty" value="${degree}.degreeType.description" />
  <c:set var="degreeCodeAttribute" value="${DataDictionary.DegreeType.attributes.description}" />
<c:set var="tdClass" value="" />
</c:if>
                  <tr>
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
                    <td class="${tdClass}"><div align=center><html:image property="methodToCall.${actionMethod}" src="${actionImage}" title="${actionTitle}" alt="${actionTitle}" styleClass="tinybutton"/></div></td>
                  </tr>
