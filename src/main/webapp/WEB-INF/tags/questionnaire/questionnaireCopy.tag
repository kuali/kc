<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="readOnly" value="false"  scope="request"/>
<c:set var="questionnaireAttributes" value="${DataDictionary.Questionnaire.attributes}" />

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Questionnaire </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.questionnaire.Questionnaire" altText="help"/> </span>
    </h3>
    
    <table id="from-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
		<td colspan="2" class="tab-subhead" width="50%">
			<div class="tab-subhead-r">
                Original
			</div>
		</td>
		<td colspan="2" class="tab-subhead" width="50%">
            New Copy
		</td>
        </tr>
        <tr>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td class="grid" width="25.0%">
               <html:text property="fromQuestionnaire.name" 
                           styleId="fromQuestionnaire.name" readonly="true"/>
            </td>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td class="grid" width="25.0%">
                <kul:htmlControlAttribute property="newQuestionnaire.name" 
                                          attributeEntry="${questionnaireAttributes.name}" />
            </td>
        </tr>
        <tr>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td class="grid" width="25.0%">
               <html:textarea property="fromQuestionnaire.description" rows="5" cols="40"
                           styleId="fromQuestionnaire.description" readonly="true"/>
            </td>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td class="grid" width="25.0%">
                <kul:htmlControlAttribute property="newQuestionnaire.description" 
                                          attributeEntry="${questionnaireAttributes.description}" />
            </td>
        </tr>
        <tr>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.isFinal}" />
            </th>
            <td class="grid" width="25.0%">
               <html:checkbox property="fromQuestionnaire.isFinal" 
                           styleId="fromQuestionnaire.isFinal" disabled="true"/>
            </td>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.isFinal}" />
            </th>
            <td class="grid" width="25.0%">
                <kul:htmlControlAttribute property="newQuestionnaire.isFinal" 
                                          attributeEntry="${questionnaireAttributes.isFinal}" />
            </td>
        </tr>
    </table>
    
	<input type="hidden" id="fromQuestionnaire.questionnaireId" name="fromQuestionnaire.questionnaireId" value = "${QuestionnaireForm.fromQuestionnaire.questionnaireId}"/>
	<input type="hidden" id="retData" name="retData" value = "${QuestionnaireForm.retData}"/>
</div>