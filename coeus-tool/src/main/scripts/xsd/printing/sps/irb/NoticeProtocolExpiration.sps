<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="irb.xsd" workingxmlfile="C:\testfop\protocolReminder2.xml">
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
							<template match="n1:RenewalReminder" matchtype="schemagraphitem">
								<children>
									<paragraph paragraphtag="h2">
										<properties align="center"/>
										<children>
											<text fixtext="Massachusetts Institute of Technology">
												<styles font-weight="bold"/>
											</text>
										</children>
									</paragraph>
									<paragraph paragraphtag="h2">
										<properties align="center"/>
										<children>
											<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
												<children>
													<template match="n1:CommitteeName" matchtype="schemagraphitem">
														<children>
															<paragraph paragraphtag="h3">
																<children>
																	<content>
																		<styles font-weight="bold"/>
																	</content>
																</children>
															</paragraph>
														</children>
													</template>
												</children>
											</template>
										</children>
									</paragraph>
									<table>
										<properties border="0"/>
										<children>
											<tablebody>
												<children>
													<tablerow>
														<children>
															<tablecell>
																<properties align="right" height="15" valign="top" width="46"/>
																<children>
																	<text fixtext="To:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" width="290"/>
																<children>
																	<template match="n1:Protocol" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:Investigator" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:Person" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:Fullname" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../n1:PI_flag =&apos;true&apos;">
																												<children>
																													<content/>
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
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" height="15" valign="top" width="112"/>
																<children>
																	<text fixtext="Date:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" width="159"/>
																<children>
																	<template match="n1:CurrentDate" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<format string="MM / DD / YYYY"/>
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
																<properties align="right" height="15" valign="top" width="46"/>
																<children>
																	<text fixtext="From:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" valign="top" width="290"/>
																<children>
																	<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:CommitteeName" matchtype="schemagraphitem">
																				<children>
																					<content/>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" height="15" valign="top" width="112"/>
																<children>
																	<text fixtext="Expiration Date:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" width="159"/>
																<children>
																	<template match="n1:Protocol" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ExpirationDate" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<format string="MM / DD / YYYY"/>
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
																<properties align="right" height="15" valign="top" width="46"/>
																<children>
																	<text fixtext="Re: ">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" valign="top" width="290"/>
																<children>
																	<text fixtext="Protocol# ">
																		<styles font-weight="bold"/>
																	</text>
																	<template match="n1:Protocol" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ProtocolNumber" matchtype="schemagraphitem">
																						<children>
																							<content/>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" width="112"/>
															</tablecell>
															<tablecell>
																<properties height="15" width="159"/>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties align="right" valign="top" width="46"/>
																<children>
																	<text fixtext="Title:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties colspan="3" valign="top" width="628"/>
																<children>
																	<template match="n1:Protocol" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ProtocolTitle" matchtype="schemagraphitem">
																						<children>
																							<content/>
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
									<newline/>
									<paragraph>
										<children>
											<text fixtext="Please be advised that the above referenced protocol has lapsed as noted by the expiration date above.  It is a violation of [name of institution] policy and federal regulations to continue research activities including data analysis after the approval period has expired.  Since the HIC has not reviewed and re-approved this research by the anniversary date, all enrollment, research activities, intervention and data analysis on previously enrolled subjects must stop.  If you believe that the health or welfare of the subjects will be jeopardized if a study treatment is discontinued, you may submit a written request to the "/>
											<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
												<children>
													<template match="n1:CommitteeName" matchtype="schemagraphitem">
														<children>
															<content/>
														</children>
													</template>
												</children>
											</template>
											<text fixtext=" with justification for continued treatment with currently enrolled subjects.  IRB approval of this request is required."/>
											<newline/>
											<text fixtext="Please be aware that all protocols involving human subjects must be reviewed at least annually or more frequently as determined by the IRB.  The principal investigator is responsible for ensuring that research studies are re-approved on or before the expiration date."/>
											<newline/>
											<text fixtext="The level of scrutiny given to the continuing review process is the same as that of any new protocol. All requests for re-approval must be reviewed at a convened IRB meeting, except for those protocols that meet the criteria for expedited review."/>
											<newline/>
											<text fixtext="To request re-approval of this research study, please submit the following documents:"/>
											<newline/>
											<table>
												<properties border="0"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="21"/>
																		<children>
																			<text fixtext="1."/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="500"/>
																		<children>
																			<text fixtext="Two (2) copies of the [Enter name of local form used to request reapproval]. You can obtain a copy of the form at our web site [note URL]."/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="21"/>
																		<children>
																			<text fixtext="2."/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="500"/>
																		<children>
																			<text fixtext="Two (2) copies of each consent form(s) used in the study [with the validation box on the last page blank to allow revalidation.]"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="21"/>
																		<children>
																			<text fixtext="3."/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="500"/>
																		<children>
																			<text fixtext="A current copy of the protocol, inclusive of all amendments and revisions, which will serve as the IRB file copy."/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Please be advised that the above-referenced research project will be considered closed by the IRB if no request for re-approval is received within xxxxx days.  You will be required to re-submit this protocol as a new submission along with a [Enter name of New Protocol form] should you wish to re-open the study."/>
													<newline/>
													<paragraph paragraphtag="p">
														<children>
															<text fixtext="Termination of this study requires communication to the IRB.  In that case, please provide a summary of your experience with this project, using "/>
															<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																<children>
																	<template match="n1:CommitteeName" matchtype="schemagraphitem">
																		<children>
																			<content/>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=".  The summary should include the following:"/>
														</children>
													</paragraph>
													<table>
														<properties border="0"/>
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="32"/>
																				<children>
																					<text fixtext="a)"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="377"/>
																				<children>
																					<text fixtext="the total number of number of subjects studied:"/>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="32"/>
																				<children>
																					<text fixtext="b)"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="377"/>
																				<children>
																					<text fixtext="a copy of the study publication or summary"/>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
	</pagelayout>
	<designfragments/>
</structure>
