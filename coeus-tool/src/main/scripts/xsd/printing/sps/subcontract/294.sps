<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="subcontractingReports.xsd" workingxmlfile="test.xml" templatexmlfile="" xsltversion="1" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<styles font-family="Times New Roman" font-size="8pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties border="0" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles margin="0" padding="0"/>
										<children>
											<table>
												<properties border="1" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-top-width="2pt" padding-bottom="0" padding-top="0"/>
																		<properties align="center" width="80%"/>
																		<children>
																			<text fixtext="SUBCONTRACTING REPORT FOR INDIVIDUAL CONTRACTS">
																				<styles font-size="11pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-top-width="2pt" padding-bottom="1pt" padding-top="1pt"/>
																		<properties width="20%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="OMB No.: "/>
																					<text fixtext="9000-0006">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</paragraph>
																			<text fixtext="Expires:    11/30/2007">
																				<styles font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-bottom-style="none" padding-bottom="0"/>
																		<properties align="left" colspan="2" width="80%"/>
																		<children>
																			<text fixtext="Public reporting burden for this collection of information is estimated to average 55.34 hours per response, including the time for
reviewing instructions, searching existing data sources, gathering and maintaining the data needed, and completing and reviewing
the collection of information. Send comments regarding this burden estimate or any other aspect of this collection of information,
including suggestions for reducing this burden, to the FAR Secretariat (VIR), Regulatory and Federal Assistance Division, GSA, Washington, DC
20405."/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-top-style="none" padding="0" padding-bottom="0"/>
										<children>
											<table>
												<properties border="1" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-top-style="none" padding-top="1pt"/>
																		<properties align="center" colspan="3" width="35%"/>
																		<children>
																			<text fixtext="1. CORPORATION, COMPANY OR SUBDIVISION COVERED"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-bottom-width="0" border-top-style="none" padding-bottom="0" padding-top="1pt"/>
																		<properties colspan="2" valign="top" width="30%"/>
																		<children>
																			<text fixtext="3. DATE SUBMITTED"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties align="left" colspan="3" width="35%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="a. COMPANY NAME"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="CompanyInfo"/>
																						<children>
																							<template>
																								<match match="NameAndAddressType"/>
																								<children>
																									<template>
																										<match match="Name"/>
																										<children>
																											<xpath allchildren="1">
																												<styles font-weight="bold"/>
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
																	</tablecol>
																	<tablecol>
																		<styles border-top-style="none" font-weight="bold"/>
																		<properties colspan="2" valign="top" width="30%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="DateSubmitted"/>
																						<children>
																							<xpath allchildren="1">
																								<styles font-weight="bold"/>
																								<format string="MM/DD/YYYY" xslt="1"/>
																							</xpath>
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
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties colspan="3" rowspan="2" width="35%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="b. STREET ADDRESS">
																						<styles padding-top="0"/>
																					</text>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="CompanyInfo"/>
																						<children>
																							<template>
																								<match match="NameAndAddressType"/>
																								<children>
																									<template>
																										<match match="StreetAddress"/>
																										<children>
																											<xpath allchildren="1">
																												<styles font-weight="bold"/>
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
																	</tablecol>
																	<tablecol>
																		<styles padding-top="5pt"/>
																		<properties align="center" colspan="2" valign="top" width="30%"/>
																		<children>
																			<text fixtext="4. REPORTING PERIOD FROM INCEPTION OF CONTRACT THRU:"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties rowspan="2" width="30%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="ReportingPeriod"/>
																						<children>
																							<template>
																								<match match="isMarchReport"/>
																								<children>
																									<checkbox ownvalue="1">
																										<properties type="checkbox"/>
																									</checkbox>
																									<text fixtext="  MAR 31 "/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="ReportingPeriod"/>
																						<children>
																							<template>
																								<match match="isSeptReport"/>
																								<children>
																									<text fixtext="                    "/>
																									<checkbox ownvalue="1">
																										<properties type="checkbox"/>
																									</checkbox>
																									<text fixtext="  SEPT  30"/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties rowspan="2" width="15%"/>
																		<children>
																			<text fixtext="YEAR  "/>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="FiscalYearReportStart"/>
																						<children>
																							<xpath allchildren="1">
																								<styles font-weight="bold"/>
																							</xpath>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext=" "/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties rowspan="2" valign="top" width="35%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="c. CITY"/>
																				</children>
																			</paragraph>
																			<text fixtext=" "/>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="CompanyInfo"/>
																						<children>
																							<template>
																								<match match="NameAndAddressType"/>
																								<children>
																									<template>
																										<match match="City"/>
																										<children>
																											<xpath allchildren="1">
																												<styles font-weight="bold"/>
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
																	</tablecol>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties rowspan="2" valign="top" width="10%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="d. STATE"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="CompanyInfo"/>
																						<children>
																							<template>
																								<match match="NameAndAddressType"/>
																								<children>
																									<template>
																										<match match="State"/>
																										<children>
																											<xpath allchildren="1">
																												<styles font-weight="bold"/>
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
																	</tablecol>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties rowspan="2" valign="top" width="10%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="e. ZIP CODE"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="CompanyInfo"/>
																						<children>
																							<template>
																								<match match="NameAndAddressType"/>
																								<children>
																									<template>
																										<match match="ZipCode"/>
																										<children>
																											<xpath allchildren="1">
																												<styles font-weight="bold"/>
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
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="1pt"/>
																		<properties align="center" colspan="2" width="30%"/>
																		<children>
																			<text fixtext="5. TYPE OF REPORT"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties colspan="3" valign="top" width="35%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="2. CONTRACTOR IDENTIFICATION NUMBER"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="ContractorIDNumber"/>
																						<children>
																							<xpath allchildren="1">
																								<styles font-weight="bold"/>
																							</xpath>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties colspan="2" valign="center" width="30%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="ReportingPeriod"/>
																						<children>
																							<template>
																								<match match="ReportType"/>
																								<children>
																									<checkbox ownvalue="1" checkedvalue="REG" checkedvalue1="true" checkedvalue2="1">
																										<properties type="checkbox"/>
																									</checkbox>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="  REGULAR                "/>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="ReportingPeriod"/>
																						<children>
																							<template>
																								<match match="ReportType"/>
																								<children>
																									<checkbox ownvalue="1" checkedvalue="FIN" checkedvalue1="true" checkedvalue2="1">
																										<properties type="checkbox"/>
																									</checkbox>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="  FINAL               "/>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="ReportingPeriod"/>
																						<children>
																							<template>
																								<match match="ReportType"/>
																								<children>
																									<checkbox ownvalue="1" checkedvalue="REV" checkedvalue1="true" checkedvalue2="1">
																										<properties type="checkbox"/>
																									</checkbox>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="  REVISED"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-bottom-style="none" border-style="none" height="0" margin="0" padding="0"/>
																		<properties height="1" width="35%"/>
																	</tablecol>
																	<tablecol>
																		<styles border-bottom-style="none" border-style="none" height="0" margin="0" padding="0"/>
																		<properties height="1" width="10%"/>
																	</tablecol>
																	<tablecol>
																		<styles border-bottom-style="none" border-style="none" height="0" margin="0" padding="0"/>
																		<properties height="1" width="10%"/>
																	</tablecol>
																	<tablecol>
																		<styles border-bottom-style="none" border-style="none" height="0" margin="0" padding="0"/>
																		<properties height="1" width="30%"/>
																	</tablecol>
																	<tablecol>
																		<styles border-bottom-style="none" border-style="none" height="0" margin="0" padding="0"/>
																		<properties height="1" width="15%"/>
																	</tablecol>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-top-style="none" padding="0"/>
										<children>
											<table>
												<properties border="1" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-top-style="none" padding-top="0"/>
																		<properties align="center" colspan="3" width="40%"/>
																		<children>
																			<text fixtext="6. ADMINISTERING ACTIVITY (Please check applicable box)"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties width="25%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AdministeringActivity"/>
																								<children>
																									<checkbox ownvalue="1" checkedvalue="ARMY" checkedvalue1="true" checkedvalue2="1">
																										<properties type="checkbox"/>
																									</checkbox>
																									<text fixtext=" "/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="ARMY"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="40%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AdministeringActivity"/>
																								<children>
																									<checkbox ownvalue="1" checkedvalue="GSA" checkedvalue1="true" checkedvalue2="1">
																										<properties type="checkbox"/>
																									</checkbox>
																									<text fixtext=" "/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="GSA"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="35%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AdministeringActivity"/>
																								<children>
																									<checkbox ownvalue="1" checkedvalue="NASA" checkedvalue1="true" checkedvalue2="1">
																										<properties type="checkbox"/>
																									</checkbox>
																									<text fixtext=" "/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="NASA"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties width="25%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AdministeringActivity"/>
																								<children>
																									<checkbox ownvalue="1" checkedvalue="NAVY" checkedvalue1="true" checkedvalue2="1">
																										<properties type="checkbox"/>
																									</checkbox>
																									<text fixtext=" "/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="NAVY"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="40%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AdministeringActivity"/>
																								<children>
																									<checkbox ownvalue="1" checkedvalue="DOE" checkedvalue1="true" checkedvalue2="1">
																										<properties type="checkbox"/>
																									</checkbox>
																									<text fixtext=" "/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="DOE"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties rowspan="2" valign="top" width="35%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<template>
																						<match match="SubcontractReports"/>
																						<children>
																							<template>
																								<match match="SubcontractReportPage"/>
																								<children>
																									<template>
																										<match match="AdministeringActivity"/>
																										<children>
																											<checkbox ownvalue="1" checkedvalue="OTHER" checkedvalue1="true" checkedvalue2="1">
																												<properties type="checkbox"/>
																											</checkbox>
																											<text fixtext=" "/>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																					<text fixtext="OTHER FEDERAL AGENCY (Specify)"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="Sponsor"/>
																								<children>
																									<xpath allchildren="1"/>
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
																		<properties width="25%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AdministeringActivity"/>
																								<children>
																									<checkbox ownvalue="1" checkedvalue="AIR FORCE" checkedvalue1="true" checkedvalue2="1">
																										<properties type="checkbox"/>
																									</checkbox>
																									<text fixtext=" "/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="AIR FORCE "/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="40%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AdministeringActivity"/>
																								<children>
																									<checkbox ownvalue="1" checkedvalue="DEFENSE LOGISTICS AGENCY" checkedvalue1="true" checkedvalue2="1">
																										<properties type="checkbox"/>
																									</checkbox>
																									<text fixtext=" "/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="DEFENSE CONTRACT MANAGEMENT AGENCY"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-top-style="none" padding="0"/>
										<children>
											<table>
												<properties border="1" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-top-style="none" padding-top="0"/>
																		<properties align="center" width="30%"/>
																		<children>
																			<text fixtext="7. REPORT SUBMITTED AS (Check one)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-top-style="none" padding-top="0"/>
																		<properties align="center" colspan="4" width="22%"/>
																		<children>
																			<text fixtext="8. AGENCY OR CONTRACTOR AWARDING CONTRACT"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties rowspan="2" width="30%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<template>
																						<match match="SubcontractReports"/>
																						<children>
																							<template>
																								<match match="ContractorType"/>
																								<children>
																									<template>
																										<match match="isPrime"/>
																										<children>
																											<checkbox ownvalue="1">
																												<properties type="checkbox"/>
																											</checkbox>
																											<text fixtext="  "/>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																					<text fixtext="PRIME CONTRACTOR"/>
																					<newline/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="ContractorType"/>
																						<children>
																							<template>
																								<match match="isSub"/>
																								<children>
																									<checkbox ownvalue="1">
																										<properties type="checkbox"/>
																									</checkbox>
																									<text fixtext="  "/>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="SUBCONTRACTOR"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties width="28%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="PRIME CONTRACT NUMBER"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="ContractorType"/>
																						<children>
																							<template>
																								<match match="PrimeContractNumber"/>
																								<children>
																									<xpath allchildren="1">
																										<styles font-weight="bold"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties colspan="3" valign="top" width="22%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="a. AGENCY'S OR CONTRACTOR'S NAME"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AwardingAgencyName"/>
																								<children>
																									<template>
																										<match match="NameAndAddressType"/>
																										<children>
																											<template>
																												<match match="Name"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-weight="bold"/>
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
																		<properties valign="top" width="28%"/>
																		<children>
																			<text fixtext="SUBCONTRACT NUMBER"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties colspan="3" width="22%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="b. STREET ADDRESS"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AwardingAgencyName"/>
																								<children>
																									<template>
																										<match match="NameAndAddressType"/>
																										<children>
																											<template>
																												<match match="StreetAddress"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-weight="bold"/>
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
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties colspan="2" valign="top" width="28%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="9. DOLLARS AND PERCENTAGES IN THE FOLLOWING BLOCKS: "/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="IncludeIndirectCosts"/>
																						<children>
																							<checkbox ownvalue="1">
																								<properties type="checkbox"/>
																							</checkbox>
																							<text fixtext="  "/>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="DO INCLUDE INDIRECT COSTS          "/>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="IncludeIndirectCosts"/>
																						<children>
																							<checkbox ownvalue="1" checkedvalue="false" checkedvalue1="1" uncheckedvalue="true">
																								<properties type="checkbox"/>
																							</checkbox>
																							<text fixtext="  "/>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="DO NOT INCLUDE INDIRECT COSTS"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties valign="top" width="22%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="c. CITY "/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AwardingAgencyName"/>
																								<children>
																									<template>
																										<match match="NameAndAddressType"/>
																										<children>
																											<template>
																												<match match="City"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-weight="bold"/>
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
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties valign="top" width="10%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="d. STATE "/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AwardingAgencyName"/>
																								<children>
																									<template>
																										<match match="NameAndAddressType"/>
																										<children>
																											<template>
																												<match match="State"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-weight="bold"/>
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
																	<tablecol>
																		<styles padding-bottom="0" padding-top="0"/>
																		<properties valign="top" width="10%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="e. ZIP CODE"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="AwardingAgencyName"/>
																								<children>
																									<template>
																										<match match="NameAndAddressType"/>
																										<children>
																											<template>
																												<match match="ZipCode"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-weight="bold"/>
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
																		<styles border-style="none" height="0" padding="0"/>
																		<properties height="1" width="30%"/>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none" height="0" padding="0"/>
																		<properties height="1" width="28%"/>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none" height="0" padding="0"/>
																		<properties height="1" width="22%"/>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none" height="0" padding="0"/>
																		<properties height="1" width="10%"/>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none" height="0" padding="0"/>
																		<properties height="1" width="10%"/>
																	</tablecol>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-top-style="none" padding="0"/>
										<children>
											<table>
												<properties border="1" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-top-style="none"/>
																		<properties align="center" colspan="6" width="5%"/>
																		<children>
																			<text fixtext="SUBCONTRACT AWARDS">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties align="center" colspan="2" rowspan="2" width="5%"/>
																		<children>
																			<text fixtext="TYPE"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="center" colspan="2" width="18%"/>
																		<children>
																			<text fixtext="CURRENT GOAL"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="center" colspan="2" width="18%"/>
																		<children>
																			<text fixtext="ACTUAL CUMULATIVE"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties align="center" width="18%"/>
																		<children>
																			<text fixtext="WHOLE DOLLARS"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="center" width="9.5%"/>
																		<children>
																			<text fixtext="PERCENT"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="center" width="18%"/>
																		<children>
																			<text fixtext="WHOLE DOLLARS"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="center" width="9.5%"/>
																		<children>
																			<text fixtext="PERCENT"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none" padding-right="0"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<text fixtext="10a."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="SMALL BUSINESS CONCERNS (Include SDB, WOSB,
HBCU/MI, HUBZone SB, and VOSB (Including
Service-Disabled VOSB)) (Dollar Amount and Percent
of 10c)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;SMALL BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;SMALL BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;SMALL BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;SMALL BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none" padding-right="0"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<text fixtext="10b."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="LARGE BUSINESS CONCERNS (Dollar Amount and
Percent of 10c.)
"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;LARGE BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<styles background-color="gray"/>
																		<properties width="9.5%"/>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;LARGE BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;LARGE BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<text fixtext="10c."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="TOTAL (Sum of 10a and 10b.)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="SubcontractReports/SubcontractReportPage/VendorType/GoalAmount[ ../TypeOfVendor =&apos;SMALL BUSINESS&apos;] !=0 and  
