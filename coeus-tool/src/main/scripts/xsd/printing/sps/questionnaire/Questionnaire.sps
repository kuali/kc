<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="MM / DD / YYYY" datatype="date"/>
	</predefinedformats>
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="Questionnaire.xsd">
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
							<table>
								<properties border="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center"/>
														<children>
															<text fixtext="Questionnaire">
																<styles font-size="14pt" font-weight="bold"/>
															</text>
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
								<styles font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="justify" nowrap="set" valign="top" width="122"/>
														<styles font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Questionnaire Name:">
																<styles width="40pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" width="30"/>
														<children>
															<template match="Questionnaire" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="QuestionnaireName" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
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
														<properties align="justify" nowrap="set" valign="top" width="122"/>
														<styles font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Description:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" width="30"/>
														<children>
															<template match="Questionnaire" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="QuestionnaireDesc" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
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
														<properties align="justify" nowrap="set" valign="top" width="122"/>
														<styles font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Module:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="247"/>
														<children>
															<template match="Questionnaire" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="ModuleUsage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="ModuleInfo" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="ModuleDesc" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
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
														<properties nowrap="set" width="72"/>
														<styles font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Sub Module:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="25"/>
														<children>
															<template match="Questionnaire" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="ModuleUsage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="ModuleInfo" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="SubModuleDesc" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
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
							<condition>
								<children>
									<conditionbranch xpath="/Questionnaire/ProtocolInfo">
										<children>
											<table>
												<properties border="0" width="100%"/>
												<styles font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties nowrap="set" width="98"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Protocol Number:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties nowrap="set" width="30"/>
																		<children>
																			<template match="Questionnaire" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AnswerHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="ModuleKey" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
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
																		<properties nowrap="set" width="122"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Sequence Number:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties nowrap="set" width="25"/>
																		<children>
																			<template match="Questionnaire" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AnswerHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="SubModuleKey" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
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
																		<properties nowrap="set" width="98"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext=" Title:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties nowrap="set" width="30"/>
																		<children>
																			<template match="Questionnaire" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="ProtocolInfo" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="Title" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
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
																		<properties nowrap="set" width="122"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Principal Investigator:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties nowrap="set" width="25"/>
																		<children>
																			<template match="Questionnaire" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="ProtocolInfo" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="Investigator" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="Fullname" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
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
											<newline/>
											<newline/>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="             "/>
												</children>
											</paragraph>
											<newline/>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="             "/>
												</children>
											</paragraph>
											<newline/>
											<newline/>
											<newline/>
										</children>
									</conditionbranch>
									<conditionbranch xpath="/Questionnaire/ProposalInfo">
										<children>
											<table>
												<properties border="0" width="100%"/>
												<styles font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties nowrap="set" width="20"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Proposal Number:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties nowrap="set" width="30"/>
																		<children>
																			<template match="Questionnaire" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="AnswerHeader" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="ModuleKey" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
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
																		<properties nowrap="set" width="25"/>
																		<styles font-weight="bold"/>
																	</tablecell>
																	<tablecell>
																		<properties nowrap="set" width="25"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties nowrap="set" width="20"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Title:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties nowrap="set" width="30"/>
																		<children>
																			<template match="Questionnaire" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="ProposalInfo" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="Title" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
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
																		<properties nowrap="set" width="25"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Principal Investigator:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties nowrap="set" width="25"/>
																		<children>
																			<template match="Questionnaire" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="ProposalInfo" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="Investigator" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="Fullname" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
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
									</conditionbranch>
								</children>
							</condition>
							<paragraph paragraphtag="p">
								<children>
									<table>
										<properties border="0" width="100%"/>
										<children>
											<tablebody>
												<children>
													<tablerow>
														<children>
															<tablecell>
																<children>
																	<template match="Questionnaire" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="Questions" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="../UserOption/UserOptionsInfo/PrintAnswers  = &apos;No&apos;">
																								<children>
																									<template match="QuestionInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="not(  Question  =  preceding-sibling::QuestionInfo[1]/Question )">
																														<children>
																															<newline/>
																															<list>
																																<properties type="square"/>
																																<children>
																																	<listrow>
																																		<children>
																																			<template match="Question" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</listrow>
																																</children>
																															</list>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</template>
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
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="/Questionnaire/UserOption/UserOptionsInfo/PrintAnsweredQuestionsOnly = &apos;Yes&apos;">
																				<children>
																					<template match="Questionnaire" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="Questions" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="QuestionInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="count(   AnswerInfo   ) &gt; 0">
																														<children>
																															<template match="AnswerInfo" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath="string-length(  Answer  )  &gt; 0">
																																				<children>
																																					<list>
																																						<properties type="square"/>
																																						<children>
																																							<listrow>
																																								<children>
																																									<template match="Answer" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="5"/>
																																										<children>
																																											<content>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</template>
																																								</children>
																																							</listrow>
																																						</children>
																																					</list>
																																				</children>
																																			</conditionbranch>
																																		</children>
																																	</condition>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="/Questionnaire/UserOption/UserOptionsInfo/PrintAnsweredQuestionsOnly = &apos;No&apos;">
																				<children>
																					<template match="Questionnaire" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<template match="Questions" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="1"/>
																								<children>
																									<template match="QuestionInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<list>
																												<children>
																													<listrow/>
																												</children>
																											</list>
																											<text fixtext="           "/>
																											<template match="Question" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																											<condition>
																												<children>
																													<conditionbranch xpath="count(  AnswerInfo  )  &gt; 0">
																														<children>
																															<template match="AnswerInfo" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<newline/>
																																	<text fixtext="           "/>
																																	<template match="Answer" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<content>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</template>
																								</children>
																							</template>
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
												</children>
											</tablebody>
										</children>
									</table>
								</children>
							</paragraph>
						</children>
					</template>
					<newline/>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts>
		<children>
			<globaltemplate match="Questionnaire" matchtype="schemagraphitem">
				<children>
					<template match="Questionnaire" matchtype="schemagraphitem">
						<editorproperties elementstodisplay="5"/>
						<children>
							<content/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</globalparts>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="landscape" paperheight="8.5in" papermarginbottom="0.3in" papermarginleft="0.8in" papermarginright="0.8in" papermargintop="0.3in" paperwidth="11in"/>
	</pagelayout>
	<designfragments/>
</structure>
