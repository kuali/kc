<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="topTab" required="true" type="java.lang.Boolean" description="is this the top tab on the page" %>

<%@ attribute name="categories" required="false" type="java.lang.String" description="comma-separated string of validation categories (ex: Validation Errors,Warnings). If not set a default will be used." %>
<%@ tag body-content="scriptless" description="The instructions for using the validation. If not set a default will be used." example="You can activate a Validation check...</p><ul><li>errors</li><li>warnings</li></ul>" %>

<c:set var="title" value="Certification" />
<c:set var="certStatement" value="${KualiForm.document.coiDisclosureList[0].certificationStatement}" />
<c:set var="ackStatement" value="${KualiForm.document.coiDisclosureList[0].acknowledgementStatement}" />
<c:set var="reporter" value = "document.coiDisclosureList[0].disclosurePersons[0].reporter" />
<c:if test="${topTab == true}">
	<%--instead of using kul:tabTop tag just define the workarea div - this gets around an unbalanced tag problem when using conditional tags --%>
	<div id="workarea">
</c:if>
	
<kul:tab tabTitle="${title}" defaultOpen="false"  transparentBackground="${topTab}" tabAuditKey="*">
	<div class="tab-container" align="center">
		<h3> 
			<span class="subhead-left">${title}</span>
		</h3>
		<table cellpadding="0" cellspacing="0" summary="">
			<tr>
				<td colspan="2">
					<div class="floaters">
						<%-- the certification instructions get inserted here --%>
						<c:choose>
							<c:when test="${empty certStatement}">
								${certStatement}
								<p>This is a dummy certification statement.  You must configure a statement in accordance with 
								the policies of your institution. This statement may be configured using the COI_CERTIFICATION_STATEMENT
								parameter.</p>
							</c:when>
							<c:otherwise>
								${certStatement}
							</c:otherwise>
						</c:choose>
					</div>
				</td>
			</tr>
			<tr>
				<th style="height: 30px;" colspan="2">Certification</th>
			</tr>
			<tr>
            	<td style="vertical-align:top;">
                	<input type="checkbox" id="C_Certification" />
                    <script type="text/javascript">
						$(document).ready(function(){
							// initial state
								$("#D_Certification").hide(0);
								$("#C_Certification").attr('checked', false);
							// trigger
								$("#C_Certification").click(
									function()
										{
											F_Certification();
										}
									);
									// function
									function F_Certification()
										{
alert('F_Certification');										
											if ($('#C_Certification').is(':checked'))
											{
												$("#D_Certification").slideDown(400);
												$("#C_Certification").attr('checked', true);
											}
											else
											{
												$("#D_Certification").slideUp(200);
												$("#C_Certification").attr('checked', false);
											}
										}
									}
								);
					</script>
                </td>
                <td>
					<c:choose>
						<c:when test="${empty ackStatement}">
							This is a dummy acknowledgement statement.  Set up an acknowledgement for your institution using
							the system parameter COI_CERTIFICATION_ACKNOWLEDGEMENT.
						</c:when>
						<c:otherwise>
							${ackStatement}
						</c:otherwise>
					</c:choose>
                    <div id="D_Certification" style="display:none;">
                    	<p>
                        	<table style="border:none; background:none; width:400px;" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td style="border:none; background:none; text-align:center;">
	                                    <kul:htmlControlAttribute property="${reporter}.fullName" 
    	                                                          attributeEntry="${personAttributes.fullName}" 
        	                                                      readOnly="true"/> 
                                    </td>
                                    <td style="border:none; background:none; text-align:center;">
                                        <u><b>10/09/2009</b></u>
                                    </td>
                                    <td style="border:none; background:none; text-align:center;" rowspan="2">
                                        <input id="BFNsubmit" src="../images/tinybutton-submit.gif" onclick="javascript: alert('Thank you for submitting your certification.'); return false" style="border:none;" alt="submit" title="Submit" type="image" />
                                        <script type="text/javascript">
													$(document).ready(function(){
														// initial state
															$("#BFNprint").hide(0);
															$("#C_Certification").attr("disabled", false);
														// trigger
															$("#BFNsubmit").click(
																function()
																{
																	$("#BFNsubmit").hide(0);
																	$("#BFNprint").slideDown(0);
																	$("#C_Certification").attr("disabled", true);
																}
															);
													});
										</script>
                                        <input id="BFNprint" src="../images/tinybutton-print.gif" onclick="javascript: alert('This would print the Internal Disclosure.'); return false" style="border:none;" alt="print" title="Print" type="image" />
                                    </td>
                                </tr>
                                <tr>
                                    <td style="border:none; background:none; text-align:center;" class="fineprint">
                                        Reporter
                                    </td>
                                    <td style="border:none; background:none; text-align:center;" class="fineprint">
                                        Date
                                    </td>
                                </tr>
                        	</table>
                        </p>
					</div>
				</td>
			</tr>
		</table>
		<c:if test="${auditActivated}">
			<c:set var="categories" value="${empty categories ? 'Validation Errors,Warnings' : categories}" />
			<table cellpadding="0" cellspacing="0" summary="">
			<c:forEach items="${categories}" var="category">
				<kul:auditSet category="${category}" />
			</c:forEach>
			</table>
		</c:if>
	</div>
</kul:tab>
