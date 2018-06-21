package com.exercicio.cardsbattle.model.skills;

/**
 * Enumerador de níveis de carta.
 */
public enum SkillRate {

    WEAK (80, 10, 1),   /* fraco */
    NORMAL (50, 15, 2), /* medio */
    STRONG (25, 20, 3); /* forte */

    public int probability; /* probabilidade da carta cair */
    public int priority;    /* prioridade de uso da carta */
    public int bonus;       /* bonus de ponto ao usar a carta */

    SkillRate(int prob, int bonus, int priority){
        this.probability = prob;
        this.bonus = bonus;
        this.priority = priority;
    }

}