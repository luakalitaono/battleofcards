package com.exercicio.cardsbattle.model;

import com.exercicio.cardsbattle.model.skills.Texts;
import com.exercicio.cardsbattle.model.skills.SkillModel;
import com.exercicio.cardsbattle.model.skills.SkillRate;
import com.exercicio.cardsbattle.model.skills.SkillType;

import java.util.List;

/**
 * Interface para as skills
 */
public interface Skill {

    int getId();

    void use(Player player, Player target);

    SkillModel getModel();

    SkillRate getRate();

    int getImage();

    int getImageItem();

    List<SkillType> getTypes();

    int getCPUHurtImage();

    int getPlayerHurtImage();

    int getAttack();

    int getHeal();

    int getDefense();

    int getDamage();

    int getParalyze();

    int getZIndex();

    int getPoison();

    boolean isCurePoison();

    boolean isSpy();

    boolean isDispel();

    Texts getTexts();

}
