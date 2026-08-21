package main;

import java.awt.EventQueue;
import view.TelaPrincipal;

public class Principal {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal tela = new TelaPrincipal();
                    tela.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}