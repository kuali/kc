<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="readOnly" value="false"  scope="request"/>
<c:set var="questionnaireAttributes" value="${DataDictionary.Questionnaire.attributes}" />

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Questionnaire </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.questionnaire.Questionnaire" altText="help"/> </span>
    </h3>
    
        
    <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="newQuestionnaire.name" 
                                          attributeEntry="${questionnaireAttributes.name}" />
            </td>
            <td align="left" valign="middle">
                Version V1.00 ?
            </td>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.isFinal}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="newQuestionnaire.isFinal" 
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
                <kul:htmlControlAttribute property="newQuestionnaire.description" 
                                          attributeEntry="${questionnaireAttributes.description}" />
            </td>
        </tr>
    </table>
	<input type="hidden" id="questionNumber" name="questionNumber" value = "${QuestionnaireForm.questionNumber}"/>
	<input type="hidden" id="newQuestionnaire.questionnaireId" name="newQuestionnaire.questionnaireId" value = "${QuestionnaireForm.newQuestionnaire.questionnaireId}"/>
	<input type="hidden" id="retData" name="retData" value = "${QuestionnaireForm.retData}"/>
	<input type="hidden" id="editData" name="retData" value = "${QuestionnaireForm.editData}"/>
</div>