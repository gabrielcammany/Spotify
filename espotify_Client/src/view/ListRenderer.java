package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * Clase per canviar l'estetica de les listes
 *
 */
public class ListRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setForeground(Color.WHITE);
        if (isSelected) {
            c.setBackground(new Color(60,60,60));
        }else{
        	 c.setBackground(new Color(70,70,70));
        }
        return c;
    }
}