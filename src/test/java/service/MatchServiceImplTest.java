package service;

import domain.Match;
import domain.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.match.MatchService;
import service.match.MatchServiceImpl;

import java.io.File;
import java.util.List;
import java.util.Objects;


public class MatchServiceImplTest {
    // test that its in order
    // test super long team name
    MatchService matchService = new MatchServiceImpl();
    File file;

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(Objects.requireNonNull(classLoader.getResource("sample-input")).getFile());
    }

    @Test
    public void verifyAllDataIsReadFromFile() {

        matchService.addLeagueData(file.getPath());

        List<Match> matchList = matchService.getMatches();
        Assert.assertEquals(matchList.size(), 5);
    }

    @Test
    public void verifyRank() {
        matchService.addLeagueData(file.getPath());
        List<Team> rank = matchService.determineRank();

        Assert.assertEquals(rank.get(0).getName(), "Tarantulas");
        Assert.assertEquals(rank.get(1).getName(), "Lions");
        Assert.assertEquals(rank.get(2).getName(), "FC Awesome");
        Assert.assertEquals(rank.get(3).getName(), "Snakes");
        Assert.assertEquals(rank.get(4).getName(), "Grouches");
    }
}
