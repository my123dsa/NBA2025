package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Team;
import dto.RankDTO;
import lombok.Getter;
import util.ConnectionFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Getter
public class TeamRepository {
    private final List<Team> teamData;
    private static final String dbFilePath = "teams.db"; // 파일 경로
    private final DataSource dataSource;

    public TeamRepository(boolean result, List<Team> teamData, ConnectionFactory factory) {
        this.teamData = teamData;
        if (!result) saveToFile();
        this.dataSource = factory.getDataSource();
    }

    public Team findTeamByName(String teamName) {
        return teamData.stream()
                .filter(t -> t.getName().equals(teamName))
                .findFirst()
                .orElse(null);
    }

    public List<RankDTO> findAllWithRankAndName() {
        return teamData.stream()
                .sorted((t1, t2) -> Integer.compare(t2.getWins(), t1.getWins()))
                .map(t -> RankDTO.builder().name(t.getName()).wins(t.getWins()).build())
                .toList();
    }

    public Team findById(Integer id) {
        return teamData.stream()
                .filter(t->t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void saveToFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(dbFilePath), teamData);
            System.out.println("✅ 팀 목록 JSON으로 저장 완료: " + dbFilePath);
//            oos.writeObject(this.teamData);
//            System.out.println("✅ 팀 목록 저장 완료: " + dbFilePath);
        } catch (IOException e) {
            System.out.println("❌ 저장 실패");
            e.printStackTrace();
        }
    }
}

