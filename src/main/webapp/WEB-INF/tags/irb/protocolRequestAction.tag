<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<kul:tabTop tabTitle="Request an Action (Under Construction)" defaultOpen="${KualiForm.auditActivated}"  
            tabErrorKey="">
	<div class="tab-container"  align="center">
		<h3> 
			<span class="subhead-left">Available Actions</span>
		</h3>
		<kra-irb-action:submitAction />
		<kra-irb-action:withdrawAction />
		<kra-irb-action:closeRequestAction />
		<kra-irb-action:suspendRequestAction />
		<kra-irb-action:closeEnrollmentRequestAction />
		<kra-irb-action:reopenEnrollmentRequestAction />
	</div>
</kul:tabTop>