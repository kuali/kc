<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="awardReportingRequirements.xsd" workingxmlfile="AwardReportingRequirements.xml">
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
							<newline/>
							<template match="awardReportingRequirement" matchtype="schemagraphitem">
								<children>
									<newline/>
									<table>
										<properties border="0"/>
										<styles font-family="Times New Roman" font-size="8pt"/>
										<children>
											<tablebody>
												<children>
													<template match="reportingReqs" matchtype="schemagraphitem">
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="2" valign="top" width="70%"/>
																		<children>
																			<text fixtext="Coeus-Award Reporting">
																				<styles font-size="13pt" font-weight="bold"/>
																			</text>
																			<newline/>
																			<text fixtext="Reporting Requirements for">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																			<newline/>
																			<table>
																				<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<text fixtext="Principle Investigator">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<text fixtext="Report Class">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<text fixtext="Report Type">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<text fixtext="Frequency">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<text fixtext="Frequency Base">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="10%"/>
																										<children>
																											<text fixtext="Base Date">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<text fixtext="Copy OSP">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<template match="principleInvestigatorName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<template match="reportClass" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<template match="reportType" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<template match="frequency" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<template match="frequencyBase" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="10%"/>
																										<children>
																											<template match="baseDate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<template match="copyOSP" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
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
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="2" valign="top" width="30%"/>
																		<children>
																			<text fixtext="Reporting Requirement Details">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																			<newline/>
																			<table>
																				<properties border="1" cellpadding="0" cellspacing="0"/>
																				<children>
																					<tableheader>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="6%"/>
																										<children>
																											<text fixtext="Award No.">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="5%"/>
																										<children>
																											<text fixtext="Unit No.">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="8%"/>
																										<children>
																											<text fixtext="Unit Name">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="8%"/>
																										<children>
																											<text fixtext="Status">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="7%"/>
																										<children>
																											<text fixtext="Due Date">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="15%"/>
																										<children>
																											<text fixtext="Contact">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="20%"/>
																										<children>
																											<text fixtext="Address">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="4%"/>
																										<children>
																											<text fixtext="Copies">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="6%"/>
																										<children>
																											<text fixtext="Overdue #">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="7%"/>
																										<children>
																											<text fixtext="Activity Date">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="6%"/>
																										<children>
																											<text fixtext="Comments">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="8%"/>
																										<children>
																											<text fixtext="Person Name">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</tableheader>
																					<tablebody>
																						<children>
																							<template match="reportingReqDetails" matchtype="schemagraphitem">
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties valign="top" width="6%"/>
																												<children>
																													<template match="awardNo" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="5%"/>
																												<children>
																													<template match="unitNo" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="8%"/>
																												<children>
																													<template match="unitName" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="8%"/>
																												<children>
																													<template match="status" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="7%"/>
																												<children>
																													<template match="dueDate" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="15%"/>
																												<children>
																													<template match="contact" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="20%"/>
																												<children>
																													<template match="address" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="4%"/>
																												<children>
																													<template match="copies" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="int"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="6%"/>
																												<children>
																													<template match="overdueNo" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="int"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="7%"/>
																												<children>
																													<template match="activityDate" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="6%"/>
																												<children>
																													<template match="comments" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="8%"/>
																												<children>
																													<template match="personName" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
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
																			<condition>
																				<children>
																					<conditionbranch xpath="position() != last()">
																						<children>
																							<newline break="page"/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
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
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="8.5in" papermarginbottom="0.3in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="0.2in" paperwidth="11in"/>
		<children>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties border="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top"/>
														<styles font-family="Verdana" font-size="7pt" padding="0"/>
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="Date: "/>
																	<template match="awardReportingRequirement" matchtype="schemagraphitem">
																		<children>
																			<template match="currentDate" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="date"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																	<text fixtext=" "/>
																</children>
															</paragraph>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="150"/>
														<styles font-family="Verdana" font-size="7pt" padding="0"/>
														<children>
															<text fixtext="Page "/>
															<field/>
															<text fixtext="                           "/>
														</children>
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
