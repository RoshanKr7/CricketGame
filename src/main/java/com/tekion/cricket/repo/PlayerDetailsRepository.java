package com.tekion.cricket.repo;

import com.tekion.cricket.bean.PlayerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerDetailsRepository extends JpaRepository<PlayerDetails, Integer> {

}
