/*
 * Copyright 2020 S-cubism inc. All rights reserved.
 */

package com.example.springboot.enemy;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("enemies")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
@With
public class Enemy
{
    @Id
    private String id;

    private String name;
}
