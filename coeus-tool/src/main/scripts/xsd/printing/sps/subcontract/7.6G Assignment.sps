<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="subcontractXSD.xsd" workingxmlfile="" templatexmlfile="" xsltversion="2" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<match overwrittenxslmatch="/"/>
		<children>
			<paragraph paragraphtag="h1">
				<children>
					<text fixtext="MIT">
						<styles font-weight="bold"/>
					</text>
				</children>
			</paragraph>
			<text fixtext="                                            "/>
			<text fixtext="SUBAWARDEE&apos;S ASSIGNMENT OF REFUNDS">
				<styles font-weight="bold"/>
			</text>
			<text fixtext=","/>
			<newline/>
			<text fixtext="                                            "/>
			<text fixtext="REBATES, CREDITS AND OTHER AMOUNTS">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="                                      "/>
			<newline/>
			<text fixtext="                                            Subaward  No. "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="PONumber"/>
								<children>
									<xpath allchildren="1"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
			<newline/>
			<text fixtext="Pursuant to the terms of Subaward No. "/>
			<text fixtext=" "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="PONumber"/>
								<children>
									<xpath allchildren="1"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<text fixtext=" and in consideration of the reimbursement of costs and payment of fee (if applicable), as provided in the said Subaward and any assignment thereunder, the"/>
			<newline/>
			<text fixtext="               "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="SubcontractorName"/>
								<children>
									<xpath allchildren="1"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<template>
				<match match="SubContractData"/>
			</template>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractContacts"/>
						<children>
							<template>
								<match match="RolodexDetails"/>
								<children>
									<template>
										<match match="Pincode"/>
										<children>
											<xpath allchildren="1"/>
										</children>
									</template>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="(hereinafter called the Subawardee) does hereby:"/>
			<newline/>
			<newline/>
			<text fixtext="     1. 	Assign, transfer, set over and release to MASSACHUSETTS INSTITUTE OF TECHNOLOGY (hereinafter called     MIT), all right, title and interest to all refunds, rebates, credits and other amounts (including any interest thereon) arising out of the performance of the said Subaward, together with all the rights of action accrued or which may hereafter accrue thereunder."/>
			<newline/>
			<newline/>
			<text fixtext="2. 	Agree to take whatever action maybe necessary to effect prompt collection of all refunds, rebates, credits and other amounts (including any interest thereon) due or which may become due, and to promptly forward to MIT checks (made payable to MIT) for any proceeds so collected. The reasonable costs of any such action to effect collection shall constitute allowable costs when approved by MIT as stated in the said Subaward and maybe applied to reduce any amounts otherwise payable to MIT under the terms hereof."/>
			<newline/>
			<newline/>
			<text fixtext="3. 	Agree to cooperate fully with MIT as to any claim or suit in connection with refunds, rebates, credits or other amounts due (including any interest thereon); to execute any protest, pleading, application, power of attorney, or other papers in connection therewith; and to permit MIT to represent it at any hearing, trial, or other proceeding, arising out of such claim or suit."/>
			<newline/>
			<newline/>
			<text fixtext="IN WITNESS WHEREOF, this assignment has been executed this _____________day of ____________________, "/>
			<newline/>
			<text fixtext="20_______________."/>
			<newline/>
			<newline/>
			<text fixtext="                                                                                                        ___________________________________"/>
			<newline/>
			<text fixtext="                                                                                                        "/>
			<newline/>
			<text fixtext="                                                                                                                          (Subawardee)"/>
			<newline/>
			<newline/>
			<text fixtext="                                         "/>
			<newline/>
			<text fixtext="                                                                     "/>
			<text fixtext="CERTIFICATE">
				<styles font-weight="bold" text-decoration="underline"/>
			</text>
			<newline/>
			<newline/>
			<text fixtext="I, ________________________________________certify that I am the _(official title)____________________________ of the corporation named as Subawardee in the foregoing assignment; that _____________________________who signed said "/>
			<newline/>
			<text fixtext="assignment on behalf of the Subawardee was the _(official title)_______________________________of said corporation; that "/>
			<newline/>
			<text fixtext="said assignment was duly signed for and in behalf of said corporation by authority of its governing body and is within the scope of its corporate powers."/>
			<newline/>
			<newline/>
			<text fixtext="         (CORPORATE SEAL)	                                                                                 Signature __________________ "/>
			<newline/>
			<newline/>
			<text fixtext="                                                                                                                               Date ______________________"/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="Form 10.2G OSP RSO revised 02/00
Subaward: Federal Contracts"/>
			<newline/>
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
