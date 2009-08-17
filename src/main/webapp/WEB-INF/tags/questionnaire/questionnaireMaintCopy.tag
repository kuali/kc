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
               <html:text property="document.oldMaintainableObject.businessObject.name" 
                           styleId="document.oldMaintainableObject.businessObject.name" readonly="true"/>
            </td>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td class="grid" width="25.0%">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.name" 
                                          attributeEntry="${questionnaireAttributes.name}" />
            </td>
        </tr>
        <tr>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td class="grid" width="25.0%">
               <html:textarea property="document.oldMaintainableObject.businessObject.description" rows="5" cols="40"
                           styleId="document.oldMaintainableObject.businessObject.description" readonly="true"/>
            </td>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td class="grid" width="25.0%">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.description" 
                                          attributeEntry="${questionnaireAttributes.description}" />
            </td>
        </tr>
        <tr>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.isFinal}" />
            </th>
            <td class="grid" width="25.0%">
               <html:checkbox property="document.oldMaintainableObject.businessObject.isFinal" 
                           styleId="document.oldMaintainableObject.businessObject.isFinal" disabled="true"/>
                           <bean:write name="KualiForm" property="document.oldMaintainableObject.businessObject.isFinal"/>
            </td>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.isFinal}" />
            </th>
            <td class="grid" width="25.0%">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.isFinal" 
                                          attributeEntry="${questionnaireAttributes.isFinal}" />
            </td>
        </tr>
    </table>
    
    <input type="hidden" id="document.oldMaintainableObject.businessObject.questionnaireRefId" name="document.oldMaintainableObject.businessObject.questionnaireRefId" value = "${KualiForm.document.oldMaintainableObject.businessObject.questionnaireRefId}"/>
    <input type="hidden" id="retData" name="retData" value = "${KualiForm.retData}"/>
</div>