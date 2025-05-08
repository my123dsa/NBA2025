package repository;


import domain.HeadCoach;
import lombok.Getter;
import util.ConnectionFactory;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@Getter
public class HeadCoachRepository {
    private final List<HeadCoach> headCoachData;
    private static final String dbFilePath = "headCoaches.db"; // 파일 경로
    private final DataSource dataSource;

    public HeadCoachRepository(boolean result,List<HeadCoach> headCoachData, ConnectionFactory factory) {
        this.headCoachData = headCoachData;
        if (!result)
            saveStatsToFile();
        this.dataSource = factory.getDataSource();
    }

    public void saveStatsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFilePath))) {
            oos.writeObject(this.headCoachData);
            System.out.println("✅ 팀 목록 저장 완료: " + dbFilePath);
        } catch (IOException e) {
            System.out.println("❌ 저장 실패");
            e.printStackTrace();
        }
    }
}
