package ca.ravi.fifaworldcup2018.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Team implements Serializable {
    private int id;
    private String teamName;
    private String continent;
    private int played;
    private int won;
    private int drawn;
    private int lost;

    private String[] continents = {"Africa", "Asia","Caribbean","N.America", "S.America", "Oceania", "Europe"};
}
