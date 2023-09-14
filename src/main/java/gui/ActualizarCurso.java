package gui;


import com.mycompany.proyecto1.Curso;
import com.mycompany.proyecto1.Profesor;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;




/**
 *
 * @author Usuario
 */
public class ActualizarCurso extends javax.swing.JFrame {
private AdministradorMainFrame adminMainFrame; // Variable para almacenar la instancia de AdministradorMainFrame
    private List<Curso> listaDeCursos;
    private Curso curso;
    
    public List<Profesor> listaDeProfesores; 

    public ActualizarCurso(AdministradorMainFrame adminMainFrame, List<Curso> cursos, Curso cursoAActualizar) {
        initComponents();
        
        
        
        int rojo = 255; // Valor de rojo (0-255)
        int verde = 187; // Valor de verde (0-255)
        int azul = 92;
        Color miColorPersonalizado = new Color(rojo, verde, azul);
        this.getContentPane().setBackground(miColorPersonalizado);
        
        this.setVisible(true); //Hace visible la ventana
        this.setLocationRelativeTo(null); //La coloca al centro
        this.setResizable(false); //Bloquea el redimensionamiento de la ventana
        this.setTitle("Actualizar Curso"); //Título a la ventana
        
        this.curso = cursoAActualizar; // Asigna el curso que se va a actualizar
        

        // Llena los campos con la información del curso seleccionado
        codigoCursoTF.setText(String.valueOf(curso.getCodigoCurso()));
        nombreCursoTF.setText(curso.getNombreCurso());
        creditosCursoTF.setText(String.valueOf(curso.getCreditos()));
        profesorComboBox.removeAllItems();
        for (Profesor profesor : adminMainFrame.getProfesores()) {
            profesorComboBox.addItem(profesor.getNombre() + " " + profesor.getApellido());
        }
        Profesor profesorDelCurso = curso.getProfesor();
        
        // Establecemos el JComboBox con el nombre del profesor asociado al curso
        profesorComboBox.setSelectedItem(profesorDelCurso.getNombre() + " " + profesorDelCurso.getApellido());
        
        
        if (profesorDelCurso != null) {
            profesorComboBox.setSelectedItem(profesorDelCurso.getNombre() + " " + profesorDelCurso.getApellido());
        }
        
        this.adminMainFrame = adminMainFrame; // Asigna la instancia de AdministradorMainFrame
        this.listaDeCursos = cursos;
    
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        updateCursoButton = new javax.swing.JButton();
        salirButton = new javax.swing.JButton();
        codigoCursoTF = new javax.swing.JTextField();
        nombreCursoTF = new javax.swing.JTextField();
        creditosCursoTF = new javax.swing.JTextField();
        profesorComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 187, 92));

        jLabel1.setText("Actualizar  Curso");

        jLabel2.setText("Código");

        jLabel3.setText("Nombre");

        jLabel4.setText("Créditos");

        jLabel5.setText("Profesor");

        updateCursoButton.setText("Actualizar");
        updateCursoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCursoButtonActionPerformed(evt);
            }
        });

        salirButton.setText("Salir");
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        codigoCursoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoCursoTFActionPerformed(evt);
            }
        });

        creditosCursoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditosCursoTFActionPerformed(evt);
            }
        });

        profesorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(codigoCursoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nombreCursoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(creditosCursoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(profesorComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(updateCursoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel1)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoCursoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombreCursoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(creditosCursoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(profesorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateCursoButton)
                    .addComponent(salirButton))
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codigoCursoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoCursoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoCursoTFActionPerformed

    private void updateCursoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCursoButtonActionPerformed
    
    String nuevoNombreCurso = nombreCursoTF.getText();
    int nuevoCreditos = Integer.parseInt(creditosCursoTF.getText());
    int nuevoCodigoCurso = Integer.parseInt(codigoCursoTF.getText());
    String nombreProfesorSeleccionado = (String) profesorComboBox.getSelectedItem();
    Profesor nuevoProfesor = null;

    for (Profesor profesor : adminMainFrame.getProfesores()) {
        String nombreCompleto = profesor.getNombre() + " " + profesor.getApellido();
        if (nombreCompleto.equals(nombreProfesorSeleccionado)) {
            nuevoProfesor = profesor;
            break;
        }
    }

    // Verifica si se encontró un Profesor válido
    if (nuevoProfesor != null) {

    // Actualizar los datos del curso
    curso.setNombreCurso(nuevoNombreCurso);
    curso.setCreditos(nuevoCreditos);
    curso.setCodigoCurso(nuevoCodigoCurso);
    curso.setProfesor(nuevoProfesor);
   
    
    this.dispose();
     adminMainFrame.actualizarTablaCursos(curso);
    
   } else {
        JOptionPane.showMessageDialog(this, "No se encontró el profesor seleccionado en la lista.");
    }
    }//GEN-LAST:event_updateCursoButtonActionPerformed

   
    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirButtonActionPerformed



    private void creditosCursoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditosCursoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditosCursoTFActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgregarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministradorMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigoCursoTF;
    private javax.swing.JTextField creditosCursoTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nombreCursoTF;
    private javax.swing.JComboBox<String> profesorComboBox;
    private javax.swing.JButton salirButton;
    private javax.swing.JButton updateCursoButton;
    // End of variables declaration//GEN-END:variables
}
