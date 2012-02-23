<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="iacucProtocolAttributes" value="${DataDictionary.IacucProtocol.attributes}" />
<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="proposalLogAttributes" value="${DataDictionary.ProposalLog.attributes}" />
<c:set var="subawardAttributes" value="${DataDictionary.SubAward.attributes}" />
<c:set var="negotiationAttributes" value="${DataDictionary.Negotiation.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="unitAdministratorAttributes" value="${DataDictionary.UnitAdministrator.attributes}" />
<c:set var="unitAdministratorTypeAttributes" value="${DataDictionary.UnitAdministratorType.attributes}" />
<c:set var="action" value="personMassChangeView" />
<c:set var="className" value="org.kuali.kra.personmasschange.document.PersonMassChangeDocument" />
<c:set var="readOnly" value="${not KualiForm.editingMode['modify']}"/>

<kul:tabTop tabTitle="Replacing ${KualiForm.document.personMassChange.replaceeFullName} with ${KualiForm.document.personMassChange.replacerFullName}" defaultOpen="true">
	<div class="tab-container" align="center">
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Award ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardNumber}" /></div></th>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.title}" /></div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.awardChangeCandidates}">
	                   <tr>
	                       <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
	                   </tr>
	                </c:if>
	                <c:forEach var="awardChangeCandidate" items="${KualiForm.personMassChangeViewHelper.awardChangeCandidates}" varStatus="status">
		                <tr>
		                    <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.awardChangeCandidates[${status.index}].awardNumber" 
		                                                                    attributeEntry="${awardAttributes.awardNumber}" readOnly="true" /></td>
		                    <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.awardChangeCandidates[${status.index}].title" 
	                                                                        attributeEntry="${awardAttributes.title}" readOnly="true" /></td>
		                </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="IACUC ${tabTitle}" defaultOpen="false" >
            <div class="tab-container" align="center">
                <table cellpadding="4" cellspacing="0" summary="">
                    <tr>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${iacucProtocolAttributes.protocolNumber}" /></div></th>
                        <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${iacucProtocolAttributes.title}" /></div></th>
                    </tr>
                    <c:if test="${empty KualiForm.personMassChangeViewHelper.iacucProtocolChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
                    <c:forEach var="iacucProtocolChangeCandidate" items="${KualiForm.personMassChangeViewHelper.iacucProtocolChangeCandidates}" varStatus="status">
                        <tr>
                            <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.iacucProtocolChangeCandidates[${status.index}].protocolNumber" 
                                                                            attributeEntry="${iacucProtocolAttributes.protocolNumber}" readOnly="true" /></td>
                            <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.iacucProtocolChangeCandidates[${status.index}].title" 
                                                                            attributeEntry="${iacucProtocolAttributes.title}" readOnly="true" /></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Institutional Proposal ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.proposalNumber}" /></div></th>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.title}" /></div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.institutionalProposalChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="institutionalProposalChangeCandidate" items="${KualiForm.personMassChangeViewHelper.institutionalProposalChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.institutionalProposalChangeCandidates[${status.index}].proposalNumber" 
	                                                                        attributeEntry="${institutionalProposalAttributes.proposalNumber}" readOnly="true" /></td>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.institutionalProposalChangeCandidates[${status.index}].title" 
	                                                                        attributeEntry="${institutionalProposalAttributes.title}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Proposal Development ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.proposalNumber}" /></div></th>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.title}" /></div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.proposalDevelopmentChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="proposalDevelopmentChangeCandidate" items="${KualiForm.personMassChangeViewHelper.proposalDevelopmentChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.proposalDevelopmentChangeCandidates[${status.index}].proposalNumber" 
	                                                                        attributeEntry="${proposalDevelopmentAttributes.proposalNumber}" readOnly="true" /></td>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.proposalDevelopmentChangeCandidates[${status.index}].title" 
	                                                                        attributeEntry="${proposalDevelopmentAttributes.title}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Proposal Log ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.proposalNumber}" /></div></th>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.title}" /></div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.proposalLogChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="proposalLogChangeCandidate" items="${KualiForm.personMassChangeViewHelper.proposalLogChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.proposalLogChangeCandidates[${status.index}].proposalNumber" 
	                                                                        attributeEntry="${proposalLogAttributes.proposalNumber}" readOnly="true" /></td>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.proposalLogChangeCandidates[${status.index}].title" 
	                                                                        attributeEntry="${proposalLogAttributes.title}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Subaward ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${subawardAttributes.subAwardCode}" /></div></th>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${subawardAttributes.title}" /></div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.subawardChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="subawardChangeCandidate" items="${KualiForm.personMassChangeViewHelper.subawardChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.subawardChangeCandidates[${status.index}].subAwardCode" 
	                                                                        attributeEntry="${subawardAttributes.subAwardCode}" readOnly="true" /></td>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.subawardChangeCandidates[${status.index}].title" 
	                                                                        attributeEntry="${subawardAttributes.title}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Negotiation ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.associatedDocumentId}" /></div></th>
	                    <th style="width:50%"><div align="left">Title:</div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.negotiationChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="negotiationChangeCandidate" items="${KualiForm.personMassChangeViewHelper.negotiationChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.negotiationChangeCandidates[${status.index}].associatedDocumentId" 
	                                                                        attributeEntry="${negotiationAttributes.associatedDocumentId}" readOnly="true" /></td>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.negotiationChangeCandidates[${status.index}].associatedDocument.title" 
	                                                                        attributeEntry="${negotiationAttributes.associatedDocument.title}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Protocol ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolNumber}" /></div></th>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.title}" /></div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.protocolChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="protocolChangeCandidate" items="${KualiForm.personMassChangeViewHelper.protocolChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.protocolChangeCandidates[${status.index}].protocolNumber" 
	                                                                        attributeEntry="${protocolAttributes.protocolNumber}" readOnly="true" /></td>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.protocolChangeCandidates[${status.index}].title" 
	                                                                        attributeEntry="${protocolAttributes.title}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Unit Administrator ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAdministratorAttributes.unitNumber}" /></div></th>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAdministratorTypeAttributes.unitAdministratorTypeCode}" /></div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.unitAdministratorChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="unitAdministratorChangeCandidate" items="${KualiForm.personMassChangeViewHelper.unitAdministratorChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.unitAdministratorChangeCandidates[${status.index}].unitNumber" 
	                                                                        attributeEntry="${unitAdministratorAttributes.unitNumber}" readOnly="true" /></td>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.unitAdministratorChangeCandidates[${status.index}].unitAdministratorType.description" 
	                                                                        attributeEntry="${unitAdministratorTypeAttributes.description}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
    </div>    
</kul:tabTop>