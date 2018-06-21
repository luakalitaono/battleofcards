package com.exercicio.cardsbattle.model;

import com.exercicio.cardsbattle.model.skills.SkillModel;
import com.exercicio.cardsbattle.model.skills.SkillRate;
import com.exercicio.cardsbattle.model.skills.SkillType;
import com.exercicio.cardsbattle.model.skills.Texts;

import java.util.List;

/**
 * Interface que representa uma carta
 */
public interface Skill {
    /** retorna o id da skill */
    int getId();

    /** retorna o modelo da carta */
    SkillModel getModel();

    /** retorna o nível da carta */
    SkillRate getRate();

    /** retorna a imagem da carta */
    int getImage();

    /** retorna a imagem do item que a carta representa */
    int getImageItem();

    /** retorna os tipos de ataques que a carta tem */
    List<SkillType> getTypes();

    /** retorna a imagem da dano na cpu */
    int getCPUHurtImage();

    /** retorna a imagem de dano no jogador */
    int getPlayerHurtImage();

    /** retorna os pontos de ataque que a carta dá */
    int getAttack();

    /** retorna os pontos de cura que a carta dá */
    int getHeal();

    /** retorna os pontos de defesa que a carta dá */
    int getDefense();

    /** retorna os pontos de dano que a carta dá */
    int getDamage();

    /** retorna os pontos de paralizia que a carta dá */
    int getParalyze();

    /** retorna os pontos de ataque que a carta dá */
    int getPoison();

    /** retorna se a carta é carta de cura */
    boolean isCurePoison();

    /** retorna se a carta é de ver as cartas do inimigo */
    boolean isSpy();

    /** retorna se a carta é de remover os itens do inimigo */
    boolean isDispel();

    /** retorna a prioridade para aparecer na tela do item da skill */
    int getZIndex();

    /** retorna os comentários relacionados à carta */
    Texts getTexts();

}
