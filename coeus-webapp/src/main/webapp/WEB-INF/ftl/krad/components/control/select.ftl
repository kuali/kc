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

<#--
Standard HTML Select Input

-->
<#macro uif_select control field>

    <#local attributes='size="${control.size!}"
        class="${control.styleClassesAsString!}" ${control.simpleDataAttributes!} '/>

    <#if control.tabIndex != 0>
        <#local attributes='${attributes} tabindex="${control.tabIndex!}"' />
    </#if>

    <#if control.disabled>
        <#local attributes='${attributes} disabled="disabled"'/>
    </#if>

    <#if control.style?has_content>
        <#local attributes='${attributes} style="${control.style}"'/>
    </#if>

    <#local bindingPath="KualiForm.extensionData['NO_PATH_${field.id}']"/>
    <#if field.propertyName?has_content>
        <#local bindingPath="KualiForm.${field.bindingInfo.bindingPath}"/>
    </#if>

    <#local attributes='${attributes} data-template="${control.templateOptionsJSString?html}"'/>
    
    <#if control.multiple>
        <@spring.formMultiSelect id="${control.id}" path="${bindingPath}" options=control.options
                                  attributes="${attributes}"/>
    <#else>
        <@spring.formSingleSelect id="${control.id}" path="${bindingPath}" options=control.options
                                  attributes="${attributes}"/>
    </#if>

    <#if control.locationSelect>
        <@krad.script value="setupLocationSelect('${control.id}');" />
    </#if>

    <@krad.disable control=field.control type="select"/>

</#macro>
