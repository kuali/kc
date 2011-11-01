<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="topTab" required="true" type="java.lang.Boolean" description="is this the top tab on the page" %>

<%@ attribute name="categories" required="false" type="java.lang.String" description="comma-separated string of validation categories (ex: Validation Errors,Warnings). If not set a default will be used." %>
<%@ tag body-content="scriptless" description="The instructions for using the validation. If not set a default will be used." example="You can activate a Validation check...</p><ul><li>errors</li><li>warnings</li></ul>" %>

<c:set var="title" value="Certification" />
<c:set var="certStatement" value="${KualiForm.document.coiDisclosureList[0].certificationStatement}" />
<c:set var="ackStatement" value="${KualiForm.document.coiDisclosureList[0].acknowledgementStatement}" />
<c:set var="reporterName" value="${KualiForm.document.coiDisclosureList[0].disclosurePersons[0].reporter.fullName}" />
<c:set var="certTimeStamp" value="${KualiForm.document.coiDisclosureList[0].certificationTimestampString}" />
<c:set var="submitThankyouStatement" value="${KualiForm.document.coiDisclosureList[0].submitThankyouStatement}" />
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
					<c:choose>
		    			<c:when test="${empty certTimeStamp}">
							<input type="checkbox" id="certCheckbox" />
						</c:when>
						<c:otherwise>
							<input type="checkbox" id="certCheckbox" checked="checked" />
						</c:otherwise>
					</c:choose>
                    <script type="text/javascript">
						$j(document).ready(function(){
							// initial state
							if ($j('#certCheckbox').is(':checked')) {
								$j("#certSubpanel").show();
							} else {
								$j("#certSubpanel").hide();
							}
							// trigger
							$j("#certCheckbox").click(
								function() {
										funcHideShowCert();
									}
								);
								// function
								function funcHideShowCert() {
										if ($j('#certCheckbox').is(':checked')) {
											$j("#certSubpanel").show();
											$j("#certCheckbox").attr('checked', true);
										} else {
											$j("#certSubpanel").hide();
											$j("#certCheckbox").attr('checked', false);
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
                    <div id="certSubpanel" style="display:none;">
                    	<p>
                        	<table style="border:none; background:none; width:400px;" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td style="border:none; background:none; text-align:center;">
	                                    <u><b>${reporterName}</b></u> 
                                    </td>
                                    <td style="border:none; background:none; text-align:center;">
										<c:choose>
		    								<c:when test="${empty certTimeStamp}">
												<u><b>N/A</b></u>										 
											</c:when>
											<c:otherwise>
												<u><b>${certTimeStamp}</b></u>										 
											</c:otherwise>
										</c:choose>
                                    </td>
                                    <td id="certSubmit" style="border:none; background:none; text-align:center;" rowspan="2">
											<html:image property="methodToCall.saveDisclosureCertification"
													    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif" 
														title="Submit disclosure certification" alt="Submit disclosure certification" 
														styleClass="tinybutton" />
                	                        <script type="text/javascript">
														$j(document).ready(function(){
															// initial state
															$j("#certPrint").hide(0);
															$j("#certCheckbox").attr("disabled", false);
															// trigger
																$j("#certSubmit").click(
																	function() {
																		alert("${submitThankyouStatement}");
																		$j("#certSubmit").hide(0);
																		$j("#certCheckbox").attr("disabled", true);
																	}
																);
														});
											</script>
                                    </td>
                                    <td style="border:none; background:none; text-align:center;" rowspan="2">
										<div id="certPrint">
											<html:image property="methodToCall.printDisclosureCertification"
													    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif" 
														title="Print disclosure certification" alt="Print disclosure certification" 
														styleClass="tinybutton" onclick='javascript: alert("Printing is still to be implemented, sorry for the delay.");' />
										</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="border:none; background:none; text-align:center;" class="fineprint">
                                        Reporter
                                    </td>
                                    <td style="border:none; background:none; text-align:center;" class="fineprint">
                                        Certification Date
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
