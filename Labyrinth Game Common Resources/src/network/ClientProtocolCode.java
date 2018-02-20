package network;

public enum ClientProtocolCode {
	NULL_CODE((byte)128),
	PLAY((byte)1), QUIT((byte)2),
		LEFT_PRESSED((byte)10), LEFT_RELEASED((byte)11),
		RIGHT_PRESSED((byte)12), RIGHT_RELEASED((byte)13),
		UP_PRESSED((byte)14), UP_RELEASED((byte)15),
		DOWN_PRESSED((byte)16), DOWN_RELEASED((byte)17);
	
	private byte byteValue;
	
	private static ClientProtocolCode[] values = ClientProtocolCode.values();
	
	private ClientProtocolCode(byte valueP){
		byteValue = valueP;
	}
	public byte getByteValue(){
		return byteValue;
	}
	/*public static ClientProtocolCode getTypeFromByte(byte valueP){
		ClientProtocolCode[] array = values;
		for(int i = 0; i < array.length; i++){
			if(array[i].getByteValue() == valueP) return array[i];
		}
		return null;
	}*/ //TODO not sure if necessary.
}