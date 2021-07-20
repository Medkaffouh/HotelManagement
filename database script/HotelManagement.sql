--CREATE DATABASE HOTELMANAGEMENT

CREATE TABLE hotels (
    hotel_id      NUMBER(4),
    hotel_name    VARCHAR2(100) NOT NULL,
    hotel_adress  VARCHAR2(100) NOT NULL,
    hotel_zip     NUMBER(12) NOT NULL,
    phone_number  VARCHAR2(20) NOT NULL,
    star_number   NUMBER(1),
    CONSTRAINT hotel_id_pk PRIMARY KEY ( hotel_id ),
    CONSTRAINT hotel_star_number_fk FOREIGN KEY ( D_number )
        REFERENCES classes ( star_number )
);

CREATE TABLE customers (
    customer_id   NUMBER(6),
    first_name    VARCHAR2(25) NOT NULL,
    last_name     VARCHAR2(25) NOT NULL,
    adress        VARCHAR2(100),
    city          VARCHAR2(30) NOT NULL,
    zip           NUMBER(12),
    phone_number  VARCHAR2(20) NOT NULL,
    email         VARCHAR2(25) NOT NULL,
    CONSTRAINT customer_id_pk PRIMARY KEY ( customer_id )
);

CREATE TABLE reservation (
    res_id       NUMBER(6),
    date_in      DATE NOT NULL,
    date_out     DATE NOT NULL,
    customer_id  NUMBER(6),
    user_id      NUMBER(4),
    roomtype_id  NUMBER(4),
    room_id      NUMBER(4),
    CONSTRAINT res_id_pk PRIMARY KEY ( res_id ),
    CONSTRAINT res_customer_id_fk FOREIGN KEY ( customer_id )
        REFERENCES customers ( customer_id ),
    CONSTRAINT res_user_id_fk FOREIGN KEY ( user_id )
        REFERENCES users ( user_id ),
    CONSTRAINT res_roomtype_id_fk FOREIGN KEY ( roomtype_id )
        REFERENCES roomtypes ( roomtype_id ),
    CONSTRAINT res_room_id_fk FOREIGN KEY ( room_id )
        REFERENCES rooms ( room_id )
);

CREATE TABLE roomtypes (
    roomtype_id  NUMBER(4),
    type         VARCHAR2(50),
    CONSTRAINT roomtype_id_pk PRIMARY KEY ( roomtype_id )
);

CREATE TABLE rooms (
    room_id       NUMBER(4),
    status        NUMBER(1) NOT NULL,
    phone_number  VARCHAR2(20),
    roomtype_id   NUMBER(4),
    hotel_id      NUMBER(4),
    CONSTRAINT room_id_pk PRIMARY KEY ( room_id ),
    CONSTRAINT room_roomtype_id_fk FOREIGN KEY ( roomtype_id )
        REFERENCES roomtypes ( roomtype_id ),
    CONSTRAINT room_hotel_id_fk FOREIGN KEY ( hotel_id )
        REFERENCES hotels ( hotel_id ),
    CONSTRAINT check_status CHECK ( status = 0
                                    OR status = 1 )
);

CREATE TABLE users (
    user_id       NUMBER(4),
    first_name    VARCHAR2(25) NOT NULL,
    last_name     VARCHAR2(25) NOT NULL,
    phone_number  VARCHAR2(20),
    email         VARCHAR2(25) NOT NULL,
    dateofbirth   DATE,
    isadmin       NUMBER(1) NOT NULL,
    password      VARCHAR2(30) NOT NULL,
    qcheck        VARCHAR2(20) NOT NULL,
    admin_id      NUMBER(4),
    CONSTRAINT user_id_pk PRIMARY KEY ( user_id ),
    CONSTRAINT user_admin_id_fk FOREIGN KEY ( admin_id )
        REFERENCES users ( user_id ),
    CONSTRAINT ckeck_isadmin CHECK ( isadmin = 0
                                     OR isadmin = 1 )
);

CREATE TABLE classes (
    star_number NUMBER(1),
    CONSTRAINT star_number_pk PRIMARY KEY ( star_number )
);

