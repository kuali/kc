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
<#macro uif_question_checkbox control field>

    <#local attributes='class="${control.styleClassesAsString!}" ${control.simpleDataAttributes!} '/>

    <#if control.tabIndex != 0>
        <#local attributes='${attributes} tabindex="${control.tabIndex!}"' />
    </#if>

    <#if control.value??>
        <#local attributes='${attributes} value="${control.value}"'/>
    </#if>

    <#if control.disabled>
        <#local attributes='${attributes} disabled="disabled"'/>
    </#if>

    <#if control.checked>
        <#local attributes='${attributes} checked="checked"'/>
    </#if>

    <#if control.style?has_content>
        <#local attributes='${attributes} style="${control.style}"'/>
    </#if>

    <@spring.formCheckbox id="${control.id}" path="KualiForm.${field.bindingInfo.bindingPath}"
    label=control.richLabelMessage attributes="${attributes}"/>

    <#if control.description?has_content>
        <div style="color: #444444"><span>&nbsp;${control.description}</span></div>
    </#if>

    <@krad.disable control=field.control type="checkbox"/>

</#macro>
