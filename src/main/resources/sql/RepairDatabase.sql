

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema repair
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `repair` DEFAULT CHARACTER SET utf8 ;
USE `repair` ;

-- -----------------------------------------------------
-- Table `repair`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair`.`Role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `repair`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair`.`User` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `role_id` INT NOT NULL,
  `email` VARCHAR(25) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `repair`.`Role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair`.`Details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair`.`Details` (
  `user_id` INT NOT NULL,
  `balance` DOUBLE NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_Details_User1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `repair`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair`.`Status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair`.`Status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `repair`.`Request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair`.`Request` (
  `request_id` INT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NULL,
  `date` DATE NOT NULL,
  `status_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `master_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(300) NOT NULL,
  `payment` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`request_id`, `status_id`, `user_id`, `master_id`),
  INDEX `status_id` (`status_id` ASC) VISIBLE,
  INDEX `fk_Request_User1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Request_User2_idx` (`master_id` ASC) VISIBLE,
  CONSTRAINT `status_id`
    FOREIGN KEY (`status_id`)
    REFERENCES `repair`.`Status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Request_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `repair`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Request_User2`
    FOREIGN KEY (`master_id`)
    REFERENCES `repair`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair`.`Feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair`.`Feedback` (
  `request_id` INT NOT NULL,
  `text` VARCHAR(200) NOT NULL,
  `stars` INT NOT NULL,
  PRIMARY KEY (`request_id`),
  CONSTRAINT `fk_Feedback_Request1`
    FOREIGN KEY (`request_id`)
    REFERENCES `repair`.`Request` (`request_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

