package desafio.grupo2.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "produtos")
public class Produto {
	
	@Id
	@Column(name = "id")
	private int id; 
	@Column(name = "nome")
	private String nome; 
	@Column(name = "descricao")
	private String descricao; 
	@Column(name = "quantidade")
	private int quantidade; 
	@Column(name = "data")
	private String data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Produto(int id, String nome, String descricao, int quantidade, String data) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.data = data;
	} 
}
