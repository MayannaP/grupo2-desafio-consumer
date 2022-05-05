package desafio.grupo2.models;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "products")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@Column(name = "prod_name")
	private String nome; 
	@Column(name = "description")
	private String descricao; 
	@Column(name = "quantity")
	private Integer quantidade; 
	@Column(name = "date")
	private Timestamp data;
	@Column(name = "price")
	private Double preco;
	@Column(name = "order_id")
	private Integer orderId;
	
	
	
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public Produto(String nome, String descricao, Integer quantidade, Double preco ,Timestamp data, Integer orderId) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.data = data;
		this.preco = preco;
		this.orderId = orderId; 
	}
	public Produto() {
		// TODO Auto-generated constructor stub
	}
	
}
