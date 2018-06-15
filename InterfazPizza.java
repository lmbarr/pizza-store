// Para la interfaz grafica se utilizo como referencia:
// https://www.tutorialspoint.com/swing/swing_jcheckbox.htm

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

abstract class Interfaz {
    private Pizza pizza;
    protected Ingrediente[] ingTodos;
    public static final int MAXIMOING = 4;

    Interfaz() {
        ingTodos = new Ingrediente[] {new Ingrediente1(), new Ingrediente2(),
            new Ingrediente3(), new Ingrediente4(), new Ingrediente5()};
    }
    public abstract void actualizarInterfaz();
    public Pizza crearPizza(ArrayList<Ingrediente> ing, Tamanos tamano) {
        pizza = new Pizza(ing, tamano);
        return pizza;
    }
}
public class InterfazPizza extends Interfaz {
    private JFrame mainFrame;
    private JLabel titulo;
    private JTextArea salidaPrecio;
    private JPanel controlPanel;
    private JCheckBox[] listCheckBox;
    private int control = 0;

    public InterfazPizza(){
        prepararGUI();
        addCheckBox();
        addComboBox();
    }
    public static void main(String[] args){
        InterfazPizza gui = new InterfazPizza();
    }
    private void prepararGUI(){
        mainFrame = new JFrame("PIZZERIA");
        mainFrame.setSize(800,200);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        titulo = new JLabel("", JLabel.CENTER);
        salidaPrecio = new JTextArea(); salidaPrecio.setEditable(false);
        salidaPrecio.setFont(new Font("Verdana", Font.BOLD, 15));

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(titulo);
        mainFrame.add(controlPanel);
        mainFrame.add(salidaPrecio);
        mainFrame.setVisible(true);
    }
    public void actualizarInterfaz() {
        for(JCheckBox checkBox: listCheckBox){
            checkBox.setSelected(false);
        }
    }
    private void addEventButton(JButton b, final JComboBox<Tamanos> tamanosCombo) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              Tamanos t = null;
              ArrayList<Ingrediente> ingSeleccionados = new ArrayList<Ingrediente>();
              if (tamanosCombo.getSelectedIndex() != -1) {
                  t = tamanosCombo.getItemAt(tamanosCombo.getSelectedIndex());

                  for(int i = 0; i < listCheckBox.length; i++) {
                      if(listCheckBox[i].isSelected()) {
                          ingSeleccionados.add(ingTodos[i]);
                      }
                  }
              }
              actualizarInterfaz();
              salidaPrecio.setText(crearPizza(ingSeleccionados, t).toString());
           }
        });
    }
    private void addEventCheckBox(final JCheckBox checkBox) {
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==1){
                    control += 1;
                }else {
                    control -= 1;
                }

                for(JCheckBox i: listCheckBox){
                    if(i != checkBox && control == MAXIMOING){
                        i.setEnabled(false);
                    }
                    else{
                        i.setEnabled(true);
                    }
                }
            }
        });
    }
    private void addCheckBox(){

        listCheckBox = new JCheckBox[ingTodos.length];

        for(int i=0; i < ingTodos.length; i++){
            JCheckBox cb = new JCheckBox(ingTodos[i].toString());
            listCheckBox[i] = cb;
            addEventCheckBox(cb);
        }
        titulo.setText("Pizzas");
        for(JCheckBox i: listCheckBox) {
            controlPanel.add(i);
        }
        mainFrame.setVisible(true);
    }
    private void addComboBox(){
        Tamanos[] s = Tamanos.values();
        DefaultComboBoxModel<Tamanos> tamanosDisponibles = new DefaultComboBoxModel<Tamanos>(s);
        final JComboBox<Tamanos> tamanosCombo = new JComboBox<Tamanos>(tamanosDisponibles);
        tamanosCombo.setSelectedIndex(0);
        JScrollPane tamanosScrollPane = new JScrollPane(tamanosCombo);
        JButton showButton = new JButton("Ordenar");
        addEventButton(showButton, tamanosCombo);

        controlPanel.add(tamanosScrollPane);
        controlPanel.add(showButton);
        mainFrame.setVisible(true);
   }
}
