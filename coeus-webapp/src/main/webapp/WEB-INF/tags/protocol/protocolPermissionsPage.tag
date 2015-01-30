<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<%@ attribute name="documentTypeName" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="modifyPermissions" required="true" %>
<%@ attribute name="permissionsUserAttributes" required="true" type="java.util.Map" %>

<div align="right"><kul:help documentTypeName="${documentTypeName}" pageName="Permissions" /></div>

<kra-protocol:protocolAssignedRoles name="${name}" /> 
<kra-protocol:protocolPermissionsUsers name="${name}" 
                                       modifyPermissions="${modifyPermissions}" 
                                       permissionsUserAttributes="${permissionsUserAttributes}"/>
<kul:panelFooter />	
	
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" />
<script language="javascript" src="dwr/interface/KraPersonService.js"></script>
<script>loadPersonName('permissionsHelper.newUser.userName', 'fullname');</script>
