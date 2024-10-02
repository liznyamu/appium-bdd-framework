package com.qa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private  static final Properties props = new Properties();
    TestUtils utils = new TestUtils();

    public Properties getProps() {
        String propsFileName = "config.properties";
        if(props.isEmpty()) {
            try (InputStream is = getClass().getClassLoader().getResourceAsStream(propsFileName)) {
                props.load(is);
            } catch (IOException e) {
                utils.log().fatal("Failed to load config properties. ABORT !!!\n" + e);
                throw new RuntimeException(e);
            }
        }
        return props;
    }
}
