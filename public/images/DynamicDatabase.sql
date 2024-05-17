-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 17, 2024 at 09:17 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `DynamicDatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `bi` varchar(20) NOT NULL,
  `contact` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `residence` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `name`, `bi`, `contact`, `email`, `residence`) VALUES
(1, 'joao ', '85928402N', '875428947', 'joao@gmail.com', 'matola'),
(2, 'Eclemirda Timane', '248908294820N', '878471482', 'mimidatimana@gmail.com', 'INfulene A'),
(3, 'Marta José', '8975238479N', '878427948', 'martajose@gmail.com', 'Matola-Gare'),
(7, 'Guilherme  Santos', '78713891N', '87482747', 'guisantos@gmail.com', 'Matola-Gare'),
(8, 'Jose Dos Santos', '7489748927N', '84724279524', 'josedsantos@gmail.com', 'Matola Gare'),
(9, 'Helio Fernando', '74927489279N', '878947929', 'helio@gmail.com', 'Matola'),
(10, 'Vanessa Michaque', '74872482378n', '872849274', 'vanessamichaque32@gmail,com', 'Matola Gare'),
(11, 'Gustavo Lazaro', '749274892N', '874829472', 'gustalazaro@gmail.com', 'Rio de Janeiro'),
(12, 'Helio Calisto', '74987489274M', '87742894', 'heliocalisto@gmal.com', 'Infulene A'),
(13, 'Helio Calisto', '74987489274M', '87742894', 'heliocalisto@gmal.com', 'Infulene A'),
(14, 'Fernando Uache', '872427748482N', '874294728', 'fernandouache@gmail.com', 'Patrice Lumumba'),
(15, 'Wady Paulino', '8947842729J', '8794829242', 'wadypaulino@gmail.com', 'Machava-Sede'),
(16, 'Julio Fernando', '78438738N', '784294728', 'julio@gmail.com', 'Maputo'),
(17, 'Osorio', '123456i65445678', '87765765545', 'osorio@gmail.com', 'matola');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `discription` varchar(300) NOT NULL,
  `task_status` varchar(100) DEFAULT NULL,
  `payd` tinyint(1) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `task_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `discription`, `task_status`, `payd`, `client_id`, `task_date`) VALUES
(1, 'instalacao de corrente', 'Served', 1, 1, '0000-00-00'),
(2, '1. Reparacao de uma geleira\n2. Instalacao electrica', 'PENDING', 0, 2, '0000-00-00'),
(3, '1. Reparacao de um fogao\r\n2. Reparacao de um congelador', 'SERVED', 1, 3, '0000-00-00'),
(6, '1. Montagem de Instalacao de corrente 	', 'PENDING', 0, 5, '0000-00-00'),
(7, '1. instalacao electrica ', 'PENDING', 0, 9, '0000-00-00'),
(8, '1. Reparacao de geleira ', 'PENDING', 0, 10, '0000-00-00'),
(9, '1. Troca de ar-condicionado', 'PENDING', 0, 11, '0000-00-00'),
(10, '1. Reparacao de fogao e manutencao electrica', 'PENDING', 0, 14, '2024-05-11'),
(11, '1. Instalacao de corrente electrica', 'PENDING', 0, 15, '2024-05-11'),
(12, '1. Manutencao de um arcondicionado e instalacao de corrente', 'PENDING', 0, 16, '2024-05-11'),
(13, '1. reparacao de AC', 'Served', 0, 17, '2024-05-14');

-- --------------------------------------------------------

--
-- Table structure for table `pagamento`
--

CREATE TABLE `pagamento` (
  `id` int(11) NOT NULL,
  `valor` int(11) NOT NULL,
  `paydate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pagamento`
--

INSERT INTO `pagamento` (`id`, `valor`, `paydate`) VALUES
(1, 12700, '2024-05-17'),
(1, 1900, '2024-05-17'),
(17, 18000, '2024-05-17');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `usuario`, `senha`) VALUES
(1, 'josenaldo', 'naldo'),
(2, 'wady', 'paulino'),
(3, '', ''),
(4, 'user1', 'user2'),
(5, 'director', 'director-access'),
(6, 'cleyton', '12345');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
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
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
