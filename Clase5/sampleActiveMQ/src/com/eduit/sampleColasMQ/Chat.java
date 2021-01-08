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
//Cuando llamamos al addActionListener() de cualquiera de estos componentes, nos estamos suscribiendo a la acción más "típica" de ese componente
		//Como parámetro, debemos pasar una clase que implemente ActionListner. Por tanto, debemos hacer una clase que implemente la interface ActionListener. Hay muchas formas de hacer esto
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
	En el código,	unicamente fijarnos	en el	método activeMQ() donde se establece la conexión con el servidor JMS. Ahí se crean también
	el productor   (el que envía los mensajes al servidor) y el	consumidor (que lee los mensajes del servidor).
   Arrancando varias veces obtenemos algo como en la siguiente figura. El el JTextField superior ponemos el nombre de usuario que queramos y 
   en el JTextField inferior escribimos el texto que queramos enviar y pulsamos enter. En el actionListener() del JTextField se envía el mensaje 
   usando el productor. En el consumidor hemos añadido un MessageListener encargado de recibir el mensaje y pintarlo en el JTextArea.
   */

	private void activeMQ() throws JMSException {
//		creamos una conexión
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		//arrancamos la connetion
		connection.start();
		/*Creamos una sesión. Los parámetros son:
			boolean indicando si queremos que la sesión permita transacciones. Si permite transacciones,
			podemos enviar/recibir mensajes que no se enviarán o retirarán de la cola definitivamente hasta que no llamemos a session.commit().
			Si algo va mal en el tratamiento de mensajes, podemos llamar a session.rollback() para reestablecer la cola de mensajes. Para nuestro 
			ejemplo sencillo, no queremos transacciones.
			Session.AUTO_ACKNOWLEDGE. A ActiveMQ hay que indicarle que hemos recibido el mensaje, para que se pueda olvidar de él. Con esta opción,
			no necesitamos decirle explícitamente que lo hemos recibido, se hará automáticamente en el momento que nos lo entregue.*/
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

//		Creamos la cola donde vamos a meter los mensajes. La cola se identifica con un nombre (un String en este caso subject) 
//		y puede ser el que queramos. Únicamente, el que envía el mensaje y el que lo recibe deben estar de 
//		acuerdo qué cola (qué nombre) van a usar.
		// destination representa aquí nuestra cola en el
		// servidor JMS. No tiene que hacer nada especial en el
		// servidor para crearlo, se creará automáticamente.
		Destination destination = session.createTopic(subject);
//		Creamos el productor de mensajes con session.createProducer(), indicando que el destino será la cola que acabamos de crear.
		producer = session.createProducer(destination);
		//Creamos el consumidor de mensajes con session.createConsumer(), indicando como destino la cola de la que queremos leer los mensajes
		MessageConsumer consumer = session.createConsumer(destination);
		//Cada sesión debe asegurarse de que transmite mensajes en serie al oyente. Esto significa que un oyente asignado a uno o más consumidores de la misma sesión puede asumir
		//que el onMessagemétodo no se llama con el siguiente mensaje hasta que la sesión haya completado la última llamada.
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
