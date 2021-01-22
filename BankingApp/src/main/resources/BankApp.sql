/*
Create all of our necessary tables
*/

CREATE TABLE customer (
  id NUMBER(10) PRIMARY KEY, --sequence this
  
  username VARCHAR2(15) UNIQUE,
  password VARCHAR2(15)
);

CREATE TABLE archived_customer (
  id NUMBER(10) PRIMARY KEY, --sequence this
  
  username VARCHAR2(15) UNIQUE,
  password VARCHAR2(15)
);

CREATE TABLE bankAcc (
    id NUMBER(10) PRIMARY KEY,  --sequence this
    
    cust_id NUMBER(10),
    
    balance NUMBER(15,2) DEFAULT(0)
);

CREATE TABLE archived_bankAcc (
    id NUMBER(10) PRIMARY KEY,  --sequence this
    
    cust_id NUMBER(10),
    
    balance NUMBER(15,2) DEFAULT(0)
);

CREATE TABLE transactions (
    id NUMBER(10) PRIMARY KEY,  --sequence this
    
    cust_id NUMBER(10),
    ba_id NUMBER(10),
    
    description VARCHAR2(50)
);

CREATE TABLE archived_transactions (
    id NUMBER(10) PRIMARY KEY,  --sequence this
    
    cust_id NUMBER(10),
    ba_id NUMBER(10),
    
    description VARCHAR2(50)
);

DROP TABLE transactions;
DROP TABLE bankacc;
DROP TABLE customer;

/*
Create foreign key relationships
*/

ALTER TABLE transactions ADD CONSTRAINT fk_trans_cust FOREIGN KEY
(cust_id) REFERENCES customer(id)
ON DELETE CASCADE;

ALTER TABLE transactions ADD CONSTRAINT fk_trans_ba FOREIGN KEY
(ba_id) REFERENCES bankacc(id)
ON DELETE CASCADE;

ALTER TABLE bankacc ADD CONSTRAINT fk_ba_cust FOREIGN KEY
(cust_id) REFERENCES customer(id)
ON DELETE CASCADE;

alter table bankacc drop constraint fk_ba_cust;
alter table transactions drop constraint fk_trans_cust;
alter table transactions drop constraint fk_trans_ba;

/*
Sequences
*/

CREATE SEQUENCE cust_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE ba_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE trans_seq
    START WITH 1
    INCREMENT BY 1;

/*
Triggers and Events
*/
--drop trigger archived_customer;

CREATE OR REPLACE TRIGGER archive_all_bankacc
BEFORE DELETE ON bankacc
FOR EACH ROW
BEGIN
    INSERT INTO archived_bankacc VALUES(:old.id, :old.cust_id, :old.balance);
END;

CREATE OR REPLACE TRIGGER archive_all_customer
BEFORE DELETE ON customer
FOR EACH ROW
BEGIN
    INSERT INTO archived_customer VALUES(:old.id, :old.username, :old.password);
END;

CREATE OR REPLACE TRIGGER archive_single_customer
BEFORE DELETE ON customer
FOR EACH ROW
BEGIN
    INSERT INTO archived_customer VALUES(:old.id, :old.username, :old.password);
END;

CREATE OR REPLACE TRIGGER archive_single_bankacc
BEFORE DELETE ON customer
BEGIN
    INSERT INTO archived_customer VALUES(:old.id, :old.username, :old.password);
END;
/*
Stored proceedures
*/

CREATE OR REPLACE PROCEDURE add_customer(username VARCHAR2, pass VARCHAR2)
IS
BEGIN
INSERT INTO customer VALUES (cust_seq.nextval, username, pass);
END;
/

CREATE OR REPLACE PROCEDURE add_bankaccount(cust_id NUMBER)
IS
BEGIN
INSERT INTO bankacc VALUES (ba_seq.nextval, cust_id, 0);
END;
/

CREATE OR REPLACE PROCEDURE add_transaction(cust_id NUMBER, bankacc_id NUMBER, description VARCHAR2)
IS
BEGIN
INSERT INTO transactions(id, cust_id, ba_id, description) VALUES (trans_seq.nextval, cust_id, bankacc_id, description);
END;
/


-- adding an admin to log into
CALL add_customer('admin','password');

call add_bankAccount(29);

select * from customer;


CALL add_bankaccount(30);


