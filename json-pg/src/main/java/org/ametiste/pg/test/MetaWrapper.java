package org.ametiste.pg.test;

import java.util.Map;

public class MetaWrapper {

    private Map<String, String> metadata;

    public MetaWrapper(Map<String, String> values) {

        this.metadata = values;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }
}
