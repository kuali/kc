<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   -
   - Copyright 2005-2015 Kuali, Inc.
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

<c:set var="activityAttributes" value="${DataDictionary.NegotiationActivity.attributes}" />
<c:set var="attachmentAttributes" value="${DataDictionary.NegotiationActivityAttachment.attributes}" />
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}"/>

<script>
function doFilterActivities() {
	var showAll = $jq('input[name="filterActivities"]:checked').val() == '${KualiForm.filterAllActivities}';
	$jq('input[id*="endDate"]').filter('[id*="activities["]').each(function() {
		if ($jq(this).val() != '') {
			if (showAll) {
				$jq(this).parents('div[id^="tab-"]').first().prev().show();
				if ($jq(this).parents('div[id^="tab-"]').first().prev().find('input[id^="tab-"]').is('[src*="hide.gif"]')) {
					$jq(this).parents('div[id^="tab-"]').first().show();
				}

			} else {
				$jq(this).parents('div[id^="tab-"]').first().prev().hide();
				$jq(this).parents('div[id^="tab-"]').first().hide();
			}
		}
	});
}
$jq(document).ready(function() {
	$jq('input[name*="viewAttachment"]').hide();
	$jq('input[name*="viewAttachment"]').click(function() {excludeSubmitRestriction = true});
	$jq('a.attachmentLink').click(function() { $jq(this).siblings('input').click(); });

	$jq('input[name*="methodToCall.sort"]').parent().hide();
	$jq('select[name*="SortingTypeName"]').change(function() { $jq(this).parent().parent().find('input[name*="methodToCall.sort"]').click(); });
	doFilterActivities();
});
</script>

<kul:tab tabTitle="Activities & Attachments" defaultOpen="false" tabErrorKey="" innerTabErrorKey="document.negotiationList[0].activities*,negotiationActivityHelper.*">
<div class="tab-container"  align="center">

<c:if test="${KualiForm.editingMode['create_activity']}">
  <kra-negotiation:negotiationActivity activity="${KualiForm.negotiationActivityHelper.newActivity}" activityIndex="-1" parentTab="Activities & Attachments" tabDivClass="innerTab-h3head" readOnly="false"/>
</c:if>
<jsp:useBean id="paramMap" class="java.util.HashMap"/>

<c:set var="tabItemCount" value="0" />
<c:if test="${!empty KualiForm.negotiationDocument.negotiation.activities}">
    <c:forEach var="activity" items="${KualiForm.negotiationDocument.negotiation.activities}" varStatus="status">
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
    </c:forEach>
</c:if>

<kul:innerTab parentTab="Activities & Attachments" tabTitle="Activities" tabItemCount="${tabItemCount}" defaultOpen="false" useCurrentTabIndexAsKey="true" overrideDivClass="innerTab-h3head">
  <table>
   <tr>
    <th style="text-align: right; width: 5em;">Sort By:</th>
    <td><html:select style="width: 100%;" property="negotiationActivityHelper.activitySortingTypeName">
          <c:forEach items="${krafn:getOptionList('org.kuali.kra.negotiations.sorting.ActivitySortingTypeValuesFinder', paramMap)}" var="option">
            <html:option value="${option.key}"><c:out value="${option.value}"/></html:option>
          </c:forEach>
    	</html:select>
    </td>
    <td style="text-align: center; width: 60px;"><html:image property="methodToCall.sort"
   		  			src="${ConfigProperties.kra.externalizable.images.url}tinybutton-sort.gif" styleClass="tinybutton" />

	</td>
   </tr>
    <th style="text-align: right; width: 5em;">Display:</th>
    <th colspan="2" style="text-align:left;">
      <label><html:radio property="filterActivities" value="${KualiForm.filterAllActivities}" onchange="doFilterActivities();">All</html:radio></label>
      <label><html:radio property="filterActivities" value="${KualiForm.filterPendingActivities}" onchange="doFilterActivities();">Pending</html:radio></label>
      <html:image property="methodToCall.printNegotiationActivity"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif'
						alt="Print Negotiation Activity" styleClass="tinybutton" onclick="excludeSubmitRestriction=true" align="right"/>
   	</th>
   </tr>

  </table>
  <c:forEach items="${KualiForm.document.negotiation.activities}" var="activity" varStatus="ctr">
  	<kra-negotiation:negotiationActivity activity="${activity}" activityIndex="${ctr.count-1}" parentTab="All Activities" readOnly="${readOnly}"/>
  </c:forEach>
</kul:innerTab>


    <kul:innerTab parentTab="Activities & Attachments" tabTitle="Activity/Location History" tabItemCount="${tabItemCount}" defaultOpen="false" useCurrentTabIndexAsKey="true" overrideDivClass="innerTab-h3head">
	<kra-negotiation:negotiationActivityHistory/>
