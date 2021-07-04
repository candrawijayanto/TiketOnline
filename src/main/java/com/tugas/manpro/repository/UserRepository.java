package com.tugas.manpro.repository;

import com.tugas.manpro.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

   public User findByIdUser(int idUser);

   // method untuk cari user berdasarkan email
   // jika ada maka akan return objek user (if !=NULL maka email sdh dipakai)
   // jika tidak ada maka akan return NULL (if NULL maka email blm dipakai)
   public User findByEmail(String email);

   // method untuk hitung record yg memiliki email dan idUser tertentu
   // jika 0 (false) maka email blm pernah dipakai
   // jika 1 (true) maka email sudah pernah dipakai, silahkan gunakan email lain
   @Query("SELECT COUNT(*) FROM User WHERE email=?1 AND idUser <> ?2")
   public int cekUpdateEmail(String email, int idUser);

}
