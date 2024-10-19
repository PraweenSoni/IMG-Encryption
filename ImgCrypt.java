import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImgCrypt {

    public static void operate(int key){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        // file input stream stream
        try{
            FileInputStream fis = new FileInputStream(file);
            byte []data=new byte[fis.available()];
            fis.read(data);
            int i=0;
            for(byte b:data){
                data[i]=(byte)(b^key);
                i++;
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done..!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        System.out.print("TEsting");
        JFrame f=new JFrame();
        f.setTitle("Image Operation");
        f.setSize(500,200);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font=new Font("Roboto", Font.BOLD, 25);
        // Creating Button
        JButton button = new JButton();
        button.setText("Open Image");
        // JButton button - ImageOperation.main(String[]);
        button.setFont(font);

        // Creating text field
        JTextField txtField=new JTextField(10);
        txtField.setFont(font);

        button.addActionListener(e->{
            // System.out.print("hisd");
            String text = txtField.getText();
            int temp = Integer.parseInt(text);
            operate(temp);
        });

        f.setLayout(new FlowLayout());

        // Adding components to the frame
        f.add(button);
        f.add(txtField);
        f.setVisible(true);
    }
}