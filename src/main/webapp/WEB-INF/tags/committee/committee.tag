<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="committeeAttributes" value="${DataDictionary.Committee.attributes}" />
<c:set var="action" value="committeeCommittee" />
<c:set var="className" value="org.kuali.kra.committee.document.CommitteeDocument" />
<c:set var="readOnly" value="${!KualiForm.committeeHelper.modifyCommittee or KualiForm.lookupHelper.viewOnly}" scope="request" />

<kul:tab tabTitle="Committee" defaultOpen="true" tabErrorKey="document.committee*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="document.title" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Committee</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.committee.bo.Committee" altText="help"/></span>
        </h3>
		
		<table cellpadding="4" cellspacing="0" summary="">
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeId}" /></div></th>
                <td>
                    <c:choose>
	                    <c:when test="${empty KualiForm.document.committeeList[0].id}">
	                    	<kra:kraControlAttribute property="document.committeeList[0].committeeId" readOnly="${readOnly}" attributeEntry="${committeeAttributes.committeeId}" />
	                    </c:when>
	                    <c:otherwise>
	                      <span id="committeeId">
	                          ${KualiForm.document.committeeList[0].committeeId}
	                      </span>
	                    </c:otherwise>
                   </c:choose>
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeName}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.committeeList[0].committeeName" attributeEntry="${committeeAttributes.committeeName}" readOnly="${readOnly}"/>
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.homeUnitNumber}" /></div></th>
                <td>
                    <kul:htmlControlAttribute property="document.committeeList[0].homeUnitNumber" attributeEntry="${committeeAttributes.homeUnitNumber}" onblur="loadUnitNameTo('document.committeeList[0].homeUnitNumber','document.committee.homeUnitName');" />
                    <c:if test="${!readOnly}">
                        <kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:document.committeeList[0].homeUnitNumber" />
                    </c:if>
                    <div id="document.committee.homeUnitName.div" align="left" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeTypeCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.committeeList[0].committeeTypeCode" 
                	                          attributeEntry="${committeeAttributes.committeeTypeCode}" 
                	                          readOnlyAlternateDisplay="${KualiForm.document.committeeList[0].committeeType.description}"/>
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeDescription}" /></div></th>
                <td>
                    <kra:kraControlAttribute property="document.committeeList[0].committeeDescription" attributeEntry="${committeeAttributes.committeeDescription}" />
                    <c:if test="${!readOnly}">
                        <kra:expandedTextArea textAreaFieldName="document.committeeList[0].committeeDescription" action="${action}" textAreaLabel="${committeeAttributes.committeeDescription.label}" />
                    </c:if>
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.scheduleDescription}" /></div></th>
                <td align="left" valign="middle">
                	<kra:kraControlAttribute property="document.committeeList[0].scheduleDescription" attributeEntry="${committeeAttributes.scheduleDescription}" />
                    <c:if test="${!readOnly}">
                        <kra:expandedTextArea textAreaFieldName="document.committeeList[0].scheduleDescription" action="${action}" textAreaLabel="${committeeAttributes.scheduleDescription.label}" />
                    </c:if>
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.minimumMembersRequired}" /></div></th>
                <td>
                    <kra:kraControlAttribute property="document.committeeList[0].minimumMembersRequired" attributeEntry="${committeeAttributes.minimumMembersRequired}" />
                </td>
                
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.reviewTypeCode}" /></div></th>
                <td align="left" valign="middle">
                    <c:if test="${readOnly}">
                	    ${KualiForm.document.committeeList[0].reviewType.description}
                    </c:if>
                    <c:if test="${!readOnly}">
                	    <kul:htmlControlAttribute property="document.committeeList[0].reviewTypeCode" attributeEntry="${committeeAttributes.reviewTypeCode}" />
                    </c:if>
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.maxProtocols}" /></div></th>
                <td>
                    <kra:kraControlAttribute property="document.committeeList[0].maxProtocols" attributeEntry="${committeeAttributes.maxProtocols}" />
                </td>
                <th><div align="right"><nobr><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.updateTimestamp}" /></nobr></div></th>
                <td align="left" valign="middle">
                    <kra:kraControlAttribute property="document.committeeList[0].updateTimestamp" readOnly="true" attributeEntry="${committeeAttributes.updateTimestamp}" />
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.advancedSubmissionDaysRequired}" /></div></th>
                <td>
                    <kra:kraControlAttribute property="document.committeeList[0].advancedSubmissionDaysRequired" attributeEntry="${committeeAttributes.advancedSubmissionDaysRequired}" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.updateUser}" /></div></th>
                <td align="left" valign="middle">
                    <kra:kraControlAttribute property="document.committeeList[0].updateUser" readOnly="true" attributeEntry="${committeeAttributes.updateUser}" />
                </td>
            </tr>
		</table>
	</div>	
</kul:tab>