-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 22 juin 2021 à 12:38
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `stockmanagement`
--

-- --------------------------------------------------------

--
-- Structure de la table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `id_adress` int(11) NOT NULL AUTO_INCREMENT,
  `id_city` int(11) NOT NULL,
  `street` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `box` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_adress`),
  KEY `ID_City` (`id_city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `adress_users`
--

DROP TABLE IF EXISTS `adress_users`;
CREATE TABLE IF NOT EXISTS `adress_users` (
  `id_adress_users` int(11) NOT NULL AUTO_INCREMENT,
  `id_adress` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `typeAdress` enum('Private','Professional','billing','') NOT NULL,
  PRIMARY KEY (`id_adress_users`) USING BTREE,
  KEY `id_adress` (`id_adress`) USING BTREE,
  KEY `id_user` (`id_user`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `batchs`
--

DROP TABLE IF EXISTS `batchs`;
CREATE TABLE IF NOT EXISTS `batchs` (
  `id_batch` int(11) NOT NULL AUTO_INCREMENT,
  `id_products` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `uniPrice` int(11) NOT NULL,
  `numberBatch` int(11) NOT NULL,
  PRIMARY KEY (`id_batch`),
  KEY `ID_Products` (`id_products`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `brands`
--

DROP TABLE IF EXISTS `brands`;
CREATE TABLE IF NOT EXISTS `brands` (
  `id_brand` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(60) NOT NULL,
  PRIMARY KEY (`id_brand`),
  UNIQUE KEY `brand` (`brand`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id_categorie` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(60) NOT NULL,
  PRIMARY KEY (`id_categorie`),
  UNIQUE KEY `category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `cities`
--

DROP TABLE IF EXISTS `cities`;
CREATE TABLE IF NOT EXISTS `cities` (
  `id_city` int(11) NOT NULL AUTO_INCREMENT,
  `id_country` int(11) NOT NULL,
  `citie` varchar(60) NOT NULL,
  `postalCode` int(11) NOT NULL,
  PRIMARY KEY (`id_city`),
  KEY `ID_Country` (`id_country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `commandsuppliers`
--

DROP TABLE IF EXISTS `commandsuppliers`;
CREATE TABLE IF NOT EXISTS `commandsuppliers` (
  `id_commandSuppliers` int(11) NOT NULL AUTO_INCREMENT,
  `id_supplier` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `orderDate` date NOT NULL,
  PRIMARY KEY (`id_commandSuppliers`),
  KEY `ID_User` (`id_user`),
  KEY `ID_Supplier` (`id_supplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `commandsuppliers_batchs`
--

DROP TABLE IF EXISTS `commandsuppliers_batchs`;
CREATE TABLE IF NOT EXISTS `commandsuppliers_batchs` (
  `id_commandsuppliers_batchs` int(11) NOT NULL AUTO_INCREMENT,
  `id_commandSupplier` int(11) NOT NULL,
  `id_batch` int(11) NOT NULL,
  `costPrice` int(11) NOT NULL,
  `lotQuantity` int(11) NOT NULL,
  PRIMARY KEY (`id_commandsuppliers_batchs`),
  KEY `ID_Batch` (`id_batch`),
  KEY `ID_CommandSupplier` (`id_commandSupplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `countries`
--

DROP TABLE IF EXISTS `countries`;
CREATE TABLE IF NOT EXISTS `countries` (
  `id_country` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(60) NOT NULL,
  PRIMARY KEY (`id_country`),
  UNIQUE KEY `Country` (`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `documents`
--

DROP TABLE IF EXISTS `documents`;
CREATE TABLE IF NOT EXISTS `documents` (
  `id_document` int(11) NOT NULL AUTO_INCREMENT,
  `id_documentType` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `dateDocument` date NOT NULL,
  PRIMARY KEY (`id_document`),
  KEY `id_documentType` (`id_documentType`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `documenttypes`
--

DROP TABLE IF EXISTS `documenttypes`;
CREATE TABLE IF NOT EXISTS `documenttypes` (
  `id_typeDocument` int(11) NOT NULL AUTO_INCREMENT,
  `documentType` varchar(60) NOT NULL,
  PRIMARY KEY (`id_typeDocument`),
  UNIQUE KEY `documentType` (`documentType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `Id_user` int(11) NOT NULL,
  `reduction` int(11) DEFAULT NULL,
  `dateORder` date NOT NULL,
  `payed` tinyint(1) DEFAULT NULL,
  `payementDate` date DEFAULT NULL,
  `deliver` tinyint(1) DEFAULT NULL,
  `deliverDate` date DEFAULT NULL,
  `modeOfPayement` enum('Cash','Bancontact') DEFAULT NULL,
  PRIMARY KEY (`id_order`),
  KEY `Id_User` (`Id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `orders_documents`
--

DROP TABLE IF EXISTS `orders_documents`;
CREATE TABLE IF NOT EXISTS `orders_documents` (
  `id_orders_docuements` int(11) NOT NULL AUTO_INCREMENT,
  `id_order` int(11) NOT NULL,
  `id_document` int(11) NOT NULL,
  PRIMARY KEY (`id_orders_docuements`),
  KEY `ID_Document` (`id_document`),
  KEY `ID_Order` (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `orders_products`
--

DROP TABLE IF EXISTS `orders_products`;
CREATE TABLE IF NOT EXISTS `orders_products` (
  `id_orders_products` int(11) NOT NULL AUTO_INCREMENT,
  `id_order` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `qteUnits` int(11) NOT NULL,
  `unitPrice` int(11) NOT NULL,
  `deliver` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_orders_products`),
  KEY `ID_Order` (`id_order`),
  KEY `ID_Product` (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `id_brand` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `designation` varchar(255) NOT NULL,
  `quantityTotal` int(11) NOT NULL,
  `unitCostPrice` int(11) NOT NULL,
  `margin` int(11) NOT NULL,
  `length` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `minimumQte` int(11) NOT NULL,
  PRIMARY KEY (`id_product`),
  KEY `id_brand` (`id_brand`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `products_categories`
--

DROP TABLE IF EXISTS `products_categories`;
CREATE TABLE IF NOT EXISTS `products_categories` (
  `id_products_categories` int(11) NOT NULL AUTO_INCREMENT,
  `id_product` int(11) NOT NULL,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`id_products_categories`),
  KEY `ID_Product` (`id_product`),
  KEY `ID_Category` (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(60) NOT NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id_role`, `role`) VALUES
(1, 'Admin');

-- --------------------------------------------------------

--
-- Structure de la table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `id_supplier` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`id_supplier`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `id_role` int(11) NOT NULL,
  `lastName` varchar(60) NOT NULL,
  `firstName` varchar(60) NOT NULL,
  `dayOfBirth` date DEFAULT NULL,
  `inscriptionDate` date NOT NULL,
  `vat` varchar(20) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `login` varchar(60) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `vat` (`vat`) USING BTREE,
  KEY `id_role` (`id_role`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id_user`, `id_role`, `lastName`, `firstName`, `dayOfBirth`, `inscriptionDate`, `vat`, `mail`, `password`, `login`, `active`) VALUES
(1, 1, 'Awaly', 'Mohamad', '2021-05-11', '2021-05-31', '123456789', 'awalymhassan@hotmail.com', '123456', 'admin', 0),
(12, 1, 'Mohamad hassan', 'awa', '2021-06-01', '2021-06-14', '123456788', 'awa@hotmail.com', '123', 'awaa', 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`id_city`) REFERENCES `cities` (`id_city`);

--
-- Contraintes pour la table `adress_users`
--
ALTER TABLE `adress_users`
  ADD CONSTRAINT `adress_users_ibfk_1` FOREIGN KEY (`id_adress`) REFERENCES `address` (`id_adress`),
  ADD CONSTRAINT `adress_users_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

--
-- Contraintes pour la table `batchs`
--
ALTER TABLE `batchs`
  ADD CONSTRAINT `batchs_ibfk_1` FOREIGN KEY (`id_products`) REFERENCES `products` (`id_product`);

--
-- Contraintes pour la table `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`id_country`) REFERENCES `countries` (`id_country`);

--
-- Contraintes pour la table `commandsuppliers`
--
ALTER TABLE `commandsuppliers`
  ADD CONSTRAINT `commandsuppliers_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`),
  ADD CONSTRAINT `commandsuppliers_ibfk_2` FOREIGN KEY (`id_supplier`) REFERENCES `suppliers` (`id_supplier`);

--
-- Contraintes pour la table `commandsuppliers_batchs`
--
ALTER TABLE `commandsuppliers_batchs`
  ADD CONSTRAINT `commandsuppliers_batchs_ibfk_1` FOREIGN KEY (`id_batch`) REFERENCES `batchs` (`id_batch`),
  ADD CONSTRAINT `commandsuppliers_batchs_ibfk_2` FOREIGN KEY (`id_commandSupplier`) REFERENCES `commandsuppliers` (`id_commandSuppliers`);

--
-- Contraintes pour la table `documents`
--
ALTER TABLE `documents`
  ADD CONSTRAINT `documents_ibfk_1` FOREIGN KEY (`id_documentType`) REFERENCES `documenttypes` (`id_typeDocument`);

--
-- Contraintes pour la table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`Id_User`) REFERENCES `users` (`id_user`);

--
-- Contraintes pour la table `orders_documents`
--
ALTER TABLE `orders_documents`
  ADD CONSTRAINT `orders_documents_ibfk_1` FOREIGN KEY (`id_document`) REFERENCES `documents` (`id_document`),
  ADD CONSTRAINT `orders_documents_ibfk_2` FOREIGN KEY (`id_order`) REFERENCES `orders` (`ID_Order`);

--
-- Contraintes pour la table `orders_products`
--
ALTER TABLE `orders_products`
  ADD CONSTRAINT `orders_products_ibfk_1` FOREIGN KEY (`id_order`) REFERENCES `orders` (`ID_Order`),
  ADD CONSTRAINT `orders_products_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`);

--
-- Contraintes pour la table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`id_brand`) REFERENCES `brands` (`id_brand`);

--
-- Contraintes pour la table `products_categories`
--
ALTER TABLE `products_categories`
  ADD CONSTRAINT `products_categories_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`),
  ADD CONSTRAINT `products_categories_ibfk_2` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id_categorie`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id_role`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
