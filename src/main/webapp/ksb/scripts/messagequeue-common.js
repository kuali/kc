// RouteQueueEntry.jsp, etc

function submitQueueId(methodToCall, queueId)
{
    document.forms[0].elements['methodToCall'].value = methodToCall;
    document.forms[0].elements['messageId'].value = queueId;
    document.forms[0].submit();
}

function executeMessageFetcher()
{
	if (confirm('This will execute the Message Fetcher which will grab all messages in the queue with this machine\'s message entity and ip address and queue them up to be processed again.  Are you sure you want to do this?')) {
	    document.forms[0].elements['methodToCall'].value = 'executeMessageFetcher';
   		document.forms[0].submit();
   	}
}

function refreshServiceRegistry()
{
	document.forms[0].elements['methodToCall'].value = 'refreshServiceRegistry';
   	document.forms[0].submit();
}