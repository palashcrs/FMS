SET SEARCH_PATH=fms_schema_1

INSERT INTO EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES('Email','PUBLIC','Review');
INSERT INTO EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES('Email','PUBLIC','Decline');
INSERT INTO EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES('Email','PRIVATE','Review');
INSERT INTO EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES('Email','PRIVATE','Decline');

INSERT INTO EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES('CardNo','PUBLIC','Review');
INSERT INTO EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES('CardNo','PUBLIC','Decline');
INSERT INTO EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES('CardNo','PRIVATE','Review');
INSERT INTO EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES('CardNo','PRIVATE','Decline');

INSERT INTO EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES('Word','PRIVATE','Review');
INSERT INTO EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES('Word','PRIVATE','Decline');