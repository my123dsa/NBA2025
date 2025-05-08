package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuarterLog {
    private  int quarter;
    private  String team1Name;
    private  int team1Score;
    private  String team2Name;
    private  int team2Score;

    public static QuarterLog of(int j, String team1Name,int team1Score,String team2Name,int team2Score) {
        return QuarterLog.builder()
                .quarter(j)
                .team1Name(team1Name)
                .team1Score(team1Score)
                .team2Name(team2Name)
                .team2Score(team2Score)
                .build();
    }
}
