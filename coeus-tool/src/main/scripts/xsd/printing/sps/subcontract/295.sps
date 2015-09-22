<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="subcontractingReports.xsd" workingxmlfile="test.xml">
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
							<template match="SubcontractReports" matchtype="schemagraphitem">
								<children>
									<table>
										<properties border="0"/>
										<styles font-family="Times New Roman" font-size="8pt"/>
										<children>
											<tablebody>
												<children>
													<tablerow>
														<children>
															<tablecell>
																<children>
																	<template match="SubcontractReportPage" matchtype="schemagraphitem">
																		<children>
																			<table>
																				<properties border="1" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles border-style="none" margin="0" padding="0"/>
																										<children>
																											<table>
																												<properties border="1" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="center" width="80%"/>
																																		<styles border-top-width="2pt" padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<text fixtext="SUMMARY SUBCONTRACT REPORT">
																																				<styles font-size="11pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="20%"/>
																																		<styles border-top-width="2pt" padding-bottom="1pt" padding-top="1pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="OMB No.: "/>
																																					<text fixtext="9000-0007">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</paragraph>
																																			<text fixtext="Expires:    2/28/2010">
																																				<styles font-size="8pt"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="left" colspan="2" width="80%"/>
																																		<styles border-bottom-style="none" padding-bottom="0"/>
																																		<children>
																																			<text fixtext="Public reporting burden for this collection of information is estimated to average 16.2 hours per response, including the time for reviewing instructions,searching existing data sources, gathering and maintaining the data needed, and completing and reviewing the collection of information. Sendcomments regarding this burden estimate or any other aspect of this collection of information, including suggestions for reducing this burden, to theFAR Secretariat (VIR), Regulatory and Federal Assistance Division, GSA, Washington, DC 20405."/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles border-bottom-style="none" border-style="none" border-top-style="none" padding="0" padding-bottom="0"/>
																										<children>
																											<table>
																												<properties border="1" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="center" colspan="3" width="35%"/>
																																		<styles border-top-style="none" padding-top="1pt"/>
																																		<children>
																																			<text fixtext="1. CORPORATION, COMPANY OR SUBDIVISION COVERED"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties colspan="2" valign="top" width="30%"/>
																																		<styles border-bottom-width="0" border-top-style="none" padding-bottom="0" padding-top="1pt"/>
																																		<children>
																																			<text fixtext="3. DATE SUBMITTED"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="left" colspan="3" width="35%"/>
																																		<styles padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="a. COMPANY NAME"/>
																																				</children>
																																			</paragraph>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="CompanyInfo" matchtype="schemagraphitem">
																																								<children>
																																									<template match="NameAndAddressType" matchtype="schemagraphitem">
																																										<children>
																																											<template match="Name" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<styles font-weight="bold"/>
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
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties colspan="2" valign="top" width="30%"/>
																																		<styles border-top-style="none" font-weight="bold"/>
																																		<children>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="DateSubmitted" matchtype="schemagraphitem">
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
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties colspan="3" rowspan="2" width="35%"/>
																																		<styles padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="b. STREET ADDRESS">
																																						<styles padding-top="0"/>
																																					</text>
																																				</children>
																																			</paragraph>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="CompanyInfo" matchtype="schemagraphitem">
																																								<children>
																																									<template match="NameAndAddressType" matchtype="schemagraphitem">
																																										<children>
																																											<template match="StreetAddress" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<styles font-weight="bold"/>
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
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" colspan="2" valign="top" width="30%"/>
																																		<styles padding-top="5pt"/>
																																		<children>
																																			<text fixtext="4. REPORTING PERIOD:"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties rowspan="2" width="30%"/>
																																		<children>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="ReportingPeriod" matchtype="schemagraphitem">
																																								<children>
																																									<template match="isMarchReport" matchtype="schemagraphitem">
																																										<children>
																																											<checkbox checkedvalue="true" checkedvalue1="1">
																																												<children>
																																													<content>
																																														<format datatype="boolean"/>
																																													</content>
																																												</children>
																																											</checkbox>
																																											<text fixtext="  OCT 1-MAR 31 "/>
																																										</children>
																																									</template>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="ReportingPeriod" matchtype="schemagraphitem">
																																								<children>
																																									<template match="isSeptReport" matchtype="schemagraphitem">
																																										<children>
																																											<text fixtext="    "/>
																																											<checkbox checkedvalue="true" checkedvalue1="1">
																																												<children>
																																													<content>
																																														<format datatype="boolean"/>
																																													</content>
																																												</children>
																																											</checkbox>
																																											<text fixtext="  OCT 1-SEPT  30"/>
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
																																		<properties rowspan="2" width="15%"/>
																																		<styles padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<text fixtext="YEAR  "/>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="FiscalYearReportStart" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-weight="bold"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																			<text fixtext=" "/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties rowspan="2" valign="top" width="35%"/>
																																		<styles padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="c. CITY"/>
																																				</children>
																																			</paragraph>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="CompanyInfo" matchtype="schemagraphitem">
																																								<children>
																																									<template match="NameAndAddressType" matchtype="schemagraphitem">
																																										<children>
																																											<template match="City" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<styles font-weight="bold"/>
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
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties rowspan="2" valign="top" width="10%"/>
																																		<styles padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="d. STATE"/>
																																				</children>
																																			</paragraph>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="CompanyInfo" matchtype="schemagraphitem">
																																								<children>
																																									<template match="NameAndAddressType" matchtype="schemagraphitem">
																																										<children>
																																											<template match="State" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<styles font-weight="bold"/>
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
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties rowspan="2" valign="top" width="10%"/>
																																		<styles padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="e. ZIP CODE"/>
																																				</children>
																																			</paragraph>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="CompanyInfo" matchtype="schemagraphitem">
																																								<children>
																																									<template match="NameAndAddressType" matchtype="schemagraphitem">
																																										<children>
																																											<template match="ZipCode" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<styles font-weight="bold"/>
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
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="center" colspan="2" width="30%"/>
																																		<styles padding-bottom="0" padding-top="1pt"/>
																																		<children>
																																			<text fixtext="5. TYPE OF REPORT"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties colspan="3" valign="top" width="35%"/>
																																		<styles padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<text fixtext="2. CONTRACTOR IDENTIFICATION NUMBER"/>
																																				</children>
																																			</paragraph>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="ContractorIDNumber" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-weight="bold"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties colspan="2" valign="center" width="30%"/>
																																		<styles padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="ReportingPeriod" matchtype="schemagraphitem">
																																								<children>
																																									<template match="ReportType" matchtype="schemagraphitem">
																																										<children>
																																											<checkbox checkedvalue="REG" checkedvalue1="true" checkedvalue2="1">
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
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																			<text fixtext="  REGULAR                "/>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="ReportingPeriod" matchtype="schemagraphitem">
																																								<children>
																																									<template match="ReportType" matchtype="schemagraphitem">
																																										<children>
																																											<checkbox checkedvalue="FIN" checkedvalue1="true" checkedvalue2="1">
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
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																			<text fixtext="  FINAL               "/>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="ReportingPeriod" matchtype="schemagraphitem">
																																								<children>
																																									<template match="ReportType" matchtype="schemagraphitem">
																																										<children>
																																											<checkbox checkedvalue="REV" checkedvalue1="true" checkedvalue2="1">
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
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																			<text fixtext="  REVISED"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties height="1" width="35%"/>
																																		<styles border-bottom-style="none" border-style="none" height="0" margin="0" padding="0"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties height="1" width="10%"/>
																																		<styles border-bottom-style="none" border-style="none" height="0" margin="0" padding="0"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties height="1" width="10%"/>
																																		<styles border-bottom-style="none" border-style="none" height="0" margin="0" padding="0"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties height="1" width="30%"/>
																																		<styles border-bottom-style="none" border-style="none" height="0" margin="0" padding="0"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties height="1" width="15%"/>
																																		<styles border-bottom-style="none" border-style="none" height="0" margin="0" padding="0"/>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles border-bottom-style="none" border-style="none" border-top-style="none" padding="0"/>
																										<children>
																											<table>
																												<properties border="1" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="center" colspan="3" width="40%"/>
																																		<styles border-top-style="none" padding-top="0"/>
																																		<children>
																																			<text fixtext="6. ADMINISTERING ACTIVITY (Please check applicable box)"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<template match="AdministeringActivity" matchtype="schemagraphitem">
																																				<children>
																																					<checkbox checkedvalue="ARMY" checkedvalue1="true" checkedvalue2="1">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</checkbox>
																																					<text fixtext=" "/>
																																				</children>
																																			</template>
																																			<text fixtext="ARMY"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="35%"/>
																																		<children>
																																			<template match="AdministeringActivity" matchtype="schemagraphitem">
																																				<children>
																																					<checkbox checkedvalue="DEFENSE LOGISTICS AGENCY" checkedvalue1="true" checkedvalue2="1">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</checkbox>
																																					<text fixtext=" "/>
																																				</children>
																																			</template>
																																			<text fixtext="DEFENSE CONTRACT MANAGEMENT AGENCY"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="40%"/>
																																		<children>
																																			<template match="AdministeringActivity" matchtype="schemagraphitem">
																																				<children>
																																					<checkbox checkedvalue="DOE" checkedvalue1="true" checkedvalue2="1">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</checkbox>
																																					<text fixtext=" "/>
																																				</children>
																																			</template>
																																			<text fixtext="DOE"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<template match="AdministeringActivity" matchtype="schemagraphitem">
																																				<children>
																																					<checkbox checkedvalue="NAVY" checkedvalue1="true" checkedvalue2="1">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</checkbox>
																																					<text fixtext=" "/>
																																				</children>
																																			</template>
																																			<text fixtext="NAVY"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="35%"/>
																																		<children>
																																			<template match="AdministeringActivity" matchtype="schemagraphitem">
																																				<children>
																																					<checkbox checkedvalue="NASA" checkedvalue1="true" checkedvalue2="1">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</checkbox>
																																					<text fixtext=" "/>
																																				</children>
																																			</template>
																																			<text fixtext="NASA"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties rowspan="2" valign="top" width="40%"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<children>
																																					<template match="AdministeringActivity" matchtype="schemagraphitem">
																																						<children>
																																							<checkbox checkedvalue="OTHER" checkedvalue1="true" checkedvalue2="1">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</checkbox>
																																							<text fixtext=" "/>
																																						</children>
																																					</template>
																																					<text fixtext="OTHER FEDERAL AGENCY (Specify)"/>
																																				</children>
																																			</paragraph>
																																			<template match="Sponsor" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<styles font-weight="bold"/>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<template match="AdministeringActivity" matchtype="schemagraphitem">
																																				<children>
																																					<checkbox checkedvalue="AIR FORCE" checkedvalue1="true" checkedvalue2="1">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</checkbox>
																																					<text fixtext=" "/>
																																				</children>
																																			</template>
																																			<text fixtext="AIR FORCE "/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="35%"/>
																																		<children>
																																			<template match="AdministeringActivity" matchtype="schemagraphitem">
																																				<children>
																																					<checkbox checkedvalue="GSA" checkedvalue1="true" checkedvalue2="1">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</checkbox>
																																					<text fixtext=" "/>
																																				</children>
																																			</template>
																																			<text fixtext="GSA"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles border-bottom-style="none" border-style="none" border-top-style="none" padding="0"/>
																										<children>
																											<table>
																												<properties border="1" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="center" width="30%"/>
																																		<styles border-top-style="none" padding-top="1pt"/>
																																		<children>
																																			<text fixtext="7. REPORT SUBMITTED AS (Check one )"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" colspan="3" width="25%"/>
																																		<styles border-top-style="none" padding-top="1pt"/>
																																		<children>
																																			<text fixtext="8. TYPE OF PLAN"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="30%"/>
																																		<children>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="ContractorType" matchtype="schemagraphitem">
																																								<children>
																																									<template match="isPrime" matchtype="schemagraphitem">
																																										<children>
																																											<checkbox checkedvalue="true" checkedvalue1="1">
																																												<children>
																																													<content>
																																														<format datatype="boolean"/>
																																													</content>
																																												</children>
																																											</checkbox>
																																											<text fixtext="  "/>
																																										</children>
																																									</template>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																			<text fixtext="PRIME CONTRACTOR"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext=" "/>
																																			<text fixtext="__">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<text fixtext=" INDIVIDUAL"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties rowspan="3" width="30%"/>
																																		<children>
																																			<text fixtext="IF PLAN IS A COMMERCIAL PLAN, SPECIFY THEPERCENTAGE OF THE DOLLARS ON THIS REPORTATTRIBUTABLE TO THIS AGENCY.       "/>
																																			<text fixtext=" ">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<text fixtext="                              ===&gt;">
																																				<styles font-size="10pt" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties rowspan="3" width="15%"/>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="30%"/>
																																		<children>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="ContractorType" matchtype="schemagraphitem">
																																								<children>
																																									<template match="isSub" matchtype="schemagraphitem">
																																										<children>
																																											<checkbox checkedvalue="true" checkedvalue1="1">
																																												<children>
																																													<content>
																																														<format datatype="boolean"/>
																																													</content>
																																												</children>
																																											</checkbox>
																																											<text fixtext="  "/>
																																										</children>
																																									</template>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																			<text fixtext="SUBCONTRACTOR"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext=" "/>
																																			<text fixtext="__">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<text fixtext=" COMMERCIAL PRODUCTS"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="30%"/>
																																		<styles padding-left="2pt"/>
																																		<children>
																																			<text fixtext="__">
																																				<styles font-weight="bold"/>
																																			</text>
																																			<text fixtext="  BOTH"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="25%"/>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="center" colspan="4" width="30%"/>
																																		<children>
																																			<text fixtext="9. CONTRACTOR&apos;S MAJOR PRODUCTS OR SERVICE LINES"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties colspan="2" width="30%"/>
																																		<children>
																																			<text fixtext="a.  "/>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="ContractorProducts" matchtype="schemagraphitem">
																																								<children>
																																									<template match="ALine" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<styles font-weight="bold"/>
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
																																	</tablecell>
																																	<tablecell>
																																		<properties colspan="2" width="30%"/>
																																		<children>
																																			<text fixtext="b.  "/>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="ContractorProducts" matchtype="schemagraphitem">
																																								<children>
																																									<template match="BLine" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<styles font-weight="bold"/>
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
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles border-bottom-style="none" border-style="none" border-top-style="none" padding="0"/>
																										<children>
																											<table>
																												<properties border="1" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="center" colspan="4" width="5%"/>
																																		<styles border-top-style="none" padding="0" padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="CUMULATIVE FISCAL YEAR SUBCONTRACT AWARDS">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</paragraph>
																																			<text fixtext="(Report cumulative figures for reporting period in Block 4)">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="center" colspan="2" width="5%"/>
																																		<children>
																																			<text fixtext="TYPE"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="25%"/>
																																		<children>
																																			<text fixtext=" WHOLE DOLLARS"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" width="20%"/>
																																		<children>
																																			<text fixtext="PERCENT"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="5%"/>
																																		<styles border-right-style="none" padding-right="0"/>
																																		<children>
																																			<text fixtext="10a."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="SMALL BUSINESS CONCERNS (Include SDB, WOSB,HBCU/MI, HUBZone SB, and VOSB (IncludingService-Disabled VOSB)) (Dollar Amount and Percentof 10c)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="25%"/>
																																		<children>
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualAmount" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;SMALL BUSINESS&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<text fixtext="$"/>
																																															<content>
																																																<format string="#,###,###,##0.00" datatype="decimal"/>
																																															</content>
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
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualPercent" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;SMALL BUSINESS&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<content>
																																																<format string="##0.00" datatype="decimal"/>
																																															</content>
																																															<text fixtext="%"/>
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
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="5%"/>
																																		<styles border-right-style="none" padding-right="0"/>
																																		<children>
																																			<text fixtext="10b."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="LARGE BUSINESS CONCERNS (Dollar Amount andPercent of 10c.)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="25%"/>
																																		<children>
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualAmount" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;LARGE BUSINESS&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<text fixtext="$"/>
																																															<content>
																																																<format string="#,###,###,##0.00" datatype="decimal"/>
																																															</content>
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
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualPercent" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;LARGE BUSINESS&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<content>
																																																<format string="##0.00" datatype="decimal"/>
																																															</content>
																																															<text fixtext="%"/>
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
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="5%"/>
																																		<styles border-right-style="none"/>
																																		<children>
																																			<text fixtext="10c."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="TOTAL (Sum of 10a and 10b.)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="25%"/>
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="VendorType/ActualAmount[ ../TypeOfVendor =&apos;SMALL BUSINESS&apos;] !=0 and  VendorType/ActualAmount[../TypeOfVendor =&apos;LARGE BUSINESS&apos;]  != 0">
																																						<children>
																																							<text fixtext="$"/>
																																							<autocalc xpath="VendorType/ActualAmount[ ../TypeOfVendor =&apos;SMALL BUSINESS&apos;] +  VendorType/ActualAmount[../TypeOfVendor =&apos;LARGE BUSINESS&apos;]">
																																								<format string="#,###,###,##0.00" datatype="decimal"/>
																																							</autocalc>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="20%"/>
																																		<children>
																																			<text fixtext="100.00%"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="5%"/>
																																		<styles border-right-style="none"/>
																																		<children>
																																			<text fixtext="11."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="SMALL DISADVANTAGED BUSINESS (SDB) CONCERNS(Include HBCU/MI) (Dollar Amount and Percent of 10c.)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="25%"/>
																																		<children>
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualAmount" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;DISADVANTAGED BUSINESS&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<text fixtext="$"/>
																																															<content>
																																																<format string="#,###,###,##0.00" datatype="decimal"/>
																																															</content>
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
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualPercent" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;DISADVANTAGED BUSINESS&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<content>
																																																<format string="##0.00" datatype="decimal"/>
																																															</content>
																																															<text fixtext="%"/>
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
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="5%"/>
																																		<styles border-right-style="none"/>
																																		<children>
																																			<text fixtext="12."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="WOMEN-OWNED SMALL BUSINESS (WOSB) CONCERNS(Dollar Amount and Percent of 10c.)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="25%"/>
																																		<children>
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualAmount" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor = &apos;WOMAN OWNED&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<text fixtext="$"/>
																																															<content>
																																																<format string="#,###,###,##0.00" datatype="decimal"/>
																																															</content>
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
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualPercent" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor = &apos;WOMAN OWNED&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<content>
																																																<format string="##0.00" datatype="decimal"/>
																																															</content>
																																															<text fixtext="%"/>
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
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="5%"/>
																																		<styles border-right-style="none"/>
																																		<children>
																																			<text fixtext="13."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="HISTORICALLY BLACK COLLEGES AND UNIVERSITIES(HBCU) AND MINORITY INSTITUTIONS (MI) (If applicable)(Dollar Amount and Percent of 10c.)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="25%"/>
																																		<children>
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualAmount" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;HBCU&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<text fixtext="$"/>
																																															<content>
																																																<format string="#,###,###,##0.00" datatype="decimal"/>
																																															</content>
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
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualPercent" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;HBCU&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<content>
																																																<format string="##0.00" datatype="decimal"/>
																																															</content>
																																															<text fixtext="%"/>
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
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="5%"/>
																																		<styles border-right-style="none"/>
																																		<children>
																																			<text fixtext="14."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="HUBZone SMALL BUSINESS (HUBZone SB) CONCERNS(Dollar Amount and Percent of 10c."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="25%"/>
																																		<children>
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualAmount" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;HUB&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<text fixtext="$"/>
																																															<content>
																																																<format string="#,###,###,##0.00" datatype="decimal"/>
																																															</content>
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
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualPercent" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;HUB&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<content>
																																																<format string="##0.00" datatype="decimal"/>
																																															</content>
																																															<text fixtext="%"/>
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
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties height="5" valign="top" width="5%"/>
																																		<styles border-right-style="none"/>
																																		<children>
																																			<text fixtext="15."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties height="5" valign="top" width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="VETERAN-OWNED SMALL BUSINESS CONCERNS(Includng Service-Disabled Veteran-Owned SB Concerns)(Dollar Amount and Percent of 10c.)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" height="5" width="25%"/>
																																		<children>
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualAmount" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;VET&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<text fixtext="$"/>
																																															<content>
																																																<format string="#,###,###,##0.00" datatype="decimal"/>
																																															</content>
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
																																		<properties align="right" height="5" width="20%"/>
																																		<children>
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualPercent" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;VET&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<content>
																																																<format string="##0.00" datatype="decimal"/>
																																															</content>
																																															<text fixtext="%"/>
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
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="5%"/>
																																		<styles border-right-style="none"/>
																																		<children>
																																			<text fixtext="16."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="SERVICE-DISABLED VETERAN-OWNED SMALLBUSINESS CONCERNS (Dollar Amount and Percentof10c.)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="25%"/>
																																		<children>
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualAmount" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;SDVO&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<text fixtext="$"/>
																																															<content>
																																																<format string="#,###,###,##0.00" datatype="decimal"/>
																																															</content>
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
																																			<template match="VendorType" matchtype="schemagraphitem">
																																				<children>
																																					<template match="ActualPercent" matchtype="schemagraphitem">
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../TypeOfVendor =&apos;SDVO&apos;">
																																										<children>
																																											<condition>
																																												<children>
																																													<conditionbranch xpath=". != 0">
																																														<children>
																																															<content>
																																																<format string="##0.00" datatype="decimal"/>
																																															</content>
																																															<text fixtext="%"/>
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
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="5%"/>
																																		<styles border-right-style="none"/>
																																		<children>
																																			<text fixtext="17."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="ALASKA NATIVE CORPORATIONS (ANCs) AND INDIAN TRIBES THAT HAVE NOT BEEN CERTIFIED BY THE SMALL BUSINESS ADMINISTRATION AS SMALL DISADVANTAGED BUSINESSES (Dollar Amount)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="25%"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="20%"/>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="5%"/>
																																		<styles border-right-style="none"/>
																																		<children>
																																			<text fixtext="18."/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<styles border-left-style="none" padding-left="0"/>
																																		<children>
																																			<text fixtext="ALASKA NATIVE CORPORATIONS (ANCs) AND INDIAN TRIBES THAT ARE NOT SMALL BUSINESSES (Dollar Amount)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="25%"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="right" width="20%"/>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top"/>
																										<styles border-bottom-style="none" border-style="none" border-top-style="none" padding="0"/>
																										<children>
																											<table>
																												<properties border="1" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties align="center" colspan="4" valign="top" width="100%"/>
																																		<styles border-top-style="none" padding-bottom="0" padding-top="0"/>
																																		<children>
																																			<text fixtext="19. CONTRACTOR&apos;S OFFICIAL WHO ADMINISTERS SUBCONTRACTING PROGRAM"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties rowspan="2" valign="top" width="35%"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles padding-bottom="0" padding-top="1pt"/>
																																				<children>
																																					<text fixtext="a. NAME"/>
																																				</children>
																																			</paragraph>
																																			<text fixtext=" "/>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AdministeringOfficial" matchtype="schemagraphitem">
																																								<children>
																																									<template match="Name" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<styles font-weight="bold"/>
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
																																	</tablecell>
																																	<tablecell>
																																		<properties rowspan="2" valign="top" width="37%"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles padding-bottom="0" padding-top="1pt"/>
																																				<children>
																																					<text fixtext="b. TITLE"/>
																																				</children>
																																			</paragraph>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AdministeringOfficial" matchtype="schemagraphitem">
																																								<children>
																																									<template match="Title" matchtype="schemagraphitem">
																																										<children>
																																											<content>
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
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" colspan="2" width="28%"/>
																																		<styles padding-bottom="1pt" padding-top="1pt"/>
																																		<children>
																																			<text fixtext="c. TELEPHONE NUMBER"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="14%"/>
																																		<styles padding-bottom="0" padding-top="1pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles padding-bottom="0" padding-top="1pt"/>
																																				<children>
																																					<text fixtext="AREA CODE"/>
																																				</children>
																																			</paragraph>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AdministeringOfficial" matchtype="schemagraphitem">
																																								<children>
																																									<template match="PhoneAreaCode" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<styles font-weight="bold"/>
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
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="14%"/>
																																		<styles padding-bottom="0" padding-top="1pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles padding-bottom="0" padding-top="1pt"/>
																																				<children>
																																					<text fixtext="NUMBER"/>
																																				</children>
																																			</paragraph>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="AdministeringOfficial" matchtype="schemagraphitem">
																																								<children>
																																									<template match="PhoneNumber" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<styles font-weight="bold"/>
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
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties height="5" valign="top" width="35%"/>
																																		<styles border-style="none" height="0" margin="0" padding="0"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties height="5" valign="top" width="37%"/>
																																		<styles border-style="none" height="0" margin="0" padding="0"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" height="5" width="14%"/>
																																		<styles border-style="none" height="0" margin="0" padding="0"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center" height="5" width="14%"/>
																																		<styles border-style="none" height="0" margin="0" padding="0"/>
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
																											<text fixtext="20. REMARKS"/>
																											<newline/>
																											<newline/>
																											<table>
																												<properties align="left" border="0" width="100%"/>
																												<styles table-layout="fixed"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties height="541" valign="top"/>
																																		<children>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="SubcontractReportPage" matchtype="schemagraphitem">
																																								<children>
																																									<template match="Remarks" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<styles font-weight="bold"/>
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
																											<newline/>
																											<newline/>
																											<newline/>
																											<newline/>
																											<newline/>
																											<newline/>
																											<newline/>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top"/>
																										<styles border-bottom-style="none" border-style="none" border-top-style="none" padding="0"/>
																										<children>
																											<table>
																												<properties border="1" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<properties valign="bottom"/>
																																<children>
																																	<tablecell>
																																		<properties align="center" colspan="2" valign="top" width="60%"/>
																																		<styles border-left-width="0" border-right-width="0" border-top-style="solid" border-top-width="1" padding="0" padding-top="0"/>
																																		<children>
																																			<text fixtext="19. CHIEF EXECUTIVE OFFICER">
																																				<styles border-top-width="medium"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="60%"/>
																																		<styles border-left-style="none" border-left-width="0"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles border-left-style="none" border-left-width="0"/>
																																				<children>
																																					<text fixtext="a. NAME"/>
																																				</children>
																																			</paragraph>
																																			<text fixtext=" ">
																																				<styles border-left-width="0"/>
																																			</text>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="CEO" matchtype="schemagraphitem">
																																								<children>
																																									<template match="Name" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<styles font-weight="bold"/>
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
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="40%"/>
																																		<styles border-right-style="none" border-right-width="0"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles border-right-style="none" border-right-width="0"/>
																																				<children>
																																					<text fixtext="c. SIGNATURE"/>
																																				</children>
																																			</paragraph>
																																			<text fixtext="     "/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="60%"/>
																																		<styles border-bottom-width="2pt" border-left-style="none" border-left-width="0" padding-bottom="1pt" padding-top="1pt"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles border-bottom-width="2pt" border-left-style="none" border-left-width="0"/>
																																				<children>
																																					<text fixtext="b. TITLE"/>
																																				</children>
																																			</paragraph>
																																			<text fixtext=" "/>
																																			<template match="$XML" matchtype="schemasource">
																																				<children>
																																					<template match="SubcontractReports" matchtype="schemagraphitem">
																																						<children>
																																							<template match="CEO" matchtype="schemagraphitem">
																																								<children>
																																									<template match="Title" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<styles font-weight="bold"/>
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
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="40%"/>
																																		<styles border-bottom-width="2pt" border-right-style="none" border-right-width="0"/>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles border-bottom-width="2pt" border-right-style="none" border-right-width="0"/>
																																				<children>
																																					<text fixtext="d. DATE"/>
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
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																			<newline break="page"/>
																			<newline/>
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
							<newline/>
							<newline/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="0" paperheight="11in" papermarginbottom="0.45in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="0.25in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#footereven" matchtype="named" parttype="footereven">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties align="right" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" colspan="2" height="20"/>
														<styles padding="0"/>
														<children>
															<text fixtext="                                                      ">
																<styles font-size="7pt"/>
															</text>
															<text fixtext="  STANDARD FORM 295">
																<styles font-size="7pt" font-weight="bold"/>
															</text>
															<text fixtext=" (REV. 9/2007) ">
																<styles font-size="7pt"/>
															</text>
															<text fixtext="PAGE 2">
																<styles font-size="7pt" font-weight="bold"/>
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
														<properties align="left"/>
														<styles font-size="smaller" margin-top="0" padding="0"/>
														<children>
															<text fixtext="AUTHORIZED FOR LOCAL REPRODUCTION">
																<styles font-size="xx-small"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles font-size="smaller" padding="0"/>
														<children>
															<text fixtext="STANDARD FORM 295">
																<styles font-size="xx-small" font-weight="bold"/>
															</text>
															<text fixtext=" (REV. 9/2007)">
																<styles font-size="xx-small"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left"/>
														<styles font-size="smaller" padding="0"/>
														<children>
															<text fixtext="PREVIOUS EDITION IS NOT USABLE">
																<styles font-size="xx-small"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles font-size="smaller" padding="0"/>
														<children>
															<text fixtext="Prescribed by GSA-FAR (48 CFR) 53.219(b)">
																<styles font-size="xx-small"/>
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
