import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

interface Observables{
    public void setLabel(String x);
    public void setBtns(int sliderVal);
    public void setOthersMessages(String id,String Message);
}
class Helicopter extends JFrame implements Observables{
    JLabel lbl1;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    JTextArea area1;
    JTextField txtfield;
    JButton btnSend;
    Helicopter(){
        setSize(300,300);
        setLocation(0,0);
        setTitle("Helicopter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1));
        
        JPanel panel1=new JPanel(new GridLayout(1,2));
        lbl1=new JLabel("");
        panel1.add(lbl1);
        
        
        JPanel panel2=new JPanel(new GridLayout(1,2));
        JPanel panelx=new JPanel(new FlowLayout());
        
        btn1=new JButton("Shooting");
        btn1.setEnabled(false);
        btn2=new JButton("missile operations");
        btn2.setEnabled(false);
        btn3=new JButton("laser operations");
        btn3.setEnabled(false);
        
        panelx.add(btn1);
        panelx.add(btn2);
        panelx.add(btn3);
        
        panel2.add(panelx);
        
        JPanel panel3=new JPanel(new FlowLayout());
        area1=new JTextArea(5,20);
        JScrollPane scroll=new JScrollPane(area1);
        scroll.setBounds(50,50,200,100);
        
        panel3.add(scroll);
        JPanel panel4=new JPanel(new FlowLayout());
        txtfield=new JTextField(15);
        
        btnSend=new JButton("Send");
        
        panel4.add(txtfield);
        panel4.add(btnSend);
        
        
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        
        
        setVisible(true);
    }
    public void setLabel(String x){
        lbl1.setText(x);
        setVisible(true);
    }
    public void setBtns(int sliderVal){
        if(sliderVal<=20){
            btn1.setEnabled(true);
        }else if(sliderVal<=40){
            btn1.setEnabled(true);
            btn2.setEnabled(true);
        }else if(sliderVal>40){
            btn1.setEnabled(true);
            btn2.setEnabled(true);
            btn3.setEnabled(true);
        }
    }
    
    public void setOthersMessages(String id,String message){
        area1.setText(id+" : "+message);
    }
    
    
}
class Tank{
    Tank(){
        
    }
}
class Submarine{
    Submarine(){
        
    }
}
class MainController extends JFrame{
    JButton btn1;
    JButton btn2;
    JCheckBox allClearcheckBox;
    JCheckBox checkBox1;
    JButton btnSend;
    
    Observables[] observables=new Observables[0];
    MainController(){
        setSize(450,450);
        setLocationRelativeTo(null);
        setTitle("Main Controller");
        setLayout(new GridLayout(5,1));
        
        JPanel panel5=new JPanel(new FlowLayout());
        JLabel labely=new JLabel("Main Controller",JLabel.CENTER);
        labely.setFont(new Font("",Font.BOLD,25));
        panel5.add(labely);
        JPanel panel1=new JPanel(new GridLayout(1,3));
        JPanel pan1=new JPanel(new FlowLayout());
        JPanel pan2=new JPanel(new FlowLayout());
        btn1=new JButton("Select Defence");
        btn2=new JButton("Select Defence");
        
        pan1.add(btn1);
        pan2.add(btn2);
        allClearcheckBox=new JCheckBox("Area Cleared");
        
        panel1.add(pan1);
        panel1.add(pan2);
        panel1.add(allClearcheckBox);
        JPanel panel2=new JPanel(new GridLayout(1,2));
        JPanel x=new JPanel(new GridLayout(3,1));
        JPanel y=new JPanel(new GridLayout(2,1));
        JLabel label1=new JLabel("Soldier Count");
        JLabel label2=new JLabel("Fuel Amout");
        JLabel label3=new JLabel("Ammo Amount");
        
        x.add(label1);
        x.add(label2);
        x.add(label3);
        JPanel z=new JPanel(new GridLayout(2,1));
        JTextArea area1=new JTextArea(5,20);
        JScrollPane scrollPane=new JScrollPane(area1);
        scrollPane.setBounds(50,50,200,100);
        panel2.add(x);
        
        JPanel panex=new JPanel(new FlowLayout());
        checkBox1=new JCheckBox("Send Private");
        
       
        z.add(scrollPane);
        
        
        btnSend=new JButton("Send");
        
        panex.add(checkBox1);
        panex.add(btnSend);
        z.add(panex);
        panel2.add(z);
        
        JPanel panel3=new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JLabel labelx=new JLabel("position");
        JSlider slider=new JSlider(JSlider.HORIZONTAL,0,100,0);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        panel3.add(labelx);
        panel3.add(slider);
        
        JPanel panel4=new JPanel();
        JTextArea are2=new JTextArea(5,20);
        JScrollPane scrollPane1=new JScrollPane(are2);
        scrollPane1.setBounds(50,50,200,100);
        panel4.add(scrollPane1);
        
        add(panel5);
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        
        allClearcheckBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(allClearcheckBox.isSelected()){
                    checkAllClearcheckBox("Area Cleared");
                }else{
                    checkAllClearcheckBox("Area Not Cleared");
                }
            }
        });
        
        slider.addChangeListener(event->{
            if(slider.getValueIsAdjusting()){
                sendTheValueForBTNs(slider.getValue());
            }
        });
        
        btnSend.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                String message = area1.getText();
                sentToOthers(message);
                System.out.println(message);
            }
        });
        
        
        
        
    
        setVisible(true);
    }
    public void extendArray(){
        if(observables.length==0){
            Observables[] temp=new Observables[observables.length+1];
            observables=temp;
        }else if(observables.length>0){
            Observables[] temp=new Observables[observables.length+1];
            
            for(int i=0;i<observables.length;i++){
                temp[i]=observables[i];
            }
            observables=temp;
        }
    }
    public void addObservable(Observables observer){
        extendArray();
        observables[observables.length-1]=observer;
    }
    
    public void checkAllClearcheckBox(String val){
        
        for(Observables ob:observables){
            ob.setLabel(val);
        }
    }
    public void sendTheValueForBTNs(int sliderVal){
        for(Observables ob:observables){
            ob.setBtns(sliderVal);
        }
    }
    public void sentToOthers(String message){
        for(Observables ob:observables){
            ob.setOthersMessages("MainContoller",message);
        }
    }
}

class Demo{
    public static void main(String args[]){
        MainController m1=new MainController();
        
        m1.addObservable(new Helicopter());
    }
}
