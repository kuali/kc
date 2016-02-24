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

<c:set var="AUDIT_ERRORS" value="<%=org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS%>" />
<c:set var="AUDIT_WARNINGS" value="<%=org.kuali.kra.infrastructure.Constants.AUDIT_WARNINGS%>" />

<kra:dataValidation auditActivated="${KualiForm.auditActivated}" topTab="true" categories="${AUDIT_ERRORS},${AUDIT_WARNINGS}" 
			     	helpParameterNamespace="KC-AWARD"  helpParameterDetailType="Document" helpParameterName="awardDataValidationHelpUrl">
                    <p>You can activate a Validation check to determine any errors or incomplete information. The following Validations types will be determined:</p>
                    <ul>
                      <li>errors that prevent submission into routing</li>
                      <li>warnings that serve as alerts to  possible data issues but will not prevent submission into routing</li>
                      <li>errors that prevent submission to grants.gov</li>
                    </ul>
</kra:dataValidation>
