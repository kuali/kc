<?xml version="1.0" encoding="UTF-8"?>
<structure version="12" xsltversion="1" htmlmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" useimportschema="1" embed-images="1" enable-authentic-scripts="1" authentic-scripts-in-debug-mode-external="0" generated-file-location="DEFAULT">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://xml.coeus.mit.edu/iacuc"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="XML" main="1" schemafile="D:\Testing\IACUC SSheet\MileStone3\Test L1\Schema Working XML\iacuc.xsd" workingxmlfile="D:\Testing\IACUC SSheet\MileStone3\Test L1\Schema Working XML\sample4schedule.xml"/>
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
	<script-project>
		<Project version="1" app="AuthenticView"/>
	</script-project>
	<importedxslt/>
	<globalstyles>
		<rule url="toall.css">
			<media>
				<media value="all"/>
			</media>
		</rule>
	</globalstyles>
	<mainparts>
		<children>
			<globaltemplate subtype="main" match="/">
				<children>
					<documentsection>
						<properties columncount="1" columngap="0.50in" headerfooterheight="fixed" pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="auto" pagestart="next" paperheight="11in" papermarginbottom="0.79in" papermarginfooter="0.30in" papermarginheader="0.30in" papermarginleft="0.60in" papermarginright="0.60in" papermargintop="0.99in" paperwidth="8.50in"/>
						<children>
							<globaltemplate subtype="pagelayout" match="headerall">
								<children>
									<paragraph paragraphtag="center">
										<children>
											<text fixtext="Meeting Minutes">
												<styles font-weight="bold"/>
											</text>
										</children>
									</paragraph>
									<line/>
								</children>
							</globaltemplate>
						</children>
					</documentsection>
					<template subtype="source" match="XML">
						<children>
							<template subtype="element" match="n1:Schedule">
								<children>
									<newline/>
									<tgrid>
										<properties border="0" cellpadding="0" cellspacing="0"/>
										<children>
											<tgridheader-cols>
												<children>
													<tgridcol>
														<styles width="1.34in"/>
													</tgridcol>
												</children>
											</tgridheader-cols>
											<tgridbody-cols>
												<children>
													<template subtype="element" match="n1:ScheduleMasterData">
														<children>
															<tgridcol/>
														</children>
														<variables/>
													</template>
												</children>
											</tgridbody-cols>
											<tgridbody-rows>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<children>
																	<text fixtext="Committee Name"/>
																</children>
															</tgridcell>
															<tgridcell>
																<children>
																	<text fixtext=": "/>
																	<template subtype="element" match="n1:CommitteeName">
																		<children>
																			<content/>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<children>
																	<text fixtext="Scheduled Date"/>
																</children>
															</tgridcell>
															<tgridcell>
																<children>
																	<text fixtext=": "/>
																	<template subtype="element" match="n1:ScheduledDate">
																		<children>
																			<content>
																				<format basic-type="xsd" string="MM / DD / YYYY" datatype="date"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<children>
																	<text fixtext="Scheduled Time"/>
																</children>
															</tgridcell>
															<tgridcell>
																<children>
																	<text fixtext=": "/>
																	<template subtype="element" match="n1:ScheduledTime">
																		<children>
																			<content>
																				<format basic-type="xsd" datatype="time"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<children>
																	<text fixtext="Place"/>
																</children>
															</tgridcell>
															<tgridcell>
																<children>
																	<text fixtext=": "/>
																	<template subtype="element" match="n1:Place">
																		<children>
																			<content/>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
											</tgridbody-rows>
										</children>
									</tgrid>
									<template subtype="element" match="n1:ProtocolSubmission">
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Protocol Details">
														<styles font-weight="bold" text-decoration="underline"/>
													</text>
												</children>
											</paragraph>
											<template subtype="element" match="n1:ProtocolSummary">
												<children>
													<template subtype="element" match="n1:ProtocolMasterData">
														<children>
															<newline/>
															<tgrid>
																<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																<children>
																	<tgridbody-cols>
																		<children>
																			<tgridcol>
																				<styles width="1.99in"/>
																			</tgridcol>
																			<tgridcol>
																				<styles width="0.35in"/>
																			</tgridcol>
																			<tgridcol/>
																		</children>
																	</tgridbody-cols>
																	<tgridbody-rows>
																		<children>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<children>
																							<text fixtext="Protocol Number"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<text fixtext=":"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<template subtype="element" match="n1:ProtocolNumber">
																								<children>
																									<content/>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<children>
																							<text fixtext="Protocol Title"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<text fixtext=":"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<template subtype="element" match="n1:ProtocolTitle">
																								<children>
																									<content/>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<children>
																							<text fixtext="Approval Date"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<text fixtext=":"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<template subtype="element" match="n1:ApprovalDate">
																								<children>
																									<content>
																										<format basic-type="xsd" string="MM / DD / YYYY" datatype="date"/>
																									</content>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<children>
																							<text fixtext="Expiration Date"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<text fixtext=":"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<template subtype="element" match="n1:ExpirationDate">
																								<children>
																									<content>
																										<format basic-type="xsd" string="MM / DD / YYYY" datatype="date"/>
																									</content>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<children>
																							<text fixtext="Principal Investigator"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<text fixtext=":"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<template subtype="element" match="n1:PrincipleInvestigatorName">
																								<children>
																									<content/>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																		</children>
																	</tgridbody-rows>
																</children>
															</tgrid>
														</children>
														<variables/>
													</template>
												</children>
												<variables/>
											</template>
											<newline/>
											<newline/>
											<text fixtext="Minutes details of the above protocol">
												<styles font-weight="bold" text-decoration="underline"/>
											</text>
											<tgrid>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<children>
													<tgridbody-cols>
														<children>
															<tgridcol/>
															<tgridcol/>
															<tgridcol/>
															<tgridcol/>
														</children>
													</tgridbody-cols>
													<tgridheader-rows>
														<children>
															<tgridrow>
																<children>
																	<tgridcell>
																		<children>
																			<text fixtext="Entry Number"/>
																		</children>
																	</tgridcell>
																	<tgridcell>
																		<children>
																			<text fixtext="Minute Entry"/>
																		</children>
																	</tgridcell>
																	<tgridcell>
																		<children>
																			<text fixtext="Update User"/>
																		</children>
																	</tgridcell>
																	<tgridcell>
																		<children>
																			<text fixtext="Update Timestamp"/>
																		</children>
																	</tgridcell>
																</children>
															</tgridrow>
														</children>
													</tgridheader-rows>
													<tgridbody-rows>
														<children>
															<template subtype="element" match="n1:Minutes">
																<children>
																	<tgridrow>
																		<children>
																			<tgridcell>
																				<children>
																					<template subtype="element" match="n1:EntryNumber">
																						<children>
																							<content>
																								<format basic-type="xsd" datatype="integer"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<children>
																					<template subtype="element" match="n1:MinuteEntry">
																						<children>
																							<content/>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<children>
																					<template subtype="element" match="n1:UpdateUser">
																						<children>
																							<content/>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<children>
																					<template subtype="element" match="n1:UpdateTimestamp">
																						<children>
																							<content>
																								<format basic-type="xsd" datatype="date"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</tgridcell>
																		</children>
																	</tgridrow>
																</children>
																<variables/>
															</template>
														</children>
													</tgridbody-rows>
												</children>
											</tgrid>
											<newline/>
											<line>
												<properties size="1"/>
											</line>
										</children>
										<variables/>
									</template>
									<newline/>
									<newline/>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties paperheight="11.69in" paperwidth="8.27in"/>
	</pagelayout>
	<designfragments/>
	<xmltables/>
	<authentic-custom-toolbar-buttons/>
</structure>
