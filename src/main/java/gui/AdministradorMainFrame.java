package gui;

import com.mycompany.proyecto1.Administrador;
import com.mycompany.proyecto1.AppState;
import com.mycompany.proyecto1.Profesor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JFrame;
import com.mycompany.proyecto1.Curso; 
import com.mycompany.proyecto1.Estudiante;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;





/**
 *
 * @author Usuario
 */
public class AdministradorMainFrame extends javax.swing.JFrame {
public List<Profesor> profesores = new ArrayList<>();
public List<Curso> cursos = new ArrayList<>();
public List<Estudiante> estudiantes = new ArrayList<>();


   
    public List<Profesor> getProfesores() {
        return profesores;
    }
    
    
    public void agregarProfesor(int codigo, String nombre, String apellido, String correo, String genero, String passwordPersona, String contrasenaAsignada) {
    Profesor profesor = new Profesor(codigo, nombre, apellido, correo, genero, passwordPersona, contrasenaAsignada);
    this.profesores.add(profesor);
    this.generarGrafica();
}
    public void agregarCurso(int codigoCurso, String nombreCurso, int creditos, Profesor profesor) {
        Curso curso = new Curso(codigoCurso, nombreCurso, creditos, profesor);
        cursos.add(curso); // Agregar el curso a la lista de cursos
    }

    // Agregar el método getCursos para obtener la lista de cursos
    public List<Curso> getCursos() {
        return cursos;
    }

   
    private final Administrador administrador;
    private Profesor profesor;
    private boolean eliminacionConfirmada = false;
    private static AdministradorMainFrame instance = null;
    public static AdministradorMainFrame getInstance() {
        if (instance == null) {
            instance = new AdministradorMainFrame();
        }
        return instance;
    }
    
    /**
     * Creates new form AdministradorMainFrame
     */
    public AdministradorMainFrame() {
        initComponents();
        
        int rojo = 255; // Valor de rojo (0-255)
        int verde = 187; // Valor de verde (0-255)
        int azul = 92;
        Color miColorPersonalizado = new Color(rojo, verde, azul);
        this.getContentPane().setBackground(miColorPersonalizado);
        
        this.setVisible(true); //Hace visible la ventana
        this.setLocationRelativeTo(null); //La coloca al centro
        this.setResizable(false); //Bloquea el redimensionamiento de la ventana
        this.setTitle("Menú Administrador"); //Título a la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.administrador = (Administrador)AppState.loggedUser;
        List<Profesor> profesores = administrador.getProfesores(); // Obtiene la lista de profesores

        this.LlenarTablaProfesores(profesores);
        this.profesores = administrador.getProfesores();
        this.generarGrafica();
        
        this.LlenarTablaEstudiantes(estudiantes);
        this.estudiantes = administrador.getEstudiantes();
        this.generarGraficaEstudiantes();
        
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent evt) {
            if (!eliminacionConfirmada) {
                // Si la eliminación no se confirmó, muestra un mensaje de advertencia
                JOptionPane.showMessageDialog(AdministradorMainFrame.this, "La eliminación del profesor fue cancelada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    });
        for (WindowListener wl : this.getWindowListeners()) {
        this.removeWindowListener(wl);
    }
        
    
    this.setVisible(true);

    }
    


public void mostrarVentana() {
        // Verifica si la ventana ya está visible
        if (!isVisible()) {
            // Si no es visible, hazla visible
            setVisible(true);
        }
        // Lleva la ventana al frente (puede depender de tu plataforma)
        toFront();
    }
    public void LlenarTablaProfesores(List<Profesor> profesores){
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Código");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Correo");
            modelo.addColumn("Género");
            
            for (Profesor profesor: profesores){
            Object[] fila = {profesor.codigo, profesor.nombre, profesor.apellido, profesor.correo, profesor.genero};
                modelo.addRow(fila);
                
            }
            
    
            profesoresJTable.setModel(modelo);
            generarGrafica();
    
    }
    public void LlenarTablaCursos(List<Curso> cursos){
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Código");
            modelo.addColumn("Nombre");
            modelo.addColumn("Créditos");
            modelo.addColumn("Profesor");

    for (Curso curso : cursos) {
    String nombreProfesor = curso.getProfesor().getNombre(); 
    String apellidoProfesor = curso.getProfesor().getApellido(); 

    
    Object[] fila = {curso.codigoCurso, curso.nombreCurso, curso.creditos, nombreProfesor + " " + apellidoProfesor};
    modelo.addRow(fila);
            cursosJTable.setModel(modelo);
            
    
    }
    }
    
    public void LlenarTablaEstudiantes(List<Estudiante> estudiantes){
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Código");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Correo");
            modelo.addColumn("Género");
             

            

            for (Estudiante estudiante : estudiantes) {
            
            Object[] fila = {
            estudiante.getCodigo(),
            estudiante.getNombre(),
            estudiante.getApellido(),
            estudiante.getCorreo(),
            estudiante.getGenero(),
           
            };
            modelo.addRow(fila);            
        }
            TableColumn column = estudiantesJTable.getColumnModel().getColumn(4);
            column.setMinWidth(0);
            column.setMaxWidth(0);
            column.setPreferredWidth(0);
            column.setResizable(false);
            
            estudiantesJTable.setModel(modelo);
            
    
    }
    



