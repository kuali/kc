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

<#-- Renders a dialog group using bootstrap modal -->

<#macro uif_dialogGroup group>

    <@krad.wrap component=group renderAs="${group.wrapperTag}">
        <div class="modal-dialog">
        	<#-- default to standard size instead of small -->
            <#local size=""/>
            <#if group.modalLarge>
                <#local size="modal-lg"/>
            </#if>

            <div class="modal-content ${size}">

                <@krad.template component=group.header/>

                <@krad.template component=group.instructionalMessage/>

                <#if group.items?has_content>
                    <div class="modal-body">
                        <#-- invoke layout manager -->
                        <#local templateName=".main.${group.layoutManager.templateName}"/>
                        <#local templateParms="items=group.items manager=group.layoutManager container=group"/>

                        <#dyncall templateName templateParms/>
                    </div>
                </#if>

                <@krad.template component=group.footer/>

            </div>
        </div>
    </@krad.wrap>

</#macro>
