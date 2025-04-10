package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "comunicacao")
public class Comunicacao extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long id_publicacao;

    @Column(name = "data_disponibilizacao")
    public String dataDisponibilizacao;

    @Column(name = "sigla_tribunal")
    public String siglaTribunal;

    @Column(name = "tipo_comunicacao")
    public String tipoComunicacao;

    @Column(name = "nome_orgao")
    public String nomeOrgao;

    @Lob
    public String texto;

    @Column(name = "numero_processo")
    public String numeroProcesso;

    public String meio;

    public String link;

    @Column(name = "tipo_documento")
    public String tipoDocumento;

    @Column(name = "nome_classe")
    public String nomeClasse;

    @Column(name = "codigo_classe")
    public String codigoClasse;

    @Column(name = "numero_comunicacao")
    public Integer numeroComunicacao;

    public boolean ativo;

    public String hash;

    public String meiocompleto;

    @Column(name = "numero_processo_com_mascara")
    public String numeroProcessoComMascara;

    @OneToMany(mappedBy = "comunicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Destinatario> destinatarios;

    @OneToMany(mappedBy = "comunicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DestinatarioAdvogado> destinatarioAdvogados;
}