    public void generarGrafica() {
    DefaultPieDataset dataset = new DefaultPieDataset();
    List<Profesor> profesores = administrador.getProfesores();

    int totalProfesores = profesores.size();
    int masculinos = 0;
    int femeninos = 0;

    // Calcular la cantidad de profesores masculinos y femeninos
    for (Profesor profesor : profesores) {
        String genero = profesor.getGenero();
        System.out.println("Género: " + genero);
        if ("F".equalsIgnoreCase(genero)) {
            femeninos++;
        } else if ("M".equalsIgnoreCase(genero)) {
            masculinos++;
        }
    }

    // Calcular los porcentajes
    double porcentajeMasculinos = (double) masculinos / totalProfesores * 100;
    double porcentajeFemeninos = (double) femeninos / totalProfesores * 100;

    // Agregar los datos al dataset
    dataset.setValue("Masculinos", porcentajeMasculinos);
    dataset.setValue("Femeninos", porcentajeFemeninos);

    // Crear la gráfica
    JFreeChart chart = ChartFactory.createPieChart(
            "Género de Profesores", // Título de la gráfica
            dataset,
            true,
            true,
            false
    );

    // Configurar el formato de porcentaje en números enteros
    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}%", NumberFormat.getIntegerInstance(), NumberFormat.getPercentInstance()));
    plot.setSectionPaint("Masculinos", Color.CYAN);
    plot.setSectionPaint("Femeninos", Color.PINK);
    
    // Crear el panel de gráfica y mostrarlo en la ventana
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(350, 314));
    chartJPanel.setLayout(new java.awt.BorderLayout()); 
    chartJPanel.add(chartPanel); 

    this.setVisible(true);
    this.validate();
    
    
}

    public void actualizarTablaProfesores(Profesor profesorActualizado) {
    // Encuentra y actualiza el profesor en la lista de profesores
    for (int i = 0; i < profesores.size(); i++) {
        Profesor prof = profesores.get(i);
        if (prof.getCodigo() == profesorActualizado.getCodigo()) {
            // Actualiza los datos del profesor
            prof.setNombre(profesorActualizado.getNombre());
            prof.setApellido(profesorActualizado.getApellido());
            prof.setCorreo(profesorActualizado.getCorreo());
            prof.setGenero(profesorActualizado.getGenero());
            break;
        }
    }

    // Luego, actualiza la tabla con los datos actualizados
    LlenarTablaProfesores(profesores);
}

public void actualizarTablaCursos(Curso cursoActualizado) {
    
    for (int i = 0; i < cursos.size(); i++) {
        Curso curso = cursos.get(i);
        if (curso.getCodigoCurso() == cursoActualizado.getCodigoCurso()) {
            // Actualiza los datos del profesor
            curso.setNombreCurso(cursoActualizado.getNombreCurso());
            curso.setCreditos(cursoActualizado.getCreditos());
            break;
        }
    }
    
    LlenarTablaCursos(cursos);
}
        
