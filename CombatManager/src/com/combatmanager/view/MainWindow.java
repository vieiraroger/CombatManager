package com.combatmanager.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.combatmanager.security.Configuration;

import controller.CombatImage;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindow extends JFrame implements View {
	private Configuration config;
	private JPanel contentPane;

	private final String NAME = "Tela Principal";
	private final int ACCESS = 3 * 5 * 7 * 11;

	@Override
	public int getAccess() {
		return this.ACCESS;
	}

	@Override
	public String getName() {
		return this.NAME;
	}
	

	/**
	 * @author Romulo Create the frame.
	 */
	public MainWindow(Configuration config) {

		
		
		this.config = config; // TO DO
		setTitle("Combat Manager 1.0");
		setIconImage(CombatImage.combatvinte_20x20.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
	

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnSystem = new JMenu("Sistema");
		menuBar.add(mnSystem);

		JMenuItem mntmUsers = new JMenuItem( new AbstractAction("Usuarios") {
		
			public void actionPerformed(ActionEvent e)
			{
			
				CreateContentPane(new UsersWindow());
				revalidate();
			}
				
		});
		mntmUsers.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img16/aplicacao.png")));
		mnSystem.add(mntmUsers);

		JMenuItem mntmExit = new JMenuItem( new AbstractAction("Sair") {
			
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
				
		});
		mntmExit.setIcon(CombatImage.alterar_16x16); //COMO USAR AS IMAGENS
		mnSystem.add(mntmExit);

		JMenu mnRegisted = new JMenu("Cadastros");
		menuBar.add(mnRegisted);
		if(new StudentsWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmStudents = new JMenuItem(new AbstractAction("Alunos"){
				
				public void actionPerformed(ActionEvent e)
				{
				
					CreateContentPane(new StudentsWindow());
					revalidate();
				}
					
			});
			mntmStudents.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img16/aplicacao.png")));
			mnRegisted.add(mntmStudents);
		}
		
		
		if(new ModalityWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmModalities = new JMenuItem(new AbstractAction("Modalidades"){
				
				public void actionPerformed(ActionEvent e)
				{
				
					CreateContentPane(new ModalityWindow());
					revalidate();
				}
					
			});
			mntmModalities.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img16/aplicacao.png")));
			mnRegisted.add(mntmModalities);
		}
		if(new PlansWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmPlans = new JMenuItem(new AbstractAction("Planos"){
				
				public void actionPerformed(ActionEvent e)
				{
				
					CreateContentPane(new PlansWindow());
					revalidate();
				}
					
			});
			
			mntmPlans.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img16/aplicacao.png")));
			mnRegisted.add(mntmPlans);
		}
		JMenu mnProcesses = new JMenu("Processos");
		menuBar.add(mnProcesses);

		;
		JMenu mnRegister = new JMenu("Matricular");
		mnRegister.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img16/aplicacao.png")));
		mnRegister.setHorizontalAlignment(SwingConstants.LEFT);
		mnProcesses.add(mnRegister);
		if(new RegisterStudentWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmStudent = new JMenuItem(new AbstractAction("Aluno"){
				
				public void actionPerformed(ActionEvent e)
				{
				
					CreateContentPane(new RegisterStudentWindow());
					revalidate();
				}
					
			});
			mnRegister.add(mntmStudent);
		}
		
		JMenu mnBilling = new JMenu("Faturamento");
		mnBilling.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img16/aplicacao.png")));
		mnProcesses.add(mnBilling);
		if(new GenerateBillWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmGenerateBill = new JMenuItem(new AbstractAction("Gerar fatura"){
				public void actionPerformed(ActionEvent e)
				{
				
					CreateContentPane(new GenerateBillWindow());
					revalidate();
				}
				
			});
			mnBilling.add(mntmGenerateBill);
		}
		
		
		if(new CheckInvoiceWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmCheckBills = new JMenuItem(new AbstractAction("Consultar Faturas"){
				
				public void actionPerformed(ActionEvent e)
				{
				
					CreateContentPane(new CheckInvoiceWindow());
					revalidate();
				}
					
			});
			mnBilling.add(mntmCheckBills);
		}
		
		if(new PayInvoiceWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmMakePayment = new JMenuItem(new AbstractAction("Pagamento de Faturas"){
				
				public void actionPerformed(ActionEvent e)
				{
				
					CreateContentPane(new PayInvoiceWindow());
					revalidate();
				}
					
			});
			
			mntmMakePayment.setSelected(true);
			mnBilling.add(mntmMakePayment);
		}
		

		JMenu mnReports = new JMenu("Relat\u00F3rios");
		menuBar.add(mnReports);
		if(new EnrollmentReportWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmMtrcula = new JMenuItem(new AbstractAction("Matricula"){
				public void actionPerformed(ActionEvent e)
				{
				
					CreateContentPane(new EnrollmentReportWindow());
					revalidate();
				}
				});
			mnReports.add(mntmMtrcula);
		}
		

		JMenu mnBills = new JMenu("Faturas");
		mnReports.add(mnBills);

		JMenu mnUtilities = new JMenu("Utilit\u00E1rios");
		if(new BackupWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmBackup = new JMenuItem(new AbstractAction("Backup"){
				public void actionPerformed(ActionEvent e)
				{
				
					CreateContentPane(new BackupWindow());
					revalidate();
				}
				});
			mnUtilities.add(mntmBackup);
		}
		
		
		menuBar.add(mnUtilities);
		

		JMenu mnHelp = new JMenu("Ajuda");
		menuBar.add(mnHelp);

		Container contentPane = this.CreateContentPane(new StudentControlWindow());
		
		this.addWindowListener((WindowListener) new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				config.printLog();
				System.exit(0);
					
			}
		});


	}

	public Container CreateContentPane(View view) {
		
		Container c = view.run(this.config);
		setContentPane(c);
		config.addToSystemLog(view.getName()+","+"Abriu tela");
		return c;

	}

	@Override
	public JPanel run(Configuration config) {
		return null;
	}

}
