package Computer;

import java.util.ArrayList;

public class TomasuloView extends javax.swing.JFrame {

	static processor processor;
	String [][] mainTableData;
	String [][] regFileData;
	String [][] fpAddData;
	String [][] fpMultData;
	String [][] loadsData;
	String [][] storesData;
	int cycle;
	String logs;
    public TomasuloView() {
    	
        initComponents();
    }
    public void nextCycleData() {
		cycle = processor.executeCycle();
		logs = processor.logs;
		ArrayList<Object[]> mainTable = processor.getInstructions();
		Object[][] registerFile = processor.getRegFile();
		Object[][] fpAddition = processor.getAddResevation();
		Object[][] fpMultiblication = processor.getMultResevation();
		Object[][] loadStations = processor.getLoadResevation();
		Object[][] storeStations = processor.getStoreResevation();

		mainTableData = new String[mainTable.size()][6];
		regFileData = new String[registerFile.length][3];
		fpAddData = new String[fpAddition.length][7];
		 fpMultData = new String[fpMultiblication.length][7];
		 loadsData = new String[loadStations.length][3];
		 storesData = new String[storeStations.length][5];
		
		for(int i = 0; i<mainTableData.length;i++) {
			Object[] inst = mainTable.get(i); 
			mainTableData[i][0]=inst[0]+"";//instruction
			mainTableData[i][1]=inst[1]+"";//issue
			mainTableData[i][2]="" +inst[2];//start
			mainTableData[i][3]="" +inst[3];//end
			mainTableData[i][4]="" +inst[4];//write
			mainTableData[i][5]="" +inst[5];//tag
		}
		for(int i = 0; i<regFileData.length;i++) {
			regFileData[i][0] = "" +registerFile[i][0];// register name
			regFileData[i][1] = "" +registerFile[i][1];// register value
			regFileData[i][2] = "" +registerFile[i][2];// register dependency
		}
		
		for(int i = 0; i<fpAddData.length;i++) {
			fpAddData[i][0]= "" +fpAddition[i][0];//tag
			fpAddData[i][1]= "" +fpAddition[i][1];//busy
			fpAddData[i][2]= "" +fpAddition[i][2];//inst
			fpAddData[i][3]= "" +fpAddition[i][3];//vj
			fpAddData[i][4]= "" +fpAddition[i][4];//vk
			fpAddData[i][5]= "" +fpAddition[i][5];//qj
			fpAddData[i][6]= "" +fpAddition[i][6];//qk
		}
		for(int i = 0; i<fpMultData.length;i++) {
			fpMultData[i][0]= "" +fpMultiblication[i][0];
			fpMultData[i][1]= "" +fpMultiblication[i][1];
			fpMultData[i][2]= "" +fpMultiblication[i][2];
			fpMultData[i][3]= "" +fpMultiblication[i][3];
			fpMultData[i][4]= "" +fpMultiblication[i][4];
			fpMultData[i][5]= "" +fpMultiblication[i][5];
			fpMultData[i][6]= "" +fpMultiblication[i][6];
		}
		for(int i = 0; i<loadsData.length;i++) {
			loadsData[i][0]="" +loadStations[i][0];//tag
			loadsData[i][1]=loadStations[i][1]+"";//busy
			loadsData[i][2]="" +loadStations[i][2];//address
		}
		for(int i = 0; i<storeStations.length;i++) {
			storesData[i][0]="" +storeStations[i][0];//tag
			storesData[i][1]="" +storeStations[i][1];//busy
			storesData[i][2]="" +storeStations[i][2];//address
			storesData[i][3]="" +storeStations[i][3];//vj
			storesData[i][4]="" +storeStations[i][4];//qj
		}		
		
	}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        LoadBuffer = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        RegFile = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        MulReservationStation = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        Discussion = new javax.swing.JTextPane();
        Next = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ClockNum = new javax.swing.JTextPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        AddReservationStation = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        StoreBuffer = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        MainTable = new javax.swing.JTable();

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            mainTableData,
            new String [] {
                "Instruction", "Issue", "Start", "End", "Write result", "Tag"
            }
        ));
        jScrollPane4.setViewportView(jTable3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            mainTableData,
            new String [] {
                "Instruction", "Issue", "Start", "End", "Write result", "Tag"
            }
        ));
        jScrollPane5.setViewportView(jTable4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(973, 3046));

        LoadBuffer.setModel(new javax.swing.table.DefaultTableModel(
            loadsData,
            new String [] {
                "Tag", "Busy", "Adress"
            }
        ));
        jScrollPane2.setViewportView(LoadBuffer);

        RegFile.setModel(new javax.swing.table.DefaultTableModel(
            regFileData,
            new String [] {
                "Register", "Value", "Qi"
            }
        ));
        jScrollPane9.setViewportView(RegFile);

        MulReservationStation.setModel(new javax.swing.table.DefaultTableModel(
            fpMultData,
            new String [] {
                    "Tag", "Busy", "OP", "Vj", "Vk", "Qj", "Qk"
            }
        ));
        jScrollPane10.setViewportView(MulReservationStation);

        jScrollPane1.setViewportView(Discussion);

        Next.setText("Next");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        jLabel1.setText("Clock :");
        ClockNum.setText((cycle)+"");
        Discussion.setText(logs);
        //ClockNum.setViewportView(jTextPane2);

        AddReservationStation.setModel(new javax.swing.table.DefaultTableModel(
            fpAddData,
            new String [] {
                "Tag", "Busy", "OP", "Vj", "Vk", "Qj", "Qk"
            }
        ));
        jScrollPane11.setViewportView(AddReservationStation);
        if (AddReservationStation.getColumnModel().getColumnCount() > 0) {
            AddReservationStation.getColumnModel().getColumn(6).setHeaderValue("Qk");
        }

        StoreBuffer.setModel(new javax.swing.table.DefaultTableModel(
            storesData,
            new String [] {
                "Tag", "Busy", "Adress", "Vj", "Qj"
            }
        ));
        jScrollPane12.setViewportView(StoreBuffer);

        MainTable.setModel(new javax.swing.table.DefaultTableModel(
        	mainTableData,
            new String [] {
                "Instruction", "Issue", "Start", "End", "Write result", "Tag"
            }
        ));
        jScrollPane13.setViewportView(MainTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel1)
                                .addGap(35, 35, 35)
                                .addComponent(ClockNum, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(Next)))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ClockNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Next)))
                .addContainerGap(2339, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    	nextCycleData();
    	System.out.println(processor.logs);
    	System.out.println();
    	this.invalidate();
    	this.validate();
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                mainTableData,
                new String [] {
                    "bla bla", "Issue", "Start", "End", "Write result", "Tag"
                }
            ));
            jScrollPane4.setViewportView(jTable3);

            jTable4.setModel(new javax.swing.table.DefaultTableModel(
                mainTableData,
                new String [] {
                    "helo btorh", "Issue", "Start", "End", "Write result", "Tag"
                }
            ));
            jScrollPane5.setViewportView(jTable4);

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setPreferredSize(new java.awt.Dimension(973, 3046));

            LoadBuffer.setModel(new javax.swing.table.DefaultTableModel(
                loadsData,
                new String [] {
                    "Tag", "Busy", "Adress"
                }
            ));
            jScrollPane2.setViewportView(LoadBuffer);

            RegFile.setModel(new javax.swing.table.DefaultTableModel(
                regFileData,
                new String [] {
                    "Register", "Value", "Qi"
                }
            ));
            jScrollPane9.setViewportView(RegFile);

            MulReservationStation.setModel(new javax.swing.table.DefaultTableModel(
                fpMultData,
                new String [] {
                        "Tag", "Busy", "OP", "Vj", "Vk", "Qj", "Qk"
                }
            ));
            jScrollPane10.setViewportView(MulReservationStation);

            jScrollPane1.setViewportView(Discussion);

            Next.setText("Next");

            jLabel1.setText("Clock :");
            ClockNum.setText((cycle-1)+"");
            Discussion.setText(logs);
