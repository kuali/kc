// DocumentSearch.jsp
function post_to_action(action, methodToCall) {
  document.forms[0].action = action;
  document.forms[0].methodToCall.value = methodToCall;
  document.forms[0].submit();
  return;
}

function lookup(quickFinderLookupable, action) {
  document.forms[0].lookupableImplServiceName.value = quickFinderLookupable;
  return post_to_action(action, 'performLookup');
}