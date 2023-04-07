-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema platzi-market
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema platzi-market
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `platzi-market` DEFAULT CHARACTER SET utf8 ;
USE `platzi-market` ;

-- -----------------------------------------------------
-- Table `platzi-market`.`CATEGORIAS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `platzi-market`.`CATEGORIAS` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platzi-market`.`PRODUCTOS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `platzi-market`.`PRODUCTOS` (
  `id_producto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `id_categoria` INT NOT NULL,
  `codigo_barras` VARCHAR(150) NULL,
  `precio_venta` DECIMAL(16,2) NULL,
  `cantidad_stock` INT NOT NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id_producto`),
  INDEX `fk_PRODUCTOS_CATEGORIAS_idx` (`id_categoria` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCTOS_CATEGORIAS`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `platzi-market`.`CATEGORIAS` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platzi-market`.`CLIENTES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `platzi-market`.`CLIENTES` (
  `id` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(40) NULL,
  `apellidos` VARCHAR(100) NULL,
  `celular` DECIMAL NULL,
  `direccion` VARCHAR(80) NULL,
  `correo_electronico` VARCHAR(70) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platzi-market`.`COMPRAS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `platzi-market`.`COMPRAS` (
  `id_compra` INT NOT NULL AUTO_INCREMENT,
  `id_cliente` VARCHAR(20) NOT NULL,
  `fecha` DATETIME NULL,
  `medio_pago` CHAR(1) NULL,
  `comentario` VARCHAR(300) NULL,
  `estado` CHAR(1) NULL,
  PRIMARY KEY (`id_compra`),
  INDEX `fk_COMPRAS_CLIENTES1_idx` (`id_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_COMPRAS_CLIENTES1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `platzi-market`.`CLIENTES` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platzi-market`.`COMPRAS_PRODUCTOS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `platzi-market`.`COMPRAS_PRODUCTOS` (
  `id_compra` INT NOT NULL,
  `id_producto` INT NOT NULL,
  `cantidad` INT NULL,
  `total` DECIMAL(16,2) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id_compra`, `id_producto`),
  INDEX `fk_COMPRAS_PRODUCTOS_COMPRAS1_idx` (`id_compra` ASC) VISIBLE,
  CONSTRAINT `fk_COMPRAS_PRODUCTOS_PRODUCTOS1`
    FOREIGN KEY (`id_producto`)
    REFERENCES `platzi-market`.`PRODUCTOS` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_COMPRAS_PRODUCTOS_COMPRAS1`
    FOREIGN KEY (`id_compra`)
    REFERENCES `platzi-market`.`COMPRAS` (`id_compra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
