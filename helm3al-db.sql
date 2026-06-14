/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.42 : Database - toko_helm3al
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`toko_helm3al` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `toko_helm3al`;

/*Table structure for table `produk` */

DROP TABLE IF EXISTS `produk`;

CREATE TABLE `produk` (
  `id_produk` int NOT NULL AUTO_INCREMENT,
  `kode_produk` varchar(20) NOT NULL,
  `nama_produk` varchar(100) NOT NULL,
  `merk` varchar(50) NOT NULL,
  `jenis` varchar(50) DEFAULT NULL,
  `ukuran` varchar(10) DEFAULT NULL,
  `warna` varchar(30) DEFAULT NULL,
  `harga` int NOT NULL,
  `stok` int NOT NULL,
  `id_supplier` int DEFAULT NULL,
  PRIMARY KEY (`id_produk`),
  UNIQUE KEY `kode_produk` (`kode_produk`),
  KEY `id_supplier` (`id_supplier`),
  CONSTRAINT `produk_ibfk_1` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `produk` */

insert  into `produk`(`id_produk`,`kode_produk`,`nama_produk`,`merk`,`jenis`,`ukuran`,`warna`,`harga`,`stok`,`id_supplier`) values 
(1,'H001','KYT R10','KYT','Full Face','L','Hitam',750000,9,1),
(2,'H002','INK Centro','INK','Half Face','M','Merah',450000,15,1),
(3,'H003','NHK GP1000','NHK','Full Face','XL','Putih',850000,8,2),
(4,'H004','Cargloss Retro','Cargloss','Retro','L','Coklat',350000,12,2),
(5,'H005','NHK R1','NHK','Half Face','M','Hitam',50000,12,2);

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `id_supplier` int NOT NULL AUTO_INCREMENT,
  `kode_supplier` varchar(20) NOT NULL,
  `nama_supplier` varchar(100) NOT NULL,
  `no_telp` varchar(20) DEFAULT NULL,
  `alamat` text,
  PRIMARY KEY (`id_supplier`),
  UNIQUE KEY `kode_supplier` (`kode_supplier`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `supplier` */

insert  into `supplier`(`id_supplier`,`kode_supplier`,`nama_supplier`,`no_telp`,`alamat`) values 
(1,'SUP001','PT Helm Nusantara','081234567890','Denpasar'),
(2,'SUP002','Bali Helmet Store','082345678901','Badung');

/*Table structure for table `transaksi` */

DROP TABLE IF EXISTS `transaksi`;

CREATE TABLE `transaksi` (
  `id_transaksi` int NOT NULL AUTO_INCREMENT,
  `kode_transaksi` varchar(30) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `id_produk` int NOT NULL,
  `jumlah` int NOT NULL,
  `harga` int NOT NULL,
  `total` int NOT NULL,
  `nama_pelanggan` varchar(100) DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`),
  UNIQUE KEY `kode_transaksi` (`kode_transaksi`),
  KEY `id_produk` (`id_produk`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`),
  CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `transaksi` */

insert  into `transaksi`(`id_transaksi`,`kode_transaksi`,`tanggal_transaksi`,`id_produk`,`jumlah`,`harga`,`total`,`nama_pelanggan`,`id_user`) values 
(1,'TRX001','2026-06-14',1,1,750000,750000,'Pelanggan Test',1),
(2,'TRX002','2026-06-14',3,2,850000,1700000,'Maele',1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `nama_lengkap` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(30) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`id_user`,`nama_lengkap`,`username`,`password`,`role`) values 
(1,'Alzendi Tator','Alzendi10','Alzendi7','Admin'),
(2,'Kasir Helm 3AL','kasir','kasir123','Kasir');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
