package com.exercicio.cardsbattle.model;

import com.exercicio.cardsbattle.component.CardManager;
import com.exercicio.cardsbattle.component.PlayerManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

/**
 * Classe que representa o jogador.
 */
@Data
public class Player {

    /* gerenciador de animações do jogador */
    private PlayerManager playerManager;

    /* gerenciadores de animações de cada uma das skills do jogador */
    private List<CardManager> cardManagers;

    /* skills do jogador */
    private Skill[] skills = { null, null, null, null, null };

    /* itens que o jogador está usando no momento */
    private Set<Integer> activeStates = new HashSet<>();

    /* a skill que o jogador selecionou */
    private Integer selectedSkill;

    /* o id do jogador */
    private int id;

    /* o nome do jogador */
    private String name;

    /* Atributo de vida */
    private int life = 150;

    /* Atributo de ataque */
    private int attack = 0;

    /* Atributo de defesa */
    private int defense = 0;

    /* Atributo de veneno */
    private int poison = 0;

    /* Pontuação para nova vida */
    private Double bonus;

    /* Atributo de paralizia */
    private int paralyze = 0;

    /* Pode ver as cartas do inimigo */
    private boolean oracle;

    /**
     * Construtor passando o id do usuário e a sua quantidade de vida inicial.
     */
    public Player(int id, int health) {
        this.id = id;
        this.life = health;
    }

}
