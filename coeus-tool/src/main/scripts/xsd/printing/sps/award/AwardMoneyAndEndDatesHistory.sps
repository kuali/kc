<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="AwardNotice.xsd" workingxmlfile="C:\COEUS40_VSS\Reports\MoneyAndEndDatesHistory000842-001$01182005-024939$.xml">
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
					<newline/>
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="5"/>
						<children>
							<template match="AwardNotice" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="5"/>
								<children>
									<template match="MoneyHistoryReport" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<newline/>
											<text fixtext="Award Sequence : ">
												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
											</text>
											<template match="MoneyHistorySeq" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<content>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<format datatype="int"/>
													</content>
												</children>
											</template>
											<newline/>
											<template match="MoneyHistoryInfo" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<newline/>
													<table>
														<properties border="0" width="100%"/>
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<styles font-family="Arial" font-size="9pt"/>
																		<children>
																			<tablecell>
																				<properties height="20"/>
																				<children>
																					<template match="TreeLevel" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles padding-bottom="20pt"/>
																								<format datatype="int"/>
																							</content>
																							<text fixtext=" ">
																								<styles font-family="Arial" font-size="9pt"/>
																							</text>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20"/>
																				<styles padding="0"/>
																				<children>
																					<template match="ObligatedChangeDirect" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<text fixtext="$"/>
																							<content>
																								<format string="#,###,###,##0.00" datatype="decimal"/>
																							</content>
																							<newline/>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20"/>
																				<styles padding="0"/>
																				<children>
																					<template match="ObligatedChangeIndirect" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<text fixtext="$"/>
																							<content>
																								<format string="#,###,###,##0.00" datatype="decimal"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20"/>
																				<styles padding="0"/>
																				<children>
																					<template match="ObligatedChange" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<text fixtext="$"/>
																							<content>
																								<format string="#,###,###,##0.00" datatype="decimal"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20"/>
																				<styles padding="0"/>
																				<children>
																					<template match="AmtObligatedToDate" matchtype="schemagraphitem">
																						<children>
																							<text fixtext="$"/>
																							<content>
																								<format string="#,###,###,##0.00" datatype="decimal"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20"/>
																				<styles overflow="hidden" padding="0" padding-left="5pt"/>
																				<children>
																					<template match="ObligatedDistributableAmt" matchtype="schemagraphitem">
																						<children>
																							<text fixtext="$"/>
																							<content>
																								<format string="#,###,###,##0.00" datatype="decimal"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20"/>
																				<styles overflow="hidden" padding="0" padding-left="10pt"/>
																				<children>
																					<template match="AnticipatedChangeDirect" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<text fixtext="$"/>
																							<content>
																								<format string="#,###,###,##0.00" datatype="decimal"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20"/>
																				<styles padding="0" padding-left="5pt"/>
																				<children>
																					<template match="AnticipatedChangeIndirect" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<text fixtext="$"/>
																							<content>
																								<format string="#,###,###,##0.00" datatype="decimal"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20"/>
																				<styles overflow="hidden" padding="0" padding-left="5pt"/>
																				<children>
																					<template match="AnticipatedChange" matchtype="schemagraphitem">
																						<children>
																							<text fixtext="$"/>
																							<content>
																								<format string="#,###,###,##0.00" datatype="decimal"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20"/>
																				<styles padding="0" padding-left="10pt"/>
																				<children>
																					<template match="AnticipatedTotalAmt" matchtype="schemagraphitem">
																						<children>
																							<text fixtext="$"/>
																							<content>
																								<format string="#,###,###,##0.00" datatype="decimal"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20"/>
																				<styles padding="0" padding-left="15pt"/>
																				<children>
																					<template match="AnticipatedDistributableAmt" matchtype="schemagraphitem">
																						<children>
																							<text fixtext="$"/>
																							<content>
																								<format string="#,###,###,##0.00" datatype="decimal"/>
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
													<table>
														<properties border="0" width="100%"/>
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties height="20" width="15%"/>
																				<styles padding="0"/>
																				<children>
																					<text fixtext="Oblg. Eff : ">
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																					</text>
																					<template match="CurrentFundEffectiveDate" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Arial" font-size="9pt"/>
																								<format string="MM/DD/YYYY" datatype="date"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20" width="15%"/>
																				<styles padding="0"/>
																				<children>
																					<text fixtext="Oblg. Exp :  ">
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																					</text>
																					<template match="ObligationExpirationDate" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Arial" font-size="9pt"/>
																								<format string="MM/DD/YYYY" datatype="date"/>
																							</content>
																						</children>
																					</template>
																					<newline/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20" width="15%"/>
																				<styles font-weight="bold" overflow="visible" padding="0"/>
																				<children>
																					<text fixtext="Final Exp :  ">
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																					</text>
																					<template match="FinalExpirationDate" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																								<format string="MM/DD/YYYY" datatype="date"/>
																							</content>
																						</children>
																					</template>
																					<text fixtext="  ">
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																					</text>
																					<text fixtext=" "/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20" width="15%"/>
																				<styles font-weight="bold" overflow="visible" padding="0"/>
																			</tablecell>
																			<tablecell/>
																			<tablecell/>
																			<tablecell/>
																			<tablecell/>
																			<tablecell/>
																			<tablecell/>
																		</children>
																	</tablerow>
																</children>
															</tablebody>
														</children>
													</table>
												</children>
											</template>
											<newline/>
										</children>
									</template>
								</children>
							</template>
						</children>
					</template>
					<newline/>
					<newline/>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="landscape" paperheight="8.5in" papermarginbottom="0.2in" papermarginleft="0.2in" papermarginright="0.65in" papermargintop="1.65in" paperwidth="11in"/>
		<children>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<newline/>
							<table>
								<properties border="0" width="100%"/>
								<styles overflow="visible"/>
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
															<newline/>
															<newline/>
															<text fixtext="coeus: "/>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<children>
																	<template match="PrintRequirement" matchtype="schemagraphitem">
																		<children>
																			<template match="CurrentDate" matchtype="schemagraphitem">
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
															<text fixtext=" "/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="150"/>
														<styles font-family="Verdana" font-size="7pt" padding="0"/>
														<children>
															<newline/>
															<newline/>
															<text fixtext="Page  "/>
															<field/>
															<text fixtext="       "/>
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
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<table>
						<properties width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecell>
												<properties colspan="2" height="30" width="100%"/>
												<styles padding="0"/>
												<children>
													<table>
														<properties border="0" width="100%"/>
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties align="left" colspan="11" valign="top"/>
																				<styles font-family="Verdana" font-size="9pt" padding-bottom="0"/>
																				<children>
																					<paragraph paragraphtag="p">
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<children>
																									<template match="SchoolInfo" matchtype="schemagraphitem">
																										<children>
																											<template match="SchoolName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Verdana" font-size="13pt" font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
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
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties align="left" colspan="11" valign="top"/>
																				<styles font-family="Verdana" font-size="9pt" padding-bottom="0"/>
																				<children>
																					<text fixtext="Money and End Dates History for award-">
																						<styles font-family="Verdana" font-size="10pt" font-style="italic" font-weight="bold"/>
																					</text>
																					<template match="AwardNotice" matchtype="schemagraphitem">
																						<children>
																							<template match="Award" matchtype="schemagraphitem">
																								<children>
																									<template match="AwardDetails" matchtype="schemagraphitem">
																										<children>
																											<template match="AwardHeader" matchtype="schemagraphitem">
																												<children>
																													<template match="AwardNumber" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<styles font-style="italic"/>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
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
								</children>
							</tablebody>
						</children>
					</table>
					<newline/>
					<table>
						<properties border="0" width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
										<children>
											<tablecell/>
											<tablecell/>
											<tablecell/>
											<tablecell>
												<properties colspan="3"/>
												<children>
													<text fixtext="Obligated Amount"/>
												</children>
											</tablecell>
											<tablecell/>
											<tablecell/>
											<tablecell>
												<properties colspan="3"/>
												<children>
													<text fixtext="Anticipated Amount"/>
												</children>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
										<children>
											<tablecell/>
											<tablecell>
												<children>
													<text fixtext="Direct"/>
												</children>
											</tablecell>
											<tablecell>
												<children>
													<text fixtext="Indirect"/>
												</children>
											</tablecell>
											<tablecell>
												<children>
													<text fixtext="Change"/>
												</children>
											</tablecell>
											<tablecell>
												<children>
													<text fixtext="Total"/>
												</children>
											</tablecell>
											<tablecell>
												<children>
													<text fixtext="Distributable"/>
												</children>
											</tablecell>
											<tablecell>
												<children>
													<text fixtext="Direct"/>
												</children>
											</tablecell>
											<tablecell>
												<children>
													<text fixtext="Indirect"/>
												</children>
											</tablecell>
											<tablecell>
												<children>
													<text fixtext="change"/>
												</children>
											</tablecell>
											<tablecell>
												<children>
													<text fixtext="Total"/>
												</children>
											</tablecell>
											<tablecell>
												<children>
													<text fixtext="Distributable"/>
												</children>
											</tablecell>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
					<newline/>
					<table>
						<properties border="0" width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecell>
												<styles padding="0"/>
												<children>
													<line>
														<properties color="black" size="1"/>
													</line>
												</children>
											</tablecell>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
