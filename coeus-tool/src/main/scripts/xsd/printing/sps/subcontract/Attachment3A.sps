<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="quirks" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="###,##0" datatype="decimal"/>
	</predefinedformats>
	<predefinedformats>
		<format string="MM / DD / YYYY" datatype="date"/>
	</predefinedformats>
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="award" uri="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award"/>
			<nspair prefix="subcontract" uri="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="D:\VSS\SVN\coeusSource\Branches\4.5\Printing\Subcontract FDP Printing\Schema\SubcontractFdpReports.xsd" workingxmlfile="Subcontract_Fdp_Report.xml">
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
					<newline/>
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="1"/>
						<children>
							<table>
								<properties align="center" border="0" cellspacing=".25" width="90%"/>
								<styles font-family="Arial" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<properties valign="top"/>
												<styles font-family="Arial"/>
												<children>
													<tablecell>
														<properties align="right" colspan="9" height="32" valign="top" width="90%"/>
														<styles font-family="Arial" font-size="9pt"/>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<text fixtext=" "/>
																	<paragraph paragraphtag="h4">
																		<children>
																			<text fixtext="Attachment 3A"/>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
															<paragraph paragraphtag="center">
																<children>
																	<paragraph paragraphtag="h4">
																		<children>
																			<text fixtext="Research Subaward Agreement"/>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
															<text fixtext="Subaward Number:"/>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:PONumber" matchtype="schemagraphitem">
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
															</template>
															<newline/>
															<text fixtext=" "/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties valign="top"/>
												<children>
													<tablecell>
														<properties align="right" colspan="9" width="90%"/>
														<children>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<paragraph paragraphtag="center">
																								<children>
																									<paragraph paragraphtag="h4">
																										<children>
																											<text fixtext="Prime Recipient Contacts"/>
																										</children>
																									</paragraph>
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
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="9" width="100%"/>
														<children>
															<text fixtext="Institution/Organization (&quot;Prime Recipient&quot;) "/>
															<newline/>
															<table>
																<properties border="0" cellspacing=".5" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" cellspacing="0.25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="Name:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeRecipientContacts" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RequisitionerOrgDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:OrganizationName" matchtype="schemagraphitem">
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="Address:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeRecipientContacts" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:OrgRolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																															<newline/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeRecipientContacts" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:OrgRolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Address2" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
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
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="City:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" width="27%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeRecipientContacts" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:OrgRolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:City" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="5%"/>
																														<children>
																															<text fixtext="State:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" colspan="2" width="30%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeRecipientContacts" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:OrgRolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:StateDescription" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="30%"/>
																														<children>
																															<text fixtext="Zip Code: "/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeRecipientContacts" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:OrgRolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Pincode" matchtype="schemagraphitem">
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
																		</children>
																	</tablebody>
																</children>
															</table>
															<line>
																<properties color="black" size="1"/>
															</line>
															<text fixtext="Administrative Contact"/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="Name:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAdministrativeContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:RolodexName" matchtype="schemagraphitem">
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="Address:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAdministrativeContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																															<newline/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAdministrativeContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Address2" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
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
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="City:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" width="27%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAdministrativeContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:City" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="5%"/>
																														<children>
																															<text fixtext="State:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" colspan="2" width="30%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAdministrativeContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:StateDescription" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="30%"/>
																														<children>
																															<text fixtext="Zip Code: "/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAdministrativeContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Pincode" matchtype="schemagraphitem">
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties align="left" width="10%"/>
																														<children>
																															<text fixtext="Telephone:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" width="25%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAdministrativeContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:PhoneNumber" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<text fixtext=" "/>
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
																														<properties align="left" width="5%"/>
																														<children>
																															<text fixtext="Fax:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" nowrap="set" width="30%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAdministrativeContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Fax" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell/>
																													<tablecell/>
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="60%"/>
																														<children>
																															<text fixtext="Email: "/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAdministrativeContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Email" matchtype="schemagraphitem">
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
																		</children>
																	</tablebody>
																</children>
															</table>
															<line>
																<properties color="black" size="1"/>
															</line>
															<text fixtext="Principal Investigator"/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="Name:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimePrincipalInvestigator" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:FullName" matchtype="schemagraphitem">
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="Address:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimePrincipalInvestigator" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:AddressLine1" matchtype="schemagraphitem">
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
																															</template>
																															<newline/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimePrincipalInvestigator" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:AddressLine2" matchtype="schemagraphitem">
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
																															</template>
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
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="City:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" width="27%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimePrincipalInvestigator" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:City" matchtype="schemagraphitem">
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
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="5%"/>
																														<children>
																															<text fixtext="State:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" colspan="2" width="30%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimePrincipalInvestigator" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:State" matchtype="schemagraphitem">
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
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="30%"/>
																														<children>
																															<text fixtext="Zip Code: "/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimePrincipalInvestigator" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:PostalCode" matchtype="schemagraphitem">
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties align="left" width="10%"/>
																														<children>
																															<text fixtext="Telephone:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" width="25%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimePrincipalInvestigator" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:MobilePhoneNumber" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<text fixtext=" "/>
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
																														<properties align="left" width="5%"/>
																														<children>
																															<text fixtext="Fax:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" nowrap="set" width="30%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimePrincipalInvestigator" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:FaxNumber" matchtype="schemagraphitem">
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
																															</template>
																														</children>
																													</tablecell>
																													<tablecell/>
																													<tablecell/>
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="60%"/>
																														<children>
																															<text fixtext="Email: "/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimePrincipalInvestigator" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:EmailAddress" matchtype="schemagraphitem">
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
																		</children>
																	</tablebody>
																</children>
															</table>
															<line>
																<properties color="black" size="1"/>
															</line>
															<text fixtext="Financial Contact"/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="Name:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeFinancialContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:RolodexName" matchtype="schemagraphitem">
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="Address:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeFinancialContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																															<newline/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeFinancialContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Address2" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
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
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="City:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" width="27%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeFinancialContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:City" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="5%"/>
																														<children>
																															<text fixtext="State:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" colspan="2" width="30%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeFinancialContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:StateDescription" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="30%"/>
																														<children>
																															<text fixtext="Zip Code: "/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeFinancialContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Pincode" matchtype="schemagraphitem">
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties align="left" width="10%"/>
																														<children>
																															<text fixtext="Telephone:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" width="25%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeFinancialContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:PhoneNumber" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<text fixtext=" "/>
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
																														<properties align="left" width="5%"/>
																														<children>
																															<text fixtext="Fax:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" nowrap="set" width="30%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeFinancialContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Fax" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell/>
																													<tablecell/>
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="60%"/>
																														<children>
																															<text fixtext="Email: "/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeFinancialContact" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Email" matchtype="schemagraphitem">
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
																		</children>
																	</tablebody>
																</children>
															</table>
															<line>
																<properties color="black" size="1"/>
															</line>
															<text fixtext="Authorized Official"/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="Name:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAuthorizedOfficial" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:RolodexName" matchtype="schemagraphitem">
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="Address:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAuthorizedOfficial" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																															<newline/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAuthorizedOfficial" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Address2" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
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
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="8%"/>
																														<children>
																															<text fixtext="City:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" width="27%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAuthorizedOfficial" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:City" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="5%"/>
																														<children>
																															<text fixtext="State:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" colspan="2" width="30%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAuthorizedOfficial" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:StateDescription" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="30%"/>
																														<children>
																															<text fixtext="Zip Code: "/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAuthorizedOfficial" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Pincode" matchtype="schemagraphitem">
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties align="left" width="10%"/>
																														<children>
																															<text fixtext="Telephone:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" width="25%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAuthorizedOfficial" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:PhoneNumber" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<text fixtext=" "/>
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
																														<properties align="left" width="5%"/>
																														<children>
																															<text fixtext="Fax:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="left" nowrap="set" width="30%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAuthorizedOfficial" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Fax" matchtype="schemagraphitem">
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
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell/>
																													<tablecell/>
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
																						<children>
																							<table>
																								<properties border="0" cellspacing=".25" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="60%"/>
																														<children>
																															<text fixtext="Email: "/>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:PrimeAuthorizedOfficial" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="subcontract:Email" matchtype="schemagraphitem">
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
																		</children>
																	</tablebody>
																</children>
															</table>
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
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientation="landscape" pageorientationportrait="portrait" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="0.1in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
