/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;

/**
 *
 * @author ASUS
 */
public class MenuItem extends JPanel{
    
    private ActionListener act;
    private JSeparator separator;
    private JLabel lblIcon;
    private JLabel lblNombre;
    private ArrayList<MenuItem> subMenu;
    private Boolean showing;
    
    public void setShowing(boolean showing){
        this.showing = showing;
    }
    
    public ArrayList<MenuItem> getSubMenu(){
        return subMenu;
    }
    
    public MenuItem(int xmax, int ymax, Icon icon, String nombreMenu, ActionListener act, MenuItem ...subMenu ){
        
        initComponents();
        
        lblIcon.setIcon(icon);
        lblNombre.setText(nombreMenu);
        
        if(act != null){
            this.act = act;
        }
        
        this.setSize(new Dimension(xmax, ymax));
        this.setMaximumSize(new Dimension(xmax, ymax));
        this.setMinimumSize(new Dimension(xmax, ymax));
        
        for(int i = 0; i < subMenu.length; i++){
            this.subMenu.add(subMenu[i]);
            subMenu[i].setVisible(false);
        }
        
    }
    
    private void initComponents(){
        subMenu = new ArrayList<>();
        separator = new JSeparator();
        lblIcon = new JLabel();
        lblNombre = new JLabel();
        
        setBackground(new Color(255, 255, 255));
        
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                formMousePressed(evt);
            }
        });
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(separator)
                .addGroup(
                    layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addContainerGap()
                )
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE) 
                .addGap(0, 0, 0)   
            )
        );
    }    
    
    private void formMousePressed(MouseEvent evt) {
        if(showing){
            hideMenu();
        }else{
            showMenu();
        }
        if(act != null){
            act.actionPerformed(null);
        }
    }
    
    private void showMenu(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < subMenu.size(); i++){
                    pause();
                    subMenu.get(i).setVisible(true);
                }
            }
        }).start();
        showing = true;
        repaint();
        revalidate();
    }
    
    private void hideMenu(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = subMenu.size() - 1; i >= 0; i--){
                    pause();
                    subMenu.get(i).setVisible(false);
                    subMenu.get(i).hideMenu();
                }
            }
        }).start();
        showing = false;
        repaint();
        revalidate();
    }
    
    private void pause(){
        try{
            Thread.sleep(20);
        }catch(Exception e){}            
    }
}
