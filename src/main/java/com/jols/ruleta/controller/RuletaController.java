package com.jols.ruleta.controller;

import com.jols.ruleta.entities.Ruleta;
import com.jols.ruleta.service.RuletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RuletaController {
    @Autowired
    private RuletaService ruletaService;

    @GetMapping("/play")
    public ModelAndView ruleta(@RequestParam(value = "betTypes") String gameType,
                               @RequestParam(value = "_betTypes") String _betType,
                               @RequestParam(value = "money") Integer dinero,
                               @RequestParam(value = "betOption") String bet) {
        Ruleta ruleta = ruletaService.getRuleta();
        ruletaService.play(gameType, bet, dinero);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("ruleta", ruleta);
        mav.addObject("longType", ruletaService.getLongType());
        mav.addObject("betType", ruletaService.getBetType());
        mav.addObject("dinero", ruletaService.getDinero());
        mav.addObject("isWinner", ruletaService.getIsWinner());
        mav.addObject("isLoser", ruletaService.getIsLoser());
        mav.addObject("betMoney", ruletaService.getBetMoney());
        mav.addObject("winnerNumber", ruletaService.getCorrectNumber());
        mav.addObject("message", ruletaService.getMessage());
        mav.addObject("parar", ruletaService.getParar());
        return mav;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("ruleta", ruletaService.getRuleta());
        modelAndView.addObject("longType", ruletaService.getLongType());
        modelAndView.addObject("betType", ruletaService.getBetType());
        modelAndView.addObject("dinero", ruletaService.getDinero());
        return modelAndView;
    }

    @GetMapping("/reset")
    public ModelAndView reset() {
        ruletaService.reset();
        return index();
    }
}
