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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="s2sOpportunityAttributes" value="${DataDictionary.S2sOpportunity.attributes}" />
<c:set var="textAreaFieldName" value="document.developmentProposalList[0].opportunityTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tabTop tabTitle="S2S" defaultOpen="true" tabErrorKey="proposalDevelopmentAttributes.cfdaNumber,proposalDevelopmentAttributes.programAnnouncementNumber ">
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Opportunity Search</span>
            <span class="subhead-right"><a href="http://www.grants.gov" target="_blank">www.grants.gov</a><kul:help businessObjectClassName="org.kuali.coeus.propdev.impl.s2s.S2sOpportunity" altText="help"/></span>
        </h3>
        
        <input type="hidden" name="document.developmentProposalList[0].cfdaNumber" value="${KualiForm.document.developmentProposalList[0].cfdaNumber}">    
        <input type="hidden" name="document.developmentProposalList[0].programAnnouncementNumber" value="${KualiForm.document.developmentProposalList[0].programAnnouncementNumber}">            
        
        S2S Lookup
        <c:if test="${!readOnly}" >
        	<kul:lookup boClassName="org.kuali.coeus.propdev.impl.s2s.S2sOpportunity"
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

