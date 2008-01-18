<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tabTop tabTitle="Grants.gov" defaultOpen="true" tabErrorKey="document.deadlineDate,document.noticeOfOpportunityCode,document.deadlineType,document.cfdaNumber,document.programAnnouncementNumber,document.primeSponsorCode,document.sponsorProposalNumber,document.nsfCode,document.subcontracts,document.agencyDivisionCode,document.agencyProgramCode,document.programAnnouncementTitle">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Opportunity Search</h2></span>
    		<span class="subhead-right"><a>www.grants.gov</><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>       
             
        <table summary="" cellpadding="0" cellspacing="0">
          <tbody><tr>
            <th width="35%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" /></div></th>
            <td><kul:htmlControlAttribute property="document.cfdaNumber" attributeEntry="${proposalDevelopmentAttributes.cfdaNumber}" /></td>
           <!-- <td align="left" valign="middle"><kul:htmlControlAttribute property="document.currentAwardNumber" attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" /></td>  --> 
          </tr>
          <tr>
			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" /></div></th>
			<td><kul:htmlControlAttribute property="document.programAnnouncementNumber" attributeEntry="${proposalDevelopmentAttributes.programAnnouncementNumber}" /></td>
			</tr>          
        </tbody></table>
        <br/>
        <html:image src="${ConfigProperties.kr.externalizable.images.url}tinybutton-grantsgovlook.gif" styleClass="globalbuttons" property="methodToCall.route" title="grants.gov lookup" alt="grants.gov lookup"/>
		<html:image src="${ConfigProperties.kr.externalizable.images.url}tinybutton-remvopp.gif" styleClass="globalbuttons" property="methodToCall.route" title="remove opportunity" alt="remove opportunity"/>        
        <br/>
        <br/>
    </div>
	<kul:innerTab tabTitle="Person Details" parentTab="Grants.gov" defaultOpen="false">
	</kul:innerTab>     
   <kul:subtab subTabTitle="Person Details" width = "1" buttonAlt="false" lookedUpCollectionName="">
   </kul:subtab>
</kul:tabTop>

