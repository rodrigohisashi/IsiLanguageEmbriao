package br.com.professorisidro.isilanguage.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class IsiSymbolTable {
	
	private HashMap<String, IsiSymbol> map;
	
	public IsiSymbolTable() {
		map = new HashMap<String, IsiSymbol>();
		
	}
	
	public void add(IsiSymbol symbol) {
		map.put(symbol.getName(), symbol);
	}
	
	public boolean exists(String symbolName) {
		return map.get(symbolName) != null;
	}

	public void attributeValue(String symbolName, IsiSymbol newValue) {
		map.replace(symbolName, newValue);
	}
	
	public IsiSymbol get(String symbolName) {
		return map.get(symbolName);
	}
	
	public ArrayList<IsiSymbol> getAll(){
		ArrayList<IsiSymbol> lista = new ArrayList<IsiSymbol>();
		for (IsiSymbol symbol : map.values()) {
			lista.add(symbol);
		}
		return lista;
	}

	public void notUsed(){
		for (IsiSymbol symbol : map.values()) {
			IsiVariable s = (IsiVariable) symbol;
			if (s.getValue()==null) {
				System.out.println("Variable [" + s.getName() + "] was declared but never used");
			}
		}
	}

	
	
}
