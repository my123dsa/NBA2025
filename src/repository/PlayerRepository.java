package repository;

import domain.Player;
import lombok.Getter;
import util.ConnectionFactory;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@Getter
public class PlayerRepository {
    private final List<Player> playerData;
    private static final String dbFilePath = "players.db"; // 파일 경로
    private final DataSource dataSource;

    public PlayerRepository(boolean result,List<Player> playerData, ConnectionFactory factory) {
        this.playerData = playerData;
        if(!result) savePlayersToFile();
        this.dataSource = factory.getDataSource();
    }

    public void save(Player newPlayer) {
        playerData.add(newPlayer);
        savePlayersToFile();
    }

    public Player findByName(String playerName) {
        return playerData.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElse(null);
    }

    public void remove(Player player) {
        playerData.remove(player);
        savePlayersToFile();
    }

    public Player findById(Integer id) {
        return playerData.stream()
                .filter(player -> player.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Player> findAllByIsFA() {
        return playerData.stream().filter(p->p.getIsFA()).toList();
    }

    public void savePlayersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFilePath))) {
            oos.writeObject(this.playerData);
            System.out.println("✅ 팀 목록 저장 완료: " + dbFilePath);
        } catch (IOException e) {
            System.out.println("❌ 저장 실패");
            e.printStackTrace();
        }
    }
}
