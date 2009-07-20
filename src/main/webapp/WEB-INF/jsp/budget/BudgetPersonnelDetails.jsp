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
<kul:page lookup="true" docTitle="Personnel Details" transactionalDocument="true" htmlFormAction="budgetPersonnel">
<script language="javascript" src="scripts/kuali_application.js"></script>
<kra-b:budgetPersonnelDetails />  
<kul:panelFooter />

<table id="buttons-table" cellpadding=0 cellspacing=0 summary="" width="100%" style="border:none">
	<tr>
		<td>
			<div id="globalbuttons" class="globalbuttons" align="center">
				<kra:section permission="modifyBudgets">
				<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.savePersonnelDescription" title="save" alt="save"/>
				&nbsp;
				</kra:section>
				&nbsp;	
				<input type="image" styleId="closeDetails" name="close"
					src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif"
					class="globalbuttons" title="close" alt="close" onclick="javascript: window.close();return false;">
			</div>
		</td>
	</tr>
</table>

</kul:page>
