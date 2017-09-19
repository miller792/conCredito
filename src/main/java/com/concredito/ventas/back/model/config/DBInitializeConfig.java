	package com.concredito.ventas.back.model.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializeConfig {

	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void initialize(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS clientes( "+
					"cliente_id INT NOT NULL , " +
					"nombre VARCHAR(50) NOT NULL, " +
					"apellido_paterno VARCHAR(50) NOT NULL, " +
					"apellido_materno VARCHAR(50) NOT NULL, " +
					"rfc VARCHAR(13) NOT NULL, " +
					"PRIMARY KEY ( cliente_id ) )");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS articulos( " + 
					"articulo_id INT NOT NULL , " +
					"descripcion VARCHAR(100) NOT NULL, " +
					"modelo VARCHAR(100) NOT NULL, " +
					"precio DECIMAL(12,2) NOT NULL, " +
					"existencia INT NOT NULL, " +
					"PRIMARY KEY ( articulo_id ) )");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS confGeneral( " +
					"conf_id INT NOT NULL , " +
					"tasa_financiamiento DECIMAL(5,2) NOT NULL, " +
					"porcentaje_enganche DECIMAL(5,2) NOT NULL, " +
					"plazo_maximo INT NOT NULL, " +
					"PRIMARY KEY ( conf_id ))");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS ventasEnc( " +
				    "folio_id INT NOT NULL , " +
				    "cliente_id INT NOT NULL, " +
				    "enganche DECIMAL(12,2) NOT NULL, " +
				    "bonificacion DECIMAL(12,2) NOT NULL, " +
				    "total_Adeudo DECIMAL(18,2) NOT NULL, " +
				    "total DECIMAL(18,2) NOT NULL, " +
				    "estatus INT NOT NULL , " +
				    "plazo INT NOT NULL, " +
				    "fecha DATE, " +
				    "PRIMARY KEY (folio_id), " +
				    "FOREIGN KEY(cliente_id) references clientes(cliente_id))");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS ventasDet( " +
					"venta_id INT NOT NULL, " +
					"folio_id INT NOT NULL, " +
					"articulo_id INT NOT NULL, " +
					"cantidad INT NOT NULL, " +
					"precio DECIMAL(12,2) NOT NULL, " +
					"importe DECIMAL(12,2) NOT NULL, " +
//					"PRIMARY KEY ( venta_id ), " +
					"CONSTRAINT fk_venta FOREIGN KEY(folio_id) references ventasEnc(folio_id), " +
					"CONSTRAINT fk_articulo FOREIGN KEY(articulo_id) references articulos(articulo_id))");
			
			///Productos
			statement.executeUpdate(
					"INSERT INTO confGeneral (conf_id, tasa_financiamiento, porcentaje_enganche, plazo_maximo) VALUES ('1', '2.8', '20', '12')");
			statement.executeUpdate("INSERT INTO articulos (articulo_id, descripcion, modelo, precio, existencia) VALUES ('1', 'Articulo 1', 'modelo 5', '4250', '10')");
			statement.executeUpdate("INSERT INTO articulos (articulo_id, descripcion, modelo, precio, existencia) VALUES ('2', 'Articulo 2', 'modelo 2', '10', '30')");
			statement.executeUpdate("INSERT INTO articulos (articulo_id, descripcion, modelo, precio, existencia) VALUES ('3', 'Articulo 3', 'modelo 3', '800', '32')");
			statement.executeUpdate("INSERT INTO articulos (articulo_id, descripcion, modelo, precio, existencia) VALUES ('4', 'Articulo 4', 'modelo 2018', '1', '5')");
			statement.executeUpdate("INSERT INTO articulos (articulo_id, descripcion, modelo, precio, existencia) VALUES ('5', 'Articulo 5', 'modelo 5000', '20', '20')");
			statement.executeUpdate("INSERT INTO articulos (articulo_id, descripcion, modelo, precio, existencia) VALUES ('6', 'Articulo 6', 'modelo z', '3000', '2')");
			statement.executeUpdate("INSERT INTO articulos (articulo_id, descripcion, modelo, precio, existencia) VALUES ('7', 'Articulo 7', 'modelo 2016', '1575', '9')");
			statement.executeUpdate("INSERT INTO clientes (cliente_id, nombre, apellido_paterno, apellido_materno, rfc) VALUES ('1', 'Ruben', 'Miller', 'Medina', 'MIMR9209072I9')");
			statement.executeUpdate("INSERT INTO clientes (cliente_id, nombre, apellido_paterno, apellido_materno, rfc) VALUES ('2', 'Adrian', 'Lopez', 'Garcia','LOGA9405051U1')");
			statement.executeUpdate("INSERT INTO clientes (cliente_id, nombre, apellido_paterno, apellido_materno, rfc) VALUES ('3', 'Victor', 'Cruz', 'Favela','CUFV850101AI9')");
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
