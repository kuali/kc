<?xml version="1.0" encoding="UTF-8"?>
<structure version="12" xsltversion="1" htmlmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" useimportschema="1" embed-images="1" enable-authentic-scripts="1" authentic-scripts-in-debug-mode-external="0" generated-file-location="DEFAULT">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://xml.coeus.mit.edu/iacuc"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="XML" main="1" schemafile="C:\Users\rkprasad\Desktop\Mile stone3\Schema &amp; Xml\iacuc.xsd" workingxmlfile="C:\Users\rkprasad\Desktop\Mile stone3\Schema &amp; Xml\Correspondence11032010-055040.xml"/>
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
	<globalstyles/>
	<mainparts>
		<children>
			<globaltemplate subtype="main" match="/">
				<children>
					<documentsection>
						<properties columncount="1" columngap="0.50in" headerfooterheight="fixed" pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="auto" pagestart="next" paperheight="11in" papermarginbottom="0.79in" papermarginfooter="0.30in" papermarginheader="0.30in" papermarginleft="0.60in" papermarginright="0.60in" papermargintop="0.79in" paperwidth="8.50in"/>
					</documentsection>
					<tgrid>
						<styles width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol/>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles font-size="10pt" padding="0" width="90%"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles font-size="10pt" padding="0" width="10%"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<styles padding="0"/>
												<children>
													<line>
														<properties color="black" size="2"/>
													</line>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
					</tgrid>
					<newline/>
					<template subtype="source" match="XML">
						<children>
							<newline/>
							<template subtype="element" match="n1:Correspondence">
								<children>
									<newline/>
									<tgrid>
										<properties border="0" width="100%"/>
										<children>
											<tgridbody-cols>
												<children>
													<tgridcol>
														<properties class="tableleft"/>
														<styles width="1.59in"/>
													</tgridcol>
													<tgridcol>
														<styles width="0.43in"/>
													</tgridcol>
													<tgridcol>
														<styles width="5.92in"/>
													</tgridcol>
												</children>
											</tgridbody-cols>
											<tgridbody-rows>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<styles text-align="left"/>
																<children>
																	<text fixtext="To:"/>
																</children>
															</tgridcell>
															<tgridcell/>
															<tgridcell>
																<children>
																	<template subtype="element" match="n1:Protocol">
																		<children>
																			<template subtype="element" match="n1:Investigator">
																				<children>
																					<template subtype="element" match="n1:Person">
																						<children>
																							<template subtype="element" match="n1:Fullname">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../n1:PI_flag  = true()">
																												<children>
																													<content>
																														<format basic-type="xsd" datatype="string"/>
																													</content>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																								<variables/>
																							</template>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
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
																<styles text-align="left"/>
																<children>
																	<text fixtext="From:"/>
																</children>
															</tgridcell>
															<tgridcell/>
															<tgridcell>
																<children>
																	<template subtype="element" match="n1:Protocol">
																		<children>
																			<template subtype="element" match="n1:Submissions">
																				<children>
																					<template subtype="element" match="n1:CommitteeMember">
																						<children>
																							<template subtype="element" match="n1:Person">
																								<children>
																									<template subtype="element" match="n1:Fullname">
																										<children>
																											<content/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																								<variables/>
																							</template>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																	<text fixtext=", Chair"/>
																	<newline/>
																	<template subtype="element" match="n1:Protocol">
																		<children>
																			<template subtype="element" match="n1:Submissions">
																				<children>
																					<template subtype="element" match="n1:CommitteeMasterData">
																						<children>
																							<template subtype="element" match="n1:CommitteeName">
																								<children>
																									<content/>
																								</children>
																								<variables/>
																							</template>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
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
																<styles text-align="left"/>
																<children>
																	<text fixtext="Date:"/>
																</children>
															</tgridcell>
															<tgridcell/>
															<tgridcell>
																<children>
																	<template subtype="element" match="n1:CurrentDate">
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
																<styles text-align="left"/>
																<children>
																	<text fixtext="Commitee Action:"/>
																</children>
															</tgridcell>
															<tgridcell/>
															<tgridcell>
																<children>
																	<text fixtext="Notification"/>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<styles text-align="left"/>
																<children>
																	<text fixtext="ActionDate:"/>
																</children>
															</tgridcell>
															<tgridcell/>
															<tgridcell>
																<children>
																	<template subtype="element" match="n1:Protocol">
																		<children>
																			<template subtype="element" match="n1:Submissions">
																				<children>
																					<template subtype="element" match="n1:SubmissionDetails">
																						<children>
																							<template subtype="element" match="n1:ActionType">
																								<children>
																									<template subtype="element" match="n1:ActionDate">
																										<children>
																											<content>
																												<format basic-type="xsd" string="MM / DD / YYYY" datatype="date"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																								<variables/>
																							</template>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
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
																<styles text-align="left"/>
																<children>
																	<text fixtext="Protocol Number:"/>
																</children>
															</tgridcell>
															<tgridcell/>
															<tgridcell>
																<children>
																	<template subtype="element" match="n1:Protocol">
																		<children>
																			<template subtype="element" match="n1:ProtocolMasterData">
																				<children>
																					<template subtype="element" match="n1:ProtocolNumber">
																						<children>
																							<content/>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
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
																<styles text-align="left"/>
																<children>
																	<text fixtext="Study Title:"/>
																</children>
															</tgridcell>
															<tgridcell/>
															<tgridcell>
																<children>
																	<template subtype="element" match="n1:Protocol">
																		<children>
																			<template subtype="element" match="n1:ProtocolMasterData">
																				<children>
																					<template subtype="element" match="n1:ProtocolTitle">
																						<children>
																							<content/>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
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
																<styles text-align="left"/>
																<children>
																	<text fixtext="Expiration Date:"/>
																</children>
															</tgridcell>
															<tgridcell/>
															<tgridcell>
																<children>
																	<template subtype="element" match="n1:Protocol">
																		<children>
																			<template subtype="element" match="n1:ProtocolMasterData">
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
																				<variables/>
																			</template>
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
									<newline/>
									<newline/>
									<newline/>
									<text fixtext="                                    This is to notify that a report is available to view/download in connection with above mentioned protocol."/>
									<newline/>
									<newline/>
								</children>
								<variables/>
							</template>
							<newline/>
							<newline/>
							<newline/>
							<newline/>
							<text fixtext="   Best Regards"/>
							<newline/>
							<newline/>
							<newline/>
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
