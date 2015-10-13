/*******************************************************************************
 * Copyright (c) 2013, 2014 Lectorius, Inc.
 * Authors:
 * Vijay Pandurangan (vijayp@mitro.co)
 * Evan Jones (ej@mitro.co)
 * Adam Hilss (ahilss@mitro.co)
 *
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *     You can contact the authors at inbound@mitro.co.
 *******************************************************************************/
package co.mitro.core.server.data;

import java.util.Date;

import com.google.gson.Gson;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "email_queue_sent")
public class DBEmailQueueSent {
	// Gson is thread-safe.
	private static final Gson gson = new Gson();

	@DatabaseField(generatedId = true, canBeNull = false)
	private int id;

	@DatabaseField(columnName = "type_string", canBeNull = false)
	private String typeString;

	@DatabaseField(columnName = "arg_string", canBeNull = true, dataType = DataType.LONG_STRING)
	private String argString;

	@DatabaseField(columnName = "mandrill_template_name", canBeNull = true, dataType = DataType.LONG_STRING)
	public String mandrillTemplateName;

	@DatabaseField(columnName = "mandrill_template_param_map_json", canBeNull = true, dataType = DataType.LONG_STRING)
	public String mandrillTemplateParamMapJson;

	// This uses SQL TIMESTAMP which has no time zone information
	// TODO: change this to dataType=DataType.DATE_LONG to avoid timezone
	// troubles
	@DatabaseField(columnName = "attempted_time")
	private Date attemptedTime;

	public int getId() {
		return id;
	}

	public DBEmailQueue.Type getType() {
		DBEmailQueue.Type t = DBEmailQueue.Type.fromString(typeString);
		assert t != null : "Unsupported typeString: " + typeString;
		return t;
	}

	public static String[] decodeArguments(String argString_) {
		return gson.fromJson(argString_, String[].class);
	}

	public String[] getArguments() {
		return decodeArguments(argString);
	}

	public String getPlainArguments() {
		return argString;
	}
}
