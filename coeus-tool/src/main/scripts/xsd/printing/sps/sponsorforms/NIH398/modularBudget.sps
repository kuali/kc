<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="H:\WinData\My Documents\Face Page$01262006-070737$.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties border="1" border-collapse="separate" cellpadding="0" cellspacing="0" table-layout="fixed" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="center" colspan="7" valign="center"/>
										<children>
											<paragraph paragraphtag="p">
												<styles font-family="Verdana" font-size="9pt"/>
												<children>
													<text fixtext="BUDGET JUSTIFICATION PAGE">
														<styles font-family="Verdana" font-size="11pt" font-weight="bold"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="MODULAR RESEARCH GRANT APPLICATION">
												<styles font-family="Verdana" font-size="11pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="left" height="15"/>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties height="15"/>
										<children>
											<text fixtext="Initial Budget Period">
												<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-size="9pt"/>
										<properties height="15"/>
										<children>
											<text fixtext="2nd">
												<styles font-family="Verdana" font-size="9pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-size="9pt"/>
										<properties height="15"/>
										<children>
											<text fixtext="3rd">
												<styles font-family="Verdana" font-size="9pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-size="9pt"/>
										<properties height="15"/>
										<children>
											<text fixtext="4th">
												<styles font-family="Verdana" font-size="9pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-size="9pt"/>
										<properties height="15"/>
										<children>
											<text fixtext="5th">
												<styles font-family="Verdana" font-size="9pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-size="9pt"/>
										<properties align="center" height="15"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Sum total">
														<styles font-family="Verdana" font-size="9pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext=" (for entire project period)">
												<styles font-family="Verdana" font-size="9pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="left" height="15"/>
										<children>
											<text fixtext="DC less Consortium F&amp;A"/>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ModularPeriodAmount[ ../BudgetPeriodID=1] -  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts[../../BudgetPeriodID=1]"/>
												</autocalc>
												<format string="#,###,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =2"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ModularPeriodAmount[../BudgetPeriodID=2] -  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts[../../BudgetPeriodID=2]"/>
																</autocalc>
																<format string="#,###,###,###,##0.00" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =3"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ModularPeriodAmount[../BudgetPeriodID=3] -  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts[../../BudgetPeriodID=3]"/>
																</autocalc>
																<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =4"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ModularPeriodAmount[../BudgetPeriodID=4] -  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts[../../BudgetPeriodID=4]"/>
																</autocalc>
																<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =5"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ModularPeriodAmount[../BudgetPeriodID=5] -  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts[../../BudgetPeriodID=5])"/>
																</autocalc>
																<format string="#,###,###,##0.00{}" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles border-width="thick" font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ModularPeriodAmount)- sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts)"/>
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
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="left" height="15"/>
										<children>
											<text fixtext="Consortium F&amp;A"/>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts[../../BudgetPeriodID=1]"/>
												</autocalc>
												<format string="#,###,###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =2"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts[../../BudgetPeriodID=2]"/>
																</autocalc>
																<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =3"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts[../../BudgetPeriodID=3]"/>
																</autocalc>
																<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =4"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts[../../BudgetPeriodID=4]"/>
																</autocalc>
																<format string="#,###,###,##0.00" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/BudgetPeriodID =5"/>
														</testexpression>
														<children>
															<text fixtext="$"/>
															<autovalue>
																<editorproperties editable="0"/>
																<autocalc>
																	<xpath value="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts[../../BudgetPeriodID=5]"/>
																</autocalc>
																<format string="#,###,###,##0.00{}" xslt="1" datatype="decimal"/>
															</autovalue>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ConsortiumCosts/IndirectCosts)"/>
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
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="left" height="15"/>
										<children>
											<text fixtext="Total Direct Costs"/>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
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
																		<match match="ModularPeriodAmount"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="../BudgetPeriodID =1"/>
																						</testexpression>
																						<children>
																							<text fixtext="$"/>
																							<xpath allchildren="1">
																								<format string="###,##0.00" xslt="1"/>
																							</xpath>
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
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
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
																		<match match="ModularPeriodAmount"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="../BudgetPeriodID =2"/>
																						</testexpression>
																						<children>
																							<text fixtext="$"/>
																							<xpath allchildren="1">
																								<format string="###,##0.00" xslt="1"/>
																							</xpath>
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
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
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
																		<match match="ModularPeriodAmount"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="../BudgetPeriodID =3"/>
																						</testexpression>
																						<children>
																							<text fixtext="$"/>
																							<xpath allchildren="1">
																								<format string="###,##0.00" xslt="1"/>
																							</xpath>
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
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
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
																		<match match="ModularPeriodAmount"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="../BudgetPeriodID =4"/>
																						</testexpression>
																						<children>
																							<text fixtext="$"/>
																							<xpath allchildren="1">
																								<format string="###,##0.00" xslt="1"/>
																							</xpath>
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
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
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
																		<match match="ModularPeriodAmount"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="../BudgetPeriodID =5"/>
																						</testexpression>
																						<children>
																							<text fixtext="$"/>
																							<xpath allchildren="1">
																								<format string="###,##0.00" xslt="1"/>
																							</xpath>
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
										<styles font-family="Verdana" font-size="9pt"/>
										<properties align="right" height="15"/>
										<children>
											<text fixtext="$"/>
											<autovalue>
												<editorproperties editable="0"/>
												<autocalc>
													<xpath value="sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod/ModularPeriodAmount )"/>
												</autocalc>
												<format string="###,###,##0.00" xslt="1" datatype="decimal"/>
											</autovalue>
										</children>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
			<newline/>
			<text fixtext="See Attached for Budget Justification.">
				<styles font-family="Verdana" font-size="9pt"/>
			</text>
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.65in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.5in" paperwidth="8.5in"/>
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
												<properties colspan="2" height="1"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles padding="0"/>
												<properties colspan="2"/>
												<children>
													<line>
														<properties color="black" size="1"/>
													</line>
												</children>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles font-size="9pt" padding="0"/>
												<properties align="left"/>
												<children>
													<text fixtext="PHS 398 (Rev. 04/06)                                            Page: __"/>
												</children>
											</tablecol>
											<tablecol>
												<styles font-size="9pt" padding="0"/>
												<properties align="right" width="150"/>
												<children>
													<text fixtext="Modular Budget Format Page"/>
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
		<headerall>
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
												<properties colspan="2" height="17"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles font-size="9pt" padding="0"/>
												<properties align="center" colspan="2"/>
												<children>
													<text fixtext="Principal Investigator/Program Director (Last, first, middle): "/>
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
																								<styles text-decoration="underline"/>
																							</xpath>
																						</children>
																					</template>
																					<text fixtext=", ">
																						<styles text-decoration="underline"/>
																					</text>
																					<template>
																						<match match="FirstName"/>
																						<children>
																							<xpath allchildren="1">
																								<styles text-decoration="underline"/>
																							</xpath>
																						</children>
																					</template>
																					<text fixtext=" ">
																						<styles text-decoration="underline"/>
																					</text>
																					<template>
																						<match match="MiddleName"/>
																						<children>
																							<xpath allchildren="1">
																								<styles text-decoration="underline"/>
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
												<styles padding="0"/>
												<properties colspan="2" height="1"/>
											</tablecol>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
				</children>
			</template>
		</headerall>
	</pagelayout>
</structure>
