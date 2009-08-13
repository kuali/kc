<%--
 Copyright 2006-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
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
