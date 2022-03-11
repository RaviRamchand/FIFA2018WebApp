package ca.ravi.fifaworldcup2018.controller;

import ca.ravi.fifaworldcup2018.bean.Team;
import ca.ravi.fifaworldcup2018.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeamController {
    @Autowired
    private DatabaseAccess da;

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/addTeam")
    public String addTeam(Model model){
        model.addAttribute("team", new Team());
        return "addTeam";
    }

    @PostMapping("/formTeam")
    public String teamForm(@ModelAttribute Team team){
        da.insertAdd(team);
        return "redirect:/displayTeam";
    }

    @GetMapping("/displayTeam")
    public String displayTeam(Model model){
        model.addAttribute("teamList", da.getTeams());
        return "displayTeam";
    }

    @GetMapping("/editTeamById/{id}")
    public String editTeamById(Model model, @PathVariable long id){
        Team teamList = da.getTeam(id).get(0);
        model.addAttribute("teamList", teamList);
        return "updateTeam";
    }

    @PostMapping("/updateTeam")
    public String updateTeam(Model model, @ModelAttribute Team team){
        da.updateTeam(team);
        System.out.println(team);
        model.addAttribute("teamList", da.getTeams());
        return "redirect:/displayTeam";
    }

    @GetMapping("/deleteTeamById/{id}")
    public String deleteTeamById(Model model, @PathVariable long id){
        da.deleteTeam(id);
        model.addAttribute("teamList", da.getTeams());
        return "displayTeam";
    }

    @GetMapping("/sortName")
    public String sortName(Model model, @ModelAttribute Team team){
        model.addAttribute("teamList", da.sortName());
        return "displayTeam";
    }

    @GetMapping("/sortContinent")
    public String sortContinent(Model model, @ModelAttribute Team team){
        model.addAttribute("teamList", da.sortContinent());
        return "displayTeam";
    }

    @GetMapping("/sortPts")
    public String sortPts(Model model, @ModelAttribute Team team){
        model.addAttribute("teamList", da.sortPts(team));
        return "displayTeam";
    }


}
