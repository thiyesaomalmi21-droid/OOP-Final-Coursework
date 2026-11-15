import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Message{
    String id;
    String message;
    
    Message(String id,String message){
        this.id=id;
        this.message=message;
    }
}
interface Observables{
    
    public void setLabel(String x);
    public void setBtns(Strength currentStrength);
    public void setOthersMessages(Message obJMessage);
    public String getId();
    public void setCounts(int soldier,int Ammo);
    public int getammocount();
    public int getsoldier();
    
}
enum Strength{
    LOW, MEDIUM, HIGH, STRONG, CLOSED;
}

class Helicopter extends JFrame implements Observables{
    JLabel lbl1;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    JTextArea area1;
    
    JTextField txtfield;
    JButton btnSend;
    JScrollPane scroll;
    JCheckBox CBox;
    SpinnerNumberModel model;
    JSpinner spinner1 ;
    JSlider energySlider;
    
    private String boxClicked="";
    private String id;
    private int soldierCount;
    private int ammoCount;
    
    private Message[] messArray=new Message[0];
    Helicopter(){
        this.id="Helicopter";
        
        setSize(400,400);
        setLocation(0,0);
        setTitle("Helicopter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(5,1));
        
        JPanel panel1=new JPanel(new GridLayout(1,2));
        lbl1=new JLabel("Area Not Cleared");
        
        
        JPanel x=new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        model = new SpinnerNumberModel(1000, 0, 1000, 1);
        spinner1 = new JSpinner(model);
        JLabel lbl =new JLabel("Soldier Count");
        lbl.setFont(new Font("",1,12));
        
        SpinnerNumberModel model1 = new SpinnerNumberModel(1000, 0, 1000, 1);
        JSpinner spinner2 = new JSpinner(model1);
        JLabel lbl2 =new JLabel("Ammo Count");
        lbl.setFont(new Font("",1,10));
        
        x.add(lbl);
        x.add(spinner1);
        x.add(lbl2);
        x.add(spinner2);
        
        panel1.add(lbl1);
        panel1.add(x);
        
        
        JPanel panel2=new JPanel(new GridLayout(1,2));
        JPanel panelx=new JPanel(new GridLayout(2,2));
        JPanel panely=new JPanel(new GridLayout(2,1));
        CBox=new JCheckBox("Position");
        JLabel label1=new JLabel("");
        
        panely.add(label1);
        panely.add(CBox);
        
        JPanel p1=new JPanel(new FlowLayout());
        JPanel p2=new JPanel(new FlowLayout());
        JPanel p3=new JPanel(new FlowLayout());
        
        btn1=new JButton("Shooting");
        btn1.setBounds(50, 50, 120, 40);
        btn1.setEnabled(false);
        btn2=new JButton("Missile Op");
        btn2.setBounds(50, 100, 120, 40);
        btn2.setEnabled(false);
        btn3=new JButton("Laser Op");
        btn3.setBounds(50, 100, 120, 40);
        btn3.setEnabled(false);
        
        p1.add(btn1);
        p2.add(btn2);
        p3.add(btn3);
        
        panelx.add(p1);
        panelx.add(p2);
        panelx.add(p3);
        
        panel2.add(panelx);
        panel2.add(panely);
        
        JPanel panel3=new JPanel(new FlowLayout());
        area1=new JTextArea(5,20);
        scroll=new JScrollPane(area1);
        scroll.setBounds(50,50,200,100);
        
        panel3.add(scroll);
        JPanel panel4=new JPanel(new FlowLayout());
        txtfield=new JTextField(15);
        
        btnSend=new JButton("Send");
        
        panel4.add(txtfield);
        panel4.add(btnSend);
        
        JPanel panel5=new JPanel();
        JLabel energylbl=new JLabel("Energy");
        energylbl.setFont(new Font("",Font.BOLD,13));
        energySlider=new JSlider(JSlider.HORIZONTAL,0,100,0);
        energySlider.setMajorTickSpacing(20);
        energySlider.setMinorTickSpacing(20);
        energySlider.setPaintTicks(true);
        energySlider.setPaintLabels(true);
        
        
        panel5.add(energylbl);
        panel5.add(energySlider);
        
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        
        btnSend.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                String message=txtfield.getText();
                Message objMess=new Message("Helicopter",message);
                sentToContoller(objMess);
                setOthersMessages(objMess);
                
                txtfield.setText("");
            }
        });
        
        CBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(CBox.isSelected()){
                    boxClicked="true";
                }else{
                    boxClicked="false";
                }
            }
        });
        
        btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                String currentVal=spinner2.getValue().toString();
                int currentVal1=Integer.parseInt(spinner2.getValue().toString())-1;
                spinner2.setValue(currentVal1);
                ammoCount=currentVal1;
                decreaseTheEnergySlider();
            }
        });
        btn2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                decreaseTheEnergySlider();
            }
        });
        btn3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                decreaseTheEnergySlider();
            }
        });
        
        
        setVisible(true);
    }
    public void decreaseTheEnergySlider(){
        int sliderVal=energySlider.getValue();
        energySlider.setValue(sliderVal-1);
        
    }
    public void setLabel(String x){
        lbl1.setText(x);
        setVisible(true);
    }
    
    
    
    public void setBtns(Strength currentStrength){
        if(boxClicked.equals("true")){
            if(currentStrength==Strength.LOW){
                btn1.setEnabled(true);
                energySlider.setValue(20);
            }else if(currentStrength==Strength.MEDIUM){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                energySlider.setValue(60);
            }else if(currentStrength==Strength.HIGH || currentStrength==Strength.STRONG){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                energySlider.setValue(100);
            }
        }else{
            if(currentStrength==Strength.LOW || currentStrength==Strength.MEDIUM || currentStrength==Strength.HIGH || currentStrength==Strength.STRONG){
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
            }
        } 
        
        
    }
    
    public void extendMessageArray(){
        if(messArray.length==0){
            Message[] temp=new Message[messArray.length+1];
            messArray=temp;
        }else if(messArray.length>0){
            Message[] temp=new Message[messArray.length+1];
            for(int i=0;i<messArray.length;i++){
                temp[i]=messArray[i];
            }
            messArray=temp;
        }
    }
    
    public void setOthersMessages(Message OBJmessage){
       
        extendMessageArray();
        messArray[messArray.length-1]=OBJmessage;
        setTextINArea();
        
    }
    public void setTextINArea(){
        area1.setText("");
        String text="";
        for(int i=0;i<messArray.length;i++){
            String id=messArray[i].id;
            if(id.equals("Helicopter")){
                text+=("You : "+messArray[i].message+"\n");
            }else{
               text+=(id+" : "+messArray[i].message+"\n"); 
               System.out.println(messArray[i].id+" : "+messArray[i].message);
            }
           
           
        }
        
        area1.setText(text);
        scroll.revalidate();
        scroll.repaint();
    }
    public void sentToContoller(Message objMess){
        MainController.takingMassage(objMess);
    }
    
    public String getId(){
        return id;
    }
    public void setCounts(int soldier,int Ammo){
       soldierCount=soldier;
       ammoCount=Ammo;
    }
    public int getammocount(){
        return ammoCount;
    }
    public int getsoldier(){
        String soldir=spinner1.getValue().toString();
        int soldirCounts=Integer.parseInt(spinner1.getValue().toString());
        return soldirCounts;
    }
    
    
}
class Tank extends JFrame implements Observables{
    JLabel areaClearorNotlbl;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    JButton btn4;
    JCheckBox checkBox;
    JTextArea txtarea;
    JTextField txtField;
    JButton sendButton;
    SpinnerNumberModel model;
    JSpinner spinner1 ;
    JSlider energySlider;
    