SubcontractReports/SubcontractReportPage/VendorType/GoalAmount[../TypeOfVendor =&apos;LARGE BUSINESS&apos;]  != 0"/>
																						</testexpression>
																						<children>
																							<text fixtext="$"/>
																							<autovalue>
																								<editorproperties editable="0"/>
																								<autocalc>
																									<xpath value="SubcontractReports/SubcontractReportPage/VendorType/GoalAmount[ ../TypeOfVendor =&apos;SMALL BUSINESS&apos;] +  
SubcontractReports/SubcontractReportPage/VendorType/GoalAmount[../TypeOfVendor =&apos;LARGE BUSINESS&apos;]"/>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<text fixtext="100.00%"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="SubcontractReports/SubcontractReportPage/VendorType/ActualAmount[ ../TypeOfVendor =&apos;SMALL BUSINESS&apos;] !=0 and 
SubcontractReports/SubcontractReportPage/VendorType/ActualAmount[../TypeOfVendor =&apos;LARGE BUSINESS&apos;] != 0"/>
																						</testexpression>
																						<children>
																							<text fixtext="$"/>
																							<autovalue>
																								<editorproperties editable="0"/>
																								<autocalc>
																									<xpath value="SubcontractReports/SubcontractReportPage/VendorType/ActualAmount[ ../TypeOfVendor =&apos;SMALL BUSINESS&apos;] +  
