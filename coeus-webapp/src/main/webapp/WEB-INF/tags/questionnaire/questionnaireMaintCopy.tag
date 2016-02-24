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

<c:set var="readOnly" value="false"  scope="request"/>
<c:set var="questionnaireAttributes" value="${DataDictionary.Questionnaire.attributes}" />

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Questionnaire </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.coeus.common.questionnaire.framework.core.Questionnaire" altText="help"/> </span>
    </h3>
    
    <table id="from-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
        <td colspan="2" class="tab-subhead" width="50%">
            <div class="tab-subhead-r">
                Original
            </div>
        </td>
        <td colspan="2" class="tab-subhead" width="50%">
            New Copy
        </td>
        </tr>
        <tr>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td class="grid" width="25.0%">
               <html:text property="document.oldMaintainableObject.businessObject.name" 
                           styleId="document.oldMaintainableObject.businessObject.name" readonly="true"/>
            </td>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td class="grid" width="25.0%">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.name" 
                                          attributeEntry="${questionnaireAttributes.name}" />
            </td>
        </tr>
        <tr>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td class="grid" width="25.0%">
               <html:textarea property="document.oldMaintainableObject.businessObject.description" rows="5" cols="40"
                           styleId="document.oldMaintainableObject.businessObject.description" readonly="true"/>
            </td>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td class="grid" width="25.0%">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.description" 
                                          attributeEntry="${questionnaireAttributes.description}" />
            </td>
        </tr>
        <tr>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.active}" />
            </th>
            <td class="grid" width="25.0%">
               <html:checkbox property="document.oldMaintainableObject.businessObject.active" 
                           styleId="document.oldMaintainableObject.businessObject.active" disabled="true"/>
                           <bean:write name="KualiForm" property="document.oldMaintainableObject.businessObject.active"/>
            </td>
            <th class="grid" width="25.0%" align="right">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.active}" />
            </th>
            <td class="grid" width="25.0%">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.active" 
                                          attributeEntry="${questionnaireAttributes.active}" />
            </td>
        </tr>
    </table>
    
    <input type="hidden" id="document.oldMaintainableObject.businessObject.id" name="document.oldMaintainableObject.businessObject.id" value = "${KualiForm.document.oldMaintainableObject.businessObject.id}"/>
</div>
