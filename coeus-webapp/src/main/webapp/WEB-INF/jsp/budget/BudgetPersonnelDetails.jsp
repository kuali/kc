<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<kul:page lookup="true" docTitle="Personnel Details" transactionalDocument="true" htmlFormAction="${KualiForm.actionPrefix}Personnel">
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
