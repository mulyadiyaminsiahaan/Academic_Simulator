# Academic Simulator
## Case
Butet baru saja bertemu dengan salah seorang dosen pemrograman yang memintanya untuk mengembangkan suatu program sederhana untuk mensimulasikan entitas-entitas di lingkup dunia akademik. Sebenarnya, sang dosen telah mengembangkan desain dasar dari simulator dan Butet hanya diminta untuk mengimplementasikannya. Sebagai langkah awal, simulator hanya akan menangani beberapa entitas yang definisinya sudah jelas, yakni mata kuliah (```course```), mata kuliah yang dibuka (```course open```), mahasiswa (```student```), dosen (```lecturer```) dan rencana studi (```enrollment```).

Berikut beberapa tantangan atau hal yang harus dipenuhi dari setiap entitas:
**Course**
Sebuah mata kuliah merupakan satu unit studi dalam suatu program akademik, yang ditandai dengan atribut seperti Kode Mata Kuliah, Nama Mata Kuliah, Kredit Mata Kuliah, dan Nilai Mata Kuliah. 

Fungsionalitas yang terkait dengan mata kuliah akan meliputi: 
1. Penambahan Mata Kuliah, yang memungkinkan penambahan mata kuliah baru ke dalam program;
2. Riwayat Mata Kuliah, yang melacak kemajuan dan hasil dari semua mata kuliah yang diambil; 
3. Cetak Mata Kuliah, yang memungkinkan pencetakan detail mata kuliah untuk keperluan pencatatan atau tinjauan.

**Course Open**
Pembukaan mata kuliah mengacu pada proses membuat mata kuliah tersedia untuk pendaftaran oleh mahasiswa, yang ditandai dengan atribut seperti Kode Mata Kuliah, Tahun Mata Kuliah, Semester Mata Kuliah, dan Dosen Awal Mata Kuliah. 

Fungsionalitas yang terkait dengan pembukaan mata kuliah ini adalah Pembukaan Mata Kuliah, yang memungkinkan proses ini dilakukan sehingga mahasiswa dapat mendaftar dan mengikuti mata kuliah tersebut

**Student**
Seorang mahasiswa mewakili individu yang terdaftar dalam mata kuliah akademik, dengan atribut seperti NIM (Nomor Induk Mahasiswa), Nama Mahasiswa, Tahun Mahasiswa, dan Program Studi Mahasiswa. 

Fungsionalitas yang terkait dengan mahasiswa meliputi: 
1. Penambahan Mahasiswa, yang memungkinkan pendaftaran mahasiswa baru;
2. Detail Mahasiswa, yang menampilkan informasi lengkap tentang mahasiswa; 
3. Transkrip Mahasiswa, yang mencakup catatan akademik mahasiswa;
4. Cetak Mahasiswa, yang memungkinkan pencetakan informasi mahasiswa untuk keperluan administrasi atau tinjauan.

**Lecturer**
Seorang dosen adalah anggota staf akademik yang bertanggung jawab untuk mengajar mata kuliah, dengan atribut seperti ID Dosen, Nama Dosen, Nama Inisial Dosen, Email Dosen, dan Program Studi Dosen.

Fungsionalitas yang terkait dengan dosen meliputi: 
1. Penambahan Dosen, yang memungkinkan penambahan dosen baru ke dalam sistem;
2. Cetak Dosen, yang memungkinkan pencetakan informasi dosen untuk keperluan administrasi atau referensi.

**Enrollment**
Enrollment mengacu pada proses mendaftarkan mahasiswa ke dalam mata kuliah, dengan atribut seperti Kode Mata Kuliah, NIM (Nomor Induk Mahasiswa), Tahun Pendaftaran, dan Semester Pendaftaran.

