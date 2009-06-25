<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="readOnly" value="false"  scope="request"/>

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Question </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.questionnaire.question.Question" altText="help"/> </span>
    </h3>
        
    <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.question}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.question" 
                                          attributeEntry="${DataDictionary.Question.attributes.question}" 
                                          readOnly="${readOnly}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                Version:
            </th>
            <td align="left" valign="middle">
                not yet implemented
            </td>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.status}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.status" 
                                          attributeEntry="${DataDictionary.Question.attributes.status}" 
                                          readOnly="${readOnly}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.questionId}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.questionId" 
                                          attributeEntry="${DataDictionary.Question.attributes.questionId}" 
                                          readOnly="${readOnly}" />
            </td>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.categoryTypeCode}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.categoryTypeCode" 
                                          attributeEntry="${DataDictionary.Question.attributes.categoryTypeCode}"
                                          readOnlyAlternateDisplay="${KualiForm.document.newMaintainableObject.businessObject.questionCategory.categoryName}" 
                                          readOnly="true" />
                <c:if test="${!readOnly}">
                    <kul:lookup boClassName="org.kuali.kra.questionnaire.question.QuestionCategory" fieldConversions="categoryTypeCode:document.newMaintainableObject.businessObject.categoryTypeCode,categoryName:document.newMaintainableObject.businessObject.questionCategory.categoryName" />
                </c:if>
                
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.explanation}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.explanation" 
                                          attributeEntry="${DataDictionary.Question.attributes.explanation}" 
                                          readOnly="${readOnly}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.policy}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.policy" 
                                          attributeEntry="${DataDictionary.Question.attributes.policy}" 
                                          readOnly="${readOnly}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.regulation}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.regulation" 
                                          attributeEntry="${DataDictionary.Question.attributes.regulation}" 
                                          readOnly="${readOnly}" />
            </td>
        </tr>
    </table>
</div>