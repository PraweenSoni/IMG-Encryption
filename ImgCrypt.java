import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImgCrypt {

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

    public static void main(String[] args) {
        System.out.println("Your app started!");

        // Set up the JFrame
        JFrame f = new JFrame();
        f.setTitle("Image Encryption/Decryption");
        f.setSize(500, 300);  // Adjusted frame size
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLayout(new BorderLayout());

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
        instructions.setOpaque(true);
        instructions.setBackground(Color.WHITE);
        instructions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        f.add(instructions, BorderLayout.NORTH);

        // Add button and text field inside a panel for better control
        JPanel bottomPanel = new JPanel(new FlowLayout());
        Font font = new Font("Roboto", Font.BOLD, 18);

        JTextField txtField = new JTextField(10);
        txtField.setFont(font);

        JButton button = new JButton("Open Image");
        button.setFont(font);
        button.addActionListener(e -> {
            String text = txtField.getText();
            try {
                int key = Integer.parseInt(text);
                operate(key);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(f, "Please enter a valid numeric key!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        bottomPanel.add(new JLabel("Enter Key:"));
        bottomPanel.add(txtField);
        bottomPanel.add(button);
        f.add(bottomPanel, BorderLayout.CENTER);

        // Add footer at the bottom
        JLabel footer = new JLabel("Copyright 2024 by Pks.", SwingConstants.CENTER);
        footer.setFont(new Font("Roboto", Font.PLAIN, 12));
        footer.setOpaque(true);
        footer.setBackground(Color.LIGHT_GRAY);
        footer.setPreferredSize(new Dimension(500, 30));  // Set proper size for footer
        f.add(footer, BorderLayout.PAGE_END);  // Add footer separately

        // Make the frame visible
        f.setVisible(true);
    }
}
