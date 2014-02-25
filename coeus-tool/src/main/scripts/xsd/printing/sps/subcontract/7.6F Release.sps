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
			<text fixtext="                                               ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="SUBAWARD&apos;S  RELEASE">
				<styles font-weight="bold" text-decoration="underline"/>
			</text>
			<newline/>
			<text fixtext="                                               "/>
			<newline/>
			<text fixtext="                                               Subaward No."/>
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
			<newline/>
			<text fixtext="Pursuant to the terms of a Subaward No. "/>
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
			<text fixtext=" and in consideration of the sum of $"/>
			<text fixtext=" "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractAmountInfo"/>
						<children>
							<template>
								<match match="ObligatedAmount"/>
								<children>
									<xpath allchildren="1"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<text fixtext=" Dollars which has been or is to be paid under the said Subaward  to "/>
			<text fixtext=" "/>
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
			<text fixtext=" "/>
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
			<text fixtext=" "/>
			<text fixtext=" (hereinafter called the Subawardee) or its assignees, if any, the Subawardee, upon payment of the said sum by MASSACHUSETTS INSTITUTE OF TECHNOLOGY (hereinafter called MIT) does remise, release, and discharge MIT and the Government, its officers, agents and employees, of and from all liabilities, obligations, claims and demands whatsoever under or arising from the said Subaward, except:
"/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="Specified claims in stated amounts, or in estimated amounts where the amounts are not susceptible of exact statement by the Subawardee, as follows:

"/>
			<newline/>
			<newline/>
			<text fixtext="	    Claims, together with reasonable expenses incidental thereto, based upon the liabilities of the 
	Subawardee to third  parties  arising out of the performance of the said Subcontract, which are not 
	known to the Subcontractor on the date of execution of this release and of which the Subawardee gives 
	notice in writing to MIT within the period specified in the said Subaward."/>
			<newline/>
			<newline/>
			<text fixtext="

 	Claims for reimbursement of costs (other than expenses of the Subawardee by reason of its 
	indemnification of MIT and the Government against patent liability), including reasonable expenses 
	incidental thereto, incurred by the Subawardee under the provision of the said  Subawardee relating to 
	patents.

"/>
			<newline/>
			<newline/>
			<text fixtext="The Subawardee agrees, in connection with patent matters and with claims which are not released as set forth above, that will comply with all of the provisions relating to notification to MIT and the Government Contracting Officer and relating to the defense or prosecution of litigation.
"/>
			<newline/>
			<newline/>
			<text fixtext="IN WITNESS WHEREOF, this release has been executed this_____________day of _________, 20 ______.

	                                                                                  "/>
			<newline/>
			<text fixtext="                                                                                         ___________________________________	"/>
			<newline/>
			<text fixtext="                                                                                                                 	(Subawardee)
 "/>
			<newline/>
			<newline/>
			<text fixtext="                                                                  "/>
			<text fixtext="CERTIFICATE">
				<styles font-weight="bold" text-decoration="underline"/>
			</text>
			<text fixtext="
"/>
			<newline/>
			<newline/>
			<text fixtext="
I, _____________________________________ certify that I am the  _(official title ______________________________ of the corporation named as Subawardee in the foregoing release; that ______________________________________ who signed said release on behalf of the Subawardee was the _(official title) _________________________________ of said corporation; that said release was duly signed for and in behalf of said corporation by authority of its governing body and is within the scope of its corporate powers.

	"/>
			<newline/>
			<newline/>
			<text fixtext="(CORPORATE SEAL)                                                                                   	Signature _________________________
	                                                           "/>
			<newline/>
			<text fixtext="                                                                                                                       	Date _____________________________


"/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="Form 10.2F OSP RSO Revised 02/01   "/>
			<newline/>
			<text fixtext="Subawards: Federal Contract"/>
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
