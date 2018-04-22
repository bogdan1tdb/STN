-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 22, 2018 at 04:11 PM
-- Server version: 5.7.21-0ubuntu0.16.04.1
-- PHP Version: 7.0.28-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";


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
-- Table structure for table `password_reset`
--

CREATE TABLE `password_reset` (
  `Id` int(11) NOT NULL,
  `Token` varchar(60) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `ExpireDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
  `IdFacultate` int(11) DEFAULT NULL
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
  ADD KEY `IdFacultate` (`IdFacultate`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `applications`
--
ALTER TABLE `applications`
  MODIFY `AppId` int(11) NOT NULL AUTO_INCREMENT;
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
  ADD CONSTRAINT `users_ibfk_3` FOREIGN KEY (`IdFacultate`) REFERENCES `facultati` (`IdFacultate`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