public void generarGraficaEstudiantes() {
    DefaultPieDataset dataset = new DefaultPieDataset();
    List<Estudiante> estudiantes = administrador.getEstudiantes(); 

    int totalEstudiantes = estudiantes.size();
    int masculinos = 0;
    int femeninos = 0;

    // Calcular la cantidad de estudiantes masculinos y femeninos
    for (Estudiante estudiante : estudiantes) {
        String genero = estudiante.getGenero();
        if ("M".equalsIgnoreCase(genero)) {
            masculinos++;
        } else if ("F".equalsIgnoreCase(genero)) {
            femeninos++;
        }
    }

    // Calcular los porcentajes
    double porcentajeMasculinos = (double) masculinos / totalEstudiantes * 100;
    double porcentajeFemeninos = (double) femeninos / totalEstudiantes * 100;

    // Agregar los datos al dataset
    dataset.setValue("Masculinos", porcentajeMasculinos);
    dataset.setValue("Femeninos", porcentajeFemeninos);

    // Crear la gráfica
    JFreeChart chart = ChartFactory.createPieChart(
            "Género de Estudiantes", // Título de la gráfica
            dataset,
            true,
            true,
            false
    );

    // Configurar el formato de porcentaje en números enteros
    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}%", NumberFormat.getIntegerInstance(), NumberFormat.getPercentInstance()));
    plot.setSectionPaint("Masculinos", Color.GREEN); 
    plot.setSectionPaint("Femeninos", Color.MAGENTA); 
    
    // Crear el panel de gráfica y mostrarlo en la ventana
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(443, 337));
    chart3JPanel.setLayout(new java.awt.BorderLayout()); 
    chart3JPanel.add(chartPanel); 
    chart3JPanel.revalidate();

    this.setVisible(true);
    this.validate();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        profesoresJTable = new javax.swing.JTable();
        crearProfesorButton = new javax.swing.JButton();
        actualizarProfesorButton = new javax.swing.JButton();
        cargarProfesorButton = new javax.swing.JButton();
        eliminarProfesorButton = new javax.swing.JButton();
        chartJPanel = new javax.swing.JPanel();
        cerrarSesionButton = new javax.swing.JButton();
        exportarProfesorButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        crearCursoButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cursosJTable = new javax.swing.JTable();
        actualizarCursoButton = new javax.swing.JButton();
        cargarCursoButton = new javax.swing.JButton();
        eliminarCursoButton = new javax.swing.JButton();
        chart2JPanel = new javax.swing.JPanel();
        exportarCursosButton = new javax.swing.JButton();
        closeSesionButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        estudiantesJTable = new javax.swing.JTable();
        cargarAlumnosButton = new javax.swing.JButton();
        exportarAlumnosButton = new javax.swing.JButton();
        chart3JPanel = new javax.swing.JPanel();
        getOutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(255, 187, 92));

        jPanel1.setBackground(new java.awt.Color(255, 187, 92));

        jLabel2.setText("Listado Oficial");

        profesoresJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Apellido", "Correo", "Género"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(profesoresJTable);

        crearProfesorButton.setText("Crear");
        crearProfesorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProfesorButtonActionPerformed(evt);
            }
        });

        actualizarProfesorButton.setText("Actualizar");
        actualizarProfesorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarProfesorButtonActionPerformed(evt);
            }
        });

        cargarProfesorButton.setText("Carga Masiva");
        cargarProfesorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarProfesorButtonActionPerformed(evt);
            }
        });

        eliminarProfesorButton.setText("Eliminar");
        eliminarProfesorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarProfesorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chartJPanelLayout = new javax.swing.GroupLayout(chartJPanel);
        chartJPanel.setLayout(chartJPanelLayout);
        chartJPanelLayout.setHorizontalGroup(
            chartJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartJPanelLayout.setVerticalGroup(
            chartJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        cerrarSesionButton.setText("Cerrar Sesión");
        cerrarSesionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionButtonActionPerformed(evt);
            }
        });

        exportarProfesorButton.setText("Exportar Listado a PDF");
        exportarProfesorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarProfesorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chartJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 59, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(exportarProfesorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(crearProfesorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(cargarProfesorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(actualizarProfesorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(eliminarProfesorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(42, 42, 42)))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cerrarSesionButton)
                        .addGap(15, 15, 15))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cerrarSesionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(crearProfesorButton)
                            .addComponent(cargarProfesorButton))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(actualizarProfesorButton)
                            .addComponent(eliminarProfesorButton))
                        .addGap(18, 18, 18)
                        .addComponent(exportarProfesorButton)
                        .addGap(18, 18, 18)
                        .addComponent(chartJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Profesores", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 187, 92));

        crearCursoButton.setText("Crear");
        crearCursoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearCursoButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Listado Oficial");

        cursosJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Créditos", "Profesor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(cursosJTable);

        actualizarCursoButton.setText("Actualizar");
        actualizarCursoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarCursoButtonActionPerformed(evt);
            }
        });

        cargarCursoButton.setText("Carga Masiva");
        cargarCursoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarCursoButtonActionPerformed(evt);
            }
        });

        eliminarCursoButton.setText("Eliminar");
        eliminarCursoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarCursoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chart2JPanelLayout = new javax.swing.GroupLayout(chart2JPanel);
        chart2JPanel.setLayout(chart2JPanelLayout);
        chart2JPanelLayout.setHorizontalGroup(
            chart2JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chart2JPanelLayout.setVerticalGroup(
            chart2JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        exportarCursosButton.setText("Exportar Listado a PDF");
        exportarCursosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarCursosButtonActionPerformed(evt);
            }
        });

        closeSesionButton.setText("Cerrar Sesión");
        closeSesionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeSesionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeSesionButton)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(crearCursoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(actualizarCursoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(eliminarCursoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cargarCursoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(exportarCursosButton, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                                    .addComponent(chart2JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(67, 67, 67))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(closeSesionButton))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(crearCursoButton)
                            .addComponent(cargarCursoButton))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(actualizarCursoButton)
                            .addComponent(eliminarCursoButton))
                        .addGap(18, 18, 18)
                        .addComponent(exportarCursosButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chart2JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cursos", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 187, 92));

        jLabel1.setText("Listado Oficial");

        estudiantesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Apellido", "Correo", "Género"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(estudiantesJTable);

        cargarAlumnosButton.setText("Carga Masiva");
        cargarAlumnosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarAlumnosButtonActionPerformed(evt);
            }
        });

        exportarAlumnosButton.setText("Exportar Listado a PDF");
        exportarAlumnosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarAlumnosButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chart3JPanelLayout = new javax.swing.GroupLayout(chart3JPanel);
        chart3JPanel.setLayout(chart3JPanelLayout);
        chart3JPanelLayout.setHorizontalGroup(
            chart3JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );
        chart3JPanelLayout.setVerticalGroup(
            chart3JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getOutButton.setText("Cerrar Sesión");
        getOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chart3JPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cargarAlumnosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(exportarAlumnosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(88, 88, 88))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(getOutButton)))
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(getOutButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cargarAlumnosButton)
                        .addGap(18, 18, 18)
                        .addComponent(exportarAlumnosButton)
                        .addGap(18, 18, 18)
                        .addComponent(chart3JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Alumnos", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearProfesorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProfesorButtonActionPerformed
        CrearProfesor crearProfesor = new CrearProfesor(this, profesores); // Pasa la instancia actual de AdministradorMainFrame al constructor de CrearProfesor
        crearProfesor.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_crearProfesorButtonActionPerformed

    private void exportarProfesorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarProfesorButtonActionPerformed
         JFileChooser fileChooser = new JFileChooser();
    int seleccion = fileChooser.showSaveDialog(this);

    if (seleccion == JFileChooser.APPROVE_OPTION) {
        // Obtener la ubicación seleccionada por el usuario
        File selectedFile = fileChooser.getSelectedFile();
        String filePath = selectedFile.getAbsolutePath();

        // Verificar si el nombre del archivo termina con ".pdf"
        if (!filePath.toLowerCase().endsWith(".pdf")) {
            // Si no termina con ".pdf", agregarlo al nombre del archivo
            filePath += ".pdf";
        }

        // Intentar crear y escribir en el archivo PDF
        try {
            // Crear un documento PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Abrir el documento para escritura
            document.open();

            // Configurar fuente y tamaño para el contenido
            Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            // Agregar título al documento
            Paragraph title = new Paragraph("Listado de Profesores", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            // Agregar contenido (lista de profesores) al documento
            for (Profesor profesor : profesores) {
                Paragraph paragraph = new Paragraph(
                    "Código: " + profesor.getCodigo() + "\n" +
                    "Nombre: " + profesor.getNombre() + "\n" +
                    "Apellido: " + profesor.getApellido() + "\n" +
                    "Correo: " + profesor.getCorreo() + "\n" +
                    "Género: " + profesor.getGenero() + "\n\n",
                    font
                );
                document.add(paragraph);
            }

            // Cerrar el documento
            document.close();

            JOptionPane.showMessageDialog(this, "El listado de profesores se ha exportado exitosamente a un archivo PDF.");
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error al exportar el listado de profesores a un archivo PDF.");
        }
    }
    }//GEN-LAST:event_exportarProfesorButtonActionPerformed

    private void cargarProfesorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarProfesorButtonActionPerformed
      JFileChooser fileChooser = new JFileChooser();
    fileChooser.setMultiSelectionEnabled(true);
    FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("Archivos CSV", "csv");
    fileChooser.setFileFilter(csvFilter);

    int fileChooserState = fileChooser.showOpenDialog(this);
    if (fileChooserState == JFileChooser.APPROVE_OPTION){
        File[] archivos = fileChooser.getSelectedFiles();

        String passwordPorDefecto = "1234";
        
        for (File file: archivos) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String linea = "";

                // Leer y descartar la primera línea (encabezados)
                reader.readLine();

                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");

                    if (datos.length >= 5) {
                        int codigo = Integer.parseInt(datos[0]);
                        String nombre = datos[1];
                        String apellido = datos[2];
                        String correo = datos[3];
                        String genero = datos[4];
                        
                        // Asignar contraseña predeterminada
                        String passwordPersona = (datos.length >= 6) ? datos[5] : passwordPorDefecto;
                    
                        this.agregarProfesor(codigo, nombre, apellido, correo, genero, passwordPersona, "1234");
                        } else {
                        System.out.println("Fila incompleta: " + linea);
                        }
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error cargando profesores");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Error al convertir cadena a número");
            }
        }
        
        this.LlenarTablaProfesores(this.profesores);
        this.generarGrafica();
    }
    }//GEN-LAST:event_cargarProfesorButtonActionPerformed

    private void actualizarProfesorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarProfesorButtonActionPerformed
         int filaSeleccionada = profesoresJTable.getSelectedRow();

    if (filaSeleccionada >= 0) {
        int codigoProfesor = (int) profesoresJTable.getValueAt(filaSeleccionada, 0);
        Profesor profesorAActualizar = null;

        for (Profesor prof : profesores) {
            if (prof.getCodigo() == codigoProfesor) {
                profesorAActualizar = prof;
                break;
            }
        }

        if (profesorAActualizar != null) {
            // Crear una instancia de ActualizarProfesor y pasar la instancia de AdministradorMainFrame y la lista de profesores
            ActualizarProfesor ventanaActualizar = new ActualizarProfesor(this, profesores, profesorAActualizar);
            ventanaActualizar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el profesor seleccionado en la lista.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un profesor de la tabla para actualizar.");
    }
    }//GEN-LAST:event_actualizarProfesorButtonActionPerformed

    private void eliminarProfesorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarProfesorButtonActionPerformed
        int filaSeleccionada = profesoresJTable.getSelectedRow();

    if (filaSeleccionada == -1) {
        // Si no se ha seleccionado ninguna fila, muestra un mensaje de error
        JOptionPane.showMessageDialog(this, "Seleccione un profesor para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        // Obtiene el código del profesor seleccionado en la tabla
        int codigoProfesor = (int) profesoresJTable.getValueAt(filaSeleccionada, 0);

        // Ventana de confirmación
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este profesor?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            // Itera sobre la lista de profesores y elimina el que tenga el código seleccionado
            for (Iterator<Profesor> iterator = profesores.iterator(); iterator.hasNext();) {
                Profesor profesor = iterator.next();
                if (profesor.getCodigo() == codigoProfesor) {
                    iterator.remove();
                    eliminacionConfirmada = true; // La eliminación se confirmó
                    break; // Termina el bucle una vez eliminado el profesor
                }
            }

            // Vuelve a cargar la tabla y actualizar la gráfica
            LlenarTablaProfesores(profesores);
            generarGrafica();

            // Muestra un mensaje de éxito
            JOptionPane.showMessageDialog(this, "Profesor eliminado correctamente.");
        } else {
            // La eliminación no se confirmó
            eliminacionConfirmada = false;
        }
    }
    }//GEN-LAST:event_eliminarProfesorButtonActionPerformed

    private void crearCursoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearCursoButtonActionPerformed
    AgregarCurso agregarCurso = new AgregarCurso(this, cursos); // Pasa la instancia actual de AdministradorMainFrame al constructor de AgregarCurso
    agregarCurso.setVisible(true);
    
    }//GEN-LAST:event_crearCursoButtonActionPerformed

    private void cargarCursoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarCursoButtonActionPerformed
    JFileChooser fileChooser = new JFileChooser();
