<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="AwardNotice.xsd" workingxmlfile="C:\COEUS40_VSS\Reports\BudgetModification000842-001$01122005-065249$.xml">
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
							<template match="AwardNotice" matchtype="schemagraphitem">
								<children>
									<template match="Award" matchtype="schemagraphitem">
										<children>
											<template match="AwardAmountInfo" matchtype="schemagraphitem">
												<children>
													<newline/>
													<newline/>
													<newline/>
													<newline/>
													<newline/>
													<table>
														<properties border="0"/>
														<styles font-family="Times New Roman" font-size="8pt" line-height="9pt"/>
														<children>
															<tablebody>
																<children>
																	<template match="AmountInfo" matchtype="schemagraphitem">
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="left" colspan="4"/>
																						<styles padding-right="0"/>
																						<children>
																							<text fixtext="Seq. ">
																								<styles font-weight="bold"/>
																							</text>
																							<template match="AwardNumber" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=":"/>
																							<template match="AccountNumber" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext="        "/>
																							<template match="SequenceNumber" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="int"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext="."/>
																							<template match="AmountSequenceNumber" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="int"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-left="20pt"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-left="22pt" padding-right="0"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-left="24pt" padding-right="0"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-left="26pt" padding-right="0"/>
																					</tablecell>
																					<tablecell>
																						<styles padding-left="29pt"/>
																					</tablecell>
																					<tablecell>
																						<styles padding-left="33pt"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-left="40pt"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-left="40pt"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-left="23pt"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-right="0"/>
																						<children>
																							<newline/>
																							<template match="ObligatedChangeDirect" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<text fixtext="$"/>
																									<content>
																										<format datatype="decimal"/>
																									</content>
																								</children>
																							</template>
																							<newline/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-right="0"/>
																						<children>
																							<template match="ObligatedChangeIndirect" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<text fixtext="$"/>
																									<content>
																										<format datatype="decimal"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-right="0"/>
																						<children>
																							<template match="ObligatedChange" matchtype="schemagraphitem">
																								<children>
																									<text fixtext="$"/>
																									<content>
																										<format string="#,###,###,##0.00" datatype="decimal"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="AmtObligatedToDateModified = &quot;1&quot;">
																										<children>
																											<template match="AmtObligatedToDate" matchtype="schemagraphitem">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="string-length( . ) &lt;7">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath=".">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																															<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																															<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																															<conditionbranch xpath="string-length( . ) &gt; 12">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																														</children>
																													</condition>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<template match="AmtObligatedToDate" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,##0.00" datatype="decimal"/>
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
																						<properties align="left"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="ObligatedDistributableAmtModified = &quot;1&quot;">
																										<children>
																											<template match="ObligatedDistributableAmt" matchtype="schemagraphitem">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="string-length( . ) &lt;7">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath=".">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																															<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																															<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																															<conditionbranch xpath="string-length( . ) &gt; 12">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																														</children>
																													</condition>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<template match="ObligatedDistributableAmt" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,##0.00" datatype="decimal"/>
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
																						<properties align="left"/>
																						<styles padding-right="0"/>
																						<children>
																							<template match="AnticipatedChangeDirect" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<text fixtext="$"/>
																									<content>
																										<format datatype="decimal"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-right="0"/>
																						<children>
																							<template match="AnticipatedChangeIndirect" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<text fixtext="$"/>
																									<content>
																										<format datatype="decimal"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left"/>
																						<styles padding-right="0"/>
																						<children>
																							<template match="AnticipatedChange" matchtype="schemagraphitem">
																								<children>
																									<text fixtext="$"/>
																									<content>
																										<format string="#,###,###,##0.00" datatype="decimal"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<template match="AnticipatedTotalAmt" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="string-length( . ) &lt;7">
																												<children>
																													<text fixtext="$">
																														<styles text-decoration="underline"/>
																													</text>
																													<autocalc xpath=".">
																														<styles text-decoration="underline"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																												<children>
																													<text fixtext="$">
																														<styles text-decoration="underline"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																														<styles text-decoration="underline"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																												<children>
																													<text fixtext="$">
																														<styles text-decoration="underline"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles text-decoration="underline"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																											<conditionbranch xpath="string-length( . ) &gt; 12">
																												<children>
																													<text fixtext="$">
																														<styles text-decoration="underline"/>
																													</text>
																													<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																														<styles text-decoration="underline"/>
																													</autocalc>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="AnticipatedDistributableAmtModified  = &quot;1&quot;">
																										<children>
																											<template match="AnticipatedDistributableAmt" matchtype="schemagraphitem">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="string-length( . ) &lt;7">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath=".">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																															<conditionbranch xpath="string-length( . ) &gt;6 and  string-length( . ) &lt;10">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -6 )  , &quot;,&quot; ,   substring( . ,  string-length( . ) -5 )  )">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																															<conditionbranch xpath="string-length( . ) &gt;9 and  string-length( . ) &lt;13">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -9 )  , &quot;,&quot; ,  substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																															<conditionbranch xpath="string-length( . ) &gt; 12">
																																<children>
																																	<text fixtext="$">
																																		<styles text-decoration="underline"/>
																																	</text>
																																	<autocalc xpath="concat(  substring( . , 1 ,  string-length( . ) -12 )  , &quot;,&quot; ,  substring( . , string-length( . ) -11, 3 ) ,&quot;,&quot;, substring( . ,  string-length( . ) -8,3), &quot;,&quot;, substring( . ,  string-length( . ) -5 )  )">
																																		<styles text-decoration="underline"/>
																																	</autocalc>
																																</children>
																															</conditionbranch>
																														</children>
																													</condition>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<template match="AnticipatedDistributableAmt" matchtype="schemagraphitem">
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="#,###,###,##0.00" datatype="decimal"/>
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
																						<properties align="left"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="CurrentFundEffectiveDateModified  = &quot;1&quot;">
																										<children>
																											<template match="CurrentFundEffectiveDate" matchtype="schemagraphitem">
																												<children>
																													<autocalc xpath="concat( substring( . , 6 ,2 ) ,&quot;/&quot;, substring( . , 9 , 2 ),&quot;/&quot;,substring( . , 1 , 4 ))">
																														<styles text-decoration="underline"/>
																													</autocalc>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<template match="CurrentFundEffectiveDate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
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
																						<properties align="left"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="ObligationExpDateModified =&quot;1&quot;">
																										<children>
																											<template match="ObligationExpirationDate" matchtype="schemagraphitem">
																												<children>
																													<autocalc xpath="concat( substring( . , 6 ,2 ) ,&quot;/&quot;, substring( . , 9 , 2 ),&quot;/&quot;,substring( . , 1 , 4 ))">
																														<styles text-decoration="underline"/>
																													</autocalc>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<template match="ObligationExpirationDate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
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
																						<properties align="left"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="FinalExpDateModified = &quot;1&quot;">
																										<children>
																											<template match="FinalExpirationDate" matchtype="schemagraphitem">
																												<children>
																													<autocalc xpath="concat( substring( . , 6 ,2 ) ,&quot;/&quot;, substring( . , 9 , 2 ),&quot;/&quot;,substring( . , 1 , 4 ))">
																														<styles text-decoration="underline"/>
																													</autocalc>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<template match="FinalExpirationDate" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
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
											<newline/>
											<newline/>
											<template match="AwardTransactionInfo" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<table>
														<properties border="0" width="100%"/>
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<children>
																			<tablecell/>
																		</children>
																	</tablerow>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties height="20pt"/>
																				<children>
																					<text fixtext="Transaction Details:">
																						<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
																					</text>
																					<newline/>
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
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<properties align="left"/>
																		<styles cursor="crosshair" font-size="medium" font-weight="bold" width="auto"/>
																		<children>
																			<tablecell>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="Award Number"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="Transaction Type"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="Notice Date"/>
																				</children>
																			</tablecell>
																			<tablecell>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="Comments"/>
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
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties align="left" valign="top" width="100%"/>
																				<styles font-family="Verdana" font-size="9pt" padding="0" padding-bottom="0" padding-top="0"/>
																				<children>
																					<line>
																						<properties color="black" size="1"/>
																					</line>
																				</children>
																			</tablecell>
																		</children>
																	</tablerow>
																</children>
															</tablebody>
														</children>
													</table>
													<template match="TransactionInfo" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<newline/>
															<table>
																<properties align="left" border="0" width="100%"/>
																<styles font-family="tim" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<properties align="left"/>
																				<children>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<template match="AwardNumber" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<template match="TransactionTypeDesc" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																									<newline/>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<template match="NoticeDate" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="76%"/>
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
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<newline/>
															<newline/>
															<newline/>
															<newline/>
														</children>
													</template>
													<newline/>
												</children>
											</template>
											<newline/>
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
	<globalparts>
		<children>
			<globaltemplate match="AwardHeader" matchtype="schemagraphitem">
				<children>
					<template match="AwardHeader" matchtype="schemagraphitem">
						<children>
							<content/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</globalparts>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="landscape" paperheight="8.5in" papermarginbottom="0.62in" papermarginleft="0.05in" papermarginright="0.05in" papermargintop="1.5in" paperwidth="11in"/>
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
																<children>
																	<text fixtext="coeus: "/>
																	<template match="AwardNotice" matchtype="schemagraphitem">
																		<children>
																			<template match="PrintRequirement" matchtype="schemagraphitem">
																				<children>
																					<template match="CurrentDate" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<format string="MM/DD/YYYY" datatype="date"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																	<text fixtext=" "/>
																</children>
															</paragraph>
															<text fixtext="Changes noted by underline."/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="150"/>
														<styles font-family="Verdana" font-size="7pt" padding="0"/>
														<children>
															<text fixtext="Page "/>
															<field/>
															<text fixtext="                           "/>
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
														<properties colspan="2" height="8" width="1341"/>
														<styles padding="0" padding-bottom="0" padding-top="10pt"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="20%"/>
														<styles font-family="Verdana" font-size="9pt" padding-bottom="0"/>
														<children>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<children>
																	<template match="SchoolInfo" matchtype="schemagraphitem">
																		<children>
																			<template match="SchoolName" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-family="Verdana" font-size="13pt" font-weight="bold"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext="        "/>
															<newline/>
															<text fixtext="Budget Modification">
																<styles font-family="Verdana" font-size="10pt" font-style="italic" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="80%"/>
														<styles font-family="Verdana" font-size="9pt" padding-bottom="0" padding-right="30pt"/>
														<children>
															<text fixtext="          Transaction Date:">
																<styles font-weight="bold"/>
															</text>
															<text fixtext=" "/>
															<template match="AwardNotice" matchtype="schemagraphitem">
																<children>
																	<template match="Award" matchtype="schemagraphitem">
																		<children>
																			<template match="AwardAmountInfo" matchtype="schemagraphitem">
																				<children>
																					<template match="AmountInfo" matchtype="schemagraphitem">
																						<children>
																							<template match="TransactionDate" matchtype="schemagraphitem">
																								<children>
																									<content>
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
							<table>
								<properties border="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
												<children>
													<tablecell/>
													<tablecell/>
													<tablecell/>
													<tablecell>
														<properties colspan="2"/>
														<children>
															<text fixtext="Obligated Amount"/>
														</children>
													</tablecell>
													<tablecell/>
													<tablecell/>
													<tablecell/>
													<tablecell>
														<properties colspan="3"/>
														<children>
															<text fixtext="Anticipated Amount"/>
														</children>
													</tablecell>
													<tablecell/>
													<tablecell/>
												</children>
											</tablerow>
											<tablerow>
												<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
												<children>
													<tablecell>
														<children>
															<text fixtext="Direct"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Indirect"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="change"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Total"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Distributable"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Direct"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Indirect"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Change"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Total"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Distributble"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Oblg. Eff"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Oblg  Exp"/>
														</children>
													</tablecell>
													<tablecell>
														<children>
															<text fixtext="Final Exp."/>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<template match="$XML" matchtype="schemasource">
								<editorproperties elementstodisplay="5"/>
								<children>
									<template match="AwardNotice" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<template match="Award" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="AwardAmountInfo" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="AmountInfo" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</template>
								</children>
							</template>
							<line>
								<properties color="black" size="1"/>
							</line>
							<newline/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
