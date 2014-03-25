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
    Bootstrap Navigation Bar

 -->

<#macro uif_navigationBar element>

    <div class="${view.contentContainerClassesAsString}">

        <nav id="${element.id!}" ${krad.attrBuild(element)} ${element.simpleDataAttributes} role="navigation">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-reorder"></span>
                </button>

				<div class="logoBrand">
	            	<h1>
						<#if element.brandImageLink?? && element.brandImageLink.render>
							<@krad.template component=element.brandImageLink/>
						<#else>
			                <a class="navbar-brand" href="#">
			                    <#if element.brandImage?? && element.brandImage.render>
	                                <@krad.template component=element.brandImage/>
	                            <#else>
	                                ${element.brandText!}
	                            </#if>
			                </a>
	    		        </#if>
					</h1>
				</div>        
            </div>

            <@krad.template component=element.navigationBarGroup/>

        </nav>

    </div>

</#macro>
