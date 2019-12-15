package com.pluarsight.conferencedemo.repositories;

import com.pluarsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
}
