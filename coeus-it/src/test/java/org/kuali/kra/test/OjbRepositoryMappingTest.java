/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.*;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.kuali.rice.core.impl.config.property.JAXBConfigImpl;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Unit Tests for validating an OJB repository XML file. The objective is to validate without initializing OJB. If OJB starts up and
 * the repository.xml file is bad, then it will fast-fail. This is an undesirable effect. What is needed is to know that OJB will
 * fail beforehand.
 * 
 * NOTE: This class was originally written to handle only repository.xml. Starting with Release 2.0, the modules have their own
 * repository file(s). This class needs significant modification before it can be considered complete
 * 
 */
public class OjbRepositoryMappingTest {
    
    private static final Log LOG = LogFactory.getLog(OjbRepositoryMappingTest.class);
    
    private static final String INTERNAL_TEST_CONFIG_FILE_PATH = "classpath:META-INF/kc-test-config.xml";
    
    // For XML parsing and validation
    private static final String CLASS_DESCRIPTOR_NAME = "class-descriptor";
    private static final String FIELD_DESCRIPTOR_NAME = "field-descriptor";
    private static final String CLASS_ATTRIBUTE_NAME = "class";
    private static final String TABLE_ATTRIBUTE_NAME = "table";
    private static final String DEFAULT_ATTRIBUTE_NAME = "name";
    private static final String COLUMN_ATTRIBUTE_NAME = "column";
    private static final String COLLECTION_DESCRIPTOR_NAME = "collection-descriptor";
    private static final String REFERENCE_DESCRIPTOR_NAME = "reference-descriptor";
    private static final String CLASS_REF_ATTRIBUTE_NAME = "class-ref";
    private static final String EL_CLASS_REF_ATTRIBUTE_NAME = "element-class-ref";
    private static final String FIELD_REF_ATTRIBUTE_NAME = "field-ref";
    private static final String FIELD_ID_REF_ATTRIBUTE_NAME = "field-id-ref";
    private static final String ID_ATTRIBUTE_NAME = "id";
    private static final String FOREIGN_KEY_NAME = "foreignkey";
    private static final String INVERSE_FOREIGN_KEY_NAME = "inverse-foreignkey";
    private static final String COLLECTION_CLASS_NAME = "collection-class";
    private static final String DATASOURCE_URL_NAME = "datasource.url";
    private static final String DATASOURCE_USERNAME_NAME = "datasource.username";
    private static final String DATASOURCE_PASSWORD_NAME = "datasource.password";
    private static final String DATASOURCE_DRIVER_NAME = "datasource.driver.name";
    private static final String EXTENT_CLASS = "extent-class";

    public static final String REPOSITORY_XML = "classpath:repository.xml";
    public static final String REPOSITORY_AWARD_XML = "classpath:org/kuali/kra/award/repository-award.xml";
    public static final String REPOSITORY_BUDGET_XML = "classpath:org/kuali/coeus/common/budget/impl/repository-budget.xml";
    public static final String REPOSITORY_COI_XML = "classpath:org/kuali/kra/coi/repository-coi.xml";
    public static final String REPOSITORY_COMMITTEE_XML = "classpath:org/kuali/kra/committee/repository-committee.xml";
    public static final String REPOSITORY_IACUC_XML = "classpath:org/kuali/kra/iacuc/repository-iacuc.xml";
    public static final String REPOSITORY_INSTITUTIONALPROPOSAL_XML = "classpath:org/kuali/kra/institutionalproposal/repository-institutionalproposal.xml";
    public static final String REPOSITORY_IRB_XML = "classpath:org/kuali/kra/irb/repository-irb.xml";
    public static final String REPOSITORY_NEGOTIATION_XML = "classpath:org/kuali/kra/negotiation/repository-negotiation.xml";
    public static final String REPOSITORY_PERSONMASSCHANGE_XML = "classpath:org/kuali/kra/personmasschange/repository-personmasschange.xml";
    public static final String REPOSITORY_PROPOSALDEVELOPMENT_XML = "classpath:org/kuali/coeus/propdev/impl/repository-proposaldevelopment.xml";
    public static final String REPOSITORY_QUESTIONNAIRE_XML = "classpath:org/kuali/coeus/common/questionnaire/impl/repository-questionnaire.xml";
    public static final String REPOSITORY_SUB_AWARD_XML = "classpath:org/kuali/kra/subaward/repository-subAward.xml";
    public static final String REPOSITORY_TIMEANDMONEY_XML = "classpath:org/kuali/kra/timeandmoney/repository-timeandmoney.xml";
    public static final String REPOSITORY_IACUC_COMMITTEE_XML = "classpath:org/kuali/kra/iacuc/repository-iacucCommittee.xml";
    public static final String REPOSITORY_COMMON_COMMITTEE_XML = "classpath:org/kuali/kra/common/committee/repository-commonCommittee.xml";

