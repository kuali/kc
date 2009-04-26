// RouteManagerDriver.jsp

function post_to_action(action, methodToCall) {
  document.forms[0].action = action;
  document.forms[0].methodToCall.value = methodToCall;
  document.forms[0].submit();
  return;
}
function clearValues(methodToCall, formname, action) {
  document.forms[formname].methodToCall.value = methodToCall;
  return post_to_action(formname, action);
}

function quick_finder(quickFinderLookupable, formname, action) {
  document.forms[formname].methodToCall.value = 'storeForm';
  document.forms[formname].quickFinderLookupable.value = quickFinderLookupable;
  return post_to_action(formname, action);
}