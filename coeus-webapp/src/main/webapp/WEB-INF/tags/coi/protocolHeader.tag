<%--
 Copyright 2005-2013 The Kuali Foundation

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
<%@ attribute name="disclProject" required="true" type="org.kuali.kra.coi.CoiDisclProject" %>

<table class=tab cellpadding="0" cellspacing="0" summary="">
	<tbody>
		<tr>
			<th><div align="right">IRB Protocol Name:</div></th> 
			<td align="left" valign="middle" colspan="3"><div align="left">
				${disclProject.protocol.title}
			</div></td>
			<th><div align="right">IRB Protocol Type:</div></th> 
			<td align="left" valign="middle"><div align="left">
				${disclProject.protocol.protocolType.description}
			</div></td>
		</tr>
		<tr>
			<th><div align="right">Application Date:</div></th> 
			<td align="left" valign="middle"><div align="left">
				${disclProject.protocol.applicationDate}
			</div></td>
			<th><div align="right">Expiration Date:</div></th> 
			<td align="left" valign="middle"><div align="left">
				${disclProject.protocol.expirationDate}
			</div></td>
			<th><div align="right">PI Name:</div></th> 
			<td align="left" valign="middle"><div align="left">
				${disclProject.protocol.principalInvestigatorName} 
			</div></td>
		</tr>
	</tbody>
</table>