## IMPLEMENTASI CRUD DENGAN UPLOAD DAN DOWNLOAD CSV BERBASIS PBO
Pada praktikum ke 14  ini dilakukan implementasi CRUD dengan upload dan download CSV berbasis OOP menggunakan Java. Data direpresentasikan sebagai objek, sehingga operasi CRUD dapat dilakukan secara terstruktur, sedangkan upload CSV memasukkan data dari file eksternal ke objek, dan download CSV mengekspor data objek ke file dengan mudah dan efisien.

- Pada class Daftar tambahkan label dan textField untuk pertanyaan.

<img width="328" height="195" alt="image" src="https://github.com/user-attachments/assets/a7bc3875-87ee-4639-ac65-5a50b7c052fc" />

Kode ini berfungsi untuk proses pendaftaran akun baru. Sistem memeriksa apakah semua TextField termasuk TextField pertanyaan keamanan telah terisi. Pertanyaan keamanan (TfFav) digunakan sebagai data tambahan untuk verifikasi jika suatu saat pengguna perlu ubah password akun. Setelah validasi, sistem mengecek apakah username sudah ada di database. Jika belum, data username, password, dan pertanyaan keamanan disimpan, lalu pengguna dialihkan ke halaman login. Jika username sudah terdaftar, pendaftaran dibatalkan dan pengguna diminta mengganti username.

<img width="619" height="435" alt="image" src="https://github.com/user-attachments/assets/99b43217-2478-4ca5-b51a-4264c4cb7c2f" />

- Tambahkan Dialog baru untuk pertanyaannya.

<img width="413" height="205" alt="image" src="https://github.com/user-attachments/assets/99f1c610-46f3-4777-9802-c33c700e728e" />

Kode ini digunakan saat tombol Selesai diklik pada dialog input pertanyaan keamanan. Program memeriksa apakah TextField TfFav berisi teks atau tidak. Jika masih kosong, muncul pesan peringatan dan kursor diarahkan kembali ke TextField agar pengguna mengisinya. Jika sudah terisi, teks dari TfFav disimpan ke variabel jawaban, kemudian dialog ditutup dengan dispose() sehingga pengguna kembali ke tampilan utama.

<img width="725" height="186" alt="image" src="https://github.com/user-attachments/assets/1908922f-1c74-4821-b787-02838d5892b2" />

- Pada class “ResetPassword” kode ini berfungsi untuk mencari akun berdasarkan username lalu menampilkan dialog pertanyaan keamanan. Jika jawaban yang diberikan sesuai dengan data di database, maka pengguna diizinkan melanjutkan proses reset password. Jika tidak sesuai, proses dihentikan dan reset password tidak dapat dilakukan.

<img width="744" height="382" alt="image" src="https://github.com/user-attachments/assets/c2711b0a-ca03-4b31-b45b-a283f5f8262c" />

<img width="753" height="341" alt="image" src="https://github.com/user-attachments/assets/9ecf2058-fa34-4bfe-afa2-5e375eaeb12e" />

- Pada class utama tambahkan button download untuk tabel film. Kode ini digunakan untuk mengekspor data film dari tabel ke file CSV. Pengguna memilih lokasi penyimpanan menggunakan JFileChooser, lalu program menuliskan setiap data dalam tabel ke dalam file dengan pemisah titik koma. Jika file sudah ada, pengguna diminta konfirmasi untuk menimpa. Setelah berhasil, muncul pesan bahwa data berhasil disimpan. Jika gagal, ditampilkan pesan error.

<img width="755" height="552" alt="image" src="https://github.com/user-attachments/assets/b490febb-f499-43e7-97f9-fb4e73174aaf" />

<img width="755" height="531" alt="image" src="https://github.com/user-attachments/assets/361b08cd-0af4-471d-8307-571bbd80fc57" />

- Pada class “utama” tambahkan button Download untuk tabel jadwal tayang. Kode ini digunakan untuk mengekspor data jadwal tayang dari tabel ke file CSV. Pengguna memilih lokasi penyimpanan melalui JFileChooser, kemudian program menulis setiap baris tabel ke file dengan pemisah titik koma. Jika file sudah ada, akan muncul konfirmasi untuk menimpanya. Setelah proses berhasil, muncul pesan sukses, dan jika terjadi kesalahan akan ditampilkan pesan error.

<img width="751" height="547" alt="image" src="https://github.com/user-attachments/assets/a6e1b47c-9b89-4184-94be-276ebe588076" />

<img width="778" height="545" alt="image" src="https://github.com/user-attachments/assets/21cbe0a7-0d8f-447b-9c27-edc3116abdd4" />
