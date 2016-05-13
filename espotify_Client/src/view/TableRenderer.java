package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * Per canviar els colors de les taules
 *
 */
public class TableRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setForeground(Color.WHITE);
        if(row % 2 == 1){
            cellComponent.setBackground(new Color(70,70,70));
        } else{
            cellComponent.setBackground(new Color(50,50,50));
        }
        return cellComponent;
    }
}
