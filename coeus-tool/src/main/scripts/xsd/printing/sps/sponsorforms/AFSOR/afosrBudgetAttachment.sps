<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="c:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\COEUS4\Reports\nsfJune8_03.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" font-size="7pt" line-height="10pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
										<properties align="center"/>
										<children>
											<text fixtext="AFOSR Proposal Budget Senior Personnel"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
			<newline/>
			<table>
				<properties border="1" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="center" width="3.5in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="center" width="1in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="center" width=".75in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="center" width=".75in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="center" width="1in"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties rowspan="2" width="3.5in"/>
										<children>
											<text fixtext="Senior Personnel: PI/PD, Co-PIs, Faculty and Other Senior Associates">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" colspan="3" width=".75in"/>
										<children>
											<text fixtext="AFOSR-Funded Person-Months"/>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" rowspan="2" width="1in"/>
										<children>
											<text fixtext="FUNDS REQUESTED"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" width="1in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="MONTHLY "/>
												</children>
											</paragraph>
											<text fixtext="RATE"/>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" width=".75in"/>
										<children>
											<text fixtext="% OF"/>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" width=".75in"/>
										<children>
											<text fixtext="MOS"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="8pt"/>
										<properties width="3.5in"/>
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
										<properties align="center" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=0]/personID) and n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=0] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=0] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=0] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=0]/personID )"/>
														</testexpression>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=0]/personID )"/>
														</testexpression>
														<children>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=0] /personID]/AppointmentMonths)"/>
																</autocalc>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=0]/personID"/>
														</testexpression>
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
																												<format string="#,###,##0" xslt="1"/>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="8pt"/>
										<properties width="3.5in"/>
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
										<properties align="center" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=1]/personID) and n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=1] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=1] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=1] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=1]/personID )"/>
														</testexpression>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=1]/personID )"/>
														</testexpression>
														<children>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=1] /personID]/AppointmentMonths)"/>
																</autocalc>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=1]/personID"/>
														</testexpression>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="8pt"/>
										<properties width="3.5in"/>
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
										<properties align="center" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=2]/personID) and n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=2] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=2] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=2] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=2]/personID )"/>
														</testexpression>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=2]/personID )"/>
														</testexpression>
														<children>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=2] /personID]/AppointmentMonths)"/>
																</autocalc>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=2]/personID"/>
														</testexpression>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="8pt"/>
										<properties width="3.5in"/>
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
										<properties align="center" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=3]/personID) and n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=3] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=3] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=3] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=3]/personID )"/>
														</testexpression>
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
																											<xpath value="../BudgetPeriodID = 1 and n1:AccountIdentifier = ../NSFSeniorPersonnel[Rownumber=3]/personID"/>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=3]/personID )"/>
														</testexpression>
														<children>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=3] /personID]/AppointmentMonths)"/>
																</autocalc>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=3]/personID"/>
														</testexpression>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="8pt"/>
										<properties width="3.5in"/>
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
										<properties align="center" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=4]/personID) and n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=4] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=4] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=4] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=4]/personID )"/>
														</testexpression>
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
																											<xpath value="../BudgetPeriodID = 1 and n1:AccountIdentifier = ../NSFSeniorPersonnel[Rownumber=4]/personID"/>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=4]/personID )"/>
														</testexpression>
														<children>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=4] /personID]/AppointmentMonths)"/>
																</autocalc>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=4]/personID"/>
														</testexpression>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="8pt"/>
										<properties width="3.5in"/>
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
																					<xpath value="Rownumber=5"/>
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
										<properties align="center" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=5]/personID) and n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=5] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=5] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=5] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=5]/personID )"/>
														</testexpression>
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
																											<xpath value="../BudgetPeriodID = 1 and n1:AccountIdentifier = ../NSFSeniorPersonnel[Rownumber=5]/personID"/>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=5]/personID )"/>
														</testexpression>
														<children>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=5] /personID]/AppointmentMonths)"/>
																</autocalc>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=5]/personID"/>
														</testexpression>
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
																									<xpath value="Rownumber=5"/>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="8pt"/>
										<properties width="3.5in"/>
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
																					<xpath value="Rownumber=6"/>
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
										<properties align="center" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=6]/personID) and n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=6] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=6] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=6] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=6]/personID )"/>
														</testexpression>
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
																											<xpath value="../BudgetPeriodID = 1 and n1:AccountIdentifier = ../NSFSeniorPersonnel[Rownumber=6]/personID"/>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=6]/personID )"/>
														</testexpression>
														<children>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=6] /personID]/AppointmentMonths)"/>
																</autocalc>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=6]/personID"/>
														</testexpression>
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
																									<xpath value="Rownumber=6"/>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="8pt"/>
										<properties width="3.5in"/>
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
																					<xpath value="Rownumber=7"/>
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
										<properties align="center" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=7]/personID) and n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=7] /personID]/BaseSalary != 0"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages[ n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=7] /personID]/BaseSalary div  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID =1]/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=7] /personID]/AppointmentMonths"/>
																</autocalc>
																<format string="#,###,##0" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=7]/personID )"/>
														</testexpression>
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
																											<xpath value="../BudgetPeriodID = 1 and n1:AccountIdentifier = ../NSFSeniorPersonnel[Rownumber=7]/personID"/>
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
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="center" width=".75in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="boolean(n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=7]/personID )"/>
														</testexpression>
														<children>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages[n1:AccountIdentifier= ../NSFSeniorPersonnel[Rownumber=7] /personID]/AppointmentMonths)"/>
																</autocalc>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="7pt"/>
										<properties align="right" width="1in"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/NSFSeniorPersonnel[Rownumber=7]/personID"/>
														</testexpression>
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
																									<xpath value="Rownumber=7"/>
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
													</choiceoption>
												</children>
											</choice>
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
