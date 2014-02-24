<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="D:\StyleVisionFiles\Correspondent templates\xmlpyproject\irb.xsd" workingxmlfile="C:\testfop\protocolReminder2.xml">
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
																<properties align="right" height="15" valign="top" width="43"/>
																<children>
																	<text fixtext="To:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" valign="top" width="281"/>
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
																<properties align="right" height="15" valign="top" width="100"/>
																<children>
																	<text fixtext="Date:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" width="99"/>
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
																<properties align="right" height="15" valign="top" width="43"/>
																<children>
																	<text fixtext="From:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" valign="top" width="281"/>
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
																<properties align="right" height="15" valign="top" width="100"/>
																<children>
																	<text fixtext="Expiration Date:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" valign="top" width="99"/>
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
																<properties align="right" height="15" valign="top" width="43"/>
																<children>
																	<text fixtext="Re:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties colspan="3" height="15" valign="top" width="292"/>
																<children>
																	<text fixtext="Protocol #  ">
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
														</children>
													</tablerow>
												</children>
											</tablebody>
										</children>
									</table>
									<paragraph>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<paragraph paragraphtag="p">
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="This letter serves as a reminder that the above-cited protocol is due for re-approval by the "/>
																	<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:CommitteeName" matchtype="schemagraphitem">
																				<children>
																					<content/>
																				</children>
																			</template>
																		</children>
																	</template>
																	<text fixtext=".  It is the primary responsibility of the Principal Investigator to ensure that the re-approval status for lapsed protocols is achieved.  All protocols must be re-approved annually by the IRB unless shorter intervals have been specified."/>
																	<newline/>
																	<text fixtext="Please note that the level of scrutiny given to the continuing review process is the same as that of any new protocol.  All requests for re-approval must be reviewed at a convened IRB meeting, except for those protocols that meet the criteria for expedited review."/>
																	<newline/>
																	<text fixtext="Please submit the following documents at least [Enter preferred submission duration, i.e., two (2) months] prior to the expiration date to allow for full committee review"/>
																	<paragraph paragraphtag="p">
																		<children>
																			<table>
																				<properties border="0"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="25"/>
																										<children>
																											<text fixtext="1)"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="469"/>
																										<children>
																											<text fixtext="Two (2) copies of the [Enter name of required IRB form]"/>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="25"/>
																										<children>
																											<text fixtext="2)"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="469"/>
																										<children>
																											<text fixtext="Two (2) copies of each consent form(s) used in the study [with the validation box on the last page blank to allow for revalidation.]"/>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="25"/>
																										<children>
																											<text fixtext="3)"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="469"/>
																										<children>
																											<text fixtext="A current copy of the protocol, inclusive of all amendments and revisions, which will serve as an IRB file copy."/>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																			<newline/>
																			<text fixtext="It is a violation of "/>
																			<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:HomeUnitName" matchtype="schemagraphitem">
																						<children>
																							<content/>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext=" policy and federal regulations to continue research activities after the approval period has expired.  If the IRB has not reviewed and re-approved this research by its current expiration date, all enrollment, research activities and intervention on previously enrolled subjects must stop.  If you believe that the health and welfare of the subjects will be jeopardized if the study treatment is discontinued, you may submit a written request to the IRB to continue treatment activities with currently enrolled subjects."/>
																			<paragraph paragraphtag="p">
																				<children>
																					<paragraph paragraphtag="p">
																						<children>
																							<paragraph paragraphtag="p">
																								<children>
																									<text fixtext="If this study has been terminated, it does not require IRB re-approval.  In that case, please enter the date of termination here_______________ and provide a summary of your experience with this project, also using [Enter name of required form].  The summary should include the following"/>
																									<newline/>
																									<table>
																										<properties border="0"/>
																										<children>
																											<tablebody>
																												<children>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties width="25"/>
																																<children>
																																	<text fixtext="a)"/>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties width="472"/>
																																<children>
																																	<text fixtext="the total number of subjects studied;"/>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties width="25"/>
																																<children>
																																	<text fixtext="b)"/>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties width="472"/>
																																<children>
																																	<text fixtext="a brief statement of the results of the study;"/>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties width="25"/>
																																<children>
																																	<text fixtext="c)"/>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties width="472"/>
																																<children>
																																	<text fixtext="a report of adverse events; and,"/>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties width="25"/>
																																<children>
																																	<text fixtext="d)"/>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties width="472"/>
																																<children>
																																	<text fixtext="a statement of any continuing obligations to subjects."/>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																												</children>
																											</tablebody>
																										</children>
																									</table>
																									<text fixtext="
"/>
																									<paragraph paragraphtag="p">
																										<children>
																											<paragraph paragraphtag="p">
																												<children>
																													<paragraph paragraphtag="p">
																														<children>
																															<text fixtext="Please note that you can obtain a copy of [Enter name of required form] through our web site: [Enter name of URL]"/>
																															<newline/>
																															<text fixtext="Your assistance and cooperation in ensuring that the above- mentioned protocol is received for re-approval evaluation at the IRB Office at [Enter preferred submission duration, i.e., two (2) months] before the lapse date is greatly appreciated."/>
																														</children>
																													</paragraph>
																												</children>
																											</paragraph>
																										</children>
																									</paragraph>
																								</children>
																							</paragraph>
																						</children>
																					</paragraph>
																				</children>
																			</paragraph>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
														</children>
													</paragraph>
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
