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
						<children>
							<newline/>
							<text fixtext="Program Director/Principal Investigator (Last, First, Middle):  ">
								<styles font-family="Verdana" font-size="8pt" line-height="9pt"/>
							</text>
							<text fixtext=" ">
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
																		<styles font-family="Verdana" font-size="8pt"/>
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
																		<styles font-family="Verdana" font-size="8pt"/>
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
																		<styles font-family="Verdana" font-size="8pt"/>
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
								<properties border="1" cellspacing="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center" width="100%"/>
														<styles border-bottom-style="none" border-left-style="none" border-right-style="none" line-height="10pt"/>
														<children>
															<text fixtext="DO NOT SUBMIT UNLESS REQUESTED">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
															</text>
															<newline/>
															<text fixtext="Renewal Applications Only">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
															</text>
															<newline/>
															<text fixtext="All PERSONNEL REPORT">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" width="100%"/>
														<styles border-bottom-style="none" border-left-style="none" border-right-style="none" font-weight="bold" line-height="10pt"/>
														<children>
															<text fixtext="Always list the PD/PI(s). In addition, list all other personnel who participated in the project during the current budget period for at least one person month or more, regardless of the source of compensation (a person month equals approximately 160 hours or 8.3% of annualized effort). Use Cal, Acad, or Summer to Enter Months Devoted to Project.">
																<styles font-size="7pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<table>
								<properties border="1" cellspacing="0" width="100%"/>
								<styles border-bottom-style="none" border-top-style="none"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<styles border-bottom-style="none"/>
												<children>
													<tablecell>
														<properties align="center" valign="bottom" width="15%"/>
														<styles border-left-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="normal"/>
														<children>
															<text fixtext="Commons ID">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" valign="bottom" width="25%"/>
														<styles border-left-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
														<children>
															<text fixtext="Name"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" valign="bottom" width="8%"/>
														<styles border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
														<children>
															<text fixtext="Degree"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" valign="bottom" width="5%"/>
														<styles border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
														<children>
															<text fixtext="SSN"/>
															<newline/>
															<text fixtext="(last 4 digits)"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" valign="bottom" width="20%"/>
														<styles border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
														<children>
															<text fixtext="Role on Project"/>
															<newline/>
															<text fixtext="(e.g. PI, Res. Assoc.)"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" valign="bottom" width="8%"/>
														<styles border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
														<children>
															<text fixtext="DoB"/>
															<newline/>
															<text fixtext=" (MM /YY)"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" valign="bottom" width="5%"/>
														<styles border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
														<children>
															<text fixtext="Cal"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" valign="bottom" width="5%"/>
														<styles border-right-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
														<children>
															<text fixtext="Acad"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" valign="bottom" width="9%"/>
														<styles border-right-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" font-weight="bold"/>
														<children>
															<text fixtext="Sum"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<newline/>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<children>
									<table>
										<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
										<styles border-top-style="none"/>
										<children>
											<tablebody>
												<children>
													<template match="ProposalPerson" matchtype="schemagraphitem">
														<children>
															<tablerow>
																<properties valign="top"/>
																<children>
																	<tablecell>
																		<properties align="left" valign="middle" width="15%"/>
																		<styles border-left-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<template match="AccountIdentifier" matchtype="schemagraphitem">
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
																		<properties align="left" valign="middle" width="25%"/>
																		<styles border-left-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<template match="Name" matchtype="schemagraphitem">
																				<children>
																					<template match="LastName" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="8pt"/>
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
																								<styles font-size="8pt"/>
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
																								<styles font-size="8pt"/>
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
																								<styles font-size="8pt"/>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="left" valign="middle" width="8%"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="Degree  != &apos;Unknown&apos; and Degree != &apos;UNKNOWN&apos;">
																						<children>
																							<template match="Degree" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-size="8pt"/>
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
																		<properties align="left" valign="middle" width="5%"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<template match="SSN" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-size="8pt"/>
																						<format datatype="token"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="left" valign="middle" width="20%"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="ProjectRole = &apos;Principal Investigator&apos; or ProjectRole=&apos;Co-PI&apos;">
																						<children>
																							<text fixtext="Principal Investigator"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<template match="ProjectRole" matchtype="schemagraphitem">
																								<children>
																									<content>
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
																		<properties align="center" valign="middle" width="8%"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="DOB  != &apos;Unknown&apos; and  DOB  != &apos;UNKNOWN&apos;">
																						<children>
																							<template match="DOB" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
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
																		<properties align="center" valign="middle" width="5%"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<template match="FundingMonths" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-size="8pt"/>
																						<format datatype="decimal"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" valign="middle" width="5%"/>
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
																	</tablecell>
																	<tablecell>
																		<properties align="center" valign="middle" width="9%"/>
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
							<newline/>
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
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.6in" papermarginleft="0.3in" papermarginright="0.3in" papermargintop="0.5in" paperwidth="8.5in"/>
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
															<text fixtext="PHS 398 ">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
															<text fixtext="(Rev.6/09)">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Page:_">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
															<field>
																<styles font-family="Verdana" font-size="9pt"/>
															</field>
															<text fixtext="_">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right"/>
														<children>
															<text fixtext="All Personnel Report Format Page">
																<styles font-family="Verdana" font-size="7pt" font-weight="bold"/>
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
