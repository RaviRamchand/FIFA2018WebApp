package ca.ravi.fifaworldcup2018.database;

import ca.ravi.fifaworldcup2018.bean.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public List<Team> getTeams(){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "SELECT * FROM Teams";

        return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<Team>(Team.class));
    }

    public void insertAdd(Team team){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "INSERT INTO Teams(TeamName, Continent, Played, Won, Drawn, Lost)" +
                "VALUES(:TeamName, :Continent, :Played, :Won, :Drawn, :Lost)";
        namedParameter.addValue("TeamName", team.getTeamName());
        namedParameter.addValue("Continent", team.getContinent());
        namedParameter.addValue("Played", team.getPlayed());
        namedParameter.addValue("Won", team.getWon());
        namedParameter.addValue("Drawn", team.getDrawn());
        namedParameter.addValue("Lost", team.getLost());

        jdbc.update(query, namedParameter);
    }

    public List<Team> getTeam(long id){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "SELECT * FROM Teams WHERE id=:id";
        namedParameter.addValue("id", id);

        return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<Team>(Team.class));
    }

    public void updateTeam(Team team){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "UPDATE Teams SET TeamName=:TeamName, Continent=:Continent, Played=:Played, " +
                "Won=:Won, Drawn=:Drawn, Lost=Lost WHERE id = :id";
        namedParameter.addValue("TeamName", team.getTeamName());
        namedParameter.addValue("Continent", team.getContinent());
        namedParameter.addValue("Played", team.getPlayed());
        namedParameter.addValue("Won", team.getWon());
        namedParameter.addValue("Drawn", team.getDrawn());
        namedParameter.addValue("Lost", team.getLost());
        namedParameter.addValue("id", team.getId());

        jdbc.update(query, namedParameter);
    }

    public void deleteTeam(long id){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "DELETE FROM Teams WHERE id = :id";
        namedParameter.addValue("id", id);

        jdbc.update(query, namedParameter);
    }

    public List<Team> sortName(){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "SELECT * FROM Teams ORDER BY TeamName";

        return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(Team.class));
    }

    public List<Team> sortContinent(){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "SELECT * FROM Teams ORDER BY Continent";

        return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(Team.class));
    }

    public List<Team> sortPts(Team team){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();

        String query = "SELECT * FROM Teams ORDER BY (won * 3) + (drawn * 1) + (lost * 0) DESC";

        return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(Team.class));
    }
}
