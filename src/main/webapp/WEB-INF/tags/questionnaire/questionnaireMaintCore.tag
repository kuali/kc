<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/>
<c:set var="questionnaireAttributes" value="${DataDictionary.Questionnaire.attributes}" />

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Details </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.questionnaire.Questionnaire" altText="help"/> </span>
    </h3>
    
        
    <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.name" 
                                          attributeEntry="${questionnaireAttributes.name}" />
            </td>
            <td align="left" valign="middle">
                    Version ${KualiForm.document.newMaintainableObject.businessObject.sequenceNumber}
            </td>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.isFinal}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.isFinal" 
                                          attributeEntry="${questionnaireAttributes.isFinal}" />
            </td>
            
        </tr>
        <tr>
            <th align="right" valign="middle" width="115">
                Status:
            </th>
            <td align="left" valign="middle" colspan="4">
                This questionnaire is up to date. All questions used are the latest versions. 
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td align="left" valign="middle" colspan="4">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.description" 
                                          attributeEntry="${questionnaireAttributes.description}" />
            </td>
        </tr>
    </table>
	<input type="hidden" id="questionNumber" name="questionNumber" value = "${KualiForm.questionNumber}"/>
    <%--  <input type="hidden" id="sqlScripts" name="sqlScripts" value = "${KualiForm.sqlScripts}"/> --%>
	<input type="hidden" id="document.newMaintainableObject.businessObject.questionnaireRefId" name="document.newMaintainableObject.businessObject.questionnaireRefId" value = "${KualiForm.document.newMaintainableObject.businessObject.questionnaireRefId}"/>
    <input type="hidden" id="document.newMaintainableObject.businessObject.questionnaireId" name="document.newMaintainableObject.businessObject.questionnaireId" value = "${KualiForm.document.newMaintainableObject.businessObject.questionnaireId}"/>
    <input type="hidden" id="document.newMaintainableObject.businessObject.sequenceNumber" name="document.newMaintainableObject.businessObject.sequenceNumber" value = "${KualiForm.document.newMaintainableObject.businessObject.sequenceNumber}"/>
	<%--  <input type="hidden" id="retData" name="retData" value = "${KualiForm.retData}"/> --%>
	<input type="hidden" id="editData" name="editData" value = "${KualiForm.editData}"/>
	<%--  <input type="hidden" id="versioned" name="versioned" value = "${KualiForm.versioned}"/>--%>
</div>