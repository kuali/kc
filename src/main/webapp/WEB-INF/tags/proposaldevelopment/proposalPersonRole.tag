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
<%@ include file="/WEB-INF/jsp/proposaldevelopment/proposalPerson.jsp"%>

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
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.proposaldevelopment.lookup.keyvalue.ProposalPersonRoleValuesFinder', paramMap)}" var="option">
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
