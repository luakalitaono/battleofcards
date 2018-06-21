package com.exercicio.cardsbattle.component;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.exercicio.cardsbattle.R;
import com.exercicio.cardsbattle.model.Skill;

/**
 * Classe que gerencia efeitos visuais na carta
 */
public class CardManager implements View.OnClickListener {

    private ImageView card;
    private int position;
    private boolean userCard;
    private Skill skill;
    private boolean shown;

    private CardSelector cardSelector;

    /**
     * Construtor
     */
    public CardManager(final ImageView card, final int position, final boolean userCard){
        this.card = card;
        this.position = position;
        this.userCard = userCard;
        card.setOnClickListener(this);
    }

    /**
     * Retorna a view que anima a carta
     * @return
     */
    public ImageView getView() {
        return this.card;
    }

    /**
     * Altera a view com a imagem da nova carta, ou esconde caso seja a CPU
     */
    public void updateSkill(Skill skill) {
        this.skill = skill;
        if(userCard) {
            card.setImageResource(skill.getImage());
            this.shown = true;
        } else {
            card.setImageResource(R.drawable.hidden_card);
            this.shown = false;
        }
    }

    /**
     * Configura o Listener que vai ouvir quando a carta for selecionada
     * @param cardSelector
     */
    public void setCardClickListener(CardSelector cardSelector) {
        this.cardSelector = cardSelector;
    }

    /**
     * Função que clica na carta programaticamente (usada pra CPU).
     */
    public void fireClick() {
        this.card.performClick();
    }

    /**
     * Função que executa quando uma carta é clicada.
     */
    @Override
    public void onClick(final View view) {
        boolean result = cardSelector.onCardSelect(position, userCard);
        if(result) {
            this.selectCard();
        }
    }

    /**
     * aplica uma borda dourada na carta ao ser selecionada.
     */
    public void selectCard() {
        card.setBackgroundColor(Color.rgb(254, 200, 0));
    }

    /**
     * remove a borda dourada da carta quando a carta deixa de ser selecionada.
     */
    public void unselectCard() {
        card.setBackgroundResource(0);
    }

    /**
     * Função que anima o efeito da carta VISÃO (ver as cartas do adiversário).
     */
    public void animateSpyStatus() {
        if(!shown) {
            shown = true;
            final float scaleX = card.getScaleX();
            final float translationX = card.getTranslationX();
            card.animate().scaleX(0)
                    .translationX(card.getWidth() / 3)
                    .setDuration(200)
                    .withEndAction(() -> {
                        card.setImageResource(skill.getImage());
                        card.animate().scaleX(scaleX)
                                .translationX(translationX)
                                .setDuration(200)
                                .start();
                    })
                    .start();
        }
    }
}
