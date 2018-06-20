package com.exercicio.cardsbattle.component;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.exercicio.cardsbattle.R;
import com.exercicio.cardsbattle.model.Skill;

public class CardManager implements View.OnClickListener {

    private ImageView card;
    private int position;
    private boolean userCard;
    private Skill skill;
    private boolean shown;

    private CardSelector cardSelector;

    public CardManager(final ImageView card, final int position, final boolean userCard){
        this.card = card;
        this.position = position;
        this.userCard = userCard;
        card.setOnClickListener(this);
    }

    public ImageView getView() {
        return this.card;
    }

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

    public void setCardClickListener(CardSelector cardSelector) {
        this.cardSelector = cardSelector;
    }

    public void fireClick() {
        this.card.performClick();
    }

    @Override
    public void onClick(final View view) {
        boolean result = cardSelector.onCardSelect(position, userCard);
        if(result) {
            this.selectCard();
        }
    }

    public void selectCard() {
        card.setBackgroundColor(Color.rgb(254, 200, 0));
    }

    public void unselectCard() {
        card.setBackgroundResource(0);
    }

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

//
//    @Override
//    public boolean onTouch(View view, MotionEvent event) {
//        switch (event.getActionMasked()) {
//            case MotionEvent.ACTION_DOWN:
//                xCoOrdinate = view.getX() - event.getRawX();
//                yCoOrdinate = view.getY() - event.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
//                break;
//            default:
//                return false;
//        }
//        return true;
//    }
}
