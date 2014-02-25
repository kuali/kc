<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="C:\Documents and Settings\coeusdev\Desktop\budgetSalary.xsd">
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
							<newline/>
							<table>
								<properties border="0" width="100%"/>
								<styles border-color="black" border-width="10%" table-layout="fixed"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="4" height="8" width="38%"/>
														<styles padding="0" padding-bottom="0"/>
														<children>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center" colspan="4" height="10" width="58%"/>
														<styles padding="0" padding-bottom="0" padding-top="10pt"/>
														<children>
															<newline/>
															<text fixtext="Coeus Proposal Development - ">
																<styles font-family="Times New Roman" font-size="15pt" font-weight="bold"/>
															</text>
															<template match="budgetSalary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="HeaderTitle" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<content>
																				<format datatype="string"/>
																			</content>
																		</children>
																	</template>
																</children>
															</template>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-right="10pt"/>
														<children>
															<text fixtext="Proposal Number:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="35%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-right="10pt"/>
														<children>
															<template match="budgetSalary" matchtype="schemagraphitem">
																<children>
																	<template match="proposalNumber" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<format datatype="string"/>
																			</content>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="35%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-left="10pt"/>
														<children>
															<text fixtext="Budget Version:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-left="10pt"/>
														<children>
															<template match="budgetSalary" matchtype="schemagraphitem">
																<children>
																	<template match="budgetVersion" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<format datatype="int"/>
																			</content>
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
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-right="10pt"/>
														<children>
															<text fixtext="Proposal Title:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="25%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-right="10pt"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="budgetSalary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="Title" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-right="10pt"/>
														<children>
															<text fixtext="Investigator Name:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="35%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-right="10pt"/>
														<children>
															<template match="budgetSalary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="PIName" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<content>
																				<format datatype="string"/>
																			</content>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="35%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-left="10pt"/>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-left="10pt"/>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<condition>
								<children>
									<conditionbranch xpath="budgetSalary/PrintBudgetComment = &apos;Yes&apos;">
										<children>
											<newline/>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="left" valign="top" width="15%"/>
																		<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-right="10pt"/>
																		<children>
																			<text fixtext=" Comments:">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="3"/>
																		<styles font-family="Times New Roman" font-size="9pt"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="budgetSalary" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="Comments" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</paragraph>
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
							<newline/>
							<table>
								<properties width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-right="10pt"/>
														<children>
															<text fixtext="Period:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="38%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0" padding-right="10pt"/>
														<children>
															<template match="budgetSalary" matchtype="schemagraphitem">
																<children>
																	<template match="startDate" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<format string="MM/DD/YYYY" datatype="date"/>
																			</content>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" - "/>
															<template match="budgetSalary" matchtype="schemagraphitem">
																<children>
																	<template match="endDate" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<format string="MM/DD/YYYY" datatype="date"/>
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
							<paragraph paragraphtag="p"/>
							<condition>
								<children>
									<conditionbranch xpath="budgetSalary/totalPeriod  = 1">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="30%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="30%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="30%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="30%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod = 2">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties height="5" width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="20%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="20%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="20%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="20%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="20%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="20%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod = 3">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod  = 4">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod = 5">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="10%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="10%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="10%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="10%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="10%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="10%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod = 6">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="30%"/>
																				<children>
																					<text fixtext="Period 6">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell headercell="1">
																				<properties width="30%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="30%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 6   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="30%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod  = 7">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="20%"/>
																				<children>
																					<text fixtext="Period 6">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="20%"/>
																				<children>
																					<text fixtext="Period 7">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell headercell="1">
																				<properties width="20%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="20%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 6   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="20%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 7   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 7  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="20%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod  = 8">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties height="1" width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties height="1" width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" height="1" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" height="1" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" height="1" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" height="1" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" height="1" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Period 6">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Period 7">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Period 8">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell headercell="1">
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties height="1" width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 6   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 7   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 7  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 8   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 8  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod  = 9">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 6">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 7">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 8">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 9">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell headercell="1">
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 7   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 7  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 8   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 8  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 9   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 9  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod  = 10">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="10%"/>
																				<children>
																					<text fixtext="Period 6">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="10%"/>
																				<children>
																					<text fixtext="Period 7">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="10%"/>
																				<children>
																					<text fixtext="Period 8">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="10%"/>
																				<children>
																					<text fixtext="Period 9">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="10%"/>
																				<children>
																					<text fixtext="Period 10">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="10%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 7  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 7 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 8   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 8  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 9   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 9  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 10   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 10  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod =11">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20" width="12%"/>
																				<children>
																					<text fixtext="Period 6">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20" width="12%"/>
																				<children>
																					<text fixtext="Period 7">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20" width="12%"/>
																				<children>
																					<text fixtext="Period 8">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20" width="12%"/>
																				<children>
																					<text fixtext="Period 9">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="20" width="12%"/>
																				<children>
																					<text fixtext="Period 10">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 7  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 7 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 8   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 8  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 9   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 9  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 10   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 10  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<newline/>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="30%"/>
																				<children>
																					<text fixtext="Period 11">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell headercell="1">
																				<properties height="13" width="30%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="30%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 11  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 11 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="30%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
													<newline/>
													<newline/>
												</children>
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod =12">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 6">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 7">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 8">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 9">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 10">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 7  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 7 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 8   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 8  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 9   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 9  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 10   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 10  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<newline/>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="23" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="23" width="20%"/>
																				<children>
																					<text fixtext="Period 11">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="23" width="20%"/>
																				<children>
																					<text fixtext="Period 12">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="23" width="20%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="20%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 11  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 11 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="20%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 12  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 12 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="20%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
													<newline/>
													<newline/>
													<newline/>
												</children>
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod =13">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties height="19" width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties height="19" width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 6">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 7">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 8">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 9">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 10">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 7  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 7 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 8   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 8  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 9   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 9  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 10   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 10  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<newline/>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties height="19" width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="15%"/>
																				<children>
																					<text fixtext="Period 11">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="15%"/>
																				<children>
																					<text fixtext="Period 12">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="15%"/>
																				<children>
																					<text fixtext="Period 13">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="15%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 11  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 11 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 12  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 12 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 13  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 13 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="15%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
													<newline/>
													<newline/>
													<newline/>
												</children>
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod =14">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Period 6">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Period 7">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Period 8">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Period 9">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Period 10">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 7  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 7 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 8   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 8  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 9   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 9  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 10   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 10  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<newline/>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 11">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 12">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 13">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="12%"/>
																				<children>
																					<text fixtext="Period 14">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 11  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 11 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 12  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 12 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 13  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 13 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 14  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 14 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
													<newline/>
													<newline/>
													<newline/>
												</children>
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="budgetSalary/totalPeriod =15">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="12%"/>
																				<children>
																					<text fixtext="Period 5">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 1   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 1  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 2   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 2  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 3   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 3  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 4   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 4  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 5  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Period 6">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Period 7">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Period 8">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Period 9">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="12%"/>
																				<children>
																					<text fixtext="Period 10">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 6  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 7  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 7 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 8   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 8  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 9   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 9  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="12%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 10   and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 10  and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		</children>
																	</template>
																</children>
															</tablebody>
														</children>
													</table>
													<newline/>
													<paragraph paragraphtag="p"/>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="15%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="25%"/>
																				<children>
																					<text fixtext="Description">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="10%"/>
																				<children>
																					<text fixtext="Period 11">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="10%"/>
																				<children>
																					<text fixtext="Period 12">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="10%"/>
																				<children>
																					<text fixtext="Period 13">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="10%"/>
																				<children>
																					<text fixtext="Period 14">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="19" width="10%"/>
																				<children>
																					<text fixtext="Period 15">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="18" width="10%"/>
																				<children>
																					<text fixtext="Total">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tableheader>
															<tablebody>
																<children>
																	<template match="salary" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="costElementCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="name" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath=". = &quot;Total&quot;">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
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
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 11  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 11 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 12  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 12 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 13  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 13 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 14  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 14 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="Period" matchtype="schemagraphitem">
																								<children>
																									<template match="periodCost" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../budgetPeriodID  = 15  and  ../../name  != &quot;Total&quot;">
																														<children>
																															<text fixtext="$"/>
																															<content>
																																<format string="#,###,###,##0.00" datatype="decimal"/>
																															</content>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../budgetPeriodID  = 15 and  ../../name  = &quot;Total&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="string-length( . ) &lt;7">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath=".">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="string-length( . ) &gt; 12">
																																		<children>
																																			<text fixtext="$">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																				<styles font-weight="bold"/>
																																			</autocalc>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																					<tablecell>
																						<properties align="right" width="10%"/>
																						<children>
																							<template match="total" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath=".">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles font-weight="bold"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles font-weight="bold"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
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
													<newline/>
													<newline/>
													<newline/>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="landscape" paperheight="8.5in" papermarginbottom="0.2in" papermarginleft="1.0in" papermarginright="1.5in" papermargintop="auto" paperwidth="11in"/>
		<children>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties border="0" width="100%"/>
								<styles padding-bottom="10pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top"/>
														<styles font-family="Verdana" font-size="7pt" padding="0"/>
														<children>
															<paragraph paragraphtag="p">
																<styles font-size="7pt"/>
																<children>
																	<text fixtext=" Page "/>
																	<field/>
																	<text fixtext="                  "/>
																</children>
															</paragraph>
															<text fixtext="Printed On:  "/>
															<template match="budgetSalary" matchtype="schemagraphitem">
																<children>
																	<template match="currentDate" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<format string="MM/DD/YYYY" datatype="date"/>
																			</content>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="150"/>
														<styles font-family="Verdana" font-size="7pt" padding="0"/>
														<children>
															<text fixtext="    "/>
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
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall"/>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
