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
<%@ attribute name="businessObjectClassName" required="true" 
              description="The specific per-module business class to use for the help pages" %>
<%@ attribute name="attributes" required="true" type="java.util.Map"
              description="The Data Dictionary reference to the Special Review attributes" %>
<%@ attribute name="exemptionAttributes" required="true" type="java.util.Map"
              description="The Data Dictionary reference to the Special Review Exemption attributes" %>
<%@ attribute name="collectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all the current Special Reviews" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Special Reviews" %>
<%@ attribute name="action" required="true" 
              description="The name of the action class" %>

<c:set var="canModify" value="${KualiForm.specialReviewHelper.canModifySpecialReview}"/>
<c:set var="enableIrbProtocolLinking" value="${KualiForm.specialReviewHelper.isIrbProtocolLinkingEnabled}" />
<c:set var="enableIacucProtocolLinking" value="${KualiForm.specialReviewHelper.isIacucProtocolLinkingEnabled}" />
<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.SPECIAL_REVIEW_COMMENT_LENGTH%>" />
<c:set var="canCreateIrbProtocol" value="false" />
<c:set var="canCreateIacucProtocol" value="false" />
<c:if test="${enableIrbProtocolLinking}">
	<c:set var="canCreateIrbProtocol" value="${KualiForm.specialReviewHelper.canCreateIrbProtocol}" />
</c:if>
<c:if test="${enableIacucProtocolLinking}">
	<c:set var="canCreateIacucProtocol" value="${KualiForm.specialReviewHelper.canCreateIacucProtocol}" />
</c:if>

<c:set var="buttonStyle" value="display:none"/>
<c:if test="${canCreateIrbProtocol && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '1'}">
	<c:set var="buttonStyle" value="display:inline"/>
</c:if>
<c:if test="${canCreateIacucProtocol && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '2'}">
	<c:set var="buttonStyle" value="display:inline"/>
</c:if>

