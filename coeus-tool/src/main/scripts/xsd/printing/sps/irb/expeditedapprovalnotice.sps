<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="irb.xsd" workingxmlfile="Y:\ele\Correspondence09202004-032207.xml">
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
									<fixtext value="/export/home/www/https/tomcat5.0.25/webapps/coeus/images/couhes_byline2.gif"/>
								</target>
								<imagesource>
									<fixtext value="/export/home/www/https/tomcat5.0.25/webapps/coeus/images/couhes_byline2.gif"/>
								</imagesource>
							</image>
							<line>
								<properties color="black" size="1"/>
							</line>
							<newline/>
							<template match="n1:Correspondence" matchtype="schemagraphitem">
								<children>
									<paragraph>
										<styles font-size="10pt" font-style="normal"/>
										<children>
											<table>
												<properties border="0" width="100%"/>
												<styles table-layout="fixed"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="24%"/>
																		<styles font-family="Arial"/>
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
																		<properties width="76%"/>
																		<styles font-family="Arial"/>
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
																																<format datatype="string"/>
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
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																													<paragraph paragraphtag="pre">
																														<children>
																															<template match="n1:OfficeLocation" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
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
																		<properties valign="top" width="24%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="From:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
																		<styles font-family="Arial"/>
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
																																										<format datatype="string"/>
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
																																										<format datatype="string"/>
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
																									<text fixtext=", Chair">
																										<styles font-size="10pt"/>
																									</text>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</template>
																			<paragraph paragraphtag="pre">
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
																																						<format datatype="string"/>
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
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="24%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="Date:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<template match="n1:CurrentDate" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-size="10pt"/>
																						<format string="MM/DD/YYYY" datatype="date"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="24%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="Committee Action:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
																		<styles font-family="Arial"/>
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
																							<text fixtext="Amendment to Approved Protocol"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="n1:Protocol/n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos; and  n1:Protocol/n1:Submissions/n1:SubmissionDetails/n1:SubmissionTypeCode = 101">
																						<children>
																							<text fixtext="Renewal"/>
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
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties border="0" width="100%"/>
												<styles table-layout="fixed"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="24%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="COUHES Protocol #:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext="    ">
																				<styles font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
																		<styles font-family="Arial" font-size="10pt"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:ProtocolNumber" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-size="10pt"/>
																										<format datatype="string"/>
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
																		<properties width="24%"/>
																		<styles font-family="Arial"/>
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
																		<properties valign="top" width="76%"/>
																		<styles font-family="Arial" font-size="10pt"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:ProtocolTitle" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-size="10pt"/>
																										<format datatype="string"/>
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
																		<properties width="24%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="Expiration Date:">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
																		<styles font-family="Arial" font-size="10pt"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:ExpirationDate" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="date"/>
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
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</paragraph>
									<paragraph>
										<styles font-family="Arial" font-size="10pt"/>
										<children>
											<condition>
												<children>
													<conditionbranch xpath="n1:Protocol/n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos; and   n1:Protocol/n1:Submissions/n1:SubmissionDetails/n1:ProtocolReviewTypeCode  = 1">
														<children>
															<paragraph paragraphtag="p">
																<styles font-family="Arial" font-size="12pt"/>
																<children>
																	<text fixtext="The above-referenced protocol has been APPROVED following Full Board Review by the Committee on the Use of Humans as Experimental Subjects (COUHES).">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
															<paragraph paragraphtag="p">
																<styles font-family="Arial" font-size="12pt"/>
																<children>
																	<text fixtext="If the research involves collaboration with another institution then the research cannot commence until COUHES receives written notification of approval from the collaborating institution&apos;s IRB.">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="It is the Principal Investigator’s responsibility to obtain review and continued approval before the expiration date.  Please allow sufficient time for continued approval.  You may not continue any research activity beyond the expiration date without COUHES approval.  Failure to receive approval for continuation before the expiration date will result in the automatic suspension of the approval of this protocol.  Information collected following suspension is unapproved research and cannot be reported or published as research data.  If you do not wish continued approval, please notify the Committee of the study termination.">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</conditionbranch>
													<conditionbranch xpath="n1:Protocol/n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos; and  n1:Protocol/n1:Submissions/n1:SubmissionDetails/n1:SubmissionTypeCode = 102">
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="The amendment to the above-referenced protocol has been APPROVED following expedited review by the Committee on the Use of Humans as Experimental Subjects (COUHES).">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="If the research involves collaboration with another institution then the research cannot commence until COUHES receives written notification of approval from the collaborating institution&apos;s IRB.">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="It is the Principal Investigator’s responsibility to obtain review and continued approval before the expiration date.  Please allow sufficient time for continued approval.  You may not continue any research activity beyond the expiration date without COUHES approval.  Failure to receive approval for continuation before the expiration date will result in the automatic suspension of the approval of this protocol.  Information collected following suspension is unapproved research and cannot be reported or published as research data.  If you do not wish continued approval, please notify the Committee of the study termination.">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
															<newline/>
														</children>
													</conditionbranch>
													<conditionbranch xpath="n1:Protocol/n1:Submissions/n1:CurrentSubmissionFlag =&apos;No&apos; and n1:Protocol/n1:Submissions/n1:SubmissionDetails/n1:SubmissionTypeCode = 101">
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="The above-referenced protocol was given renewed approval following Expedited Review by the Committee on the Use of Humans as Experimental Subjects (COUHES).">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="If the research involves collaboration with another institution then the research cannot commence until COUHES receives written notification of approval from the collaborating institution&apos;s IRB.">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																	<newline/>
																	<newline/>
																	<text fixtext="It is the Principal Investigator&apos;s responsibility to obtain review and continued approval before the expiration date.  You may not continue any research activity beyond the expiration date without approval by COUHES.  Failure to renew your study before the expiration date will result in termination of the study and suspension of related research grants.">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</conditionbranch>
													<conditionbranch>
														<children>
															<paragraph paragraphtag="p">
																<styles font-family="Bodoni MT" font-size="12pt"/>
																<children>
																	<text fixtext="The above-referenced protocol was approved following expedited review by the Committee on the Use of Humans as Experimental Subjects (COUHES).">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
															<paragraph paragraphtag="p">
																<styles font-size="12pt"/>
																<children>
																	<text fixtext="If the research involves collaboration with another institution then the research cannot commence until COUHES receives written notification of approval from the collaborating institution&apos;s IRB.">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
															<paragraph paragraphtag="p">
																<styles font-family="Arial" font-size="12pt"/>
																<children>
																	<text fixtext="It is the Principal Investigator’s responsibility to obtain review and continued approval before the expiration date.  You may not continue any research activity beyond the expiration date without approval by COUHES.  Failure to renew your study before the expiration date will result in termination of the study and suspension of related research grants.">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
															<newline/>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<paragraph paragraphtag="p">
												<styles font-family="Arial" font-size="12pt"/>
												<children>
													<text fixtext="Adverse Events:  Any serious or unexpected adverse event must be reported to COUHES within 48 hours. All other adverse events should be reported in writing within 10 working days.">
														<styles font-family="Arial" font-size="10pt"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<styles font-family="Arial" font-size="12pt"/>
												<children>
													<text fixtext="Amendments: Any changes to the protocol that impact human subjects, including changes in experimental design, equipment, personnel or funding, must be approved by COUHES before they can be initiated.">
														<styles font-family="Arial" font-size="10pt"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<styles font-family="Arial" font-size="12pt"/>
												<children>
													<text fixtext="Prospecitve new study personnel must, where applicable, complete training in human subjects research and in the HIPAA Privacy Rule before participating in the study.">
														<styles font-family="Arial" font-size="10pt"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<styles font-family="Arial" font-size="10pt"/>
												<children>
													<text fixtext="COUHES should be notified when your study is completed.  You must maintain a research file for at least 3 years after completion of the study.  This file should include all correspondence with COUHES, original signed consent forms, and study data.">
														<styles font-family="Arial" font-size="12 pt"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<newline/>
													<text fixtext="cc:   Tom Duff">
														<styles font-family="Arial" font-size="10pt"/>
													</text>
													<newline/>
													<template match="n1:Protocol" matchtype="schemagraphitem">
														<children>
															<template match="n1:Correspondent" matchtype="schemagraphitem">
																<children>
																	<table>
																		<properties border="0"/>
																		<styles border-collapse="0"/>
																		<children>
																			<tablebody>
																				<children>
																					<template match="n1:Person" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<properties align="left"/>
																								<children>
																									<tablecell/>
																								</children>
																							</tablerow>
																							<tablerow>
																								<properties align="left"/>
																								<children>
																									<tablecell>
																										<children>
																											<text fixtext="       ">
																												<styles font-size="10pt"/>
																											</text>
																											<template match="n1:Fullname" matchtype="schemagraphitem">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="../../n1:TypeOfCorrespondent =&apos;CRC&apos;">
																																<children>
																																	<content>
																																		<styles font-family="Arial" font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</conditionbranch>
																														</children>
																													</condition>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</template>
																				</children>
																			</tablebody>
																		</children>
																	</table>
																</children>
															</template>
														</children>
													</template>
													<newline/>
													<template match="n1:Protocol" matchtype="schemagraphitem">
														<children>
															<template match="n1:Correspondent" matchtype="schemagraphitem">
																<children>
																	<template match="n1:Person" matchtype="schemagraphitem"/>
																</children>
															</template>
														</children>
													</template>
												</children>
											</paragraph>
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
