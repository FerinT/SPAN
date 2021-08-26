package repository.match;

import domain.Match;
import domain.Team;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MatchRepositoryImpl implements MatchRepository {

    // aka league?
    List<Match> matchList = new ArrayList<>();

    @Override
    public void add(String path) {
        //read file into stream
        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            stream.forEach((val) -> {
                String[] teams = val.split(",");

                Match match = new Match();
                match.setTeam1(parseTeam(teams[0]));
                match.setTeam2(parseTeam(teams[1]));

                this.matchList.add(match);

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Team parseTeam(String teamData) {
        Team team = new Team();

        String[] teamInfo = teamData.split(" ");

        team.setPoints(Long.parseLong(teamInfo[teamInfo.length - 1]));

        // for the scenario where team has a space in the name
        for (int i = 0; i < teamInfo.length - 1; i++) {
            team.setName(team.getName() + " " +teamInfo[i]);
        }

        return team;
    }

    @Override
    public void add(List<Match> matchList) {
        this.matchList = matchList;
    }

    @Override
    public List<Match> getMatches() {
        return matchList;
    }
}
