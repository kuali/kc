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

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />

<h3>
	<span class="subhead-left">Miscellaneous Procurement/Purchasing</span>
	<span class="subhead-right">
		<kul:help businessObjectClassName="org.kuali.kra.award.home.Award" altText="help"/>
	</span>
</h3>
	<table border="0" cellpadding="0" cellspacing="0" summary="">
        <tr>
        	<th width="6%"><div align="center">
            	<kul:htmlAttributeLabel attributeEntry="${awardAttributes.subPlanFlag}" noColon="true" /></div></th>
            </div></th>          		
          	<td width="5%">
          	<div align="center">
          		<kul:htmlControlAttribute property="document.awardList[0].subPlanFlag" attributeEntry="${awardAttributes.subPlanFlag}" />
          	</div>
          	</td>
            <th width="6%"><div align="center">
            	<kul:htmlAttributeLabel attributeEntry="${awardAttributes.procurementPriorityCode}" noColon="true" /></div></th>
            </div></th>          		
          	<td width="5%">
          	<div align="center">
          		<kul:htmlControlAttribute property="document.awardList[0].procurementPriorityCode" attributeEntry="${awardAttributes.procurementPriorityCode}" />
          	</div>
          	</td>
        </tr>
    </table>      	
