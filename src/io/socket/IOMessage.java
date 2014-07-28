/*
 * socket.io-java-client IOMessage.java
 *
 * Copyright (c) 2012, Enno Boland
 * socket.io-java-client is a implementation of the socket.io protocol in Java.
 * 
 * See LICENSE file for more information
 */
package io.socket;

/**
 * The Class IOMessage.
 */
class IOMessage {

	public static final int EIO_TYPE_OPEN = 0;
	public static final int EIO_TYPE_CLOSE = 1;
	public static final int EIO_TYPE_PING = 2;
	public static final int EIO_TYPE_PONG = 3;
	public static final int EIO_TYPE_MESSAGE = 4;
	public static final int EIO_TYPE_UPGRADE = 5;
	public static final int EIO_TYPE_NOOP = 6;
	
	
	public static final int SIO_TYPE_CONNECT = 0;
	public static final int SIO_TYPE_DISCONNECT = 1;
	public static final int SIO_TYPE_EVENT = 2;
	public static final int SIO_TYPE_ACK = 3;
	public static final int SIO_TYPE_ERRORT = 4;
	public static final int SIO_TYPE_BINARY_EVENT = 5;
	public static final int SIO_TYPE_BINARY_ACK = 6;
	
	
	
	/** Message type disconnect */
	public static final int TYPE_DISCONNECT = 0;

	/** Message type connect */
	public static final int TYPE_CONNECT = 1;

	/** Message type heartbeat */
	public static final int TYPE_HEARTBEAT = 2;

	/** Message type message */
	public static final int TYPE_MESSAGE = 3;

	/** Message type JSON message */
	public static final int TYPE_JSON_MESSAGE = 4;

	/** Message type event */
	public static final int TYPE_EVENT = 5;

	/** Message type acknowledge */
	public static final int TYPE_ACK = 6;

	/** Message type error */
	public static final int TYPE_ERROR = 7;

	/** Message type noop */
	public static final int TYPE_NOOP = 8;

	/** Index of the type field in a message */
	public static final int FIELD_EIO_TYPE = 0;
	public static final int FIELD_SIO_TYPE = 1;

	/** Index of the id field in a message */
	public static final int FIELD_ID = 2;

	/** Index of the end point field in a message */
	public static final int FIELD_ENDPOINT = 3;

	/** Index of the data field in a message */
	public static final int FIELD_DATA = 4;

	/** Number of fields in a message. */
	public static final int NUM_FIELDS = 5;

	/** The field values */
	private final String[] fields = new String[NUM_FIELDS];

	/** Type */
	private int type;
	private int eio_type;
	private int sio_type;
	/**
	 * Instantiates a new IOMessage by given data.
	 * 
	 * @param type
	 *            the type
	 * @param id
	 *            the id
	 * @param namespace
	 *            the namespace
	 * @param data
	 *            the data
	 */
	public IOMessage(int eio_type, int sio_type, String id, String namespace, String data) {
		this.eio_type = eio_type;
		this.sio_type = sio_type;
		this.fields[FIELD_EIO_TYPE] = ""+eio_type;
		this.fields[FIELD_SIO_TYPE] = ""+sio_type;
		this.fields[FIELD_ID] = id;
		this.fields[FIELD_ENDPOINT] = namespace;
		this.fields[FIELD_DATA] = data;
	}

	/**
	 * Instantiates a new IOMessage by given data.
	 * 
	 * @param type
	 *            the type
	 * @param namespace
	 *            the name space
	 * @param data
	 *            the data
	 */
	public IOMessage(int eio_type, int sio_type, String namespace, String data) {
		this(eio_type, sio_type, null, namespace, data);
	}

	/**
	 * Instantiates a new IOMessage from a String representation. If the String
	 * is not well formated, the result is undefined.
	 * 
	 * @param message
	 *            the message
	 */
	public IOMessage(String message) {
		eio_type = Integer.valueOf(message.substring(0, 1));
		String data = "";
		if(eio_type == 4) {
			sio_type = Integer.valueOf(message.substring(1, 2));
			data = message.substring(2);
		}
		else {
			sio_type = 0;
			data = message.substring(1);
		}
		this.fields[FIELD_EIO_TYPE] = ""+eio_type;
		this.fields[FIELD_SIO_TYPE] = ""+sio_type;
		this.fields[FIELD_ID] = "";
		this.fields[FIELD_ENDPOINT] = "";
		this.fields[FIELD_DATA] = data;
//		
//		String[] fields = message.split(":", NUM_FIELDS);
//		for (int i = 0; i < fields.length; i++) {
//			this.fields[i] = fields[i];
//			if(i == FIELD_TYPE)
//				this.type = Integer.parseInt(fields[i]);
//		}
	}

	/**
	 * Generates a String representation of this object.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(""+eio_type+sio_type);
		builder.append(getData());
		return builder.toString();
//		for(int i = 0; i < fields.length; i++) {
//			builder.append(':');
//			if (fields[i] != null)
//				builder.append(fields[i]);
//		}
//		return builder.substring(1);
	}

	/**
	 * Returns the type of this IOMessage.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	public int getEioType() {
		return eio_type;
	}
	
	public int getSioType() {
		return sio_type;
	}
	
	

	/**
	 * Returns the id of this IOMessage.
	 * 
	 * @return the id
	 */
	public String getId() {
		return fields[FIELD_ID];
	}

	/**
	 * Sets the id of this IOMessage
	 * 
	 * @param id
	 */
	public void setId(String id) {
		fields[FIELD_ID] = id;
	}

	/**
	 * Returns the endpoint of this IOMessage.
	 * 
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return fields[FIELD_ENDPOINT];
	}

	/**
	 * Returns the data of this IOMessage.
	 * 
	 * @return the data
	 */
	public String getData() {
		return fields[FIELD_DATA];
	}

}
