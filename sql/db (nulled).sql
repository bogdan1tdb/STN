-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 13, 2018 at 12:23 PM
-- Server version: 5.7.22-0ubuntu0.16.04.1
-- PHP Version: 7.0.28-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+03:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stn_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `applications`
--

CREATE TABLE `applications` (
  `AppId` int(11) NOT NULL,
  `Nume` varchar(120) NOT NULL,
  `Prenume` varchar(120) NOT NULL,
  `Facultate` varchar(120) NOT NULL,
  `Serie` varchar(120) NOT NULL,
  `Grupa` varchar(120) NOT NULL,
  `Email` varchar(120) NOT NULL,
  `Document` varchar(120) NOT NULL,
  `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Type` int(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE `countries` (
  `CountryId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `facultati`
--

CREATE TABLE `facultati` (
  `IdFacultate` int(11) NOT NULL,
  `Nume` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `failed_logins`
--

CREATE TABLE `failed_logins` (
  `Id` int(11) NOT NULL,
  `Ip` varchar(30) NOT NULL,
  `Attempts` int(2) NOT NULL,
  `ExpireDate` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `grupe`
--

CREATE TABLE `grupe` (
  `IdGrupa` int(11) NOT NULL,
  `Nume` varchar(120) NOT NULL,
  `IdSerie` int(11) DEFAULT NULL,
  `Token` varchar(120) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `invites`
--

CREATE TABLE `invites` (
  `IdInvitatie` int(11) NOT NULL,
  `Token` varchar(40) NOT NULL,
  `Email` varchar(120) NOT NULL,
  `IdGrupa` int(11) DEFAULT NULL,
  `IdSerie` int(11) DEFAULT NULL,
  `IdFacultate` int(11) DEFAULT NULL,
  `Class` int(11) NOT NULL,
  `ExpDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `news_serie`
--

CREATE TABLE `news_serie` (
  `IdNews` int(11) NOT NULL,
  `Title` varchar(60) NOT NULL,
  `Body` text NOT NULL,
  `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IdUser` int(11) NOT NULL,
  `IdSerie` int(11) NOT NULL,
  `LastEdit` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

CREATE TABLE `notes` (
  `Id` int(10) NOT NULL,
  `IdOra` int(11) NOT NULL,
  `IdStudent` int(10) NOT NULL,
  `Teme` text NOT NULL,
  `Examen` text NOT NULL,
  `Nota` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ore`
--

CREATE TABLE `ore` (
  `Id` int(11) NOT NULL,
  `IdGrupa` int(10) NOT NULL,
  `Nume` varchar(50) NOT NULL DEFAULT '',
  `Durata` varchar(15) NOT NULL DEFAULT '',
  `TipActivitate` varchar(10) NOT NULL DEFAULT '',
  `Semigrupa` int(2) DEFAULT NULL,
  `Sala` varchar(10) NOT NULL DEFAULT '',
  `NumeProfesor` varchar(50) NOT NULL DEFAULT '',
  `Zi` varchar(10) NOT NULL DEFAULT '',
  `Grupa` int(5) NOT NULL,
  `Saptamana` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `password_reset`
--

CREATE TABLE `password_reset` (
  `Id` int(11) NOT NULL,
  `Token` varchar(60) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `ExpireDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `serii`
--

CREATE TABLE `serii` (
  `IdSerie` int(11) NOT NULL,
  `Nume` varchar(120) NOT NULL,
  `IdFacultate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Id` int(10) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(80) NOT NULL,
  `Salt` varbinary(120) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `FirstName` varchar(30) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `JoinDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `LastSeen` datetime DEFAULT CURRENT_TIMESTAMP,
  `Class` int(2) NOT NULL DEFAULT '1',
  `LoginToken` varchar(28) DEFAULT '',
  `Ip` varchar(18) DEFAULT '',
  `Avatar` varchar(120) DEFAULT '',
  `IdGrupa` int(11) DEFAULT NULL,
  `IdSerie` int(11) DEFAULT NULL,
  `IdFacultate` int(11) DEFAULT NULL,
  `Gender` int(1) NOT NULL DEFAULT '0',
  `CountryId` int(11) DEFAULT NULL,
  `Anonymity` int(1) NOT NULL DEFAULT '0',
  `Donor` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `applications`
--
ALTER TABLE `applications`
  ADD PRIMARY KEY (`AppId`);

--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`CountryId`);

--
-- Indexes for table `facultati`
--
ALTER TABLE `facultati`
  ADD PRIMARY KEY (`IdFacultate`);

--
-- Indexes for table `failed_logins`
--
ALTER TABLE `failed_logins`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `grupe`
--
ALTER TABLE `grupe`
  ADD PRIMARY KEY (`IdGrupa`),
  ADD KEY `IdSerie` (`IdSerie`);

--
-- Indexes for table `invites`
--
ALTER TABLE `invites`
  ADD PRIMARY KEY (`IdInvitatie`),
  ADD KEY `IdGrupa` (`IdGrupa`),
  ADD KEY `IdSerie` (`IdSerie`),
  ADD KEY `IdFacultate` (`IdFacultate`);

--
-- Indexes for table `news_serie`
--
ALTER TABLE `news_serie`
  ADD PRIMARY KEY (`IdNews`),
  ADD KEY `IdUser` (`IdUser`),
  ADD KEY `IdSerie` (`IdSerie`),
  ADD KEY `IdUser_2` (`IdUser`);

--
-- Indexes for table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Id_ora` (`IdOra`),
  ADD KEY `IdStudent` (`IdStudent`);

--
-- Indexes for table `ore`
--
ALTER TABLE `ore`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Id` (`IdGrupa`);

--
-- Indexes for table `password_reset`
--
ALTER TABLE `password_reset`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `serii`
--
ALTER TABLE `serii`
  ADD PRIMARY KEY (`IdSerie`),
  ADD KEY `IdFacultate` (`IdFacultate`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdGrupa` (`IdGrupa`),
  ADD KEY `IdSerie` (`IdSerie`),
  ADD KEY `IdFacultate` (`IdFacultate`),
  ADD KEY `CountryId` (`CountryId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `applications`
--
ALTER TABLE `applications`
  MODIFY `AppId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
  MODIFY `CountryId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `facultati`
--
ALTER TABLE `facultati`
  MODIFY `IdFacultate` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `failed_logins`
--
ALTER TABLE `failed_logins`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `grupe`
--
ALTER TABLE `grupe`
  MODIFY `IdGrupa` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `invites`
--
ALTER TABLE `invites`
  MODIFY `IdInvitatie` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `news_serie`
--
ALTER TABLE `news_serie`
  MODIFY `IdNews` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `notes`
--
ALTER TABLE `notes`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ore`
--
ALTER TABLE `ore`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `password_reset`
--
ALTER TABLE `password_reset`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `serii`
--
ALTER TABLE `serii`
  MODIFY `IdSerie` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `grupe`
--
ALTER TABLE `grupe`
  ADD CONSTRAINT `grupe_ibfk_1` FOREIGN KEY (`IdSerie`) REFERENCES `serii` (`IdSerie`);

--
-- Constraints for table `invites`
--
ALTER TABLE `invites`
  ADD CONSTRAINT `invites_ibfk_1` FOREIGN KEY (`IdGrupa`) REFERENCES `grupe` (`IdGrupa`),
  ADD CONSTRAINT `invites_ibfk_2` FOREIGN KEY (`IdSerie`) REFERENCES `serii` (`IdSerie`),
  ADD CONSTRAINT `invites_ibfk_3` FOREIGN KEY (`IdFacultate`) REFERENCES `facultati` (`IdFacultate`);

--
-- Constraints for table `news_serie`
--
ALTER TABLE `news_serie`
  ADD CONSTRAINT `news_serie_ibfk_1` FOREIGN KEY (`IdUser`) REFERENCES `users` (`Id`),
  ADD CONSTRAINT `news_serie_ibfk_2` FOREIGN KEY (`IdSerie`) REFERENCES `serii` (`IdSerie`);

--
-- Constraints for table `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`IdOra`) REFERENCES `ore` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `notes_ibfk_2` FOREIGN KEY (`IdStudent`) REFERENCES `users` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ore`
--
ALTER TABLE `ore`
  ADD CONSTRAINT `ore_ibfk_1` FOREIGN KEY (`IdGrupa`) REFERENCES `grupe` (`IdGrupa`);

--
-- Constraints for table `serii`
--
ALTER TABLE `serii`
  ADD CONSTRAINT `serii_ibfk_1` FOREIGN KEY (`IdFacultate`) REFERENCES `facultati` (`IdFacultate`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`IdGrupa`) REFERENCES `grupe` (`IdGrupa`),
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`IdSerie`) REFERENCES `serii` (`IdSerie`),
  ADD CONSTRAINT `users_ibfk_3` FOREIGN KEY (`IdFacultate`) REFERENCES `facultati` (`IdFacultate`),
  ADD CONSTRAINT `users_ibfk_4` FOREIGN KEY (`CountryId`) REFERENCES `countries` (`CountryId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
