package br.com.sprj.backend.repository;

import org.springframework.stereotype.Repository;

import br.com.sprj.backend.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findBySocialSecurity(String socialSecurity);

	List<User> queryByNameLike(String name);

}