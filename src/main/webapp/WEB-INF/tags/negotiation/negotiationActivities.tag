<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="activityAttributes" value="${DataDictionary.NegotiationActivity.attributes}" />
<c:set var="attachmentAttributes" value="${DataDictionary.NegotiationActivityAttachment.attributes}" />
<c:set var="readOnly" value="${not KualiForm.editingMode['modify_activity']}"/>

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
<kul:innerTab parentTab="Activities & Attachments" tabTitle="Activities" defaultOpen="false" useCurrentTabIndexAsKey="true" overrideDivClass="innerTab-h3head">
  <table>
   <tr>
    <th style="text-align: right; width: 5em;">Sort By:</th>
    <td><html:select style="width: 100%;" property="negotiationActivityHelper.activitySortingTypeName">
          <c:forEach items="${krafn:getOptionList('org.kuali.kra.negotiations.sorting.ActivitySortingTypeValuesFinder', paramMap)}" var="option">
            <html:option value="${option.key}"><c:out value="${option.label}"/></html:option>
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


<kul:innerTab parentTab="Activities & Attachments" tabTitle="Activity/Location History" defaultOpen="false" useCurrentTabIndexAsKey="true" overrideDivClass="innerTab-h3head">
	<kra-negotiation:negotiationActivityHistory/>
</kul:innerTab>


<kul:innerTab parentTab="Activities & Attachments" tabTitle="All Attachments" defaultOpen="false" useCurrentTabIndexAsKey="true" overrideDivClass="innerTab-h3head">
  <table>
   <tr>
    <th style="text-align: right; width: 5em;">Sort By:</th>
    <td><html:select style="width: 100%;" property="negotiationActivityHelper.attachmentSortingTypeName">
          <c:forEach items="${krafn:getOptionList('org.kuali.kra.negotiations.sorting.AttachmentSortingTypeValuesFinder', paramMap)}" var="option">
            <html:option value="${option.key}"><c:out value="${option.label}"/></html:option>
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
    </tbody>
  </table>
</kul:innerTab>
</div>
</kul:tab> 