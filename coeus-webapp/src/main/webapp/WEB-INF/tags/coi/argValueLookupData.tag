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

<%@ attribute name="valueIndex" required="true" %>
<%@ attribute name="bean" required="true" type="org.kuali.kra.coi.personfinancialentity.FinEntityDataMatrixBean" %>
<%@ attribute name="property" required="true" %>
        ${kfunc:registerEditableProperty(KualiForm, property)}
                        <jsp:useBean id="paramMap" class="java.util.HashMap"/>
                        <c:set target="${paramMap}" property="argName" value="${bean.lookupArgument}" />

                            <html:select property="${property}" tabindex="0">
                                <c:forEach items="${krafn:getOptionList('org.kuali.coeus.common.impl.custom.arg.ArgValueLookupValuesFinder', paramMap)}" var="option">
                                    <c:choose>                      
                                        <c:when test="${bean.relationshipTypeBeans[valueIndex].stringValue == option.key}">
                                            <option value="${option.key}" selected>${option.value}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${option.key}">${option.value}</option>
                                        </c:otherwise>
                                    </c:choose>                    
                                </c:forEach>
                            </html:select>
