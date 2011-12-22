<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardPersonMassChangeAttributes" value="${DataDictionary.AwardPersonMassChange.attributes}" />
<c:set var="institutionalProposalPersonMassChangeAttributes" value="${DataDictionary.InstitutionalProposalPersonMassChange.attributes}" />
<c:set var="proposalDevelopmentPersonMassChangeAttributes" value="${DataDictionary.ProposalDevelopmentPersonMassChange.attributes}" />
<c:set var="proposalLogPersonMassChangeAttributes" value="${DataDictionary.ProposalLogPersonMassChange.attributes}" />
<c:set var="subawardPersonMassChangeAttributes" value="${DataDictionary.SubawardPersonMassChange.attributes}" />
<c:set var="negotiationPersonMassChangeAttributes" value="${DataDictionary.NegotiationPersonMassChange.attributes}" />
<c:set var="committeePersonMassChangeAttributes" value="${DataDictionary.CommitteePersonMassChange.attributes}" />
<c:set var="protocolPersonMassChangeAttributes" value="${DataDictionary.ProtocolPersonMassChange.attributes}" />
<c:set var="schedulePersonMassChangeAttributes" value="${DataDictionary.SchedulePersonMassChange.attributes}" />
<c:set var="unitPersonMassChangeAttributes" value="${DataDictionary.UnitPersonMassChange.attributes}" />
<c:set var="action" value="personMassChangeHome" />
<c:set var="className" value="org.kuali.kra.personmasschange.document.PersonMassChangeDocument" />
<c:set var="readOnly" value="${not KualiForm.editingMode['modify']}"/>

