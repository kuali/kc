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

<%@ attribute name="protocolPrinciples" required="true" type="java.util.Map" %>

<c:set var="modifyPermissions" value="${KualiForm.iacucAlternateSearchHelper.modifyPermissions}" />
<c:set var="readOnly" value="${!modifyPermissions}" />

<kul:tabTop tabTitle="The Three R's" defaultOpen="true" tabErrorKey="">
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Principles</span>
            <span class="subhead-right">
                <kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucProtocolThreeRsPrinciplesHelp" altText="help"/>                
            </span>
        </h3>
        <table cellpadding="4" cellspacing="0" summary="">
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolPrinciples.reduction}" /></div></th>
                <td width="50%" align="left" valign="center">
                    <kul:htmlControlAttribute property="document.protocolList[0].iacucPrinciples[0].reduction" readOnly="${readOnly}" attributeEntry="${protocolPrinciples.reduction}" />
                </td>        
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolPrinciples.refinement}" /></div></th>
                <td width="50%" align="left" valign="center">
                    <kul:htmlControlAttribute property="document.protocolList[0].iacucPrinciples[0].refinement" readOnly="${readOnly}" attributeEntry="${protocolPrinciples.refinement}" />
                </td>        
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolPrinciples.replacement}" /></div></th>
                <td width="50%" align="left" valign="center">
                    <kul:htmlControlAttribute property="document.protocolList[0].iacucPrinciples[0].replacement" readOnly="${readOnly}" attributeEntry="${protocolPrinciples.replacement}" />
                </td>        
            </tr>                        
        </table>
    </div> 
</kul:tabTop>        
