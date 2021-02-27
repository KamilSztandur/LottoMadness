package com.kamil.LottoMadness;

import javax.swing.*;
import java.awt.*;

public class LottoMadness extends JFrame{
    /* ==================================== */
    LottoEvent lotto = new LottoEvent( this );
    /* ==================================== */
    JPanel row1 = new JPanel();
    
    ButtonGroup option = new ButtonGroup();
    JCheckBox quickpick = new JCheckBox("Chybił trafił", false );
    JCheckBox personal = new JCheckBox("Typowanie", true );
    
    JPanel row2 = new JPanel();
    
    JLabel numbersLabel = new JLabel( "Twoje typy: ", JLabel.RIGHT  );
    JTextField[] numbers = new JTextField[6];
    JLabel winnersLabel = new JLabel( "Wygrywają: ", JLabel.RIGHT  );
    JTextField[] winners = new JTextField[6];
    
    JPanel row3 = new JPanel();
    
    JButton stop = new JButton("Stop");
    JButton play = new JButton("Losowanie");
    JButton reset = new JButton("Reset");
    
    JPanel row4 = new JPanel();
    
    JLabel got3Label = new JLabel("3 z 6: ", JLabel.RIGHT );
    JTextField got3 = new JTextField("0");
    
    JLabel got4Label = new JLabel("4 z 6: ", JLabel.RIGHT  );
    JTextField got4 = new JTextField("0");  
    
    JLabel got5Label = new JLabel("5 z 6: ", JLabel.RIGHT  );
    JTextField got5 = new JTextField("0");
    
    JLabel got6Label = new JLabel("6 z 6: ", JLabel.RIGHT  );
    JTextField got6 = new JTextField("0", 10 );
    
    JLabel drawingsLabel = new JLabel("Losowania: ", JLabel.RIGHT );
    JTextField drawings = new JTextField("0");
    
    JLabel yearsLabel = new JLabel("Lata: ", JLabel.RIGHT );
    JTextField years = new JTextField();
    
    JPanel row5 = new JPanel();
    JLabel youWon = new JLabel( "", JLabel.CENTER );
    JLabel winInfo = new JLabel( "", JLabel.CENTER );
    
    
    public LottoMadness(){
        setTitle("Szaleństwo Lotto");
        
        /* =============================== */
        quickpick.addItemListener( lotto );
        personal.addItemListener( lotto );
        stop.addActionListener( lotto );
        play.addActionListener( lotto );
        reset.addActionListener( lotto );
        /* ============================== */
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 550, 450 );
        
        GridLayout layout = new GridLayout( 5, 1, 10, 10 );
        setLayout( layout );
        
        
        FlowLayout layout1 = new FlowLayout( FlowLayout.CENTER, 10, 10 );
        option.add( quickpick );
        option.add( personal );
        row1.setLayout( layout1 );
        row1.add( quickpick );
        row1.add( personal );
        add( row1 );
        
        GridLayout layout2 = new GridLayout( 2, 7, 10, 10 );
        row2.setLayout( layout2 );
        row2.add( numbersLabel );
        for( int i = 0; i < 6; i++ ) {
            numbers[i] = new JTextField();
            row2.add( numbers[i] );
        }
        row2.add( winnersLabel );
        for( int i = 0; i < 6; i++ ) {
            winners[i] = new JTextField();
            winners[i].setEditable( false );
            row2.add( winners[i] );
        }
        add( row2 );
        
        FlowLayout layout3 = new FlowLayout( FlowLayout.CENTER, 10, 10 );
        row3.setLayout( layout3 );
        stop.setEnabled( false );
        row3.add( stop );
        row3.add( play );
        row3.add( reset );
        add( row3 );
        
        GridLayout layout4 = new GridLayout( 2, 3, 20, 10 );
        row4.setLayout( layout4 );
        
        row4.add( got3Label );
        got3.setEditable( false );
        row4.add( got3 );
        
        row4.add( got4Label );
        got4.setEditable( false );
        row4.add( got4 );
        
        row4.add( got5Label );
        got5.setEditable( false );
        row4.add( got5 );
        
        row4.add( got6Label );
        got6.setEditable( false );
        row4.add( got6 );
        
        row4.add( drawingsLabel );
        drawings.setEditable( false );
        row4.add( drawings );
        
        row4.add( yearsLabel );
        years.setEditable( false );
        row4.add( years );
        
        add( row4 );
        
        FlowLayout layout5 = new FlowLayout( FlowLayout.CENTER, 10, 10 );
        row5.setLayout( layout5 );
        row5.add( youWon );
        row5.add( winInfo );
        
        add( row5 );
            
        setVisible( true );
    }
    
    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch ( Exception e ) {
            //Ignoruj
        }
    }
    
    @Override
    public Insets getInsets() {
        Insets squeeze = new Insets( 50, 15, 0, 15 );
        return squeeze;
    }
}