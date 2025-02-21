-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `mail` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 'USER',
  `pseudo` VARCHAR(45) NOT NULL,
  `image` VARCHAR(255) NULL,
  `dietary` VARCHAR(45) NULL,
  `create` DATETIME NULL,
  `update` DATETIME NULL,
  PRIMARY KEY (`mail`),
  UNIQUE INDEX `pseudo_UNIQUE` (`pseudo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`recipe` (
  `id_recipe` INT NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NULL,
  `image` VARCHAR(255) NULL,
  `person` INT NULL,
  `state` VARCHAR(45) NULL DEFAULT 'tovalidation',
  `rate` INT NULL,
  `nb_rate` INT NULL,
  `create` DATETIME NULL,
  `update` DATETIME NULL,
  PRIMARY KEY (`id_recipe`),
  INDEX `user_mail_idx` (`mail` ASC) VISIBLE,
  CONSTRAINT `fk_recipe_user`
    FOREIGN KEY (`mail`)
    REFERENCES `mydb`.`user` (`mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`favorite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`favorite` (
  `mail` VARCHAR(45) NOT NULL,
  `favoriteable_type` VARCHAR(45) NOT NULL COMMENT 'Correspond à la table ou est le favoris',
  `favoriteable_id` INT NOT NULL COMMENT 'Correspond à l\'identifiant dans la table de item_type du favoris',
  `create` DATETIME NULL,
  PRIMARY KEY (`mail`, `favoriteable_type`, `favoriteable_id`),
  CONSTRAINT `fk_favorite_user`
    FOREIGN KEY (`mail`)
    REFERENCES `mydb`.`user` (`mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ingredient` (
  `id_ingredient` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `content` TEXT NULL,
  `create` DATETIME NULL,
  `update` DATETIME NULL,
  PRIMARY KEY (`id_ingredient`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stage` (
  `stage` INT NOT NULL,
  `id_recipe` INT NOT NULL,
  `content` TEXT NULL,
  `create` DATETIME NULL,
  `update` DATETIME NULL,
  PRIMARY KEY (`stage`, `id_recipe`),
  INDEX `id_recipe_idx` (`id_recipe` ASC) VISIBLE,
  CONSTRAINT `id_stage_recipe`
    FOREIGN KEY (`id_recipe`)
    REFERENCES `mydb`.`recipe` (`id_recipe`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`allergen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`allergen` (
  `id_allergen` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `content` TEXT NULL,
  `create` DATETIME NULL,
  `update` DATETIME NULL,
  PRIMARY KEY (`id_allergen`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ingredient_allergen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ingredient_allergen` (
  `id_allergen` INT NOT NULL,
  `id_ingredient` INT NOT NULL,
  PRIMARY KEY (`id_allergen`, `id_ingredient`),
  INDEX `id_ingredient_idx` (`id_ingredient` ASC) VISIBLE,
  CONSTRAINT `fk_ingredientallergen_allergen`
    FOREIGN KEY (`id_allergen`)
    REFERENCES `mydb`.`allergen` (`id_allergen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingredient_allergen_ingredient`
    FOREIGN KEY (`id_ingredient`)
    REFERENCES `mydb`.`ingredient` (`id_ingredient`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`measurement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`measurement` (
  `id_measurement` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id_measurement`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`recipe_ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`recipe_ingredient` (
  `id_recipe` INT NOT NULL,
  `id_ingredient` INT NOT NULL,
  `quantity` INT NULL,
  `id_measurment` INT NULL,
  PRIMARY KEY (`id_recipe`, `id_ingredient`),
  INDEX `id_ingredient_idx` (`id_ingredient` ASC) VISIBLE,
  INDEX `id_mesearment_idx` (`id_measurment` ASC) VISIBLE,
  CONSTRAINT `fk_recipeingredient_recipe`
    FOREIGN KEY (`id_recipe`)
    REFERENCES `mydb`.`recipe` (`id_recipe`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipeingredient_ingredient`
    FOREIGN KEY (`id_ingredient`)
    REFERENCES `mydb`.`ingredient` (`id_ingredient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipeingredient_mesearment`
    FOREIGN KEY (`id_measurment`)
    REFERENCES `mydb`.`measurement` (`id_measurement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`opinion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`opinion` (
  `id_recipe` INT NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  `rate` INT NULL,
  `comment` TEXT NULL,
  PRIMARY KEY (`id_recipe`, `mail`),
  INDEX `fk_user_idx` (`mail` ASC) VISIBLE,
  CONSTRAINT `fk_opinion_recipe`
    FOREIGN KEY (`id_recipe`)
    REFERENCES `mydb`.`recipe` (`id_recipe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_opinion_user`
    FOREIGN KEY (`mail`)
    REFERENCES `mydb`.`user` (`mail`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`request` (
  `id_ingredient` INT NOT NULL,
  `mail` VARCHAR(255) NOT NULL,
  `state` VARCHAR(45) NULL,
  `create` DATETIME NULL,
  `update` DATETIME NULL,
  PRIMARY KEY (`id_ingredient`, `mail`),
  INDEX `fk_mail_idx` (`mail` ASC) VISIBLE,
  CONSTRAINT `fk_request_ingredient`
    FOREIGN KEY (`id_ingredient`)
    REFERENCES `mydb`.`ingredient` (`id_ingredient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_user`
    FOREIGN KEY (`mail`)
    REFERENCES `mydb`.`user` (`mail`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
