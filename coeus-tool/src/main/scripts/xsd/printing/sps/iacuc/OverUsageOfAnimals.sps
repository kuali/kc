<?xml version="1.0" encoding="UTF-8"?>
<structure version="12" xsltversion="1" htmlmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" useimportschema="1" embed-images="1" enable-authentic-scripts="1" authentic-scripts-in-debug-mode-external="0" generated-file-location="DEFAULT">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://xml.coeus.mit.edu/iacuc"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="XML" main="1" schemafile="D:\Testing\IACUC SSheet\MileStone3\Test L1\Schema Working XML\iacuc.xsd" workingxmlfile="D:\Testing\IACUC SSheet\MileStone3\Test L1\Schema Working XML\Correspondence11032010-055040.xml"/>
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
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<styles padding="0"/>
																<children>
																	<line>
																		<properties color="black" size="1"/>
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
								</children>
							</globaltemplate>
						</children>
					</documentsection>
					<template subtype="source" match="XML">
						<children>
							<template subtype="element" match="n1:Correspondence">
								<children>
									<tgrid>
										<properties border="0" width="100%"/>
										<styles font-family="inherit"/>
										<children>
											<tgridbody-cols>
												<children>
													<tgridcol>
														<properties class="tableleft"/>
														<styles width="1.52in"/>
													</tgridcol>
													<tgridcol>
														<styles width="0.43in"/>
													</tgridcol>
													<tgridcol>
														<styles width="6.43in"/>
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
																	<text fixtext="Committee Action:"/>
																</children>
															</tgridcell>
															<tgridcell/>
															<tgridcell>
																<children>
																	<text fixtext="About the over usage of animals."/>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<styles text-align="left"/>
																<children>
																	<text fixtext="Action Date:"/>
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
														<styles height="0.38in"/>
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
														<styles height="0.20in"/>
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
								</children>
								<variables/>
							</template>
							<newline/>
							<newline/>
							<newline/>
							<paragraph paragraphtag="p">
								<children>
									<text fixtext="The committee found the over usage of animals for above mentioned protocol. The protocol must cover at least these points: "/>
								</children>
							</paragraph>
							<paragraph paragraphtag="p">
								<children>
									<list>
										<properties start="0"/>
										<children>
											<listrow>
												<children>
													<text fixtext="Identification of the species and approximate number of animals to be used."/>
												</children>
											</listrow>
											<listrow>
												<children>
													<text fixtext="Rationale for involving animals, and for the appropriateness of the species and numbers used."/>
												</children>
											</listrow>
											<listrow>
												<children>
													<text fixtext="A complete description of the proposed use of the animals."/>
												</children>
											</listrow>
											<listrow>
												<children>
													<text fixtext="A description of procedures designed to assure that discomfort and injury to animals will be limited to that which is unavoidable in the conduct of scientifically valuable research, and that analgesic, anesthetic, and tranquilizing drugs will be used where indicated and appropriate to minimize discomfort and pain to animals."/>
												</children>
											</listrow>
											<listrow>
												<children>
													<text fixtext="A description of any euthanasia method to be used. "/>
												</children>
											</listrow>
										</children>
									</list>
								</children>
							</paragraph>
							<paragraph paragraphtag="p">
								<children>
									<text fixtext="Kindly do the needful."/>
								</children>
							</paragraph>
							<paragraph paragraphtag="p">
								<children>
									<text fixtext="Thank you."/>
								</children>
							</paragraph>
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