fileChooser.setMultiSelectionEnabled(true);
FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("Archivos CSV", "csv");
fileChooser.setFileFilter(csvFilter);

int fileChooserState = fileChooser.showOpenDialog(this);
if (fileChooserState == JFileChooser.APPROVE_OPTION){
    File[] archivos = fileChooser.getSelectedFiles();
    for (File file: archivos) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linea = "";

            // Leer y descartar la primera línea (encabezados)
            reader.readLine();

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 4) {
                    int codigoCurso = Integer.parseInt(datos[0]);
                    String nombreCurso = datos[1];
                    int creditos = Integer.parseInt(datos[2]);
                    int codigoProfesor = Integer.parseInt(datos[3]);

                    // Buscar al profesor por su código y asignarlo al curso
                    Profesor profesorAsignado = buscarProfesorPorCodigo(codigoProfesor);
                    if (profesorAsignado != null) {
                        Curso curso = new Curso(codigoCurso, nombreCurso, creditos, profesorAsignado);
                        cursos.add(curso); // Agregar el curso a la lista de cursos
                    } else {
                        System.out.println("Profesor no encontrado para el código: " + codigoProfesor);
                    }
                } else {
                    System.out.println("Fila incompleta: " + linea);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error cargando cursos");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Error al convertir cadena a número");
        }
    }
    this.LlenarTablaCursos(this.cursos);

}
    }

