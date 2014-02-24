<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
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
						<editorproperties elementstodisplay="5"/>
						<children>
							<table>
								<properties border="1" cellpadding="0" cellspacing="0"/>
								<styles font-family="Verdana" font-size="8pt"/>
								<children>
									<tableheader>
										<children>
											<tablerow>
												<children>
													<tablecell headercell="1">
														<properties align="left" colspan="4" height="3" width="55%"/>
														<children>
															<text fixtext="Contact Program Director/Principal Investigator (Last, First, Middle):"/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="nih:ProgramDirectorPrincipalInvestigator" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="Name" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="LastName" matchtype="schemagraphitem">
																								<children>
																									<text fixtext="    "/>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=", ">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
																							<template match="FirstName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=" ">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
																							<template match="MiddleName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
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
									</tableheader>
									<tablebody>
										<styles color="black"/>
										<children>
											<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="KeyPerson" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties bgcolor="gray" colspan="4" height="3" width="55%"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="4" height="3" width="4.0in"/>
																		<children>
																			<text fixtext="3.  PROGRAM DIRECTOR / PRINCIPAL INVESTIGATOR">
																				<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="2" valign="top" width="4.0in"/>
																		<children>
																			<text fixtext="3a. NAME (Last, first, middle)"/>
																			<newline/>
																			<text fixtext="  "/>
																			<condition>
																				<children>
																					<conditionbranch xpath="keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos;">
																						<children>
																							<template match="Name" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="LastName" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</template>
																									<text fixtext=", ">
																										<styles font-family="Verdana" font-size="8pt"/>
																									</text>
																									<template match="FirstName" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</template>
																									<text fixtext=" ">
																										<styles font-family="Verdana" font-size="8pt"/>
																									</text>
																									<template match="MiddleName" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format datatype="token"/>
																											</content>
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
																	<tablecell>
																		<properties align="left" valign="top" width="1.5in"/>
																		<styles font-family="Verdana" font-size="8pt" line-height="9pt"/>
																		<children>
																			<text fixtext="3b. DEGREE(S)">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<newline/>
																			<condition>
																				<children>
																					<conditionbranch xpath="keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos; and  Degree != &apos;Unknown&apos; and Degree != &apos;UNKNOWN&apos;">
																						<children>
																							<template match="Degree" matchtype="schemagraphitem">
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
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="2.0in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="3h. NIH Commons User Name"/>
																			<newline/>
																			<condition>
																				<children>
																					<conditionbranch xpath="keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos;">
																						<children>
																							<template match="nih:AccountIdentifier" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". != &apos;Unknown&apos; and . != &apos;UNKNOWN&apos;">
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
																		<properties colspan="2" valign="top" width="4.0in"/>
																		<children>
																			<text fixtext="3c. POSITION TITLE"/>
																			<newline/>
																			<text fixtext="  "/>
																			<condition>
																				<children>
																					<conditionbranch xpath="keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos;">
																						<children>
																							<template match="PositionTitle" matchtype="schemagraphitem">
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
																	</tablecell>
																	<tablecell>
																		<properties align="left" colspan="2" rowspan="3" valign="top" width="132"/>
																		<styles border-bottom-style="none" font-family="Verdana" line-height="9pt"/>
																		<children>
																			<text fixtext="3d. MAILING ADDRESS (Street, city, state, zip code) ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<newline/>
																			<condition>
																				<children>
																					<conditionbranch xpath="keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos;">
																						<children>
																							<template match="ContactInformation" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="PostalAddress" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="Street" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<paragraph>
																														<children>
																															<content>
																																<styles font-family="Verdana" font-size="9pt"/>
																																<format datatype="token"/>
																															</content>
																														</children>
																													</paragraph>
																												</children>
																											</template>
																											<newline/>
																											<template match="City" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Verdana" font-size="9pt"/>
																														<format datatype="token"/>
																													</content>
																												</children>
																											</template>
																											<text fixtext=", ">
																												<styles font-family="Verdana" font-size="9pt"/>
																											</text>
																											<template match="State" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Verdana" font-size="9pt"/>
																														<format datatype="token"/>
																													</content>
																												</children>
																											</template>
																											<text fixtext=" ">
																												<styles font-family="Verdana" font-size="9pt"/>
																											</text>
																											<template match="PostalCode" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Verdana" font-size="9pt"/>
																														<format datatype="token"/>
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
																			<newline/>
																			<newline/>
																			<newline/>
																			<newline/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="2" width="4.0in"/>
																		<children>
																			<text fixtext="3e. DEPARTMENT, SERVICE, LABORATORY, OR EQUIVALENT"/>
																			<newline/>
																			<text fixtext="  "/>
																			<condition>
																				<children>
																					<conditionbranch xpath="keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos;">
																						<children>
																							<template match="OrganizationDepartment" matchtype="schemagraphitem">
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
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="2" width="4.0in"/>
																		<children>
																			<text fixtext="3f. MAJOR SUBDIVISION "/>
																			<newline/>
																			<text fixtext="  "/>
																			<condition>
																				<children>
																					<conditionbranch xpath="keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos;">
																						<children>
																							<template match="OrganizationDivision" matchtype="schemagraphitem">
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
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="left" colspan="2" valign="top" width="4.0in"/>
																		<styles border-bottom-style="none" font-family="Verdana" font-size="8pt" line-height="9pt"/>
																		<children>
																			<text fixtext="3g. TELEPHONE AND FAX (Area code, number and extension)"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="left" colspan="2" rowspan="2" valign="top" width="132"/>
																		<styles border-top-style="none" font-family="Verdana" line-height="9pt"/>
																		<children>
																			<text fixtext="E-MAIL ADDRESS:">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<newline/>
																			<condition>
																				<children>
																					<conditionbranch xpath="keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos;">
																						<children>
																							<template match="ContactInformation" matchtype="schemagraphitem">
																								<children>
																									<template match="Email" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format datatype="token"/>
																											</content>
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
																		<properties align="left" valign="top" width="4.0in"/>
																		<styles border-right-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" line-height="9pt"/>
																		<children>
																			<text fixtext="TEL: ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<condition>
																				<children>
																					<conditionbranch xpath="keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos;">
																						<children>
																							<template match="ContactInformation" matchtype="schemagraphitem">
																								<children>
																									<template match="PhoneNumber" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																			<text fixtext="    ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="left" valign="top" width="4.0in"/>
																		<styles border-left-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" line-height="9pt"/>
																		<children>
																			<text fixtext="FAX: ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<condition>
																				<children>
																					<conditionbranch xpath="keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos;">
																						<children>
																							<template match="ContactInformation" matchtype="schemagraphitem">
																								<children>
																									<template match="FaxNumber" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format datatype="token"/>
																											</content>
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
													</template>
												</children>
											</template>
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
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<table>
						<properties width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<styles color="white"/>
										<children>
											<tablecell>
												<properties colspan="2" height="30"/>
												<styles padding="0"/>
												<children>
													<text fixtext="."/>
													<newline/>
												</children>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<styles font-family="Traditional Arabic" font-size="8pt"/>
										<children>
											<tablecell>
												<properties colspan="2" height="30"/>
												<styles padding="0"/>
												<children>
													<text fixtext="Use only If preparing an application with Multiple PDs/PI.  See "/>
													<text fixtext="http://grants.nih.gov/grants/multi_pi/index.htm">
														<styles text-decoration="underline"/>
													</text>
													<text fixtext=" for details."/>
												</children>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties colspan="2"/>
												<styles padding="0"/>
											</tablecell>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
				</children>
			</globaltemplate>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<table>
						<properties width="100%"/>
						<children>
							<tablebody>
								<styles font-family="Verdana" font-size="8pt"/>
								<children>
									<tablerow>
										<styles margin-top="0"/>
										<children>
											<tablecell>
												<properties colspan="3"/>
												<styles padding="0"/>
												<children>
													<line>
														<properties color="black" size="1"/>
														<styles top="-37pt"/>
													</line>
												</children>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties align="left"/>
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext="PHS 398 (Rev. 6/09)"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left"/>
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext="Face Page continued"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="right" width="150"/>
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext="From Page 1-continued"/>
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
