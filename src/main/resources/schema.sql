-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bddgmf
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bddgmf
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bddgmf` ;
USE `bddgmf` ;

-- -----------------------------------------------------
-- Table `bddgmf`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`user` (
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 'USER',
  `pseudo` VARCHAR(45),
  `image` VARCHAR(255) NULL,
  `create_time` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`email`),
  UNIQUE INDEX `pseudo_UNIQUE` (`pseudo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`recipe` (
  `id_recipe` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `title` VARCHAR(64) NOT NULL,
  `content` TEXT(10000) NULL,
  `image` VARCHAR(255) NULL,
  `person` INT NULL,
  `state` VARCHAR(45) NULL DEFAULT 'tovalidation',
  `rate` DECIMAL(10,2) NULL DEFAULT 0,
  `nb_rate` INT NULL DEFAULT 0,
  `create_time` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id_recipe`),
  INDEX `user_mail_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_recipe_user`
    FOREIGN KEY (`email`)
    REFERENCES `bddgmf`.`user` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`favorite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`favorite` (
  `email` VARCHAR(255) NOT NULL,
  `favoriteable_type` VARCHAR(45) NOT NULL COMMENT 'Correspond à la table ou est le favoris',
  `favoriteable_id` INT NOT NULL COMMENT 'Correspond à l\'identifiant dans la table de item_type du favoris',
  `create_time` DATETIME NULL,
  PRIMARY KEY (`email`, `favoriteable_type`, `favoriteable_id`),
  CONSTRAINT `fk_favorite_user`
    FOREIGN KEY (`email`)
    REFERENCES `bddgmf`.`user` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`ingredient` (
  `id_ingredient` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `content` TEXT(10000) NULL,
  `create_time` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id_ingredient`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`stage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`stage` (
  `stage` INT NOT NULL,
  `id_recipe` INT NOT NULL,
  `content` TEXT(10000) NOT NULL,
  PRIMARY KEY (`stage`, `id_recipe`),
  INDEX `id_recipe_idx` (`id_recipe` ASC) VISIBLE,
  CONSTRAINT `id_stage_recipe`
    FOREIGN KEY (`id_recipe`)
    REFERENCES `bddgmf`.`recipe` (`id_recipe`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`measurement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`measurement` (
  `id_measurement` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `type` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id_measurement`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`recipe_ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`recipe_ingredient` (
  `id_recipe` INT NOT NULL,
  `id_ingredient` INT NOT NULL,
  `quantity` INT NOT NULL,
  `id_measurement` INT NOT NULL,
  PRIMARY KEY (`id_recipe`, `id_ingredient`),
  INDEX `id_ingredient_idx` (`id_ingredient` ASC) VISIBLE,
  INDEX `id_mesearment_idx` (`id_measurement` ASC) VISIBLE,
  CONSTRAINT `fk_recipeingredient_recipe`
    FOREIGN KEY (`id_recipe`)
    REFERENCES `bddgmf`.`recipe` (`id_recipe`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipeingredient_ingredient`
    FOREIGN KEY (`id_ingredient`)
    REFERENCES `bddgmf`.`ingredient` (`id_ingredient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipeingredient_mesearment`
    FOREIGN KEY (`id_measurement`)
    REFERENCES `bddgmf`.`measurement` (`id_measurement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`opinion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`opinion` (
  `id_recipe` INT NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `rate` int NOT NULL,
  `comment` TEXT(10000) NULL,
  PRIMARY KEY (`id_recipe`, `email`),
  INDEX `fk_user_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_opinion_recipe`
    FOREIGN KEY (`id_recipe`)
    REFERENCES `bddgmf`.`recipe` (`id_recipe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_opinion_user`
    FOREIGN KEY (`email`)
    REFERENCES `bddgmf`.`user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`request` (
  `id_ingredient` INT NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `state` VARCHAR(45) NOT NULL DEFAULT 'tovalidate',
  `create_time` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id_ingredient`, `email`),
  INDEX `fk_mail_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_request_ingredient`
    FOREIGN KEY (`id_ingredient`)
    REFERENCES `bddgmf`.`ingredient` (`id_ingredient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_user`
    FOREIGN KEY (`email`)
    REFERENCES `bddgmf`.`user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`diet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`diet` (
  `id_diet` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `content` VARCHAR(255) NULL,
  PRIMARY KEY (`id_diet`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`diet_ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`diet_ingredient` (
  `id_diet` INT NOT NULL,
  `id_ingredient` INT NOT NULL,
  PRIMARY KEY (`id_diet`, `id_ingredient`),
  INDEX `fk_dietary_ingredient_ingredient_idx` (`id_ingredient` ASC) VISIBLE,
  CONSTRAINT `fk_dietary_ingredient_dietary`
    FOREIGN KEY (`id_diet`)
    REFERENCES `bddgmf`.`diet` (`id_diet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dietary_ingredient_ingredient`
    FOREIGN KEY (`id_ingredient`)
    REFERENCES `bddgmf`.`ingredient` (`id_ingredient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bddgmf`.`user_diet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`user_diet` (
  `email` VARCHAR(255) NOT NULL,
  `id_diet` INT NOT NULL,
  PRIMARY KEY (`email`),
  INDEX `fk_user_dietary_dietary_idx` (`id_diet` ASC) VISIBLE,
  CONSTRAINT `fk_user_dietary_user`
    FOREIGN KEY (`email`)
    REFERENCES `bddgmf`.`user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_dietary_dietary`
    FOREIGN KEY (`id_diet`)
    REFERENCES `bddgmf`.`diet` (`id_diet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `bddgmf` ;

-- -----------------------------------------------------
-- Placeholder table for view `bddgmf`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bddgmf`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `bddgmf`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bddgmf`.`view1`;
USE `bddgmf`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;