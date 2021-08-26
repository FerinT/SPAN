package domain;

/*
* Creating a separate domain class in case we add attributes to a 'team'
* */
public class Team {
    private String name = "";
    private long points;
    private long leaguePoints;

    public long getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(long leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public String getName() {
        return name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}
