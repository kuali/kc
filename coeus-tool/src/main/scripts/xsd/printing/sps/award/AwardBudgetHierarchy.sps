<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="AwardNotice.xsd" workingxmlfile="C:\COEUS40_VSS\Reports\BudgetHierarchy000842-001$01112005-070550$.xml">
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
							<template match="AwardNotice" matchtype="schemagraphitem">
								<children>
									<template match="Award" matchtype="schemagraphitem">
										<children>
											<template match="AwardAmountInfo" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="0"/>
														<styles font-family="Times New Roman" font-size="8pt" line-height="9pt" padding-left="80pt"/>
														<children>
															<tablebody>
																<styles padding-left="80pt"/>
																<children>
																	<template match="AmountInfo" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<styles padding-left="80pt"/>
																				<children>
																					<tablecell>
																						<properties height="20"/>
																						<styles padding-right="10pt"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="TreeLevel  =  2">
																										<children>
																											<text fixtext="    "/>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="TreeLevel  =  3">
																										<children>
																											<text fixtext="        "/>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="TreeLevel  =  4">
																										<children>
																											<text fixtext="            "/>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="TreeLevel  =  5">
																										<children>
																											<text fixtext="                "/>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="TreeLevel  &gt; 5">
																										<children>
																											<text fixtext="                    "/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<text fixtext="["/>
																							<template match="TreeLevel" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="int"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext="] "/>
																							<template match="AwardNumber" matchtype="schemagraphitem">
																								<children>
																									<text fixtext="  "/>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=" :"/>
																							<template match="AccountNumber" matchtype="schemagraphitem">
																								<children>
																									<text fixtext=" "/>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties height="20"/>
																						<children>
																							<template match="ObligatedTotalDirect" matchtype="schemagraphitem">
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
																						<children>
																							<template match="ObligatedTotalIndirect" matchtype="schemagraphitem">
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
																						<children>
																							<template match="AnticipatedTotalDirect" matchtype="schemagraphitem">
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
																						<children>
																							<template match="AnticipatedTotalIndirect" matchtype="schemagraphitem">
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
																					<tablecell>
																						<properties height="20"/>
																						<children>
																							<template match="CurrentFundEffectiveDate" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties height="20"/>
																						<children>
																							<template match="ObligationExpirationDate" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties height="20"/>
																						<children>
																							<template match="FinalExpirationDate" matchtype="schemagraphitem">
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
							</template>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="8.5in" papermarginbottom="0.65in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="1.5in" paperwidth="11in"/>
		<children>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<newline/>
							<newline/>
							<newline/>
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
														<properties align="left"/>
														<styles font-family="Verdana" font-size="7pt" padding="0"/>
														<children>
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" width="150"/>
														<styles font-family="Verdana" font-size="7pt" padding="0"/>
														<children>
															<text fixtext="Page  "/>
															<field/>
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
												<properties colspan="2" height="30"/>
												<styles padding="0"/>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties align="left" colspan="2" valign="top" width="1055"/>
												<styles font-family="Verdana" font-size="9pt" padding-bottom="0"/>
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
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties align="left" valign="top" width="1055"/>
												<styles font-family="Verdana" font-size="9pt" padding-bottom="0"/>
												<children>
													<text fixtext="Budget Hierarchy">
														<styles font-family="Verdana" font-size="10pt" font-style="italic" font-weight="bold"/>
													</text>
												</children>
											</tablecell>
											<tablecell>
												<properties align="right"/>
												<styles font-size="10pt" padding="0"/>
											</tablecell>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
					<table>
						<properties border="0" width="100%"/>
						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecell/>
											<tablecell/>
											<tablecell/>
											<tablecell>
												<properties colspan="2"/>
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
											<tablecell/>
											<tablecell/>
										</children>
									</tablerow>
									<tablerow>
										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
										<children>
											<tablecell>
												<properties align="left"/>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Direct"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Indirect"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Total"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Distributable"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Direct"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Indirect"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Total"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Distributable"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Oblg. Eff"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Oblg. Exp"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<children>
													<text fixtext="Final Exp"/>
												</children>
											</tablecell>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
					<newline/>
					<line>
						<properties color="black" size="1"/>
					</line>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
