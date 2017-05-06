-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2017 at 10:22 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `content` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `author_id`, `post_id`, `content`) VALUES
(1, 1, 1, 'testcomment3 '),
(2, 1, 2, 'testcomment4'),
(3, 1, 3, 'comment'),
(4, 1, 3, 'dadas'),
(5, 1, 3, ''),
(6, 1, 3, '');

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `id_1` int(11) NOT NULL,
  `id_2` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`id_1`, `id_2`) VALUES
(1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(250) NOT NULL,
  `creator_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`id`, `title`, `description`, `creator_id`) VALUES
(1, 'test', 'test3', 1),
(2, 'testttt', 'tesssssssssssst', 1),
(3, 'lllll', 'llllll', 1);

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(250) NOT NULL,
  `content` varchar(5000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `group_id`, `author_id`, `title`, `description`, `content`) VALUES
(1, 1, 1, 'testttt', 'tesssssssssssst', 'tesssssssssssssssssssssstttttttt'),
(2, 1, 1, 'testtttEdited2', 'tesssssssssssst', 'testcomment3 '),
(3, 3, 1, 'adsasda', 'dasdasd', 'sdasdas');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `uname` varchar(20) CHARACTER SET utf8 NOT NULL,
  `password` varchar(256) CHARACTER SET utf8 NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `image` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `uname`, `password`, `email`, `image`) VALUES
(1, 'Mohamed Hegazy', '$2a$10$0HufQr6qhm6EeKEeLv8hTuP6xwyc998CUfrhuEUYRSqWM7Yh0wdK.', 'm.hegazy94@hotmail.com', '.jpg'),
(2, 'Mohamed2', '$2a$10$vXaYGuP2I7deL3zEl8M7wOT/83tVD77TmCT.nnFmEEnXD4b5y9xXy', 'Connief7648@gmail.com', ''),
(3, 'encrypted', '$2a$10$nsHy1a3YK2DCg8oYrOo1SO.K.AfwBNMFjJzvkWA8Hj/gU3anog7I2', 'z.samia.android@gmail.com', ''),
(4, 'encrypted', '$2a$10$JeptBZ6/M6KroDkOC2dJmO.Vh8dksBCcsqguiHFdE1uKomSUrW7Ty', 'z.samia.android@gmail.com', ''),
(5, 'encrypted', '$2a$10$e0bGmUx1ivgvBs51WfqxGeCOeY71Alzx1cccVoZv3hvWozLg4U9uK', 'z.samia.android@gmail.com', ''),
(6, 'encrypted', '$2a$10$v5oCoENsXdcEfxF3kU1ftuYf3W1o.IOcZbqSagJUEzT7O48GVaafi', 'z.samia.android@gmail.com', ''),
(7, 'encrypted', '$2a$10$4dM.z8kS/kErB.7I4jnZX.P2GJIa2EVqgW0triJNzxNNcMc327N9y', 'z.samia.android@gmail.com', ''),
(8, 'encrypted', '$2a$10$r21eo3Le3MSYqUd.QNY8jeBX5e5Vf2Wn6Xcev4jrndra.AFBFwAIW', 'z.samia.android@gmail.com', ''),
(9, 'encrypted2', '$2a$10$dsIVp3sSDB4hS6OMfLINWe1S0nxs9YUaqwpDAngfxjkjCaXKRazM2', 'schuskii@live.de', ''),
(10, 'encrypted2', '$2a$10$e.Nz14ovUQOEm4UGfTmbNOt6U4Jw9Hw4AmVYkUswT5dNXvCGfpOPi', 'schuskii@live.de', ''),
(11, 'hegazy2', '$2a$10$I.12b6sSlHQfiXs5S2VL8eZec/OuxZy91.L42MeN3j8RA8fOO19DW', 'z.samia.android@gmail.com', ''),
(12, 'hegazy2', '$2a$10$Qwu9qZIrE0EWBhPWvnxfXefX.EAO.GCDC5Rx5IOym94h79ygm4ZAy', 'z.samia.android@gmail.com', ''),
(13, 'hegazy2', '$2a$10$Gm4u8XBhMooaY/vgL/x/MOVQ42c9YT2Z7Wmoq/P65BRleJ5EaS9tW', 'z.samia.android@gmail.com', ''),
(14, 'hegazy2', '$2a$10$NugPZYWjdZggDtEtkO3.ZessDL5uy8emqjk0AdIPLn5l4U8WeIYpW', 'z.samia.android@gmail.com', ''),
(15, 'hegazy2', '$2a$10$5SvJNyOKRuuhIL5EACfce.XW0bOyObh5ow1ICMaGLjmlnqaJKPey2', 'z.samia.android@gmail.com', ''),
(16, 'hegazy2', '$2a$10$MyoiTb6W3yTHsDU9wp/rlOJFiTYMpqCceQZ6Z75rwh3vYNTUFSQ4q', 'z.samia.android@gmail.com', ''),
(17, 'hegazy2', '$2a$10$KmAxyJbiKIunbz4ssj83eOQcqgn24u4mUSrgsADjWpuiM7Mq8w24q', 'z.samia.android@gmail.com', ''),
(18, 'hegazy2', '$2a$10$nicYvOSiO/XAnvzyCBcP1uJ3pPUDwEKlWnC28TDwypQk6momnvv8K', 'z.samia.android@gmail.com', ''),
(19, 'hegazy2', '$2a$10$CXW3BWuv849l61jtJ1QsNu1u6sTsPJgiXqEOuYP4vctzqQD6hu3d2', 'z.samia.android@gmail.com', ''),
(20, 'hegazy2', '$2a$10$pqULnPN6jhEygxLPH8ZLV.TB1Xk/AgYqwOfltElbHXZLAeJPQymPe', 'z.samia.android@gmail.com', ''),
(21, 'encrypted', '$2a$10$u0ghGpE4ffGX9gq1mkHvc.2My2zafC4qQcZ6YLaqGRjaVI7Kfya76', 'z.samia.android@gmail.com', ''),
(22, 'encrypted', '$2a$10$t0Nx9BBaaeNO8B5R.upyau8Sz7bNuDpyzpUndqmnC0MgAyAqk886O', 'z.samia.android@gmail.com', ''),
(23, 'encrypted', '$2a$10$pUQ7mrrLIz7WNacufBZCAOj9Q.I9cK3OaJhH/ZxHAlnxtRpuOGYrm', 'z.samia.android@gmail.com', ''),
(24, 'encrypted', '$2a$10$3rEGqvaZS8/g3OfiTVRks.LcN6erx10hy2S4foB1LWT.wSoIYu0jm', 'z.samia.android@gmail.com', ''),
(25, 'encrypted', '$2a$10$zqsaMBlj3ZO9npkksFH4ROcE9sKVe59Pt9Mj6q8h7olsH9IQ66156', 'z.samia.android@gmail.com', ''),
(26, 'encrypted', '$2a$10$qa9eYtknFDPqgsvhmaB/veZgN1ndmdA6DbE6Sa/BdIUvfTYsTxQo2', 'z.samia.android@gmail.com', ''),
(27, 'encrypted', '$2a$10$zrTuAZu9qnvkp8B.RfocMee8lXhoG4yKMIAXqqsHXcqsU3FXhyeqW', 'z.samia.android@gmail.com', ''),
(28, 'encrypted', '$2a$10$.XsKzKJ7zRyKYFfUVfLLnuEdiibNDMY9j2uiA1df838GNMDY/YFNO', 'z.samia.android@gmail.com', ''),
(29, 'encrypted', '$2a$10$4r8j0MpNHUm.Sn.TwQGejeKIP1jCotofcdoYOtanuBmIqyrKPklka', 'z.samia.android@gmail.com', ''),
(30, 'encrypted', '$2a$10$ZYhZ0n3/QcPYz0v9mS4MmOZlwqSQnrIkMLRAOjNo.DLVLf0P8NapC', 'z.samia.android@gmail.com', ''),
(31, 'encrypted', '$2a$10$PUpOtgBYVLSWB1ixamKC8uBePK4EyvvE0S/tkK2Sk66hSAYoDO9Pu', 'z.samia.android@gmail.com', ''),
(32, 'encrypted', '$2a$10$C7ddTlqHh/VgqiPLtbdHLOnfItlLL2alNh3S7n/ID..KARYLey05e', 'z.samia.android@gmail.com', ''),
(33, 'encrypted', '$2a$10$dRWzgBkeJ/TSexnpLZp6uOZhEP28kji1XR0s8CO77YpjotL7slyee', 'z.samia.android@gmail.com', ''),
(34, 'encrypted', '$2a$10$2qQ0YXIDLfHKpS/v7.IChuo5CXufYKGdUmGtgwuPcIg0vyLR38Hbm', 'z.samia.android@gmail.com', ''),
(35, 'encrypted', '$2a$10$cGOb7fsPlhb92WSMABjNSuCW8expj19Mj2.UTwj.2fTFDa2TQsCYy', 'z.samia.android@gmail.com', ''),
(36, 'encrypted', '$2a$10$sQIryP7TtTvAXFvPlC82KOPQbu0STx1oqZKoxZDdhi8Dnjv0ubk92', 'z.samia.android@gmail.com', ''),
(37, 'encrypted', '$2a$10$qAZ9iIXaS5390b8xm2pBUey7CAwQBa1uPAx3i8PjhTQdgE4NEcrDS', 'z.samia.android@gmail.com', ''),
(38, 'hegazy2', '$2a$10$dU2Yup6qLECUx8QFxbgzFuLftUMn6tc2OjsFcTeulGfGGZCiHbdj.', 'm.hega@hotma.com', ''),
(39, 'testimg', '$2a$10$zclzQKqknJ458.sUo7D3R.i7X4nigZdxuoAa0E0pWt.RdxfUhebsi', 'testimg@hotmail.com', ''),
(40, 'testimg2', '$2a$10$ljZ.HW52O9W3MgnAzkQHdOm.RfH7zbpKmiMdrHnLqKvlQ7Zn25BkW', 'testimg2@hotmail.com', ''),
(41, 'testimg', '$2a$10$J.a2FjAuoEGze56RMo7Izu2bRu5piMGf6saY/kNwpfPJ5gTuvzfpi', 'z.samia.android@gmail.com', ''),
(42, 'testimg', '$2a$10$I1o/YT7eqSgJDML5O17WIujLvRDzF4/C9tlSUK/svuZq3leTRqpAK', 'test@test.com', ''),
(43, 'testimg', '$2a$10$vbZQTHq.zXcjf2IXhUfX0.XuINQG.MBVPNPWwZsbqj7gNxQ1F4Cjq', 'z.samia.android@gmail.com', ''),
(44, 'testimg', '$2a$10$aaKdopGHdpPhp64h3yxgKO1ubkJrmKucBbTeBbE998w8TOGt9akcG', 'z.samia.android@gmail.com', ''),
(45, 'testimg', '$2a$10$NgchUNbIduDV3yTvYbGJr.F/VmAFNLy/mclGqblvU30Czutd1CJtW', 'z.samia.android@gmail.com', ''),
(46, 'testimg', '$2a$10$huye9o1KDvq5EItC4tsdKecZKvugN9wsxfMhf04vv/eUO1VgTdoQm', 'z.samia.android@gmail.com', ''),
(47, 'testimg', '$2a$10$V5ZdR1LXQqNfBbGA23ZzlOBtldCmh8fS0cNaJBSj0dBePJWNGClTS', 'z.samia.android@gmail.com', ''),
(48, 'testimg', '$2a$10$/.aP48/M.0jK/QyORaOUX.7TlhrWVCNTHpRcUByR9RxnKvX9.DbNi', 'z.samia.android@gmail.com', ''),
(49, 'testimg', '$2a$10$3p7DoSkF5vQlxGhDkI7TkO4m5Csweo/O.pbzuY44EIxhnHllW/y4K', 'z.samia.android@gmail.com', ''),
(50, 'testimg', '$2a$10$eKP20Vf7txKIlhSue9uMNePJMVzoO1Y1UvWX2brTM7DB3jNjMVtRK', 'z.samia.android@gmail.com', ''),
(51, 'testimg', '$2a$10$a1PRCwJPUlBWVjvBtljhQOeGJBckTnsS8iVlQYDUii7T4CF/CGIHK', 'm.hegazy94@hotmail.com', ''),
(52, 'testimg', '$2a$10$O4Zb4da3NDEMJby2KATXx.jNVaDrBBuDERUNPDsk8.wahp5UPGwAi', 'm.hegazy94@hotmail.com', ''),
(53, 'testimg', '$2a$10$QaJeza7bOK4.izdfhUgAcubLYShEggqlfbFs/SqX4XZ0ntZKqRVUG', 'm.hegazy94@hotmail.com', ''),
(54, 'testimg', '$2a$10$qgc//B7.tqY0rZAK9h9CmeVkSRJQIFl43Zq08lcDHP3DzahMBtrtu', 'm.hegazy94@hotmail.com', ''),
(55, 'testimg', '$2a$10$W.y3gr8Sonl2aBapzef6GepWvcEyjwjBVmH4V3ORam8jPnH3skZUe', 'm.hegazy94@hotmail.com', ''),
(56, 'testimg', '$2a$10$4FJUJHZgOkBWNqrIpOjCWerxGA12jH6EBeXdyBwb5iq.WUnrjWQHO', 'm.hegazy94@hotmail.com', ''),
(57, 'testimg', '$2a$10$Yyrf0bLnNjqVlIzFsqWsy.mD9mD0n3EqiMqAVaoTe61kMxsnKDh0u', 'm.hegazy94@hotmail.com', ''),
(58, 'testimg', '$2a$10$FdCAfKctQoi5IvOaG9ieje5wy2XtNV4FBu69twLHEEN9pXaf1LTti', 'm.hegazy94@hotmail.com', ''),
(59, 'testimg', '$2a$10$oJzEabx1dT8JTO7F8GEtWO1aU13udoIOacwsa/dghrJk5tgqu5LA.', 'm.hegazy94@hotmail.com', ''),
(60, 'testimg', '$2a$10$S4Xz4QKBOnprQaGGjHjwVuZod/kB9UQYogNvFwUxPpXH2z68/D7k6', 'm.hegazy94@hotmail.com', ''),
(61, 'testimg', '$2a$10$ntWWIXODG.V5HA4PqVC3MOWWIlCctMAfMQj0KaOWhHvg7LTkwPgmu', 'm.hegazy94@hotmail.com', ''),
(62, 'testimg', '$2a$10$ToaozTV.DK4A7eN.c.0A5OAi/JaMKAhJfs5.b00/4ufg20/Y8YOLS', 'm.hegazy94@hotmail.com', ''),
(63, 'testimg', '$2a$10$XL/S6BGVoRsA7Vn2iwyVleitf/YXCp60SVVNxXT7BNtdgUsjuwDE.', 'm.hegazy94@hotmail.com', ''),
(64, 'testimg', '$2a$10$CLmcg7obem40LtnUTaauFe1dF.gSd3xvy40kPd6RbK22/ACK6pUgS', 'm.hegazy94@hotmail.com', ''),
(65, 'testimg', '$2a$10$wgpcdtztVwQTVEYmIgbNfeErj3jXtvU35tZS3Go7T4AH0itXzIJMW', 'm.hegazy94@hotmail.com', ''),
(66, 'testimg', '$2a$10$qa7qqe2hBLCctQdihQtjBOE.MgEs8CHqjRTYWULqy6gFq7G9siESi', 'm.hegazy94@hotmail.com', ''),
(67, 'testimg', '$2a$10$QiqgEDxOjACC1zmUTDKX.u7zkU457lK0KxAM/N5cs4vz31yJ.Sto6', 'm.hegazy94@hotmail.com', ''),
(68, 'testimg', '$2a$10$ttdynpDdHXYehyjAbiZbB.olqfxP/Eoo9LTLksqtN/MCN0jZTHzYW', 'm.hegazy94@hotmail.com', ''),
(69, 'testimg', '$2a$10$wwaBfo2uDDbxlT3rkKEtQeYFyS/fPTKsLRZN/3YKhZiBIKKYYTMxS', 'm.hegazy94@hotmail.com', ''),
(70, 'testimg', '$2a$10$/cQj9Lud9D7rJRB3k0PAIuSMCyTOBNsCpSMRRwoWInLXKIPAXCche', 'm.hegazy94@hotmail.com', ''),
(71, 'testimg', '$2a$10$tl98eSc9gjdeRx9.QnCPNeNBsAieSVyhnW66uDzHvRBu1VnccBdjK', 'm.hegazy94@hotmail.com', ''),
(72, 'testimg', '$2a$10$.19tzHrcZR5jGySaTc.OWOKrdY9UF6YcHgMBKQBBpjz3br41E9dP6', 'm.hegazy94@hotmail.com', ''),
(73, 'testimg', '$2a$10$DJG9YiZHLEz2H4YaLqeteezYJSvBUeJDyatmRKiowWuPSPNzhf6F6', 'm.hegazy94@hotmail.com', ''),
(74, 'testimg', '$2a$10$AC5/8ufdMC.m0KukWnQfL.d6RFyOGBJcVLfHBAqxeQ0gMLGC8XPfC', 'm.hegazy94@hotmail.com', ''),
(75, 'testimg', '$2a$10$MakYuTAwt3VoovB42zLZkO3W8UHJI1MRr87QhFw0OXjpBGLVSmcBO', 'z.samia.android@gmail.com', ''),
(76, 'testimg', '$2a$10$JDR.kdlj4quylVDzVMLlVuc7IFK60e6G10Qi.ARLtw1eYH7ZANOB.', 'z.samia.android@gmail.com', ''),
(77, 'testimg', '$2a$10$C10OfjSFkkx3EEStmHanw.N7gjbj74TpQ8QqGMfBnxJ5xDO3uhlhy', 'z.samia.android@gmail.com', ''),
(78, 'testimg', '$2a$10$lnb1CO8D8kyWV3kdWg4FP.AIy/IpIjussWT59RGhJ37U8v5sY0756', 'm.hegazy94@hotmail.com', ''),
(79, 'testimg', '$2a$10$eP0opzuGXLUWyGFimVSdbuQyaqdLii4fVfrZll3jzH.s4GBlgSMrC', 'm.hegazy94@hotmail.com', ''),
(80, 'testimg', '$2a$10$EzSSh2FpC.y2R4ELKgb4OOtWjrMJMZdj.wOuDnZp.yLrm6V64ZczW', 'm.hegazy94@hotmail.com', ''),
(81, 'testimg', '$2a$10$nGVdfef4bmZziQq7cJVVdeEHymhcQAklp9CmOFYxve6bbU1DunZ1.', 'm.hegazy94@hotmail.com', ''),
(82, 'testimg', '$2a$10$MVnJx11qj9DfHgZdG1WKTOkrNXjog1Dr2U4kLkKJ/J70Cwc7oJBBm', 'z.samia.android@gmail.com', '.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `author_id` (`author_id`),
  ADD KEY `post_id` (`post_id`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD KEY `id_1` (`id_1`),
  ADD KEY `id_2` (`id_2`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`),
  ADD KEY `creator` (`creator_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `group_id` (`group_id`),
  ADD KEY `author_id` (`author_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`id_1`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`id_2`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `posts_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
