// Lookup.jsp

function clearValues(methodToCall, formname, action) {
  document.forms[formname].methodToCall.value = methodToCall;
  return post_to_action(formname, action);
}

function quick_finder(quickFinderLookupable, formname, action) {
  document.forms[formname].methodToCall.value = 'performLookup';
  document.forms[formname].quickFinderLookupable.value = quickFinderLookupable;
  return post_to_action(formname, action);
}