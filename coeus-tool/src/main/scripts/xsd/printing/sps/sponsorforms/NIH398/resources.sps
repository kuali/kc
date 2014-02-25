<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="common" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/common.namespace"/>
			<nspair prefix="nih" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
			<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
			<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="phs398schema.xsd" workingxmlfile="nihproposal.xml">
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
								<properties cellspacing="0" width="100%"/>
								<styles font-family="Verdana" line-height="10pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<properties valign="top"/>
												<children>
													<tablecell>
														<properties align="center" colspan="3" valign="top"/>
														<children>
															<line>
																<properties color="black" size="1"/>
															</line>
															<text fixtext=" "/>
															<newline/>
															<text fixtext="RESOURCES">
																<styles font-weight="bold"/>
															</text>
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
														<properties colspan="3" height="22" valign="middle"/>
														<styles padding-top="0pt"/>
														<children>
															<text fixtext="Follow the 398 application instructions in Part I, 2.7 Resources."/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" valign="middle"/>
														<styles padding-bottom="0pt" padding-top="6pt"/>
														<children>
															<text fixtext="Laboratory:">
																<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" valign="middle"/>
														<styles line-height="12pt" padding-left="12pt" padding-top="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 4]/AbstractText ) &lt;= 0">
																		<children>
																			<newline/>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="Abstract" matchtype="schemagraphitem">
																		<children>
																			<template match="AbstractText" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="../AbstractTypeCode = 4">
																								<children>
																									<paragraph paragraphtag="pre">
																										<styles line-height="10pt"/>
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="10pt"/>
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
														<properties colspan="3" valign="middle"/>
														<styles padding-bottom="0pt" padding-top="6pt"/>
														<children>
															<text fixtext="Clinical:">
																<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" valign="middle"/>
														<styles padding-left="12pt" padding-top="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 5]/AbstractText ) &lt;= 0">
																		<children>
																			<newline/>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="Abstract" matchtype="schemagraphitem">
																		<children>
																			<template match="AbstractText" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="../AbstractTypeCode = 5">
																								<children>
																									<paragraph paragraphtag="pre">
																										<styles line-height="10pt"/>
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="10pt"/>
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
														<properties colspan="3" valign="middle"/>
														<styles padding-bottom="0pt" padding-top="6pt"/>
														<children>
															<text fixtext="Animal:">
																<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" valign="middle"/>
														<styles padding-left="12pt" padding-top="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 6]/AbstractText ) &lt;= 0">
																		<children>
																			<newline/>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="Abstract" matchtype="schemagraphitem">
																		<children>
																			<template match="AbstractText" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="../AbstractTypeCode = 6">
																								<children>
																									<paragraph paragraphtag="pre">
																										<styles line-height="10pt"/>
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="10pt"/>
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
														<properties colspan="3" valign="middle"/>
														<styles padding-bottom="0pt" padding-top="6pt"/>
														<children>
															<text fixtext="Computer:">
																<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" valign="middle"/>
														<styles padding-left="12pt" padding-top="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 7]/AbstractText ) &lt;= 0">
																		<children>
																			<newline/>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="Abstract" matchtype="schemagraphitem">
																		<children>
																			<template match="AbstractText" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="../AbstractTypeCode = 7">
																								<children>
																									<paragraph paragraphtag="pre">
																										<styles line-height="10pt"/>
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="10pt"/>
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
														<properties colspan="3" valign="middle"/>
														<styles padding-bottom="0pt" padding-top="6pt"/>
														<children>
															<text fixtext="Office:">
																<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" valign="middle"/>
														<styles padding-left="12pt" padding-top="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 8]/AbstractText ) &lt;= 0">
																		<children>
																			<newline/>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="Abstract" matchtype="schemagraphitem">
																		<children>
																			<template match="AbstractText" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="../AbstractTypeCode = 8">
																								<children>
																									<paragraph paragraphtag="pre">
																										<styles line-height="10pt"/>
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="10pt"/>
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
														<properties colspan="3" valign="middle"/>
														<styles padding-bottom="0pt" padding-top="6pt"/>
														<children>
															<text fixtext="Other:">
																<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" valign="middle"/>
														<styles padding-left="12pt" padding-top="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 11]/AbstractText ) &lt;= 0">
																		<children>
																			<newline/>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="Abstract" matchtype="schemagraphitem">
																		<children>
																			<template match="AbstractText" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="../AbstractTypeCode = 11">
																								<children>
																									<paragraph paragraphtag="pre">
																										<styles line-height="10pt"/>
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="10pt"/>
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
														<properties colspan="3" valign="middle"/>
														<children>
															<paragraph paragraphtag="p">
																<styles padding-bottom="0pt" padding-top="6pt"/>
																<children>
																	<line>
																		<properties color="black" size="1"/>
																	</line>
																</children>
															</paragraph>
															<text fixtext="MAJOR EQUIPMENT:">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
															</text>
															<text fixtext=" List the most important equipment items already available for this project, noting the location and pertinent capabilities of each.">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" valign="middle"/>
														<styles padding-bottom="0pt" padding-left="12pt" padding-top="0pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="Abstract" matchtype="schemagraphitem">
																		<children>
																			<template match="AbstractText" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="../AbstractTypeCode = 10">
																								<children>
																									<paragraph paragraphtag="pre">
																										<styles line-height="10pt"/>
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="10pt"/>
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
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.65in" papermarginleft="0.35in" papermarginright="0.35in" papermargintop="0.45in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties width="100%"/>
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
														<properties colspan="2"/>
														<styles padding="0"/>
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
														<properties align="left" height="16"/>
														<styles font-size="9pt" padding="0"/>
														<children>
															<text fixtext="PHS 398 (Rev. 6/09)                                                            Page: "/>
															<field>
																<styles text-decoration="underline"/>
															</field>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="16" width="150"/>
														<styles font-size="9pt" padding="0"/>
														<children>
															<text fixtext="Resources Format Page"/>
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
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2" height="18"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="2" valign="bottom"/>
														<styles font-size="9pt" padding="0"/>
														<children>
															<text fixtext="                 Program Director/Principal Investigator (Last, first, middle): "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:ProgramDirectorPrincipalInvestigator" matchtype="schemagraphitem">
																				<children>
																					<template match="Name" matchtype="schemagraphitem">
																						<children>
																							<template match="LastName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles text-decoration="underline"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=", ">
																								<styles text-decoration="underline"/>
																							</text>
																							<template match="FirstName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles text-decoration="underline"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=" ">
																								<styles text-decoration="underline"/>
																							</text>
																							<template match="MiddleName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles text-decoration="underline"/>
																										<format datatype="token"/>
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
					</template>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
