<%--
 Copyright 2006-2008 The Kuali Foundation
 
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


<kul:tab tabTitle="Graduate Students" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Graduate Students</span>
        </h3>
        <table id="Graduate-Students" cellpadding="0" cellspacing="0" summary="Graduate Students">
        ${KualiForm.document.institutionalProposalList[0].gradStudHeadcount}
        	<tr>
            	<th width="200" align="right" scope="row"><div align="right">Head Count:</div></th>
            	<td>
            	  	<div align="left">
            	  		<c:out value="${institutionalProposalAttributes == null}" />
            	  		${institutionalProposalAttributes.gradStudHeadcount.label}
            	  	 	<kul:htmlControlAttribute property="document.institutionalProposal.gradStudHeadcount" attributeEntry="${institutionalProposalAttributes.gradStudHeadcount}"/>
            	 	</div>
            	</td>
            	<th width="200" align="right" scope="row"><div align="right">Person Months:</div></th>
            	<td>
            	  	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.institutionalProposal.gradStudPersonMonths" attributeEntry="${institutionalProposalAttributes.gradStudPersonMonths}"/>
            	 	</div>
            	</td>
            </tr>
       	</table>
  	</div>
</kul:tab>
