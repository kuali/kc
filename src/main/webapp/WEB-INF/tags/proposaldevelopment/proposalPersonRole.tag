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

<c:choose>
    <c:when test="${KualiForm.document.proposalPersons[personIndex].role.readOnly or readOnly}">
        ${KualiForm.document.proposalPersons[personIndex].role.description}
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${not attributeEntry.control.select}">
                <kul:htmlControlAttribute property="${proposalPerson}.proposalPersonRoleId" attributeEntry="${proposalPersonAttributes.proposalPersonRoleId}" />
            </c:when>
            <c:otherwise>
                <jsp:useBean id="paramMap" class="java.util.HashMap"/>
                <c:set target="${paramMap}" property="forAddedPerson" value="true" />
                <kul:checkErrors keyMatch="${proposalPerson}.proposalPersonRoleId" auditMatch="${proposalPerson}.proposalPersonRoleId"/>  
                <c:set var="roleStyle" value=""/>
                <c:if test="${hasErrors==true}">
                    <c:set var="roleStyle" value="background-color:#FFD5D5"/>
                </c:if>
                <html:select property="${proposalPerson}.proposalPersonRoleId" tabindex="0" style="${roleStyle}">
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.proposaldevelopment.lookup.keyvalue.ProposalPersonRoleValuesFinder', paramMap)}" var="option">
                <c:choose>
                    <c:when test="${KualiForm.document.proposalPersons[personIndex].proposalPersonRoleId == option.key}">
                    <option value="${option.key}" selected>${option.label}</option>
                    </c:when>
                    <c:otherwise>
                    <option value="${option.key}">${option.label}</option>
                    </c:otherwise>
                </c:choose>
                </c:forEach>
                </html:select>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>