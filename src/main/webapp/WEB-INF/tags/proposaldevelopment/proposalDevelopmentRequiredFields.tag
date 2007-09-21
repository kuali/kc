<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.title" />
<c:set var="action" value="proposalDevelopmentProposal" />
<c:set var="className" value="org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument" />

<kul:tab tabTitle="Required Fields for Saving Document" defaultOpen="true" tabErrorKey="document.sponsorCode*,document.proposalTypeCode*,document.requestedStartDateInitial*,document.ownedByUnit*,document.requestedEndDateInitial*,document.activityTypeCode*,document.title">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Required Fields for Saving Document</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>

        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<th><div align="right">*Proposal #:</div></th>
                <td>${KualiForm.document.proposalNumber}</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.sponsorCode}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.sponsorCode" attributeEntry="${proposalDevelopmentAttributes.sponsorCode}" onblur="loadSponsorName('document.sponsorCode', 'sponsorName');" />
                	<img src="${ConfigProperties.kr.externalizable.images.url}searchicon.gif" alt="lookup" height="16" width="16"/>
                    <div id="sponsorName.div" >
                        <c:if test="${!empty KualiForm.document.sponsorCode}">
                        	<c:choose>
								<c:when test="${empty KualiForm.document.sponsor}">
									<c:out value="not found" />
								</c:when>
								<c:otherwise>
									<c:out value="${KualiForm.document.sponsor.sponsorName}" />
								</c:otherwise>
						</c:choose>
                        </c:if>
					</div>
				</td>
            </tr>
            <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.proposalTypeCode}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.proposalTypeCode" attributeEntry="${proposalDevelopmentAttributes.proposalTypeCode}" />
                  	<div id="registration01">
<!--
                    	<table cellpadding=0 cellspacing=0 class="nobord" id="1st" style="width:20%">
                      		<tr>
                        		<td nowrap class="nobord"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" /></div></td>
                        		<td class="nobord"><kul:htmlControlAttribute property="document.currentAwardNumber" attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" /></td>
                      		</tr>
                      		<tr>
                        		<td nowrap class="nobord"><div align=right>Original Proposal #:</div></td>
                        		<td class="nobord"><!- -webbot bot="Validation" b-value-required="TRUE" - ->
                          			<input name="textfield" type=text id="textfield" size=12>
                          		</td>
                      		</tr>
                    	</table>
 -->
                  	</div>
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.requestedStartDateInitial}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.requestedStartDateInitial" attributeEntry="${proposalDevelopmentAttributes.requestedStartDateInitial}" datePicker="true" />
                </td>
            </tr>
            <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.ownedByUnit}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.ownedByUnit" attributeEntry="${proposalDevelopmentAttributes.ownedByUnit}" />
				</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.requestedEndDateInitial}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.requestedEndDateInitial" attributeEntry="${proposalDevelopmentAttributes.requestedEndDateInitial}" datePicker="true" />
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.activityTypeCode}" /></div></th>
                <td><kul:htmlControlAttribute property="document.activityTypeCode" attributeEntry="${proposalDevelopmentAttributes.activityTypeCode}" /></td>
				<th>&nbsp;</th>
                <td align="left" valign="middle">&nbsp;</td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.title}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.title" attributeEntry="${proposalDevelopmentAttributes.title}" />
                    <html:image property="methodToCall.updateTextArea.((#${textAreaFieldName}:${action}:${proposalDevelopmentAttributes.title.label}#)).anchor${currentTabIndex}" src='${ConfigProperties.kra.externalizable.images.url}pencil_add.png' onclick="javascript: textAreaPop(document.getElementById('${textAreaFieldName}').value,'${textAreaFieldName}','proposalDevelopment','${proposalDevelopmentAttributes.title.label}');return false" styleClass="tinybutton"  />
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