    private static Properties configFileParms;
    
    private String dsUrl;
    private String dsUser;
    private String dsPass;
    private String dsSchema;
    private String dsDriver;
    
    @SuppressWarnings("unchecked")
    @BeforeClass
    public static void loadParms() throws Exception {
        Config config = new JAXBConfigImpl(INTERNAL_TEST_CONFIG_FILE_PATH);
        config.parseConfig();
        configFileParms = new Properties();
        configFileParms.putAll(config.getProperties());
        configFileParms.putAll(System.getProperties());
    }
    
    @AfterClass
    public static void unloadParms() throws Exception {
        if(configFileParms != null) {
            configFileParms.clear();
            configFileParms = null;
        }
    }
    
    @Before
    public void setUp() throws Exception {
        dsUrl = (String) configFileParms.get(DATASOURCE_URL_NAME);
        dsUser = (String) configFileParms.get(DATASOURCE_USERNAME_NAME);
        dsPass =  (String) configFileParms.get(DATASOURCE_PASSWORD_NAME);
        dsDriver = (String) configFileParms.get(DATASOURCE_DRIVER_NAME);
        dsSchema = StringUtils.upperCase(dsUser);
        
        LOG.debug(String.format("dsUrl = %s\n", dsUrl));
        LOG.debug(String.format("dsUser = %s\n", dsUser));
        LOG.debug(String.format("dsDriver = %s\n", dsDriver));
        LOG.debug(String.format("dsSchema = %s\n", dsSchema));
    }

    /**
     * Tear down
     */
    @After
    public void tearDown() {
        dsUrl = null;
        dsUser = null;
        dsPass =  null;
        dsSchema = null;
        dsDriver = null;
    }

    @Test
    public void xmlValidationRepositoryXml() throws Exception {
        validateXml(REPOSITORY_XML);
    }
    @Test
    public void xmlValidationAwardXml() throws Exception {
        validateXml(REPOSITORY_AWARD_XML);
    }
    @Test
    public void xmlValidationBudgetXml() throws Exception {
        validateXml(REPOSITORY_BUDGET_XML);
    }
    @Test
    public void xmlValidationCoiXml() throws Exception {
        validateXml(REPOSITORY_COI_XML);
    }
    @Test
    public void xmlValidationCommitteeXml() throws Exception {
        validateXml(REPOSITORY_COMMITTEE_XML);
    }
    @Test
    public void xmlValidationIacucXml() throws Exception {
        validateXml(REPOSITORY_IACUC_XML);
    }
    @Test
    public void xmlValidationIpXml() throws Exception {
        validateXml(REPOSITORY_INSTITUTIONALPROPOSAL_XML);
    }
    @Test
    public void xmlValidationIrbXml() throws Exception {
        validateXml(REPOSITORY_IRB_XML);
    }
    @Test
    public void xmlValidationNegotiationXml() throws Exception {
        validateXml(REPOSITORY_NEGOTIATION_XML);
    }
    @Test
    public void xmlValidationPmcXml() throws Exception {
        validateXml(REPOSITORY_PERSONMASSCHANGE_XML);
    }
    @Test
    public void xmlValidationPdXml() throws Exception {
        validateXml(REPOSITORY_PROPOSALDEVELOPMENT_XML);
    }
    @Test
    public void xmlValidationQuestionXml() throws Exception {
        validateXml(REPOSITORY_QUESTIONNAIRE_XML);
    }

    @Test
    public void xmlValidationSubAwardXml() throws Exception {
        validateXml(REPOSITORY_SUB_AWARD_XML);
    }
    @Test
    public void xmlValidationTimeMoneyXml() throws Exception {
        validateXml(REPOSITORY_TIMEANDMONEY_XML);
    }

    @Test
    public void xmlValidationIacucCommitteeXml() throws Exception {
        validateXml(REPOSITORY_IACUC_COMMITTEE_XML);
    }
    @Test
    public void xmlValidationCommonCommitteeXml() throws Exception {
        validateXml(REPOSITORY_COMMON_COMMITTEE_XML);
    }

    @Test
    public void verifyTablesRepositoryXml() throws Exception {
        verifyTableForRepository(REPOSITORY_XML);
    }
    @Test
    public void verifyTablesAwardXml() throws Exception {
        verifyTableForRepository(REPOSITORY_AWARD_XML);
    }
    
