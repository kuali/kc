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

<%@ attribute name="node" required="true" type="org.kuali.coeus.common.framework.medusa.MedusaNode"%>

<div id="medusaNewDetails">
<c:choose>
<c:when test="${node.type == 'IP'}">
  <kra-m:medusaInstPropSummary node="${node}"/>
</c:when>
<c:when test="${node.type == 'award'}">
  <kra-m:medusaAwardSummary node="${node}"/>
</c:when>
<c:when test="${node.type == 'DP'}">
  <kra-m:medusaDevPropSummary node="${node}"/>
</c:when>
<c:when test="${node.type == 'neg'}">
  <kra-m:medusaNegotiationSummary node="${node}"/>
</c:when>
<c:when test="${node.type == 'subaward'}">
  <kra-m:medusaSubAwardSummary node="${node}"/>
</c:when>
<c:when test="${node.type == 'irb'}">
  <kra-m:medusaProtocolSummary node="${node}"/>
</c:when>
<c:when test="${node.type == 'iacuc'}">
  <kra-m:medusaIacucSummary node="${node}"/>
</c:when>
</c:choose>
</div>


