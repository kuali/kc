<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="disclProjectBean" required="true" type="org.kuali.kra.coi.disclosure.CoiDisclosureProjectBean" description="A List of active or inactive FE" %>
<%@ attribute name="parentTab" required="true" description="name for project parent tab" %>

    <c:set var="answerHeaderIndex" value="0" />
    <c:set var="property" value="disclosureQuestionnaireHelper" />
    <c:set var="bean" value="${KualiForm.disclosureQuestionnaireHelper}" />
            
            
    <c:forEach items="${bean.answerHeaders}" var="answerHeader" varStatus="ahstatus">
    <c:forEach items="${disclProjectBean.answerHeaders}" var="answerHeader1" varStatus="ahstatus1">
       <c:if test="${answerHeader.answerHeaderId eq answerHeader1.answerHeaderId}" >
      <%--  <div class="tab-container" align="center"> --%>
            <c:set var="prop" value="${property}.answerHeaders[${ahstatus.index}].showQuestions"/>
            ${kfunc:registerEditableProperty(KualiForm, prop)}
            <input type="hidden" name="${prop}" id ="${prop}" 
                   value = "${bean.answerHeaders[ahstatus.index].showQuestions}" />
            <kra-coi:masterQuestionnaireAnswers bean = "${bean}" property = "${property}" answerHeaderIndex = "${ahstatus.index}" parentTab="${parentTab}"/>
     <%--    </div>     --%>
       </c:if>               
    </c:forEach>
    </c:forEach>
