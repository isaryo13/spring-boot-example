package com.example.springboot.hero;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends PagingAndSortingRepository<Hero, Long>
{
}