// Método para buscar un profesor por su código
private Profesor buscarProfesorPorCodigo(int codigoProfesor) {
    for (Profesor profesor : this.profesores) {
        if (profesor.getCodigo() == codigoProfesor) {
            return profesor; // Se encontró el profesor
        }
    }
    return null; // No se encontró el profesor


    }//GEN-LAST:event_cargarCursoButtonActionPerformed

    private void actualizarCursoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarCursoButtonActionPerformed
       int filaSeleccionada = cursosJTable.getSelectedRow();

    if (filaSeleccionada >= 0) {
        int codigoCurso = (int) cursosJTable.getValueAt(filaSeleccionada, 0);
        Curso cursoAActualizar = null;

        for (Curso curso : cursos) {
            if (curso.getCodigoCurso() == codigoCurso) {
                cursoAActualizar = curso;
                break;
            }
        }

        if (cursoAActualizar != null) {
            // Crear una instancia de ActualizarCurso y pasar la instancia de AdministradorMainFrame y la lista de cursos
            ActualizarCurso ventanaActualizarCurso = new ActualizarCurso(this, cursos, cursoAActualizar);
            ventanaActualizarCurso.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el curso seleccionado en la lista.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un curso de la tabla para actualizar.");
    }
                                    
    }//GEN-LAST:event_actualizarCursoButtonActionPerformed

    private void exportarCursosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarCursosButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
