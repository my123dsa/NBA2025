package service;

import controller.MainController;
import domain.Player;
import domain.Stats;
import domain.Team;
import repository.PlayerRepository;
import repository.TeamRepository;

import java.util.List;

public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public List<Player> findAllPlayerByTeam(Team team) {
        List<Player> playerList = team.getPlayers();
        playerRepository.savePlayersToFile();
        return playerList;
    }

    public Stats getStats(String playerName) {

        Player player = playerRepository.findByName(playerName);
        if (player != null) {
            return player.getStats();
        }
        return null;
    }

    public List<Player> FAPlayerList(){
        return playerRepository.findAllByIsFA();
    }

    public void createPlayer(String[]data,String[] statsList ) {
        //사실 문자열이 아닌 dto로 빼줬어야함
        String name = data[0].trim();
        int age = Integer.parseInt(data[1].trim());
        Long salary = Long.parseLong(data[2].trim());
        int height = Integer.parseInt(data[3].trim());
        int wingspan = Integer.parseInt(data[4].trim());
        String position = data[5].trim();
        Player newPlayer = Player.builder()
                .name(name)
                .age(age)
                .height(height)
                .wingSpan(wingspan)
                .position(position)
                .salary(salary)
                .build();

        Integer shoot = Integer.parseInt(statsList[0].trim());
        Integer pass = Integer.parseInt(statsList[1].trim());
        Integer dribble = Integer.parseInt(statsList[2].trim());
        Integer rebound = Integer.parseInt(statsList[3].trim());
        Integer block = Integer.parseInt(statsList[4].trim());
        Integer steal = Integer.parseInt(statsList[5].trim());
        Stats stats = Stats.builder()
                .block(block)
                .steal(steal)
                .dribble(dribble)
                .rebound(rebound)
                .pass(pass)
                .shoot(shoot)
                .build();
        newPlayer.setStats(stats);
        playerRepository.save(newPlayer);
    }

    public String retirePlayer(String[] TP) {

        String teamName = TP[0].trim();
        String playerName = TP[1].trim();

        Player player = playerRepository.findByName(playerName);
        if(player != null) {
            String name = player.getName();
            if (!teamName.equals("없음")){
                playerRepository.remove(player);
                return name;
            }
            else  {
                Team team = teamRepository.findTeamByName(teamName);
                if(team !=null && team.getPlayers().size()>=6){
                    team.getPlayers().remove(player);
                    playerRepository.remove(player);
                    teamRepository.saveToFile();
                    return name;
                }
            }
        }
        return null;
    }
}