<script>
var SpecialReviewGlobals = {};
SpecialReviewGlobals.approvalTypeSelector = 'select[name*="approvalTypeCode"]';
SpecialReviewGlobals.specialReviewTypeSelector = 'select[name*="specialReviewTypeCode"]';
SpecialReviewGlobals.disabledApprovalTypeSelector = 'select[name*="disabledApprovalTypeCode"]';
SpecialReviewGlobals.notYetAppliedApprovalTypeCode = '3';
SpecialReviewGlobals.humanSubjectsTypeCode = '1';
SpecialReviewGlobals.animalUsageTypeCode = '2';
SpecialReviewGlobals.origSearchIcon;
SpecialReviewGlobals.disabledSearchIcon = "${ConfigProperties.kra.externalizable.images.url}/searchicon1.gif";
SpecialReviewGlobals.enableIrbProtocolLinking = ${enableIrbProtocolLinking};
SpecialReviewGlobals.enableIacucProtocolLinking = ${enableIacucProtocolLinking};
SpecialReviewGlobals.processNotYetAppliedChange = function(control) {
	var row = this.getRow(control);
	var reviewTypes = jQuery(row).find(this.specialReviewTypeSelector);
	var typeCode = reviewTypes.val();
	var approvalTypes = jQuery(row).find(this.approvalTypeSelector);
	var protocolNumber = jQuery(row).find('input[type="text"][name*="protocolNumber"]');
	if ((typeCode == this.humanSubjectsTypeCode && this.enableIrbProtocolLinking)
			|| (typeCode == this.animalUsageTypeCode && this.enableIacucProtocolLinking)) {
		if (approvalTypes.val() == this.notYetAppliedApprovalTypeCode) {
			jQuery(protocolNumber).prop('readonly', true);
			jQuery(protocolNumber).siblings().find('input').prop('disabled', true);
			jQuery(protocolNumber).siblings().find('input').attr('src', this.disabledSearchIcon);
		} else {
			jQuery(protocolNumber).prop('readonly', false);
			jQuery(protocolNumber).siblings().find('input').prop('disabled', false);
			jQuery(protocolNumber).siblings().find('input').attr('src', this.origSearchIcon);
		}
		if (jQuery(protocolNumber).val().length > 0) {
			approvalTypes.val('');
			approvalTypes.prop('disabled', true);
			if (approvalTypes.siblings('span').html().match(/\w/).length > 0) {
				approvalTypes.hide();
				approvalTypes.siblings('span').show();
			}
		} else {
			approvalTypes.show();
			approvalTypes.prop('disabled', false);
			approvalTypes.siblings('span').hide();
		}
	} else {
		approvalTypes.siblings('span').hide();
		approvalTypes.show();
		approvalTypes.prop('disabled', false);
		jQuery(protocolNumber).prop('readonly', false);
		jQuery(protocolNumber).siblings().find('input').prop('disabled', false);
		jQuery(protocolNumber).siblings().find('input').attr('src', this.origSearchIcon);
	}
}
SpecialReviewGlobals.getRow = function(control) {
	var parent = jQuery(control).parentsUntil('tr');
	return jQuery(parent).parent();
}
SpecialReviewGlobals.removeApprovalOptions = function(approvalType, optionValuesToKeep) {
	jQuery(approvalType).children().each(function() {
		if (jQuery.inArray(jQuery(this).val(), optionValuesToKeep) == -1) {
			jQuery(this).remove();
		}
	});
}
SpecialReviewGlobals.addDisabledOptionsBack = function(approvalType) {
	jQuery(approvalType).html('');
	jQuery(this.disabledApprovalTypeSelector).children().clone().each(function() {
		jQuery(approvalType).append(this);
	});
}
SpecialReviewGlobals.showHideSpecialReviewProtocolLink = function(specialReviewControl, canCreateIrbProtocol, canCreateIacucProtocol) {
	var row = this.getRow(specialReviewControl);
	var typeCode = jQuery(specialReviewControl).val();
	if ((typeCode == this.humanSubjectsTypeCode && canCreateIrbProtocol)
			|| (typeCode == this.animalUsageTypeCode && canCreateIacucProtocol)) {
		jQuery(row).find('input[name*="createProtocol"]').show();
	} else {
		jQuery(row).find('input[name*="createProtocol"]').hide(); 	
	}
	var selectedApprovalType = jQuery(row).find(this.approvalTypeSelector).val();
	if (typeCode == this.humanSubjectsTypeCode && this.enableIrbProtocolLinking) {
		jQuery(row).find('span.irbLookupLink').show();
		jQuery(row).find('span.iacucLookupLink').hide();
		jQuery(row).find('.dynamicReadDiv').show();
		jQuery(row).find('.dynamicEditDiv').hide();
		this.removeApprovalOptions(jQuery(row).find(this.approvalTypeSelector), new Array("", this.notYetAppliedApprovalTypeCode));
	} else if (typeCode == this.animalUsageTypeCode && this.enableIacucProtocolLinking) {
		jQuery(row).find('span.irbLookupLink').hide();
		jQuery(row).find('span.iacucLookupLink').show();
		jQuery(row).find('.dynamicReadDiv').show();
		jQuery(row).find('.dynamicEditDiv').hide();
		this.removeApprovalOptions(jQuery(row).find(this.approvalTypeSelector), new Array("", this.notYetAppliedApprovalTypeCode));
	} else {
		jQuery(row).find('span.irbLookupLink').hide();
		jQuery(row).find('span.iacucLookupLink').hide();
		jQuery(row).find('.dynamicReadDiv').hide();
		jQuery(row).find('.dynamicEditDiv').show();
		this.addDisabledOptionsBack(jQuery(row).find(this.approvalTypeSelector));
	}
	jQuery(row).find(this.approvalTypeSelector).val(selectedApprovalType);
	this.processNotYetAppliedChange(jQuery(row).find(this.approvalTypeSelector));
}

