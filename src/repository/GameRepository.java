package repository;

import controller.MainController;
import domain.Game;
import lombok.Getter;
import util.ConnectionFactory;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

@Getter
public class GameRepository {
    private final List<Game> gameData;
    private final String dbFilePath = "games.db"; // DB 파일 경로
    private final DataSource dataSource;

    public GameRepository(List<Game> gameData, ConnectionFactory factory) {
        this.gameData = gameData;
        this.dataSource= factory.getDataSource();
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection= dataSource.getConnection();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
//    public <T> T withConnection(Function<Connection, T> logic) {
//        try (Connection conn = dataSource.getConnection()) {
//            return logic.apply(conn);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public List<Game> findByTeamId(Integer teamId) {
//        return withConnection(conn ->
//                gameData.stream()
//                        .filter(g -> g.getTeam1().getId().equals(teamId)
//                                || g.getTeam2().getId().equals(teamId))
//                        .toList()
//        );
//    }

    public List<Game> findByTeamId(Integer teamId) {
        return gameData.stream()
                .filter(g->g.getTeam1().getId().equals(teamId)
                        || g.getTeam2().getId().equals(teamId) )
                .toList();
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dbFilePath))) {
            for (Game game : gameData) {
                // 게임 데이터를 CSV 형식으로 파일에 저장 (게임 ID, 팀1 이름, 팀2 이름, 팀1 점수, 팀2 점수)
                writer.write(game.getId() + "," +
                        game.getTeam1().getName() + "," +
                        game.getTeam2().getName() + "," +
                        game.getScores()[0] + "," +
                        game.getScores()[1] + "," +
                        game.getResult());
                writer.newLine();
            }
            System.out.println("게임 데이터가 " + dbFilePath + "에 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("게임 데이터를 저장하는 중 오류가 발생했습니다.");
        }
    }

    public void saveAll(List<Game> gameList) {
        gameData.addAll(gameList);
        saveToFile();
    }
}
