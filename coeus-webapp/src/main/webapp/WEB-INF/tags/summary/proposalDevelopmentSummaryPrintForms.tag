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
<%@ attribute name="transparentBackground" required="false" %>
<kul:tab tabTitle="Print" defaultOpen="false" tabErrorKey="print*" transparentBackground="${transparentBackground }">

	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Print</span> <span class="subhead-right"><kul:help
					parameterNamespace="KC-PD" parameterDetailType="Document"
					parameterName="proposalDevelopmentPrintFormsHelpUrl" altText="help" />
			</span>
		</h3>
		<kra-pd:proposalDevelopmentActionsPrintForms />
		<kra-pd:printProposalSponsorForms />
	</div>
</kul:tab>
