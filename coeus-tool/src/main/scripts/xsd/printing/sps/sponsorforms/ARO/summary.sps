<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="c:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\coeusBuild\build\web\Reports\Proposal00001858.xml" templatexmlfile="" xsltversion="1" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
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
										<properties colspan="3" height="3" width=".5in"/>
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
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="center" colspan="4" width=".5in"/>
										<children>
											<text fixtext=" PROPOSAL BUDGET">
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
										<properties colspan="3" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none" font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" colspan="4" width=".5in"/>
										<children>
											<text fixtext="Total Project"/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="left" colspan="4" width=".5in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<properties height=".15in"/>
								<children>
									<tablecol>
										<properties align="left" colspan="8" valign="top" width=".5in"/>
										<children>
											<text fixtext="OFFEROR   ">
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
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties align="left" colspan="8" width=".5in"/>
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
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties rowspan="2" width=".5in"/>
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
										<properties rowspan="2" width=".5in"/>
										<children>
											<text fixtext="Man">
												<styles font-size="7pt"/>
											</text>
											<newline/>
											<text fixtext="Hrs">
												<styles font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties colspan="2" rowspan="2" width=".5in"/>
										<children>
											<text fixtext="Rates">
												<styles font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties colspan="3" width=".5in"/>
										<children>
											<text fixtext="months">
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
											<text fixtext="Offeror">
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
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
										<styles padding-left="6pt"/>
										<properties colspan="4" width=".5in"/>
										<children>
											<text fixtext="6. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="count(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/Rownumber)&gt;5"/>
														</testexpression>
														<children>
															<autovalue>
																<styles font-family="Verdana" font-size="7pt"/>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="count(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/Rownumber)-5"/>
																</autocalc>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
											<text fixtext=" ) OTHERS (LIST INDIVIDUALLY ON BUDGET EXPLANATION)">
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber&gt;4]/CalendarMonthsFunded )"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber&gt;4]/AcademicMonthsFunded )"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber&gt;4]/SummerMonthsFunded )"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber&gt;4]/FundsRequested)"/>
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
										<styles padding-left="6pt"/>
										<properties colspan="4" width=".5in"/>
										<children>
											<text fixtext="7. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="count(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/Rownumber)"/>
												</autocalc>
											</autovalue>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/CalendarMonthsFunded )"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/AcademicMonthsFunded )"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/SummerMonthsFunded )"/>
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
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/FundsRequested  )"/>
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
										<styles padding-left="6pt"/>
										<properties align="left" colspan="4" width=".5in"/>
										<children>
											<text fixtext="B. OTHER PERSONNEL (SHOW NUMBERS IN PARENTHESES)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties colspan="4" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="4" width=".5in"/>
										<children>
											<text fixtext="1. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/PostDocCount )"/>
												</autocalc>
											</autovalue>
											<text fixtext=" )  POST DOCTORAL ASSOCIATES">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<properties colspan="2" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width=".5in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/PostDocFunds )"/>
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
										<styles padding-left="6pt"/>
										<properties colspan="4" width=".5in"/>
										<children>
											<text fixtext="2. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherProfCount )"/>
												</autocalc>
											</autovalue>
											<text fixtext=" )   OTHER PROFESSIONS (TECHNICIAN, PROGRAMMER, ETC.)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<properties colspan="2" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width=".5in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherProfFunds )"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="3. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/GradCount )"/>
												</autocalc>
											</autovalue>
											<text fixtext=" ) GRADUATE STUDENTS">
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/GradFunds )"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="4. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/UnderGradCount )"/>
												</autocalc>
											</autovalue>
											<text fixtext=" ) UNDERGRADUATE STUDENTS">
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/UnderGradFunds )"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="5. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/ClericalCount )"/>
												</autocalc>
											</autovalue>
											<text fixtext=" ) SECRETARIAL - CLERICAL (IF CHARGED DIRECTLY)">
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/ClericalFunds )"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="6. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherCount )"/>
												</autocalc>
											</autovalue>
											<text fixtext=" ) OTHER                   (PERSONNEL LAB ALLOCATION: ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="         $"/>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherLAFunds )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
											<text fixtext="   ) ">
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
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherLAFunds ) + sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherFunds )"/>
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
										<styles padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
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
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/FundsRequested  ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/PostDocFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherProfFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/GradFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/UnderGradFunds ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherLAFunds ) + sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherFunds )"/>
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
										<styles padding-left="6pt"/>
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
																<match match="TotalFringe"/>
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
											</template>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
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
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/FundsRequested  ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/PostDocFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherProfFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/GradFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/UnderGradFunds ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherLAFunds ) +
 sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherFunds ) +
 n1:ResearchAndRelatedProject/BudgetSummary/TotalFringe"/>
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
										<styles border-bottom-style="none" font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<paragraph paragraphtag="p">
												<styles font-family="Verdana" font-size="7pt"/>
												<children>
													<text fixtext="D. EQUIPMENT (LIST ITEM AND DOLLAR AMOUN">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
													<text fixtext="T FOR EACH ITEM EXCEEDING $5000.)">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="   ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text>
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
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
										<styles border-top-style="none" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:EquipmentCosts/Cost)"/>
												</autocalc>
												<format string="#,###,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="E. TRAVEL         ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="  1. DOMESTIC (INCL. CANADA, MEXICO AND U.S. POSSESSIONS)     ">
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:TravelCosts[Type=&apos;Domestic Travel&apos;]/Cost)"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left=".9in"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="2. FOREIGN     ">
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:TravelCosts[Type=&apos;Foreign Travel&apos;]/Cost )"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
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
															<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:ParticipantPatientCosts[Type=&apos;Participant Stipends&apos;]/Cost )"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:ParticipantPatientCosts[Type=&apos;Participant Travel&apos;]/Cost )"/>
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
															<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:ParticipantPatientCosts[Type=&apos;Participant Subsistence&apos;]/Cost )"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:ParticipantPatientCosts[Type=&apos;Participant Other&apos;]/Cost )"/>
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
										<styles padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
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
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ParticipantPatientTotal )"/>
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
										<styles padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
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
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt" padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost )"/>
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
										<styles padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost )"/>
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
										<styles padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost )"/>
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
										<styles padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost )"/>
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
										<styles padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
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
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost )"/>
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
										<styles padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="6. OTHER                                      (NON-PERSONNEL LAB ALLOCATION:         $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;][ starts-with( Description , &apos;LA&apos; )]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
											<text fixtext=" ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
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
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost )"/>
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
										<styles padding-left="9pt"/>
										<properties colspan="7" width=".5in"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)"/>
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
										<styles padding-left="6pt"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/FundsRequested  ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/PostDocFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherProfFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/GradFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/UnderGradFunds ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherLAFunds ) +
 sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherFunds ) +
 n1:ResearchAndRelatedProject/BudgetSummary/TotalFringe +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:EquipmentCosts/Cost) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:TravelCosts[Type=&apos;Foreign Travel&apos;]/Cost ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:TravelCosts[Type=&apos;Domestic Travel&apos;]/Cost) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) 
