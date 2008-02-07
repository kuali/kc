<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="editRolesAttributes" value="${DataDictionary.ProposalUserEditRoles.attributes}" />

<kul:tabTop defaultOpen="true" tabTitle="Roles" tabErrorKey="proposalUserEditRole*">
	<div class="tab-container" align="center">
		<div class="h2-container">
    		<span class="subhead-left"><h2>Roles</h2></span>
        </div>	
        <table id="edit-roles-table" cellpadding="0" cellspacing="0" summary="">
          	<tr>
            	<th width="35%"><div align="right">Aggregator</div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="proposalUserEditRoles.aggregator" attributeEntry="${editRolesAttributes.aggregator}" />
				</td>
        	</tr>
        	<tr>
            	<th><div align="right">Budget Creator</div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="proposalUserEditRoles.budgetCreator" attributeEntry="${editRolesAttributes.budgetCreator}" />
				</td>
        	</tr>
        	<tr>
            	<th><div align="right">Narrative Writer</div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="proposalUserEditRoles.narrativeWriter" attributeEntry="${editRolesAttributes.narrativeWriter}" />
				</td>
        	</tr>
        	<tr>
            	<th><div align="right">Viewer</div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="proposalUserEditRoles.viewer" attributeEntry="${editRolesAttributes.viewer}" />
				</td>
        	</tr>
        </table>
    </div>
</kul:tabTop>