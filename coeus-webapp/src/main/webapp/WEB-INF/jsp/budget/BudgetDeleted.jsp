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
<c:set var="readOnly" value="true" scope="request" />
<kul:page showDocumentInfo="false"
	      headerTitle="Budget Deleted"
	      docTitle="Budget Deleted"
	      transactionalDocument="false"
	      htmlFormAction="${KualiForm.actionPrefix}Parameters"
	      defaultMethodToCall="notify"
	      errorKey="*">

    <div class="topblurb">
		<div align="center">
			<table cellpadding="10" cellspacing="0" border="0" class="container2">
				<tr>
					<td>
						<div align="left" valign="top">
							<strong>Error Message</strong>
						</div>
					</td>
					<td align="left">
						<div align="left">
							<font color="red">The Budget has been deleted.</font>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td align="left">
						<div>
							<html:image property="methodToCall.close" value="true" 
								   src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif"
								   styleClass="tinybutton" title="close" alt="Close"/>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
  	
  	
  
  
</kul:page>
