package io.maxsanttos.vendas2.domain.entity;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "Informe um CPF válido.")
    private String cpf;

    @JsonIgnore
    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
    private Set<Pedido> pedidos;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
