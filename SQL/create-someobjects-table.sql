CREATE TABLE IF NOT EXISTS someobjects (
  `id` INT NOT NULL AUTO_INCREMENT,
  `someString` VARCHAR(45) NULL,
  `someInt` int NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
INSERT INTO someobjects (someString,someInt) VALUES ('Default Data', 123);