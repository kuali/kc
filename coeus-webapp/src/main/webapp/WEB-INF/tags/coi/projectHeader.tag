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
<%@ attribute name="boLocation" required="true" description="Location of the BO on the form such that it can be referenced by htmlControlAttribute tags" %>
<%@ attribute name="style" required="false" description="style for current project" %>
<%@ attribute name="disclProject" required="true" type="org.kuali.kra.coi.CoiDisclProject" %>

<style>
.dispositionSelect {
	font-size: 90%;
}
</style>
<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:choose>
	<c:when test="${disclProject.awardEvent}">
		<c:set var="projectId" value="${disclProject.award.awardNumber}"/>
	</c:when>
	<c:when test="${disclProject.proposalEvent}">
		<c:set var="projectId" value="${disclProject.proposal.proposalNumber}"/>
	</c:when>
	<c:when test="${disclProject.institutionalProposalEvent}">
		<c:set var="projectId" value="${disclProject.institutionalProposal.proposalNumber}"/>
	</c:when>
	<c:when test="${disclProject.protocolEvent}">
		<c:set var="projectId" value="${disclProject.protocol.protocolNumber}"/>
	</c:when>		
	<c:when test="${disclProject.iacucProtocolEvent}">
		<c:set var="projectId" value="${disclProject.iacucProtocol.protocolNumber}"/>
	</c:when>		
	<c:otherwise>
		<c:set var="projectId" value="${disclProject.projectId}"/>
	</c:otherwise>
</c:choose>
<div>
	<h3>
		<span class="subhead-left" style="${style}">${disclProject.coiDisclosureEventType.projectIdLabel}: ${projectId}</span>
		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
		<span style="text-align: right; float: right; padding-left: 20px; padding-right: 5px;">
			<c:if test="${not empty disclProject.coiDispositionStatus.description}">
			Disposition: 
				<c:out value="${disclProject.coiDispositionStatus.description}"/>
			</c:if>
		</span>                    
	</h3>
	
	<c:choose>
		<c:when test="${disclProject.awardEvent}">
			<kra-coi:awardHeader disclProject="${disclProject}" />
		</c:when>
		<c:when test="${disclProject.proposalEvent}">
			<kra-coi:proposalHeader disclProject="${disclProject}" />
		</c:when>
		<c:when test="${disclProject.institutionalProposalEvent}">
			<kra-coi:institutionalProposalHeader disclProject="${disclProject}"/>
		</c:when>
		<c:when test="${disclProject.protocolEvent}">
			<kra-coi:protocolHeader disclProject="${disclProject}"/>
		</c:when>		
		<c:when test="${disclProject.iacucProtocolEvent}">
			<kra-coi:iacucProtocolHeader disclProject="${disclProject}"/>
		</c:when>		
		<c:otherwise>
	    	<table class=tab cellpadding="0" cellspacing="0" summary="">
	        	<tbody>
		        	<%-- Header --%>
				    <c:forEach var="labelValue" items="${disclProject.headerItems}" varStatus="status">
		        		<c:if test="${(status.index mod 2) == 0}">
					        <tr>
	        			</c:if>
						<th width="15%">
							<div align="right">
								${labelValue.label}:
							</div>
						</th> 
	    	       		<td align="left" valign="middle" width="35%">
	        	       	<div align="left">
	            	    	${labelValue.value}
		            	</div>
		         		<c:if test="${(status.index mod 2) != 0}">
		        			</tr>
	    	    		</c:if>
	    			</c:forEach>
	        		<c:if test="${fn:length(disclProject.headerItems) mod 2 != 0}">
	        			<td/>
		        		<td/>
		        		</tr>
		        	</c:if>
			</table>
		</c:otherwise>
	</c:choose>
</div>
