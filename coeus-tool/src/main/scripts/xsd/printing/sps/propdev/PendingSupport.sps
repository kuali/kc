<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="MM / DD / YYYY" datatype="date"/>
	</predefinedformats>
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="CurrentPendingSupport.xsd">
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
							<text fixtext="Pending Support for ">
								<styles font-size="8" font-weight="bold" text-decoration="underline"/>
							</text>
							<template match="CurrentAndPendingSupport" matchtype="schemagraphitem">
								<children>
									<template match="PersonName" matchtype="schemagraphitem">
										<children>
											<content>
												<styles font-size="8" font-weight="bold" text-decoration="underline"/>
												<format datatype="string"/>
											</content>
										</children>
									</template>
									<newline/>
								</children>
							</template>
							<newline/>
							<paragraph paragraphtag="p"/>
							<table>
								<properties border="1" cellpadding="0" cellspacing="0"/>
								<styles font-family="Arial" font-size="8pt"/>
								<children>
									<tableheader>
										<children>
											<tablerow>
												<properties align="center"/>
												<styles font-weight="bold"/>
												<children>
													<tablecell headercell="1">
														<properties align="center" width="5%"/>
														<children>
															<text fixtext="Proposal No"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="12%"/>
														<children>
															<text fixtext="Agency"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="5%"/>
														<children>
															<text fixtext="PI/Key-Per"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="12%"/>
														<children>
															<text fixtext="Title"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="8%"/>
														<children>
															<text fixtext="Total Requested"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="7%"/>
														<children>
															<text fixtext="Eff. Date"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="7%"/>
														<children>
															<text fixtext="End Date"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="5%"/>
														<children>
															<text fixtext="Effort%"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="5%"/>
														<children>
															<text fixtext="Academic Year Effort%"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="5%"/>
														<children>
															<text fixtext="Summer Year Effort%"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="5%"/>
														<children>
															<text fixtext="Calendar Year Effort%"/>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="8%"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="CurrentAndPendingSupport" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PendingReportCEColumnNames" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="CEColumnName1" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
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
															</paragraph>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="8%"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="CurrentAndPendingSupport" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PendingReportCEColumnNames" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="CEColumnName2" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
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
															</paragraph>
														</children>
													</tablecell>
													<tablecell headercell="1">
														<properties align="center" width="8%"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="CurrentAndPendingSupport" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PendingReportCEColumnNames" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="CEColumnName3" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
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
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tableheader>
									<tablebody>
										<children>
											<template match="CurrentAndPendingSupport" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="PendingSupport" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="center" width="5%"/>
																		<children>
																			<template match="ProposalNumber" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="12%"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="Agency" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="5%"/>
																		<children>
																			<template match="PI" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="12%"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="Title" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="right" width="8%"/>
																		<children>
																			<template match="TotalRequested" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<text fixtext="$"/>
																					<content>
																						<format datatype="decimal"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="7%"/>
																		<children>
																			<template match="EffectiveDate" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="date"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="7%"/>
																		<children>
																			<template match="EndDate" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="date"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="right" width="5%"/>
																		<children>
																			<template match="PercentageEffort" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="###,##0.00" datatype="decimal"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="right" width="5%"/>
																		<children>
																			<template match="AcademicYearEffort" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="###,##0.00" datatype="decimal"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="right" width="5%"/>
																		<children>
																			<template match="SummerYearEffort" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="###,##0.00" datatype="decimal"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="right" width="5%"/>
																		<children>
																			<template match="CalendarYearEffort" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="###,##0.00" datatype="decimal"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="8%"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="CEColumnValue1" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="8%"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="CEColumnValue2" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="8%"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="CEColumnValue3" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
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
											</template>
										</children>
									</tablebody>
								</children>
							</table>
							<newline/>
							<newline/>
							<newline/>
							<newline/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts>
		<children>
			<globaltemplate match="CurrentAndPendingSupport" matchtype="schemagraphitem">
				<children>
					<template match="CurrentAndPendingSupport" matchtype="schemagraphitem">
						<children>
							<content/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</globalparts>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="landscape" paperheight="8.5in" papermarginbottom="0.3in" papermarginleft="0.1in" papermarginright="0.1in" papermargintop="0.3in" paperwidth="11in"/>
	</pagelayout>
	<designfragments/>
</structure>
