package com.eduit.sampleColasMQ;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Chat {
	
	private MessageProducer producer;
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	private static String subject = "CHAT";
	private JTextField textField;
	private JTextField textFieldNombre;
	private JTextArea textArea;
	private Session session;

	public static void main(String[] args) throws JMSException {
		System.out.println("conectaremos a: "+ url);
		new Chat();
	}

	public Chat() throws JMSException {
		
		JFrame frame = new JFrame("chat");
		textArea = new JTextArea(15, 30);
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		textField = new JTextField(30);
		frame.getContentPane().add(textField, BorderLayout.SOUTH);

		textFieldNombre = new JTextField(30);
		frame.getContentPane().add(textFieldNombre, BorderLayout.NORTH);

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
//Cuando llamamos al addActionListener() de cualquiera de estos componentes, nos estamos suscribiendo a la acci�n m�s "t�pica" de ese componente
		//Como par�metro, debemos pasar una clase que implemente ActionListner. Por tanto, debemos hacer una clase que implemente la interface ActionListener. Hay muchas formas de hacer esto
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MapMessage map;
				try {
					map = session.createMapMessage();
					map.setString("usuario", textFieldNombre.getText());
					map.setString("texto", textField.getText());
					textField.setText("");
					producer.send(map);

				} catch (JMSException e1) {
					e1.printStackTrace();
				}
			}
		});

		activeMQ();
	}
	/*
	En el c�digo,	unicamente fijarnos	en el	m�todo activeMQ() donde se establece la conexi�n con el servidor JMS. Ah� se crean tambi�n
	el productor   (el que env�a los mensajes al servidor) y el	consumidor (que lee los mensajes del servidor).
   Arrancando varias veces obtenemos algo como en la siguiente figura. El el JTextField superior ponemos el nombre de usuario que queramos y 
   en el JTextField inferior escribimos el texto que queramos enviar y pulsamos enter. En el actionListener() del JTextField se env�a el mensaje 
   usando el productor. En el consumidor hemos a�adido un MessageListener encargado de recibir el mensaje y pintarlo en el JTextArea.
   */

	private void activeMQ() throws JMSException {
//		creamos una conexi�n
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		//arrancamos la connetion
		connection.start();
		/*Creamos una sesi�n. Los par�metros son:
			boolean indicando si queremos que la sesi�n permita transacciones. Si permite transacciones,
			podemos enviar/recibir mensajes que no se enviar�n o retirar�n de la cola definitivamente hasta que no llamemos a session.commit().
			Si algo va mal en el tratamiento de mensajes, podemos llamar a session.rollback() para reestablecer la cola de mensajes. Para nuestro 
			ejemplo sencillo, no queremos transacciones.
			Session.AUTO_ACKNOWLEDGE. A ActiveMQ hay que indicarle que hemos recibido el mensaje, para que se pueda olvidar de �l. Con esta opci�n,
			no necesitamos decirle expl�citamente que lo hemos recibido, se har� autom�ticamente en el momento que nos lo entregue.*/
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

//		Creamos la cola donde vamos a meter los mensajes. La cola se identifica con un nombre (un String en este caso subject) 
//		y puede ser el que queramos. �nicamente, el que env�a el mensaje y el que lo recibe deben estar de 
//		acuerdo qu� cola (qu� nombre) van a usar.
		// destination representa aqu� nuestra cola en el
		// servidor JMS. No tiene que hacer nada especial en el
		// servidor para crearlo, se crear� autom�ticamente.
		Destination destination = session.createTopic(subject);
//		Creamos el productor de mensajes con session.createProducer(), indicando que el destino ser� la cola que acabamos de crear.
		producer = session.createProducer(destination);
		//Creamos el consumidor de mensajes con session.createConsumer(), indicando como destino la cola de la que queremos leer los mensajes
		MessageConsumer consumer = session.createConsumer(destination);
		//Cada sesi�n debe asegurarse de que transmite mensajes en serie al oyente. Esto significa que un oyente asignado a uno o m�s consumidores de la misma sesi�n puede asumir
		//que el onMessagem�todo no se llama con el siguiente mensaje hasta que la sesi�n haya completado la �ltima llamada.
		consumer.setMessageListener(new MessageListener() {//Registre un oyente de mensajes para recibir los mensajes del destino.

			@Override
			public void onMessage(Message message) {
				
				if (message instanceof MapMessage) {//valido si es un map message
					MapMessage map = (MapMessage) message; //casteo 
					try {//intenamos leer mensaje y usuario y lo printeamos en el chat
						String usuario = map.getString("usuario");
						String texto = map.getString("texto");
						textArea.append(usuario + ": " + texto + "\n");
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}

			}
		});
	}
	
	
	
}
