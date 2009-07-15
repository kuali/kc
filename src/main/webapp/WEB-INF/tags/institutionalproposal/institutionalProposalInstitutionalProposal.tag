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


<kul:tab tabTitle="Institutional Proposal" defaultOpen="false" tabErrorKey="">
	<!-- Institution -->
<div class="tab-container" align="center">

<h3>
	<span class="subhead-left">Institutional Proposal</span>
	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposal" altText="help"/></span>
</h3>
<table cellpAdding="0" cellspacing="0" summary="">
  	<tr>
    	<th width="400">
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.proposalNumber}" /></div>
    	</th>
    	<td>
    		${KualiForm.institutionalProposalDocument.institutionalProposal.proposalNumber}&nbsp;
    	</td>
    	<th width="400">
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.sequenceNumber}" /></div>
		</th>
    	<td>
    		${KualiForm.institutionalProposalDocument.institutionalProposal.sequenceNumber}&nbsp;
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right">Award ID:</div>
      	</th>
    	<td>
    		<div align="left"> 
					<kul:htmlControlAttribute property="document.institutionalProposal.currentAwardNumber" attributeEntry="${institutionalProposalAttributes.currentAwardNumber}"/>
					<kul:lookup boClassName="org.kuali.kra.award.home.Award" fieldConversions="awardId:document.institutionalProposal.currentAwardNumber" anchor="${tabKey}"/>
			</div>
    	</td>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.statusCode}" /></div>
    	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.institutionalProposal.statusCode" attributeEntry="${institutionalProposalAttributes.statusCode}" />
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.proposalTypeCode}" /></div>
    	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.institutionalProposal.proposalTypeCode" attributeEntry="${institutionalProposalAttributes.proposalTypeCode}" />
		</td>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.activityTypeCode}" /></div>
    	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.institutionalProposal.activityTypeCode" attributeEntry="${institutionalProposalAttributes.activityTypeCode}" />
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.initialContractAdmin}" /></div>
    	</th>
    	<td>
    		${KualiForm.institutionalProposalDocument.institutionalProposal.initialContractAdmin}&nbsp;
		</td>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.createTimeStamp}" /></div>
    	</th>
    	<td>
    		${KualiForm.institutionalProposalDocument.institutionalProposal.createTimeStamp}&nbsp;
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.updateUser}" /></div>
    	</th>
    	<td>
    		${KualiForm.institutionalProposalDocument.institutionalProposal.updateUser}&nbsp;
		</td>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.updateTimestamp}" /></div>
    	</th>
    	<td>
    		${KualiForm.institutionalProposalDocument.institutionalProposal.updateTimestamp}&nbsp;
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right">
        		<kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.title}" />
      		</div>
      	</th>
    	<td colspan="3">
        	<table style="border:none; width:100%;">
        		<tr>
            		<td style="border:none; width:100%;">
            			<kul:htmlControlAttribute property="document.institutionalProposal.title" attributeEntry="${institutionalProposalAttributes.title}" />
                    	<kra:expandedTextArea textAreaFieldName="document.institutionalProposal.title" action="institutionalProposalHome" textAreaLabel="${institutionalProposalAttributes.title.label}" />
        			</td>
            	</tr>
        	</table>
    	</td>
  	</tr>
</table>
</div>
</kul:tab>
