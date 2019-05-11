package br.newtonpaiva.estrutura.dados.lista.vetor.model;

public class Aluno {
	private int ra;
	private String nome;
	private String curso;

	public int getRa() {
		return ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return String.format("RA: %d, Nome: %s, Curso: %s", getRa(), getNome(), getCurso());
	}
	
}
