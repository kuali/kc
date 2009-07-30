<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<script type="text/javascript">
    function showQuestionType() {
        document.getElementById("displayed_answers_pre_message").innerHTML = "";
        document.getElementById("displayed_answers_post_message").innerHTML = "";
        document.getElementById("displayed_answers_html_control").style.display = "none";
        document.getElementById("displayed_answers_br").style.display = "none";
        document.getElementById("lookup_gui_pre_message").innerHTML = "";
        document.getElementById("lookup_gui_post_message").innerHTML = "";
        document.getElementById("lookup_gui_html_control").style.display = "none";
        document.getElementById("lookup_gui_br").style.display = "none";
        document.getElementById("lookup_name_pre_message").innerHTML = "";
        document.getElementById("lookup_name_post_message").innerHTML = "";
        document.getElementById("lookup_name_html_control").style.display = "none";
        document.getElementById("lookup_name_br").style.display = "none";
        document.getElementById("response_message").innerHTML = "";
        document.getElementById("response_message_br").style.display = "none";
        document.getElementById("answer_max_length_pre_message").innerHTML = "";
        document.getElementById("answer_max_length_post_message").innerHTML = "";
        document.getElementById("answer_max_length_html_control").style.display = "none";
        document.getElementById("answer_max_length_br").style.display = "none";
        document.getElementById("max_answers_pre_message").innerHTML = "";
        document.getElementById("max_answers_post_message").innerHTML = "";
        document.getElementById("max_answers_html_control").style.display = "none";
        document.getElementById("max_answers_br").style.display = "none";

        switch(document.getElementById("document.newMaintainableObject.businessObject.questionTypeId").value) {
        case "" :
            document.getElementById("response_message").innerHTML = "<i> Please select the Type of response you would like for this question. </i>";
            document.getElementById("response_message_br").style.display = "inline";
            break;
        case "1" :
            document.getElementById("response_message").innerHTML = "The user will be presented with the following radio buttons: Yes, No. <br /> Only one selection is possible. <br /> A selection is required.";
            document.getElementById("response_message_br").style.display = "inline";
            break;
        case "2" :
            document.getElementById("response_message").innerHTML = "The user will be presented with the following pulldown: Yes, No, Not Applicable. <br /> Only one selection is possible. <br /> A selection is required.";
            document.getElementById("response_message_br").style.display = "inline";
            break;
        case "3" :
            document.getElementById("displayed_answers_pre_message").innerHTML = "The user will be presented with ";
            document.getElementById("displayed_answers_post_message").innerHTML = " text boxes.";
            document.getElementById("displayed_answers_html_control").style.display = "inline";
            document.getElementById("displayed_answers_br").style.display = "inline";
            document.getElementById("response_message").innerHTML = "The entered value will be validated requiring a number only.";
            document.getElementById("response_message_br").style.display = "inline";
            document.getElementById("answer_max_length_pre_message").innerHTML = "The maximum length of the number in characters is ";
            document.getElementById("answer_max_length_post_message").innerHTML = ".";
            document.getElementById("answer_max_length_html_control").style.display = "inline";
            document.getElementById("answer_max_length_br").style.display = "inline";
            document.getElementById("max_answers_pre_message").innerHTML = "The number of possible answers is ";
            document.getElementById("max_answers_post_message").innerHTML = ", not exceeding the number of text boxes presented.";
            document.getElementById("max_answers_html_control").style.display = "inline";
            document.getElementById("max_answers_br").style.display = "inline";
            break;
        case "4" :
            document.getElementById("displayed_answers_pre_message").innerHTML = "The user will be presented with ";
            document.getElementById("displayed_answers_post_message").innerHTML = " text boxes.";
            document.getElementById("displayed_answers_html_control").style.display = "inline";
            document.getElementById("displayed_answers_br").style.display = "inline";
            document.getElementById("response_message").innerHTML = "The entered value will be validated for a date in MM/DD/YYYY format. <br /> A response is required for each text box.";
            document.getElementById("response_message_br").style.display = "inline";
            document.getElementById("max_answers_pre_message").innerHTML = "The number of possible answers is ";
            document.getElementById("max_answers_post_message").innerHTML = ", not exceeding the number of text boxes presented.";
            document.getElementById("max_answers_html_control").style.display = "inline";
            document.getElementById("max_answers_br").style.display = "inline";
            break;
        case "5" :
        	document.getElementById("displayed_answers_pre_message").innerHTML = "The user will be presented with ";
        	document.getElementById("displayed_answers_post_message").innerHTML = " text areas.";
            document.getElementById("displayed_answers_html_control").style.display = "inline";
            document.getElementById("displayed_answers_br").style.display = "inline";
            document.getElementById("answer_max_length_pre_message").innerHTML = "Maximum length of each response in characters: ";
            document.getElementById("answer_max_length_post_message").innerHTML = ".";
            document.getElementById("answer_max_length_html_control").style.display = "inline";
            document.getElementById("answer_max_length_br").style.display = "inline";
            document.getElementById("max_answers_pre_message").innerHTML = "The number of possible answers is ";
            document.getElementById("max_answers_post_message").innerHTML = ", not exceeding the number of text areas presented.";
            document.getElementById("max_answers_html_control").style.display = "inline";
            document.getElementById("max_answers_br").style.display = "inline";
            break;
        case "6" :
            document.getElementById("lookup_gui_pre_message").innerHTML = "The user will be presented with the ability to search for ";
            document.getElementById("lookup_gui_post_message").innerHTML = ".";
            document.getElementById("lookup_gui_html_control").style.display = "inline";
            document.getElementById("lookup_gui_br").style.display = "inline";
            document.getElementById("lookup_name_pre_message").innerHTML = "The field to return is ";
            document.getElementById("lookup_name_post_message").innerHTML = ".";
            document.getElementById("lookup_name_html_control").style.display = "inline";
            document.getElementById("lookup_name_br").style.display = "inline";
            document.getElementById("max_answers_pre_message").innerHTML = "The number of possible returns is ";
            document.getElementById("max_answers_post_message").innerHTML = ".";
            document.getElementById("max_answers_html_control").style.display = "inline";
            document.getElementById("max_answers_br").style.display = "inline";
            break;
        default :
            document.getElementById("response_message").innerHTML = "<i>The question type is not yet supported.  Contact the system administrator to have this fixed.  Meanwhile please select a different Type of response for for this question.</i> <br />";
            document.getElementById("response_message_br").style.display = "inline";
            break;
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
                                              readOnlyAlternateDisplay="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName}"
                                              onchange="javascript:showQuestionType();" />
                    <noscript>
                        <p>
                            <html:image property="methodToCall.loadQuestionResponse.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif' styleClass="tinybutton" />
                        </p>
                    </noscript>
                </div>
            </td>
            <td>
                <br />
                <kra-questionnaire:questionManagerResponseDisplayedAnswers />
                <kra-questionnaire:questionManagerResponseLookupGui />
                <kra-questionnaire:questionManagerResponseLookupName />
                <kra-questionnaire:questionManagerResponseMessage />
                <kra-questionnaire:questionManagerResponseAnswerMaxLength />
                <kra-questionnaire:questionManagerResponseMaxAnswers />
                <br />
            </td>
        </tr>
    </table>
</div>
