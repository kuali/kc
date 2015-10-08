<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="0">
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
							<text fixtext="Form Approved Through 6/30/2012">
								<styles font-family="Verdana" font-size="8pt" line-height="9pt"/>
							</text>
							<text fixtext="                                                                                                  ">
								<styles font-family="Verdana"/>
							</text>
							<text fixtext="                  OMB No. 0925-0001">
								<styles font-family="Verdana" font-size="8pt"/>
							</text>
							<table>
								<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<properties height="25"/>
												<children>
													<tablecell>
														<properties align="center" colspan="3" valign="top" width="606"/>
														<styles line-height="11pt" padding-top="2pt"/>
														<children>
															<text fixtext="Department of Health and Human Services">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
															<newline/>
															<text fixtext="Public Health Services">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
															<newline/>
															<text fixtext="Grant Application">
																<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
															</text>
															<newline/>
															<text fixtext="Do not exeed character length restrictions indicated">
																<styles font-family="Verdana" font-size="8pt" font-style="italic"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" height="25" valign="top" width="130"/>
														<styles line-height="9pt" margin-bottom="0" padding-top="2pt"/>
														<children>
															<text fixtext="LEAVE BLANK-FOR PHS USE ONLY.">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																<styles border-bottom-style="none"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<styles border-left-style="none" font-family="Verdana" font-size="8pt" line-height="9pt" margin-bottom="0" padding-top="2pt"/>
																						<children>
																							<text fixtext="Type"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<styles font-family="Verdana" font-size="8pt" line-height="9pt" margin-bottom="0" padding-top="2pt"/>
																						<children>
																							<text fixtext="Activity"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<styles border-bottom-width="0" border-right-style="none" font-family="Verdana" font-size="8pt" line-height="9pt" margin-bottom="0" padding-top="2pt"/>
																						<children>
																							<text fixtext="Number"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="2"/>
																						<styles border-left-style="none" font-family="Verdana" font-size="8pt" line-height="9pt" margin-bottom="0" padding-top="2pt"/>
																						<children>
																							<text fixtext="Review Group"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<styles border-bottom-width="0" border-right-style="none" font-family="Verdana" font-size="8pt" line-height="9pt" margin-bottom="0" padding-top="2pt"/>
																						<children>
																							<text fixtext="Formerly"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<styles border-bottom="0" border-bottom-style="none" border-bottom-width="0"/>
																				<children>
																					<tablecell>
																						<properties colspan="2"/>
																						<styles border-bottom-style="none" border-bottom-width="0" border-left-style="none" font-family="Verdana" font-size="8pt" line-height="9pt" margin-bottom="0" padding-top="2pt"/>
																						<children>
																							<text fixtext="Council/Board (Month, Year)"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<styles border-bottom-style="none" border-bottom-width="0" border-right-style="none" font-family="Verdana" font-size="8pt" line-height="9pt" margin-bottom="0" padding-top="2pt"/>
																						<children>
																							<text fixtext="Date Received"/>
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
														<properties align="left" colspan="6" valign="top"/>
														<styles font-family="Verdana" font-size="8pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="1. TITLE OF PROJECT (Do not exceed 81 characters, including spaces and punctuation):  ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ProjectTitle" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-family="Verdana" font-size="9pt"/>
																						<format datatype="string"/>
																					</content>
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
											<tablerow>
												<properties height="35"/>
												<children>
													<tablecell>
														<properties align="left" colspan="6" height="25" valign="top" width="132"/>
														<styles line-height="9pt" padding-top="3pt"/>
														<children>
															<text fixtext="2. RESPONSE TO SPECIFIC REQUEST FOR APPLICATIONS OR PROGRAM ANNOUNCEMENT OR SOLICITATION    ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="FundingOpportunityDetails" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="FundingOpportunityResponseCode" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<text fixtext=" "/>
																							<checkbox checkedvalue="false" uncheckedvalue="true">
																								<children>
																									<content>
																										<format datatype="boolean"/>
																									</content>
																								</children>
																							</checkbox>
																							<text fixtext="  No">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
																							<text fixtext="      "/>
																							<checkbox checkedvalue="true">
																								<children>
																									<content>
																										<format datatype="boolean"/>
																									</content>
																								</children>
																							</checkbox>
																							<text fixtext=" ">
																								<styles font-weight="bold"/>
																							</text>
																							<text fixtext="Yes">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" "/>
															<newline/>
															<text fixtext=" ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext=" "/>
															<text fixtext=" ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext=" "/>
															<text fixtext="(If &quot;Yes&quot;, state number and title) ">
																<styles font-family="Verdana" font-size="7pt" font-style="italic"/>
															</text>
															<newline/>
															<text fixtext="Number">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext=":  ">
																<styles font-size="9pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="FundingOpportunityDetails" matchtype="schemagraphitem">
																				<children>
																					<template match="FundingOpportunityNumber" matchtype="schemagraphitem">
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
															<text fixtext="            ">
																<styles font-size="9pt"/>
															</text>
															<text fixtext="Title:">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext=" ">
																<styles font-size="9pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="FundingOpportunityDetails" matchtype="schemagraphitem">
																				<children>
																					<template match="FundingOpportunityTitle" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="9pt"/>
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
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="6" valign="top" width="606"/>
														<styles line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="3.  PROGRAM DIRECTOR/PRINCIPAL INVESTIGATOR">
																<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties valign="top"/>
												<children>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="606"/>
														<styles line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="3a. NAME (Last, first, middle) ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="    "/>
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
													<tablecell>
														<properties align="left" valign="top" width="130"/>
														<styles font-family="Verdana" font-size="8pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="3b. DEGREE(S)">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/ProposalPerson[1]/Degree != &apos;Unknown&apos; and nih:ResearchAndRelatedProject/ProposalPerson[1]/Degree != &apos;UNKNOWN&apos;">
																		<children>
																			<autocalc xpath="nih:ResearchAndRelatedProject/ProposalPerson[1]/Degree">
																				<styles font-family="Verdana" font-size="9pt"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="2" valign="top" width="198"/>
														<styles font-family="Verdana" font-size="8pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="3h. eRA Commons User Name"/>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:ProgramDirectorPrincipalInvestigator" matchtype="schemagraphitem">
																				<children>
																					<template match="nih:AccountIdentifier" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath=".  != &apos;Unknown&apos; and . != &apos;UNKNOWN&apos;">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
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
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="606"/>
														<styles font-family="Verdana" font-size="8pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="3c. POSITION TITLE"/>
															<newline/>
															<text fixtext="     "/>
															<autocalc xpath="nih:ResearchAndRelatedProject/KeyPerson[1]/PositionTitle">
																<styles font-family="Verdana" font-size="9pt"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" rowspan="4" valign="top" width="132"/>
														<styles font-family="Verdana" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="3d. MAILING ADDRESS (Street, city, state, zip code) ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:ProgramDirectorPrincipalInvestigator" matchtype="schemagraphitem">
																				<children>
																					<template match="ContactInformation" matchtype="schemagraphitem">
																						<children>
																							<template match="PostalAddress" matchtype="schemagraphitem">
																								<children>
																									<text fixtext=" ">
																										<styles font-family="Verdana" font-size="9pt"/>
																									</text>
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
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="nih:ProgramDirectorPrincipalInvestigator" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ContactInformation" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="PostalAddress" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Street" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
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
															<newline/>
															<text fixtext="E-MAIL ADDRESS: ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:ProgramDirectorPrincipalInvestigator" matchtype="schemagraphitem">
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
														<properties align="left" colspan="3" valign="top" width="606"/>
														<styles font-family="Verdana" font-size="8pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="3e. DEPARTMENT, SERVICE, LABORATORY, OR EQUIVALENT"/>
															<newline/>
															<text fixtext="     "/>
															<autocalc xpath="nih:ResearchAndRelatedProject/KeyPerson[1]/OrganizationDepartment">
																<styles font-family="Verdana" font-size="9pt"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="606"/>
														<styles font-family="Verdana" font-size="8pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="3f. MAJOR SUBDIVISION"/>
															<newline/>
															<text fixtext="    "/>
															<autocalc xpath="nih:ResearchAndRelatedProject/KeyPerson[1]/OrganizationDivision">
																<styles font-family="Verdana" font-size="9pt"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="606"/>
														<styles font-family="Verdana" font-size="8pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="3g. TELEPHONE AND FAX (Area code, number and extension)"/>
															<newline/>
															<text fixtext="TEL: ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:ProgramDirectorPrincipalInvestigator" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext="    FAX: ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:ProgramDirectorPrincipalInvestigator" matchtype="schemagraphitem">
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
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="159"/>
														<styles line-height="11pt" padding-bottom="2pt" padding-top="2pt"/>
														<children>
															<text fixtext="4. HUMAN SUBJECTS RESEARCH">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext=" "/>
															<newline/>
															<text fixtext="  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="nih:HumanSubject" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="HumanSubjectsUsedQuestion" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<text fixtext=" "/>
																							<checkbox checkedvalue="false" uncheckedvalue="true">
																								<children>
																									<content>
																										<format datatype="boolean"/>
																									</content>
																								</children>
																							</checkbox>
																							<text fixtext="  No">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
																							<text fixtext="      "/>
																							<checkbox checkedvalue="true">
																								<children>
																									<content>
																										<format datatype="boolean"/>
																									</content>
																								</children>
																							</checkbox>
																							<text fixtext=" ">
																								<styles font-weight="bold"/>
																							</text>
																							<text fixtext="Yes">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
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
														<properties align="left" colspan="2" valign="top" width="80"/>
														<styles border-right-style="none" line-height="11pt" padding-top="2pt"/>
														<children>
															<text fixtext="4a. RESEARCH EXEMPT">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="   "/>
															<condition>
																<children>
																	<conditionbranch xpath="count(nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber ) =false()">
																		<children>
																			<checkbox checkedvalue="false" uncheckedvalue="true">
																				<children>
																					<content/>
																				</children>
																			</checkbox>
																			<text fixtext="  No">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<text fixtext="     "/>
																			<checkbox checkedvalue="true">
																				<children>
																					<content/>
																				</children>
																			</checkbox>
																			<text fixtext=" ">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext="Yes">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<checkbox checkedvalue="false" uncheckedvalue="true">
																				<children>
																					<content/>
																				</children>
																			</checkbox>
																			<text fixtext="  No">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<text fixtext="     "/>
																			<checkbox checkedvalue="true">
																				<children>
																					<content/>
																				</children>
																			</checkbox>
																			<text fixtext=" ">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext="Yes">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<newline/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="132"/>
														<styles border-left-style="none" border-left-width="0" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="If &quot;Yes&quot;, Exemption No. ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="nih:HumanSubject" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ExemptionNumber" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="9pt"/>
																								<format datatype="token"/>
																							</content>
																							<text fixtext=" "/>
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
														<properties align="left" valign="top" width="159"/>
														<styles font-size="8pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="4b. Federal-Wide  Assurance No.">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:HumanSubject" matchtype="schemagraphitem">
																				<children>
																					<template match="AssuranceNumber" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="9pt"/>
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
														<properties align="left" colspan="2" valign="top" width="80"/>
														<styles line-height="11pt" padding-bottom="3pt" padding-top="2pt"/>
														<children>
															<text fixtext="4c. Clinical Trial ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="   "/>
															<condition>
																<children>
																	<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ProjectDescription/rar:ProjectSurvey/ClinicalTrialQuestion   )  =  true()">
																		<children>
																			<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="rar:ProjectSurvey" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="ClinicalTrialQuestion" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<checkbox checkedvalue="false" uncheckedvalue="true">
																												<children>
																													<content>
																														<format datatype="boolean"/>
																													</content>
																												</children>
																											</checkbox>
																											<text fixtext="  No">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
																											<text fixtext="      "/>
																											<checkbox checkedvalue="true">
																												<children>
																													<content>
																														<format datatype="boolean"/>
																													</content>
																												</children>
																											</checkbox>
																											<text fixtext=" ">
																												<styles font-weight="bold"/>
																											</text>
																											<text fixtext="Yes">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
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
																	<conditionbranch>
																		<children>
																			<checkbox checkedvalue="false" uncheckedvalue="true">
																				<children>
																					<content/>
																				</children>
																			</checkbox>
																			<text fixtext="  No">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<text fixtext="     "/>
																			<checkbox checkedvalue="true">
																				<children>
																					<content/>
																				</children>
																			</checkbox>
																			<text fixtext=" ">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext="Yes">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext=" "/>
															<text fixtext=" ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext=" "/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="132"/>
														<styles line-height="11pt" padding-top="2pt"/>
														<children>
															<text fixtext="4d.">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext=" ">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="NIH-defined Phase III Clinical Trial ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="       "/>
															<newline/>
															<text fixtext="  "/>
															<condition>
																<children>
																	<conditionbranch xpath="boolean( nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/Phase3ClinicalTrialQuestion )  =  true()">
																		<children>
																			<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="nih:HumanSubject" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="Phase3ClinicalTrialQuestion" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<checkbox checkedvalue="false" uncheckedvalue="true">
																												<children>
																													<content>
																														<format datatype="boolean"/>
																													</content>
																												</children>
																											</checkbox>
																											<text fixtext="  No">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
																											<text fixtext="      "/>
																											<checkbox checkedvalue="true">
																												<children>
																													<content>
																														<format datatype="boolean"/>
																													</content>
																												</children>
																											</checkbox>
																											<text fixtext=" ">
																												<styles font-weight="bold"/>
																											</text>
																											<text fixtext="Yes">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
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
																	<conditionbranch>
																		<children>
																			<text fixtext=" "/>
																			<checkbox checkedvalue="false" uncheckedvalue="true">
																				<children>
																					<content/>
																				</children>
																			</checkbox>
																			<text fixtext="  No">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<text fixtext="      "/>
																			<checkbox checkedvalue="true">
																				<children>
																					<content/>
																				</children>
																			</checkbox>
																			<text fixtext=" ">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext="Yes">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext="   ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="606"/>
														<styles line-height="9pt" padding-bottom="3pt" padding-top="3pt"/>
														<children>
															<text fixtext="5. VERTEBRATE ANIMALS          ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="   "/>
															<condition>
																<children>
																	<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ProjectDescription/rar:AnimalSubject/VertebrateAnimalsUsedQuestion  )  =  true()">
																		<children>
																			<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="rar:AnimalSubject" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="VertebrateAnimalsUsedQuestion" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<checkbox checkedvalue="false" uncheckedvalue="true">
																												<children>
																													<content>
																														<format datatype="boolean"/>
																													</content>
																												</children>
																											</checkbox>
																											<text fixtext="  No">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
																											<text fixtext="      "/>
																											<checkbox checkedvalue="true">
																												<children>
																													<content>
																														<format datatype="boolean"/>
																													</content>
																												</children>
																											</checkbox>
																											<text fixtext=" ">
																												<styles font-weight="bold"/>
																											</text>
																											<text fixtext="Yes">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
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
																	<conditionbranch>
																		<children>
																			<text fixtext=" "/>
																			<checkbox checkedvalue="false" uncheckedvalue="true">
																				<children>
																					<content/>
																				</children>
																			</checkbox>
																			<text fixtext="  No">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<text fixtext="      "/>
																			<checkbox checkedvalue="true">
																				<children>
																					<content/>
																				</children>
																			</checkbox>
																			<text fixtext=" ">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext="Yes">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="132"/>
														<styles line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="5a. Animal Welfare Assurance No               ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<children>
																			<template match="rar:AnimalSubject" matchtype="schemagraphitem">
																				<children>
																					<template match="AssuranceNumber" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="9pt"/>
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
												<properties height=".15in"/>
												<children>
													<tablecell>
														<properties align="left" height="19" valign="top" width="159"/>
														<styles border-bottom-style="none" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="6. DATES OF PROPOSED PERIOD OF SUPPORT (month, day, year—MM/DD/YY)">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="2" height="19" valign="top" width="79"/>
														<styles line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="7. COSTS REQUESTED FOR INITIAL BUDGET PERIOD">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" height="19" valign="top" width="132"/>
														<styles line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="8. COSTS REQUESTED FOR PROPOSED PERIOD OF SUPPORT">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" height="19" valign="top" width="159"/>
														<styles border-top-style="none" line-height="9pt" padding-top="2pt"/>
														<children>
															<table>
																<properties border="1" width="100%"/>
																<styles border-bottom-style="none" border-top-style="none" font-family="Verdana" font-size="8pt" padding-bottom="0" padding-left="0" padding-right="0" padding-top="0"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<styles border-bottom-style="none" border-left-style="none" border-top-style="none" padding-bottom="0" padding-left="0" padding-top="2pt"/>
																						<children>
																							<text fixtext="From"/>
																							<newline/>
																							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="ProjectDates" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="ProjectStartDate" matchtype="schemagraphitem">
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
																					</tablecell>
																					<tablecell>
																						<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none" padding-left="0" padding-top="2pt"/>
																						<children>
																							<text fixtext="Through"/>
																							<newline/>
																							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="ProjectDates" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="ProjectEndDate" matchtype="schemagraphitem">
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
														<properties align="left" valign="top" width="79"/>
														<styles font-family="Verdana" font-size="9pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="7a. Direct Costs ($)">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/BudgetSummary/ModularBudgetQuestion  = &apos;false&apos;">
																		<children>
																			<text fixtext="$">
																				<styles font-family="Verdana" font-size="9pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/PeriodDirectCostsTotal - nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/ConsortiumCosts/IndirectCosts">
																				<styles font-family="Verdana" font-size="9pt"/>
																				<format string="#,###,###,##0" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="$">
																				<styles font-family="Verdana" font-size="9pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ModularPeriodAmount [../BudgetPeriodID=1] -  nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts [../../BudgetPeriodID=1]">
																				<styles font-family="Verdana" font-size="8pt" line-height="9pt"/>
																				<format string="###,###,##0" datatype="integer"/>
																			</autocalc>
																			<newline/>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="79"/>
														<styles font-family="Verdana" font-size="8pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="7b. Total Costs ($)">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/BudgetSummary/ModularBudgetQuestion  = &apos;true&apos;">
																		<children>
																			<text fixtext="$">
																				<styles font-size="9pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/IndirectCostsTotal + nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/ModularPeriodAmount">
																				<styles font-family="Verdana" font-size="9pt" line-height="9pt"/>
																				<format string="#,###,###,##0" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																				<children>
																					<template match="BudgetSummary" matchtype="schemagraphitem">
																						<children>
																							<template match="BudgetPeriod" matchtype="schemagraphitem">
																								<children>
																									<template match="PeriodCostsTotal" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../BudgetPeriodID =1">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<styles font-family="Verdana" font-size="9pt"/>
																																<format string="#,###,###,##0" datatype="decimal"/>
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
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="150"/>
														<styles font-family="Verdana" font-size="9pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="8a. Direct Costs ($)">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/BudgetSummary/ModularBudgetQuestion  = &apos;false&apos;">
																		<children>
																			<text fixtext="$">
																				<styles font-family="Verdana" font-size="9pt"/>
																			</text>
																			<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/PeriodDirectCostsTotal ) - sum( nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts)">
																				<styles font-family="Verdana" font-size="9pt"/>
																				<format string="#,###,###,##0" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="$">
																				<styles font-family="Verdana" font-size="9pt"/>
																			</text>
																			<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ModularPeriodAmount)- sum( nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts)">
																				<styles font-size="8pt"/>
																				<format string="###,###,##0" datatype="integer"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="2" valign="top" width="198"/>
														<styles font-family="Verdana" font-size="8pt" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="8b. Total Costs">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext=" ($)"/>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/BudgetSummary/ModularBudgetQuestion =&apos;true&apos;">
																		<children>
																			<text fixtext="$">
																				<styles font-size="9pt"/>
																			</text>
																			<autocalc xpath="sum( nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ModularPeriodAmount ) + nih:ResearchAndRelatedProject/BudgetSummary/BudgetIndirectCostsTotal">
																				<styles font-family="Verdana" font-size="9pt"/>
																				<format string="#,###,###,##0" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="$"/>
																			<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																				<children>
																					<template match="BudgetSummary" matchtype="schemagraphitem">
																						<children>
																							<template match="BudgetCostsTotal" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format string="#,###,###,##0" datatype="decimal"/>
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
												<properties height="13"/>
												<children>
													<tablecell>
														<properties align="left" colspan="3" rowspan="3" valign="top" width="606"/>
														<styles line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="9. APPLICANT ORGANIZATION  ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="Name        ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationName" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="9pt"/>
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
															<text fixtext=" ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="Address   ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationAddress" matchtype="schemagraphitem">
																						<children>
																							<text fixtext=" "/>
																							<template match="Street" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<paragraph>
																										<children>
																											<text fixtext="          "/>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</paragraph>
																								</children>
																							</template>
																							<text fixtext="          "/>
																							<template match="City" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=", "/>
																							<template match="State" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=" "/>
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
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="132"/>
														<styles line-height="15pt" padding-top="2pt"/>
														<children>
															<text fixtext="10. TYPE OF ORGANIZATION">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext=" "/>
															<newline/>
															<text fixtext="     Public:      --&gt;         ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationClassification" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="CategoryCode" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<checkbox checkedvalue="3" checkedvalue1="8">
																										<children>
																											<content>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</checkbox>
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
															<text fixtext=" "/>
															<text fixtext=" Federal       ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="   "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationClassification" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="CategoryCode" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<checkbox checkedvalue="2" checkedvalue1="21">
																										<children>
																											<content>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</checkbox>
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
															<text fixtext="  "/>
															<text fixtext="State   ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="        "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationClassification" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="CategoryCode" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<checkbox checkedvalue="&gt;21" checkedvalue1="&lt;27" checkedvalue2="22" checkedvalue3="23" checkedvalue4="24" checkedvalue5="25" checkedvalue6="26">
																										<children>
																											<content>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</checkbox>
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
															<text fixtext="  "/>
															<text fixtext="Local  ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="     Private:    --&gt;">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="      "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationClassification" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="CategoryCode" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<checkbox checkedvalue="4" checkedvalue1="5" checkedvalue2="10">
																										<children>
																											<content>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</checkbox>
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
															<text fixtext="  Private Nonprofit ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="     For-profit: --&gt;  ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="    "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationClassification" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="CategoryCode" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<checkbox checkedvalue="6" checkedvalue1="7" checkedvalue2="9">
																										<children>
																											<content>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</checkbox>
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
															<text fixtext=" "/>
															<text fixtext=" General  ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="      "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationClassification" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="CategoryCode" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<checkbox checkedvalue="11">
																										<children>
																											<content>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</checkbox>
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
															<text fixtext="  Small Business       ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="  "/>
															<text fixtext="  ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="   "/>
															<newline/>
															<text fixtext="  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationClassification" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="CategoryCode" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<checkbox checkedvalue="15">
																										<children>
																											<content>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</checkbox>
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
															<text fixtext="  "/>
															<text fixtext="Woman-owned">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="   "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationClassification" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="CategoryCode" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<checkbox checkedvalue="14">
																										<children>
																											<content>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</checkbox>
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
															<text fixtext="  "/>
															<text fixtext="Socially and Economically Disadvantaged">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="13"/>
												<children>
													<tablecell>
														<properties align="left" colspan="3" height="19" valign="top" width="132"/>
														<styles border-bottom-style="none" line-height="10pt" padding-top="2pt"/>
														<children>
															<text fixtext="11. ENTITY IDENTIFICATION NUMBER: ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="PHSAccountID" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="9pt"/>
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
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem"/>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="13"/>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="132"/>
														<styles border-top-style="none" font-family="Verdana" font-size="8pt" line-height="10pt" padding-top="2pt"/>
														<children>
															<text fixtext="DUNS NO.   "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationDUNS" matchtype="schemagraphitem">
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
													</tablecell>
													<tablecell>
														<properties align="left" colspan="2" valign="top" width="132"/>
														<styles border-top-style="none" font-family="Verdana" font-size="8pt" line-height="10pt" padding-top="2pt"/>
														<children>
															<text fixtext="Cong. District:   "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationCongressionalDistrict" matchtype="schemagraphitem">
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
												<properties height="13"/>
												<children>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="606"/>
														<styles line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="12. ADMINISTRATIVE OFFICIAL TO BE NOTIFIED IF AWARD IS MADE ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="Name:          ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationContactPerson" matchtype="schemagraphitem">
																						<children>
																							<template match="Name" matchtype="schemagraphitem">
																								<children>
																									<text fixtext=" ">
																										<styles font-size="9pt"/>
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
																										<styles font-size="9pt"/>
																									</text>
																									<template match="LastName" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</template>
																							<text fixtext=" ">
																								<styles font-size="9pt"/>
																							</text>
																							<newline/>
																							<text fixtext="Title">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
																							<text fixtext=":           ">
																								<styles font-family="Verdana" font-size="9pt"/>
																							</text>
																							<template match="PositionTitle" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=" ">
																								<styles font-size="9pt"/>
																							</text>
																							<newline/>
																							<text fixtext="Address">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
																							<text fixtext=":     ">
																								<styles font-size="9pt"/>
																							</text>
																							<text fixtext="        "/>
																							<template match="ContactInformation" matchtype="schemagraphitem">
																								<children>
																									<template match="PostalAddress" matchtype="schemagraphitem">
																										<children>
																											<template match="Street" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<paragraph>
																														<children>
																															<text fixtext="           "/>
																															<content>
																																<styles font-family="Verdana" font-size="9pt"/>
																																<format datatype="token"/>
																															</content>
																														</children>
																													</paragraph>
																												</children>
																											</template>
																											<text fixtext="           "/>
																											<template match="City" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Verdana" font-size="9pt"/>
																														<format datatype="token"/>
																													</content>
																												</children>
																											</template>
																											<text fixtext=", ">
																												<styles font-size="9pt"/>
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
																												<styles font-size="9pt"/>
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
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<newline/>
															<text fixtext="Tel:  ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="      "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationContactPerson" matchtype="schemagraphitem">
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
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext="                      "/>
															<text fixtext="FAX:">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationContactPerson" matchtype="schemagraphitem">
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
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<newline/>
															<text fixtext="E-Mail:      ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationContactPerson" matchtype="schemagraphitem">
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
														<properties align="left" colspan="3" height="13" valign="top" width="132"/>
														<styles line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="13. OFFICIAL SIGNING FOR APPLICANT ORGANIZATION ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="Name:     ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="AuthorizedOrganizationalRepresentative" matchtype="schemagraphitem">
																				<children>
																					<template match="Name" matchtype="schemagraphitem">
																						<children>
																							<template match="FirstName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=" "/>
																							<template match="LastName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</template>
																					<newline/>
																					<text fixtext="Title: ">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
																					<text fixtext="    "/>
																					<template match="PositionTitle" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="9pt"/>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																					<template match="ContactInformation" matchtype="schemagraphitem">
																						<children>
																							<newline/>
																							<text fixtext="Address:  ">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
																							<text fixtext="          "/>
																							<template match="PostalAddress" matchtype="schemagraphitem">
																								<children>
																									<template match="Street" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<paragraph>
																												<children>
																													<text fixtext="          "/>
																													<content>
																														<styles font-family="Verdana" font-size="9pt"/>
																														<format datatype="token"/>
																													</content>
																												</children>
																											</paragraph>
																										</children>
																									</template>
																									<newline/>
																									<text fixtext="          "/>
																									<template match="City" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</template>
																									<text fixtext=", "/>
																									<template match="State" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</template>
																									<text fixtext=" "/>
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
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<newline/>
															<text fixtext="Tel:         ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="AuthorizedOrganizationalRepresentative" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext="                  FAX:  ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="AuthorizedOrganizationalRepresentative" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<newline/>
															<text fixtext="E-Mail">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext=":"/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="AuthorizedOrganizationalRepresentative" matchtype="schemagraphitem">
																				<children>
																					<template match="ContactInformation" matchtype="schemagraphitem">
																						<children>
																							<template match="Email" matchtype="schemagraphitem">
																								<children>
																									<text fixtext="  "/>
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
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="606"/>
														<styles line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="14. APPLICANT ORGANIZATION CERTIFICATION AND ACCEPTANCE: ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="I certify that the statements herein are true, complete and accurate to the best of my knowledge, and accept the obligation to comply with Public Health Services terms and conditions if a grant is awarded as a result of this application. I am aware that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil, or administrative penalties. ">
																<styles font-family="Verdana" font-size="7pt"/>
															</text>
															<newline/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="2" valign="top"/>
														<styles font-family="Verdana" font-size="8pt" font-weight="normal" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="SIGNATURE OF OFFICIAL NAMED IN 13.">
																<styles font-family="Verdana" font-size="7pt"/>
															</text>
															<text fixtext=" "/>
															<text fixtext="(In ink. &quot;Per&quot; signature not acceptable.)    ">
																<styles font-family="Verdana" font-size="7pt" font-style="italic"/>
															</text>
															<newline/>
															<newline/>
															<newline/>
															<text fixtext="                                            ">
																<styles font-family="Verdana" font-size="7pt" font-style="italic"/>
															</text>
															<newline/>
															<text fixtext="                 ">
																<styles font-family="Verdana" font-size="7pt" font-style="italic"/>
															</text>
															<text fixtext="                         "/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top"/>
														<styles font-family="Verdana" font-size="8pt" font-weight="normal" line-height="9pt" padding-top="2pt"/>
														<children>
															<text fixtext="DATE"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<text fixtext="PHS 398 (rev. 6/09)                                                                                                      Face Page                                                                                                                         ">
								<styles font-family="Verdana" font-size="7pt"/>
							</text>
							<text fixtext="Form Page 1">
								<styles font-family="Verdana" font-size="7pt" font-weight="bold"/>
							</text>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.15in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop=".3in" paperwidth="8.5in"/>
		<styles margin-bottom="1in"/>
		<children>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<template match="$XML" matchtype="schemasource"/>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
