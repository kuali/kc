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

<c:set var="personMassChangeAttributes" value="${DataDictionary.PersonMassChange.attributes}" />
<c:set var="iacucProtocolPersonMassChangeAttributes" value="${DataDictionary.IacucProtocolPersonMassChange.attributes}" />
<c:set var="awardPersonMassChangeAttributes" value="${DataDictionary.AwardPersonMassChange.attributes}" />
<c:set var="institutionalProposalPersonMassChangeAttributes" value="${DataDictionary.InstitutionalProposalPersonMassChange.attributes}" />
<c:set var="proposalDevelopmentPersonMassChangeAttributes" value="${DataDictionary.ProposalDevelopmentPersonMassChange.attributes}" />
<c:set var="proposalLogPersonMassChangeAttributes" value="${DataDictionary.ProposalLogPersonMassChange.attributes}" />
<c:set var="subawardPersonMassChangeAttributes" value="${DataDictionary.SubawardPersonMassChange.attributes}" />
<c:set var="negotiationPersonMassChangeAttributes" value="${DataDictionary.NegotiationPersonMassChange.attributes}" />
<c:set var="protocolPersonMassChangeAttributes" value="${DataDictionary.ProtocolPersonMassChange.attributes}" />
<c:set var="unitAdministratorPersonMassChangeAttributes" value="${DataDictionary.UnitAdministratorPersonMassChange.attributes}" />
<c:set var="action" value="personMassChangeHome" />
<c:set var="className" value="org.kuali.kra.personmasschange.document.PersonMassChangeDocument" />
<c:set var="readOnly" value="${not KualiForm.editingMode['modify']}"/>

