<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="MM/DD/YYYY" datatype="date"/>
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
						<children>
							<condition>
								<children>
									<conditionbranch xpath="count(  AwardNotice/AwardDisclosure/DisclosureItem  )  &gt; 0 or  count(   AwardNotice/AwardDisclosure/AwardValidation )  &gt; 0">
										<children>
											<template match="AwardNotice" matchtype="schemagraphitem">
												<children>
													<template match="SchoolInfo" matchtype="schemagraphitem">
														<children>
															<newline/>
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
																	<text fixtext="Award Modification - Hold Notice">
																		<styles font-family="Arial" font-size="14pt" font-weight="bold"/>
																	</text>
																	<newline/>
																	<newline>
																		<properties clear="none"/>
																	</newline>
																</children>
															</paragraph>
															<newline/>
														</children>
													</template>
												</children>
											</template>
											<newline/>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt" line-height="9pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<children>
																			<text fixtext="Account Number:">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial" font-size="9pt"/>
																		<children>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<children>
																									<template match="AccountNumber" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath=". != 0">
																														<children>
																															<content>
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
																			<text fixtext="Award Number : ">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																			<text fixtext="  "/>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<children>
																									<template match="AwardNumber" matchtype="schemagraphitem">
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
																			<text fixtext="                          "/>
																			<text fixtext="Sequence: ">
																				<styles font-weight="bold"/>
																			</text>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<children>
																									<template match="SequenceNumber" matchtype="schemagraphitem">
																										<children>
																											<content>
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
																			<text fixtext="               "/>
																			<text fixtext="Status: ">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<children>
																									<template match="StatusDescription" matchtype="schemagraphitem">
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
																			<text fixtext="             "/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<children>
																			<text fixtext="Modification No:">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-size="9pt"/>
																		<children>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<children>
																									<template match="ModificationNumber" matchtype="schemagraphitem">
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
																		<children>
																			<text fixtext="Sponsor Award No:">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-size="9pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="substring-after(  AwardNotice/AwardDisclosure/AwardHeader/SponsorAwardNumber  ,  substring( AwardNotice/AwardDisclosure/AwardHeader/SponsorAwardNumber , 1 ,  string-length(AwardNotice/AwardDisclosure/AwardHeader/SponsorAwardNumber) -1 )  ) = &quot;*&quot;">
																						<children>
																							<autocalc xpath="substring-before(  AwardNotice/AwardDisclosure/AwardHeader/SponsorAwardNumber  , &quot;*&quot; )"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<children>
																									<template match="AwardDisclosure" matchtype="schemagraphitem">
																										<children>
																											<template match="AwardHeader" matchtype="schemagraphitem">
																												<children>
																													<template match="SponsorAwardNumber" matchtype="schemagraphitem">
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
																		<properties align="right" width="20%"/>
																		<children>
																			<text fixtext="Sponsor:">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-size="9pt"/>
																		<children>
																			<template match="AwardNotice" matchtype="schemagraphitem">
																				<children>
																					<template match="AwardDisclosure" matchtype="schemagraphitem">
																						<children>
																							<template match="AwardHeader" matchtype="schemagraphitem">
																								<children>
																									<template match="SponsorCode" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																									<text fixtext="  "/>
																									<condition>
																										<children>
																											<conditionbranch xpath="substring-after(   SponsorDescription  ,  substring( SponsorDescription , 1 ,  string-length(SponsorDescription) -1 )  ) = &quot;*&quot;">
																												<children>
																													<autocalc xpath="substring-before(  SponsorDescription  , &quot;*&quot;)"/>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
																												<children>
																													<template match="SponsorDescription" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																									<text fixtext=" "/>
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
																		<children>
																			<text fixtext="Prime Sponsor:">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial" font-size="9pt"/>
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
																											<condition>
																												<children>
																													<conditionbranch xpath="substring-after(    PrimeSponsorDescription ,  substring(  PrimeSponsorDescription , 1 ,  string-length( PrimeSponsorDescription ) -1 )  ) = &quot;*&quot;">
																														<children>
																															<autocalc xpath="substring-before(  SponsorDescription  , &quot;*&quot;)"/>
																														</children>
																													</conditionbranch>
																													<conditionbranch>
																														<children>
																															<template match="PrimeSponsorDescription" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
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
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<children>
																			<text fixtext="Title:">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-size="9pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="substring-after(  AwardNotice/AwardDisclosure/AwardHeader/Title,  substring( AwardNotice/AwardDisclosure/AwardHeader/Title , 1 ,  string-length(AwardNotice/AwardDisclosure/AwardHeader/Title) -1 )  ) = &quot;*&quot;">
																						<children>
																							<autocalc xpath="substring-before(  AwardNotice/AwardDisclosure/AwardHeader/Title  , &quot;*&quot; )"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<children>
																									<template match="AwardDisclosure" matchtype="schemagraphitem">
																										<children>
																											<template match="AwardHeader" matchtype="schemagraphitem">
																												<children>
																													<template match="Title" matchtype="schemagraphitem">
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
																		<properties colspan="2" width="20%"/>
																		<styles font-family="Arial" font-size="12pt" padding="0" padding-left="0"/>
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
																																				<properties width="15%"/>
																																				<children>
																																					<template match="DisclosureTypeDesc" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="1"/>
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																					<newline/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="20%"/>
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
															<newline/>
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
							<newline/>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Times New Roman" font-size="9pt" line-height="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties valign="top"/>
														<styles padding-top="0"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<children>
																	<template match="SchoolInfo" matchtype="schemagraphitem">
																		<children>
																			<newline/>
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
																					<text fixtext="Award Modification">
																						<styles font-family="Arial" font-size="14pt" font-weight="bold"/>
																					</text>
																					<newline/>
																					<newline>
																						<properties clear="none"/>
																					</newline>
																				</children>
																			</paragraph>
																			<newline/>
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
														<properties height="130"/>
														<styles padding="0"/>
														<children>
															<newline/>
															<table>
																<properties width="100%"/>
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																<children>
																	<tablebody>
																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																				<children>
																					<tablecell>
																						<properties colspan="2" width="30%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal" white-space="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="SchoolInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="Acronym" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
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
																							<newline/>
																							<text fixtext="Account Number:">
																								<styles font-weight="bold"/>
																							</text>
																							<text fixtext="  "/>
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
																						<properties align="left" colspan="2" width="30%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal" white-space="normal"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="SchoolInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="Acronym" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																							<newline/>
																							<text fixtext="Award Number:  ">
																								<styles font-weight="bold"/>
																							</text>
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
																						<properties align="left" valign="bottom" width="15%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<text fixtext="Sequence:">
																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																							</text>
																							<text fixtext=" "/>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<children>
																													<template match="AwardHeader" matchtype="schemagraphitem">
																														<children>
																															<template match="SequenceNumber" matchtype="schemagraphitem">
																																<children>
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
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties valign="bottom" width="25%"/>
																						<children>
																							<text fixtext="Status: ">
																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																							</text>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<children>
																													<template match="AwardHeader" matchtype="schemagraphitem">
																														<children>
																															<template match="StatusDescription" matchtype="schemagraphitem">
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
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<table>
																<styles font-family="Arial"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties valign="middle" width="175"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<text fixtext="Investigator:">
																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																							</text>
																							<newline/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties valign="middle" width="175"/>
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
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																															<condition>
																																<children>
																																	<conditionbranch xpath="../PrincipalInvestigator  =  1 or ../PrincipalInvestigator = &apos;true&apos;">
																																		<children>
																																			<text fixtext="(PI)"/>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																																		</children>
																																	</template>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath="../LeadUnit  = 1 or ../LeadUnit = &apos;true&apos;">
																																				<children>
																																					<text fixtext="(LU)"/>
																																				</children>
																																			</conditionbranch>
																																		</children>
																																	</condition>
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
																<properties border="0" cellpadding="1px" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="13%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<text fixtext="Sponsor:">
																								<styles font-weight="bold"/>
																							</text>
																							<text fixtext="  "/>
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
																					<tablecell>
																						<properties width="13%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Award Number:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="2" width="17%"/>
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
																					</tablecell>
																					<tablecell>
																						<properties width="40%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-right="20pt"/>
																						<children>
																							<text fixtext="Modification Number:  ">
																								<styles font-weight="bold"/>
																							</text>
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
																															<button>
																																<action>
																																	<datepicker/>
																																</action>
																															</button>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="ExecutionDateModified" matchtype="schemagraphitem">
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
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0"/>
														<children>
															<newline/>
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
																								<children>
																									<tablecell>
																										<properties colspan="4" height="33"/>
																										<styles line-height="13pt" padding="6pt"/>
																										<children>
																											<text fixtext="Signatures on Notice of Award REQUIRED.  Please indicate your approval of the terms and conditions identified in the document and return this notice to ">
																												<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																											</text>
																											<template match="AwardNotice" matchtype="schemagraphitem">
																												<children>
																													<template match="Award" matchtype="schemagraphitem">
																														<children>
																															<template match="AwardDetails" matchtype="schemagraphitem">
																																<children>
																																	<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																		<children>
																																			<template match="FellowshipAdminName" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
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
																												<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
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
																																		<styles line-height="13pt" padding-left="36pt"/>
																																		<children>
																																			<text fixtext="PI Signature :">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" valign="bottom" width="50%"/>
																																		<styles line-height="13pt"/>
																																		<children>
																																			<text fixtext="______________________________">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="10%"/>
																																		<styles line-height="13pt"/>
																																		<children>
																																			<text fixtext="Date:">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="20%"/>
																																		<styles line-height="13pt"/>
																																		<children>
																																			<text fixtext="_______________">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="left" width="20%"/>
																																		<styles line-height="13pt" padding-left="36pt"/>
																																		<children>
																																			<text fixtext="AO Signature :">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="50%"/>
																																		<styles line-height="13pt"/>
																																		<children>
																																			<text fixtext="______________________________">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="10%"/>
																																		<styles line-height="13pt"/>
																																		<children>
																																			<text fixtext="Date:">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="20%"/>
																																		<styles line-height="13pt"/>
																																		<children>
																																			<text fixtext="_______________">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="left" width="20%"/>
																																		<styles line-height="13pt" padding-left="36pt"/>
																																		<children>
																																			<text fixtext="OSP Signature :">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="50%"/>
																																		<styles line-height="13pt"/>
																																		<children>
																																			<text fixtext="______________________________">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="10%"/>
																																		<styles line-height="13pt"/>
																																		<children>
																																			<text fixtext="Date:">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="20%"/>
																																		<styles line-height="13pt"/>
																																		<children>
																																			<text fixtext="_______________">
																																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
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
																			<newline/>
																			<table>
																				<properties border="1" width="100%"/>
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
																			<paragraph paragraphtag="p"/>
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
														<styles padding="0"/>
														<children>
															<table>
																<properties width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="5" width="25%"/>
																						<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																						<children>
																							<newline/>
																							<text fixtext="PROJECT PERIOD:">
																								<styles font-weight="bold"/>
																							</text>
																							<newline/>
																							<table>
																								<properties border="0" width="100%"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="30%"/>
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																														<children>
																															<text fixtext="Effective Date:">
																																<styles font-weight="bold"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="70%"/>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<template match="AwardNotice" matchtype="schemagraphitem">
																																<children>
																																	<template match="Award" matchtype="schemagraphitem">
																																		<children>
																																			<template match="AwardDetails" matchtype="schemagraphitem">
																																				<children>
																																					<template match="EffectiveDate" matchtype="schemagraphitem">
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
																															<template match="AwardNotice" matchtype="schemagraphitem">
																																<children>
																																	<template match="Award" matchtype="schemagraphitem">
																																		<children>
																																			<template match="AwardDetails" matchtype="schemagraphitem">
																																				<children>
																																					<template match="EffectiveDateModified" matchtype="schemagraphitem">
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
																							<newline/>
																							<condition>
																								<children>
																									<conditionbranch xpath="count( AwardNotice/Award/AwardAmountInfo  )  = 1">
																										<children>
																											<table>
																												<properties width="100%"/>
																												<children>
																													<tablebody>
																														<properties valign="top"/>
																														<children>
																															<tablerow>
																																<properties valign="top"/>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Final Expiration Date:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="70%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="AwardNotice" matchtype="schemagraphitem">
																																						<children>
																																							<template match="Award" matchtype="schemagraphitem">
																																								<children>
																																									<template match="AwardAmountInfo" matchtype="schemagraphitem">
																																										<children>
																																											<template match="AmountInfo" matchtype="schemagraphitem">
																																												<children>
																																													<template match="FinalExpirationDate" matchtype="schemagraphitem">
																																														<children>
																																															<content>
																																																<styles font-size="9pt"/>
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
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="AwardNotice" matchtype="schemagraphitem">
																																						<children>
																																							<template match="Award" matchtype="schemagraphitem">
																																								<children>
																																									<template match="AwardAmountInfo" matchtype="schemagraphitem">
																																										<children>
																																											<template match="AmountInfo" matchtype="schemagraphitem">
																																												<children>
																																													<template match="FinalExpDateModified" matchtype="schemagraphitem">
																																														<children>
																																															<content>
																																																<styles font-size="9pt"/>
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
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<properties valign="top"/>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Award Transaction Type:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="70%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="AwardNotice" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="Award" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<template match="AwardTransactionInfo" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="5"/>
																																								<children>
																																									<template match="TransactionInfo" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="5"/>
																																										<children>
																																											<template match="TransactionTypeDesc" matchtype="schemagraphitem">
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
																															<tablerow>
																																<properties valign="top"/>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Notice Date:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="70%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="AwardNotice" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="Award" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<template match="AwardTransactionInfo" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="5"/>
																																								<children>
																																									<template match="TransactionInfo" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="5"/>
																																										<children>
																																											<template match="NoticeDate" matchtype="schemagraphitem">
																																												<editorproperties elementstodisplay="5"/>
																																												<children>
																																													<content>
																																														<format string="MM/DD/YYYY" datatype="date"/>
																																													</content>
																																													<button>
																																														<action>
																																															<datepicker/>
																																														</action>
																																													</button>
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
																																<properties valign="top"/>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Comments:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="70%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="AwardNotice" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="Award" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<template match="AwardTransactionInfo" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="5"/>
																																								<children>
																																									<template match="TransactionInfo" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="5"/>
																																										<children>
																																											<template match="Comments" matchtype="schemagraphitem">
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
																															<tablerow>
																																<properties valign="top"/>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Total Change to Anticipated Award:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="70%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="AwardNotice" matchtype="schemagraphitem">
																																						<children>
																																							<template match="Award" matchtype="schemagraphitem">
																																								<children>
																																									<template match="AwardAmountInfo" matchtype="schemagraphitem">
																																										<children>
																																											<template match="AmountInfo" matchtype="schemagraphitem">
																																												<children>
																																													<template match="AnticipatedChange" matchtype="schemagraphitem">
																																														<children>
																																															<text fixtext="$"/>
																																															<content>
																																																<styles font-size="9pt"/>
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
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="boolean( AwardNotice/Award/AwardAmountInfo/AmountInfo/AnticipatedChange )  and AwardNotice/Award/AwardAmountInfo/AmountInfo/AnticipatedChange != 0">
																																						<children>
																																							<text fixtext=" *"/>
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
																																						<properties width="30%"/>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<text fixtext="Change to Direct Anticipated Award:"/>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties width="70%"/>
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
																																																		<format datatype="decimal"/>
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
																																						<properties width="30%"/>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<text fixtext="Change to Indirect Anticipated Award"/>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties width="40%"/>
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
																																																		<format datatype="decimal"/>
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
																																		<properties width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Total Anticipated Award:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="70%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-top="10pt"/>
																																		<children>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="AwardNotice" matchtype="schemagraphitem">
																																						<children>
																																							<template match="Award" matchtype="schemagraphitem">
																																								<children>
																																									<template match="AwardAmountInfo" matchtype="schemagraphitem">
																																										<children>
																																											<template match="AmountInfo" matchtype="schemagraphitem">
																																												<children>
																																													<template match="AnticipatedTotalAmt" matchtype="schemagraphitem">
																																														<children>
																																															<text fixtext="$"/>
																																															<content>
																																																<styles font-size="9pt"/>
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
																																						<properties width="30%"/>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<text fixtext="Direct Anticipated Total Award:">
																																								<styles font-weight="bold"/>
																																							</text>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties width="70%"/>
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
																																						<properties width="30%"/>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<text fixtext="Indirect Anticipated Total Award:">
																																								<styles font-weight="bold"/>
																																							</text>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties width="40%"/>
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
																												<styles font-weight="bold"/>
																											</text>
																											<newline/>
																											<newline/>
																											<table>
																												<properties border="0" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<properties valign="top"/>
																																<children>
																																	<tablecell>
																																		<properties width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Effective Date of Current Obligation:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="70%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="AwardNotice" matchtype="schemagraphitem">
																																						<children>
																																							<template match="Award" matchtype="schemagraphitem">
																																								<children>
																																									<template match="AwardAmountInfo" matchtype="schemagraphitem">
																																										<children>
																																											<template match="AmountInfo" matchtype="schemagraphitem">
																																												<children>
																																													<template match="CurrentFundEffectiveDate" matchtype="schemagraphitem">
																																														<children>
																																															<content>
																																																<styles font-size="9pt"/>
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
																																			<template match="AwardNotice" matchtype="schemagraphitem">
																																				<children>
																																					<template match="Award" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AwardAmountInfo" matchtype="schemagraphitem">
																																								<children>
																																									<template match="AmountInfo" matchtype="schemagraphitem">
																																										<children>
																																											<template match="CurrentFundEffectiveDateModified" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<styles font-size="9pt"/>
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
																															<tablerow>
																																<properties valign="top"/>
																																<children>
																																	<tablecell>
																																		<properties width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Expiration Date of Current Obligation:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="70%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="AwardNotice" matchtype="schemagraphitem">
																																				<children>
																																					<template match="Award" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AwardAmountInfo" matchtype="schemagraphitem">
																																								<children>
																																									<template match="AmountInfo" matchtype="schemagraphitem">
																																										<children>
																																											<template match="ObligationExpirationDate" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<styles font-size="9pt"/>
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
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="AwardNotice" matchtype="schemagraphitem">
																																						<children>
																																							<template match="Award" matchtype="schemagraphitem">
																																								<children>
																																									<template match="AwardAmountInfo" matchtype="schemagraphitem">
																																										<children>
																																											<template match="AmountInfo" matchtype="schemagraphitem">
																																												<children>
																																													<template match="ObligationExpDateModified" matchtype="schemagraphitem">
																																														<children>
																																															<content>
																																																<styles font-size="9pt"/>
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
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<properties valign="top"/>
																																<children>
																																	<tablecell>
																																		<properties width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="T">
																																				<styles font-weight="bold" padding-top="10pt"/>
																																			</text>
																																			<text fixtext="otal Change to Amount Obligated:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="70%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="AwardNotice" matchtype="schemagraphitem">
																																				<children>
																																					<template match="Award" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AwardAmountInfo" matchtype="schemagraphitem">
																																								<children>
																																									<template match="AmountInfo" matchtype="schemagraphitem">
																																										<children>
																																											<template match="ObligatedChange" matchtype="schemagraphitem">
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
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="boolean( AwardNotice/Award/AwardAmountInfo/AmountInfo/ObligatedChange )  and   AwardNotice/Award/AwardAmountInfo/AmountInfo/ObligatedChange  != 0">
																																						<children>
																																							<text fixtext=" *"/>
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
																																		<properties width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Change to Direct Amount Obligated:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="70%"/>
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
																																		<properties width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Change to Indirect Amount Obligated:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="40%"/>
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
																							<table>
																								<properties border="0" width="100%"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="30%"/>
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																														<children>
																															<text fixtext="Total Amount Obligated:">
																																<styles font-weight="bold"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="70%"/>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<template match="AwardNotice" matchtype="schemagraphitem">
																																<children>
																																	<template match="Award" matchtype="schemagraphitem">
																																		<children>
																																			<template match="AwardAmountInfo" matchtype="schemagraphitem">
																																				<children>
																																					<template match="AmountInfo" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AmtObligatedToDate" matchtype="schemagraphitem">
																																								<children>
																																									<text fixtext="$"/>
																																									<content>
																																										<styles font-size="9pt"/>
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
																															<condition>
																																<children>
																																	<conditionbranch xpath="boolean( AwardNotice/Award/AwardAmountInfo/AmountInfo/ObligatedChange )  and   AwardNotice/Award/AwardAmountInfo/AmountInfo/ObligatedChange  != 0">
																																		<children>
																																			<text fixtext=" *"/>
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
																																		<properties width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Direct Amount Obligated:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="70%"/>
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
																																		<properties width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Indirect Amount Obligated:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="70%"/>
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
																											<newline/>
																											<newline/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="30%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Type of Activity:">
																								<styles font-size="9pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties colspan="4" width="70%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<children>
																															<template match="ActivityTypeDesc" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="9pt"/>
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="30%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Type of Award:">
																								<styles font-size="9pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties colspan="4" width="70%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<children>
																															<template match="AwardTypeDesc" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="9pt"/>
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="30%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Type of Account:">
																								<styles font-size="9pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<template match="AwardNotice" matchtype="schemagraphitem">
																								<children>
																									<template match="Award" matchtype="schemagraphitem">
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<children>
																															<template match="AccountTypeDesc" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="9pt"/>
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
																						<properties colspan="2" width="18%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="substring(AwardNotice/Award/AwardDetails/AwardHeader/AwardNumber ,8)!=&quot;001&quot;">
																										<children>
																											<newline/>
																											<text fixtext="Root Account Number :">
																												<styles font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="27%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="substring(AwardNotice/Award/AwardDetails/AwardHeader/AwardNumber ,8)!=&quot;001&quot;">
																										<children>
																											<newline/>
																											<template match="AwardNotice" matchtype="schemagraphitem">
																												<children>
																													<template match="Award" matchtype="schemagraphitem">
																														<children>
																															<template match="AwardDetails" matchtype="schemagraphitem">
																																<children>
																																	<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																		<children>
																																			<template match="RootAccountNumber" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<styles font-size="9pt"/>
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
																											<newline/>
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
																						<properties colspan="5" width="25%"/>
																						<styles font-family="Arial" font-size="12pt" font-weight="bold" padding="0"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="(boolean( AwardNotice/Award/AwardDetails/OtherHeaderDetails/PreAwardAuthorizedAmt )  =  true() and  number(AwardNotice/Award/AwardDetails/OtherHeaderDetails/PreAwardAuthorizedAmt) &gt;0 ) or boolean( AwardNotice/Award/AwardDetails/OtherHeaderDetails/PreAwardEffectiveDate )">
																										<children>
																											<text fixtext="Pre Award:">
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																											</text>
																											<table>
																												<properties border="0" width="100%"/>
																												<styles font-weight="bold"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Authorized Amount:">
																																				<styles font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="40%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-left="4pt"/>
																																		<children>
																																			<template match="AwardNotice" matchtype="schemagraphitem">
																																				<children>
																																					<template match="Award" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AwardDetails" matchtype="schemagraphitem">
																																								<children>
																																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																										<children>
																																											<template match="PreAwardAuthorizedAmt" matchtype="schemagraphitem">
																																												<children>
																																													<text fixtext="$"/>
																																													<content>
																																														<styles font-size="9pt"/>
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
																																			<template match="AwardNotice" matchtype="schemagraphitem">
																																				<children>
																																					<template match="Award" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AwardDetails" matchtype="schemagraphitem">
																																								<children>
																																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																										<children>
																																											<template match="PreAwardAuthorizedAmtModifeid" matchtype="schemagraphitem">
																																												<children>
																																													<text fixtext="$"/>
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
																																		<properties colspan="2" width="51%"/>
																																		<styles padding-left="5pt"/>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="30%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Effective Date:">
																																				<styles font-size="9pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="40%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-left="4pt"/>
																																		<children>
																																			<template match="AwardNotice" matchtype="schemagraphitem">
																																				<children>
																																					<template match="Award" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AwardDetails" matchtype="schemagraphitem">
																																								<children>
																																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																										<children>
																																											<template match="PreAwardEffectiveDate" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<styles font-size="9pt"/>
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
																																			<template match="AwardNotice" matchtype="schemagraphitem">
																																				<children>
																																					<template match="Award" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AwardDetails" matchtype="schemagraphitem">
																																								<children>
																																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																										<children>
																																											<template match="PreAwardEffectiveDateModifeid" matchtype="schemagraphitem">
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
																																		<properties colspan="2" width="51%"/>
																																		<styles padding-left="5pt"/>
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
							<template match="AwardNotice" matchtype="schemagraphitem">
								<children>
									<newline/>
									<template match="Award" matchtype="schemagraphitem">
										<children>
											<template match="AwardDetails" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="1"/>
												<children>
													<newline/>
													<table>
														<properties width="100%"/>
														<styles font-family="Arial"/>
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<styles font-family="Arial"/>
																		<children>
																			<tablecell>
																				<properties width="30%"/>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<text fixtext=" AO:">
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
																					</template>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="18%"/>
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
																			<template match="ChildAwardDetails" matchtype="schemagraphitem">
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
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																												<children>
																													<text fixtext="PI">
																														<styles font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																												<children>
																													<text fixtext="Account Number">
																														<styles font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																												<children>
																													<text fixtext="Start Date">
																														<styles font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																												<children>
																													<text fixtext="End Date">
																														<styles font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																												<children>
																													<text fixtext="Total">
																														<styles font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																										</children>
																									</tablerow>
																								</children>
																							</tableheader>
																							<tablebody>
																								<children>
																									<template match="ChildAward" matchtype="schemagraphitem">
																										<children>
																											<tablerow>
																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																												<children>
																													<tablecell>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<template match="PIName" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="9pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<template match="AccountNumber" matchtype="schemagraphitem">
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath=". &gt;0">
																																				<children>
																																					<content>
																																						<styles font-size="9pt"/>
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
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<template match="CurrentFundEffectiveDate" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="9pt"/>
																																		<format datatype="date"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<template match="ObligationExpirationDate" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="9pt"/>
																																		<format datatype="date"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<text fixtext="$">
																																<styles font-size="9pt"/>
																															</text>
																															<template match="AmtObligatedToDate" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="9pt"/>
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
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																											</tablecell>
																											<tablecell>
																												<properties height="22"/>
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																											</tablecell>
																											<tablecell>
																												<properties colspan="3" height="22" valign="top"/>
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																												<children>
																													<line>
																														<properties color="black" size="0.3"/>
																													</line>
																												</children>
																											</tablecell>
																										</children>
																									</tablerow>
																									<tablerow>
																										<children>
																											<tablecell>
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																											</tablecell>
																											<tablecell>
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																											</tablecell>
																											<tablecell>
																												<properties align="center" colspan="2"/>
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																												<children>
																													<text fixtext="Total Obligated:">
																														<styles font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																												<children>
																													<text fixtext="$">
																														<styles font-size="9pt"/>
																													</text>
																													<autocalc xpath="sum( ChildAward/AmtObligatedToDate )">
																														<styles font-size="9pt"/>
																														<format string="MM/DD/YYYY" datatype="string"/>
																													</autocalc>
																												</children>
																											</tablecell>
																										</children>
																									</tablerow>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties colspan="5"/>
																												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
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
											<condition>
												<children>
													<conditionbranch xpath="substring(   AwardDetails/ScienceCodeIndicator  , 2 ) = &quot;1&quot;">
														<children>
															<text fixtext="Science Code:">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<template match="AwardScienceCodes" matchtype="schemagraphitem">
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
																								<properties width="30%"/>
																								<children>
																									<text fixtext="Code">
																										<styles font-size="9pt"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties align="left" width="40%"/>
																								<children>
																									<text fixtext="Description">
																										<styles font-size="9pt"/>
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
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																								<children>
																									<tablecell>
																										<properties width="125"/>
																										<children>
																											<template match="Code" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-size="9pt"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="left"/>
																										<children>
																											<template match="Description" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-size="9pt"/>
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
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/ScienceCodeRequired = &quot;2&quot;">
																		<children>
																			<text fixtext="Science Code information deleted.">
																				<styles font-family="Arial" font-size="9pt"/>
																			</text>
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
											<newline/>
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
																												<properties cellpadding="0pt" cellspacing="0pt" width="100%"/>
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
													<conditionbranch xpath="../PrintRequirement/CommentsRequired = &quot;1&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="count(  AwardComments/Comment [CommentDetails/CommentCode  &gt; 1 and CommentDetails/CommentCode  &lt; 7  and  string-length(CommentDetails/Comments ) &gt;= 1] )  &gt; 0">
																		<children>
																			<text fixtext="Comments:">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<newline/>
																			<template match="AwardComments" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
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
																				<children>
																					<template match="ReportDetails" matchtype="schemagraphitem">
																						<children>
																							<template match="ReportTermDetails" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="ReportClassCode = 4">
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
																																				<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="Type of Report: "/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<template match="ReportCodeDesc" matchtype="schemagraphitem">
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
																																				<properties width="160"/>
																																				<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="Frequency:"/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<template match="FrequencyCodeDesc" matchtype="schemagraphitem">
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
																																				<properties width="160"/>
																																				<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="Frequency Basis: "/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<template match="FrequencyBaseDesc" matchtype="schemagraphitem">
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
																																				<properties width="160"/>
																																				<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath="boolean(  DueDate  ) and  DueDate != &apos;1900-01-01+00:00&apos;">
																																								<children>
																																									<text fixtext=" Due Date:"/>
																																								</children>
																																							</conditionbranch>
																																						</children>
																																					</condition>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath="boolean(  DueDate  ) and DueDate != &apos;1900-01-01+00:00&apos;">
																																								<children>
																																									<template match="DueDate" matchtype="schemagraphitem">
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
																																				<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="OSP Distribution:"/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<template match="OSPDistributionDesc" matchtype="schemagraphitem">
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
																																				<properties colspan="2" width="160"/>
																																				<styles padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath="MailCopies/NumberOfCopies != &quot;0&quot;">
																																								<children>
																																									<table>
																																										<properties border="0" cellpadding="0"/>
																																										<children>
																																											<tablebody>
																																												<children>
																																													<template match="MailCopies" matchtype="schemagraphitem">
																																														<children>
																																															<tablerow>
																																																<children>
																																																	<tablecell>
																																																		<properties colspan="2"/>
																																																		<styles font-family="Arial" font-size="9pt" padding="0" padding-bottom="0" padding-top="0"/>
																																																		<children>
																																																			<text fixtext="Mail "/>
																																																			<template match="NumberOfCopies" matchtype="schemagraphitem">
																																																				<children>
																																																					<content>
																																																						<format datatype="string"/>
																																																					</content>
																																																				</children>
																																																			</template>
																																																			<text fixtext=" "/>
																																																			<condition>
																																																				<children>
																																																					<conditionbranch xpath="NumberOfCopies &gt;1">
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
																																																			<text fixtext=" to Rolodex Id "/>
																																																			<template match="RolodexId" matchtype="schemagraphitem">
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
											<condition>
												<children>
													<conditionbranch xpath="string-length(  AwardDetails/OtherHeaderDetails/NonCompetingContDesc  ) &gt;0  or  string-length(  AwardDetails/OtherHeaderDetails/CompetingRenewalDesc  )  &gt; 0">
														<children>
															<text fixtext="Proposal Due:">
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
																									<conditionbranch xpath="string-length(  AwardDetails/OtherHeaderDetails/NonCompetingContDesc  ) &gt;0">
																										<children>
																											<table>
																												<properties border="0" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="23%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Non Competing Continuation:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="77%"/>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<template match="AwardDetails" matchtype="schemagraphitem">
																																				<children>
																																					<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																						<children>
																																							<template match="NonCompetingContDesc" matchtype="schemagraphitem">
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
																									<conditionbranch xpath="string-length(  AwardDetails/OtherHeaderDetails/CompetingRenewalDesc  )  &gt; 0">
																										<children>
																											<table>
																												<properties border="0" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="23%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																		<children>
																																			<text fixtext="Competing Renewal:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="77%"/>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<template match="AwardDetails" matchtype="schemagraphitem">
																																				<children>
																																					<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																						<children>
																																							<template match="CompetingRenewalDesc" matchtype="schemagraphitem">
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
											<condition>
												<children>
													<conditionbranch xpath="substring(  AwardDetails/ApprvdEquipmentIndicator , 2 )  = &quot;1&quot;  or  substring(  AwardDetails/ApprvdForeginTripIndicator , 2 ) = &quot;1&quot; or  substring(  AwardDetails/ApprvdSubcontractIndicator , 2 ) = &quot;1&quot;">
														<children>
															<text fixtext="Approved Special Items:">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<styles font-family="Arial"/>
																				<children>
																					<tablecell>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="substring(  AwardDetails/ApprvdEquipmentIndicator , 2 ) = &quot;1&quot;">
																										<children>
																											<text fixtext="Equipment:">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																											<template match="AwardSpecialItems" matchtype="schemagraphitem">
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
																																		<children>
																																			<tablerow>
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																				<children>
																																					<tablecell>
																																						<properties width="32%"/>
																																						<children>
																																							<template match="Item" matchtype="schemagraphitem">
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
																																							<template match="Vendor" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties width="27%"/>
																																						<children>
																																							<template match="Model" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties width="15%"/>
																																						<styles font-size="9pt"/>
																																						<children>
																																							<template match="Amount" matchtype="schemagraphitem">
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
																											<condition>
																												<children>
																													<conditionbranch xpath="../PrintRequirement/EquipmentRequired = &quot;2&quot;">
																														<children>
																															<text fixtext="Approved Equipment information deleted.">
																																<styles font-family="Arial" font-size="9pt"/>
																															</text>
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="substring(  AwardDetails/ApprvdForeginTripIndicator , 2) = &quot;1&quot;">
																										<children>
																											<text fixtext="Foreign Travel:">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																											</text>
																											<template match="AwardSpecialItems" matchtype="schemagraphitem">
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
																																		<children>
																																			<tablerow>
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																				<children>
																																					<tablecell>
																																						<children>
																																							<template match="PersonName" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<children>
																																							<template match="Destination" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<styles font-size="9pt"/>
																																						<children>
																																							<template match="DateFrom" matchtype="schemagraphitem">
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
																											<condition>
																												<children>
																													<conditionbranch xpath="../PrintRequirement/ForeignTravelRequired = &quot;2&quot;">
																														<children>
																															<text fixtext="Foreign Travel information deleted.">
																																<styles font-family="Arial" font-size="9pt"/>
																															</text>
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="substring(  AwardDetails/ApprvdSubcontractIndicator , 2 ) = &quot;1&quot;">
																										<children>
																											<text fixtext="Subcontracts:">
																												<styles font-family="Arial" font-size="9pt" font-weight="bold" line-height="9pt"/>
																											</text>
																											<template match="AwardSpecialItems" matchtype="schemagraphitem">
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
																																				<properties width="23%"/>
																																				<children>
																																					<text fixtext="Amount">
																																						<styles font-weight="bold" line-height="9pt"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="77%"/>
																																				<children>
																																					<text fixtext="Subcontractor&apos;s Name">
																																						<styles font-weight="bold" line-height="9pt"/>
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
																																		<children>
																																			<tablerow>
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																				<children>
																																					<tablecell>
																																						<properties width="23%"/>
																																						<styles font-size="9pt"/>
																																						<children>
																																							<template match="Amount" matchtype="schemagraphitem">
																																								<children>
																																									<text fixtext="$"/>
																																									<content>
																																										<styles line-height="9pt"/>
																																										<format string="#,###,###,##0.00" datatype="decimal"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties width="77%"/>
																																						<children>
																																							<template match="SubcontractorName" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles line-height="9pt"/>
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
																											<condition>
																												<children>
																													<conditionbranch xpath="../PrintRequirement/SubcontractRequired  =&quot;2&quot;">
																														<children>
																															<text fixtext="Subcontract information deleted.">
																																<styles font-family="Arial" font-size="9pt"/>
																															</text>
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
														</children>
													</conditionbranch>
												</children>
											</condition>
											<condition>
												<children>
													<conditionbranch xpath="../PrintRequirement/CostSharingRequired != 0 and AwardDetails/CostSharingIndicator != &quot;N0&quot; or ( AwardDetails/CostSharingIndicator = &quot;N0&quot; and string-length(AwardCostSharing/Comments) &gt;= 1 )">
														<children>
															<text fixtext="Cost Sharing:">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<template match="AwardCostSharing" matchtype="schemagraphitem">
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
																											<conditionbranch xpath="../AwardDetails/CostSharingIndicator != &quot;N0&quot;">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="starts-with(../AwardDetails/CostSharingIndicator  , &quot;P&quot; ) and  count(   CostSharingItem    ) &gt; 0">
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
																																									<text fixtext="FY">
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
																																						<children>
																																							<tablerow>
																																								<styles font-family="Arial" font-size="9pt"/>
																																								<children>
																																									<tablecell>
																																										<properties valign="top" width="62"/>
																																										<styles font-size="9pt"/>
																																										<children>
																																											<template match="Percentage" matchtype="schemagraphitem">
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
																																											<template match="CostSharingDescription" matchtype="schemagraphitem">
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
																																										<children>
																																											<template match="FiscalYear" matchtype="schemagraphitem">
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
																																												<children>
																																													<content>
																																														<format datatype="string"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top"/>
																																										<children>
																																											<template match="DestinationAccount" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<format datatype="string"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top"/>
																																										<styles font-size="9pt"/>
																																										<children>
																																											<template match="Amount" matchtype="schemagraphitem">
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
																																						<styles font-family="Arial" font-size="9pt"/>
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
																																						<styles font-family="Arial" font-size="9pt"/>
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
																																						<styles font-family="Arial" font-size="9pt"/>
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
																															<conditionbranch>
																																<children>
																																	<newline/>
																																	<text fixtext="Cost Sharing information deleted."/>
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
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties valign="top" width="121"/>
																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="substring(  ../AwardDetails/CostSharingIndicator  , 2 )  = &quot;1&quot;  or   boolean(  Comments  )">
																												<children>
																													<text fixtext="Comments:">
																														<styles font-weight="bold"/>
																													</text>
																													<text fixtext=" ">
																														<styles font-size="12pt"/>
																													</text>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties valign="top" width="75%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="substring(  ../AwardDetails/CostSharingIndicator  , 2 )  = &quot;1&quot;  or   boolean(  Comments  )">
																												<children>
																													<template match="Comments" matchtype="schemagraphitem">
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
															</template>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<condition>
												<children>
													<conditionbranch xpath="../PrintRequirement/IndirectCostRequired = &quot;1&quot;and  count(  AwardIndirectCosts  )  &gt; 0 and not(  string-length( AwardDetails/OtherHeaderDetails/SpecialRateComments ) &lt;= 1 and  string-length(  AwardIndirectCosts/Comments ) &lt;= 1 and  substring(  AwardDetails/IDCIndicator  , 2 ) != &quot;1&quot;  )">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="AwardDetails/OtherHeaderDetails/SpecialEBRateOffCampus &gt; 0.0 or  AwardDetails/OtherHeaderDetails/SpecialEBRateOnCampus &gt; 0.0  or   string-length( AwardDetails/OtherHeaderDetails/SpecialRateComments )  &gt;=  1">
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
																													<conditionbranch xpath="AwardDetails/OtherHeaderDetails/SpecialEBRateOffCampus &gt; 0.0 or  AwardDetails/OtherHeaderDetails/SpecialEBRateOnCampus &gt; 0.0">
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
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<template match="AwardDetails" matchtype="schemagraphitem">
																																								<children>
																																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																										<children>
																																											<template match="SpecialEBRateOnCampus" matchtype="schemagraphitem">
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
																																						<styles font-family="Arial" font-size="9pt"/>
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
																																								<children>
																																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																										<children>
																																											<template match="SpecialEBRateOffCampus" matchtype="schemagraphitem">
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
																																<properties border="0" width="100%"/>
																																<children>
																																	<tablebody>
																																		<children>
																																			<tablerow>
																																				<children>
																																					<tablecell>
																																						<properties valign="top" width="120"/>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<text fixtext="Special Rate Comments:">
																																								<styles font-weight="bold"/>
																																							</text>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties valign="top"/>
																																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																						<children>
																																							<template match="AwardDetails" matchtype="schemagraphitem">
																																								<children>
																																									<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																										<children>
																																											<template match="SpecialRateComments" matchtype="schemagraphitem">
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
															<condition>
																<children>
																	<conditionbranch xpath="substring(  AwardDetails/IDCIndicator  , 2 )  = &quot;1&quot;  or  count(  AwardIndirectCosts/Comments  )  &gt; 0">
																		<children>
																			<text fixtext="Indirect Cost:">
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
																										<properties colspan="2" valign="top" width="20"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="substring(  AwardDetails/IDCIndicator  , 2 )  = &quot;1&quot;">
																														<children>
																															<template match="AwardIndirectCosts" matchtype="schemagraphitem">
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
																																								<properties width="35"/>
																																								<styles font-family="Arial" padding-left="20pt"/>
																																								<children>
																																									<text fixtext="Rate"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="100"/>
																																								<styles padding-left="6pt"/>
																																								<children>
																																									<text fixtext="Type"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="38"/>
																																								<children>
																																									<text fixtext="Year"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="65"/>
																																								<children>
																																									<text fixtext="Start Date"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="65"/>
																																								<children>
																																									<text fixtext="End Date"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="60"/>
																																								<children>
																																									<text fixtext="On Campus"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="80"/>
																																								<children>
																																									<text fixtext="UnderRecovery"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="65"/>
																																								<children>
																																									<text fixtext="Source"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="70"/>
																																								<children>
																																									<text fixtext="Destination"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="70"/>
																																							</tablecell>
																																						</children>
																																					</tablerow>
																																				</children>
																																			</tableheader>
																																			<tablebody>
																																				<children>
																																					<template match="IndirectCostSharingItem" matchtype="schemagraphitem">
																																						<children>
																																							<tablerow>
																																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																								<children>
																																									<tablecell>
																																										<properties valign="top" width="35"/>
																																										<styles padding-bottom="0" padding-left="20pt" padding-top="0"/>
																																										<children>
																																											<template match="ApplicableRate" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<format string="##0.00" datatype="decimal"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top" width="100"/>
																																										<styles padding-bottom="0" padding-left="6pt" padding-top="0"/>
																																										<children>
																																											<template match="IDCRateDescription" matchtype="schemagraphitem">
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
																																												<children>
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
																																												<children>
																																													<content>
																																														<format datatype="string"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top" width="70"/>
																																										<styles padding-bottom="0" padding-top="0"/>
																																										<children>
																																											<template match="DestinationAccount" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<format datatype="string"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties valign="top" width="70"/>
																																										<styles padding-bottom="0" padding-top="0"/>
																																									</tablecell>
																																								</children>
																																							</tablerow>
																																						</children>
																																					</template>
																																					<tablerow>
																																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																						<children>
																																							<tablecell>
																																								<properties valign="top" width="35"/>
																																								<styles padding-bottom="0" padding-left="20pt" padding-top="0"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top" width="100"/>
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
																																								<properties valign="top" width="35"/>
																																								<styles padding-bottom="0" padding-left="20pt" padding-top="0"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top" width="100"/>
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
																																								<properties valign="top" width="35"/>
																																								<styles padding-bottom="0" padding-left="20pt" padding-top="0"/>
																																							</tablecell>
																																							<tablecell>
																																								<properties valign="top" width="100"/>
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
																													<conditionbranch xpath="not (count(  AwardIndirectCosts/IndirectCostSharingItem  ) &gt; 0 ) and substring(  AwardDetails/IDCIndicator  , 2 )  = &quot;1&quot;">
																														<children>
																															<text fixtext="          Indirect Cost information deleted."/>
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
																										<properties width="20"/>
																									</tablecell>
																									<tablecell>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="count(  AwardIndirectCosts/Comments  )  &gt; 0  and  string-length(  AwardIndirectCosts/Comments  )  &gt; 0">
																														<children>
																															<table>
																																<properties border="0" width="100%"/>
																																<children>
																																	<tablebody>
																																		<children>
																																			<tablerow>
																																				<children>
																																					<tablecell>
																																						<properties valign="top" width="182"/>
																																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																						<children>
																																							<text fixtext="Indirect Cost Comments:">
																																								<styles font-weight="bold"/>
																																							</text>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties valign="top"/>
																																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																						<children>
																																							<template match="AwardIndirectCosts" matchtype="schemagraphitem">
																																								<children>
																																									<template match="Comments" matchtype="schemagraphitem">
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
											<condition>
												<children>
													<conditionbranch xpath="not( substring(  AwardDetails/TransferSponsorIndicator  , 2) = 0 and  not (starts-with(  AwardDetails/OtherHeaderDetails/PrimeSponsorCode  , &quot;*&quot; ) ) )">
														<children>
															<text fixtext="Flow Thru:">
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
																						<properties colspan="2"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="string-length(AwardDetails/OtherHeaderDetails/PrimeSponsorCode) &gt; 0">
																										<children>
																											<text fixtext="Prime Sponsor:                                  ">
																												<styles font-weight="bold"/>
																											</text>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<children>
																															<template match="PrimeSponsorCode" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-weight="normal"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																															<text fixtext=" ">
																																<styles font-size="12pt" font-weight="bold"/>
																															</text>
																															<text fixtext=": ">
																																<styles font-size="9pt" font-weight="normal"/>
																															</text>
																															<template match="PrimeSponsorDescription" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-weight="normal"/>
																																		<format datatype="string"/>
																																	</content>
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
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="2"/>
																						<styles font-family="Arial" font-size="9pt" padding="0"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="not (starts-with( AwardDetails/TransferSponsorIndicator , &quot;N&quot;)) and count(  AwardTransferringSponsors/TransferringSponsor  )  &gt; 0">
																										<children>
																											<paragraph paragraphtag="p">
																												<children>
																													<text fixtext="Sponsor Funding Transfered:">
																														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																													</text>
																												</children>
																											</paragraph>
																											<template match="AwardTransferringSponsors" matchtype="schemagraphitem">
																												<children>
																													<table>
																														<properties border="0" width="100%"/>
																														<children>
																															<tableheader>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<styles padding-bottom="0" padding-left="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="Sponsor Name">
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
																																	<template match="TransferringSponsor" matchtype="schemagraphitem">
																																		<children>
																																			<tablerow>
																																				<children>
																																					<tablecell>
																																						<properties valign="top"/>
																																						<styles padding-bottom="0" padding-left="0" padding-top="0"/>
																																						<children>
																																							<template match="SponsorCode" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-family="Arial" font-size="9pt"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																							<text fixtext=" "/>
																																							<text fixtext=": ">
																																								<styles font-family="Arial" font-size="9pt"/>
																																							</text>
																																							<template match="SponsorDescription" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-family="Arial" font-size="9pt"/>
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
																									</conditionbranch>
																								</children>
																							</condition>
																							<condition>
																								<children>
																									<conditionbranch xpath="../PrintRequirement/FlowThruRequired  = 2">
																										<children>
																											<text fixtext="Sponsor Funding Transfered information deleted.">
																												<styles font-family="Arial" font-size="9pt"/>
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
														</children>
													</conditionbranch>
												</children>
											</condition>
											<condition>
												<children>
													<conditionbranch xpath="(../PrintRequirement/ReportingRequired = 1 and AwardReportingDetails/ReportDetails/ReportTermDetails/ReportClassCode  != 4)or starts-with( AwardDetails/OtherHeaderDetails/BasisPaymentDesc , &quot;*&quot;) or  starts-with(  AwardDetails/OtherHeaderDetails/PaymentMethodDesc  , &quot;*&quot; ) or  starts-with(  AwardDetails/OtherHeaderDetails/PaymentFreqDesc  , &quot;*&quot; )   or  starts-with(   AwardDetails/OtherHeaderDetails/InvoiceInstructions   , &quot;*&quot; ) or number(  AwardDetails/OtherHeaderDetails/InvoiceCopies  )  &gt; 0 or  number(  AwardDetails/OtherHeaderDetails/FinalInvoiceDue  ) &gt; 0 or (substring(  AwardDetails/PaymentScheduleIndicator  , 2 )  = &quot;1&quot; and count(  AwardPaymentSchedules/PaymentSchedule  )  &gt; 0)   or (substring(  AwardDetails/PaymentScheduleIndicator  , 2 )  = &quot;1&quot;  and count(  AwardPaymentSchedules/PaymentSchedule  )  &gt; 0 and count(  AwardPaymentSchedules/PaymentSchedule ) &gt; 0) or count( AwardFundingProposals/FundingProposal/ProposalNumber ) &gt;0or boolean(  AwardDetails/OtherHeaderDetails/DFAFSNumber  )or( boolean(  AwardDetails/OtherHeaderDetails/CFDANumber  ) and AwardDetails/OtherHeaderDetails/CFDANumber != &quot;      &quot;)or boolean(  AwardDetails/OtherHeaderDetails/ProcurementPriorityCode  )or boolean(  AwardDetails/OtherHeaderDetails/SubPlan  )or ( ../PrintRequirement/CloseoutRequired = 1 and  count(  CloseOutDeadlines  )  &gt; 0)">
														<children>
															<text fixtext="The following articles are provided for your information and do not require unit approval.">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<newline/>
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
																																		<properties border="0" cellpadding="0" width="100%"/>
																																		<children>
																																			<tablebody>
																																				<children>
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties width="24%"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																								<children>
																																									<text fixtext="Type of Report: "/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="76%"/>
																																								<styles font-family="Arial" font-weight="normal" padding-bottom="1pt"/>
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
																																							</tablecell>
																																						</children>
																																					</tablerow>
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties width="24%"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																								<children>
																																									<text fixtext="Frequency:"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="76%"/>
																																								<styles font-family="Arial" font-weight="normal" padding-bottom="1pt"/>
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
																																							</tablecell>
																																						</children>
																																					</tablerow>
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties width="24%"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																								<children>
																																									<text fixtext="Frequency Basis: "/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="76%"/>
																																								<styles font-family="Arial" font-weight="normal" padding-bottom="1pt"/>
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
																																							</tablecell>
																																						</children>
																																					</tablerow>
																																					<tablerow>
																																						<children>
																																							<tablecell>
																																								<properties width="24%"/>
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
																																								<properties width="76%"/>
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
																																								<properties width="24%"/>
																																								<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="1pt"/>
																																								<children>
																																									<text fixtext="OSP Distribution:"/>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<properties width="76%"/>
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
													<conditionbranch xpath="starts-with( AwardDetails/OtherHeaderDetails/BasisPaymentDesc , &quot;*&quot;) or  starts-with(  AwardDetails/OtherHeaderDetails/PaymentMethodDesc  , &quot;*&quot; ) or  starts-with(  AwardDetails/OtherHeaderDetails/PaymentFreqDesc  , &quot;*&quot; )   or  starts-with(   AwardDetails/OtherHeaderDetails/InvoiceInstructions   , &quot;*&quot; ) or number(  AwardDetails/OtherHeaderDetails/InvoiceCopies  )  &gt; 0 or  number(  AwardDetails/OtherHeaderDetails/FinalInvoiceDue  ) &gt; 0 or (substring(  AwardDetails/PaymentScheduleIndicator  , 2 )  = &quot;1&quot; and count(  AwardPaymentSchedules/PaymentSchedule  )  &gt; 0)">
														<children>
															<text fixtext="Payment:">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<template match="AwardDetails" matchtype="schemagraphitem">
												<children>
													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
														<children>
															<newline/>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="starts-with(  BasisPaymentDesc  , &quot;*&quot;)">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Basis of Payment:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="BasisPaymentDesc" matchtype="schemagraphitem">
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
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="starts-with(   PaymentMethodDesc , &quot;*&quot;)">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Method of Payment:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="PaymentMethodDesc" matchtype="schemagraphitem">
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
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="string-length(  PaymentFreqDesc  )  &gt; 0 and  starts-with( PaymentFreqDesc  , &quot;*&quot; )">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Payment/Invoice Frequency:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="PaymentFreqDesc" matchtype="schemagraphitem">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Number of Copies:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<text fixtext="* "/>
																											<template match="InvoiceCopies" matchtype="schemagraphitem">
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
																											<text fixtext="* Final due within ">
																												<styles font-weight="bold"/>
																											</text>
																											<template match="FinalInvoiceDue" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-size="10pt" font-weight="bold"/>
																														<format datatype="int"/>
																													</content>
																												</children>
																											</template>
																											<text fixtext=" days of expiration">
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
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="starts-with(    InvoiceInstructions , &quot;*&quot;)">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Special Invoice Instructions:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="InvoiceInstructions" matchtype="schemagraphitem">
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
											<condition>
												<children>
													<conditionbranch xpath="substring(  AwardDetails/PaymentScheduleIndicator  , 2 )  = &quot;1&quot;  and count(  AwardPaymentSchedules/PaymentSchedule  )  &gt; 0">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="count(  AwardPaymentSchedules/PaymentSchedule ) &gt; 0">
																		<children>
																			<text fixtext="         Payment Schedule:">
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																			</text>
																			<paragraph paragraphtag="p">
																				<children>
																					<template match="AwardPaymentSchedules" matchtype="schemagraphitem">
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
																												<children>
																													<tablerow>
																														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																														<children>
																															<tablecell>
																																<properties width="20"/>
																															</tablecell>
																															<tablecell>
																																<styles font-size="9pt"/>
																																<children>
																																	<template match="DueDate" matchtype="schemagraphitem">
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
																			</paragraph>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="../PrintRequirement/PaymentRequired = &quot;2&quot;">
																		<children>
																			<text fixtext="Payment Schedule information deleted.">
																				<styles font-family="Arial" font-size="9pt"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<condition>
												<children>
													<conditionbranch xpath="../PrintRequirement/PaymentRequired  = 1">
														<children>
															<newline/>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="count( AwardFundingProposals/FundingProposal/ProposalNumber ) &gt;0">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Proposal Number:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="count(  AwardFundingProposals/FundingProposal/ProposalNumber  ) &gt;0">
																														<children>
																															<template match="AwardFundingProposals" matchtype="schemagraphitem">
																																<children>
																																	<template match="FundingProposal" matchtype="schemagraphitem">
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="SequenceNumber = ../../AwardDetails/AwardHeader/SequenceNumber">
																																						<children>
																																							<text fixtext=" * "/>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																			<template match="ProposalNumber" matchtype="schemagraphitem">
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
																																							<text fixtext=",    "/>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</template>
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
															<condition>
																<children>
																	<conditionbranch xpath="boolean(  AwardDetails/OtherHeaderDetails/DFAFSNumber  )">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="DFAFS Number:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="boolean(  AwardDetails/OtherHeaderDetails/DFAFSNumber  )">
																														<children>
																															<template match="AwardDetails" matchtype="schemagraphitem">
																																<children>
																																	<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																		<children>
																																			<template match="DFAFSNumber" matchtype="schemagraphitem">
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
															<condition>
																<children>
																	<conditionbranch xpath="boolean(  AwardDetails/OtherHeaderDetails/CFDANumber  ) and AwardDetails/OtherHeaderDetails/CFDANumber != &quot;      &quot;">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="CFDA Numbe">
																												<styles font-weight="bold"/>
																											</text>
																											<text fixtext="r:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="boolean(  AwardDetails/OtherHeaderDetails/CFDANumber  ) and AwardDetails/OtherHeaderDetails/CFDANumber != &quot;      &quot;">
																														<children>
																															<template match="AwardDetails" matchtype="schemagraphitem">
																																<children>
																																	<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																		<children>
																																			<template match="CFDANumber" matchtype="schemagraphitem">
																																				<children>
																																					<autocalc xpath="concat(  substring( .,1 , 2 )   ,  &quot;.&quot; ,substring-after( . , substring( .,1 , 2 ) )  )"/>
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
																	<conditionbranch xpath="boolean(  AwardDetails/OtherHeaderDetails/ProcurementPriorityCode  )">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Procurement Priority Code:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="boolean(  AwardDetails/OtherHeaderDetails/ProcurementPriorityCode  )">
																														<children>
																															<template match="AwardDetails" matchtype="schemagraphitem">
																																<children>
																																	<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																		<children>
																																			<template match="ProcurementPriorityCode" matchtype="schemagraphitem">
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
															<condition>
																<children>
																	<conditionbranch xpath="boolean(  AwardDetails/OtherHeaderDetails/SubPlan  )">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Sub Plan:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="boolean(  AwardDetails/OtherHeaderDetails/SubPlan  )">
																														<children>
																															<template match="AwardDetails" matchtype="schemagraphitem">
																																<children>
																																	<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																																		<children>
																																			<template match="SubPlan" matchtype="schemagraphitem">
																																				<children>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath=". = &apos;y&apos; or . = &apos;Y&apos;">
																																								<children>
																																									<text fixtext="* Yes"/>
																																								</children>
																																							</conditionbranch>
																																							<conditionbranch xpath=". = &apos;N&apos; or . = &apos;n&apos;">
																																								<children>
																																									<text fixtext="* No"/>
																																								</children>
																																							</conditionbranch>
																																							<conditionbranch>
																																								<children>
																																									<text fixtext="*Unknown"/>
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
																	<conditionbranch xpath="count(AwardDetails/OtherHeaderDetails/UpdateUser) &gt;0">
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
																										<properties width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Award Last Update User:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="UpdateUser" matchtype="schemagraphitem">
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
																	<conditionbranch xpath="count(AwardDetails/OtherHeaderDetails/LastUpdate) &gt;0">
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
																										<properties height="18" width="24%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Award Last Update:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="76%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="OtherHeaderDetails" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="LastUpdate" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="dateTime"/>
																																	</content>
																																	<button>
																																		<action>
																																			<datepicker/>
																																		</action>
																																	</button>
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
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="24%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																					</tablecell>
																					<tablecell>
																						<properties width="76%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<text fixtext="Submission Date">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="24%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Invoice:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="76%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																								<children>
																									<template match="FinalInvSubDate" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<format string="MM/DD/YYYY" datatype="date"/>
																											</content>
																										</children>
																									</template>
																									<template match="FinalInvSubDateModified" matchtype="schemagraphitem">
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
																						<properties width="24%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Technical:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="76%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																								<children>
																									<template match="FinalTechSubDate" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<format string="MM/DD/YYYY" datatype="date"/>
																											</content>
																										</children>
																									</template>
																									<template match="FinalTechSubDateModified" matchtype="schemagraphitem">
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
																						<properties width="24%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Patent:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="76%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																								<children>
																									<template match="FinalPatentSubDate" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<format string="MM/DD/YYYY" datatype="date"/>
																											</content>
																										</children>
																									</template>
																									<template match="FinalPatentSubDateModified" matchtype="schemagraphitem">
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
																						<properties width="24%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Property:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="76%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																						<children>
																							<template match="CloseOutDeadlines" matchtype="schemagraphitem">
																								<children>
																									<template match="FinalPropSubDate" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<format string="MM/DD/YYYY" datatype="date"/>
																											</content>
																										</children>
																									</template>
																									<template match="FinalPropSubDateModified" matchtype="schemagraphitem">
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
													<conditionbranch xpath="../PrintRequirement/SpecialReviewRequired = &quot;1&quot; and   substring(  AwardDetails/SpecialReviewIndicator  , 2) = &quot;1&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="starts-with(  AwardDetails/SpecialReviewIndicator  , &quot;P&quot;) and  count(  AwardSpecialReviews/SpecialReview  ) &gt;0">
																		<children>
																			<text fixtext="Special Review:">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<template match="AwardSpecialReviews" matchtype="schemagraphitem">
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
																										<children>
																											<tablerow>
																												<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																												<children>
																													<tablecell>
																														<styles padding-bottom="0" padding-top="0"/>
																														<children>
																															<template match="ReviewTypeDesc" matchtype="schemagraphitem">
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
																																			<text fixtext="Comments:">
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
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath="string-length()  &gt; 0">
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
																	<conditionbranch>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="Special Review:">
																						<styles font-size="12pt" font-weight="bold"/>
																					</text>
																				</children>
																			</paragraph>
																			<text fixtext="                    Special Review information deleted."/>
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
													<conditionbranch xpath="count (AwardOtherDatas/OtherData )&gt;0">
														<children>
															<text fixtext="Other Data:">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<template match="AwardOtherDatas" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="OtherData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<newline/>
																			<table>
																				<properties cellpadding="0pt" cellspacing="0pt" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="1%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																									</tablecell>
																									<tablecell>
																										<properties width="25%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<template match="ColumnName" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<paragraph paragraphtag="pre-wrap">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																															<text fixtext=":"/>
																														</children>
																													</paragraph>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="74%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<template match="ColumnValue" matchtype="schemagraphitem">
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
															</template>
															<newline/>
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
															<text fixtext="Address list for Account Number">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<text fixtext=" ">
																<styles font-size="12pt"/>
															</text>
															<template match="AwardDetails" matchtype="schemagraphitem">
																<children>
																	<template match="AwardHeader" matchtype="schemagraphitem">
																		<children>
																			<template match="AccountNumber" matchtype="schemagraphitem">
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
																<children>
																	<table>
																		<properties border="0" width="100%"/>
																		<children>
																			<tablebody>
																				<children>
																					<template match="ContactDetails" matchtype="schemagraphitem">
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
																												<children>
																													<template match="RolodexId" matchtype="schemagraphitem">
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
																										<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																										<children>
																											<text fixtext="Contact Type:">
																												<styles font-weight="bold" text-decoration="underline"/>
																											</text>
																											<template match="ContactTypeDesc" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="   "/>
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
																										<properties colspan="4" width="80"/>
																										<styles padding="0" padding-top="1pt"/>
																										<children>
																											<template match="RolodexDetails" matchtype="schemagraphitem">
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
																																				<styles font-family="Arial" font-size="9pt" padding-top="5pt"/>
																																				<children>
																																					<template match="LastName" matchtype="schemagraphitem">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
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
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<template match="Organization" matchtype="schemagraphitem">
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
																																					<text fixtext="Address:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties colspan="3"/>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<template match="Address1" matchtype="schemagraphitem">
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
																																				<properties width="130"/>
																																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties colspan="3"/>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<template match="Address2" matchtype="schemagraphitem">
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
																																				<properties width="130"/>
																																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties colspan="3"/>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<template match="Address3" matchtype="schemagraphitem">
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
																																					<text fixtext="Title:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<template match="Title" matchtype="schemagraphitem">
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
																																					<text fixtext="Phone:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																				<children>
																																					<template match="PhoneNumber" matchtype="schemagraphitem">
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
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																				<children>
																																					<template match="StateDescription" matchtype="schemagraphitem">
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
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																				<children>
																																					<template match="CountryDescription" matchtype="schemagraphitem">
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
																																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																				<children>
																																					<template match="Email" matchtype="schemagraphitem">
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
						<children>
							<content/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</globalparts>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="1.35in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties border="0" width="100%"/>
								<styles table-layout="fixed"/>
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
														<styles font-family="Arial" font-size="9pt" padding-bottom="0"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<children>
																	<template match="SchoolInfo" matchtype="schemagraphitem">
																		<children>
																			<template match="Acronym" matchtype="schemagraphitem">
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
															<text fixtext=" ">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="30%"/>
														<styles padding-bottom="0" padding-left="0" padding-top="1pt"/>
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
														<properties align="left" valign="middle" width="20%"/>
														<styles font-family="Arial" font-size="9pt" padding-bottom="0"/>
														<children>
															<text fixtext="Account Number:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="30%"/>
														<styles padding-bottom="0" padding-left="0" padding-top="1pt"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<children>
																			<template match="AwardDetails" matchtype="schemagraphitem">
																				<children>
																					<template match="AwardHeader" matchtype="schemagraphitem">
																						<children>
																							<template match="AccountNumber" matchtype="schemagraphitem">
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
														<styles padding-right="6pt" padding-top="0"/>
														<children>
															<text fixtext="Date:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="middle" width="15%"/>
														<styles font-size="9pt" padding-top="0"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<children>
																	<template match="PrintRequirement" matchtype="schemagraphitem">
																		<children>
																			<template match="CurrentDate" matchtype="schemagraphitem">
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