CREATE TABLE bills (
    bill_id      NUMBER(6),
    price        NUMBER(8, 2),
    star_number  NUMBER(1),
    roomtype_id  NUMBER(4),
    CONSTRAINT bill_id_pk PRIMARY KEY ( bill_id ),
    CONSTRAINT bill_star_number_fk FOREIGN KEY ( star_number )
        REFERENCES classes ( star_number ),
    CONSTRAINT bill_roomtype_id_fk FOREIGN KEY ( roomtype_id )
        REFERENCES roomtypes ( roomtype_id )
);

COMMIT;
--CREATE SEQUENCES
CREATE SEQUENCE hotel_id_seq START WITH 10 INCREMENT BY 10;

CREATE SEQUENCE customer_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE reservation_seq START WITH 1000 INCREMENT BY 1;

CREATE SEQUENCE roomtype_id_seq START WITH 100 INCREMENT BY 10;

CREATE SEQUENCE user_seq START WITH 100 INCREMENT BY 1;

CREATE SEQUENCE rooms_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE bills_seq START WITH 4000 INCREMENT BY 1;

COMMIT;

--INSERT DATA TO TABLES
INSERT INTO classes VALUES ( 4 );
INSERT INTO classes VALUES ( 5 );

INSERT INTO hotels VALUES (
    hotel_id_seq.NEXTVAL,
    'Atlantic',
    '111 78th St.',
    33441,
    '+212-545925952',
    5
);

INSERT INTO roomtypes VALUES (
    roomtype_id_seq.NEXTVAL,
    'Single'
);

INSERT INTO roomtypes VALUES (
    roomtype_id_seq.NEXTVAL,
    'Double'
);

INSERT INTO roomtypes VALUES (
    roomtype_id_seq.NEXTVAL,
    'Queen'
);

INSERT INTO roomtypes VALUES (
    roomtype_id_seq.NEXTVAL,
    'King'
);

INSERT INTO roomtypes VALUES (
    roomtype_id_seq.NEXTVAL,
    '2-Bedroom'
);

INSERT INTO roomtypes VALUES (
    roomtype_id_seq.NEXTVAL,
    'Suite'
);

insert into rooms values(rooms_seq.nextval,0,'+212-500004343',100,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004244',110,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500002343',120,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500005354',130,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500006234',140,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004344',150,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500006233',100,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500007434',110,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500005434',120,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500007465',130,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003334',140,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500005743',150,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500006645',100,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003454',110,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500008655',120,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003456',130,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003433',140,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004322',150,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500006688',100,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500002323',110,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500002222',120,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003346',130,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500007866',140,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500002784',150,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004452',100,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004577',110,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003458',120,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004573',130,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003343',140,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500007763',150,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003377',100,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500008885',110,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500005688',120,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500006762',130,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004443',140,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003457',150,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003456',100,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003456',110,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500005777',120,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003873',130,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500003474',140,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500005376',150,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004585',100,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004347',110,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004688',120,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500004556',130,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500005678',140,10);
insert into rooms values(rooms_seq.nextval,0,'+212-500007427',150,10);
commit;

insert into bills values(bills_seq.nextval,1000,5,100);
insert into bills values(bills_seq.nextval,1000,5,110);
insert into bills values(bills_seq.nextval,2000,5,120);
insert into bills values(bills_seq.nextval,2100,5,130);
insert into bills values(bills_seq.nextval,3500,5,140);
insert into bills values(bills_seq.nextval,5000,5,150);
commit;

insert into users values(user_seq.nextval,'Mohamed','Kaffouh','+212-507843427','Mkaffouh@gmail.com','15-MAR-1999',1,'Ntic1234','Taza',null);
insert into users values(user_seq.nextval,'Adam','Nori','+212-507898657','Anori@gmail.com','23-MAR-1998',1,'Adam1234','Rabat',101);
commit;

alter table users
add active number(1) DEFAULT 0;

alter table users
add CONSTRAINT ckeck_active_user CHECK ( active = 0
                                     OR active = 1 );
                                     
                                     
alter table rooms
MODIFY status DEFAULT 0;


alter table customers
MODIFY email UNIQUE;