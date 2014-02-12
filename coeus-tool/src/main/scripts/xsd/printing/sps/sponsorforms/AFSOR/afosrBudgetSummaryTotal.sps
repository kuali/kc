<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="c:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="c:\coeus4\reports\afosrJune8_01.xml" templatexmlfile="">
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
								<children>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none" font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" colspan="2" width=".5in"/>
										<children>
											<text fixtext="SUMMARY PROPOSAL BUDGET FORM        Total Project"/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="left" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles background-color="gray" border-left-width="thick" border-top-width="thick"/>
										<properties colspan="4" width="1in"/>
										<children>
											<text fixtext="FOR AFOSR USE ONLY">
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
										<properties align="left" rowspan="2" valign="top" width=".5in"/>
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
										<properties align="left" colspan="2" rowspan="2" valign="top" width=".5in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="CAGE CODE:">
														<styles font-family="Verdana" font-size="8pt"/>
													</text>
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
																		<match match="CageNumber"/>
																		<children>
																			<xpath allchildren="1">
																				<styles font-family="Verdana" font-size="8pt"/>
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
										<properties colspan="2" valign="top" width=".5in"/>
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
										<properties colspan="2" valign="top" width=".5in"/>
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
													<text fixtext="A. SENIOR PERSONNEL: PI/PD, Co-PIs, Faculty and Other Senior Associates">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="List each separately with name and title. (A.7. Show number in brackets)">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties colspan="3" width=".5in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="AFOSR-Funded">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Person-months">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="**(Monthly rate)">
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
													<text fixtext="Granted by AFOSR">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="(If Different)">
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
											<text fixtext="RATE">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext="% OF"/>
										</children>
									</tablecol>
									<tablecol>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext="MOS">
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=0] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=0] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=0] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
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
																<match match="BudgetPeriod"/>
																<children>
																	<template>
																		<match match="SalariesAndWages"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="../BudgetPeriodID = 1 and n1:AccountIdentifier = ../NSFSeniorPersonnel[Rownumber=0]/personID"/>
																						</testexpression>
																						<children>
																							<template>
																								<match match="PercentEffort"/>
																								<children>
																									<xpath allchildren="1"/>
																									<text fixtext="%"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=0] /personID]/AppointmentMonths)"/>
												</autocalc>
											</autovalue>
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
																								<format string="#,###,###,##0" xslt="1"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=1] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=1] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=1] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
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
																<match match="BudgetPeriod"/>
																<children>
																	<template>
																		<match match="SalariesAndWages"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="../BudgetPeriodID = 1 and n1:AccountIdentifier = ../NSFSeniorPersonnel[Rownumber=1]/personID"/>
																						</testexpression>
																						<children>
																							<template>
																								<match match="PercentEffort"/>
																								<children>
																									<xpath allchildren="1"/>
																									<text fixtext="%"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=1] /personID]/AppointmentMonths)"/>
												</autocalc>
											</autovalue>
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
																								<format string="#,###,###,##0" xslt="1"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=2] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=2] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=2] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
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
																<match match="BudgetPeriod"/>
																<children>
																	<template>
																		<match match="SalariesAndWages"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="../BudgetPeriodID = 1 and n1:AccountIdentifier = ../NSFSeniorPersonnel[Rownumber=2]/personID"/>
																						</testexpression>
																						<children>
																							<template>
																								<match match="PercentEffort"/>
																								<children>
																									<xpath allchildren="1"/>
																									<text fixtext="%"/>
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
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
										<children>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=2] /personID]/AppointmentMonths)"/>
												</autocalc>
											</autovalue>
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
																								<format string="#,###,###,##0" xslt="1"/>
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
										<properties colspan="2" width=".5in"/>
										<children>
											<text fixtext="6. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="count(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/Rownumber)&gt;3"/>
														</testexpression>
														<children>
															<autovalue>
																<styles font-family="Verdana" font-size="7pt"/>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="count(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel/Rownumber)-3"/>
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
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber&gt;2]/FundsRequested) != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber&gt;2]/FundsRequested)"/>
																</autocalc>
																<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
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
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".5in"/>
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
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/PostDocCount)"/>
												</autocalc>
											</autovalue>
											<text fixtext=" )  POSTDOCTORAL">
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
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
											<text fixtext="2. (">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherProfCount)"/>
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
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="3. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/GradCount)"/>
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
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
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
											<text fixtext="4. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/UnderGradCount)"/>
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
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="5. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/ClericalCount)"/>
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
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="6. ( ">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFOtherPersonnel/OtherCount)"/>
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
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="TotalSalariesAndWages"/>
																<children>
																	<text fixtext="$"/>
																	<xpath allchildren="1">
																		<format string="#,###,###,##0" xslt="1"/>
																	</xpath>
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
											<text fixtext="C. FRINGE BENEFITS (IF CHARGED AS DIRECT COSTS)">
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
																		<format string="#,###,###,##0" xslt="1"/>
																	</xpath>
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
											<text fixtext="TOTAL SALARIES, WAGES, AND FRINGE BENEFITS (A+B+C)">
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
																<match match="TotalSalariesWagesAndFringe"/>
																<children>
																	<text fixtext="$"/>
																	<xpath allchildren="1">
																		<format string="#,###,###,##0" xslt="1"/>
																	</xpath>
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
										<styles border-bottom-style="none" font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<paragraph paragraphtag="p">
												<styles font-family="Verdana" font-size="7pt"/>
												<children>
													<text fixtext="D. EQUIPMENT (LIST ITEM AND DOLLAR AMOUNT FOR EACH ITEM EXCEEDING $5000.)">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="   See Attached.">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
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
											<text fixtext="TOTAL EQUIPMENT">
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
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:EquipmentCosts/Cost)"/>
												</autocalc>
												<format string="#,###,###,###,##0" xslt="1" datatype="decimal"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="E. TRAVEL           1. DOMESTIC (INCL. CANADA, MEXICO AND U.S. POSSESSIONS)     ">
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:TravelCosts[Type=&apos;Domestic Travel&apos;]/Cost)"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left=".9in"/>
										<properties colspan="5" width=".5in"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:TravelCosts[Type=&apos;Foreign Travel&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
										<styles font-family="Verdana" font-size="7pt" padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="  PARITICIPANT SUPPORT  ">
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
															<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:ParticipantPatientCosts[Type=&apos;Participant Stipends&apos;]/Cost )"/>
														</autocalc>
														<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
													</autovalue>
												</children>
											</paragraph>
											<text fixtext="     2. TRAVEL                 $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:ParticipantPatientCosts[Type=&apos;Participant Travel&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
															<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:ParticipantPatientCosts[Type=&apos;Participant Subsistence&apos;]/Cost )"/>
														</autocalc>
														<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
													</autovalue>
												</children>
											</paragraph>
											<text fixtext="     4. OTHER                  $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:ParticipantPatientCosts[Type=&apos;Participant Other&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ParticipantPatientTotal )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
											<text fixtext="6. OTHER                                      (NON-PERSONNEL LAB ALLOCATION:         $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;][ starts-with( Description , &apos;LA&apos; )]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
											</autovalue>
											<text fixtext="  )">
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
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
											<text fixtext="TOTAL OTHER DIRECT COSTS">
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
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
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
											<text fixtext="H. TOTAL DIRECT COSTS (A THROUGH G)">
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
																<match match="BudgetDirectCostsTotal"/>
																<children>
																	<text fixtext="$"/>
																	<xpath allchildren="1">
																		<format string="#,###,###,##0" xslt="1"/>
																	</xpath>
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
											<text fixtext="I. FACILITIES AND ADMINISTRATION EXPENSES (overhead)   (SPECIFY RATE AND BASE)">
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
										<styles font-family="Verdana" font-size="7pt" padding-left="12pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="TOTAL "/>
											<text fixtext="FACILITIES AND ADMINISTRATION EXPENSES">
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
																		<format string="#,###,###,##0" xslt="1"/>
																	</xpath>
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
										<styles padding="0pt"/>
										<properties colspan="7" width=".5in"/>
										<children>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties rowspan="5" valign="top" width="2in"/>
																		<children>
																			<text fixtext="J. TOTAL DIRECT AND INDIRECT COSTS(H + I)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<children>
																			<text fixtext="Rate(%)">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<children>
																			<text fixtext="Base($)">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<children>
																			<text fixtext="Total($)">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties width=".5in"/>
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
																								<match match="BudgetCostsTotal"/>
																								<children>
																									<text fixtext="$"/>
																									<xpath allchildren="1">
																										<format string="#,###,###,##0" xslt="1"/>
																									</xpath>
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
																		<properties width="1in"/>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<children>
																			<text fixtext="Overhead"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties colspan="3" rowspan="4" valign="top"/>
																		<children>
																			<text fixtext=" "/>
																			<text fixtext="See Attached">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties width=".5in"/>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties width="1in"/>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties width="1in"/>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<children>
																			<text fixtext="G &amp; A"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties width=".5in"/>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties width="1in"/>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties width="1in"/>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<children>
																			<text fixtext="Fringe Benefits"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties width=".5in"/>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties width="1in"/>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<properties width="1in"/>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles font-family="Verdana" font-size="7pt"/>
																		<children>
																			<text fixtext="FCCOM"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width=".5in"/>
																	</tablecol>
																	<tablecol>
																		<properties width="1in"/>
																	</tablecol>
																	<tablecol>
																		<properties width="1in"/>
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
										<styles padding-left="6pt"/>
										<properties colspan="5" width=".5in"/>
										<children>
											<text fixtext="K. RESIDUAL FUNDS (IF FOR FURTHER SUPPORT OF CURRENT PROJECT SEE GPG II.D.7.j.)">
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
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="BudgetSummary"/>
														<children>
															<template>
																<match match="BudgetCostsTotal"/>
																<children>
																	<text fixtext="$"/>
																	<xpath allchildren="1">
																		<format string="#,###,###,##0" xslt="1"/>
																	</xpath>
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
										<properties valign="top" width="4in"/>
										<children>
											<text fixtext="M. COST SHARING: PROPOSED LEVEL     $">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
											<autovalue>
												<styles font-family="Verdana" font-size="7pt"/>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/NSFCostSharingAmount )"/>
												</autocalc>
												<format string="#,###,###,##0" xslt="1" datatype="decimal"/>
											</autovalue>
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
										<properties colspan="4" width="1in"/>
										<children>
											<text fixtext="FOR  AFOSR USE ONLY">
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
											<text fixtext="ORG. REP. TYPED NAME AND SIGNATURE*  ">
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
											<text fixtext="Date of Rate Sheet">
												<styles font-family="Verdana" font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles background-color="gray"/>
										<properties width="1in"/>
										<children>
											<text fixtext="Initials-ORG">
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
			<newline withpagebreak="1">
				<styles page-break-after="always"/>
			</newline>
			<newline/>
			<table>
				<properties width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" line-height="9"/>
										<properties align="center"/>
										<children>
											<text fixtext="Equipment Description">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
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
																	<template>
																		<match match="n3:EquipmentCosts"/>
																		<children>
																			<template>
																				<match match="EquipmentDescription"/>
																				<children>
																					<text fixtext=" "/>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</xpath>
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
											</template>
										</children>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
			<newline/>
			<newline/>
			<table>
				<properties border="0" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<properties align="center"/>
										<children>
											<text fixtext="Indirect Costs ">
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
									<choice>
										<children>
											<choiceoption>
												<testexpression>
													<xpath value="BudgetPeriodID =1"/>
												</testexpression>
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
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" colspan="3"/>
																						<children>
																							<text fixtext="Budget Period 1">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Base Amount">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Rate">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Funds Requested">
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
																						<styles font-family="Verdana" font-size="8pt"/>
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
																						<styles font-family="Verdana" font-size="8pt"/>
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
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<template>
																								<match match="FundsRequested"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
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
													<xpath value="BudgetPeriodID =2"/>
												</testexpression>
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
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" colspan="3"/>
																						<children>
																							<text fixtext="Budget Period 2">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Base Amount">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Rate">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Funds Requested">
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
																						<styles font-family="Verdana" font-size="8pt"/>
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
																						<styles font-family="Verdana" font-size="8pt"/>
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
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<template>
																								<match match="FundsRequested"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
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
													<xpath value="BudgetPeriodID =3"/>
												</testexpression>
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
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" colspan="3"/>
																						<children>
																							<text fixtext="Budget Period 3">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Base Amount">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Rate">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Funds Requested">
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
																						<styles font-family="Verdana" font-size="8pt"/>
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
																						<styles font-family="Verdana" font-size="8pt"/>
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
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<template>
																								<match match="FundsRequested"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
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
													<xpath value="BudgetPeriodID =4"/>
												</testexpression>
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
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" colspan="3"/>
																						<children>
																							<text fixtext="Budget Period 4">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Base Amount">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Rate">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Funds Requested">
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
																						<styles font-family="Verdana" font-size="8pt"/>
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
																						<styles font-family="Verdana" font-size="8pt"/>
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
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<template>
																								<match match="FundsRequested"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
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
													<xpath value="BudgetPeriodID =5"/>
												</testexpression>
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
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" colspan="3"/>
																						<children>
																							<text fixtext="Budget Period 5">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Base Amount">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Rate">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<text fixtext="Funds Requested">
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
																						<styles font-family="Verdana" font-size="8pt"/>
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
																						<styles font-family="Verdana" font-size="8pt"/>
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
																						<styles font-family="Verdana" font-size="8pt"/>
																						<children>
																							<template>
																								<match match="FundsRequested"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
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
											</tablecol>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" padding="0"/>
												<properties align="center" width="4in"/>
												<children>
													<text fixtext="ATTACHMENT 5"/>
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