jQuery(document).ready(function() {
	SpecialReviewGlobals.origSearchIcon = jQuery('input[type="image"][src*="searchicon.gif"]').attr('src');
	jQuery('input[type="text"][name*="protocolNumber"]').change(function() {SpecialReviewGlobals.processNotYetAppliedChange(this);});
	jQuery(SpecialReviewGlobals.approvalTypeSelector).change(function() {SpecialReviewGlobals.processNotYetAppliedChange(this);});
	jQuery(SpecialReviewGlobals.approvalTypeSelector).first().children().clone().each(function() {jQuery(SpecialReviewGlobals.disabledApprovalTypeSelector).append(this);});
	jQuery('select[name*="specialReviewTypeCode"]').each(function() {
		SpecialReviewGlobals.showHideSpecialReviewProtocolLink(this, '${canCreateIrbProtocol}', '${canCreateIacucProtocol}');
	});
	jQuery('input[type="text"][name*="protocolNumber"]').each(function() {SpecialReviewGlobals.processNotYetAppliedChange(this);});
});
</script>

<kul:tab tabTitle="Special Review" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="specialReviewHelper.newSpecialReview*,${collectionProperty}*">
    <div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Special Review</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClassName}" altText="help"/></span>
        </h3>
        <%-- used to store the original list of approval type codes --%>
        <select name="disabledApprovalTypeCode" style="display:none;"/>
        <table id="specialReviewTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.specialReviewTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.approvalTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.protocolNumber}" noColon="true" /></nobr></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.applicationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.approvalDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.expirationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${exemptionAttributes.exemptionTypeCode}" noColon="true" /></div></th>
              	<c:if test="${canModify}"> 
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    </c:if>
          	</tr>     

            <c:if test="${canModify}">
            	<tbody class="addline">
                <tr>
                    <c:set var="protocolLinkingReadOnly" value="${(enableIrbProtocolLinking && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '1') || (enableIacucProtocolLinking && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '2')}" />
                    <c:choose>
                       <c:when test="${enableIrbProtocolLinking && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '1'}">
                           <c:set var="initialStyleIrb" value="display:inline"/>
                       </c:when>
                       <c:otherwise>
                          <c:set var="initialStyleIrb" value="display:none"/>
                       </c:otherwise>
                    </c:choose>
                    <c:choose>
                       <c:when test="${enableIacucProtocolLinking && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '2'}">
                           <c:set var="initialStyleIacuc" value="display:inline"/>
                       </c:when>
                       <c:otherwise>
                          <c:set var="initialStyleIacuc" value="display:none"/>
                       </c:otherwise>
                    </c:choose>
                    
	                <c:set var="textAreaFieldName" value="specialReviewHelper.newSpecialReview.comments" />
					<th class="infoline" rowspan="2">
						Add:
					</th>
	                <td align="left" valign="middle" class="infoline"><div align="center">
	                   <kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.specialReviewTypeCode" 
		                                         attributeEntry="${attributes.specialReviewTypeCode}"
		                                         styleClass="fixed-size-200-select"
		                                         onchange="SpecialReviewGlobals.showHideSpecialReviewProtocolLink(this, '${canCreateIrbProtocol}', '${canCreateIacucProtocol}');return false"/>
					</div></td>
	                <td class="infoline"><div align="center">
	                   <kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.approvalTypeCode" 
		                                                attributeEntry="${attributes.approvalTypeCode}"/>
                       <span>${KualiForm.specialReviewHelper.newSpecialReview.protocolStatus}</span>
	                </div></td>
	                <td class="infoline"><div align="center">
                        <kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.protocolNumber" 
		                                          attributeEntry="${attributes.protocolNumber}" />
                            <span class="irbLookupLink" style="${initialStyleIrb}">
                                <kul:lookup boClassName="org.kuali.kra.irb.Protocol" 
                                            fieldConversions="protocolNumber:specialReviewHelper.newSpecialReview.protocolNumber" />
                            </span>
                            <span class="iacucLookupLink" style="${initialStyleIacuc}">
                                <kul:lookup boClassName="org.kuali.kra.iacuc.IacucProtocol" 
                                            fieldConversions="protocolNumber:specialReviewHelper.newSpecialReview.protocolNumber" />
                            </span>
					</div></td>
	                <td align="left" valign="middle" class="infoline"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="specialReviewHelper.newSpecialReview.applicationDate" 
                                                         attributeEntry="${attributes.applicationDate}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         staticOnly="false" />
	                </div></td>
                    <td align="left" valign="middle" class="infoline"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="specialReviewHelper.newSpecialReview.approvalDate" 
                                                         attributeEntry="${attributes.approvalDate}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         staticOnly="false" />
	                </div></td>
	                <td align="left" valign="middle" class="infoline"><div align="center">
	                	<kra:dynamicHtmlControlAttribute property="specialReviewHelper.newSpecialReview.expirationDate" 
	                	                                 attributeEntry="${attributes.expirationDate}" 
	                	                                 initialReadOnly="${protocolLinkingReadOnly}"
	                	                                 staticOnly="false" />
	                </div></td>
	                <td align="left" valign="middle" class="infoline"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="specialReviewHelper.newSpecialReview.exemptionTypeCodes" 
                                                         attributeEntry="${exemptionAttributes.exemptionTypeCode}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}" 
                                                         staticOnly="false" />  			
	                </div></td>
					<td class="infoline" rowspan="1"><div align="center">
						<html:image property="methodToCall.addSpecialReview.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton addButton"/>

					            <c:if test="${canCreateIrbProtocol || canCreateIacucProtocol}">
		                            <html:image property="methodToCall.createProtocol.anchor${tabKey}"
		                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-startprotocol.gif' 
	    	                        title="Create Protocol"
	        	                    styleClass="tinybutton"/>
	        	                </c:if>
	                </div></td>
	            </tr>
	            
	            <tr>
	            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${attributes.comments}" noColon="false" /></div></th>
	            	<td colspan="7">
	            		<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.comments" 
	            		                          attributeEntry="${attributes.comments}" />
	            	</td>  
	            </tr>
	            </tbody>     
            </c:if>
            
        	<c:forEach var="specialReview" items="${collectionReference}" varStatus="status">
                <tr>
                    <c:set var="protocolLinkingReadOnly" value="${specialReview.approvalTypeCode != '3' && ((enableIrbProtocolLinking and specialReview.specialReviewTypeCode == '1' ) || (enableIacucProtocolLinking and specialReview.specialReviewTypeCode == '2' )) }" />
                    <c:choose>
                       <c:when test="${enableIrbProtocolLinking && collectionReference[status.index].specialReviewTypeCode == '1'}">
                           <c:set var="initialStyleIrb" value="display:inline"/>
                       </c:when>
                       <c:otherwise>
                          <c:set var="initialStyleIrb" value="display:none"/>
                       </c:otherwise>
                    </c:choose>
                    <c:choose>
                       <c:when test="${enableIacucProtocolLinking && collectionReference[status.index].specialReviewTypeCode == '2'}">
                           <c:set var="initialStyleIacuc" value="display:inline"/>
                       </c:when>
                       <c:otherwise>
                          <c:set var="initialStyleIacuc" value="display:none"/>
                       </c:otherwise>
                    </c:choose>

	                <c:set var="textAreaFieldName" value="${collectionProperty}[${status.index}].comments" />
					<th class="infoline" rowspan="2">
					   <c:out value="${status.index+1}" />
					</th>
                    <td align="left" valign="middle"><div align="center">
                        <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].specialReviewTypeCode" 
	                                              attributeEntry="${attributes.specialReviewTypeCode}"  
	                                              readOnly="${not canModify}"
	                                              styleClass="fixed-size-200-select"
	                                              readOnlyAlternateDisplay="${specialReview.specialReviewType.description}" 
	                                              onchange="SpecialReviewGlobals.showHideSpecialReviewProtocolLink(this, '${canCreateIrbProtocol}', '${canCreateIacucProtocol}');return false" />
					</div></td>
                    <td><div align="center">
						<c:if test="${canModify}">
                        <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].approvalTypeCode" 
	                                                     attributeEntry="${attributes.approvalTypeCode}" 
                                                         readOnly="${not canModify}"/>
						</c:if>
						<span>
                            <c:choose>
	                            <c:when test="${protocolLinkingReadOnly}">
	                                ${specialReview.protocolStatus}
	                            </c:when>
	                            <c:otherwise>
	                                ${specialReview.approvalType.description}
	                            </c:otherwise>
                            </c:choose>
                       </span>
	                </div></td>
                    <td><div align="center">
                        <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].protocolNumber" 
                                                  attributeEntry="${attributes.protocolNumber}" 
                                                  readOnly="${not canModify}" />
							<c:if test="${canModify}">
                            <span class="irbLookupLink" style="${initialStyleIrb}">
	                            <kul:lookup boClassName="org.kuali.kra.irb.Protocol" 
		                                    fieldConversions="protocolNumber:${collectionProperty}[${status.index}].protocolNumber" />
                            </span>
                            <span class="iacucLookupLink" style="${initialStyleIacuc}">
	                            <kul:lookup boClassName="org.kuali.kra.iacuc.IacucProtocol" 
		                                    fieldConversions="protocolNumber:${collectionProperty}[${status.index}].protocolNumber" />
                            </span>
                            </c:if>
					</div></td>
                    <td align="left" valign="middle"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="${collectionProperty}[${status.index}].applicationDate" 
                                                         attributeEntry="${attributes.applicationDate}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         readOnly="${not canModify}"
                                                         staticOnly="false" />
	                </div></td>
	                <td align="left" valign="middle"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="${collectionProperty}[${status.index}].approvalDate" 
                                                         attributeEntry="${attributes.approvalDate}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         readOnly="${not canModify}"
                                                         staticOnly="false" />
	                </div></td>
	                <td align="left" valign="middle"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="${collectionProperty}[${status.index}].expirationDate" 
                                                         attributeEntry="${attributes.expirationDate}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         readOnly="${not canModify}"
                                                         staticOnly="false"/>
	                </div></td>
	                <td align="left" valign="middle"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="${collectionProperty}[${status.index}].exemptionTypeCodes" 
                                                         attributeEntry="${exemptionAttributes.exemptionTypeCode}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         readOnly="${not canModify}"
                                                         staticOnly="false"/>
	                </div></td>
					<td rowspan="1"><div align=center>
                        <c:if test="${canModify}">
                            <html:image property="methodToCall.deleteSpecialReview.line${status.index}.anchor${currentTabIndex}.validate0"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                        </c:if>
                        <c:if test="${protocolLinkingReadOnly and not empty collectionReference[status.index].protocolNumber}">
                            <html:image property="methodToCall.viewSpecialReviewProtocolLink"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' alt="View Protocol" styleClass="tinybutton"
                                        onclick="javascript: specialReviewProtocolPop(${KualiForm.document.sessionDocument}, '${action}', 'viewSpecialReviewProtocolLink', ${status.index}, ${KualiForm.formKey});return false" />
                        </c:if>
	                </div></td>
	            </tr>
	            <tr>
	            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${attributes.comments}" noColon="false" /></div></th>
	            	<td colspan="7">
		            	<nobr>
	                        <c:choose>
	                            <c:when test="${canModify}">
	                                <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].comments" 
	                                                          attributeEntry="${attributes.comments}"/>
	                            </c:when>
	                            <c:otherwise>
			            		    <kra:truncateComment textAreaFieldName="${collectionProperty}[${status.index}].comments" action="${action}" 
		                                                 textAreaLabel="${attributes.comments.label}" textValue="${specialReview.comments}"  
		                                                 displaySize="${commentDisplayLength}"/>
	                            </c:otherwise>
	                        </c:choose>
	                    </nobr>
	            	</td> 
	            </tr>
        	</c:forEach>
        </table>
    </div> 
</kul:tab>
