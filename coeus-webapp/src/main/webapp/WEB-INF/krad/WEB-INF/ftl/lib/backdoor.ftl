<#--

    Copyright 2005-2015 The Kuali Foundation

    Licensed under the Educational Community License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.opensource.org/licenses/ecl2.php

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

-->
<#macro backdoor>

    <#if UserSession??>
      <div class="backdoor">
	    <#if UserSession.backdoorInUse>        
          Backdoor Id <b>${UserSession.principalName}</b> is in use
      	</#if>
        <#if UserSession.objectMap.AUTH_SERVICE_FILTER_AUTHED_USER??
      	    && UserSession.objectMap.AUTH_SERVICE_FILTER_AUTHED_USER.actualUser??>
      		CoreAuth User <b>${UserSession.objectMap.AUTH_SERVICE_FILTER_AUTHED_USER.actualUser}</b> is proxied
        </#if>
      </div>
    </#if>

</#macro>