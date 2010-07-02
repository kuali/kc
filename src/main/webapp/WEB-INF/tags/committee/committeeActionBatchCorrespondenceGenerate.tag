<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="committeeAttributes" value="${DataDictionary.Committee.attributes}" />
<c:set var="batchCorrespondenceDetailAttributes" value="${DataDictionary.BatchCorrespondenceDetail.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />

<kra:softError softErrorKey="committeeHelper.committeeActionsHelper.generateBatchCorrespondenceTypeCode" />

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

<c:if test="${not empty KualiForm.committeeHelper.committeeActionsHelper.generateBatchCorrespondence}">
    <p>&nbsp;</p>
        
    <div align="left">
        <kul:errors keyMatch="committeeHelper.committeeActionsHelper.generateBatchCorrespondence" /> <br>
    </div>

    <h3>
        <span class="subhead-left">
            Generated Batch Correspondence
        </span>
        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.committee.bo.CommitteeBatchCorrespondence" altText="help" /></span>
    </h3>

    <table cellpadding=0 cellspacing=0 border=0>
        <tr>
            <td class="infoline" colspan="2">
                <kra-committee:committeeActionBatchCorrespondenceRun committeeBatchCorrespondence="${KualiForm.committeeHelper.committeeActionsHelper.generateBatchCorrespondence[0]}" committeeBatchCorrespondenceProperty="committeeHelper.committeeActionsHelper.generateBatchCorrespondence[0]" />
            </td>
        </tr>
        <tr>
            <td class="neutral" style="background-color: #e4e4e4;">
                &nbsp;
            </td>
            <td  style="background-color: #e4e4e4;" width="63">
                <div align="center">
                    <html:image property="methodToCall.viewBatchCorrespondenceGenerated"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
                                styleClass="tinybutton"
                                onclick="excludeSubmitRestriction = true;" />
                </div>                         
            </td>
        </tr>
    </table>
    
    <div style="center">
        Number of protocols on which final action has been performed: 
        ${KualiForm.committeeHelper.committeeActionsHelper.generateBatchCorrespondence[0].finalActionCounter}
    </div>
</c:if>            

