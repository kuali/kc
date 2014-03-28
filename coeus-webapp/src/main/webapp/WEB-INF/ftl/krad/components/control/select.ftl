<#--

    Copyright 2005-2014 The Kuali Foundation

    Licensed under the Educational Community License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.opensource.org/licenses/ecl2.php

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

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
