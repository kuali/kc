<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="subcontractXSD.xsd" workingxmlfile="" templatexmlfile="" xsltversion="2" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<match overwrittenxslmatch="/"/>
		<children>
			<newline/>
			<text fixtext="    "/>
			<paragraph paragraphtag="center">
				<children>
					<text/>
					<paragraph paragraphtag="center">
						<children>
							<text fixtext="Massachusetts Institute of Technology">
								<styles font-weight="bold"/>
							</text>
						</children>
					</paragraph>
				</children>
			</paragraph>
			<paragraph paragraphtag="center">
				<children>
					<text fixtext="          ">
						<styles font-weight="bold"/>
					</text>
					<paragraph paragraphtag="center">
						<children>
							<text fixtext="77 Massachusetts Avenue">
								<styles font-weight="bold"/>
							</text>
						</children>
					</paragraph>
					<newline/>
					<paragraph paragraphtag="center">
						<children>
							<paragraph paragraphtag="center">
								<children>
									<text fixtext="Cambridge, MA 02139-4307">
										<styles font-weight="bold"/>
									</text>
								</children>
							</paragraph>
						</children>
					</paragraph>
				</children>
			</paragraph>
			<newline/>
			<newline/>
			<text fixtext="                                                  "/>
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
			<text fixtext=" "/>
			<text fixtext=", "/>
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
										<match match="FirstName"/>
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
						<match match="SubcontractContacts"/>
						<children>
							<template>
								<match match="RolodexDetails"/>
								<children>
									<template>
										<match match="LastName"/>
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
			<text fixtext="Do you comply with FAR 52-217.7? i.e.: Have you submitted to your Governement Contracting Officer Anual certified indirect cost porposal."/>
			<newline/>
			<newline/>
			<text fixtext="                            O	    Comply"/>
			<newline/>
			<newline/>
			<text fixtext="                            O	    Non-Compliance (if not in compliance, attach rationale)"/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="If you comply, what years during the period of performance of this purchase order are on file?      \"/>
			<newline/>
			<newline/>
			<text fixtext="                            FY______"/>
			<newline/>
			<text fixtext="                            FY______"/>
			<newline/>
			<text fixtext="                            FY______"/>
			<newline/>
			<newline/>
			<text fixtext="Please forward copies of the appropriate anual certified indirect cost proposal which fall within the purchase order performace period."/>
			<newline/>
			<newline/>
			<text fixtext="Do you comply with the 15 percent rule contained in FAR 42.708(a)(2)(ii). i.e.: the cumulative unsettled indirect costs (on all Subcontracts subject to quick-closeout) do not excedd 15 percent of their total unsettled indirect costs allocable to cost-type contracts for that fyscal year."/>
			<newline/>
			<newline/>
			<text fixtext="                           O	    Comply
  "/>
			<newline/>
			<text fixtext="   "/>
			<newline/>
			<text fixtext="                          	O    	Non-Compliance (if not in compliance, attach rationale)"/>
			<newline/>
			<text fixtext="                                                              "/>
			<newline/>
			<text fixtext="Following receipt of the above information, MIT will review the information to determine if the Purchase Order meets the requirement of the Quick-Closeout procedures and contact you to finalyze negotiations of costs as defined per your final cumulative invoice."/>
			<newline/>
			<newline/>
			<text fixtext="Subcontractor :  "/>
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
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="BY:___________________________________________________________________________
              "/>
			<newline/>
			<text fixtext="             	(Signature of person authorized to sign)"/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="Name and title of signer:_______________________________________________________"/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="Date: ____/____/____"/>
			<newline/>
			<newline/>
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="0" paperheight="11in" papermarginbottom="0.39in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.39in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
