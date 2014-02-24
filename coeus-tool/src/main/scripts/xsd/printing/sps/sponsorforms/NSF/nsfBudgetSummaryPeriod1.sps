<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="c:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="Y:\lmrobin\printing\xml\Proposal_00000702Sponsor_000500.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" font-size="7pt" line-height="7pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties align="center" border="1" cellpadding="0" cellspacing="0" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<properties height=".05in"/>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-style="none"/>
										<properties height="3" width="4in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-style="none"/>
										<properties height="3" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-style="none"/>
										<properties align="left" height="3" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-style="none"/>
										<properties height="3" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-style="none"/>
										<properties height="3" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-style="none"/>
										<properties align="right" height="3" width="1in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-style="none"/>
										<properties height="3" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="center" colspan="2" width=".5in"/>
										<children>
											<text fixtext="SUMMARY PROPOSAL BUDGET">
												<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties colspan="4" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none" font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" colspan="2" width=".5in"/>
										<children>
											<text fixtext="Budget Period 1"/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles background-color="gray" border-left-width="thick" border-top-width="thick"/>
										<properties align="center" colspan="4" width="1in"/>
										<children>
											<text fixtext="FOR NSF USE ONLY">
												<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<properties height=".15in"/>
								<children>
									<tablecol>
										<properties align="left" colspan="3" rowspan="2" valign="top" width=".5in"/>
										<children>
											<text fixtext="ORGANIZATION   ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
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
									</tablecol>
									<tablecol>
										<styles background-color="gray" border-left-width="thick"/>
										<properties align="center" colspan="2" valign="top" width=".5in"/>
										<children>
											<text fixtext="PROPOSAL NO.">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="center" colspan="2" width="1in"/>
										<children>
											<text fixtext="DURATION (MONTHS)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<properties height=".15in"/>
								<children>
									<tablecol>
										<styles background-color="gray" border-left-width="thick"/>
										<properties colspan="2" valign="top" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="center" width="1in"/>
										<children>
											<text fixtext="Proposed">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="center" width="1in"/>
										<children>
											<text fixtext="Granted">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties align="left" colspan="3" width=".5in"/>
										<children>
											<text fixtext="PRINCIPAL INVESTIGATOR/PROJECT DIRECTOR  ">
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
										<styles background-color="gray" border-left-width="thick"/>
										<properties align="center" colspan="2" valign="top" width=".5in"/>
										<children>
											<text fixtext="AWARD NO.">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="right" width="1in"/>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" rowspan="2" width=".5in"/>
										<children>
											<paragraph paragraphtag="p">
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
										<properties colspan="3" width=".5in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="NSF-Fun">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
													<text fixtext="ded">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="Person-months">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text>
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties align="center" rowspan="2" width="1in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Funds">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Requested by">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="Proposer">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties align="center" rowspan="2" width="1in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Funds">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Granted by NSF">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="(If">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" Different)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
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
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext="ACAD">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
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
										<styles padding-left="6pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties align="left" colspan="2" width=".5in"/>
										<children>
											<text fixtext="B. OTHER PERSONNEL (SHOW NUMBERS IN BRACKETS)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties colspan="5" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
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
											<text fixtext=" )  POSTDOCTORAL">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<properties colspan="2" width=".5in"/>
									</tablecol>
									<tablecol>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
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
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<properties colspan="2" width=".5in"/>
									</tablecol>
									<tablecol>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="TOTAL SALARIES AND WAGES (A + B)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="TOTAL">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" SALARIES,">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" WAGES, AND FRING">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="E BENEFITS (A+B+C)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="D. EQUIPMENT (LIST ITEM AND DOLLAR AMOUN">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
													<text fixtext="T FOR EACH ITEM EXCEEDING $5000.)   ">
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
																								<match match="n3:EquipmentCosts"/>
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
											<text>
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="right" colspan="2" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-top-style="none" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="TO">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="TAL EQ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="UIPMENT">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
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
																						<match match="n3:TravelCosts"/>
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
																						<match match="n3:TravelCosts"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left=".9in"/>
										<properties colspan="5" width=".5in"/>
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
																						<match match="n3:TravelCosts"/>
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
																						<match match="n3:TravelCosts"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="  PARTICIPANT SUPPORT  ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<paragraph paragraphtag="p">
												<styles font-family="Verdana" font-size="7pt"/>
												<children>
													<text fixtext="     1. STIPENDS             $">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
													<autovalue>
														<editorproperties editable="0"/>
														<autocalc>
															<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:ParticipantPatientCosts[Type=&apos;Participant Stipends&apos;]/Cost )"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:ParticipantPatientCosts[Type=&apos;Participant Travel&apos;]/Cost )"/>
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
															<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:ParticipantPatientCosts[Type=&apos;Participant Subsistence&apos;]/Cost )"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:ParticipantPatientCosts[Type=&apos;Participant Other&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="right" width="1in"/>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="TOTAL NUMBER OF PARTICIPANTS(   )    TOTAL PARTICIPANT COSTS">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="G. OTHER DIRECT COSTS">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="right" width="1in"/>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="9pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="1. MATERIALS AND SUPPLIES">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="9pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="2. PUBLICATION/DOCUMENTATION/DISSEMINATION">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="9pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="3. CONSULTANT SERVICES">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="9pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="4. COMPUTER SERVICES">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="9pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="5. SUBAWARDS">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="9pt"/>
										<properties colspan="5" width=".5in"/>
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
																						<match match="n3:OtherDirectCosts"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="9pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="TOTAL O">
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="I. IND">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="IRECT COSTS (F &amp; A) (SPECIFY RATE AND BASE)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="right" width="1in"/>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-top-style="none" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
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
											<text fixtext="STS (F">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" &amp; A)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
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
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
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
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost) +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/IndirectCostsTotal"/>
												</autocalc>
												<format string="##,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="K. RE">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="SIDUAL FUNDS (IF FOR FURTHER SUPPORT OF CURRENT PROJECT SEE GPG II.D.7.j.)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties align="right" width="1in"/>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="L. AMOUNT OF THIS REQUEST (J MINUS K)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
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
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost) +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/IndirectCostsTotal"/>
												</autocalc>
												<format string="##,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<properties width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties valign="top" width="4in"/>
										<children>
											<text fixtext="M. COST SHARING: PROPOSED LEVEL             ">
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
																						<match match="NSFCostSharingAmount"/>
																						<children>
																							<text fixtext="$"/>
																							<xpath allchildren="1">
																								<styles font-family="Verdana" font-size="7pt"/>
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
											<text fixtext="  ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties colspan="6" width="1in"/>
										<children>
											<text fixtext="AGREED LEVEL IF DIFFERENT: $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties rowspan="2" width="4in"/>
										<children>
											<text fixtext="PI/PD TYPED NAME AND SIGNATURE*  ">
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
										<properties align="left" colspan="2" rowspan="2" width=".5in"/>
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
									<tablecol>
										<styles background-color="gray"/>
										<properties align="center" colspan="4" width="1in"/>
										<children>
											<text fixtext="FOR NSF USE ONLY">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles background-color="gray"/>
										<properties colspan="4" width="1in"/>
										<children>
											<text fixtext="INDIRECT COST RATE VERIFICATION">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties valign="top" width="4in"/>
										<children>
											<text fixtext="OR">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="G. REP.">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" TY">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="PED NAME AND ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="SIGNATURE*  ">
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
										<properties align="left" colspan="2" width=".5in"/>
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
									<tablecol>
										<styles background-color="gray"/>
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="Date Checked">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="Date ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="of Rate Sheet">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties width="1in"/>
										<children>
											<text fixtext="Initials">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="-ORG">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
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
									<tablecol>
										<styles border-style="none"/>
										<properties height="1" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
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
													<text fixtext="NSF Form 1030 (10/98) Supersedes All Previous Editions">
														<styles font-weight="bold"/>
													</text>
												</children>
											</tablecol>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" padding="0"/>
												<properties align="center" width="4in"/>
												<children>
													<text fixtext="*SIGNATURES REQUIRED ONLY FOR REVISED BUDGET (GPG III.C)">
														<styles font-weight="bold"/>
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
