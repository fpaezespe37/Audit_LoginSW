-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: screamsinc
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pecan_canton`
--

DROP TABLE IF EXISTS `pecan_canton`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pecan_canton` (
  `PEPAI_CODIGO` char(2) NOT NULL,
  `PEPRO_CODIGO` char(3) NOT NULL,
  `PECAN_CODIGO` char(3) NOT NULL,
  `PECAN_DESCRI` varchar(100) NOT NULL,
  PRIMARY KEY (`PEPAI_CODIGO`,`PEPRO_CODIGO`,`PECAN_CODIGO`),
  CONSTRAINT `FK_PR_PEPRO_PECAN` FOREIGN KEY (`PEPAI_CODIGO`, `PEPRO_CODIGO`) REFERENCES `pepro_provin` (`PEPAI_CODIGO`, `PEPRO_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para realizar la Gestion de los diferentes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pecan_canton`
--

LOCK TABLES `pecan_canton` WRITE;
/*!40000 ALTER TABLE `pecan_canton` DISABLE KEYS */;
INSERT INTO `pecan_canton` VALUES ('1','1','1','Quito'),('1','1','2','Mejia'),('1','1','3','Rumiñahui'),('1','1','4','Cayambe'),('1','2','5','Daule'),('1','2','6','Guayaquil');
/*!40000 ALTER TABLE `pecan_canton` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedir_direcc`
--

DROP TABLE IF EXISTS `pedir_direcc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedir_direcc` (
  `PEDIR_CODIGODIRECCION` varchar(10) NOT NULL,
  `PEPAI_CODIGO` char(2) NOT NULL,
  `PEPRO_CODIGO` char(3) NOT NULL,
  `PECAN_CODIGO` char(3) NOT NULL,
  `PEPAR_CODIGO` char(5) NOT NULL,
  `PEDIR_CALLEPRINCIPAL` varchar(50) DEFAULT NULL,
  `PEDIR_CALLESECUNDARIA` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PEDIR_CODIGODIRECCION`),
  KEY `FK_PR_PEPAR_PEDIR` (`PEPAI_CODIGO`,`PEPRO_CODIGO`,`PECAN_CODIGO`,`PEPAR_CODIGO`),
  CONSTRAINT `FK_PR_PEPAR_PEDIR` FOREIGN KEY (`PEPAI_CODIGO`, `PEPRO_CODIGO`, `PECAN_CODIGO`, `PEPAR_CODIGO`) REFERENCES `pepar_parroq` (`PEPAI_CODIGO`, `PEPRO_CODIGO`, `PECAN_CODIGO`, `PEPAR_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad conformada de la información de la Provincia, Canton';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedir_direcc`
--

LOCK TABLES `pedir_direcc` WRITE;
/*!40000 ALTER TABLE `pedir_direcc` DISABLE KEYS */;
INSERT INTO `pedir_direcc` VALUES ('1','1','1','1','1','Abdón Calderon','Marqueza de Solanda'),('2','1','2','5','3','Av. Libertadores','SM'),('3','1','2','6','5','Eloy Alfaro','Atahualpa'),('4','1','2','6','6','Campana','De la Plata'),('5','1','2','5','3','Pedro Pinto','Napo');
/*!40000 ALTER TABLE `pedir_direcc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peemp_emple`
--

DROP TABLE IF EXISTS `peemp_emple`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peemp_emple` (
  `PEEMP_CURP` varchar(18) NOT NULL,
  `PEDIR_CODIGODIRECCION` varchar(10) NOT NULL,
  `CODGENERO` int NOT NULL,
  `PEE_PEEMP_CURP` varchar(18) DEFAULT NULL,
  `CODNACION` char(3) NOT NULL,
  `PEESC_CODIGO` char(1) NOT NULL,
  `PESEX_CODIGO` char(1) NOT NULL,
  `CODDEPART` varchar(10) NOT NULL,
  `PEEMP_NOMBRE` varchar(35) NOT NULL,
  `PEEMP_APELLPATERNO` varchar(35) NOT NULL,
  `PEEMP_APELLMATERNO` varchar(35) NOT NULL,
  `PEEMP_SALARIO` decimal(10,2) NOT NULL,
  `PEEMP_FECNACIMIENTO` date NOT NULL,
  `PEEMP_CEDULA` varchar(10) NOT NULL,
  `PEEMP_FOTO` varchar(100) DEFAULT NULL,
  `PEEMP_EMAIL` varchar(100) NOT NULL,
  `PEEMP_TELEFONO` varchar(15) NOT NULL,
  PRIMARY KEY (`PEEMP_CURP`),
  KEY `FK_PR_PEEMP_PEDIR` (`PEDIR_CODIGODIRECCION`),
  KEY `FK_PR_PEEMP_PEEMP` (`PEE_PEEMP_CURP`),
  KEY `FK_PR_PEEST_PEEMP` (`PEESC_CODIGO`),
  KEY `FK_PR_PEGEN_PEEMP` (`CODGENERO`),
  KEY `FK_PR_PENAC_PEEMP` (`CODNACION`),
  KEY `FK_PR_PESEX_EMPLE` (`PESEX_CODIGO`),
  KEY `FK_TR_TEDEP_PEEMP` (`CODDEPART`),
  CONSTRAINT `FK_PR_PEEMP_PEDIR` FOREIGN KEY (`PEDIR_CODIGODIRECCION`) REFERENCES `pedir_direcc` (`PEDIR_CODIGODIRECCION`),
  CONSTRAINT `FK_PR_PEEMP_PEEMP` FOREIGN KEY (`PEE_PEEMP_CURP`) REFERENCES `peemp_emple` (`PEEMP_CURP`),
  CONSTRAINT `FK_PR_PEEST_PEEMP` FOREIGN KEY (`PEESC_CODIGO`) REFERENCES `peest_estciv` (`PEESC_CODIGO`),
  CONSTRAINT `FK_PR_PEGEN_PEEMP` FOREIGN KEY (`CODGENERO`) REFERENCES `pegen_gener` (`CODGENERO`),
  CONSTRAINT `FK_PR_PENAC_PEEMP` FOREIGN KEY (`CODNACION`) REFERENCES `penac_nacion` (`CODNACION`),
  CONSTRAINT `FK_PR_PESEX_EMPLE` FOREIGN KEY (`PESEX_CODIGO`) REFERENCES `pesex_sexo` (`PESEX_CODIGO`),
  CONSTRAINT `FK_TR_TEDEP_PEEMP` FOREIGN KEY (`CODDEPART`) REFERENCES `tedep_depart` (`CODDEPART`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad que almacena  la información de los empleados de la ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peemp_emple`
--

LOCK TABLES `peemp_emple` WRITE;
/*!40000 ALTER TABLE `peemp_emple` DISABLE KEYS */;
INSERT INTO `peemp_emple` VALUES ('1','2',3,NULL,'1','2','1','2','Diego','Padilla','Rodriguez',400.00,'2000-10-25','1725666760','fotoDiego.jpg','dipadilla@espe.edu.ec','0991234567'),('2','3',1,NULL,'1','1','1','1','Christian','Novoa','Chico',9250.00,'2001-01-19','1725793390','fotoMike.jpg','cpnovoa@espe.edu.ec','0991234533'),('3','4',2,'1','1','3','1','2','Ericks','Morales','Andino',800.00,'1997-03-19','1709071300','fotoRoz.jpg','ermorales5@espe.edu.ec','0991234569');
/*!40000 ALTER TABLE `peemp_emple` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peest_estciv`
--

DROP TABLE IF EXISTS `peest_estciv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peest_estciv` (
  `PEESC_CODIGO` char(1) NOT NULL,
  `PEESC_NOMB` char(30) NOT NULL,
  `PEESC_DESCRI` char(50) NOT NULL,
  PRIMARY KEY (`PEESC_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peest_estciv`
--

LOCK TABLES `peest_estciv` WRITE;
/*!40000 ALTER TABLE `peest_estciv` DISABLE KEYS */;
INSERT INTO `peest_estciv` VALUES ('1','Soltero','Soltero'),('2','Casado','Casado'),('3','Divorciado','Divorciado'),('4','Viudo','Viudo');
/*!40000 ALTER TABLE `peest_estciv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pefam_famili`
--

DROP TABLE IF EXISTS `pefam_famili`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pefam_famili` (
  `PEEMP_CURP` varchar(18) NOT NULL,
  `CODGENERO` int NOT NULL,
  `PEFAM_FAMNOMBRE` varchar(35) NOT NULL,
  `PEFAM_FAMAPELLPATERNO` varchar(35) NOT NULL,
  `PEFAM_FAMAPELLMATERNO` varchar(35) NOT NULL,
  `PEFAM_FAMFECNACIMIENTO` date NOT NULL,
  `PEFAM_PARENTESCO` varchar(15) NOT NULL,
  PRIMARY KEY (`PEEMP_CURP`),
  KEY `FK_PR_PEGEN_PEFAM` (`CODGENERO`),
  CONSTRAINT `FK_PR_PEEMP_PEFAM` FOREIGN KEY (`PEEMP_CURP`) REFERENCES `peemp_emple` (`PEEMP_CURP`),
  CONSTRAINT `FK_PR_PEGEN_PEFAM` FOREIGN KEY (`CODGENERO`) REFERENCES `pegen_gener` (`CODGENERO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pefam_famili`
--

LOCK TABLES `pefam_famili` WRITE;
/*!40000 ALTER TABLE `pefam_famili` DISABLE KEYS */;
INSERT INTO `pefam_famili` VALUES ('1',2,'Rosa','Granda','Espinoza','1998-12-25','Hermana'),('2',1,'Jose','Guamán','Chicaiza','2005-02-09','Hermano');
/*!40000 ALTER TABLE `pefam_famili` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pegen_gener`
--

DROP TABLE IF EXISTS `pegen_gener`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pegen_gener` (
  `CODGENERO` int NOT NULL,
  `DESCRIPCION` varchar(50) NOT NULL,
  PRIMARY KEY (`CODGENERO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pegen_gener`
--

LOCK TABLES `pegen_gener` WRITE;
/*!40000 ALTER TABLE `pegen_gener` DISABLE KEYS */;
INSERT INTO `pegen_gener` VALUES (1,'Masculino'),(2,'Femenino'),(3,'Prefiero no responder');
/*!40000 ALTER TABLE `pegen_gener` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penac_nacion`
--

DROP TABLE IF EXISTS `penac_nacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `penac_nacion` (
  `CODNACION` char(3) NOT NULL,
  `DESCRINAC` varchar(100) NOT NULL,
  PRIMARY KEY (`CODNACION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para el realizar el CRUD de la Nacionalida';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penac_nacion`
--

LOCK TABLES `penac_nacion` WRITE;
/*!40000 ALTER TABLE `penac_nacion` DISABLE KEYS */;
INSERT INTO `penac_nacion` VALUES ('1','Ecuatoriano'),('2','Colombiano'),('3','Venezolano'),('4','Argentino'),('5','Peruano');
/*!40000 ALTER TABLE `penac_nacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pepai_pais`
--

DROP TABLE IF EXISTS `pepai_pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pepai_pais` (
  `PEPAI_CODIGO` char(2) NOT NULL,
  `PEPAI_DESCRI` varchar(100) NOT NULL,
  PRIMARY KEY (`PEPAI_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para el realizar la gestion de paises ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pepai_pais`
--

LOCK TABLES `pepai_pais` WRITE;
/*!40000 ALTER TABLE `pepai_pais` DISABLE KEYS */;
INSERT INTO `pepai_pais` VALUES ('1','Ecuador');
/*!40000 ALTER TABLE `pepai_pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pepar_parroq`
--

DROP TABLE IF EXISTS `pepar_parroq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pepar_parroq` (
  `PEPAI_CODIGO` char(2) NOT NULL,
  `PEPRO_CODIGO` char(3) NOT NULL,
  `PECAN_CODIGO` char(3) NOT NULL,
  `PEPAR_CODIGO` char(5) NOT NULL,
  `PEPAR_DESCRI` varchar(100) NOT NULL,
  PRIMARY KEY (`PEPAI_CODIGO`,`PEPRO_CODIGO`,`PECAN_CODIGO`,`PEPAR_CODIGO`),
  CONSTRAINT `FK_PR_PECAN_PEPAR` FOREIGN KEY (`PEPAI_CODIGO`, `PEPRO_CODIGO`, `PECAN_CODIGO`) REFERENCES `pecan_canton` (`PEPAI_CODIGO`, `PEPRO_CODIGO`, `PECAN_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para realizar la gestion de las diferentes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pepar_parroq`
--

LOCK TABLES `pepar_parroq` WRITE;
/*!40000 ALTER TABLE `pepar_parroq` DISABLE KEYS */;
INSERT INTO `pepar_parroq` VALUES ('1','1','1','1','Amaguaña'),('1','1','1','2','Uyumbicho'),('1','2','5','3','Laurel'),('1','2','5','4','Limonal'),('1','2','6','5','Chongon'),('1','2','6','6','Febres Cordero'),('1','2','6','7','Chongon');
/*!40000 ALTER TABLE `pepar_parroq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pepro_provin`
--

DROP TABLE IF EXISTS `pepro_provin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pepro_provin` (
  `PEPAI_CODIGO` char(2) NOT NULL,
  `PEPRO_CODIGO` char(3) NOT NULL,
  `PEPRO_NOMBRE` varchar(100) NOT NULL,
  PRIMARY KEY (`PEPAI_CODIGO`,`PEPRO_CODIGO`),
  CONSTRAINT `FK_PR_PEPAI_PEPRO` FOREIGN KEY (`PEPAI_CODIGO`) REFERENCES `pepai_pais` (`PEPAI_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para realizar la gestion de las diferentes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pepro_provin`
--

LOCK TABLES `pepro_provin` WRITE;
/*!40000 ALTER TABLE `pepro_provin` DISABLE KEYS */;
INSERT INTO `pepro_provin` VALUES ('1','1','Pichincha'),('1','2','Guayas'),('1','3','Esmeraldas'),('1','4','Cotopaxi'),('1','5','Azuay'),('1','6','Bolivar'),('1','7','Loja'),('1','8','Los Rios');
/*!40000 ALTER TABLE `pepro_provin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pesex_sexo`
--

DROP TABLE IF EXISTS `pesex_sexo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pesex_sexo` (
  `PESEX_CODIGO` char(1) NOT NULL,
  `PESEX_NOMB` varchar(32) NOT NULL,
  `PESEX_DESCRI` varchar(50) NOT NULL,
  PRIMARY KEY (`PESEX_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para el realizar el CRUD del Sexo de un em';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pesex_sexo`
--

LOCK TABLES `pesex_sexo` WRITE;
/*!40000 ALTER TABLE `pesex_sexo` DISABLE KEYS */;
INSERT INTO `pesex_sexo` VALUES ('1','Hombre','Sexo de tipo Maculino'),('2','Mujer','Sexo de tipo Femenino');
/*!40000 ALTER TABLE `pesex_sexo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ptdir_dirigi`
--

DROP TABLE IF EXISTS `ptdir_dirigi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ptdir_dirigi` (
  `CODDEPART` varchar(10) NOT NULL,
  `PEEMP_CURP` varchar(18) NOT NULL,
  `FECHA` date NOT NULL,
  PRIMARY KEY (`CODDEPART`),
  KEY `FK_TR_PEEMPL_TEDIR` (`PEEMP_CURP`),
  CONSTRAINT `FK_TR_PEEMPL_TEDIR` FOREIGN KEY (`PEEMP_CURP`) REFERENCES `peemp_emple` (`PEEMP_CURP`),
  CONSTRAINT `FK_TR_TEDEP_TEDIR` FOREIGN KEY (`CODDEPART`) REFERENCES `tedep_depart` (`CODDEPART`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad que relaciona al empleado que dirige un departamento';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ptdir_dirigi`
--

LOCK TABLES `ptdir_dirigi` WRITE;
/*!40000 ALTER TABLE `ptdir_dirigi` DISABLE KEYS */;
INSERT INTO `ptdir_dirigi` VALUES ('1','1','2023-02-11'),('2','1','2023-02-11');
/*!40000 ALTER TABLE `ptdir_dirigi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tedep_depart`
--

DROP TABLE IF EXISTS `tedep_depart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tedep_depart` (
  `CODDEPART` varchar(10) NOT NULL,
  `NUMDEPTO` int NOT NULL,
  `NOMBREDEPTO` varchar(30) NOT NULL,
  PRIMARY KEY (`CODDEPART`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad  relacionada a la gestión y almacenamiento de inform';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tedep_depart`
--

LOCK TABLES `tedep_depart` WRITE;
/*!40000 ALTER TABLE `tedep_depart` DISABLE KEYS */;
INSERT INTO `tedep_depart` VALUES ('1',1,'Agrícola'),('2',2,'Minero'),('3',3,'Ganadero'),('4',4,'Forestal');
/*!40000 ALTER TABLE `tedep_depart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tefas_fases`
--

DROP TABLE IF EXISTS `tefas_fases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tefas_fases` (
  `CODFASE` varchar(10) NOT NULL,
  `CODPROYEC` varchar(10) NOT NULL,
  `NUMFASE` int NOT NULL,
  `DURACION` int NOT NULL,
  `FECHAINICIO` date NOT NULL,
  `PRESUPUESTO` int NOT NULL,
  `OBJETIVO` varchar(100) NOT NULL,
  `ALCANCE` varchar(100) NOT NULL,
  PRIMARY KEY (`CODFASE`),
  KEY `FK_TR_TEPRO_TEFAS` (`CODPROYEC`),
  CONSTRAINT `FK_TR_TEPRO_TEFAS` FOREIGN KEY (`CODPROYEC`) REFERENCES `tepro_proyec` (`CODPROYEC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tefas_fases`
--

LOCK TABLES `tefas_fases` WRITE;
/*!40000 ALTER TABLE `tefas_fases` DISABLE KEYS */;
INSERT INTO `tefas_fases` VALUES ('1','1',1,12,'2023-03-12',5000,'Criar 30 cabezas de ganado vacuno','Sin alcance'),('2','2',1,14,'2023-07-05',250000,'Extraer 1000 toneladas de oro','Sin alcance');
/*!40000 ALTER TABLE `tefas_fases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tepar_partic`
--

DROP TABLE IF EXISTS `tepar_partic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tepar_partic` (
  `CODPROYEC` varchar(10) NOT NULL,
  `PEEMP_CURP` varchar(18) NOT NULL,
  `HORAS` int NOT NULL,
  PRIMARY KEY (`CODPROYEC`),
  KEY `FK_TR_PEEMP_TEPAR` (`PEEMP_CURP`),
  CONSTRAINT `FK_TR_PEEMP_TEPAR` FOREIGN KEY (`PEEMP_CURP`) REFERENCES `peemp_emple` (`PEEMP_CURP`),
  CONSTRAINT `FK_TR_TEPRO_TEPAR` FOREIGN KEY (`CODPROYEC`) REFERENCES `tepro_proyec` (`CODPROYEC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad que registra a los empleados que se encuentran parti';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tepar_partic`
--

LOCK TABLES `tepar_partic` WRITE;
/*!40000 ALTER TABLE `tepar_partic` DISABLE KEYS */;
INSERT INTO `tepar_partic` VALUES ('1','3',180);
/*!40000 ALTER TABLE `tepar_partic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tepro_proyec`
--

DROP TABLE IF EXISTS `tepro_proyec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tepro_proyec` (
  `CODPROYEC` varchar(10) NOT NULL,
  `CODDEPART` varchar(10) NOT NULL,
  `NUMPROYECTO` int NOT NULL,
  `NOMBREPROYECTO` varchar(50) NOT NULL,
  PRIMARY KEY (`CODPROYEC`),
  KEY `FK_TR_TEDEP_TEPRO` (`CODDEPART`),
  CONSTRAINT `FK_TR_TEDEP_TEPRO` FOREIGN KEY (`CODDEPART`) REFERENCES `tedep_depart` (`CODDEPART`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad relacionada a la gestión y almacenamiento de la info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tepro_proyec`
--

LOCK TABLES `tepro_proyec` WRITE;
/*!40000 ALTER TABLE `tepro_proyec` DISABLE KEYS */;
INSERT INTO `tepro_proyec` VALUES ('1','1',1,'Cria de vacas'),('2','2',2,'Extracción de oro');
/*!40000 ALTER TABLE `tepro_proyec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xeest_estad`
--

DROP TABLE IF EXISTS `xeest_estad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xeest_estad` (
  `XEEST_CODIGO` char(1) NOT NULL,
  `XEEST_DESCRI` varchar(50) NOT NULL,
  PRIMARY KEY (`XEEST_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para gestionar el estado de las difetrente';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xeest_estad`
--

LOCK TABLES `xeest_estad` WRITE;
/*!40000 ALTER TABLE `xeest_estad` DISABLE KEYS */;
INSERT INTO `xeest_estad` VALUES ('1','Activo'),('2','Inactivo');
/*!40000 ALTER TABLE `xeest_estad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xeopc_opcio`
--

DROP TABLE IF EXISTS `xeopc_opcio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xeopc_opcio` (
  `XEVEN_CODIGO` varchar(2) NOT NULL,
  `XEOPC_CODIGO` char(3) NOT NULL,
  `XEOPC_DESCRI` varchar(100) NOT NULL,
  `XEOPC_URL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`XEVEN_CODIGO`,`XEOPC_CODIGO`),
  CONSTRAINT `FK_XR_XESIS_XEOPC` FOREIGN KEY (`XEVEN_CODIGO`) REFERENCES `xesis_ventan` (`XEVEN_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para realizar el registro de las diferente';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xeopc_opcio`
--

LOCK TABLES `xeopc_opcio` WRITE;
/*!40000 ALTER TABLE `xeopc_opcio` DISABLE KEYS */;
INSERT INTO `xeopc_opcio` VALUES ('1','10','Opciones','/xeopcOpcio/List'),('1','11','Sistemas','/xesisSiste/List'),('1','8','Usuarios','/peempEmple/List'),('1','9','Perfiles','/xeperPerfi/List'),('10','21','Reporte de Personal','/Reporte/reporte'),('2','1','Empleados','/peempEmple/List'),('2','3','Departamentos','/tedepDepart/List'),('3','12','Proyectos','/teproProyec/List'),('3','13','Tipo Proyecto',''),('4','4','Plan de cuentas',''),('4','5','Tipo de cuenta',''),('4','6','Cliente',''),('4','7','Bancos',''),('5','17','Usuarios por Perfil','/xeuxpUsupe/asignarRoles'),('5','18','Opciones por Perfil','/xeoxpOpcpe/opcionesPerfil'),('5','19','Cambiar Contraseña','/Password/cambiarPass'),('6','14','Rol de Pagos',''),('7','20','Planificar Proyectos',''),('8','15','Presupuesto',''),('8','16','Inventario','');
/*!40000 ALTER TABLE `xeopc_opcio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xeoxp_opcpe`
--

DROP TABLE IF EXISTS `xeoxp_opcpe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xeoxp_opcpe` (
  `XEVEN_CODIGO` varchar(2) NOT NULL,
  `XEOPC_CODIGO` char(3) NOT NULL,
  `XEPER_CODIGO` char(8) NOT NULL,
  `XEOXP_OPCPER` char(5) NOT NULL,
  `XEOXP_FECASI` date NOT NULL,
  `XEOXP_FECRET` date NOT NULL,
  PRIMARY KEY (`XEVEN_CODIGO`,`XEOPC_CODIGO`,`XEPER_CODIGO`,`XEOXP_OPCPER`),
  KEY `FK_XR_XEPER_XEOXP` (`XEPER_CODIGO`),
  CONSTRAINT `FK_XR_XEOPC_XEOXP` FOREIGN KEY (`XEVEN_CODIGO`, `XEOPC_CODIGO`) REFERENCES `xeopc_opcio` (`XEVEN_CODIGO`, `XEOPC_CODIGO`),
  CONSTRAINT `FK_XR_XEPER_XEOXP` FOREIGN KEY (`XEPER_CODIGO`) REFERENCES `xeper_perfi` (`XEPER_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para llevar el registro de las opciones qu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xeoxp_opcpe`
--

LOCK TABLES `xeoxp_opcpe` WRITE;
/*!40000 ALTER TABLE `xeoxp_opcpe` DISABLE KEYS */;
INSERT INTO `xeoxp_opcpe` VALUES ('1','10','1','11','2022-02-20','2022-02-20'),('1','11','1','12','2022-02-20','2022-02-20'),('1','8','1','9','2022-02-20','2022-02-20'),('1','9','1','10','2022-02-20','2022-02-20'),('10','21','1','27','2022-02-20','2022-02-20'),('2','1','1','1','2022-02-20','2022-02-20'),('2','3','1','3','2022-02-20','2022-02-20'),('2','3','2','4','2022-02-20','2022-02-20'),('3','12','1','13','2022-02-20','2022-02-20'),('3','12','2','15','2022-02-20','2022-02-20'),('3','13','1','14','2022-02-20','2022-02-20'),('3','13','2','16','2022-02-20','2022-02-20'),('4','4','1','5','2022-02-20','2022-02-20'),('4','5','1','6','2022-02-20','2022-02-20'),('4','6','1','7','2022-02-20','2022-02-20'),('4','7','1','8','2022-02-20','2022-02-20'),('5','17','1','20','2022-02-20','2022-02-20'),('5','18','1','21','2022-02-20','2022-02-20'),('5','19','1','22','2022-02-20','2022-02-20'),('5','19','2','23','2022-02-20','2022-02-20'),('6','14','1','17','2022-02-20','2022-02-20'),('7','20','1','25','2022-02-20','2022-02-20'),('7','20','2','26','2022-02-20','2022-02-20'),('8','15','1','18','2022-02-20','2022-02-20'),('8','16','1','19','2022-02-20','2022-02-20');
/*!40000 ALTER TABLE `xeoxp_opcpe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xeper_perfi`
--

DROP TABLE IF EXISTS `xeper_perfi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xeper_perfi` (
  `XEPER_CODIGO` char(8) NOT NULL,
  `XEPER_DESCRI` varchar(100) NOT NULL,
  `XEPER_OBSER` text,
  PRIMARY KEY (`XEPER_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para realizar la gestión de los diferentes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xeper_perfi`
--

LOCK TABLES `xeper_perfi` WRITE;
/*!40000 ALTER TABLE `xeper_perfi` DISABLE KEYS */;
INSERT INTO `xeper_perfi` VALUES ('1','Administrador',''),('2','Dirigente de departamento',''),('3','Empleado',NULL);
/*!40000 ALTER TABLE `xeper_perfi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xesis_siste`
--

DROP TABLE IF EXISTS `xesis_siste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xesis_siste` (
  `XESIS_CODIGO` char(1) NOT NULL,
  `XESIS_DESCRI` varchar(50) NOT NULL,
  PRIMARY KEY (`XESIS_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para realziar la gestión de los diferentes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xesis_siste`
--

LOCK TABLES `xesis_siste` WRITE;
/*!40000 ALTER TABLE `xesis_siste` DISABLE KEYS */;
INSERT INTO `xesis_siste` VALUES ('1','Seguridad'),('2','Personal'),('3','Proyectos'),('4','Finanzas');
/*!40000 ALTER TABLE `xesis_siste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xesis_ventan`
--

DROP TABLE IF EXISTS `xesis_ventan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xesis_ventan` (
  `XEVEN_CODIGO` varchar(2) NOT NULL,
  `XESIS_CODIGO` char(1) NOT NULL,
  `XEVEN_DESCRI` varchar(100) NOT NULL,
  `XEVEN_MENSAJ` varchar(100) NOT NULL,
  PRIMARY KEY (`XEVEN_CODIGO`),
  KEY `FK_SIS_VENT` (`XESIS_CODIGO`),
  CONSTRAINT `FK_SIS_VENT` FOREIGN KEY (`XESIS_CODIGO`) REFERENCES `xesis_siste` (`XESIS_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xesis_ventan`
--

LOCK TABLES `xesis_ventan` WRITE;
/*!40000 ALTER TABLE `xesis_ventan` DISABLE KEYS */;
INSERT INTO `xesis_ventan` VALUES ('1','1','CRUD','CRUD de seguridad'),('10','2','Reportes','Reportes de personal'),('11','3','Reportes','Reportes de proyectos'),('12','4','Reportes','Reportes de finanzas'),('2','2','CRUD','CRUD de personal'),('3','3','CRUD','CRUD de proyectos'),('4','4','CRUD','CRUD de finanzas'),('5','1','Procesos','Procesos de seguridad'),('6','2','Procesos','Procesos de personal'),('7','3','Procesos','Procesos de proyectos'),('8','4','Procesos','Procesos de finanzas'),('9','1','Reportes','Reportes de seguridad');
/*!40000 ALTER TABLE `xesis_ventan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xeusu_usuar`
--

DROP TABLE IF EXISTS `xeusu_usuar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xeusu_usuar` (
  `XEUSU_CODIGO` varchar(10) NOT NULL,
  `XEEST_CODIGO` char(1) NOT NULL,
  `PEEMP_CURP` varchar(18) NOT NULL,
  `XEUSU_PASWD` varchar(128) NOT NULL,
  `XEUSU_FECCRE` datetime NOT NULL,
  `XEUSU_FECMOD` datetime NOT NULL,
  `XEUSU_PIEFIR` varchar(100) NOT NULL,
  PRIMARY KEY (`XEUSU_CODIGO`),
  KEY `FK_XR_PEEMP_XEUSU` (`PEEMP_CURP`),
  KEY `FK_XR_XEEST_XEUSU` (`XEEST_CODIGO`),
  CONSTRAINT `FK_XR_PEEMP_XEUSU` FOREIGN KEY (`PEEMP_CURP`) REFERENCES `peemp_emple` (`PEEMP_CURP`),
  CONSTRAINT `FK_XR_XEEST_XEUSU` FOREIGN KEY (`XEEST_CODIGO`) REFERENCES `xeest_estad` (`XEEST_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad relacionada para gentionar los usuario que ingrsan a';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xeusu_usuar`
--

LOCK TABLES `xeusu_usuar` WRITE;
/*!40000 ALTER TABLE `xeusu_usuar` DISABLE KEYS */;
INSERT INTO `xeusu_usuar` VALUES ('1','1','1','827ccb0eea8a706c4c34a16891f84e7b','2023-02-11 01:27:12','2023-02-11 01:27:12','1'),('2','1','2','cd4af21b1af8a7f127c362b70f114254','2023-02-11 01:27:12','2023-02-11 01:27:12','1'),('3','1','3','81dc9bdb52d04dc20036dbd8313ed055','2023-02-11 01:27:12','2023-02-11 01:27:12','1');
/*!40000 ALTER TABLE `xeusu_usuar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xeuxp_usupe`
--

DROP TABLE IF EXISTS `xeuxp_usupe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xeuxp_usupe` (
  `XEUSU_CODIGO` varchar(10) NOT NULL,
  `XEPER_CODIGO` char(8) NOT NULL,
  `XEUXP_CODUSU` varchar(10) NOT NULL,
  `XEUXP_FECASI` date NOT NULL,
  `XEUXP_FECRET` date DEFAULT NULL,
  PRIMARY KEY (`XEUSU_CODIGO`,`XEPER_CODIGO`,`XEUXP_CODUSU`),
  KEY `FK_XR_XEPER_XEUXP` (`XEPER_CODIGO`),
  CONSTRAINT `FK_XR_XEPER_XEUXP` FOREIGN KEY (`XEPER_CODIGO`) REFERENCES `xeper_perfi` (`XEPER_CODIGO`),
  CONSTRAINT `FK_XR_XEUSU_XEUXP` FOREIGN KEY (`XEUSU_CODIGO`) REFERENCES `xeusu_usuar` (`XEUSU_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Entidad utilizada para realizar el registro de los diferente';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xeuxp_usupe`
--

LOCK TABLES `xeuxp_usupe` WRITE;
/*!40000 ALTER TABLE `xeuxp_usupe` DISABLE KEYS */;
INSERT INTO `xeuxp_usupe` VALUES ('1','1','1','2023-02-11',NULL),('2','1','2','2023-02-13','2023-02-13'),('3','3','3','2023-02-11',NULL);
/*!40000 ALTER TABLE `xeuxp_usupe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-25 21:55:34
