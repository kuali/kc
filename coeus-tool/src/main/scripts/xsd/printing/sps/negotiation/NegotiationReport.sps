<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="negotiation.xsd" workingxmlfile="C:\coeus40_vss\Reports\NnegotiationActivity01040669$08082005-075931$.xml">
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
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Times New Roman" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center"/>
														<children>
															<text fixtext="Negotiation Report">
																<styles font-family="Times New Roman" font-size="15pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<template match="negotiations" matchtype="schemagraphitem">
																<children>
																	<table>
																		<properties border="0"/>
																		<children>
																			<tablebody>
																				<children>
																					<template match="negotiationData" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties colspan="6" width="20%"/>
																										<children>
																											<line>
																												<properties color="black" size="1"/>
																											</line>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<text fixtext="Proposal No:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="2" width="12.5%"/>
																										<children>
																											<template match="proposalNumber" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="20%"/>
																										<children>
																											<text fixtext="Investigator: ">
																												<styles font-weight="bold"/>
																											</text>
																											<template match="investigator" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<text fixtext="Sponsor:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="20%"/>
																										<children>
																											<template match="sponsor" matchtype="schemagraphitem">
																												<children>
																													<template match="sponsorCode" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																													<template match="sponsorName" matchtype="schemagraphitem">
																														<children>
																															<text fixtext=" : "/>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="2" width="13%"/>
																										<styles padding-left="0" padding-right="0"/>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<text fixtext="Tiele:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="5" width="20%"/>
																										<children>
																											<template match="title" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<text fixtext="Lead Unit:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="5" width="20%"/>
																										<children>
																											<template match="leadUnit" matchtype="schemagraphitem">
																												<children>
																													<template match="unitNumber" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																													<template match="unitName" matchtype="schemagraphitem">
																														<children>
																															<text fixtext=" : "/>
																															<content>
																																<format datatype="string"/>
																															</content>
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
																										<properties width="15%"/>
																										<children>
																											<text fixtext="Proposal Type:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="2" width="12.5%"/>
																										<children>
																											<template match="proposalType" matchtype="schemagraphitem">
																												<children>
																													<template match="proposalTypeDesc" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="20%"/>
																										<children>
																											<text fixtext="Start Date: ">
																												<styles font-weight="bold"/>
																											</text>
																											<template match="startDate" matchtype="schemagraphitem">
																												<children>
																													<content>
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
																										<properties width="15%"/>
																										<children>
																											<text fixtext="Negotiator:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="2" width="12.5%"/>
																										<children>
																											<template match="negotiator" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="20%"/>
																										<children>
																											<text fixtext="Status:  ">
																												<styles font-weight="bold"/>
																											</text>
																											<template match="status" matchtype="schemagraphitem">
																												<children>
																													<template match="statusDesc" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
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
																										<properties width="15%"/>
																										<styles padding-right="0"/>
																										<children>
																											<text fixtext="Doc File Address:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="20%"/>
																										<children>
																											<template match="docFileAddress" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="20%"/>
																									</tablecell>
																									<tablecell>
																										<properties width="20%"/>
																									</tablecell>
																									<tablecell>
																										<properties colspan="2" width="13%"/>
																										<styles padding-left="0" padding-right="0"/>
																										<children>
																											<text fixtext="Contract Admin:  ">
																												<styles font-weight="bold"/>
																											</text>
																											<template match="contractAdmin" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties colspan="6" width="20%"/>
																										<children>
																											<paragraph paragraphtag="p">
																												<children>
																													<text fixtext="Most Recent Activity">
																														<styles font-size="10pt" font-weight="bold"/>
																													</text>
																												</children>
																											</paragraph>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<text fixtext="Activity Date:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="25%"/>
																										<children>
																											<template match="activities" matchtype="schemagraphitem">
																												<children>
																													<template match="activityDate" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format string="MM/DD/YYYY" datatype="date"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="12.5%"/>
																										<children>
																											<text fixtext="Create Date:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<template match="activities" matchtype="schemagraphitem">
																												<children>
																													<template match="createDate" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format string="MM/DD/YYYY" datatype="date"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="13%"/>
																										<styles padding-left="0"/>
																										<children>
																											<text fixtext="Followup Date:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="20%"/>
																										<styles padding-left="0" padding-right="0"/>
																										<children>
																											<template match="activities" matchtype="schemagraphitem">
																												<children>
																													<template match="followupDate" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format string="MM/DD/YYYY" datatype="date"/>
																															</content>
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
																										<properties width="15%"/>
																										<children>
																											<text fixtext="Activity Type: ">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="25%"/>
																										<children>
																											<template match="activities" matchtype="schemagraphitem">
																												<children>
																													<template match="activity" matchtype="schemagraphitem">
																														<children>
																															<template match="activityDesc" matchtype="schemagraphitem">
																																<children>
																																	<content>
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
																									<tablecell>
																										<properties width="12.5%"/>
																										<children>
																											<text fixtext="Last Update:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<template match="activities" matchtype="schemagraphitem">
																												<children>
																													<template match="lastDate" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format string="MM/DD/YYYY" datatype="date"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="13%"/>
																										<styles padding-left="0"/>
																										<children>
																											<text fixtext="Updated By:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="20%"/>
																										<styles padding-left="0" padding-right="0"/>
																										<children>
																											<template match="activities" matchtype="schemagraphitem">
																												<children>
																													<template match="updatedBy" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
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
																										<properties width="15%"/>
																										<children>
																											<text fixtext="Description:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="25%"/>
																									</tablecell>
																									<tablecell>
																										<properties width="12.5%"/>
																									</tablecell>
																									<tablecell>
																										<properties width="15%"/>
																									</tablecell>
																									<tablecell>
																										<properties width="13%"/>
																									</tablecell>
																									<tablecell>
																										<properties width="20%"/>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties colspan="6" width="20%"/>
																										<styles padding-left="6pt"/>
																										<children>
																											<paragraph paragraphtag="pre">
																												<styles padding-left="6pt"/>
																												<children>
																													<template match="activities" matchtype="schemagraphitem">
																														<children>
																															<template match="description" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</paragraph>
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
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.65in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.65in" paperwidth="8.5in"/>
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
														<properties colspan="2" height="1"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2" height="1"/>
														<styles padding="0"/>
														<children>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left"/>
														<styles font-size="8pt" padding="0"/>
														<children>
															<text fixtext=" ">
																<styles font-family="Times New Roman" font-size="8pt"/>
															</text>
															<autocalc xpath="negotiations/negotiationData/currentDate">
																<styles font-family="Times New Roman" font-size="8pt"/>
																<format string="MM/DD/YYYY" datatype="date"/>
															</autocalc>
															<text fixtext="       ">
																<styles font-family="Times New Roman" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles padding="0"/>
														<children>
															<text fixtext="Page: ">
																<styles font-family="Times New Roman" font-size="8pt"/>
															</text>
															<field>
																<styles font-family="Times New Roman" font-size="8pt"/>
															</field>
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
