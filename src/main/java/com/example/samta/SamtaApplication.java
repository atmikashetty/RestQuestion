package com.example.samta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.samta.service.FetchData;
@SpringBootApplication
public class SamtaApplication implements CommandLineRunner{
	
	@Autowired
	private FetchData  data;

	public static void main(String[] args) {
		SpringApplication.run(SamtaApplication.class, args);
	}
	
	public void run(String... args) {
		data.deleteAllData();
		data.saveData();
	}
}