//            ClockNum.setViewportView(jTextPane2);
            AddReservationStation.setModel(new javax.swing.table.DefaultTableModel(
                fpAddData,
                new String [] {
                    "Tag", "Busy", "OP", "Vj", "Vk", "Qj", "Qk"
                }
            ));
            jScrollPane11.setViewportView(AddReservationStation);
            if (AddReservationStation.getColumnModel().getColumnCount() > 0) {
                AddReservationStation.getColumnModel().getColumn(6).setHeaderValue("Qk");
            }

            StoreBuffer.setModel(new javax.swing.table.DefaultTableModel(
                storesData,
                new String [] {
                    "Tag", "Busy", "Adress", "Vj", "Qj"
                }
            ));
            jScrollPane12.setViewportView(StoreBuffer);

            MainTable.setModel(new javax.swing.table.DefaultTableModel(
                mainTableData,
                new String [] {
                    "Instruction", "Issue", "Start", "End", "Write result", "Tag"
                }
            ));
            jScrollPane13.setViewportView(MainTable);

    	this.repaint();    	
    }                                    

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
            java.util.logging.Logger.getLogger(TomasuloView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TomasuloView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TomasuloView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TomasuloView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TomasuloView().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTable AddReservationStation;
    private javax.swing.JTable MainTable;
    private javax.swing.JTextPane ClockNum;
    private javax.swing.JTextPane Discussion;
    private javax.swing.JTable LoadBuffer;
    private javax.swing.JTable MulReservationStation;
    private javax.swing.JButton Next;
    private javax.swing.JTable RegFile;
    private javax.swing.JTable StoreBuffer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration                   
}