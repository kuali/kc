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
								<properties border="1" cellspacing="0"/>
								<styles line-height="10pt" table-layout="fixed"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="8" height="7" valign="center" width="588"/>
														<styles border-left-style="none" border-right-style="none" border-top-style="none"/>
														<children>
															<text fixtext="             ">
																<styles font-family="Verdana" font-size="smaller"/>
															</text>
															<text fixtext="Program Director/Principal Investigator (Last, first, middle): ">
																<styles font-family="Verdana" font-size="8pt"/>
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
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center" colspan="8" height="30" valign="center" width="588"/>
														<children>
															<paragraph paragraphtag="h6">
																<styles padding-top="6pt"/>
																<children>
																	<text fixtext="BUDGET FOR ENTIRE PROPOSED PROJECT PERIOD">
																		<styles font-size="10pt" font-weight="bold"/>
																	</text>
																	<newline/>
																	<text fixtext="DIRECT COSTS ONLY">
																		<styles font-size="10pt" font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center" colspan="3" height="17" valign="middle" width="300"/>
														<children>
															<text fixtext="BUDGET CATEGORY">
																<styles font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="TOTALS">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" height="17" width="69"/>
														<children>
															<text fixtext="INITIAL BUDGET PERIOD ">
																<styles font-size="6pt"/>
															</text>
															<newline/>
															<text fixtext="(from Form Page 4)">
																<styles font-size="6pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" height="17" valign="center" width="69"/>
														<children>
															<text fixtext="2nd ADDITIONAL ">
																<styles font-size="6pt"/>
															</text>
															<newline/>
															<text fixtext="YEAR OF SUPPORT">
																<styles font-size="6pt"/>
															</text>
															<newline/>
															<text fixtext=" REQUESTED">
																<styles font-size="6pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" height="17" valign="center" width="69"/>
														<children>
															<text fixtext="3rd ADDITIONAL YEAR OF SUPPORT">
																<styles font-size="6pt"/>
															</text>
															<newline/>
															<text fixtext=" REQUESTED">
																<styles font-size="6pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" height="17" valign="center" width="69"/>
														<children>
															<text fixtext="4th ADDITIONAL ">
																<styles font-size="6pt"/>
															</text>
															<newline/>
															<text fixtext="YEAR OF SUPPORT">
																<styles font-size="6pt"/>
															</text>
															<newline/>
															<text fixtext=" REQUESTED">
																<styles font-size="6pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="center" height="17" valign="center" width="69"/>
														<children>
															<text fixtext="5th">
																<styles font-size="6pt"/>
															</text>
															<text fixtext=" ">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="ADDITIONAL ">
																<styles font-size="6pt"/>
															</text>
															<newline/>
															<text fixtext="YEAR OF SUPPORT">
																<styles font-size="6pt"/>
															</text>
															<newline/>
															<text fixtext=" REQUESTED">
																<styles font-size="6pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="20" valign="top" width="300"/>
														<children>
															<text fixtext="PERSONNEL: Salary and fringe benefits. Applicant organization only.">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="20" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="SalariesWagesTotal" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID  = 1">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="20" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="SalariesWagesTotal" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID  = 2">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="20" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="SalariesWagesTotal" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID  = 3">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="20" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="SalariesWagesTotal" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID = 4">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="20" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="SalariesWagesTotal" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID  = 5">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" width="300"/>
														<children>
															<text fixtext="CONSULTANT COSTS">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 1 and  ../Type =  &apos;Consultant Costs&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 2 and  ../Type =  &apos;Consultant Costs&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 3 and  ../Type = &apos;Consultant Costs&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 4 and  ../Type = &apos;Consultant Costs&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 5 and  ../Type = &apos;Consultant Costs&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" width="300"/>
														<children>
															<text fixtext="EQUIPMENT">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:EquipmentCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 1">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:EquipmentCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 2">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:EquipmentCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 3">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:EquipmentCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID = 4">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:EquipmentCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 5">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" width="300"/>
														<children>
															<text fixtext="SUPPLIES">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 1 and  ../Type =  &apos;Materials and Supplies&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 2 and  ../Type =   &apos;Materials and Supplies&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 3 and  ../Type =  &apos;Materials and Supplies&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 4 and  ../Type =   &apos;Materials and Supplies&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 5 and  ../Type =  &apos;Materials and Supplies&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" width="300"/>
														<children>
															<text fixtext="TRAVEL">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:TravelCosts" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID =1">
																										<children>
																											<template match="Cost" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
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
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:TravelCosts" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID =2">
																										<children>
																											<template match="Cost" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
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
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:TravelCosts" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID =3">
																										<children>
																											<template match="Cost" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
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
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:TravelCosts" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID =4">
																										<children>
																											<template match="Cost" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
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
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:TravelCosts" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID =5">
																										<children>
																											<template match="Cost" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
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
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" width="75"/>
														<children>
															<text fixtext="INPATIENT">
																<styles font-size="7pt"/>
															</text>
															<text fixtext=" PATIENT CARE COSTS">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 1 and  ../Type =  &apos;Patient Care&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 2 and  ../Type =  &apos;Patient Care&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 3 and  ../Type =   &apos;Patient Care&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID = 4 and  ../Type =  &apos;Patient Care&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 5 and  ../Type =  &apos;Patient Care&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" width="75"/>
														<children>
															<text fixtext="OUTPATIENT CARE COSTS">
																<styles font-size="7pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 1 and  ../Type =  &apos;Outpatient Services&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 2 and  ../Type =  &apos;Outpatient Services&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 3 and  ../Type =  &apos;Outpatient Services&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 4 and  ../Type =  &apos;Outpatient Services&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:ParticipantPatientCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 5 and  ../Type =  &apos;Outpatient Services&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" valign="top" width="300"/>
														<children>
															<text fixtext="ALTERATIONS AND RENOVATIONS">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 1 and  ../Type =  &apos;Alterations and Renovations&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 2 and  ../Type =  &apos;Alterations and Renovations&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 3 and  ../Type =  &apos;Alterations and Renovations&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 4 and  ../Type =  &apos;Alterations and Renovations&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="rar:OtherDirectCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="Cost" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID  = 5 and  ../Type =  &apos;Alterations and Renovations&apos;">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" width="300"/>
														<children>
															<text fixtext="OTHER EXPENSES">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<text fixtext="$"/>
															<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)">
																<format string="#,###,###,##0" datatype="decimal"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<text fixtext="$"/>
															<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=2]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)">
																<format string="#,###,###,##0" datatype="decimal"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<text fixtext="$"/>
															<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=3]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)">
																<format string="#,###,###,##0" datatype="decimal"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)">
																<format string="#,###,###,##0" datatype="decimal"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<autocalc xpath="sum(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=5]/rar:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost)">
																<format string="#,###,###,##0" datatype="decimal"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" valign="top" width="75"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="DIRECT CONSORTIUM/CONTRACTUAL COSTS"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="ConsortiumCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="DirectCosts" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID = 1">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="ConsortiumCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="DirectCosts" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID = 2">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="ConsortiumCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="DirectCosts" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID = 3">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="ConsortiumCosts" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID = 4">
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="ConsortiumCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="DirectCosts" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID = 5">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" valign="top" width="75"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="SUBTOTAL DIRECT COSTS">
																<styles font-weight="bold"/>
															</text>
															<newline/>
															<text fixtext="(Sum = Item 8a, Face Page)"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="$"/>
															<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/PeriodDirectCostsTotal -   nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/ConsortiumCosts/IndirectCosts">
																<format string="#,###,###,###,##0" datatype="decimal"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="boolean(number(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=2]/PeriodDirectCostsTotal -   nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=2]/ConsortiumCosts/IndirectCosts)) = true()">
																		<children>
																			<text fixtext="$"/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=2]/PeriodDirectCostsTotal -   nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=2]/ConsortiumCosts/IndirectCosts">
																				<format string="#,###,###,###,##0" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="boolean(number(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=3]/PeriodDirectCostsTotal -   nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=3]/ConsortiumCosts/IndirectCosts)) = true()">
																		<children>
																			<text fixtext="$"/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=3]/PeriodDirectCostsTotal -   nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=3]/ConsortiumCosts/IndirectCosts">
																				<format string="#,###,###,##0" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-family="Verdana" font-size="8pt" max-width="39"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="boolean(number(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/PeriodDirectCostsTotal - nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/ConsortiumCosts/IndirectCosts )  ) = true()">
																		<children>
																			<text fixtext="$"/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/PeriodDirectCostsTotal - nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/ConsortiumCosts/IndirectCosts">
																				<format string="#,###,###,###,##0" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="boolean(number(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=5]/PeriodDirectCostsTotal -   nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=5]/ConsortiumCosts/IndirectCosts )  ) = true()">
																		<children>
																			<text fixtext="$"/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=5]/PeriodDirectCostsTotal -   nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=5]/ConsortiumCosts/IndirectCosts">
																				<format string="#,###,###,###,##0" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" valign="top" width="75"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="F&amp;A CONSORTIUM/CONTRACTUAL COSTS"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="ConsortiumCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="IndirectCosts" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID = 1">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="ConsortiumCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="IndirectCosts" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID = 2">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="ConsortiumCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="IndirectCosts" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID = 3">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="ConsortiumCosts" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID = 4">
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="ConsortiumCosts" matchtype="schemagraphitem">
																						<children>
																							<template match="IndirectCosts" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../BudgetPeriodID = 5">
																												<children>
																													<text fixtext="$">
																														<styles font-size="8pt"/>
																													</text>
																													<content>
																														<styles font-size="8pt"/>
																														<format string="#,###,###,##0" datatype="decimal"/>
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
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="15" width="300"/>
														<children>
															<text fixtext="TOTAL DIRECT COSTS">
																<styles font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="PeriodDirectCostsTotal" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID = 1">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="PeriodDirectCostsTotal" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID = 2">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="PeriodDirectCostsTotal" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID = 3">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt" max-width="39"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="PeriodDirectCostsTotal" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID = 4">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="15" valign="center" width="69"/>
														<styles font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="PeriodDirectCostsTotal" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../BudgetPeriodID = 5">
																										<children>
																											<text fixtext="$">
																												<styles font-size="8pt"/>
																											</text>
																											<content>
																												<styles font-size="8pt"/>
																												<format string="#,###,###,##0" datatype="decimal"/>
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
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="7" height="18" valign="center" width="588"/>
														<children>
															<text fixtext="TOTAL DIRECT COSTS FOR ENTIRE PROPOSED PROJECT PERIOD    ">
																<styles font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" height="18" valign="center" width="69"/>
														<styles border-bottom-width="thick" border-left-width="thick" border-right-width="thick" border-top-width="thick" font-size="8pt"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetDirectCostsTotal" matchtype="schemagraphitem">
																				<children>
																					<text fixtext="$">
																						<styles font-size="8pt" font-weight="bold"/>
																					</text>
																					<content>
																						<styles font-size="8pt" font-weight="bold"/>
																						<format string="#,###,###,##0" datatype="decimal"/>
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
												<children>
													<tablecell>
														<properties colspan="8" height="271" valign="top" width="75"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="JUSTIFICATION. ">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="Follow the budget justification instructions exactly. Use continuation pages as needed."/>
															<newline/>
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
														<properties colspan="2" height="1"/>
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
											<tablerow>
												<children>
													<tablecell>
														<properties align="left"/>
														<styles font-size="smaller" padding="0"/>
														<children>
															<text fixtext="PHS 398 (Rev.  6/09)                                                                Page:__">
																<styles font-size="7pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles font-size="smaller" padding="0"/>
														<children>
															<text fixtext="Form Page: 5">
																<styles font-size="7pt" font-weight="bold"/>
															</text>
															<text fixtext=" ">
																<styles font-weight="bold"/>
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
