<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="quirks" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="###,##0" datatype="decimal"/>
	</predefinedformats>
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="AwardNotice.xsd" workingxmlfile="Award_Notice$09142012-011726$.xml">
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
							<condition>
								<children>
									<conditionbranch xpath="count(  AwardNotice/AwardDisclosure/DisclosureItem  )  &gt; 0 or  count(   AwardNotice/AwardDisclosure/AwardValidation )  &gt; 0">
										<children>
											<template match="AwardNotice" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="1"/>
												<children>
													<template match="SchoolInfo" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="1"/>
														<children>
															<paragraph paragraphtag="center">
																<styles font-family="Arial"/>
																<children>
																	<template match="SchoolName" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<content>
																				<styles font-family="Arial" font-size="16pt" font-weight="bold"/>
																				<format datatype="string"/>
																			</content>
																		</children>
																	</template>
																</children>
															</paragraph>
															<paragraph paragraphtag="center">
																<properties align="center"/>
																<styles font-family="Arial"/>
																<children>
																	<newline/>
																	<newline/>
																	<text fixtext="Notice of Award - Hold Notice">
																		<styles font-family="Arial" font-size="14pt" font-weight="bold"/>
																	</text>
																	<newline/>
																	<newline>
																		<properties clear="none"/>
																	</newline>
																</children>
															</paragraph>
														</children>
													</template>
												</children>
											</template>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Account Number"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="left"/>
																		<styles font-family="Arial" font-size="9pt"/>
																		<children>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="AccountNumber" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath=". != 0">
																														<children>
																															<content>
																																<styles font-family="Arial" font-size="9pt"/>
																																<format datatype="string"/>
																															</content>
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
																			<text fixtext="                      "/>
																			<text fixtext="Award Number: ">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="AwardNumber" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<text fixtext=" "/>
																											<content>
																												<styles font-family="Arial" font-size="9pt"/>
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
																			<text fixtext="                         Sequence:">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="SequenceNumber" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<text fixtext=" "/>
																											<content>
																												<styles font-family="Arial" font-size="9pt"/>
																												<format datatype="int"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="                  "/>
																			<text fixtext="Status:">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="StatusDescription" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<text fixtext=" "/>
																											<content>
																												<styles font-family="Arial" font-size="9pt"/>
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
																		<properties align="right" width="20%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Modification Number:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial" font-size="9pt"/>
																		<children>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="ModificationNumber" matchtype="schemagraphitem">
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
																		<properties align="right" width="20%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Sponsor Award Number:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial" font-size="9pt"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="AwardNotice" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardDisclosure" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="AwardHeader" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="SponsorAwardNumber" matchtype="schemagraphitem">
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
																			</paragraph>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Sponsor:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial" font-size="9pt"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="AwardNotice" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardDisclosure" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="AwardHeader" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="SponsorCode" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																											<text fixtext=" - "/>
																											<template match="SponsorDescription" matchtype="schemagraphitem">
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
																			</paragraph>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Prime Sponsor"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial" font-size="9pt"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="AwardNotice" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="Award" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="AwardDetails" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="PrimeSponsorCode" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																													<text fixtext=" - "/>
																													<template match="PrimeSponsorDescription" matchtype="schemagraphitem">
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
																			</paragraph>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Title:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial" font-size="9pt"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="AwardNotice" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardDisclosure" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="AwardHeader" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
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
													</tablebody>
												</children>
											</table>
											<text fixtext="Holds for this award">
												<styles font-family="Arial" font-size="12pt" font-weight="bold" text-decoration="underline"/>
											</text>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="AwardNotice/AwardDisclosure/DisclosureValidation  = 1">
														<children>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt" line-height="9pt"/>
																<children>
																	<tablebody>
																		<styles font-family="Arial"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="2" height="2" valign="bottom" width="285"/>
																						<styles font-family="Arial"/>
																						<children>
																							<text fixtext="This award has financial interest disclosures associated with it which are in Pending or Incomplete status.">
																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="2" valign="top" width="285"/>
																						<styles font-family="Arial"/>
																						<children>
																							<text fixtext="Disclosures for this award">
																								<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																							</text>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="AwardDisclosure" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<table>
																												<properties border="0"/>
																												<styles font-family="Arial"/>
																												<children>
																													<tableheader>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="40%"/>
																																		<styles font-family="Arial" padding-left="20pt"/>
																																		<children>
																																			<text fixtext="Investigator">
																																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="10%"/>
																																		<children>
																																			<text fixtext="Role">
																																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Disclosure">
																																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Training">
																																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tableheader>
																													<tablebody>
																														<children>
																															<template match="DisclosureItem" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="40%"/>
																																				<styles padding-left="20pt"/>
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<template match="PersonName" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<content>
																																										<styles font-family="Arial"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</paragraph>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="10%"/>
																																				<children>
																																					<template match="DisclosureNumber" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="25%"/>
																																				<children>
																																					<template match="DisclosureTypeDesc" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="25%"/>
																																				<children>
																																					<template match="DisclosureStatusDesc" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
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
													</conditionbranch>
												</children>
											</condition>
											<newline/>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  AwardNotice/AwardDisclosure/AwardValidation  )  &gt; 0">
														<children>
															<newline/>
															<text fixtext="  "/>
															<newline/>
															<text fixtext="   "/>
															<newline/>
															<table>
																<properties border="0"/>
																<styles font-family="Arial" font-size="10pt"/>
																<children>
																	<tablebody>
																		<children>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardValidation" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties valign="top" width="8%"/>
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="contains( ValidationDetails  , &quot;has financial interest disclosures associated with it which are not complete&quot; )  =  false">
																																<children>
																																	<text fixtext="         "/>
																																	<text fixtext=" .">
																																		<styles font-size="x-large" font-weight="bold"/>
																																	</text>
																																</children>
																															</conditionbranch>
																														</children>
																													</condition>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="bottom" width="92%"/>
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="contains( ValidationDetails  , &quot;has financial interest disclosures associated with it which are not complete&quot; )  =  false">
																																<children>
																																	<paragraph paragraphtag="pre-wrap">
																																		<children>
																																			<template match="ValidationDetails" matchtype="schemagraphitem">
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
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablebody>
																</children>
															</table>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<newline/>
											<newline break="page"/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<paragraph paragraphtag="center">
								<children>
									<paragraph paragraphtag="h2">
										<children>
											<template match="AwardNotice" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="SchoolInfo" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="SchoolName" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<content>
																		<styles font-family="Arial"/>
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
							</paragraph>
							<paragraph paragraphtag="center">
								<styles font-family="Arial"/>
								<children>
									<paragraph paragraphtag="h3">
										<children>
											<text fixtext="Notice Of Award">
												<styles font-family="Arial"/>
											</text>
										</children>
									</paragraph>
								</children>
							</paragraph>
							<newline/>
							<newline/>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
								<children>
									<tablebody>
										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
										<children>
											<tablerow>
												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
												<children>
													<tablecell>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="Account Number:"/>
																</children>
															</paragraph>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="bottom"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AccountNumber" matchtype="schemagraphitem">
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
														<properties align="left"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="Award Number:"/>
																</children>
															</paragraph>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="bottom"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardNumber" matchtype="schemagraphitem">
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
														<properties align="left" valign="bottom" width="20%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Status:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="bottom" width="19%"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="StatusDescription" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<table>
								<properties border="0"/>
								<styles font-family="Arial"/>
								<children>
									<tableheader>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties valign="middle" width="181"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<text fixtext="Investigator:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="middle" width="181"/>
														<children>
															<text fixtext="Address:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="middle"/>
														<styles padding-left="0pt"/>
														<children>
															<text fixtext="Unit:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tableheader>
									<tablebody>
										<children>
											<template match="AwardNotice" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="Award" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="AwardInvestigators" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="Investigator" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties valign="top" width="31%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-left="10pt"/>
																						<children>
																							<template match="PersonName" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<content>
																										<styles font-family="Arial" font-size="9pt"/>
																										<format datatype="string"/>
																									</content>
																									<text fixtext=" ">
																										<styles font-family="Arial" font-size="9pt"/>
																									</text>
																									<condition>
																										<children>
																											<conditionbranch xpath="../PrincipalInvestigator  =  1 or ../PrincipalInvestigator = &apos;true&apos;">
																												<children>
																													<text fixtext="(PI)">
																														<styles font-family="Arial" font-size="9pt"/>
																													</text>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties valign="top" width="31%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="PersonAddress" matchtype="schemagraphitem">
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
																						<properties valign="top" width="38%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="InvestigatorUnit" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<newline/>
																									<template match="UnitNumber" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																									<text fixtext="  "/>
																									<template match="UnitName" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<content>
																												<styles font-family="Arial" font-size="9pt"/>
																												<format datatype="string"/>
																											</content>
																											<text fixtext=" ">
																												<styles font-family="Arial" font-size="9pt"/>
																											</text>
																											<condition>
																												<children>
																													<conditionbranch xpath="../LeadUnit  = 1 or ../LeadUnit = &apos;true&apos;">
																														<children>
																															<text fixtext="(LU) ">
																																<styles font-family="Arial" font-size="9pt" padding-left="0pt"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</template>
																									<newline/>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablebody>
								</children>
							</table>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Arial" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties width="13%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Sponsor:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" nowrap="set" width="87%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-right="20pt"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="AwardNotice" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="Award" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="SponsorCode" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																									<text fixtext=" - "/>
																									<template match="SponsorDescription" matchtype="schemagraphitem">
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
															</paragraph>
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
														<properties width="13%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Award Number:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" width="48%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-right="20pt"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="AwardNotice" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="Award" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="SponsorAwardNumber" matchtype="schemagraphitem">
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
															</paragraph>
														</children>
													</tablecell>
													<tablecell>
														<properties width="20%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-right="20pt"/>
														<children>
															<text fixtext=" Modification Number:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" width="19%"/>
														<styles font-family="Arial" font-size="9pt" padding-right="20pt"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ModificationNumber" matchtype="schemagraphitem">
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
														<properties width="13%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Execution Date:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="bottom" width="87%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-right="20pt"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ExecutionDate" matchtype="schemagraphitem">
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
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="13%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="NSF Code:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="bottom" width="87%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-right="20pt"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="NSFDescription" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<paragraph paragraphtag="pre-wrap">
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</paragraph>
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
														<properties width="13%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Title of Project:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="87%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AwardHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="Title" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<paragraph paragraphtag="pre-wrap">
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</paragraph>
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
							<paragraph paragraphtag="p"/>
							<condition>
								<children>
									<conditionbranch xpath="AwardNotice/PrintRequirement/SignatureRequired  =  &quot;0&quot;">
										<children>
											<newline/>
										</children>
									</conditionbranch>
									<conditionbranch xpath="AwardNotice/PrintRequirement/SignatureRequired  =   true()">
										<children>
											<newline/>
											<table>
												<properties border="1" width="100%"/>
												<styles table-layout="fixed"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<properties valign="top"/>
																<children>
																	<tablecell>
																		<properties colspan="4"/>
																		<styles font-family="Arial" line-height="13pt" padding="6pt"/>
																		<children>
																			<text fixtext="Signatures on Notice of Award REQUIRED.  Please indicate your approval of the terms and conditions identified in the document and return this notice to ">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="Award" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="AwardDetails" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="FellowshipAdminName" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<content>
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
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
																			<text fixtext=", E19-750. Any attachments may be kept for your records.">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																			<newline/>
																			<newline/>
																			<table>
																				<properties border="0" width="100%"/>
																				<styles border-collapse="separate"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties align="left" width="20%"/>
																										<styles font-family="Arial" line-height="13pt" padding-left="36pt"/>
																										<children>
																											<text fixtext="PI Signature :">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="center" valign="bottom" width="50%"/>
																										<styles font-family="Arial" line-height="13pt"/>
																										<children>
																											<text fixtext="______________________________">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="10%"/>
																										<styles font-family="Arial" line-height="13pt"/>
																										<children>
																											<text fixtext="Date:">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="20%"/>
																										<styles font-family="Arial" line-height="13pt"/>
																										<children>
																											<text fixtext="_______________">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties align="left" width="20%"/>
																										<styles font-family="Arial" line-height="13pt" padding-left="36pt"/>
																										<children>
																											<text fixtext="AO Signature :">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="center" width="50%"/>
																										<styles font-family="Arial" line-height="13pt"/>
																										<children>
																											<text fixtext="______________________________">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="10%"/>
																										<styles font-family="Arial" line-height="13pt"/>
																										<children>
																											<text fixtext="Date:">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="20%"/>
																										<styles font-family="Arial" line-height="13pt"/>
																										<children>
																											<text fixtext="_______________">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties align="left" width="20%"/>
																										<styles font-family="Arial" line-height="13pt" padding-left="36pt"/>
																										<children>
																											<text fixtext="OSP Signature :">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="center" width="50%"/>
																										<styles font-family="Arial" line-height="13pt"/>
																										<children>
																											<text fixtext="______________________________">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="10%"/>
																										<styles font-family="Arial" line-height="13pt"/>
																										<children>
																											<text fixtext="Date:">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="20%"/>
																										<styles font-family="Arial" line-height="13pt"/>
																										<children>
																											<text fixtext="_______________">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
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
									</conditionbranch>
								</children>
							</condition>
							<condition>
								<children>
									<conditionbranch xpath="AwardNotice/PrintRequirement/SignatureRequired  =  &quot;0&quot;">
										<children>
											<table>
												<properties border="1" width="100%"/>
												<styles color="black" font-family="Arial"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="Signatures on Notice of Award NOT REQUIRED.  Expenditure of funds signifies agreement with terms and conditions of award.">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
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
							<newline/>
							<paragraph paragraphtag="p"/>
							<newline/>
							<text fixtext="PROJECT PERIOD:">
								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
							</text>
							<newline/>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Arial"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties width="31%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="0"/>
														<children>
															<text fixtext="Effective Date:">
																<styles font-weight="bold" padding-top="10pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="69%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="EffectiveDate" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
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
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="31%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Final Expiration Date:">
																<styles font-weight="bold" padding-top="10pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="69%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="AwardAmountInfo" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AmountInfo" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="FinalExpirationDate" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
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
							<condition>
								<children>
									<conditionbranch xpath="count( AwardNotice/Award/AwardAmountInfo  )  = 1">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="AwardNotice/Award/AwardAmountInfo/AmountInfo/EnableAwdAntOblDirectIndirectCost  = &apos;1&apos;">
														<children>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="31%"/>
																						<styles font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Total Change to Anticipated Award:">
																								<styles font-weight="bold" padding-top="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="69%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="AwardAmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="AmountInfo" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="AnticipatedChange" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<format string="#,###,###,##0.00" datatype="decimal"/>
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="31%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Change to Direct Anticipated Award:">
																								<styles font-weight="bold" padding-top="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="69%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="AwardAmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="AmountInfo" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
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
																						<properties width="31%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Change to Indirect Anticipated Award :">
																								<styles font-weight="bold" padding-top="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="69%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="AwardAmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="AmountInfo" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
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
													</conditionbranch>
												</children>
											</condition>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="31%"/>
																		<styles font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Total Anticipated Award:">
																				<styles font-weight="bold" padding-top="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="69%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																		<children>
																			<template match="$XML" matchtype="schemasource">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AwardNotice" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="Award" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="AwardAmountInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="AmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="AnticipatedTotalAmt" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
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
											<condition>
												<children>
													<conditionbranch xpath="AwardNotice/Award/AwardAmountInfo/AmountInfo/EnableAwdAntOblDirectIndirectCost  = &apos;1&apos;">
														<children>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="31%"/>
																						<styles font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Direct Anticipated Total Award:">
																								<styles font-weight="bold" padding-top="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="69%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="AwardAmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="AmountInfo" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
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
																						<properties width="31%"/>
																						<styles font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Indirect Anticipated Total Award:">
																								<styles font-weight="bold" padding-top="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="69%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="AwardAmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="AmountInfo" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
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
													</conditionbranch>
												</children>
											</condition>
											<newline/>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<text fixtext="BUDGET PERIOD:">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="31%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Effective Date of Current Obligation:">
																				<styles font-weight="bold" padding-top="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="69%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																		<children>
																			<template match="$XML" matchtype="schemasource">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AwardNotice" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="Award" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="AwardAmountInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="AmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="CurrentFundEffectiveDate" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
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
																		<properties width="31%"/>
																		<styles font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Expiration Date of Current Obligation:">
																				<styles font-weight="bold" padding-top="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="69%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																		<children>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="Award" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="AwardAmountInfo" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="AmountInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="ObligationExpirationDate" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
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
											<condition>
												<children>
													<conditionbranch xpath="AwardNotice/Award/AwardAmountInfo/AmountInfo/EnableAwdAntOblDirectIndirectCost  = &apos;1&apos;">
														<children>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="31%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext=" Change to Direct Amount Obligated:">
																								<styles font-weight="bold" padding-top="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="69%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="AwardAmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="AmountInfo" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="ObligatedChangeDirect" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<format string="#,###,###,##0.00" datatype="decimal"/>
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="31%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Change to Indirect Amount Obligated:">
																								<styles font-weight="bold" padding-top="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="69%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="AwardAmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="AmountInfo" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
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
													</conditionbranch>
												</children>
											</condition>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="31%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Total Amount Obligated:">
																				<styles font-weight="bold" padding-top="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="69%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																		<children>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="Award" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="AwardAmountInfo" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="AmountInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="AmtObligatedToDate" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="###,###,###,###,##0.00" datatype="decimal"/>
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
											<condition>
												<children>
													<conditionbranch xpath="AwardNotice/Award/AwardAmountInfo/AmountInfo/EnableAwdAntOblDirectIndirectCost  = &apos;1&apos;">
														<children>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="31%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Direct Amount Obligated:">
																								<styles font-weight="bold" padding-top="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="69%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="AwardAmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="AmountInfo" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="ObligatedTotalDirect" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<format string="###,###,###,###,##0.00" datatype="decimal"/>
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="31%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Indirect Amount Obligated:">
																								<styles font-weight="bold" padding-top="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="69%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="AwardAmountInfo" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="AmountInfo" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="ObligatedTotalIndirect" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<format string="###,###,###,###,##0.00" datatype="decimal"/>
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
													</conditionbranch>
												</children>
											</condition>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<table>
								<properties width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties width="31%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Type of Activity:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" width="69%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="AwardNotice" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="Award" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AwardDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="ActivityTypeDesc" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
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
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="31%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Type of Award:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" width="69%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="AwardNotice" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="Award" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AwardDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="AwardTypeDesc" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
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
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="31%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Type of Account:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="25%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="AwardNotice" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="Award" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AwardDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="AccountTypeDesc" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
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
															</paragraph>
														</children>
													</tablecell>
													<tablecell>
														<properties width="17%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="substring(AwardNotice/Award/AwardDetails/AwardHeader/AwardNumber ,8)!=&quot;001&quot;">
																		<children>
																			<text fixtext="Root Account Number:">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" "/>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tablecell>
													<tablecell>
														<properties width="27%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="substring(AwardNotice/Award/AwardDetails/AwardHeader/AwardNumber ,8)!=&quot;001&quot;">
																		<children>
																			<newline/>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="Award" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="AwardDetails" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="RootAccountNumber" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<paragraph paragraphtag="pre-wrap">
																														<children>
																															<content>
																																<styles font-weight="normal"/>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</paragraph>
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
																			<newline/>
																			<newline/>
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
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="(boolean( AwardNotice/Award/AwardDetails/OtherHeaderDetails/PreAwardAuthorizedAmt )  =  true() and  number(AwardNotice/Award/AwardDetails/OtherHeaderDetails/PreAwardAuthorizedAmt) &gt;0 ) or boolean( AwardNotice/Award/AwardDetails/OtherHeaderDetails/PreAwardEffectiveDate )">
										<children>
											<text fixtext="Pre Award:">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<table>
												<properties width="100%"/>
												<styles font-family="Arial" font-weight="normal"/>
												<children>
													<tablebody>
														<styles font-weight="normal"/>
														<children>
															<tablerow>
																<styles font-weight="normal"/>
																<children>
																	<tablecell>
																		<properties width="31%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Authorized Amount:">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="69%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-left="4pt"/>
																		<children>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="Award" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="AwardDetails" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="PreAwardAuthorizedAmt" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<styles font-family="Arial" font-size="9pt"/>
																														<format string="#,###,###,##0.00" datatype="decimal"/>
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
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="31%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Effective Date:">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="69%"/>
																		<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-left="4pt"/>
																		<children>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="Award" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="AwardDetails" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="PreAwardEffectiveDate" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<content>
																														<styles font-family="Arial" font-size="9pt"/>
																														<format string="MM/DD/YYYY" datatype="date"/>
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
									</conditionbranch>
								</children>
							</condition>
							<template match="AwardNotice" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<newline/>
									<template match="Award" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="1"/>
										<children>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<template match="AwardDetails" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="1"/>
												<children>
													<newline/>
													<table>
														<properties border="0" width="100%"/>
														<styles font-family="Arial"/>
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<styles font-family="Arial"/>
																		<children>
																			<tablecell>
																				<properties width="31%"/>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<text fixtext="AO:">
																						<styles font-weight="bold"/>
																					</text>
																					<newline/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="25%"/>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<template match="$XML" matchtype="schemasource">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="AwardNotice" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="AODetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="AOName" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
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
																					</template>
																					<newline/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="17%"/>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<text fixtext="Address:">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="27%"/>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<template match="$XML" matchtype="schemasource">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="AwardNotice" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="AODetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="AOAddress" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
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
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="../PrintRequirement/AddressListRequired = 1 or  ../PrintRequirement/CloseoutRequired = 1 or  ../PrintRequirement/CommentsRequired = 1 or  ../PrintRequirement/CostSharingRequired = 1 or  ../PrintRequirement/EquipmentRequired = 1 or  ../PrintRequirement/FlowThruRequired = 1 or  ../PrintRequirement/ForeignTravelRequired = 1 or  ../PrintRequirement/HierarchyInfoRequired = 1 or  ../PrintRequirement/CurrentDate = 1 or  ../PrintRequirement/IndirectCostRequired = 1 or  ../PrintRequirement/PaymentRequired = 1 or  ../PrintRequirement/ProposalDueRequired = 1 or  ../PrintRequirement/ReportingRequired = 1 or  ../PrintRequirement/ScienceCodeRequired = 1 or  ../PrintRequirement/SpecialReviewRequired = 1 or  ../PrintRequirement/SubcontractRequired = 1 or  ../PrintRequirement/TechnicalReportingRequired = 1 or  ../PrintRequirement/TermsRequired = 1 or  ../PrintRequirement/OtherDataRequired  = 1">
														<children>
															<newline/>
															<newline break="page"/>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/HierarchyInfoRequired   = &quot;1&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="count(    ChildAwardDetails/ChildAward )  &gt; 0">
																						<children>
																							<text fixtext="Distributions:">
																								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																							</text>
																							<newline/>
																							<template match="ChildAwardDetails" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<table>
																										<properties border="0"/>
																										<styles font-family="Arial"/>
																										<children>
																											<tableheader>
																												<children>
																													<tablerow>
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																														<children>
																															<tablecell>
																																<children>
																																	<text fixtext="PI">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<children>
																																	<text fixtext="Account Number">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<children>
																																	<text fixtext="Start Date">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<children>
																																	<text fixtext="End Date">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<children>
																																	<text fixtext="Total">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																												</children>
																											</tableheader>
																											<tablebody>
																												<styles font-family="Arial"/>
																												<children>
																													<template match="ChildAward" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<tablerow>
																																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																<children>
																																	<tablecell>
																																		<children>
																																			<paragraph paragraphtag="pre-wrap">
																																				<children>
																																					<template match="PIName" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
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
																																		<children>
																																			<template match="AccountNumber" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath=". &gt;0">
																																								<children>
																																									<content>
																																										<styles font-family="Arial"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</conditionbranch>
																																						</children>
																																					</condition>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<styles font-size="9pt"/>
																																		<children>
																																			<template match="CurrentFundEffectiveDate" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<content>
																																						<format string="MM/DD/YYYY" datatype="date"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<styles font-size="9pt"/>
																																		<children>
																																			<template match="ObligationExpirationDate" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<content>
																																						<format string="MM/DD/YYYY" datatype="date"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<styles font-size="9pt"/>
																																		<children>
																																			<text fixtext="$"/>
																																			<template match="AmtObligatedToDate" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
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
																													</template>
																												</children>
																											</tablebody>
																											<tablefooter>
																												<children>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties height="22"/>
																															</tablecell>
																															<tablecell>
																																<properties height="22"/>
																															</tablecell>
																															<tablecell>
																																<properties colspan="3" height="22" valign="top"/>
																																<children>
																																	<line>
																																		<properties color="black" size="0.3"/>
																																	</line>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																													<tablerow>
																														<styles font-family="Arial"/>
																														<children>
																															<tablecell/>
																															<tablecell/>
																															<tablecell>
																																<properties align="center" colspan="2"/>
																																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																<children>
																																	<text fixtext="Total Obligated:">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																<children>
																																	<text fixtext="$"/>
																																	<autocalc xpath="sum( ChildAward/AmtObligatedToDate )">
																																		<styles font-family="Arial"/>
																																		<format string="#,###,###,##0.00" datatype="decimal"/>
																																	</autocalc>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties colspan="5"/>
																																<children>
																																	<line>
																																		<properties color="black" size="1"/>
																																	</line>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																												</children>
																											</tablefooter>
																										</children>
																									</table>
																								</children>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/ScienceCodeRequired  = &quot;1&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="starts-with(AwardDetails/ScienceCodeIndicator , &quot;P&quot; )">
																						<children>
																							<text fixtext="Science Code:">
																								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																							</text>
																							<newline/>
																							<template match="AwardScienceCodes" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<table>
																										<properties border="0" width="100%"/>
																										<styles font-family="Arial" font-weight="bold"/>
																										<children>
																											<tableheader>
																												<children>
																													<tablerow>
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																														<children>
																															<tablecell>
																																<properties width="362"/>
																																<children>
																																	<text fixtext="Code">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="left"/>
																																<children>
																																	<text fixtext="Description">
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
																													<template match="ScienceCodeDetail" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<tablerow>
																																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																<children>
																																	<tablecell>
																																		<properties width="362"/>
																																		<children>
																																			<template match="Code" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="left"/>
																																		<children>
																																			<paragraph paragraphtag="pre-wrap">
																																				<children>
																																					<template match="Description" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
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
																											</tablebody>
																										</children>
																									</table>
																								</children>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																				<children>
																					<tablecell>
																						<properties width="100%"/>
																						<styles font-family="Arial" font-size="12pt"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../PrintRequirement/TermsRequired  = &quot;1&quot;">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="count(  AwardTermsDetails/Term  )  &gt; 0">
																														<children>
																															<text fixtext="Terms:">
																																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																															</text>
																															<newline/>
																															<template match="AwardTermsDetails" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<newline/>
																																	<template match="Term" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<newline/>
																																			<table>
																																				<properties width="100%"/>
																																				<children>
																																					<tablebody>
																																						<children>
																																							<tablerow>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<tablecell>
																																										<properties width="581"/>
																																										<styles font-family="Arial" font-size="9pt"/>
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath="TermDetails/TermCode  != &quot;1&quot;">
																																														<children>
																																															<template match="Description" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<content>
																																																		<styles font-weight="bold"/>
																																																		<format datatype="string"/>
																																																	</content>
																																																</children>
																																															</template>
																																															<text fixtext=":">
																																																<styles font-weight="bold"/>
																																															</text>
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
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="TermDetails/TermCode != &quot;1&quot;">
																																						<children>
																																							<newline/>
																																							<table>
																																								<properties border="0" width="100%"/>
																																								<styles font-family="Arial" padding-left="10pt"/>
																																								<children>
																																									<tablebody>
																																										<children>
																																											<tablerow>
																																												<children>
																																													<tablecell>
																																														<properties width="5%"/>
																																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																													</tablecell>
																																													<tablecell>
																																														<properties width="95%"/>
																																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																														<children>
																																															<template match="TermDetails" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="5"/>
																																																<children>
																																																	<paragraph paragraphtag="pre-wrap">
																																																		<children>
																																																			<template match="TermDescription" matchtype="schemagraphitem">
																																																				<editorproperties elementstodisplay="5"/>
																																																				<children>
																																																					<text fixtext="-"/>
																																																					<content>
																																																						<format datatype="string"/>
																																																					</content>
																																																				</children>
																																																			</template>
																																																		</children>
																																																	</paragraph>
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
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																			<newline/>
																																		</children>
																																	</template>
																																	<newline/>
																																</children>
																															</template>
																															<newline/>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
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
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/CommentsRequired = &quot;1&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="count(  AwardComments/Comment [CommentDetails/CommentCode  &gt; 0  and  string-length(CommentDetails/Comments ) &gt;= 1] )  &gt; 0">
																						<children>
																							<text fixtext="Comments:">
																								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																							</text>
																							<newline/>
																							<template match="AwardComments" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<newline/>
																									<newline/>
																									<template match="Comment" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<newline/>
																											<condition>
																												<children>
																													<conditionbranch xpath="CommentDetails/CommentCode  &gt;0  and  string-length(CommentDetails/Comments ) &gt;= 1">
																														<children>
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
																																						<properties valign="top" width="20%"/>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<template match="Description" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<content>
																																										<styles font-weight="bold"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																							<text fixtext=":">
																																								<styles font-weight="bold"/>
																																							</text>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties valign="top" width="80%"/>
																																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																						<children>
																																							<template match="CommentDetails" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<template match="Comments" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<content>
																																														<styles line-height="9pt"/>
																																														<format datatype="string"/>
																																													</content>
																																												</children>
																																											</paragraph>
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
																													</conditionbranch>
																												</children>
																											</condition>
																											<newline/>
																										</children>
																									</template>
																									<newline/>
																								</children>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/TechnicalReportingRequired = &quot;1&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="AwardReportingDetails/ReportDetails/ReportTermDetails/ReportClassCode = 4">
																						<children>
																							<text fixtext="Technical Reporting Requirements:">
																								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																							</text>
																							<template match="AwardReportingDetails" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="ReportDetails" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="ReportTermDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="ReportClassCode = 4">
																																<children>
																																	<table>
																																		<properties border="0pt" cellpadding="0pt" width="100%"/>
																																		<styles font-family="Arial" padding-top="10pt"/>
																																		<children>
																																			<tablebody>
																																				<children>
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties width="160"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																								<children>
																																									<newline/>
																																									<text fixtext="Type of Report: ">
																																										<styles font-family="Arial"/>
																																									</text>
																																									<newline/>
																																									<newline/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties align="left"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="1pt"/>
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<template match="ReportCodeDesc" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties width="160"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																								<children>
																																									<newline/>
																																									<text fixtext="Frequency:"/>
																																									<newline/>
																																									<newline/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties align="left"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="1pt"/>
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<template match="FrequencyCodeDesc" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties width="160"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																								<children>
																																									<newline/>
																																									<text fixtext="Frequency Basis"/>
																																									<text fixtext=": ">
																																										<styles padding-top="10pt"/>
																																									</text>
																																									<newline/>
																																									<newline/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties align="left"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="1pt"/>
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<template match="FrequencyBaseDesc" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties width="160"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																								<children>
																																									<newline/>
																																									<condition>
																																										<children>
																																											<conditionbranch xpath="DueDate != &apos;1900-01-01+00:00&apos;">
																																												<children>
																																													<text fixtext=" Due Date:"/>
																																												</children>
																																											</conditionbranch>
																																										</children>
																																									</condition>
																																									<newline/>
																																									<newline/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties align="left"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="1pt"/>
																																								<children>
																																									<condition>
																																										<children>
																																											<conditionbranch xpath="DueDate != &apos;1900-01-01+00:00&apos;">
																																												<children>
																																													<template match="DueDate" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
																																														<children>
																																															<content>
																																																<format string="MM/DD/YYYY" datatype="date"/>
																																															</content>
																																														</children>
																																													</template>
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
																																								<properties width="160"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																								<children>
																																									<newline/>
																																									<text fixtext="OSP Distribution:">
																																										<styles padding-top="10pt"/>
																																									</text>
																																									<newline/>
																																									<newline/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties align="left"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="1pt"/>
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<template match="OSPDistributionDesc" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties colspan="2" width="160"/>
																																								<styles padding="0" padding-bottom="0" padding-top="0"/>
																																								<children>
																																									<condition>
																																										<children>
																																											<conditionbranch xpath="MailCopies/NumberOfCopies &gt;0">
																																												<children>
																																													<table>
																																														<properties border="0" cellpadding="0"/>
																																														<styles font-family="Arial"/>
																																														<children>
																																															<tablebody>
																																																<children>
																																																	<template match="MailCopies" matchtype="schemagraphitem">
																																																		<editorproperties elementstodisplay="1"/>
																																																		<children>
																																																			<tablerow>
																																																				<children>
																																																					<tablecell>
																																																						<properties colspan="2"/>
																																																						<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="0"/>
																																																						<children>
																																																							<paragraph paragraphtag="pre-wrap">
																																																								<children>
																																																									<text fixtext="Mail "/>
																																																									<template match="NumberOfCopies" matchtype="schemagraphitem">
																																																										<editorproperties elementstodisplay="1"/>
																																																										<children>
																																																											<content>
																																																												<format datatype="string"/>
																																																											</content>
																																																										</children>
																																																									</template>
																																																									<text fixtext=" "/>
																																																									<condition>
																																																										<children>
																																																											<conditionbranch xpath="NumberOfCopies &gt; 1">
																																																												<children>
																																																													<text fixtext="copies"/>
																																																												</children>
																																																											</conditionbranch>
																																																											<conditionbranch xpath="NumberOfCopies &lt;2">
																																																												<children>
																																																													<text fixtext="copy"/>
																																																												</children>
																																																											</conditionbranch>
																																																										</children>
																																																									</condition>
																																																									<text fixtext=" to "/>
																																																									<template match="RolodexName" matchtype="schemagraphitem">
																																																										<editorproperties elementstodisplay="1"/>
																																																										<children>
																																																											<content>
																																																												<format datatype="string"/>
																																																											</content>
																																																										</children>
																																																									</template>
																																																									<text fixtext="  "/>
																																																									<template match="RolodexOrganization" matchtype="schemagraphitem">
																																																										<editorproperties elementstodisplay="1"/>
																																																										<children>
																																																											<content>
																																																												<format datatype="string"/>
																																																											</content>
																																																										</children>
																																																									</template>
																																																									<text fixtext="  (For complete address, see following address page Rolodex ID  "/>
																																																									<template match="RolodexId" matchtype="schemagraphitem">
																																																										<editorproperties elementstodisplay="1"/>
																																																										<children>
																																																											<content>
																																																												<format datatype="string"/>
																																																											</content>
																																																										</children>
																																																									</template>
																																																									<text fixtext=" )"/>
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
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<table>
																<properties width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
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
																						<properties width="681"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../PrintRequirement/ProposalDueRequired = 1">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="string-length(  AwardDetails/OtherHeaderDetails/NonCompetingContDesc  ) &gt;0  or  string-length(  AwardDetails/OtherHeaderDetails/CompetingRenewalDesc  )  &gt; 0">
																														<children>
																															<text fixtext="Proposal Due:">
																																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																															</text>
																															<newline/>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length(  AwardDetails/OtherHeaderDetails/NonCompetingContDesc  ) &gt;0">
																																		<children>
																																			<table>
																																				<properties border="0" width="100%"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<tablebody>
																																						<children>
																																							<tablerow>
																																								<children>
																																									<tablecell>
																																										<properties width="160"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																										<children>
																																											<text fixtext="Non Competing Continuation:">
																																												<styles font-weight="bold"/>
																																											</text>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<template match="AwardDetails" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
																																														<children>
																																															<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<template match="NonCompetingContDesc" matchtype="schemagraphitem">
																																																		<editorproperties elementstodisplay="1"/>
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
																																					</tablebody>
																																				</children>
																																			</table>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length(  AwardDetails/OtherHeaderDetails/CompetingRenewalDesc  )  &gt; 0">
																																		<children>
																																			<table>
																																				<properties border="0" width="100%"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<tablebody>
																																						<children>
																																							<tablerow>
																																								<children>
																																									<tablecell>
																																										<properties width="160"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																										<children>
																																											<text fixtext="Competing Renewal:">
																																												<styles font-weight="bold"/>
																																											</text>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<template match="AwardDetails" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
																																														<children>
																																															<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<template match="CompetingRenewalDesc" matchtype="schemagraphitem">
																																																		<editorproperties elementstodisplay="1"/>
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
																																					</tablebody>
																																				</children>
																																			</table>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
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
															<table>
																<properties width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<condition>
																<children>
																	<conditionbranch xpath="(../PrintRequirement/EquipmentRequired =&quot;1&quot;  and  starts-with( AwardDetails/ApprvdEquipmentIndicator , &quot;P&quot; ) )  or  (../PrintRequirement/ForeignTravelRequired = 1  and  starts-with(  AwardDetails/ApprvdForeginTripIndicator , &quot;P&quot; ) )or ( ../PrintRequirement/SubcontractRequired  = 1  and  starts-with(  AwardDetails/ApprvdSubcontractIndicator , &quot;P&quot; ) )">
																		<children>
																			<text fixtext="Approved Special Items:">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<newline/>
																			<table>
																				<properties border="0" width="100%"/>
																				<styles font-family="Arial"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../PrintRequirement/EquipmentRequired = 1 and  starts-with( AwardDetails/ApprvdEquipmentIndicator , &quot;P&quot; )">
																														<children>
																															<text fixtext="Equipment:">
																																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																															</text>
																															<newline/>
																															<template match="AwardSpecialItems" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<table>
																																		<properties border="0" width="100%"/>
																																		<children>
																																			<tableheader>
																																				<children>
																																					<tablerow>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<tablecell>
																																								<properties width="1%"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="32%"/>
																																								<children>
																																									<text fixtext="Item">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="25%"/>
																																								<children>
																																									<text fixtext="Vendor">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="27%"/>
																																								<children>
																																									<text fixtext="Model">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="15%"/>
																																								<children>
																																									<text fixtext="Amount">
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
																																					<template match="Equipment" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
																																						<children>
																																							<tablerow>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																								<children>
																																									<tablecell>
																																										<properties width="1%"/>
																																									</tablecell>
																																									<tablecell>
																																										<properties width="32%"/>
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<template match="Item" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
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
																																										<properties width="25%"/>
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<template match="Vendor" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
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
																																										<properties width="27%"/>
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<template match="Model" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
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
																																										<properties width="15%"/>
																																										<styles font-size="9pt"/>
																																										<children>
																																											<template match="Amount" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					</template>
																																				</children>
																																			</tablebody>
																																		</children>
																																	</table>
																																</children>
																															</template>
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
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="(../PrintRequirement/ForeignTravelRequired = &quot;1&quot;  and  starts-with(  AwardDetails/ApprvdForeginTripIndicator , &quot;P&quot; ) )">
																														<children>
																															<text fixtext="Foreign Travel:">
																																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																															</text>
																															<newline/>
																															<template match="AwardSpecialItems" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<table>
																																		<properties border="0"/>
																																		<children>
																																			<tableheader>
																																				<children>
																																					<tablerow>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<tablecell>
																																								<properties width="10"/>
																																							</tablecell>
																																							<tablecell>
																																								<children>
																																									<text fixtext="Name of Traveler">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<children>
																																									<text fixtext="Destination">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<children>
																																									<text fixtext="Date From">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<children>
																																									<text fixtext="Date To">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<children>
																																									<text fixtext="Amount">
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
																																					<template match="ForeignTravel" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
																																						<children>
																																							<tablerow>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																								<children>
																																									<tablecell>
																																										<properties width="10"/>
																																									</tablecell>
																																									<tablecell>
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<template match="PersonName" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
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
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<template match="Destination" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
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
																																										<styles font-size="9pt"/>
																																										<children>
																																											<template match="DateFrom" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<content>
																																														<format string="MM/DD/YYYY" datatype="date"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<styles font-size="9pt"/>
																																										<children>
																																											<template match="DateTo" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<content>
																																														<format string="MM/DD/YYYY" datatype="date"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<styles font-size="9pt"/>
																																										<children>
																																											<template match="Amount" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					</template>
																																				</children>
																																			</tablebody>
																																		</children>
																																	</table>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																								<children>
																									<tablecell>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="( ../PrintRequirement/SubcontractRequired  =&quot;1&quot; and  starts-with(  AwardDetails/ApprvdSubcontractIndicator , &quot;P&quot; ) )">
																														<children>
																															<text fixtext="Subcontracts:">
																																<styles font-weight="bold"/>
																															</text>
																															<newline/>
																															<template match="AwardSpecialItems" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<table>
																																		<properties border="0" width="100%"/>
																																		<styles font-family="Arial"/>
																																		<children>
																																			<tableheader>
																																				<children>
																																					<tablerow>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<tablecell>
																																								<properties width="10"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="150"/>
																																								<children>
																																									<text fixtext="Amount">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<children>
																																									<text fixtext="Subcontractor Name">
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
																																					<template match="Subcontract" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
																																						<children>
																																							<tablerow>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																								<children>
																																									<tablecell>
																																										<properties width="10"/>
																																									</tablecell>
																																									<tablecell>
																																										<properties width="150"/>
																																										<styles font-size="9pt"/>
																																										<children>
																																											<template match="Amount" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<template match="SubcontractorName" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
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
																																			</tablebody>
																																		</children>
																																	</table>
																																</children>
																															</template>
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
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<table>
																<properties width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/CostSharingRequired   = &quot;1&quot; and  count(  AwardCostSharing  ) &gt; 0 and not (starts-with( AwardDetails/CostSharingIndicator , &quot;N&quot; )  and   string-length(AwardCostSharing/Comments) &lt;= 1  )">
																		<children>
																			<text fixtext="Cost Sharing:">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<newline/>
																			<template match="AwardCostSharing" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
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
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="starts-with(../AwardDetails/CostSharingIndicator  , &quot;P&quot; )">
																																<children>
																																	<table>
																																		<properties border="0" cellspacing="0" width="100%"/>
																																		<children>
																																			<tableheader>
																																				<children>
																																					<tablerow>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<tablecell>
																																								<properties width="62"/>
																																								<children>
																																									<text fixtext="%">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<children>
																																									<text fixtext="Type">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="65"/>
																																								<children>
																																									<text fixtext="Project Year">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="94"/>
																																								<children>
																																									<text fixtext="Source Acct">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<children>
																																									<text fixtext="Destination Acct">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<children>
																																									<text fixtext="Amount">
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
																																					<template match="CostSharingItem" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
																																						<children>
																																							<tablerow>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																								<children>
																																									<tablecell>
																																										<properties valign="top" width="62"/>
																																										<styles font-size="9pt"/>
																																										<children>
																																											<template match="Percentage" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<content>
																																														<format string="##0.00" datatype="double"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top"/>
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<template match="CostSharingDescription" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
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
																																										<properties valign="top" width="65"/>
																																										<children>
																																											<template match="FiscalYear" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<content>
																																														<format datatype="string"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top" width="94"/>
																																										<children>
																																											<template match="SourceAccount" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<paragraph paragraphtag="pre-wrap">
																																														<children>
																																															<content>
																																																<format datatype="string"/>
																																															</content>
																																														</children>
																																													</paragraph>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top"/>
																																										<children>
																																											<template match="DestinationAccount" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<paragraph paragraphtag="pre-wrap">
																																														<children>
																																															<content>
																																																<format datatype="string"/>
																																															</content>
																																														</children>
																																													</paragraph>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top"/>
																																										<styles font-size="9pt"/>
																																										<children>
																																											<template match="Amount" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					</template>
																																					<tablerow>
																																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																						<children>
																																							<tablecell>
																																								<properties valign="top" width="62"/>
																																								<styles font-size="9pt"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top" width="65"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top" width="94"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties height="10" valign="top"/>
																																								<children>
																																									<line>
																																										<properties color="black" size="1"/>
																																									</line>
																																								</children>
																																							</tablecell>
																																						</children>
																																					</tablerow>
																																					<tablerow>
																																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																						<children>
																																							<tablecell>
																																								<properties valign="top" width="62"/>
																																								<styles font-size="9pt"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top" width="65"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top" width="94"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties align="right" valign="top"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="Total:"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top"/>
																																								<styles font-size="9pt"/>
																																								<children>
																																									<text fixtext="$"/>
																																									<autocalc xpath="sum(  CostSharingItem/Amount  )">
																																										<format string="###,##0.00" datatype="decimal"/>
																																									</autocalc>
																																								</children>
																																							</tablecell>
																																						</children>
																																					</tablerow>
																																					<tablerow>
																																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																						<children>
																																							<tablecell>
																																								<properties valign="top" width="62"/>
																																								<styles font-size="9pt"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top" width="65"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top" width="94"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties height="10" valign="top"/>
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
																												<properties valign="top"/>
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																												<children>
																													<text fixtext="Comments:">
																														<styles font-weight="bold"/>
																													</text>
																													<text fixtext=" ">
																														<styles font-size="12pt"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="75%"/>
																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																												<children>
																													<template match="Comments" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<paragraph paragraphtag="pre-wrap">
																																<children>
																																	<content>
																																		<styles line-height="9pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</paragraph>
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
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<table>
																<properties width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/IndirectCostRequired = &quot;1&quot;and  count(  AwardIndirectCosts  )  &gt; 0">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="AwardDetails/OtherHeaderDetails/SpecialEBRateOffCampus &gt; 0.0 or  AwardDetails/OtherHeaderDetails/SpecialEBRateOnCampus &gt; 0.0 or   string-length( AwardDetails/OtherHeaderDetails/SpecialRateComments )  &gt;=  1">
																						<children>
																							<text fixtext="Benefit Rates:">
																								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																							</text>
																							<table>
																								<properties border="0" width="100%"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="AwardDetails/OtherHeaderDetails/SpecialEBRateOffCampus &gt; 0.0  or  AwardDetails/OtherHeaderDetails/SpecialEBRateOnCampus &gt; 0.0">
																																		<children>
																																			<table>
																																				<properties border="0" width="100%"/>
																																				<children>
																																					<tablebody>
																																						<children>
																																							<tablerow>
																																								<children>
																																									<tablecell>
																																										<properties width="173"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																										<children>
																																											<text fixtext="Employee Benefit Rate:">
																																												<styles font-weight="bold"/>
																																											</text>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties width="90"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																										<children>
																																											<text fixtext="On-Campus">
																																												<styles font-weight="bold"/>
																																											</text>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties width="87"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																										<children>
																																											<template match="AwardDetails" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
																																														<children>
																																															<template match="SpecialEBRateOnCampus" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<content>
																																																		<format string="##0.00" datatype="decimal"/>
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
																																										<properties width="96"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																										<children>
																																											<text fixtext="Off-Campus">
																																												<styles font-weight="bold"/>
																																											</text>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																										<children>
																																											<template match="AwardDetails" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
																																														<children>
																																															<template match="SpecialEBRateOffCampus" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<content>
																																																		<format string="##0.00" datatype="decimal"/>
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
																											<tablerow>
																												<children>
																													<tablecell>
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( AwardDetails/OtherHeaderDetails/SpecialRateComments )  &gt; 0">
																																		<children>
																																			<table>
																																				<properties width="100%"/>
																																				<children>
																																					<tablebody>
																																						<children>
																																							<tablerow>
																																								<children>
																																									<tablecell>
																																										<properties valign="top" width="20%"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																										<children>
																																											<text fixtext="Special Rate Comments:">
																																												<styles font-weight="bold"/>
																																											</text>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top" width="80%"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																										<children>
																																											<template match="AwardDetails" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
																																														<children>
																																															<template match="SpecialRateComments" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<paragraph paragraphtag="pre-wrap">
																																																		<styles font-family="Arial"/>
																																																		<children>
																																																			<content>
																																																				<styles line-height="9pt"/>
																																																				<format datatype="string"/>
																																																			</content>
																																																		</children>
																																																	</paragraph>
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
																					</conditionbranch>
																				</children>
																			</condition>
																			<newline/>
																			<table>
																				<properties width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell/>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																			<newline/>
																			<condition>
																				<children>
																					<conditionbranch xpath="starts-with( AwardDetails/IDCIndicator , &quot;P&quot; )  or  (count(  AwardIndirectCosts/Comments  )  &gt; 0  and  string-length(  AwardIndirectCosts/Comments  )  &gt; 0 )">
																						<children>
																							<text fixtext="Indirect Cost:">
																								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																							</text>
																							<newline/>
																							<table>
																								<properties width="100%"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties colspan="2" valign="top" width="20"/>
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="starts-with( AwardDetails/IDCIndicator , &quot;P&quot; )">
																																		<children>
																																			<template match="AwardIndirectCosts" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<table>
																																						<properties border="0" width="100%"/>
																																						<children>
																																							<tableheader>
																																								<children>
																																									<tablerow>
																																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																										<children>
																																											<tablecell>
																																												<properties width="55"/>
																																												<styles padding-left="20pt"/>
																																												<children>
																																													<text fixtext="Rate">
																																														<styles font-weight="bold"/>
																																													</text>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties width="80"/>
																																												<styles padding-left="6pt"/>
																																												<children>
																																													<text fixtext="Type">
																																														<styles font-weight="bold"/>
																																													</text>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties width="38"/>
																																												<children>
																																													<text fixtext="Year">
																																														<styles font-weight="bold"/>
																																													</text>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties width="65"/>
																																												<children>
																																													<text fixtext="Start Date">
																																														<styles font-weight="bold"/>
																																													</text>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties width="65"/>
																																												<children>
																																													<text fixtext="End Date">
																																														<styles font-weight="bold"/>
																																													</text>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties width="60"/>
																																												<children>
																																													<text fixtext="On Campus">
																																														<styles font-weight="bold"/>
																																													</text>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties width="80"/>
																																												<children>
																																													<text fixtext="UnderRecovery">
																																														<styles font-weight="bold"/>
																																													</text>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties width="65"/>
																																												<children>
																																													<text fixtext="Source">
																																														<styles font-weight="bold"/>
																																													</text>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties width="70"/>
																																												<children>
																																													<text fixtext="Destination">
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
																																									<template match="IndirectCostSharingItem" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
																																										<children>
																																											<tablerow>
																																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																												<children>
																																													<tablecell>
																																														<properties valign="top" width="55"/>
																																														<styles padding-bottom="0" padding-left="20pt" padding-top="0"/>
																																														<children>
																																															<template match="ApplicableRate" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<content>
																																																		<format string="##0.00" datatype="decimal"/>
																																																	</content>
																																																</children>
																																															</template>
																																														</children>
																																													</tablecell>
																																													<tablecell>
																																														<properties valign="top" width="80"/>
																																														<styles padding-bottom="0" padding-left="6pt" padding-top="0"/>
																																														<children>
																																															<template match="IDCRateDescription" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<content>
																																																		<format datatype="string"/>
																																																	</content>
																																																</children>
																																															</template>
																																														</children>
																																													</tablecell>
																																													<tablecell>
																																														<properties valign="top" width="38"/>
																																														<styles padding-bottom="0" padding-top="0"/>
																																														<children>
																																															<template match="FiscalYear" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<content>
																																																		<format datatype="string"/>
																																																	</content>
																																																</children>
																																															</template>
																																														</children>
																																													</tablecell>
																																													<tablecell>
																																														<properties valign="top" width="65"/>
																																														<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																																														<children>
																																															<template match="StartDate" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<content>
																																																		<format string="MM/DD/YYYY" datatype="date"/>
																																																	</content>
																																																</children>
																																															</template>
																																														</children>
																																													</tablecell>
																																													<tablecell>
																																														<properties valign="top" width="65"/>
																																														<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																																														<children>
																																															<template match="EndDate" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<content>
																																																		<format string="MM/DD/YYYY" datatype="date"/>
																																																	</content>
																																																</children>
																																															</template>
																																														</children>
																																													</tablecell>
																																													<tablecell>
																																														<properties valign="top" width="60"/>
																																														<styles padding-bottom="0" padding-top="0"/>
																																														<children>
																																															<template match="Campus" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<condition>
																																																		<children>
																																																			<conditionbranch xpath=". = &apos;true&apos;">
																																																				<children>
																																																					<text fixtext="Yes"/>
																																																				</children>
																																																			</conditionbranch>
																																																			<conditionbranch>
																																																				<children>
																																																					<text fixtext="No"/>
																																																				</children>
																																																			</conditionbranch>
																																																		</children>
																																																	</condition>
																																																</children>
																																															</template>
																																														</children>
																																													</tablecell>
																																													<tablecell>
																																														<properties valign="top" width="80"/>
																																														<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																																														<children>
																																															<template match="UnderRecoveryAmount" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
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
																																														<properties valign="top" width="65"/>
																																														<styles padding-bottom="0" padding-top="0"/>
																																														<children>
																																															<template match="SourceAccount" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<paragraph paragraphtag="pre-wrap">
																																																		<children>
																																																			<content>
																																																				<format datatype="string"/>
																																																			</content>
																																																		</children>
																																																	</paragraph>
																																																</children>
																																															</template>
																																														</children>
																																													</tablecell>
																																													<tablecell>
																																														<properties valign="top" width="70"/>
																																														<styles padding-bottom="0" padding-top="0"/>
																																														<children>
																																															<template match="DestinationAccount" matchtype="schemagraphitem">
																																																<editorproperties elementstodisplay="1"/>
																																																<children>
																																																	<paragraph paragraphtag="pre-wrap">
																																																		<children>
																																																			<content>
																																																				<format datatype="string"/>
																																																			</content>
																																																		</children>
																																																	</paragraph>
																																																</children>
																																															</template>
																																														</children>
																																													</tablecell>
																																												</children>
																																											</tablerow>
																																										</children>
																																									</template>
																																									<tablerow>
																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																										<children>
																																											<tablecell>
																																												<properties valign="top" width="55"/>
																																												<styles padding-bottom="0" padding-left="20pt" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="80"/>
																																												<styles padding-bottom="0" padding-left="6pt" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="38"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="65"/>
																																												<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="65"/>
																																												<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="60"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="60"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																												<children>
																																													<line>
																																														<properties color="black" size="1"/>
																																													</line>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="65"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="70"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																										</children>
																																									</tablerow>
																																									<tablerow>
																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																										<children>
																																											<tablecell>
																																												<properties valign="top" width="55"/>
																																												<styles padding-bottom="0" padding-left="20pt" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="80"/>
																																												<styles padding-bottom="0" padding-left="6pt" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="38"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="65"/>
																																												<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="65"/>
																																												<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties align="right" valign="top"/>
																																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																												<children>
																																													<text fixtext="Total:"/>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="middle" width="80"/>
																																												<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																																												<children>
																																													<text fixtext="$"/>
																																													<autocalc xpath="sum(  IndirectCostSharingItem/UnderRecoveryAmount  )">
																																														<format string="###,##0.00" datatype="decimal"/>
																																													</autocalc>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="65"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="70"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																										</children>
																																									</tablerow>
																																									<tablerow>
																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																										<children>
																																											<tablecell>
																																												<properties valign="top" width="55"/>
																																												<styles padding-bottom="0" padding-left="20pt" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="80"/>
																																												<styles padding-bottom="0" padding-left="6pt" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="38"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="65"/>
																																												<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="65"/>
																																												<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="60"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="60"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																												<children>
																																													<line>
																																														<properties color="black" size="1"/>
																																													</line>
																																												</children>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="65"/>
																																												<styles padding-bottom="0" padding-top="0"/>
																																											</tablecell>
																																											<tablecell>
																																												<properties valign="top" width="70"/>
																																												<styles padding-bottom="0" padding-top="0"/>
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
																							<condition>
																								<children>
																									<conditionbranch xpath="count(  AwardIndirectCosts/Comments  )  &gt; 0  and  string-length(  AwardIndirectCosts/Comments  )  &gt; 0">
																										<children>
																											<newline/>
																											<table>
																												<properties width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<children>
																																			<table>
																																				<properties width="100%"/>
																																				<children>
																																					<tablebody>
																																						<children>
																																							<tablerow>
																																								<children>
																																									<tablecell>
																																										<properties valign="top" width="20%"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																										<children>
																																											<text fixtext="Indirect Cost Comments:">
																																												<styles font-weight="bold"/>
																																											</text>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top" width="80%"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																										<children>
																																											<template match="AwardIndirectCosts" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<template match="Comments" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
																																														<children>
																																															<paragraph paragraphtag="pre-wrap">
																																																<children>
																																																	<content>
																																																		<styles line-height="9pt"/>
																																																		<format datatype="string"/>
																																																	</content>
																																																</children>
																																															</paragraph>
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
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																							<newline/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<table>
																<properties width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/FlowThruRequired = &quot;1&quot; and   not ( (starts-with( AwardDetails/TransferSponsorIndicator , &quot;N&quot; ) and  string-length(AwardDetails/OtherHeaderDetails/PrimeSponsorCode) &lt;= 0 ))">
																		<children>
																			<text fixtext="Flow Thru:">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold" padding-top="10pt"/>
																			</text>
																			<newline/>
																			<condition>
																				<children>
																					<conditionbranch xpath="string-length(AwardDetails/OtherHeaderDetails/PrimeSponsorCode) &gt; 0">
																						<children>
																							<table>
																								<properties border="0" width="100%"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="160"/>
																														<children>
																															<text fixtext="Prime Sponsor:">
																																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<children>
																															<paragraph paragraphtag="pre-wrap">
																																<children>
																																	<template match="AwardDetails" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<template match="PrimeSponsorCode" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
																																						<children>
																																							<content>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																					<text fixtext=" ">
																																						<styles font-size="12pt" font-weight="bold"/>
																																					</text>
																																					<text fixtext=":">
																																						<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																																					</text>
																																					<text fixtext=" ">
																																						<styles font-size="12pt" font-weight="bold"/>
																																					</text>
																																					<template match="PrimeSponsorDescription" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
																																						<children>
																																							<content>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
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
																									</tablebody>
																								</children>
																							</table>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
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
																										<styles font-family="Arial" font-size="9pt" font-weight="normal" padding="0"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="not (starts-with( AwardDetails/TransferSponsorIndicator , &quot;N&quot;)) and count(  AwardTransferringSponsors/TransferringSponsor  )  &gt; 0">
																														<children>
																															<text fixtext="Sponsor Funding Transfered:">
																																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																															</text>
																															<template match="AwardTransferringSponsors" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<table>
																																		<properties border="0" width="100%"/>
																																		<children>
																																			<tableheader>
																																				<children>
																																					<tablerow>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<tablecell>
																																								<properties width="20"/>
																																								<styles padding-bottom="0" padding-top="0"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties align="right" width="29"/>
																																								<styles padding-bottom="0" padding-top="0"/>
																																								<children>
																																									<text fixtext=" "/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="0" padding-left="0" padding-top="0"/>
																																								<children>
																																									<text fixtext="Sponsor Name">
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
																																					<template match="TransferringSponsor" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
																																						<children>
																																							<tablerow>
																																								<children>
																																									<tablecell>
																																										<properties width="20"/>
																																									</tablecell>
																																									<tablecell>
																																										<properties align="right" valign="top" width="29"/>
																																										<styles padding-bottom="0" padding-top="0"/>
																																										<children>
																																											<text fixtext=" "/>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top"/>
																																										<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="0" padding-left="0" padding-top="0"/>
																																										<children>
																																											<paragraph paragraphtag="pre-wrap">
																																												<children>
																																													<template match="SponsorCode" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
																																														<children>
																																															<content>
																																																<format datatype="string"/>
																																															</content>
																																														</children>
																																													</template>
																																													<text fixtext=" : "/>
																																													<template match="SponsorDescription" matchtype="schemagraphitem">
																																														<editorproperties elementstodisplay="1"/>
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
																																			</tablebody>
																																		</children>
																																	</table>
																																</children>
																															</template>
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
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="731"/>
																						<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="(../PrintRequirement/ReportingRequired = 1 and AwardReportingDetails/ReportDetails/ReportTermDetails/ReportClassCode  != 4)">
																										<children>
																											<text fixtext="Reporting:">
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																											</text>
																											<template match="AwardReportingDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<table>
																														<properties border="0" width="100%"/>
																														<children>
																															<tablebody>
																																<children>
																																	<template match="ReportDetails" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<tablerow>
																																				<children>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="12pt" font-weight="bold" padding-top="10pt"/>
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="ReportTermDetails/ReportClassCode != 4">
																																										<children>
																																											<template match="Description" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<content>
																																														<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																																														<format datatype="string"/>
																																													</content>
																																												</children>
																																											</template>
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
																																						<properties height="1"/>
																																						<styles font-family="Arial" font-size="12pt" font-weight="bold" padding="0"/>
																																						<children>
																																							<template match="ReportTermDetails" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<condition>
																																										<children>
																																											<conditionbranch xpath="ReportClassCode != 4">
																																												<children>
																																													<table>
																																														<properties border="0pt" cellpadding="0pt" width="100%"/>
																																														<children>
																																															<tablebody>
																																																<children>
																																																	<tablerow>
																																																		<children>
																																																			<tablecell>
																																																				<properties width="160"/>
																																																				<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																																				<children>
																																																					<text fixtext="Type of Report: "/>
																																																				</children>
																																																			</tablecell>
																																																			<tablecell>
																																																				<styles font-family="Arial" font-weight="normal" padding-bottom="1pt"/>
																																																				<children>
																																																					<paragraph paragraphtag="pre-wrap">
																																																						<children>
																																																							<template match="ReportCodeDesc" matchtype="schemagraphitem">
																																																								<editorproperties elementstodisplay="1"/>
																																																								<children>
																																																									<content>
																																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
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
																																																	<tablerow>
																																																		<children>
																																																			<tablecell>
																																																				<properties width="160"/>
																																																				<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																																				<children>
																																																					<text fixtext="Frequency:"/>
																																																				</children>
																																																			</tablecell>
																																																			<tablecell>
																																																				<styles font-family="Arial" font-weight="normal" padding-bottom="1pt"/>
																																																				<children>
																																																					<paragraph paragraphtag="pre-wrap">
																																																						<children>
																																																							<template match="FrequencyCodeDesc" matchtype="schemagraphitem">
																																																								<editorproperties elementstodisplay="1"/>
																																																								<children>
																																																									<content>
																																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
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
																																																	<tablerow>
																																																		<children>
																																																			<tablecell>
																																																				<properties width="160"/>
																																																				<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																																				<children>
																																																					<text fixtext="Frequency Basis: "/>
																																																				</children>
																																																			</tablecell>
																																																			<tablecell>
																																																				<styles font-family="Arial" font-weight="normal" padding-bottom="1pt"/>
																																																				<children>
																																																					<paragraph paragraphtag="pre-wrap">
																																																						<children>
																																																							<template match="FrequencyBaseDesc" matchtype="schemagraphitem">
																																																								<editorproperties elementstodisplay="1"/>
																																																								<children>
																																																									<content>
																																																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
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
																																																	<tablerow>
																																																		<children>
																																																			<tablecell>
																																																				<properties width="160"/>
																																																				<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																																				<children>
																																																					<condition>
																																																						<children>
																																																							<conditionbranch xpath="DueDate != &apos;1900-01-01+00:00&apos;">
																																																								<children>
																																																									<text fixtext=" Due Date:"/>
																																																								</children>
																																																							</conditionbranch>
																																																						</children>
																																																					</condition>
																																																				</children>
																																																			</tablecell>
																																																			<tablecell>
																																																				<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="1pt"/>
																																																				<children>
																																																					<condition>
																																																						<children>
																																																							<conditionbranch xpath="DueDate != &apos;1900-01-01+00:00&apos;">
																																																								<children>
																																																									<template match="DueDate" matchtype="schemagraphitem">
																																																										<editorproperties elementstodisplay="1"/>
																																																										<children>
																																																											<content>
																																																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																																												<format string="MM/DD/YYYY" datatype="date"/>
																																																											</content>
																																																										</children>
																																																									</template>
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
																																																				<properties width="160"/>
																																																				<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																																				<children>
																																																					<text fixtext="OSP Distribution:"/>
																																																				</children>
																																																			</tablecell>
																																																			<tablecell>
																																																				<styles font-family="Arial" font-weight="normal" padding-bottom="1pt"/>
																																																				<children>
																																																					<template match="OSPDistributionDesc" matchtype="schemagraphitem">
																																																						<editorproperties elementstodisplay="1"/>
																																																						<children>
																																																							<content>
																																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
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
																																																				<properties colspan="2"/>
																																																				<styles padding="0" padding-bottom="0" padding-top="0"/>
																																																				<children>
																																																					<condition>
																																																						<children>
																																																							<conditionbranch xpath="MailCopies/NumberOfCopies != &quot;0&quot;">
																																																								<children>
																																																									<table>
																																																										<properties border="0" cellpadding="0" width="100%"/>
																																																										<children>
																																																											<tablebody>
																																																												<children>
																																																													<template match="MailCopies" matchtype="schemagraphitem">
																																																														<editorproperties elementstodisplay="1"/>
																																																														<children>
																																																															<tablerow>
																																																																<styles padding-top="10pt"/>
																																																																<children>
																																																																	<tablecell>
																																																																		<properties colspan="2"/>
																																																																		<styles font-family="Arial" font-size="9pt" font-weight="normal" padding="0" padding-bottom="0"/>
																																																																		<children>
																																																																			<paragraph paragraphtag="pre-wrap">
																																																																				<children>
																																																																					<text fixtext="Mail "/>
																																																																					<template match="NumberOfCopies" matchtype="schemagraphitem">
																																																																						<editorproperties elementstodisplay="1"/>
																																																																						<children>
																																																																							<content>
																																																																								<format datatype="string"/>
																																																																							</content>
																																																																						</children>
																																																																					</template>
																																																																					<text fixtext=" "/>
																																																																					<condition>
																																																																						<children>
																																																																							<conditionbranch xpath="NumberOfCopies  &gt; 1">
																																																																								<children>
																																																																									<text fixtext="copies"/>
																																																																								</children>
																																																																							</conditionbranch>
																																																																							<conditionbranch xpath="NumberOfCopies &lt; 2">
																																																																								<children>
																																																																									<text fixtext="copy"/>
																																																																								</children>
																																																																							</conditionbranch>
																																																																						</children>
																																																																					</condition>
																																																																					<text fixtext=" to "/>
																																																																					<template match="RolodexName" matchtype="schemagraphitem">
																																																																						<editorproperties elementstodisplay="1"/>
																																																																						<children>
																																																																							<content>
																																																																								<format datatype="string"/>
																																																																							</content>
																																																																						</children>
																																																																					</template>
																																																																					<template match="RolodexOrganization" matchtype="schemagraphitem">
																																																																						<editorproperties elementstodisplay="1"/>
																																																																						<children>
																																																																							<content>
																																																																								<format datatype="string"/>
																																																																							</content>
																																																																						</children>
																																																																					</template>
																																																																					<text fixtext=" (For complete address, see following address page Rolodex ID "/>
																																																																					<template match="RolodexId" matchtype="schemagraphitem">
																																																																						<editorproperties elementstodisplay="1"/>
																																																																						<children>
																																																																							<content>
																																																																								<format datatype="string"/>
																																																																							</content>
																																																																						</children>
																																																																					</template>
																																																																					<text fixtext=")"/>
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
																																											</conditionbranch>
																																										</children>
																																									</condition>
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
																											<newline/>
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
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/PaymentRequired = &quot;1&quot;">
																		<children>
																			<text fixtext="Payment:">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<table>
																								<properties width="100%"/>
																								<children>
																									<tablebody>
																										<properties valign="top"/>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties valign="top" width="160"/>
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																														<children>
																															<text fixtext="Basis of Payment:">
																																<styles font-size="9pt" font-weight="bold"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<template match="BasisPaymentDesc" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
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
																														<properties valign="top" width="160"/>
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																														<children>
																															<text fixtext="Method of Payment:">
																																<styles font-size="9pt" font-weight="bold"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<template match="PaymentMethodDesc" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
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
																							<condition>
																								<children>
																									<conditionbranch xpath="string-length(  PaymentFreqDesc  )  &gt; 0">
																										<children>
																											<newline/>
																											<table>
																												<properties border="0" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="160"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Payment/Invoice Frequency:">
																																				<styles font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<paragraph paragraphtag="pre-wrap">
																																				<children>
																																					<template match="PaymentFreqDesc" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
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
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																							<condition>
																								<children>
																									<conditionbranch xpath="InvoiceCopies &gt; 0">
																										<children>
																											<newline/>
																											<table>
																												<properties width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties height="21" valign="top" width="160"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Number of Copies:">
																																				<styles font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties height="21"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="InvoiceCopies" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<content>
																																						<format datatype="int"/>
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
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																							<condition>
																								<children>
																									<conditionbranch xpath="FinalInvoiceDue &gt;0">
																										<children>
																											<newline/>
																											<table>
																												<properties width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Final due within ">
																																				<styles font-size="9pt" font-weight="bold"/>
																																			</text>
																																			<template match="FinalInvoiceDue" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<content>
																																						<styles font-size="10pt" font-weight="bold"/>
																																						<format datatype="int"/>
																																					</content>
																																				</children>
																																			</template>
																																			<text fixtext=" days of expiration">
																																				<styles font-size="10pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
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
																							<newline/>
																							<condition>
																								<children>
																									<conditionbranch xpath="string-length( InvoiceInstructions )  &gt; 0">
																										<children>
																											<newline/>
																											<table>
																												<properties width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="160"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Special Invoice Instructions:">
																																				<styles font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="left" valign="top"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="InvoiceInstructions" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</paragraph>
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
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="not (starts-with(  AwardDetails/PaymentScheduleIndicator  , &quot;N&quot;)) and count(  AwardPaymentSchedules/PaymentSchedule  )  &gt; 0">
																		<children>
																			<text fixtext="Payment Schedule:">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																			<newline/>
																			<template match="AwardPaymentSchedules" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<table>
																						<properties border="0" width="40%"/>
																						<children>
																							<tableheader>
																								<children>
																									<tablerow>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<tablecell>
																												<properties width="20"/>
																											</tablecell>
																											<tablecell>
																												<properties width="140"/>
																												<children>
																													<text fixtext="Due Date">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<children>
																													<text fixtext="Amount">
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
																									<template match="PaymentSchedule" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<tablerow>
																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																												<children>
																													<tablecell>
																														<properties width="20"/>
																													</tablecell>
																													<tablecell>
																														<properties width="140"/>
																														<styles font-size="9pt"/>
																														<children>
																															<template match="DueDate" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY" datatype="date"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<children>
																															<template match="Amount" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
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
																									</template>
																									<tablerow>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<tablecell>
																												<properties width="20"/>
																											</tablecell>
																											<tablecell>
																												<properties width="140"/>
																												<styles font-size="9pt"/>
																											</tablecell>
																											<tablecell>
																												<properties height="5"/>
																												<styles font-size="9pt"/>
																												<children>
																													<line>
																														<properties color="black" size="1"/>
																													</line>
																												</children>
																											</tablecell>
																										</children>
																									</tablerow>
																									<tablerow>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<tablecell>
																												<properties width="20"/>
																											</tablecell>
																											<tablecell>
																												<properties align="right" valign="top"/>
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																												<children>
																													<text fixtext="Total:"/>
																												</children>
																											</tablecell>
																											<tablecell>
																												<children>
																													<text fixtext="$"/>
																													<autocalc xpath="sum(  PaymentSchedule/Amount  )">
																														<format string="###,##0.00" datatype="decimal"/>
																													</autocalc>
																												</children>
																											</tablecell>
																										</children>
																									</tablerow>
																									<tablerow>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<tablecell>
																												<properties width="20"/>
																											</tablecell>
																											<tablecell>
																												<properties width="140"/>
																												<styles font-size="9pt"/>
																											</tablecell>
																											<tablecell>
																												<properties height="5"/>
																												<styles font-size="9pt"/>
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
																			</template>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/PaymentRequired  = 1">
																		<children>
																			<table>
																				<properties border="0" width="100%"/>
																				<styles font-family="Arial"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Proposal Number:">
																												<styles font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="AwardFundingProposals" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="FundingProposal" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="ProposalNumber" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																															<condition>
																																<children>
																																	<conditionbranch xpath="position()  !=  last()">
																																		<children>
																																			<text fixtext=",    ">
																																				<styles font-size="9pt"/>
																																			</text>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="DFAFS Number:">
																												<styles font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="DFAFSNumber" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
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
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="CFDA Number:">
																												<styles font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="CFDANumber" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<autocalc xpath="concat(  substring( .,1 , 2 )   ,  &quot;.&quot; ,substring-after( . , substring( .,1 , 2 ) )  )"/>
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
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Procurement Priority Code:">
																												<styles font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="ProcurementPriorityCode" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
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
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Sub Plan:">
																												<styles font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="SubPlan" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath=". = &apos;y&apos; or . = &apos;Y&apos;">
																																				<children>
																																					<text fixtext="Yes">
																																						<styles font-family="Arial" font-size="9pt"/>
																																					</text>
																																				</children>
																																			</conditionbranch>
																																			<conditionbranch xpath=". = &apos;n&apos; or . = &apos;N&apos;">
																																				<children>
																																					<text fixtext="No">
																																						<styles font-family="Arial" font-size="9pt"/>
																																					</text>
																																				</children>
																																			</conditionbranch>
																																			<conditionbranch>
																																				<children>
																																					<text fixtext="Unknown">
																																						<styles font-family="Arial" font-size="9pt"/>
																																					</text>
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
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Award Last Update User:">
																												<styles font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="UpdateUser" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
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
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Award Last Update:">
																												<styles font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="LastUpdate" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY HH:mm:ss AM" datatype="dateTime"/>
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
																						</children>
																					</tablebody>
																				</children>
																			</table>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/CloseoutRequired = &quot;1&quot; and count( CloseOutDeadlines )  &gt; 0">
																		<children>
																			<text fixtext="Close-Out Deadlines:">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
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
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																									</tablecell>
																									<tablecell>
																										<properties width="70"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Due Date"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Submission Date"/>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Invoice:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="70"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="FinalInvDueDateModified" matchtype="schemagraphitem">
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
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="FinalInvSubDateModified" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
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
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Technical:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="70"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="FinalTechDueDateModified" matchtype="schemagraphitem">
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
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="FinalTechSubDateModified" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
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
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Patent:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="70"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="FinalPatentDueDateModified" matchtype="schemagraphitem">
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
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="FinalPatentSubDateModified" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
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
																										<properties width="160"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Property:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="70"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="FinalPropDueDateModified" matchtype="schemagraphitem">
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
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="FinalPropSubDateModified" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
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
																						</children>
																					</tablebody>
																				</children>
																			</table>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/SpecialReviewRequired = &quot;1&quot; and  starts-with(  AwardDetails/SpecialReviewIndicator  , &quot;P&quot;)">
																		<children>
																			<text fixtext="Special Review:">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<newline/>
																			<template match="AwardSpecialReviews" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<table>
																						<properties border="0"/>
																						<children>
																							<tableheader>
																								<children>
																									<tablerow>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<tablecell>
																												<properties width="20"/>
																											</tablecell>
																											<tablecell>
																												<children>
																													<text fixtext="Special Review">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<children>
																													<text fixtext="Approval type">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<children>
																													<text fixtext="Protocol Number">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<children>
																													<text fixtext="Application Date">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<children>
																													<text fixtext="Approval Date">
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
																									<template match="SpecialReview" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<tablerow>
																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																												<children>
																													<tablecell>
																														<properties width="20"/>
																														<styles padding-bottom="0" padding-top="0"/>
																													</tablecell>
																													<tablecell>
																														<styles padding-bottom="0" padding-top="0"/>
																														<children>
																															<template match="ReviewTypeDesc" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<styles padding-bottom="0" padding-top="0"/>
																														<children>
																															<template match="ApprovalTypeDesc" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<styles padding-bottom="0" padding-top="0"/>
																														<children>
																															<template match="ProtocolNumber" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																														<children>
																															<template match="ApplicationDate" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY" datatype="date"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<styles font-size="9pt"/>
																														<children>
																															<template match="ApprovalDate" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
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
																														<properties width="20"/>
																													</tablecell>
																													<tablecell>
																														<properties valign="top"/>
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																														<children>
																															<text fixtext="  ">
																																<styles font-size="12pt"/>
																															</text>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length()  &gt; 0">
																																		<children>
																																			<text fixtext="Comments">
																																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																			</text>
																																			<text fixtext=":">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
																															<newline/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties colspan="4" valign="top"/>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<template match="Comments" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath="string-length()  &gt; 0">
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<content>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal" line-height="9pt"/>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</paragraph>
																																				</children>
																																			</conditionbranch>
																																		</children>
																																	</condition>
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
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/OtherDataRequired  = &quot;1&quot;">
																		<children>
																			<text fixtext="Other Data:">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<newline/>
																			<newline/>
																			<template match="AwardOtherDatas" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="OtherData" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="OtherDetails" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<newline/>
																									<table>
																										<properties border="0" width="100%"/>
																										<children>
																											<tablebody>
																												<children>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties width="160"/>
																																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																																<children>
																																	<template match="Description" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<content>
																																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																</children>
																															</tablecell>
																															<tablecell/>
																														</children>
																													</tablerow>
																												</children>
																											</tablebody>
																										</children>
																									</table>
																									<template match="OtherGroupDetails" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<table>
																												<properties border="0" cellpadding="0pt" cellspacing="0pt" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																<children>
																																	<tablecell>
																																		<properties width="2%"/>
																																		<styles font-family="Arial" font-size="9pt"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="160"/>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<template match="ColumnName" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																			<text fixtext=":"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="98%"/>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<template match="ColumnValue" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
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
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/AddressListRequired = 1 and    count(  AwardContacts/ContactDetails ) &gt; 0">
																		<children>
																			<newline/>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell/>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																			<newline break="page"/>
																			<text fixtext="Address list for Account Number">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<text fixtext=" ">
																				<styles font-size="12pt"/>
																			</text>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AwardHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="AccountNumber" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<content>
																										<styles font-family="Arial" font-size="12pt"/>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<newline/>
																			<template match="AwardContacts" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<table>
																						<properties border="0" width="100%"/>
																						<styles font-family="Arial"/>
																						<children>
																							<tablebody>
																								<children>
																									<template match="ContactDetails" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties align="right" valign="top" width="132"/>
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																														<children>
																															<text fixtext="Rolodex Id:">
																																<styles font-weight="bold" text-decoration="underline"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties valign="top" width="80"/>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<template match="RolodexDetails" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<template match="RolodexId" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
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
																														<properties colspan="2" valign="top"/>
																														<children>
																															<paragraph paragraphtag="pre-wrap">
																																<children>
																																	<text fixtext="Contact Type:">
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold" text-decoration="underline"/>
																																	</text>
																																	<template match="ContactTypeDesc" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<text fixtext="   "/>
																																			<content>
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
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
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties colspan="4" width="80"/>
																														<styles padding="0" padding-top="1pt"/>
																														<children>
																															<template match="RolodexDetails" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<table>
																																		<properties border="0" width="100%"/>
																																		<children>
																																			<tablebody>
																																				<children>
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties align="right" width="130"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-top="5pt"/>
																																								<children>
																																									<text fixtext="Name:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties colspan="3"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-top="5pt"/>
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<template match="LastName" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
																																												<children>
																																													<content>
																																														<format datatype="string"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</paragraph>
																																									<text fixtext="    "/>
																																								</children>
																																							</tablecell>
																																						</children>
																																					</tablerow>
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties align="right" width="130"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="Organization:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties colspan="3"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<template match="Organization" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties align="right" width="130"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="Address:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties colspan="3"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<template match="Address1" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties width="130"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties colspan="3"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<template match="Address2" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties width="130"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties colspan="3"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<template match="Address3" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties align="right" width="130"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="Title:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<styles font-family="Arial" font-size="9pt"/>
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<template match="Title" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="1"/>
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
																																								<properties align="right" width="76"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="Phone:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<styles font-family="Arial" font-size="9pt"/>
																																								<children>
																																									<template match="PhoneNumber" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
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
																																								<properties align="right" width="130"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="City:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<styles font-family="Arial" font-size="9pt"/>
																																								<children>
																																									<template match="City" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
																																										<children>
																																											<content>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</template>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties align="right" width="76"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="State:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<styles font-family="Arial" font-size="9pt"/>
																																								<children>
																																									<template match="StateDescription" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
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
																																								<properties align="right" width="130"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="Postal Code:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<styles font-family="Arial" font-size="9pt"/>
																																								<children>
																																									<template match="Pincode" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
																																										<children>
																																											<content>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</template>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties align="right" width="76"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="Country:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<styles font-family="Arial" font-size="9pt"/>
																																								<children>
																																									<template match="CountryDescription" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
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
																																								<properties align="right" width="130"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="Fax:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<styles font-family="Arial" font-size="9pt"/>
																																								<children>
																																									<template match="Fax" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
																																										<children>
																																											<content>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</template>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties align="right" width="76"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																								<children>
																																									<text fixtext="E Mail:">
																																										<styles font-weight="bold"/>
																																									</text>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<styles font-family="Arial" font-size="9pt"/>
																																								<children>
																																									<template match="Email" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
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
																															</template>
																														</children>
																													</tablecell>
																												</children>
																											</tablerow>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties colspan="4" width="80"/>
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="position() !=  last()">
																																		<children>
																																			<line>
																																				<properties color="black" size="1"/>
																																			</line>
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
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/FundingSummaryRequired  = &quot;1&quot;">
																		<children>
																			<text fixtext="Funding Summary:">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<template match="AwardFundingSummary" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<table>
																						<properties border="0" width="100%"/>
																						<children>
																							<tablebody>
																								<children>
																									<tablerow>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<tablecell>
																												<properties width="200"/>
																											</tablecell>
																											<tablecell>
																												<properties width="150"/>
																												<children>
																													<text fixtext="Start Date">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties width="150"/>
																												<children>
																													<text fixtext="End Date">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<children>
																													<text fixtext="Total">
																														<styles font-weight="bold"/>
																													</text>
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
																					<table>
																						<properties border="0" width="100%"/>
																						<children>
																							<tablebody>
																								<children>
																									<tablerow>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<tablecell>
																												<properties width="200"/>
																												<children>
																													<text fixtext="Total Project Period:">
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties width="150"/>
																												<children>
																													<template match="FundingSummary" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="TotalStartDate" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
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
																												<properties width="150"/>
																												<children>
																													<template match="FundingSummary" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="TotalEndDate" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
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
																												<children>
																													<text fixtext="$"/>
																													<autocalc xpath="FundingSummary/AnticipatedTotalAmt">
																														<format string="#,###,###,##0.00" datatype="decimal"/>
																													</autocalc>
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
																					<template match="FundingSummary" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
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
																														<properties width="200"/>
																														<children>
																															<text fixtext="Seq."/>
																															<template match="SequenceNumber" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<format datatype="int"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="150"/>
																														<children>
																															<template match="CurrentFundEffectiveDate" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<format string="MM/DD/YYYY" datatype="date"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="150"/>
																														<children>
																															<template match="ObligationExpirationDate" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY" datatype="date"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<children>
																															<text fixtext="$"/>
																															<autocalc xpath="sum( ObligatedChange)">
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</autocalc>
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
																					<table>
																						<properties border="0" width="100%"/>
																						<children>
																							<tablebody>
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties height="10" width="200"/>
																											</tablecell>
																											<tablecell>
																												<properties height="10"/>
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
																					<table>
																						<properties border="0" width="100%"/>
																						<children>
																							<tablebody>
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties height="24" width="200"/>
																											</tablecell>
																											<tablecell>
																												<properties height="24" width="100"/>
																											</tablecell>
																											<tablecell>
																												<properties height="24" width="200"/>
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																												<children>
																													<text fixtext="Total Obligated:">
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties height="24"/>
																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																												<children>
																													<text fixtext="$"/>
																													<autocalc xpath="FundingSummary/AmtObligatedToDate">
																														<format string="#,###,###,##0.00" datatype="decimal"/>
																													</autocalc>
																												</children>
																											</tablecell>
																										</children>
																									</tablerow>
																									<tablerow>
																										<properties valign="top"/>
																										<children>
																											<tablecell>
																												<properties width="200"/>
																											</tablecell>
																											<tablecell>
																												<properties width="100"/>
																											</tablecell>
																											<tablecell>
																												<properties width="200"/>
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																												<children>
																													<text fixtext="Amount for ">
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																													</text>
																													<template match="FundingSummary" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="AwardNumber" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																												<children>
																													<text fixtext="$"/>
																													<autocalc xpath="FundingSummary/ObligatedDistributableAmt">
																														<format string="#,###,###,##0.00" datatype="decimal"/>
																													</autocalc>
																												</children>
																											</tablecell>
																										</children>
																									</tablerow>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties width="200"/>
																											</tablecell>
																											<tablecell>
																												<properties width="100"/>
																											</tablecell>
																											<tablecell>
																												<properties width="200"/>
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																												<children>
																													<text fixtext="Child Proj/Grant Total: ">
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																												<children>
																													<text fixtext="$"/>
																													<autocalc xpath="FundingSummary/AmtObligatedToDate   -   FundingSummary/ObligatedDistributableAmt">
																														<format string="#,###,###,##0.00" datatype="decimal"/>
																													</autocalc>
																												</children>
																											</tablecell>
																										</children>
																									</tablerow>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties width="200"/>
																											</tablecell>
																											<tablecell>
																												<properties width="100"/>
																											</tablecell>
																											<tablecell>
																												<properties width="200"/>
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																												<children>
																													<text fixtext="Remaining Anticipated:">
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																												<children>
																													<text fixtext="$"/>
																													<autocalc xpath="FundingSummary/AnticipatedTotalAmt -   FundingSummary/AmtObligatedToDate">
																														<format string="#,###,###,##0.00" datatype="decimal"/>
																													</autocalc>
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
																			</template>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
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
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts>
		<children>
			<globaltemplate match="AwardHeader" matchtype="schemagraphitem">
				<children>
					<template match="AwardHeader" matchtype="schemagraphitem">
						<editorproperties elementstodisplay="1"/>
						<children>
							<content>
								<styles font-family="Arial"/>
							</content>
						</children>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate match="AwardNotice" matchtype="schemagraphitem">
				<children>
					<template match="AwardNotice" matchtype="schemagraphitem">
						<editorproperties elementstodisplay="5"/>
						<children>
							<content/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</globalparts>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.2in" papermarginright="0.1in" papermargintop="1.2in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="1"/>
						<children>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Arial" font-size="9pt" table-layout="fixed"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="4" height="18" width="30%"/>
														<styles padding="0" padding-bottom="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="20%"/>
														<styles font-family="Verdana" font-size="9pt" padding-bottom="0"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="SchoolInfo" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="Acronym" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<content>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" Account Number: ">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="bottom" width="30%"/>
														<styles padding-bottom="0" padding-left="0" padding-top="1pt"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AwardHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="AccountNumber" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". != 0">
																												<children>
																													<content>
																														<styles font-family="Arial" font-size="9pt"/>
																														<format datatype="string"/>
																													</content>
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
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="35%"/>
														<styles font-size="9pt" padding-bottom="0" padding-right="5pt"/>
														<children>
															<text fixtext="Page: ">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-size="9pt" padding-bottom="0"/>
														<children>
															<field>
																<styles font-family="Arial" font-size="9pt"/>
															</field>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="20%"/>
														<styles padding-top="0"/>
														<children>
															<text fixtext="PI:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="30%"/>
														<styles padding-left="0" padding-top="0"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AwardHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="PIName" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<content>
																										<styles font-family="Arial" font-size="9pt"/>
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
														<properties align="right" valign="top" width="35%"/>
														<styles padding-right="6pt" padding-top="0"/>
														<children>
															<text fixtext="Date:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="bottom" width="15%"/>
														<styles font-size="9pt" padding-top="0"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="PrintRequirement" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="CurrentDate" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<content>
																						<styles font-family="Arial" font-size="9pt"/>
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="4" valign="top" width="30%"/>
														<styles font-family="Verdana" font-size="9pt" padding="0" padding-top="0"/>
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
					</template>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
