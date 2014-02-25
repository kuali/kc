<?xml version="1.0" encoding="UTF-8"?>
<structure version="6" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="common" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/common.namespace"/>
			<nspair prefix="nih" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
			<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
			<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\coeusBuild\build\web\Reports\useme.xml">
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
								<properties border="1" height="0.9in" size="0.5" width="7.7in"/>
								<styles font-size="7pt" line-height="10pt" table-layout="fixed"/>
								<children>
									<tablebody>
										<properties size="0.5"/>
										<styles font-family="Verdana" font-size="8pt"/>
										<children>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties align="center" colspan="3" rowspan="3" valign="top" width="170"/>
														<styles line-height="9pt"/>
														<children>
															<newline/>
															<text fixtext="Department of Health and Human Services">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
															<newline/>
															<text fixtext="Public Health Services">
																<styles font-family="Verdana" font-size="9pt"/>
															</text>
															<newline/>
															<newline/>
															<newline/>
															<newline/>
															<paragraph paragraphtag="p"/>
															<paragraph paragraphtag="p"/>
															<paragraph paragraphtag="p"/>
															<newline/>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="Grant Progress Report">
																		<styles font-family="Verdana" font-size="16pt" font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="40"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Review Group">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="38"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Type">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihApplicationTypeCode" matchtype="schemagraphitem">
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
													</tablecell>
													<tablecell>
														<properties valign="top" width="54"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Activity">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihActivityCode" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<format datatype="string"/>
																			</content>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="4" valign="top" width="442"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Grant Number">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihPriorGrantNumber" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<styles font-family="Verdana" font-size="8pt"/>
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
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" height="10" valign="top" width="181"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Total project Period">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<newline/>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="From: ">
																		<styles bottom="auto" font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</paragraph>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="totalProjectStartDt" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<format string="MM/DD/YYYY" datatype="date"/>
																			</content>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="4" height="10" valign="center" width="442"/>
														<styles line-height="9pt"/>
														<children>
															<paragraph paragraphtag="p">
																<styles line-height="9pt"/>
																<children>
																	<text fixtext=" ">
																		<styles font-size="8pt"/>
																	</text>
																</children>
															</paragraph>
															<text fixtext="Through: ">
																<styles bottom="0.5em" font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="totalProjectEndDt" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<format string="MM/DD/YYYY" datatype="date"/>
																			</content>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" ">
																<styles bottom="0.5em" font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" valign="top" width="181"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Requested Budget Period">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="From: ">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																	<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																				<children>
																					<template match="ProjectDates" matchtype="schemagraphitem">
																						<children>
																							<template match="ProjectStartDate" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="8pt"/>
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
															</paragraph>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="4" valign="center" width="442"/>
														<styles line-height="9pt"/>
														<children>
															<paragraph paragraphtag="p">
																<styles line-height="9pt"/>
																<children>
																	<text fixtext=" ">
																		<styles font-size="8pt"/>
																	</text>
																</children>
															</paragraph>
															<text fixtext="Through: ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ProjectDates" matchtype="schemagraphitem">
																				<children>
																					<template match="ProjectEndDate" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="8pt"/>
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
														<properties colspan="10" valign="top" width="772"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="1. TITLE OF PROJECT:   ">
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
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" rowspan="4" valign="top" width="170"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="2a. PRINCIPAL INVESTIGATOR OR PROGRAM DIRECTOR">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="   (Name and address, street, city, state, zip code)">
																<styles font-family="Verdana" font-size="7pt"/>
															</text>
															<paragraph paragraphtag="p"/>
															<text fixtext="   ">
																<styles font-size="7pt"/>
															</text>
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
																								<styles font-family="Verdana" font-size="9pt"/>
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
																								<styles font-family="Verdana" font-size="9pt"/>
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
															<newline/>
															<text fixtext="   ">
																<styles font-size="8pt"/>
															</text>
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
																									<template match="Street" matchtype="schemagraphitem">
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
															<text fixtext="   ">
																<styles font-size="8pt"/>
															</text>
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
															<newline/>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="7" height="20" size="0.5" valign="top" width="592"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="2b. E-MAIL ADDRESS">
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
																									<text fixtext="   ">
																										<styles font-size="8pt"/>
																									</text>
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
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="7" height="20" size="0.5" valign="top" width="592"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="2c. DEPARTMENT, SERVICE, LABORATORY, OR EQUIVALENT">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<autocalc xpath="nih:ResearchAndRelatedProject/KeyPerson[1]/OrganizationDepartment"/>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="7" height="20" size="0.5" valign="top" width="592"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="2d. MAJOR SUBDIVISION">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<autocalc xpath="nih:ResearchAndRelatedProject/KeyPerson[1]/OrganizationDivision"/>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" height="20" valign="top" width="378"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="2e. Tel:">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
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
																							<template match="PhoneNumber" matchtype="schemagraphitem">
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
														<properties colspan="4" height="20" valign="top" width="442"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Fax:">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
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
																							<template match="FaxNumber" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" height="1" rowspan="3" valign="top" width="170"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="3a. APPLICANT ORGANIZATION">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="     ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="(Name and address, street, city, state, zip code)">
																<styles font-family="Verdana" font-size="7pt"/>
															</text>
															<paragraph paragraphtag="p"/>
															<text fixtext="    ">
																<styles font-size="8pt"/>
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
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationAddress" matchtype="schemagraphitem">
																						<children>
																							<template match="Street" matchtype="schemagraphitem">
																								<children>
																									<text fixtext="   ">
																										<styles font-family="Verdana" font-size="9pt"/>
																									</text>
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
															<text fixtext="   ">
																<styles font-size="9pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationAddress" matchtype="schemagraphitem">
																						<children>
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
															<newline/>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" height="1" valign="top" width="378"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="3b. Tel:">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="4" height="1" valign="top" width="442"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Fax:">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="7" height="1" valign="top" width="592"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="3c. DUNS:  "/>
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
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="7" height="1" size="0.5" valign="top" width="592"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="4. ENTITY IDENTIFICATION NUMBER">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationEIN" matchtype="schemagraphitem">
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
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.035in"/>
												<children>
													<tablecell>
														<properties colspan="3" valign="center" width="170"/>
														<styles border-bottom-style="none" line-height="9pt"/>
														<children>
															<text fixtext="6.HUMAN SUBJECTS">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:HumanSubject" matchtype="schemagraphitem">
																				<children>
																					<template match="HumanSubjectsUsedQuestion" matchtype="schemagraphitem">
																						<children>
																							<text fixtext="  ">
																								<styles font-size="8pt"/>
																							</text>
																							<text fixtext="      "/>
																							<checkbox checkedvalue="false" checkedvalue1="0" uncheckedvalue="true">
																								<styles font-size="8pt"/>
																								<children>
																									<content>
																										<format datatype="boolean"/>
																									</content>
																								</children>
																							</checkbox>
																							<text fixtext="  ">
																								<styles font-size="8pt"/>
																							</text>
																							<text fixtext="No">
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
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:HumanSubject" matchtype="schemagraphitem">
																				<children>
																					<template match="HumanSubjectsUsedQuestion" matchtype="schemagraphitem">
																						<children>
																							<text fixtext="  ">
																								<styles font-size="8pt"/>
																							</text>
																							<text fixtext="          "/>
																							<checkbox checkedvalue="true" checkedvalue1="1">
																								<styles font-size="8pt"/>
																								<children>
																									<content>
																										<format datatype="boolean"/>
																									</content>
																								</children>
																							</checkbox>
																							<text fixtext=" ">
																								<styles font-size="8pt"/>
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
														<properties colspan="7" height="41" rowspan="2" size="0.5" valign="top" width="592"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="5. ">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="TITLE AND ADDRESS OF ADMINISTRATIVE OFFICIAL ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext=" "/>
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
																									<template match="FirstName" matchtype="schemagraphitem">
																										<children>
																											<text fixtext="   ">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-family="Verdana" font-size="8pt"/>
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
																												<styles font-family="Verdana" font-size="8pt"/>
																												<format datatype="token"/>
																											</content>
																										</children>
																									</template>
																									<text fixtext=" ">
																										<styles font-family="Verdana" font-size="8pt"/>
																									</text>
																									<template match="LastName" matchtype="schemagraphitem">
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
																</children>
															</template>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationContactPerson" matchtype="schemagraphitem">
																						<children>
																							<template match="PositionTitle" matchtype="schemagraphitem">
																								<children>
																									<text fixtext="   ">
																										<styles font-family="Verdana" font-size="8pt"/>
																									</text>
																									<content>
																										<styles font-family="Verdana" font-size="8pt"/>
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
																									<template match="PostalAddress" matchtype="schemagraphitem">
																										<children>
																											<template match="Street" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="   ">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
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
																		</children>
																	</template>
																</children>
															</template>
															<newline/>
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
																									<template match="PostalAddress" matchtype="schemagraphitem">
																										<children>
																											<template match="City" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="   ">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-family="Verdana" font-size="8pt"/>
																														<format datatype="token"/>
																													</content>
																												</children>
																											</template>
																											<text fixtext=", ">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
																											<template match="State" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Verdana" font-size="8pt"/>
																														<format datatype="token"/>
																													</content>
																												</children>
																											</template>
																											<text fixtext=" ">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
																											<template match="PostalCode" matchtype="schemagraphitem">
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
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.055in"/>
												<children>
													<tablecell>
														<properties height="9" valign="top" width="1.2in"/>
														<styles border-bottom-style="none" border-top-style="none" line-height="9pt"/>
														<children>
															<text fixtext="6a. Research Exempt ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="count( nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber  )  = 0">
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
																									<template match="HumanSubjectsUsedQuestion" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<checkbox checkedvalue="true" checkedvalue1="1">
																												<children>
																													<content>
																														<format datatype="boolean"/>
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
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="count( nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber  )  != 0">
																		<children>
																			<text fixtext="__"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext=" No  "/>
															<condition>
																<children>
																	<conditionbranch xpath="count( nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber  )  != 0">
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
																									<template match="HumanSubjectsUsedQuestion" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<checkbox checkedvalue="true" checkedvalue1="1">
																												<children>
																													<content>
																														<format datatype="boolean"/>
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
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="count( nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber  )  = 0">
																		<children>
																			<text fixtext="__"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext="  Yes"/>
														</children>
													</tablecell>
													<tablecell>
														<properties height="9" valign="top" width="1.3in"/>
														<styles border-bottom-style="none" border-top-style="none" line-height="9pt"/>
														<children>
															<text fixtext="If  Exempt (&quot;Yes&quot; in 6a):">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="Exemption No.">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="count(nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber ) != 0">
																		<children>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber [1]"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
														</children>
													</tablecell>
													<tablecell>
														<properties height="9" valign="top" width="1.5in"/>
														<styles border-bottom-style="none" border-top-style="none" line-height="9pt"/>
														<children>
															<text fixtext="If Not Exempt (&quot;No&quot; in 6a):">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="IRB Approval Date:">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:HumanSubject" matchtype="schemagraphitem">
																				<children>
																					<template match="IRBApprovalDate" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../../rar:AnimalSubject/VertebrateAnimalsUsedQuestion  = &apos;true&apos;">
																										<children>
																											<text fixtext="   ">
																												<styles font-family="Verdana" font-size="9pt"/>
																											</text>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format string="MM/DD/YYYY" datatype="date"/>
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
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" valign="top" width="130"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="6b.Federal Wide Assurance No.">
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
																							<text fixtext="   ">
																								<styles font-size="8pt"/>
																							</text>
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
														<properties colspan="3" valign="top" width="378"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Tel:"/>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationContactPerson" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ContactInformation" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="PhoneNumber" matchtype="schemagraphitem">
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
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="4" valign="top" width="442"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Fax:"/>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ApplicantOrganization" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationContactPerson" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ContactInformation" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="FaxNumber" matchtype="schemagraphitem">
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
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" height="4" width="130"/>
														<styles border-bottom-style="none" line-height="9pt"/>
														<children>
															<text fixtext="6c. NIH-Defined Phase III">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="Clinical Trial  ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:HumanSubject" matchtype="schemagraphitem">
																				<children>
																					<template match="Phase3ClinicalTrialQuestion" matchtype="schemagraphitem">
																						<children>
																							<checkbox checkedvalue="false" checkedvalue1="0" uncheckedvalue="true">
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<content>
																										<format datatype="boolean"/>
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
															<text fixtext=" No  ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<children>
																			<template match="nih:HumanSubject" matchtype="schemagraphitem">
																				<children>
																					<template match="Phase3ClinicalTrialQuestion" matchtype="schemagraphitem">
																						<children>
																							<checkbox checkedvalue="true" checkedvalue1="1">
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<content>
																										<format datatype="boolean"/>
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
															<text fixtext="  Yes">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="7" size="0.5" valign="top" width="592"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="E-MAIL:"/>
															<newline/>
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
																											<text fixtext="  ">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
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
																</children>
															</template>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" height="4" width="130"/>
														<styles border-bottom-style="none" line-height="9pt"/>
														<children>
															<text fixtext="7. VERTEBRATE ANIMALS">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<children>
																			<template match="rar:AnimalSubject" matchtype="schemagraphitem">
																				<children>
																					<template match="VertebrateAnimalsUsedQuestion" matchtype="schemagraphitem">
																						<children>
																							<text fixtext="   ">
																								<styles font-size="8pt"/>
																							</text>
																							<checkbox checkedvalue="false" checkedvalue1="0" uncheckedvalue="true">
																								<styles font-size="8pt"/>
																								<children>
																									<content>
																										<format datatype="boolean"/>
																									</content>
																								</children>
																							</checkbox>
																							<text fixtext="  ">
																								<styles font-size="8pt"/>
																							</text>
																							<text fixtext="No">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																	<newline/>
																</children>
															</template>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<children>
																			<template match="rar:AnimalSubject" matchtype="schemagraphitem">
																				<children>
																					<template match="VertebrateAnimalsUsedQuestion" matchtype="schemagraphitem">
																						<children>
																							<text fixtext="   ">
																								<styles font-size="8pt"/>
																							</text>
																							<checkbox checkedvalue="true" checkedvalue1="1">
																								<styles font-size="8pt"/>
																								<children>
																									<content>
																										<format datatype="boolean"/>
																									</content>
																								</children>
																							</checkbox>
																							<text fixtext="  ">
																								<styles font-size="8pt"/>
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
															<text fixtext=" ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="7" height="4" rowspan="2" size="0.5" valign="top" width="592"/>
														<styles border-bottom-style="none" line-height="9pt"/>
														<children>
															<text fixtext="10. PROJECT/PERFORMANCE SITE(S)">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext=" Organizational Name:">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationName" matchtype="schemagraphitem">
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
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" height="9" valign="top" width="208"/>
														<styles border-bottom-style="none" border-left-style="none" border-top-style="none" line-height="9pt"/>
														<children>
															<text fixtext="7a. If &quot;Yes&quot;, IACUC Approval Date">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<newline/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ProjectDescription" matchtype="schemagraphitem">
																		<children>
																			<template match="rar:AnimalSubject" matchtype="schemagraphitem">
																				<children>
																					<template match="IACUCApprovalDate" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../VertebrateAnimalsUsedQuestion = &apos;true&apos;">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="9pt"/>
																												<format string="MM/DD/YYYY" datatype="date"/>
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
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" height="9" valign="top" width="208"/>
														<styles border-bottom-style="none" border-left-style="none" border-top-style="none" line-height="9pt"/>
														<children>
															<text fixtext="7b. Animal Welfare Assurance No.">
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
																							<text fixtext="      ">
																								<styles font-family="Verdana" font-size="9pt"/>
																							</text>
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
														<properties colspan="7" size="0.5" valign="top" width="592"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="DUNS:"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.045in" size="0.5"/>
												<children>
													<tablecell>
														<properties colspan="3" valign="top" width="170"/>
														<styles border-bottom-style="none" line-height="9pt"/>
														<children>
															<text fixtext="8. COSTS REQUESTED FOR NEXT BUDGET PERIOD">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="7" rowspan="2" size="0.5" valign="top" width="592"/>
														<styles border-bottom-style="none" border-right-style="none" line-height="9pt"/>
														<children>
															<text fixtext="Street 1:"/>
															<text fixtext="  ">
																<styles font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="PostalAddress" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="Street" matchtype="schemagraphitem">
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
															<line>
																<properties size="0.5"/>
															</line>
															<newline/>
															<text fixtext="Street 2:"/>
															<text fixtext="             ">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties align="left" colspan="2" height="9" valign="top" width="130"/>
														<styles border-top-style="none" line-height="9pt" padding-top="10pt"/>
														<children>
															<text fixtext=" ">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="8a. DIRECT">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="  ">
																<styles font-family="Verdana" font-size="7pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="BudgetPeriodID=1">
																								<children>
																									<template match="PeriodDirectCostsTotal" matchtype="schemagraphitem">
																										<children>
																											<text fixtext="$">
																												<styles font-family="Verdana" font-size="9pt"/>
																											</text>
																											<content>
																												<format string="#,###,###,###,##0" datatype="decimal"/>
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
													</tablecell>
													<tablecell>
														<properties height="9" valign="top" width="1.3in"/>
														<styles border-top-style="none" line-height="9pt" padding-top="10pt"/>
														<children>
															<text fixtext=" 8b. ">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="TOTAL ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="BudgetPeriodID=1">
																								<children>
																									<template match="PeriodCostsTotal" matchtype="schemagraphitem">
																										<children>
																											<text fixtext="$">
																												<styles font-family="Verdana" font-size="9pt"/>
																											</text>
																											<content>
																												<format string="#,###,###,###,##0" datatype="decimal"/>
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
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" valign="top" width="181"/>
														<styles border-bottom-style="none" border-right-style="none" line-height="9pt"/>
														<children>
															<text fixtext="9. INVENTIONS AND PATENTS     ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<condition>
																<children>
																	<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nihInventions  ) = false()">
																		<children>
																			<text fixtext="__">
																				<styles font-size="8pt"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihInventions" matchtype="schemagraphitem">
																		<children>
																			<checkbox checkedvalue="X" checkedvalue1="x">
																				<styles font-size="8pt"/>
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</checkbox>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext="  No           ">
																<styles font-size="8pt"/>
															</text>
															<condition>
																<children>
																	<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nihInventions  ) = false()">
																		<children>
																			<text fixtext="__">
																				<styles font-size="8pt"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihInventions" matchtype="schemagraphitem">
																		<children>
																			<checkbox checkedvalue="Y" checkedvalue1="y" checkedvalue2="N" checkedvalue3="n">
																				<styles font-size="8pt"/>
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</checkbox>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext="  Yes ">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" height="25" valign="top" width="181"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="City:  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="PostalAddress" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="City" matchtype="schemagraphitem">
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
														<properties colspan="4" height="25" valign="top" width="442"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="County:  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="CountyName" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" height="25" rowspan="3" valign="top" width="170"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="If &quot;Yes&quot;, "/>
															<paragraph paragraphtag="p">
																<styles border-left-style="none" border-top-style="none" line-height="9pt"/>
																<children>
																	<text fixtext="                   "/>
																	<condition>
																		<children>
																			<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nihInventions  ) = false()">
																				<children>
																					<text fixtext="__">
																						<styles font-size="8pt"/>
																					</text>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																		<children>
																			<template match="nihInventions" matchtype="schemagraphitem">
																				<children>
																					<checkbox checkedvalue="Y" checkedvalue1="y">
																						<styles font-size="8pt"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</checkbox>
																				</children>
																			</template>
																		</children>
																	</template>
																	<text fixtext="   ">
																		<styles font-size="8pt"/>
																	</text>
																	<text fixtext="Previously Reported">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</paragraph>
															<paragraph paragraphtag="p"/>
															<text fixtext="                   "/>
															<condition>
																<children>
																	<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nihInventions  ) = false()">
																		<children>
																			<text fixtext="__">
																				<styles font-size="8pt"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihInventions" matchtype="schemagraphitem">
																		<children>
																			<checkbox checkedvalue="N" checkedvalue1="n">
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</checkbox>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext="   Not Previously Reported">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" height="25" valign="top" width="181"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="State:  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="PostalAddress" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="State" matchtype="schemagraphitem">
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
														<properties colspan="4" height="25" valign="top" width="442"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Province:"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" height="25" valign="top" width="181"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Country:  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="PostalAddress" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="Country" matchtype="schemagraphitem">
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
														<properties colspan="4" height="25" valign="top" width="442"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Zip/Postal Code:  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="PostalAddress" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="PostalCode" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="7" height="25" size="0.5" valign="top" width="592"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="Congressional Districts:  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="CongressionalDistrict" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="10" valign="top" width="772"/>
														<styles border-bottom-style="none" line-height="9pt"/>
														<children>
															<text fixtext="11.  NAME AND TITLE OF OFFICIAL SIGNING FOR APPLICANT ORGANIZATION (item 13)">
																<styles font-family="Verdana" font-size="7pt"/>
															</text>
															<newline/>
															<text fixtext="   "/>
															<text fixtext="NAME:  ">
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
																									<text fixtext="   ">
																										<styles font-family="Verdana" font-size="9pt"/>
																									</text>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format datatype="token"/>
																									</content>
																									<text fixtext=" ">
																										<styles font-family="Verdana" font-size="9pt"/>
																									</text>
																								</children>
																							</template>
																							<template match="MiddleName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format datatype="token"/>
																									</content>
																									<text fixtext=" ">
																										<styles font-family="Verdana" font-size="9pt"/>
																									</text>
																								</children>
																							</template>
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
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext="     "/>
															<text fixtext="   TITLE">
																<styles font-family="Verdana" font-size="7pt"/>
															</text>
															<text fixtext=":  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<children>
																			<template match="AuthorizedOrganizationalRepresentative" matchtype="schemagraphitem">
																				<children>
																					<template match="PositionTitle" matchtype="schemagraphitem">
																						<children>
																							<text fixtext="   ">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</text>
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
												<properties height="0.3in"/>
												<children>
													<tablecell>
														<properties colspan="3" height="25" valign="top" width="170"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="TEL:  "/>
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
																									<text fixtext="     ">
																										<styles font-family="Verdana" font-size="8pt"/>
																									</text>
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
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" height="25" valign="top" width="181"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="FAX:  "/>
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
																									<text fixtext="    ">
																										<styles font-family="Verdana" font-size="8pt"/>
																									</text>
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
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="4" height="25" valign="top" width="442"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="E-MAIL:  "/>
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
												<properties height=".4in"/>
												<children>
													<tablecell>
														<properties colspan="10" height="15" valign="top" width="772"/>
														<styles border-bottom-style="none" line-height="9pt"/>
														<children>
															<text fixtext="12. Corrections to Page 1 Face Page">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties height=".4in"/>
												<children>
													<tablecell>
														<properties colspan="5" valign="top" width="153"/>
														<styles line-height="9pt"/>
														<children>
															<text fixtext="13. ">
																<styles font-size="7pt"/>
															</text>
															<text fixtext="APPLICANT ORGANIZATION CERTIFICATION AND ACCEPTANCE: ">
																<styles font-family="Verdana" font-size="7pt"/>
															</text>
															<text fixtext="I certify that the statements herein are true, complete and accurate to the best of my knowledge and accept the obligation to comply with Public Health Services terms and conditions if a grant is awarded as a result of this application. I am aware that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil, or administrative penalties.">
																<styles font-size="7pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" height="13" valign="top" width="446"/>
														<styles border-bottom-style="none" line-height="9pt"/>
														<children>
															<text fixtext="SIGNATURE OF OFFICIAL NAMED IN 11. ">
																<styles font-family="Verdana" font-size="7pt"/>
															</text>
															<text fixtext="(in ink)">
																<styles font-size="7pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="2" height="13" valign="top" width="446"/>
														<styles border-bottom-style="none" line-height="9pt"/>
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
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.5in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.5in" paperwidth="8.5in"/>
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
														<properties colspan="2" height="1"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left"/>
														<styles font-size="7pt" padding="0"/>
														<children>
															<text fixtext="     PHS 2590 (Rev. 11/07)                                                               Face  Page"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles font-size="7pt" padding="0"/>
														<children>
															<text fixtext="Form Page 1"/>
															<text fixtext=" "/>
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
														<properties colspan="2" height="24"/>
														<styles padding="0"/>
														<children>
															<text fixtext=" "/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left"/>
														<styles font-size="7pt" padding="0"/>
														<children>
															<text fixtext="Form Approved Through 11/30/2010"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles font-size="7pt" padding="0"/>
														<children>
															<text fixtext="OMB No.0925-001"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2" height="1"/>
														<styles padding="0"/>
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
