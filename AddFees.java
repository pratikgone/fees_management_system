/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fees_management_system;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sound.midi.Receiver;
import javax.swing.JOptionPane;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
/**
 *
 * @author Pratik
 */
public class AddFees extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddFees.class.getName());

    /**
     * Creates new form AddFees
     */
    public void displayCashFirst(){
        lbltransactionid.setVisible(false);
        lbldd.setVisible(false);
        txtdd.setVisible(false);
        
        txtcheque.setVisible(false);
        lblcheque.setVisible(false);
        txttransactionid.setVisible(false);
        lblbankname.setVisible(false);
        txtbankname.setVisible(false);
      
    }
    
    public AddFees() {
        initComponents();
        displayCashFirst();
        fillcombobox();
        int r = fillreceiptno();
        r++;
        txtreceipt.setText(Integer.toString(r));
    }
    
    
    



public class NumberToWordsConverter {

	public static final String[] units = { "", "One", "Two", "Three", "Four",
			"Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
			"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
			"Eighteen", "Nineteen" };

	public static final String[] tens = { 
			"", 		// 0
			"",		// 1
			"Twenty", 	// 2
			"Thirty", 	// 3
			"Forty", 	// 4
			"Fifty", 	// 5
			"Sixty", 	// 6
			"Seventy",	// 7
			"Eighty", 	// 8
			"Ninety" 	// 9
	};

	public static String convert(final int n) {
		if (n < 0) 
                {
			return "Minus " + convert(-n);
		}

		if (n < 20) 
                {
			return units[n];
		}

		if (n < 100) {
			return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
		}

		if (n < 1000) {
			return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}

		if (n < 100000) {
			return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		}

		if (n < 10000000) {
			return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
		}

		return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
	}