Fungsionalitas yang terkait dengan pendaftaran meliputi:
1. Penambahan Pendaftaran, yang memungkinkan pendaftaran mahasiswa baru ke dalam mata kuliah; 
2. Nilai Pendaftaran, yang mencatat nilai yang diperoleh mahasiswa dalam mata kuliah tersebut; 
3. Perbaikan Pendaftaran, yang memungkinkan mahasiswa untuk memperbaiki nilai melalui remedial;
4. Cetak Pendaftaran, yang memungkinkan pencetakan informasi pendaftaran untuk keperluan administrasi atau tinjauan.

Note: Pada sesi ini saya diminta untuk menyelesaikan persoalan di atas dengan database sebagai media penyimpanan.

## Solution
Sistem ini terdiri dari beberapa kelas utama, termasuk kelas utama Drive, kelas abstrak AbstractDatabase, dan kelas turunan ContactDatabase. Sistem ini dirancang untuk mengelola dan menghubungkan berbagai entitas dengan database.

**Drive1**
Kelas Drive adalah titik eksekusi utama atau program utama dari sistem ini. Kelas ini bertanggung jawab untuk meminta koneksi dengan basis data dan mengelola seluruh alur kerja program. Ketika program dijalankan, Drive akan menginisialisasi koneksi dengan basis data menggunakan kelas AbstractDatabase. Pada Class ini juga akan diimplementasikan perintah apa atau fungsionalitas apa yang ingin dijalankan. Dibuat dalam bentuk percangan if-else. Untuk menghentikan program saya menggunakan inputan "---".

**AbstractDatabase**
Kelas AbstractDatabase adalah kelas induk yang mendefinisikan kerangka dasar untuk menghubungkan dan berinteraksi dengan basis data. Kelas ini tidak diimplementasikan secara langsung tetapi akan diwarisi oleh kelas-kelas turunan. AbstractDatabase menyediakan metodologi dasar untuk koneksi basis data, termasuk penyimpanan dan pengambilan data dari basis data tersebut. Pada case ini juga dibuat sebuah table yang akan mengeksekusi nama-nama atribut di atas.

**ContactDatabase**
ContactDatabase adalah kelas turunan dari AbstractDatabase yang mengimplementasikan berbagai fungsionalitas khusus untuk setiap entitas dan atribut yang akan dikelola dalam sistem. Kelas ini mencakup metode untuk menyimpan, memperbarui, menghapus, dan mengambil data entitas dari basis data. Setiap entitas yang dikelola oleh sistem ini akan memiliki representasi dan fungsionalitasnya sendiri dalam ContactDatabase.

Berikut beberapa deskripsi dari fungsionalitas dari setiap entitas:
Berikut adalah fungsi-fungsi yang tersedia dalam sistem manajemen kampus:

1. **Tambah Mahasiswa (student-add)**: Menambahkan data mahasiswa ke dalam basis data kampus.
2. **Tambah Dosen (lecturer-add)**: Menambahkan data dosen ke dalam basis data kampus.
3. **Tambah Informasi Mata Kuliah (course-add)**: Menambahkan informasi mengenai mata kuliah ke dalam basis data kampus.
4. **Buka Mata Kuliah (course-open)**: Menandai atau memberi pemberitahuan jika sebuah mata kuliah telah dibuka.
5. **Riwayat Mata Kuliah (course-history)**: Memberikan informasi terperinci mengenai suatu mata kuliah.
6. **Tambah Pendaftaran (enrollment-add)**: Digunakan untuk menambahkan aktivitas seorang mahasiswa yang ingin mengambil sebuah mata kuliah.
7. **Nilai Pendaftaran (enrollment-grade)**: Digunakan untuk memasukkan nilai bagi seorang mahasiswa yang mengambil sebuah mata kuliah tertentu.
8. **Pemulihan Pendaftaran (enrollment-remedial)**: Digunakan untuk memasukkan nilai pemulihan bagi seorang mahasiswa yang mengulang sebuah mata kuliah, asalkan mereka belum mendapat nilai pemulihan sebelumnya.
9. **Detail Mahasiswa (student-details)**: Menampilkan informasi seorang mahasiswa.
10. **Transkrip Mahasiswa (student-transcript)**: Menampilkan informasi seorang mahasiswa beserta mata kuliah yang telah diambilnya.

