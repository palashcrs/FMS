drop schema if exists fms_schema_1 cascade;
create schema fms_schema_1;


drop table if exists fms_schema_1.EDGEPAY_FMS_RULE_TYPE cascade;
create table fms_schema_1.EDGEPAY_FMS_RULE_TYPE
(
EDGEPAY_RULE_TYPE_ID 			serial primary key not null,
EDGEPAY_RULE_TYPE 				character varying(100),
EDGEPAY_ACCESS_MODE 			character varying(100),
EDGEPAY_ACTION 					character varying(100),
UNIQUE (EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION)
);


drop table if exists fms_schema_1.EDGEPAY_FMS_RULE cascade;
create table fms_schema_1.EDGEPAY_FMS_RULE
(
EDGEPAY_RULE_ID			  		serial primary key not null, 
EDGEPAY_RULE_TYPE_ID		  	integer REFERENCES fms_schema_1.EDGEPAY_FMS_RULE_TYPE(EDGEPAY_RULE_TYPE_ID),      
EDGEPAY_EMAIL              	  	character varying(100),   
EDGEPAY_CARD_NO            	  	character varying(100),
EDGEPAY_IP                		character varying(100),         
EDGEPAY_STR_ADDR          	  	character varying(100),																						        
EDGEPAY_WORD                	character varying(100),                                                           
EDGEPAY_LIMIT_CARD 	  			integer,                                          
EDGEPAY_LIMIT_IP 		  		integer,
EDGEPAY_LIMIT_MAX_AMT	  		decimal,
EDGEPAY_TIME_PERIOD 	  		character varying(100),
EDGEPAY_AVS_ZIP                	character varying(100),                                                           
EDGEPAY_AVS_STR_ADDR         	character varying(100),           
EDGEPAY_AVS_CITY                character varying(100),                                                           
EDGEPAY_AVS_STATE               character varying(100), 
EDGEPAY_AVS_RESULT              character varying(50),                                          
EDGEPAY_GEOLOC_IP               character varying(100), 
EDGEPAY_DEVICE_ID               character varying(100),                                                          
EDGEPAY_RULE_CREATED_BY 	  	character varying(100),
EDGEPAY_RULE_CREATION_TS 	  	timestamp without time zone
);


drop table if exists fms_schema_1.EDGEPAY_FMS_TRANSACTION cascade;					                                               
create table fms_schema_1.EDGEPAY_FMS_TRANSACTION
(    
EDGEPAY_FMS_TXN_ID				serial primary key not null, 
EDGEPAY_TXN_ID					character varying(100),
EDGEPAY_TXN_TYPE				character varying(100),
EDGEPAY_TXN_TOTAL_AMT			decimal,
EDGEPAY_TXN_STATUS				character varying(100),
EDGEPAY_FMS_TXN_STATUS			character varying(100),
EDGEPAY_TXN_EMAIL				character varying(100),
EDGEPAY_TXN_CARD_NO            	character varying(100),
EDGEPAY_TXN_IP                	character varying(100),         
EDGEPAY_TXN_STR_ADDR          	character varying(100),	
EDGEPAY_TXN_CUSTNAME          	character varying(100),	
EDGEPAY_TXN_AVS_ZIP             character varying(100),                                                           
EDGEPAY_TXN_AVS_STR_ADDR        character varying(100),           
EDGEPAY_TXN_AVS_CITY            character varying(100),                                                           
EDGEPAY_TXN_AVS_STATE           character varying(100),
EDGEPAY_TXN_GEOLOC_IP           character varying(100), 
EDGEPAY_TXN_DEVICE_ID           character varying(100),
EDGEPAY_TXN_NOTES 				character varying(300),
EDGEPAY_TXN_VIOLATED_RULES		text,
EDGEPAY_TXN_CREATED_BY			character varying(100),
EDGEPAY_TXN_CREATION_TS			timestamp without time zone,
EDGEPAY_TXN_UPDATED_BY			character varying(100),
EDGEPAY_TXN_UPDATED_TS			timestamp without time zone,
UNIQUE (EDGEPAY_TXN_ID)
);