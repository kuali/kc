<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="irb.xsd" workingxmlfile="Y:\ele\samples\renewalReminder.xml">
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
																				<format string="MM/DD/YYYY"/>
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
																	<text fixtext="The approval of your project by the "/>
																	<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:CommitteeName" matchtype="schemagraphitem">
																				<children>
																					<content/>
																				</children>
																			</template>
																		</children>
																	</template>
																	<text fixtext=" will expire on "/>
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
																	<text fixtext=".  Before the Committee extends its approval for an additional period (usually one year), you must complete and return a Continuing Review Questionnaire (CRQ) for this project before the expiration date of your approval.  If the CRQ is not received by COUHES by the required date, the study will be automatically administratively closed and research grants related to the study will be suspended.  No further research with human subjects can be conducted under this protocol.  Please go to http://web.mit.edu/committees/couhes/forms.shtml to download the CRQ."/>
																	<newline/>
																	<newline/>
																	<text fixtext="COUHES requires that MIT consent forms follow the template on the website.  You must transfer the information from the currently approved MIT consent form/s to either the &quot;Consent to Participate in Biomedical Research&quot; or &quot;Consent to Participate in Non-Biomedical Research&quot; (whichever applies to your project) and submit with your CRQ."/>
																	<newline/>
																	<newline/>
																	<text fixtext="As of July 1, 2003, all personnel involved in Human Subjects Research must complete the human subjects training course.  Please make sure all personnel associated with this study have completed the human subjects training course (go to the COUHES website for a link to the Human Subjects Training Course)."/>
																	<newline/>
																	<newline/>
																	<text fixtext="If you have any questions, please contact the COUHES office."/>
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
