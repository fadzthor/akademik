-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 26, 2022 at 05:36 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `akademik`
--

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `Kd_Dosen` varchar(10) NOT NULL,
  `Nama_Dosen` varchar(100) NOT NULL,
  `Jenis_Kelamin` char(1) NOT NULL,
  `Alamat` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `No_Telp` varchar(13) NOT NULL,
  `Pendidikan_Terakhir` varchar(100) NOT NULL,
  `Pasfoto` text NOT NULL,
  `Status` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`Kd_Dosen`, `Nama_Dosen`, `Jenis_Kelamin`, `Alamat`, `Email`, `No_Telp`, `Pendidikan_Terakhir`, `Pasfoto`, `Status`) VALUES
('UAPDK50012', 'Ratna Sari, S.Kom.,M.Kom', 'P', 'Bulokmanis, Pringsewu', 'ratnasari_uap@gmail.com', '085768229441', 'Megister Ilmu Komputer - S2', '', 'A'),
('UAPDK50013', 'Arief S.Kom.,M.TI', 'L', 'Pringsewu', 'Arief@gmail.com', '085768229444', 'Magister Teknik Informatika', '', 'A'),
('UAPDK50014', 'Shinta,S.Kom., M.SI', 'P', 'Pesawaran', 'Shinta@gmail.com', '085768229477', 'Magister Sistem Infromasi', '', 'A'),
('UAPDK50015', 'wibowo,S.Kom., M.Kom', 'L', 'Pringsewu', 'wibowo@gmail.com', '085768229478', 'Magister Ilmu Komputer', '', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `fakultas`
--

CREATE TABLE `fakultas` (
  `Kd_Fakultas` varchar(5) NOT NULL,
  `Nama_Fakultas` varchar(100) NOT NULL,
  `Status` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fakultas`
--

INSERT INTO `fakultas` (`Kd_Fakultas`, `Nama_Fakultas`, `Status`) VALUES
('FK001', 'Fakultas Teknologi dan Informatika', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal`
--

CREATE TABLE `jadwal` (
  `Kd_Jadwal` varchar(5) NOT NULL,
  `Kd_Mtk` varchar(5) NOT NULL,
  `Kd_DosenPengampu` varchar(10) NOT NULL,
  `Jam_Mulai` time DEFAULT NULL,
  `Jam_selesai` time DEFAULT NULL,
  `Hari` varchar(10) DEFAULT NULL,
  `Status` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jadwal`
--

INSERT INTO `jadwal` (`Kd_Jadwal`, `Kd_Mtk`, `Kd_DosenPengampu`, `Jam_Mulai`, `Jam_selesai`, `Hari`, `Status`) VALUES
('Jd001', 'Mt001', 'UAPDK50012', '10:00:00', '12:00:00', 'SENIN', 'A'),
('Jd002', 'Mt002', 'UAPDK50013', '01:00:00', '03:00:00', 'SELASA', 'A'),
('Jd003', 'Mt003', 'UAPDK50014', '01:00:00', '03:00:00', 'SENIN', 'A'),
('Jd004', 'Mt004', 'UAPDK50015', '08:00:00', '10:00:00', 'SELASA', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `krs`
--

CREATE TABLE `krs` (
  `Kode_Krs` varchar(5) NOT NULL,
  `Npm` varchar(9) NOT NULL,
  `Kd_Mtk` varchar(5) NOT NULL,
  `Kd_Jadwal` varchar(5) NOT NULL,
  `Kd_DosenPengampu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `krs`
--

INSERT INTO `krs` (`Kode_Krs`, `Npm`, `Kd_Mtk`, `Kd_Jadwal`, `Kd_DosenPengampu`) VALUES
('Krs01', '190201003', 'Mt001', 'Jd001', 'UAPDK50012'),
('Krs02', '190201003', 'Mt002', 'Jd002', 'UAPDK50013'),
('Krs03', '190201003', 'Mt003', 'Jd003', 'UAPDK50014'),
('Krs04', '190201003', 'Mt004', 'Jd004', 'UAPDK50015');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `Npm` varchar(9) NOT NULL,
  `Nama_Mhs` varchar(100) NOT NULL,
  `Tempat_Lahir` varchar(100) NOT NULL,
  `Tanggal_Lahir` date NOT NULL,
  `Alamat` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `No_Tlp` varchar(13) NOT NULL,
  `Pasfoto` text NOT NULL,
  `Jenis_kelamin` char(1) NOT NULL,
  `Semester` int(11) NOT NULL,
  `Kd_Prodi` varchar(5) NOT NULL,
  `Status` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`Npm`, `Nama_Mhs`, `Tempat_Lahir`, `Tanggal_Lahir`, `Alamat`, `email`, `No_Tlp`, `Pasfoto`, `Jenis_kelamin`, `Semester`, `Kd_Prodi`, `Status`) VALUES
('190201003', 'Fadzlan Thoriq', 'Pringsewu', '2002-07-01', 'Jl. Melati 1 No.10 Rt,003/Rw.002 Pringombo , Pringsewu', 'fadzthor@gmail.cpm', '085768993446', '', 'L', 6, 'P0001', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `matakuliah`
--

CREATE TABLE `matakuliah` (
  `Kd_Mtk` varchar(5) NOT NULL,
  `Nama_Mtk` varchar(100) NOT NULL,
  `Jml_Sks` int(11) NOT NULL,
  `Status` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `matakuliah`
--

INSERT INTO `matakuliah` (`Kd_Mtk`, `Nama_Mtk`, `Jml_Sks`, `Status`) VALUES
('Mt001', 'Pemograman Mobile 2', 3, 'A'),
('Mt002', 'Basis Data', 3, 'A'),
('Mt003', 'Pemograman Web', 3, 'A'),
('Mt004', 'Object-Oriented Programming', 3, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `prodi`
--

CREATE TABLE `prodi` (
  `Kd_Prodi` varchar(5) NOT NULL,
  `Kd_Fakultas` varchar(5) NOT NULL,
  `Nama_Prodi` varchar(100) NOT NULL,
  `Jenjang` varchar(50) NOT NULL,
  `Status` char(1) NOT NULL,
  `UKT` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prodi`
--

INSERT INTO `prodi` (`Kd_Prodi`, `Kd_Fakultas`, `Nama_Prodi`, `Jenjang`, `Status`, `UKT`) VALUES
('P0001', 'FK001', 'Teknik Informatika', 'S1', 'A', 'Rp 3.100.000,-');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`) VALUES
(1, '190201003', '81dc9bdb52d04dc20036dbd8313ed055', 'fadzthor@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`Kd_Dosen`);

--
-- Indexes for table `fakultas`
--
ALTER TABLE `fakultas`
  ADD PRIMARY KEY (`Kd_Fakultas`);

--
-- Indexes for table `jadwal`
--
ALTER TABLE `jadwal`
  ADD PRIMARY KEY (`Kd_Jadwal`);

--
-- Indexes for table `krs`
--
ALTER TABLE `krs`
  ADD PRIMARY KEY (`Kode_Krs`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`Npm`);

--
-- Indexes for table `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD PRIMARY KEY (`Kd_Mtk`);

--
-- Indexes for table `prodi`
--
ALTER TABLE `prodi`
  ADD PRIMARY KEY (`Kd_Prodi`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