</kul:innerTab>

<c:set var="tabItemCount" value="0" />
<c:if test="${!empty KualiForm.negotiationActivityHelper.allAttachments}">
    <c:forEach var="allAttachment" items="${KualiForm.negotiationActivityHelper.allAttachments}" varStatus="status">
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
    </c:forEach>
</c:if>

<kul:innerTab parentTab="Activities & Attachments" tabTitle="All Attachments" tabItemCount="${tabItemCount}" defaultOpen="false" useCurrentTabIndexAsKey="true" overrideDivClass="innerTab-h3head">
  <table>
   <tr>
    <th style="text-align: right; width: 5em;">Sort By:</th>
    <td><html:select style="width: 100%;" property="negotiationActivityHelper.attachmentSortingTypeName">
          <c:forEach items="${krafn:getOptionList('org.kuali.kra.negotiations.sorting.AttachmentSortingTypeValuesFinder', paramMap)}" var="option">
            <html:option value="${option.key}"><c:out value="${option.value}"/></html:option>
          </c:forEach>
    	</html:select>
    </td>
    <td style="text-align: center; width: 60px;"><html:image property="methodToCall.sort"
   		  			src="${ConfigProperties.kra.externalizable.images.url}tinybutton-sort.gif" styleClass="tinybutton" />

	</td>
   </tr>
  </table>
  <table cellpadding="4" cellspacing="0" summary="">
    <thead>
    <tr>
    <th>&nbsp;</th>
    <th><kul:htmlAttributeLabel attributeEntry="${activityAttributes.startDate}" readOnly="true" noColon="true"/></th>
    <th>File</th>
    <th><kul:htmlAttributeLabel attributeEntry="${attachmentAttributes.description}" readOnly="true" noColon="true"/></th>
    <th><kul:htmlAttributeLabel attributeEntry="${activityAttributes.locationId}" readOnly="true" noColon="true"/></th>
    <th><kul:htmlAttributeLabel attributeEntry="${activityAttributes.activityTypeId}" readOnly="true" noColon="true"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${KualiForm.negotiationActivityHelper.allAttachments}" var="attachment" varStatus="ctr">
      <c:if test="${!attachment.restricted || KualiForm.editingMode['view_unrestricted']}">
      <tr>
        <th style="text-align:right;">${ctr.count}</th>
        <td><kul:htmlControlAttribute property="negotiationActivityHelper.allAttachments[${ctr.count-1}].activity.startDate" attributeEntry="${activityAttributes.startDate}" readOnly="true"/></td>
        <td><a href="#" class="attachmentLink"><kra:fileicon attachment="${attachment.file}"/><c:out value="${attachment.file.name}"/></a>
        	<html:image property="methodToCall.viewAttachmentFromAllAttachments.attachmentIndex${ctr.count-1}"
   		  				src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" styleClass="tinybutton" />
   		</td>
        <td><kul:htmlControlAttribute property="negotiationActivityHelper.allAttachments[${ctr.count-1}].description" attributeEntry="${attachmentAttributes.description}" readOnly="true"/></td>
        <td><kul:htmlControlAttribute property="negotiationActivityHelper.allAttachments[${ctr.count-1}].activity.locationId" attributeEntry="${activityAttributes.locationId}" readOnly="true"/></td>
        <td><kul:htmlControlAttribute property="negotiationActivityHelper.allAttachments[${ctr.count-1}].activity.activityTypeId" attributeEntry="${activityAttributes.activityTypeId}" readOnly="true"/></td>
      </tr>
      </c:if>
    </c:forEach>
    <tr id="negotiation-attachment-download-all">
        <td colspan="6" class="infoline">
            <div align="center">
                <html:image property="methodToCall.downloadAllNegotiationAttachments"
                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-save-all.gif' styleClass="tinybutton"
                            alt="Save All Negotiation Attachments" onclick="excludeSubmitRestriction = true;" />
            </div>
        </td>
    </tr>
    </tbody>
  </table>
</kul:innerTab>

<c:if test="${!empty KualiForm.negotiationDocument.negotiation.negotiationNotifications}">
    <c:forEach var="notifications" items="${KualiForm.negotiationDocument.negotiation.negotiationNotifications}" varStatus="status">
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
    </c:forEach>
</c:if>

<kul:innerTab parentTab="Activities & Attachments" tabTitle="Notifications" tabItemCount="${tabItemCount}" defaultOpen="false" useCurrentTabIndexAsKey="true" overrideDivClass="innerTab-h3head">
	<kra-negotiation:negotiationNotifications/>
</kul:innerTab>

</div>
</kul:tab>
