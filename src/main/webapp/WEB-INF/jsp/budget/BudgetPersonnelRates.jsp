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
<kul:page lookup="true" docTitle="Rates" transactionalDocument="true" htmlFormAction="budgetPersonnel">
<script language="javascript" src="scripts/kuali_application.js"></script>
<kra-b:budgetPersonnelRates />  
<kul:panelFooter />

<kra:section permission="modifyBudgets">
<table id="buttons-table" width="100%" cellpadding=0 cellspacing=0 summary="" style="border:none">
<tr>
	<td>
	<div id="globalbuttons" class="globalbuttons" align="center">
	<input type="image" styleId="closeRates" name="close"
		src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif"
		class="globalbuttons" title="close" alt="close" onclick="javascript: window.close();return false;"></div>
	</td>
</tr>
</table>
</kra:section>

</kul:page>
