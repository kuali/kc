<#--
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
-->
<#macro uif_lineItemTable group>
    <@krad.groupWrap group=group>
        <table id="${group.id}_table" class="table table-condensed table-bordered">
            <thead>
                <tr class="${group.headerRow.cssClass!}">
                    <#list group.headerRow.cellContent as cell>
                        <#if !cell_has_next && group.renderRowTotalColumn>
                            <th class="text-center uif-lineItem-rowTotal">${cell!}</th>
                        <#elseif (cell_index > group.numPeriodColumns)>
                            <th class="text-center collapse">${cell!}</th>
                        <#else>
                            <th class="text-center">${cell!}</th>
                        </#if>
                    </#list>
                </tr>
            </thead>
            <tbody>
                <#list group.flattenedRows as row>
                    <tr id="${row.id!}" class="${row.cssClass!}" data-parent_row="${row.parentRow!}">
                        <#list row.cellContent as cell>
                            <#if cell_index == 0>
                                <#if row.groupRow>
                                    <th colspan="0">${cell!}</th>
                                <#else>
                                    <th scope="row">${cell!}</th>
                                </#if>
                            <#elseif !cell_has_next && group.renderRowTotalColumn>
                                <td class="text-right uif-lineItem-rowTotal">${cell!}</td>
                            <#elseif (cell_index > group.numPeriodColumns)>
                                <td class="text-right collapse">${cell!}</td>
                            <#else>
                                <td class="text-right">${cell!}</td>
                            </#if>
                        </#list>
                    </tr>
                </#list>
            </tbody>
        </table>
    </@krad.groupWrap>
</#macro>