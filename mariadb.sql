-- member
create table member (
  mno int primary key auto_increment,
  name varchar(15) not null,
  jumin VARCHAR (18) not null,

  userid VARCHAR (18) not null,
  passwd VARCHAR (18) not null,

  zipcode VARCHAR (7) not null,
  addr1 VARCHAR (50) not null,
  addr2 VARCHAR (50) not null,
  email VARCHAR (50) not null,
  mobile VARCHAR (13) not null,
  regdate datetime default CURRENT_TIMESTAMP
);

-- board
create table board (
  bno int primary key auto_increment,
  title VARCHAR(50) not null,
  userid VARCHAR(18) not null,
  regdate datetime default CURRENT_TIMESTAMP,
  thumbup int DEFAULT 0,
  views int DEFAULT 0,
  contents mediumtext not null
);

-- pds
create table pds (
  pno int primary key auto_increment,
  title VARCHAR(50) not null,
  userid VARCHAR(18) not null,
  regdate datetime default CURRENT_TIMESTAMP,
  thumbup int DEFAULT 0,
  views int DEFAULT 0,
  contents mediumtext not null,
  fname VARCHAR(50),
  fsize int DEFAULT 0,
  fdown int DEFAULT 0,
  ftype VARCHAR(10)
);


-- gallery
create table gallery (
  gno int primary key auto_increment,
  title VARCHAR(50) not null,
  userid VARCHAR(18) not null,
  regdate datetime default CURRENT_TIMESTAMP,
  thumbup int DEFAULT 0,
  views int DEFAULT 0,
  contents mediumtext not null,
  fname1 VARCHAR(50),
  fname2 VARCHAR(50),
  fname3 VARCHAR(50)
);


-- zipcode : 2013.02 지번주소(총 52144건)
create table zipcode (
  zipcode varchar(7),
  sido varchar(10),
  gugun varchar(25),
  dong varchar(35),
  ri varchar(50),
  bunji varchar(25),
  seq varchar(5)
);

select * from zipcode
where dong like '자양동';