<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>

<html-el:messages name="workflowServiceError" id="msg">
  <!-- workflow-service-error -->
  <div class="error-div"><span class="workflow-service-error"><c:out value="${msg}"/></span></div>
</html-el:messages>

<html-el:messages name="exceptionError" id="msg">
  <!-- exception-error -->
  <div class="exception-error-div"><span class="exception-error"><c:out value="${msg}"/></span>&nbsp;&nbsp;<span class="feedback-link"><html-el:link page="/Feedback.do?exception=${msg}" target="_blank">Contact Us for Assistance</html-el:link></span></div>
</html-el:messages>

<html-el:messages id="msg" message="true">
  <!-- messages message=true -->
  <div class="message-div"><span class="info-message"><c:out value="${msg}"/></span></div>
</html-el:messages>

<html-el:messages id="msg" >
  <!-- messages -->
  <div class="message-div"><span class="error-message"><c:out value="${msg}"/></span></div>
</html-el:messages>
