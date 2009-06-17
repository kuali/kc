<%--
 Copyright 2006-2009 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kra:infopage title="Checklist Item" action="protocolActions" htmlFormAction="protocolProtocolActions">

    <div align="center">
        <textarea title="Description" class="" style="" id="description" rows="15" cols="60" tabindex="0" name="description" readonly="readonly">${KualiForm.actionHelper.protocolSubmitAction.checkListItemDescription}</textarea> 
    </div>
    
</kra:infopage>
