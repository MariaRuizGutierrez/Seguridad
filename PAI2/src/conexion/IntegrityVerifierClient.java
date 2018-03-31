package conexion;

import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.swing.*;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.net.*;

public class IntegrityVerifierClient {
	// Constructor que abre una conexión Socket para enviar mensaje/MAC al servidor
	public IntegrityVerifierClient() {
		try {
			SocketFactory socketFactory = (SocketFactory) SocketFactory.getDefault();
			Socket socket = (Socket) socketFactory.createSocket("localhost", 7070);

			// Crea un PrintWriter para enviar mensaje/MAC al servidor
			PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String userName = JOptionPane.showInputDialog(null, "Introduzca su mensaje:");
			Scanner sc = new Scanner(System.in);
			String mensaje = sc.nextLine();
			Mac mac1 = Mac.getInstance("HmacSHA256");
			KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
			SecretKey clave = kg.generateKey();
			
			mac1.update(mensaje.getBytes());
			byte[] b = mac1.doFinal();
			
				
				// Envío del mensaje al servidor
				output.println(mensaje);

				// Habría que calcular el correspondiente MAC con la clave compartida por
				// servidor/cliente
				output.println(b);

				// Importante para que el mensaje se envíe
				output.flush();

				// Crea un objeto BufferedReader para leer la respuesta del servidor
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// Lee la respuesta del servidor
				String respuesta = input.readLine();

				// Muestra la respuesta al cliente
				JOptionPane.showMessageDialog(null, respuesta);

				// Se cierra la conexion
				output.close();
				input.close();
				socket.close();

		

		} catch (NoSuchAlgorithmException | IOException es) {
			
			es.printStackTrace();
		}

		// Salida de la aplicacion
		finally {
			System.exit(0);
		}
	}
	
public static void main(String[] args) {
	
		
		// ejecución del cliente de verificación de la integridad
		new IntegrityVerifierClient();
	}
}