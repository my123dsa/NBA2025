package util;

import domain.Game;
import domain.Player;
import domain.Stats;
import dto.QuarterLog;
import dto.RankDTO;

import java.util.List;

public class View {
    private static StringBuilder sb =new StringBuilder();

    public static void printInit(){

        String sb = "===========================\n" +
                "NBA 게임에 오신 걸 환영합니다\n" +
                "===========================\n" +
                "팀을 선택하세요\n" +
                "1. Los Angeles Lakers\n" +
                "2. Golden State Warriors\n" +
                "3. Boston Celtics\n" +
                "4. Oklahoma City Thunder\n" +
                "(번호로 입력하세요)" +
                "===========================\n";

        System.out.println(sb);
    }

    public static void printEnter(){
        System.out.println("\n=============================");
        System.out.println("     1. 내 구단");
        System.out.println("     2. 선수 이벤트 발생");
        System.out.println("=============================");
        System.out.print("메뉴를 선택하세요");

    }

    public static void printValidNum() {
        System.out.println("보기에 있는 번호를 입력해주세요");
    }

    public static void printTeam() {
        System.out.println("    구단 메뉴");
        System.out.println("\n=============================");
        System.out.println("     1. 선수 목록");
        System.out.println("     2. 구단 관리");
        System.out.println("     3. 시즌 관리");
        System.out.println("     0. 이전 메뉴");
        System.out.println("=============================");
        System.out.print("항목을 선택하세요");
    }

    public static void printTeamEvent() {
        System.out.println("    NBA 이벤트 메뉴");
        System.out.println("\n=============================");
        System.out.println("     1. 신입 등록");
        System.out.println("     2. 선수 은퇴");
        System.out.println("     0. 이전 메뉴");
        System.out.println("=============================");
        System.out.print("항목을 선택하세요");
    }

    public static void printPlayer() {
        System.out.println("    선수 메뉴");
        System.out.println("\n=============================");
        System.out.println("     1. 기본 정보 보기");
        System.out.println("     2. 능력치 보기");
        System.out.println("     0. 이전 메뉴");
        System.out.println("=============================");
        System.out.print("항목을 선택하세요");
    }

    public static void printManagement() {
        System.out.println("    구단 관리 메뉴");
        System.out.println("\n=============================");
        System.out.println("     1. 트레이드");
        System.out.println("     2. FA선수 구매");
        System.out.println("     3. 선수 판매");
        System.out.println("     4. 감독 교체");
        System.out.println("     0. 이전 메뉴");
        System.out.println("=============================");
        System.out.print("항목을 선택하세요");
    }

    public static void printGame() {
        System.out.println("    시즌 관리 메뉴");
        System.out.println("\n=============================");
        System.out.println("     1. 순위 확인");
        System.out.println("     2. 기록 보기");
        System.out.println("     3. 경기 진행");
        System.out.println("     0. 이전 메뉴");
        System.out.println("=============================");
        System.out.print("항목을 선택하세요");
    }

    public static void printCreatePlayer() {
        System.out.println("아래 형식대로 쉼표(,)로 구분하여 입력하세요:");
        System.out.println("이름, 나이, 연봉,키(cm), 윙스팬(cm), 포지션");
    }

    public static void printCreatePlayerStats() {
        System.out.println("아래 형식대로 쉼표(,)로 구분하여 입력하세요:");
        System.out.println("슛, 패스, 드리블, 리바운드, 블록, 스틸 (1~100)");
    }

    public static void printRetirePlayer() {
        System.out.println("아래 형식대로 쉼표(,)로 구분하여 입력하세요:");
        System.out.println("팀 이름, 선수 이름");
    }

    public static void printGetStatsList() {
        System.out.println("능력치를 알고 싶은 선수의 이름을 입력하세요");
    }

