<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="c:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="budget.xml" templatexmlfile="" xsltversion="1" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<styles font-family="Verdana" font-size="9pt" line-height="10pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<newline/>
			<newline/>
			<newline/>
			<table>
				<properties border="1" cellpadding="0" cellspacing="0" table-layout="fixed" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none" font-family="Verdana" font-size="9pt"/>
										<properties align="center" colspan="4" width="1.5in"/>
										<children>
											<text fixtext="PROGRAM DIRECTOR/PRINCIPAL INVESTIGATOR    ">
												<styles font-size="9pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="n1:ResearchCoverPage"/>
														<children>
															<template>
																<match match="n1:ProgramDirectorPrincipalInvestigator"/>
																<children>
																	<template>
																		<match match="Name"/>
																		<children>
																			<template>
																				<match match="LastName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="9pt"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=", ">
																				<styles font-family="Verdana" font-size="9pt"/>
																			</text>
																			<template>
																				<match match="FirstName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="9pt"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=" ">
																				<styles font-family="Verdana" font-size="9pt"/>
																			</text>
																			<template>
																				<match match="MiddleName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="9pt"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=" ">
																				<styles font-family="Verdana" font-size="9pt"/>
																			</text>
																			<template>
																				<match match="NameSuffix"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="9pt"/>
																					</xpath>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" font-family="Verdana" font-size="9pt"/>
										<properties align="center" colspan="2" rowspan="2" width="4in"/>
										<children>
											<text fixtext="PROGRESS REPORT SUMMARY">
												<styles font-family="Verdana" font-size="11pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-right-style="none" font-family="Verdana" font-size="9pt"/>
										<properties colspan="2" width="1in"/>
										<children>
											<paragraph paragraphtag="p">
												<styles padding-top="3pt"/>
												<children>
													<text fixtext="GRANT NUMBER   "/>
												</children>
											</paragraph>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="nihPriorGrantNumber"/>
														<children>
															<xpath allchildren="1"/>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-right-style="none" font-family="Verdana" font-size="9pt"/>
										<properties colspan="2" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-right-style="none" font-family="Verdana" font-size="9pt"/>
										<properties colspan="2" valign="center" width="1.5in"/>
										<children>
											<paragraph paragraphtag="p">
												<styles padding-top="5pt"/>
												<children>
													<text fixtext="PERIOD COVERED BY THIS REPORT"/>
												</children>
											</paragraph>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-left-style="none" font-family="Verdana" font-size="9pt"/>
										<properties colspan="2" width="4in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<paragraph paragraphtag="p">
														<children>
															<text fixtext="PROGRAM DIRECTOR/PRINCIPAL INVESTIGATOR "/>
														</children>
													</paragraph>
													<template>
														<styles font-family="Verdana" font-size="7pt"/>
														<match match="n1:ResearchAndRelatedProject"/>
														<children>
															<template>
																<match match="n1:ResearchCoverPage"/>
																<children>
																	<template>
																		<match match="n1:ProgramDirectorPrincipalInvestigator"/>
																		<children>
																			<template>
																				<match match="Name"/>
																				<children>
																					<template>
																						<match match="LastName"/>
																						<children>
																							<xpath allchildren="1">
																								<styles font-family="Verdana" font-size="9pt"/>
																							</xpath>
																						</children>
																					</template>
																					<text fixtext=", ">
																						<styles font-family="Verdana" font-size="9pt"/>
																					</text>
																					<template>
																						<match match="FirstName"/>
																						<children>
																							<xpath allchildren="1">
																								<styles font-family="Verdana" font-size="9pt"/>
																							</xpath>
																						</children>
																					</template>
																					<text fixtext=" ">
																						<styles font-family="Verdana" font-size="9pt"/>
																					</text>
																					<template>
																						<match match="MiddleName"/>
																						<children>
																							<xpath allchildren="1">
																								<styles font-family="Verdana" font-size="9pt"/>
																							</xpath>
																						</children>
																					</template>
																					<text fixtext=" ">
																						<styles font-family="Verdana" font-size="9pt"/>
																					</text>
																					<template>
																						<match match="NameSuffix"/>
																						<children>
																							<xpath allchildren="1">
																								<styles font-family="Verdana" font-size="9pt"/>
																							</xpath>
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
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties valign="top" width="1.5in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<paragraph paragraphtag="p">
														<children>
															<text fixtext="FROM"/>
														</children>
													</paragraph>
													<template>
														<match match="n1:ResearchAndRelatedProject"/>
														<children>
															<template>
																<match match="n1:ResearchCoverPage"/>
																<children>
																	<template>
																		<match match="ProjectDates"/>
																		<children>
																			<template>
																				<match match="ProjectStartDate"/>
																				<children>
																					<xpath allchildren="1">
																						<format string="MM/DD/" xslt="1"/>
																					</xpath>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
													<autovalue>
														<editorproperties editable="0"/>
														<autocalc>
															<xpath value="substring(  n1:ResearchAndRelatedProject/n1:ResearchCoverPage/ProjectDates/ProjectStartDate , 1, 4 ) -1"/>
														</autocalc>
													</autovalue>
												</children>
											</paragraph>
										</children>
									</tablecol>
									<tablecol>
										<styles border-right-style="none" font-family="Verdana" font-size="9pt"/>
										<properties valign="top" width="1.5in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<paragraph paragraphtag="p">
														<children>
															<text fixtext="THROUGH"/>
														</children>
													</paragraph>
													<template>
														<match match="n1:ResearchAndRelatedProject"/>
														<children>
															<template>
																<match match="n1:ResearchCoverPage"/>
																<children>
																	<template>
																		<match match="ProjectDates"/>
																		<children>
																			<template>
																				<match match="ProjectEndDate"/>
																				<children>
																					<xpath allchildren="1">
																						<format string="MM/DD/" xslt="1"/>
																					</xpath>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
													<autovalue>
														<editorproperties editable="0"/>
														<autocalc>
															<xpath value="substring(  n1:ResearchAndRelatedProject/n1:ResearchCoverPage/ProjectDates/ProjectEndDate , 1, 4 ) -1"/>
														</autocalc>
													</autovalue>
												</children>
											</paragraph>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" font-family="Verdana" font-size="9pt"/>
										<properties colspan="4" valign="top" width="4in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<paragraph paragraphtag="p">
														<styles border-right-style="none"/>
														<children>
															<text fixtext="APPLICANT ORGANIZATION"/>
														</children>
													</paragraph>
													<template>
														<match match="n1:ResearchAndRelatedProject"/>
														<children>
															<template>
																<match match="n1:ResearchCoverPage"/>
																<children>
																	<template>
																		<match match="ApplicantOrganization"/>
																		<children>
																			<template>
																				<match match="OrganizationName"/>
																				<children>
																					<xpath allchildren="1"/>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" font-family="Verdana" font-size="9pt"/>
										<properties colspan="4" valign="top" width="4in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<paragraph paragraphtag="p">
														<children>
															<paragraph paragraphtag="p">
																<styles border-right-style="none"/>
																<children>
																	<text fixtext="TITLE OF PROJECT (Repeat title shown in Item 1 on first page)"/>
																</children>
															</paragraph>
														</children>
													</paragraph>
													<template>
														<match match="n1:ResearchAndRelatedProject"/>
														<children>
															<template>
																<match match="n1:ResearchCoverPage"/>
																<children>
																	<template>
																		<match match="ProjectTitle"/>
																		<children>
																			<xpath allchildren="1"/>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</paragraph>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
										<properties colspan="4" height="15" valign="top" width="4in"/>
										<children>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties colspan="2" width="40%"/>
																		<children>
																			<text fixtext="A.	Human Subjects (Complete Item 6 on Face Page)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="20%"/>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="15pt"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="Involvement of Human Subjects"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="___No Change Since Previous Submission"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="20%"/>
																		<children>
																			<text fixtext="___Change"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties colspan="2" width="40%"/>
																		<children>
																			<text fixtext="B. Vertebrate Animals (Complete Item 7 on Face Page"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="20%"/>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="15pt"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="Use of Vertebrate Animals"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="___No Change Since Previous Submission"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="20%"/>
																		<children>
																			<text fixtext="___Change"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="C. Select Agent Research"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="___No Change Since Previous Submission"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="20%"/>
																		<children>
																			<text fixtext="___Change"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="D. Multiple PI Leadership Plan"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="___No Change Since Previous Submission"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-right-style="none" font-family="Verdana" font-size="9pt" padding-left="5pt"/>
																		<properties width="20%"/>
																		<children>
																			<text fixtext="___Change"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" font-family="Verdana" font-size="9pt"/>
										<properties colspan="4" valign="top" width="4in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<paragraph paragraphtag="p">
														<styles border-bottom-style="none"/>
														<children>
															<text fixtext="See PHS 2590 Instructions"/>
														</children>
													</paragraph>
													<text fixtext="WOMEN AND MINORITY INCLUSION: See PHS 398 Instructions.  Use Inclusion Enrollment Report Format Page and, if necessary, Targeted/Planned Enrollment Format Page.">
														<styles font-weight="bold"/>
													</text>
												</children>
											</paragraph>
										</children>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
			<paragraph paragraphtag="p">
				<children>
					<newline/>
				</children>
			</paragraph>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.6in" papermarginleft="0.3in" papermarginright="0.3in" papermargintop="0.6in" paperwidth="8.5in"/>
		<footerall>
			<template>
				<match overwrittenxslmatch="/"/>
				<children>
					<table topdown="0">
						<properties width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecol>
												<styles padding="0"/>
												<properties colspan="3" height="8" width="3.5in"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles padding="0"/>
												<properties colspan="3" height="4" width="3.5in"/>
												<children>
													<line>
														<properties color="black" size="1"/>
													</line>
												</children>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" padding="0"/>
												<properties align="left" width="3.5in"/>
												<children>
													<text fixtext="PHS 2590 (Rev. 11/07)">
														<styles font-family="Verdana" font-size="9pt"/>
													</text>
												</children>
											</tablecol>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" padding="0"/>
												<properties align="left"/>
												<children>
													<text fixtext="Page: ">
														<styles font-family="Verdana" font-size="9pt"/>
													</text>
												</children>
											</tablecol>
											<tablecol>
												<styles font-size="smaller" padding="0"/>
												<properties align="right" width="150"/>
												<children>
													<text fixtext="Form Page 5">
														<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
													</text>
												</children>
											</tablecol>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
				</children>
			</template>
		</footerall>
	</pagelayout>
</structure>
