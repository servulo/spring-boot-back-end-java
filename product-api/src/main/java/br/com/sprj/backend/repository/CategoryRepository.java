package br.com.sprj.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sprj.backend.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}