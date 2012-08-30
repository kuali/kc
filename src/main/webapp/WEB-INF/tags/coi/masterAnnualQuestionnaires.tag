<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:tab defaultOpen="true" tabTitle="Questionnaire" transparentBackground="false"
    tabErrorKey="" >
    <c:set var="answerHeaderIndex" value="0" />
    <c:set var="property" value="disclosureQuestionnaireHelper" />
    <c:set var="bean" value="${KualiForm.disclosureQuestionnaireHelper}" />
            
            
    <c:forEach items="${bean.answerHeaders}" var="answerHeader" varStatus="ahstatus">
        <div class="tab-container" align="center">
            <c:set var="prop" value="${property}.answerHeaders[${ahstatus.index}].showQuestions"/>
            ${kfunc:registerEditableProperty(KualiForm, prop)}
            <input type="hidden" name="${prop}" id ="${prop}" 
                   value = "${bean.answerHeaders[ahstatus.index].showQuestions}" />
            <kra-coi:masterQuestionnaireAnswers bean = "${bean}" property = "${property}" answerHeaderIndex = "${ahstatus.index}" parentTab="Master Disclosure"/>
       </div>
    </c:forEach>
</kul:tab>