package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contrato")
public class Contrato {

	@Id
	private String id;

	private int valor;

	private String tipoEquipo;

	private String estado;

	@DBRef
	private Cliente cliente;

	@DBRef
	private Usuario usuario;

	public Contrato() {
		super();
	}

	public Contrato(String id, int valor, String tipoEquipo, String estado, Cliente cliente, Usuario usuario) {
		super();
		this.id = id;
		this.valor = valor;
		this.tipoEquipo = tipoEquipo;
		this.estado = estado;
		this.cliente = cliente;
		this.usuario = usuario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getTipoEquipo() {
		return tipoEquipo;
	}

	public void setTipoEquipo(String tipoEquipo) {
		this.tipoEquipo = tipoEquipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
