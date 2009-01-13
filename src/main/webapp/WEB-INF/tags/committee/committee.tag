<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="committeeAttributes" value="${DataDictionary.Committee.attributes}" />
<c:set var="action" value="committeeCommittee" />
<c:set var="className" value="org.kuali.kra.committee.document.CommitteeDocument" />

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
                    <kra:kraControlAttribute property="document.committeeList[0].committeeId" readOnly="${readOnly}" attributeEntry="${committeeAttributes.committeeId}" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeName}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.committeeList[0].committeeName" attributeEntry="${committeeAttributes.committeeName}" />
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.homeUnitNumber}" /></div></th>
                <td>
                    <kul:htmlControlAttribute property="document.committeeList[0].homeUnitNumber" readOnly="${readOnly}" attributeEntry="${committeeAttributes.homeUnitNumber}" onblur="loadUnitNameTo('document.committeeList[0].homeUnitNumber','document.committee.homeUnitName');" />
                    <kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:document.committeeList[0].homeUnitNumber" />
                    
                    <div id="document.committee.homeUnitName.div" align="left" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeTypeCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.committeeList[0].committeeTypeCode" attributeEntry="${committeeAttributes.committeeTypeCode}" />
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeDescription}" /></div></th>
                <td>
                    <kra:kraControlAttribute property="document.committeeList[0].committeeDescription" readOnly="${readOnly}" attributeEntry="${committeeAttributes.committeeDescription}" />
                    <kra:expandedTextArea textAreaFieldName="document.committeeList[0].committeeDescription" action="${action}" textAreaLabel="${committeeAttributes.committeeDescription.label}" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.scheduleDescription}" /></div></th>
                <td align="left" valign="middle">
                	<kra:kraControlAttribute property="document.committeeList[0].scheduleDescription" readOnly="${readOnly}" attributeEntry="${committeeAttributes.scheduleDescription}" />
                    <kra:expandedTextArea textAreaFieldName="document.committeeList[0].scheduleDescription" action="${action}" textAreaLabel="${committeeAttributes.scheduleDescription.label}" />
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.minimumMembersRequired}" /></div></th>
                <td>
                    <kra:kraControlAttribute property="document.committeeList[0].minimumMembersRequired" readOnly="${readOnly}" attributeEntry="${committeeAttributes.minimumMembersRequired}" />
                </td>
                
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.reviewTypeCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.committeeList[0].reviewTypeCode" attributeEntry="${committeeAttributes.reviewTypeCode}" />
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.maxProtocols}" /></div></th>
                <td>
                    <kra:kraControlAttribute property="document.committeeList[0].maxProtocols" readOnly="${readOnly}" attributeEntry="${committeeAttributes.maxProtocols}" />
                </td>
                <th></th>
                <td align="left" valign="middle">
                
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.advancedSubmissionDaysRequired}" /></div></th>
                <td>
                    <kra:kraControlAttribute property="document.committeeList[0].advancedSubmissionDaysRequired" readOnly="${readOnly}" attributeEntry="${committeeAttributes.advancedSubmissionDaysRequired}" />
                </td>
                <th></th>
                <td align="left" valign="middle">
                
                </td>
            </tr>
		</table>
	</div>	
</kul:tab>