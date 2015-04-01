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
