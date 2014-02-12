<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="budgetSalary.xsd" workingxmlfile="C:\COEUS40_VSS\Reports\BudgetSalary4.xml">
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
							<condition>
								<children>
									<conditionbranch xpath="budgetSalary/totalPeriod  = 1">
										<children>
											<template match="budgetSalary" matchtype="schemagraphitem">
												<children>
													<table>
														<properties border="1" cellpadding="0" cellspacing="0"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="25%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="33%"/>
																				<children>
																					<text fixtext="Name ">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="19%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="23%"/>
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
																						<properties width="25%"/>
																						<children>
																							<template match="costElementDesc" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="33%"/>
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
																						<properties align="right" width="19%"/>
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
																						<properties align="right" width="23%"/>
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
														<properties border="1" cellpadding="0" cellspacing="0"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties height="5" width="25%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="33%"/>
																				<children>
																					<text fixtext="Name">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="13%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="13%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties height="5" width="16%"/>
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
																						<properties width="25%"/>
																						<children>
																							<template match="costElementDesc" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="33%"/>
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
																						<properties align="right" width="13%"/>
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
																						<properties align="right" width="13%"/>
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
																						<properties align="right" width="16%"/>
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
														<properties border="1" cellpadding="0" cellspacing="0"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="24%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="30%"/>
																				<children>
																					<text fixtext="Name">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="11%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="11%"/>
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
																				<properties width="13%"/>
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
																						<properties width="24%"/>
																						<children>
																							<template match="costElementDesc" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="30%"/>
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
																						<properties align="right" width="11%"/>
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
																						<properties align="right" width="11%"/>
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
																						<properties align="right" width="10%"/>
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
																						<properties align="right" width="13%"/>
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
														<properties border="1" cellpadding="0" cellspacing="0"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="18%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="25%"/>
																				<children>
																					<text fixtext="Name">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="11%"/>
																				<children>
																					<text fixtext="Period 1">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="11%"/>
																				<children>
																					<text fixtext="Period 2">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="11%"/>
																				<children>
																					<text fixtext="Period 3">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="11%"/>
																				<children>
																					<text fixtext="Period 4">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="13%"/>
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
																						<properties width="18%"/>
																						<children>
																							<template match="costElementDesc" matchtype="schemagraphitem">
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
																						<properties align="right" width="11%"/>
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
																						<properties align="right" width="11%"/>
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
																						<properties align="right" width="11%"/>
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
																						<properties align="right" width="11%"/>
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
																						<properties align="right" width="13%"/>
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
														<properties border="1" cellpadding="0" cellspacing="0"/>
														<styles font-family="Times New Roman" font-size="8pt"/>
														<children>
															<tableheader>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties width="16%"/>
																				<children>
																					<text fixtext="Cost Element">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties width="22%"/>
																				<children>
																					<text fixtext="Name">
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
																						<properties width="16%"/>
																						<children>
																							<template match="costElementDesc" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="22%"/>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="8.5in" papermarginbottom="0.45in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="1.3in" paperwidth="11in"/>
		<children>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties border="0" width="100%"/>
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
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties border="0" width="100%"/>
								<styles table-layout="fixed"/>
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
														<properties colspan="4" height="8" width="38%"/>
														<styles padding="0" padding-bottom="0"/>
														<children>
															<newline/>
															<text fixtext="Coeus Proposal Development - Salary requested on proposal budget  ">
																<styles font-family="Times New Roman" font-size="15pt" font-weight="bold"/>
															</text>
															<text fixtext="  ">
																<styles font-family="Times New Roman" font-size="13pt" font-weight="bold"/>
															</text>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" valign="top" width="12%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0"/>
														<children>
															<text fixtext="Proposal Number:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="38%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0"/>
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
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0"/>
														<children>
															<text fixtext="Budget Version:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0"/>
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
														<properties align="right" valign="top" width="12%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0"/>
														<children>
															<text fixtext="Start Date:">
																<styles font-weight="bold"/>
															</text>
															<text fixtext=" "/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="38%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0"/>
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
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="35%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0"/>
														<children>
															<text fixtext="End Date:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Times New Roman" font-size="9pt" padding-bottom="0"/>
														<children>
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
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
