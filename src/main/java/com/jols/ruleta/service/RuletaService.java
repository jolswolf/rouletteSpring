package com.jols.ruleta.service;

import com.jols.ruleta.entities.Ruleta;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Getter
@Setter
public class RuletaService {
    private Ruleta ruleta;
    private Boolean isWinner;
    private Boolean isLoser;
    private int[] redNumbers;
    private int[] blackNumbers;
    private String greenNumber;
    private List<String> betType;
    private int longType;
    private Boolean parar;
    private Integer dinero;
    private Integer betMoney;
    private Integer correctNumber;
    private Boolean message;

    public RuletaService() {
        this.ruleta = new Ruleta();
        this.betType = new ArrayList<>();
        this.isLoser = false;
        this.isWinner = false;
        this.parar = false;
        this.redNumbers = ruleta.getRed();
        this.blackNumbers = ruleta.getBlack();
        this.greenNumber = ruleta.getGreen();
        this.betType = ruleta.getBetTypes();
        this.longType = ruleta.getBetTypes().size();
        this.dinero = 7500;
        this.betMoney = 0;
        this.correctNumber = 0;
        this.message = false;
    }


    public void play(String modoJuego, String apuesta, Integer dineroApostado) { //Metodo para jugar
        betMoney = dineroApostado; //Se guarda el dinero apostado
        if (parar) { return; } //Si el juego esta parado no se puede jugar
        switch (modoJuego) { //Se evalua el modo de juego
            case "0":
                if (apuesta.equals("1-18")) { //Si la apuesta es 1-18
                    if (ruleta.getWinnerNumber() >= 1 && ruleta.getWinnerNumber() <= 18) {
                        dinero += dineroApostado;
                        isWinner = true;
                        isLoser = false;
                    } else {
                        dinero -= dineroApostado;
                        isLoser = true;
                        isWinner = false;
                    }

                } else if (apuesta.equals("19-36")) { //Si la apuesta es 19-36
                    if (ruleta.getWinnerNumber() >= 19 && ruleta.getWinnerNumber() <= 36) {
                        dinero += dineroApostado;
                        isWinner = true;
                        isLoser = false;
                    } else {
                        dinero -= dineroApostado;
                        isLoser = true;
                        isWinner = false;
                    }
                }

                break;
            case "1":
                if (apuesta.equals(ruleta.getWinnerNumber().toString())) { //Si la apuesta es igual al numero ganador
                    dinero += dineroApostado * 7;
                    betMoney = dineroApostado * 7;
                    isWinner = true;
                    isLoser = false;
                } else {
                    dinero -= dineroApostado;
                    isLoser = true;
                    isWinner = false;
                }
                break;
            case "2":
                boolean containsValue;
                switch (apuesta) { //Se evalua la apuesta
                    case "rojo": //Si la apuesta es rojo
                        containsValue = contains(redNumbers, ruleta.getWinnerNumber());
                        if (containsValue) {
                            dinero += dineroApostado;
                            isWinner = true;
                            isLoser = false;
                        } else {
                            dinero -= dineroApostado;
                            isLoser = true;
                            isWinner = false;
                        }

                        break;
                    case "negro":
                        containsValue = contains(blackNumbers, ruleta.getWinnerNumber());
                        if (containsValue) {
                            dinero += dineroApostado;
                            isWinner = true;
                            isLoser = false;
                        } else {
                            dinero -= dineroApostado;
                            isLoser = true;
                            isWinner = false;
                        }
                        break;
                    case "verde":
                        if ((ruleta.getWinnerNumber().toString()).equals(greenNumber)) {
                            dinero += dineroApostado * 14;
                            betMoney = dineroApostado * 14;
                            isWinner = true;
                            isLoser = false;
                        } else {
                            dinero -= dineroApostado;
                            isLoser = true;
                            isWinner = false;
                        }
                        break;
                }
                break;
                case "3":
                    if (apuesta.equals("par")) { //Si la apuesta es par
                        if (ruleta.getWinnerNumber() % 2 == 0) {
                            dinero += dineroApostado;
                            isWinner = true;
                            isLoser = false;
                        }else{
                            dinero -= dineroApostado;
                            isLoser = true;
                            isWinner = false;
                        }
                    }else if (apuesta.equals("impar")){ //Si la apuesta es impar
                        if (ruleta.getWinnerNumber() % 2 != 0) {
                            dinero += dineroApostado;
                            isWinner = true;
                            isLoser = false;
                        }else {
                            dinero -= dineroApostado;
                            isLoser = true;
                            isWinner = false;
                        }
                    }

                break;

        }
        correctNumber = ruleta.getWinnerNumber(); //Se guarda el numero ganador
        ruleta = new Ruleta(); //Se reinicia la ruleta
        if (dinero <= 0) { //Si el jugador no tiene dinero, se le quita el juego
            parar = true;
            message = true;
        }
    }

    public void reset() { //Resetea el juego
        this.ruleta = new Ruleta();
        this.betType = new ArrayList<>();
        this.dinero = 7500;
        this.betMoney = 0;
        this.isLoser = false;
        this.isWinner = false;
        this.parar = false;
        this.redNumbers = ruleta.getRed();
        this.blackNumbers = ruleta.getBlack();
        this.greenNumber = ruleta.getGreen();
        this.betType = ruleta.getBetTypes();
        this.longType = ruleta.getBetTypes().size();
    }

    public static boolean contains(final int[] array, final int value) { //Metodo para saber si un numero esta en un array
        return Arrays.stream(array).anyMatch(x -> x == value);
    }
}
