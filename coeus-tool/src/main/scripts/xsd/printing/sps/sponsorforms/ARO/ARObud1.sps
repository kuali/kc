<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="c:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\coeusBuild\build\web\Reports\aro.xml" templatexmlfile="" xsltversion="1" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<styles font-family="Verdana" font-size="7pt" line-height="7pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties align="center" bgcolor="white" border="1" cellpadding="1" cellspacing="1" frame="box" rules="all" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" font-weight="bold" line-height="20pt"/>
										<properties align="center" colspan="2" width=".5in"/>
										<children>
											<text fixtext="       PROPOSAL BUDGET - "/>
											<text fixtext="YEAR 1"/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles background-color="white" border-left-width="thick" border-top-width="thick"/>
										<properties align="center" colspan="3" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<properties height=".15in"/>
								<children>
									<tablecol>
										<styles border-bottom-color="black" border-bottom-style="solid" border-bottom-width="thin" border-color="black" border-left-color="black" border-left-style="solid" border-left-width="thin" border-style="solid" border-top-color="black" border-top-style="solid" border-top-width="thin" border-width="thin" font-size="7pt"/>
										<properties align="left" colspan="8" height="29" valign="bottom" width=".5in"/>
										<children>
											<text fixtext="OFFEROR    "/>
											<newline/>
											<text fixtext="  "/>
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
																			<xpath allchildren="1">
																				<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-color="black" border-bottom-style="solid" border-bottom-width="thin" border-left-color="black" border-left-style="solid" border-left-width="thin"/>
										<properties align="left" colspan="8" width=".5in"/>
										<children>
											<text fixtext="PRINCIPAL INVESTIGATOR/PROJECT DIRECTOR(PI/PD) :     ">
												<styles font-family="Verdana" font-size="7pt"/>
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
																						<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=", ">
																				<styles font-family="Verdana" font-size="10pt"/>
																			</text>
																			<template>
																				<match match="FirstName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=" ">
																				<styles font-family="Verdana" font-size="10pt"/>
																			</text>
																			<template>
																				<match match="MiddleName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=" ">
																				<styles font-family="Verdana" font-size="10pt"/>
																			</text>
																			<template>
																				<match match="NameSuffix"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
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
										<styles border-left-color="black" border-left-style="solid" border-left-width="thin" line-height="10pt"/>
										<properties colspan="2" rowspan="2" valign="bottom" width=".5in"/>
										<children>
											<paragraph paragraphtag="p">
												<styles border-top-color="black" border-top-style="solid" border-top-width="thin" font-size="7pt" line-height="10pt"/>
												<children>
													<text fixtext="A. S">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
													<text fixtext="ENIOR">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
													<text fixtext=" PERSONNEL: PI/PD, Co-PIs, Faculty and Other Senior Associates">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="List each separately with name and ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="title. (A.7. Show number in brackets)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-size="7pt"/>
										<properties width=".5in"/>
										<children>
											<text fixtext="Man Hrs/Mo"/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-size="7pt"/>
										<properties width=".5in"/>
										<children>
											<text fixtext="Rates"/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties colspan="3" width=".5in"/>
										<children>
											<paragraph paragraphtag="p">
												<styles border-color="white" border-style="solid" border-width="thin" font-size="7pt"/>
												<children>
													<text fixtext="Mos"/>
												</children>
											</paragraph>
											<text>
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" rowspan="2" width="1in"/>
										<children>
											<paragraph paragraphtag="p">
												<styles border-color="white" border-style="solid" border-width="thin"/>
												<children>
													<text fixtext="Funds">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<styles border-color="white" border-style="solid" border-width="thin"/>
												<children>
													<text fixtext="Requested by Offeror">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext="CA">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="L">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text>
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext="ACAD">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext="SUMR">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-color="black" border-bottom-style="solid" border-bottom-width="thin" border-left-color="black" border-left-style="solid" border-left-width="thin" border-right-color="black" border-right-style="solid" border-right-width="thin" border-top-color="black" border-top-style="solid" border-top-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="1. ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=0"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="FullName"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=0"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="CalendarMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=0"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="AcademicMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-right-color="black" border-right-style="solid" border-right-width="thin" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=0"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="SummerMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=0"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="FundsRequested"/>
																												<children>
																													<text fixtext="$"/>
																													<xpath allchildren="1">
																														<format string="#,###,###,##0.00" xslt="1"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-bottom-color="black" border-bottom-style="solid" border-bottom-width="thin" border-color="black" border-left-color="black" border-left-style="solid" border-left-width="thin" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="2. ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=1"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="FullName"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=1"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="CalendarMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=1"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="AcademicMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=1"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="SummerMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=1"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="FundsRequested"/>
																												<children>
																													<text fixtext="$"/>
																													<xpath allchildren="1">
																														<format string="#,###,###,##0.00" xslt="1"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-bottom-color="black" border-bottom-style="solid" border-bottom-width="thin" border-color="black" border-left-color="black" border-left-style="solid" border-left-width="thin" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="3. ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=2"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="FullName"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=2"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="CalendarMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=2"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="AcademicMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=2"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="SummerMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=2"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="FundsRequested"/>
																												<children>
																													<text fixtext="$"/>
																													<xpath allchildren="1">
																														<format string="#,###,###,##0.00" xslt="1"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-bottom-color="black" border-bottom-style="solid" border-bottom-width="thin" border-color="black" border-left-color="black" border-left-style="solid" border-left-width="thin" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="4. ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=3"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="FullName"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=3"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="CalendarMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=3"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="AcademicMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=3"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="SummerMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=3"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="FundsRequested"/>
																												<children>
																													<text fixtext="$"/>
																													<xpath allchildren="1">
																														<format string="#,###,###,##0.00" xslt="1"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-left-color="black" border-left-style="solid" border-left-width="thin" border-style="solid" border-top-color="black" border-top-style="solid" border-top-width="thin" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="5. ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=4"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="FullName"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=4"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="CalendarMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=4"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="AcademicMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=4"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="SummerMonthsFunded"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="7pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFSeniorPersonnel"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Rownumber=4"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="FundsRequested"/>
																												<children>
																													<text fixtext="$"/>
																													<xpath allchildren="1">
																														<format string="#,###,###,##0.00" xslt="1"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" line-height="15pt" padding-left="6pt"/>
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="6. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFTotalSeniorPersonnel &gt;5"/>
														</testexpression>
														<children>
															<autovalue>
																<styles font-family="Verdana" font-size="7pt"/>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFTotalSeniorPersonnel)-5"/>
																</autocalc>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
											<text fixtext=" ) OTHERS (LIST INDIVIDUALLY ON BUDGET EXPLANATION PAGE)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel[Rownumber&gt;4]/CalendarMonthsFunded)"/>
												</autocalc>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel[Rownumber&gt;4]/AcademicMonthsFunded)"/>
												</autocalc>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel[Rownumber&gt;4]/SummerMonthsFunded)"/>
												</autocalc>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel[Rownumber&gt;4]/FundsRequested)"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="15pt" padding-left="6pt"/>
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="7. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID =1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFTotalSeniorPersonnel"/>
																						<children>
																							<xpath allchildren="1"/>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext=" )  TOTAL SENIOR PERSONNEL (1-6)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext=" "/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel/CalendarMonthsFunded )"/>
												</autocalc>
												<format string="#0.#" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel/AcademicMonthsFunded )"/>
												</autocalc>
												<format string="#0.#" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel/SummerMonthsFunded )"/>
												</autocalc>
												<format string="#0.#" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel/FundsRequested  )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="15pt" padding-left="6pt"/>
										<properties align="left" colspan="2" width=".5in"/>
										<children>
											<text fixtext="B. OTHER PERSONNEL (SHOW NUMBERS IN PARENTHESES)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray" border-color="black" border-style="solid" border-width="thin"/>
										<properties width="1in"/>
									</tablecol>
									<tablecol>
										<styles background-color="gray" border-color="black" border-style="solid" border-width="thin"/>
										<properties width="1in"/>
									</tablecol>
									<tablecol>
										<styles background-color="gray" border-color="black" border-style="solid" border-width="thin"/>
										<properties colspan="4" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="15pt" padding-left="6pt"/>
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="1. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="PostDocCount"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext=" )  POSTDOCTORAL ASSOCIATES">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties colspan="2" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="PostDocFunds"/>
																								<children>
																									<text fixtext="$"/>
																									<xpath allchildren="1">
																										<format string="#,###,###,##0.00" xslt="1"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" line-height="15pt" padding-left="6pt"/>
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="2. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="OtherProfCount"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext=" )   OTHER PROFESSIONS (TECHNICIAN, PROGRAMMER, ETC.)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties colspan="2" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="OtherProfFunds"/>
																								<children>
																									<text fixtext="$"/>
																									<xpath allchildren="1">
																										<format string="#,###,###,##0.00" xslt="1"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="3. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="GradCount"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext=" ) GRADUATE STUDENTS">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="GradFunds"/>
																								<children>
																									<text fixtext="$"/>
																									<xpath allchildren="1">
																										<format string="#,###,###,##0.00" xslt="1"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="4. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="UnderGradCount"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext=" ) UNDERGRADUATE STUDENTS">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="UnderGradFunds"/>
																								<children>
																									<text fixtext="$"/>
																									<xpath allchildren="1">
																										<format string="#,###,###,##0.00" xslt="1"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" line-height="15pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="5. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="ClericalCount"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext=" ) SECRETARIAL - CLERICAL (IF CHARGED DIRECTLY)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="ClericalFunds"/>
																								<children>
																									<text fixtext="$"/>
																									<xpath allchildren="1">
																										<format string="#,###,###,##0.00" xslt="1"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="6. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="OtherCount"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext=" ) OTHER                   (PERSO">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="NNEL LAB ALLOCATION:         ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="NSFOtherPersonnel"/>
																						<children>
																							<template>
																								<match match="OtherLAFunds"/>
																								<children>
																									<text fixtext="$"/>
																									<xpath allchildren="1">
																										<format string="#,###,###,##0.00" xslt="1"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext=" "/>
											<text fixtext=" )">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherFunds +  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherLAFunds"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="15pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="7.  TOTAL SALARIES AND WAGES (A + B)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel/FundsRequested  )+
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/PostDocFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherProfFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/GradFunds ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/UnderGradFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/ClericalFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherLAFunds  )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="C. FRINGE BEN">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="EFITS (IF ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="CHARGED AS DIRECT COSTS)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="SalarySubtotals"/>
																						<children>
																							<template>
																								<match match="FringeBenefits"/>
																								<children>
																									<text fixtext="$"/>
																									<xpath allchildren="1">
																										<format string="#,###,###,##0.00" xslt="1"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="TOTAL">
												<styles border-color="white" font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" SALARIES,">
												<styles border-color="white" font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" WAGES, AND FRING">
												<styles border-color="white" font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="E BENEFITS (A+B+C)">
												<styles border-color="white" font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel/FundsRequested  )+
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/PostDocFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherProfFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/GradFunds ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/UnderGradFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/ClericalFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherLAFunds  ) +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalarySubtotals/FringeBenefits"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-color="black" border-style="solid" border-width="thin" font-size="7pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<paragraph paragraphtag="p">
												<styles border-color="white" border-style="solid" border-width="thin" font-size="7pt"/>
												<children>
													<text fixtext="D. PERMANENT EQUIPMENT (LIST ITEM AND DOLLAR AMOUN">
														<styles border-color="white" border-style="none" font-family="Verdana" font-size="7pt"/>
													</text>
													<text fixtext="T FOR EACH ITEM EXCEEDING $5000.)   ">
														<styles border-color="white" border-style="none" font-family="Verdana" font-size="7pt"/>
													</text>
													<template>
														<styles font-family="Verdana" font-size="7pt"/>
														<match match="n1:ResearchAndRelatedProject"/>
														<children>
															<template>
																<match match="BudgetSummary"/>
																<children>
																	<template>
																		<match match="BudgetPeriod"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="BudgetPeriodID=1"/>
																						</testexpression>
																						<children>
																							<template>
																								<match match="rar:EquipmentCosts"/>
																								<children>
																									<text fixtext=" "/>
																									<template>
																										<match match="EquipmentDescription"/>
																										<children>
																											<xpath allchildren="1"/>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</choiceoption>
																				</children>
																			</choice>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</paragraph>
											<text fixtext="Attach additional explanation pages, if necessary."/>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="right" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-color="black" border-bottom-style="solid" border-bottom-width="thin" border-color="black" border-right-color="black" border-right-style="solid" border-right-width="thin" border-style="solid" border-top-color="black" border-top-style="solid" border-top-width="thin" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="TO">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="TAL PERMANENT EQ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="UIPMENT">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="EquipmentTotal"/>
																						<children>
																							<text fixtext="$"/>
																							<xpath allchildren="1">
																								<format string="#,###,###,##0.00" xslt="1"/>
																							</xpath>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="E. TRAVEL           1. DOMESTIC (INCL. CANADA, MEXICO AND U.S. POSSESSIONS)   ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="rar:TravelCosts"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Type =&apos;Domestic Travel&apos;"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="Description"/>
																												<children>
																													<xpath allchildren="1"/>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="rar:TravelCosts"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Type=&apos;Domestic Travel&apos;"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="Cost"/>
																												<children>
																													<text fixtext="$"/>
																													<xpath allchildren="1">
																														<format string="#,###,###,##0.00" xslt="1"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt" line-height="12pt" padding-left=".9in"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="2. FOREIGN    ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="rar:TravelCosts"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Type =&apos;Foreign Travel&apos;"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="Description"/>
																												<children>
																													<xpath allchildren="1"/>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="rar:TravelCosts"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="Type=&apos;Foreign Travel&apos;"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="Cost"/>
																												<children>
																													<text fixtext="$"/>
																													<xpath allchildren="1">
																														<format string="#,###,###,##0.00" xslt="1"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="  PARTICIPANT SUPPORT  COSTS">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<paragraph paragraphtag="p">
												<styles border-color="white" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
												<children>
													<text fixtext="     1. STIPENDS             $">
														<styles border-color="white" border-style="none" border-width="thin" font-family="Verdana" font-size="7pt"/>
													</text>
													<autovalue>
														<editorproperties editable="0"/>
														<autocalc>
															<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:ParticipantPatientCosts[Type=&apos;Participant Stipends&apos;]/Cost )"/>
														</autocalc>
														<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
													</autovalue>
												</children>
											</paragraph>
											<text fixtext="     2. TRAVEL                 $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:ParticipantPatientCosts[Type=&apos;Participant Travel&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
											<paragraph paragraphtag="p">
												<styles line-height="6pt"/>
												<children>
													<text fixtext="     3. SUBSISTENCE      $">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
													<autovalue>
														<editorproperties editable="0"/>
														<autocalc>
															<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:ParticipantPatientCosts[Type=&apos;Participant Subsistence&apos;]/Cost )"/>
														</autocalc>
														<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
													</autovalue>
												</children>
											</paragraph>
											<text fixtext="     4. OTHER                  $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:ParticipantPatientCosts[Type=&apos;Participant Other&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="right" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="(   )    TOTAL PARTICIPANT COSTS">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="ParticipantPatientTotal"/>
																						<children>
																							<text fixtext="$"/>
																							<xpath allchildren="1">
																								<format string="#,###,###,##0.00" xslt="1"/>
																							</xpath>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="G. OTHER DIRECT COSTS (ITEMIZE PN BUDGET EXPLANATION PAGE)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray" border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt" line-height="12pt" padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="1. MATERIALS AND SUPPLIES">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="2. PUBLICATION/DOCUMENTATION/DISSEMINATION">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="3. CONSULTANT SERVICES">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="4. COMPUTER (ADPE) SERVICES">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="5. SUBAWARDS">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="6. OTHER                                      (NON-PERSONNEL LAB ALLOCATION:          ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="rar:OtherDirectCosts"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="starts-with( Description, &apos;LA&apos; )"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="Cost"/>
																												<children>
																													<text fixtext="$"/>
																													<xpath allchildren="1">
																														<format string="#,###,###,##0.00" xslt="1"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext=" )">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="7. TOTAL O">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="T">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="HER DIRECT COSTS">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="H. T">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="OTAL DI">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="RE">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="CT ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="COSTS (A THROUGH G)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$">
												<styles font-family="Verdana" font-size="8pt"/>
											</text>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel/FundsRequested  )+
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/PostDocFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherProfFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/GradFunds ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/UnderGradFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/ClericalFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherLAFunds  ) +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalarySubtotals/FringeBenefits + 
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/EquipmentTotal +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/TravelTotal  +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID]/ParticipantPatientTotal +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-color="black" border-style="solid" border-width="thin" line-height="20pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="I. IND">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="IRECT COSTS                        ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" SEE ATTACHED FOR DETAILS">
												<styles font-family="Verdana" font-size="7pt" font-weight="bold"/>
											</text>
											<newline/>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray" border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-top-style="none" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="T">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="OTAL INDIR">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="ECT CO">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="STS ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
										<children>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetPeriod"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="BudgetPeriodID=1"/>
																				</testexpression>
																				<children>
																					<template>
																						<match match="IndirectCostsTotal"/>
																						<children>
																							<text fixtext="$"/>
																							<xpath allchildren="1">
																								<format string="#,###,###,##0.00" xslt="1"/>
																							</xpath>
																						</children>
																					</template>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="J. TOTAL DIRECT AN">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="D INDIRECT COSTS (H + I)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel/FundsRequested  )+
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/PostDocFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherProfFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/GradFunds ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/UnderGradFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/ClericalFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherLAFunds  ) +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalarySubtotals/FringeBenefits + 
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/EquipmentTotal +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/TravelTotal  +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID]/ParticipantPatientTotal +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost) +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/IndirectCostsTotal"/>
												</autocalc>
												<format string="##,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="K. FEE (   %) BASE $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="right" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="L.  COST SHARING ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFSeniorPersonnel/FundsRequested  )+
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/PostDocFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherProfFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/GradFunds ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/UnderGradFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/ClericalFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherFunds  ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/NSFOtherPersonnel/OtherLAFunds  ) +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalarySubtotals/FringeBenefits + 
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/EquipmentTotal +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/TravelTotal  +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID]/ParticipantPatientTotal +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost) +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/IndirectCostsTotal"/>
												</autocalc>
												<format string="##,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties colspan="7" valign="top" width="4in"/>
										<children>
											<text fixtext="M. AMOUNT OF THIS REQUEST       ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="  ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="12pt" padding-left="6pt"/>
										<properties valign="top" width="4in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<styles font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:NSFSeniorPersonnel/n1:FundsRequested  )+
