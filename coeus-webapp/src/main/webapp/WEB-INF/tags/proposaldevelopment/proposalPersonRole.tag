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

<c:choose>
    <c:when test="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].role.readOnly or readOnly or isParent}">
        ${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].role.description}
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${not proposalPersonAttributes.proposalPersonRoleId.control.select}">
                <kul:htmlControlAttribute property="${proposalPerson}.proposalPersonRoleId" attributeEntry="${proposalPersonAttributes.proposalPersonRoleId}" />
            </c:when>
            <c:otherwise>
            <%-- // START SNIPPET: jstlFunctionJspExample1 --%>
                <jsp:useBean id="paramMap" class="java.util.HashMap"/>
                <c:set target="${paramMap}" property="forAddedPerson" value="true" />
            <%-- // END SNIPPET: jstlFunctionJspExample1 --%>

                <kul:checkErrors keyMatch="${proposalPerson}.proposalPersonRoleId" auditMatch="${proposalPerson}.proposalPersonRoleId"/>  
                <c:set var="roleStyle" value=""/>
                <c:if test="${hasErrors==true}">
                    <c:set var="roleStyle" value="background-color:#FFD5D5"/>
                </c:if>

                <%-- // START SNIPPET: jstlFunctionJspExample2 --%>
                <html:select property="${proposalPerson}.proposalPersonRoleId" tabindex="0" style="${roleStyle}">
                <c:forEach items="${krafn:getOptionList('org.kuali.coeus.propdev.impl.person.ProposalPersonRoleValuesFinder', paramMap)}" var="option">
                <c:choose>
                    <c:when test="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].proposalPersonRoleId == option.key}">
                    <option value="${option.key}" selected>${option.value}</option>
                    </c:when>
                    <c:otherwise>
                    <option value="${option.key}">${option.value}</option>
                    </c:otherwise>
                </c:choose>
                </c:forEach>
                </html:select>
                <%-- // END SNIPPET: jstlFunctionJspExample2 --%>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
