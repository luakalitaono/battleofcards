package com.exercicio.cardsbattle.component;

public interface CardSelector {

    boolean onCardSelect(int position, boolean userCard);

    void setGameRestart();

    void setGameExit();

}
