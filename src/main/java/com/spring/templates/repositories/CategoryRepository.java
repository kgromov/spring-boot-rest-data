package com.spring.templates.repositories;

import com.spring.templates.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by jt on 6/13/17.
 */
// search is working out of the box by method name and parameters:
// e.g. categories/search/findByDescription?description=American
// also page, size, sort are out of the box for PagingAndSortingRepository inherited repositories
// sort works in the following way: ?sort=<fieldName1>,ASC|DESC[&sort=<fieldName1>,ASC|DESC]
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByDescription(@Param("description") String description);
}
