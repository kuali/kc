<%--
 Copyright 2005-2014 The Kuali Foundation
 
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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="s2sOpportunityAttributes" value="${DataDictionary.S2sOpportunity.attributes}" />
<c:set var="textAreaFieldName" value="document.developmentProposalList[0].opportunityTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tabTop tabTitle="S2S" defaultOpen="true" tabErrorKey="proposalDevelopmentAttributes.cfdaNumber,proposalDevelopmentAttributes.programAnnouncementNumber ">
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Opportunity Search</span>
            <span class="subhead-right"><a href="http://www.grants.gov" target="_blank">www.grants.gov</a><kul:help businessObjectClassName="org.kuali.kra.s2s.bo.S2sOpportunity" altText="help"/></span>
        </h3>
        
        <input type="hidden" name="document.developmentProposalList[0].cfdaNumber" value="${KualiForm.document.developmentProposalList[0].cfdaNumber}">    
        <input type="hidden" name="document.developmentProposalList[0].programAnnouncementNumber" value="${KualiForm.document.developmentProposalList[0].programAnnouncementNumber}">            
        
        S2S Lookup
        <c:if test="${!readOnly}" >
        	<kul:lookup boClassName="org.kuali.kra.s2s.bo.S2sOpportunity" 
        	fieldConversions="opportunityId:newS2sOpportunity.opportunityId,cfdaNumber:newS2sOpportunity.cfdaNumber,opportunityTitle:newS2sOpportunity.opportunityTitle,s2sSubmissionTypeCode:newS2sOpportunity.s2sSubmissionTypeCode,revisionCode:newS2sOpportunity.revisionCode,competetionId:newS2sOpportunity.competetionId,openingDate:newS2sOpportunity.openingDate,closingDate:newS2sOpportunity.closingDate,instructionUrl:newS2sOpportunity.instructionUrl,schemaUrl:newS2sOpportunity.schemaUrl,providerCode:newS2sOpportunity.providerCode" 
        	anchor="${tabKey}" autoSearch="no" 
        	lookupParameters="document.developmentProposalList[0].programAnnouncementNumber:opportunityId,document.developmentProposalList[0].cfdaNumber:cfdaNumber,document.developmentProposalList[0].s2sOpportunity.providerCode:providerCode" 
        	readOnlyFields="yes"/>
        </c:if>	
               
            
        <br/>
        <br/>       
        <c:if test="${!readOnly}" >
        <c:choose>
            <c:when test="${!empty KualiForm.document.developmentProposalList[0].s2sOpportunity.opportunityId}" >    
                <html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-remvopp.gif" styleClass="globalbuttons" property="methodToCall.removeOpportunity" title="remove opportunity" alt="remove opportunity"/>
            </c:when>    
        </c:choose>            
        </c:if>
        <br/>   
        
        <div align="center">
            <table cellpadding="0" cellspacing="0" summary="">
                <tr>
                    <td style="padding:0px">
	                    <kra-pd:proposalDevelopmentGrantsGovOpportunity />
                    </td>
                </tr>
                <tr>
                    <td style="padding:0px">
	        			<kra-pd:proposalDevelopmentGrantsGovSubmissionDetails />
                    </td>
                </tr>
                <tr>
                    <td style="padding:0px">
	        			<kra-pd:proposalDevelopmentGrantsGovForms />   
                    </td>
                </tr>
                <tr>
                    <td style="padding:0px">
	        			<kra-pd:proposalDevelopmentGrantsGovUserAttachedForm />   
                    </td>
                </tr>
			</table>
	    </div>  
	   
    </div>
</kul:tabTop>