SubcontractReports/SubcontractReportPage/VendorType/ActualAmount[../TypeOfVendor =&apos;LARGE BUSINESS&apos;]"/>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<text fixtext="100.00%"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<text fixtext="11."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="SMALL DISADVANTAGED BUSINESS (SDB) CONCERNS
 (Dollar Amount and Percent of 10c.)
"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;DISADVANTAGED BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;DISADVANTAGED BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;DISADVANTAGED BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;DISADVANTAGED BUSINESS&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<text fixtext="12."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="WOMEN-OWNED SMALL BUSINESS (WOSB) CONCERNS
(Dollar Amount and Percent of 10c.)
"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;WOMAN OWNED&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =  &apos;WOMAN OWNED&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;WOMAN OWNED&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;WOMAN OWNED&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<text fixtext="13."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="HISTORICALLY BLACK COLLEGES AND UNIVERSITIES
(HBCU) AND MINORITY INSTITUTIONS (MI) (If applicable)
(Dollar Amount and Percent of 10c.)
"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;HBCU&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;HBCU&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;HBCU&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;HBCU&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<text fixtext="14."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="HUBZone SMALL BUSINESS (HUBZone SB) CONCERNS
(Dollar Amount and Percent of 10c.
"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;HUB&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;HUB&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;HUB&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;HUB&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none"/>
																		<properties height="5" valign="top" width="5%"/>
																		<children>
																			<text fixtext="15."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties height="5" valign="top" width="40%"/>
																		<children>
																			<text fixtext="VETERAN-OWNED SMALL BUSINESS CONCERNS

(Dollar Amount and Percent of 10c.)
"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" height="5" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;VET&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" height="5" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;VET&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" height="5" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;VET&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" height="5" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;VET&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<text fixtext="16."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="SERVICE-DISABLED VETERAN-OWNED SMALL
BUSINESS CONCERNS (Dollar Amount and Percent
of10c.)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;SDVO&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="GoalPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor = &apos;SDVO&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="18%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualAmount"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;SDVO&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<text fixtext="$"/>
																																			<xpath allchildren="1">
																																				<format string="#,###,###,##0.00" xslt="1"/>
																																			</xpath>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																		<properties align="right" width="9.5%"/>
																		<children>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="VendorType"/>
																								<children>
																									<template>
																										<match match="ActualPercent"/>
																										<children>
																											<choice>
																												<children>
																													<choiceoption>
																														<testexpression>
																															<xpath value="../TypeOfVendor =&apos;SDVO&apos;"/>
																														</testexpression>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value=". != 0"/>
																																		</testexpression>
																																		<children>
																																			<xpath allchildren="1">
																																				<format string="##0.00" xslt="1"/>
																																			</xpath>
																																			<text fixtext="%"/>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<text fixtext="17."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="ALASKA NATIVE CORPORATIONS (ANCs) AND INDIAN TRIBES THAT HAVE NOT BEEN CERTIFIED BY THE SMALL BUSINESS ADMINISTRATION AS SMALL DISADVANTAGED BUSINESSES (Dollar Amount)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles background-color="gray"/>
																		<properties align="right" width="18%"/>
																	</tablecol>
																	<tablecol>
																		<styles background-color="gray"/>
																		<properties align="right" width="9.5%"/>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																	</tablecol>
																	<tablecol>
																		<styles background-color="gray"/>
																		<properties align="right" width="9.5%"/>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<text fixtext="18."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-left="0"/>
																		<properties width="40%"/>
																		<children>
																			<text fixtext="
ALASKA NATIVE CORPORATIONS (ANCs) AND INDIAN TRIBES THAT ARE NOT SMALL BUSINESSES (Dollar Amount)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles background-color="gray"/>
																		<properties align="right" width="18%"/>
																	</tablecol>
																	<tablecol>
																		<styles background-color="gray"/>
																		<properties align="right" width="9.5%"/>
																	</tablecol>
																	<tablecol>
																		<properties align="right" width="18%"/>
																	</tablecol>
																	<tablecol>
																		<styles background-color="gray"/>
																		<properties align="right" width="9.5%"/>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-bottom-style="none" border-bottom-width="0" border-left-style="none" border-left-width="0" border-right-style="none" border-right-width="0" border-style="none" border-width="0" padding-bottom="1pt" padding-top="2pt"/>
																		<properties valign="top" width="5%"/>
																		<children>
																			<newline withpagebreak="1">
																				<styles page-break-after="always"/>
																			</newline>
																			<newline/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-bottom-style="none" border-bottom-width="0" border-left-style="none" border-left-width="0" border-right-style="none" border-right-width="0" border-style="none" padding-bottom="1pt" padding-left="0" padding-top="2pt"/>
																		<properties colspan="5" valign="top" width="40%"/>
																		<children>
																			<newline/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-right-style="none" padding-bottom="1pt" padding-top="2pt"/>
																		<properties height="525" valign="top" width="5%"/>
																		<children>
																			<text fixtext="19."/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-left-style="none" padding-bottom="1pt" padding-left="0" padding-top="2pt"/>
																		<properties colspan="5" height="525" valign="top" width="40%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="REMARKS"/>
																				</children>
																			</paragraph>
																			<text fixtext="  "/>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="SubcontractReportPage"/>
																						<children>
																							<template>
																								<match match="Remarks"/>
																								<children>
																									<xpath allchildren="1">
																										<styles font-weight="bold"/>
																									</xpath>
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
																		<styles border-bottom-width="2pt" padding-bottom="0" padding-top="0"/>
																		<properties colspan="3" rowspan="2" valign="top" width="5%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles border-bottom-width="2pt" padding-bottom="0"/>
																				<children>
																					<text fixtext="20a.NAME OF INDIVIDUAL ADMINISTERING SUBCONTRACTING PLAN"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="AdministeringOfficial"/>
																						<children>
																							<template>
																								<match match="Name"/>
																								<children>
																									<xpath allchildren="1">
																										<styles font-weight="bold"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-bottom="1pt" padding-top="1pt"/>
																		<properties align="center" colspan="3" width="9.5%"/>
																		<children>
																			<text fixtext="20b. TELEPHONE NUMBER"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-bottom-width="2pt" padding-bottom="0" padding-top="0"/>
																		<properties width="9.5%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles border-bottom-width="2pt" padding-bottom="0"/>
																				<children>
																					<text fixtext="AREA CODE"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="AdministeringOfficial"/>
																						<children>
																							<template>
																								<match match="PhoneAreaCode"/>
																								<children>
																									<xpath allchildren="1">
																										<styles font-weight="bold"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-bottom-width="2pt" padding-bottom="0" padding-top="0"/>
																		<properties colspan="2" valign="top" width="18%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles border-bottom-width="2pt" padding-bottom="0"/>
																				<children>
																					<text fixtext="NUMBER"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="SubcontractReports"/>
																				<children>
																					<template>
																						<match match="AdministeringOfficial"/>
																						<children>
																							<template>
																								<match match="PhoneNumber"/>
																								<children>
																									<xpath allchildren="1">
																										<styles font-weight="bold"/>
																									</xpath>
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
														</children>
													</tablebody>
												</children>
											</table>
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
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.5in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="0.5in" paperwidth="8.5in"/>
		<footerall>
			<template>
				<match overwrittenxslmatch="/"/>
				<children>
					<newline/>
					<table topdown="0">
						<properties border="0" width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecol>
												<styles padding="0"/>
												<properties colspan="2"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" line-height="5pt" padding="0"/>
												<properties align="left" valign="top"/>
												<children>
													<paragraph paragraphtag="p">
														<styles line-height="5pt"/>
														<children>
															<text fixtext="AUTHORIZED FOR LOCAL REPRODUCTION"/>
														</children>
													</paragraph>
													<text fixtext="Previous edition is not usable"/>
													<text fixtext=" "/>
												</children>
											</tablecol>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" line-height="5pt" padding="0"/>
												<properties align="right"/>
												<children>
													<paragraph paragraphtag="p">
														<styles line-height="5pt"/>
														<children>
															<text fixtext="STANDARD FORM 294 ">
																<styles font-size="9pt" font-weight="bold"/>
															</text>
															<text fixtext="(REV. 9/2007)  "/>
														</children>
													</paragraph>
													<text fixtext="Prescribed by GSA-FAR (48 CFR) 53.219(a)  "/>
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
		<headerodd>
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
												<properties colspan="2" height="30"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles font-size="smaller" padding="0"/>
												<properties align="left"/>
											</tablecol>
											<tablecol>
												<styles font-size="smaller" padding="0"/>
												<properties align="right" width="150"/>
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
								</children>
							</tablebody>
						</children>
					</table>
				</children>
			</template>
		</headerodd>
	</pagelayout>
</structure>
