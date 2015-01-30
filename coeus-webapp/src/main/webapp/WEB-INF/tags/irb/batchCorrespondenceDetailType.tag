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
<tr><td colspan="3">
<div align="center" style="padding: 20px">
<table cellspacing="0" cellpadding="0" border="0" style="width: auto; border-top: 1px solid rgb(153, 153, 153);">
    <tr>
        <td>
                <div align="left" style="font-weight:bold;">
                   Correspondence Type:
                </div>
                <kul:htmlControlAttribute property="batchCorrespondence.batchCorrespondenceTypeCode"
                                          attributeEntry="${DataDictionary.BatchCorrespondenceDetail.attributes.batchCorrespondenceTypeCode}" 
                                          readOnly="false" />          
        </td>
    </tr>
    <tr>
        <td colspan="2" class="infoline nobord" style="padding:4px">
            <div align="center">
            <html:image property="methodToCall.start" 
                 src="${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif" 
                 title="refresh" 
                 alt="refresh" 
                 styleClass="tinybutton"/>
            </div>
        </td>
    </tr>
</table>
</div>
</td></tr>
