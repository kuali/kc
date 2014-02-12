<?xml version="1.0" encoding="UTF-8"?>
<structure version="6" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="common" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/common.namespace"/>
			<nspair prefix="nih" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
			<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
			<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="c:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\coeusBuild\build\web\Reports\useme.xml">
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
							<newline/>
							<text fixtext="Program Director/Principal Investigator (Last, First, Middle): ">
								<styles font-family="Verdana" font-size="8pt" line-height="10pt"/>
							</text>
							<text fixtext="  ">
								<styles font-family="Verdana" font-size="9pt"/>
							</text>
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
																<editorproperties elementstodisplay="5"/>
																<children>
																	<content>
																		<format datatype="token"/>
																	</content>
																</children>
															</template>
															<text fixtext=", ">
																<styles font-size="10pt"/>
															</text>
															<template match="FirstName" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<content>
																		<format datatype="token"/>
																	</content>
																</children>
															</template>
															<text fixtext=" ">
																<styles font-size="10pt"/>
															</text>
															<template match="MiddleName" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<content>
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
							<table>
								<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center" valign="center" width="5in"/>
														<styles border-bottom-style="none" border-left-style="none" border-right-style="none" font-family="Verdana" font-size="8pt"/>
														<children>
															<paragraph paragraphtag="p">
																<styles font-size="8pt" padding-top="3pt"/>
																<children>
																	<text fixtext="SENIOR/KEY PERSONNEL REPORT">
																		<styles font-size="12pt" font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
															<text fixtext="Place this form at the end of the signed original copy of the application.  Do not duplicate."/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="2.5in"/>
														<styles border-bottom-style="none" border-left-style="solid" border-right-style="none" font-family="Verdana" font-size="9pt"/>
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="GRANT NUMBER">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihPriorGrantNumber" matchtype="schemagraphitem">
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
														<properties align="center" colspan="2" valign="bottom" width="3in"/>
														<styles border-bottom-style="none" border-left-style="none" border-right-style="none"/>
														<children>
															<text fixtext="All Key Personnel for Current Budget Period (do not include Other Significant Contributors)">
																<styles font-size="9pt" font-weight="bold"/>
															</text>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="left" rowspan="2" width="25%"/>
																						<styles border-left-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Name"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" rowspan="2" width="15%"/>
																						<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Degree"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" rowspan="2" width="5%"/>
																						<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
																						<children>
																							<paragraph paragraphtag="p">
																								<children>
																									<text fixtext="SSN"/>
																								</children>
																							</paragraph>
																							<text fixtext="(last 4 digits)"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" rowspan="2" width="20%"/>
																						<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
																						<children>
																							<paragraph paragraphtag="p">
																								<styles font-weight="bold"/>
																								<children>
																									<text fixtext="Role on Project"/>
																								</children>
																							</paragraph>
																							<text fixtext="(e.g. PI, Res. Assoc.)"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" colspan="3" width="8%"/>
																						<styles border-right-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Months Devoted to Project"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="center" width="8%"/>
																						<styles border-right-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Cal"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" width="8%"/>
																						<styles border-right-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Acad"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" width="9%"/>
																						<styles border-right-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																						<children>
																							<text fixtext="Sum"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="25%"/>
																						<styles border-left-style="none" font-family="Verdana" font-size="9pt"/>
																						<children>
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
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="token"/>
																																	</content>
																																</children>
																															</template>
																															<text fixtext=", ">
																																<styles font-size="10pt"/>
																															</text>
																															<template match="FirstName" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="token"/>
																																	</content>
																																</children>
																															</template>
																															<text fixtext=" ">
																																<styles font-size="10pt"/>
																															</text>
																															<template match="MiddleName" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
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
																					<tablecell>
																						<properties width="15%"/>
																						<styles font-family="Verdana" font-size="9pt"/>
																						<children>
																							<autocalc xpath="nih:ResearchAndRelatedProject/ProposalPerson[1]/Degree"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="5%"/>
																						<styles font-family="Verdana" font-size="9pt"/>
																						<children>
																							<autocalc xpath="nih:ResearchAndRelatedProject/ProposalPerson[1]/SSN"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="20%"/>
																						<styles font-family="Verdana" font-size="9pt"/>
																						<children>
																							<autocalc xpath="nih:ResearchAndRelatedProject/ProposalPerson[1]/ProjectRole"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" width="8%"/>
																						<styles border-right-style="none" font-family="Verdana" font-size="9pt"/>
																						<children>
																							<autocalc xpath="nih:ResearchAndRelatedProject/ProposalPerson[1]/FundingMonths"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" width="8%"/>
																						<styles font-family="Verdana" font-size="9pt"/>
																						<children>
																							<autocalc xpath="nih:ResearchAndRelatedProject/ProposalPerson[1]/AcademicFundingMonths"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" width="9%"/>
																						<styles border-right-style="none" font-family="Verdana" font-size="9pt"/>
																						<children>
																							<autocalc xpath="nih:ResearchAndRelatedProject/ProposalPerson[1]/SummerFundingMonths"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<table>
																		<properties border="1" cellpadding="0" cellspacing="0"/>
																		<children>
																			<tableheader>
																				<children>
																					<tablerow>
																						<properties height="0pt"/>
																						<children>
																							<tablecell>
																								<properties align="left" height="2" width="25%"/>
																								<styles border-bottom-style="none" border-left-style="none" border-style="none" border-top-style="none" padding="0"/>
																							</tablecell>
																							<tablecell>
																								<properties align="center" height="2" width="15%"/>
																								<styles border-bottom-style="none" border-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																							</tablecell>
																							<tablecell>
																								<properties align="center" height="2" width="5%"/>
																								<styles border-bottom-style="none" border-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																							</tablecell>
																							<tablecell>
																								<properties align="center" height="2" width="20%"/>
																								<styles border-bottom-style="none" border-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																							</tablecell>
																							<tablecell>
																								<properties align="center" height="2" width="8%"/>
																								<styles border-bottom-style="none" border-right-style="none" border-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																							</tablecell>
																							<tablecell>
																								<properties align="center" height="2" width="8%"/>
																								<styles border-bottom-style="none" border-right-style="none" border-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																							</tablecell>
																							<tablecell>
																								<properties align="center" height="2" width="9%"/>
																								<styles border-bottom-style="none" border-right-style="none" border-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tableheader>
																			<tablebody>
																				<children>
																					<template match="ProposalPerson" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties align="left" height="15" width="25%"/>
																										<styles border-left-style="none" border-top-style="none" font-family="Verdana" font-size="9pt"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Name/LastName != ../nih:ResearchCoverPage/nih:ProgramDirectorPrincipalInvestigator/Name/LastName">
																														<children>
																															<template match="Name" matchtype="schemagraphitem">
																																<children>
																																	<template match="LastName" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<styles font-size="9pt"/>
																																				<format datatype="token"/>
																																			</content>
																																		</children>
																																	</template>
																																	<text fixtext=", ">
																																		<styles font-size="9pt"/>
																																	</text>
																																	<template match="FirstName" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<styles font-size="9pt"/>
																																				<format datatype="token"/>
																																			</content>
																																		</children>
																																	</template>
																																	<text fixtext=" ">
																																		<styles font-size="9pt"/>
																																	</text>
																																	<template match="MiddleName" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<styles font-size="9pt"/>
																																				<format datatype="token"/>
																																			</content>
																																		</children>
																																	</template>
																																	<text fixtext=" ">
																																		<styles font-size="9pt"/>
																																	</text>
																																	<template match="NameSuffix" matchtype="schemagraphitem">
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
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="left" height="15" width="15%"/>
																										<styles border-top-style="none" font-family="Verdana" font-size="9pt"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Name/LastName != ../nih:ResearchCoverPage/nih:ProgramDirectorPrincipalInvestigator/Name/LastName">
																														<children>
																															<template match="Degree" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="9pt"/>
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
																										<properties align="center" height="15" width="5%"/>
																										<styles border-top-style="none" font-family="Verdana" font-size="9pt"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Name/LastName != ../nih:ResearchCoverPage/nih:ProgramDirectorPrincipalInvestigator/Name/LastName">
																														<children>
																															<template match="SSN" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="9pt"/>
																																		<format datatype="token"/>
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
																										<properties align="left" height="15" width="20%"/>
																										<styles border-top-style="none" font-family="Verdana" font-size="9pt"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Name/LastName != ../nih:ResearchCoverPage/nih:ProgramDirectorPrincipalInvestigator/Name/LastName">
																														<children>
																															<template match="ProjectRole" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-size="9pt"/>
																																		<format datatype="token"/>
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
																										<properties align="center" height="15" width="8%"/>
																										<styles border-right-style="none" border-top-style="none" font-family="Verdana" font-size="9pt"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Name/LastName != ../nih:ResearchCoverPage/nih:ProgramDirectorPrincipalInvestigator/Name/LastName">
																														<children>
																															<template match="FundingMonths" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<format datatype="decimal"/>
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
																										<properties align="center" height="15" width="8%"/>
																										<styles border-right-style="none" border-top-style="none" font-family="Verdana" font-size="9pt"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Name/LastName != ../nih:ResearchCoverPage/nih:ProgramDirectorPrincipalInvestigator/Name/LastName">
																														<children>
																															<template match="AcademicFundingMonths" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<format datatype="decimal"/>
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
																										<properties align="center" height="15" width="9%"/>
																										<styles border-right-style="none" border-top-style="none" font-family="Verdana" font-size="9pt"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Name/LastName != ../nih:ResearchCoverPage/nih:ProgramDirectorPrincipalInvestigator/Name/LastName">
																														<children>
																															<template match="SummerFundingMonths" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<format datatype="decimal"/>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="0" paperheight="11in" papermarginbottom="0.6in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="0.5in" paperwidth="8.5in"/>
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
														<properties colspan="3" height="4"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="286"/>
														<children>
															<text fixtext="PHS 2590 ">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
															<text fixtext="(Rev. 11/07)">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Page:">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right"/>
														<children>
															<text fixtext="Form Page 7">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
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
					</template>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
