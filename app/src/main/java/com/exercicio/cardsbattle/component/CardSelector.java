package com.exercicio.cardsbattle.component;

/**
 * Interface pra observar as ações do usuário
 */
public interface CardSelector {

    /** vai executar ao selecionar uma carta */
    boolean onCardSelect(int position, boolean userCard);

    /** vai executar ao reiniciar a partida */
    void setGameRestart();

    /** vai executar ao fechar o jogo */
    void setGameExit();

}
