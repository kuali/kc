<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="readOnly" value="false"  scope="request"/>
<c:set var="questionnaireAttributes" value="${DataDictionary.Questionnaire.attributes}" />

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Questionnaire </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.questionnaire.Questionnaire" altText="help"/> </span>
    </h3>
    
   <table width="100%" cellpadding="0" cellspacing="0" style="border-top:#E4E3E4 solid 1px;">
       <tr>
           <th class="subelementheader" style="text-align:left;color: #708090;" colspan="3" >
               From Questionnaire :
           </th>
       </tr>
   </table>
    

    <table id="from-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="fromQuestionnaire.name" 
                                          attributeEntry="${questionnaireAttributes.name}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="fromQuestionnaire.description" 
                                          attributeEntry="${questionnaireAttributes.description}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.isFinal}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="fromQuestionnaire.isFinal" 
                                          attributeEntry="${questionnaireAttributes.isFinal}" />
            </td>
        </tr>
    </table>
   <table width="100%" cellpadding="0" cellspacing="0" style="border-top:#E4E3E4 solid 1px;">
       <tr>
           <th class="subelementheader" style="text-align:left;;color: #708090;" colspan="3">
               To Questionnaire :
           </th>
       </tr>
   </table>
         
    <table id="to-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="newQuestionnaire.name" 
                                          attributeEntry="${questionnaireAttributes.name}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="newQuestionnaire.description" 
                                          attributeEntry="${questionnaireAttributes.description}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.isFinal}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="newQuestionnaire.isFinal" 
                                          attributeEntry="${questionnaireAttributes.isFinal}" />
            </td>
        </tr>
    </table>
    
	<input type="hidden" id="fromQuestionnaire.questionnaireId" name="fromQuestionnaire.questionnaireId" value = "${QuestionnaireForm.fromQuestionnaire.questionnaireId}"/>
</div>