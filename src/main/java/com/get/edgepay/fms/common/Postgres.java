package com.get.edgepay.fms.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(value = "spring.datasource.driver-class-name", havingValue = "org.postgresql.Driver", matchIfMissing = false)
public @interface Postgres {

}