    public static void printGetRank(List<RankDTO> items) {
        System.out.println("📊 현재 구단 순위입니다.\n");

        int rank = 1;
        int prevScore = -1;  // 이전 팀의 점수
        int rankForTie = 1;  // 동점 순위를 위한 변수

        for (int i = 0; i < items.size(); i++) {
            String name = items.get(i).getName();
            int score = items.get(i).getWins();

            // 동점일 경우
            if (score == prevScore) {
                System.out.printf("%d위 (공동) - %s (점수: %d)\n", rankForTie, name, score);
            } else {
                // 동점이 아니면 순위 갱신
                rankForTie = rank;
                System.out.printf("%d위 - %s (점수: %d)\n", rankForTie, name, score);
            }

            prevScore = score;  // 현재 점수를 이전 점수로 설정
            rank++;  // 순위 증가
        }

        System.out.println();
    }

    public static void printQuarterScore(List<QuarterLog> quarterLogs) {
        for (QuarterLog log : quarterLogs) {
            System.out.printf("[%d쿼터] %s %d : %d %s\n",
                    log.getQuarter(), log.getTeam1Name(), log.getTeam1Score(), log.getTeam2Score(), log.getTeam2Name());
        }
    }

    public static void printFormat() {
        System.out.println("형식을 지켜서 다시 입력하세요, 이전메뉴로 돌아갑니다");
    }


    public static void printGetGameList(List<Game> gameList) {
        System.out.println("📋 경기 기록");
        for (Game game : gameList) {
            String team1Name = game.getTeam1().getName();
            String team2Name = game.getTeam2().getName();
            int team1Score = game.getScores()[0];
            int team2Score = game.getScores()[1];
            String result;

            if (game.getResult() == 1) {
                result = team1Name + " 승";
            } else if (game.getResult() == -1) {
                result = team2Name + " 승";
            } else {
                result = "무승부";
            }

            System.out.printf("%s (%d) vs %s (%d) → 결과: %s\n", team1Name, team1Score, team2Name, team2Score, result);
        }
        System.out.println();
    }

    public static void printTradeTeamName() {
        sb = new StringBuilder();

        sb.append("===========================\n")

                .append("팀을 선택하세요\n")
                .append("1. Los Angeles Lakers\n")
                .append("2. Golden State Warriors\n")
                .append("3. Boston Celtics\n")
                .append("4. Oklahoma City Thunder\n")
                .append("(번호로 입력하세요)")
                .append("===========================\n");
        System.out.println(sb.toString());
    }

    public static void printPlayerList(List<Player> playerList) {
        if (playerList == null || playerList.isEmpty()) {
            System.out.println("출력할 선수 목록이 없습니다.");
            return;
        }

        System.out.printf("%-5s %-20s %-5s %-10s %-8s %-10s %-8s %-5s\n",
                "ID", "이름", "나이", "연봉", "신장", "윙스팬", "포지션", "FA");

        for (Player p : playerList) {
            System.out.printf("%-5d %-20s %-5d %-10d %-8d %-10d %-8s %-5s\n",
                    p.getId(),
                    p.getName(),
                    p.getAge(),
                    p.getSalary(),
                    p.getHeight(),
                    p.getWingSpan(),
                    p.getPosition(),
                    p.getIsFA() ? "O" : "X");
        }
    }

    public static void printPlayerStats(Stats stats) {
        System.out.println("===== 선수 스탯 =====");
        System.out.println("ID        : " + stats.getId());
        System.out.println("슛        : " + stats.getShoot());
        System.out.println("패스       : " + stats.getPass());
        System.out.println("드리블     : " + stats.getDribble());
        System.out.println("리바운드   : " + stats.getRebound());
        System.out.println("블락       : " + stats.getBlock());
        System.out.println("스틸       : " + stats.getSteal());
        System.out.println("3점슛     : " + stats.getPoint3());
        System.out.println("====================");
    }

    public static void printResult(boolean result) {
        if (result) {
            System.out.println("성공했습니다");
        }
        else
            System.out.println("실패했습니다");
    }

    public static void printRetirePlayerResult(String result) {
        if (result != null) {
            System.out.println(result+" 가 은퇴했습니다.");
        }
    }
}
