-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 03, 2016 at 02:06 PM
-- Server version: 5.5.49-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cms`
--

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(20) NOT NULL,
  `authority` varchar(20) NOT NULL,
  PRIMARY KEY (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_EDITOR'),
('editor', 'ROLE_EDITOR');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `name` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `page`
--

CREATE TABLE IF NOT EXISTS `page` (
  `page_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `page_name` varchar(50) NOT NULL,
  `page_content` text NOT NULL,
  `display_index` int(3) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`page_id`),
  KEY `user_id` (`user_id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_title` varchar(140) NOT NULL,
  `post_content` blob NOT NULL,
  `post_datetime` datetime NOT NULL,
  `expire_datetime` datetime NOT NULL,
  `flagged_for_review` tinyint(1) NOT NULL,
  `draft` tinyint(1) NOT NULL,
  `editor_comments` text NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `post_category`
--

CREATE TABLE IF NOT EXISTS `post_category` (
  `post_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  KEY `category_id` (`category_id`),
  KEY `post_id` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `post_tag`
--

CREATE TABLE IF NOT EXISTS `post_tag` (
  `post_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  KEY `post_id` (`post_id`),
  KEY `tag_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(140) NOT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `tag_name` (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `name` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `enabled`) VALUES
(1, 'admin', 'admin', 1),
(2, 'editor', 'editor', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON UPDATE CASCADE;

--
-- Constraints for table `page`
--
ALTER TABLE `page`
  ADD CONSTRAINT `page_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `page` (`page_id`),
  ADD CONSTRAINT `page_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `post_category`
--
ALTER TABLE `post_category`
  ADD CONSTRAINT `post_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `post_category_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

--
-- Constraints for table `post_tag`
--
ALTER TABLE `post_tag`
  ADD CONSTRAINT `post_tag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`),
  ADD CONSTRAINT `post_tag_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
