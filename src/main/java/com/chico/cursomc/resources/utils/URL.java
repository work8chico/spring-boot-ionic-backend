package com.chico.cursomc.resources.utils;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
		public static String decodeParam(String s) {
	
		try {
			
			return URLDecoder.decode(s, "UTF-8");
			
		} catch (Exception e) {
			return "";
		}
		
		
	}
	
	
	public static List<Integer> decodeIntList(String s){
		
		String [] vet = s.split(",");
		List<Integer> list = new ArrayList<>();		
		for (int i=0; i<vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));			
		}
		
		return list;
		// Forma Lambda de escrever o memo código acima.
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
				
	}


}
