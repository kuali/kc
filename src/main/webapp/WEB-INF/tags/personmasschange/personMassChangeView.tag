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
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardNumber}" /></div></td>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.title}" /></div></td>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.awardChangeCandidates}">
	                   <tr>
	                       <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
	                   </tr>
	                </c:if>
	                <c:forEach var="awardChangeCandidate" items="${KualiForm.personMassChangeViewHelper.awardChangeCandidates}" varStatus="status">
		                <tr>
		                    <td><kul:htmlControlAttribute property="awardChangeCandidate.awardNumber" 
		                                                  attributeEntry="${awardAttributes.awardNumber}" readOnly="true" /></td>
		                    <td><kul:htmlControlAttribute property="awardChangeCandidate.title" 
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
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.proposalNumber}" /></div></td>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.title}" /></div></td>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.institutionalProposalChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="institutionalProposalChangeCandidate" items="${KualiForm.personMassChangeViewHelper.institutionalProposalChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td><kul:htmlControlAttribute property="institutionalProposalChangeCandidate.proposalNumber" 
	                                                      attributeEntry="${institutionalProposalAttributes.proposalNumber}" readOnly="true" /></td>
	                        <td><kul:htmlControlAttribute property="institutionalProposalChangeCandidate.title" 
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
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.proposalNumber}" /></div></td>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.title}" /></div></td>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.proposalDevelopmentChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="proposalDevelopmentChangeCandidate" items="${KualiForm.personMassChangeViewHelper.proposalDevelopmentChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td><kul:htmlControlAttribute property="proposalDevelopmentChangeCandidate.proposalNumber" 
	                                                      attributeEntry="${proposalDevelopmentAttributes.proposalNumber}" readOnly="true" /></td>
	                        <td><kul:htmlControlAttribute property="proposalDevelopmentChangeCandidate.title" 
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
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.proposalNumber}" /></div></td>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.title}" /></div></td>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.proposalLogChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="proposalLogChangeCandidate" items="${KualiForm.personMassChangeViewHelper.proposalLogChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td><kul:htmlControlAttribute property="proposalLogChangeCandidate.proposalNumber" 
	                                                      attributeEntry="${proposalLogAttributes.proposalNumber}" readOnly="true" /></td>
	                        <td><kul:htmlControlAttribute property="proposalLogChangeCandidate.title" 
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
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${subawardAttributes.subAwardCode}" /></div></td>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${subawardAttributes.title}" /></div></td>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.subawardChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="subawardChangeCandidate" items="${KualiForm.personMassChangeViewHelper.subawardChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td><kul:htmlControlAttribute property="subawardChangeCandidate.subAwardCode" 
	                                                      attributeEntry="${subawardAttributes.subAwardCode}" readOnly="true" /></td>
	                        <td><kul:htmlControlAttribute property="subawardChangeCandidate.title" 
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
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.associatedDocumentId}" /></div></td>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.associatedNegotiable.title}" /></div></td>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.negotiationChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="negotiationChangeCandidate" items="${KualiForm.personMassChangeViewHelper.negotiationChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td><kul:htmlControlAttribute property="negotiationChangeCandidate.associatedDocumentId" 
	                                                      attributeEntry="${negotiationAttributes.associatedDocumentId}" readOnly="true" /></td>
	                        <td><kul:htmlControlAttribute property="negotiationChangeCandidate.associatedNegotiable.title" 
	                                                      attributeEntry="${negotiationAttributes.associatedNegotiable.title}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
	
	    <kul:innerTab parentTab="${parentTab}" tabTitle="Committee ${tabTitle}" defaultOpen="false" >
	        <div class="tab-container" align="center">
	            <table cellpadding="4" cellspacing="0" summary="">
	                <tr>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeId}" /></div></td>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeName}" /></div></td>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.committeeChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="committeeChangeCandidate" items="${KualiForm.personMassChangeViewHelper.committeeChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td><kul:htmlControlAttribute property="committeeChangeCandidate.committeeId" 
	                                                      attributeEntry="${committeeAttributes.committeeId}" readOnly="true" /></td>
	                        <td><kul:htmlControlAttribute property="committeeChangeCandidate.committeeName" 
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
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolNumber}" /></div></td>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.title}" /></div></td>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.protocolChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="protocolChangeCandidate" items="${KualiForm.personMassChangeViewHelper.protocolChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td><kul:htmlControlAttribute property="protocolChangeCandidate.protocolNumber" 
	                                                      attributeEntry="${protocolAttributes.protocolNumber}" readOnly="true" /></td>
	                        <td><kul:htmlControlAttribute property="protocolChangeCandidate.title" 
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
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${scheduleAttributes.id}" /></div></td>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${scheduleAttributes.scheduleId}" /></div></td>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.scheduleChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="scheduleChangeCandidate" items="${KualiForm.personMassChangeViewHelper.scheduleChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td><kul:htmlControlAttribute property="scheduleChangeCandidate.id" 
	                                                      attributeEntry="${scheduleAttributes.id}" readOnly="true" /></td>
	                        <td><kul:htmlControlAttribute property="scheduleChangeCandidate.scheduleId" 
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
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAttributes.unitNumber}" /></div></td>
	                    <td><div align="left"><kul:htmlAttributeLabel attributeEntry="${unitAttributes.unitName}" /></div></td>
	                </tr>
	                <c:if test="${empty KualiForm.personMassChangeViewHelper.unitChangeCandidates}">
                       <tr>
                           <td colspan="2"><div align="center"><c:out value="${KualiForm.personMassChangeViewHelper.emptyCandidatesMessage}" /></div></td>
                       </tr>
                    </c:if>
	                <c:forEach var="unitChangeCandidate" items="${KualiForm.personMassChangeViewHelper.unitChangeCandidates}" varStatus="status">
	                    <tr>
	                        <td><kul:htmlControlAttribute property="unitChangeCandidate.unitNumber" 
	                                                      attributeEntry="${unitAttributes.unitNumber}" readOnly="true" /></td>
	                        <td><kul:htmlControlAttribute property="unitChangeCandidate.unitName" 
	                                                      attributeEntry="${unitAttributes.unitName}" readOnly="true" /></td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </div>
	    </kul:innerTab>
    </div>    
</kul:tabTop>