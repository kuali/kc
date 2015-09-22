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
			<xsdschemasource name="$XML" main="1" schemafile="phs398schema.xsd" workingxmlfile="nihproposalSample.xml">
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
			<pagelayout match="#footereven" isactive="0"/>
			<pagelayout match="#footerodd" isactive="0"/>
			<pagelayout match="#headerall" isactive="1"/>
			<pagelayout match="#headereven" isactive="0"/>
			<pagelayout match="#headerodd" isactive="0"/>
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
							<table>
								<properties border="1" cellspacing="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center"/>
														<children>
															<text fixtext=" "/>
															<text fixtext="CHECKLIST">
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
							<table>
								<properties border="0" width="100%"/>
								<styles font-size="8pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3"/>
														<children>
															<text fixtext="TYPE OF APPLICATION ">
																<styles font-weight="bold"/>
															</text>
															<text fixtext="(check all that apply.)"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier !=&apos;New&apos;">
																		<children>
																			<text fixtext="___"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier =&apos;New&apos;">
																		<children>
																			<text fixtext="_X_"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext="NEW application. (This application is being submitted to the PHS for the first time.)"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties height="8" valign="top" width="50%"/>
														<styles font-family="Verdana" font-size="9pt" line-height="9pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier != &apos;Resubmission&apos;">
																		<children>
																			<text fixtext="___"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier = &apos;Resubmission&apos;">
																		<children>
																			<text fixtext="_X_"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext="  ">
																<styles margin-bottom="0pt"/>
															</text>
															<text fixtext="RESUBMISSION ">
																<styles font-size="8pt" margin-bottom="0pt"/>
															</text>
															<text fixtext="of application number: ">
																<styles margin-bottom="0pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="2" valign="top"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihPriorGrantNumber" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="../nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier  =&apos;Revision&apos; or ../nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier  = &apos;Resubmission&apos;">
																						<children>
																							<content>
																								<styles font-size="8pt"/>
																								<format datatype="string"/>
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
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3"/>
														<children>
															<text fixtext="  (This application replaces a prior unfunded version of a new, renewal, or revision application.)"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties valign="top" width="20%"/>
														<styles font-family="Verdana" font-size="9pt" line-height="9pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier !=&apos;Continuation&apos; and nih:ResearchAndRelatedProject/nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier !=&apos;Renewal&apos;">
																		<children>
																			<text fixtext="__"/>
																			<text fixtext="_ ">
																				<styles margin-bottom="0pt"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier =&apos;Continuation&apos; or nih:ResearchAndRelatedProject/nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier =&apos;Renewal&apos;">
																		<children>
																			<text fixtext="_X_"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext=" ">
																<styles margin-bottom="0pt"/>
															</text>
															<text fixtext="RENEWAL of grant number:">
																<styles font-size="8pt" margin-bottom="0pt"/>
															</text>
															<text fixtext=" ">
																<styles margin-bottom="0pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="2" valign="top"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihPriorGrantNumber" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="../nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier =&apos;Competing Continuation&apos; or ../nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier =&apos;Renewal&apos;">
																						<children>
																							<content>
																								<styles font-size="8pt"/>
																								<format datatype="string"/>
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
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3"/>
														<children>
															<text fixtext="  (This application is to extend a funded grant beyond its current project period.)"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties valign="top" width="20%"/>
														<styles font-family="Verdana" font-size="8pt" line-height="6pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier !=&apos;Revision&apos;">
																		<children>
																			<text fixtext="___"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier =&apos;Revision&apos;">
																		<children>
																			<text fixtext="_X_"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext="  REVISION to grant number: ">
																<styles margin-bottom="0pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="2" valign="top"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="nihPriorGrantNumber" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="../nih:ResearchCoverPage/ApplicationCategory/CategoryIdentifier = &apos;Supplement&apos;">
																						<children>
																							<content>
																								<format datatype="string"/>
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
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3"/>
														<children>
															<text fixtext="  (This application is for additional funds to supplement a currently funded grant.)"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" width="20%"/>
														<styles font-family="Verdana" font-size="8pt" line-height="6pt"/>
														<children>
															<text fixtext="___ CHANGE of program director/principal investigator."/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<text fixtext="     Name of former program director/principal investigator."/>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="2" width="20%"/>
														<styles font-family="Verdana" font-size="8pt" line-height="6pt"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties valign="top" width="20%"/>
														<styles font-family="Verdana" font-size="8pt" line-height="6pt"/>
														<children>
															<text fixtext="___ CHANGE of Grantee Institution"/>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="2" valign="top" width="20%"/>
														<styles font-family="Verdana" font-size="8pt" line-height="6pt"/>
														<children>
															<text fixtext="Name of former institution:"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties valign="top" width="20%"/>
														<styles font-family="Verdana" font-size="8pt" line-height="6pt"/>
														<children>
															<text fixtext="___ FOREIGN application"/>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="20%"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="   __ Domestic Grant with  foreign involvement   "/>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="20%"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="List country(ies) involved: "/>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<text fixtext="INVENTIONS AND PATENTS (Renewal appl. only)  ">
								<styles font-size="8pt"/>
							</text>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<children>
									<template match="nihInventions" matchtype="schemagraphitem">
										<children>
											<checkbox checkedvalue="X" uncheckedvalue="true">
												<children>
													<content>
														<format datatype="string"/>
													</content>
												</children>
											</checkbox>
										</children>
									</template>
								</children>
							</template>
							<text fixtext="No  ">
								<styles font-size="8pt"/>
							</text>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<children>
									<template match="nihInventions" matchtype="schemagraphitem">
										<children>
											<checkbox checkedvalue="true" checkedvalue1="1" checkedvalue2="Y" checkedvalue3="N">
												<styles border="1" width="21px"/>
												<children>
													<content>
														<format datatype="string"/>
													</content>
												</children>
											</checkbox>
										</children>
									</template>
								</children>
							</template>
							<text fixtext="Yes">
								<styles font-size="8pt"/>
							</text>
							<newline/>
							<text fixtext="If &quot;Yes,&quot;   ">
								<styles font-size="8pt"/>
							</text>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<children>
									<template match="nihInventions" matchtype="schemagraphitem">
										<children>
											<checkbox checkedvalue="Y">
												<children>
													<content>
														<format datatype="string"/>
													</content>
												</children>
											</checkbox>
										</children>
									</template>
								</children>
							</template>
							<text fixtext="Previously reported   ">
								<styles font-size="8pt"/>
							</text>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<children>
									<template match="nihInventions" matchtype="schemagraphitem">
										<children>
											<checkbox checkedvalue="N">
												<children>
													<content>
														<format datatype="string"/>
													</content>
												</children>
											</checkbox>
										</children>
									</template>
								</children>
							</template>
							<text fixtext=" Not previously reported  ">
								<styles font-size="8pt"/>
							</text>
							<newline/>
							<line>
								<properties color="black" size="1"/>
							</line>
							<text fixtext="1.  PROGRAM INCOME (See instructions.)">
								<styles font-size="8pt" font-weight="bold"/>
							</text>
							<newline/>
							<text fixtext="All applications must indicate whether program income is anticipated during the period(s) for which grant support is requested. If program income is anticipated, use the format below to reflect the amount and source(s).">
								<styles font-family="Verdana" font-size="8pt"/>
							</text>
							<condition>
								<children>
									<conditionbranch xpath="count(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ProgramIncomeDetails/AnticipatedAmount ) = 0">
										<children>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<children>
																			<text fixtext="No Program Income Anticipated.">
																				<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
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
									</conditionbranch>
								</children>
							</condition>
							<condition>
								<children>
									<conditionbranch xpath="count( nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ProgramIncomeDetails/AnticipatedAmount ) != 0">
										<children>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<children>
																			<text fixtext="See Attached.">
																				<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
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
									</conditionbranch>
								</children>
							</condition>
							<text fixtext="2. ASSURANCES/CERTIFICATIONS (See instructions.)">
								<styles font-size="8pt" font-weight="bold"/>
							</text>
							<table>
								<properties width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2" valign="top"/>
														<styles font-family="Verdana" font-size="8pt"/>
														<children>
															<text fixtext="In signing the application Face Page, the authorized organizational representative agrees to comply with the policies, assurances and/or certifications listed in the application instructions when applicable. Descriptions of individual assurances/certifications are provided in Part III and listed in Part 1, 4.1 under Item 14.  If unable to certify compliance, where applicable, provide an explanation and place it after this page."/>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<line>
								<properties color="black" size="1"/>
							</line>
							<text fixtext="3. FACILITIES AND ADMINISTRATION COSTS (F &amp; A)/INDIRECT COSTS.">
								<styles font-size="8pt" font-weight="bold"/>
							</text>
							<text fixtext=" See specific instructions">
								<styles font-size="8pt"/>
							</text>
							<newline/>
							<table>
								<properties border="0" width="100%"/>
								<styles font-size="8pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<styles font-size="8pt"/>
														<children>
															<text fixtext=" "/>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/BudgetSummary/IndirectCostRateDetails/DHHSAgreementDate  = true()">
																		<children>
																			<text fixtext="X">
																				<styles text-decoration="underline"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="_"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext=" DHHS Agreement dated:  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="IndirectCostRateDetails" matchtype="schemagraphitem">
																				<children>
																					<template match="DHHSAgreementDate" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-weight="bold"/>
																								<format string="MM/DD/YYYY" datatype="date"/>
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
													</tablecell>
													<tablecell>
														<properties colspan="2"/>
														<children>
															<text fixtext="___">
																<styles font-weight="bold"/>
															</text>
															<text fixtext="   No Facilities and Administration Costs Requested."/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3"/>
														<children>
															<text fixtext=" "/>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/BudgetSummary/IndirectCostRateDetails/DHHSAgreementNegotiationOffice =true()">
																		<children>
																			<text fixtext="X">
																				<styles text-decoration="underline"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="_"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext="  DHHS Agreement being negotiated with "/>
															<text fixtext="____">
																<styles font-weight="bold"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="IndirectCostRateDetails" matchtype="schemagraphitem">
																				<children>
																					<template match="DHHSAgreementNegotiationOffice" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-weight="bold" text-decoration="underline"/>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext="__________">
																<styles font-weight="bold"/>
															</text>
															<text fixtext="  Regional Office."/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="2"/>
														<children>
															<text fixtext=" "/>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/BudgetSummary/IndirectCostRateDetails/NoDHHSAgreement/AgencyName =true()">
																		<children>
																			<text fixtext="X">
																				<styles text-decoration="underline"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="_"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext=" No DHHS Agreement , but rate established with:"/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="IndirectCostRateDetails" matchtype="schemagraphitem">
																				<children>
																					<template match="NoDHHSAgreement" matchtype="schemagraphitem">
																						<children>
																							<template match="AgencyName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles text-decoration="underline"/>
																										<format datatype="string"/>
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
															<text fixtext="     "/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="1in"/>
														<children>
															<text fixtext="Date:"/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="IndirectCostRateDetails" matchtype="schemagraphitem">
																				<children>
																					<template match="NoDHHSAgreement" matchtype="schemagraphitem">
																						<children>
																							<template match="AgreementDate" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles text-decoration="underline"/>
																										<format string="MM/DD/YYYY" datatype="date"/>
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
										</children>
									</tablebody>
								</children>
							</table>
							<newline/>
							<table>
								<properties width="100%"/>
								<styles font-size="8pt" table-layout="fixed"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="6" height="16" valign="bottom" width="26%"/>
														<styles line-height="10pt"/>
														<children>
															<text fixtext="CALCULATION* (The entire grant application, including the Checklist, will be reproduced and provided to peer reviewers as confidential information. "/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="26%"/>
														<children>
															<text fixtext="a. Initial budget period: ">
																<styles font-size="7pt"/>
															</text>
															<text fixtext="Amount of base $"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="18%"/>
													</tablecell>
													<tablecell>
														<properties width="12%"/>
														<styles font-family="Verdana"/>
														<children>
															<text fixtext="x Rate applied"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="13%"/>
														<styles font-family="Verdana"/>
													</tablecell>
													<tablecell>
														<properties align="right" width="14%"/>
														<styles font-family="Verdana"/>
														<children>
															<text fixtext="% = F&amp;A cost $"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" width="17%"/>
														<styles font-family="Verdana"/>
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
																									<template match="IndirectCostsTotal" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles text-decoration="underline"/>
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
											<tablerow>
												<children>
													<tablecell>
														<properties width="26%"/>
														<children>
															<text fixtext="b.  02 year                Amount of base $"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="18%"/>
													</tablecell>
													<tablecell>
														<properties width="12%"/>
														<children>
															<text fixtext="x Rate applied"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="13%"/>
													</tablecell>
													<tablecell>
														<properties align="right" width="14%"/>
														<children>
															<text fixtext="% = F&amp;A cost $"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" width="17%"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="BudgetPeriodID =2">
																								<children>
																									<template match="IndirectCostsTotal" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles text-decoration="underline"/>
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
											<tablerow>
												<children>
													<tablecell>
														<properties width="26%"/>
														<children>
															<text fixtext="c.  03 year                Amount of base $"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="18%"/>
													</tablecell>
													<tablecell>
														<properties width="12%"/>
														<children>
															<text fixtext="x Rate applied"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="13%"/>
													</tablecell>
													<tablecell>
														<properties align="right" width="14%"/>
														<children>
															<text fixtext="% = F&amp;A cost $"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" width="17%"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="BudgetPeriodID =3">
																								<children>
																									<template match="IndirectCostsTotal" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles text-decoration="underline"/>
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
											<tablerow>
												<children>
													<tablecell>
														<properties width="26%"/>
														<children>
															<text fixtext="d.  04 year                Amount of base $"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="18%"/>
													</tablecell>
													<tablecell>
														<properties width="12%"/>
														<children>
															<text fixtext="x Rate applied"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="13%"/>
													</tablecell>
													<tablecell>
														<properties align="right" width="14%"/>
														<children>
															<text fixtext="% = F&amp;A cost $"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" width="17%"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="BudgetPeriodID =4">
																								<children>
																									<template match="IndirectCostsTotal" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles text-decoration="underline"/>
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
											<tablerow>
												<children>
													<tablecell>
														<properties width="26%"/>
														<children>
															<text fixtext="e.  05 year                Amount of base $"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="18%"/>
													</tablecell>
													<tablecell>
														<properties width="12%"/>
														<children>
															<text fixtext="x Rate applied"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="13%"/>
													</tablecell>
													<tablecell>
														<properties align="right" width="14%"/>
														<children>
															<text fixtext="% = F&amp;A cost $"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" width="17%"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="BudgetPeriodID =5">
																								<children>
																									<template match="IndirectCostsTotal" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<styles text-decoration="underline"/>
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
							<table>
								<properties width="100%"/>
								<styles font-size="8pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" width="30%"/>
														<children>
															<text fixtext="*Check appropriate box(es):"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="30%"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =1 and nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/IndirectCostDetails/CostType != &apos;SandW&apos;">
																		<children>
																			<text fixtext="___">
																				<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="BudgetPeriodID" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath=". =1 and  ../IndirectCostDetails/CostType =&apos;SandW&apos;">
																										<children>
																											<checkbox checkedvalue="true" checkedvalue1="1">
																												<children>
																													<content>
																														<format datatype="integer"/>
																													</content>
																												</children>
																											</checkbox>
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
															<text fixtext=" Salary and wages base"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="30%"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =1 and nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/IndirectCostDetails/CostType != &apos;MTDC&apos;">
																		<children>
																			<text fixtext="___">
																				<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="BudgetPeriodID" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath=". =1 and  ../IndirectCostDetails/CostType =&apos;MTDC&apos;">
																										<children>
																											<checkbox checkedvalue="true" checkedvalue1="1">
																												<children>
																													<content>
																														<format datatype="integer"/>
																													</content>
																												</children>
																											</checkbox>
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
															<text fixtext=" Modified total direct cost base"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="40%"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =1 and (nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/IndirectCostDetails/CostType = &apos;SandW&apos; or nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=1]/IndirectCostDetails/CostType = &apos;MTDC&apos;)">
																		<children>
																			<text fixtext="___">
																				<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="BudgetPeriodID" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath=". =1 and  ../IndirectCostDetails/CostType !=&apos;MTDC&apos; and ../IndirectCostDetails/CostType !=&apos;SandW&apos;">
																										<children>
																											<checkbox checkedvalue="true" checkedvalue1="1">
																												<children>
																													<content>
																														<format datatype="integer"/>
																													</content>
																												</children>
																											</checkbox>
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
															<text fixtext=" Other base (Explain)"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" width="30%"/>
														<children>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<children>
																	<template match="BudgetSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<template match="BudgetPeriodID" matchtype="schemagraphitem">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath=". =1">
																										<children>
																											<checkbox checkedvalue="true" checkedvalue1="1">
																												<children>
																													<content>
																														<format datatype="integer"/>
																													</content>
																												</children>
																											</checkbox>
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
															<text fixtext=" Off-site, other special rate, or more than one rate involved (Explain)"/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" width="30%"/>
														<children>
															<text fixtext="Explanation (Attach separate sheet, if necessary.): "/>
															<newline/>
															<line>
																<properties size="1"/>
																<styles font-variant="normal"/>
															</line>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<text fixtext="4. DISCLOSURE PERMISSION STATEMENT: ">
								<styles font-size="8pt" font-weight="bold"/>
							</text>
							<text fixtext="If this application does not result in an award, is the Government permitted to disclose the title of your proposed project, and the name, address, telephone number and e-mail address of the official signing for the appliant organization, to organizations that may be interested in contacting you for further information (e.g. possible collaborations, investment)?   ">
								<styles font-size="8pt"/>
							</text>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="5"/>
								<children>
									<template match="nih:ProjectDescription" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<template match="rar:ProjectSurvey" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="DisclosurePermissionQuestion" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<checkbox checkedvalue="true" checkedvalue1="1">
																<children>
																	<content>
																		<format datatype="boolean"/>
																	</content>
																</children>
															</checkbox>
															<text fixtext=" "/>
															<text fixtext="Yes ">
																<styles font-size="8pt"/>
															</text>
														</children>
													</template>
												</children>
											</template>
										</children>
									</template>
								</children>
							</template>
							<text fixtext=" "/>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="5"/>
								<children>
									<template match="nih:ProjectDescription" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<template match="rar:ProjectSurvey" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="DisclosurePermissionQuestion" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<checkbox checkedvalue="false" checkedvalue1="0" uncheckedvalue="true">
																<children>
																	<content>
																		<format datatype="boolean"/>
																	</content>
																</children>
															</checkbox>
															<text fixtext=" "/>
															<text fixtext="No ">
																<styles font-size="8pt"/>
															</text>
														</children>
													</template>
												</children>
											</template>
										</children>
									</template>
								</children>
							</template>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="boolean(nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ProgramIncome =true() or  nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/IndirectCostDetails/BaseAmount = true())">
										<children>
											<newline break="page"/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<condition>
								<children>
									<conditionbranch xpath="count( nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ProgramIncomeDetails/AnticipatedAmount ) != 0">
										<children>
											<text fixtext="Program Income">
												<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
											</text>
											<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
												<children>
													<newline/>
													<template match="BudgetSummary" matchtype="schemagraphitem">
														<children>
															<table>
																<properties border="1" cellspacing="0" width="100%"/>
																<styles font-size="8pt"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties valign="top" width="15%"/>
																						<styles font-family="Verdana" font-size="9pt" line-height="6pt"/>
																						<children>
																							<text fixtext="Budget Period"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties valign="top" width="40%"/>
																						<styles font-family="Verdana" font-size="9pt" line-height="6pt"/>
																						<children>
																							<text fixtext="Anticipated Amount"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties valign="top" width="45%"/>
																						<styles font-family="Verdana" font-size="9pt" line-height="6pt"/>
																						<children>
																							<text fixtext="Source(s)"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tableheader>
																	<tablebody>
																		<children>
																			<template match="BudgetPeriod" matchtype="schemagraphitem">
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties height="21" valign="top" width="15%"/>
																								<styles font-family="Verdana" font-size="9pt" line-height="6pt"/>
																								<children>
																									<template match="BudgetPeriodID" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<format datatype="integer"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties height="21" valign="top" width="40%"/>
																								<styles font-family="Verdana" font-size="9pt" line-height="6pt"/>
																								<children>
																									<table>
																										<children>
																											<tablebody>
																												<children>
																													<template match="ProgramIncomeDetails" matchtype="schemagraphitem">
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<styles font-size="9pt"/>
																																		<children>
																																			<template match="AnticipatedAmount" matchtype="schemagraphitem">
																																				<children>
																																					<text fixtext="$">
																																						<styles font-family="Verdana"/>
																																					</text>
																																					<content>
																																						<styles font-family="Verdana"/>
																																						<format datatype="decimal"/>
																																					</content>
																																				</children>
																																			</template>
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
																									<text fixtext=" "/>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties height="21" width="45%"/>
																								<styles font-family="Verdana" font-size="9pt"/>
																								<children>
																									<table>
																										<properties border="1" cellspacing="0"/>
																										<children>
																											<tablebody>
																												<children>
																													<template match="ProgramIncomeDetails" matchtype="schemagraphitem">
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<styles font-size="9pt"/>
																																		<children>
																																			<template match="Sources" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
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
																									<text fixtext="  "/>
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
													</template>
												</children>
											</template>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<text fixtext=" ">
								<styles font-weight="bold"/>
							</text>
							<condition>
								<children>
									<conditionbranch xpath="count( nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/IndirectCostDetails )  &gt;0">
										<children>
											<text fixtext="FACILITIES AND ADMINISTRATION COSTS (F &amp; A)/INDIRECT COSTS.">
												<styles font-family="Verdana" font-size="8pt" font-weight="bold"/>
											</text>
											<text fixtext=" "/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
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
																	<table>
																		<properties border="1" cellspacing="0"/>
																		<children>
																			<tableheader>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties align="center" colspan="3"/>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Budget Period 1">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Base Amount">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Rate Applied">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Funds Requested">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tableheader>
																			<tablebody>
																				<children>
																					<template match="IndirectCostDetails" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="BaseAmount" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="Rate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="decimal"/>
																													</content>
																													<text fixtext="%"/>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="FundsRequested" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
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
									</template>
								</children>
							</template>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<children>
									<template match="BudgetSummary" matchtype="schemagraphitem">
										<children>
											<template match="BudgetPeriod" matchtype="schemagraphitem">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="BudgetPeriodID =2">
																<children>
																	<table>
																		<properties border="1" cellspacing="0"/>
																		<children>
																			<tableheader>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties align="center" colspan="3"/>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Budget Period 2">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Base Amount">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Rate Applied">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Funds Requested">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tableheader>
																			<tablebody>
																				<children>
																					<template match="IndirectCostDetails" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="BaseAmount" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="Rate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="decimal"/>
																													</content>
																													<text fixtext="%"/>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="FundsRequested" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
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
									</template>
								</children>
							</template>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<children>
									<template match="BudgetSummary" matchtype="schemagraphitem">
										<children>
											<template match="BudgetPeriod" matchtype="schemagraphitem">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="BudgetPeriodID =3">
																<children>
																	<table>
																		<properties border="1" cellspacing="0"/>
																		<children>
																			<tableheader>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties align="center" colspan="3"/>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Budget Period 3">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Base Amount">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Rate Applied">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Funds Requested">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tableheader>
																			<tablebody>
																				<children>
																					<template match="IndirectCostDetails" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="BaseAmount" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="Rate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="decimal"/>
																													</content>
																													<text fixtext="%"/>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="FundsRequested" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
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
									</template>
								</children>
							</template>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<children>
									<template match="BudgetSummary" matchtype="schemagraphitem">
										<children>
											<template match="BudgetPeriod" matchtype="schemagraphitem">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="BudgetPeriodID =4">
																<children>
																	<table>
																		<properties border="1" cellspacing="0"/>
																		<children>
																			<tableheader>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties align="center" colspan="3"/>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Budget Period 4">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Base Amount">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Rate Applied">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Funds Requested">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tableheader>
																			<tablebody>
																				<children>
																					<template match="IndirectCostDetails" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="BaseAmount" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="Rate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="decimal"/>
																													</content>
																													<text fixtext="%"/>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="FundsRequested" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
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
									</template>
								</children>
							</template>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<children>
									<template match="BudgetSummary" matchtype="schemagraphitem">
										<children>
											<template match="BudgetPeriod" matchtype="schemagraphitem">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="BudgetPeriodID =5">
																<children>
																	<table>
																		<properties border="1" cellspacing="0"/>
																		<children>
																			<tableheader>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties align="center" colspan="3"/>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Budget Period 5">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Base Amount">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Rate Applied">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<children>
																									<text fixtext="Funds Requested">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tableheader>
																			<tablebody>
																				<children>
																					<template match="IndirectCostDetails" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="BaseAmount" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="Rate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="decimal"/>
																													</content>
																													<text fixtext="%"/>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<children>
																											<template match="FundsRequested" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,###,##0" datatype="decimal"/>
																													</content>
																												</children>
																											</template>
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
									</template>
								</children>
							</template>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.45in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.5in" paperwidth="8.5in"/>
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
														<properties colspan="2"/>
														<styles padding="0"/>
														<children>
															<line>
																<properties color="black" size="1"/>
															</line>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left"/>
														<styles font-size="9pt" padding="0"/>
														<children>
															<text fixtext="PHS 398 (Rev. 6/09)                                                            Page: _"/>
															<field/>
															<text fixtext="_"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles font-size="9pt" padding="0"/>
														<children>
															<text fixtext="Checklist Form Page"/>
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
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
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
														<properties colspan="2" height="18"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="2"/>
														<styles font-size="9pt" padding="0"/>
														<children>
															<text fixtext="Program Director/Principal Investigator(Last, first, middle): "/>
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
																										<styles text-decoration="underline"/>
																										<format datatype="token"/>
																									</content>
																									<text fixtext=", ">
																										<styles text-decoration="underline"/>
																									</text>
																								</children>
																							</template>
																							<template match="FirstName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles text-decoration="underline"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<template match="MiddleName" matchtype="schemagraphitem">
																								<children>
																									<text fixtext=" ">
																										<styles text-decoration="underline"/>
																									</text>
																									<content>
																										<styles text-decoration="underline"/>
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
														<properties colspan="2" height="1"/>
														<styles padding="0"/>
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
			<globaltemplate match="#headerodd" matchtype="named" parttype="headerodd">
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
														<properties colspan="2" height="18"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="2"/>
														<styles font-size="9pt" padding="0"/>
														<children>
															<text fixtext="Program Director/Principal Investigator(Last, first, middle): "/>
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
																										<styles text-decoration="underline"/>
																										<format datatype="token"/>
																									</content>
																									<text fixtext=", ">
																										<styles text-decoration="underline"/>
																									</text>
																								</children>
																							</template>
																							<template match="FirstName" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles text-decoration="underline"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<template match="MiddleName" matchtype="schemagraphitem">
																								<children>
																									<text fixtext=" ">
																										<styles text-decoration="underline"/>
																									</text>
																									<content>
																										<styles text-decoration="underline"/>
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
														<properties colspan="2" height="1"/>
														<styles padding="0"/>
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
			<globaltemplate match="#headereven" matchtype="named" parttype="headereven">
				<children>
					<condition>
						<children>
							<conditionbranch xpath="count( nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/IndirectCostDetails )  &gt;0">
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
																		<properties colspan="2" height="18"/>
																		<styles padding="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="left" colspan="2"/>
																		<styles font-size="9pt" padding="0"/>
																		<children>
																			<text fixtext="Program Director/Principal Investigator(Last, first, middle): "/>
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
																														<styles text-decoration="underline"/>
																														<format datatype="token"/>
																													</content>
																													<text fixtext=", ">
																														<styles text-decoration="underline"/>
																													</text>
																												</children>
																											</template>
																											<template match="FirstName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles text-decoration="underline"/>
																														<format datatype="token"/>
																													</content>
																												</children>
																											</template>
																											<template match="MiddleName" matchtype="schemagraphitem">
																												<children>
																													<text fixtext=" ">
																														<styles text-decoration="underline"/>
																													</text>
																													<content>
																														<styles text-decoration="underline"/>
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
																		<properties colspan="2" height="1"/>
																		<styles padding="0"/>
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
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate match="#footerodd" matchtype="named" parttype="footerodd">
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
														<properties colspan="2"/>
														<styles padding="0"/>
														<children>
															<line>
																<properties color="black" size="1"/>
															</line>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left"/>
														<styles font-size="9pt" padding="0"/>
														<children>
															<text fixtext="PHS 398 (Rev. 6/09)                                                            Page: _"/>
															<field/>
															<text fixtext="_"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles font-size="9pt" padding="0"/>
														<children>
															<text fixtext="Checklist Form Page"/>
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
			<globaltemplate match="#footereven" matchtype="named" parttype="footereven">
				<children>
					<condition>
						<children>
							<conditionbranch xpath="count( nih:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/IndirectCostDetails )  &gt;0">
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
																		<properties colspan="2"/>
																		<styles padding="0"/>
																		<children>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="left"/>
																		<styles font-size="9pt" padding="0"/>
																		<children>
																			<text fixtext="PHS 398 (Rev. 6/09)                                                            Page: _"/>
																			<field/>
																			<text fixtext="_"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="right" width="150"/>
																		<styles font-size="9pt" padding="0"/>
																		<children>
																			<text fixtext="Checklist Form Page"/>
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
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