    @Test
    public void verifyTablesBudgetXml() throws Exception {
        verifyTableForRepository(REPOSITORY_BUDGET_XML);
    }
    @Test
    public void verifyTablesCoiXml() throws Exception {
        verifyTableForRepository(REPOSITORY_COI_XML);
    }
    @Test
    public void verifyTablesCommitteeXml() throws Exception {
        verifyTableForRepository(REPOSITORY_COMMITTEE_XML);
    }
    @Test
    public void verifyTablesIacucXml() throws Exception {
        verifyTableForRepository(REPOSITORY_IACUC_XML);
    }
    @Test
    public void verifyTablesIpXml() throws Exception {
        verifyTableForRepository(REPOSITORY_INSTITUTIONALPROPOSAL_XML);
    }
    @Test
    public void verifyTablesIrbXml() throws Exception {
        verifyTableForRepository(REPOSITORY_IRB_XML);
    }
    @Test
    public void verifyTablesNegotiationXml() throws Exception {
        verifyTableForRepository(REPOSITORY_NEGOTIATION_XML);
    }
    @Test
    public void verifyTablesPmcXml() throws Exception {
        verifyTableForRepository(REPOSITORY_PERSONMASSCHANGE_XML);
    }
    @Test
    public void verifyTablesPdXml() throws Exception {
        verifyTableForRepository(REPOSITORY_PROPOSALDEVELOPMENT_XML);
    }
    @Test
    public void verifyTablesQuestionXml() throws Exception {
        verifyTableForRepository(REPOSITORY_QUESTIONNAIRE_XML);
    }

    @Test
    public void verifyTablesSubAwardXml() throws Exception {
        verifyTableForRepository(REPOSITORY_SUB_AWARD_XML);
    }
    @Test
    public void verifyTablesTimeMoneyXml() throws Exception {
        verifyTableForRepository(REPOSITORY_TIMEANDMONEY_XML);
    }

    @Test
    public void verifyTablesIacucCommitteeXml() throws Exception {
        verifyTableForRepository(REPOSITORY_IACUC_COMMITTEE_XML);
    }
    @Test
    public void verifyTablesCommonCommitteeXml() throws Exception {
        verifyTableForRepository(REPOSITORY_COMMON_COMMITTEE_XML);
    }

    protected Resource getFileResource(String sourceName) {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());

