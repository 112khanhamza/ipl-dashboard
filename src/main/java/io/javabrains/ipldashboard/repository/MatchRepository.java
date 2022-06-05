package io.javabrains.ipldashboard.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.javabrains.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long>{
    
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    // List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
    //     String team1, LocalDate date1, LocalDate date2, 
    //     String team2, LocalDate date3, LocalDate date4
    //     );

    @Query("select m from Match m where (m.team1 = ?1 or m.team2 = ?1) and year(m.date) = ?2 order by m.date desc")
    List<Match> getMatchesByTeamBetweenDates(String teamName, int year);

    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0,count));
    }
}
