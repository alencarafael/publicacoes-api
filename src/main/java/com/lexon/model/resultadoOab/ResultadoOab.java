package com.lexon.model.resultadoOab;

import com.lexon.model.CadastroPublicacao;
import com.lexon.model.Tribunal;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "resultado_oab")
public class ResultadoOab extends PanacheEntityBase {

    @Id
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "cadastro_publicacao_id", nullable = false)
    public CadastroPublicacao cadastroPublicacao;

    @ManyToOne
    @JoinColumn(name = "tribunal_id", nullable = false)
    public Tribunal tribunal;

    @ManyToOne
    @JoinColumn(name = "resultado_oab_tipo_id")
    public ResultadoOabTipo tipo;

    @ManyToOne
    @JoinColumn(name = "resultado_oab_setor_id")
    public ResultadoOabSetor setor;

    @Column(name = "id_publicacao")
    public Integer idPublicacao;

    public String ano;
    public String issn;
    public String titulo;
    public String conteudo;

    @Column(name = "conteudo_codificado", columnDefinition = "TEXT")
    public String conteudoCodificado;

    @Column(name = "local_publicacao")
    public String localPublicacao;

    @Column(name = "data_publicacao")
    public LocalDate dataPublicacao;

    public Integer pagina;
    public Integer edicao;

    @Column(name = "edicao_extra")
    public String edicaoExtra;
}
