-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Апр 07 2024 г., 14:07
-- Версия сервера: 8.0.24
-- Версия PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `musicsite`
--

DELIMITER $$
--
-- Процедуры
--
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `FixIncorrectArtistId` (IN `Id` INT, OUT `NewId` INT)  IF Id NOT IN (SELECT artist.Artist_id FROM artist) 
THEN SET NewId = null;
ELSE SET NewId = Id;
END IF$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `album`
--

CREATE TABLE `album` (
  `Album_id` int NOT NULL,
  `Name` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Release_date` date NOT NULL,
  `Style` varchar(20) DEFAULT NULL,
  `Artist_id` int DEFAULT NULL,
  `Cover_file` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `album`
--

INSERT INTO `album` (`Album_id`, `Name`, `Release_date`, `Style`, `Artist_id`, `Cover_file`) VALUES
(1, 'To Pimp a Butterfly', '2015-03-14', 'Hip-hop/Rap', 3, '02f084ee-5c4e-40f8-918b-4790467c5018.PNG'),
(2, 'DAMN.', '2017-04-13', 'Hip-hop/Rap', 3, '111acd93-8364-45e5-81f3-9219b360b3e1.PNG'),
(4, 'ANTI', '2016-01-26', 'Pop', 1, '0fa3a2f6-b04f-4dcf-bc30-d86c6b7ce9f7.PNG'),
(5, 'Views', '2011-11-14', 'Hip-hop/Rap', 2, 'jkjgfbkl-9754-68l4-58d4-jhkgfdtrtkk5.PNG'),
(6, 'Loud', '2010-11-11', 'Pop', 1, '6ed0a869-21de-4a68-9903-642feeb3a780.PNG'),
(7, 'Flower Boy', '2017-07-20', 'Hip-hop/Rap', 4, '3e484890-3253-478a-b89a-1eac1605b20d.PNG'),
(8, '2014 Forest Hills Drive', '2014-12-08', 'Hip-hop/Rap', 5, '5a98d6f3-34ac-458b-9fad-02c8f0a019ea.PNG'),
(9, '4 Your Eyez Only', '2016-12-09', 'Hip-hop/Rap', 5, 'e2254e46-44fd-4953-968f-f238cf1d5fad.PNG'),
(10, 'Starboy', '2016-09-20', 'R&B/Soul', 6, '232630c8-4219-4168-922a-266673861b02.PNG'),
(11, 'Beauty Behind the Madness', '2015-08-28', 'R&B/Soul', 6, '571c2880-3d6c-4408-8d5f-e2ec71d2b79f.PNG'),
(12, 'Graduation', '2007-09-04', 'Hip-hop/Rap', 7, '38a2e359-2fa5-4c26-9dfd-10bb38cb66a4.PNG'),
(14, 'The Heart Part 4 - Single', '2017-03-23', 'Hip-hop/Rap', 3, 'd34bca88-cf24-4ac3-a8c4-a21c547b15b0.PNG'),
(15, 'Чёрный альбом', '1990-01-01', 'Rock', 9, '6d7aad58-0d7a-4679-865d-573fef05fab7.PNG'),
(16, 'Группа крови', '1988-01-01', 'Rock', 9, 'e93d6294-2894-49fa-8103-633eeacca1ee.PNG'),
(17, 'The Heart Part 5 - Single', '2022-05-08', 'Hip-hop/Rap', 3, '98f97380-c8dd-47d2-b2d9-e369bf8cb669.PNG'),
(18, 'Songs of Innocence', '2014-09-09', 'Rock', 8, '68a52df4-3db2-42af-b93a-f28431780241.PNG'),
(19, 'Mr. Morale & The Big Steppers', '2022-05-13', 'Hip-hop/Rap', 3, '92abe2ff-3fa4-44f0-ac5e-824a3d104900.PNG'),
(21, 'Good Kid, M.A.A.D City', '2012-10-22', 'Hip-hop/Rap', 3, 'cf7fb429-9a29-47d8-bf69-b9026113a5a9.PNG');

--
-- Триггеры `album`
--
DELIMITER $$
CREATE TRIGGER `delete_song_triger` BEFORE DELETE ON `album` FOR EACH ROW BEGIN
	DELETE song 
	FROM song 
	INNER JOIN song_in_album ON song_in_album.Song_id = song.Song_id
    WHERE song_in_album.Album_id = OLD.Album_id; 
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `album_in_library`
--

CREATE TABLE `album_in_library` (
  `User_id` int NOT NULL,
  `Album_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `album_in_library`
--

INSERT INTO `album_in_library` (`User_id`, `Album_id`) VALUES
(1, 1),
(1, 2),
(1, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `artist`
--

CREATE TABLE `artist` (
  `Artist_id` int NOT NULL,
  `Nickname` varchar(40) NOT NULL,
  `Email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Avatar_file` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Page_photo_file` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `artist`
--

INSERT INTO `artist` (`Artist_id`, `Nickname`, `Email`, `Phone`, `Avatar_file`, `Page_photo_file`) VALUES
(1, 'Rihanna', 'rihanna@mail.com', '+2956475867', 'ef6def6a-5966-4b45-ab3c-563e1ee24367.PNG', 'd9facdaf-38a3-4bcb-b8ae-bc439bcd8823.PNG'),
(2, 'Drake', 'drake@mail.com', '+2968498165', '1e78e568-fedb-428a-b670-71377dcf4da2.PNG', '2c0437d0-7a74-4f5c-afc0-4b802baedd85.PNG'),
(3, 'Kendrick Lamar', 'kendrick@mail.com', '+2968494432', '9d401646-8663-4d73-a0d7-2863d7e03330.PNG', 'ab14d462-2144-41de-bc47-931df8715547.PNG'),
(4, 'Tyler, The Creator', 'tyler@mail.com', '+2968497140', '022d10a1-dc18-4072-8dee-30af49ba823b.PNG', '1eafc45a-7da1-4d0d-a6ce-d20ec44bcd52.PNG'),
(5, 'J. Cole', 'jcole@mail.com', '+2968493041', '3d364615-936a-4991-af27-f72abe69f91f.PNG', '32f47ce3-24ae-4b22-93ac-a8d635acb924.PNG'),
(6, 'The Weeknd', 'weeknd@mail.com', '', 'ea85d0a9-09db-4202-a481-9bef42672c0d.PNG', '1f3dfd5e-f0ef-4b0d-807a-f640ff626eb6.PNG'),
(7, 'Kanye West', 'kanye@mail.com', '+2968499868', '85be66af-4be7-4eff-b884-c2e8407a7365.PNG', '85a95848-521d-442f-8a24-14c90d3078bc.PNG'),
(8, 'U2', 'u2@mail.com', '+2956474004', 'a546e16f-6c79-49c2-8a05-b521f9614766.PNG', '46df068c-74b4-4cad-b33c-806636e80988.PNG'),
(9, 'Кино', '', '', '8215bf82-e73c-4afe-b4fd-7b8fcab0d8be.PNG', '789d4a4f-4736-4f1e-88a8-5df3891061a7.PNG');

-- --------------------------------------------------------

--
-- Структура таблицы `chart`
--

CREATE TABLE `chart` (
  `Chart_id` int NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Cover_file` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `chart`
--

INSERT INTO `chart` (`Chart_id`, `Name`, `Cover_file`) VALUES
(2, 'A-LIST Pop', '9c8b4e77-7fc2-4acb-a34b-bc9a6add9be6.PNG'),
(3, 'Rap Life', '1046f196-b80e-4ca0-9a0c-7aaaeb664262.PNG'),
(4, 'Танцпоп', 'e435657c-c443-44bf-9f26-8033d5dc8dec.PNG'),
(5, 'Поп-музыка вне времени', '9930fa81-8f91-42ae-8f12-96e28363d0b4.PNG');

-- --------------------------------------------------------

--
-- Структура таблицы `featuring_artist`
--

CREATE TABLE `featuring_artist` (
  `Song_id` int NOT NULL,
  `Feat_artist_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `featuring_artist`
--

INSERT INTO `featuring_artist` (`Song_id`, `Feat_artist_id`) VALUES
(4, 1),
(5, 1),
(46, 2),
(95, 2),
(126, 2),
(35, 8);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(4);

-- --------------------------------------------------------

--
-- Структура таблицы `listener`
--

CREATE TABLE `listener` (
  `Listener_id` int NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  `Surname` varchar(30) NOT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Name` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `listener`
--

INSERT INTO `listener` (`Listener_id`, `role`, `Surname`, `Phone`, `Name`, `Email`) VALUES
(1, 'role', 'Kireev', '89298888888', 'Artem', 'tema@yandex.ru'),
(2, 'role', 'Kireev', '89297777777', 'Oleg', 'oleg@yandex.ru'),
(3, 'role', 'Larin', '89256666666', 'Alexei', 'alexei@yandex.ru');

-- --------------------------------------------------------

--
-- Структура таблицы `song`
--

CREATE TABLE `song` (
  `Song_id` int NOT NULL,
  `Name` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Main_artist_id` int DEFAULT NULL,
  `Song_file` varchar(60) DEFAULT NULL,
  `Listening` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song`
--

INSERT INTO `song` (`Song_id`, `Name`, `Main_artist_id`, `Song_file`, `Listening`) VALUES
(4, 'LOYALTY. (FEAT. RIHANNA.)', 3, 'Kendrick Lamar feat. Rihanna - LOYALTY..mp3', 751),
(5, 'Too Good (feat. Rihanna)', 2, NULL, 0),
(6, 'Hype', 2, NULL, 0),
(11, 'January 28th', 5, NULL, 0),
(12, '03\' Adolescence', 5, NULL, 0),
(13, 'No Role Modelz', 5, NULL, 0),
(14, 'Immortal', 5, NULL, 0),
(15, 'Ville Mentality', 5, NULL, 0),
(16, 'Starboy (feat. Daft Punk)', 6, NULL, 0),
(17, 'Party Monster', 6, NULL, 0),
(18, 'False Alarm', 6, NULL, 0),
(19, 'Reminder', 6, NULL, 0),
(20, 'Rockin\'', 6, NULL, 0),
(21, 'Real Life', 6, NULL, 0),
(22, 'Losers (feat. Labrinth)', 6, NULL, 0),
(23, 'Often', 6, NULL, 0),
(24, 'The Hills', 6, NULL, 0),
(26, 'BLOOD.', 3, 'Kendrick Lamar - BLOOD..mp3', 9),
(27, 'DNA.', 3, 'Kendrick Lamar - DNA..mp3', 1004),
(28, 'YAH.', 3, 'Kendrick Lamar - YAH..mp3', 1),
(29, 'ELEMENT.', 3, 'Kendrick Lamar - ELEMENT..mp3', 650),
(30, 'FEEL.', 3, 'Kendrick Lamar - FEEL..mp3', 1),
(31, 'PRIDE.', 3, 'Kendrick Lamar - PRIDE..mp3', 640),
(32, 'HUMBLE.', 3, 'Kendrick Lamar - HUMBLE..mp3', 1203),
(33, 'LUST.', 3, 'Kendrick Lamar - LUST..mp3', 1),
(34, 'LOVE. (FEAT. ZACARI.)', 3, 'Kendrick Lamar feat. Zacari - LOVE..mp3', 720),
(35, 'XXX. (FEAT. U2.)', 3, 'Kendrick Lamar feat. U2 - XXX..mp3', 601),
(36, 'FEAR.', 3, 'Kendrick Lamar - FEAR..mp3', 3),
(37, 'GOD.', 3, 'Kendrick Lamar - GOD..mp3', 0),
(38, 'DUCKWORTH.', 3, 'Kendrick Lamar - DUCKWORTH..mp3', 0),
(39, 'Good Morning', 7, NULL, 0),
(40, 'Champion', 7, NULL, 0),
(41, 'Stronger', 7, NULL, 0),
(42, 'I Wonder', 7, NULL, 0),
(43, 'S&M', 1, '6434f00f-dd9c-4157-881f-15d221769ab5.mp3', 0),
(44, 'Man Down', 1, '2ec9ed03-1f80-4fc2-9ede-b958510bd282.mp3', 0),
(45, 'James Joint', 1, NULL, 0),
(46, 'Work (feat. Drake)', 1, '5e87fce8-d54d-4a8e-9b38-e5982eb9595f.mp3', 0),
(53, 'November', 4, NULL, 0),
(54, 'Glitter', 4, NULL, 0),
(55, 'The Heart Part 4', 3, 'Kendrick_Lamar_-_The_Heart_Part_4_48247991.mp3', 0),
(56, 'Лето', 9, 'Kino_-_Konchitsya_leto_47828973.mp3', 1),
(57, 'Кукушка', 9, 'Viktor_Cojj_-_Kukushka_54697834.mp3', 2),
(58, 'Следи за собой', 9, 'Kino_-_Sledi_za_sobojj_47828981.mp3', 0),
(59, 'Группа крови', 9, 'Kino_-_Gruppa_krovi_47829944.mp3', 0),
(60, 'Закрой за мной дверь, я ухожу', 9, 'Kino_-_Zakrojj_za_mnojj_dver_ya_ukhozhu_47829945.mp3', 0),
(61, 'Спокойная ночь', 9, 'Kino_-_Spokojjnaya_noch_48614890.mp3', 0),
(62, 'Легенда', 9, 'Kino_-_Legenda_47829957.mp3', 0),
(64, 'The Miracle (Of Joey Ramone)', 8, '08ed58c9-de99-4dc5-9c2a-79f1da6d72b5.mp3', 0),
(65, 'Every Breaking Wave', 8, '4560b213-1711-4d02-ba8a-19eaded9788b.mp3', 0),
(66, 'California (There Is No End to Love)', 8, '2c2c2948-840c-40f6-9280-c5c9367542e4.mp3', 0),
(67, 'Song for Someone', 8, 'a8daeeb6-866e-4f1a-a4a9-3fc49404c697.mp3', 0),
(68, 'Iris (Hold Me Close)', 8, '10b7f5ca-ad18-4deb-ab54-d3022143dcb9.mp3', 0),
(69, 'Volcano', 8, '90daa5f1-aef6-41d4-8ea6-e78c06fca168.mp3', 0),
(70, 'Raised By Wolves', 8, '76b67b3a-85bb-4bd1-8c63-2eeb132e7bc5.mp3', 0),
(71, 'Cedarwood Road', 8, '128ed50d-133d-435e-9c17-d34f16f85fca.mp3', 0),
(72, 'Sleep Like A Baby Tonight', 8, '81d75fcc-7c8f-470b-ad3f-84b39ff49a59.mp3', 0),
(73, 'This Is Where You Can Reach Me Now', 8, '8215f53f-ddfc-4997-9960-3743c375d3d8.mp3', 0),
(74, 'The Troubles (feat. Lykke Li)', 8, '359a7f37-86fb-4327-b3e8-169b1835bebc.mp3', 0),
(75, 'The Heart Part 5', 3, 'd5dd13ab-5677-43b1-9f63-23d41d885201.mp3', 2),
(76, 'United In Grief', 3, 'ef65485b-7ad8-4c41-8cb3-7b9fabb3c228.mp3', 0),
(77, 'N95', 3, '481aa753-6e8e-4fb5-b6e0-a33b35176fd0.mp3', 670),
(78, 'Worldwide Steppers', 3, '12035476-b485-42f0-af27-86ff3ad85852.mp3', 0),
(79, 'Die Hard', 3, 'adf808d3-d6c9-4dbf-88a9-125b781f9d5b.mp3', 0),
(80, 'Father Time (feat. Sampha)', 3, '6eb2580f-b5e8-425b-bd46-d0e990e39d97.mp3', 1),
(81, 'Rich (Interlude)', 3, 'c81bf8e6-05e4-4572-bb53-8f803113b2a8.mp3', 1),
(82, 'Rich Spirit', 3, '64a7da06-177b-40c7-8b15-214d57e87e23.mp3', 480),
(83, 'We Cry Together', 3, '59176d09-acd0-4091-85f1-a5b8f4696c4a.mp3', 0),
(84, 'Purple Hearts', 3, 'd172f467-90b5-47f4-8af4-ba0125a03949.mp3', 0),
(85, 'Count Me Out', 3, 'a7100e20-8b76-42c5-a45f-f0140fa66e56.mp3', 1),
(86, 'Crown', 3, '969e32c6-66e5-4e48-b6c2-2c2a28983013.mp3', 4),
(87, 'Silent Hill', 3, '4d28c538-d7d8-4bc9-aeb6-0c657f5c561f.mp3', 1),
(88, 'Savior (Interlude)', 3, '282b3137-d3d9-4078-b135-31db56b48a1d.mp3', 2),
(89, 'Savior', 3, 'd3e5984a-fb4f-4d52-97bb-d60cf7be48af.mp3', 5),
(90, 'Auntie Diaries', 3, '060057fa-958b-49ba-ad4c-98d94911f057.mp3', 0),
(91, 'Mr. Morale', 3, '8e3c1bdb-4737-49cf-8982-15a527f8333e.mp3', 0),
(92, 'Mother I Sober (feat. Beth Gibbons)', 3, 'a3418841-a094-4864-9cbc-3df25311652f.mp3', 0),
(93, 'Mirror', 3, '255b683f-a937-4046-bd23-82a6670d3159.mp3', 1),
(95, 'What\'s My Name? (feat. Drake)', 1, '351fac1b-b479-4893-8140-6fcb8b0a96a9.mp3', 0),
(96, 'Cheers (Drink to That)', 1, 'b7b27d87-e247-446c-8261-28a1dc9141a7.mp3', 0),
(97, 'Fading', 1, '13ea2205-78d4-48f7-be81-97dbd358bb2e.mp3', 0),
(98, 'Only Girl (In the World)', 1, '02b2b8dc-ab19-4cfa-ab8e-f35a2883d2b8.mp3', 0),
(99, 'California King Bed', 1, 'cea8afad-0afb-439b-9877-e57aaf415663.mp3', 0),
(101, 'Raining Men (feat. Nicki Minaj)', 1, '26780129-dd5c-491c-a831-cd2f1157cef8.mp3', 0),
(102, 'Complicated', 1, 'fa7a7637-7e4b-4473-a850-05feb2329717.mp3', 0),
(103, 'Skin', 1, 'c9be4769-c58f-4a2c-9a95-386af0aa6db7.mp3', 0),
(104, 'Love the Way You Lie, Pt. 2 (feat. Eminem)', 1, '98202031-d43e-44fe-b171-1ae9c3a9f162.mp3', 0),
(105, 'Wesley\'s Theory (feat. George Clinton & Thundercat)', 3, '94bf1956-f632-4c2b-b3a8-f8e0f9c5584d.mp3', 3),
(106, 'For Free? (Interlude)', 3, '6ddd6e45-8c80-4c4a-baa3-aabdd30c0c62.mp3', 0),
(107, 'King Kunta', 3, '352cd5b9-fdda-432d-a599-bbd8e6a69d61.mp3', 0),
(108, 'Institutionalized (feat. Bilal, Anna Wise & Snoop Dogg)', 3, '8c3a85b7-7603-4b16-a1ac-e8dbe5dec69e.mp3', 1),
(109, 'These Walls (feat. Bilal, Anna Wise & Thundercat)', 3, '6d737fda-4e12-4a37-a844-1c6365bac78e.mp3', 1),
(110, 'u', 3, '807ad674-6966-4e0a-9fae-6f7e645e5b60.mp3', 0),
(111, 'Alright', 3, '61adb2a4-f5d8-47af-ba5d-8ad83aa46630.mp3', 580),
(112, 'For Sale? (Interlude)', 3, '19bfa3bf-6f48-48f6-a1ec-77d72af4a58c.mp3', 0),
(113, 'Momma', 3, '26341d9e-08c7-4771-8743-538bf53f8c8e.mp3', 1),
(114, 'Hood Politics', 3, '5d9163f7-89a1-4437-a79e-aafdc6cd8edc.mp3', 0),
(115, 'How Much a Dollar Cost (feat. James Fauntleroy & Ronald Isley)', 3, '62e2a818-2f61-4d38-82ac-2cfee70f4054.mp3', 3),
(116, 'Complexion (A Zulu Love) [feat. Rapsody]', 3, '2751a20f-f4ba-4090-81a3-9998e9fa7718.mp3', 0),
(117, 'The Blacker the Berry', 3, '0d081dd0-2d0b-4783-b7f0-abdd5bb07384.mp3', 2),
(118, 'You Ain\'t Gotta Lie (Momma Said)', 3, '2cecbaa8-e176-477a-b8b6-1ce04dc0026f.mp3', 0),
(119, 'i', 3, '5c3c8cdb-c8dc-47f8-b49d-0f8b0e9c75d8.mp3', 0),
(120, 'Mortal Man', 3, '8a36f1d0-6071-4ffb-a99c-ccc0f27047c0.mp3', 0),
(121, 'Sherane a.k.a Master Splinter\'s Daughter', 3, 'f74908b9-66a4-40a0-955d-2a69b7723da4.mp3', 0),
(122, 'Bitch, Don\'t Kill My Vibe', 3, '6c7bbafb-5d71-4093-9504-5d71521cd6b5.mp3', 620),
(123, 'Backseat Freestyle', 3, '4aa9c25c-bda8-4a4e-8bd3-52cb290f7b9c.mp3', 660),
(124, 'The Art of Peer Pressure', 3, '0bf01b18-e494-4b4d-9eb2-a35ca3bd61b9.mp3', 1),
(125, 'Money Trees (feat. Jay Rock)', 3, 'ac3f5e42-4ae2-4a03-9b37-e14357908f21.mp3', 740),
(126, 'Poetic Justic (feat. Drake)', 3, 'ecd5312f-5dcb-45be-89db-45e3abf33c9e.mp3', 1),
(127, 'Good Kid', 3, 'f73750fe-bc8e-4470-9e61-3df876c86f52.mp3', 1),
(128, 'm.A.A.d City (feat. MC Eiht)', 3, '0ed6fe8c-e3c4-4e4a-baa4-2acde512767b.mp3', 680),
(129, 'Swimming Pools (Drank) [Extended version]', 3, 'e6d26fd5-d7ac-4b5b-af45-4449d683162f.mp3', 800),
(130, 'Sing About Me, I\'m Dying of Thirst', 3, '9444c6ed-91e4-4589-b199-649cf25b69f9.mp3', 2),
(131, 'Real (feat. Anna Wise)', 3, 'a3f17888-f1e1-4815-ab2a-d3c3f4a6313d.mp3', 0),
(132, 'Compton (feat. Dr. Dre)', 3, '6c78b382-d224-4580-bf2d-206d87cd4daf.mp3', 0);

--
-- Триггеры `song`
--
DELIMITER $$
CREATE TRIGGER `insert_triger` BEFORE INSERT ON `song` FOR EACH ROW BEGIN
CALL FixIncorrectArtistId(NEW.Main_artist_id, @newId);
SET NEW.Main_artist_id = @newId;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `song_in_album`
--

CREATE TABLE `song_in_album` (
  `Album_id` int NOT NULL,
  `Song_id` int NOT NULL,
  `Is_lead_song` tinyint(1) NOT NULL,
  `Serial_number` int NOT NULL,
  `Order_in_album_number` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song_in_album`
--

INSERT INTO `song_in_album` (`Album_id`, `Song_id`, `Is_lead_song`, `Serial_number`, `Order_in_album_number`) VALUES
(2, 4, 0, 6, 6),
(5, 5, 0, 16, 16),
(5, 6, 1, 5, 5),
(8, 11, 1, 2, 2),
(8, 12, 1, 4, 4),
(8, 13, 1, 9, 9),
(9, 14, 1, 2, 2),
(9, 15, 0, 4, 4),
(10, 16, 1, 1, 1),
(10, 17, 1, 2, 2),
(10, 18, 1, 3, 3),
(10, 19, 1, 4, 4),
(10, 20, 0, 5, 5),
(11, 21, 1, 1, 1),
(11, 22, 0, 2, 2),
(11, 23, 0, 4, 4),
(11, 24, 0, 5, 5),
(2, 26, 0, 1, 1),
(2, 27, 1, 2, 2),
(2, 28, 0, 3, 3),
(2, 29, 0, 4, 4),
(2, 30, 0, 5, 5),
(2, 31, 0, 7, 7),
(2, 32, 1, 8, 8),
(2, 33, 0, 9, 9),
(2, 34, 0, 10, 10),
(2, 35, 0, 11, 11),
(2, 36, 0, 12, 12),
(2, 37, 0, 13, 13),
(2, 38, 0, 14, 14),
(12, 39, 1, 1, 1),
(12, 40, 1, 2, 2),
(12, 41, 1, 3, 3),
(12, 42, 1, 4, 4),
(6, 43, 1, 1, 1),
(6, 44, 0, 7, 7),
(4, 45, 1, 2, 2),
(4, 46, 0, 4, 4),
(7, 53, 1, 12, 12),
(7, 54, 0, 13, 13),
(14, 55, 1, 1, 1),
(15, 56, 0, 1, 1),
(15, 57, 1, 5, 5),
(15, 58, 0, 8, 8),
(16, 59, 1, 1, 1),
(16, 60, 0, 2, 2),
(16, 61, 0, 4, 4),
(16, 62, 0, 11, 11),
(18, 64, 1, 1, 1),
(18, 65, 1, 2, 2),
(18, 66, 0, 3, 3),
(18, 67, 0, 4, 4),
(18, 68, 0, 5, 5),
(18, 69, 0, 6, 6),
(18, 70, 0, 7, 7),
(18, 71, 0, 8, 8),
(18, 72, 0, 9, 9),
(18, 73, 0, 10, 10),
(18, 74, 0, 11, 11),
(17, 75, 1, 1, 1),
(19, 76, 0, 1, 1),
(19, 77, 1, 2, 2),
(19, 78, 0, 3, 3),
(19, 79, 0, 4, 4),
(19, 80, 0, 5, 5),
(19, 81, 0, 6, 6),
(19, 82, 1, 7, 7),
(19, 83, 0, 8, 8),
(19, 84, 0, 9, 9),
(19, 85, 0, 1, 10),
(19, 86, 0, 2, 11),
(19, 87, 0, 3, 12),
(19, 88, 0, 4, 13),
(19, 89, 1, 5, 14),
(19, 90, 0, 6, 15),
(19, 91, 0, 7, 16),
(19, 92, 0, 8, 17),
(19, 93, 0, 9, 18),
(6, 95, 1, 2, 2),
(6, 96, 0, 3, 3),
(6, 97, 0, 4, 4),
(6, 98, 0, 5, 5),
(6, 99, 0, 6, 6),
(6, 101, 0, 8, 8),
(6, 102, 0, 9, 9),
(6, 103, 0, 10, 10),
(6, 104, 0, 11, 11),
(1, 105, 1, 1, 1),
(1, 106, 0, 2, 2),
(1, 107, 0, 3, 3),
(1, 108, 0, 4, 4),
(1, 109, 0, 5, 5),
(1, 110, 0, 6, 6),
(1, 111, 1, 7, 7),
(1, 112, 0, 8, 8),
(1, 113, 0, 9, 9),
(1, 114, 0, 10, 10),
(1, 115, 1, 11, 11),
(1, 116, 0, 12, 12),
(1, 117, 0, 13, 13),
(1, 118, 0, 14, 14),
(1, 119, 0, 15, 15),
(1, 120, 0, 16, 16),
(21, 121, 0, 1, 1),
(21, 122, 0, 2, 2),
(21, 123, 0, 3, 3),
(21, 124, 0, 4, 4),
(21, 125, 1, 5, 5),
(21, 126, 0, 6, 6),
(21, 127, 0, 7, 7),
(21, 128, 0, 8, 8),
(21, 129, 1, 9, 9),
(21, 130, 0, 10, 10),
(21, 131, 0, 11, 11),
(21, 132, 0, 12, 12);

-- --------------------------------------------------------

--
-- Структура таблицы `song_in_chart`
--

CREATE TABLE `song_in_chart` (
  `Chart_id` int NOT NULL,
  `Song_id` int NOT NULL,
  `Serial_number` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song_in_chart`
--

INSERT INTO `song_in_chart` (`Chart_id`, `Song_id`, `Serial_number`) VALUES
(5, 5, 0),
(2, 6, 4),
(3, 6, 4),
(3, 13, 1),
(2, 16, 1),
(4, 16, 0),
(5, 17, 0),
(2, 24, 2),
(3, 32, 5),
(5, 34, 0),
(5, 39, 0),
(3, 41, 6),
(4, 41, 0),
(5, 41, 0),
(5, 43, 0),
(2, 44, 5),
(5, 44, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `song_in_library`
--

CREATE TABLE `song_in_library` (
  `User_id` int NOT NULL,
  `Song_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song_in_library`
--

INSERT INTO `song_in_library` (`User_id`, `Song_id`) VALUES
(1, 4),
(1, 5),
(1, 6),
(1, 13),
(1, 16),
(1, 26),
(1, 30),
(1, 31),
(1, 32),
(1, 35),
(1, 38),
(1, 43),
(1, 55),
(1, 59),
(1, 76),
(1, 80),
(1, 82);

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `song_in_library_with_name`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `song_in_library_with_name` (
`Name` varchar(70)
,`Song_id` int
,`User_id` int
);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `username` varchar(40) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`user_id`, `is_active`, `username`, `password`) VALUES
(1, 1, 'admin', '1'),
(2, 1, 'user2new', '2'),
(3, 1, 'user3', '3');

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `roles` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(1, 'USER'),
(2, 'USER'),
(1, 'ADMIN'),
(3, 'USER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Структура для представления `song_in_library_with_name`
--
DROP TABLE IF EXISTS `song_in_library_with_name`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `song_in_library_with_name`  AS SELECT `song_in_library`.`User_id` AS `User_id`, `song_in_library`.`Song_id` AS `Song_id`, `song`.`Name` AS `Name` FROM (`song_in_library` join `song` on((`song_in_library`.`Song_id` = `song`.`Song_id`))) ;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`Album_id`),
  ADD KEY `album_ibfk_1` (`Artist_id`);

--
-- Индексы таблицы `album_in_library`
--
ALTER TABLE `album_in_library`
  ADD PRIMARY KEY (`User_id`,`Album_id`);

--
-- Индексы таблицы `artist`
--
ALTER TABLE `artist`
  ADD PRIMARY KEY (`Artist_id`);

--
-- Индексы таблицы `chart`
--
ALTER TABLE `chart`
  ADD PRIMARY KEY (`Chart_id`);

--
-- Индексы таблицы `featuring_artist`
--
ALTER TABLE `featuring_artist`
  ADD PRIMARY KEY (`Song_id`,`Feat_artist_id`),
  ADD KEY `featuring_artist_ibfk_2` (`Feat_artist_id`);

--
-- Индексы таблицы `listener`
--
ALTER TABLE `listener`
  ADD PRIMARY KEY (`Listener_id`),
  ADD KEY `XIE1Listener` (`Surname`);

--
-- Индексы таблицы `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`Song_id`),
  ADD KEY `song_ibfk_1` (`Main_artist_id`);

--
-- Индексы таблицы `song_in_album`
--
ALTER TABLE `song_in_album`
  ADD PRIMARY KEY (`Song_id`,`Album_id`),
  ADD KEY `song_in_album_ibfk_2` (`Album_id`);

--
-- Индексы таблицы `song_in_chart`
--
ALTER TABLE `song_in_chart`
  ADD PRIMARY KEY (`Song_id`,`Chart_id`),
  ADD KEY `song_in_chart_ibfk_2` (`Chart_id`);

--
-- Индексы таблицы `song_in_library`
--
ALTER TABLE `song_in_library`
  ADD PRIMARY KEY (`User_id`,`Song_id`),
  ADD KEY `R_4` (`Song_id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `users_username_uindex` (`username`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `users_roles_users_id_fk` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`Artist_id`) REFERENCES `artist` (`Artist_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `featuring_artist`
--
ALTER TABLE `featuring_artist`
  ADD CONSTRAINT `featuring_artist_ibfk_1` FOREIGN KEY (`Song_id`) REFERENCES `song` (`Song_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `featuring_artist_ibfk_2` FOREIGN KEY (`Feat_artist_id`) REFERENCES `artist` (`Artist_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `song`
--
ALTER TABLE `song`
  ADD CONSTRAINT `song_ibfk_1` FOREIGN KEY (`Main_artist_id`) REFERENCES `artist` (`Artist_id`) ON DELETE CASCADE ON UPDATE SET NULL;

--
-- Ограничения внешнего ключа таблицы `song_in_album`
--
ALTER TABLE `song_in_album`
  ADD CONSTRAINT `song_in_album_ibfk_1` FOREIGN KEY (`Song_id`) REFERENCES `song` (`Song_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `song_in_album_ibfk_2` FOREIGN KEY (`Album_id`) REFERENCES `album` (`Album_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `song_in_chart`
--
ALTER TABLE `song_in_chart`
  ADD CONSTRAINT `song_in_chart_ibfk_1` FOREIGN KEY (`Song_id`) REFERENCES `song` (`Song_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `song_in_chart_ibfk_2` FOREIGN KEY (`Chart_id`) REFERENCES `chart` (`Chart_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `song_in_library`
--
ALTER TABLE `song_in_library`
  ADD CONSTRAINT `song_in_library_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `listener` (`Listener_id`),
  ADD CONSTRAINT `song_in_library_ibfk_2` FOREIGN KEY (`Song_id`) REFERENCES `song` (`Song_id`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `users_roles_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
