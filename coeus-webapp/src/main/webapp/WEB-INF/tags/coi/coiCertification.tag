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
<%@ attribute name="topTab" required="true" type="java.lang.Boolean" description="is this the top tab on the page" %>

<%@ attribute name="categories" required="false" type="java.lang.String" description="comma-separated string of validation categories (ex: Validation Errors,Warnings). If not set a default will be used." %>
<%@ tag body-content="scriptless" description="The instructions for using the validation. If not set a default will be used." example="You can activate a Validation check...</p><ul><li>errors</li><li>warnings</li></ul>" %>

<c:set var="title" value="Certification" />
<c:set var="certStatement" value="${KualiForm.document.coiDisclosureList[0].certificationStatement}" />
<c:set var="ackStatement" value="${KualiForm.document.coiDisclosureList[0].acknowledgementStatement}" />
<c:set var="reporterName" value="${KualiForm.document.coiDisclosureList[0].disclosurePersons[0].reporter.fullName}" />
<c:set var="certTimeStamp" value="${KualiForm.document.coiDisclosureList[0].certificationTimestampString}" />
<c:set var="submitThankyouStatement" value="${KualiForm.document.coiDisclosureList[0].submitThankyouStatement}" />
<c:set var="viewCertificationAuth" value="${KualiForm.disclosureHelper.canViewDisclosureCertification}" />
<c:set var="certifyCertificationAuth" value="${KualiForm.disclosureHelper.canCertifyDisclosure}" />
<c:set var="disclosureHelper" value="${KualiForm.disclosureHelper}" />

<c:if test="${topTab == true}">
	<%--instead of using kul:tabTop tag just define the workarea div - this gets around an unbalanced tag problem when using conditional tags --%>
	<div id="workarea">
</c:if>
	
<kul:tab tabTitle="${title}" defaultOpen="false"  transparentBackground="${topTab}" tabAuditKey="*">
	<div class="tab-container" align="center">
		<h3> 
			<span class="subhead-left">${title}</span>
	   		<span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiCertificationHelp" altText="help"/></span>
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
				<th style="height: 30px;" colspan="2">
					Certification
					<c:if test="${empty certTimeStamp}">
						- Not yet certified
					</c:if>
					
				</th>
			</tr>
			<tr>
            	<td style="vertical-align:top;">
					<input type="checkbox" id="certCheckbox" 
		    			<c:if test="${!empty certTimeStamp}">
							checked="checked"
						</c:if>
						<c:if test="${readOnly}">
							disabled="true"
						</c:if>
					/>
	                <script type="text/javascript">
						$j(document).ready(function(){
							// initial state
							if ($j('#certCheckbox').is(':checked') || '${viewCertificationAuth}' == 'true') {
								$j("#certSubpanel").show();
								$j("#certPrint").show();
								$j("#certSubmit").hide();
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
										$j("#certSubmit").show();
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
                                    	<c:if test="${!readOnly && certifyCertificationAuth}">
											<html:image property="methodToCall.submitDisclosureCertification"
													    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif" 
														title="Submit disclosure certification" alt="Submit disclosure certification" 
														styleClass="tinybutton" />
                	                        <script type="text/javascript">
														$j(document).ready(function(){
															// trigger
															$j("#certSubmit").click(
																function() {
<%-- we don't really need this...											alert("${submitThankyouStatement}");   --%>
																	$j("#certSubmit").hide(0);
																	$j("#certCheckbox").attr("disabled", true);
																}
															);
														});
											</script>
										</c:if>
                                    </td>
                                    <c:if test="${viewCertificationAuth}">
                    	                <td id="certPrint" style="border:none; background:none; text-align:center;" rowspan="2">
											<html:image property="methodToCall.printDisclosureCertification"
													    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif" 
														title="Print Disclosure" alt="Print Disclosure" 
														styleClass="tinybutton"  onclick="excludeSubmitRestriction = true;"/>
	                                    </td>
                                    </c:if>
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
