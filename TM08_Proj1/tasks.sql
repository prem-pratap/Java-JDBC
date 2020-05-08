CREATE TABLE TBL_STOCK( product_id VARCHAR2(6) CONSTRAINT pid_pk PRIMARY KEY,
                        product_name VARCHAR2(20) CONSTRAINT pname UNIQUE,
                        quantity_on_hand NUMBER(11) CONSTRAINT quan_on_hand CHECK (quantity_on_hand >= 0),
                        product_unit_price NUMBER(11,2) CONSTRAINT prod_unit_price CHECK (product_unit_price >=0),
                        reorder_level NUMBER(11) CONSTRAINT reorder CHECK (reorder_level>=0));
                        
DESCRIBE tbl_stock;

CREATE TABLE tbl_sales(sales_id VARCHAR2(6) CONSTRAINT sale_id_pk PRIMARY KEY,
                        sales_date Date,
                        product_id VARCHAR2(6),
                        quantity_sold NUMBER(11) CONSTRAINT qsold CHECK (quantity_sold >= 0),
                        sales_price_per_unit NUMBER(11,2) CONSTRAINT sale_price CHECK (sales_price_per_unit >= 0),
                         CONSTRAINT pid_fk FOREIGN KEY (product_id) REFERENCES tbl_stock(product_id));
DESCRIBE tbl_sales;






INSERT INTO  TBL_STOCK VALUES('RE1001','REDMI Note 3',20,12000,5);
INSERT INTO TBL_STOCK VALUES('ip1002','Iphone 5s',10,21000,2);
INSERT INTO TBL_STOCK VALUES('pa1003','Panasonic P55',55,5500,5);

CREATE SEQUENCE SEQ_SALES_ID START WITH 1000 INCREMENT BY 1;
CREATE SEQUENCE SEQ_PRODUCT_ID START WITH 1004 INCREMENT BY 1;

CREATE VIEW V_SALES_REPORT AS 
    SELECT sales_id,sales_date,product_id,product_name,quantity_sold,product_unit_price,sales_price_per_unit,
            (sales_price_per_unit-product_unit_price)Profit_amount
            FROM tbl_stock NATURAL JOIN tbl_sales
            ORDER BY Profit_amount DESC, sales_id ASC;
commit;

