import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class MainController extends JFrame{
    MainController(){
        setSize(600,600);
        setLocationRelativeTo(null);
        setTitle("Main Controller");
        setVisible(true);
    }
}

class Demo{
    public static void main(String args[]){
        MainController m1=new MainController();
    }
}
