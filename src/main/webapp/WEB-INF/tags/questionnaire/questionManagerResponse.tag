<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<script>
    function showQuestionType() {
        document.getElementById("question_response_type_select").style.display = "none";
        document.getElementById("question_response_type_yes_no").style.display = "none";
        document.getElementById("question_response_type_yes_no_na").style.display = "none";
        document.getElementById("question_response_type_number").style.display = "none";
        document.getElementById("question_response_type_date").style.display = "none";
        document.getElementById("question_response_type_text").style.display = "none";
        document.getElementById("question_response_type_lookup").style.display = "none";

        switch(document.getElementById("document.newMaintainableObject.businessObject.questionTypeId").value) {
        case "1" :
            document.getElementById("question_response_type_yes_no").style.display = "block";
            break;
        case "2" :
            document.getElementById("question_response_type_yes_no_na").style.display = "block";
            break;
        case "3" :
            document.getElementById("question_response_type_number").style.display = "block";
            break;
        case "4" :
            document.getElementById("question_response_type_date").style.display = "block";
            break;
        case "5" :
            document.getElementById("question_response_type_text").style.display = "block";
            break;
        case "6" :
            document.getElementById("question_response_type_lookup").style.display = "block";
            break;
        default :
            document.getElementById("question_response_type_select").style.display = "block";
        }
    }
</script>

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Response </span>
    </h3>
        
    <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
            <th align="center" valign="middle" width="115">Type</th>
            <th align="center" valign="middle">Values</th>
        </tr>
        <tr>
            <td align="left" valign="middle">
                <div align="center">
                    <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.questionTypeId" 
                                              attributeEntry="${DataDictionary.Question.attributes.questionTypeId}"
                                              onchange="javascript:showQuestionType();" 
                                              readOnly="${readOnly}" />
                </div>
            </td>
            <td>
             <div id="deb1"></div>
                <%-- Start No Selection --%>
                <c:if test="${(KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName == 'select') || (empty KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName)}">
                    <div id="question_response_type_select" style="display: block">
                </c:if>
                <c:if test="${(KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName != 'select') && (!empty KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName)}">
                    <div id="question_response_type_select" style="display: none">
                </c:if>
                    <p> 
                        <i>Please select the Type of response you would like for this question.</i> <br />
                    </p>
                </div>
                <%-- End No Selection --%>
                
                <%-- Start Yes/No Answer --%>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName == 'Yes/No'}">
                    <div id="question_response_type_yes_no" style="display: block">
                </c:if>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName != 'Yes/No'}">
                    <div id="question_response_type_yes_no" style="display: none">
                </c:if>
                    <p> 
                        The user will be presented with the following radio buttons: Yes, No. <br />
                        Only one selection is possible. <br />
                        A selection is required. <br />
                    </p>
                </div>
                <%-- End Yes/No Answer --%>

                <%-- Start Yes/No/NA Answer --%>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName == 'Yes/No/NA'}">
                    <div id="question_response_type_yes_no_na" style="display: block">
                </c:if>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName != 'Yes/No/NA'}">
                    <div id="question_response_type_yes_no_na" style="display: none">
                </c:if>
                    <p> 
                        The user will be presented with the following pulldown: Yes, No, Not Applicable. <br />
                        Only one selection is possible. <br />
                        A selection is required. <br />
                    </p>
                </div>
                <%-- End Yes/No/NA Answer --%>
                
                <%-- Start Number --%>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName == 'Number'}">
                    <div id="question_response_type_number" style="display: block">
                </c:if>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName != 'Number'}">
                    <div id="question_response_type_number" style="display: none">
                </c:if>
                    <p> 
                        The user will be presented with
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.displayedAnswers" 
                                                  attributeEntry="${DataDictionary.Question.attributes.displayedAnswers}" 
                                                  readOnly="${readOnly}" /> text boxes. <br />
                        The entered value will be validated requiring a number only. <br />
                        The maximum length of the number in characters is
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.answerMaxLength" 
                                                  attributeEntry="${DataDictionary.Question.attributes.answerMaxLength}" 
                                                  readOnly="${readOnly}" />. <br />
                        The number of possible answers is
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.maxAnswers" 
                                                  attributeEntry="${DataDictionary.Question.attributes.maxAnswers}" 
                                                  readOnly="${readOnly}" />, not exceeding the number of text boxes presented. <br />
                    </p>
                </div>
                <%-- End Number --%>
                
                <%-- Start Date --%>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName == 'Date'}">
                    <div id="question_response_type_date" style="display: block">
                </c:if>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName != 'Date'}">
                    <div id="question_response_type_date" style="display: none">
                </c:if>
                    <p> 
                        The user will be presented with
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.displayedAnswers" 
                                                  attributeEntry="${DataDictionary.Question.attributes.displayedAnswers}" 
                                                  readOnly="${readOnly}" /> text boxes. <br />
                        The entered value will be validated for a date in MM/DD/YYYY format. <br />
                        A response is required for each text box. <br />
                        The number of possible answers is
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.maxAnswers" 
                                                  attributeEntry="${DataDictionary.Question.attributes.maxAnswers}" 
                                                  readOnly="${readOnly}" />, not exceeding the number of text boxes presented. <br />
                    </p>
                </div>
                <%-- End Date --%>
                
                <%-- Start Text --%>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName == 'Text'}">
                    <div id="question_response_type_text" style="display: block">
                </c:if>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName != 'Text'}">
                    <div id="question_response_type_text" style="display: none">
                </c:if>
                    <p> 
                        The user will be presented with
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.displayedAnswers" 
                                                  attributeEntry="${DataDictionary.Question.attributes.displayedAnswers}" 
                                                  readOnly="${readOnly}" /> text areas. <br />
                        The number of possible answers is
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.maxAnswers" 
                                                  attributeEntry="${DataDictionary.Question.attributes.maxAnswers}" 
                                                  readOnly="${readOnly}" />, not exceeding the number of text areas presented. <br />
                        The maximum length of each response in characters: 
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.answerMaxLength" 
                                                  attributeEntry="${DataDictionary.Question.attributes.answerMaxLength}" 
                                                  readOnly="${readOnly}" />. <br />
                    </p>
                </div>
                <%-- End Text --%>

                <%-- Start Lookup --%>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName == 'Lookup'}">
                    <div id="question_response_type_lookup" style="display: block">
                </c:if>
                <c:if test="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName != 'Lookup'}">
                    <div id="question_response_type_lookup" style="display: none">
                </c:if>
                    <p>
                        The user will be presented with the ability to search for ____. <br />
                        The field to return is ______. <br />
                        The number of possible returns is
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.maxAnswers" 
                                                  attributeEntry="${DataDictionary.Question.attributes.maxAnswers}" 
                                                  readOnly="${readOnly}" />, not exceeding the number of text areas presented. <br />
                    </p>
                </div>
                <%-- End Lookup --%>

            </td>
        </tr>
    </table>
</div>