int seleccion = fileChooser.showSaveDialog(this);

if (seleccion == JFileChooser.APPROVE_OPTION) {
    // Obtener la ubicación seleccionada por el usuario
    File selectedFile = fileChooser.getSelectedFile();
    String filePath = selectedFile.getAbsolutePath();

    // Verificar si el nombre del archivo termina con ".pdf"
    if (!filePath.toLowerCase().endsWith(".pdf")) {
        // Si no termina con ".pdf", agregarlo al nombre del archivo
        filePath += ".pdf";
    }

    // Intentar crear y escribir en el archivo PDF
    try {
        // Crear un documento PDF
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        // Abrir el documento para escritura
        document.open();

        // Configurar fuente y tamaño para el contenido
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

        // Agregar título al documento
        Paragraph title = new Paragraph("Listado de Cursos", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        // Agregar contenido (lista de cursos) al documento
        for (Curso curso : cursos) {
            // Mostrar nombre del curso y otros detalles
            Paragraph paragraph = new Paragraph(
                
                "Código del Curso: " + curso.getCodigoCurso() + "\n" +
                "Nombre del Curso: " + curso.getNombreCurso() + "\n" +
                "Créditos: " + curso.getCreditos() + "\n" +
                "Profesor: " + curso.getProfesor().getNombre() + " " + curso.getProfesor().getApellido() + "\n\n",
                font
            );
            document.add(paragraph);
        }

        // Cerrar el documento
        document.close();

        JOptionPane.showMessageDialog(this, "El listado de cursos se ha exportado exitosamente a un archivo PDF.");
    } catch (DocumentException | IOException e) {
        JOptionPane.showMessageDialog(this, "Error al exportar el listado de cursos a un archivo PDF.");
    }
}
    }//GEN-LAST:event_exportarCursosButtonActionPerformed

    private void cargarAlumnosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarAlumnosButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setMultiSelectionEnabled(true);
    FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("Archivos CSV", "csv");
    fileChooser.setFileFilter(csvFilter);

    int fileChooserState = fileChooser.showOpenDialog(this);
    if (fileChooserState == JFileChooser.APPROVE_OPTION) {
        File[] archivos = fileChooser.getSelectedFiles();
        for (File file : archivos) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String linea = "";

                // Leer y descartar la primera línea (encabezados)
                reader.readLine();

                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");

                    if (datos.length >= 5) {
                        Estudiante estudiante = new Estudiante(
                   Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        datos[3],
                        datos[4],
            "1234" // Contraseña predeterminada
                        );

                        
                        estudiantes.add(estudiante); // Agregar el estudiante a la lista
                    } else {
                        System.out.println("Fila incompleta: " + linea);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error cargando estudiantes");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Error al convertir cadena a número");
            }
        }
        this.LlenarTablaEstudiantes(this.estudiantes);
        generarGraficaEstudiantes();
    }
    }//GEN-LAST:event_cargarAlumnosButtonActionPerformed

    private void exportarAlumnosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarAlumnosButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
    int seleccion = fileChooser.showSaveDialog(this);

    if (seleccion == JFileChooser.APPROVE_OPTION) {
        // Obtener la ubicación seleccionada por el usuario
        File selectedFile = fileChooser.getSelectedFile();
        String filePath = selectedFile.getAbsolutePath();

        // Verificar si el nombre del archivo termina con ".pdf"
        if (!filePath.toLowerCase().endsWith(".pdf")) {
            // Si no termina con ".pdf", agregarlo al nombre del archivo
            filePath += ".pdf";
        }

        // Intentar crear y escribir en el archivo PDF
        try {
            // Crear un documento PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Abrir el documento para escritura
            document.open();

            // Configurar fuente y tamaño para el contenido
            Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            // Agregar título al documento
            Paragraph title = new Paragraph("Listado de Alumnos", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            // Agregar contenido (lista de profesores) al documento
            for (Estudiante estudiante: estudiantes) {
                Paragraph paragraph = new Paragraph(
                    "Código: " + estudiante.getCodigo() + "\n" +
                    "Nombre: " + estudiante.getNombre() + "\n" +
                    "Apellido: " + estudiante.getApellido() + "\n" +
                    "Correo: " + estudiante.getCorreo() + "\n" +
                    "Género: " + estudiante.getGenero() + "\n\n",
                    font
                );
                document.add(paragraph);
            }

            // Cerrar el documento
            document.close();

            JOptionPane.showMessageDialog(this, "El listado de alumnos se ha exportado exitosamente a un archivo PDF.");
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error al exportar el listado de profesores a un archivo PDF.");
        }
    }
    }//GEN-LAST:event_exportarAlumnosButtonActionPerformed

    private void cerrarSesionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionButtonActionPerformed
        this.dispose();
        LoginJFrame loginJFrame = new LoginJFrame();
        loginJFrame.setVisible(true);
    }//GEN-LAST:event_cerrarSesionButtonActionPerformed

    private void closeSesionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeSesionButtonActionPerformed
        this.dispose();
        LoginJFrame loginJFrame = new LoginJFrame();
        loginJFrame.setVisible(true);
    }//GEN-LAST:event_closeSesionButtonActionPerformed

    private void getOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getOutButtonActionPerformed
        this.dispose();
        LoginJFrame loginJFrame = new LoginJFrame();
        loginJFrame.setVisible(true);
    }//GEN-LAST:event_getOutButtonActionPerformed

    private void eliminarCursoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarCursoButtonActionPerformed
       int filaSeleccionada = cursosJTable.getSelectedRow();

    if (filaSeleccionada == -1) {
        // Si no se ha seleccionado ninguna fila, muestra un mensaje de error
        JOptionPane.showMessageDialog(this, "Seleccione un curso para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        // Obtiene el código del curso seleccionado en la tabla
        int codigoCurso = (int) cursosJTable.getValueAt(filaSeleccionada, 0);

        // Ventana de confirmación
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este curso?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            
            for (Iterator<Curso> iterator = cursos.iterator(); iterator.hasNext();) {
                Curso curso = iterator.next();
                if (curso.getCodigoCurso() == codigoCurso) {
                    iterator.remove();
                    eliminacionConfirmada = true; // La eliminación se confirmó
                    break; 
                }
            }

            // Vuelve a cargar la tabla y actualizar la gráfica
            LlenarTablaCursos(cursos);
           

            // Muestra un mensaje de éxito
            JOptionPane.showMessageDialog(this, "Profesor eliminado correctamente.");
        } else {
            // La eliminación no se confirmó
            eliminacionConfirmada = false;
        }
    }
    }//GEN-LAST:event_eliminarCursoButtonActionPerformed
    

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
            java.util.logging.Logger.getLogger(AdministradorMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministradorMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministradorMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministradorMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton actualizarCursoButton;
    private javax.swing.JButton actualizarProfesorButton;
    private javax.swing.JButton cargarAlumnosButton;
    private javax.swing.JButton cargarCursoButton;
    private javax.swing.JButton cargarProfesorButton;
    private javax.swing.JButton cerrarSesionButton;
    private javax.swing.JPanel chart2JPanel;
    private javax.swing.JPanel chart3JPanel;
    private javax.swing.JPanel chartJPanel;
    private javax.swing.JButton closeSesionButton;
    private javax.swing.JButton crearCursoButton;
    private javax.swing.JButton crearProfesorButton;
    private javax.swing.JTable cursosJTable;
    private javax.swing.JButton eliminarCursoButton;
    private javax.swing.JButton eliminarProfesorButton;
    private javax.swing.JTable estudiantesJTable;
    private javax.swing.JButton exportarAlumnosButton;
    private javax.swing.JButton exportarCursosButton;
    private javax.swing.JButton exportarProfesorButton;
    private javax.swing.JButton getOutButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable profesoresJTable;
    // End of variables declaration//GEN-END:variables

    
}
