// WorkgroupReport.jsp
function post_to_action(action, methodToCall) {
  document.forms[0].action = action;
  document.forms[0].methodToCall.value = methodToCall;
  document.forms[0].submit();
  return;
}