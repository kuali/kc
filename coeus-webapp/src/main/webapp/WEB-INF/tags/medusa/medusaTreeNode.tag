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

<%@ attribute name="node" required="true" type="org.kuali.coeus.common.framework.medusa.MedusaNode"%>
<%@ attribute name="openned" required="true" type="java.lang.Boolean"%>


<li class="open" style="text-align: left;">
    <c:set var="hideOpen" value=""/>
    <c:set var="currentDoc" value="false"/>

    <c:choose>
        <c:when test="${node.type == 'IP'}">
            <c:if test="${KualiForm.medusaBean.moduleName == node.type && KualiForm.medusaBean.moduleIdentifier == node.bo.proposalId}">
                <c:set var="hideOpen" value="hideOpen"/>
                <c:set var="currentDoc" value="true"/>
            </c:if>
            <span class="medusaNode"><a name="${node.type}-${node.bo.proposalId}" class="${hideOpen}"><img src="static/images/instituteproposal12.gif"/>Institutional Proposal ${node.bo.proposalNumber}</a></span><!-- hack for treeview --> <a></a>
        </c:when>
        <c:when test="${node.type == 'award'}">
            <c:if test="${KualiForm.medusaBean.moduleName == node.type && KualiForm.medusaBean.moduleIdentifier == node.bo.awardId}">
                <c:set var="hideOpen" value="hideOpen"/>
                <c:set var="currentDoc" value="true"/>
            </c:if>
            <c:choose>
                <c:when test="${node.bo.awardStatus.statusCode == 1}">
                    <c:set var="statusImg" value="static/images/award_active.gif"/>
                    <c:set var="statusNm" value="Active"/>
                </c:when>
                <c:when test="${node.bo.awardStatus.statusCode == 2}">
                    <c:set var="statusImg" value="static/images/award_inactive.gif"/>
                    <c:set var="statusNm" value="Inactive"/>
                </c:when>
                <c:when test="${node.bo.awardStatus.statusCode == 3}">
                    <c:set var="statusImg" value="static/images/award_pending.gif"/>
                    <c:set var="statusNm" value="Pending"/>
                </c:when>
                <c:when test="${node.bo.awardStatus.statusCode == 4}">
                    <c:set var="statusImg" value="static/images/award_inactive.gif"/>
                    <c:set var="statusNm" value="Terminated"/>
                </c:when>
                <c:when test="${node.bo.awardStatus.statusCode == 5}">
                    <c:set var="statusImg" value="static/images/award_inactive.gif"/>
                    <c:set var="statusNm" value="Closed"/>
                </c:when>
                <c:when test="${node.bo.awardStatus.statusCode == 6}">
                    <c:set var="statusImg" value="static/images/award_holding.gif"/>
                    <c:set var="statusNm" value="Hold"/>
                </c:when>
                <c:otherwise><c:set var="statusImg" value="static/images/award_inactive.gif"/></c:otherwise>
            </c:choose>
            <span class="medusaNode"><a name="${node.type}-${node.bo.awardId}" class="${hideOpen}"><img src="static/images/sponsor12.gif"/><img src="${statusImg}" alt="${statusNm}" title="${statusNm}"/>Award ${node.bo.awardNumber}</a></span><!-- hack for treeview --><a></a>
        </c:when>
        <c:when test="${node.type == 'DP'}">
            <c:if test="${KualiForm.medusaBean.moduleName == node.type && KualiForm.medusaBean.moduleIdentifier == node.bo.proposalNumber}">
                <c:set var="hideOpen" value="hideOpen"/>
                <c:set var="currentDoc" value="true"/>
            </c:if>
            <span class="medusaNode"><a name="${node.type}-${node.bo.proposalNumber}" class="${hideOpen}"><img src="static/images/developmentproposal12.gif" />Development Proposal ${node.bo.proposalNumber}</a></span><!-- hack for treeview --><a></a>
        </c:when>
        <c:when test="${node.type == 'neg'}">
            <c:if test="${KualiForm.medusaBean.moduleName == node.type && KualiForm.medusaBean.moduleIdentifier == node.bo.negotiationId}">
                <c:set var="hideOpen" value="hideOpen"/>
                <c:set var="currentDoc" value="true"/>
            </c:if>
            <span class="medusaNode"><a name="${node.type}-${node.bo.negotiationId}" class="${hideOpen}"><img src="static/images/negotiations12.png"/>Negotiation ${node.bo.negotiationId}</a></span><!-- hack for treeview --><a></a>
        </c:when>
        <c:when test="${node.type == 'subaward'}">
            <c:if test="${KualiForm.medusaBean.moduleName == node.type && KualiForm.medusaBean.moduleIdentifier == node.bo.subAwardId}">
                <c:set var="hideOpen" value="hideOpen"/>
                <c:set var="currentDoc" value="true"/>
            </c:if>
            <span class="medusaNode"><a name="${node.type}-${node.bo.subAwardCode}" class="${hideOpen}"><img src="static/images/sponsor12.gif"/>Subaward ${node.bo.subAwardCode}</a></span><a></a>
        </c:when>
        <c:when test="${node.type == 'irb'}">
            <c:if test="${KualiForm.medusaBean.moduleName == node.type && KualiForm.medusaBean.moduleIdentifier == node.bo.protocolId}">
                <c:set var="hideOpen" value="hideOpen"/>
                <c:set var="currentDoc" value="true"/>
            </c:if>
            <span class="medusaNode"><a name="${node.type}-${node.bo.protocolId}" class="${hideOpen}"><img src="static/images/protocol12.gif"/>Protocol ${node.bo.protocolNumber}</a></span><a></a>
        </c:when>
        <c:when test="${node.type == 'iacuc'}">
            <c:if test="${KualiForm.medusaBean.moduleName == node.type && KualiForm.medusaBean.moduleIdentifier == node.bo.protocolId}">
                <c:set var="hideOpen" value="hideOpen"/>
                <c:set var="currentDoc" value="true"/>
            </c:if>
            <span class="medusaNode"><a name="${node.type}-${node.bo.protocolId}" class="${hideOpen}"><img src="static/images/protocol12.gif"/>Iacuc Protocol ${node.bo.protocolNumber}</a></span><a></a>
        </c:when>

    </c:choose>

    <c:choose>
        <c:when test="${currentDoc && !openned}">
            <div class="medusaDetails medusaDetailsLoaded">
                <kra-m:medusaNodeView node="${node}"/>
            </div>
            <c:set var="openned" value="true" scope="request"/>
        </c:when>
        <c:otherwise>
            <div class="medusaDetails" style="display:none;"></div>
        </c:otherwise>
    </c:choose>


    <c:if test="${not empty node.childNodes}">
        <ul>
            <c:forEach items="${node.childNodes}" var="childNode">
                <c:set var="_node" value="${childNode}" scope="request" />
                <c:set var="_openned" value="${openned}" scope="request" />
                <c:import url="/WEB-INF/jsp/medusa/recurseTreeNode.jsp" />
            </c:forEach>
        </ul>
    </c:if>
</li>

