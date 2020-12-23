package com.oded.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oded.entity.User;


@Repository
public interface UserRepo  extends JpaRepository<User,Integer>{
	
	@Query("select u from User u where u.username=:username")
	User findByUsername(@Param("username")String username);
////	@Modifying
////	@Query(name="update u from User u where u.password=:password and u.username=:username",nativeQuery = true)
////	User updatePassword(@Param("password")String password,@Param("username")String username);
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("update User u set u.password =:password where u.username =:username")
//	void updatethePassword(@Param("password") String password, @Param("username") String username);
//	


}
