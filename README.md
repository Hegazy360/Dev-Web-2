# Dev-Web-2

Users Table Query :
----------------

CREATE TABLE `users` (
  
  `uname` varchar(10) NOT NULL,
  
  `password` varchar(10) NOT NULL,
  
  `email` varchar(50) default NULL,
  
  PRIMARY KEY  (`uname`),
  
  UNIQUE KEY `email` (`email`)
  
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
