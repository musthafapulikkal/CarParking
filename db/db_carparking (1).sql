-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 04, 2019 at 05:36 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_carparking`
--

-- --------------------------------------------------------

--
-- Table structure for table `area`
--

CREATE TABLE `area` (
  `id` int(11) NOT NULL,
  `mall_id` varchar(5) NOT NULL,
  `mall_name` varchar(50) NOT NULL,
  `area` varchar(50) NOT NULL,
  `status` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `area`
--

INSERT INTO `area` (`id`, `mall_id`, `mall_name`, `area`, `status`) VALUES
(9, '1', 'shobha', 'area1', 0),
(10, '1', 'shobha', 'area2', 1),
(11, '8', 'abc', 'area1', 0),
(12, '8', 'abc', 'area2', 0),
(13, '8', 'abc', 'area3', 0);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(20) NOT NULL,
  `mall_name` varchar(80) NOT NULL,
  `mall_id` int(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `slot` varchar(10) NOT NULL,
  `area` varchar(10) NOT NULL,
  `amount` varchar(20) NOT NULL,
  `time` varchar(20) NOT NULL,
  `am_pm` varchar(5) NOT NULL,
  `hour` varchar(10) NOT NULL,
  `c_date` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `mall_name`, `mall_id`, `email`, `name`, `slot`, `area`, `amount`, `time`, `am_pm`, `hour`, `c_date`) VALUES
(1, 'abc', 7, 'jyothi@gmail.com', 'musthafa', 'slot1', 'area1', '40', '8', 'PM', '2 hour', '07-004-2019');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_bank`
--

CREATE TABLE `tbl_bank` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `card_no` varchar(20) NOT NULL,
  `e_date` varchar(20) NOT NULL,
  `cvv` varchar(10) NOT NULL,
  `balance` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_bank`
--

INSERT INTO `tbl_bank` (`id`, `name`, `card_no`, `e_date`, `cvv`, `balance`) VALUES
(1, 'musthafa', '1234', '05/05/2019', '123', 770);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_mall`
--

CREATE TABLE `tbl_mall` (
  `id` int(10) NOT NULL,
  `mall_name` varchar(50) NOT NULL,
  `state` varchar(20) NOT NULL,
  `district` varchar(20) NOT NULL,
  `latitude` varchar(20) NOT NULL,
  `longtitude` varchar(20) NOT NULL,
  `mall_place` varchar(20) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_mall`
--

INSERT INTO `tbl_mall` (`id`, `mall_name`, `state`, `district`, `latitude`, `longtitude`, `mall_place`, `image`) VALUES
(1, 'shobha', 'Kerala', 'Thrissur', '10.527641599999999', '76.2144349', 'puzhakkal', 'shobha.JPEG'),
(4, 'abcd', 'Kerala', 'Alappuzha', '', '', 'rym', 'abcd.JPEG'),
(6, 'Citymall', 'Kerala', 'Palakkad', '10.5236565', '76.2095366', 'lakkidi', 'Citymall.JPEG'),
(7, 'abc', 'Kerala', 'Palakkad', '0', '0', 'palakkad', 'abc.JPEG'),
(8, 'abc', 'Kerala', 'Palakkad', '10.8067836', '76.1964852', 'palakkad', 'abc.JPEG');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `phone` bigint(20) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `username`, `phone`, `password`) VALUES
(1, 'musthafa.fabstudioz@gmail.com', 'musthafa', 9539206718, '1234'),
(2, 'jyothi@gmail.com', 'jyothi', 9539206718, '1234'),
(3, 'haripriya@gmail.com', 'haripriya', 9064784439, '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_bank`
--
ALTER TABLE `tbl_bank`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_mall`
--
ALTER TABLE `tbl_mall`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `area`
--
ALTER TABLE `area`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_bank`
--
ALTER TABLE `tbl_bank`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_mall`
--
ALTER TABLE `tbl_mall`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
