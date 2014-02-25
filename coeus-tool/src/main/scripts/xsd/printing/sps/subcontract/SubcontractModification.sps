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
							<newline/>
							<table>
								<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
								<styles font-family="Arial"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<styles font-family="Arial" font-size="16pt"/>
												<children>
													<tablecell>
														<properties align="center" colspan="3" height="32" width="100%"/>
														<styles font-family="Arial"/>
														<children>
															<paragraph paragraphtag="center">
																<properties align="center"/>
																<children>
																	<text fixtext="Research Subaward Agreement"/>
																</children>
															</paragraph>
															<paragraph paragraphtag="center">
																<children>
																	<text fixtext="Amendment"/>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<styles font-family="Arial" font-size="16pt"/>
												<children>
													<tablecell>
														<properties align="center" width="50%"/>
														<children>
															<text fixtext="Prime Recipient"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" colspan="2" width="50%"/>
														<children>
															<text fixtext="Subrecipient"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="50%"/>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="100%"/>
																						<children>
																							<text fixtext="Institution/Organization (&quot;Prime Recipient&quot;)"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt"/>
																				<children>
																					<tablecell>
																						<properties width="100%"/>
																						<children>
																							<text fixtext="Name: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:PrimeRecipientContacts" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RequisitionerOrgDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:OrganizationName" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="100%"/>
																						<children>
																							<text fixtext="Address: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:PrimeRecipientContacts" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:OrgRolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																							<text fixtext="                 "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:PrimeRecipientContacts" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:OrgRolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address2" matchtype="schemagraphitem">
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
													<tablecell>
														<properties colspan="2" width="50%"/>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt"/>
																				<children>
																					<tablecell>
																						<properties width="100%"/>
																						<children>
																							<text fixtext="Institution/Organization (&quot;Subrecipient&quot;)"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="100%"/>
																						<children>
																							<text fixtext="Name: "/>
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
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="100%"/>
																						<children>
																							<text fixtext="Address: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorOrgRolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																							<text fixtext="                 "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorOrgRolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address2" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
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
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tablecell>
														<properties width="50%"/>
														<children>
															<text fixtext="Prime Award No."/>
															<newline/>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="award:AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="award:AwardHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="award:SponsorAwardNumber" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties width="25%"/>
														<children>
															<text fixtext="Subaward No."/>
															<newline/>
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
													<tablecell>
														<properties width="25%"/>
														<children>
															<text fixtext="Principal Investigator "/>
															<newline/>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:SiteInvestigator" matchtype="schemagraphitem">
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
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tablecell>
														<properties width="50%"/>
														<children>
															<text fixtext="Effective Date of Amendment "/>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractAmountInfo" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:ModificationEffectiveDate" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
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
														<properties colspan="2" width="50%"/>
														<children>
															<text fixtext="Amendment No. "/>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractAmountInfo" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:ModificationNumber" matchtype="schemagraphitem">
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
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tablecell>
														<properties colspan="3" width="100%"/>
														<children>
															<newline/>
															<paragraph paragraphtag="center">
																<children>
																	<paragraph paragraphtag="h3">
																		<children>
																			<text fixtext="Amendment(s) to Original Terms and Conditions"/>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
															<newline/>
															<newline/>
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
																						<properties height="320pt"/>
																						<children>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<newline/>
															<newline/>
															<newline/>
															<text fixtext="All other terms and conditions of this Subaward Agreement remain in full force and effect."/>
															<newline/>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="50%"/>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="2" width="100%"/>
																						<children>
																							<text fixtext="By an Authorized Official of Prime Recipient:"/>
																							<newline/>
																							<newline/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="75%"/>
																						<children>
																							<text fixtext="______________________________________________"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext=" _______________"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="75%"/>
																						<children>
																							<text fixtext="Name                                                  "/>
																							<newline/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Date   "/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="2" width="100%"/>
																						<children>
																							<text fixtext="Title    "/>
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
													<tablecell>
														<properties colspan="2" width="50%"/>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="2" width="100%"/>
																						<children>
																							<text fixtext="By an Authorized Official of Subrecipient:"/>
																							<newline/>
																							<newline/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="75%"/>
																						<children>
																							<text fixtext="______________________________________________"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext=" _______________"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="75%"/>
																						<children>
																							<text fixtext="Name                                                     "/>
																							<newline/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Date       "/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="2" width="100%"/>
																						<children>
																							<text fixtext="Title"/>
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
