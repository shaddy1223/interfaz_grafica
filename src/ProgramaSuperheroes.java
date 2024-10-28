import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ProgramaSuperheroes {
    private JFrame ventanaPrincipal;
    private JFrame ventanaListado;
    private JFrame ventanaFormulario;

    public ProgramaSuperheroes() {
        crearVentanaPrincipal();
    }

    private void crearVentanaPrincipal() {
        ventanaPrincipal = new JFrame("Marvel Superhéroes");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(800, 600);
        ventanaPrincipal.setLayout(new BorderLayout());

        // Panel principal con fondo negro
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.BLACK);

        // Título estilo Marvel
        JLabel titulo = new JLabel("PROGRAMA DE SUPERHÉROES");
        titulo.setFont(new Font("Impact", Font.BOLD, 48));
        titulo.setForeground(Color.RED);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón estilo Marvel
        JButton btnEntrar = new JButton("ENTRAR AL UNIVERSO MARVEL");
        btnEntrar.setFont(new Font("Impact", Font.BOLD, 24));
        btnEntrar.setBackground(Color.RED);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.setMaximumSize(new Dimension(400, 50));

        btnEntrar.addActionListener(e -> mostrarVentanaListado());

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titulo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(btnEntrar);
        mainPanel.add(Box.createVerticalGlue());

        ventanaPrincipal.add(mainPanel);
        ventanaPrincipal.setVisible(true);
    }

    private void mostrarVentanaListado() {
        ventanaListado = new JFrame("Listado de Héroes");
        ventanaListado.setSize(800, 600);
        ventanaListado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);

        // Título
        JLabel titulo = new JLabel("HÉROES Y ANTIHÉROES REGISTRADOS");
        titulo.setFont(new Font("Impact", Font.BOLD, 36));
        titulo.setForeground(Color.RED);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel de botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(Color.BLACK);

        JButton btnAnadir = crearBotonMarvel("AÑADIR HÉROE");
        JButton btnEliminar = crearBotonMarvel("ELIMINAR HÉROE");
        JButton btnSalir = crearBotonMarvel("SALIR");

        btnAnadir.addActionListener(e -> mostrarFormularioHeroe());
        btnEliminar.addActionListener(e -> JOptionPane.showMessageDialog(ventanaListado, "Función de eliminar"));
        btnSalir.addActionListener(e -> System.exit(0));

        botonesPanel.add(btnAnadir);
        botonesPanel.add(btnEliminar);
        botonesPanel.add(btnSalir);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(new JScrollPane(new JList<>()), BorderLayout.CENTER);
        panel.add(botonesPanel, BorderLayout.SOUTH);

        ventanaListado.add(panel);
        ventanaListado.setVisible(true);
        ventanaPrincipal.setVisible(false);
    }

    private void mostrarFormularioHeroe() {
        ventanaFormulario = new JFrame("Añadir Héroe");
        ventanaFormulario.setSize(800, 800);
        ventanaFormulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Componentes del formulario
        JButton btnImagen = new JButton("Seleccionar Imagen");
        JLabel imagenLabel = new JLabel();
        imagenLabel.setPreferredSize(new Dimension(200, 200));
        imagenLabel.setBorder(BorderFactory.createLineBorder(Color.RED));

        btnImagen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(ventanaFormulario) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                imagenLabel.setIcon(new ImageIcon(file.getPath()));
            }
        });

        // Campos del formulario
        JTextField txtEdad = new JTextField(20);
        JTextArea txtDescripcion = new JTextArea(5, 20);
        JTextField txtFrase = new JTextField(20);
        JTextField txtPoder = new JTextField(20);

        // Añadir componentes con GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Imagen:"), gbc);

        gbc.gridx = 1;
        panel.add(btnImagen, gbc);

        gbc.gridy = 1;
        gbc.gridheight = 2;
        panel.add(imagenLabel, gbc);

        gbc.gridheight = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        panel.add(txtEdad, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(txtDescripcion), gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(new JLabel("Frase:"), gbc);
        gbc.gridx = 1;
        panel.add(txtFrase, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        panel.add(new JLabel("Poder:"), gbc);
        gbc.gridx = 1;
        panel.add(txtPoder, gbc);

        // Botón Guardar
        JButton btnGuardar = crearBotonMarvel("GUARDAR");
        gbc.gridy = 7;
        gbc.gridx = 1;
        panel.add(btnGuardar, gbc);

        ventanaFormulario.add(new JScrollPane(panel));
        ventanaFormulario.setVisible(true);
    }

    private JButton crearBotonMarvel(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Impact", Font.BOLD, 18));
        boton.setBackground(Color.RED);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProgramaSuperheroes());
    }
}