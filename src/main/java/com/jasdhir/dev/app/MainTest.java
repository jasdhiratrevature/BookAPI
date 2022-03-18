package com.jasdhir.dev.app;



import java.sql.Timestamp;

import com.google.gson.GsonBuilder;

public class MainTest {
public static void main(String[] args) {
	Timestamp t = new Timestamp(System.currentTimeMillis());

	String json = new GsonBuilder()
	               .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
	               .create()
	               .toJson(t);

	System.out.println(json);
}
}
