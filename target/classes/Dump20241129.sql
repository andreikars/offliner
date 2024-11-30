-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: offliner
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (4,52,2,5),(23,52,3,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Смартфоны','Мобильные телефоны с функциями смартфона, включая камеры, экраны и возможности для подключения.'),(2,'Наушники','Аудиооборудование для прослушивания музыки или других аудио, включая проводные и беспроводные модели.'),(3,'Видеокарты','Графические карты для компьютеров, используемые для обработки изображений и видео, включая модели для игр и профессионального использования.'),(4,'Ноутбуки','Переносные компьютеры, включая модели для работы, игр и учебы, с различными характеристиками.'),(5,'Мониторы','Дисплеи для отображения изображения с компьютеров и других устройств, с разными размерами и разрешениями.'),(6,'Телевизоры','Телекоммуникационные устройства для просмотра видео, включая OLED, LED и 4K модели.'),(7,'Компьютерные мыши','Устройства для управления указателем на экране компьютера, включая проводные и беспроводные модели.'),(8,'Клавиатуры','Клавишные устройства для ввода текста и команд на компьютерах, включая механические и мембранные модели.');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'Смартфон iPhone 14','Смартфон Apple iPhone 14 с 128 ГБ памяти, 5G, камеры 12 Мп',2100,50,1,'product/iphone.jpg'),(3,'Наушники Sony WH-1000XM4','Наушники с активным шумоподавлением, длительное время работы',24999.99,100,2,'product/sonyWH.jpg'),(4,'Видеокарта NVIDIA GeForce RTX 3080','Видеокарта для игр с производительностью 4K, поддержка DLSS',89999.99,30,3,'product/nvidia.jpg'),(5,'Ноутбук ASUS ROG Strix G15','Игровой ноутбук с процессором Ryzen 9, 16 ГБ ОЗУ и 1 ТБ SSD',139999.99,20,4,'product/asus.jpg'),(6,'Монитор Samsung Odyssey G7','Изогнутый монитор 32\", разрешение 2560x1440, 240 Гц',37999.99,15,5,'product/monik.jpg'),(15,'Смартфон iPhone 15 pro','Смартфон Apple iPhone 15 с 512ГБ памяти, 5G, камеры 48 Мп',999999,20,1,'product/apple-iphone-15-pro-max-512gb-gray-1.jpg'),(16,'OLED телевизор Sony Bravia A80L XR-55A80L','Миллиарды точных цветов и чистый черный цвет OLED оживляют все, что вы смотрите и во что играете, с естественными оттенками благодаря интеллектуальному когнитивному процессору XR.',6100,42,6,'product/sony_bravia.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'123@123','123','123','123','123','$2a$10$b6TB8nB4ZPDjsyNuQeIjhOHqOcJI3RJm/h7Oa0tCWZz15oMW9q3/G','USER'),(52,'12@12','12','12','12','12','$2a$10$j0UgCklG5i7N5nK5Kr7qGePohHuGhHt1a8g7IwYQKlS73kWLLHV8S','ADMIN'),(304,'andreikars@gmail.com','Andrei','SADOUNIKAV','+375291434295','andreikars','$2a$10$uBKd5wJTAgNGKOylXygW4ugH5ce2FKxtjDEwSYcN11CqfVUrdU.qe','USER'),(352,'34@34','34','34','34','34','$2a$10$A4aRnxIkjDy2CtCpytXZPedet0OUTkHXvSjY3s8MMtg2HHMWC7JiW','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_seq`
--

LOCK TABLES `user_seq` WRITE;
/*!40000 ALTER TABLE `user_seq` DISABLE KEYS */;
INSERT INTO `user_seq` VALUES (451);
/*!40000 ALTER TABLE `user_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-29  4:38:13
