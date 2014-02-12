<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="quirks" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="###,##0" datatype="decimal"/>
	</predefinedformats>
	<predefinedformats>
		<format string="MM / DD / YYYY" datatype="date"/>
	</predefinedformats>
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="award" uri="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award"/>
			<nspair prefix="subcontract" uri="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="SubcontractFdpReports.xsd" workingxmlfile="Subcontract_Fdp_Report.xml">
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
						<editorproperties elementstodisplay="1"/>
						<children>
							<table>
								<properties border="0" width="90%"/>
								<styles font-family="Arial" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<properties valign="top"/>
												<styles font-family="Arial"/>
												<children>
													<tablecell>
														<properties align="right" colspan="9" height="32" width="90%"/>
														<styles font-family="Arial"/>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<paragraph paragraphtag="h4">
																		<children>
																			<text fixtext="Attachment 3B - Research Subaward Agreement"/>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties valign="top"/>
												<children>
													<tablecell>
														<properties align="right" colspan="9" width="90%"/>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<paragraph paragraphtag="h4">
																		<children>
																			<text fixtext="Page 2 - Place of Performance &amp; Highest Compensated Officers"/>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
															<text fixtext="Subaward Number: "/>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:PONumber" matchtype="schemagraphitem">
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
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="9" width="100%"/>
														<children>
															<text fixtext="Institution/Organization (&quot;Subrecipient&quot;) "/>
															<newline/>
															<newline/>
															<text fixtext="Name:  "/>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:SubcontractorName" matchtype="schemagraphitem">
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
															<newline/>
															<newline/>
															<line>
																<properties color="black" size="1"/>
															</line>
															<text fixtext="Place of Performance"/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Name:     "/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Address: "/>
																							<newline/>
																							<text fixtext="                  "/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<text fixtext="City:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																					</tablecell>
																					<tablecell>
																						<properties width="5%"/>
																						<children>
																							<text fixtext="State:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="3" width="30%"/>
																					</tablecell>
																					<tablecell>
																						<properties colspan="7" width="12%"/>
																						<children>
																							<text fixtext="Zip Code +4:"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="left" width="8%"/>
																						<children>
																							<text fixtext="Telephone: "/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="5%"/>
																						<children>
																							<text fixtext="Fax:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="3" width="30%"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="12%"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="6" width="15%"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<text fixtext="Email: "/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="5%"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="3" width="30%"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="7" width="12%"/>
																						<children>
																							<text fixtext="Congressional District:"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<line>
																<properties color="black" size="1"/>
															</line>
															<newline/>
															<table>
																<properties border="0" cellspacing="1.5" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="100%"/>
																						<children>
																							<text fixtext="The names and total compensation of the five most highly compensated officers of the entity(ies) must be listed if--"/>
																							<paragraph paragraphtag="p"/>
																							<text fixtext=" (i) the entity in the preceding fiscal year received-"/>
																							<paragraph paragraphtag="p"/>
																							<text fixtext="     (I) 80 percent or more of its annual gross revenues in Federal awards (federal contracts (and subcontracts), loans, grants (and subgrants) and cooperative agreements); AND"/>
																							<newline/>
																							<paragraph paragraphtag="p"/>
																							<text fixtext="     (II) $25,000,000 or more in annual gross revenues from Federal awards; and "/>
																							<newline/>
																							<paragraph paragraphtag="p"/>
																							<text fixtext="(ii) the public does not have access to information about the compensation of the senior executives of the entity through periodic reports filed under section 13(a) or 15(d) of the Securities Exchange Act of 1934 (15 U.S.C. 78m(a), 78o(d)) or section 6104 of the Internal Revenue Code of 1986."/>
																							<newline/>
																							<paragraph paragraphtag="p"/>
																							<text fixtext="Is subaward entity exempt from reporting executive compensation? "/>
																							<condition>
																								<children>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:ExemptFromRprtgExecComp = &quot;Y&quot;">
																										<children>
																											<text fixtext="Yes"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:ExemptFromRprtgExecComp = &quot;N&quot;">
																										<children>
																											<text fixtext="No"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="100%"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:ExemptFromRprtgExecComp = &quot;N&quot;">
																										<children>
																											<newline/>
																											<text fixtext="Complete the information below."/>
																											<newline/>
																											<table>
																												<properties border="0" width="100%"/>
																												<styles font-family="Arial" font-size="9pt"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<text fixtext="Officer 1 Name"/>
																																		</children>
																																	</tablecell>
																																	<tablecell/>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<text fixtext="Officer 1 Compensation"/>
																																		</children>
																																	</tablecell>
																																	<tablecell/>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<text fixtext="Officer 2 Name"/>
																																		</children>
																																	</tablecell>
																																	<tablecell/>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<text fixtext="Officer 2 Compensation"/>
																																		</children>
																																	</tablecell>
																																	<tablecell/>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<text fixtext="Officer 3 Name"/>
																																		</children>
																																	</tablecell>
																																	<tablecell/>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<text fixtext="Officer 3 Compensation"/>
																																		</children>
																																	</tablecell>
																																	<tablecell/>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<text fixtext="Officer 4 Name"/>
																																		</children>
																																	</tablecell>
																																	<tablecell/>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<text fixtext="Officer 4 Compensation"/>
																																		</children>
																																	</tablecell>
																																	<tablecell/>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<text fixtext="Officer 5 Name"/>
																																		</children>
																																	</tablecell>
																																	<tablecell/>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<text fixtext="Officer 5 Compensation"/>
																																		</children>
																																	</tablecell>
																																	<tablecell/>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientation="landscape" pageorientationportrait="portrait" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="0.5in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