sum( n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:NSFOtherPersonnel/n1:PostDocFunds  ) +
sum( n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:NSFOtherPersonnel/n1:OtherProfFunds  ) +
sum( n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:NSFOtherPersonnel/n1:GradFunds ) +
sum( n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:NSFOtherPersonnel/n1:UnderGradFunds  ) +
sum( n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:NSFOtherPersonnel/n1:ClericalFunds  ) +
sum( n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:NSFOtherPersonnel/n1:OtherFunds  ) +
sum( n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:NSFOtherPersonnel/n1:OtherLAFunds  ) +
 n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:SalarySubtotals/n1:FringeBenefits + 
 n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:EquipmentTotal +
 n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:TravelTotal  +
 n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID]/n1:ParticipantPatientTotal +
sum(n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/rar:OtherDirectCosts[n1:Type=&apos;Materials and Supplies&apos;]/n1:Cost ) +
 sum(n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/rar:OtherDirectCosts[n1:Type=&apos;Publication Costs&apos;]/n1:Cost ) + 
sum(n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/rar:OtherDirectCosts[n1:Type=&apos;Consultant Costs&apos;]/n1:Cost ) + 
sum(n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/rar:OtherDirectCosts[n1:Type=&apos;Computer Services&apos;]/n1:Cost ) + sum(n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/rar:OtherDirectCosts[n1:Type=&apos;Subcontract&apos;]/n1:Cost ) + 
sum(n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/rar:OtherDirectCosts[n1:Type=&apos;Other Direct Costs&apos;]/n1:Cost) +
 n1:ResearchAndRelatedProject/n1:BudgetSummary/n1:BudgetPeriod[n1:BudgetPeriodID=1]/n1:IndirectCostsTotal"/>
												</autocalc>
												<format string="###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="16pt"/>
										<properties width="4in"/>
										<children>
											<text fixtext="PI/PD NAME (TYPED)AND SIGNATURE ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
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
																						<styles font-family="Verdana" font-size="7pt"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=",  ">
																				<styles font-family="Verdana" font-size="7pt"/>
																			</text>
																			<template>
																				<match match="FirstName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="7pt"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=" ">
																				<styles font-family="Verdana" font-size="7pt"/>
																			</text>
																			<template>
																				<match match="MiddleName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="7pt"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=" ">
																				<styles font-family="Verdana" font-size="7pt"/>
																			</text>
																			<template>
																				<match match="NameSuffix"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="7pt"/>
																					</xpath>
																				</children>
																			</template>
																		</children>
																	</template>
																	<text fixtext="    ">
																		<styles font-family="Verdana" font-size="7pt"/>
																	</text>
																	<template>
																		<match match="DirectorInvestigatorSignature"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="SignatureAuthentication  != unknown"/>
																						</testexpression>
																						<children>
																							<template>
																								<match match="SignatureAuthentication"/>
																								<children>
																									<xpath allchildren="1">
																										<styles font-family="Verdana" font-size="7pt"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</choiceoption>
																				</children>
																			</choice>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext="     ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="left" colspan="7" valign="top" width=".5in"/>
										<children>
											<text fixtext="DATE   ">
												<styles font-family="Verdana" font-size="7pt"/>
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
																		<match match="DirectorInvestigatorSignature"/>
																		<children>
																			<template>
																				<match match="SignatureDate"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="7pt"/>
																						<format string="MM/DD/YYYY" xslt="1"/>
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
											<text>
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin" line-height="16pt"/>
										<properties valign="top" width="4in"/>
										<children>
											<text fixtext="OFFEROR&apos;S AUTHORIZED">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" REP.">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" TY">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="PED NAME AND ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="SIGNATURE ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="n1:ResearchCoverPage"/>
														<children>
															<template>
																<match match="AuthorizedOrganizationalRepresentative"/>
																<children>
																	<template>
																		<match match="Name"/>
																		<children>
																			<template>
																				<match match="LastName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="7pt"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=", ">
																				<styles font-family="Verdana" font-size="7pt"/>
																			</text>
																			<template>
																				<match match="FirstName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="7pt"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=" ">
																				<styles font-family="Verdana" font-size="7pt"/>
																			</text>
																			<template>
																				<match match="MiddleName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="7pt"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=" ">
																				<styles font-family="Verdana" font-size="7pt"/>
																			</text>
																			<template>
																				<match match="NameSuffix"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="7pt"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=" ">
																				<styles font-family="Verdana" font-size="7pt"/>
																			</text>
																			<newline/>
																		</children>
																	</template>
																	<text fixtext="     ">
																		<styles font-family="Verdana" font-size="7pt"/>
																	</text>
																	<template>
																		<match match="OrganizationalOfficialSignature"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="SignatureAuthentication !=unknown"/>
																						</testexpression>
																						<children>
																							<template>
																								<match match="SignatureAuthentication"/>
																								<children>
																									<xpath allchildren="1">
																										<styles font-family="Verdana" font-size="7pt"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</choiceoption>
																				</children>
																			</choice>
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
									<tablecol>
										<styles border-color="black" border-style="solid" border-width="thin"/>
										<properties align="left" colspan="7" valign="top" width=".5in"/>
										<children>
											<text fixtext="DATE   ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<template>
												<styles font-family="Verdana" font-size="7pt"/>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="n1:ResearchCoverPage"/>
														<children>
															<template>
																<match match="AuthorizedOrganizationalRepresentative"/>
																<children>
																	<template>
																		<match match="OrganizationalOfficialSignature"/>
																		<children>
																			<template>
																				<match match="SignatureDate"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="7pt"/>
																						<format string="MM/DD/YYYY" xslt="1"/>
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
								<properties height=".05in"/>
								<children>
									<tablecol>
										<styles border-style="none"/>
										<properties height="1" width="4in"/>
									</tablecol>
									<tablecol>
										<styles border-style="none"/>
										<properties height="1" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-style="none"/>
										<properties align="left" height="1" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-style="none"/>
										<properties align="left" height="1" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-style="none"/>
										<properties align="left" height="1" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-style="none"/>
										<properties height="1" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-style="none"/>
										<properties height="1" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-style="none"/>
										<properties align="right" height="1" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
			<newline/>
			<newline/>
			<text fixtext="INDIRECT COST DETAILS">
				<styles font-family="Verdana" font-size="medium" font-weight="bold"/>
			</text>
			<newline/>
			<newline/>
			<newline/>
			<template>
				<match match="n1:ResearchAndRelatedProject"/>
				<children>
					<template>
						<match match="BudgetSummary"/>
						<children>
							<template>
								<match match="BudgetPeriod"/>
								<children>
									<template>
										<match match="IndirectCostDetails"/>
										<children>
											<table dynamic="1">
												<properties border="1"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles font-size="small"/>
																		<children>
																			<text fixtext="Type">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-size="small"/>
																		<children>
																			<text fixtext="Rate">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-size="small"/>
																		<children>
																			<text fixtext="Base">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-size="small"/>
																		<children>
																			<text fixtext="Total">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles font-size="small"/>
																		<children>
																			<template>
																				<match match="BaseAmount"/>
																				<children>
																					<xpath allchildren="1"/>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-size="small"/>
																		<children>
																			<template>
																				<match match="Rate"/>
																				<children>
																					<xpath allchildren="1"/>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-size="small"/>
																		<children>
																			<template>
																				<match match="FundsRequested"/>
																				<children>
																					<xpath allchildren="1"/>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-size="small"/>
																	</tablecol>
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
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.2in" papermarginleft="0.4in" papermarginright="0.3in" papermargintop="0.2in" paperwidth="8.5in"/>
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
												<properties align="center" colspan="2" height="4" width="4.5in"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" padding="0"/>
												<properties align="left" width="4.5in"/>
												<children>
													<text fixtext="ARO Form 99 (MAY 97) ">
														<styles font-weight="bold"/>
													</text>
												</children>
											</tablecol>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" padding="0"/>
												<properties align="center" width="4in"/>
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
