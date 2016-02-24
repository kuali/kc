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
