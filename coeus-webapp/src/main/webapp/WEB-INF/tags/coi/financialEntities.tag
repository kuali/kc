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

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="personFinIntDisclAttribute" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="entityContactInfoAttribute" value="${DataDictionary.FinancialEntityContactInfo.attributes}" />


<div align="right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="financialEntityMyHelp" altText="help"/></div>

<kul:tab defaultOpen="true" tabTitle="Financial Entities"  transparentBackground="true">
    <div class="tab-container" align="center">

        <kra-coi:financialEntityList  activeFlag="active" />

  <%-- Inactive --%>
  
        <kra-coi:financialEntityList  activeFlag="inactive" />
    
            <input type="hidden" name="editIndex" id="editIndex" value="${KualiForm.financialEntityHelper.editEntityIndex}" />
            <input type="hidden" name="editType" id="editType" value="${KualiForm.financialEntityHelper.editType}" />
        
    </div>

</kul:tab>
