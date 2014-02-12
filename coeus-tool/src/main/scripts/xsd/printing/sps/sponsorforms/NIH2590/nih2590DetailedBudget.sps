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
			<xsdschemasource name="$XML" main="1" schemafile="phs398schema.xsd" workingxmlfile="C:\coeusBuild42\build\web\Reports\budget.xml">
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
							<paragraph paragraphtag="center">
								<styles border-left-style="none" border-right-style="none" border-top-style="none" line-height="12pt"/>
								<children>
									<text fixtext="Principal Investigator/Program Director (Last, first, middle): ">
										<styles font-family="Verdana" font-size="8pt"/>
									</text>
									<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages/Name/LastName [../../ProjectRole=&quot;Principal Investigator&quot;][../../../BudgetPeriodID=1]">
										<styles font-family="Verdana" font-size="8pt"/>
									</autocalc>
									<text fixtext=" , ">
										<styles font-family="Verdana" font-size="8pt"/>
									</text>
									<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/SalariesAndWages/Name/FirstName [../../ProjectRole=&quot;Principal Investigator&quot;][../../../BudgetPeriodID=1]">
										<styles font-family="Verdana" font-size="8pt"/>
									</autocalc>
								</children>
							</paragraph>
							<newline/>
							<table>
								<properties border="1" cellpadding="5" cellspacing="0" width="100%"/>
								<styles border-collapse="collapse" line-height="12pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties width="4.5in"/>
														<styles font-family="Verdana" font-size="9pt"/>
														<children>
															<paragraph paragraphtag="center">
																<styles border-left-color="white" border-left-style="none" font-family="Verdana" font-size="9pt"/>
																<children>
																	<text fixtext="DETAILED BUDGET FOR NEXT BUDGET PERIOD ">
																		<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
																	</text>
																	<text fixtext="DIRECT COSTS ONLY">
																		<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="1in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="From">
																<styles font-family="Verdana"/>
															</text>
															<newline/>
															<paragraph paragraphtag="p">
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
																											<template match="StartDate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Verdana" font-size="9pt"/>
																														<format string="MM/DD/YYYY"/>
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
															</paragraph>
															<newline/>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="1in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="Through">
																<styles font-family="Verdana"/>
															</text>
															<paragraph paragraphtag="p">
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
																											<template match="EndDate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Verdana" font-size="9pt"/>
																														<format string="MM/DD/YYYY"/>
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
															</paragraph>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="1.5in"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="GRANT NUMBER"/>
																</children>
															</paragraph>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihPriorGrantNumber" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<styles font-family="Verdana" font-size="9pt"/>
																			</content>
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
							<paragraph paragraphtag="center">
								<styles line-height="12pt"/>
								<children>
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
																												<properties border="1" cellpadding="5" cellspacing="0"/>
																												<styles border-collapse="collapse"/>
																												<children>
																													<tableheader>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties colspan="2" width="17%"/>
																																		<styles border-top-style="none" line-height="9pt"/>
																																		<children>
																																			<text fixtext="PERSONNEL(Applicant organization only)">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" colspan="3" width="6%"/>
																																		<styles border-top-style="none" line-height="9pt"/>
																																		<children>
																																			<text fixtext="Months Devoted to Project">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																			<newline/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" colspan="3" width="18%"/>
																																		<styles border-top-style="none" line-height="9pt"/>
																																		<children>
																																			<text fixtext="DOLLAR AMOUNT REQUESTED (omit cents)">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="20%"/>
																																		<styles line-height="9pt"/>
																																		<children>
																																			<text fixtext="NAME">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="17%"/>
																																		<styles line-height="9pt"/>
																																		<children>
																																			<text fixtext="ROLE ON">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																			<newline/>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="PROJECT">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																					</text>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="6%"/>
																																		<styles border-top-style="none" line-height="9pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles line-height="9pt"/>
																																				<children>
																																					<text fixtext="Cal.">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																					</text>
																																					<newline/>
																																					<text fixtext="mnths">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																					</text>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="6%"/>
																																		<styles border-top-style="none" line-height="9pt"/>
																																		<children>
																																			<text fixtext="Acad. ">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																			<newline/>
																																			<text fixtext="mnths">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="6%"/>
																																		<styles border-top-style="none" line-height="9pt"/>
																																		<children>
																																			<text fixtext="Sum.">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																			<newline/>
																																			<text fixtext="mnths">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="13.5%"/>
																																		<styles line-height="9pt"/>
																																		<children>
																																			<text fixtext="SALARY">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																			<paragraph paragraphtag="p">
																																				<styles line-height="9pt"/>
																																				<children>
																																					<text fixtext="REQUESTED">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																					</text>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="13.5%"/>
																																		<styles line-height="9pt"/>
																																		<children>
																																			<text fixtext="FRINGE">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																			<paragraph paragraphtag="p">
																																				<styles line-height="9pt"/>
																																				<children>
																																					<text fixtext="BENEFITS">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																					</text>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="18%"/>
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
																																				<properties width="20%"/>
																																				<styles font-family="Verdana" font-size="8pt"/>
																																				<children>
																																					<template match="Name" matchtype="schemagraphitem">
																																						<children>
																																							<template match="LastName" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-family="Verdana" font-size="9pt"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath="string-length(  Name/FirstName  ) &gt;1">
																																								<children>
																																									<text fixtext=", ">
																																										<styles font-size="8pt"/>
																																									</text>
																																								</children>
																																							</conditionbranch>
																																						</children>
																																					</condition>
																																					<template match="Name" matchtype="schemagraphitem">
																																						<children>
																																							<template match="FirstName" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-family="Verdana" font-size="9pt"/>
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
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="17%"/>
																																				<styles font-family="Verdana" font-size="8pt"/>
																																				<children>
																																					<template match="ProjectRole" matchtype="schemagraphitem">
																																						<children>
																																							<content>
																																								<styles font-family="Verdana" font-size="8pt"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties align="center" width="6%"/>
																																				<styles font-family="Verdana" font-size="8pt"/>
																																				<children>
																																					<template match="FundingMonths" matchtype="schemagraphitem">
																																						<children>
																																							<content/>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties align="center" width="6%"/>
																																				<styles font-family="Verdana" font-size="8pt"/>
																																				<children>
																																					<template match="AcademicFundingMonths" matchtype="schemagraphitem">
																																						<children>
																																							<content/>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties align="center" width="6%"/>
																																				<styles font-family="Verdana" font-size="8pt"/>
																																				<children>
																																					<template match="SummerFundingMonths" matchtype="schemagraphitem">
																																						<children>
																																							<content/>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties align="right" width="13.5%"/>
																																				<styles border-bottom-style="none" font-family="Verdana" font-size="8pt"/>
																																				<children>
																																					<template match="RequestedCost" matchtype="schemagraphitem">
																																						<children>
																																							<text fixtext="$">
																																								<styles font-family="Verdana" font-size="8pt"/>
																																							</text>
																																							<content>
																																								<styles font-family="Verdana" font-size="8pt"/>
																																								<format string="#,###,###,###,##0"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties align="right" width="13.5%"/>
																																				<styles border-bottom-style="none" font-size="8pt"/>
																																				<children>
																																					<template match="FringeCost" matchtype="schemagraphitem">
																																						<children>
																																							<text fixtext="$">
																																								<styles font-size="8pt"/>
																																							</text>
																																							<content>
																																								<styles font-family="Verdana" font-size="8pt"/>
																																								<format string="#,###,###,###,##0"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties align="right" width="18%"/>
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
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties colspan="2" width="17%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="PERSONNEL(Applicant organization only)"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties align="center" colspan="3" valign="center" width="6%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="Months Devoted to Project"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties align="center" colspan="3" width="18%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="DOLLAR AMOUNT REQUESTED (omit cents)"/>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="20%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="NAME"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="17%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="ROLE ON "/>
																					<text fixtext="PROJECT"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties align="center" width="6%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="Cal. Mnths"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties align="center" width="6%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="Acad. Mnths"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties align="center" width="6%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="Sum. Mnths"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties align="center" width="13.5%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="SALARY REQUESTED"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties align="center" width="13.5%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="FRINGE BENEFITS"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties align="center" width="18%"/>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="TOTALS"/>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="20%"/>
																				<styles font-family="Verdana" font-size="9pt"/>
																				<children>
																					<text fixtext="See attached."/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="17%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="18%"/>
																			</tablecell>
																		</children>
																	</tablerow>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="20%"/>
																				<children>
																					<newline/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="17%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="18%"/>
																			</tablecell>
																		</children>
																	</tablerow>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="20%"/>
																				<children>
																					<newline/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="17%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="18%"/>
																			</tablecell>
																		</children>
																	</tablerow>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="20%"/>
																				<children>
																					<newline/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="17%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="18%"/>
																			</tablecell>
																		</children>
																	</tablerow>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="20%"/>
																				<children>
																					<newline/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="17%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="18%"/>
																			</tablecell>
																		</children>
																	</tablerow>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="20%"/>
																				<children>
																					<newline/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="17%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="18%"/>
																			</tablecell>
																		</children>
																	</tablerow>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="20%"/>
																				<children>
																					<newline/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="17%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="6%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="13.5%"/>
																			</tablecell>
																			<tablecell>
																				<properties width="18%"/>
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
										<properties border="1" cellpadding="1" cellspacing="0" width="100%"/>
										<styles border-collapse="collapse"/>
										<children>
											<tablebody>
												<children>
													<tablerow>
														<children>
															<tablecell>
																<properties align="left" width="1.5in"/>
																<styles border-right-style="none" border-top-style="none" font-size="8pt"/>
															</tablecell>
															<tablecell>
																<properties align="left" width="3.05in"/>
																<styles border-left-style="none" border-top-style="none" font-size="8pt" line-height="10pt"/>
																<children>
																	<text fixtext="SUBTOTALS-----------------------------------------&gt;">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" width=".95in"/>
																<styles border-bottom-width="thick" border-left-width="thick" border-top-width="thick" font-family="Verdana" font-size="8pt"/>
																<children>
																	<text fixtext="$">
																		<styles font-size="8pt"/>
																	</text>
																	<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages/RequestedCost )">
																		<styles font-size="8pt"/>
																		<format string="#,###,###,##0" datatype="decimal"/>
																	</autocalc>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" width=".98in"/>
																<styles border-bottom-width="thick" border-top-width="thick" font-family="Verdana" font-size="8pt"/>
																<children>
																	<text fixtext="$">
																		<styles font-size="8pt"/>
																	</text>
																	<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages/FringeCost )">
																		<styles font-size="8pt"/>
																		<format string="#,###,###,##0" datatype="decimal"/>
																	</autocalc>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" width="1.5in"/>
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
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-family="Verdana" font-size="8pt"/>
																														<format string="###,###,##0"/>
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
																<properties align="left" colspan="4" width="1.5in"/>
																<styles font-family="Verdana" font-size="8pt"/>
																<children>
																	<text fixtext="CONSULTANT COSTS">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" width="1.5in"/>
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
																																				<format string="#,###,###,##0"/>
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
																<properties align="left" colspan="4" width="1.5in"/>
																<styles font-family="Verdana" font-size="8pt"/>
																<children>
																	<paragraph paragraphtag="p">
																		<children>
																			<text fixtext="EQUIPMENT (">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<text fixtext="Itemize">
																				<styles font-family="Verdana" font-size="8pt" font-style="italic"/>
																			</text>
																			<text fixtext=") ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</paragraph>
																	<text fixtext=" ">
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
																<properties align="right" width="1.5in"/>
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
																														<format string="#,###,###,##0"/>
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
																<properties align="left" colspan="4" width="1.5in"/>
																<styles font-family="Verdana" font-size="8pt"/>
																<children>
																	<paragraph paragraphtag="p">
																		<children>
																			<text fixtext="SUPPLIES (">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<text fixtext="Itemize by category">
																				<styles font-family="Verdana" font-size="8pt" font-style="italic"/>
																			</text>
																			<text fixtext=")">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</paragraph>
																	<text fixtext="  ">
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
																																			<content/>
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
																<properties align="right" width="1.5in"/>
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
																																				<format string="#,###,###,##0"/>
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
																<properties align="left" colspan="4" width="1.5in"/>
																<styles font-size="8pt"/>
																<children>
																	<text fixtext="TRAVEL">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" width="1.5in"/>
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
																														<format string="#,###,###,##0"/>
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
																<properties align="left" rowspan="2" width="1.5in"/>
																<styles font-size="8pt"/>
																<children>
																	<text fixtext="PATIENT CARE COSTS">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties align="left" colspan="3" width="3.05in"/>
																<styles font-size="8pt"/>
																<children>
																	<text fixtext="INPATIENT">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" width="1.5in"/>
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
																																				<format string="#,###,###,##0"/>
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
																<properties align="left" colspan="3" width="3.05in"/>
																<styles font-size="8pt"/>
																<children>
																	<text fixtext="OUTPATIENT">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" width="1.5in"/>
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
																																				<format string="#,###,###,##0"/>
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
																<properties align="left" colspan="4" width="1.5in"/>
																<styles font-family="Verdana" font-size="8pt"/>
																<children>
																	<text fixtext="ALTERATIONS AND RENOVATIONS">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" width="1.5in"/>
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
																																				<format string="#,###,###,##0"/>
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
																<properties align="left" colspan="4" width="1.5in"/>
																<styles font-size="8pt"/>
																<children>
																	<paragraph paragraphtag="p">
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
																		</children>
																	</paragraph>
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
																<properties align="right" width="1.5in"/>
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
																<properties align="left" colspan="3" width="1.5in"/>
																<styles font-size="8pt"/>
																<children>
																	<text fixtext="SUBTOTAL DIRECT COSTS FOR NEXT BUDGET PERIOD">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" colspan="2" width="1.5in"/>
																<styles border-width="thick" font-family="Verdana" font-size="8pt"/>
																<children>
																	<text fixtext="$"/>
																	<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/PeriodDirectCostsTotal  -  nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/ConsortiumCosts/IndirectCosts">
																		<format string="#,###,###,##0.00" datatype="decimal"/>
																	</autocalc>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties align="left" colspan="2" width="1.5in"/>
																<styles border-bottom-style="dashed" border-bottom-width="0" border-right-width="0" font-family="Verdana" font-size="8pt"/>
																<children>
																	<text fixtext="CONSORITUM/CONTRACTUAL COSTS"/>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" colspan="2" width="2.1in"/>
																<styles border-left-width="0" font-family="Verdana" font-size="8pt"/>
																<children>
																	<text fixtext="DIRECT COSTS"/>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" width="1.5in"/>
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
																																<format string="#,###,###,##0"/>
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
																<properties align="left" colspan="2" width="1.5in"/>
																<styles border-right-width="0" border-top-width="0" font-family="Verdana" font-size="8pt"/>
															</tablecell>
															<tablecell>
																<properties align="right" colspan="2" width="2.1in"/>
																<styles border-left-width="0" font-family="Verdana" font-size="8pt"/>
																<children>
																	<text fixtext="FACILITIES AND ADMINISTRATION COSTS"/>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" width="1.5in"/>
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
																																<format string="#,###,###,##0"/>
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
																<properties align="left" colspan="3" width="1.5in"/>
																<styles font-size="8pt"/>
																<children>
																	<text fixtext="TOTAL DIRECT COSTS FOR NEXT BUDGET PERIOD (Item 8a, Face Page)">
																		<styles font-family="Verdana" font-size="8pt"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" colspan="2" width="1.5in"/>
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
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,###,##0"/>
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
								</children>
							</paragraph>
							<condition>
								<children>
									<conditionbranch xpath="count(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/SalariesAndWages/Name ) &gt;=8">
										<children>
											<newline break="page"/>
											<table>
												<properties border="1" cellpadding="5" cellspacing="0" width="100%"/>
												<styles border-collapse="collapse" line-height="12pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="4.5in"/>
																		<styles font-family="Verdana" font-size="9pt"/>
																		<children>
																			<paragraph paragraphtag="center">
																				<styles border-left-color="white" border-left-style="none" font-family="Verdana" font-size="9pt"/>
																				<children>
																					<text fixtext="DETAILED BUDGET FOR NEXT BUDGET PERIOD DIRECT COSTS ONLY">
																						<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
																					</text>
																				</children>
																			</paragraph>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="From">
																				<styles font-family="Verdana"/>
																			</text>
																			<newline/>
																			<paragraph paragraphtag="p">
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
																															<template match="StartDate" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-family="Verdana" font-size="9pt"/>
																																		<format string="MM/DD/YYYY"/>
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
																			</paragraph>
																			<newline/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="1in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<text fixtext="Through">
																				<styles font-family="Verdana"/>
																			</text>
																			<paragraph paragraphtag="p">
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
																															<template match="EndDate" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<styles font-family="Verdana" font-size="9pt"/>
																																		<format string="MM/DD/YYYY"/>
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
																			</paragraph>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="1.5in"/>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="GRANT NUMBER"/>
																				</children>
																			</paragraph>
																			<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																				<children>
																					<template match="nihPriorGrantNumber" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="9pt"/>
																							</content>
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
											<paragraph paragraphtag="center">
												<styles line-height="12pt"/>
											</paragraph>
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
																										<properties border="1" cellpadding="5" cellspacing="0"/>
																										<styles border-collapse="collapse" line-height="12pt"/>
																										<children>
																											<tableheader>
																												<children>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties colspan="2" width="2.1in"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="PERSONNEL(Applicant organization only)">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" colspan="3" width=".6in"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="Months Devoted to Project">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" colspan="3" width=".95in"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="DOLLAR AMOUNT REQUESTED (omit cents)">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties width="2.1in"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="NAME">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties width="1in"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<text fixtext="ROLE ON">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</text>
																																	<newline/>
																																	<paragraph paragraphtag="p">
																																		<children>
																																			<text fixtext="PROJECT">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																		</children>
																																	</paragraph>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width=".6in"/>
																																<styles font-family="Verdana" font-size="8pt" line-height="9pt"/>
																																<children>
																																	<text fixtext="Cal."/>
																																	<paragraph paragraphtag="p">
																																		<children>
																																			<text fixtext="Mnths"/>
																																		</children>
																																	</paragraph>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width=".6in"/>
																																<styles font-family="Verdana" font-size="8pt" line-height="9pt"/>
																																<children>
																																	<paragraph paragraphtag="p">
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="Acad."/>
																																				</children>
																																			</paragraph>
																																			<text fixtext="Mnths"/>
																																		</children>
																																	</paragraph>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width=".8in"/>
																																<styles font-family="Verdana" font-size="8pt" line-height="9pt"/>
																																<children>
																																	<paragraph paragraphtag="p">
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="Sum.">
																																						<styles font-size="8pt"/>
																																					</text>
																																				</children>
																																			</paragraph>
																																			<text fixtext="Mnths">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																		</children>
																																	</paragraph>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width=".95in"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<paragraph paragraphtag="p">
																																		<children>
																																			<text fixtext="SALARY">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																			<paragraph paragraphtag="p">
																																				<styles line-height="9pt"/>
																																				<children>
																																					<text fixtext="REQUESTED">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																					</text>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</paragraph>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width=".95in"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<paragraph paragraphtag="p">
																																		<children>
																																			<text fixtext="FRINGE">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																			<paragraph paragraphtag="p">
																																				<styles line-height="9pt"/>
																																				<children>
																																					<text fixtext="BENEFITS">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																					</text>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</paragraph>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties align="center" width=".95in"/>
																																<styles line-height="9pt"/>
																																<children>
																																	<paragraph paragraphtag="p">
																																		<children>
																																			<text fixtext="TOTALS">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																			</text>
																																		</children>
																																	</paragraph>
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
																																<children>
																																	<tablecell>
																																		<properties width="2.1in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<template match="Name" matchtype="schemagraphitem">
																																						<children>
																																							<template match="LastName" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-family="Verdana" font-size="9pt"/>
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
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="1in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<template match="ProjectRole" matchtype="schemagraphitem">
																																						<children>
																																							<content>
																																								<styles font-family="Verdana" font-size="8pt"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width=".6in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<template match="FundingMonths" matchtype="schemagraphitem">
																																						<children>
																																							<content/>
																																						</children>
																																					</template>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width=".6in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<template match="AcademicFundingMonths" matchtype="schemagraphitem">
																																						<children>
																																							<content/>
																																						</children>
																																					</template>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width=".8in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<template match="SummerFundingMonths" matchtype="schemagraphitem">
																																						<children>
																																							<content/>
																																						</children>
																																					</template>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width=".95in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<template match="RequestedCost" matchtype="schemagraphitem">
																																						<children>
																																							<text fixtext="$">
																																								<styles font-family="Verdana" font-size="8pt"/>
																																							</text>
																																							<content>
																																								<styles font-family="Verdana" font-size="8pt"/>
																																								<format string="#,###,###,##0"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width=".95in"/>
																																		<styles font-size="8pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<template match="FringeCost" matchtype="schemagraphitem">
																																						<children>
																																							<text fixtext="$">
																																								<styles font-size="8pt"/>
																																							</text>
																																							<content>
																																								<styles font-family="Verdana" font-size="8pt"/>
																																								<format string="#,###,###,##0"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width=".95in"/>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="$">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																					</text>
																																					<autocalc xpath="RequestedCost  +  FringeCost">
																																						<styles font-family="Verdana" font-size="8pt"/>
																																						<format string="#,###,###,##0" datatype="decimal"/>
																																					</autocalc>
																																				</children>
																																			</paragraph>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.3in" papermarginright="0.3in" papermargintop="0.79in" paperwidth="8.5in"/>
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
														<properties colspan="3" height="30"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" width="276"/>
														<styles padding="0"/>
														<children>
															<text fixtext="PHS 2590 (Rev. 11/07)">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left"/>
														<styles padding="0"/>
														<children>
															<text fixtext="Page: ">
																<styles font-family="Verdana" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles padding="0"/>
														<children>
															<text fixtext="Form Page  2">
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