    private String boxClicked="";
    private Message[] messageArr=new Message[0];
    private String id;
    
    private int soldierCount;
    private int ammoCount;
    
    Tank(){
        this.id="Tank";
        
        setSize(400,400);
        setTitle("Tank");
        setLocation(400,0);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));
       
        JPanel panel1=new JPanel(new GridLayout(1,2));
        JPanel panel2=new JPanel(new GridLayout(1,2));
        JPanel panel3=new JPanel(new FlowLayout());
        JPanel panel4=new JPanel(new FlowLayout());
        JPanel x=new JPanel(new FlowLayout(FlowLayout.CENTER) );
        
        model = new SpinnerNumberModel(1000, 0, 1000, 1);
        spinner1 = new JSpinner(model);
        JLabel lbl =new JLabel("Soldier Count");
        lbl.setFont(new Font("",1,12));
    
        SpinnerNumberModel model1 = new SpinnerNumberModel(1000, 0, 1000, 1);
        JSpinner spinner2 = new JSpinner(model1);
        JLabel lbl2 =new JLabel("Ammo Count");
        lbl.setFont(new Font("",1,10));
        
        x.add(lbl);
        x.add(spinner1);
        x.add(lbl2);
        x.add(spinner2);
       
        areaClearorNotlbl=new JLabel("Area Not Cleared");
       
        panel1.add(areaClearorNotlbl);
        panel1.add(x);
       
        JPanel panely=new JPanel(new GridLayout(2,2));
        
        JPanel p1=new JPanel(new FlowLayout());
        JPanel p2=new JPanel(new FlowLayout());
        JPanel p3=new JPanel(new FlowLayout());
        JPanel p4=new JPanel(new FlowLayout());
        
        
        
        btn1=new JButton("Shooting");
        btn1.setBounds(50, 50, 120, 40);
        btn1.setEnabled(false);
        btn2=new JButton("Missile Op");
        btn2.setBounds(50, 100, 120, 40);
        btn2.setEnabled(false);
        btn3=new JButton("Radar Op");
        btn3.setBounds(50, 100, 120, 40);
        btn3.setEnabled(false);
        btn4=new JButton("RotateShoot");
        btn4.setBounds(50, 100, 120, 40);
        btn4.setEnabled(false);
        
        p1.add(btn1);
        p2.add(btn2);
        p3.add(btn3);
        p4.add(btn4);
        
        panely.add(p1);
        panely.add(p2);
        panely.add(p3);
        panely.add(p4);
       
        checkBox=new JCheckBox("Position");
       
        panel2.add(panely);
        panel2.add(checkBox);
       
        txtarea=new JTextArea(5,20);
        JScrollPane scrollPane=new JScrollPane(txtarea);
        scrollPane.setBounds(50,50,200,100);
       
        panel3.add(scrollPane);
       
        txtField=new JTextField(20);
        sendButton=new JButton("Send");
       
        panel4.add(txtField);
        panel4.add(sendButton);
        
        JPanel panel5=new JPanel();
        JLabel energylbl=new JLabel("Energy");
        energylbl.setFont(new Font("",Font.BOLD,13));
        energySlider=new JSlider(JSlider.HORIZONTAL,0,100,0);
        energySlider.setMajorTickSpacing(20);
        energySlider.setMinorTickSpacing(20);
        energySlider.setPaintTicks(true);
        energySlider.setPaintLabels(true);
        
        
        panel5.add(energylbl);
        panel5.add(energySlider);
       
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        
        checkBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(checkBox.isSelected()){
                    boxClicked="true";
                }else{
                    boxClicked="false";
                }
            }
        });
        
        sendButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                String message=txtField.getText();
                
                Message OBJMessage=new Message("Tank",message);
                sentToContoller(OBJMessage);
                setOthersMessages(OBJMessage);
                
                txtField.setText("");
            }
        });
        
        btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                String currentVal=spinner2.getValue().toString();
                int currentVal1=Integer.parseInt(spinner2.getValue().toString())-1;
                spinner2.setValue(currentVal1);
                ammoCount=currentVal1;
                decreaseTheEnergySlider();
            }
        });
        
        btn2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
               
                decreaseTheEnergySlider();
            }
        });
        
        btn3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
               
                decreaseTheEnergySlider();
            }
        });
        
        btn4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
               
                decreaseTheEnergySlider();
            }
        });
       
       setVisible(true); 
       
    }
    
    public void decreaseTheEnergySlider(){
        int sliderVal=energySlider.getValue();
        energySlider.setValue(sliderVal-1);
        
    }
    
    public void setLabel(String x){
        areaClearorNotlbl.setText(x);
        setVisible(true);
    }
    public void setBtns(Strength currentStrength){
        if(boxClicked.equals("true")){
            if(currentStrength==Strength.LOW){
                btn1.setEnabled(true);
                energySlider.setValue(20);
            }else if(currentStrength==Strength.MEDIUM){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                energySlider.setValue(40);
            }else if(currentStrength==Strength.HIGH){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                energySlider.setValue(60);
                
            }else if(currentStrength==Strength.STRONG){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                energySlider.setValue(100);
            }
        }else{
            if(currentStrength==Strength.LOW || currentStrength==Strength.MEDIUM || currentStrength==Strength.HIGH || currentStrength==Strength.STRONG){
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
            }
        } 
    }
    public void extendMessageArray(){
        if(messageArr.length==0){
            Message[] temp=new Message[messageArr.length+1];
            messageArr=temp;
        }else if(messageArr.length>0){
            Message[] temp=new Message[messageArr.length+1];
            for(int i=0;i<messageArr.length;i++){
                temp[i]=messageArr[i];
            }
            messageArr=temp;
        }
    }
    public void setOthersMessages(Message obJMessage){
        extendMessageArray();
        messageArr[messageArr.length-1]=obJMessage;
        DisplayIntextArea();
    }
    public void DisplayIntextArea(){
        txtarea.setText("");
        String text="";
        for(int i=0;i<messageArr.length;i++){
            String id=messageArr[i].id;
            if(id.equals("Tank")){
                text+=("You : "+messageArr[i].message+"\n");
            }else{
                text+=(id+" : "+messageArr[i].message+"\n");
            }
            
        }
        txtarea.setText(text);
    }
    
    public void sentToContoller(Message objMess){
        MainController.takingMassage(objMess);
    }
    
    public String getId(){
        return id;
    }
    public void setCounts(int soldier,int Ammo){
       soldierCount=soldier;
       ammoCount=Ammo;
    }
    
    public int getammocount(){
        return ammoCount;
    }
    public int getsoldier(){
        String soldir=spinner1.getValue().toString();
        int soldirCounts=Integer.parseInt(spinner1.getValue().toString());
        return soldirCounts;

    }
    
    
}
class Submarine extends JFrame implements Observables {
    JLabel areaClearorNotlbl;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    JButton btn4;
    JCheckBox checkBox;
    JTextArea txtarea;
    JTextField txtField;
    JButton sendButton;
    SpinnerNumberModel model;
    JSpinner spinner1 ;
    JSlider energySlider;
    
