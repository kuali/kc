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

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />


<kul:tab tabTitle="Graduate Students" defaultOpen="false" tabErrorKey="document.institutionalProposalList[0].gradStud*" auditCluster="graduateStudentAuditErrors" tabAuditKey="document.graduateStudentAuditRules*" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Graduate Students</span>
      		<span class="subhead-right"><kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="graduateStudentsHelpUrl" altText="help"/></span>
        </h3>
        <table id="Graduate-Students" cellpadding="0" cellspacing="0" summary="Graduate Students">
        	<tr>
        		<th width="300" align="right">
        		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.gradStudHeadcount}" /></div>
            	</th>
            	<td>
            	  	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.institutionalProposal.gradStudHeadcount" attributeEntry="${institutionalProposalAttributes.gradStudHeadcount}"/>
            	 	</div>
            	</td>
            	<th width="300" align="right" scope="row">
				     <div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.gradStudPersonMonths}" /></div>
				</th>
            	<td>
            	  	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.institutionalProposal.gradStudPersonMonths" attributeEntry="${institutionalProposalAttributes.gradStudPersonMonths}"/>
            	 	</div>
            	</td>
            </tr>
       	</table>
  	</div>
</kul:tab>
