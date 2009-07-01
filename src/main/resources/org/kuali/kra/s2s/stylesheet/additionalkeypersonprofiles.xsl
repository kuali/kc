<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://apply.grants.gov/coeus/PersonProfile" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
                <fo:region-body margin-top="0.79in" margin-bottom="0.79in" />
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:template match="/">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set" />
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:block font-size="18pt" font-weight="bold" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:block text-align="center">Additional Profiles for Proposal&#160; <xsl:for-each select="n1:PersonProfileList">
                                    <xsl:for-each select="n1:ProposalNumber">
                                        <xsl:apply-templates />
                                    </xsl:for-each>
                                </xsl:for-each>
                            </fo:block>
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                        <xsl:for-each select="n1:PersonProfileList">
                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-header>
                                    <fo:table-row>
                                        <fo:table-cell border-after-width="0pt" border-before-width="0pt" border-end-width="0pt" border-start-width="0pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block />
                                        </fo:table-cell>
                                        <fo:table-cell border-after-width="0pt" border-before-width="0pt" border-end-width="0pt" border-start-width="0pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block />
                                        </fo:table-cell>
                                        <fo:table-cell border-after-width="0pt" border-before-width="0pt" border-end-width="0pt" border-start-width="0pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block />
                                        </fo:table-cell>
                                        <fo:table-cell border-after-width="0pt" border-before-width="0pt" border-end-width="0pt" border-start-width="0pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block />
                                        </fo:table-cell>
                                        <fo:table-cell border-before-width="0pt" border-end-width="0pt" border-start-width="0pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block />
                                        </fo:table-cell>
                                        <fo:table-cell border-before-width="0pt" border-end-width="0pt" border-start-width="0pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block />
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-header>
                                <fo:table-body>
                                    <xsl:for-each select="n1:ExtraKeyPerson">
                                        <fo:table-row>
                                            <fo:table-cell border-before-width="0pt" border-start-width="0pt" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">Name</fo:inline>:</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-before-width="0pt" border-start-width="0pt" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>&#160;<xsl:for-each select="n1:Name">
                                                        <xsl:for-each select="n1:FirstName">
                                                            <xsl:apply-templates />
                                                        </xsl:for-each>&#160;<xsl:for-each select="n1:LastName">
                                                            <xsl:apply-templates />
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-after-width="0pt" border-before-width="0pt" border-end-width="0pt" border-start-width="0pt" display-align="before" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">Title</fo:inline>:</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-before-width="0pt" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <xsl:for-each select="n1:Title">
                                                        <xsl:apply-templates />
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-before-width="0pt" display-align="before" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">Role</fo:inline>:</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-before-width="0pt" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <xsl:for-each select="n1:ProjectRole">
                                                        <xsl:apply-templates />
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                        <fo:table-row>
                                            <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block />
                                            </fo:table-cell>
                                            <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block />
                                            </fo:table-cell>
                                            <fo:table-cell border-after-width="0pt" border-before-width="0pt" border-end-width="0pt" border-start-width="0pt" display-align="before" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">Address</fo:inline>:</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <xsl:for-each select="n1:Address">
                                                        <xsl:for-each select="n1:Street1">
                                                            <xsl:apply-templates />
                                                        </xsl:for-each>
                                                        <fo:block>
                                                            <fo:leader leader-pattern="space" />
                                                        </fo:block>
                                                        <xsl:for-each select="n1:City">
                                                            <xsl:apply-templates />
                                                        </xsl:for-each>&#160;<xsl:for-each select="n1:State">
                                                            <xsl:apply-templates />
                                                        </xsl:for-each>&#160;<xsl:for-each select="n1:ZipCode">
                                                            <xsl:apply-templates />
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell display-align="before" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">Phone</fo:inline>: <fo:block>
                                                        <fo:leader leader-pattern="space" />
                                                    </fo:block>
                                                    <fo:inline font-weight="bold">Fax</fo:inline>:<fo:block>
                                                        <fo:leader leader-pattern="space" />
                                                    </fo:block>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <xsl:for-each select="n1:Phone">
                                                        <xsl:apply-templates />
                                                    </xsl:for-each>
                                                    <fo:block>
                                                        <fo:leader leader-pattern="space" />
                                                    </fo:block>
                                                    <xsl:for-each select="n1:Fax">
                                                        <xsl:apply-templates />
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                        <fo:table-row>
                                            <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block />
                                            </fo:table-cell>
                                            <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block />
                                            </fo:table-cell>
                                            <fo:table-cell border-after-width="0pt" border-before-width="0pt" border-end-width="0pt" border-start-width="0pt" display-align="before" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">Organization</fo:inline>:</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <xsl:for-each select="n1:OrganizationName">
                                                        <xsl:apply-templates />
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell display-align="before" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">Email</fo:inline>:</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <xsl:for-each select="n1:Email">
                                                        <xsl:apply-templates />
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                        <fo:table-row>
                                            <fo:table-cell border-after-width="4pt" height="23pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block />
                                            </fo:table-cell>
                                            <fo:table-cell border-after-width="4pt" height="23pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block />
                                            </fo:table-cell>
                                            <fo:table-cell border-after-width="0pt" border-before-width="0pt" border-end-width="0pt" border-start-width="0pt" display-align="before" height="23pt" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">Department</fo:inline>:</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell display-align="before" height="23pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <xsl:for-each select="n1:DepartmentName">
                                                        <xsl:apply-templates />
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell display-align="before" height="23pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">Division</fo:inline>:</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell display-align="before" height="23pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                <fo:block>
                                                    <xsl:for-each select="n1:DivisionName">
                                                        <xsl:apply-templates />
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>
                                </fo:table-body>
                            </fo:table>
                            <fo:block>
                                <fo:leader leader-pattern="space" />
                            </fo:block>
                        </xsl:for-each>
                        <fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                        <fo:block>
                            <xsl:text>&#xA;</xsl:text>
                        </fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
