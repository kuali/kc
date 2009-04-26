// SUActionRequests.jsp
function processActionRequest(formname, recipientTypeCode, networkId, workGroupId, actionRequestId, buttonClickValue)
{
  setMethod("actionRequestApprove");
  document.forms[formname].actionTakenNetworkId.value = networkId;
  document.forms[formname].actionTakenWorkGroupId.value = workGroupId;
  document.forms[formname].actionTakenActionRequestId.value = actionRequestId;
  document.forms[formname].actionTakenRecipientCode.value = recipientTypeCode;
  document.forms[formname].buttonClick.value = buttonClickValue;
  document.forms[formname].submit();
}