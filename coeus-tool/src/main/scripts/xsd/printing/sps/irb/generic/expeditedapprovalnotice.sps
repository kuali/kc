<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="D:\StyleVisionFiles\Correspondent templates\xmlpyproject\irb.xsd" workingxmlfile="C:\correspondenceTemplates\xml files\Correspondence.xml">
				<xmltablesupport/>
				<textstateicons/>
			</xsdschemasource>
		</schemasources>
	</schemasources>
	<modules/>
	<flags>
		<scripts/>
		<globalparts/>
		<designfragments/>
		<pagelayouts/>
	</flags>
	<scripts>
		<script language="javascript"/>
	</scripts>
	<globalstyles/>
	<mainparts>
		<children>
			<globaltemplate match="/" matchtype="named" parttype="main">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<image>
								<properties border="0"/>
								<target>
									<fixtext value="http://localhost:8080/CoeusApplet/images/couhes_byline2.gif"/>
								</target>
								<imagesource>
									<fixtext value="http://localhost:8080/CoeusApplet/images/couhes_byline2.gif"/>
								</imagesource>
							</image>
							<newline/>
							<template match="n1:Correspondence" matchtype="schemagraphitem">
								<children>
									<paragraph>
										<styles font-size="10pt" font-style="normal"/>
										<children>
											<table>
												<properties border="0"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="127"/>
																		<children>
																			<text fixtext="To:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext="      ">
																				<styles font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="388"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:Investigator" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="n1:PI_flag = &apos;true&apos;">
																										<children>
																											<template match="n1:Person" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:Firstname" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<styles font-size="10pt"/>
																															</content>
																														</children>
																													</template>
																													<text fixtext=" ">
																														<styles font-size="10pt"/>
																													</text>
																													<template match="n1:LastName" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<styles font-size="10pt"/>
																															</content>
																														</children>
																													</template>
																													<paragraph paragraphtag="p">
																														<children>
																															<paragraph paragraphtag="p">
																																<children>
																																	<paragraph paragraphtag="pre">
																																		<children>
																																			<template match="n1:OfficeLocation" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<styles font-size="10pt"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</paragraph>
																																</children>
																															</paragraph>
																														</children>
																													</paragraph>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="127"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="From:">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="388"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<template match="n1:Protocol" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos;">
																										<children>
																											<template match="n1:Submissions" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:CommitteeMember" matchtype="schemagraphitem">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="n1:CommitteeMemberRole/n1:MemberRoleDesc = &apos;Chair&apos;">
																																		<children>
																																			<template match="n1:Person" matchtype="schemagraphitem">
																																				<children>
																																					<template match="n1:Firstname" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																																										<children>
																																											<content>
																																												<styles font-size="10pt"/>
																																											</content>
																																										</children>
																																									</conditionbranch>
																																								</children>
																																							</condition>
																																						</children>
																																					</template>
																																					<text fixtext=" ">
																																						<styles font-size="10pt"/>
																																					</text>
																																					<template match="n1:LastName" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																																										<children>
																																											<content>
																																												<styles font-size="10pt"/>
																																											</content>
																																										</children>
																																									</conditionbranch>
																																								</children>
																																							</condition>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</template>
																					<text fixtext=", Chair ">
																						<styles font-size="10pt"/>
																					</text>
																					<paragraph paragraphtag="p">
																						<children>
																							<template match="n1:Protocol" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos;">
																												<children>
																													<template match="n1:Submissions" matchtype="schemagraphitem">
																														<children>
																															<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																																<children>
																																	<template match="n1:CommitteeName" matchtype="schemagraphitem">
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																																						<children>
																																							<content>
																																								<styles font-size="10pt"/>
																																							</content>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</template>
																						</children>
																					</paragraph>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="127"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="Date:">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="388"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<template match="n1:CurrentDate" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-size="10pt"/>
																								<format string="MM/DD/YYYY"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="127"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="Committee Action:">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="388"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="n1:Protocol/n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos; and   n1:Protocol/n1:Submissions/n1:SubmissionDetails/n1:ProtocolReviewTypeCode  = 1">
																								<children>
																									<text fixtext="Approval">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch xpath="n1:Protocol/n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos; and  n1:Protocol/n1:Submissions/n1:SubmissionDetails/n1:SubmissionTypeCode = 102">
																								<children>
																									<text fixtext="Amendment to Approved Protocol">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch xpath="n1:Protocol/n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos; and  n1:Protocol/n1:Submissions/n1:SubmissionDetails/n1:SubmissionTypeCode = 101">
																								<children>
																									<text fixtext="Renewal">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<text fixtext="Expedited Approval">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties border="0"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="127"/>
																		<children>
																			<text fixtext="Approval Date: ">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext="     ">
																				<styles font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="380"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:ApprovalDate" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format string="MM/DD/YYYY"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="127"/>
																		<children>
																			<text fixtext="IRB Protocol #:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext="    ">
																				<styles font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="380"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:ProtocolNumber" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-size="10pt"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="127"/>
																		<children>
																			<text fixtext="Study Title: ">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext="             ">
																				<styles font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="380"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:ProtocolTitle" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-size="10pt"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="127"/>
																		<children>
																			<text fixtext="Expiration Date:">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="380"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:ExpirationDate" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format string="MM/DD/YYYY"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<newline/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</paragraph>
									<paragraph paragraphtag="p">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="n1:Protocol/n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos; and   n1:Protocol/n1:Submissions/n1:SubmissionDetails/n1:ProtocolReviewTypeCode  = 1">
														<children>
															<text fixtext="T">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<text fixtext="he above-referenced protocol has been APPROVED following Full Board Review by the Institutional Review Board for the period of ">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ApprovalDate" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-family="Arial" font-size="10pt"/>
																						<format string="MM/DD/YYYY"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" through ">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ExpirationDate" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-family="Arial" font-size="10pt"/>
																						<format string="MM/DD/YYYY"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=".  This approval does not replace any departmental or other approvals that may be required.  It is the Principal Investigator’s responsibility to obtain review and continued approval of ongoing research before the expiration date noted above.  Please allow sufficient time for reapproval.  Research activity of any sort may not continue beyond the expiration date without committee approval.  Failure to receive approval for continuation before the expiration date will result in the automatic suspension of the approval of this protocol on the expiration date.  Information">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<text fixtext=" ">
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
															</text>
															<text fixtext="collected following suspension is unapproved research and cannot be reported or published as research data.  ">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<newline/>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="If you do not wish continued approval, please notify the Committee of the study termination.">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																	<text fixtext="This approval by the ">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																	<template match="n1:Protocol" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos;">
																						<children>
																							<template match="n1:Submissions" matchtype="schemagraphitem">
																								<children>
																									<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:CommitteeName" matchtype="schemagraphitem">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																																<children>
																																	<content>
																																		<styles font-family="Arial" font-size="10pt"/>
																																	</content>
																																</children>
																															</conditionbranch>
																														</children>
																													</condition>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</template>
																	<text fixtext=" does not replace or supersede any departmental or oversight committee review that may be required by institutional policy.">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</conditionbranch>
													<conditionbranch xpath="n1:Protocol/n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos; and  n1:Protocol/n1:Submissions/n1:SubmissionDetails/n1:SubmissionTypeCode = 102">
														<children>
															<text fixtext="The amendment to the above-referenced protocol has been APPROVED following Expedited Review by the Institutional Review Board.">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<text fixtext=" ">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<text fixtext=" This approval does not replace any departmental or other approvals that may be required.  It is the Principal Investigator’s responsibility to obtain review and continued approval of ongoing research before the expiration noted above.  Please allow sufficient time for reapproval.  Research activity of any sort may not continue beyond the expiration date without committee approval.  Failure to receive approval for continuation before the expiration date will result in the automatic suspension of the approval of this protocol on the expiration date.  Information">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<text fixtext=" ">
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
															</text>
															<text fixtext="collected following suspension is unapproved research and cannot be reported or published as research data.  If you do not wish continued approval, please notify the Committee of the study termination.">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="This approval by the ">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<children>
																	<template match="n1:Submissions" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:CommitteeName" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																										<children>
																											<content>
																												<styles font-family="Arial" font-size="10pt"/>
																											</content>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" does not replace or supersede any departmental or oversight committee review that may be required by institutional policy.">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
														</children>
													</conditionbranch>
													<conditionbranch xpath="n1:Protocol/n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos; and n1:Protocol/n1:Submissions/n1:SubmissionDetails/n1:SubmissionTypeCode = 101">
														<children>
															<text fixtext="The above-referenced protocol was given renewed approval following Expedited Review by the Institutional Review Board.  ">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="It is the Principal Investigator’s responsibility to obtain review and continued approval of ongoing research before the expiration noted above.  Please allow sufficient time for reapproval.  Research activity of any sort may not continue beyond the expiration date without committee approval.  Failure to receive approval for continuation before the expiration date will result in the automatic suspension of the approval of this protocol on the expiration date.  Information">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<text fixtext=" ">
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
															</text>
															<text fixtext="collected following suspension is unapproved research and cannot be reported or published as research data.  If you do not wish continued approval, please notify the Committee of the study termination.">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="This approval by the ">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<children>
																	<template match="n1:Submissions" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:CommitteeName" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																										<children>
																											<content>
																												<styles font-family="Arial" font-size="10pt"/>
																											</content>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" does not replace or supersede any departmental or oversight committee review that may be required by institutional policy.">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
														</children>
													</conditionbranch>
													<conditionbranch>
														<children>
															<text fixtext="The above-referenced protocol was approved following expedited review by the Institutional Review Board.">
																<styles font-size="10pt"/>
															</text>
															<newline/>
															<text fixtext="It is the Principal Investigator’s responsibility to obtain review and continued approval before the expiration date.  You may not continue any research activity beyond the expiration date without approval by the Institutional Review Board.">
																<styles font-size="10pt"/>
															</text>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<newline/>
											<text fixtext="Adverse Reactions:  If any untoward incidents or severe reactions should develop as a result of this study, you are required to notify the  ">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
											<template match="n1:Protocol" matchtype="schemagraphitem">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos;">
																<children>
																	<template match="n1:Submissions" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:CommitteeName" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																										<children>
																											<content>
																												<styles font-family="Arial" font-size="10pt"/>
																											</content>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</template>
											<text fixtext=" ">
												<styles font-size="10pt"/>
											</text>
											<text fixtext="i">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
											<text fixtext="mmediately.  If necessary a member of the IRB will be assigned to look into the matter.  If the problem is serious, approval may be withdrawn pending IRB review.">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
											<newline/>
											<newline/>
											<text fixtext="Amendments: If you wish to change any aspect of this study, such as the procedures, the consent forms, or the investigators, please communicate your requested changes to the">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
											<text fixtext=" ">
												<styles font-size="10pt"/>
											</text>
											<template match="n1:Protocol" matchtype="schemagraphitem">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos;">
																<children>
																	<template match="n1:Submissions" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:CommitteeName" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																										<children>
																											<content>
																												<styles font-family="Arial" font-size="10pt"/>
																											</content>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</template>
											<text fixtext=".  ">
												<styles font-size="10pt"/>
											</text>
											<text fixtext="The new procedure is not to be initiated until the IRB approval has been given.">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
											<newline/>
											<newline/>
											<text fixtext="Please retain a copy of this letter with your approved protocol.">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
											<newline/>
											<newline/>
											<text fixtext="
"/>
											<text fixtext="
"/>
											<newline/>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles page-break-before="auto" table-layout="auto"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="25"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="(n1:Protocol/n1:KeyStudyPerson/n1:Person/n1:PersonID != &apos;null&apos;)  and  (n1:Protocol/n1:Correspondent/n1:Person/n1:PersonID != &apos;null&apos;)">
																						<children>
																							<text fixtext="cc:">
																								<styles font-size="10pt"/>
																							</text>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:KeyStudyPerson" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:Person" matchtype="schemagraphitem">
																								<children>
																									<template match="n1:Fullname" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-size="10pt"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																					<template match="n1:Correspondent" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="n1:TypeOfCorrespondent  = &apos;Protocol level&apos;">
																										<children>
																											<template match="n1:Person" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:Fullname" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<styles font-size="10pt"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</template>
																					<newline/>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<text fixtext="
">
												<styles font-size="10pt"/>
											</text>
										</children>
									</paragraph>
								</children>
							</template>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="1.0in" papermargintop="0.79in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2" height="30"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left"/>
														<styles font-size="smaller" padding="0"/>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles font-size="smaller" padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
