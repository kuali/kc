<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="proposalLogAttributes" value="${DataDictionary.ProposalLog.attributes}" />
<c:set var="subawardAttributes" value="${DataDictionary.SubAward.attributes}" />
<c:set var="negotiationAttributes" value="${DataDictionary.Negotiation.attributes}" />
<c:set var="committeeAttributes" value="${DataDictionary.Committee.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="scheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />
<c:set var="unitAttributes" value="${DataDictionary.Unit.attributes}" />
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
	
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Committee ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeId}" /></div></th>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeName}" /></div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.committeeChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="committeeChangeCandidate" items="${KualiForm.personMassChangeViewHelper.committeeChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.committeeChangeCandidates[${status.index}].committeeId" 
	                                                                        attributeEntry="${committeeAttributes.committeeId}" readOnly="true" /></td>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.committeeChangeCandidates[${status.index}].committeeName" 
	                                                                        attributeEntry="${committeeAttributes.committeeName}" readOnly="true" /></td>
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
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Schedule ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${scheduleAttributes.id}" /></div></th>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${scheduleAttributes.scheduleId}" /></div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.scheduleChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="scheduleChangeCandidate" items="${KualiForm.personMassChangeViewHelper.scheduleChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.scheduleChangeCandidates[${status.index}].id" 
	                                                                        attributeEntry="${scheduleAttributes.id}" readOnly="true" /></td>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.scheduleChangeCandidates[${status.index}].scheduleId" 
	                                                                        attributeEntry="${scheduleAttributes.scheduleId}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
	    
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Unit ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAttributes.unitNumber}" /></div></th>
	                    <th style="width:50%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAttributes.unitName}" /></div></th>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.unitChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="unitChangeCandidate" items="${KualiForm.personMassChangeViewHelper.unitChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.unitChangeCandidates[${status.index}].unitNumber" 
	                                                                        attributeEntry="${unitAttributes.unitNumber}" readOnly="true" /></td>
	                        <td style="width:50%"><kul:htmlControlAttribute property="personMassChangeViewHelper.unitChangeCandidates[${status.index}].unitName" 
	                                                                        attributeEntry="${unitAttributes.unitName}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
    </div>    
</kul:tabTop>