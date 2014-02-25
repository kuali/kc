<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="c:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\coeus40_vss\Reports\Form 1030 Attachment$03152006-060627$.xml" templatexmlfile="">
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
											<text fixtext="NSF Proposal Budget"/>
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
										<properties align="center" width="4.5in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="center" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="center" width=".5in"/>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none"/>
										<properties align="center" width=".5in"/>
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
										<properties rowspan="2" width="4.5in"/>
										<children>
											<text fixtext="Senior Personnel: PI/PD, Co-PIs, Faculty and Other Senior Associates">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" colspan="3" width=".5in"/>
										<children>
											<text fixtext="NSF-Funded Person-Months"/>
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
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext="CAL"/>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext="ACAD"/>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
										<properties align="center" width=".5in"/>
										<children>
											<text fixtext="SUMR"/>
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
													<xpath value="BudgetPeriodID=1"/>
												</testexpression>
												<children>
													<template>
														<match match="NSFSeniorPersonnel"/>
														<children>
															<table dynamic="1">
																<properties border="1" cellpadding="5" cellspacing="5"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<children>
																					<tablecol>
																						<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																						<properties colspan="5" width="4.5in"/>
																						<children>
																							<text fixtext="Budget Period 1"/>
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
																						<properties width="4.5in"/>
																						<children>
																							<template>
																								<match match="FullName"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<text fixtext=",   "/>
																							<template>
																								<match match="Title"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<text fixtext=" "/>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="CalendarMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="AcademicMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="SummerMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="right" width="1in"/>
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
									<choice>
										<children>
											<choiceoption>
												<testexpression>
													<xpath value="BudgetPeriodID=2"/>
												</testexpression>
												<children>
													<template>
														<match match="NSFSeniorPersonnel"/>
														<children>
															<table dynamic="1">
																<properties border="1" cellpadding="5" cellspacing="5"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<children>
																					<tablecol>
																						<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																						<properties colspan="5" width="4.5in"/>
																						<children>
																							<text fixtext="Budget Period 2"/>
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
																						<properties width="4.5in"/>
																						<children>
																							<template>
																								<match match="FullName"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<text fixtext=",   "/>
																							<template>
																								<match match="Title"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<text fixtext=" "/>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="CalendarMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="AcademicMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="SummerMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="right" width="1in"/>
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
									<choice>
										<children>
											<choiceoption>
												<testexpression>
													<xpath value="BudgetPeriodID=3"/>
												</testexpression>
												<children>
													<template>
														<match match="NSFSeniorPersonnel"/>
														<children>
															<table dynamic="1">
																<properties border="1" cellpadding="5" cellspacing="5"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<children>
																					<tablecol>
																						<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																						<properties colspan="5" width="4.5in"/>
																						<children>
																							<text fixtext="Budget Period 3"/>
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
																						<properties width="4.5in"/>
																						<children>
																							<template>
																								<match match="FullName"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<text fixtext=",   "/>
																							<template>
																								<match match="Title"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<text fixtext=" "/>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="CalendarMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="AcademicMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="SummerMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="right" width="1in"/>
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
									<choice>
										<children>
											<choiceoption>
												<testexpression>
													<xpath value="BudgetPeriodID=4"/>
												</testexpression>
												<children>
													<template>
														<match match="NSFSeniorPersonnel"/>
														<children>
															<table dynamic="1">
																<properties border="1" cellpadding="5" cellspacing="5"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<children>
																					<tablecol>
																						<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																						<properties colspan="5" width="4.5in"/>
																						<children>
																							<text fixtext="Budget Period 4"/>
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
																						<properties width="4.5in"/>
																						<children>
																							<template>
																								<match match="FullName"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<text fixtext=",   "/>
																							<template>
																								<match match="Title"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<text fixtext=" "/>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="CalendarMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="AcademicMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="SummerMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="right" width="1in"/>
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
									<choice>
										<children>
											<choiceoption>
												<testexpression>
													<xpath value="BudgetPeriodID=5"/>
												</testexpression>
												<children>
													<template>
														<match match="NSFSeniorPersonnel"/>
														<children>
															<table dynamic="1">
																<properties border="1" cellpadding="5" cellspacing="5"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<children>
																					<tablecol>
																						<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																						<properties colspan="5" width="4.5in"/>
																						<children>
																							<text fixtext="Budget Period 5"/>
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
																						<properties width="4.5in"/>
																						<children>
																							<template>
																								<match match="FullName"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<text fixtext=",   "/>
																							<template>
																								<match match="Title"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<text fixtext=" "/>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="CalendarMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="AcademicMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="center" width=".5in"/>
																						<children>
																							<template>
																								<match match="SummerMonthsFunded"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</tablecol>
																					<tablecol>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<properties align="right" width="1in"/>
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
