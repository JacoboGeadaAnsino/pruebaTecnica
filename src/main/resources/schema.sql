drop table if exists PRICES;

create table prices (
	BRAND_ID NUMBER,
	START_DATE TIMESTAMP,
	END_DATE TIMESTAMP,
	PRICE_LIST NUMBER,
	PRODUCT_ID NUMBER,
	PRIORITY NUMBER,
	PRICE DECIMAL,
	CURR VARCHAR(10),
	PRIMARY KEY (BRAND_ID, PRODUCT_ID, PRICE_LIST)
);

drop index if exists idx_brand_product;

create index idx_brand_product ON prices (BRAND_ID, PRODUCT_ID, PRICE_LIST);
