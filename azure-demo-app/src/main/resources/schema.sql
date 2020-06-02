CREATE TABLE stores (
  id int(11) NOT NULL,
  type varchar(100),
  name varchar(100),
  address varchar(100),
  address2 varchar(100),
  city varchar(100),
  state varchar(100),
  zip varchar(100),
  location longtext,
  hours longtext,
  services longtext,
  PRIMARY KEY (id));
