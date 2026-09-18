import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImgCrypt {

    // Method to perform encryption/decryption
    public static void operate(int key) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image File");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[fis.available()];
                fis.read(data);
                int i = 0;
                for (byte b : data) {
                    data[i] = (byte) (b ^ key);
                    i++;
                }
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                fos.close();
                fis.close();
                JOptionPane.showMessageDialog(null, "Operation completed successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No file selected!");
        }
    }

    // Create main panel with layout
    private static JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Stack components vertically

        // Add instructions
        JLabel instructions = new JLabel("<html>"
                + "<p><b>Instructions:</b></p>"
                + "<ol>"
                + "<li>Set your Encryption/Decryption key in the text box.</li>"
                + "<li>Click the 'Open Image' button and select your image file.</li>"
                + "<li>Congratulations! Your image will be Encrypted/Decrypted.</li>"
                + "</ol>"
                + "</html>");
        instructions.setFont(new Font("Roboto", Font.PLAIN, 14));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center align
        mainPanel.add(instructions);

        // Add key input and button panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField txtField = new JTextField(10);
        txtField.setFont(new Font("Roboto", Font.BOLD, 18));

        JButton button = new JButton("Open Image");
        button.setFont(new Font("Roboto", Font.BOLD, 18));
        button.addActionListener(e -> {
            String text = txtField.getText();
            try {
                int key = Integer.parseInt(text);
                operate(key);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Please enter a valid numeric key!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        inputPanel.add(new JLabel("Enter Key:"));
        inputPanel.add(txtField);
        inputPanel.add(button);

        mainPanel.add(inputPanel);

        return mainPanel;
    }

    // Create footer panel
    private static JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel footer = new JLabel("Copyright 2024 by Pks.");
        footer.setFont(new Font("Roboto", Font.PLAIN, 12));
        footer.setBackground(Color.LIGHT_GRAY);
        footerPanel.add(footer);
        return footerPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Encryption/Decryption");
        frame.setSize(500, 300);  // Adjusted frame size for scalability
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        // Main content
        JPanel mainPanel = createMainPanel();
        frame.add(mainPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = createFooterPanel();
        frame.add(footerPanel, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