<kul:tab tabTitle="Person Type" defaultOpen="true" innerTabErrorKey="document.personMassChange*">
	<div class="tab-container" align="center">
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Award ${tabTitle}" tabErrorKey="document.personMassChange.awardPersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardPersonMassChangeAttributes.investigator}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.awardPersonMassChange.investigator" 
	                                                                    attributeEntry="${awardPersonMassChangeAttributes.investigator}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardPersonMassChangeAttributes.contactPerson}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.awardPersonMassChange.contactPerson" 
	                                                                    attributeEntry="${awardPersonMassChangeAttributes.contactPerson}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardPersonMassChangeAttributes.foreignTrip}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.awardPersonMassChange.foreignTrip" 
	                                                                    attributeEntry="${awardPersonMassChangeAttributes.foreignTrip}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardPersonMassChangeAttributes.unitAdministrator}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.awardPersonMassChange.unitAdministrator" 
	                                                                    attributeEntry="${awardPersonMassChangeAttributes.unitAdministrator}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <td colspan="2"><div align="center">
	                        <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
	                                    onclick="selectAllPersonMassChangeCategory('document.personMassChange.awardPersonMassChange', 'investigator', 'contactPerson', 'foreignTrip', 'unitAdministrator')" />
	                        <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
	                                    onclick="unselectAllPersonMassChangeCategory('document.personMassChange.awardPersonMassChange', 'investigator', 'contactPerson', 'foreignTrip', 'unitAdministrator')"/>
	                    </div></td>
	                </tr>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Institutional Proposal ${tabTitle}" tabErrorKey="document.personMassChange.institutionalProposalPersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonMassChangeAttributes.investigator}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.institutionalProposalPersonMassChange.investigator" 
	                                                                    attributeEntry="${institutionalProposalPersonMassChangeAttributes.investigator}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonMassChangeAttributes.unitAdministrator}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.institutionalProposalPersonMassChange.unitAdministrator" 
	                                                                    attributeEntry="${institutionalProposalPersonMassChangeAttributes.unitAdministrator}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonMassChangeAttributes.mailingInformation}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.institutionalProposalPersonMassChange.mailingInformation" 
	                                                                    attributeEntry="${institutionalProposalPersonMassChangeAttributes.mailingInformation}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonMassChangeAttributes.ipReviewer}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.institutionalProposalPersonMassChange.ipReviewer" 
	                                                                    attributeEntry="${institutionalProposalPersonMassChangeAttributes.ipReviewer}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.institutionalProposalPersonMassChange', 'investigator', 'unitAdministrator', 'mailingInformation', 'ipReviewer')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.institutionalProposalPersonMassChange', 'investigator', 'unitAdministrator', 'mailingInformation', 'ipReviewer')"/>
                        </div></td>
                    </tr>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Proposal Development ${tabTitle}" tabErrorKey="document.personMassChange.proposalDevelopmentPersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentPersonMassChangeAttributes.investigator}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.proposalDevelopmentPersonMassChange.investigator" 
	                                                                    attributeEntry="${proposalDevelopmentPersonMassChangeAttributes.investigator}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentPersonMassChangeAttributes.mailingInformation}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.proposalDevelopmentPersonMassChange.mailingInformation" 
	                                                                    attributeEntry="${proposalDevelopmentPersonMassChangeAttributes.mailingInformation}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentPersonMassChangeAttributes.keyStudyPerson}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.proposalDevelopmentPersonMassChange.keyStudyPerson" 
	                                                                    attributeEntry="${proposalDevelopmentPersonMassChangeAttributes.keyStudyPerson}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentPersonMassChangeAttributes.budgetPerson}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.proposalDevelopmentPersonMassChange.budgetPerson" 
	                                                                    attributeEntry="${proposalDevelopmentPersonMassChangeAttributes.budgetPerson}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.proposalDevelopmentPersonMassChange', 'investigator', 'mailingInformation', 'keyStudyPerson', 'budgetPerson')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.proposalDevelopmentPersonMassChange', 'investigator', 'mailingInformation', 'keyStudyPerson', 'budgetPerson')"/>
                        </div></td>
                    </tr>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Proposal Log ${tabTitle}" tabErrorKey="document.personMassChange.proposalLogPersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalLogPersonMassChangeAttributes.principalInvestigator}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.proposalLogPersonMassChange.principalInvestigator" 
	                                                                    attributeEntry="${proposalLogPersonMassChangeAttributes.principalInvestigator}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.proposalLogPersonMassChange', 'principalInvestigator')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.proposalLogPersonMassChange', 'principalInvestigator')"/>
                        </div></td>
                    </tr>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Subaward ${tabTitle}" tabErrorKey="document.personMassChange.subawardPersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${subawardPersonMassChangeAttributes.contactPerson}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.subawardPersonMassChange.contactPerson" 
	                                                                    attributeEntry="${subawardPersonMassChangeAttributes.contactPerson}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${subawardPersonMassChangeAttributes.requisitioner}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.subawardPersonMassChange.requisitioner" 
	                                                                    attributeEntry="${subawardPersonMassChangeAttributes.requisitioner}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.subawardPersonMassChange', 'contactPerson', 'requisitioner')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.subawardPersonMassChange', 'contactPerson', 'requisitioner')"/>
                        </div></td>
                    </tr>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Negotiation ${tabTitle}" tabErrorKey="document.personMassChange.negotiationPersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${negotiationPersonMassChangeAttributes.negotiator}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.negotiationPersonMassChange.negotiator" 
	                                                                    attributeEntry="${negotiationPersonMassChangeAttributes.negotiator}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.negotiationPersonMassChange', 'negotiator')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.negotiationPersonMassChange', 'negotiator')"/>
                        </div></td>
                    </tr>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Committee ${tabTitle}" tabErrorKey="document.personMassChange.committeePersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${committeePersonMassChangeAttributes.member}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.committeePersonMassChange.member" 
	                                                                    attributeEntry="${committeePersonMassChangeAttributes.member}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.committeePersonMassChange', 'member')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.committeePersonMassChange', 'member')"/>
                        </div></td>
                    </tr>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Protocol ${tabTitle}" tabErrorKey="document.personMassChange.protocolPersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${protocolPersonMassChangeAttributes.investigator}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.protocolPersonMassChange.investigator" 
	                                                                    attributeEntry="${protocolPersonMassChangeAttributes.investigator}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${protocolPersonMassChangeAttributes.keyStudyPerson}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.protocolPersonMassChange.keyStudyPerson" 
	                                                                    attributeEntry="${protocolPersonMassChangeAttributes.keyStudyPerson}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${protocolPersonMassChangeAttributes.correspondents}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.protocolPersonMassChange.correspondents" 
	                                                                    attributeEntry="${protocolPersonMassChangeAttributes.correspondents}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${protocolPersonMassChangeAttributes.reviewer}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.protocolPersonMassChange.reviewer" 
	                                                  attributeEntry="${protocolPersonMassChangeAttributes.reviewer}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.protocolPersonMassChange', 'investigator', 'keyStudyPerson', 'correspondents', 'reviewer')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.protocolPersonMassChange', 'investigator', 'keyStudyPerson', 'correspondents', 'reviewer')"/>
                        </div></td>
                    </tr>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Schedule ${tabTitle}" tabErrorKey="document.personMassChange.schedulePersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${schedulePersonMassChangeAttributes.attendees}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.schedulePersonMassChange.attendees" 
	                                                                    attributeEntry="${schedulePersonMassChangeAttributes.attendees}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.schedulePersonMassChange', 'attendees')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.schedulePersonMassChange', 'attendees')"/>
                        </div></td>
                    </tr>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Unit ${tabTitle}" tabErrorKey="document.personMassChange.unitPersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitPersonMassChangeAttributes.administrator}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.unitPersonMassChange.administrator" 
	                                                                    attributeEntry="${unitPersonMassChangeAttributes.administrator}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.unitPersonMassChange', 'administrator')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.unitPersonMassChange', 'administrator')"/>
                        </div></td>
                    </tr>
	            </table>
	        </div>
	    </kul:innerTab>
    </div>
</kul:tab>