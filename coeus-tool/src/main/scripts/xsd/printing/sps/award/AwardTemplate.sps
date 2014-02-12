<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="template.xsd" workingxmlfile="AwardTemplate1.xml">
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
							<template match="template" matchtype="schemagraphitem">
								<children>
									<template match="schoolInfo" matchtype="schemagraphitem">
										<children>
											<paragraph paragraphtag="center">
												<children>
													<paragraph paragraphtag="h2">
														<properties align="center"/>
														<styles padding-bottom="0"/>
														<children>
															<template match="SchoolName" matchtype="schemagraphitem">
																<children>
																	<content>
																		<styles font-family="Arial" font-weight="bold"/>
																		<format datatype="string"/>
																	</content>
																</children>
															</template>
															<newline/>
														</children>
													</paragraph>
												</children>
											</paragraph>
											<paragraph paragraphtag="center">
												<styles font-family="Arial" padding-bottom="0"/>
												<children>
													<text fixtext="Award Template Report">
														<styles font-size="14pt" font-weight="bold"/>
													</text>
												</children>
											</paragraph>
										</children>
									</template>
								</children>
							</template>
							<paragraph paragraphtag="p"/>
							<table>
								<properties border="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties width="20%"/>
														<styles font-family="Arial" font-size="10pt"/>
														<children>
															<text fixtext="Template Code:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="30%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="templateCode" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<format datatype="int"/>
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
														<properties align="right" width="40"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
														<children>
															<text fixtext="Status:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="25%"/>
														<styles font-family="Arial" font-size="9pt"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="templateStatus" matchtype="schemagraphitem">
																				<children>
																					<template match="statusDesc" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="20%"/>
														<styles font-family="Arial" font-size="10pt"/>
														<children>
															<text fixtext="Description:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" width="30%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="template" matchtype="schemagraphitem">
																		<children>
																			<template match="templateMaster" matchtype="schemagraphitem">
																				<children>
																					<template match="description" matchtype="schemagraphitem">
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
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="20%"/>
														<styles font-family="Arial" font-size="10pt"/>
														<children>
															<text fixtext="Prime Sponsor:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" width="30%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
														<children>
															<paragraph paragraphtag="pre-wrap">
																<children>
																	<template match="template" matchtype="schemagraphitem">
																		<children>
																			<template match="templateMaster" matchtype="schemagraphitem">
																				<children>
																					<template match="primeSponsor" matchtype="schemagraphitem">
																						<children>
																							<template match="sponsorCode" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=" : "/>
																							<template match="sponsorName" matchtype="schemagraphitem">
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
															</paragraph>
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
														<properties colspan="2" width="20%"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2" width="20%"/>
														<children>
															<text fixtext="Proposal Due:">
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="25%"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold" padding-left="20pt"/>
														<children>
															<text fixtext="Non Competing Continuation:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="60%"/>
														<styles font-family="Arial" font-size="9pt" padding-left="20pt"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="nonCompetingCont" matchtype="schemagraphitem">
																				<children>
																					<template match="nonCompetingContDesc" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="25%"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold" padding-left="20pt"/>
														<children>
															<text fixtext="Competing Renewal:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="60%"/>
														<styles font-family="Arial" font-size="9pt" padding-left="20pt"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="competingRenewal" matchtype="schemagraphitem">
																				<children>
																					<template match="competingRenewalDesc" matchtype="schemagraphitem">
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
														<properties colspan="2"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<styles font-family="Arial"/>
												<children>
													<tablecell>
														<properties colspan="2"/>
														<children>
															<text fixtext="Payment:">
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="25%"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold" padding-left="20pt"/>
														<children>
															<text fixtext="Basis of Payment:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="60%"/>
														<styles font-family="Arial" font-size="9pt" padding-left="20pt"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="basisPayment" matchtype="schemagraphitem">
																				<children>
																					<template match="basisPaymentDesc" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="25%"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold" padding-left="20pt"/>
														<children>
															<text fixtext="Method of Payment:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="60%"/>
														<styles font-family="Arial" font-size="9pt" padding-left="20pt"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="paymentMethod" matchtype="schemagraphitem">
																				<children>
																					<template match="paymentMethodDesc" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="25%"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold" padding-left="20pt"/>
														<children>
															<text fixtext="Payment/Invoice Frequency:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="60%"/>
														<styles font-family="Arial" font-size="9pt" padding-left="20pt"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="paymentFreq" matchtype="schemagraphitem">
																				<children>
																					<template match="paymentFreqDesc" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="25%"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold" padding-left="20pt"/>
														<children>
															<text fixtext="Number of Copies:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="60%"/>
														<styles font-family="Arial" font-size="9pt" padding-left="20pt"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="invoiceCopies" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<format datatype="int"/>
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
														<properties width="60%"/>
														<styles font-family="Arial" font-size="9pt" padding-left="20pt"/>
														<children>
															<text fixtext="Final due within ">
																<styles font-weight="bold"/>
															</text>
															<condition>
																<children>
																	<conditionbranch xpath="template/templateMaster/finalInvoiceDue &gt; 0">
																		<children>
																			<template match="template" matchtype="schemagraphitem">
																				<children>
																					<template match="templateMaster" matchtype="schemagraphitem">
																						<children>
																							<template match="finalInvoiceDue" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-size="10pt" font-weight="bold"/>
																										<format datatype="int"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="__"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext=" days of expiration">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="60%"/>
														<styles font-family="Arial" font-size="9pt" padding-left="20pt"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties width="25%"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold" padding-left="20pt"/>
														<children>
															<text fixtext="Special Invoice Instructions:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="60%"/>
														<styles font-family="Arial" font-size="9pt" padding-left="20pt"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="invoiceInstructions" matchtype="schemagraphitem">
																				<children>
																					<paragraph paragraphtag="pre-wrap">
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</paragraph>
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
								<properties border="0" width="100%"/>
								<styles font-family="Times New Roman" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<newline/>
															<text fixtext="Comments:">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<table>
																		<properties border="0" width="100%"/>
																		<children>
																			<tablebody>
																				<children>
																					<template match="comment" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="25%"/>
																										<styles padding-bottom="5pt" padding-left="20pt" padding-top="0"/>
																										<children>
																											<template match="Description" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																													<text fixtext=":">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="60%"/>
																										<styles padding-bottom="5pt" padding-left="20pt" padding-top="0"/>
																										<children>
																											<template match="Comments" matchtype="schemagraphitem">
																												<children>
																													<paragraph paragraphtag="pre-wrap">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</paragraph>
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
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Times New Roman" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<text fixtext="Terms:">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<table>
																		<properties border="0"/>
																		<children>
																			<tablebody>
																				<children>
																					<template match="term" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="90%"/>
																										<styles padding-left="20pt"/>
																										<children>
																											<template match="Description" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Arial" font-weight="bold"/>
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
																										<properties width="90%"/>
																										<styles padding-left="20pt"/>
																										<children>
																											<table>
																												<properties border="0"/>
																												<children>
																													<tablebody>
																														<children>
																															<template match="TermDetails" matchtype="schemagraphitem">
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="3%"/>
																																				<styles padding-left="20pt"/>
																																				<children>
																																					<text fixtext="-"/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="97%"/>
																																				<styles padding-left="20pt"/>
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<template match="TermDescription" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
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
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell/>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Times New Roman" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<text fixtext="Reporting:">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<table>
																		<properties border="0"/>
																		<children>
																			<tablebody>
																				<children>
																					<template match="report" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="95%"/>
																										<styles padding-top="0"/>
																										<children>
																											<template match="Description" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Arial" font-weight="bold"/>
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
																										<properties width="95%"/>
																										<styles padding="0"/>
																										<children>
																											<table>
																												<properties border="0" cellpadding="0" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<template match="ReportTermDetails" matchtype="schemagraphitem">
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="20"/>
																																				<styles padding="0" padding-bottom="0" padding-top="0"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="160"/>
																																				<styles font-family="Arial" padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="Type of Report: "/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<template match="ReportCodeDesc" matchtype="schemagraphitem">
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
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="20"/>
																																				<styles padding="0" padding-bottom="0" padding-top="0"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="160"/>
																																				<styles font-family="Arial" padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="Frequency:"/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<template match="FrequencyCodeDesc" matchtype="schemagraphitem">
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
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="20"/>
																																				<styles padding="0" padding-bottom="0" padding-top="0"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="160"/>
																																				<styles font-family="Arial" padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="Frequency Basis: "/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<template match="FrequencyBaseDesc" matchtype="schemagraphitem">
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
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="20"/>
																																				<styles padding="0" padding-bottom="0" padding-top="0"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="160"/>
																																				<styles font-family="Arial" padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="Due Date:"/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt" padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath="DueDate != &apos;1900-01-01+00:00&apos;">
																																								<children>
																																									<template match="DueDate" matchtype="schemagraphitem">
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
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="20"/>
																																				<styles padding="0" padding-bottom="0" padding-top="0"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="160"/>
																																				<styles font-family="Arial" padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<text fixtext="OSP Distribution:"/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<template match="OSPDistributionDesc" matchtype="schemagraphitem">
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
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="20"/>
																																				<styles padding="0" padding-bottom="0" padding-top="0"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties colspan="2"/>
																																				<styles padding="0" padding-bottom="0" padding-top="0"/>
																																				<children>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath="MailCopies/RolodexId &gt; -1">
																																								<children>
																																									<table>
																																										<properties border="0" cellpadding="0" width="100%"/>
																																										<children>
																																											<tablebody>
																																												<children>
																																													<template match="MailCopies" matchtype="schemagraphitem">
																																														<children>
																																															<tablerow>
																																																<children>
																																																	<tablecell>
																																																		<properties colspan="2"/>
																																																		<styles padding="0" padding-bottom="0" padding-top="0"/>
																																																		<children>
																																																			<text fixtext="   Mail "/>
																																																			<template match="NumberOfCopies" matchtype="schemagraphitem">
																																																				<children>
																																																					<content>
																																																						<format datatype="string"/>
																																																					</content>
																																																				</children>
																																																			</template>
																																																			<text fixtext=" "/>
																																																			<condition>
																																																				<children>
																																																					<conditionbranch xpath="NumberOfCopies &gt;1">
																																																						<children>
																																																							<text fixtext="copies"/>
																																																						</children>
																																																					</conditionbranch>
																																																					<conditionbranch xpath="NumberOfCopies &lt;2">
																																																						<children>
																																																							<text fixtext="copy"/>
																																																						</children>
																																																					</conditionbranch>
																																																				</children>
																																																			</condition>
																																																			<text fixtext=" to Rolodex Id "/>
																																																			<template match="RolodexId" matchtype="schemagraphitem">
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
																																								</children>
																																							</conditionbranch>
																																						</children>
																																					</condition>
																																					<newline/>
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
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Times New Roman" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<newline/>
															<text fixtext="Contact list:">
																<styles font-size="12pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<table>
																		<properties border="0"/>
																		<children>
																			<tableheader>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties align="right" height="1" valign="top" width="22%"/>
																								<styles border-width="0" font-family="Arial" margin="0" padding="0"/>
																							</tablecell>
																							<tablecell>
																								<properties height="1" width="53%"/>
																								<styles border-width="0" font-family="Arial"/>
																							</tablecell>
																							<tablecell>
																								<properties height="1" width="25%"/>
																								<styles border-width="0" font-family="Arial"/>
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tableheader>
																			<tablebody>
																				<children>
																					<template match="contact" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<properties width="10%"/>
																								<children>
																									<tablecell>
																										<properties align="right" width="22%"/>
																										<styles font-family="Arial"/>
																										<children>
																											<text fixtext="Contact Type:">
																												<styles font-weight="bold" text-decoration="underline"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="53%"/>
																										<styles font-family="Arial"/>
																										<children>
																											<template match="ContactTypeDesc" matchtype="schemagraphitem">
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
																										<styles font-family="Arial"/>
																										<children>
																											<text fixtext="Rolodex Id: ">
																												<styles font-weight="bold" text-decoration="underline"/>
																											</text>
																											<text fixtext="      ">
																												<styles font-weight="bold"/>
																											</text>
																											<template match="RolodexDetails" matchtype="schemagraphitem">
																												<children>
																													<template match="RolodexId" matchtype="schemagraphitem">
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
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties colspan="4" width="22%"/>
																										<styles padding="0" padding-top="1pt"/>
																										<children>
																											<template match="RolodexDetails" matchtype="schemagraphitem">
																												<children>
																													<table>
																														<properties border="0" width="100%"/>
																														<children>
																															<tablebody>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties align="right" width="130"/>
																																				<styles font-family="Arial" padding-top="5pt"/>
																																				<children>
																																					<text fixtext="Name:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties colspan="3"/>
																																				<styles font-family="Arial" padding-top="5pt"/>
																																				<children>
																																					<template match="LastName" matchtype="schemagraphitem">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																					<template match="FirstName" matchtype="schemagraphitem">
																																						<children>
																																							<text fixtext=", "/>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																					<template match="MiddleName" matchtype="schemagraphitem">
																																						<children>
																																							<text fixtext=" "/>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																					<text fixtext="    "/>
																																				</children>
																																			</tablecell>
																																		</children>
																																	</tablerow>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties align="right" width="130"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<text fixtext="Organization:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties colspan="3"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<template match="Organization" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
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
																																				<properties align="right" width="130"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<text fixtext="Address:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties colspan="3"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<template match="Address1" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
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
																																				<properties width="130"/>
																																				<styles font-family="Arial"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties colspan="3"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<template match="Address2" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
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
																																				<properties width="130"/>
																																				<styles font-family="Arial"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties colspan="3"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<template match="Address3" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
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
																																				<properties align="right" width="130"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<text fixtext="Title:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<template match="Title" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</paragraph>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties align="right" width="76"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<text fixtext="Phone:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<template match="PhoneNumber" matchtype="schemagraphitem">
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
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties align="right" width="130"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<text fixtext="City:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<template match="City" matchtype="schemagraphitem">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties align="right" width="76"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<text fixtext="State:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<template match="StateDescription" matchtype="schemagraphitem">
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
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties align="right" width="130"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<text fixtext="Postal Code:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<template match="PostalCode" matchtype="schemagraphitem">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties align="right" width="76"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<text fixtext="Country:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<template match="CountryDescription" matchtype="schemagraphitem">
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
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties align="right" width="130"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<text fixtext="Fax:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<template match="Fax" matchtype="schemagraphitem">
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties align="right" width="76"/>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<text fixtext="E Mail:">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial"/>
																																				<children>
																																					<template match="Email" matchtype="schemagraphitem">
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
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties colspan="4" width="22%"/>
																										<children>
																											<line>
																												<properties color="black" size="1"/>
																											</line>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="0.92in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Times New Roman" font-size="9pt" table-layout="fixed"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="4" height="18" width="15%"/>
														<styles padding="0" padding-bottom="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" width="10%"/>
														<styles font-family="Verdana" font-size="9pt" padding-bottom="0"/>
														<children>
															<text fixtext="Template: ">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" rowspan="2" valign="top" width="65%"/>
														<styles font-family="Arial" font-size="9pt" padding-bottom="0" padding-left="0"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="templateCode" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath=". != 0">
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="9pt"/>
																										<format datatype="int"/>
																									</content>
																									<text fixtext=" : "/>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</template>
																			<template match="description" matchtype="schemagraphitem">
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
													</tablecell>
													<tablecell>
														<properties align="right" width="10%"/>
														<styles font-family="Arial" font-size="9pt" padding-bottom="0" padding-right="5pt"/>
														<children>
															<text fixtext="Page: ">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Arial" font-size="9pt" padding-bottom="0"/>
														<children>
															<field>
																<styles font-family="Verdana" font-size="9pt"/>
															</field>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="10%"/>
														<styles padding-top="0"/>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="10%"/>
														<styles font-family="Arial" padding-right="6pt" padding-top="0"/>
														<children>
															<text fixtext="Date:">
																<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" width="15%"/>
														<styles font-family="Arial" font-size="9pt" padding-bottom="0" padding-left="0" padding-top="0"/>
														<children>
															<template match="template" matchtype="schemagraphitem">
																<children>
																	<template match="templateMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="CurrentDate" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-family="Verdana" font-size="9pt" padding-right="3pt" padding-top="3pt"/>
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
														<properties align="left" colspan="4" valign="top" width="15%"/>
														<styles font-family="Verdana" font-size="9pt" padding="0" padding-top="0"/>
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
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
