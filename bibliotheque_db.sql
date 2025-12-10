-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 10 déc. 2025 à 15:12
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bibliotheque_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `books`
--

CREATE TABLE `books` (
  `book_id` varchar(100) NOT NULL,
  `title` varchar(150) NOT NULL,
  `author` varchar(100) NOT NULL,
  `year` int(11) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `status` enum('disponible','emprunté','reserver') DEFAULT 'disponible',
  `loan_count` int(11) DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `books`
--

INSERT INTO `books` (`book_id`, `title`, `author`, `year`, `image`, `category`, `description`, `status`, `loan_count`, `created_at`) VALUES
('BO2067', 'Paris ', 'Mike', 2005, 'uploaded_books/d4cd29_0233d0_paris.jpg', 'litteraire', 'Paris c\'est la vie', 'emprunté', 1, '2025-12-10 14:00:49'),
('BO8440', 'KNY', 'Tite', 2003, 'uploaded_books/e7eea5_a65dcb_photo_2025-08-26_15-36-03.jpg', 'fiction', 'Toiture', 'disponible', 0, '2025-12-10 14:04:19');

-- --------------------------------------------------------

--
-- Structure de la table `loans`
--

CREATE TABLE `loans` (
  `loan_id` varchar(100) NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `book_id` varchar(100) NOT NULL,
  `borrow_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `due_date` date NOT NULL,
  `return_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `loans`
--

INSERT INTO `loans` (`loan_id`, `user_id`, `book_id`, `borrow_date`, `due_date`, `return_date`) VALUES
('LOc8c2', 'MEM001', 'BO2067', '2025-12-10 14:01:18', '2025-12-24', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` varchar(100) NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `book_id` varchar(100) NOT NULL,
  `reservation_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `expire_date` date DEFAULT NULL,
  `status` enum('ACTIVE','CANCELLED','FULFILLED') DEFAULT 'ACTIVE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `user_id`, `book_id`, `reservation_date`, `expire_date`, `status`) VALUES
('RES23B2', 'MEM002', 'BO2067', '2025-12-10 14:02:12', '2025-12-12', 'ACTIVE');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `user_id` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `tel_num` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','MEMBER') NOT NULL,
  `registration_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`user_id`, `name`, `surname`, `tel_num`, `email`, `password`, `role`, `registration_date`) VALUES
('admin', 'Admin', 'Principal', '656950045', 'bibliotech@gmail.com', 'admin', 'ADMIN', '2025-12-10 13:59:43'),
('MEM001', 'Mike', 'Ken', '679670590', 'mikeken@gmail.com', 'mike', 'MEMBER', '2025-12-10 13:59:43'),
('MEM002', 'Rene', 'Loic', '688000492', 'loicrene@gmail.com', 'mike', 'MEMBER', '2025-12-10 13:59:43'),
('MEME43E', 'Don', 'Man', '677640888', 'mike@gmail.com', 'mike', 'MEMBER', '2025-12-10 14:05:23');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`);

--
-- Index pour la table `loans`
--
ALTER TABLE `loans`
  ADD PRIMARY KEY (`loan_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Index pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `loans`
--
ALTER TABLE `loans`
  ADD CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