	public static void main(final String[] args) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the Amount : ");
		int n=sc.nextInt();

		
		System.out.println( convert(n)+ " Only");

	
	}
}
    
    
    
 boolean validation(){
    if(txtreceivername.getText().equals("")){
        JOptionPane.showMessageDialog(this,"Enter Receiver name first");
        return false;
    }
    if(txtdate.getDate()==null){
        JOptionPane.showMessageDialog(this,"Enter Date first");
        return false;
    }
    if(txtamount.getText().equals("") || !txtamount.getText().matches("[0-9]+")){
        JOptionPane.showMessageDialog(this,"Enter amount (in numbers)");
        return false;
    }
    if(txtmodeofpayment.getSelectedItem().toString().equals("Cheque")){
        if(txtcheque.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Enter cheque number first");
            return false;
        }
        if(txtbankname.getText().equals("")){   
            JOptionPane.showMessageDialog(this,"Enter bank name first");
            return false;
        }
    }

    if(txtmodeofpayment.getSelectedItem().toString().equals("DD")){
        if(txtdd.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Enter DD number first");
            return false;
        }
        if(txtbankname.getText().equals("")){  
            JOptionPane.showMessageDialog(this,"Enter bank name first");
            return false;
        }
    }


    if(txtmodeofpayment.getSelectedItem().toString().equals("PhonePe")){
        if(txttransactionid.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter transaction id first");
            return false;
        }
    }

    if(txtmodeofpayment.getSelectedItem().toString().equals("UPI")){
        if(txttransactionid.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter transaction id first");
            return false;
        }
    }
    if(txtrollno.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter Roll no. first");
        return false;
    }
    if(txtfromyear.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter from year first");
        return false;
    }
    if(txttoyear.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter to year first");
        return false;
    }
    if(txtremark.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter remark first");
        return false;
    }
    return true;
}

 public void fillcombobox(){
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/csfmsdatabase?zeroDateTimeBehavior=CONVERT_TO_NULL";
        Connection con = DriverManager.getConnection(url,"root","Pratik@123");

        String sql = "SELECT course_name FROM course";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery(sql);
         while(rs.next()){
             txtcoursecombobox.addItem(rs.getString("course_name"));

         }  
    } catch(Exception e) {
        e.printStackTrace();
    }
 }

 public int fillreceiptno(){
     int rno=0;
     try{
       Class.forName("com.mysql.cj.jdbc.Driver");
       String url = "jdbc:mysql://localhost:3306/csfmsdatabase?zeroDateTimeBehavior=CONVERT_TO_NULL";
       Connection con = DriverManager.getConnection(url,"root","Pratik@123");
       
       String sql = "SELECT MAX(receipt_no) FROM fees_details";
       PreparedStatement st = con.prepareStatement(sql);
       ResultSet rs = st.executeQuery(sql);
       
       if(rs.next()==true){
          rno =  rs.getInt(1);
       }
       
     }catch(Exception e){
         e.printStackTrace();
     }
     return rno;
 }
 
 //memory
 public String insertData() {
    int receiptno = Integer.parseInt(txtreceipt.getText());
    String sname = txtreceivername.getText();
    String rollno = txtrollno.getText();
    String paymentmode = txtmodeofpayment.getSelectedItem().toString();
    String chequeno = txtcheque.getText();
    String bankname = txtbankname.getText();
    String ddno = txtdd.getText();
    String cname = txtcoursecombobox.getSelectedItem().toString();
    String gstin = labelgstin.getText();
    float totalamount = Float.parseFloat(txttotal.getText());
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    String date = sd.format(txtdate.getDate());   
    
    float amount = Float.parseFloat(txtamount.getText());
    float cgst = Float.parseFloat(txtcgst.getText());
    float sgst = Float.parseFloat(txtsgst.getText());
    String totalwords = txttotalwords.getText();
    String remark = txtremark.getText();
    int year1 = Integer.parseInt(txtfromyear.getText());
    int year2 = Integer.parseInt(txttoyear.getText());

    String status = "";
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/csfmsdatabase?zeroDateTimeBehavior=CONVERT_TO_NULL";
        Connection con = DriverManager.getConnection(url, "root", "Pratik@123");

        String sql = "INSERT INTO fees_details(receipt_no, student_name, roll_no, payment_mode, cheque_no, bank_name, dd_no, course_name, gstin, total_amount, date, amount, cgst, sgst, total_in_words, remark, year1, year2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

PreparedStatement st = con.prepareStatement(sql);
st.setInt(1, receiptno);                
st.setString(2, sname);                  
st.setString(3, rollno);                
st.setString(4, paymentmode);            
st.setString(5, chequeno);             
st.setString(6, bankname);              
st.setString(7, ddno);                   
st.setString(8, cname);                  
st.setString(9, gstin);                  
st.setFloat(10, totalamount);            
st.setString(11, date);                  
st.setFloat(12, amount);                
st.setFloat(13, cgst);                   
st.setFloat(14, sgst);                   
st.setString(15, totalwords);          
st.setString(16, remark);                
st.setInt(17, year1);                    
st.setInt(18, year2);                    


        int c = st.executeUpdate();
        if (c > 0) {
            status = "success";  
        } else {
            status = "fail";
        }

        st.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
        status = "fail";  
    }

    return status;
}

 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtn2 = new javax.swing.JButton();
        jbtn3 = new javax.swing.JButton();
        jbtn4 = new javax.swing.JButton();
        jbtn5 = new javax.swing.JButton();
        jbtn6 = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        jbtn9 = new javax.swing.JButton();
        jbtn10 = new javax.swing.JButton();
        jbtn7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbltransactionid = new javax.swing.JLabel();
        lblmodeofpayment = new javax.swing.JLabel();
        lbldd = new javax.swing.JLabel();
        labelgstin = new javax.swing.JLabel();
        txtreceipt = new javax.swing.JTextField();
        txttransactionid = new javax.swing.JTextField();
        txtdate = new com.toedter.calendar.JDateChooser();
        lblgstin = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txtrollno = new javax.swing.JTextField();
        txtcgst = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtsgst = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtreceivername = new javax.swing.JTextField();
        txthead = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtremark = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jbtn8 = new javax.swing.JButton();
        lblreceivername = new javax.swing.JLabel();
        txttotalwords = new javax.swing.JTextField();
        txtamount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txttoyear = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtfromyear = new javax.swing.JTextField();
        lblreceipt = new javax.swing.JLabel();
        txtmodeofpayment = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtcheque = new javax.swing.JTextField();
        lblbankname = new javax.swing.JLabel();
        txtbankname = new javax.swing.JTextField();
        lblcheque = new javax.swing.JLabel();
        txtdd = new javax.swing.JTextField();
        lblmodeofpayment1 = new javax.swing.JLabel();
        txtcoursecombobox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtn2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtn2.setText("Home");
        jbtn2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jbtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtn2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtn2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtn2MouseExited(evt);
            }
        });
        jbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn2ActionPerformed(evt);
            }
        });
        jPanel1.add(jbtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 160, 40));

        jbtn3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtn3.setText("Search Record");
        jbtn3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jbtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtn3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtn3MouseExited(evt);
            }
        });
        jbtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn3ActionPerformed(evt);
            }
        });
        jPanel1.add(jbtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 160, 40));

        jbtn4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtn4.setText("Add Course");
        jbtn4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jbtn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtn4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtn4MouseExited(evt);
            }
        });
        jbtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn4ActionPerformed(evt);
            }
        });
        jPanel1.add(jbtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 160, 40));

        jbtn5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtn5.setText("Course List");
        jbtn5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jbtn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtn5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtn5MouseExited(evt);
            }
        });
        jbtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn5ActionPerformed(evt);
            }
        });
        jPanel1.add(jbtn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 160, 40));

        jbtn6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtn6.setText("View All Record");
        jbtn6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jbtn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtn6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtn6MouseExited(evt);
            }
        });
        jbtn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn6ActionPerformed(evt);
            }
        });
        jPanel1.add(jbtn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 160, 40));

        btnclear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnclear.setText("Clear");
        btnclear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnclear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnclearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnclearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnclearMouseExited(evt);
            }
        });
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });
        jPanel1.add(btnclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 160, 40));

        jbtn9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtn9.setText("Logout");
        jbtn9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jbtn9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtn9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtn9MouseExited(evt);
            }
        });
        jbtn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn9ActionPerformed(evt);
            }
        });
        jPanel1.add(jbtn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 160, 40));

        jbtn10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtn10.setText("Back");
        jbtn10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jbtn10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtn10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtn10MouseExited(evt);
            }
        });
        jbtn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn10ActionPerformed(evt);
            }
        });
        jPanel1.add(jbtn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 160, 40));

        jbtn7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtn7.setText("Edit Course");
        jbtn7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jbtn7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtn7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtn7MouseExited(evt);
            }
        });
        jbtn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn7ActionPerformed(evt);
            }
        });
        jPanel1.add(jbtn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 160, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 670));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, -1, -1));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbltransactionid.setText("Transaction id");
        jPanel3.add(lbltransactionid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 100, 30));

        lblmodeofpayment.setText("Mode of Payment");
        jPanel3.add(lblmodeofpayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 100, 30));

        lbldd.setText("DD No");
        jPanel3.add(lbldd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 100, 30));

        labelgstin.setText("AVC5677GHJ");
        jPanel3.add(labelgstin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 80, 30));

        txtreceipt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel3.add(txtreceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 80, -1));

        txttransactionid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel3.add(txttransactionid, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 80, -1));
        jPanel3.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        lblgstin.setText("GSTIN");
        jPanel3.add(lblgstin, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 40, 30));

        lbldate.setText("Date");
        jPanel3.add(lbldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 40, 30));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Receiver Signature");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 100, 30));

        jLabel9.setText("Roll No");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, 30));

        jSeparator1.setForeground(new java.awt.Color(255, 51, 51));
        jPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, 120, 10));

        jSeparator2.setForeground(new java.awt.Color(255, 51, 51));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 470, 30));

        jLabel15.setText("Sr No");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 100, 30));

        jLabel16.setText("Head");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 100, 30));

        txttotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel5.add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 110, -1));

        txtrollno.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtrollno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrollnoActionPerformed(evt);
            }
        });
        jPanel5.add(txtrollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 110, -1));

        txtcgst.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel5.add(txtcgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 110, -1));

        jLabel17.setText("Amount");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 100, 30));

        jLabel18.setText("CGST 7%");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 100, 30));

        jSeparator3.setForeground(new java.awt.Color(255, 51, 51));
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 470, 30));

        txtsgst.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel5.add(txtsgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 110, -1));

        jLabel19.setText("SGST 7%");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 100, 30));

        txtreceivername.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel5.add(txtreceivername, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 210, -1));

        txthead.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel5.add(txthead, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 210, -1));

        jLabel20.setText("Total");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 100, 30));

        jLabel21.setText("Total in Words");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 100, 30));

        txtremark.setColumns(20);
        txtremark.setRows(5);
        jScrollPane1.setViewportView(txtremark);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 210, 120));

        jLabel22.setText("Remark");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 100, 30));

        jSeparator4.setForeground(new java.awt.Color(255, 51, 51));
        jPanel5.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 150, -1));

        jbtn8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtn8.setText("Print");
        jbtn8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jbtn8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtn8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtn8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtn8MouseExited(evt);
            }
        });
        jbtn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn8ActionPerformed(evt);
            }
        });
        jPanel5.add(jbtn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 110, 30));

        lblreceivername.setText("Receiver Name");
        jPanel5.add(lblreceivername, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 30));

        txttotalwords.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel5.add(txttotalwords, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 330, 40));

        txtamount.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtamountActionPerformed(evt);
            }
        });
        jPanel5.add(txtamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 110, -1));

        jLabel10.setText("to");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 20, 30));

        txttoyear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txttoyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttoyearActionPerformed(evt);
            }
        });
        jPanel5.add(txttoyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 90, -1));

        jLabel11.setText("From year");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 60, 30));

        txtfromyear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtfromyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfromyearActionPerformed(evt);
            }
        });
        jPanel5.add(txtfromyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 90, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 470, 550));

        lblreceipt.setText("Receipt No");
        jPanel3.add(lblreceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 100, 30));

        txtmodeofpayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "CASH", "PhonePe", "UPI", "Cheque" }));
        txtmodeofpayment.setSelectedIndex(1);
        txtmodeofpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmodeofpaymentActionPerformed(evt);
            }
        });
        jPanel3.add(txtmodeofpayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        jLabel13.setText("Date");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 40, 30));

        txtcheque.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel3.add(txtcheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 80, -1));

        lblbankname.setText("Bank Name");
        jPanel3.add(lblbankname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 100, 30));

        txtbankname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel3.add(txtbankname, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 80, -1));

        lblcheque.setText("Cheque No");
        jPanel3.add(lblcheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 100, 30));

        txtdd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel3.add(txtdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 80, -1));

        lblmodeofpayment1.setText("Course");
        jPanel3.add(lblmodeofpayment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 100, 40));

        txtcoursecombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoursecomboboxActionPerformed(evt);
            }
        });
        jPanel3.add(txtcoursecombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 470, 810));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn8ActionPerformed
        // TODO add your handling code here:
        if(validation()== true){
             String s = insertData();
             if(s.equals("success")){
                 JOptionPane.showMessageDialog(this, "Record inserted successfully");
             }else{
                 JOptionPane.showMessageDialog(this, "record not inserted");
             }
        }
    }//GEN-LAST:event_jbtn8ActionPerformed

    private void jbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn2ActionPerformed

    private void jbtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn3ActionPerformed

    private void jbtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn4ActionPerformed

    private void jbtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn5ActionPerformed

    private void jbtn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn6ActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnclearActionPerformed

    private void jbtn2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn2MouseEntered
        // TODO add your handling code here:
        Color c1 = new Color(51,255,51);
        jbtn2.setBackground(c1);
    }//GEN-LAST:event_jbtn2MouseEntered

    private void jbtn2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn2MouseExited
        // TODO add your handling code here:
        Color c1 = new Color(255,255,255);
        jbtn2.setBackground(c1);
    }//GEN-LAST:event_jbtn2MouseExited

    private void jbtn3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn3MouseEntered
        // TODO add your handling code here:
        Color c1 = new Color(51,255,51);
        jbtn3.setBackground(c1);
    }//GEN-LAST:event_jbtn3MouseEntered

    private void jbtn3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn3MouseExited
        // TODO add your handling code here:
        Color c1 = new Color(255,255,255);
        jbtn3.setBackground(c1);
    }//GEN-LAST:event_jbtn3MouseExited

    private void jbtn4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn4MouseEntered
        // TODO add your handling code here:
        Color c1 = new Color(51,255,51);
        jbtn4.setBackground(c1);
    }//GEN-LAST:event_jbtn4MouseEntered

    private void jbtn4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn4MouseExited
        // TODO add your handling code here:
        Color c1 = new Color(255,255,255);
        jbtn4.setBackground(c1);
    }//GEN-LAST:event_jbtn4MouseExited

    private void jbtn5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn5MouseEntered
        // TODO add your handling code here:
        Color c1 = new Color(51,255,51);
        jbtn5.setBackground(c1);
    }//GEN-LAST:event_jbtn5MouseEntered

    private void jbtn5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn5MouseExited
        // TODO add your handling code here:
        Color c1 = new Color(255,255,255);
        jbtn5.setBackground(c1);
    }//GEN-LAST:event_jbtn5MouseExited

    private void jbtn6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn6MouseEntered
        // TODO add your handling code here:
        Color c1 = new Color(51,255,51);
        jbtn6.setBackground(c1);
    }//GEN-LAST:event_jbtn6MouseEntered

    private void jbtn6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn6MouseExited
        // TODO add your handling code here:
        Color c1 = new Color(255,255,255);
        jbtn6.setBackground(c1);
    }//GEN-LAST:event_jbtn6MouseExited

    private void btnclearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnclearMouseEntered
        // TODO add your handling code here:
        Color c1 = new Color(51,255,51);
        btnclear.setBackground(c1);
    }//GEN-LAST:event_btnclearMouseEntered

    private void btnclearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnclearMouseExited
        // TODO add your handling code here:
        Color c1 = new Color(255,255,255);
        btnclear.setBackground(c1);
    }//GEN-LAST:event_btnclearMouseExited

    private void jbtn8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn8MouseEntered
        // TODO add your handling code here:
        Color c1 = new Color(51,255,51);
        jbtn8.setBackground(c1);
    }//GEN-LAST:event_jbtn8MouseEntered

    private void jbtn8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn8MouseExited
        // TODO add your handling code here:
        Color c1 = new Color(255,255,255);
        jbtn8.setBackground(c1);
    }//GEN-LAST:event_jbtn8MouseExited

    private void jbtn9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn9MouseEntered

    private void jbtn9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn9MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn9MouseExited

    private void jbtn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn9ActionPerformed

    private void txtmodeofpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmodeofpaymentActionPerformed
        // TODO add your handling code here:
   
        //dd
       if(txtmodeofpayment.getSelectedIndex()==0){
           lbldd.setVisible(true);
           txtdd.setVisible(true);
           txttransactionid.setVisible(false);
         
           lblbankname.setVisible(true);
           txtbankname.setVisible(true);
           lbltransactionid.setVisible(false);
           txtcheque.setVisible(false);
           lblcheque.setVisible(false);
       }
      
       //cash
       if(txtmodeofpayment.getSelectedIndex()==1){
           lbltransactionid.setVisible(false);
        lbldd.setVisible(false);
        txtdd.setVisible(false);
    
        txtcheque.setVisible(false);
        lblcheque.setVisible(false);
        txttransactionid.setVisible(false);
        lblbankname.setVisible(false);
        txtbankname.setVisible(false);
       }
       //phonepe
       if(txtmodeofpayment.getSelectedIndex()==2){
           lbltransactionid.setVisible(true);
           txttransactionid.setVisible(true);
           lblbankname.setVisible(false);
           txtbankname.setVisible(false);
           lbldd.setVisible(false);
           txtdd.setVisible(false);
           lblcheque.setVisible(false);
           txtcheque.setVisible(false);
       }
       
           
           //upi
        if(txtmodeofpayment.getSelectedIndex()==3){
            lbltransactionid.setVisible(true);
            txttransactionid.setVisible(true);
            lbldd.setVisible(false);
            txtdd.setVisible(false);
            lblcheque.setVisible(false);
            txtcheque.setVisible(false);
            lblbankname.setVisible(false);
            txtbankname.setVisible(false);
        }
           
       
       //cheque
       if(txtmodeofpayment.getSelectedIndex()==4){
          
           txtcheque.setVisible(true);
           lblcheque.setVisible(true);
           lbldd.setVisible(false);
           txtdd.setVisible(false);
           txttransactionid.setVisible(false);
           lbltransactionid.setVisible(false);
          
           lblbankname.setVisible(true);
           txtbankname.setVisible(true);
       }
        
    }//GEN-LAST:event_txtmodeofpaymentActionPerformed

    private void jbtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn2MouseClicked
        // TODO add your handling code here:
        Home h1 = new Home();
        h1.dispose();
        h1.setVisible(true);
    }//GEN-LAST:event_jbtn2MouseClicked

    private void jbtn10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn10MouseEntered

    private void jbtn10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn10MouseExited

    private void jbtn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn10ActionPerformed

    private void btnclearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnclearMouseClicked
        // TODO add your handling code here:
       txtreceivername.setText("");
       txtbankname.setText("");
       txtrollno.setText("");
       txtdate.setDate(null);
       txtdd.setText("");
       txtcheque.setText("");
       txttransactionid.setText("");
       txtfromyear.setText("");
       txttoyear.setText("");
       txthead.setText("");
       txtamount.setText("");
       txtcgst.setText("");
       txtsgst.setText("");
       txttotal.setText("");
       txttotalwords.setText("");
       txtremark.setText("");
    }//GEN-LAST:event_btnclearMouseClicked

    private void txtrollnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrollnoActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_txtrollnoActionPerformed

    private void jbtn7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn7MouseEntered

    private void jbtn7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn7MouseExited

    private void jbtn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn7ActionPerformed

    private void txtcoursecomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoursecomboboxActionPerformed
        // TODO add your handling code here:
        txthead.setText(txtcoursecombobox.getSelectedItem().toString());
    }//GEN-LAST:event_txtcoursecomboboxActionPerformed

    private void txtamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtamountActionPerformed
        // TODO add your handling code here:
         String s1 = txtamount.getText();
        float amt = Float.parseFloat(s1);
        
        float cgst = amt*0.07f;
        float sgst = amt*0.07f;
        
        txtcgst.setText(Float.toString(cgst));
        txtsgst.setText(Float.toString(sgst));
        
        float t = amt+cgst+sgst;
        txttotal.setText(Float.toString(t));
        
       txttotalwords.setText(NumberToWordsConverter.convert((int) t));
    }//GEN-LAST:event_txtamountActionPerformed

    private void txttoyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttoyearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttoyearActionPerformed

    private void txtfromyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfromyearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfromyearActionPerformed

    private void jbtn8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtn8MouseClicked
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jbtn8MouseClicked

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AddFees().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton jbtn10;
    private javax.swing.JButton jbtn2;
    private javax.swing.JButton jbtn3;
    private javax.swing.JButton jbtn4;
    private javax.swing.JButton jbtn5;
    private javax.swing.JButton jbtn6;
    private javax.swing.JButton jbtn7;
    private javax.swing.JButton jbtn8;
    private javax.swing.JButton jbtn9;
    private javax.swing.JLabel labelgstin;
    private javax.swing.JLabel lblbankname;
    private javax.swing.JLabel lblcheque;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lbldd;
    private javax.swing.JLabel lblgstin;
    private javax.swing.JLabel lblmodeofpayment;
    private javax.swing.JLabel lblmodeofpayment1;
    private javax.swing.JLabel lblreceipt;
    private javax.swing.JLabel lblreceivername;
    private javax.swing.JLabel lbltransactionid;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtbankname;
    private javax.swing.JTextField txtcgst;
    private javax.swing.JTextField txtcheque;
    private javax.swing.JComboBox<String> txtcoursecombobox;
    private com.toedter.calendar.JDateChooser txtdate;
    private javax.swing.JTextField txtdd;
    private javax.swing.JTextField txtfromyear;
    private javax.swing.JTextField txthead;
    private javax.swing.JComboBox<String> txtmodeofpayment;
    private javax.swing.JTextField txtreceipt;
    private javax.swing.JTextField txtreceivername;
    private javax.swing.JTextArea txtremark;
    private javax.swing.JTextField txtrollno;
    private javax.swing.JTextField txtsgst;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttotalwords;
    private javax.swing.JTextField txttoyear;
    private javax.swing.JTextField txttransactionid;
    // End of variables declaration//GEN-END:variables
}
