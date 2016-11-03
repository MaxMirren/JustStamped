package com.maxm.just_stamped.tabs;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.maxm.just_stamped.js.R;

/**
 * Created by Mr_M on 27.10.2016.
 */

public class TabMine extends Fragment {

    private View currentView;
    private FloatingActionButton fabAdd, fabAddNote, fabAddFolder;
    private Animation fabOpen, fabClose, fabRotateUp, fabRotateDown;
    private boolean floatingActionButtonPressed = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.tab_mine, container, false);
        setFloatingActionButtons();
        return currentView;
    }

    /*
    Этот метод добавляет управляющие кнопки Floating Action Buttons
    */
    protected void setFloatingActionButtons () {
        fabAdd = (FloatingActionButton) currentView.findViewById(R.id.floating_action_button_add);
        fabAddNote = (FloatingActionButton) currentView.findViewById(R.id.floating_action_button_add_note);
        fabAddFolder = (FloatingActionButton) currentView.findViewById(R.id.floating_action_button_add_folder);
        fabOpen = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close);
        fabRotateUp = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_rotate_up);
        fabRotateDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_rotate_down);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (floatingActionButtonPressed) {
                    fabAdd.startAnimation(fabRotateDown);
                    fabAddNote.startAnimation(fabClose);
                    fabAddFolder.startAnimation(fabClose);
                    fabAddNote.setClickable(false);
                    fabAddFolder.setClickable(false);
                    floatingActionButtonPressed = false;
                }
                else {
                    fabAdd.startAnimation(fabRotateUp);
                    fabAddNote.startAnimation(fabOpen);
                    fabAddFolder.startAnimation(fabOpen);
                    fabAddNote.setClickable(true);
                    fabAddFolder.setClickable(true);
                    floatingActionButtonPressed = true;
                }
            }
        });
    }
}
