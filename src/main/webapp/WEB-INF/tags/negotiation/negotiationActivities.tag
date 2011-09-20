<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="activityAttributes" value="${DataDictionary.NegotiationActivity.attributes}" />
<c:set var="attachmentAttributes" value="${DataDictionary.NegotiationActivityAttachment.attributes}" />
<c:set var="readOnly" value="${false}" scope="request" />

<script>
$jq(document).ready(function() {
	$jq('input[name*="viewAttachment"]').hide();
	$jq('input[name*="viewAttachment"]').click(function() {excludeSubmitRestriction = true});
	$jq('a.attachmentLink').click(function() { $jq(this).siblings('input').click(); });
});
</script>

<kul:tab tabTitle="Activities & Attachments" defaultOpen="false" tabErrorKey="" innerTabErrorKey="document.negotiationList[0].activities*,negotiationActivityHelper.*">
<div class="tab-container"  align="center">
<kra-negotiation:negotiationActivity activity="${KualiForm.negotiationActivityHelper.newActivity}" activityIndex="-1" parentTab="Activities & Attachments" tabDivClass="innerTab-h3head"/>

<kul:innerTab parentTab="Activities & Attachments" tabTitle="Activities" defaultOpen="false" useCurrentTabIndexAsKey="true" overrideDivClass="innerTab-h3head">
  <c:forEach items="${KualiForm.document.negotiation.activities}" var="activity" varStatus="ctr">
  	<kra-negotiation:negotiationActivity activity="${activity}" activityIndex="${ctr.count-1}" parentTab="All Activities"/>
  </c:forEach>
</kul:innerTab>

<kul:innerTab parentTab="Activities & Attachments" tabTitle="All Attachments" defaultOpen="false" useCurrentTabIndexAsKey="true" overrideDivClass="innerTab-h3head">
  <table cellpadding="4" cellspacing="0" summary="">
    <thead>
    <th>&nbsp;</th>
    <th><kul:htmlAttributeLabel attributeEntry="${activityAttributes.startDate}" readOnly="true" noColon="true"/></th>
    <th>File</th>
    <th><kul:htmlAttributeLabel attributeEntry="${attachmentAttributes.description}" readOnly="true" noColon="true"/></th>
    <th><kul:htmlAttributeLabel attributeEntry="${activityAttributes.locationId}" readOnly="true" noColon="true"/></th>
    <th><kul:htmlAttributeLabel attributeEntry="${activityAttributes.activityTypeId}" readOnly="true" noColon="true"/></th>
    </thead>
    <tbody>
    <c:forEach items="${KualiForm.document.negotiation.allAttachments}" var="attachment" varStatus="ctr">
      <tr>
        <th style="text-align:right;">${ctr.count}</th>
        <td><kul:htmlControlAttribute property="document.negotiation.allAttachments[${ctr.count-1}].activity.startDate" attributeEntry="${activityAttributes.startDate}" readOnly="true"/></td>
        <td><a href="#" class="attachmentLink"><kra:fileicon attachment="${attachment.file}"/><c:out value="${attachment.file.name}"/></a>
        	<html:image property="methodToCall.viewAttachmentFromAllAttachments.attachmentIndex${ctr.count-1}"
   		  				src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" styleClass="tinybutton" />
   		</td>
        <td><kul:htmlControlAttribute property="document.negotiation.allAttachments[${ctr.count-1}].description" attributeEntry="${attachmentAttributes.description}" readOnly="true"/></td>
        <td><kul:htmlControlAttribute property="document.negotiation.allAttachments[${ctr.count-1}].activity.locationId" attributeEntry="${activityAttributes.locationId}" readOnly="true"/></td>
        <td><kul:htmlControlAttribute property="document.negotiation.allAttachments[${ctr.count-1}].activity.activityTypeId" attributeEntry="${activityAttributes.activityTypeId}" readOnly="true"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</kul:innerTab>
</div>
</kul:tab> 