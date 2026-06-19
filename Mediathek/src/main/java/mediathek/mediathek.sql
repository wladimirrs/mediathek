-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 19. Jun 2026 um 10:38
-- Server-Version: 10.4.32-MariaDB
-- PHP-Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `mediathek`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `artikel`
--

CREATE TABLE `artikel` (
  `id` int(11) NOT NULL,
  `typ` int(11) DEFAULT NULL,
  `titel` varchar(100) DEFAULT NULL,
  `abachtzehn` tinyint(1) DEFAULT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `umfang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `artikel`
--

INSERT INTO `artikel` (`id`, `typ`, `titel`, `abachtzehn`, `genre`, `umfang`) VALUES
(1, 1, 'Titanic', 1, 'Horror', 200);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bestellungen`
--

CREATE TABLE `bestellungen` (
  `id` int(11) NOT NULL,
  `kunden_id` int(11) DEFAULT NULL,
  `artikel_id` int(11) DEFAULT NULL,
  `von` varchar(30) DEFAULT NULL,
  `bis` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `bestellungen`
--

INSERT INTO `bestellungen` (`id`, `kunden_id`, `artikel_id`, `von`, `bis`) VALUES
(1, 1, 1, '2000-06-12', '2000-06-19');

--
-- Trigger `bestellungen`
--
DELIMITER $$
CREATE TRIGGER `trg_ausleihdauer_pruefen` BEFORE INSERT ON `bestellungen` FOR EACH ROW BEGIN
    DECLARE max_tage INT;

    SELECT t.ausleihdauer
    INTO max_tage
    FROM artikel a
    JOIN typen t ON a.typ = t.id
    WHERE a.id = NEW.artikel_id;

    IF DATEDIFF(NEW.bis, NEW.von) > max_tage THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ausleihdauer überschreitet die erlaubte Dauer';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_ausleihdauer_pruefen_update` BEFORE UPDATE ON `bestellungen` FOR EACH ROW BEGIN
    DECLARE max_tage INT;

    SELECT t.ausleihdauer
    INTO max_tage
    FROM artikel a
    JOIN typen t ON a.typ = t.id
    WHERE a.id = NEW.artikel_id;

    IF DATEDIFF(NEW.bis, NEW.von) > max_tage THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ausleihdauer überschreitet die erlaubte Dauer';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_keine_doppelbelegung` BEFORE INSERT ON `bestellungen` FOR EACH ROW BEGIN
    IF EXISTS (
        SELECT 1
        FROM bestellungen b
        WHERE b.artikel_id = NEW.artikel_id
          AND NEW.von <= b.bis
          AND NEW.bis >= b.von
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Artikel bereits verliehen';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_keine_doppelbelegung_update` BEFORE UPDATE ON `bestellungen` FOR EACH ROW BEGIN
    IF EXISTS (
        SELECT 1
        FROM bestellungen b
        WHERE b.artikel_id = NEW.artikel_id
          AND NEW.von <= b.bis
          AND NEW.bis >= b.von
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Artikel bereits verliehen';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kunden`
--

CREATE TABLE `kunden` (
  `id` int(11) NOT NULL,
  `nachname` varchar(50) DEFAULT NULL,
  `vorname` varchar(50) DEFAULT NULL,
  `mediatheknummer` varchar(11) NOT NULL,
  `strasse` varchar(50) DEFAULT NULL,
  `hausnummer` varchar(11) DEFAULT NULL,
  `plz` varchar(11) DEFAULT NULL,
  `ort` varchar(50) DEFAULT NULL,
  `geburtsdatum` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `kunden`
--

INSERT INTO `kunden` (`id`, `nachname`, `vorname`, `mediatheknummer`, `strasse`, `hausnummer`, `plz`, `ort`, `geburtsdatum`) VALUES
(1, 'Ausleiher', 'Peter', '300300', 'Hausstraße', '10', '33333', 'Gotham', '1962-11-13');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `nutzer`
--

CREATE TABLE `nutzer` (
  `user_id` int(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `passwort` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `nutzer`
--

INSERT INTO `nutzer` (`user_id`, `email`, `passwort`) VALUES
(1, 'a@a.com', '12345678');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `typen`
--

CREATE TABLE `typen` (
  `id` int(11) NOT NULL,
  `bezeichnung` varchar(50) DEFAULT NULL,
  `ausleihdauer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `typen`
--

INSERT INTO `typen` (`id`, `bezeichnung`, `ausleihdauer`) VALUES
(1, 'Film', 7),
(2, 'Buch', 14),
(3, 'Brettspiel', 14),
(4, 'Videospiel', 7);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `artikel`
--
ALTER TABLE `artikel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `typ` (`typ`);

--
-- Indizes für die Tabelle `bestellungen`
--
ALTER TABLE `bestellungen`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kunden_id` (`kunden_id`),
  ADD KEY `artikel_id` (`artikel_id`);

--
-- Indizes für die Tabelle `kunden`
--
ALTER TABLE `kunden`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mediatheknummer` (`mediatheknummer`);

--
-- Indizes für die Tabelle `nutzer`
--
ALTER TABLE `nutzer`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indizes für die Tabelle `typen`
--
ALTER TABLE `typen`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `artikel`
--
ALTER TABLE `artikel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `bestellungen`
--
ALTER TABLE `bestellungen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `kunden`
--
ALTER TABLE `kunden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `nutzer`
--
ALTER TABLE `nutzer`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `typen`
--
ALTER TABLE `typen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `artikel`
--
ALTER TABLE `artikel`
  ADD CONSTRAINT `artikel_ibfk_1` FOREIGN KEY (`typ`) REFERENCES `typen` (`id`);

--
-- Constraints der Tabelle `bestellungen`
--
ALTER TABLE `bestellungen`
  ADD CONSTRAINT `bestellungen_ibfk_1` FOREIGN KEY (`kunden_id`) REFERENCES `kunden` (`id`),
  ADD CONSTRAINT `bestellungen_ibfk_2` FOREIGN KEY (`artikel_id`) REFERENCES `artikel` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
