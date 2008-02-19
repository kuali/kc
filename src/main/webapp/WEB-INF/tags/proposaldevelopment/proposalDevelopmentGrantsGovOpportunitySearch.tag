<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="s2sOpportunityAttributes" value="${DataDictionary.S2sOpportunity.attributes}" />
<c:set var="textAreaFieldName" value="document.opportunityTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tabTop tabTitle="Grants.gov" defaultOpen="true" tabErrorKey="proposalDevelopmentAttributes.cfdaNumber,proposalDevelopmentAttributes.programAnnouncementNumber ">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Opportunity Search</h2></span>
    		<span class="subhead-right"><a>www.grants.gov</><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <input type="hidden" name="document.cfdaNumber" value="${KualiForm.document.cfdaNumber}">	
        <input type="hidden" name="document.programAnnouncementNumber" value="${KualiForm.document.programAnnouncementNumber}">			
        
        <table summary="" cellpadding="0" cellspacing="0">
          <tbody><tr>
			<kul:lookup boClassName="org.kuali.kra.s2s.bo.S2sOpportunity" fieldConversions="opportunityId:document.s2sOpportunity.opportunityId,cfdaNumber:document.s2sOpportunity.cfdaNumber,opportunityTitle:document.s2sOpportunity.opportunityTitle,s2sSubmissionTypeCode:document.s2sOpportunity.s2sSubmissionTypeCode,revisionCode:document.s2sOpportunity.revisionCode,competetionId:document.s2sOpportunity.competetionId,openingDate:document.s2sOpportunity.openingDate,closingDate:document.s2sOpportunity.closingDate,instructionUrl:document.s2sOpportunity.instructionUrl,schemaUrl:document.s2sOpportunity.schemaUrl" anchor="${tabKey}" autoSearch="yes" lookupParameters="document.programAnnouncementNumber:opportunityId,document.cfdaNumber:cfdaNumber" readOnlyFields="yes"/>
          </tr>
          <tr>
			Grants.gov Lookup
			</tr>          
        </tbody></table>          
		<br/>
        <br/>       
        <c:choose>
        	<c:when	test="${!empty KualiForm.document.s2sOpportunity.opportunityId}" >	
				<html:image src="${ConfigProperties.kr.externalizable.images.url}tinybutton-remvopp.gif" styleClass="globalbuttons" property="methodToCall.removeOpportunity" title="remove opportunity" alt="remove opportunity"/>
			</c:when>	
		</c:choose>	        
        <br/>        
    </div>
</kul:tabTop>

