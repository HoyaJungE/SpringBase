 package base.common.common;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CamelCaseMap extends HashMap { 
	private static final long serialVersionUID = 1L;
	
	@Override
	public Object put(Object key, Object value)
	{
		return super.put(columnNameConvert((String)key), convertIfLob(value));
	}
	
	public Object convertIfLob(Object object) {
		Object result = object;
		if ((object instanceof Blob)) {
			try {
				Blob blob = (Blob)object;
				result = blob.getBytes(1L, (int)blob.length());
			}catch (SQLException e) {
				throw new RuntimeException("Exception occurred when convert Blob to byte[]", e);
			}
		}
		else if ((object instanceof Clob)) {
			try {
				Clob clob = (Clob)object;
				result = clob.getSubString(1L, (int)clob.length());
			} catch (SQLException e) {
				throw new RuntimeException("Exception occurred when convert Clob to String", e);
			}
		}
		return result;
	}
	
	public String columnNameConvert(String columnName) {
		String newColumnName = null;
		
		if (columnName.indexOf("_") == -1 && (Character.isLowerCase(columnName.charAt(0)))) {
			return columnName;
		}else {
			StringBuffer sb = new StringBuffer();
			boolean isFirst = true;
			StringTokenizer tokenizer = new StringTokenizer(columnName, "_");
			while (tokenizer.hasMoreTokens()) {
				if(isFirst)
					sb.append(tokenizer.nextToken().toLowerCase());
				else
					sb.append(org.apache.commons.lang3.StringUtils.capitalize((tokenizer.nextToken().toLowerCase())));
				
				isFirst = false;
			}
			newColumnName = sb.toString();
		}
		
		return newColumnName;
	}
}
