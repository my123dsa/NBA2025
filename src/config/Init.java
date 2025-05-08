package config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.HeadCoach;
import domain.Player;
import domain.Stats;
import domain.Team;
import domain.oner.Oner;
import lombok.Getter;
import lombok.Setter;
import util.ScanUtil;
import util.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Init {
    private Oner oner;
    private List<Team> teams;
    private List<Player> players;
    private List<Stats> stats;
    private List<HeadCoach> headCoaches;


    public Init() {
        teams = new ArrayList<>();
        players = new ArrayList<>();
        stats = new ArrayList<>();
        headCoaches = new ArrayList<>();
    }

    public Oner createOner() {
        while (true) {
            View.printInit();
            int next = ScanUtil.readInt();
            System.out.println(next + "");
            switch (next) {
                case 1:
                    return Oner.builder()
                            .teamId(next)
                            .teamName("Los Angeles Lakers")
                            .build();
                case 2:
                    return Oner.builder()
                            .teamId(next)
                            .teamName("Golden State Warriors")
                            .build();
                case 3:
                    return Oner.builder()
                            .teamId(next)
                            .teamName("Boston Celtics")
                            .build();
                case 4:
                    return Oner.builder()
                            .teamId(next)
                            .teamName("Oklahoma City Thunder")
                            .build();
                default:
                    View.printValidNum();
            }
        }
    }

    public void init() {
        // Create sample entities
        Team lakers = Team.builder()
                .name("Los Angeles Lakers")
                .money(150_000_000L)
                .wins(0)
                .totalGames(0)
                .headCoach(new HeadCoach("Darvin Ham", 50, 5_000_000L, 1, 2_000_000))
                .players(new ArrayList<>(List.of(
                        Player.builder()
                                .name("Luka Doncic")
                                .age(26)
                                .salary(45_000_000L)
                                .height(201)
                                .wingSpan(210)
                                .position("PG")
                                .stats(Stats.builder()
                                        .shoot(95)
                                        .pass(98)
                                        .dribble(96)
                                        .rebound(80)
                                        .block(50)
                                        .steal(70)
                                        .point3(89) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Austin Reaves")
                                .age(27)
                                .salary(12_000_000L)
                                .height(196)
                                .wingSpan(203)
                                .position("SG")
                                .stats(Stats.builder()
                                        .shoot(85)
                                        .pass(80)
                                        .dribble(82)
                                        .rebound(70)
                                        .block(40)
                                        .steal(60)
                                        .point3(80) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("LeBron James")
                                .age(40)
                                .salary(50_000_000L)
                                .height(206)
                                .wingSpan(215)
                                .position("SF")
                                .stats(Stats.builder()
                                        .shoot(90)
                                        .pass(95)
                                        .dribble(88)
                                        .rebound(85)
                                        .block(60)
                                        .steal(65)
                                        .point3(78) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Dorian Finney-Smith")
                                .age(32)
                                .salary(13_000_000L)
                                .height(201)
                                .wingSpan(215)
                                .position("PF")
                                .stats(Stats.builder()
                                        .shoot(75)
                                        .pass(70)
                                        .dribble(72)
                                        .rebound(80)
                                        .block(55)
                                        .steal(50)
                                        .point3(73) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Jaxson Hayes")
                                .age(25)
                                .salary(8_000_000L)
                                .height(211)
                                .wingSpan(220)
                                .position("C")
                                .stats(Stats.builder()
                                        .shoot(65)
                                        .pass(60)
                                        .dribble(58)
                                        .rebound(90)
                                        .block(85)
                                        .steal(45)
                                        .point3(55) // 3-point shooting ability
                                        .build())
                                .build()
                )))
//                .games(new ArrayList<>())
                .build();

        Team warriors = Team.builder()
                .name("Golden State Warriors")
                .money(140_000_000L)
                .wins(0)
                .totalGames(0)
                .headCoach(new HeadCoach("Steve Kerr", 59, 6_000_000L, 2, 1_500_000))
                .players(new ArrayList<>(List.of(
                        Player.builder()
                                .name("Stephen Curry")
                                .age(37)
                                .salary(55_000_000L)
                                .height(188)
                                .wingSpan(193)
                                .position("PG")
                                .stats(Stats.builder()
                                        .shoot(99)
                                        .pass(92)
                                        .dribble(95)
                                        .rebound(60)
                                        .block(30)
                                        .steal(80)
                                        .point3(99) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Brandin Podziemski")
                                .age(22)
                                .salary(3_000_000L)
                                .height(196)
                                .wingSpan(202)
                                .position("SG")
                                .stats(Stats.builder()
                                        .shoot(78)
                                        .pass(75)
                                        .dribble(77)
                                        .rebound(65)
                                        .block(35)
                                        .steal(55)
                                        .point3(80) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Jimmy Butler III")
                                .age(35)
                                .salary(37_000_000L)
                                .height(201)
                                .wingSpan(210)
                                .position("SF")
                                .stats(Stats.builder()
                                        .shoot(85)
                                        .pass(80)
                                        .dribble(83)
                                        .rebound(70)
                                        .block(50)
                                        .steal(75)
                                        .point3(72) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Moses Moody")
                                .age(23)
                                .salary(4_000_000L)
                                .height(198)
                                .wingSpan(210)
                                .position("PF")
                                .stats(Stats.builder()
                                        .shoot(75)
                                        .pass(70)
                                        .dribble(72)
                                        .rebound(75)
                                        .block(55)
                                        .steal(60)
                                        .point3(77) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Draymond Green")
                                .age(35)
                                .salary(25_000_000L)
                                .height(198)
                                .wingSpan(210)
                                .position("C")
                                .stats(Stats.builder()
                                        .shoot(70)
                                        .pass(85)
                                        .dribble(78)
                                        .rebound(85)
                                        .block(70)
                                        .steal(80)
                                        .point3(65) // 3-point shooting ability
                                        .build())
                                .build()
                )))
//                .games(new ArrayList<>())
                .build();

        Team celtics = Team.builder()
                .name("Boston Celtics")
                .money(160000000L)
                .wins(0)
                .totalGames(0)
                .headCoach(new HeadCoach("Joe Mazzulla", 36, 4000000L, 3, 1500000))
                .players(new ArrayList<>(List.of(
                        Player.builder()
                                .name("Jrue Holiday")
                                .age(35)
                                .salary(37000000L)
                                .height(193)
                                .wingSpan(200)
                                .position("PG")
                                .stats(Stats.builder()
                                        .shoot(85)
                                        .pass(87)
                                        .dribble(84)
                                        .rebound(65)
                                        .block(50)
                                        .steal(85)
                                        .point3(79) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Derrick White")
                                .age(31)
                                .salary(18000000L)
                                .height(193)
                                .wingSpan(201)
                                .position("SG")
                                .stats(Stats.builder()
                                        .shoot(83)
                                        .pass(82)
                                        .dribble(81)
                                        .rebound(60)
                                        .block(45)
                                        .steal(75)
                                        .point3(80) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Jaylen Brown")
                                .age(28)
                                .salary(52000000L)
                                .height(198)
                                .wingSpan(210)
                                .position("SF")
                                .stats(Stats.builder()
                                        .shoot(90)
                                        .pass(78)
                                        .dribble(85)
                                        .rebound(75)
                                        .block(60)
                                        .steal(70)
                                        .point3(81) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Jayson Tatum")
                                .age(27)
                                .salary(33000000L)
                                .height(203)
                                .wingSpan(213)
                                .position("PF")
                                .stats(Stats.builder()
                                        .shoot(93)
                                        .pass(80)
                                        .dribble(86)
                                        .rebound(80)
                                        .block(55)
                                        .steal(65)
                                        .point3(85) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Kristaps Porzingis")
                                .age(30)
                                .salary(29000000L)
                                .height(221)
                                .wingSpan(229)
                                .position("C")
                                .stats(Stats.builder()
                                        .shoot(85)
                                        .pass(70)
                                        .dribble(72)
                                        .rebound(88)
                                        .block(90)
                                        .steal(50)
                                        .point3(80) // 3-point shooting ability
                                        .build())
                                .build()
                )))
//                .games(new ArrayList<>())
                .build();

        Team thunder = Team.builder()
                .name("Oklahoma City Thunder")
                .money(130000000L)
                .wins(0)
                .totalGames(0)
                .headCoach(new HeadCoach("Mark Daigneault", 39, 3500000L, 4, 1800000))
                .players(new ArrayList<>(List.of(
                        Player.builder()
                                .name("Shai Gilgeous-Alexander")
                                .age(26)
                                .salary(33000000L)
                                .height(198)
                                .wingSpan(206)
                                .position("PG")
                                .stats(Stats.builder()
                                        .shoot(94)
                                        .pass(88)
                                        .dribble(92)
                                        .rebound(70)
                                        .block(45)
                                        .steal(85)
                                        .point3(85) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Josh Giddey")
                                .age(23)
                                .salary(6000000L)
                                .height(203)
                                .wingSpan(210)
                                .position("SG")
                                .stats(Stats.builder()
                                        .shoot(78)
                                        .pass(85)
                                        .dribble(80)
                                        .rebound(78)
                                        .block(40)
                                        .steal(60)
                                        .point3(74) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Luguentz Dort")
                                .age(26)
                                .salary(17000000L)
                                .height(193)
                                .wingSpan(206)
                                .position("SF")
                                .stats(Stats.builder()
                                        .shoot(75)
                                        .pass(68)
                                        .dribble(70)
                                        .rebound(65)
                                        .block(55)
                                        .steal(75)
                                        .point3(60) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Jalen Williams")
                                .age(24)
                                .salary(4000000L)
                                .height(198)
                                .wingSpan(210)
                                .position("PF")
                                .stats(Stats.builder()
                                        .shoot(82)
                                        .pass(80)
                                        .dribble(82)
                                        .rebound(72)
                                        .block(50)
                                        .steal(65)
                                        .point3(70) // 3-point shooting ability
                                        .build())
                                .build(),
                        Player.builder()
                                .name("Chet Holmgren")
                                .age(23)
                                .salary(11000000L)
                                .height(216)
                                .wingSpan(229)
                                .position("C")
                                .stats(Stats.builder()
                                        .shoot(80)
                                        .pass(72)
                                        .dribble(75)
                                        .rebound(85)
                                        .block(90)
                                        .steal(60)
                                        .point3(75) // 3-point shooting ability
                                        .build())
                                .build()
                )))
//                .games(new ArrayList<>())
                .build();

        // Free agent players
        Player faPlayer1 = Player.builder()
                .name("Spencer Dinwiddie")
                .age(31)
                .salary(8000000L)
                .height(196)
                .wingSpan(201)
                .position("PG")
                .stats(Stats.builder()
                        .shoot(78)
                        .pass(80)
                        .dribble(82)
                        .rebound(60)
                        .block(40)
                        .steal(65)
                        .point3(75) // 3-point shooting ability
                        .build())
                .isFA(true)
                .build();

        Player faPlayer2 = Player.builder()
                .name("Danilo Gallinari")
                .age(36)
                .salary(5000000L)
                .height(208)
                .wingSpan(215)
                .position("PF")
                .stats(Stats.builder()
                        .shoot(80).pass(72).dribble(68)
                        .rebound(70).block(50).steal(45).point3(60)
                        .build())
                .isFA(true)
                .build();
        List<Team> teams = List.of(lakers,warriors,celtics,thunder);

        List<Player> players = new ArrayList<>();
        List<HeadCoach> headCoaches = new ArrayList<>();
        List<Stats> stats = new ArrayList<>();
        for (Team team : teams) {
            players.addAll(team.getPlayers());
            headCoaches.add(team.getHeadCoach());
            for( Player player : team.getPlayers()) {
                stats.add(player.getStats());
            }
        }
        players.add(faPlayer1);
        players.add(faPlayer2);
        stats.add(faPlayer1.getStats());
        stats.add(faPlayer2.getStats());
        this.teams= teams;
        this.players = players;
        this.headCoaches = headCoaches;
        this.stats = stats;
    }

    public void initByDB(){
        this.teams = loadTeams("teams.db");
        this.players = loadPlayer("players.db");
        this.stats = loadStats("stats.db");
        this.headCoaches = loadHeadCoach("headCoaches.db");
    }

    public List<Team> loadTeams(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try  {
            List<Team> teams = mapper.readValue(
                    new File(filePath)
                    , new TypeReference<List<Team>>() {}
            );
            System.out.println("✅ 팀 목록 불러오기 완료");
            return teams;
        } catch (IOException e) {
            System.out.println("❌ 불러오기 실패");
            return null;
        }
    }

    public List<Player> loadPlayer(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Player> players = (List<Player>) ois.readObject();
            System.out.println("✅ 팀 목록 불러오기 완료");
            return players;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ 불러오기 실패");
            return null;
        }
    }

    public List<Stats> loadStats(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Stats> statsList = (List<Stats>) ois.readObject();
            System.out.println("✅ 팀 목록 불러오기 완료");
            return statsList;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ 불러오기 실패");
            return null;
        }
    }

    public List<HeadCoach> loadHeadCoach(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<HeadCoach> headCoaches = (List<HeadCoach>) ois.readObject();
            System.out.println("✅ 팀 목록 불러오기 완료");
            return headCoaches;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ 불러오기 실패");
            return null;
        }
    }
    public boolean check(){
        if(this.getTeams() ==null|| this.getPlayers() ==null
        || this.getHeadCoaches() ==null|| this.getStats() ==null){
            return false;
        }
        return true;
    }
}