<kul:tab tabTitle="Person Type" defaultOpen="true" innerTabErrorKey="document.personMassChange*">
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Modules</span>
        </h3>
        
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Award ${tabTitle}" tabErrorKey="document.personMassChange.awardPersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardPersonMassChangeAttributes.investigator}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.awardPersonMassChange.investigator" 
	                                                                    attributeEntry="${awardPersonMassChangeAttributes.investigator}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardPersonMassChangeAttributes.keyStudyPerson}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.awardPersonMassChange.keyStudyPerson" 
                                                                        attributeEntry="${awardPersonMassChangeAttributes.keyStudyPerson}" readOnly="false" /></td>
                    </tr>
                    <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardPersonMassChangeAttributes.unitContact}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.awardPersonMassChange.unitContact" 
                                                                        attributeEntry="${awardPersonMassChangeAttributes.unitContact}" readOnly="false" /></td>
                    </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardPersonMassChangeAttributes.sponsorContact}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.awardPersonMassChange.sponsorContact" 
	                                                                    attributeEntry="${awardPersonMassChangeAttributes.sponsorContact}" readOnly="false" /></td>
	                </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardPersonMassChangeAttributes.approvedForeignTravel}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.awardPersonMassChange.approvedForeignTravel" 
	                                                                    attributeEntry="${awardPersonMassChangeAttributes.approvedForeignTravel}" readOnly="false" /></td>
	                </tr>

	                <tr>
	                    <td colspan="2"><div align="center">
	                        <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
	                                    onclick="selectAllPersonMassChangeCategory('document.personMassChange.awardPersonMassChange', 'investigator', 'keyStudyPerson', 'unitContact', 'sponsorContact', 'approvedForeignTravel')" />
	                        <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
	                                    onclick="unselectAllPersonMassChangeCategory('document.personMassChange.awardPersonMassChange', 'investigator', 'keyStudyPerson', 'unitContact', 'sponsorContact', 'approvedForeignTravel')"/>
	                    </div></td>
	                </tr>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="IACUC ${tabTitle}" tabErrorKey="document.personMassChange.iacucProtocolPersonMassChange.*" defaultOpen="false" >
            <div class="tab-container" align="center">
                <table cellpadding="4" cellspacing="0" summary="">
                    <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${iacucProtocolPersonMassChangeAttributes.investigator}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.iacucProtocolPersonMassChange.investigator" 
                                                                        attributeEntry="${iacucProtocolPersonMassChangeAttributes.investigator}" readOnly="false" /></td>
                    </tr>
                    <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${iacucProtocolPersonMassChangeAttributes.keyStudyPerson}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.iacucProtocolPersonMassChange.keyStudyPerson" 
                                                                        attributeEntry="${iacucProtocolPersonMassChangeAttributes.keyStudyPerson}" readOnly="false" /></td>
                    </tr>
                    <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${iacucProtocolPersonMassChangeAttributes.correspondents}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.iacucProtocolPersonMassChange.correspondents" 
                                                                        attributeEntry="${iacucProtocolPersonMassChangeAttributes.correspondents}" readOnly="false" /></td>
                    </tr>
                    <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${iacucProtocolPersonMassChangeAttributes.reviewer}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.iacucProtocolPersonMassChange.reviewer" 
                                                      attributeEntry="${iacucProtocolPersonMassChangeAttributes.reviewer}" readOnly="false" /></td>
                    </tr>                    
                    <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.iacucProtocolPersonMassChange', 'investigator', 'keyStudyPerson', 'correspondents')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.iacucProtocolPersonMassChange', 'investigator', 'keyStudyPerson', 'correspondents')"/>
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
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonMassChangeAttributes.keyStudyPerson}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.institutionalProposalPersonMassChange.keyStudyPerson" 
                                                                        attributeEntry="${institutionalProposalPersonMassChangeAttributes.keyStudyPerson}" readOnly="false" /></td>
                    </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonMassChangeAttributes.mailingInformation}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.institutionalProposalPersonMassChange.mailingInformation" 
	                                                                    attributeEntry="${institutionalProposalPersonMassChangeAttributes.mailingInformation}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonMassChangeAttributes.unitContact}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.institutionalProposalPersonMassChange.unitContact" 
                                                                        attributeEntry="${institutionalProposalPersonMassChangeAttributes.unitContact}" readOnly="false" /></td>
                    </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonMassChangeAttributes.ipReviewer}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.institutionalProposalPersonMassChange.ipReviewer" 
	                                                                    attributeEntry="${institutionalProposalPersonMassChangeAttributes.ipReviewer}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.institutionalProposalPersonMassChange', 'investigator', 'keyStudyPerson', 'mailingInformation', 'unitContact', 'ipReviewer')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.institutionalProposalPersonMassChange', 'investigator', 'keyStudyPerson', 'mailingInformation', 'unitContact', 'ipReviewer')"/>
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
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${subawardPersonMassChangeAttributes.requisitioner}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.subawardPersonMassChange.requisitioner" 
	                                                                    attributeEntry="${subawardPersonMassChangeAttributes.requisitioner}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${subawardPersonMassChangeAttributes.contact}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.subawardPersonMassChange.contact" 
                                                                        attributeEntry="${subawardPersonMassChangeAttributes.contact}" readOnly="false" /></td>
                    </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.subawardPersonMassChange', 'requisitioner', 'contact')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.subawardPersonMassChange', 'requisitioner', 'contact')"/>
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
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Unit Administrator ${tabTitle}" tabErrorKey="document.personMassChange.unitAdministratorPersonMassChange.*" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	               <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAdministratorPersonMassChangeAttributes.administrativeOfficer}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.unitAdministratorPersonMassChange.administrativeOfficer" 
                                                                        attributeEntry="${unitAdministratorPersonMassChangeAttributes.administrativeOfficer}" readOnly="false" /></td>
                    </tr>
                    <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAdministratorPersonMassChangeAttributes.ospAdministrator}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.unitAdministratorPersonMassChange.ospAdministrator" 
                                                                        attributeEntry="${unitAdministratorPersonMassChangeAttributes.ospAdministrator}" readOnly="false" /></td>
                    </tr>
                    <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAdministratorPersonMassChangeAttributes.unitHead}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.unitAdministratorPersonMassChange.unitHead" 
                                                                        attributeEntry="${unitAdministratorPersonMassChangeAttributes.unitHead}" readOnly="false" /></td>
                    </tr>
                    <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAdministratorPersonMassChangeAttributes.deanVP}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.unitAdministratorPersonMassChange.deanVP" 
                                                                        attributeEntry="${unitAdministratorPersonMassChangeAttributes.deanVP}" readOnly="false" /></td>
                    </tr>
                    <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAdministratorPersonMassChangeAttributes.otherIndividualToNotify}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.unitAdministratorPersonMassChange.otherIndividualToNotify" 
                                                                        attributeEntry="${unitAdministratorPersonMassChangeAttributes.otherIndividualToNotify}" readOnly="false" /></td>
                    </tr>
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAdministratorPersonMassChangeAttributes.administrativeContact}" /></div></th>
	                    <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.unitAdministratorPersonMassChange.administrativeContact" 
	                                                                    attributeEntry="${unitAdministratorPersonMassChangeAttributes.administrativeContact}" readOnly="false" /></td>
	                </tr>
	                <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAdministratorPersonMassChangeAttributes.financialContact}" /></div></th>
                        <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.unitAdministratorPersonMassChange.financialContact" 
                                                                        attributeEntry="${unitAdministratorPersonMassChangeAttributes.financialContact}" readOnly="false" /></td>
                    </tr>
	                <tr>
                        <td colspan="2"><div align="center">
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' alt="Select All" styleClass="tinybutton" 
                                        onclick="selectAllPersonMassChangeCategory('document.personMassChange.unitAdministratorPersonMassChange', 'administrativeOfficer', 'ospAdministrator', 'unitHead', 'deanVP', 'otherIndividualToNotify', 'administrativeContact', 'financialContact')" />
                            <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' alt="Select None" styleClass="tinybutton" 
                                        onclick="unselectAllPersonMassChangeCategory('document.personMassChange.unitAdministratorPersonMassChange', 'administrativeOfficer', 'ospAdministrator', 'unitHead', 'deanVP', 'otherIndividualToNotify', 'administrativeContact', 'financialContact')"/>
                        </div></td>
                    </tr>
	            </table>
	        </div>
	    </kul:innerTab>
    </div>
    
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Options</span>
        </h3>
        
        <table cellpadding="4" cellspacing="0" summary="">
            <tr>
                <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${personMassChangeAttributes.changeAllSequences}" /></div></th>
                <td style="width:50%"><kul:htmlControlAttribute property="document.personMassChange.changeAllSequences" 
                                                                attributeEntry="${personMassChangeAttributes.changeAllSequences}" readOnly="false" /></td>
            </tr>
        </table>
    </div>
</kul:tab>
