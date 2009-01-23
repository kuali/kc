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

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="action" value="awardPaymentReportsAndTerms" />


<kul:tab tabTitle="Proposal Due" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Proposals Due For</span>
        </h3>
        <table id="Proposals Due" cellpadding="0" cellspacing="0" summary="Proposals Due">
        	<tr>
            	<th width="600" align="right" scope="row"><div align="right">Non-Competing Continuation</div></th>
            	<td>
            	  	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.awardList[0].nonCompetingContProposalDue" attributeEntry="${awardAttributes.nonCompetingContProposalDue}"/>
            	 	</div>
            	</td>
            </tr>
            <tr>
            	<th width="600" align="right" scope="row"><div align="right">Competing Renewal</div></th>
            	<td>
            	  	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.awardList[0].competingRenewalProposalDue" attributeEntry="${awardAttributes.competingRenewalProposalDue}"/>
            	 	</div>
            	</td>
             </tr>
          </table>
      </div>
          
</kul:tab>
