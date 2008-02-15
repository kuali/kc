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
             
        <table summary="" cellpadding="0" cellspacing="0">
          <tbody><tr>
            <!-- <th width="35%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunityAttributes.cfdaNumber}" /></div></th> -->            
            <th width="35%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" /></div></th>
            <c:choose>            	
            	<c:when test="${empty KualiForm.document.s2sOpportunity.opportunityId}"> 
            		<td><kul:htmlControlAttribute property="document.cfdaNumber" attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" /></td>
            	</c:when>
            	<c:otherwise>
					<td><kul:htmlControlAttribute property="document.cfdaNumber" attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" readOnly="true"/>				            	
            	</c:otherwise>
            </c:choose>
            
			<!-- <kul:htmlControlAttribute property="document.s2sOpportunity.cfdaNumber" attributeEntry="${s2sOpportunityAttributes.cfdaNumber}" />    -->
          </tr>
          <tr>
			<!-- <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${s2sOpportunityAttributes.opportunityId}" /></div></th> -->
			 <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" /></div></th>
            <c:choose>            	
            	<c:when test="${empty KualiForm.document.s2sOpportunity.opportunityId}"> 
					 <td><kul:htmlControlAttribute property="document.programAnnouncementNumber" attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" /></td>
            	</c:when>
            	<c:otherwise>
					 <td><kul:htmlControlAttribute property="document.programAnnouncementNumber" attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" readOnly="true"/></td>				            	
            	</c:otherwise>
            </c:choose>			 

			<!--<kul:htmlControlAttribute property="document.s2sOpportunity.opportunityId" attributeEntry="${s2sOpportunityAttributes.opportunityId}" /> 			-->
			</tr>          
        </tbody></table>
        <br/>
        <html:image src="${ConfigProperties.kr.externalizable.images.url}tinybutton-grantsgovlook.gif" styleClass="globalbuttons" property="methodToCall.route" title="grants.gov lookup" alt="grants.gov lookup"/>
<!--  	<kul:lookup boClassName="org.kuali.kra.s2s.bo.S2sOpportunity" fieldConversions="opportunityId:document.s2sOpportunity.opportunityId,cfdaNumber:document.s2sOpportunity.cfdaNumber,opportunityTitle:document.s2sOpportunity.opportunityTitle,s2sSubmissionTypeCode:document.s2sOpportunity.s2sSubmissionTypeCode,revisionCode:document.s2sOpportunity.revisionCode,competetionId:document.s2sOpportunity.competetionId,openingDate:document.s2sOpportunity.openingDate,closingDate:document.s2sOpportunity.closingDate,instructionUrl:document.s2sOpportunity.instructionUrl,schemaUrl:document.s2sOpportunity.schemaUrl" anchor="${tabKey}" autoSearch="yes" lookupParameters="document.s2sOpportunity.opportunityId:opportunityId,document.s2sOpportunity.cfdaNumber:cfdaNumber" readOnlyFields="yes" extraButtonSource="${ConfigProperties.kr.externalizable.images.url}tinybutton-grantsgovlook.gif" />-->
        		<kul:lookup boClassName="org.kuali.kra.s2s.bo.S2sOpportunity" fieldConversions="opportunityId:document.s2sOpportunity.opportunityId,cfdaNumber:document.s2sOpportunity.cfdaNumber,opportunityTitle:document.s2sOpportunity.opportunityTitle,s2sSubmissionTypeCode:document.s2sOpportunity.s2sSubmissionTypeCode,revisionCode:document.s2sOpportunity.revisionCode,competetionId:document.s2sOpportunity.competetionId,openingDate:document.s2sOpportunity.openingDate,closingDate:document.s2sOpportunity.closingDate,instructionUrl:document.s2sOpportunity.instructionUrl,schemaUrl:document.s2sOpportunity.schemaUrl" anchor="${tabKey}" autoSearch="yes" lookupParameters="document.programAnnouncementNumber:opportunityId,document.cfdaNumber:cfdaNumber" readOnlyFields="yes" extraButtonSource="${ConfigProperties.kr.externalizable.images.url}tinybutton-grantsgovlook.gif" />
		<html:image src="${ConfigProperties.kr.externalizable.images.url}tinybutton-remvopp.gif" styleClass="globalbuttons" property="methodToCall.removeOpportunity" title="remove opportunity" alt="remove opportunity"/>        
        <br/>
        <br/>
    </div>
</kul:tabTop>

