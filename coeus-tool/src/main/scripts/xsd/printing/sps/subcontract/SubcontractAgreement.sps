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
			<xsdschemasource name="$XML" main="1" schemafile="E:\SubcontractFDPReports\SubcontractFdpReports.xsd" workingxmlfile="Subcontract_Fdp_Report.xml">
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
							<newline/>
							<newline/>
							<table>
								<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
								<styles font-family="Arial" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<styles font-size="12pt" font-weight="bold"/>
												<children>
													<tablecell>
														<properties colspan="2" width="100%"/>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<text fixtext="Research Subaward Agreement"/>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<styles font-size="9pt" font-weight="normal"/>
												<children>
													<tablecell>
														<properties width="50%"/>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<text fixtext="Institution/Organization (&quot;Prime Recipient&quot;)"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<text fixtext="Name: "/>
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<text fixtext="Prime Award No.: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="award:AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="award:AwardHeader" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="award:SponsorAwardNumber" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<text fixtext="Awarding Agency:"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="100%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="award:AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="award:AwardHeader" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="award:SponsorDescription" matchtype="schemagraphitem">
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
													<tablecell>
														<properties width="50%"/>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="3" width="50%"/>
																						<children>
																							<text fixtext="Institution/Organization (&quot;Subrecipient&quot;)"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="3"/>
																						<children>
																							<text fixtext="Name: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorName" matchtype="schemagraphitem">
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<text fixtext="Subaward No.: "/>
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
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties colspan="2"/>
																						<children>
																							<text fixtext="CFDA #: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="award:AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="award:OtherHeaderDetails" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="award:CFDANumber" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<autocalc xpath="concat(  substring( .,1 , 2 )   ,  &quot;.&quot; ,substring-after( . , substring( .,1 , 2 ) )  )"/>
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
																							<newline/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<styles font-size="9pt"/>
																				<children>
																					<tablecell>
																						<properties width="50%"/>
																						<children>
																							<text fixtext="Amount Funded This Action:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties colspan="2" width="50%"/>
																						<children>
																							<text fixtext="Est. Total (if incrementally funded)"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="50%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractAmountInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:ObligatedAmount" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="###,##0.00" datatype="decimal"/>
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
																						<properties colspan="2" width="50%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractAmountInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:AnticipatedAmount" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<text fixtext="$"/>
																													<content>
																														<format string="###,##0.00" datatype="decimal"/>
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
												<styles font-size="9pt" font-weight="normal"/>
												<children>
													<tablecell>
														<properties colspan="2" width="50%"/>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="50%"/>
																						<children>
																							<text fixtext="Subaward Period of Performance:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="50%"/>
																						<children>
																							<text fixtext="Estimated Project Period (if incrementally funded):"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="25%"/>
																														<children>
																															<text fixtext="Budget Period:  "/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="20%"/>
																														<children>
																															<text fixtext="From:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<children>
																															<text fixtext="To:"/>
																														</children>
																													</tablecell>
																												</children>
																											</tablerow>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="25%"/>
																													</tablecell>
																													<tablecell>
																														<properties width="20%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:SubcontractAmountInfo" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:PerformanceStartDate" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
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
																													</tablecell>
																													<tablecell>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:SubcontractAmountInfo" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:PerformanceEndDate" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
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
																													</tablecell>
																												</children>
																											</tablerow>
																										</children>
																									</tablebody>
																								</children>
																							</table>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<table>
																								<properties border="0" width="100%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="20%"/>
																														<children>
																															<text fixtext="From:"/>
																														</children>
																													</tablecell>
																													<tablecell>
																														<children>
																															<text fixtext="To:"/>
																														</children>
																													</tablecell>
																												</children>
																											</tablerow>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="20%"/>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:StartDate" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
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
																													</tablecell>
																													<tablecell>
																														<children>
																															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="subcontract:EndDate" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
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
																						<properties colspan="2" width="50%"/>
																						<children>
																							<text fixtext="Project Title: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:Award" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="award:AwardDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="award:AwardHeader" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="award:Title" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="2" width="50%"/>
																						<children>
																							<text fixtext="Reporting Requirements : "/>
																							<condition>
																								<children>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment4Required = &quot;N&quot;">
																										<children>
																											<image>
																												<properties height="8" width="7"/>
																												<target>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																													<fixtext value="checkbox.gif"/>
																												</target>
																												<imagesource>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																													<fixtext value="checkbox.gif"/>
																												</imagesource>
																											</image>
																											<text fixtext=" See Attachment 4">
																												<styles font-family="Arial" font-size="8pt"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment4Required = &quot;Y&quot;">
																										<children>
																											<image>
																												<properties height="8" width="8"/>
																												<target>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath"/>
																													<fixtext value="checked.gif"/>
																												</target>
																												<imagesource>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath"/>
																													<fixtext value="checked.gif"/>
																												</imagesource>
																											</image>
																											<text fixtext=" See Attachment 4">
																												<styles font-family="Arial" font-size="8pt"/>
																											</text>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<text fixtext="     ">
																								<styles font-family="Arial" font-size="8pt"/>
																							</text>
																							<condition>
																								<children>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment3BRequired = &quot;N&quot;">
																										<children>
																											<image>
																												<properties height="8" width="7"/>
																												<target>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																													<fixtext value="checkbox.gif"/>
																												</target>
																												<imagesource>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																													<fixtext value="checkbox.gif"/>
																												</imagesource>
																											</image>
																											<text fixtext=" FFATA (Attachment 3B) ">
																												<styles font-family="Arial" font-size="8pt"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment3BRequired = &quot;Y&quot;">
																										<children>
																											<text fixtext=" ">
																												<styles font-family="Arial" font-size="8pt"/>
																											</text>
																											<image>
																												<properties height="8" width="8"/>
																												<target>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath"/>
																													<fixtext value="checked.gif"/>
																												</target>
																												<imagesource>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath"/>
																													<fixtext value="checked.gif"/>
																												</imagesource>
																											</image>
																											<text fixtext=" FFATA (Attachment 3B) ">
																												<styles font-family="Arial" font-size="8pt"/>
																											</text>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<text fixtext="     ">
																								<styles font-family="Arial" font-size="8pt"/>
																							</text>
																							<condition>
																								<children>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment4ARequired  = &quot;N&quot;">
																										<children>
																											<image>
																												<properties height="8" width="7"/>
																												<target>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																													<fixtext value="checkbox.gif"/>
																												</target>
																												<imagesource>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																													<fixtext value="checkbox.gif"/>
																												</imagesource>
																											</image>
																											<text fixtext=" "/>
																											<text fixtext="ARRA Funds (Attachment 4A">
																												<styles font-family="Arial" font-size="8pt"/>
																											</text>
																											<text fixtext=")"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment4ARequired  = &quot;Y&quot;">
																										<children>
																											<image>
																												<properties height="8" width="8"/>
																												<target>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath"/>
																													<fixtext value="checked.gif"/>
																												</target>
																												<imagesource>
																													<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath"/>
																													<fixtext value="checked.gif"/>
																												</imagesource>
																											</image>
																											<text fixtext=" ARRA Funds (Attachment 4A) ">
																												<styles font-family="Arial" font-size="8pt"/>
																											</text>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<text fixtext=" ">
																								<styles font-family="Arial" font-size="8pt"/>
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
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<styles font-size="10pt" font-weight="bold"/>
												<children>
													<tablecell>
														<properties colspan="2" width="50%"/>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<text fixtext="Terms &amp; conditions"/>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<styles font-weight="normal"/>
												<children>
													<tablecell>
														<properties colspan="2" width="100%"/>
														<styles font-family="Arial" font-size="9pt"/>
														<children>
															<text fixtext="1) Prime Recipient hereby awards a cost reimbursable subaward, as described above, to Subrecipient. The statement of work and budget for this">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="subaward are : ">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<condition>
																<children>
																	<conditionbranch xpath="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment5Required = &quot;N&quot;">
																		<children>
																			<text fixtext=" ">
																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																			</text>
																			<condition>
																				<children>
																					<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:SowOrSubProposalBudget  = &quot;Y&quot;">
																						<children>
																							<image>
																								<properties height="8" width="8"/>
																								<target>
																									<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath"/>
																									<fixtext value="checked.gif"/>
																								</target>
																								<imagesource>
																									<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath"/>
																									<fixtext value="checked.gif"/>
																								</imagesource>
																							</image>
																							<text fixtext=" ">
																								<styles font-family="Arial" font-size="8pt"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:SowOrSubProposalBudget  = &quot;N&quot;">
																						<children>
																							<text fixtext=" ">
																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																							</text>
																							<image>
																								<properties height="8" width="7"/>
																								<target>
																									<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																									<fixtext value="checkbox.gif"/>
																								</target>
																								<imagesource>
																									<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																									<fixtext value="checkbox.gif"/>
																								</imagesource>
																							</image>
																							<text fixtext=" ">
																								<styles font-family="Arial" font-size="8pt"/>
																							</text>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																			<text fixtext="As specified in Subrecipient&apos;s proposal dated ">
																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																			</text>
																			<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="subcontract:SubcontractTemplateInfo" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="subcontract:SubProposalDate" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<styles font-family="Arial" font-size="9pt"/>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext=" ;or">
																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																			</text>
																			<text fixtext=" "/>
																			<image>
																				<properties height="8" width="7"/>
																				<target>
																					<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																					<fixtext value="checkbox.gif"/>
																				</target>
																				<imagesource>
																					<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																					<fixtext value="checkbox.gif"/>
																				</imagesource>
																			</image>
																			<text fixtext=" ">
																				<styles font-family="Arial" font-size="8pt"/>
																			</text>
																			<text fixtext="as shown in Attachment 5">
																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																			</text>
																			<text fixtext=" "/>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment5Required = &quot;Y&quot;">
																		<children>
																			<text fixtext=" ">
																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																			</text>
																			<image>
																				<properties height="8" width="7"/>
																				<target>
																					<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																					<fixtext value="checkbox.gif"/>
																				</target>
																				<imagesource>
																					<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath"/>
																					<fixtext value="checkbox.gif"/>
																				</imagesource>
																			</image>
																			<text fixtext=" ">
																				<styles font-family="Arial" font-size="8pt"/>
																			</text>
																			<text fixtext="As specified in Subrecipient&apos;s proposal dated ">
																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																			</text>
																			<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="subcontract:SubcontractTemplateInfo" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="subcontract:SubProposalDate" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
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
																			<text fixtext=" ;or ">
																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																			</text>
																			<text fixtext=" "/>
																			<image>
																				<properties height="8" width="8"/>
																				<target>
																					<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath"/>
																					<fixtext value="checked.gif"/>
																				</target>
																				<imagesource>
																					<xpath value="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath"/>
																					<fixtext value="checked.gif"/>
																				</imagesource>
																			</image>
																			<text fixtext=" "/>
																			<text fixtext="as shown in Attachment 5">
																				<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext=". In its performance of the subaward work, Subrecipient shall be an independent entity and not an employee or agent of Prime Recipient.">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="2) Prime Recipient Shall reimburse Subrecipient not more often than monthly for allowable costs. All invoices shall be submitted using Subrecipient&apos;s standard invoice, but at a minimum shall include current and cumulative costs (including cost sharing), subaward number, and certification as to truth and accuracy of invoice. Invoices that do not reference Prime Recipient&apos;s Subaward Number shall be returned to Subrecipient. Invoices and questions concerning invoice receipt or payments should be directed to the appropriate party&apos;s ">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractTemplateInfo" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:InvoiceOrPaymentContactDescription" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<styles font-family="Arial" font-size="9pt"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" "/>
															<text fixtext="Contact as shown in Attachments 3A &amp; 3B.">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="3) A final statement of cumulative costs incurred, including cost sharing, marked &quot;FINAL&quot; must be submitted to Prime Recipient&apos;s ">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractTemplateInfo" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:FinalStmtOfCostsContactDescription" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<styles font-family="Arial" font-size="9pt"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" Contact, as shown in Attachments 3A and 3B, NOT LATER THAN sixty (60) days after subaward end date. The final statement of costs shall constitute Subrecipient&apos;s final financial report.">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="4) All payments shall be considered provisional and subject to adjustment within the total estimated cost in the event such adjustment is necessary as a result of an adverse audit finding against the Subrecipient.">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="5) Matters concerning the technical performance of this subaward should be directed to the appropriate party&apos;s Principal Investigator, as shown in Attachments 3A and 3B. Technical reports are required as shown above, &quot;Reporting Requirements&quot;.">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="6) Matters concerning the request or negotiation of any changes in the terms, conditions, or amounts cited in this subaward agreement, and any changes requiring prior approval, should be directed to the appropriate party&apos;s ">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractTemplateInfo" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:ChangeRequestsContactDescription" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<styles font-family="Arial" font-size="9pt"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" Contact, as shown in Attachments 3A &amp; 3B. Any such changes made to this subaward agreement require the written approval of each party&apos;s Authorized Official as shown in Attachments 3A &amp; 3B.">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="7) Each party shall be responsible for its negligent acts or omissions and the negligent acts or omissions of its employees, officers, or director&apos;s, to the extent allowed by law.">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="8) Either party may terminate this subaward with thirty days written notice to the appropriate party&apos;s ">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractTemplateInfo" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:TerminationContactDescription" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<styles font-family="Arial" font-size="9pt"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" Contact as shown in Attachments 3A &amp; 3B. Prime Recipient shall pay Subrecipient for termination costs as allowable under OMB Circular A-21 or A-122 or 45 CFR Part 74 Appendix E, &quot;Principles for Determining Costs Applicable to Research and Development under Grants and Contracts with Hospitals&quot; as applicable.">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="9) No-cost extensions require the approval of the Prime Recipient. Any requests for a no-cost extension should be addressed to and received by the">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<text fixtext=" "/>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractTemplateInfo" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:NoCostExtensionContactDescription" matchtype="schemagraphitem">
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
															<text fixtext=" Contact, as shown in Attachments 3A &amp; 3B, not less than thirty (30) days prior to the desired effective date of the requested change.">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="10) The Subaward is subject to the terms and conditions of the Prime Award and other special terms and conditions, as identified in Attachment 2.">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<newline/>
															<text fixtext="11) By signing below Subrecipient makes the certifications and assurances shown in Attachments 1 and 2. Subrecipient also assures that it will comply with applicable statutory and regulatory requirements specified in the Research Terms &amp; Conditions Appendix C found at ">
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
															</text>
															<link>
																<children>
																	<text fixtext="http://nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf">
																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																	</text>
																</children>
																<action>
																	<none/>
																</action>
																<bookmark/>
																<hyperlink>
																	<fixtext value="nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf"/>
																</hyperlink>
															</link>
															<text fixtext=" "/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<styles font-size="9pt" font-weight="normal"/>
												<children>
													<tablecell>
														<properties width="50%"/>
														<children>
															<text fixtext="  By an Authorized Official of Prime Recipient"/>
															<paragraph paragraphtag="p"/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="5%"/>
																					</tablecell>
																					<tablecell>
																						<properties width="70%"/>
																						<children>
																							<text fixtext="_______________________________________________"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="20%"/>
																						<children>
																							<text fixtext="_____________"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="5%"/>
																					</tablecell>
																					<tablecell>
																						<properties width="70%"/>
																					</tablecell>
																					<tablecell>
																						<properties width="20%"/>
																						<children>
																							<text fixtext="Date"/>
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
													<tablecell>
														<properties width="50%"/>
														<children>
															<text fixtext="  By an Authorized Official of Subrecipient "/>
															<paragraph paragraphtag="p"/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="5%"/>
																					</tablecell>
																					<tablecell>
																						<properties width="70%"/>
																						<children>
																							<text fixtext="_______________________________________________"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="20%"/>
																						<children>
																							<text fixtext="_____________"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="5%"/>
																					</tablecell>
																					<tablecell>
																						<properties width="70%"/>
																					</tablecell>
																					<tablecell>
																						<properties width="20%"/>
																						<children>
																							<text fixtext="Date"/>
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
							<newline/>
							<newline/>
							<newline/>
							<newline/>
							<newline break="page"/>
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
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="12pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<paragraph paragraphtag="center">
																								<children>
																									<text fixtext="Attachment 1">
																										<styles font-family="Arial" font-size="14pt" font-weight="bold"/>
																									</text>
																								</children>
																							</paragraph>
																							<paragraph paragraphtag="center">
																								<children>
																									<text fixtext="Research Subaward Agreement">
																										<styles font-family="Arial" font-size="14pt" font-weight="bold"/>
																									</text>
																								</children>
																							</paragraph>
																							<paragraph paragraphtag="center">
																								<children>
																									<text fixtext="Certifications and Assurances">
																										<styles font-family="Arial" font-size="14pt" font-weight="bold"/>
																									</text>
																									<text fixtext="  ">
																										<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																									</text>
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
												<children>
													<tablecell>
														<children>
															<text fixtext="By signing the Subaward Agreement, the authorized official of Subrecipient certifies, to the best of his/her knowledge and belief that:">
																<styles font-family="Arial" font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell/>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<text fixtext="Certification Regarding Lobbying">
																		<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<text fixtext="1) No Federal appropriated funds have been paid or will be paid, by or on behalf of the Subrecipient, to any person for influencing or attempting to influence an officer or employee of any agency, a Member of Congress, an officer or employee of Congress, or an employee of a Member of Congress in connection with the awarding of any Federal contract, the making of any Federal grant, the making of any Federal grant, the making of any Federal loan, the entering into of any cooperative agreement, and the extension, continuation, renewal, amendment, or modification of any Federal contract, grant, loan, or cooperative agreement.">
																<styles font-family="Arial" font-size="9pt"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext=" 2) If any funds other than Federal appropriated funds have been paid or will be paid to any person for influencing or intending to influence an officer or employee of any agency, a Member of Congress, an officer or employee of Congress, or an employee of a Member of Congress in connection with this Federal contract, grant, loan, or cooperative agreement, the Subrecipient shall complete and submit Standard Form -LLL, &quot;Disclosure Form to Report Lobbying&quot;, to the Prime Recipient.">
																<styles font-family="Arial" font-size="9pt"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="3) The Subrecipient shall require that the language of this certification be included in the award documents for all subawards at all tiers (including subcontracts, subgrants, and contracts under grants, loans, and cooperative agreements) and that all subrecipients shall certify and disclose accordingly. This certification is a material representation of fact upon which reliance was placed when this transaction was made or entered into. Submission of this certification is a prerequisite for making or entering into this transaction imposed by Section 1352, Title 31, U.S. Code. Any person who fails to file the required certification shall be subject to a civil penalty of not less than $10,000 and not more than $100,000 for each such failure.">
																<styles font-family="Arial" font-size="9pt"/>
															</text>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell/>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<text fixtext="Debarment, Suspension, and Other Responsibility Matters">
																		<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<newline/>
															<text fixtext="Subrecipient certifies by signing this Subaward Agreement that neither it nor its principals are presently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from participation in this transaction by any federal department or agency.">
																<styles font-family="Arial" font-size="9pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell/>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<text fixtext="OMB Circular A-133 Assurance">
																		<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<newline/>
															<text fixtext="Subrecipient assures Prime Recipient that it complies with A-133 and that it will notify Prime Recipient of completion of required audits and of any adverse findings which impact this subaward.">
																<styles font-family="Arial" font-size="9pt"/>
															</text>
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
						</children>
					</template>
					<newline/>
					<newline/>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientation="landscape" pageorientationportrait="portrait" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="0.5in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="1"/>
					</template>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