    private String id;
    
    private int soldierCount;
    private int ammoCount;
    
    private String boxClicked="";
    private Message[] messageArr=new Message[0];
    Submarine(){
        this.id="Submarine";
        setSize(400,400);
        setTitle("Submarine");
        setLocation(800,0);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));
       
        JPanel panel1=new JPanel(new GridLayout(1,2));
        JPanel panel2=new JPanel(new GridLayout(1,2));
        JPanel panel3=new JPanel(new FlowLayout());
        JPanel panel4=new JPanel(new FlowLayout());
        
        JPanel x=new JPanel(new FlowLayout(FlowLayout.CENTER) );
        
        model = new SpinnerNumberModel(1000, 0, 1000, 1);
        spinner1 = new JSpinner(model);
        JLabel lbl =new JLabel("Soldier Count");
        lbl.setFont(new Font("",1,12));
    
        SpinnerNumberModel model1 = new SpinnerNumberModel(1000, 0, 1000, 1);
        JSpinner spinner2 = new JSpinner(model1);
        JLabel lbl2 =new JLabel("Ammo Count");
        lbl.setFont(new Font("",1,10));
        
        x.add(lbl);
        x.add(spinner1);
        x.add(lbl2);
        x.add(spinner2);
       
        areaClearorNotlbl=new JLabel("Area Not Cleared");
       
        panel1.add(areaClearorNotlbl);
        panel1.add(x);
       
        JPanel panely=new JPanel(new GridLayout(2,1));
        
        JPanel p1=new JPanel(new FlowLayout());
        JPanel p2=new JPanel(new FlowLayout());
        JPanel p3=new JPanel(new FlowLayout());
        JPanel p4=new JPanel(new FlowLayout());
        
        
        btn1=new JButton("Shooting");
        btn1.setBounds(50, 50, 120, 40);
        btn1.setEnabled(false);
        btn2=new JButton("Sonar Op");
        btn2.setBounds(50, 100, 120, 40);
        btn2.setEnabled(false);
        btn3=new JButton("Tomahawk");
        btn3.setBounds(50, 100, 120, 40);
        btn3.setEnabled(false);
        btn4=new JButton("Trident-2");
        btn4.setBounds(50, 100, 120, 40);
        btn4.setEnabled(false);
    
        p1.add(btn1);
        p2.add(btn2);
        p3.add(btn3);
        p4.add(btn4);
        
        panely.add(p1);
        panely.add(p2);
        panely.add(p3);
        panely.add(p4);
    
        checkBox=new JCheckBox("Position");
       
        panel2.add(panely);
        panel2.add(checkBox);
       
        txtarea=new JTextArea(5,20);
        JScrollPane scrollPane=new JScrollPane(txtarea);
        scrollPane.setBounds(50,50,200,100);
       
        panel3.add(scrollPane);
       
        txtField=new JTextField(20);
        sendButton=new JButton("Send");
       
        panel4.add(txtField);
        panel4.add(sendButton);
        
        JPanel panel5=new JPanel(new FlowLayout());
        JLabel energylbl=new JLabel("Energy");
        energylbl.setFont(new Font("",Font.BOLD,13));
        energySlider=new JSlider(JSlider.HORIZONTAL,0,100,0);
        energySlider.setMajorTickSpacing(20);
        energySlider.setMinorTickSpacing(20);
        energySlider.setPaintTicks(true);
        energySlider.setPaintLabels(true);
        
        
        panel5.add(energylbl);
        panel5.add(energySlider);
       
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        
        checkBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(checkBox.isSelected()){
                    boxClicked="true";
                }else{
                     boxClicked="false";
                }
            }
        });
        
        sendButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                String message=txtField.getText();
                
                Message OBJMessage=new Message("Submarine",message);
                sentToContoller(OBJMessage);
                setOthersMessages(OBJMessage);
                
                txtField.setText("");
            }
        });
        
        btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                String currentVal=spinner2.getValue().toString();
                int currentVal1=Integer.parseInt(spinner2.getValue().toString())-1;
                spinner2.setValue(currentVal1);
                ammoCount=currentVal1;
                decreaseTheEnergySlider();
            }
        });
        btn2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                decreaseTheEnergySlider();
            }
        });
        btn3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
               decreaseTheEnergySlider();
            }
        });
        btn4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                decreaseTheEnergySlider();
            }
        });
       
       setVisible(true); 
    }
    
    public void decreaseTheEnergySlider(){
        int sliderVal=energySlider.getValue();
        energySlider.setValue(sliderVal-1);
        
    }
    public void setLabel(String x){
        areaClearorNotlbl.setText(x);
        setVisible(true);
    }
    public void setBtns(Strength currentStrength){
        if(boxClicked.equals("true")){
            if(currentStrength==Strength.LOW){
                btn1.setEnabled(true);
                energySlider.setValue(20);
            }else if(currentStrength==Strength.MEDIUM){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                energySlider.setValue(40);
            }else if(currentStrength==Strength.HIGH){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                energySlider.setValue(60);
                
            }else if(currentStrength==Strength.STRONG){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                energySlider.setValue(100);
            }
        }else{
            if(currentStrength==Strength.LOW || currentStrength==Strength.MEDIUM || currentStrength==Strength.HIGH || currentStrength==Strength.STRONG){
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
            }
        } 
    }
    public void extendMessageArray(){
        if(messageArr.length==0){
            Message[] temp=new Message[messageArr.length+1];
            messageArr=temp;
        }else if(messageArr.length>0){
            Message[] temp=new Message[messageArr.length+1];
            for(int i=0;i<messageArr.length;i++){
                temp[i]=messageArr[i];
            }
            messageArr=temp;
        }
    }
    public void setOthersMessages(Message obJMessage){
        extendMessageArray();
        messageArr[messageArr.length-1]=obJMessage;
        DisplayIntextArea();
    }
    public void DisplayIntextArea(){
        txtarea.setText("");
        String text="";
        for(int i=0;i<messageArr.length;i++){
            String id=messageArr[i].id;
            if(id.equals("Submarine")){
               text+=("You : "+messageArr[i].message+"\n"); 
            }else{
               text+=(id+" : "+messageArr[i].message+"\n"); 
            }
        }
        txtarea.setText(text);
    }
    
    public void sentToContoller(Message objMess){
        MainController.takingMassage(objMess);
    }
    
    public String getId(){
        return id;
    }
    public void setCounts(int soldier,int Ammo){
       soldierCount=soldier;
       ammoCount=Ammo;
    }
    public int getammocount(){
        return ammoCount;
    }
    public int getsoldier(){
        String soldir=spinner1.getValue().toString();
        int soldirCounts=Integer.parseInt(spinner1.getValue().toString());
        return soldirCounts;

    }
    
}
class MainController extends JFrame{
    
