/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;


import LabEstructuras.Display;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class MenuItem extends JPanel{
    
    private final int xmax, ymax;
    private int positionX, positionY;
    private ActionListener act;
    private JSeparator separator;
    private final String nombreMenu;
    private final Icon icon;
    private JLabel lblIcon;
    private JLabel lblNombre;
    private ArrayList<MenuItem> subMenu;
    private Boolean showing;
    private final Icon iconArrowP;
    private final Icon iconArrowE;
    private Boolean isEntregable;
    
    
    public void setShowing(boolean showing){
        this.showing = showing;
    }
    
    public ArrayList<MenuItem> getSubMenu(){
        return subMenu;
    }
    
    public MenuItem(Icon icon, String nombreMenu, ActionListener act, boolean isEntregable){
        iconArrowP = new ImageIcon(getClass().getResource("/Images/arrow.png"));
        iconArrowE = new ImageIcon(getClass().getResource("/Images/arrow2.png"));
        
        this.nombreMenu = nombreMenu;
        this.xmax = 530;
        this.ymax = 50;
        this.icon = icon;
        this.isEntregable = isEntregable;
        
        //initComponents();
        testingLocation();
        
        lblIcon.setIcon(icon);
        lblNombre.setText(nombreMenu);
        
        if(act != null){
            this.act = act;
        }
        
        this.setSize(new Dimension(xmax, ymax));
        this.setMaximumSize(new Dimension(xmax, ymax));
        this.setMinimumSize(new Dimension(xmax, ymax));
        
    }
    
    private void initComponents(){
        showing = false;
        subMenu = new ArrayList<>();
        separator = new JSeparator();
        lblIcon = new JLabel();
        lblNombre = new JLabel();
        
        setBackground(new Color(255, 255, 255));
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                formMousePressed(evt);
            }
        });
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(separator)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()   
                    .addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
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
    
    private void testingLocation(){
        showing = false;
        subMenu = new ArrayList<>();
        separator = new JSeparator();
        lblIcon = new JLabel();
        lblNombre = new JLabel();
        positionX = 15;
        positionY = 15;
                
        setBackground(new Color(255, 255, 255));
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                formMousePressed(evt);
            }
        });
        
        this.setLayout(null);
        
        lblIcon.setSize(40, 24);
        lblIcon.setLocation(positionX, positionY);
        
        lblNombre.setSize(470, 24);
        lblNombre.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        lblNombre.setLocation(positionX + lblIcon.getWidth(), positionY);
        
        this.add(lblIcon);
        this.add(lblNombre);
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
    }
    
    
    
    public void addSubMenuItem(MenuItem item){
        this.subMenu.add(item);
        item.setVisible(false);
    }
    
    private void formMousePressed(MouseEvent evt) {
        Display.setNombreObject(this.nombreMenu);
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
        new Thread(() -> {
            for(int i = 0; i < subMenu.size(); i++){
                pause();
                subMenu.get(i).setVisible(true);
            }
            showing = true;
            if(isEntregable){
                lblIcon.setIcon(iconArrowE);
            }else{
                lblIcon.setIcon(iconArrowP);
            }
            getParent().repaint();
            getParent().revalidate();
        }).start();
    }
    
    private void hideMenu(){
        new Thread(() -> {
            for(int i = subMenu.size() - 1; i >= 0; i--){
                pause();
                subMenu.get(i).setVisible(false);
                subMenu.get(i).hideMenu();
            }
            showing = false;
            
            lblIcon.setIcon(icon);
            getParent().repaint();
            getParent().revalidate();
        }).start();
    }
    
    private void pause(){
        try{
            Thread.sleep(20);
        }catch(InterruptedException e){}            
    }
    
    public String getNombreMenu(){
        return this.nombreMenu;
    }
}
