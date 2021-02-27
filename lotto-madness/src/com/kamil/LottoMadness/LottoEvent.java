package com.kamil.LottoMadness;

import javax.swing.*;
import java.awt.event.*;

public class LottoEvent implements ItemListener, ActionListener, Runnable {
    LottoMadness gui;
    Thread playing;
    
    public LottoEvent( LottoMadness in ){
        gui = in;
    }
    
    @Override
    public void actionPerformed( ActionEvent event ) {
        String command = event.getActionCommand();
        if( command.equals("Losowanie") ){
            startPlaying();
        }
        
        if( command.equals("Stop") ){
            stopPlaying();
        }
        
        if( command.equals("Reset") ){
            clearAllFields();
            gui.play.setEnabled( true );
            gui.youWon.setText("");
            gui.winInfo.setText("");
        }
    }
    
    void startPlaying(){
        playing = new Thread( this );
        playing.start();
        gui.play.setEnabled(false);
        gui.stop.setEnabled(true);
        gui.reset.setEnabled(false);
        gui.quickpick.setEnabled(false);
        gui.personal.setEnabled(false);
    }
    
    void stopPlaying(){
        gui.stop.setEnabled( false );
        gui.play.setEnabled( true );
        gui.reset.setEnabled( true );
        gui.quickpick.setEnabled( true );
        gui.personal.setEnabled( true );
        playing = null;
    }
    
    void clearAllFields(){
        for( int i = 0; i < 6; i++ ){
            gui.numbers[i].setText( null );
            gui.winners[i].setText( null );
        }
        
        gui.got3.setText("0");
        gui.got4.setText("0");
        gui.got5.setText("0");
        gui.got6.setText("0");
        gui.drawings.setText("0");
        gui.years.setText("0");
    }
    
    @Override
    public void itemStateChanged( ItemEvent event ){
        Object item = event.getItem();
        if( item == gui.quickpick ){
            for( int i = 0; i < 6; i++ ){
                int pick;
                do {
                    pick = (int) Math.floor( Math.random() * 49 + 1);
                } while( numberGone( pick, gui.numbers, i ) );
                gui.numbers[i].setText( "" + pick );
            }
        } else {
            for( int i = 0; i < 6; i++ ) {
                gui.numbers[i].setText( null );
            }
        }
    }
    
    void addOneToField( JTextField field ){
        int num = Integer.parseInt( "0" + field.getText() );
        num++;
        field.setText("" + num );
    }
    
    boolean numberGone( int num, JTextField[] pastNums, int count ){
        for( int i = 0; i < count; i++ ) {
            if( Integer.parseInt( pastNums[i].getText() ) == num ) {
                return true;
            }
        }
        return false;
    }
    
    boolean matchedOne( JTextField win, JTextField[] allPicks ){
        for( int i = 0; i < 6; i++ ) {
            String winText = win.getText();
            if( winText.equals( allPicks[i].getText() ) ){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void run(){
        Thread thisThread = Thread.currentThread();
        while( playing == thisThread ) {
            addOneToField( gui.drawings );
            int draw = Integer.parseInt( gui.drawings.getText() );
            float numYears = (float) draw/365;
            gui.years.setText( "" + numYears );
            
            if( gui.quickpick.isSelected() ) {
                for( int i = 0; i < 6; i++ ){
                    int pick;
                    
                    do {
                        pick = (int) Math.floor( Math.random() * 49 + 1);
                    } while( numberGone( pick, gui.numbers, i ) );
                    
                    gui.numbers[i].setText( "" + pick );
                }
            }
            
            int matches = 0;
            for( int i = 0; i < 6; i++ ){
                int ball;
                do {
                    ball = (int) Math.floor( Math.random()*49 + 1 );
                } while( numberGone( ball, gui.winners, i ));
                gui.winners[i].setText("" + ball );
                if( matchedOne( gui.winners[i], gui.numbers )){
                    matches++;
                }
            }
        
            switch( matches ){
                case 3:
                    addOneToField( gui.got3 );
                    break;
                case 4:
                    addOneToField( gui.got4 );
                    break;
                case 5:
                    addOneToField( gui.got5 );
                case 6:
                    addOneToField( gui.got6 );
                    gui.stop.setEnabled( false );
                    gui.play.setEnabled( false );
                    gui.reset.setEnabled( true );
                    
                    int n = ( int ) Double.parseDouble( gui.years.getText() );
                    gui.youWon.setText( "Gratulacje. Trafiłeś szóstkę po " + Integer.toString( n ) + " latach :)" );
                    int value = 1_800_000 + Integer.parseInt( gui.got3.getText() )*40 + Integer.parseInt( gui.got4.getText() )*1_000 + Integer.parseInt( gui.got5.getText() ) * 300_000;
                    gui.winInfo.setText( "\nWydałeś na to " + Double.toString( 1.5 * draw ) + " zł i wygrałeś " + value + " złotych (podatek od wygranej). ");
                    playing = null;
            }
        }
    }
}