<#macro uif_question_radiobuttons control field>

    <#local attributes='class="${control.styleClassesAsString!}" ${control.simpleDataAttributes!} '/>

    <#if control.tabIndex != 0>
        <#local attributes='${attributes} tabindex="${control.tabIndex!}"' />
    </#if>

    <#if control.disabled>
        <#local attributes='${attributes} disabled="disabled"'/>
    </#if>

    <#if control.style?has_content>
        <#local attributes='${attributes} style="${control.style}"'/>
    </#if>

<fieldset id="${field.id}_fieldset" aria-labelledby="${field.id}_label" class="${control.fieldsetClassesAsString}"
          data-type="RadioSet">
    <legend style="display: none">${field.label!}</legend>
    <@spring.bind path="KualiForm.${field.bindingInfo.bindingPath}"/>
    <#list control.richOptions as option>
        <#assign id="${control.id}_${option_index}">
        <span class="uif-tooltip">
                <input type="radio" id="${id}" name="${spring.status.expression}" value="${option.key?html}"<#if spring.stringStatusValue == option.key> checked="checked"</#if><#if option.disabled> disabled="disabled"</#if> ${attributes}<@spring.closeTag/>
                    <#if option.message.richMessage>
                        <label for="${id}" onclick="handleRadioLabelClick('${id}',event); return false;"><@krad.template component=option.message/></label>
                    <#else>
                        <label for="${id}">${option.value!}</label>
                    </#if>
                    <#if control.optionDescriptions[option_index]?has_content>
                        <div style="color: #444444; padding-bottom: 4px"><span>&nbsp;${control.optionDescriptions[option_index]}</span></div>
                    </#if>
            </span>
        <#if option_has_next>
        ${control.delimiter!}
        </#if>
    </#list>
</fieldset>

    <@krad.disable control=field.control type="radioGroup"/>

</#macro>