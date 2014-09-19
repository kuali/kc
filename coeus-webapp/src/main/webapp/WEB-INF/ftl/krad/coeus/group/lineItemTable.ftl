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