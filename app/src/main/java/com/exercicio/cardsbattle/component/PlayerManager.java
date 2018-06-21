package com.exercicio.cardsbattle.component;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exercicio.cardsbattle.MainActivity;
import com.exercicio.cardsbattle.R;
import com.exercicio.cardsbattle.model.Skill;

/**
 * Classe que gerencia as animações de um jogador.
 */
public class PlayerManager {

    private boolean isCPU;

    private ImageView avatar;

    private TextView lifeView;

    private TextView defenseView;

    private TextView attackView;

    private TextView poisonView;

    private TextView paralyzeView;

    private TextView commentView;

    private int lastParalyzeId;

    /**
     * Construtor que recebe as views relativas ao jogador.
     */
    public PlayerManager(final ImageView avatar,
                         final TextView lifeView,
                         final TextView defenseView,
                         final TextView attackView,
                         final TextView poisonView,
                         final TextView paralyzeView,
                         final TextView commentView,
                         final boolean isCPU) {
        this.avatar = avatar;
        this.lifeView = lifeView;
        this.defenseView = defenseView;
        this.attackView = attackView;
        this.poisonView = poisonView;
        this.paralyzeView = paralyzeView;
        this.commentView = commentView;

        this.isCPU = isCPU;
        this.lastParalyzeId = isCPU ? R.drawable.alice_congelada : R.drawable.kalista_congelada;
    }

    /**
     * Anima a aparência de ataque do jogador.
     */
    public void setPlayerAttackingState() {
        this.avatar.setImageResource(isCPU ? R.drawable.alice_ataque : R.drawable.kalista_ataque);
    }

    /**
     * Anima a aparência padrão do jogador.
     */
    public void setPlayerNormalState() {
        this.avatar.setImageResource(isCPU ? R.drawable.alice_normal : R.drawable.kalista_normal);
    }

    /**
     * Anima a aparencia de dor do jogador.
     */
    public void setPlayerHurtState(Skill skill) {
        this.avatar.setImageResource(isCPU ? skill.getCPUHurtImage() : skill.getPlayerHurtImage());
    }

    /**
     * Anima um novo item de defesa ou ataque no jogador.
     */
    public void addState(Skill skill, int viewId, final RelativeLayout parent) {
        int imageRes = isCPU ? skill.getCPUHurtImage() : skill.getPlayerHurtImage();
        if(imageRes != 0) {
            final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) avatar.getLayoutParams();
            final ImageView newState = new ImageView(parent.getContext());
            newState.setImageResource(imageRes);
            newState.setId(viewId);
            parent.addView(newState, layoutParams);
            newState.setZ(skill.getZIndex());
        }
    }

    /**
     * Anima os pontos de ataque do jogador.
     */
    public void animateAttackStatus(Integer currentValue, Integer newValue) {
        animateViewValue(attackView, currentValue, newValue);
    }

    /**
     * Anima os pontos de defesa do jogador.
     */
    public void animateDefenseStatus(Integer currentValue, Integer newValue) {
        animateViewValue(defenseView, currentValue, newValue);
    }

    /**
     * Anima os pontos de veneno do jogador.
     */
    public void animatePoisonStatus(Integer currentValue, Integer newValue) {
        animateViewValue(poisonView, currentValue, newValue);
    }

    /**
     * Anima os pontos de vida do jogador.
     */
    public void animateLifeStatus(Integer currentValue, Integer newValue) {
        animateViewValue(lifeView, currentValue, newValue);
    }

    /**
     * Anima uma view com pontos. (Função usada pelas outras funções de ataque, defesa, vida, veneno, paralyze).
     */
    private void animateViewValue(TextView view, Integer currentValue, Integer newValue) {
        if(!currentValue.equals(newValue)) {
            view.setText(currentValue.toString());
            final int value = Math.abs(currentValue - newValue);
            final int multiplier = value > 20 ? 40 : 60;
            final ValueAnimator animator = ValueAnimator.ofInt(currentValue, newValue);
            animator.setDuration(multiplier * value);
            animator.addUpdateListener(animation -> view.setText(animation.getAnimatedValue().toString()));
            animator.start();
        }
    }

    /**
     * Anima os pontos da ultima carta de paralyze usada pelo jogador.
     */
    public void animateParalyzeStatus(Skill skill, int currentParalyze, int paralyze) {
        this.lastParalyzeId = isCPU ? skill.getCPUHurtImage() : skill.getPlayerHurtImage();
        this.animateParalyzeStatus(currentParalyze, paralyze);
    }

    /**
     * Anima os pontos de paralyze do jogador.
     */
    public void animateParalyzeStatus(int currentParalyze, int paralyze) {
        avatar.setImageResource(lastParalyzeId);
        animateViewValue(paralyzeView, currentParalyze, paralyze);
    }

    /**
     * Anima o veneno atacando o jogador.
     */
    public void animatePoison() {
        avatar.setImageResource(isCPU ? R.drawable.alice_envenenada : R.drawable.kalista_envenenada);
    }

    /**
     * Exibe o comentário do jogador.
     */
    public void addComment(String text) {
        commentView.setText(text);
        commentView.setVisibility(View.VISIBLE);
        commentView.postDelayed(() -> commentView.setVisibility(View.INVISIBLE), 2000);
    }

    /**
     * Reseta e anima os pontos de status do jogador.
     * @param maxHealth
     */
    public void initValues(int maxHealth) {
        this.lifeView.setText(maxHealth + "");
        this.defenseView.setText(0+ "");
        this.attackView.setText(0+ "");
        this.poisonView.setText(0+ "");
        this.paralyzeView.setText(0+ "");
        this.commentView.setText(null);
    }
}
