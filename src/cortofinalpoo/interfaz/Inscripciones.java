/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cortofinalpoo.interfaz;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class Inscripciones extends JFrame {

    public JLabel lblNumeroInscripcion, lblNombre, lblEdad, lblPropietario,lblRaza;
    public JTextField numInscripcion, nombre, edad,propietario;
    public JComboBox marca;

    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;
    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 175, ALTOC = 20;

    DefaultTableModel tm;

    public Inscripciones() {
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNumeroInscripcion);
        container.add(lblNombre);
        container.add(lblEdad);
        container.add(lblPropietario);
        container.add(numInscripcion);
        container.add(marca);
        container.add(propietario);
        container.add(edad);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(900, 600);
        eventos();
    }

    public final void agregarLabels() {
        lblNumeroInscripcion = new JLabel("N° Inscripcion");
        lblNombre = new JLabel("Nombre");
        lblEdad = new JLabel("Edad");
        lblPropietario = new JLabel("Propietario");

        lblNumeroInscripcion.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblEdad.setBounds(10, 100, ANCHOC, ALTOC);
        lblPropietario.setBounds(400, 60, ANCHOC, ALTOC);

    }

    public final void formulario() {
        //CREACION DE TEXT FIELDS
        numInscripcion = new JTextField();
        marca = new JComboBox();
        edad = new JTextField();
        propietario= new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();
        marca.addItem("FRAM");
        marca.addItem("WIX");
        marca.addItem("Luber Finer");
        marca.addItem("OSK");

        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        //TAMAÑO Y LUGAR DE LOS TEXTFIELDS
        numInscripcion.setBounds(140, 10, ANCHOC, ALTOC);
        marca.setBounds(140, 60, ANCHOC, ALTOC);
        propietario.setBounds(500, 60, ANCHOC, ALTOC);
        edad.setBounds(140, 100, 80, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);

        //TAMAÑO Y LUGAR DE LOS BOTONES 
        buscar.setBounds(350, 10, 100, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Codigo");
        tm.addColumn("Marca");
        tm.addColumn("Stock");
        tm.addColumn("Stock en Sucursal");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getCodigo(), fi.getMarca(), fi.getStock(), fi.getExistencia()});
        }
        resultados.setModel(tm);
    }

    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(numInscripcion.getText(), marca.getSelectedItem().toString(),
                        Integer.parseInt(edad.getText()), true);
                if (no.isSelected()) {
                    f.setExistencia(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(numInscripcion.getText(), marca.getSelectedItem().toString(), Integer.parseInt(edad.getText()), true);
                if (no.isSelected()) {
                    f.setExistencia(false);
                }
                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el filtro");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(numInscripcion.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el filtro");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(numInscripcion.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "Filtro buscado no se ha encontrado");
                } else {
                    numInscripcion.setText(f.getCodigo());
                    marca.setSelectedItem(f.getMarca());
                    edad.setText(Integer.toString(f.getStock()));

                    if (f.getExistencia()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limpiarCampos();
            }
        });
    }
    
    public void limpiarCampos() {
        numInscripcion.setText("");
        marca.setSelectedItem("");
        edad.setText("");
    }
    
    public static void main(String [] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Inscripciones().setVisible(true);
            }
        });
    }
    
    
}
