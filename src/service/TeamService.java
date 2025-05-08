package service;

import domain.Team;
import repository.TeamRepository;

public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findTeamById(int teamId) {
        return teamRepository.findById(teamId);
    }
}
