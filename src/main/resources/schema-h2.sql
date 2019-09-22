create table if not exists PRODUCT_PRICE (
ID int not null primary key auto_increment,
PRODUCT VARCHAR(100),
PRICE NUMBER(3),
DESCRIPTION VARCHAR(100),
CREATE_TIME DATE,
MODIFY_TIME DATE
);