        return resourceLoader.getResource(sourceName);
    }

    /**
     * This method verifies the tables for a repository file
     * @throws SQLException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void verifyTableForRepository(String repositoryFilePath) throws SQLException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException {
        
        //loading the driver class so that DriverManager can find it
        Class.forName(dsDriver);
        final Connection conn = DriverManager.getConnection(dsUrl, dsUser, dsPass);
        final DefaultHandler handler = new TableValidationHandler(conn);

        LOG.debug(String.format("Starting XML validation"));
        final InputStream repositoryStream = getFileResource(repositoryFilePath).getInputStream();

        LOG.debug(String.format("Found repository url %s\n", repositoryFilePath));

        final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(true);
        saxParserFactory.setNamespaceAware(false);

        final SAXParser parser = saxParserFactory.newSAXParser();
        try {
            parser.parse(repositoryStream, handler);
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception e) {
            }
        }
    }

    /**
     * For parsing the repository.xml file and validating database table information as it goes. Primarily, this will verify that
     * tables exist in the database, and that the fields of each table exist as they are mapped in the repository.xml file.
     *
     */
    class TableValidationHandler extends DefaultHandler {
        private static final String COLUMN_NOT_FOUND_MESSAGE = "There is no column named %s in table or view %s\n";
        private static final String TABLE_NOT_FOUND_MESSAGE = "There is no table or view named %s\n";
        private Connection connection;
        private Locator locator;
        private String currentTableName;
        private Boolean extents;

        public TableValidationHandler(Connection conn) {
            connection = conn;
        }

        /**
         * Used for constructing <code>{@link SAXParseException}</code> instances
         *
         * @see org.xml.sax.helpers.DefaultHandler#setDocumentLocator(org.xml.sax.Locator)
         * @see org.xml.sax.SAXParseException
         */
        @Override
        public void setDocumentLocator(Locator locator) {
            super.setDocumentLocator(locator);
            this.locator = locator;
        }

        /**
         * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
         *      org.xml.sax.Attributes)
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXParseException {
            if(EXTENT_CLASS.equals(qName)) {
                setExtents(true);
            } else if (CLASS_DESCRIPTOR_NAME.equals(qName)) {
                setCurrentTableName(attributes.getValue(TABLE_ATTRIBUTE_NAME));
                if (getCurrentTableName() != null) {


                    // LOG.debug(String.format("Looking for table " + getCurrentTableName());
                    ResultSet results = null;
                    try {
                        results = getConnection().getMetaData().getTables(null, dsSchema, getCurrentTableName(),
                                new String[] { "TABLE", "VIEW" });

                        boolean found = false;
                        while (results.next() && !found) {
                            String tableNameResult = results.getString("TABLE_NAME");
                            if (getCurrentTableName().equalsIgnoreCase(tableNameResult)) {
                                found = true;
                            }
                        }

                        if (!found) {
                            LOG.debug(String.format(TABLE_NOT_FOUND_MESSAGE, attributes.getValue(TABLE_ATTRIBUTE_NAME)));
                            throw createSaxParseException(TABLE_NOT_FOUND_MESSAGE, attributes.getValue(TABLE_ATTRIBUTE_NAME));
                        }
                        else {
                            LOG.debug(String.format("Found table or view %s\n", getCurrentTableName()));
                        }
                    }
                    catch (Exception e) {
                        throw createSaxParseException(e, TABLE_NOT_FOUND_MESSAGE, attributes.getValue(TABLE_ATTRIBUTE_NAME));
                    }
                    finally {
                        if (results != null) {
                            try {
                                results.close();
                            }
                            catch (Exception e) {
                            }
                        }
                    }
                }

            }
            handleFieldDescriptor(qName, attributes);
        }

        /**
         *
         * @param qName
         * @param attributes
         * @throws SAXParseException
         */
        private void handleFieldDescriptor(String qName, Attributes attributes) throws SAXParseException {
            if (FIELD_DESCRIPTOR_NAME.equals(qName)) {
                String columnName = attributes.getValue(COLUMN_ATTRIBUTE_NAME).toUpperCase();

                ResultSet results = null;
                try {
                    results = getConnection().getMetaData().getColumns(null, dsSchema, getCurrentTableName(), columnName);

                    boolean found = false;
                    String columnNameResult = null;
                    while (results.next() && !found) {
                        columnNameResult = results.getString("COLUMN_NAME");
                        LOG.debug(String.format("Comparing %s to %s in table %s\n", columnName, columnNameResult, getCurrentTableName()));
                        if (columnName.equalsIgnoreCase(columnNameResult)) {
                            found = true;
                        }
                    }

                    if (!found) {
                        throw createSaxParseException(COLUMN_NOT_FOUND_MESSAGE, attributes.getValue(COLUMN_ATTRIBUTE_NAME),
                                getCurrentTableName());
                    }
                }
                catch (Exception e) {
                    throw createSaxParseException(e, COLUMN_NOT_FOUND_MESSAGE, attributes.getValue(COLUMN_ATTRIBUTE_NAME),
                            getCurrentTableName());
                }
                finally {
                    if (results != null) {
                        try {
                            results.close();
                        }
                        catch (Exception e) {
                        }
                    }
                }
            }

        }

        /**
         * Convenience method for creating a <code>{@link SAXParseException}</code> instance.
         *
         * @param e
         * @param pattern Message pattern in C-Style format
         * @param params parameters for the C-style format
         * @return SAXParseException
         */
        private SAXParseException createSaxParseException(Exception e, String pattern, String... params) {
            StringWriter writer = new StringWriter();
            new PrintWriter(writer).printf(pattern, (Object[]) params);

            return new SAXParseException(writer.toString() + "\n" + e.getMessage(), locator.getPublicId(), locator.getSystemId(),
                locator.getLineNumber(), locator.getColumnNumber(),e);
        }

        /**
         * Convenience method for creating a <code>{@link SAXParseException}</code> instance.
         *
         * @param pattern Message pattern in C-Style format
         * @param params parameters for the C-style format
         * @return SAXParseException
         */
        private SAXParseException createSaxParseException(String pattern, String... params) {
            StringWriter writer = new StringWriter();
            new PrintWriter(writer).printf(pattern, (Object[]) params);

            return new SAXParseException(writer.toString(), locator);
        }

        public String getCurrentTableName() {
            return currentTableName;
        }

        public void setCurrentTableName(String currentTableName) {
            this.currentTableName = currentTableName;
        }

        public Boolean isExtents() {
            return extents;
        }

        public void setExtents(Boolean extents) {
            this.extents = extents;
        }

        public Connection getConnection() {
            return connection;
        }

        public void setConnection(Connection connection) {
            this.connection = connection;
        }
    }
    
    /**
     * @param repositoryFilePath
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    private void validateXml(String repositoryFilePath) throws Exception {
        LOG.debug(String.format("Starting XML validation"));        
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(true);
        saxParserFactory.setNamespaceAware(false);

        SAXParser parser = saxParserFactory.newSAXParser();
        final InputStream repositoryStream = getFileResource(repositoryFilePath).getInputStream();
        parser.parse(repositoryStream, new DefaultHandler());
    }
}
