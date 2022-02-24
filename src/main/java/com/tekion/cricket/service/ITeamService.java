package com.tekion.cricket.service;

import com.tekion.cricket.bean.TeamDetails;

public interface ITeamService{
    TeamDetails initialiseTeam();

    TeamDetails initialiseTeamByFile();


}
