// RouteQueueEntry.jsp, etc

function submitQueueId(methodToCall, queueId)
{
    document.forms[0].elements['methodToCall'].value = methodToCall;
    document.forms[0].elements['routeQueueId'].value = queueId;
    document.forms[0].submit();
}