    JButton collectInfoBtn;
    JComboBox<String> comboBox;
    JCheckBox allClearcheckBox;
    JCheckBox checkBox1;
    JButton btnSend;
    JLabel ammoCountlbl;
    JLabel solCountlbl;
    
    static JTextArea are2;
    static JScrollPane scrollPane1;
    
    public static Message[] messageArr=new Message[0];
    private String privateOrNot="Not";
    
    private Observables[] observables=new Observables[0];
    MainController(){
        setSize(450,450);
        setLocation(400,400);
        setTitle("Main Controller");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));
        
        JPanel panel5=new JPanel(new FlowLayout());
        JLabel labely=new JLabel("Main Controller",JLabel.CENTER);  
        labely.setFont(new Font("",Font.BOLD,25));
        labely.setForeground(Color.BLUE);
        panel5.add(labely);
        JPanel panel1=new JPanel(new GridLayout(1,3));
        JPanel pan1=new JPanel(new FlowLayout());
        JPanel pan2=new JPanel(new FlowLayout());
        
        String[] observableNamesArray={"Select Defence","Helicopter","Tank","Submarine"};
        comboBox=new JComboBox<>(observableNamesArray);
        collectInfoBtn=new JButton("Collect Information");
        
        pan1.add(comboBox);
        pan2.add(collectInfoBtn);
        allClearcheckBox=new JCheckBox("Area Cleared");
        
        panel1.add(pan1);
        panel1.add(pan2);
        panel1.add(allClearcheckBox);
        JPanel panel2=new JPanel(new GridLayout(1,2));
        JPanel x=new JPanel(new GridLayout(3,2));
        JPanel y=new JPanel(new GridLayout(2,1));
        JLabel label1=new JLabel("Soldier Count");
        solCountlbl=new JLabel("");
        JLabel label2=new JLabel("Fuel Amout");
        JLabel fuelCountlbl=new JLabel("");
        JLabel label3=new JLabel("Ammo Amount");
        ammoCountlbl=new JLabel("");
        
        x.add(label1);
        x.add(solCountlbl);
        x.add(label2);
        x.add(fuelCountlbl);
        x.add(label3);
        x.add(ammoCountlbl);
        
        
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
        are2=new JTextArea(5,20);
        scrollPane1=new JScrollPane(are2);
        scrollPane1.setBounds(50,50,200,100);
        panel4.add(scrollPane1);
        
        add(panel5);
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        
        checkBox1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(checkBox1.isSelected()){
                    privateOrNot="Private";
                }else{
                    privateOrNot="Not";
                }
            }
        });
        
        
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
                int value=slider.getValue();
                Strength currentStrength=Strength.LOW;
                
                if(value<=20){
                    currentStrength=Strength.LOW;
                }else if(value<=40){
                    currentStrength=Strength.MEDIUM;
                }else if(value<=60){
                    currentStrength=Strength.HIGH;
                }else if(value<=100){
                    currentStrength=Strength.STRONG;
                }
                sendTheValueForBTNs(currentStrength);
            }
        });
        
        btnSend.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(privateOrNot.equals("Not")){
                    String message = area1.getText();
                    Message obj=new Message("MainController",message);
                    sentToOthers(obj);
                    
                }else if(privateOrNot.equals("Private")){
                    String id=comboBox.getSelectedItem().toString();
                    if(id.equals("Select Defence")){
                        JOptionPane.showMessageDialog(null,"Select either helicopter,tank or submarine from the drop down");
                    }else if(! id.equals("Select Defence")){
                        String pvtMessage=area1.getText();
                        Message obj=new Message("MainController",pvtMessage);
                    
                        int index=checkForIndex(id);
                        sentToOthers2(index,obj);
                    }
                    
                    
                    
                    
                }
                area1.setText("");
            }
        });
        
        collectInfoBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                String id=comboBox.getSelectedItem().toString();
                if(id.equals("Select Defence")){
                    JOptionPane.showMessageDialog(null,"Select The diffence for either helicopter,tank or submarine");
                }else if(! id.equals("Select Defence")){
                    int ammo=checkForID(id);
                    int index=checkForIndex(id);
                    getSoldierC(index);
                    setammolbl(ammo);
                    System.out.println(ammo);
                }
               
                    
            }
        });
        
        setCounts();
        
        setVisible(true);
    }
    private int checkForIndex(String id){
        for(int i=0;i<observables.length;i++){
            if(observables[i].getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
    public void getSoldierC(int index){
        int soldierCount=observables[index].getsoldier();
        setsoldierlbl(soldierCount);
    }
    public void setsoldierlbl(int soldierCount){
        solCountlbl.setText(soldierCount+"");
    }
    
    public void setammolbl(int ammo){
        ammoCountlbl.setText(ammo+"");
    }
    public int checkForID(String id){
        for(Observables ob:observables){
            if(ob.getId() .equals (id)){
                int count=ob.getammocount();
                return count;
            }
            
        }
        return 0;
    }
    public void setCounts(){
        for(Observables obs : observables){
            obs.setCounts(1000,1000);
        }
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
    public void sendTheValueForBTNs(Strength currentStrength){
        for(Observables ob:observables){
            ob.setBtns(currentStrength);
        }
    }
    public void sentToOthers(Message OBjmessage){
        for(Observables ob:observables){
            ob.setOthersMessages(OBjmessage);
        }
    }
    public void sentToOthers2(int index,Message obj){
        observables[index].setOthersMessages(obj);
    }
    public static void  extendMessageArray(){
        if(messageArr.length==0){ 
            Message[] temp=new Message[messageArr.length+1];
            messageArr=temp;
        }else if(messageArr.length>0){
            Message[] temp=new Message[messageArr.length+1];
            for(int i=0;i<messageArr.length;i++){
                temp[i]=messageArr[i];
            }
            messageArr=temp;
        }
    }
    public static void takingMassage(Message objMess){
        extendMessageArray();
        messageArr[messageArr.length-1]=objMess;
        setTextArea();
    }
    public static void setTextArea(){
        are2.setText("");
        String text="";
        for(int i=0;i<messageArr.length;i++){
           text+=(messageArr[i].id+" : "+messageArr[i].message+"\n");
        }
        
        are2.setText(text);
    }
}

class Demo{
    public static void main(String args[]){
        MainController m1=new MainController();
        
        m1.addObservable(new Helicopter());
        m1.addObservable(new Tank());
        m1.addObservable(new Submarine());
    }
}
