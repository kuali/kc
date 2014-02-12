<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="common" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/common.namespace"/>
			<nspair prefix="nih" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
			<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
			<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="phs398schema.xsd" workingxmlfile="nihproposal.xml">
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
							<newline/>
							<text fixtext="BIOGRAPHICAL SKETCH">
								<styles font-family="Verdana" font-size="12pt" font-weight="bold" line-height="12pt"/>
							</text>
							<newline/>
							<newline/>
							<text fixtext="NIH provides a fillable PDF form for the Biographical Sketch.  ">
								<styles font-family="Verdana" font-size="10pt" line-height="12pt"/>
							</text>
							<newline/>
							<newline/>
							<text fixtext="Alternatively, you can create your own PDF file, using Adobe Acrobat.  ">
								<styles font-family="Verdana" font-size="10pt" line-height="12pt"/>
							</text>
							<newline/>
							<newline/>
							<text fixtext="There should be one biosketch for each key person, and all should be named &quot;biosketch.pdf&quot;.">
								<styles font-family="Verdana" font-size="10pt" line-height="12pt"/>
							</text>
							<newline/>
							<newline/>
							<text fixtext="After completion, the forms should be uploaded to Coeus as part of the Proposal Personnel screen.">
								<styles font-family="Verdana" font-size="10pt" line-height="12pt"/>
							</text>
							<newline/>
							<newline/>
							<text fixtext="To use the NIH fillable form, select the link below and save the file to your local machine, naming it biosketch.pdf.    ">
								<styles font-family="Verdana" font-size="10pt" line-height="12pt"/>
							</text>
							<newline/>
							<newline/>
							<text fixtext="The form is located at: ">
								<styles font-family="Verdana" font-size="10pt" line-height="12pt"/>
							</text>
							<link>
								<styles line-height="12pt"/>
								<children>
									<text fixtext="http://grants.nih.gov/grants/funding/phs398/phs398.html"/>
								</children>
								<action>
									<none/>
								</action>
								<bookmark/>
								<hyperlink>
									<fixtext value="http://grants.nih.gov/grants/funding/phs398/phs398.html"/>
								</hyperlink>
							</link>
							<text fixtext=".">
								<styles font-family="Verdana" font-size="10pt" line-height="12pt"/>
							</text>
							<newline/>
							<newline/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.79in" papermarginright="0.79in" papermargintop="0.79in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<template match="$XML" matchtype="schemasource"/>
				</children>
			</globaltemplate>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<template match="$XML" matchtype="schemasource"/>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
