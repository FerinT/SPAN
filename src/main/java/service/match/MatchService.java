package service.match;

import domain.Match;
import domain.Team;

import java.util.List;

public interface MatchService {
    void addLeagueData(String path);
    void addLeagueData(List<Match> matchList);
    List<Match> getMatches();


    List<Team> determineRank();
    void printAverageScore();
}
