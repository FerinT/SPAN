package service.match;

import domain.Match;
import domain.Team;
import repository.match.MatchRepository;
import repository.match.MatchRepositoryImpl;

import java.util.*;

public class MatchServiceImpl implements MatchService {
    MatchRepository matchRepository = new MatchRepositoryImpl();

    @Override
    public void addLeagueData(String path) {
        matchRepository.add(path);
    }

    @Override
    public void addLeagueData(List<Match> matchList) {
        matchRepository.add(matchList);
    }

    @Override
    public List<Match> getMatches() {
        return matchRepository.getMatches();
    }

    @Override
    public List<Team> determineRank() {
        List<Match> matchList = matchRepository.getMatches();

        List<Team> teams = this.calculatePoints(matchList);
        return this.calculateRank(teams);
    }

    @Override
    public void printAverageScore() {
        // an example of how we could extend usage
    }

    private List<Team> calculatePoints(List<Match> matchList){
        HashMap<String, Team> hashMap = new HashMap<>();

        for(Match match: matchList) {

            if(match.getTeam1().getPoints() > match.getTeam2().getPoints()) {
                Team team = hashMap.get(match.getTeam1().getName());
                if(team == null) {
                    match.getTeam1().setLeaguePoints(match.getTeam1().getLeaguePoints() + 3);
                    hashMap.put(match.getTeam1().getName(), match.getTeam1());
                } else {
                    team.setLeaguePoints(team.getLeaguePoints() + 3);
                    hashMap.put(match.getTeam1().getName(), team);
                }
                hashMap.putIfAbsent(match.getTeam2().getName(), match.getTeam2());

            } else if(match.getTeam2().getPoints() > match.getTeam1().getPoints()) {
                Team teamB = hashMap.get(match.getTeam2().getName());
                if(teamB == null) {
                    match.getTeam2().setLeaguePoints(match.getTeam2().getLeaguePoints() + 3);
                    hashMap.put(match.getTeam2().getName(), match.getTeam2());
                } else {
                    teamB.setLeaguePoints(teamB.getLeaguePoints() + 3);
                    hashMap.put(match.getTeam2().getName(), teamB);
                }
                hashMap.putIfAbsent(match.getTeam1().getName(), match.getTeam1());
            } else if(match.getTeam1().getPoints() == match.getTeam2().getPoints()) {

                Team teamA = hashMap.get(match.getTeam1().getName());
                if(teamA == null) {
                    match.getTeam1().setLeaguePoints(match.getTeam1().getLeaguePoints() + 1);
                    hashMap.put(match.getTeam1().getName(), match.getTeam1());
                } else {
                    teamA.setLeaguePoints(teamA.getLeaguePoints() + 1);
                    hashMap.put(match.getTeam1().getName(), teamA);
                }

                Team teamB = hashMap.get(match.getTeam2().getName());
                if(teamB == null) {
                    match.getTeam2().setLeaguePoints(match.getTeam2().getLeaguePoints() + 1);
                    hashMap.put(match.getTeam2().getName(), match.getTeam2());
                } else {
                    teamB.setLeaguePoints(teamB.getLeaguePoints() + 1);
                    hashMap.put(match.getTeam2().getName(), teamB);
                }
            }
        }

        return new ArrayList<>(hashMap.values());
    }

    private List<Team> calculateRank(List<Team> teamList){

        teamList.sort(Comparator.comparing(Team::getLeaguePoints).reversed()
                .thenComparing(Team::getName));

        return teamList;
    }
}
