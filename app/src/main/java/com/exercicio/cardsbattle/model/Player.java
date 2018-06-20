package com.exercicio.cardsbattle.model;

import com.exercicio.cardsbattle.component.CardManager;
import com.exercicio.cardsbattle.component.PlayerManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class Player {

    private PlayerManager playerManager;

    private List<CardManager> cardManagers;

    private Skill[] skills = { null, null, null, null, null };

    private Set<Integer> activeStates = new HashSet<>();

    private Integer selectedSkill;

    private int id;

    private String name;

    /* vida */
    private int life = 150;

    /* Atributo de ataque */
    private int attack = 0;

    /* Atributo de defesa */
    private int defense = 0;

    /* Envenedado */
    private int poison = 0;

    /* Pontuação para nova vida */
    private Double bonus;

    /* Paralizado */
    private int paralyze = 0;

    /* Pode ver as cartas do inimigo */
    private boolean oracle;

    public Player(int id, int health) {
        this.id = id;
        this.life = health;
    }

}
