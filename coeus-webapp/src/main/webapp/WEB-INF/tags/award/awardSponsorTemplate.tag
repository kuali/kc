<%--
 Copyright 2005-2013 The Kuali Foundation
 
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
<c:set var="awardTemplateAttributes" value="${DataDictionary.AwardTemplate.attributes}" />
<c:set var="action" value="awardTemplateSync" />

<script>
function loadSponsorDesc(field) {
	callAjax('getSponsorTemplateDescription',
			$j(field).attr('value'),
			function(xml) {
		      $j('#awardTemplateDescription').html($j(xml).find('#ret_value').html());
			},
			function() {
				alert('Error loading XML document');
			});			
}
</script>
<kul:tab tabTitle="Sponsor Template" defaultOpen="false" tabErrorKey="document.award.templateCode,document.award.awardTemplate*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Sponsor Template</span>
    		<span class="subhead-right">
    			<kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardSponsorTemplateHelp" altText="help"/>
			</span>
        </h3>
        <table id="sponsor-template-table" cellpadding="0" cellspacing="0" summary="Sponsor Template">
			<tr>
                <th width="30%" style="text-align: right;" scope="row"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.templateCode}" /></div></th>
            	<td width="10%" style="text-align: left;">
            		<kul:htmlControlAttribute property="document.award.templateCode" attributeEntry="${awardAttributes.templateCode}" 
            			onblur="loadSponsorDesc(this)"/>
                    <c:if test="${!readOnly}">
                        <kul:lookup boClassName="org.kuali.kra.award.home.AwardTemplate" 
                        fieldConversions="templateCode:document.award.templateCode,description:document.award.awardTemplate.description" anchor="${currentTabIndex}"/>
                    </c:if>             			
            	</td>
            	<th width="25%" style="text-align: right;" scope="row"><kul:htmlAttributeLabel attributeEntry="${awardTemplateAttributes.description}"/></th>
            	<td width="25%" style="text-align: left;">
                    <div id="awardTemplateDescription" style="display:inline;"><c:out value="${KualiForm.document.award.awardTemplate.description}"/></div>
                </td>
            	<td width="10%" class="infoline" style="text-align:center">
            	  <c:if test="${!readOnly}">
            	   <html:image property="methodToCall.applySponsorTemplate"
						src="${ConfigProperties.kra.externalizable.images.url}tinybutton-apply.gif" styleClass="tinybutton"/>
            	  </c:if>
            	</td>
            </tr>
            <tr>
              <td colspan="5" class="infoline" style="text-align:center;">
				<span class="fineprint">Note: Award data may have changed since Sponsor Template was applied </span>              
              </td>
            </tr>
		</table>
	</div>
</kul:tab>
