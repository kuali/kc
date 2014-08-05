package org.kuali.coeus.s2sgen.impl.hash;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.s2sgen.api.hash.GrantApplicationHashService;
import org.springframework.stereotype.Component;

@Component("grantApplicationHashService")
public class GrantApplicationHashServiceImpl implements GrantApplicationHashService {
    @Override
    public String computeGrantFormsHash(String xml) {
        if (StringUtils.isBlank(xml)) {
            throw new IllegalArgumentException("xml is blank");
        }

        return GrantApplicationHash.computeGrantFormsHash(xml);
    }

    @Override
    public String computeAttachmentHash(byte[] attachment) {
        if (ArrayUtils.isEmpty(attachment)) {
            throw new IllegalArgumentException("attachment is null or empty");
        }

        return GrantApplicationHash.computeAttachmentHash(attachment);
    }
}
