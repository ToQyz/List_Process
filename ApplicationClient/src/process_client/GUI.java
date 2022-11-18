package process_client;

import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class GUI extends javax.swing.JFrame {
    private SocketHandler socketHandler;
    private byte[] byteArray;
    private DefaultTableModel model;
    
    
    public GUI(SocketHandler socketHandler) throws UnsupportedEncodingException {
        System.out.println("start init GUI");
        initComponents();
        this.socketHandler = socketHandler;
        
        // set non-editable JTable
        tblApp.setDefaultEditor(Object.class, null);
        
        model = (DefaultTableModel) tblApp.getModel();
        
         try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    socketHandler.send("<GET>$<>");
                    byteArray = socketHandler.receive();
                    System.out.println("Read Successfully");
                    
                    String data =null;
                    
                    try {
                        data = new String(byteArray, "UTF-8");
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    System.out.println(data);

                    // start display
                    draw(data);
        
//        Thread thread = new Thread(){
//            public void run(){
//                while(true){
//                    try {
//                        Thread.sleep(1500);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    socketHandler.send("<GET>$<>");
//                    byteArray = socketHandler.receive();
//                    System.out.println("Read Successfully");
//                    
//                    String data =null;
//                    
//                    try {
//                        data = new String(byteArray, "UTF-8");
//                    } catch (UnsupportedEncodingException ex) {
//                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    
//                    System.out.println(data);
//
//                    // start display
//                    draw(data);
//                    
//                    //tblApp.setRowSelectionInterval(0, 0);
//                }
//            }
//        };
//        
//        thread.start();
    }
    
    private void draw(String data){
        String[] apps = data.split("\n");
        
        Vector<Integer> index = new Vector<>();
        
        for(int i = 0; i < apps.length; i++){
            if(apps[i].equals("")) continue;
            String[] attribution = apps[i].split(" ");
            if(attribution.length != 4) continue;
            
            int j =0;
            for(; j < model.getRowCount(); j++){
                String name = (String) model.getValueAt(j, 0);
                if(name.equals(attribution[0])){
                    index.add(j);
                    model.setValueAt(attribution[1],j,1);
                    model.setValueAt(attribution[2],j,2);
                    model.setValueAt(attribution[3],j,3);
                    break;
                }
            }
            
            if(j >= model.getRowCount()){
                index.add(j);
                model.addRow(new Object[] {attribution[0], attribution[1], attribution[2], attribution[3]});
            }
        }
        
        for(int i = model.getRowCount()-1; i>=0; i--){
            if(!index.contains(i)){
                model.removeRow(i);
            }
        }
    }  
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblApp = new javax.swing.JTable();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("LIST RUNNING PROCESSES");

        jButton2.setText("kill");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tblApp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "PID", "CPU", "RAM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblApp.setFocusable(false);
        tblApp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblApp.setShowGrid(false);
        jScrollPane1.setViewportView(tblApp);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(470, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 37, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println("Start kill app");
        socketHandler.send("<KILL>$<20940>");
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblApp;
    // End of variables declaration//GEN-END:variables
}