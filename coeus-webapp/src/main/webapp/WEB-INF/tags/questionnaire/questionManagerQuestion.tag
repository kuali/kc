<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Question </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.coeus.common.questionnaire.framework.question.Question" altText="help"/> </span>
    </h3>
        
    <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.question}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.question" 
                                          attributeEntry="${DataDictionary.Question.attributes.question}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                Version:
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.sequenceNumber" 
                                          attributeEntry="${DataDictionary.Question.attributes.sequenceNumber}"
                                          readOnly="true" />
            </td>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.status}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.status" 
                                          attributeEntry="${DataDictionary.Question.attributes.status}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.id}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.id" 
                                          attributeEntry="${DataDictionary.Question.attributes.id}" 
                                          readOnly="true" />
            </td>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.categoryTypeCode}" />
            </th>
            <td align="left" valign="middle">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.categoryTypeCode" 
                                          attributeEntry="${DataDictionary.Question.attributes.categoryTypeCode}"/>
                                        
                <c:if test="${!readOnly}">
                    <kul:lookup boClassName="org.kuali.coeus.common.questionnaire.framework.question.QuestionCategory"
                                fieldConversions="categoryTypeCode:document.newMaintainableObject.businessObject.categoryTypeCode,name:document.newMaintainableObject.businessObject.questionCategory.name" />
                    <c:forEach items="${ErrorPropertyList}" var="key">
                        <c:if test="${key eq 'document.newMaintainableObject.businessObject.categoryTypeCode'}">
                            <kul:fieldShowErrorIcon />
                        </c:if>
                    </c:forEach>            
                </c:if>
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.explanation}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.explanation" 
                                          attributeEntry="${DataDictionary.Question.attributes.explanation}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.policy}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.policy" 
                                          attributeEntry="${DataDictionary.Question.attributes.policy}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.regulation}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.regulation" 
                                          attributeEntry="${DataDictionary.Question.attributes.regulation}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.affirmativeStatementConversion}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.affirmativeStatementConversion" 
                                          attributeEntry="${DataDictionary.Question.attributes.affirmativeStatementConversion}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.Question.attributes.negativeStatementConversion}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.negativeStatementConversion" 
                                          attributeEntry="${DataDictionary.Question.attributes.negativeStatementConversion}" />
            </td>
        </tr>
    </table>
</div>
