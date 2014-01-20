/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.test;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.*;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.impl.config.property.JAXBConfigImpl;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;

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
    
    private static final String[] repositoryFiles = {   "repository.xml", 
                                                        "org/kuali/kra/award/repository-award.xml",  
                                                        //"org/kuali/kra/budget/repository-budget.xml",
                                                        "org/kuali/kra/coi/repository-coi.xml",
                                                        "org/kuali/kra/committee/repository-committee.xml",
                                                        //"org/kuali/kra/iacuc/repository-iacuc.xml",
                                                        "org/kuali/kra/institutionalproposal/repository-institutionalproposal.xml",
                                                        "org/kuali/kra/irb/repository-irb.xml",
                                                        "org/kuali/kra/negotiation/repository-negotiation.xml",
                                                        "org/kuali/kra/personmasschange/repository-personmasschange.xml",
                                                        "org/kuali/kra/proposaldevelopment/repository-proposaldevelopment.xml",
                                                        "org/kuali/kra/questionnaire/repository-questionnaire.xml",
                                                        //"org/kuali/kra/subaward/repository-subAward.xml",
                                                        "org/kuali/kra/timeandmoney/repository-timeandmoney.xml"
                                                    };

    private static Map<String, String> configFileParms;
    
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
        configFileParms = new HashMap(config.getProperties());
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
        dsUrl = configFileParms.get(DATASOURCE_URL_NAME);
        dsUser = configFileParms.get(DATASOURCE_USERNAME_NAME);
        dsPass =  configFileParms.get(DATASOURCE_PASSWORD_NAME);
        dsDriver = configFileParms.get(DATASOURCE_DRIVER_NAME);
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

    /**
     * Validate the XML against the DTD
     * @throws Exception
     */
    @Test
    public void xmlValidation() throws Exception {
        for(String repositoryFilePath : repositoryFiles) {
            validateXml(repositoryFilePath);
        }
    }

    @Test
    public void verifyTables() throws Exception {
        for(String repositoryFilePath : repositoryFiles) {
            verifyTableForRepository(repositoryFilePath);
        }        
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
        final URL repositoryUrl = getClass().getClassLoader().getResource(repositoryFilePath);

        LOG.debug(String.format("Found repository url %s\n", repositoryUrl));

        final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(true);
        saxParserFactory.setNamespaceAware(false);

        final SAXParser parser = saxParserFactory.newSAXParser();
        try {
            parser.parse(repositoryUrl.getFile(), handler);
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

        /**
         * Default Constructor
         */
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
            if (CLASS_DESCRIPTOR_NAME.equals(qName)) {
                setCurrentTableName(attributes.getValue(TABLE_ATTRIBUTE_NAME));
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
                locator.getLineNumber(), locator.getColumnNumber());
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
    private void validateXml(String repositoryFilePath) throws ParserConfigurationException, SAXException {
        LOG.debug(String.format("Starting XML validation"));        
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(true);
        saxParserFactory.setNamespaceAware(false);

        SAXParser parser = saxParserFactory.newSAXParser();
        try {
            parser.parse(findRepositoryFilePath(repositoryFilePath), new DefaultHandler());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Test should not encounter exceptions during parsing.");
        }
    }
    
    /**
     * This method finds the file path for a repository file
     * @param repositoryFilePath
     * @return
     */
    private String findRepositoryFilePath(String repositoryFilePath) {
        return getClass().getClassLoader().getResource(repositoryFilePath).getFile();
    }
}
