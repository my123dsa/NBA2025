package service;

import controller.MainController;
import domain.Game;
import domain.Player;
import domain.Stats;
import domain.Team;
import dto.QuarterLog;
import dto.RankDTO;
import repository.GameRepository;
import repository.TeamRepository;
import util.View;

import java.util.*;

public class GameService {
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;

    public GameService(TeamRepository teamRepository, GameRepository gameRepository) {
        this.teamRepository = teamRepository;
        this.gameRepository = gameRepository;
    }

    public List<RankDTO> getRank() {
        return teamRepository.findAllWithRankAndName();
    }

    public List<Game> getGameList() {
        return gameRepository.findByTeamId(MainController.getOner().getTeamId());
    }

    public List<QuarterLog> doGame() {
        Set<Integer> set=new HashSet<>(List.of(1,2,3,4));

        Integer id= MainController.getOner().getTeamId();
        Integer vs;
        do{
            vs = (int) (Math.random() * 4) +1;
        }while (id==vs);
        List<QuarterLog> logs = new ArrayList<>();
        Game g1= simulate(id,vs,true,logs);
        set.remove(vs);
        set.remove(id);
        Game g2 = otherTeamDoGame(set,logs);
        List<Game> gameList = List.of(g1,g2);
        gameRepository.saveAll(gameList);
        teamRepository.saveToFile();
        return logs;
    }

    private Game otherTeamDoGame(Set<Integer> set,List<QuarterLog> logs) {
        Iterator<Integer> it= set.iterator();
        return simulate(it.next(),it.next(),false,logs);
    }

    // 총200
    // Math.random()<=(shoot-(dribble+pass -block-steal)/2)/100 +2
    // 3점 스탯/2  > Math.random() +3 or +2
    //
    private Game simulate(Integer id1, Integer id2,boolean print,List<QuarterLog> logs) {
        Team team1= teamRepository.findById(id1);
        Team team2 = teamRepository.findById(id2);
        //이 부분에서  playerlist id로 가져와야함

        int team1Score =0;
        int team2Score =0;
        boolean flag = false;
        for(int j=1;j<=4;j++){
            for(int i=0;i<50;i++) {
                Player p1 = selectRandomPlayer(team1);
                Player p2 = selectRandomPlayer(team2);
                if (flag) {
                    team1Score += point(p1, p2);
                } else {
                    team2Score += point(p2, p1);
                }
                flag = !flag;
            }
            //todo view 뺴기
            if(print)
                logs.add(QuarterLog.of(j, team1.getName(), team1Score, team2.getName(), team2Score));
//                View.printQuarterScore(j,team1.getName() ,team1Score, team2.getName(),team2Score);
        }

        int result = determineWinner(team1Score, team2Score);

        updateTeamStats(team1, team2, result);

        return Game.builder()
                .team1(team1)
                .team2(team2)
                .scores(new Integer[]{team1Score,team2Score})
                .result(result)
                .build();
    }

    private int determineWinner(int team1Score, int team2Score) {
        if (team1Score > team2Score) {
            return 1; // 팀 1 승
        } else if (team2Score > team1Score) {
            return -1; // 팀 2 승
        } else {
            return 0; // 무승부
        }
    }

    private void updateTeamStats(Team team1, Team team2, int result) {
        if (result == 1) {
            team1.setWins(team1.getWins() + 2);
        } else if (result == -1) {
            team2.setWins(team2.getWins() + 2);
        } else {
            team1.setWins(team1.getWins() + 1);
            team2.setWins(team2.getWins() + 1);
        }
        team1.setTotalGames(team1.getTotalGames() + 1);
        team2.setTotalGames(team2.getTotalGames() + 1);
    }

    private Player selectRandomPlayer(Team team) {
        int idx = (int) (Math.random() * team.getPlayers().size());
        return team.getPlayers().get(idx);
    }

    private int point(Player attacker, Player defender) {
        Stats atkStats = attacker.getStats();
        Stats defStats = defender.getStats();

        int offenseScore = atkStats.getDribble() + atkStats.getPass() + atkStats.getRebound();
        int defenseScore = defStats.getBlock() + defStats.getSteal() + defStats.getRebound();

        int successRate = (atkStats.getShoot() / 2) + (offenseScore - defenseScore) / 5;

        if (Math.random() <= (double) successRate / 100) {
            if (Math.random() <= (double) atkStats.getPoint3() / 2) {
                return 3;
            }
            return 2;
        }
        return 0;
    }
}
