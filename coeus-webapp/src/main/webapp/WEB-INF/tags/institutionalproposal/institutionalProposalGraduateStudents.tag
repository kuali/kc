<%--
 Copyright 2005-2013 The Kuali Foundation
 
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