+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) 
+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) 
+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) 
+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost) 
+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)"/>
												</autocalc>
												<format string="#,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="I. IND">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="IRECT COSTS (F &amp; A)   --                                                    ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="SEE ATTACHED">
												<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
											</text>
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
										<styles border-top-style="none" padding-left="6pt"/>
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
											<text fixtext="STS (F">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext=" &amp; A)">
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
																<match match="BudgetIndirectCostsTotal"/>
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
											</template>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/FundsRequested  ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/PostDocFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherProfFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/GradFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/UnderGradFunds ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherLAFunds ) +
 sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherFunds ) +
 n1:ResearchAndRelatedProject/BudgetSummary/TotalFringe +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:EquipmentCosts/Cost) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:TravelCosts[Type=&apos;Foreign Travel&apos;]/Cost ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:TravelCosts[Type=&apos;Domestic Travel&apos;]/Cost) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) 

+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) 

+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) 

+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) 
+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost) 
+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)
+  n1:ResearchAndRelatedProject/BudgetSummary/BudgetIndirectCostsTotal"/>
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
										<styles padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="K. FEE(     %) BASE $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties align="right" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="6pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<text fixtext="L. COST SHARING">
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
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/FundsRequested  ) + 
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/PostDocFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherProfFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/GradFunds ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/UnderGradFunds ) +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherLAFunds ) +
 sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherFunds ) +
 n1:ResearchAndRelatedProject/BudgetSummary/TotalFringe +
sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:EquipmentCosts/Cost) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:TravelCosts[Type=&apos;Foreign Travel&apos;]/Cost ) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:TravelCosts[Type=&apos;Domestic Travel&apos;]/Cost) +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) 

+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) 

+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) 

+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) 
+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost) 
+ sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)
+  n1:ResearchAndRelatedProject/BudgetSummary/BudgetIndirectCostsTotal"/>
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
										<styles padding-left="6pt"/>
										<properties valign="top" width="4in"/>
										<children>
											<text fixtext="M. AMOUNT OF THIS REQUEST     $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFCostSharingAmount )"/>
												</autocalc>
												<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
											<text fixtext="  ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties colspan="7" width="1in"/>
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
										<properties width="4in"/>
										<children>
											<text fixtext="PI/PD TYPED NAME AND SIGNATURE">
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
										<properties align="left" colspan="7" width=".5in"/>
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
										<properties valign="top" width="4in"/>
										<children>
											<text fixtext="OFFEROR&apos;S AUTHORIZED REP. NAME  &amp; ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<text fixtext="SIGNATURE  ">
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
										<properties align="left" colspan="7" width=".5in"/>
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
										<properties colspan="3" height="1" width=".5in"/>
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
