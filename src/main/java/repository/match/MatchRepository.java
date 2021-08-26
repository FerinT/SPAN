package repository.match;

import domain.Match;

import java.util.List;

public interface MatchRepository {
    void add(String path);
    void add(List<Match> matchList);

    List<Match> getMatches();
}
