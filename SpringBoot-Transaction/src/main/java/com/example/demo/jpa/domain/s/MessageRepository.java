package com.example.demo.jpa.domain.s;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据访问接口
 */
public interface MessageRepository extends JpaRepository<Message, Long> {


}
