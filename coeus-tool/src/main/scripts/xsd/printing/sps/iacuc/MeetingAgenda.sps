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
						<properties columncount="1" columngap="0.50in" headerfooterheight="fixed" pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="auto" pagestart="next" paperheight="11in" papermarginbottom="0.79in" papermarginfooter="0.30in" papermarginheader="0.30in" papermarginleft="0.60in" papermarginright="0.60in" papermargintop="1.00in" paperwidth="8.50in"/>
						<children>
							<globaltemplate subtype="pagelayout" match="headerall">
								<children>
									<paragraph paragraphtag="center">
										<children>
											<text fixtext="Meeting Agenda">
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
							<newline/>
							<template subtype="element" match="n1:Schedule">
								<children>
									<newline/>
									<paragraph paragraphtag="p">
										<properties class="tableleft"/>
										<children>
											<text fixtext="Meeting Details">
												<styles text-decoration="underline"/>
											</text>
										</children>
									</paragraph>
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
								</children>
								<variables/>
							</template>
							<newline/>
							<newline/>
							<template subtype="element" match="n1:Schedule">
								<children>
									<paragraph paragraphtag="p">
										<properties class="tableleft"/>
										<children>
											<text fixtext="Agenda Details">
												<styles text-decoration="underline"/>
											</text>
										</children>
									</paragraph>
									<condition>
										<children>
											<conditionbranch xpath="string-length(normalize-space(string(n1:ScheduleMasterData/n1:MeetingDate)))&gt;0">
												<children>
													<list>
														<properties start="0"/>
														<children>
															<listrow>
																<children>
																	<text fixtext="Review the minutes of the meeting held on "/>
																	<template subtype="element" match="n1:PreviousSchedule">
																		<children>
																			<template subtype="element" match="n1:ScheduleMasterData">
																				<children>
																					<template subtype="element" match="n1:MeetingDate">
																						<children>
																							<content>
																								<format basic-type="xsd" string="Month DD&apos;,&apos; YYYY" datatype="date"/>
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
																	<text fixtext="."/>
																</children>
															</listrow>
														</children>
													</list>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<newline/>
									<condition>
										<children>
											<conditionbranch xpath="string-length(normalize-space(string(n1:NextSchedule/n1:ScheduleMasterData/n1:MeetingDate)))&gt;0">
												<children>
													<list>
														<properties start="0"/>
														<children>
															<listrow>
																<children>
																	<text fixtext="The next meeting will be held on "/>
																	<template subtype="element" match="n1:NextSchedule">
																		<children>
																			<template subtype="element" match="n1:ScheduleMasterData">
																				<children>
																					<template subtype="element" match="n1:MeetingDate">
																						<children>
																							<content>
																								<format basic-type="xsd" string="Month DD&apos;,&apos; YYYY" datatype="date"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																					<condition>
																						<children>
																							<conditionbranch xpath="string-length(normalize-space(n1:NextSchedule/n1:ScheduleMasterData/n1:Place))&gt;0">
																								<children>
																									<text fixtext=" at "/>
																									<template subtype="element" match="n1:Place">
																										<children>
																											<content/>
																										</children>
																										<variables/>
																									</template>
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
																	<text fixtext="."/>
																</children>
															</listrow>
														</children>
													</list>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<text fixtext=" "/>
									<paragraph paragraphtag="p">
										<properties class="tableleft"/>
										<children>
											<text fixtext="Protocols Submitted for Review">
												<styles text-decoration="underline"/>
											</text>
										</children>
									</paragraph>
									<paragraph>
										<children>
											<template subtype="element" match="n1:ProtocolSubmission">
												<children>
													<template subtype="element" match="n1:SubmissionDetails">
														<children>
															<newline/>
															<template subtype="element" match="n1:SubmissionTypeDesc">
																<children>
																	<content>
																		<styles font-weight="bold"/>
																		<format basic-type="xsd" datatype="string"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
													<newline/>
													<paragraph>
														<children>
															<template subtype="element" match="n1:ProtocolSummary">
																<children>
																	<paragraph>
																		<children>
																			<template subtype="element" match="n1:ProtocolMasterData">
																				<children>
																					<text fixtext=" "/>
																					<tgrid>
																						<properties border="0"/>
																						<children>
																							<tgridbody-cols>
																								<children>
																									<tgridcol>
																										<styles width="187px"/>
																									</tgridcol>
																									<tgridcol>
																										<styles width="153px"/>
																									</tgridcol>
																									<tgridcol>
																										<styles width="173px"/>
																									</tgridcol>
																									<tgridcol>
																										<styles width="109px"/>
																									</tgridcol>
																									<tgridcol>
																										<styles width="109px"/>
																									</tgridcol>
																								</children>
																							</tgridbody-cols>
																							<tgridbody-rows>
																								<children>
																									<tgridrow>
																										<children>
																											<tgridcell>
																												<properties valign="top"/>
																												<children>
																													<text fixtext="Protocol Number">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tgridcell>
																											<tgridcell>
																												<properties valign="top"/>
																												<children>
																													<text fixtext=": "/>
																													<template subtype="element" match="n1:ProtocolNumber">
																														<children>
																															<content>
																																<format basic-type="xsd" datatype="string"/>
																															</content>
																														</children>
																														<variables/>
																													</template>
																												</children>
																											</tgridcell>
																											<tgridcell>
																												<properties valign="top"/>
																												<children>
																													<text fixtext="Expiration Date:">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tgridcell>
																											<tgridcell>
																												<properties align="left" valign="top"/>
																												<children>
																													<template subtype="element" match="n1:ExpirationDate">
																														<children>
																															<content>
																																<format basic-type="xsd" string="MM/DD/YYYY" datatype="date"/>
																															</content>
																														</children>
																														<variables/>
																													</template>
																												</children>
																											</tgridcell>
																											<tgridcell>
																												<properties align="left" valign="top"/>
																											</tgridcell>
																										</children>
																									</tgridrow>
																									<tgridrow>
																										<children>
																											<tgridcell>
																												<properties valign="top"/>
																												<children>
																													<text fixtext="Protocol Title">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tgridcell>
																											<tgridcell>
																												<properties align="left" valign="top"/>
																												<children>
																													<text fixtext=": "/>
																													<template subtype="element" match="n1:ProtocolTitle">
																														<children>
																															<content>
																																<format basic-type="xsd" datatype="string"/>
																															</content>
																														</children>
																														<variables/>
																													</template>
																												</children>
																											</tgridcell>
																											<tgridcell joinleft="1"/>
																											<tgridcell joinleft="1"/>
																											<tgridcell joinleft="1"/>
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
																	</paragraph>
																	<paragraph>
																		<children>
																			<tgrid>
																				<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																				<children>
																					<tgridbody-cols>
																						<children>
																							<tgridcol>
																								<styles width="1.98in"/>
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
																											<text fixtext="Principal Investigator">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tgridcell>
																									<tgridcell>
																										<children>
																											<text fixtext=": "/>
																											<template subtype="element" match="n1:Investigator">
																												<children>
																													<template subtype="element" match="n1:Person">
																														<children>
																															<template subtype="element" match="n1:Fullname">
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath="../../n1:PIFlag=true()">
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
																									</tgridcell>
																								</children>
																							</tgridrow>
																						</children>
																					</tgridbody-rows>
																				</children>
																			</tgrid>
																		</children>
																	</paragraph>
																</children>
																<variables/>
															</template>
															<paragraph paragraphtag="p"/>
															<paragraph>
																<children>
																	<tgrid>
																		<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																		<children>
																			<tgridbody-cols>
																				<children>
																					<tgridcol>
																						<styles width="1.95in"/>
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
																									<text fixtext="Primary Reviewers">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<children>
																									<template subtype="element" match="n1:SubmissionDetails">
																										<children>
																											<template subtype="element" match="n1:ProtocolReviewer">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="n1:ReviewerTypeCode=1">
																																<children>
																																	<template subtype="element" match="n1:Person">
																																		<children>
																																			<template subtype="element" match="n1:Fullname">
																																				<children>
																																					<text fixtext=": "/>
																																					<content/>
																																					<newline/>
																																				</children>
																																				<variables/>
																																			</template>
																																		</children>
																																		<variables/>
																																	</template>
																																</children>
																															</conditionbranch>
																														</children>
																													</condition>
																													<newline/>
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
																	<tgrid>
																		<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																		<children>
																			<tgridbody-cols>
																				<children>
																					<tgridcol>
																						<styles width="1.95in"/>
																					</tgridcol>
																					<tgridcol>
																						<styles width="5.72in"/>
																					</tgridcol>
																				</children>
																			</tgridbody-cols>
																			<tgridbody-rows>
																				<children>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<children>
																									<text fixtext="Secondary Reviewers">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<children>
																									<template subtype="element" match="n1:SubmissionDetails">
																										<children>
																											<template subtype="element" match="n1:ProtocolReviewer">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="n1:ReviewerTypeCode=2">
																																<children>
																																	<template subtype="element" match="n1:Person">
																																		<children>
																																			<template subtype="element" match="n1:Fullname">
																																				<children>
																																					<text fixtext=": "/>
																																					<content/>
																																				</children>
																																				<variables/>
																																			</template>
																																		</children>
																																		<variables/>
																																	</template>
																																</children>
																															</conditionbranch>
																														</children>
																													</condition>
																													<newline/>
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
															</paragraph>
														</children>
													</paragraph>
													<line>
														<properties size="1"/>
													</line>
													<newline/>
												</children>
												<variables/>
											</template>
										</children>
									</paragraph>
								</children>
								<variables/>
							</template>
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
