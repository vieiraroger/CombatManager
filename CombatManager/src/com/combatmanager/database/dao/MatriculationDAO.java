package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.City;
import com.combatmanager.database.model.Matriculation;

public class MatriculationDAO extends MasterDAO{
		
	
	private String selectAll = "select * from matriculas order by data_matricula";
	private String select = "select * from matriculas where codigo_matricula = ? order by data_matricula";
	private String insert = "INSERT INTO matriculas			"
								+"	(						" 
								+"		codigo_matricula, 		"
								+"		codigo_aluno, 				"
								+"		data_matricula, 	"
								+"		dia_vencimento, 	"
								+"		data_encerramento 	"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		DEFAULT, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		? 					"
								+"	)";
	private String update = "UPDATE matriculas"
							+ "SET"
							+ "		codigo_aluno = ?,"
							+ "		data_matricula = ?,"
							+ "		dia_vencimento = ?,"
							+ "		data_encerramento = ?"
							+ "WHERE"
							+ "		codigo_matricula = ?";
	private String delete = "DELETE FROM matriculas WHERE codigo_matricula = ?";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;
	
	Connection io_connection;
	
	public  MatriculationDAO(Connection connection) throws SQLException{

		io_connection = connection;
		
		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		pst_update = connection.prepareStatement(update);
		pst_delete = connection.prepareStatement(delete);
		
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlMatriculation = new ArrayList<Object>();
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			Matriculation mat = new Matriculation();
			mat.setCode(Integer.parseInt(rst.getString("codigo_matricula")));
			mat.setStudent_code(Integer.parseInt(rst.getString("codigo_aluno")));
			mat.setMatriculation_date(rst.getString("data_matricula"));
			mat.setDue_date(Integer.parseInt(rst.getString("dia_vencimento")));
			mat.setClosing_date(rst.getString("data_encerramento"));
			
			
			arlMatriculation.add(mat);
		}
		
		return arlMatriculation;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		pst_select.clearParameters();
		
		Matriculation mat = null;
		
		Set(pst_select, 1, ((Matriculation)parameter).getCode());

		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			mat = new Matriculation();
			mat.setCode(Integer.parseInt(rst.getString("codigo_matricula")));
			mat.setStudent_code(Integer.parseInt(rst.getString("codigo_aluno")));
			mat.setMatriculation_date(rst.getString("data_matricula"));
			mat.setDue_date(Integer.parseInt(rst.getString("dia_vencimento")));
			mat.setClosing_date(rst.getString("data_encerramento"));
			
			
		}
		
		return mat;
	}

	@Override
	public void Update(Object last_parameter, Object new_parameter) throws SQLException {
		pst_update.clearParameters();
		
		Matriculation mat = new Matriculation();
		
		mat = (Matriculation) new_parameter;
		
		
		Set(pst_update, 1, mat.getStudent_code());
		Set(pst_update, 2, mat.getMatriculation_date());
		Set(pst_update, 3, mat.getDue_date());
		Set(pst_update, 4, mat.getClosing_date());
		
		mat = (Matriculation) last_parameter;
		
		Set(pst_update, 5, mat.getCode());
		
		pst_update.execute();
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Matriculation mat = (Matriculation)parameter;
		
		Set(pst_insert, 1, mat.getCode());
		Set(pst_insert, 2, mat.getStudent_code());
		Set(pst_insert, 3, mat.getMatriculation_date());
		Set(pst_insert, 4, mat.getDue_date());
		Set(pst_insert, 5, mat.getClosing_date());

		pst_insert.execute();
		
	}

	@Override
	public void Delete(Object parameter) throws SQLException {
		pst_delete.clearParameters();
		
		Matriculation mat = new Matriculation();
		
		mat = (Matriculation) parameter;
		
		Set(pst_delete, 1, mat.getCode());
		
		pst_delete.execute();
		
	}
}
