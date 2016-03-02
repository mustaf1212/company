CREATE SCHEMA `companies` ;

CREATE TABLE `companies`.`companies` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `parent_id` INT,
  `name` VARCHAR(45) NOT NULL,
  `estimated_ernings` DOUBLE,
  PRIMARY KEY (`id`));

ALTER TABLE `companies`.`companies` 
ADD CONSTRAINT `fk_parent_id`
  FOREIGN KEY (`parent_id`)
  REFERENCES `companies`.`companies` (`id`);

INSERT INTO `companies`.`companies` (`name`, `estimated_ernings`) VALUES ('Company1', 25);
INSERT INTO `companies`.`companies` (`parent_id`, `name`, `estimated_ernings`) VALUES (1, 'Company2', 13);
INSERT INTO `companies`.`companies` (`parent_id`, `name`, `estimated_ernings`) VALUES (2, 'Company3', 5);
INSERT INTO `companies`.`companies` (`parent_id`, `name`, `estimated_ernings`) VALUES (1, 'Company4', 10);