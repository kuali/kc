<%--
 Copyright 2006-2008 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="awardAttributes" value="${DataDictionary.AwardDocument.attributes}" />
<c:set var="awardApprovedSubawardAttributes" value="${DataDictionary.AwardTemplate.attributes}" />
<c:set var="syncPropertyName" value="awardComments" />
<c:set var="action" value="awardTemplateSync" />

<kul:tab tabTitle="Comment Template" defaultOpen="false" tabErrorKey="document.award.awardTemplate*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Sponsor Template</span>
        </h3>
        <table id="comment-template-table" cellpadding="0" cellspacing="0" summary="Comment Template">
			<tr>
                <th width="50" align="center" scope="row"><div align="center">Select:</div></th>
            	<td class="infoline">
            		<div align="left">
            		<kul:htmlControlAttribute property="document.award.templateCode" attributeEntry="${awardAttributes.templateCode}" />
                    <c:out value="${KualiForm.document.award.awardTemplate.description}"/>
                    <kul:lookup boClassName="org.kuali.kra.award.bo.AwardTemplate" 
                    fieldConversions="templateCode:document.award.templateCode,description:document.award.awardTemplate.description" anchor="${currentTabIndex}"/> 
                    <kul:directInquiry boClassName="org.kuali.kra.award.bo.AwardTemplate" inquiryParameters="document.award.templateCode:templateCode" anchor="${currentTabIndex}"/>
					<span class="fineprint">Note: Award data may have changed since Comment Template was applied </span>
					</div>
            	</td>
            </tr>
            <tr>
            	<th colspan="2" align="center" scope="row">
            		<div align="center">
	         			<html:image property="methodToCall.syncAwardTemplate.syncPropertyName${syncPropertyName}.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-synctotemplate.gif' styleClass="tinybutton"/>
					</div>
	         	</th>
			</tr>
		</table>
	</div>
</kul:tab>
