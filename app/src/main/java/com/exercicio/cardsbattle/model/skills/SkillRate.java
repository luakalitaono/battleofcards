package com.exercicio.cardsbattle.model.skills;

public enum SkillRate {

    WEAK (80, 10, 1),
    NORMAL (50, 15, 2),
    STRONG (25, 20, 3);

    public int probability;
    public int priority;
    public int bonus;

    SkillRate(int prob, int bonus, int priority){
        this.probability = prob;
        this.bonus = bonus;
        this.priority = priority;
    }

}