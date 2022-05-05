-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2022 at 07:11 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mobilefinalproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `item_id` int(11) NOT NULL,
  `weight` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pantry`
--

CREATE TABLE `pantry` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `date_of_expiration` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `weight` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pantry`
--

INSERT INTO `pantry` (`item_id`, `item_name`, `date_of_expiration`, `location`, `weight`, `user_id`) VALUES
(25, 'Nutella', '20/02/2022', 'Fridge', '1 jar', 14),
(32, 'Chicken', '5/2/2022', 'Fridge', '1 kg', 10),
(33, 'Fish', '7/2/2022', 'Fridge', '2 kg', 10),
(38, 'Labne', '5/5/2022', 'Fridge', '2 kg', 10),
(39, 'Tuna', '20/5/2023', 'Pantry', '2 cans', 10),
(40, 'Tuna', '20/5/2023', 'Pantry', '2 cans', 14),
(42, 'nshs', 'ksks', 'sksls', 'ksks', 11),
(43, 'ueus', 'js', 'isi', 'ksis', 15),
(44, 'Pepsi', '20/4/2023', 'fridge', '1 bottle', 10),
(48, 'Pepsi', '2/2/2023', 'Fridge', '1 bottle', 10),
(49, 'Pepsis', '2/2/2023', 'Fridge', '1 bottle', 19);

-- --------------------------------------------------------

--
-- Table structure for table `recipe`
--

CREATE TABLE `recipe` (
  `Recipe_id` int(11) NOT NULL,
  `Recipe_name` varchar(255) NOT NULL,
  `Instructions` varchar(255) NOT NULL,
  `Ingredients` varchar(255) NOT NULL,
  `calories` varchar(255) NOT NULL,
  `cooktime` varchar(255) NOT NULL,
  `image` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recipe`
--

INSERT INTO `recipe` (`Recipe_id`, `Recipe_name`, `Instructions`, `Ingredients`, `calories`, `cooktime`, `image`, `user_id`) VALUES
(25, 'Pizza', 'Prepare the dough\r\nAdd sauce and topings\r\nBake it in the oven until golden brown\r\nEnjoy', 'Dough\r\nMozarella\r\nPizza sauce\r\nPeperoni', '2500 kcal', '20 min', 2131165361, 10),
(28, 'mjadra', 'Don\'t do it\r\nIt does not taste good\r\nORDER A PIZZA', 'Beans\r\nWater\r\nTomato paste', '1000 kcal', '30 min', 2131165361, 10),
(29, 'Tawook', 'Marinate the chicken\r\nPrepare the grill\r\nGrill the chicken\r\nEnjoy', 'Chicken Breast\r\nLabne\r\nSpices', '1500 kcal', '5 min', 2131165364, 14),
(30, 'hshs', 'hshah\njsjs\nksks', '\nsjjs\nshah\nhshs', 'isis', 'jsks', 2131165367, 11),
(31, 'hshs', 'usua\nksks\nksks\njsjs\n', '\nshha\nysys\nhshs\nhshs', 'kzkz', 'jsjs', 2131165363, 11),
(32, 'burger', 'cook the beef\nadd ketchup\nEnjoy', '\nBeef\nketchup\nbuns\nletuce', '200 kcal', '10 min', 2131165364, 10),
(33, 'us', 'us\nis\nis', '\nue\nhs\nhs', 'is', 'is', 2131165363, 11),
(35, 'hsu', 'udunsnsjia', '\nhshs\ndhhs\nhs', 'kd', 'ks.iskdkd', 2131165363, 11),
(36, 'mjadra', 'haha\njsjs', '\nbeans\nwater', '2500 kcal', '10 min', 2131165367, 19);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `email`, `date`) VALUES
(10, 'charbel', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ramiakl33@gmail.com', '2024-04-22'),
(11, 'elias', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'akfnnsdf', '2024-04-22'),
(14, 'rami', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ramikl33', '2026-04-22'),
(15, 'joe', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'joe.ajo', '2002-05-22'),
(19, 'test', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'test@gmail.co.', '2005-05-22');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `pantry`
--
ALTER TABLE `pantry`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`Recipe_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `pantry`
--
ALTER TABLE `pantry`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `recipe`
--
ALTER TABLE `recipe`
  MODIFY `Recipe_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pantry`
--
ALTER TABLE `pantry`
  ADD CONSTRAINT `pantry_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `recipe`
--
ALTER TABLE `recipe`
  ADD CONSTRAINT `recipe_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
