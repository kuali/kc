<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="committeeAttributes" value="${DataDictionary.Committee.attributes}" />
<c:set var="batchCorrespondenceDetailAttributes" value="${DataDictionary.BatchCorrespondenceDetail.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />

<h3>
    <span class="subhead-left">Generate Batch Correspondence</span>
    <span class="subhead-right"><kul:help documentTypeName="CommitteeDocument" pageName="Committee Actions" /></span>
</h3>

<table cellpadding="0" cellspacing="0">
    <tr>
        <th width="20%">
            <div align="right">
                Committee ID:
            </div>
        </th width="30%"> 
        <td>
            <div align="left">
                <kul:htmlControlAttribute property="document.committeeList[0].committeeId" 
                                          attributeEntry="${committeeAttributes.committeeId}" 
                                          readOnly="true" />
            </div>
        </td>
        
        <th width="20%">
            <div align="right">
                Committee Name:
            </div>
        </th> 
        <td width="30%">
            <div align="left">
                <kul:htmlControlAttribute property="document.committeeList[0].committeeName" 
                                          attributeEntry="${committeeAttributes.committeeName}" 
                                          readOnly="true" />
            </div>
        </td>
    </tr> 
    <tr>
        <th>
            <div align="right">
                Batch Type:
            </div>
        </th> 
        <td>
            <div align="left">
                <kul:htmlControlAttribute property="committeeHelper.committeeActionsHelper.generateBatchCorrespondenceTypeCode" 
                                          attributeEntry="${batchCorrespondenceDetailAttributes.batchCorrespondenceTypeCode}" 
                                          readOnly="false" />
            </div>
        </td>
        
        <th>
            <div align="right">
                Protocol Expiration / Committee Action:
            </div>
        </th> 
        <td>
            <div align="left">
                from
                <kul:htmlControlAttribute property="committeeHelper.committeeActionsHelper.generateStartDate" 
                                          attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}"
                                          readOnly="false" />
                to
                <kul:htmlControlAttribute property="committeeHelper.committeeActionsHelper.generateEndDate" 
                                          attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" 
                                          readOnly="false" />
            </div>
        </td>
    </tr> 
    <tr>
        <td class="infoline" colspan="4">
            <div align="center">
                <html:image property="methodToCall.generateBatchCorrespondence"
                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' 
                            styleClass="tinybutton"/>
            </div>                         
        </td>
    </tr> 
</table>
