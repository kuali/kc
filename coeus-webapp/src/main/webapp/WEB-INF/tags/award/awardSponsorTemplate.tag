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
