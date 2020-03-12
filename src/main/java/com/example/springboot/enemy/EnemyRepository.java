package com.example.springboot.enemy;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyRepository extends PagingAndSortingRepository<Enemy, String>
{
}
