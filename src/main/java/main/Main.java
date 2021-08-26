package main;

import domain.Match;
import domain.Team;
import service.match.MatchService;
import service.match.MatchServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MatchService matchService = new MatchServiceImpl();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter league data, press 1 for file or 2 to manually enter: ");
        String option = scanner.nextLine();

        // before parsing validate the input
        if (option.equals("1")) {
            System.out.print("Please enter the file path: ");
            String filePath = scanner.nextLine();
            matchService.addLeagueData(filePath);

            List<Team> teamList = matchService.determineRank();
            displayRank(teamList);

        } else if (option.equals("2")) {

            System.out.print("Please enter team 1 name: ");
            String name = scanner.nextLine();
            List<Match> matchList = new ArrayList<>();

            while (!name.equals("q")) {
                System.out.print("Please enter team 1 score: ");
                String score = scanner.nextLine();

                Team team1 = new Team();
                team1.setName(name);
                team1.setPoints(Long.parseLong(score));

                System.out.print("Please enter team 2 name: ");
                name = scanner.nextLine();
                System.out.print("Please enter team 2 score: ");
                score = scanner.nextLine();

                Team team2 = new Team();
                team2.setName(name);
                team2.setPoints(Long.parseLong(score));

                Match match = new Match();
                match.setTeam1(team1);
                match.setTeam2(team2);

                matchList.add(match);

                System.out.print("Please enter team 1 name or 'q' to quit: ");
                name = scanner.nextLine();

            }
            matchService.addLeagueData(matchList);
            List<Team> teamList = matchService.determineRank();
            displayRank(teamList);
        }


    }

    private static void displayRank(List<Team> teamList) {
        System.out.println("==================== REPORT ====================");
        for (int i = 0; i < teamList.size(); i++) {
            System.out.println(i + 1 + ". " + teamList.get(i).getName() + ", " + teamList.get(i).getLeaguePoints() + " pts");
        }
        System.out.println("==================== REPORT END ====================");
    }
}
