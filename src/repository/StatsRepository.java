package repository;

import domain.Stats;
import lombok.Getter;
import util.ConnectionFactory;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@Getter
public class StatsRepository {
    private final List<Stats> statsData;
    private static final String dbFilePath = "stats.db"; // 파일 경로
    private final DataSource dataSource;
    public StatsRepository(boolean result,List<Stats> statsData, ConnectionFactory factory) {
        this.statsData = statsData;
        if(!result) saveStatsToFile();
        this.dataSource = factory.getDataSource();
    }

    public void saveStatsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFilePath))) {
            oos.writeObject(this.statsData);
            System.out.println("✅ 팀 목록 저장 완료: " + dbFilePath);
        } catch (IOException e) {
            System.out.println("❌ 저장 실패");
            e.printStackTrace();
        }
    }
}