Untuk memastikan program tepat dapat dijalankan test sebagai berikut:

**Input**:

```bash
lecturer-add#0130058501#Parmonangan Rotua Togatorop#PAT#mona.togatorop@del.ac.id#Information Systems
lecturer-add#0114129002#Iustisia Natalia Simbolon#IUS#iustisia.simbolon@del.ac.id#Informatics
lecturer-add#0124108201#Rosni Lumbantoruan#RSL#rosni@del.ac.id#Information Systems
course-add#12S1101#Dasar Sistem Informasi#3#D
course-add#12S2102#Basisdata#4#C
student-add#12S20001#Marcelino Manalu#2020#Information Systems
student-add#12S20002#Yoga Sihombing#2020#Information Systems
student-add#12S20003#Marcel Simanjuntak#2020#Information Systems
course-open#12S1101#2020/2021#odd#IUS
enrollment-add#12S1101#12S20001#2020/2021#odd
enrollment-add#12S1101#12S20002#2020/2021#odd
enrollment-add#12S1101#12S20003#2020/2021#odd
enrollment-grade#12S1101#12S20001#2020/2021#odd#B
enrollment-remedial#12S1101#12S20001#2020/2021#odd#B
enrollment-grade#12S1101#12S20002#2020/2021#odd#B
enrollment-grade#12S1101#12S20003#2020/2021#odd#B
student-details#12S20003
enrollment-remedial#12S1101#12S20003#2020/2021#odd#AB
course-open#12S2102#2021/2022#odd#PAT,IUS,RSL
enrollment-add#12S2102#12S20001#2021/2022#odd
enrollment-add#12S2102#12S20002#2021/2022#odd
enrollment-add#12S2102#12S20003#2021/2022#odd
enrollment-grade#12S2102#12S20001#2021/2022#odd#B
enrollment-grade#12S2102#12S20002#2021/2022#odd#AB
enrollment-grade#12S2102#12S20003#2021/2022#odd#BC
student-details#12S20001
course-open#12S2102#2022/2023#odd#IUS,RSL
enrollment-add#12S2102#12S20003#2022/2023#odd
enrollment-grade#12S2102#12S20003#2022/2023#odd#AB
enrollment-remedial#12S2102#12S20003#2022/2023#odd#B
student-details#12S20001
student-details#12S20002
student-transcript#12S20003
---

```

**Output**:

```bash
12S20003|Marcel Simanjuntak|2020|Information Systems|3.00|3
12S20001|Marcelino Manalu|2020|Information Systems|3.00|7
12S20001|Marcelino Manalu|2020|Information Systems|3.00|7
12S20002|Yoga Sihombing|2020|Information Systems|3.29|7
12S20003|Marcel Simanjuntak|2020|Information Systems|3.21|7
12S1101|12S20003|2020/2021|odd|AB(B)
12S2102|12S20003|2022/2023|odd|B(AB)
0130058501|Parmonangan Rotua Togatorop|PAT|mona.togatorop@del.ac.id|Information Systems
0114129002|Iustisia Natalia Simbolon|IUS|iustisia.simbolon@del.ac.id|Informatics
0124108201|Rosni Lumbantoruan|RSL|rosni@del.ac.id|Information Systems
12S1101|Dasar Sistem Informasi|3|D
12S2102|Basisdata|4|C
12S20001|Marcelino Manalu|2020|Information Systems
12S20002|Yoga Sihombing|2020|Information Systems
12S20003|Marcel Simanjuntak|2020|Information Systems
12S1101|12S20001|2020/2021|odd|B(B)
12S1101|12S20002|2020/2021|odd|B
12S1101|12S20003|2020/2021|odd|AB(B)
12S2102|12S20001|2021/2022|odd|B
12S2102|12S20002|2021/2022|odd|AB
12S2102|12S20003|2021/2022|odd|BC
12S2102|12S20003|2022/2023|odd|B(AB)

```

## How to submit?
Please see https://youtu.be/ZOhgmVjWFyo
