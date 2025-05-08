package service;

import controller.MainController;
import domain.Player;
import domain.Team;
import repository.PlayerRepository;
import repository.TeamRepository;

import java.util.List;

public class ManagementService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public ManagementService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public boolean trade(Team otherTeam, Team myTeam, Integer otherPId, Integer myPId) {
        Player otherPlayer = playerRepository.findById(otherPId);
        Player myPlayer = playerRepository.findById(myPId);
        if (otherPlayer == null || myPlayer == null)
            return false;
        long charge = myPlayer.getSalary() - otherPlayer.getSalary();
        if (myTeam.getMoney() + charge >= 0) {
            switchTeam(otherTeam, otherPlayer, myTeam, myPlayer, charge);
            teamRepository.saveToFile();
            return true;
        }
        return false;
    }


    private void switchTeam(Team otherTeam, Player otherPlayer, Team myTeam, Player myPlayer, long charge) {
        myTeam.setMoney(myTeam.getMoney() + charge);
        otherTeam.setMoney(otherTeam.getMoney() - charge);

        myTeam.getPlayers().remove(myPlayer);
        otherTeam.getPlayers().remove(otherPlayer);

        otherTeam.getPlayers().add(myPlayer);
        myTeam.getPlayers().add(otherPlayer);
    }

    public boolean buyPlayer(List<Player> faPlayerList, Integer id) {
        Player player =faPlayerList.stream()
                .filter(p->p.getId().equals(id))
                .findFirst().orElse(null);
        if (player == null)
            return false;
        Team team= teamRepository.findById(MainController.getOner().getTeamId());
        if(team.getMoney() < player.getSalary()){
            return false;
        }
        team.setMoney(team.getMoney() - player.getSalary());
        player.setIsFA(false);
        playerRepository.save(player);
        team.getPlayers().add(player);
        teamRepository.saveToFile();
        return true;
    }

    public String sellPlayer() {

        return "management";
    }

    public String changeHead() {

        return "management";
    }
}
