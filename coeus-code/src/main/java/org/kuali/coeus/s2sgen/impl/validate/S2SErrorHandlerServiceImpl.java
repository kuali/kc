package org.kuali.coeus.s2sgen.impl.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.kuali.coeus.s2sgen.api.core.AuditError;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("s2SErrorHandlerService")
public class S2SErrorHandlerServiceImpl implements S2SErrorHandlerService, InitializingBean {

    private static final Log LOG = LogFactory.getLog(S2SErrorHandlerServiceImpl.class);

    private Map<String, AuditError> auditErrorMap = new HashMap<String, AuditError>();

    @Value("classpath:org/kuali/coeus/s2sgen/impl/validate/S2SErrorMessages.xml")
    private Resource errorFile;

    @Override
    public AuditError getError(String key) {
        AuditError error = auditErrorMap.get(key);
        AuditError defaultError = new AuditError(AuditError.NO_FIELD_ERROR_KEY, key + " is not valid", AuditError.GG_LINK);
        return error == null ? defaultError : error;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadErrors(errorFile);
    }

    protected void loadErrors(Resource errorMapResource) throws ParserConfigurationException, IOException, SAXException {
        try (InputStream stream = errorMapResource.getInputStream()) {
            org.w3c.dom.Document errorsDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            Document document = new DOMBuilder().build(errorsDocument);
            Element root = document.getRootElement();
            for (Element errorElement : (List<Element>) root.getChildren("Error")) {
                String errorKey = errorElement.getChildTextTrim("ErrorKey");
                String messageKey = errorElement.getChildTextTrim("MessageKey");
                String errorMessage = errorElement.getChildTextTrim("Message");
                String errorFixLink = errorElement.getChildTextTrim("FixLink");
                errorFixLink = errorFixLink == null || errorFixLink.equals("") ? AuditError.GG_LINK : errorFixLink;
                AuditError s2sError = new AuditError(errorKey == null ? AuditError.NO_FIELD_ERROR_KEY : errorKey, errorMessage, errorFixLink);
                auditErrorMap.put(messageKey, s2sError);
            }
        }
    }
}
