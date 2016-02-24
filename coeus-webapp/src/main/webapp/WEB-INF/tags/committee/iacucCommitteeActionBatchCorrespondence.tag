<%--
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
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<div id="workarea">
    <kul:tab tabTitle="Batch Correspondence"
             tabErrorKey="committeeHelper.generateBatchCorrespondenceTypeCode,committeeHelper.generateStartDate,committeeHelper.generateEndDate,committeeHelper.history*"
             auditCluster="requiredFieldsAuditErrors"  
             defaultOpen="false"
             useCurrentTabIndexAsKey="false"  
             transparentBackground="true">
        <div class="tab-container" align="center">
            <kra-committee:iacucCommitteeActionBatchCorrespondenceGenerate />
            <p>&nbsp;</p>
            <kra-committee:iacucCommitteeActionBatchCorrespondenceHistory />
            <p>&nbsp;</p>
        </div> 
    </kul:tab>
</div>
