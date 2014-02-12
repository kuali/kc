<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="subcontractXSD.xsd" workingxmlfile="" templatexmlfile="" xsltversion="2" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<match overwrittenxslmatch="/"/>
		<children>
			<newline/>
			<newline/>
			<text fixtext="                                                                    ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="                                    ">
				<styles font-weight="bold"/>
			</text>
			<paragraph paragraphtag="h1">
				<children>
					<text fixtext="                                     MIT">
						<styles font-weight="bold"/>
					</text>
				</children>
			</paragraph>
			<newline/>
			<text fixtext="Important: payment of the presumed final invoice under this purchase order is being withheld and cannot be paid until after this form is completed and returned to the undersigned.  Your prompt response is appreciated."/>
			<newline/>
			<newline/>
			<text fixtext="Date: ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="   "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="UserDetails"/>
						<children>
							<template>
								<match match="Today"/>
								<children>
									<xpath allchildren="1"/>
									<datepicker ownvalue="1" datatype="date"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
			<newline/>
			<text fixtext="To:">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="        "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="RequistionerName"/>
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
			<text fixtext="From: ">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="              E19-604"/>
			<newline/>
			<newline/>
			<text fixtext="Subject:   ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="    Closeout of Subaward No:  "/>
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
			<text fixtext="    MIT Account: "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="AccountNumber"/>
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
			<text fixtext="Reference:">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="  Subaward Expires On: "/>
			<text fixtext=" "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="EndDate"/>
								<children>
									<xpath allchildren="1"/>
									<datepicker ownvalue="1" datatype="date"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<text fixtext="       Unexpended Balance:"/>
			<newline/>
			<newline/>
			<text fixtext="                     Subawardee:  "/>
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
			<text fixtext="             "/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="                                    "/>
			<text/>
			<text fixtext="               ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="CLOSEOUT CHECKLIST">
				<styles font-weight="bold" text-decoration="underline"/>
			</text>
			<newline/>
			<text fixtext="                                         "/>
			<text fixtext="Complete BOTH SECTIONS and Sign Below">
				<styles font-style="italic" font-weight="bold" text-decoration="underline"/>
			</text>
			<newline/>
			<newline/>
			<text fixtext="Section I.">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<newline/>
			<text fixtext="Check one of the following:"/>
			<newline/>
			<text fixtext="       ____  Extension of this Subaward and/or further funding expected - requisition will follow.  Do Not Close out.  "/>
			<newline/>
			<newline/>
			<text fixtext="      ____  Final Invoice for this Subaward expected shortly.  Upon receipt, closeout requisition will follow."/>
			<newline/>
			<newline/>
			<text fixtext="      ____  This Subaward is complete.  No funding balance.  Close Out Subaward."/>
			<newline/>
			<newline/>
			<text fixtext="Section II.">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<newline/>
			<text fixtext="1.	    Have all deliverable items/services contracted for been delivered/performed?  Yes_______ No_______"/>
			<newline/>
			<text fixtext="        If No, please indicate what remains to be delivered/performed."/>
			<newline/>
			<newline/>
			<text fixtext="2.	    To your knowledge, have there been any  inventions or discoveries conceived or first actually reduced to "/>
			<newline/>
			<text fixtext="        practice under this subaward?  	Yes_______  No_______"/>
			<newline/>
			<text fixtext="        If Yes, please provide as detailed a listing as you can."/>
			<newline/>
			<newline/>
			<text fixtext="3.	    MIT retains title to property not consumed in the performance of the subaward which remains at the "/>
			<newline/>
			<text fixtext="        facility.  Such property may consist of equipment, hardware, components, materials, etc.  Property may "/>
			<newline/>
			<text fixtext="        have been acquired by purchase or it may have been furnished by MIT or the Government."/>
			<newline/>
			<text fixtext="                         "/>
			<newline/>
			<text fixtext="                           Was any said property acquired?  Yes_______  No________"/>
			<newline/>
			<text fixtext="                            Is any property so acquired still in the possession of the ?  Yes_______  No________"/>
			<newline/>
			<text fixtext="                            If Yes, please provide as detailed a listing as you can."/>
			<newline/>
			<newline/>
			<text fixtext="       _________________________________				                                      _________________                                               	       (Signature)								                                                                                   (Date)

	________________________________
	(Printed Name)
 
Form 10.2B OSP RSO Revised 02/01"/>
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
