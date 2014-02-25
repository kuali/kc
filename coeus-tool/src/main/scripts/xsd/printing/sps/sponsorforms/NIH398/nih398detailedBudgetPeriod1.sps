<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
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
		<pagelayouts>
			<pagelayout match="#footerall" isactive="1"/>
		</pagelayouts>
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
							<text fixtext="Program Director/Principal Investigator (Last, first, middle) :">
								<styles font-family="Verdana" font-size="8pt"/>
							</text>
							<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages/Name/LastName[../../ProjectRole =&quot;Principal Investigator&quot;][../../../BudgetPeriodID=1]">
								<styles font-family="Verdana" font-size="8pt"/>
							</autocalc>
							<text fixtext=" , ">
								<styles font-family="Verdana" font-size="8pt"/>
							</text>
							<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages/Name/FirstName[../../ProjectRole =&quot;Principal Investigator&quot;][../../../BudgetPeriodID=1]">
								<styles font-family="Verdana" font-size="8pt"/>
							</autocalc>
							<table>
								<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
								<styles border-bottom-style="none" border-collapse="collapse"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center" width="70%"/>
														<styles font-family="Verdana" font-size="9pt" padding-top="5pt"/>
														<children>
															<text fixtext="DETAILED BUDGET FOR INITIAL BUDGET PERIOD">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
															</text>
															<newline/>
															<text fixtext="DIRECT COSTS ONLY">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="15%"/>
														<styles font-family="Verdana" font-size="8pt" line-height="15pt" padding-left="3pt"/>
														<children>
															<text fixtext="From">
																<styles font-family="Verdana"/>
															</text>
															<newline/>
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
																									<template match="StartDate" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="8pt"/>
																												<format string="MM/DD/YYYY" datatype="date"/>
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
															<newline/>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="15%"/>
														<styles font-family="Verdana" font-size="8pt" line-height="15pt" padding-left="3pt"/>
														<children>
															<text fixtext="Through">
																<styles font-family="Verdana"/>
															</text>
															<newline/>
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
																									<template match="EndDate" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="8pt"/>
																												<format string="MM/DD/YYYY" datatype="date"/>
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
												<styles padding-left="5pt" padding-top="5pt"/>
												<children>
													<tablecell>
														<properties colspan="3"/>
														<styles font-family="Verdana" font-size="7pt" padding-left="3pt" padding-top="3pt"/>
														<children>
															<text fixtext="List PERSONNEL (Applicant organization only) "/>
															<newline/>
															<text fixtext="Use Cal, Acad, or Summer to Enter Months Devoted to Project "/>
															<newline/>
															<text fixtext="Enter Dollar Amounts Requested (omit cents) for Salary Requested and Fringe Benefits"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<condition>
								<children>
									<conditionbranch xpath="count(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages/Name ) &lt; 8">
										<children>
											<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
												<children>
													<template match="BudgetSummary" matchtype="schemagraphitem">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="BudgetPeriod/BudgetPeriodID =1">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="BudgetPeriodID=1">
																								<children>
																									<table>
																										<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																										<styles border-collapse="collapse"/>
																										<children>
																											<tableheader>
																												<children>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties align="center" width="25%"/>
																																<styles border-top-style="none" line-height="9pt"/>
																																<children>
																																	<text fixtext="NAME">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="12%"/>
																																<styles border-top-style="none" line-height="9pt"/>
																																<children>
																																	<text fixtext="ROLE ON">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="PROJECT">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" valign="top" width="7%"/>
																																<styles border-top-style="none" line-height="9pt"/>
																																<children>
																																	<newline/>
																																	<newline/>
																																	<text fixtext="Cal. Mnths">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" valign="top" width="7%"/>
																																<styles border-top-style="none" line-height="9pt"/>
																																<children>
																																	<newline/>
																																	<newline/>
																																	<text fixtext="Acad. Mnths">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" valign="top" width="7%"/>
																																<styles border-top-style="none" font-family="Verdana" line-height="9pt" margin-left="0" margin-right="0" padding-left="0" padding-right="0"/>
																																<children>
																																	<newline/>
																																	<newline/>
																																	<text fixtext="Sum. mnths">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="10%"/>
																																<styles border-top-style="none" line-height="9pt"/>
																																<children>
																																	<text fixtext="INST.BASE">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="SALARY">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="10%"/>
																																<styles border-top-style="none" line-height="9pt"/>
																																<children>
																																	<newline/>
																																	<text fixtext="SALARY">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="REQUESTED">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="10%"/>
																																<styles border-top-style="none" line-height="9pt"/>
																																<children>
																																	<text fixtext="FRINGE">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="BENEFITS">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="12%"/>
																																<styles border-top-style="none" line-height="9pt"/>
																																<children>
																																	<text fixtext="TOTALS">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																												</children>
																											</tableheader>
																											<tablebody>
																												<children>
																													<template match="SalariesAndWages" matchtype="schemagraphitem">
																														<children>
																															<tablerow>
																																<properties height=".39in"/>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
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
																																				</children>
																																			</template>
																																			<text fixtext=", ">
																																				<styles font-size="8pt"/>
																																			</text>
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
																																				</children>
																																			</template>
																																			<text fixtext=" ">
																																				<styles font-size="8pt"/>
																																			</text>
																																			<template match="Name" matchtype="schemagraphitem">
																																				<children>
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
																																	</tablecell>
																																	<tablecell>
																																		<properties width="12%"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<template match="ProjectRole" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<styles font-family="Verdana" font-size="8pt"/>
																																						<format datatype="token"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="7%"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<template match="FundingMonths" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="7%"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<template match="AcademicFundingMonths" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="7%"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<template match="SummerFundingMonths" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="10%"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="BaseSalary != 0">
																																						<children>
																																							<template match="BaseSalary" matchtype="schemagraphitem">
																																								<children>
																																									<text fixtext="$">
																																										<styles font-family="Verdana" font-size="8pt"/>
																																									</text>
																																									<content>
																																										<styles font-family="Verdana" font-size="8pt"/>
																																										<format string="#,###,###,##0" datatype="decimal"/>
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
																																		<properties align="right" width="10%"/>
																																		<styles border-bottom-style="none" font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<template match="RequestedCost" matchtype="schemagraphitem">
																																				<children>
																																					<text fixtext="$">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																					</text>
																																					<content>
																																						<styles font-family="Verdana" font-size="8pt"/>
																																						<format string="#,###,###,###,##0" datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="10%"/>
																																		<styles border-bottom-style="none" font-size="8pt"/>
																																		<children>
																																			<template match="FringeCost" matchtype="schemagraphitem">
																																				<children>
																																					<text fixtext="$">
																																						<styles font-size="8pt"/>
																																					</text>
																																					<content>
																																						<styles font-family="Verdana" font-size="8pt"/>
																																						<format string="#,###,###,###,##0" datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="12%"/>
																																		<styles border-bottom-style="none" font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<text fixtext="$">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																			<autocalc xpath="RequestedCost  +  FringeCost">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																				<format string="#,###,###,##0" datatype="string"/>
																																			</autocalc>
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
																							</conditionbranch>
																						</children>
																					</condition>
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
									</conditionbranch>
								</children>
							</condition>
							<condition>
								<children>
									<conditionbranch xpath="count(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages/Name ) &gt;=8">
										<children>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
												<styles border-bottom-style="none" border-top-style="none"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="center" width="25%"/>
																		<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="NAME"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="12%"/>
																		<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="ROLE ON PROJECT"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="7%"/>
																		<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="Cal."/>
																			<newline/>
																			<text fixtext="Mnths"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="7%"/>
																		<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="Acad."/>
																			<newline/>
																			<text fixtext="Mnths"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="7%"/>
																		<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="Sum."/>
																			<newline/>
																			<text fixtext="Mnths"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="10%"/>
																		<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="INST.BASE"/>
																			<newline/>
																			<text fixtext="SALARY"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="10%"/>
																		<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="SALARY"/>
																			<newline/>
																			<text fixtext="REQUESTED"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="10%"/>
																		<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="FRINGE"/>
																			<newline/>
																			<text fixtext="BENEFITS"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="center" width="12%"/>
																		<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="TOTALS"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<properties height=".39in"/>
																<children>
																	<tablecell>
																		<properties width="2.1in"/>
																		<styles font-family="Verdana" font-size="9pt"/>
																		<children>
																			<text fixtext="See attached."/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".8in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<properties height=".39in"/>
																<children>
																	<tablecell>
																		<properties width="2.1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext=" "/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".8in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<properties height=".39in"/>
																<children>
																	<tablecell>
																		<properties width="2.1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext=" "/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".8in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<properties height=".39in"/>
																<children>
																	<tablecell>
																		<properties width="2.1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext=" "/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".8in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<properties height=".39in"/>
																<children>
																	<tablecell>
																		<properties width="2.1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext=" "/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".8in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<properties height=".39in"/>
																<children>
																	<tablecell>
																		<properties width="2.1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext=" "/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".8in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<properties height=".39in"/>
																<children>
																	<tablecell>
																		<properties width="2.1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext=" "/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".4in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".8in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																	<tablecell>
																		<properties width=".95in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<table>
								<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
								<styles border-collapse="collapse" border-top-style="none" line-height="20pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" colspan="3" width="68%"/>
														<styles border-right-style="none" border-top-style="none" font-size="8pt"/>
														<children>
															<text fixtext="SUBTOTALS-----------------------------------------&gt;     ">
																<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="10%"/>
														<styles border-bottom-width="thick" border-left-width="thick" border-top-width="thick" font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="$">
																<styles font-size="8pt" font-weight="bold"/>
															</text>
															<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages/RequestedCost )">
																<styles font-size="8pt" font-weight="bold"/>
																<format string="#,###,###,##0" datatype="decimal"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="10%"/>
														<styles border-bottom-width="thick" border-top-width="thick" font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="$">
																<styles font-size="8pt" font-weight="bold"/>
															</text>
															<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages/FringeCost )">
																<styles font-size="8pt" font-weight="bold"/>
																<format string="#,###,###,##0" datatype="decimal"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="12%"/>
														<styles border-bottom-width="thick" border-top-width="thick" font-family="Verdana" font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="BudgetPeriodID =1">
																								<children>
																									<template match="SalariesWagesTotal" matchtype="schemagraphitem">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt" font-weight="bold"/>
																											</text>
																											<content>
																												<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
																												<format string="###,###,##0" datatype="decimal"/>
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
												<children>
													<tablecell>
														<properties align="left" colspan="5" width="2.1in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="CONSULTANT COSTS">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width=".95in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
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
																									<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Type =&apos;Consultant Costs&apos;">
																														<children>
																															<template match="Cost" matchtype="schemagraphitem">
																																<children>
																																	<text fixtext="$">
																																		<styles font-size="8pt"/>
																																	</text>
																																	<content>
																																		<styles font-size="8pt"/>
																																		<format string="#,###,###,##0" datatype="decimal"/>
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
												<children>
													<tablecell>
														<properties align="left" colspan="5" width="2.1in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="EQUIPMENT (">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="Itemize">
																<styles font-family="Verdana" font-size="8pt" font-style="italic"/>
															</text>
															<text fixtext=")  ">
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
																							<conditionbranch xpath="BudgetPeriodID =1">
																								<children>
																									<template match="rar:EquipmentCosts" matchtype="schemagraphitem">
																										<children>
																											<text fixtext=" ">
																												<styles font-size="8pt"/>
																											</text>
																											<template match="EquipmentDescription" matchtype="schemagraphitem">
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
														<properties align="right" width=".95in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
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
																									<template match="EquipmentTotal" matchtype="schemagraphitem">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
												<children>
													<tablecell>
														<properties align="left" colspan="5" width="2.1in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="SUPPLIES (">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="Itemize by category">
																<styles font-family="Verdana" font-size="8pt" font-style="italic"/>
															</text>
															<text fixtext=")  ">
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
																									<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Type =&apos;Materials and Supplies&apos;">
																														<children>
																															<template match="Description" matchtype="schemagraphitem">
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
														<properties align="right" width=".95in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
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
																									<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Type =&apos;Materials and Supplies&apos;">
																														<children>
																															<template match="Cost" matchtype="schemagraphitem">
																																<children>
																																	<text fixtext="$">
																																		<styles font-size="8pt"/>
																																	</text>
																																	<content>
																																		<styles font-size="8pt"/>
																																		<format string="#,###,###,##0" datatype="decimal"/>
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
												<children>
													<tablecell>
														<properties align="left" colspan="5" width="2.1in"/>
														<styles font-size="8pt"/>
														<children>
															<text fixtext="TRAVEL">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width=".95in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
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
																									<template match="TravelTotal" matchtype="schemagraphitem">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
												<children>
													<tablecell>
														<properties align="left" colspan="5" width="2.1in"/>
														<styles font-size="8pt"/>
														<children>
															<text fixtext="INPATIENT CARE COSTS">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width=".95in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
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
																									<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Type=&apos;Patient Care&apos;">
																														<children>
																															<template match="Cost" matchtype="schemagraphitem">
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<styles font-size="8pt"/>
																																		<format string="#,###,###,##0" datatype="decimal"/>
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
												<children>
													<tablecell>
														<properties align="left" colspan="5" width="2.1in"/>
														<styles font-size="8pt"/>
														<children>
															<text fixtext="OUTPATIENT CARE COSTS">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width=".95in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
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
																									<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Type=&apos;Outpatient Services&apos;">
																														<children>
																															<template match="Cost" matchtype="schemagraphitem">
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<styles font-size="8pt"/>
																																		<format string="#,###,###,##0" datatype="decimal"/>
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
												<children>
													<tablecell>
														<properties align="left" colspan="5" width="2.1in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="ALTERATIONS AND RENOVATIONS(">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="Itemize by category">
																<styles font-family="Verdana" font-size="8pt" font-style="italic"/>
															</text>
															<text fixtext=")">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width=".95in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
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
																									<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="Type=&apos;Alterations and Renovations&apos;">
																														<children>
																															<template match="Cost" matchtype="schemagraphitem">
																																<children>
																																	<text fixtext="$">
																																		<styles font-size="8pt"/>
																																	</text>
																																	<content>
																																		<styles font-size="8pt"/>
																																		<format string="#,###,###,##0" datatype="decimal"/>
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
												<children>
													<tablecell>
														<properties align="left" colspan="5" width="2.1in"/>
														<styles font-size="8pt"/>
														<children>
															<text fixtext="OTHER EXPENSES (">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<text fixtext="Itemize by category">
																<styles font-family="Verdana" font-size="8pt" font-style="italic"/>
															</text>
															<text fixtext=")">
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
																							<conditionbranch xpath="BudgetPeriodID =1">
																								<children>
																									<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																										<children>
																											<text fixtext="   ">
																												<styles font-size="8pt"/>
																											</text>
																											<condition>
																												<children>
																													<conditionbranch xpath="Type =&apos;Other Direct Costs&apos;">
																														<children>
																															<template match="Description" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<format datatype="token"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<text fixtext="       ">
																												<styles font-size="8pt"/>
																											</text>
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
														<properties align="right" width=".95in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="$"/>
															<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost )">
																<format string="#,###,###,##0" datatype="decimal"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="3" width="2.1in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="CONSORITUM/CONTRACTUAL COSTS"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" colspan="2" width="2.1in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="DIRECT COSTS  "/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width=".95in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
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
																									<template match="ConsortiumCosts" matchtype="schemagraphitem">
																										<children>
																											<template match="DirectCosts" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
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
												<children>
													<tablecell>
														<properties align="left" colspan="4" width="2.1in"/>
														<styles font-size="8pt"/>
														<children>
															<text fixtext="SUBTOTAL DIRECT COSTS FOR INITIAL BUDGET PERIOD">
																<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
															</text>
															<text fixtext="(Item 7a, Face Page)">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" colspan="2" width=".95in"/>
														<styles border-width="thick" font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="$">
																<styles font-weight="bold"/>
															</text>
															<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/PeriodDirectCostsTotal  -  nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/ConsortiumCosts/IndirectCosts">
																<styles font-weight="bold"/>
																<format string="#,###,###,##0" datatype="decimal"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="2" width="2.1in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="CONSORTIUM/CONTRACTUAL COSTS"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" colspan="3"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="FACILITIES AND ADMINISTRATION COSTS  "/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width=".95in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
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
																									<template match="ConsortiumCosts" matchtype="schemagraphitem">
																										<children>
																											<template match="IndirectCosts" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
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
												<children>
													<tablecell>
														<properties align="left" colspan="4" width="2.1in"/>
														<styles font-size="8pt"/>
														<children>
															<text fixtext="TOTAL DIRECT COSTS FOR INITIAL BUDGET PERIOD ">
																<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" colspan="2" width=".95in"/>
														<styles border-width="thick" font-family="Verdana" font-size="8pt"/>
														<children>
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
																												<styles font-size="8pt" font-weight="bold"/>
																											</text>
																											<content>
																												<styles font-size="8pt" font-weight="bold"/>
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
										</children>
									</tablebody>
								</children>
							</table>
							<condition>
								<children>
									<conditionbranch xpath="count(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages/Name ) &gt;=8">
										<children>
											<newline break="page"/>
											<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
												<children>
													<template match="BudgetSummary" matchtype="schemagraphitem">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="BudgetPeriod/BudgetPeriodID =1">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="BudgetPeriodID=1">
																								<children>
																									<table>
																										<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																										<styles border-collapse="collapse"/>
																										<children>
																											<tableheader>
																												<children>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties align="center" width="25%"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="NAME">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="12%"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="ROLE ON">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="PROJECT">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="7%"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="Cal.">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="Mnths">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="7%"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="Acad.">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="Mnths">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="7%"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="Sum.">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="Mnths">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="10%"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="INST.BASE">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="SALARY">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="10%"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="SALARY">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="REQUESTED">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="10%"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="FRINGE">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<text fixtext="BENEFITS">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width="12%"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="TOTALS">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																												</children>
																											</tableheader>
																											<tablebody>
																												<children>
																													<template match="SalariesAndWages" matchtype="schemagraphitem">
																														<children>
																															<tablerow>
																																<properties height=".39in"/>
																																<children>
																																	<tablecell>
																																		<properties width="2.1in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
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
																																				</children>
																																			</template>
																																			<text fixtext=", ">
																																				<styles font-size="8pt"/>
																																			</text>
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
																																				</children>
																																			</template>
																																			<text fixtext=" ">
																																				<styles font-size="8pt"/>
																																			</text>
																																			<template match="Name" matchtype="schemagraphitem">
																																				<children>
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
																																	</tablecell>
																																	<tablecell>
																																		<properties width="1in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<template match="ProjectRole" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<styles font-family="Verdana" font-size="8pt"/>
																																						<format datatype="token"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width=".42in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<template match="FundingMonths" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width=".41in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<template match="AcademicFundingMonths" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width=".41in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<template match="SummerFundingMonths" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width=".8in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="BaseSalary != 0">
																																						<children>
																																							<template match="BaseSalary" matchtype="schemagraphitem">
																																								<children>
																																									<text fixtext="$">
																																										<styles font-family="Verdana" font-size="8pt"/>
																																									</text>
																																									<content>
																																										<styles font-family="Verdana" font-size="8pt"/>
																																										<format string="#,###,###,##0" datatype="decimal"/>
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
																																		<properties align="right" width=".95in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<template match="RequestedCost" matchtype="schemagraphitem">
																																				<children>
																																					<text fixtext="$">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																					</text>
																																					<content>
																																						<styles font-family="Verdana" font-size="8pt"/>
																																						<format string="#,###,###,##0" datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width=".95in"/>
																																		<styles font-size="8pt"/>
																																		<children>
																																			<template match="FringeCost" matchtype="schemagraphitem">
																																				<children>
																																					<text fixtext="$">
																																						<styles font-size="8pt"/>
																																					</text>
																																					<content>
																																						<styles font-family="Verdana" font-size="8pt"/>
																																						<format string="#,###,###,##0" datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width=".95in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<text fixtext="$">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																			<autocalc xpath="RequestedCost  +  FringeCost">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																				<format string="#,###,###,##0" datatype="decimal"/>
																																			</autocalc>
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
																							</conditionbranch>
																						</children>
																					</condition>
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
									</conditionbranch>
								</children>
							</condition>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.5in" papermarginleft="0.45in" papermarginright="0.45in" papermargintop="0.5in" paperwidth="8.5in"/>
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
														<properties align="left" width="276"/>
														<styles padding="0"/>
														<children>
															<text fixtext="PHS 398 (Rev. 6/09)">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left"/>
														<styles padding="0"/>
														<children>
															<text fixtext="Page: _">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
															<field>
																<styles font-family="Verdana" font-size="8pt"/>
															</field>
															<text fixtext="_">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles padding="0"/>
														<children>
															<text fixtext="Form Page 4">
																<styles font-family="Verdana" font-size="8pt"/>
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
