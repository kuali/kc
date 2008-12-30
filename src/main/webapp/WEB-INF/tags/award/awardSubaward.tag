<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<c:set var="awardAttributes" value="${DataDictionary.AwardDocument.attributes}" />
<c:set var="awardApprovedSubawardAttributes" value="${DataDictionary.AwardApprovedSubaward.attributes}" />
<c:set var="action" value="awardTimeAndMoney" />


<kul:tab tabTitle="Subaward" defaultOpen="false" tabErrorKey="document.award*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Approved Subawards</span>
        </h3>
        <table id="approved-subaward-table" cellpadding="0" cellspacing="0" summary="Approved Subaward">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardApprovedSubawardAttributes.organizationName}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${awardApprovedSubawardAttributes.amount}" useShortLabel="true" noColon="true" /></th>
				<th><div align="center">Actions</div></th>
			</tr>
			
			<tr>
            	<th width="50" align="center" scope="row"><div align="center">Add:</div></th>
            	<td class="infoline">
            	  	<div align="center">
            	  	 	<kul:htmlControlAttribute property="newAwardApprovedSubaward.organizationName" attributeEntry="${awardApprovedSubawardAttributes.organizationName}"/>
            	 	</div>
            	</td>
	            <td class="infoline">
	              	<div align="center">
	            		<kul:htmlControlAttribute property="newAwardApprovedSubaward.amount" attributeEntry="${awardApprovedSubawardAttributes.amount}" />
	              	</div>
	            </td>
	            <td class="infoline">
	            	<div align=center>
						<html:image property="methodToCall.addApprovedSubaward.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	            </td>
          	</tr>
          	<c:forEach var="awardApprovedSubawards" items="${KualiForm.document.award.awardApprovedSubawards}" varStatus="status">
	             <tr>
					<th width="5%" class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td width="10%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardApprovedSubawards[${status.index}].organizationName" attributeEntry="${awardApprovedSubawardAttributes.organizationName}"/>
					</div>
					</td>
	                <td width="20%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardApprovedSubawards[${status.index}].amount" attributeEntry="${awardApprovedSubawardAttributes.amount}"/>
					</div>
	                </td>
					<td width="10%">
					<div align="center">&nbsp;
						<html:image property="methodToCall.deleteApprovedSubaward.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
	                </td>
	            </tr>
        	</c:forEach> 
        </table>
     </div>
</kul:tab>
