package com.rssoftware.fms.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(value = "spring.datasource.driver-class-name", havingValue = "oracle.jdbc.driver.OracleDriver", matchIfMissing = false)
public @interface Oracle {

}
