package uy.ort.edu.ae2.abb;

import java.util.Objects;

public class Animal {

	
	private long codigo;//ejemplo 202109130 ->>->>> 2021-09-13-0
	private String nombre;
	
	
	public Animal(long codigoFecha,int codigoGenerico, String nombre) {
		super();
		this.codigo = Long.parseLong(String.format("%s%3s", codigoFecha,codigoGenerico).replace(" ", "0"));
		this.nombre = nombre;
	}
	
	public String toString() {
		String codString=""+codigo;
		return nombre+"["+codString.substring(0,4)+"-"+codString.substring(4,6)+"-"+codString.substring(6,8)+"-"+codString.substring(8)+"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return codigo == other.codigo;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
	
}
