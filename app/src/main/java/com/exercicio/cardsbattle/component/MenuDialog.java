package com.exercicio.cardsbattle.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercicio.cardsbattle.R;

/**
 * Menu de configurações em formato Dialog.
 */
public class MenuDialog extends DialogFragment implements View.OnClickListener {

    private CardSelector selector;

    public MenuDialog() { }

    /** Função estática que cria um menu*/
    public static MenuDialog createMenuDialog(CardSelector selector) {
        final MenuDialog dialog = new MenuDialog();
        dialog.selector = selector;
        return dialog;
    }

    /**
     * Função que executa quando a view vai ser criada.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_dialog, container);
    }

    /**
     * Função que executa quando a view acabou de ser criada.
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getDialog().setTitle("Menu");

        View restartView = this.getView().findViewById(R.id.restart);
        restartView.setOnClickListener(this);

        View exitView = this.getView().findViewById(R.id.exit);
        exitView.setOnClickListener(this);
    }

    /**
     * Função que executa quando um item do menu é selecionado.
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.restart) {
            this.dismissAllowingStateLoss();
            this.selector.setGameRestart();

        } else if(v.getId() == R.id.exit) {
            this.dismissAllowingStateLoss();
            this.selector.setGameExit();
        }
    }
}
