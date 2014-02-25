<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\Proposal printing\xml files\nsfproposalwithabstracts1.xml" templatexmlfile="">
	<nspair prefix="nih" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<styles font-family="Verdana" line-height="10pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties cellpadding="0" cellspacing="0" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0"/>
										<properties align="center" colspan="3"/>
										<children>
											<paragraph paragraphtag="p">
												<styles padding="0"/>
											</paragraph>
											<table>
												<properties border="2" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-top="10pt"/>
																		<properties align="center" height="12"/>
																		<children>
																			<text fixtext="FACILITIES, EQUIPMENT, AND OTHER RESOURCES">
																				<styles font-size="14pt" font-weight="bold"/>
																			</text>
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
										<styles padding-top="0pt"/>
										<properties colspan="3" height="22" valign="top"/>
										<children>
											<text fixtext='FACIILTIES:  Identify the facilities to be used at each performance site listed and, as appropriate, briefly indicate their capabilities, pertinent capabilities, relative proximity, and extent of availability to the project. Use "Other" to describe the facilities at any other performance sites listed and at sites for field studies. Use additional pages, if necessary.'>
												<styles font-family="Verdana" font-size="9pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt" padding-top="6pt"/>
										<properties colspan="3"/>
										<children>
											<text fixtext="Laboratory:">
												<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles line-height="12pt" padding-left="12pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 4]/AbstractText ) &lt;= 0"/>
														</testexpression>
														<children>
															<newline/>
															<newline/>
														</children>
													</choiceoption>
												</children>
											</choice>
											<template>
												<match match="nih:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="Abstract"/>
														<children>
															<template>
																<match match="AbstractText"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="../AbstractTypeCode = 4"/>
																				</testexpression>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt"/>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt" padding-top="6pt"/>
										<properties colspan="3"/>
										<children>
											<text fixtext="Clinical:">
												<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="12pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 5]/AbstractText ) &lt;= 0"/>
														</testexpression>
														<children>
															<newline/>
															<newline/>
														</children>
													</choiceoption>
												</children>
											</choice>
											<template>
												<match match="nih:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="Abstract"/>
														<children>
															<template>
																<match match="AbstractText"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="../AbstractTypeCode = 5"/>
																				</testexpression>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt"/>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt" padding-top="6pt"/>
										<properties colspan="3"/>
										<children>
											<text fixtext="Animal:">
												<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="12pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 6]/AbstractText ) &lt;= 0"/>
														</testexpression>
														<children>
															<newline/>
															<newline/>
														</children>
													</choiceoption>
												</children>
											</choice>
											<template>
												<match match="nih:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="Abstract"/>
														<children>
															<template>
																<match match="AbstractText"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="../AbstractTypeCode = 6"/>
																				</testexpression>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt"/>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt" padding-top="6pt"/>
										<properties colspan="3"/>
										<children>
											<text fixtext="Computer:">
												<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="12pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 7]/AbstractText ) &lt;= 0"/>
														</testexpression>
														<children>
															<newline/>
															<newline/>
														</children>
													</choiceoption>
												</children>
											</choice>
											<template>
												<match match="nih:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="Abstract"/>
														<children>
															<template>
																<match match="AbstractText"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="../AbstractTypeCode = 7"/>
																				</testexpression>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt"/>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt" padding-top="6pt"/>
										<properties colspan="3"/>
										<children>
											<text fixtext="Office:">
												<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="12pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 8]/AbstractText ) &lt;= 0"/>
														</testexpression>
														<children>
															<newline/>
															<newline/>
														</children>
													</choiceoption>
												</children>
											</choice>
											<template>
												<match match="nih:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="Abstract"/>
														<children>
															<template>
																<match match="AbstractText"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="../AbstractTypeCode = 8"/>
																				</testexpression>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt"/>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt" padding-top="6pt"/>
										<properties colspan="3"/>
										<children>
											<text fixtext="Other:">
												<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="12pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 9]/AbstractText ) &lt;= 0"/>
														</testexpression>
														<children>
															<newline/>
															<newline/>
														</children>
													</choiceoption>
												</children>
											</choice>
											<template>
												<match match="nih:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="Abstract"/>
														<children>
															<template>
																<match match="AbstractText"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="../AbstractTypeCode = 9"/>
																				</testexpression>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt"/>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="3"/>
										<children>
											<paragraph paragraphtag="p">
												<styles padding-bottom="0pt" padding-top="6pt"/>
												<children>
													<line>
														<properties color="black" size="1"/>
													</line>
												</children>
											</paragraph>
											<text fixtext="MAJOR EQUIPMENT:">
												<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
											</text>
											<text fixtext=" List the most important items available for this project and, as appropriate, identify the location and pertinent capabilities of each:">
												<styles font-family="Verdana" font-size="9pt"/>
											</text>
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
										<styles padding-bottom="0pt" padding-left="12pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 10]/AbstractText ) &lt;= 0"/>
														</testexpression>
														<children>
															<newline/>
															<newline/>
														</children>
													</choiceoption>
												</children>
											</choice>
											<template>
												<match match="nih:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="Abstract"/>
														<children>
															<template>
																<match match="AbstractText"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="../AbstractTypeCode = 10"/>
																				</testexpression>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt"/>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
										<children>
											<paragraph paragraphtag="p">
												<styles padding-top="6pt"/>
												<children>
													<line>
														<properties color="black" size="1"/>
													</line>
												</children>
											</paragraph>
											<text fixtext="ADDITIONAL INFORMATION:">
												<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
											</text>
											<text fixtext=" Provide any other information describing the other resources available for the project. Identify support services such as consultant, secretarial, machine shop, and electronics shop, and the extent to which they will be available for the project. Include an explanation of any consortium/contractual/subaward arrangements with other organizations.">
												<styles font-family="Verdana" font-size="9pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt" padding-left="12pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 11]/AbstractText ) &lt;= 0"/>
														</testexpression>
														<children>
															<newline/>
															<newline/>
														</children>
													</choiceoption>
												</children>
											</choice>
											<template>
												<match match="nih:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="Abstract"/>
														<children>
															<template>
																<match match="AbstractText"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="../AbstractTypeCode = 11"/>
																				</testexpression>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt"/>
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
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
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
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.65in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.5in" paperwidth="8.5in"/>
		<footerall>
			<template>
				<match overwrittenxslmatch="/"/>
				<children>
					<table topdown="0">
						<properties border="0" width="100%"/>
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
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" padding="0"/>
												<properties align="center" colspan="2"/>
												<children>
													<text fixtext="Page H-"/>
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
	</pagelayout>
</structure>
