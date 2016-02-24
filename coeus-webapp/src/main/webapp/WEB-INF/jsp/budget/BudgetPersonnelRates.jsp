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
<kul:page lookup="true" docTitle="Rates" transactionalDocument="true" htmlFormAction="${KualiForm.actionPrefix}Personnel">
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
