/*
 * Copyright 2008-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// RouteQueueEntry.jsp, etc

function submitQueueId(methodToCall, queueId)
{
    document.forms[0].elements['methodToCall'].value = methodToCall;
    document.forms[0].elements['messageId'].value = queueId;
    document.forms[0].submit();
}

function executeMessageFetcher()
{
	if (confirm('This will execute the Message Fetcher which will grab all messages in the queue with this machine\'s service namespace and ip address and queue them up to be processed again.  Are you sure you want to do this?')) {
	    document.forms[0].elements['methodToCall'].value = 'executeMessageFetcher';
   		document.forms[0].submit();
   	}
}

function refreshServiceRegistry()
{
	document.forms[0].elements['methodToCall'].value = 'refreshServiceRegistry';
   	document.forms[0].submit();
}
